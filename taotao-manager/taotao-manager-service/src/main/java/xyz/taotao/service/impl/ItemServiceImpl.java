package xyz.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.taotao.mapper.TbItemMapper;
import xyz.taotao.pojo.EasyUIDataGridResult;
import xyz.taotao.pojo.TbItem;
import xyz.taotao.pojo.TbItemExample;
import xyz.taotao.redis.JedisClient;
import xyz.taotao.service.ItemService;
import xyz.taotao.utils.JsonUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/05 19:02
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    TbItemMapper tbItemMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("ITEM_LIST_KEY")
    private String ITEM_LIST_KEY;

    /**
     * 为查询列表项添加缓存
     * 结构：Hash
     * <p>
     * item_list_key
     * |
     * 1,50  ——  EasyUIDataGridResult Json字符串
     * 2,50  ——  EasyUIDataGridResult Json字符串
     * 3,50  ——  EasyUIDataGridResult Json字符串
     * <p>
     * 缓存不能影响业务流程，用try-catch起来。
     *
     * @param page 查询页码
     * @param rows 查询行数
     * @return EasyUIDataGridResult
     */
    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //查询缓存
        try {
            String json = jedisClient.hget(ITEM_LIST_KEY, page + "-" + rows);
            //判断json是否为空
            if (StringUtils.isNotBlank(json)) {
                //把json转换成list
                System.out.println("获得缓存");
                return JsonUtils.jsonToPojo(json, EasyUIDataGridResult.class);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        //设置分页信息
        PageHelper.startPage(page, rows);
        //查询数据，设置查询条件
        TbItemExample example = new TbItemExample();
        List<TbItem> tbItemList = tbItemMapper.selectByExample(example);
        //封装分页结果集
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);
        //创建分页对象
        EasyUIDataGridResult result = new EasyUIDataGridResult((int) pageInfo.getTotal(), pageInfo.getList());

        //向缓存中添加数据
        try {
            jedisClient.hset(ITEM_LIST_KEY, page + "-" + rows, JsonUtils.objectToJson(result));
            System.out.println("放入redis -> " + page + "-" + rows);
            System.out.println(JsonUtils.objectToJson(result));
        } catch (Exception e) {
//            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
}

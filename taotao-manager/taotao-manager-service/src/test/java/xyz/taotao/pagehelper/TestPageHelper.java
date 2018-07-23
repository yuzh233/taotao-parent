package xyz.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.taotao.mapper.TbContentCategoryMapper;
import xyz.taotao.mapper.TbItemMapper;
import xyz.taotao.pojo.TbContentCategory;
import xyz.taotao.pojo.TbItem;
import xyz.taotao.pojo.TbItemExample;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/05 14:36
 */
public class TestPageHelper {

    @Test
    public void testPageHelper() {
        //初始化spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
        //获取mapper代理对象
        TbItemMapper tbItemMapper = ac.getBean(TbItemMapper.class);

        //设置分页信息
        PageHelper.startPage(3, 100);
        //调用mapper方法查询数据
        //查询条件对象
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        //创建时间不为空才查询，免去了mapper中编辑sql
        criteria.andCreatedIsNotNull();
        //第一个查询列表被分页
        List<TbItem> tbItemList = tbItemMapper.selectByExample(tbItemExample);
        //第二个不会被分页
        List<TbItem> tbItemList2 = tbItemMapper.selectByExample(tbItemExample);
        //获取分页信息
        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItemList);
        //遍历信息列表
        tbItemPageInfo.getList();
        System.out.println("被分页后条数："+tbItemPageInfo.getEndRow());
        System.out.println("总记录数"+tbItemPageInfo.getTotal());
        System.out.println("页数"+tbItemPageInfo.getPages());
        System.out.println("当前页"+tbItemPageInfo.getPageNum());
        System.out.println("页大小"+tbItemPageInfo.getPageSize());

        int i = 1;
        for (TbItem item : tbItemList2) {
            i++;
        }
        System.out.println("全部条数："+i);
    }
}

package xyz.taotao.service;

import xyz.taotao.pojo.EasyUIDataGridResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/05 18:57
 */
public interface ItemService {

    /**
     * 查询商品列表
     * @param page 查询页码
     * @param rows 查询行数
     * @return
     */
    public EasyUIDataGridResult getItemList(int page, int rows);

}

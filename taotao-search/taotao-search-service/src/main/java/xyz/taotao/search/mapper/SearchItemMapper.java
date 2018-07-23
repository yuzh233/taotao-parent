package xyz.taotao.search.mapper;

import xyz.taotao.pojo.SearchItem;

import java.util.List;

/**
 * 定义Mapper 关联查询3张表 查询出搜索时的商品数据
 */
public interface SearchItemMapper {
	//查询所有的商品的数据
	public List<SearchItem> getSearchItemList();
}

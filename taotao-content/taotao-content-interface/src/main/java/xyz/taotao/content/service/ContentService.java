package xyz.taotao.content.service;


import xyz.taotao.pojo.TaotaoResult;
import xyz.taotao.pojo.TbContent;

/**
 * 内容处理的接口
 */
public interface ContentService {
	/**
	 * 插入内容表
	 * @param content
	 * @return
	 */
	public TaotaoResult saveContent(TbContent content);
}

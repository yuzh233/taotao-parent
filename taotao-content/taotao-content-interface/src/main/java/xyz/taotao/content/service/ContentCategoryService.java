package xyz.taotao.content.service;


import xyz.taotao.pojo.EasyUITreeNode;
import xyz.taotao.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {

    //通过节点的id查询该节点的哦子节点列表
    public List<EasyUITreeNode> getContentCategoryList(Long parentId);

    /**
     * @param parentId 父节点的id
     * @param name     新增节点的名称
     * @return
     */
    public TaotaoResult createContentCategory(Long parentId, String name);
}

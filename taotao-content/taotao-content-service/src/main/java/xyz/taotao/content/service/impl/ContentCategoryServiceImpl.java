package xyz.taotao.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.taotao.content.service.ContentCategoryService;
import xyz.taotao.mapper.TbContentCategoryMapper;
import xyz.taotao.pojo.EasyUITreeNode;
import xyz.taotao.pojo.TaotaoResult;
import xyz.taotao.pojo.TbContentCategory;
import xyz.taotao.pojo.TbContentCategoryExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/08 16:15
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper mapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        //1.注入mapper
        //2.创建example
        TbContentCategoryExample example = new TbContentCategoryExample();
        //3.设置条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);//select * from tbcontentcategory where parent_id=1
        //4.执行查询
        List<TbContentCategory> list = mapper.selectByExample(example);
        //5.转成EasyUITreeNode 列表

        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            node.setText(tbContentCategory.getName());//分类名称
            nodes.add(node);
        }
        //6.返回
        return nodes;
    }

    @Override
    public TaotaoResult createContentCategory(Long parentId, String name) {
        //1.构建对象  补全其他的属性
        TbContentCategory category = new TbContentCategory();
        category.setCreated(new Date());
        category.setIsParent(false);//新增的节点都是叶子节点
        category.setName(name);
        category.setParentId(parentId);
        category.setSortOrder(1);
        category.setStatus(1);
        category.setUpdated(category.getCreated());
        //2.插入contentcategory表数据
        mapper.insertSelective(category);
        //3.返回taotaoresult 包含内容分类的id   需要主键返回

        //判断如果要添加的节点的父节点本身叶子节点  需要更新其为父节点
        TbContentCategory parent = mapper.selectByPrimaryKey(parentId);
        if (parent.getIsParent() == false) {//原本就是叶子节点
            parent.setIsParent(true);
            mapper.updateByPrimaryKeySelective(parent);//更新节点的is_parent属性为true
        }

        return TaotaoResult.ok(category);
    }
}

package xyz.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.taotao.content.service.ContentCategoryService;
import xyz.taotao.pojo.EasyUITreeNode;
import xyz.taotao.pojo.TaotaoResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/08 20:04
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(
            @RequestParam(value="id", defaultValue="0") Long parentId) {

        List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createCategory(Long parentId, String name) {
        TaotaoResult result = contentCategoryService.createContentCategory(parentId, name);
        return result;
    }
}
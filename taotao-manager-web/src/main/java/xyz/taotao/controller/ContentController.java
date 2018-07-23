package xyz.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.taotao.content.service.ContentService;
import xyz.taotao.pojo.TaotaoResult;
import xyz.taotao.pojo.TbContent;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/08 20:54
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent content) {
        TaotaoResult result = contentService.saveContent(content);
        return result;
    }
}

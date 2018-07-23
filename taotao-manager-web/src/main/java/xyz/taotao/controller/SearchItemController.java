package xyz.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.taotao.pojo.TaotaoResult;
import xyz.taotao.search.service.SearchService;

@Controller
public class SearchItemController {

    @Autowired
    private SearchService searchItemService;

    @RequestMapping("/index/importAll")
    @ResponseBody
    public TaotaoResult importAllItems() {
        try {
            TaotaoResult result = searchItemService.importAllSearchItems();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, "导入数据失败");
        }
    }
}
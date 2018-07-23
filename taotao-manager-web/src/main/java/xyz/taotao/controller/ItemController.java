package xyz.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.taotao.pojo.EasyUIDataGridResult;
import xyz.taotao.service.ItemService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/05 19:16
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult itemList(Integer page, Integer rows){
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
}

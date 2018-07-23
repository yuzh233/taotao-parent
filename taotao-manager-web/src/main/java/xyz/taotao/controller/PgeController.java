package xyz.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/05 2:42
 */
@Controller
public class PgeController {

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
     * 显示商品的查询的页面
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}

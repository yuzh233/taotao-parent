package xyz.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.taotao.service.TestService;

/**
 * Created with IntelliJ IDEA.
 * @Author: yu_zh
 * @DateTime: 2018/07/04 20:48
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    /**
     * 测试dubbo配置是否正常
     * @return
     */
    @RequestMapping("/test/queryNow")
    @ResponseBody
    public String queryNow(){
        return testService.queryNow();
    }

}

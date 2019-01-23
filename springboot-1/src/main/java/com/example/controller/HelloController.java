package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api("配置测试Controller")
@Controller
@RequestMapping("/HelloController")

public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "测试接口",notes = "测试SpringBoot的配置",httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/index")
    public String index(){
        logger.info("日志输出：这是一个请求。。。");
        return "index";
    }
}

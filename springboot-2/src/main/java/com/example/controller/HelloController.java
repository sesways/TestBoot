package com.example.controller;

import com.example.exception.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Api("配置测试Controller")
@Controller
@RequestMapping("/HelloController")

public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "测试接口",notes = "测试SpringBoot的配置",httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        logger.info("日志输出：这是一个请求。。。");
        return "index";
    }

    @ApiOperation(value = "群居异常测试",notes = "测试全局异常是否配置成功,当前端传入的code为1时，发生异常。",httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/testException",method = RequestMethod.GET)
    public Map<String,String> testException(String code){
        if ("1".equals(code)){
            throw new GlobalException("101","程序发生异常");
        }
        Map<String,String> map = new HashMap<>();
        map.put("success","true");
        return map;
    }
}

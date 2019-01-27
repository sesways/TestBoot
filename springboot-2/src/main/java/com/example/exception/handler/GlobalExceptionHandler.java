package com.example.exception.handler;

import com.example.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @Description:当@RequestMapping注释的方法抛出此类异常时，被改方法拦截；
     * 返回的数据类型是JSON类型；
     * @Auth: ZC
     * @DateTime: 2019/1/27 18:50
     * @Email: chao_actor@163.com
     */
//    @ResponseBody
//    @ExceptionHandler(value = GlobalException.class)
//    public Map throwGlobalException(GlobalException e){
//        logger.error(format.format(new Date())+" 请求发生GlobalException：" + e);
//        Map<String,String> map = new HashMap<>();
//        map.put("code",e.getCode());
//        map.put("msg",e.getInfo());
//        return map;
//    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map throwException(Exception e){
        logger.error(format.format(new Date())+" 请求发生Exception：" + e);
        Map<String,String> map = new HashMap<>();
        map.put("class",e.getClass().toString());
        map.put("msg",e.getMessage());
        return map;
    }

    /**
     * @Description:可以指定返回的视图；
     * @Auth: ZC
     * @DateTime: 2019/1/27 20:04
     * @Email: chao_actor@163.com
     */
    @ExceptionHandler(value = GlobalException.class)
    public ModelAndView throwExceptionForView(GlobalException e){
        Map<String,String> map = new HashMap<>();
        map.put("code",e.getCode());
        map.put("msg",e.getInfo());
        logger.error(format.format(new Date())+" 请求发生GlobalException：" + e);
        return new ModelAndView("hello/error",map);
    }
}

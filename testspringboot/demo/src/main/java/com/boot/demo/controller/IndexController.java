/**
 * @Project Name:testspringboot
 * @Package Name:com.boot.demo.controller
 */
package com.boot.demo.controller;

import com.boot.demo.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/11/2 16:38
 */
@Controller
public class IndexController {

    /**
     *@Description: 此处一定要使用model进行数据绑定，否则前端无法解析；
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/11/2 17:27
     *@Params: [model]
     *@ReturnType: java.lang.String
     */
    @RequestMapping("/")
    public String index(Model model){
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);

        ArrayList<String> names = new ArrayList<>();
        names.add("李四");
        names.add("王五");
        names.add("赵六");
        names.add("刘七");

        model.addAttribute("person",person);
        model.addAttribute("names",names);
        return "index/index";
    }
}

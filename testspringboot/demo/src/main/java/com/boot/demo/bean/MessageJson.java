/**
 * @Project Name:testspringboot
 * @Package Name:com.boot.demo.bean
 */
package com.boot.demo.bean;

import lombok.Data;

import java.util.Map;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/11/2 22:47
 */
@Data
public class MessageJson {
    private int code;
    private String time;
    private String description;
    private Map<String,Object> resultDate;
}

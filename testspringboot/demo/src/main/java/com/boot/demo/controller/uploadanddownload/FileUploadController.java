/**
 * @Project Name:testspringboot
 * @Package Name:com.boot.demo.controller.uploadanddownload
 */
package com.boot.demo.controller.uploadanddownload;

import com.boot.demo.bean.MessageJson;
import com.boot.demo.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/11/2 17:37
 */
@Controller
@RequestMapping("/FileUploadController")
public class FileUploadController {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *@Description: 跳转至文件上传页面
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/11/2 17:47
     *@Params: []
     *@ReturnType: java.lang.String
     */
    @RequestMapping("/toviewUploadPage")
    public String toviewUploadPage(){
        return "fileupload/uploadPage";
    }

    /**
     *@Description: 文件的上传功能；[ajax实现]
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/11/3 10:55
     *@Params: [request, model]
     *@ReturnType: com.boot.demo.bean.MessageJson
     */
    @ResponseBody
    @RequestMapping("/fileUpload")
    public MessageJson fileUpload(HttpServletRequest request, Model model) throws IOException {
        MessageJson messageJson = new MessageJson();
        HashMap<String, Object> map = new HashMap<>();
//        将HttpServletRequest转换成multipartHttpServletRequest并获取上传文件；
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        if (!file.isEmpty()){
//            设置上传文件的写入文件
            String file_Type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String file_Path = new String("D:\\");
            String file_Name = UUID.randomUUID().toString().replaceAll("-", "");
            File ord_file = new File(file_Path + file_Name + file_Type);
            map.put("fileName",file_Name);
            map.put("filePath",file_Path);
            map.put("fileType",file_Type);
//            开始保存上传文件
            FileOutputStream outputStream = new FileOutputStream(ord_file);
            InputStream inputStream = file.getInputStream();
            byte[] bytes = new byte[1024];
            int c = -1;
            while ((c = inputStream.read(bytes,0,1024)) != -1){
                outputStream.write(bytes,0,c);
            }
//            关闭文件流
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
//        设置返回数据的信息；
        messageJson.setCode(200);
        messageJson.setResultDate(map);
        messageJson.setTime(dateFormat.format(new Date()));
        messageJson.setDescription("成功上传文件");

        return messageJson;
    }

    /**
     *@Description: 一次上传多个文件；[表单提交的方式]
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/11/3 13:37
     *@Params: []
     *@ReturnType: java.lang.String
     */
    @RequestMapping("/multipartUpload")
    public String multipartUpload(MultipartHttpServletRequest request) throws IOException {
        List<MultipartFile> files = request.getFiles("file_image");
        File ord_file = null;
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (!file.isEmpty()){
//            设置上传文件的写入文件
                String file_Type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String file_Path = new String("D:\\upload\\");
                String file_Name = UUID.randomUUID().toString().replaceAll("-", "");
                ord_file = new File(file_Path + file_Name + file_Type);
//            开始保存上传文件
                outputStream = new FileOutputStream(ord_file);
                inputStream = file.getInputStream();
                byte[] bytes = new byte[1024];
                int c = -1;
                while ((c = inputStream.read(bytes,0,1024)) != -1){
                    outputStream.write(bytes,0,c);
                }
            }
        }
//        关闭文件流
        if (outputStream != null){
            outputStream.flush();
            outputStream.close();
        }
        if (inputStream != null){
            inputStream.close();
        }
        return "fileupload/uploadPage";
    }


}

/**
 * @Project Name:testspringboot
 * @Package Name:com.boot.demo.controller.uploadanddownload
 */
package com.boot.demo.controller.uploadanddownload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/11/3 14:31
 */
@Controller
@RequestMapping("/FileDownLoadController")
public class FileDownLoadController {

    @RequestMapping("/toviewFileDownload")
    public String toviewFileDownload(){
        return "filedownload/filedownloadpage";
    }

    /**
     *@Description: 文件下载；
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/11/3 14:26
     *@Params: [request, response]
     *@ReturnType: java.lang.String
     */
    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        if (true) {
            //设置文件路径
            File file = new File("D:\\upload\\d4ca20d28d524134833888e5d30c4333.jpg");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "fileupload/uploadPage";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "fileupload/uploadPage";
    }
}

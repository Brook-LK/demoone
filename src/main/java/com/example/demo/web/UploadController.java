package com.example.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
public class UploadController {

    @Value("${file.path}")
    private String filePathDev;

    @RequestMapping(value = "toUpload", method = RequestMethod.GET)
    public String toUpload(){
        return "upload";
    }

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartFile file, HttpServletRequest request){
        try{
            //创建文件在服务器上的地址
//            File localFilePath = new File("src/main/resources/static/");
            String dir = request.getServletContext().getRealPath("/upload");  //得到在服务器上存储的绝对路径
            File filePath = new File(filePathDev);
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            //生成文件在服务器存放名
            String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID().toString()+fileSuffix;
            File files = new File(filePath+"/"+fileName);
            //上传
            file.transferTo(files);
        }catch(Exception e){
            return "上传失败";
        }
        return "success";
    }

    @RequestMapping(value = "toUploads", method = RequestMethod.GET)
    public String toUploads(){
        return "uploads";
    }

    @RequestMapping(value="/upload/batch",method= RequestMethod.POST)
    @ResponseBody
    public String uploadFiles(MultipartFile[] file, HttpServletRequest request){
        try{
            //创建文件在服务器上的地址
//            File localFilePath = new File("src/main/resources/static/");
            String dir = request.getServletContext().getRealPath("/upload");  //得到在服务器上存储的绝对路径
            File filePath = new File(dir);
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            for(int i=0;i<file.length;i++){
                //生成文件在服务器存放名
                String fileSuffix = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));
                String fileName = UUID.randomUUID().toString()+fileSuffix;
                File files = new File(filePath+"/"+fileName);
                //上传
                file[i].transferTo(files);
            }

        }catch(Exception e){
            return "上传失败";
        }
        return "success";
    }
}

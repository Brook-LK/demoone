package com.example.demo.service.impl;

import com.example.demo.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class DownloadServiceImpl extends HttpServlet implements DownloadService {

    @Autowired
    private ServletContext getServletContext;

    //传入fileName参数
    @Override
    public void downloadFile(String fileNames,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("fileName");
        String path = this.getServletContext.getRealPath("download/"+fileName);
        ServletOutputStream out = response.getOutputStream();
        //设置浏览器不解析，而是下载
        String mimeType = this.getServletContext.getMimeType(fileName);
        response.setContentType(mimeType);
        //设置为原下载名保存
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        FileInputStream in = new FileInputStream(path);
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=in.read(buffer))!=-1){
            out.write(buffer,0,len);
        }
        in.close();
    }
}

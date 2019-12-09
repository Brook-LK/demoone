package com.example.demo.web;

import com.example.demo.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    //@RequestMapping(value = "/download/{fileNames}",method = RequestMethod.GET)
    //通过servlet下载文件不能上面这样写，这样就直接下载fileNames（因为文件名.的原因）了挑不到后面request传入的文件了
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        downloadService.downloadFile("fileNames",request,response);
    }
}

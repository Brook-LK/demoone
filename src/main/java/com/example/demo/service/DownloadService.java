package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface DownloadService {
    public void downloadFile(String fileNames,HttpServletRequest request, HttpServletResponse response) throws IOException;
}

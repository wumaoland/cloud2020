package com.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.easyexcel.common.UploadDataListener;
import com.easyexcel.pojo.Job;
import com.easyexcel.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/upload")
    public void upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Job.class,new UploadDataListener(jobService)).sheet(1).doRead();
    }
}

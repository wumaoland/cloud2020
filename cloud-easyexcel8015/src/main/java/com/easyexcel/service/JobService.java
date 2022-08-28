package com.easyexcel.service;

import com.easyexcel.mapper.JobMapper;
import com.easyexcel.pojo.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements ExcelBaseService<Job>{
    @Autowired
    private JobMapper jobMapper;

    @Override
    public void excelDataBatchSave(List<Job> list) {
        jobMapper.batchSave(list);
    }
}

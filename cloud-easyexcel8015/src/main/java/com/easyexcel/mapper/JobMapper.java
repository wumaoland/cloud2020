package com.easyexcel.mapper;

import com.easyexcel.pojo.Job;
import com.easyexcel.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JobMapper {
    void batchSave(List<Job> jobs);
}

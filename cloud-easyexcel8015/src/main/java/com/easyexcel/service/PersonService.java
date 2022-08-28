package com.easyexcel.service;

import com.easyexcel.mapper.PersonMapper;
import com.easyexcel.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements ExcelBaseService<Person>{
    @Autowired
    PersonMapper personMapper;

    public List<Person> queryPersonList(){
        return personMapper.queryPersonList();
    }


    public void excelDataBatchSave(List<Person> persons) {
        personMapper.batchSave(persons);
    }
}

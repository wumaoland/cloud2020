package com.easyexcel.service;

import com.easyexcel.mapper.PersonMapper;
import com.easyexcel.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonMapper personMapper;

    public List<Person> queryPersonList(){
        return personMapper.queryPersonList();
    }

    public void save(Person person) {
        personMapper.save(person);
    }
}

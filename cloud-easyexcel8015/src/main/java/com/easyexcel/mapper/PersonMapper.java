package com.easyexcel.mapper;

import com.easyexcel.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//这个注解表示这是一个mybatis的mapper类，也可以在启动类上写 @ScanMapper(mapper包的路径) 来扫描
@Mapper
@Repository
public interface PersonMapper {
    List<Person> queryPersonList();
    void batchSave(List<Person> persons);
}

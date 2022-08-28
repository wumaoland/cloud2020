package com.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.easyexcel.common.UploadDataListener;
import com.easyexcel.pojo.Person;
import com.easyexcel.pojo.PersonExcel;
import com.easyexcel.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/queryPersonList")
    public List<Person> queryPersonList(){
        return personService.queryPersonList();
    }

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Person.class, new UploadDataListener(personService)).sheet(0).doRead();
        return "success";
    }

    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试easyexcel", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), PersonExcel.class).sheet("模板").doWrite(data());
    }

    private List<PersonExcel> data() {
        List<PersonExcel> datas = new ArrayList<>();
        List<Person> peoples = personService.queryPersonList();
        peoples.forEach(k->{
            PersonExcel personExcel = new PersonExcel(k.getName(), k.getSex(), k.getAge(), k.getSaveMoney());
            datas.add(personExcel);
        });

        return datas;
    }

}

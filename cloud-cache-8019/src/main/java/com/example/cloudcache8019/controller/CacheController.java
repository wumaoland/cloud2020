package com.example.cloudcache8019.controller;

import com.example.cloudcache8019.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CacheController {
    @Autowired
    private  CacheService cacheService;

    @GetMapping("/save/{username}")
    public int save(@PathVariable("username") String username) {
        int count = cacheService.getUsername(username);
        return cacheService.save(username,count);
    }
    @GetMapping("/get/{username}")
    public String getUsername(@PathVariable("username") String username) {
//       return cacheService.getUsername(username);
        return "哦哟";
    }

    @GetMapping("/remove/{username}")
    public void remove(@PathVariable("username") String username) {
         cacheService.remove(username);
    }
}

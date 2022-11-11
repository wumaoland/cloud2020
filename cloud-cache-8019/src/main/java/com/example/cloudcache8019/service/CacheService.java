package com.example.cloudcache8019.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @CachePut(value = "user", key = "#username")
    public int save(String username,int count) {
        count++;
        return count;
    }
    @Cacheable(value = "user",key = "#username")
    public int getUsername(String username) {
        return 0;
    }
    @CacheEvict(value = "user", key = "#username")
    public void remove(String username) {
        System.out.println("删除用户"+username+"的缓存");
    }
}

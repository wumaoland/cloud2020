package com.performmance.controller;

import com.performmance.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class TestConfigServiceImpl implements TestConfigService {
    @Autowired
    private TestConfig testConfig;


    @Override
    public String test() {
        this.testConfig.setUrl("test");
        return testConfig.getUrl();
    }

    @Override
    public String testConfig() {
        return this.testConfig.getUrl();
    }
}

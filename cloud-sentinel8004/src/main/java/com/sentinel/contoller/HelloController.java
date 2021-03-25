package com.sentinel.contoller;

import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author lw
 * @Date 2021/3/25 0025 22:51
 * @Vsersion 1.0
 **/
@RestController
public class HelloController {
  @GetMapping("/hello")
    public String hello(){
        try {
            SphU.entry("hello");
            return "hello sentinel";
        } catch (BlockException e) {
           return "服务繁忙请稍后";
        }
    }

    /**
     * 定义限流规则
     * @PostConstruct 注解的意思是在类的构造函数执行完毕的时候执行
     */
    @PostConstruct
    public void initSentinelFlowRules(){
        //定义规则集合
        List<FlowRule> flowRules = new ArrayList<>();

        //创建规则并自定义配置
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("hello");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);

        //将自定义配置加入规则集合中
        flowRules.add(flowRule);

        //加载规则
        FlowRuleManager.loadRules(flowRules);
    }

}

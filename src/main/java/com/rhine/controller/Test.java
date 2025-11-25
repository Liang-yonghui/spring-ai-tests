package com.rhine.controller;


import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/dede")
    public String test(){
        return "hello world";
    }
}

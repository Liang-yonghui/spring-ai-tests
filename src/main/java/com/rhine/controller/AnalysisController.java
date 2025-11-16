package com.rhine.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {


    @PostMapping("/ai/analysis")
    public String analysis(@RequestBody MultipartFile file){


        return "hello world";
    }
}

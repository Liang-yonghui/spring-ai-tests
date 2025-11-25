package com.rhine.controller;

import cn.hutool.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @param
 * @author Rhine
 * @date 2025/11/25 22:46
 * @description
 */

@RequestMapping("/report/ai")
public class ReportController {


    @GetMapping("/report/data")
    public JSONObject report()
}

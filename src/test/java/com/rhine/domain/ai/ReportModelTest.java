package com.rhine.domain.ai;

import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import com.rhine.springaitests.SpringAiTestsApplication;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = SpringAiTestsApplication.class)
@ComponentScan(basePackages = "com.rhine")
class ReportModelTest {

    @Resource
    private ReportModel reportModel;
    @Test
    void chatWithAgent() {

        try {
            reportModel.chatWithAgent("你是谁");
        } catch (GraphRunnerException e) {
            throw new RuntimeException(e);
        }
    }
}
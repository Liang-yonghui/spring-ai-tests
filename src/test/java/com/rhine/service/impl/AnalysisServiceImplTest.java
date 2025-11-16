package com.rhine.service.impl;

import com.rhine.service.AnalysisService;
import com.rhine.springaitests.SpringAiTestsApplication;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = SpringAiTestsApplication.class)
@ComponentScan(basePackages = "com.rhine")

public class AnalysisServiceImplTest {

    @Resource
    private AnalysisService analysisService;
    @Qualifier("messageSource")
    @Autowired
    private MessageSource messageSource;

    public String loadInterviewQADocumentFromClasspath() throws IOException, URISyntaxException {
        // Using class loader to get resource
        ClassLoader classLoader = getClass().getClassLoader();
        Path filePath = Paths.get(classLoader.getResource("interview_qa/Java面试Q&A.md").toURI());

        // Read all content as string
        return Files.readString(filePath, StandardCharsets.UTF_8);
    }

    @Test
    public void analysis() throws Exception {
        String s = loadInterviewQADocumentFromClasspath();
//        System.out.println(s);
        Flux<String> analysis = analysisService.analysis(s);
//        log.info(analysis.collectList().block().stream().collect(Collectors.joining()));

    }
}
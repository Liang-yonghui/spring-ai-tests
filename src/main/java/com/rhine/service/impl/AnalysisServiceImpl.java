package com.rhine.service.impl;

import com.rhine.domain.ai.AnalysisModel;
import com.rhine.service.AnalysisService;
import jakarta.annotation.Resource;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;


@Service
public class AnalysisServiceImpl implements AnalysisService {


    @Resource
    private AnalysisModel analysisModel;



    @Override
    public Flux<String> analysis(String text) {
        ChatClient chatClient = analysisModel.getChatClient();

        // 示例：同步获取响应 - 使用默认配置的advisors
        Flux<String> content = chatClient.prompt()
                .user(text)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "1"))
                .stream()
                .content();

        // 方式1: 收集所有字符串并拼接（同步阻塞）
        String result = content.collectList().block().stream().collect(Collectors.joining());
        System.out.println(result);
        // 方式2: 获取第一个字符串
        // String firstResult = content.next().block();

        // 方式3: 获取最后一个字符串
        // String lastResult = content.last().block();

        // 方式4: 异步处理每个元素
        // content.subscribe(chunk -> System.out.println("收到: " + chunk));
        return content;
    }


    public void testReactAgent(String message){

    }
}

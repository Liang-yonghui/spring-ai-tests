package com.rhine.controller;


import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.memory.repository.jdbc.MysqlChatMemoryRepositoryDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/dashscope")
public class DashScopeModelChatController {

    private final static String defaultPrompt = "You are a helpful assistant.";

    private ChatClient dashScopeClient;

    private JdbcTemplate jdbcTemplate;

    DashScopeModelChatController(ChatClient.Builder chatClientBuilder){
        // 构造 ChatMemoryRepository 和 ChatMemory
//        ChatMemoryRepository chatMemoryRepository = JdbcChatMemoryRepository.builder()
//                .jdbcTemplate(this.jdbcTemplate)
//                .build();
        InMemoryChatMemoryRepository inMemoryChatMemoryRepository = new InMemoryChatMemoryRepository();
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(inMemoryChatMemoryRepository)
                .build();
        this.dashScopeClient = chatClientBuilder.defaultSystem(defaultPrompt)
                .defaultAdvisors(new SimpleLoggerAdvisor(), MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultOptions(DashScopeChatOptions.builder().withTopP(0.7).build())
                .build();

    }


    @GetMapping("/chat")
    public Flux<String> chat(@RequestParam("prompt") String prompt, @RequestParam("chatId") String chatId){
        // 创建一个新的MessageChatMemoryAdvisor实例，每次请求时设置不同的chatId

        
        // 使用reactive方式处理响应，不阻塞操作
//        return dashScopeClient.prompt()
//                .user(prompt)
//                .advisors(sepc->sepc.param(ChatMemory.CONVERSATION_ID, chatId))
//                .stream()
//                .content();
        Flux<String> content1 = dashScopeClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId)).stream().content();
        String collect = content1.collectList().block().stream().collect(Collectors.joining());
        System.out.println(collect);
        return content1;
    }

}

package com.rhine.domain.ai;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import lombok.Data;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Data
@Component
public class AnalysisModel {

    private DashScopeApi dashScopeApi;

    private ChatModel chatModel;

    private ChatClient chatClient;

    private final String defaultSystem;


    public AnalysisModel(ChatClient.Builder chatClientBuilder) {
        // 读取agent配置文件
        this.defaultSystem = loadDefaultSystem();

        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(System.getenv("AI_DASHSCOPE_API_KEY"))
                .build();

        this.dashScopeApi = dashScopeApi;
        this.chatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .build();

        // 创建聊天记忆
        MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        // 创建ChatClient，使用默认系统提示和顾问
        this.chatClient = ChatClient.builder(chatModel)
                .defaultSystem(defaultSystem)
                .defaultAdvisors(
                    new SimpleLoggerAdvisor(),
                    MessageChatMemoryAdvisor.builder(memory).build()
                )
                .build();
    }

    private String loadDefaultSystem() {
        try {
            ClassPathResource resource = new ClassPathResource("agent/interviewAgent.md");
            return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // 如果读取失败，返回默认提示
            return "现在你是一个面试分析师，你的任务是将下面给到的面试QA结果进行分析";
        }
    }

}

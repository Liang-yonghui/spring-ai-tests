package com.rhine.config;


import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SiliconFlowConfig {
    
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;
    
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    
    @Value("${spring.ai.openai.chat.options.model}")
    private String model;
    
    @Bean
    public OpenAiApi siliconFlowApi() {
        return new OpenAiApi.Builder().apiKey(apiKey)
                .baseUrl(baseUrl)
                .build();
    }
    
    @Bean
    public OpenAiChatOptions siliconFlowChatOptions() {
        return OpenAiChatOptions.builder()
                .model(model)
                .temperature(0.7)
                .maxTokens(2000)
                .build();
    }
    
    @Bean
    @Primary
    public OpenAiChatModel siliconFlowChatModel(OpenAiApi siliconFlowApi,
                                                OpenAiChatOptions siliconFlowChatOptions) {
        return OpenAiChatModel.builder().openAiApi(siliconFlowApi).defaultOptions(siliconFlowChatOptions).build();
    }
    
    @Bean
    public ChatClient siliconFlowChatClient(OpenAiChatModel siliconFlowChatModel) {
        return ChatClient.builder(siliconFlowChatModel)
                .defaultSystem("你是一个由硅基流动提供支持的AI助手")
                .build();
    }

    @Bean
    public ReactAgent siliconFlowReactAgent(OpenAiChatModel siliconFlowChatModel, OpenAiChatOptions siliconFlowChatOptions) {
        return ReactAgent.builder()
                .name("siliconFlowReactAgent")
                .model(siliconFlowChatModel)
                .build();
    }
}
package com.rhine.domain.ai;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@Component
public class ReportModel {

    private ReactAgent reactAgent;

    private ChatModel chatModel;

    private DashScopeApi dashScopeApi;

    private String defaultSystem;

    public ReportModel() {
        // 读取agent配置文件
        this.defaultSystem = loadDefaultSystem();

        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(System.getenv("AI_DASHSCOPE_API_KEY"))
                .build();

        this.dashScopeApi = dashScopeApi;
        this.chatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .build();
        this.reactAgent = ReactAgent.builder()
                .name("report_agent")
                .model(chatModel)
                .instruction(defaultSystem)
                .chatOptions(DashScopeChatOptions.builder()
                        .withToolCallbacks(List.of())
                        .withTopP(0.7)
                        .withMaxToken(2000)
                        .build())
                .build();
    }


    public void chatWithAgent(String message) throws GraphRunnerException {
        Optional<OverAllState> invoke = reactAgent.invoke(message);
        invoke.ifPresent(
                overAllState -> {
                    for (String s : overAllState.data().keySet()) {
                        System.out.println(s + ": " + overAllState.value(s));
                    }
                }
        );
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

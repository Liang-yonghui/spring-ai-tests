package com.rhine.domain.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;

/**
 * @param
 * @author Rhine
 * @date 2025/12/11 21:44
 * @description
 */
public class DemoModel {

    private ChatOptions chatOptions;

    private OpenAiChatModel chatModel;
    public DemoModel(OpenAiChatModel chatModel){
        this.chatModel = chatModel;
    }


    public String call(String question){
        String call = chatModel.call(question);
        return call;
    }

}

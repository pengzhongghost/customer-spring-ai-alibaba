package com.zhong.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder,
                                 ChatMemory chatMemory) {
        return builder
                .defaultFunctions("productFunctionCall")
                //对话记忆功能
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory)).build();
    }

}

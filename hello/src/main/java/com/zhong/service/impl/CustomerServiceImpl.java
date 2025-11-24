package com.zhong.service.impl;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.zhong.pojo.Faq;
import com.zhong.service.CustomerService;
import com.zhong.service.IFaqService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ResourceUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private String prompt = ResourceUtils.getText("classpath:prompt.txt");

    @Resource
    private ChatClient chatClient;

    @Resource
    private IFaqService faqService;

    @Resource
    private SensitiveWordBs sensitiveWordBs;

    @Override
    public String generateResponse(String userMessage, Long userId) {
        if (StringUtils.isEmpty(userMessage)) {
            return "输入的内容不能为空";
        }
        //1.过滤敏感词
        if (sensitiveWordBs.contains(userMessage)) {
            return "内容包含敏感词汇，您的问题我暂时无法回答，让我们换个话题再聊把";
        }
        //2.先查询知识库尝试FAQ回答
        List<Faq> faqs = faqService.similaritySearch(userMessage);
        if (!CollectionUtils.isEmpty(faqs)) {
            return faqs.get(0).getAnswer();
        }
        //3.使用AI进行回答
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        String resolvedPrompt = promptTemplate.render(
                Map.of("current_date", LocalDate.now().toString())
        );
        ChatClient.CallResponseSpec response = chatClient.prompt()
                .system(resolvedPrompt)
                .user(userMessage)
                //记忆管理
                .advisors(advisorSpec -> {
                    //对话记忆用户id
                    advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, userId.toString());
                    //对话记忆每次保留多少条数
                    advisorSpec.param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 3);
                })
                .call();
        return response.content();
    }

}

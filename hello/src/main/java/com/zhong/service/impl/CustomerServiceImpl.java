package com.zhong.service.impl;

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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private String prompt = ResourceUtils.getText("classpath:prompt.txt");

    @Resource
    private ChatClient chatClient;

    @Resource
    private IFaqService faqService;

    @Override
    public String generateResponse(String userMessage) {
        //2.先查询知识库
        List<Faq> faqs = faqService.similaritySearch(userMessage);
        if (!CollectionUtils.isEmpty(faqs)) {
            return faqs.get(0).getAnswer();
        }
        //3.使用AI进行回答
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        String resolvedPrompt = promptTemplate.render(
                Map.of("current_date", LocalDate.now().toString())
        );
        ChatClient.CallResponseSpec response = chatClient.prompt().system(resolvedPrompt).user(userMessage).call();
        return response.content();
    }

}

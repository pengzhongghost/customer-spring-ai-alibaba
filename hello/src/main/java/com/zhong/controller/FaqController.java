package com.zhong.controller;

import com.zhong.pojo.Faq;
import com.zhong.service.IFaqService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faq")
public class FaqController {

    @Resource
    private IFaqService faqService;

    @GetMapping("/syncFaqToQdrant")
    public void syncFaqToQdrant() {
        faqService.syncFaqToQdrant();
    }

    @GetMapping("/similaritySearch")
    public List<Faq> similaritySearch(@RequestParam("message") String message) {
        return faqService.similaritySearch(message);
    }

}

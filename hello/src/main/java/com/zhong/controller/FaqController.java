package com.zhong.controller;

import com.zhong.service.IFaqService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raq")
public class FaqController {

    @Resource
    private IFaqService faqService;

    @GetMapping("/syncFaqToQdrant")
    public void syncFaqToQdrant() {
        faqService.syncFaqToQdrant();
    }

}

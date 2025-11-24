package com.zhong.config;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 敏感词配置类
 */
@Configuration
public class SensitiveWordConfig {

    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        return SensitiveWordBs.newInstance()
                //使用内置的默认黑名单
                .wordDeny(WordDenys.chains(WordDenys.defaults()))
                //使用内置的默认白名单
                .wordAllow(WordAllows.chains(WordAllows.defaults()))
                //忽略大小写
                .ignoreCase(true)
                //忽略半全角
                .ignoreWidth(true)
                //启用邮箱检查
                .enableEmailCheck(true)
                //启用url检查
                .enableUrlCheck(true)
                //启用数字检查
                .enableNumCheck(true)
                .init();
    }

}

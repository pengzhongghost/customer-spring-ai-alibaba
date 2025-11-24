package com.zhong.config;

import com.zhong.functioncall.ProductFunctionCall;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class FunctionCallConfig {

    @Bean
    @Description("查询商品信息，输入商品关键字和查询条数（默认为5）")
    public Function<ProductFunctionCall.Request, ProductFunctionCall.Response> productFunctionCall(){
        return new ProductFunctionCall();
    }

}

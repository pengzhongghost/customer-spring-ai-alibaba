package com.zhong.config;

//import org.apache.http.HttpHost;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class EsConfig {
//
//    @Bean
//    public RestHighLevelClient initRestHighLevelClient() {
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")).setHttpClientConfigCallback(httpClientBuilder -> {
//                    httpClientBuilder.disableAuthCaching();
//                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                }));
//        return client;
//    }
//}

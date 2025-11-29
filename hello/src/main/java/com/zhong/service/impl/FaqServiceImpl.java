package com.zhong.service.impl;

//import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhong.mapper.FaqMapper;
import com.zhong.pojo.Faq;
import com.zhong.service.IFaqService;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.WithPayloadSelectorFactory;
import io.qdrant.client.grpc.Points;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.model.EmbeddingUtils;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FaqServiceImpl extends ServiceImpl<FaqMapper, Faq> implements IFaqService {

    @Resource
    private VectorStore vectorStore;

    @Value("${spring.ai.vectorstore.qdrant.similarity-threshold}")
    private double similarityThreshold;

//    @Resource
//    private RestHighLevelClient restHighLevelClient;

    @Override
    public void syncFaqToQdrant() {
        List<Faq> list = lambdaQuery().list();
        List<Document> documents = new ArrayList<>();
        for (Faq faq : list) {
            //构建元数据
            Map<String, Object> map = Map.of(
                    "faq_id", faq.getId(),
                    "category_id", faq.getCategoryId(),
                    "question", faq.getQuestion(),
                    "answer", faq.getAnswer()
            );
            //构建document
            //faq的id作为文档id
            //问题转为向量
            Document document = new Document(faq.getId(), faq.getQuestion(), map);
            documents.add(document);
        }
        vectorStore.add(documents);
    }

    @Override
    public List<Faq> similaritySearch(String message) {
        SearchRequest searchRequest = SearchRequest
                .builder()
                .query(message)
                .topK(5)
                .similarityThreshold(similarityThreshold).build();
        List<Document> documents = vectorStore.similaritySearch(searchRequest);
        List<Faq> faqs = new ArrayList<>();
        if (CollectionUtils.isEmpty(documents)) {
            return faqs;
        }
        List<String> ids = documents.stream().map(Document::getId).collect(Collectors.toList());
        return lambdaQuery().in(Faq::getId, ids).list();
    }

//    @Override
//    public void syncFaqToEs() {
//        try {
//            List<Faq> list = lambdaQuery().list();
//            BulkRequest bulkRequest = new BulkRequest();
//            for (Faq faq : list) {
//                IndexRequest indexRequest = new IndexRequest().index("faq").id(faq.getId());
//                indexRequest.source(JSON.toJSONString((faq)), XContentType.JSON);
//                bulkRequest.add(indexRequest);
//            }
//            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//            log.info("syncFaqToEs bulkResponse: {}", bulkResponse);
//        } catch (Exception e) {
//            log.error("syncFaqToEs", e);
//        }
//    }
//
//    @Override
//    public List<Faq> similaritySearchFromEs(String message) {
//        return List.of();
//    }

}

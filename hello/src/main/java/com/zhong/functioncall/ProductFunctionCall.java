package com.zhong.functioncall;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhong.pojo.SkuInfo;
import com.zhong.service.ISkuInfoService;
import jakarta.annotation.Resource;

import java.util.List;
import java.util.function.Function;

public class ProductFunctionCall implements Function<ProductFunctionCall.Request,ProductFunctionCall.Response> {

    @Resource
    private ISkuInfoService skuInfoService;

    /**
     * 应用
     * @param request the function argument
     * @return
     */
    @Override
    public Response apply(Request request) {
        Integer size = request.size == null ? 1: request.size;
        String keyword = request.keyword() == null? "": request.keyword();
        List<SkuInfo> records = skuInfoService.lambdaQuery().like(SkuInfo::getSkuName, keyword).page(Page.of(1, size)).getRecords();
        return new Response(records);
    }

    /**
     * 服务的输入
     * @param keyword 关键字
     * @param size   查询条数
     */
    public record Request(String keyword,Integer size) {}

    /**
     * 服务的输出
     * @param goodsESList 商品信息集合
     */
    public record Response(List<SkuInfo> goodsESList) {}

}

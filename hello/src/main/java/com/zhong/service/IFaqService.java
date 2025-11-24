package com.zhong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhong.pojo.Faq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ear
 * @since 2025-11-24
 */
public interface IFaqService extends IService<Faq> {

    void syncFaqToQdrant();

}

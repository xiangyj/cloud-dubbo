package com.xiangyj.cloud.dubbo.zipkin.product.service;

import com.xiangyj.cloud.dubbo.product.api.ProductService;
import com.xiangyj.cloud.dubbo.product.api.vo.ProductBaseVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DubboService
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    @Override
    public ProductBaseVO getProductById(String productId) {
        LOGGER.info("productId:{}", productId);
        return new ProductBaseVO();
    }
}

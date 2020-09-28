package com.xiangyj.cloud.dubbo.sw.product.service;

import com.xiangyj.cloud.dubbo.product.api.ProductService;
import com.xiangyj.cloud.dubbo.product.api.vo.ProductBaseVO;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class ProductServiceImpl implements ProductService {

    @Override
    public ProductBaseVO getProductById(String productId) {
        ProductBaseVO result = new ProductBaseVO();
        result.setProductId(productId);
        return result;
    }
}

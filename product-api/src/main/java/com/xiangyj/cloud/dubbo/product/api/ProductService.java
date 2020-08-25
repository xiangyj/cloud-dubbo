package com.xiangyj.cloud.dubbo.product.api;

import com.xiangyj.cloud.dubbo.product.api.vo.ProductBaseVO;

public interface ProductService {
    /**
     * 根据商品ID获取商品信息
     * @param productId 商品ID
     * @return 商品VO
     */
    ProductBaseVO getProductById(String productId);
}

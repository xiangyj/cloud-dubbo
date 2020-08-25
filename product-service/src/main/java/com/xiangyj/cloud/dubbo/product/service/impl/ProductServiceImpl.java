package com.xiangyj.cloud.dubbo.product.service.impl;

import com.xiangyj.cloud.dubbo.product.api.ProductService;
import com.xiangyj.cloud.dubbo.product.api.vo.ProductBaseVO;
import com.xiangyj.cloud.dubbo.product.service.dao.entity.ProductBase;
import com.xiangyj.cloud.dubbo.product.service.dao.mapper.ProductBaseMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductBaseMapper productBaseMapper;

    @Override
    public ProductBaseVO getProductById(String productId) {
        ProductBase productBase = new ProductBase();
        productBase.setProductId(productId);
        ProductBase select = productBaseMapper.select(productBase);
        ProductBaseVO target = new ProductBaseVO();
        BeanUtils.copyProperties(select, target);
        return target;
    }
}

package com.xiangyj.cloud.dubbo.product.service.dao.entity;

import com.xiangyj.cloud.dubbo.product.service.dao.BaseEntity;

import java.math.BigDecimal;

public class ProductBase extends BaseEntity {
    /** 商品D*/
    private String productId;
    /** 商品名称*/
    private String productName;
    /** 商品价格*/
    private BigDecimal productPrice;
    /** 商品状态：1.待上架，2.上架中，3.已下架*/
    private Integer productStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
}

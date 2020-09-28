package com.xiangyj.cloud.dubbo.sw.order.web;

import com.xiangyj.cloud.dubbo.product.api.ProductService;
import com.xiangyj.cloud.dubbo.product.api.vo.ProductBaseVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @DubboReference
    private ProductService productService;

    @GetMapping("/orders/{id}")
    public String orders(@PathVariable("id") String id) {
        final ProductBaseVO productById = productService.getProductById(id);
        LOGGER.info("product:{}", productById);
        return id;
    }
}

package com.xiangyj.cloud.dubbo.zipkin.order.web;

import brave.Span;
import brave.Tracing;
import com.xiangyj.cloud.dubbo.product.api.ProductService;
import com.xiangyj.cloud.dubbo.product.api.vo.ProductBaseVO;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Reference
    private ProductService productService;

    @Autowired
    private Tracing tracing;

    @GetMapping("/order/{id}")
    public String order(@PathVariable("id") String id) {
        LOGGER.info("入参:{}", id);
        final Span span = tracing.tracer().currentSpan();
        LOGGER.info("current span:{}", span);
        tracing.propagation().injector((MDC mdc, String key, String val) -> mdc.put(key, val));
        final ProductBaseVO productById = productService.getProductById(id);
        LOGGER.info("product服务返回:{}", productById);
        return id;
    }
}

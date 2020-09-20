package com.xiangyj.cloud.dubbo.order.service.web;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Scope;
import co.elastic.apm.api.Transaction;
import com.xiangyj.cloud.dubbo.order.service.dao.entity.Order;
import com.xiangyj.cloud.dubbo.order.service.dao.mapper.OrderMapper;
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
    @Autowired
    private OrderMapper orderMapper;

    @Reference
    private ProductService productService;

    @GetMapping("/orders/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId) {
        final Transaction transaction = ElasticApm.currentTransaction();
        final String traceId = transaction.getTraceId();
        final String id = transaction.getId();
        LOGGER.info("traceId={},id={}", traceId, id);
        try (final Scope activate = transaction.activate()) {
            // 通过MDC往下传递Span到Dubbo的consumer端
            transaction.injectTraceHeaders(MDC::put);
        } catch (Exception e) {
            transaction.captureException(e);
            throw e;
        } finally {
            transaction.end();
        }
        LOGGER.info("MDC:{}", MDC.getCopyOfContextMap());
        Order order = orderMapper.findById(orderId);
        LOGGER.info("订单信息:{}", order);
        if (order != null) {
            ProductBaseVO product = productService.getProductById(order.getProductId());
            LOGGER.info("订单关联的商品信息:{}", product);
        }
        return "order.getOrderId()";
    }

}

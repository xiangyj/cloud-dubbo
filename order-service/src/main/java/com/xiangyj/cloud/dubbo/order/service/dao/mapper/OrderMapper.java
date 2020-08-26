package com.xiangyj.cloud.dubbo.order.service.dao.mapper;


import com.xiangyj.cloud.dubbo.order.service.dao.entity.Order;

public interface OrderMapper {

    Order findById(String orderId);
}

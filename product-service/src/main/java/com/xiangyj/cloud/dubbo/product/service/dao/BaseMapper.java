package com.xiangyj.cloud.dubbo.product.service.dao;

import java.util.List;

public interface BaseMapper<T> {

    T findById(Integer sysId);

    int create(T t);

    int batchCreate(T t);

    T select(T t);

    List<T> selectList(T t);

    int update(T t);
}

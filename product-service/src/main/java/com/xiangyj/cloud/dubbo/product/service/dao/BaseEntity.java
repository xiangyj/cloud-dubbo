package com.xiangyj.cloud.dubbo.product.service.dao;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    /** 系统主键*/
    private Integer sysId;

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }
}

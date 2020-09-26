package com.xiangyj.cloud.dubbo.zipkin.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

@Activate(group = "consumer")
public class ConsumerZipkinFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerZipkinFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        final Map<String, String> traces = MDC.getCopyOfContextMap();
        LOGGER.info("traces:{}", traces);
        if (traces != null && !traces.isEmpty()) {
            invocation.getAttachments().putAll(traces);
        }
        return invoker.invoke(invocation);
    }

}

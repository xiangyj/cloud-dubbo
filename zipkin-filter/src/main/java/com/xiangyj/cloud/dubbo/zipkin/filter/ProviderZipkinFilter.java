package com.xiangyj.cloud.dubbo.zipkin.filter;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.propagation.Propagation;
import brave.propagation.TraceContext;
import brave.propagation.TraceContextOrSamplingFlags;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Activate(group = "provider")
public class ProviderZipkinFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderZipkinFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 根据invocation中传递的zipkin相关信息创建span
        Propagation.Getter<Invocation, String> getter = (invoke, key) -> (String) invoke.get(key);
        final Tracing tracing = Tracing
                .newBuilder()
                .build();
        final TraceContextOrSamplingFlags extract = tracing
                .propagation()
                .extractor(getter)
                .extract(invocation);

        final Tracer tracer = tracing.tracer();
        final Span span;
        final TraceContext context = extract.context();
        LOGGER.info("context:{}", context);
        invocation.getAttachments().forEach(MDC::put);
        if (context != null) {
            span = tracer.joinSpan(context);
        } else {
            span = tracer.nextSpan(extract);
        }
        span.start();
        return invoker.invoke(invocation);
    }
}

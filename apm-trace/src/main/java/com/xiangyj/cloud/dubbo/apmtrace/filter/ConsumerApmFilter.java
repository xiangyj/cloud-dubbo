package com.xiangyj.cloud.dubbo.apmtrace.filter;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Scope;
import co.elastic.apm.api.Transaction;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * consumer端filter，由consumer端生成traceId，由于elastic APM暂时不支持Dubbo，
 * 所以通过Dubbo的RPC invocation机制向下游传递，traceId到达provider端之后，通过Invocation获取traceId再重新生成APM所需要的Span。
 * 然后通过重新生成的Span完成向APM Service的埋点，最终实现了AMP追踪的功能
 */
@Activate(group = "consumer")
public class ConsumerApmFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApmFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 获取当前thread中的APM transaction
        Transaction transaction = ElasticApm.currentTransaction();
        if (transaction.getId().isEmpty()) {
            transaction = ElasticApm.startTransaction();
        }
        try (final Scope ignored = transaction.activate()) {
            String name = "consumer:" + invocation.getInvoker().getInterface().getName() + "#" + invocation.getMethodName();
            transaction.startSpan("Dubbo", "consumer", "Rpc Invoke");
            transaction.setName(name);
            transaction.setType(Transaction.TYPE_REQUEST);
            transaction.injectTraceHeaders(invocation::setAttachment);
            return invoker.invoke(invocation);
        } catch (Exception e) {
            transaction.captureException(e);
            throw e;
        } finally {
            transaction.end();
        }
    }
}

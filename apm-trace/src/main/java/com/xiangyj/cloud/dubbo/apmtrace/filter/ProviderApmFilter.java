package com.xiangyj.cloud.dubbo.apmtrace.filter;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Scope;
import co.elastic.apm.api.Transaction;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = "provider")
public class ProviderApmFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderApmFilter.class);
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // consumer端已经将APM span相关的数据传入了invocation中，在provider端直接使用
        // use startTransactionWithRemoteParent to create transaction with parent, which id from prc context
        Transaction transaction = ElasticApm.startTransactionWithRemoteParent(kye -> {
            final String attachment = invocation.getAttachment(kye);
            LOGGER.info("kye={},value={}", kye, attachment);
            return attachment;
        });
        try (final Scope ignored = transaction.activate()) {
            String name = "provider:" + invocation.getInvoker().getInterface().getName() + "#" + invocation.getMethodName();
            transaction.setName(name);
            transaction.setType(Transaction.TYPE_REQUEST);
            return invoker.invoke(invocation);
        } catch (Exception e) {
            transaction.captureException(e);
            throw e;
        } finally {
            transaction.end();
        }
    }
}

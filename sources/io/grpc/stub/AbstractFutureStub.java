package io.grpc.stub;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractFutureStub extends AbstractStub {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFutureStub(Channel channel, CallOptions callOptions) {
        super(channel, callOptions);
    }

    public static AbstractStub newStub(AbstractStub.StubFactory stubFactory, Channel channel) {
        return stubFactory.newStub(channel, CallOptions.DEFAULT.withOption(ClientCalls.STUB_TYPE_OPTION, ClientCalls.StubType.FUTURE));
    }
}

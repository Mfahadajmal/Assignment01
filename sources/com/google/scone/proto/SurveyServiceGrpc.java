package com.google.scone.proto;

import com.google.search.mdi.aratea.proto.ArateaServiceGrpc;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyServiceGrpc {
    public static volatile MethodDescriptor getRecordEventAnonymousMethod;
    public static volatile MethodDescriptor getRecordEventMethod;
    public static volatile MethodDescriptor getTriggerAnonymousMethod;
    public static volatile MethodDescriptor getTriggerMethod;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SurveyServiceFutureStub extends AbstractFutureStub {
        public SurveyServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override // io.grpc.stub.AbstractStub
        public final /* synthetic */ AbstractStub build(Channel channel, CallOptions callOptions) {
            return new SurveyServiceFutureStub(channel, callOptions);
        }
    }

    private SurveyServiceGrpc() {
    }

    public static int forNumber$ar$edu(int i) {
        if (i != 0) {
            if (i != 2) {
                return 0;
            }
            return 3;
        }
        return 1;
    }

    public static SurveyServiceFutureStub newFutureStub(Channel channel) {
        return (SurveyServiceFutureStub) SurveyServiceFutureStub.newStub(new ArateaServiceGrpc.AnonymousClass3(1), channel);
    }
}

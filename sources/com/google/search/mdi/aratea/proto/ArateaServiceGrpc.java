package com.google.search.mdi.aratea.proto;

import com.google.scone.proto.SurveyServiceGrpc;
import google.internal.feedback.v1.SurveyServiceGrpc;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArateaServiceGrpc {
    public static volatile MethodDescriptor getGenerateMethod;

    /* compiled from: PG */
    /* renamed from: com.google.search.mdi.aratea.proto.ArateaServiceGrpc$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements AbstractStub.StubFactory {
        private final /* synthetic */ int switching_field;

        public AnonymousClass3(int i) {
            this.switching_field = i;
        }

        @Override // io.grpc.stub.AbstractStub.StubFactory
        public final /* synthetic */ AbstractStub newStub(Channel channel, CallOptions callOptions) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    return new SurveyServiceGrpc.SurveyServiceFutureStub(channel, callOptions);
                }
                return new SurveyServiceGrpc.SurveyServiceFutureStub(channel, callOptions);
            }
            return new ArateaServiceFutureStub(channel, callOptions);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ArateaServiceFutureStub extends AbstractFutureStub {
        public ArateaServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override // io.grpc.stub.AbstractStub
        public final /* synthetic */ AbstractStub build(Channel channel, CallOptions callOptions) {
            return new ArateaServiceFutureStub(channel, callOptions);
        }
    }

    private ArateaServiceGrpc() {
    }
}

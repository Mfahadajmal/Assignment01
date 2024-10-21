package google.internal.feedback.v1;

import com.google.search.mdi.aratea.proto.ArateaServiceGrpc;
import dagger.internal.Provider;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyServiceGrpc {
    public static volatile MethodDescriptor getGetSurveyStartupConfigMethod;
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

    public static Provider asDaggerProvider(final javax.inject.Provider provider) {
        provider.getClass();
        return new Provider() { // from class: dagger.internal.Providers$1
            @Override // javax.inject.Provider
            public final Object get() {
                return javax.inject.Provider.this.get();
            }
        };
    }

    public static SurveyServiceFutureStub newFutureStub(Channel channel) {
        return (SurveyServiceFutureStub) SurveyServiceFutureStub.newStub(new ArateaServiceGrpc.AnonymousClass3(2), channel);
    }
}

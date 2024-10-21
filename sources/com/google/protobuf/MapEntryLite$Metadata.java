package com.google.protobuf;

import android.content.Context;
import android.util.Log;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda7;
import com.google.android.accessibility.talkback.actor.gemini.ArateaEndpoint;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0;
import com.google.android.libraries.surveys.internal.config.SurveyConfigProvider;
import com.google.android.libraries.surveys.internal.datastore.SurveyDataStore;
import com.google.android.libraries.surveys.internal.model.Answer;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import com.google.android.libraries.surveys.internal.network.NetworkExecutor;
import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrarProcessor;
import com.google.frameworks.client.data.android.ChannelConfig;
import com.google.frameworks.client.data.android.RpcServiceConfig;
import com.google.frameworks.client.data.android.impl.CredentialStrategyInterceptor;
import com.google.frameworks.client.data.android.impl.NoAuthClientInterceptor;
import com.google.frameworks.client.data.android.impl.TransportChannelCachingChannel;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.WireFormat;
import com.google.scone.proto.Service$SurveyRecordEventRequest;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$ProductContext;
import com.google.scone.proto.Survey$Session;
import com.google.scone.proto.SurveyServiceGrpc;
import com.google.search.mdi.aratea.proto.rpcids.ArateaServiceConfig;
import google.internal.feedback.v1.Service$SurveyRecordEventResponse;
import google.internal.feedback.v1.SurveyServiceGrpc;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.auth.GoogleAuthLibraryCallCredentials;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import io.grpc.stub.ClientCalls;
import j$.time.Instant;
import j$.time.temporal.ChronoUnit;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MapEntryLite$Metadata {
    public final Object MapEntryLite$Metadata$ar$keyType;
    public final Object MapEntryLite$Metadata$ar$valueType;
    public final Object defaultKey;
    public final Object defaultValue;

    public MapEntryLite$Metadata(int i) {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
    public final void addComponent$ar$ds(Component component) {
        this.defaultKey.add(component);
    }

    public final Channel get(RpcServiceConfig rpcServiceConfig) {
        String str = ArateaServiceConfig.PREFERRED_HOST_NAME.value;
        Channel channel = (Channel) ((ConcurrentHashMap) this.defaultKey).get(rpcServiceConfig);
        if (channel == null) {
            Channel channel2 = (Channel) ((ConcurrentHashMap) this.defaultValue).get(str);
            if (channel2 == null) {
                ImmutableList.Builder builder = new ImmutableList.Builder();
                builder.add$ar$ds$4f674a09_0(ContextDataProvider.forStage(new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(this, 9)));
                builder.add$ar$ds$4f674a09_0(new NoAuthClientInterceptor());
                builder.add$ar$ds$4f674a09_0(new CredentialStrategyInterceptor());
                builder.add$ar$ds$4f674a09_0(ContextDataProvider.forStage(new PrimesController$$ExternalSyntheticLambda7(15)));
                builder.add$ar$ds$4f674a09_0(ContextDataProvider.forStage(new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(this, 7)));
                channel2 = OnDeviceFaceMeshCreateLogEvent.interceptForward(new TransportChannelCachingChannel(str, (ChannelConfig) this.MapEntryLite$Metadata$ar$keyType), builder.build());
                Channel channel3 = (Channel) ((ConcurrentHashMap) this.defaultValue).putIfAbsent(str, channel2);
                if (channel3 != null) {
                    channel2 = channel3;
                }
            }
            Channel interceptForward = OnDeviceFaceMeshCreateLogEvent.interceptForward(channel2, Arrays.asList(ContextDataProvider.forStage(new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(rpcServiceConfig, 8))));
            Channel channel4 = (Channel) ((ConcurrentHashMap) this.defaultKey).putIfAbsent(rpcServiceConfig, interceptForward);
            if (channel4 != null) {
                return channel4;
            }
            return interceptForward;
        }
        return channel;
    }

    public final void transmit(Answer answer, boolean z) {
        Instant truncatedTo;
        Survey$Event.SurveyShown surveyShown;
        String nullToEmpty = ContextDataProvider.nullToEmpty(answer.apiKey);
        NetworkCaller createNetworkCaller = SurveyConfigProvider.instance.networkCallerProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.createNetworkCaller((Context) this.MapEntryLite$Metadata$ar$keyType, (String) this.defaultValue, answer.accountName, nullToEmpty);
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.DEFAULT_INSTANCE.createBuilder();
        truncatedTo = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        long epochMilli = truncatedTo.toEpochMilli() - answer.lastTriggerRequestTime;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(epochMilli);
        long nanos = TimeUnit.MILLISECONDS.toNanos(epochMilli - (TimeUnit.SECONDS.toMillis(1L) * seconds));
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Duration.DEFAULT_INSTANCE.createBuilder();
        builder2.copyOnWrite();
        ((Duration) builder2.instance).seconds_ = seconds;
        builder2.copyOnWrite();
        ((Duration) builder2.instance).nanos_ = (int) nanos;
        Duration duration = (Duration) builder2.build();
        builder.copyOnWrite();
        Survey$Event survey$Event = (Survey$Event) builder.instance;
        duration.getClass();
        survey$Event.timeSinceTrigger_ = duration;
        survey$Event.bitField0_ |= 1;
        int i = answer.answerType$ar$edu;
        int i2 = i - 1;
        if (i != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                Log.e("SurveyAnswerTransmitter", "Invalid survey answer type specified.");
                            } else {
                                Survey$Event.SurveyClosed surveyClosed = Survey$Event.SurveyClosed.DEFAULT_INSTANCE;
                                builder.copyOnWrite();
                                Survey$Event survey$Event2 = (Survey$Event) builder.instance;
                                surveyClosed.getClass();
                                survey$Event2.event_ = surveyClosed;
                                survey$Event2.eventCase_ = 6;
                            }
                        } else {
                            Survey$Event.QuestionAnswered questionAnswered = answer.response;
                            builder.copyOnWrite();
                            Survey$Event survey$Event3 = (Survey$Event) builder.instance;
                            questionAnswered.getClass();
                            survey$Event3.event_ = questionAnswered;
                            survey$Event3.eventCase_ = 5;
                        }
                    } else {
                        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.InvitationAnswered.DEFAULT_INSTANCE.createBuilder();
                        boolean z2 = answer.invitationAccepted;
                        builder3.copyOnWrite();
                        ((Survey$Event.InvitationAnswered) builder3.instance).accepted_ = z2;
                        Survey$Event.InvitationAnswered invitationAnswered = (Survey$Event.InvitationAnswered) builder3.build();
                        builder.copyOnWrite();
                        Survey$Event survey$Event4 = (Survey$Event) builder.instance;
                        invitationAnswered.getClass();
                        survey$Event4.event_ = invitationAnswered;
                        survey$Event4.eventCase_ = 4;
                    }
                } else {
                    SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.SurveyAccepted.DEFAULT_INSTANCE.createBuilder();
                    Survey$ProductContext survey$ProductContext = answer.productContext;
                    if (survey$ProductContext != null) {
                        if (!z) {
                            SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) survey$ProductContext.toBuilder();
                            builder5.copyOnWrite();
                            Survey$ProductContext survey$ProductContext2 = (Survey$ProductContext) builder5.instance;
                            survey$ProductContext2.sensitiveContext_ = null;
                            survey$ProductContext2.bitField0_ &= -2;
                            survey$ProductContext = (Survey$ProductContext) builder5.build();
                        }
                        builder4.copyOnWrite();
                        Survey$Event.SurveyAccepted surveyAccepted = (Survey$Event.SurveyAccepted) builder4.instance;
                        survey$ProductContext.getClass();
                        surveyAccepted.productContext_ = survey$ProductContext;
                        surveyAccepted.bitField0_ |= 2;
                    }
                    Survey$Event.SurveyAccepted surveyAccepted2 = (Survey$Event.SurveyAccepted) builder4.build();
                    builder.copyOnWrite();
                    Survey$Event survey$Event5 = (Survey$Event) builder.instance;
                    surveyAccepted2.getClass();
                    survey$Event5.event_ = surveyAccepted2;
                    survey$Event5.eventCase_ = 3;
                }
            } else {
                if (SurveyDataStore.instance.getSurveyStyle(((Survey$Session) this.defaultKey).sessionId_) == SurveyStyle.EMBEDDED) {
                    int i3 = Survey$Event.SurveyShown.SurveyPromptType.SURVEY_PROMPT_TYPE_EMBEDDED$ar$edu;
                    SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) Survey$Event.SurveyShown.DEFAULT_INSTANCE.createBuilder();
                    builder6.copyOnWrite();
                    Survey$Event.SurveyShown surveyShown2 = (Survey$Event.SurveyShown) builder6.instance;
                    surveyShown2.surveyPromptType_ = Survey$Event.SurveyShown.SurveyPromptType.getNumber$ar$edu$a6ec0b40_0(i3);
                    surveyShown2.bitField0_ |= 1;
                    surveyShown = (Survey$Event.SurveyShown) builder6.build();
                } else {
                    surveyShown = Survey$Event.SurveyShown.DEFAULT_INSTANCE;
                }
                builder.copyOnWrite();
                Survey$Event survey$Event6 = (Survey$Event) builder.instance;
                surveyShown.getClass();
                survey$Event6.event_ = surveyShown;
                survey$Event6.eventCase_ = 2;
            }
            Object obj = this.defaultKey;
            long j = SurveyUtils.TIMEOUT_MS;
            if (obj != null) {
                Survey$Session survey$Session = (Survey$Session) obj;
                if (!survey$Session.sessionId_.isEmpty() && !survey$Session.sessionToken_.isEmpty()) {
                    SystemHealthProto$PackedHistogram.Builder builder7 = (SystemHealthProto$PackedHistogram.Builder) Service$SurveyRecordEventRequest.DEFAULT_INSTANCE.createBuilder();
                    Object obj2 = this.defaultKey;
                    builder7.copyOnWrite();
                    Service$SurveyRecordEventRequest service$SurveyRecordEventRequest = (Service$SurveyRecordEventRequest) builder7.instance;
                    obj2.getClass();
                    service$SurveyRecordEventRequest.session_ = (Survey$Session) obj2;
                    service$SurveyRecordEventRequest.bitField0_ |= 2;
                    Survey$Event survey$Event7 = (Survey$Event) builder.build();
                    builder7.copyOnWrite();
                    Service$SurveyRecordEventRequest service$SurveyRecordEventRequest2 = (Service$SurveyRecordEventRequest) builder7.instance;
                    survey$Event7.getClass();
                    service$SurveyRecordEventRequest2.event_ = survey$Event7;
                    service$SurveyRecordEventRequest2.bitField0_ |= 1;
                    final Service$SurveyRecordEventRequest service$SurveyRecordEventRequest3 = (Service$SurveyRecordEventRequest) builder7.build();
                    final Stopwatch stopwatch = new Stopwatch();
                    if (service$SurveyRecordEventRequest3 == null) {
                        Log.e("NetworkCallerGrpc", "Survey record event request was null");
                    } else {
                        final NetworkCallerGrpc networkCallerGrpc = (NetworkCallerGrpc) createNetworkCaller;
                        NetworkExecutor.getNetworkExecutor().execute(new Runnable() { // from class: com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                ListenableFuture futureUnaryCall;
                                ListenableFuture futureUnaryCall2;
                                NetworkCallerGrpc networkCallerGrpc2 = NetworkCallerGrpc.this;
                                OAuth2Credentials authCredentials = networkCallerGrpc2.getAuthCredentials();
                                Channel channel = networkCallerGrpc2.getChannel(authCredentials);
                                if (channel == null) {
                                    Log.e("NetworkCallerGrpc", "Could not get channel for recordEvent.");
                                    return;
                                }
                                Stopwatch stopwatch2 = stopwatch;
                                Service$SurveyRecordEventRequest service$SurveyRecordEventRequest4 = service$SurveyRecordEventRequest3;
                                if (SurveyConfigProvider.instance.feedback1pEnabled) {
                                    try {
                                        google.internal.feedback.v1.Service$SurveyRecordEventRequest service$SurveyRecordEventRequest5 = (google.internal.feedback.v1.Service$SurveyRecordEventRequest) GeneratedMessageLite.parseFrom(google.internal.feedback.v1.Service$SurveyRecordEventRequest.DEFAULT_INSTANCE, service$SurveyRecordEventRequest4.toByteString(), ExtensionRegistryLite.getGeneratedRegistry());
                                        if (authCredentials != null) {
                                            SurveyServiceGrpc.SurveyServiceFutureStub surveyServiceFutureStub = (SurveyServiceGrpc.SurveyServiceFutureStub) SurveyServiceGrpc.newFutureStub(channel).withCallCredentials$ar$class_merging(new GoogleAuthLibraryCallCredentials(authCredentials));
                                            Channel channel2 = surveyServiceFutureStub.channel;
                                            MethodDescriptor methodDescriptor = SurveyServiceGrpc.getRecordEventMethod;
                                            if (methodDescriptor == null) {
                                                synchronized (SurveyServiceGrpc.class) {
                                                    methodDescriptor = SurveyServiceGrpc.getRecordEventMethod;
                                                    if (methodDescriptor == null) {
                                                        MethodDescriptor.Builder newBuilder = MethodDescriptor.newBuilder();
                                                        newBuilder.type = MethodDescriptor.MethodType.UNARY;
                                                        newBuilder.fullMethodName = MethodDescriptor.generateFullMethodName("google.internal.feedback.v1.SurveyService", "RecordEvent");
                                                        newBuilder.setSampledToLocalTracing$ar$ds();
                                                        google.internal.feedback.v1.Service$SurveyRecordEventRequest service$SurveyRecordEventRequest6 = google.internal.feedback.v1.Service$SurveyRecordEventRequest.DEFAULT_INSTANCE;
                                                        ExtensionRegistryLite extensionRegistryLite = ProtoLiteUtils.globalRegistry;
                                                        newBuilder.requestMarshaller = new ProtoLiteUtils.MessageMarshaller(service$SurveyRecordEventRequest6);
                                                        newBuilder.responseMarshaller = new ProtoLiteUtils.MessageMarshaller(Service$SurveyRecordEventResponse.DEFAULT_INSTANCE);
                                                        methodDescriptor = newBuilder.build();
                                                        SurveyServiceGrpc.getRecordEventMethod = methodDescriptor;
                                                    }
                                                }
                                            }
                                            futureUnaryCall = ClientCalls.futureUnaryCall(channel2.newCall(methodDescriptor, surveyServiceFutureStub.callOptions), service$SurveyRecordEventRequest5);
                                        } else {
                                            SurveyServiceGrpc.SurveyServiceFutureStub newFutureStub = SurveyServiceGrpc.newFutureStub(channel);
                                            Channel channel3 = newFutureStub.channel;
                                            MethodDescriptor methodDescriptor2 = SurveyServiceGrpc.getRecordEventAnonymousMethod;
                                            if (methodDescriptor2 == null) {
                                                synchronized (SurveyServiceGrpc.class) {
                                                    methodDescriptor2 = SurveyServiceGrpc.getRecordEventAnonymousMethod;
                                                    if (methodDescriptor2 == null) {
                                                        MethodDescriptor.Builder newBuilder2 = MethodDescriptor.newBuilder();
                                                        newBuilder2.type = MethodDescriptor.MethodType.UNARY;
                                                        newBuilder2.fullMethodName = MethodDescriptor.generateFullMethodName("google.internal.feedback.v1.SurveyService", "RecordEventAnonymous");
                                                        newBuilder2.setSampledToLocalTracing$ar$ds();
                                                        google.internal.feedback.v1.Service$SurveyRecordEventRequest service$SurveyRecordEventRequest7 = google.internal.feedback.v1.Service$SurveyRecordEventRequest.DEFAULT_INSTANCE;
                                                        ExtensionRegistryLite extensionRegistryLite2 = ProtoLiteUtils.globalRegistry;
                                                        newBuilder2.requestMarshaller = new ProtoLiteUtils.MessageMarshaller(service$SurveyRecordEventRequest7);
                                                        newBuilder2.responseMarshaller = new ProtoLiteUtils.MessageMarshaller(Service$SurveyRecordEventResponse.DEFAULT_INSTANCE);
                                                        methodDescriptor2 = newBuilder2.build();
                                                        SurveyServiceGrpc.getRecordEventAnonymousMethod = methodDescriptor2;
                                                    }
                                                }
                                            }
                                            futureUnaryCall = ClientCalls.futureUnaryCall(channel3.newCall(methodDescriptor2, newFutureStub.callOptions), service$SurveyRecordEventRequest5);
                                        }
                                        ContextDataProvider.addCallback(futureUnaryCall, new ArateaEndpoint.AnonymousClass1(networkCallerGrpc2, service$SurveyRecordEventRequest4, stopwatch2, 4), NetworkExecutor.getNetworkExecutor());
                                        return;
                                    } catch (InvalidProtocolBufferException e) {
                                        Log.e("NetworkCallerGrpc", "Failed to convert scone record event request to feedback1p.", e);
                                        return;
                                    }
                                }
                                if (authCredentials != null) {
                                    SurveyServiceGrpc.SurveyServiceFutureStub surveyServiceFutureStub2 = (SurveyServiceGrpc.SurveyServiceFutureStub) com.google.scone.proto.SurveyServiceGrpc.newFutureStub(channel).withCallCredentials$ar$class_merging(new GoogleAuthLibraryCallCredentials(authCredentials));
                                    Channel channel4 = surveyServiceFutureStub2.channel;
                                    MethodDescriptor methodDescriptor3 = com.google.scone.proto.SurveyServiceGrpc.getRecordEventMethod;
                                    if (methodDescriptor3 == null) {
                                        synchronized (com.google.scone.proto.SurveyServiceGrpc.class) {
                                            methodDescriptor3 = com.google.scone.proto.SurveyServiceGrpc.getRecordEventMethod;
                                            if (methodDescriptor3 == null) {
                                                MethodDescriptor.Builder newBuilder3 = MethodDescriptor.newBuilder();
                                                newBuilder3.type = MethodDescriptor.MethodType.UNARY;
                                                newBuilder3.fullMethodName = MethodDescriptor.generateFullMethodName("scone.v1.SurveyService", "RecordEvent");
                                                newBuilder3.setSampledToLocalTracing$ar$ds();
                                                Service$SurveyRecordEventRequest service$SurveyRecordEventRequest8 = Service$SurveyRecordEventRequest.DEFAULT_INSTANCE;
                                                ExtensionRegistryLite extensionRegistryLite3 = ProtoLiteUtils.globalRegistry;
                                                newBuilder3.requestMarshaller = new ProtoLiteUtils.MessageMarshaller(service$SurveyRecordEventRequest8);
                                                newBuilder3.responseMarshaller = new ProtoLiteUtils.MessageMarshaller(com.google.scone.proto.Service$SurveyRecordEventResponse.DEFAULT_INSTANCE);
                                                methodDescriptor3 = newBuilder3.build();
                                                com.google.scone.proto.SurveyServiceGrpc.getRecordEventMethod = methodDescriptor3;
                                            }
                                        }
                                    }
                                    futureUnaryCall2 = ClientCalls.futureUnaryCall(channel4.newCall(methodDescriptor3, surveyServiceFutureStub2.callOptions), service$SurveyRecordEventRequest4);
                                } else {
                                    SurveyServiceGrpc.SurveyServiceFutureStub newFutureStub2 = com.google.scone.proto.SurveyServiceGrpc.newFutureStub(channel);
                                    Channel channel5 = newFutureStub2.channel;
                                    MethodDescriptor methodDescriptor4 = com.google.scone.proto.SurveyServiceGrpc.getRecordEventAnonymousMethod;
                                    if (methodDescriptor4 == null) {
                                        synchronized (com.google.scone.proto.SurveyServiceGrpc.class) {
                                            methodDescriptor4 = com.google.scone.proto.SurveyServiceGrpc.getRecordEventAnonymousMethod;
                                            if (methodDescriptor4 == null) {
                                                MethodDescriptor.Builder newBuilder4 = MethodDescriptor.newBuilder();
                                                newBuilder4.type = MethodDescriptor.MethodType.UNARY;
                                                newBuilder4.fullMethodName = MethodDescriptor.generateFullMethodName("scone.v1.SurveyService", "RecordEventAnonymous");
                                                newBuilder4.setSampledToLocalTracing$ar$ds();
                                                Service$SurveyRecordEventRequest service$SurveyRecordEventRequest9 = Service$SurveyRecordEventRequest.DEFAULT_INSTANCE;
                                                ExtensionRegistryLite extensionRegistryLite4 = ProtoLiteUtils.globalRegistry;
                                                newBuilder4.requestMarshaller = new ProtoLiteUtils.MessageMarshaller(service$SurveyRecordEventRequest9);
                                                newBuilder4.responseMarshaller = new ProtoLiteUtils.MessageMarshaller(com.google.scone.proto.Service$SurveyRecordEventResponse.DEFAULT_INSTANCE);
                                                methodDescriptor4 = newBuilder4.build();
                                                com.google.scone.proto.SurveyServiceGrpc.getRecordEventAnonymousMethod = methodDescriptor4;
                                            }
                                        }
                                    }
                                    futureUnaryCall2 = ClientCalls.futureUnaryCall(channel5.newCall(methodDescriptor4, newFutureStub2.callOptions), service$SurveyRecordEventRequest4);
                                }
                                ContextDataProvider.addCallback(futureUnaryCall2, new ArateaEndpoint.AnonymousClass1(networkCallerGrpc2, service$SurveyRecordEventRequest4, stopwatch2, 3), NetworkExecutor.getNetworkExecutor());
                            }
                        });
                    }
                    answer.response = Survey$Event.QuestionAnswered.DEFAULT_INSTANCE;
                    answer.answerType$ar$edu = 1;
                    return;
                }
            }
            Log.e("SurveyAnswerTransmitter", "Session is invalid, dropping event.");
            answer.response = Survey$Event.QuestionAnswered.DEFAULT_INSTANCE;
            answer.answerType$ar$edu = 1;
            return;
        }
        throw null;
    }

    public MapEntryLite$Metadata(Context context, String str, Survey$Session survey$Session) {
        this.MapEntryLite$Metadata$ar$valueType = new SpannableUtils$NonCopyableTextSpan();
        this.MapEntryLite$Metadata$ar$keyType = context;
        this.defaultValue = str;
        this.defaultKey = survey$Session;
    }

    public MapEntryLite$Metadata(WireFormat.FieldType fieldType, Object obj, WireFormat.FieldType fieldType2, Object obj2) {
        this.MapEntryLite$Metadata$ar$keyType = fieldType;
        this.defaultKey = obj;
        this.MapEntryLite$Metadata$ar$valueType = fieldType2;
        this.defaultValue = obj2;
    }

    public MapEntryLite$Metadata(ChannelConfig channelConfig, List list) {
        this(1);
        this.defaultKey = new ConcurrentHashMap();
        this.defaultValue = new ConcurrentHashMap();
        this.MapEntryLite$Metadata$ar$keyType = channelConfig;
        this.MapEntryLite$Metadata$ar$valueType = list;
    }

    public MapEntryLite$Metadata(Executor executor) {
        this.defaultValue = new ArrayList();
        this.defaultKey = new ArrayList();
        this.MapEntryLite$Metadata$ar$keyType = ComponentRegistrarProcessor.NOOP;
        this.MapEntryLite$Metadata$ar$valueType = executor;
    }
}

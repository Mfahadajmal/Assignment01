package com.google.wireless.android.play.playlog.proto;

import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClientAnalytics$LogEvent extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final ClientAnalytics$LogEvent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public Compliance$ComplianceData complianceData_;
    public int eventCode_;
    public long eventTimeMs_;
    public long eventUptimeMs_;
    public boolean inDirectBootMode_;
    private byte memoizedIsInitialized = 2;
    public ByteString sourceExtension_;
    public long timezoneOffsetSeconds_;
    public String zwiebackCookieOverride_;

    static {
        ClientAnalytics$LogEvent clientAnalytics$LogEvent = new ClientAnalytics$LogEvent();
        DEFAULT_INSTANCE = clientAnalytics$LogEvent;
        GeneratedMessageLite.registerDefaultInstance(ClientAnalytics$LogEvent.class, clientAnalytics$LogEvent);
    }

    private ClientAnalytics$LogEvent() {
        emptyProtobufList();
        ByteString byteString = ByteString.EMPTY;
        this.sourceExtension_ = ByteString.EMPTY;
        this.timezoneOffsetSeconds_ = 180000L;
        emptyIntList();
        emptyIntList();
        this.zwiebackCookieOverride_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        byte b = 1;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                if (obj == null) {
                    b = 0;
                }
                this.memoizedIsInitialized = b;
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001!\b\u0000\u0000\u0001\u0001ဂ\u0000\u0006ည\u000b\u000bင\u0005\u000fတ\u0011\u0011ဂ\u0001\u0019ဇ\u0017\u001cဈ\u0018!ᐉ\u001b", new Object[]{"bitField0_", "eventTimeMs_", "sourceExtension_", "eventCode_", "timezoneOffsetSeconds_", "eventUptimeMs_", "inDirectBootMode_", "zwiebackCookieOverride_", "complianceData_"});
            case NEW_MUTABLE_INSTANCE:
                return new ClientAnalytics$LogEvent();
            case NEW_BUILDER:
                return new SchedulerOptions.Builder((char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ClientAnalytics$LogEvent.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            default:
                throw new UnsupportedOperationException();
        }
    }
}

package com.google.wireless.android.play.playlog.proto;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.identity.signedoutstate.v1.SignedOutState;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protos.privacy.context.external.ExternalPrivacyContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Compliance$ComplianceData extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final Compliance$ComplianceData DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    private byte memoizedIsInitialized = 2;
    public ExternalPrivacyContext privacyContext_;
    public int productIdOrigin_;
    public SignedOutState signedOutState_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProductIdOrigin {
        public static final int NOT_SET$ar$edu = 1;
        public static final int CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu = 2;
        public static final int NON_CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu = 3;
        public static final int LOGGER_OVERRIDE_PROVIDER$ar$edu = 4;
        public static final int LOGGER_DEFERRING_PROVIDER$ar$edu = 5;
        public static final int EVENT_OVERRIDE$ar$edu = 6;
        public static final int EVENT_DEFERRING$ar$edu = 7;
        public static final int LOG_SOURCE_MAPPED$ar$edu = 8;
        public static final int SERVER_INFRASTRUCTURE$ar$edu = 9;
        public static final int LOG_REQUEST_SETTER_WEB$ar$edu = 10;
        public static final int PRIVACY_CONTEXT_RESOLVER$ar$edu = 11;
        private static final /* synthetic */ int[] $VALUES$ar$edu$f797a833_0 = {NOT_SET$ar$edu, CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu, NON_CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu, LOGGER_OVERRIDE_PROVIDER$ar$edu, LOGGER_DEFERRING_PROVIDER$ar$edu, EVENT_OVERRIDE$ar$edu, EVENT_DEFERRING$ar$edu, LOG_SOURCE_MAPPED$ar$edu, SERVER_INFRASTRUCTURE$ar$edu, LOG_REQUEST_SETTER_WEB$ar$edu, PRIVACY_CONTEXT_RESOLVER$ar$edu};

        public static int forNumber$ar$edu$b40d0fe9_0(int i) {
            switch (i) {
                case 0:
                    return NOT_SET$ar$edu;
                case 1:
                    return CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu;
                case 2:
                    return NON_CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu;
                case 3:
                    return LOGGER_OVERRIDE_PROVIDER$ar$edu;
                case 4:
                    return LOGGER_DEFERRING_PROVIDER$ar$edu;
                case 5:
                    return EVENT_OVERRIDE$ar$edu;
                case 6:
                    return EVENT_DEFERRING$ar$edu;
                case 7:
                    return LOG_SOURCE_MAPPED$ar$edu;
                case 8:
                    return SERVER_INFRASTRUCTURE$ar$edu;
                case 9:
                    return LOG_REQUEST_SETTER_WEB$ar$edu;
                case 10:
                    return PRIVACY_CONTEXT_RESOLVER$ar$edu;
                default:
                    return 0;
            }
        }

        public static String toString$ar$edu$3f0d2c63_0(int i) {
            return Integer.toString(i - 1);
        }

        public static int[] values$ar$edu$2e85465d_0() {
            return new int[]{NOT_SET$ar$edu, CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu, NON_CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu, LOGGER_OVERRIDE_PROVIDER$ar$edu, LOGGER_DEFERRING_PROVIDER$ar$edu, EVENT_OVERRIDE$ar$edu, EVENT_DEFERRING$ar$edu, LOG_SOURCE_MAPPED$ar$edu, SERVER_INFRASTRUCTURE$ar$edu, LOG_REQUEST_SETTER_WEB$ar$edu, PRIVACY_CONTEXT_RESOLVER$ar$edu};
        }
    }

    static {
        Compliance$ComplianceData compliance$ComplianceData = new Compliance$ComplianceData();
        DEFAULT_INSTANCE = compliance$ComplianceData;
        GeneratedMessageLite.registerDefaultInstance(Compliance$ComplianceData.class, compliance$ComplianceData);
    }

    private Compliance$ComplianceData() {
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
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "privacyContext_", "productIdOrigin_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$10, "signedOutState_"});
            case NEW_MUTABLE_INSTANCE:
                return new Compliance$ComplianceData();
            case NEW_BUILDER:
                return new SchedulerOptions.Builder((short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Compliance$ComplianceData.class) {
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

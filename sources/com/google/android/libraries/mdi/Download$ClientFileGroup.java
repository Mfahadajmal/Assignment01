package com.google.android.libraries.mdi;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Download$ClientFileGroup extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Download$ClientFileGroup DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public long buildId_;
    public int status_;
    public int versionNumber_;
    public String groupName_ = "";
    public String ownerPackage_ = "";
    public String account_ = "";
    public Internal.ProtobufList file_ = emptyProtobufList();
    public String variantId_ = "";
    public Internal.ProtobufList locale_ = GeneratedMessageLite.emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Status implements Internal.EnumLite {
        UNSPECIFIED(0),
        DOWNLOADED(1),
        PENDING(2),
        PENDING_CUSTOM_VALIDATION(3);

        public final int value;

        Status(int i) {
            this.value = i;
        }

        public static Status forNumber(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return null;
                        }
                        return PENDING_CUSTOM_VALIDATION;
                    }
                    return PENDING;
                }
                return DOWNLOADED;
            }
            return UNSPECIFIED;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return Integer.toString(this.value);
        }
    }

    static {
        Download$ClientFileGroup download$ClientFileGroup = new Download$ClientFileGroup();
        DEFAULT_INSTANCE = download$ClientFileGroup;
        GeneratedMessageLite.registerDefaultInstance(Download$ClientFileGroup.class, download$ClientFileGroup);
    }

    private Download$ClientFileGroup() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\f\t\u0000\u0002\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဈ\u0001\u0004င\u0003\u0005᠌\u0004\u0006ဈ\u0002\bဂ\u0005\n\u001a\fဈ\u0006", new Object[]{"bitField0_", "groupName_", "file_", Download$ClientFile.class, "ownerPackage_", "versionNumber_", "status_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$1, "account_", "buildId_", "locale_", "variantId_"});
            case NEW_MUTABLE_INSTANCE:
                return new Download$ClientFileGroup();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[]) null, (boolean[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Download$ClientFileGroup.class) {
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

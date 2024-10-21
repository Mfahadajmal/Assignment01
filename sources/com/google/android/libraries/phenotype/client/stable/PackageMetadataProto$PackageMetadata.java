package com.google.android.libraries.phenotype.client.stable;

import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageMetadataProto$PackageMetadata extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final PackageMetadataProto$PackageMetadata DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public boolean accountScoped_;
    public boolean autoSubpackage_;
    public int backing_;
    private int bitField0_;
    public boolean directBootAware_;
    public String staticConfigPackage_ = "";
    public boolean stickyAccountSupport_;

    static {
        PackageMetadataProto$PackageMetadata packageMetadataProto$PackageMetadata = new PackageMetadataProto$PackageMetadata();
        DEFAULT_INSTANCE = packageMetadataProto$PackageMetadata;
        GeneratedMessageLite.registerDefaultInstance(PackageMetadataProto$PackageMetadata.class, packageMetadataProto$PackageMetadata);
    }

    private PackageMetadataProto$PackageMetadata() {
        GeneratedMessageLite.emptyProtobufList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0004᠌\u0002\u0005ဇ\u0003\u0006ဇ\u0005\u0007ဇ\u0004", new Object[]{"bitField0_", "staticConfigPackage_", "autoSubpackage_", "backing_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$6, "stickyAccountSupport_", "directBootAware_", "accountScoped_"});
            case NEW_MUTABLE_INSTANCE:
                return new PackageMetadataProto$PackageMetadata();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PackageMetadataProto$PackageMetadata.class) {
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

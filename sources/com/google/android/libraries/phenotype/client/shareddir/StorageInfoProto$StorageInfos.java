package com.google.android.libraries.phenotype.client.shareddir;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageInfoProto$StorageInfos extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final StorageInfoProto$StorageInfos DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public StorageInfoProto$CredentialEncryptedStorageInfo credentialEncryptedStorageInfo_;
    public StorageInfoProto$DeviceEncryptedStorageInfo deviceEncryptedStorageInfo_;

    static {
        StorageInfoProto$StorageInfos storageInfoProto$StorageInfos = new StorageInfoProto$StorageInfos();
        DEFAULT_INSTANCE = storageInfoProto$StorageInfos;
        GeneratedMessageLite.registerDefaultInstance(StorageInfoProto$StorageInfos.class, storageInfoProto$StorageInfos);
    }

    private StorageInfoProto$StorageInfos() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "credentialEncryptedStorageInfo_", "deviceEncryptedStorageInfo_"});
            case NEW_MUTABLE_INSTANCE:
                return new StorageInfoProto$StorageInfos();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (short[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (StorageInfoProto$StorageInfos.class) {
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

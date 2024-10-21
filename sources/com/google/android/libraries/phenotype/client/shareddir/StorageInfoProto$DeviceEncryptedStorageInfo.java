package com.google.android.libraries.phenotype.client.shareddir;

import com.google.android.accessibility.talkback.analytics.TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog;
import com.google.android.accessibility.talkback.analytics.VoiceCommandEnums$VoiceCommandType;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageInfoProto$DeviceEncryptedStorageInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final StorageInfoProto$DeviceEncryptedStorageInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public static final Internal.IntListAdapter.IntConverter enabledBackings_converter_ = new TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.AnonymousClass4(3);
    private int bitField0_;
    public boolean shouldUseSharedStorage_;
    public ByteString secret_ = ByteString.EMPTY;
    public String dirPath_ = "";
    public Internal.ProtobufList includeStaticConfigPackages_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList excludeStaticConfigPackages_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.IntList enabledBackings_ = emptyIntList();

    static {
        StorageInfoProto$DeviceEncryptedStorageInfo storageInfoProto$DeviceEncryptedStorageInfo = new StorageInfoProto$DeviceEncryptedStorageInfo();
        DEFAULT_INSTANCE = storageInfoProto$DeviceEncryptedStorageInfo;
        GeneratedMessageLite.registerDefaultInstance(StorageInfoProto$DeviceEncryptedStorageInfo.class, storageInfoProto$DeviceEncryptedStorageInfo);
    }

    private StorageInfoProto$DeviceEncryptedStorageInfo() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0003\u0000\u0001ည\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004\u001a\u0005\u001a\u0007ࠬ", new Object[]{"bitField0_", "secret_", "shouldUseSharedStorage_", "dirPath_", "includeStaticConfigPackages_", "excludeStaticConfigPackages_", "enabledBackings_", VoiceCommandEnums$VoiceCommandType.VoiceCommandTypeVerifier.class_merging$INSTANCE$6});
            case NEW_MUTABLE_INSTANCE:
                return new StorageInfoProto$DeviceEncryptedStorageInfo();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (byte[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (StorageInfoProto$DeviceEncryptedStorageInfo.class) {
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

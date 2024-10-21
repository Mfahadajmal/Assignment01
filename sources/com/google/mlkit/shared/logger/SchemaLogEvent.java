package com.google.mlkit.shared.logger;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.proto.ProtobufDataEncoderContext;
import com.google.firebase.encoders.proto.ProtobufEncoder$Builder;
import com.google.mlkit.common.sdkinternal.CloseGuard$Factory;
import com.google.mlkit.logging.schema.AutoMLKitSdkLogEventEncoder;
import com.google.mlkit.logging.schema.MLKitSdkLogEvent;
import com.google.mlkit.logging.schema.SystemInfo;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SchemaLogEvent {
    public final MLKitSdkLogEvent builder$ar$class_merging$afa8b912_0;
    public final int priority;
    public SystemInfo systemInfoBuilder$ar$class_merging = new SystemInfo();

    public SchemaLogEvent(MLKitSdkLogEvent mLKitSdkLogEvent, int i) {
        this.builder$ar$class_merging$afa8b912_0 = mLKitSdkLogEvent;
        CloseGuard$Factory.getInstance$ar$ds$cb56d710_0();
        this.priority = i;
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.google.firebase.encoders.ObjectEncoder, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.Map, java.lang.Object] */
    public final byte[] toBytes$ar$ds(int i) {
        ProtobufDataEncoderContext protobufDataEncoderContext;
        ObjectEncoder objectEncoder;
        int i2 = i ^ 1;
        SystemInfo systemInfo = this.systemInfoBuilder$ar$class_merging;
        boolean z = true;
        if (1 != i2) {
            z = false;
        }
        systemInfo.SystemInfo$ar$isJsonLogging = Boolean.valueOf(z);
        this.systemInfoBuilder$ar$class_merging.SystemInfo$ar$isClearcutClient = false;
        this.builder$ar$class_merging$afa8b912_0.systemInfo = new SystemInfo(this.systemInfoBuilder$ar$class_merging);
        try {
            CloseGuard$Factory.getInstance$ar$ds$cb56d710_0();
            if (i == 0) {
                return new MLKitSdkLogEvent(this.builder$ar$class_merging$afa8b912_0).encodeAsJson().getBytes("utf-8");
            }
            MLKitSdkLogEvent mLKitSdkLogEvent = new MLKitSdkLogEvent(this.builder$ar$class_merging$afa8b912_0);
            ProtobufEncoder$Builder protobufEncoder$Builder = new ProtobufEncoder$Builder();
            AutoMLKitSdkLogEventEncoder.configure$ar$ds(protobufEncoder$Builder);
            OptionalMethod optionalMethod = new OptionalMethod((Map) new HashMap(protobufEncoder$Builder.objectEncoders), (Map) new HashMap(protobufEncoder$Builder.valueEncoders), protobufEncoder$Builder.fallbackEncoder);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                protobufDataEncoderContext = new ProtobufDataEncoderContext(byteArrayOutputStream, optionalMethod.OptionalMethod$ar$methodParams, optionalMethod.OptionalMethod$ar$returnType, optionalMethod.OptionalMethod$ar$methodName);
                objectEncoder = (ObjectEncoder) protobufDataEncoderContext.objectEncoders.get(mLKitSdkLogEvent.getClass());
            } catch (IOException unused) {
            }
            if (objectEncoder != null) {
                objectEncoder.encode(mLKitSdkLogEvent, protobufDataEncoderContext);
                return byteArrayOutputStream.toByteArray();
            }
            throw new EncodingException("No encoder for ".concat(String.valueOf(String.valueOf(mLKitSdkLogEvent.getClass()))));
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}

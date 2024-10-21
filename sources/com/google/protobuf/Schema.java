package com.google.protobuf;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import io.grpc.okhttp.internal.framed.Settings;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Schema<T> {
    boolean equals(Object obj, Object obj2);

    int getSerializedSize(Object obj);

    int hashCode(Object obj);

    boolean isInitialized(Object obj);

    void makeImmutable(Object obj);

    void mergeFrom(Object obj, Object obj2);

    void mergeFrom(Object obj, byte[] bArr, int i, int i2, ArrayDecoders$Registers arrayDecoders$Registers);

    void mergeFrom$ar$class_merging$eb9677be_0$ar$class_merging(Object obj, Settings settings, ExtensionRegistryLite extensionRegistryLite);

    Object newInstance();

    void writeTo$ar$class_merging$d1b76bae_0$ar$class_merging$ar$class_merging(Object obj, ExecutorSelector executorSelector);
}

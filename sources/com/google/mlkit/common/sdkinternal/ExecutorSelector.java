package com.google.mlkit.common.sdkinternal;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.acceleration.GPUInfo;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite$Metadata;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Schema;
import com.google.protobuf.WireFormat;
import j$.util.DesugarCollections;
import java.util.Map;
import java.util.StringTokenizer;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExecutorSelector {
    public final Object ExecutorSelector$ar$defaultExecutorProvider;

    public ExecutorSelector(WireFormat.FieldType fieldType, Object obj, WireFormat.FieldType fieldType2, Object obj2) {
        this.ExecutorSelector$ar$defaultExecutorProvider = new MapEntryLite$Metadata(fieldType, obj, fieldType2, obj2);
    }

    private final void checkType$ar$edu(int i) {
        int type$ar$edu$2edc95a9_0 = getType$ar$edu$2edc95a9_0();
        if (i == type$ar$edu$2edc95a9_0) {
            return;
        }
        throw new IllegalStateException("Attempted to access flag value as " + ((Object) GPUInfo.toStringGenerated5ff6403310ffba06(i)) + ", but actual type is " + ((Object) GPUInfo.toStringGenerated5ff6403310ffba06(type$ar$edu$2edc95a9_0)));
    }

    public static int computeSerializedSize(MapEntryLite$Metadata mapEntryLite$Metadata, Object obj, Object obj2) {
        return FieldSet.computeElementSize((WireFormat.FieldType) mapEntryLite$Metadata.MapEntryLite$Metadata$ar$keyType, 1, obj) + FieldSet.computeElementSize((WireFormat.FieldType) mapEntryLite$Metadata.MapEntryLite$Metadata$ar$valueType, 2, obj2);
    }

    public static int[] parseVersionString(String str) {
        try {
            if (!str.isEmpty()) {
                StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
                int countTokens = stringTokenizer.countTokens();
                int[] iArr = new int[countTokens];
                for (int i = 0; i < countTokens; i++) {
                    iArr[i] = Integer.parseInt(stringTokenizer.nextToken());
                }
                return iArr;
            }
            throw new IllegalArgumentException("Version string is empty");
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Unable to parse HTTP flags version string: `", "`"), e);
        }
    }

    public static void writeTo(CodedOutputStream codedOutputStream, MapEntryLite$Metadata mapEntryLite$Metadata, Object obj, Object obj2) {
        FieldSet.writeElement(codedOutputStream, (WireFormat.FieldType) mapEntryLite$Metadata.MapEntryLite$Metadata$ar$keyType, 1, obj);
        FieldSet.writeElement(codedOutputStream, (WireFormat.FieldType) mapEntryLite$Metadata.MapEntryLite$Metadata$ar$valueType, 2, obj2);
    }

    public final boolean addLast(Object obj) {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) ((AtomicRef) this.ExecutorSelector$ar$defaultExecutorProvider).value;
            int addLast = lockFreeTaskQueueCore.addLast(obj);
            if (addLast == 0) {
                return true;
            }
            if (addLast != 1) {
                return false;
            }
            ((AtomicRef) this.ExecutorSelector$ar$defaultExecutorProvider).compareAndSet(lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
        }
    }

    public final void close() {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) ((AtomicRef) this.ExecutorSelector$ar$defaultExecutorProvider).value;
            if (lockFreeTaskQueueCore.close()) {
                return;
            }
            ((AtomicRef) this.ExecutorSelector$ar$defaultExecutorProvider).compareAndSet(lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    public final Map flags() {
        return DesugarCollections.unmodifiableMap(this.ExecutorSelector$ar$defaultExecutorProvider);
    }

    public final AtomicRef get(int i) {
        return ((AtomicRef[]) this.ExecutorSelector$ar$defaultExecutorProvider)[i];
    }

    public final boolean getBoolValue() {
        checkType$ar$edu(1);
        return ((Boolean) this.ExecutorSelector$ar$defaultExecutorProvider).booleanValue();
    }

    public final ByteString getBytesValue() {
        checkType$ar$edu(5);
        return (ByteString) this.ExecutorSelector$ar$defaultExecutorProvider;
    }

    public final float getFloatValue() {
        checkType$ar$edu(3);
        return ((Float) this.ExecutorSelector$ar$defaultExecutorProvider).floatValue();
    }

    public final long getIntValue() {
        checkType$ar$edu(2);
        return ((Long) this.ExecutorSelector$ar$defaultExecutorProvider).longValue();
    }

    public final int getSize() {
        long j = ((LockFreeTaskQueueCore) ((AtomicRef) this.ExecutorSelector$ar$defaultExecutorProvider).value)._state.value;
        return (((int) ((j & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j))) & 1073741823;
    }

    public final String getStringValue() {
        checkType$ar$edu(4);
        return (String) this.ExecutorSelector$ar$defaultExecutorProvider;
    }

    public final int getType$ar$edu$2edc95a9_0() {
        Object obj = this.ExecutorSelector$ar$defaultExecutorProvider;
        if (obj instanceof Boolean) {
            return 1;
        }
        if (obj instanceof Long) {
            return 2;
        }
        if (obj instanceof Float) {
            return 3;
        }
        if (obj instanceof String) {
            return 4;
        }
        if (obj instanceof ByteString) {
            return 5;
        }
        throw new IllegalStateException("Unexpected flag value type: ".concat(String.valueOf(obj.getClass().getName())));
    }

    public final Object removeFirstOrNull() {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) ((AtomicRef) this.ExecutorSelector$ar$defaultExecutorProvider).value;
            Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
            if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                return removeFirstOrNull;
            }
            ((AtomicRef) this.ExecutorSelector$ar$defaultExecutorProvider).compareAndSet(lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
        }
    }

    public final void writeBool(int i, boolean z) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeBool(i, z);
    }

    public final void writeBytes(int i, ByteString byteString) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeBytes(i, byteString);
    }

    public final void writeDouble(int i, double d) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeDouble(i, d);
    }

    @Deprecated
    public final void writeEndGroup(int i) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 4);
    }

    public final void writeEnum(int i, int i2) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeInt32(i, i2);
    }

    public final void writeFixed32(int i, int i2) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32(i, i2);
    }

    public final void writeFixed64(int i, long j) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64(i, j);
    }

    public final void writeFloat(int i, float f) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeFloat(i, f);
    }

    public final void writeGroup(int i, Object obj, Schema schema) {
        CodedOutputStream codedOutputStream = (CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider;
        codedOutputStream.writeTag(i, 3);
        schema.writeTo$ar$class_merging$d1b76bae_0$ar$class_merging$ar$class_merging((MessageLite) obj, codedOutputStream.wrapper$ar$class_merging$36e28e02_0$ar$class_merging);
        codedOutputStream.writeTag(i, 4);
    }

    public final void writeInt32(int i, int i2) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeInt32(i, i2);
    }

    public final void writeInt64(int i, long j) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64(i, j);
    }

    public final void writeMessage(int i, Object obj, Schema schema) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeMessage(i, (MessageLite) obj, schema);
    }

    public final void writeMessageSetItem(int i, Object obj) {
        if (obj instanceof ByteString) {
            ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeRawMessageSetExtension(i, (ByteString) obj);
        } else {
            ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeMessageSetExtension(i, (MessageLite) obj);
        }
    }

    public final void writeSFixed32(int i, int i2) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32(i, i2);
    }

    public final void writeSFixed64(int i, long j) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64(i, j);
    }

    public final void writeSInt32(int i, int i2) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeSInt32(i, i2);
    }

    public final void writeSInt64(int i, long j) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeSInt64(i, j);
    }

    @Deprecated
    public final void writeStartGroup(int i) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 3);
    }

    public final void writeString(int i, String str) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeString(i, str);
    }

    public final void writeUInt32(int i, int i2) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32(i, i2);
    }

    public final void writeUInt64(int i, long j) {
        ((CodedOutputStream) this.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64(i, j);
    }

    public ExecutorSelector(Object obj) {
        this.ExecutorSelector$ar$defaultExecutorProvider = obj;
    }

    public ExecutorSelector(int i) {
        AtomicRef[] atomicRefArr = new AtomicRef[i];
        for (int i2 = 0; i2 < i; i2++) {
            atomicRefArr[i2] = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
        }
        this.ExecutorSelector$ar$defaultExecutorProvider = atomicRefArr;
    }

    public ExecutorSelector(CodedOutputStream codedOutputStream) {
        Internal.checkNotNull$ar$ds$40668187_0(codedOutputStream, "output");
        this.ExecutorSelector$ar$defaultExecutorProvider = codedOutputStream;
        codedOutputStream.wrapper$ar$class_merging$36e28e02_0$ar$class_merging = this;
    }

    public ExecutorSelector(byte[] bArr) {
        this.ExecutorSelector$ar$defaultExecutorProvider = OnDeviceSubjectSegmentationCreateLogEvent.atomic(new LockFreeTaskQueueCore(8, false));
    }

    public ExecutorSelector() {
        this((byte[]) null);
    }

    public ExecutorSelector(float f) {
        this.ExecutorSelector$ar$defaultExecutorProvider = Float.valueOf(f);
    }

    public ExecutorSelector(long j) {
        this.ExecutorSelector$ar$defaultExecutorProvider = Long.valueOf(j);
    }

    public ExecutorSelector(boolean z) {
        this.ExecutorSelector$ar$defaultExecutorProvider = Boolean.valueOf(z);
    }
}

package com.google.protobuf;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UnknownFieldSetLite {
    public static final UnknownFieldSetLite DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    public int count;
    private boolean isMutable;
    public int memoizedSerializedSize;
    public Object[] objects;
    public int[] tags;

    private UnknownFieldSetLite(int i, int[] iArr, Object[] objArr, boolean z) {
        this.memoizedSerializedSize = -1;
        this.count = i;
        this.tags = iArr;
        this.objects = objArr;
        this.isMutable = z;
    }

    public static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        int i = unknownFieldSetLite.count + unknownFieldSetLite2.count;
        int[] copyOf = Arrays.copyOf(unknownFieldSetLite.tags, i);
        System.arraycopy(unknownFieldSetLite2.tags, 0, copyOf, unknownFieldSetLite.count, unknownFieldSetLite2.count);
        Object[] copyOf2 = Arrays.copyOf(unknownFieldSetLite.objects, i);
        System.arraycopy(unknownFieldSetLite2.objects, 0, copyOf2, unknownFieldSetLite.count, unknownFieldSetLite2.count);
        return new UnknownFieldSetLite(i, copyOf, copyOf2, true);
    }

    private static void writeField$ar$class_merging$ar$class_merging$ar$class_merging(int i, Object obj, ExecutorSelector executorSelector) {
        int tagWireType = WireFormat.getTagWireType(i);
        int tagFieldNumber = WireFormat.getTagFieldNumber(i);
        if (tagWireType != 0) {
            if (tagWireType != 1) {
                if (tagWireType != 2) {
                    if (tagWireType != 3) {
                        if (tagWireType == 5) {
                            executorSelector.writeFixed32(tagFieldNumber, ((Integer) obj).intValue());
                            return;
                        }
                        throw new RuntimeException(new InvalidProtocolBufferException.InvalidWireTypeException());
                    }
                    executorSelector.writeStartGroup(tagFieldNumber);
                    ((UnknownFieldSetLite) obj).writeTo$ar$class_merging$8755f9f6_0$ar$class_merging$ar$class_merging(executorSelector);
                    executorSelector.writeEndGroup(tagFieldNumber);
                    return;
                }
                executorSelector.writeBytes(tagFieldNumber, (ByteString) obj);
                return;
            }
            executorSelector.writeFixed64(tagFieldNumber, ((Long) obj).longValue());
            return;
        }
        executorSelector.writeInt64(tagFieldNumber, ((Long) obj).longValue());
    }

    public final void checkMutable() {
        if (this.isMutable) {
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public final void ensureCapacity(int i) {
        int[] iArr = this.tags;
        if (i > iArr.length) {
            int i2 = this.count;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.tags = Arrays.copyOf(iArr, i);
            this.objects = Arrays.copyOf(this.objects, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnknownFieldSetLite)) {
            return false;
        }
        UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
        int i = this.count;
        if (i == unknownFieldSetLite.count) {
            int[] iArr = this.tags;
            int[] iArr2 = unknownFieldSetLite.tags;
            int i2 = 0;
            while (true) {
                if (i2 < i) {
                    if (iArr[i2] != iArr2[i2]) {
                        break;
                    }
                    i2++;
                } else {
                    Object[] objArr = this.objects;
                    Object[] objArr2 = unknownFieldSetLite.objects;
                    int i3 = this.count;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public final int getSerializedSize() {
        int computeUInt64Size;
        int i = this.memoizedSerializedSize;
        if (i == -1) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.count; i3++) {
                int i4 = this.tags[i3];
                int tagFieldNumber = WireFormat.getTagFieldNumber(i4);
                int tagWireType = WireFormat.getTagWireType(i4);
                if (tagWireType != 0) {
                    if (tagWireType != 1) {
                        if (tagWireType != 2) {
                            if (tagWireType != 3) {
                                if (tagWireType == 5) {
                                    ((Integer) this.objects[i3]).intValue();
                                    computeUInt64Size = CodedOutputStream.computeFixed32Size$ar$ds(tagFieldNumber);
                                } else {
                                    throw new IllegalStateException(new InvalidProtocolBufferException.InvalidWireTypeException());
                                }
                            } else {
                                int computeTagSize = CodedOutputStream.computeTagSize(tagFieldNumber);
                                computeUInt64Size = computeTagSize + computeTagSize + ((UnknownFieldSetLite) this.objects[i3]).getSerializedSize();
                            }
                        } else {
                            computeUInt64Size = CodedOutputStream.computeBytesSize(tagFieldNumber, (ByteString) this.objects[i3]);
                        }
                    } else {
                        ((Long) this.objects[i3]).longValue();
                        computeUInt64Size = CodedOutputStream.computeFixed64Size$ar$ds(tagFieldNumber);
                    }
                } else {
                    computeUInt64Size = CodedOutputStream.computeUInt64Size(tagFieldNumber, ((Long) this.objects[i3]).longValue());
                }
                i2 += computeUInt64Size;
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }
        return i;
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = i + 527;
        int[] iArr = this.tags;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = ((i2 * 31) + i4) * 31;
        Object[] objArr = this.objects;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final void makeImmutable() {
        if (this.isMutable) {
            this.isMutable = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean mergeFieldFrom(int i, CodedInputStream codedInputStream) {
        int readTag;
        checkMutable();
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType != 0) {
            if (tagWireType != 1) {
                if (tagWireType != 2) {
                    if (tagWireType != 3) {
                        if (tagWireType != 4) {
                            if (tagWireType == 5) {
                                storeField(i, Integer.valueOf(codedInputStream.readFixed32()));
                                return true;
                            }
                            throw new InvalidProtocolBufferException.InvalidWireTypeException();
                        }
                        return false;
                    }
                    UnknownFieldSetLite unknownFieldSetLite = new UnknownFieldSetLite();
                    do {
                        readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            break;
                        }
                    } while (unknownFieldSetLite.mergeFieldFrom(readTag, codedInputStream));
                    codedInputStream.checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    storeField(i, unknownFieldSetLite);
                    return true;
                }
                storeField(i, codedInputStream.readBytes());
                return true;
            }
            storeField(i, Long.valueOf(codedInputStream.readFixed64()));
            return true;
        }
        storeField(i, Long.valueOf(codedInputStream.readInt64()));
        return true;
    }

    public final void storeField(int i, Object obj) {
        checkMutable();
        ensureCapacity(this.count + 1);
        int[] iArr = this.tags;
        int i2 = this.count;
        iArr[i2] = i;
        this.objects[i2] = obj;
        this.count = i2 + 1;
    }

    public final void writeTo$ar$class_merging$8755f9f6_0$ar$class_merging$ar$class_merging(ExecutorSelector executorSelector) {
        if (this.count != 0) {
            for (int i = 0; i < this.count; i++) {
                writeField$ar$class_merging$ar$class_merging$ar$class_merging(this.tags[i], this.objects[i], executorSelector);
            }
        }
    }

    public UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }
}

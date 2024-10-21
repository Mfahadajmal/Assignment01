package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Internal {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final ByteBuffer EMPTY_BYTE_BUFFER;
    static final Charset UTF_8;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BooleanList extends ProtobufList {
        BooleanList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DoubleList extends ProtobufList {
        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        DoubleList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EnumLite {
        int getNumber();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EnumLiteMap<T extends EnumLite> {
        EnumLite findValueByNumber$ar$ds();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EnumVerifier {
        boolean isInRange(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface FloatList extends ProtobufList {
        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        FloatList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface IntList extends ProtobufList {
        void addInt(int i);

        int getInt(int i);

        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        IntList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IntListAdapter extends AbstractList {
        private final IntConverter converter;
        private final IntList fromList;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface IntConverter {
            Object convert(int i);
        }

        public IntListAdapter(IntList intList, IntConverter intConverter) {
            this.fromList = intList;
            this.converter = intConverter;
        }

        @Override // java.util.AbstractList, java.util.List
        public final Object get(int i) {
            return this.converter.convert(this.fromList.getInt(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final int size() {
            return this.fromList.size();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface LongList extends ProtobufList {
        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        LongList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ProtobufList<E> extends List<E>, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList mutableCopyWithCapacity(int i);
    }

    static {
        Charset.forName("US-ASCII");
        UTF_8 = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        EMPTY_BYTE_BUFFER = ByteBuffer.wrap(bArr);
        CodedInputStream.newInstance(bArr);
    }

    public static void checkNotNull$ar$ds$40668187_0(Object obj, String str) {
        if (obj != null) {
        } else {
            throw new NullPointerException(str);
        }
    }

    public static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isProto1Group(MessageLite messageLite) {
        if (!(messageLite instanceof AbstractMutableMessageLite)) {
            return false;
        }
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int partialHash(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }
}

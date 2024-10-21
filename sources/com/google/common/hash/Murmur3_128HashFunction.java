package com.google.common.hash;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.hash.HashCode;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    public static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
    private static final long serialVersionUID = 0;
    public final int seed = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Murmur3_128Hasher extends AbstractStreamingHasher {
        private long h1 = 0;
        private long h2 = 0;
        private int length = 0;

        public Murmur3_128Hasher(int i) {
        }

        private static long fmix64(long j) {
            long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
            long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
            return j3 ^ (j3 >>> 33);
        }

        private static long mixK1(long j) {
            return Long.rotateLeft(j * (-8663945395140668459L), 31) * 5545529020109919103L;
        }

        private static long mixK2(long j) {
            return Long.rotateLeft(j * 5545529020109919103L, 33) * (-8663945395140668459L);
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected final HashCode makeHash() {
            long j = this.h1;
            long j2 = this.length;
            long j3 = j ^ j2;
            long j4 = j2 ^ this.h2;
            long j5 = j3 + j4;
            this.h1 = j5;
            long j6 = j4 + j5;
            this.h2 = j6;
            long fmix64 = fmix64(j5);
            this.h1 = fmix64;
            long fmix642 = fmix64(j6);
            long j7 = fmix64 + fmix642;
            this.h1 = j7;
            this.h2 = fmix642 + j7;
            byte[] array = ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.h1).putLong(this.h2).array();
            int i = HashCode.HashCode$ar$NoOp;
            return new HashCode.BytesHashCode(array);
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected final void process(ByteBuffer byteBuffer) {
            long j = byteBuffer.getLong();
            long j2 = byteBuffer.getLong();
            long mixK1 = mixK1(j) ^ this.h1;
            this.h1 = mixK1;
            long rotateLeft = Long.rotateLeft(mixK1, 27);
            long j3 = this.h2;
            this.h1 = ((rotateLeft + j3) * 5) + 1390208809;
            long mixK2 = mixK2(j2) ^ j3;
            this.h2 = mixK2;
            this.h2 = ((Long.rotateLeft(mixK2, 31) + this.h1) * 5) + 944331445;
            this.length += 16;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001b. Please report as an issue. */
        @Override // com.google.common.hash.AbstractStreamingHasher
        protected final void processRemaining(ByteBuffer byteBuffer) {
            long j;
            long j2;
            long j3;
            long j4;
            long j5;
            long j6;
            long j7;
            this.length += byteBuffer.remaining();
            long j8 = 0;
            switch (byteBuffer.remaining()) {
                case 1:
                    j = 0;
                    j7 = j ^ ContextDataProvider.toInt(byteBuffer.get(0));
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 2:
                    j2 = 0;
                    j = j2 ^ (ContextDataProvider.toInt(byteBuffer.get(1)) << 8);
                    j7 = j ^ ContextDataProvider.toInt(byteBuffer.get(0));
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 3:
                    j3 = 0;
                    j2 = j3 ^ (ContextDataProvider.toInt(byteBuffer.get(2)) << 16);
                    j = j2 ^ (ContextDataProvider.toInt(byteBuffer.get(1)) << 8);
                    j7 = j ^ ContextDataProvider.toInt(byteBuffer.get(0));
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 4:
                    j4 = 0;
                    j3 = j4 ^ (ContextDataProvider.toInt(byteBuffer.get(3)) << 24);
                    j2 = j3 ^ (ContextDataProvider.toInt(byteBuffer.get(2)) << 16);
                    j = j2 ^ (ContextDataProvider.toInt(byteBuffer.get(1)) << 8);
                    j7 = j ^ ContextDataProvider.toInt(byteBuffer.get(0));
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 5:
                    j5 = 0;
                    j4 = j5 ^ (ContextDataProvider.toInt(byteBuffer.get(4)) << 32);
                    j3 = j4 ^ (ContextDataProvider.toInt(byteBuffer.get(3)) << 24);
                    j2 = j3 ^ (ContextDataProvider.toInt(byteBuffer.get(2)) << 16);
                    j = j2 ^ (ContextDataProvider.toInt(byteBuffer.get(1)) << 8);
                    j7 = j ^ ContextDataProvider.toInt(byteBuffer.get(0));
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 6:
                    j6 = 0;
                    j5 = (ContextDataProvider.toInt(byteBuffer.get(5)) << 40) ^ j6;
                    j4 = j5 ^ (ContextDataProvider.toInt(byteBuffer.get(4)) << 32);
                    j3 = j4 ^ (ContextDataProvider.toInt(byteBuffer.get(3)) << 24);
                    j2 = j3 ^ (ContextDataProvider.toInt(byteBuffer.get(2)) << 16);
                    j = j2 ^ (ContextDataProvider.toInt(byteBuffer.get(1)) << 8);
                    j7 = j ^ ContextDataProvider.toInt(byteBuffer.get(0));
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 7:
                    j6 = ContextDataProvider.toInt(byteBuffer.get(6)) << 48;
                    j5 = (ContextDataProvider.toInt(byteBuffer.get(5)) << 40) ^ j6;
                    j4 = j5 ^ (ContextDataProvider.toInt(byteBuffer.get(4)) << 32);
                    j3 = j4 ^ (ContextDataProvider.toInt(byteBuffer.get(3)) << 24);
                    j2 = j3 ^ (ContextDataProvider.toInt(byteBuffer.get(2)) << 16);
                    j = j2 ^ (ContextDataProvider.toInt(byteBuffer.get(1)) << 8);
                    j7 = j ^ ContextDataProvider.toInt(byteBuffer.get(0));
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 8:
                    j7 = byteBuffer.getLong();
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 9:
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(8));
                    j7 = byteBuffer.getLong();
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 10:
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(9)) << 8;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(8));
                    j7 = byteBuffer.getLong();
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 11:
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(10)) << 16;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(9)) << 8;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(8));
                    j7 = byteBuffer.getLong();
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 12:
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(11)) << 24;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(10)) << 16;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(9)) << 8;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(8));
                    j7 = byteBuffer.getLong();
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 13:
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(12)) << 32;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(11)) << 24;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(10)) << 16;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(9)) << 8;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(8));
                    j7 = byteBuffer.getLong();
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 14:
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(13)) << 40;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(12)) << 32;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(11)) << 24;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(10)) << 16;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(9)) << 8;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(8));
                    j7 = byteBuffer.getLong();
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                case 15:
                    j8 = ContextDataProvider.toInt(byteBuffer.get(14)) << 48;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(13)) << 40;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(12)) << 32;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(11)) << 24;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(10)) << 16;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(9)) << 8;
                    j8 ^= ContextDataProvider.toInt(byteBuffer.get(8));
                    j7 = byteBuffer.getLong();
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 ^= mixK2(j8);
                    return;
                default:
                    throw new AssertionError("Should never get here.");
            }
        }
    }

    static {
        int i = Hashing.Hashing$ar$NoOp;
    }

    public Murmur3_128HashFunction(int i) {
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Murmur3_128HashFunction) {
            int i = ((Murmur3_128HashFunction) obj).seed;
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return getClass().hashCode();
    }

    public final String toString() {
        return "Hashing.murmur3_128(0)";
    }
}

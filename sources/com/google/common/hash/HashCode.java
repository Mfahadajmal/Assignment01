package com.google.common.hash;

import com.google.common.flogger.context.ContextDataProvider;
import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class HashCode {
    public static final /* synthetic */ int HashCode$ar$NoOp = 0;
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class BytesHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final byte[] bytes;

        public BytesHashCode(byte[] bArr) {
            bArr.getClass();
            this.bytes = bArr;
        }

        @Override // com.google.common.hash.HashCode
        public final byte[] asBytes() {
            return (byte[]) this.bytes.clone();
        }

        @Override // com.google.common.hash.HashCode
        public final int asInt() {
            boolean z;
            int length = this.bytes.length;
            if (length >= 4) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", length);
            byte[] bArr = this.bytes;
            return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
        }

        @Override // com.google.common.hash.HashCode
        public final int bits() {
            return this.bytes.length * 8;
        }

        @Override // com.google.common.hash.HashCode
        public final boolean equalsSameBits(HashCode hashCode) {
            boolean z;
            if (this.bytes.length != hashCode.getBytesInternal().length) {
                return false;
            }
            boolean z2 = true;
            int i = 0;
            while (true) {
                byte[] bArr = this.bytes;
                if (i < bArr.length) {
                    if (bArr[i] == hashCode.getBytesInternal()[i]) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 &= z;
                    i++;
                } else {
                    return z2;
                }
            }
        }

        @Override // com.google.common.hash.HashCode
        public final byte[] getBytesInternal() {
            return this.bytes;
        }
    }

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract int bits();

    public final boolean equals(Object obj) {
        if (obj instanceof HashCode) {
            HashCode hashCode = (HashCode) obj;
            if (bits() == hashCode.bits() && equalsSameBits(hashCode)) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean equalsSameBits(HashCode hashCode);

    public byte[] getBytesInternal() {
        throw null;
    }

    public final int hashCode() {
        if (bits() >= 32) {
            return asInt();
        }
        byte[] bytesInternal = getBytesInternal();
        int i = bytesInternal[0] & 255;
        for (int i2 = 1; i2 < bytesInternal.length; i2++) {
            i |= (bytesInternal[i2] & 255) << (i2 * 8);
        }
        return i;
    }

    public final String toString() {
        byte[] bytesInternal = getBytesInternal();
        int length = bytesInternal.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (byte b : bytesInternal) {
            char[] cArr = hexDigits;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }
}

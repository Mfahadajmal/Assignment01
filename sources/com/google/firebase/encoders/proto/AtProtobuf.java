package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.proto.Protobuf;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AtProtobuf {
    public final Object AtProtobuf$ar$intEncoding;
    public int tag;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ProtobufImpl implements Protobuf {
        private final Protobuf.IntEncoding intEncoding;
        private final int tag;

        public ProtobufImpl(int i, Protobuf.IntEncoding intEncoding) {
            this.tag = i;
            this.intEncoding = intEncoding;
        }

        @Override // java.lang.annotation.Annotation
        public final Class annotationType() {
            return Protobuf.class;
        }

        @Override // java.lang.annotation.Annotation
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Protobuf)) {
                return false;
            }
            Protobuf protobuf = (Protobuf) obj;
            if (this.tag == protobuf.tag() && this.intEncoding.equals(protobuf.intEncoding())) {
                return true;
            }
            return false;
        }

        @Override // java.lang.annotation.Annotation
        public final int hashCode() {
            return (this.tag ^ 14552422) + (this.intEncoding.hashCode() ^ 2041407134);
        }

        @Override // com.google.firebase.encoders.proto.Protobuf
        public final Protobuf.IntEncoding intEncoding() {
            return this.intEncoding;
        }

        @Override // com.google.firebase.encoders.proto.Protobuf
        public final int tag() {
            return this.tag;
        }

        @Override // java.lang.annotation.Annotation
        public final String toString() {
            return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.tag + "intEncoding=" + this.intEncoding + ')';
        }
    }

    public AtProtobuf() {
        this.AtProtobuf$ar$intEncoding = Protobuf.IntEncoding.DEFAULT;
    }

    public final Object acquire() {
        int i = this.tag;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = (Object[]) this.AtProtobuf$ar$intEncoding;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.tag = i2;
        return obj;
    }

    public final Protobuf build() {
        return new ProtobufImpl(this.tag, (Protobuf.IntEncoding) this.AtProtobuf$ar$intEncoding);
    }

    public final void release$ar$ds$843266ac_0(Object obj) {
        int i = this.tag;
        if (i < 256) {
            ((Object[]) this.AtProtobuf$ar$intEncoding)[i] = obj;
            this.tag = i + 1;
        }
    }

    public final void tag$ar$ds(int i) {
        this.tag = i;
    }

    public AtProtobuf(byte[] bArr) {
        this.AtProtobuf$ar$intEncoding = new Object[256];
    }
}

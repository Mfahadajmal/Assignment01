package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Metadata {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Empty extends Metadata {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // com.google.common.flogger.backend.Metadata
        public final Object findValue(MetadataKey metadataKey) {
            return null;
        }

        @Override // com.google.common.flogger.backend.Metadata
        public final MetadataKey getKey(int i) {
            throw new IndexOutOfBoundsException("cannot read from empty metadata");
        }

        @Override // com.google.common.flogger.backend.Metadata
        public final Object getValue(int i) {
            throw new IndexOutOfBoundsException("cannot read from empty metadata");
        }

        @Override // com.google.common.flogger.backend.Metadata
        public final int size() {
            return 0;
        }
    }

    public abstract Object findValue(MetadataKey metadataKey);

    public abstract MetadataKey getKey(int i);

    public abstract Object getValue(int i);

    public abstract int size();
}

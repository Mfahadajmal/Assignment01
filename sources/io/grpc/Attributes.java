package io.grpc;

import _COROUTINE._BOUNDARY;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Attributes {
    public static final Attributes EMPTY;
    private static final IdentityHashMap EMPTY_MAP;
    public final IdentityHashMap data;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Attributes base;
        public IdentityHashMap newdata;

        public Builder(Attributes attributes) {
            this.base = attributes;
        }

        public final Attributes build() {
            if (this.newdata != null) {
                Attributes attributes = this.base;
                Attributes attributes2 = Attributes.EMPTY;
                for (Map.Entry entry : attributes.data.entrySet()) {
                    if (!this.newdata.containsKey(entry.getKey())) {
                        this.newdata.put((Key) entry.getKey(), entry.getValue());
                    }
                }
                this.base = new Attributes(this.newdata);
                this.newdata = null;
            }
            return this.base;
        }

        public final void set$ar$ds$d0d6fadb_0(Key key, Object obj) {
            if (this.newdata == null) {
                this.newdata = new IdentityHashMap(1);
            }
            this.newdata.put(key, obj);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Key {
        private final String debugString;

        public Key(String str) {
            this.debugString = str;
        }

        public final String toString() {
            return this.debugString;
        }
    }

    static {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        EMPTY_MAP = identityHashMap;
        EMPTY = new Attributes(identityHashMap);
    }

    public Attributes(IdentityHashMap identityHashMap) {
        this.data = identityHashMap;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attributes attributes = (Attributes) obj;
        if (this.data.size() != attributes.data.size()) {
            return false;
        }
        for (Map.Entry entry : this.data.entrySet()) {
            if (!attributes.data.containsKey(entry.getKey()) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(entry.getValue(), attributes.data.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public final Object get(Key key) {
        return this.data.get(key);
    }

    public final int hashCode() {
        int i = 0;
        for (Map.Entry entry : this.data.entrySet()) {
            i += Arrays.hashCode(new Object[]{entry.getKey(), entry.getValue()});
        }
        return i;
    }

    public final String toString() {
        return this.data.toString();
    }
}

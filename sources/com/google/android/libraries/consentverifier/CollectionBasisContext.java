package com.google.android.libraries.consentverifier;

import android.content.Context;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisContext {
    private final Optional accountNames;
    public final Context context;
    public final Optional executor;
    private final Optional stacktrace;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Optional accountNames;
        public Context context;
        public Optional executor;
        public byte set$0;
        public Optional stacktrace;

        public Builder() {
        }

        public final void setGooglerOverridesCheckbox$ar$ds() {
            this.set$0 = (byte) 1;
        }

        public Builder(byte[] bArr) {
            this();
            Absent absent = Absent.INSTANCE;
            this.accountNames = absent;
            this.stacktrace = absent;
            this.executor = absent;
        }
    }

    public CollectionBasisContext() {
    }

    public final Optional accountNames() {
        return this.accountNames;
    }

    public final Context context() {
        return this.context;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CollectionBasisContext) {
            CollectionBasisContext collectionBasisContext = (CollectionBasisContext) obj;
            if (this.context.equals(collectionBasisContext.context()) && this.accountNames.equals(collectionBasisContext.accountNames()) && this.stacktrace.equals(collectionBasisContext.stacktrace()) && this.executor.equals(collectionBasisContext.executor())) {
                return true;
            }
        }
        return false;
    }

    public final Optional executor() {
        return this.executor;
    }

    public final int hashCode() {
        return ((((((((this.context.hashCode() ^ 1000003) * 1000003) ^ 2040732332) * 1000003) ^ this.stacktrace.hashCode()) * 1000003) ^ 1237) * 1000003) ^ this.executor.hashCode();
    }

    public final Optional stacktrace() {
        return this.stacktrace;
    }

    public final String toString() {
        Optional optional = this.executor;
        Optional optional2 = this.stacktrace;
        Optional optional3 = this.accountNames;
        return "CollectionBasisContext{context=" + String.valueOf(this.context) + ", accountNames=" + String.valueOf(optional3) + ", stacktrace=" + String.valueOf(optional2) + ", googlerOverridesCheckbox=false, executor=" + String.valueOf(optional) + "}";
    }

    public CollectionBasisContext(Context context, Optional optional, Optional optional2, Optional optional3) {
        this();
        this.context = context;
        this.accountNames = optional;
        this.stacktrace = optional2;
        this.executor = optional3;
    }
}

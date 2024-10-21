package com.google.common.flogger.util;

import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RecursionDepth implements Closeable {
    public static final ThreadLocal holder = new ThreadLocal() { // from class: com.google.common.flogger.util.RecursionDepth.1
        @Override // java.lang.ThreadLocal
        protected final /* synthetic */ Object initialValue() {
            return new RecursionDepth();
        }
    };
    public int value = 0;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        int i = this.value;
        if (i > 0) {
            this.value = i - 1;
            return;
        }
        throw new AssertionError("Mismatched calls to RecursionDepth (possible error in core library)");
    }
}

package com.google.common.android.concurrent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ContextHolder {
    public static final ContextHolder NOOP = new ContextHolder() { // from class: com.google.common.android.concurrent.ContextHolder.1
        @Override // com.google.common.android.concurrent.ContextHolder
        public final void propagateToRunnable$ar$ds(Runnable runnable) {
        }
    };

    protected ContextHolder() {
    }

    public void propagateToRunnable$ar$ds(Runnable runnable) {
        throw null;
    }
}

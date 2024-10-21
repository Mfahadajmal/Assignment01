package com.google.common.io;

import com.google.common.base.Throwables;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Closer implements Closeable {
    public static final ContextDataProvider SUPPRESSING_SUPPRESSOR$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new ContextDataProvider((char[]) null);
    public final Deque stack = new ArrayDeque(4);
    final ContextDataProvider suppressor$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public Throwable thrown;

    public Closer(ContextDataProvider contextDataProvider) {
        this.suppressor$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = contextDataProvider;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Throwable th = this.thrown;
        while (!this.stack.isEmpty()) {
            Closeable closeable = (Closeable) this.stack.removeFirst();
            try {
                closeable.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    ContextDataProvider.suppress$ar$ds(closeable, th, th2);
                }
            }
        }
        if (this.thrown == null && th != null) {
            Throwables.throwIfInstanceOf(th, IOException.class);
            Throwables.throwIfUnchecked(th);
            throw new AssertionError(th);
        }
    }
}

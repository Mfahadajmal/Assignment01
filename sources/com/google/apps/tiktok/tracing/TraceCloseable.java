package com.google.apps.tiktok.tracing;

import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TraceCloseable extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}

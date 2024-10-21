package com.google.android.libraries.storage.file.common;

import java.io.Closeable;
import java.util.zip.Inflater;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReleasableResource implements Closeable {
    public final Object ReleasableResource$ar$resource;
    private final /* synthetic */ int switching_field;

    public ReleasableResource(Closeable closeable, int i) {
        this.switching_field = i;
        this.ReleasableResource$ar$resource = closeable;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.Closeable, java.lang.Object] */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.switching_field != 0) {
            ((Inflater) this.ReleasableResource$ar$resource).end();
            return;
        }
        ?? r0 = this.ReleasableResource$ar$resource;
        if (r0 != 0) {
            r0.close();
        }
    }

    public ReleasableResource(int i) {
        this.switching_field = i;
        this.ReleasableResource$ar$resource = new Inflater(true);
    }
}

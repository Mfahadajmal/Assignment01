package io.grpc.internal;

import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InsightBuilder {
    private final ArrayList buffer = new ArrayList();

    public final void append$ar$ds(Object obj) {
        this.buffer.add(obj.toString());
    }

    public final void appendKeyValue$ar$ds(String str, Object obj) {
        this.buffer.add(str + "=" + String.valueOf(obj));
    }

    public final String toString() {
        return this.buffer.toString();
    }
}

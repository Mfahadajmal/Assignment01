package io.grpc.okhttp.internal;

import com.google.mlkit.common.model.RemoteModelManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Headers {
    private final String[] namesAndValues;

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.List, java.lang.Object] */
    public Headers(RemoteModelManager remoteModelManager) {
        ?? r2 = remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances;
        this.namesAndValues = (String[]) r2.toArray(new String[r2.size()]);
    }

    public final String name(int i) {
        int i2 = i + i;
        if (i2 >= 0) {
            String[] strArr = this.namesAndValues;
            if (i2 < strArr.length) {
                return strArr[i2];
            }
            return null;
        }
        return null;
    }

    public final int size() {
        return this.namesAndValues.length >> 1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            sb.append(name(i));
            sb.append(": ");
            sb.append(value(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public final String value(int i) {
        int i2 = i + i + 1;
        if (i2 >= 0) {
            String[] strArr = this.namesAndValues;
            if (i2 < strArr.length) {
                return strArr[i2];
            }
            return null;
        }
        return null;
    }
}

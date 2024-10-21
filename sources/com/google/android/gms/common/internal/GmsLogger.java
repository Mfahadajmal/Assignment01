package com.google.android.gms.common.internal;

import android.util.Log;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsLogger {
    private final String logTag;
    private final String prefix;

    public GmsLogger(String str, String str2) {
        Object[] objArr = {str, 23};
        if (str.length() <= 23) {
            this.logTag = str;
            this.prefix = (str2 == null || str2.length() <= 0) ? null : str2;
            return;
        }
        throw new IllegalArgumentException(String.format("tag \"%s\" is longer than the %d character maximum", objArr));
    }

    public final boolean canLog(int i) {
        return Log.isLoggable(this.logTag, i);
    }

    public final void e(String str, String str2, Throwable th) {
        if (canLog(6)) {
            Log.e(str, prefix(str2), th);
        }
    }

    public final String prefix(String str) {
        String str2 = this.prefix;
        if (str2 == null) {
            return str;
        }
        return str2.concat(str);
    }
}

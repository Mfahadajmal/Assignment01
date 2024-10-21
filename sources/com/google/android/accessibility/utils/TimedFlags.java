package com.google.android.accessibility.utils;

import android.os.SystemClock;
import android.util.SparseArray;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TimedFlags {
    public final SparseArray mFlags = new SparseArray();

    public final boolean checkAndClearRecentFlag(int i) {
        Long l = (Long) this.mFlags.get(i);
        if (l != null && SystemClock.uptimeMillis() - l.longValue() < 1000) {
            this.mFlags.remove(i);
            return true;
        }
        return false;
    }

    public final void setFlag(int i) {
        this.mFlags.put(i, Long.valueOf(SystemClock.uptimeMillis()));
    }
}

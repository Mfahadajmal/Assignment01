package com.google.apps.tiktok.tracing;

import android.text.TextUtils;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceInfo {
    public final ImmutableList spanNames;

    public TraceInfo(ImmutableList immutableList) {
        this.spanNames = immutableList;
    }

    public final String toString() {
        return TextUtils.join(" -> ", this.spanNames);
    }
}

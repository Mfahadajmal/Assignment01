package com.google.android.accessibility.utils;

import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TreeDebug$$ExternalSyntheticLambda0 implements Logger {
    private final /* synthetic */ int switching_field;

    @Override // com.google.android.accessibility.utils.Logger
    public final void log(String str, Object[] objArr) {
        if (this.switching_field != 0) {
            LogUtils.v("Performance", str, objArr);
        } else {
            LogUtils.v("TreeDebug", str, objArr);
        }
    }
}

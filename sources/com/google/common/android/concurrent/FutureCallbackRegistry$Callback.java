package com.google.common.android.concurrent;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class FutureCallbackRegistry$Callback {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onFailure(Object obj, Throwable th);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onSuccess(Object obj, Object obj2);
}

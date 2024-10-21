package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PendingResult<R extends Result> {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface StatusListener {
        void onComplete(Status status);
    }

    public void addStatusListener(StatusListener statusListener) {
        throw null;
    }

    public abstract void await$ar$ds(TimeUnit timeUnit);

    public abstract void setResultCallback(ResultCallback resultCallback);
}

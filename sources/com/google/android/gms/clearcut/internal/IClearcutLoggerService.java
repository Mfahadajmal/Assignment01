package com.google.android.gms.clearcut.internal;

import android.os.IInterface;
import com.google.android.gms.clearcut.LogEventParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IClearcutLoggerService extends IInterface {
    void logError(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, BatchedLogErrorParcelable batchedLogErrorParcelable);

    void logEvent(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, LogEventParcelable logEventParcelable);
}

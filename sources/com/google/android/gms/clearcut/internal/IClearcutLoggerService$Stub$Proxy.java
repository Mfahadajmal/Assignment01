package com.google.android.gms.clearcut.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.clearcut.LogEventParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IClearcutLoggerService$Stub$Proxy extends BaseProxy implements IClearcutLoggerService {
    public IClearcutLoggerService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
    }

    @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerService
    public final void logError(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, BatchedLogErrorParcelable batchedLogErrorParcelable) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iClearcutLoggerCallbacks);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, batchedLogErrorParcelable);
        transactOneway(8, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerService
    public final void logEvent(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, LogEventParcelable logEventParcelable) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iClearcutLoggerCallbacks);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, logEventParcelable);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}

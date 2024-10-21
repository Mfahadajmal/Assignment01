package com.google.android.gms.usagereporting.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IUsageReportingService$Stub$Proxy extends BaseProxy implements IUsageReportingService {
    public IUsageReportingService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.usagereporting.internal.IUsageReportingService");
    }

    @Override // com.google.android.gms.usagereporting.internal.IUsageReportingService
    public final void getOptInOptions(IUsageReportingCallbacks iUsageReportingCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iUsageReportingCallbacks);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.usagereporting.internal.IUsageReportingService
    public final void registerOptInOptionsChangedListener(IUsageReportingOptInOptionsChangedListener iUsageReportingOptInOptionsChangedListener, IUsageReportingCallbacks iUsageReportingCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iUsageReportingOptInOptionsChangedListener);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iUsageReportingCallbacks);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.usagereporting.internal.IUsageReportingService
    public final void unregisterOptInOptionsChangedListener(IUsageReportingOptInOptionsChangedListener iUsageReportingOptInOptionsChangedListener, IUsageReportingCallbacks iUsageReportingCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iUsageReportingOptInOptionsChangedListener);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iUsageReportingCallbacks);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.usagereporting.internal.IUsageReportingService
    public final void unregisterOptInOptionsChangedListenerConnectionless(IUsageReportingOptInOptionsChangedListener iUsageReportingOptInOptionsChangedListener, IUsageReportingCallbacks iUsageReportingCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iUsageReportingOptInOptionsChangedListener);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iUsageReportingCallbacks);
        transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }
}

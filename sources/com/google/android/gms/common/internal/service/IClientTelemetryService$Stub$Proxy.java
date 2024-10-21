package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.internal.TelemetryData;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IClientTelemetryService$Stub$Proxy extends BaseProxy implements IClientTelemetryService {
    public IClientTelemetryService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.IClientTelemetryService");
    }

    @Override // com.google.android.gms.common.internal.service.IClientTelemetryService
    public final void recordData(TelemetryData telemetryData) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, telemetryData);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}

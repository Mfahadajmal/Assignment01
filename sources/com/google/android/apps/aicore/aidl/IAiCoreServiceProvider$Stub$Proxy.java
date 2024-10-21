package com.google.android.apps.aicore.aidl;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IAiCoreServiceProvider$Stub$Proxy extends BaseProxy implements IAiCoreServiceProvider {
    public IAiCoreServiceProvider$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.aicore.aidl.IAiCoreServiceProvider");
    }

    @Override // com.google.android.apps.aicore.aidl.IAiCoreServiceProvider
    public final void get(IAiCoreServiceProviderCallback iAiCoreServiceProviderCallback) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iAiCoreServiceProviderCallback);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }
}

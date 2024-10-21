package com.google.android.gms.common.moduleinstall.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IModuleInstallService$Stub$Proxy extends BaseProxy implements IModuleInstallService {
    public IModuleInstallService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.moduleinstall.internal.IModuleInstallService");
    }

    @Override // com.google.android.gms.common.moduleinstall.internal.IModuleInstallService
    public final void installModules(IModuleInstallCallbacks iModuleInstallCallbacks, ApiFeatureRequest apiFeatureRequest, IModuleInstallStatusListener iModuleInstallStatusListener) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iModuleInstallCallbacks);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, apiFeatureRequest);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, null);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}

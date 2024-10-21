package com.google.android.play.core.splitinstall.protocol;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ISplitInstallService$Stub$Proxy extends BaseProxy implements ISplitInstallService {
    public ISplitInstallService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
    }

    @Override // com.google.android.play.core.splitinstall.protocol.ISplitInstallService
    public final void deferredUninstall(String str, List list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeTypedList(list);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iSplitInstallServiceCallback);
        transactOneway(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.play.core.splitinstall.protocol.ISplitInstallService
    public final void getSessionStates_deprecated(String str, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iSplitInstallServiceCallback);
        transactOneway(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.play.core.splitinstall.protocol.ISplitInstallService
    public final void startInstall(String str, List list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeTypedList(list);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, bundle);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iSplitInstallServiceCallback);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }
}

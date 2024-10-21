package com.google.android.apps.aicore.aidl;

import android.os.IBinder;
import com.google.android.aidl.BaseProxy;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ICancellationCallback$Stub$Proxy extends BaseProxy implements ICancellationCallback {
    public ICancellationCallback$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.aicore.aidl.ICancellationCallback");
    }

    @Override // com.google.android.apps.aicore.aidl.ICancellationCallback
    public final void cancel() {
        transactOneway(2, obtainAndWriteInterfaceToken());
    }
}

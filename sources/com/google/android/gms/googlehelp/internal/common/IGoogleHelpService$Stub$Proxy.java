package com.google.android.gms.googlehelp.internal.common;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.InProductHelp;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IGoogleHelpService$Stub$Proxy extends BaseProxy implements IGoogleHelpService {
    public IGoogleHelpService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.googlehelp.internal.common.IGoogleHelpService");
    }

    @Override // com.google.android.gms.googlehelp.internal.common.IGoogleHelpService
    public final void processGoogleHelpAndPip(GoogleHelp googleHelp, Bitmap bitmap, IGoogleHelpCallbacks iGoogleHelpCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleHelp);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, null);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGoogleHelpCallbacks);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.googlehelp.internal.common.IGoogleHelpService
    public final void processInProductHelpAndPip(InProductHelp inProductHelp, Bitmap bitmap, IGoogleHelpCallbacks iGoogleHelpCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, inProductHelp);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, null);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGoogleHelpCallbacks);
        transactAndReadExceptionReturnVoid(17, obtainAndWriteInterfaceToken);
    }
}

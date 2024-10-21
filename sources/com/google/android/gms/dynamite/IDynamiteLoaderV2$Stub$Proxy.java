package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IDynamiteLoaderV2$Stub$Proxy extends BaseProxy implements IDynamiteLoaderV2 {
    public IDynamiteLoaderV2$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    @Override // com.google.android.gms.dynamite.IDynamiteLoaderV2
    public final IObjectWrapper loadModule2(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) {
        IObjectWrapper proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper2);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            if (queryLocalInterface instanceof IObjectWrapper) {
                proxy = (IObjectWrapper) queryLocalInterface;
            } else {
                proxy = new IObjectWrapper.Stub.Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return proxy;
    }

    @Override // com.google.android.gms.dynamite.IDynamiteLoaderV2
    public final IObjectWrapper loadModule2NoCrashUtils(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) {
        IObjectWrapper proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper2);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            if (queryLocalInterface instanceof IObjectWrapper) {
                proxy = (IObjectWrapper) queryLocalInterface;
            } else {
                proxy = new IObjectWrapper.Stub.Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return proxy;
    }
}

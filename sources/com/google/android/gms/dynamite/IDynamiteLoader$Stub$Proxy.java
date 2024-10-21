package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IDynamiteLoader$Stub$Proxy extends BaseProxy implements IDynamiteLoader {
    public IDynamiteLoader$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    @Override // com.google.android.gms.dynamite.IDynamiteLoader
    public final IObjectWrapper createModuleContext(IObjectWrapper iObjectWrapper, String str, int i) {
        IObjectWrapper proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
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

    @Override // com.google.android.gms.dynamite.IDynamiteLoader
    public final IObjectWrapper createModuleContext3NoCrashUtils(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) {
        IObjectWrapper proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper2);
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken);
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

    @Override // com.google.android.gms.dynamite.IDynamiteLoader
    public final IObjectWrapper createModuleContextNoCrashUtils(IObjectWrapper iObjectWrapper, String str, int i) {
        IObjectWrapper proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken);
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

    @Override // com.google.android.gms.dynamite.IDynamiteLoader
    public final int getIDynamiteLoaderVersion() {
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.dynamite.IDynamiteLoader
    public final int getModuleVersion2(IObjectWrapper iObjectWrapper, String str, boolean z) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(z ? 1 : 0);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.dynamite.IDynamiteLoader
    public final int getModuleVersion2NoCrashUtils(IObjectWrapper iObjectWrapper, String str, boolean z) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(z ? 1 : 0);
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken);
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.dynamite.IDynamiteLoader
    public final IObjectWrapper queryForDynamiteModuleNoCrashUtils(IObjectWrapper iObjectWrapper, String str, boolean z, long j) {
        IObjectWrapper proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(z ? 1 : 0);
        obtainAndWriteInterfaceToken.writeLong(j);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
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

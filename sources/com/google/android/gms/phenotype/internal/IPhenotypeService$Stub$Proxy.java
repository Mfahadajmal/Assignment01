package com.google.android.gms.phenotype.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IPhenotypeService$Stub$Proxy extends BaseProxy implements IPhenotypeService {
    public IPhenotypeService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.phenotype.internal.IPhenotypeService");
    }

    @Override // com.google.android.gms.phenotype.internal.IPhenotypeService
    public final void commitToConfiguration(IPhenotypeCallbacks iPhenotypeCallbacks, String str) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iPhenotypeCallbacks);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.phenotype.internal.IPhenotypeService
    public final void getConfigurationSnapshotWithToken$ar$ds(IPhenotypeCallbacks iPhenotypeCallbacks, String str, String str2) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iPhenotypeCallbacks);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeString(null);
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.phenotype.internal.IPhenotypeService
    public final void getStorageInfo(IGetStorageInfoCallbacks iGetStorageInfoCallbacks) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iGetStorageInfoCallbacks);
        transactAndReadExceptionReturnVoid(27, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.phenotype.internal.IPhenotypeService
    public final void register$ar$ds(IPhenotypeCallbacks iPhenotypeCallbacks, String str, int i, String[] strArr) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iPhenotypeCallbacks);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        obtainAndWriteInterfaceToken.writeByteArray(null);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.phenotype.internal.IPhenotypeService
    public final void registerFlagUpdateListener(String str, IFlagUpdateListener iFlagUpdateListener) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iFlagUpdateListener);
        transactAndReadExceptionReturnVoid(28, obtainAndWriteInterfaceToken);
    }
}

package com.google.android.gms.phenotype.internal;

import android.os.IInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IPhenotypeService extends IInterface {
    void commitToConfiguration(IPhenotypeCallbacks iPhenotypeCallbacks, String str);

    void getConfigurationSnapshotWithToken$ar$ds(IPhenotypeCallbacks iPhenotypeCallbacks, String str, String str2);

    void getStorageInfo(IGetStorageInfoCallbacks iGetStorageInfoCallbacks);

    void register$ar$ds(IPhenotypeCallbacks iPhenotypeCallbacks, String str, int i, String[] strArr);

    void registerFlagUpdateListener(String str, IFlagUpdateListener iFlagUpdateListener);
}

package com.google.android.apps.aicore.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IAICoreService$Stub$Proxy extends BaseProxy implements IAICoreService {
    public IAICoreService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.aicore.aidl.IAICoreService");
    }

    @Override // com.google.android.apps.aicore.aidl.IAICoreService
    public final int getApiVersion() {
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.apps.aicore.aidl.IAICoreService
    public final long getDownloadableSizeInBytes(AIFeature aIFeature) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, aIFeature);
        Parcel transactAndReadException = transactAndReadException(19, obtainAndWriteInterfaceToken);
        long readLong = transactAndReadException.readLong();
        transactAndReadException.recycle();
        return readLong;
    }

    @Override // com.google.android.apps.aicore.aidl.IAICoreService
    public final int getFeatureStatus(AIFeature aIFeature) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, aIFeature);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.apps.aicore.aidl.IAICoreService
    public final IImageDescriptionService getImageDescriptionService(AIFeature aIFeature) {
        IImageDescriptionService iImageDescriptionService$Stub$Proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, aIFeature);
        Parcel transactAndReadException = transactAndReadException(30, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            iImageDescriptionService$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.apps.aicore.aidl.IImageDescriptionService");
            if (queryLocalInterface instanceof IImageDescriptionService) {
                iImageDescriptionService$Stub$Proxy = (IImageDescriptionService) queryLocalInterface;
            } else {
                iImageDescriptionService$Stub$Proxy = new IImageDescriptionService$Stub$Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return iImageDescriptionService$Stub$Proxy;
    }

    @Override // com.google.android.apps.aicore.aidl.IAICoreService
    public final AIFeature[] listFeatures() {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        AIFeature[] aIFeatureArr = (AIFeature[]) transactAndReadException.createTypedArray(AIFeature.CREATOR);
        transactAndReadException.recycle();
        return aIFeatureArr;
    }

    @Override // com.google.android.apps.aicore.aidl.IAICoreService
    public final void requestDownloadableFeatureWithDownloadListener$ar$ds(AIFeature aIFeature, IDownloadListener iDownloadListener) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, aIFeature);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iDownloadListener);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        transactAndReadException.readInt();
        transactAndReadException.recycle();
    }

    @Override // com.google.android.apps.aicore.aidl.IAICoreService
    public final void requestDownloadableFeatureWithDownloadListener2$ar$ds(AIFeature aIFeature, IDownloadListener2 iDownloadListener2) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, aIFeature);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iDownloadListener2);
        Parcel transactAndReadException = transactAndReadException(12, obtainAndWriteInterfaceToken);
        transactAndReadException.readInt();
        transactAndReadException.recycle();
    }
}

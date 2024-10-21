package com.google.android.apps.aicore.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IImageDescriptionService$Stub$Proxy extends BaseProxy implements IImageDescriptionService {
    public IImageDescriptionService$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.aicore.aidl.IImageDescriptionService");
    }

    @Override // com.google.android.apps.aicore.aidl.IImageDescriptionService
    public final ICancellationCallback prepareInferenceEngine(IPrepareInferenceEngineCallback iPrepareInferenceEngineCallback) {
        ICancellationCallback iCancellationCallback$Stub$Proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iPrepareInferenceEngineCallback);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            iCancellationCallback$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.apps.aicore.aidl.ICancellationCallback");
            if (queryLocalInterface instanceof ICancellationCallback) {
                iCancellationCallback$Stub$Proxy = (ICancellationCallback) queryLocalInterface;
            } else {
                iCancellationCallback$Stub$Proxy = new ICancellationCallback$Stub$Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return iCancellationCallback$Stub$Proxy;
    }

    @Override // com.google.android.apps.aicore.aidl.IImageDescriptionService
    public final ICancellationCallback runCancellableInference(ImageDescriptionRequest imageDescriptionRequest, IImageDescriptionResultCallback iImageDescriptionResultCallback) {
        ICancellationCallback iCancellationCallback$Stub$Proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, imageDescriptionRequest);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iImageDescriptionResultCallback);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            iCancellationCallback$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.apps.aicore.aidl.ICancellationCallback");
            if (queryLocalInterface instanceof ICancellationCallback) {
                iCancellationCallback$Stub$Proxy = (ICancellationCallback) queryLocalInterface;
            } else {
                iCancellationCallback$Stub$Proxy = new ICancellationCallback$Stub$Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return iCancellationCallback$Stub$Proxy;
    }
}

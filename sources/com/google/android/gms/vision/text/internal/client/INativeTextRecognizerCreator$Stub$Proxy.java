package com.google.android.gms.vision.text.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class INativeTextRecognizerCreator$Stub$Proxy extends BaseProxy implements INativeTextRecognizerCreator {
    public INativeTextRecognizerCreator$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
    }

    @Override // com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator
    public final INativeTextRecognizer newTextRecognizer(IObjectWrapper iObjectWrapper, TextRecognizerOptions textRecognizerOptions) {
        INativeTextRecognizer iNativeTextRecognizer$Stub$Proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, textRecognizerOptions);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            iNativeTextRecognizer$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
            if (queryLocalInterface instanceof INativeTextRecognizer) {
                iNativeTextRecognizer$Stub$Proxy = (INativeTextRecognizer) queryLocalInterface;
            } else {
                iNativeTextRecognizer$Stub$Proxy = new INativeTextRecognizer$Stub$Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return iNativeTextRecognizer$Stub$Proxy;
    }
}

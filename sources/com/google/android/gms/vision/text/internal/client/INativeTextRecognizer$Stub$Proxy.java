package com.google.android.gms.vision.text.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class INativeTextRecognizer$Stub$Proxy extends BaseProxy implements INativeTextRecognizer {
    public INativeTextRecognizer$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
    }

    @Override // com.google.android.gms.vision.text.internal.client.INativeTextRecognizer
    public final void finalizeRecognizer() {
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.vision.text.internal.client.INativeTextRecognizer
    public final LineBoxParcel[] recognizeBitmap(IObjectWrapper iObjectWrapper, FrameMetadataParcel frameMetadataParcel) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, frameMetadataParcel);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        LineBoxParcel[] lineBoxParcelArr = (LineBoxParcel[]) transactAndReadException.createTypedArray(LineBoxParcel.CREATOR);
        transactAndReadException.recycle();
        return lineBoxParcelArr;
    }
}

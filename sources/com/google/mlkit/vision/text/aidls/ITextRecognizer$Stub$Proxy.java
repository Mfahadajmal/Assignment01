package com.google.mlkit.vision.text.aidls;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.mlkit.vision.common.aidls.ImageMetadataParcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ITextRecognizer$Stub$Proxy extends BaseProxy implements ITextRecognizer {
    public ITextRecognizer$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.text.aidls.ITextRecognizer");
    }

    @Override // com.google.mlkit.vision.text.aidls.ITextRecognizer
    public final void init() {
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.mlkit.vision.text.aidls.ITextRecognizer
    public final TextParcel process(IObjectWrapper iObjectWrapper, ImageMetadataParcel imageMetadataParcel) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, imageMetadataParcel);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        TextParcel textParcel = (TextParcel) Codecs.createParcelable(transactAndReadException, TextParcel.CREATOR);
        transactAndReadException.recycle();
        return textParcel;
    }

    @Override // com.google.mlkit.vision.text.aidls.ITextRecognizer
    public final void release() {
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken());
    }
}

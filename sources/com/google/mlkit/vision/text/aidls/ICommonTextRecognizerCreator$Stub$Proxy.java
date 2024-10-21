package com.google.mlkit.vision.text.aidls;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ICommonTextRecognizerCreator$Stub$Proxy extends BaseProxy implements ICommonTextRecognizerCreator {
    public ICommonTextRecognizerCreator$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.text.aidls.ICommonTextRecognizerCreator");
    }

    @Override // com.google.mlkit.vision.text.aidls.ICommonTextRecognizerCreator
    public final ITextRecognizer newTextRecognizerWithOptions$ar$ds(IObjectWrapper iObjectWrapper, TextRecognizerOptions textRecognizerOptions) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        ITextRecognizer iTextRecognizer = null;
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, null);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, textRecognizerOptions);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizer");
            if (queryLocalInterface instanceof ITextRecognizer) {
                iTextRecognizer = (ITextRecognizer) queryLocalInterface;
            } else {
                iTextRecognizer = new ITextRecognizer$Stub$Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return iTextRecognizer;
    }
}

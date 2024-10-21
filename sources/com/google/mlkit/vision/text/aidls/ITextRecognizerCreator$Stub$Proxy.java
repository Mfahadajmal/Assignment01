package com.google.mlkit.vision.text.aidls;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ITextRecognizerCreator$Stub$Proxy extends BaseProxy implements ITextRecognizerCreator {
    public ITextRecognizerCreator$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.text.aidls.ITextRecognizerCreator");
    }

    @Override // com.google.mlkit.vision.text.aidls.ITextRecognizerCreator
    public final ITextRecognizer newTextRecognizer(IObjectWrapper iObjectWrapper) {
        ITextRecognizer iTextRecognizer$Stub$Proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            iTextRecognizer$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizer");
            if (queryLocalInterface instanceof ITextRecognizer) {
                iTextRecognizer$Stub$Proxy = (ITextRecognizer) queryLocalInterface;
            } else {
                iTextRecognizer$Stub$Proxy = new ITextRecognizer$Stub$Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return iTextRecognizer$Stub$Proxy;
    }

    @Override // com.google.mlkit.vision.text.aidls.ITextRecognizerCreator
    public final ITextRecognizer newTextRecognizerWithOptions(IObjectWrapper iObjectWrapper, TextRecognizerOptions textRecognizerOptions) {
        ITextRecognizer iTextRecognizer$Stub$Proxy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, textRecognizerOptions);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            iTextRecognizer$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizer");
            if (queryLocalInterface instanceof ITextRecognizer) {
                iTextRecognizer$Stub$Proxy = (ITextRecognizer) queryLocalInterface;
            } else {
                iTextRecognizer$Stub$Proxy = new ITextRecognizer$Stub$Proxy(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return iTextRecognizer$Stub$Proxy;
    }
}

package com.google.mlkit.vision.text.aidls;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ITextRecognizerCreator extends IInterface {
    ITextRecognizer newTextRecognizer(IObjectWrapper iObjectWrapper);

    ITextRecognizer newTextRecognizerWithOptions(IObjectWrapper iObjectWrapper, TextRecognizerOptions textRecognizerOptions);
}

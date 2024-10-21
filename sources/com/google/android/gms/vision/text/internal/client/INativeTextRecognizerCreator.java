package com.google.android.gms.vision.text.internal.client;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface INativeTextRecognizerCreator extends IInterface {
    INativeTextRecognizer newTextRecognizer(IObjectWrapper iObjectWrapper, TextRecognizerOptions textRecognizerOptions);
}

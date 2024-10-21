package com.google.mlkit.vision.text.aidls;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.mlkit.vision.common.aidls.ImageMetadataParcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ITextRecognizer extends IInterface {
    void init();

    TextParcel process(IObjectWrapper iObjectWrapper, ImageMetadataParcel imageMetadataParcel);

    void release();
}

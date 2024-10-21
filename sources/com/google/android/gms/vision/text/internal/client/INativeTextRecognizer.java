package com.google.android.gms.vision.text.internal.client;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface INativeTextRecognizer extends IInterface {
    void finalizeRecognizer();

    LineBoxParcel[] recognizeBitmap(IObjectWrapper iObjectWrapper, FrameMetadataParcel frameMetadataParcel);
}

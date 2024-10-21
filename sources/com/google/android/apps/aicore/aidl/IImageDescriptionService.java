package com.google.android.apps.aicore.aidl;

import android.os.IInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IImageDescriptionService extends IInterface {
    ICancellationCallback prepareInferenceEngine(IPrepareInferenceEngineCallback iPrepareInferenceEngineCallback);

    ICancellationCallback runCancellableInference(ImageDescriptionRequest imageDescriptionRequest, IImageDescriptionResultCallback iImageDescriptionResultCallback);
}

package com.google.android.apps.aicore.aidl;

import android.os.IInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IAICoreService extends IInterface {
    int getApiVersion();

    long getDownloadableSizeInBytes(AIFeature aIFeature);

    int getFeatureStatus(AIFeature aIFeature);

    IImageDescriptionService getImageDescriptionService(AIFeature aIFeature);

    AIFeature[] listFeatures();

    @Deprecated
    void requestDownloadableFeatureWithDownloadListener$ar$ds(AIFeature aIFeature, IDownloadListener iDownloadListener);

    void requestDownloadableFeatureWithDownloadListener2$ar$ds(AIFeature aIFeature, IDownloadListener2 iDownloadListener2);
}

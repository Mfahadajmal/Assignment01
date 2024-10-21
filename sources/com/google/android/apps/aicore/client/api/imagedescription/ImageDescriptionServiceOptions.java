package com.google.android.apps.aicore.client.api.imagedescription;

import com.google.android.apps.aicore.client.api.AiCoreClient;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.DownloadCallback;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionServiceOptions {
    public final AiCoreClient aiCoreClient;
    public final DownloadCallback downloadCallback;
    public final AiFeature feature;

    public ImageDescriptionServiceOptions() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImageDescriptionServiceOptions) {
            ImageDescriptionServiceOptions imageDescriptionServiceOptions = (ImageDescriptionServiceOptions) obj;
            if (this.aiCoreClient.equals(imageDescriptionServiceOptions.getAiCoreClient()) && this.downloadCallback.equals(imageDescriptionServiceOptions.getDownloadCallback()) && this.feature.equals(imageDescriptionServiceOptions.getFeature())) {
                return true;
            }
        }
        return false;
    }

    public final AiCoreClient getAiCoreClient() {
        return this.aiCoreClient;
    }

    public final DownloadCallback getDownloadCallback() {
        return this.downloadCallback;
    }

    public final AiFeature getFeature() {
        return this.feature;
    }

    public final int hashCode() {
        return ((((this.aiCoreClient.hashCode() ^ 1000003) * 1000003) ^ this.downloadCallback.hashCode()) * 1000003) ^ this.feature.hashCode();
    }

    public final String toString() {
        AiFeature aiFeature = this.feature;
        DownloadCallback downloadCallback = this.downloadCallback;
        return "ImageDescriptionServiceOptions{aiCoreClient=" + String.valueOf(this.aiCoreClient) + ", downloadCallback=" + String.valueOf(downloadCallback) + ", feature=" + String.valueOf(aiFeature) + "}";
    }

    public ImageDescriptionServiceOptions(AiCoreClient aiCoreClient, DownloadCallback downloadCallback, AiFeature aiFeature) {
        this();
        this.aiCoreClient = aiCoreClient;
        this.downloadCallback = downloadCallback;
        this.feature = aiFeature;
    }
}

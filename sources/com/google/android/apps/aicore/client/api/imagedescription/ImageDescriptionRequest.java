package com.google.android.apps.aicore.client.api.imagedescription;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionRequest {
    private final List images;

    public ImageDescriptionRequest() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImageDescriptionRequest) && ContextDataProvider.equalsImpl(this.images, ((ImageDescriptionRequest) obj).getImages())) {
            return true;
        }
        return false;
    }

    public final List getImages() {
        return this.images;
    }

    public final int hashCode() {
        return (this.images.hashCode() ^ 1000003) * 1000003;
    }

    public final String toString() {
        return "ImageDescriptionRequest{images=" + String.valueOf(this.images) + ", onNextTextListener=null}";
    }

    public ImageDescriptionRequest(List list) {
        this();
        this.images = list;
    }
}

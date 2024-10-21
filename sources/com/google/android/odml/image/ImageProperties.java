package com.google.android.odml.image;

import com.google.android.odml.image.C$AutoValue_ImageProperties;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ImageProperties {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        abstract ImageProperties build();

        abstract Builder setImageFormat(int i);

        abstract Builder setStorageType(int i);
    }

    static Builder builder() {
        return new C$AutoValue_ImageProperties.Builder();
    }

    public abstract int getImageFormat();

    public abstract int getStorageType();
}

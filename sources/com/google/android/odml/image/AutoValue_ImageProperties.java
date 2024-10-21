package com.google.android.odml.image;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_ImageProperties extends C$AutoValue_ImageProperties {
    private volatile transient int hashCode;
    private volatile transient boolean hashCode$Memoized;

    public AutoValue_ImageProperties(int i, int i2) {
        super(i, i2);
    }

    @Override // com.google.android.odml.image.C$AutoValue_ImageProperties
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof AutoValue_ImageProperties) && hashCode() == obj.hashCode()) {
            if (this != obj) {
                if (obj instanceof ImageProperties) {
                    ImageProperties imageProperties = (ImageProperties) obj;
                    if (this.imageFormat != imageProperties.getImageFormat() || this.storageType != imageProperties.getStorageType()) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.odml.image.C$AutoValue_ImageProperties
    public final int hashCode() {
        if (!this.hashCode$Memoized) {
            synchronized (this) {
                if (!this.hashCode$Memoized) {
                    this.hashCode = ((this.imageFormat ^ 1000003) * 1000003) ^ this.storageType;
                    this.hashCode$Memoized = true;
                }
            }
        }
        return this.hashCode;
    }
}

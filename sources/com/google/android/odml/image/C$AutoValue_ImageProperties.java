package com.google.android.odml.image;

import com.google.android.odml.image.ImageProperties;

/* compiled from: PG */
/* renamed from: com.google.android.odml.image.$AutoValue_ImageProperties, reason: invalid class name */
/* loaded from: classes.dex */
class C$AutoValue_ImageProperties extends ImageProperties {
    public final int imageFormat;
    public final int storageType;

    /* compiled from: PG */
    /* renamed from: com.google.android.odml.image.$AutoValue_ImageProperties$Builder */
    /* loaded from: classes.dex */
    final class Builder extends ImageProperties.Builder {
        private int imageFormat;
        private byte set$0;
        private int storageType;

        @Override // com.google.android.odml.image.ImageProperties.Builder
        final ImageProperties build() {
            if (this.set$0 != 3) {
                StringBuilder sb = new StringBuilder();
                if ((this.set$0 & 1) == 0) {
                    sb.append(" imageFormat");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" storageType");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }
            return new AutoValue_ImageProperties(this.imageFormat, this.storageType);
        }

        @Override // com.google.android.odml.image.ImageProperties.Builder
        final ImageProperties.Builder setImageFormat(int i) {
            this.imageFormat = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.android.odml.image.ImageProperties.Builder
        final ImageProperties.Builder setStorageType(int i) {
            this.storageType = i;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }
    }

    public C$AutoValue_ImageProperties(int i, int i2) {
        this.imageFormat = i;
        this.storageType = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImageProperties) {
            ImageProperties imageProperties = (ImageProperties) obj;
            if (this.imageFormat == imageProperties.getImageFormat() && this.storageType == imageProperties.getStorageType()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.odml.image.ImageProperties
    public final int getImageFormat() {
        return this.imageFormat;
    }

    @Override // com.google.android.odml.image.ImageProperties
    public final int getStorageType() {
        return this.storageType;
    }

    public int hashCode() {
        return ((this.imageFormat ^ 1000003) * 1000003) ^ this.storageType;
    }

    public final String toString() {
        return "ImageProperties{imageFormat=" + this.imageFormat + ", storageType=" + this.storageType + "}";
    }
}

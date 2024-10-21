package com.google.android.accessibility.talkback.trainingcommon.tv;

import com.google.android.accessibility.talkback.trainingcommon.ExternalDrawableResource;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TvPageConfig {
    public final boolean enabled;
    public final ExternalDrawableResource image;
    public final String summary;
    public final String title;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object TvPageConfig$Builder$ar$image;
        public boolean enabled;
        public byte set$0;
        public String summary;
        public String title;

        public Builder() {
        }

        public final TvPageConfig build() {
            if (this.set$0 == 1) {
                return new TvPageConfig(this.enabled, this.title, this.summary, (ExternalDrawableResource) this.TvPageConfig$Builder$ar$image);
            }
            throw new IllegalStateException("Missing required properties: enabled");
        }

        public final void setEnabled$ar$ds$5aaec64b_0(boolean z) {
            this.enabled = z;
            this.set$0 = (byte) 1;
        }

        public Builder(byte[] bArr) {
            this();
        }

        public Builder(byte[] bArr, byte[] bArr2) {
            this();
        }
    }

    public TvPageConfig() {
    }

    public static Builder builder() {
        Builder builder = new Builder(null);
        builder.setEnabled$ar$ds$5aaec64b_0(true);
        return builder;
    }

    public final boolean enabled() {
        return this.enabled;
    }

    public final boolean equals(Object obj) {
        String str;
        String str2;
        ExternalDrawableResource externalDrawableResource;
        if (obj == this) {
            return true;
        }
        if (obj instanceof TvPageConfig) {
            TvPageConfig tvPageConfig = (TvPageConfig) obj;
            if (this.enabled == tvPageConfig.enabled() && ((str = this.title) != null ? str.equals(tvPageConfig.title()) : tvPageConfig.title() == null) && ((str2 = this.summary) != null ? str2.equals(tvPageConfig.summary()) : tvPageConfig.summary() == null) && ((externalDrawableResource = this.image) != null ? externalDrawableResource.equals(tvPageConfig.image()) : tvPageConfig.image() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int i;
        int hashCode2;
        String str = this.title;
        int i2 = 0;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        if (true != this.enabled) {
            i = 1237;
        } else {
            i = 1231;
        }
        int i3 = hashCode ^ ((i ^ 1000003) * 1000003);
        String str2 = this.summary;
        if (str2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = str2.hashCode();
        }
        int i4 = ((i3 * 1000003) ^ hashCode2) * 1000003;
        ExternalDrawableResource externalDrawableResource = this.image;
        if (externalDrawableResource != null) {
            i2 = externalDrawableResource.hashCode();
        }
        return i4 ^ i2;
    }

    public final ExternalDrawableResource image() {
        return this.image;
    }

    public final String summary() {
        return this.summary;
    }

    public final String title() {
        return this.title;
    }

    public final String toString() {
        return "TvPageConfig{enabled=" + this.enabled + ", title=" + this.title + ", summary=" + this.summary + ", image=" + String.valueOf(this.image) + "}";
    }

    public TvPageConfig(boolean z, String str, String str2, ExternalDrawableResource externalDrawableResource) {
        this();
        this.enabled = z;
        this.title = str;
        this.summary = str2;
        this.image = externalDrawableResource;
    }
}

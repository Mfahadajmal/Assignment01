package com.google.android.accessibility.talkback.trainingcommon;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExternalDrawableResource {
    public final String packageName;
    public final int resourceId;

    public ExternalDrawableResource() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ExternalDrawableResource) {
            ExternalDrawableResource externalDrawableResource = (ExternalDrawableResource) obj;
            if (this.packageName.equals(externalDrawableResource.packageName()) && this.resourceId == externalDrawableResource.resourceId()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.packageName.hashCode() ^ 1000003) * 1000003) ^ this.resourceId;
    }

    public final String packageName() {
        return this.packageName;
    }

    public final int resourceId() {
        return this.resourceId;
    }

    public final String toString() {
        return "ExternalDrawableResource{packageName=" + this.packageName + ", resourceId=" + this.resourceId + "}";
    }

    public ExternalDrawableResource(String str, int i) {
        this();
        this.packageName = str;
        this.resourceId = i;
    }
}

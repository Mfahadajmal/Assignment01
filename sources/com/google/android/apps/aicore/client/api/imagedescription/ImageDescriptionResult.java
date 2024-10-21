package com.google.android.apps.aicore.client.api.imagedescription;

import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionResult {
    private final List descriptions;

    public ImageDescriptionResult() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImageDescriptionResult) {
            return this.descriptions.equals(((ImageDescriptionResult) obj).getDescriptions());
        }
        return false;
    }

    public final List getDescriptions() {
        return this.descriptions;
    }

    public final int hashCode() {
        return this.descriptions.hashCode() ^ 1000003;
    }

    public final String toString() {
        return "ImageDescriptionResult{descriptions=" + this.descriptions.toString() + "}";
    }

    public ImageDescriptionResult(List list) {
        this();
        if (list == null) {
            throw new NullPointerException("Null descriptions");
        }
        this.descriptions = list;
    }
}

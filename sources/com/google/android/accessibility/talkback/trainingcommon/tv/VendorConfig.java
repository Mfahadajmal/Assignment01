package com.google.android.accessibility.talkback.trainingcommon.tv;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VendorConfig {
    private final ImmutableMap defaultPages;
    private final ImmutableList vendorPages;

    public VendorConfig() {
    }

    public final ImmutableMap defaultPages() {
        return this.defaultPages;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VendorConfig) {
            VendorConfig vendorConfig = (VendorConfig) obj;
            if (ContextDataProvider.equalsImpl(this.defaultPages, vendorConfig.defaultPages()) && ContextDataProvider.equalsImpl(this.vendorPages, vendorConfig.vendorPages())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.defaultPages.hashCode() ^ 1000003) * 1000003) ^ this.vendorPages.hashCode();
    }

    public final String toString() {
        ImmutableList immutableList = this.vendorPages;
        return "VendorConfig{defaultPages=" + this.defaultPages.toString() + ", vendorPages=" + immutableList.toString() + "}";
    }

    public final ImmutableList vendorPages() {
        return this.vendorPages;
    }

    public VendorConfig(ImmutableMap immutableMap, ImmutableList immutableList) {
        this();
        if (immutableMap == null) {
            throw new NullPointerException("Null defaultPages");
        }
        this.defaultPages = immutableMap;
        if (immutableList != null) {
            this.vendorPages = immutableList;
            return;
        }
        throw new NullPointerException("Null vendorPages");
    }
}

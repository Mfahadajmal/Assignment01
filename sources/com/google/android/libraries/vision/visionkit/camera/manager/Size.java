package com.google.android.libraries.vision.visionkit.camera.manager;

import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Size {
    public final int height;
    public final int width;

    public Size(int i, int i2) {
        boolean z;
        if (i < 32767 && i >= 0) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z);
        ContextDataProvider.checkArgument(i2 < 32767 && i2 >= 0);
        this.width = i;
        this.height = i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.width == size.width && this.height == size.height) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.width << 16) | this.height;
    }

    public final String toString() {
        return this.width + "x" + this.height;
    }
}

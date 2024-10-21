package com.google.android.accessibility.utils.coordinate;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MagnificationCoordinateConfig {
    public static final SpannableUtils$IdentifierSpan Companion$ar$class_merging$89f24df3_0 = new SpannableUtils$IdentifierSpan();
    private final Point centerGlobal;
    public final Rect displayGlobal;
    public final Rect displayLocal;
    private final float scale;

    public /* synthetic */ MagnificationCoordinateConfig(float f, Point point, Rect rect) {
        float width = rect.width();
        float height = rect.height();
        int width2 = (int) ((rect.width() / f) / 2.0f);
        int height2 = (int) ((rect.height() / f) / 2.0f);
        Rect rect2 = new Rect(point.x - width2, point.y - height2, point.x + width2, point.y + height2);
        int i = -((int) (rect2.left * f));
        int i2 = -((int) (rect2.top * f));
        Rect rect3 = new Rect(i, i2, ((int) (width * f)) + i, ((int) (height * f)) + i2);
        this.scale = f;
        this.centerGlobal = point;
        this.displayGlobal = rect;
        this.displayLocal = rect3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MagnificationCoordinateConfig)) {
            return false;
        }
        MagnificationCoordinateConfig magnificationCoordinateConfig = (MagnificationCoordinateConfig) obj;
        if (Float.compare(this.scale, magnificationCoordinateConfig.scale) == 0 && Intrinsics.areEqual(this.centerGlobal, magnificationCoordinateConfig.centerGlobal) && Intrinsics.areEqual(this.displayGlobal, magnificationCoordinateConfig.displayGlobal) && Intrinsics.areEqual(this.displayLocal, magnificationCoordinateConfig.displayLocal)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((((Float.floatToIntBits(this.scale) * 31) + this.centerGlobal.hashCode()) * 31) + this.displayGlobal.hashCode()) * 31) + this.displayLocal.hashCode();
    }

    public final String toString() {
        return "MagnificationCoordinateConfig(scale=" + this.scale + ", centerGlobal=" + this.centerGlobal + ", displayGlobal=" + this.displayGlobal + ", displayLocal=" + this.displayLocal + ")";
    }
}

package androidx.cardview.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RoundRectDrawableWithShadow extends Drawable {
    private static final double COS_45 = Math.cos(Math.toRadians(45.0d));

    public static float calculateHorizontalPadding(float f, float f2, boolean z) {
        if (z) {
            return (float) (f + ((1.0d - COS_45) * f2));
        }
        return f;
    }

    public static float calculateVerticalPadding(float f, float f2, boolean z) {
        float f3 = f * 1.5f;
        if (z) {
            return (float) (f3 + ((1.0d - COS_45) * f2));
        }
        return f3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    protected final boolean onStateChange(int[] iArr) {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        throw null;
    }
}

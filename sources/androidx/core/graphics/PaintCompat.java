package androidx.core.graphics;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EdgeEffect;
import androidx.core.widget.EdgeEffectCompat$Api21Impl;
import androidx.core.widget.EdgeEffectCompat$Api31Impl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PaintCompat {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        public static EdgeEffect create(Context context, AttributeSet attributeSet) {
            if (Build.VERSION.SDK_INT >= 31) {
                return EdgeEffectCompat$Api31Impl.create(context, attributeSet);
            }
            return new EdgeEffect(context);
        }

        public static float getDistance(EdgeEffect edgeEffect) {
            if (Build.VERSION.SDK_INT >= 31) {
                return EdgeEffectCompat$Api31Impl.getDistance(edgeEffect);
            }
            return 0.0f;
        }

        public static float onPullDistance(EdgeEffect edgeEffect, float f, float f2) {
            if (Build.VERSION.SDK_INT >= 31) {
                return EdgeEffectCompat$Api31Impl.onPullDistance(edgeEffect, f, f2);
            }
            EdgeEffectCompat$Api21Impl.onPull(edgeEffect, f, f2);
            return f;
        }

        static void setBlendMode(Paint paint, Object obj) {
            paint.setBlendMode((BlendMode) obj);
        }
    }

    static {
        new ThreadLocal();
    }

    public static void setBlendMode$ar$ds(Paint paint, BlendModeCompat blendModeCompat) {
        PorterDuff.Mode mode;
        PorterDuffXfermode porterDuffXfermode = null;
        Object obj = null;
        if (Build.VERSION.SDK_INT >= 29) {
            if (blendModeCompat != null) {
                obj = BlendModeUtils$Api29Impl.obtainBlendModeFromCompat(blendModeCompat);
            }
            Api29Impl.setBlendMode(paint, obj);
            return;
        }
        if (blendModeCompat != null) {
            switch (blendModeCompat) {
                case CLEAR:
                    mode = PorterDuff.Mode.CLEAR;
                    break;
                case SRC:
                    mode = PorterDuff.Mode.SRC;
                    break;
                case DST:
                    mode = PorterDuff.Mode.DST;
                    break;
                case SRC_OVER:
                    mode = PorterDuff.Mode.SRC_OVER;
                    break;
                case DST_OVER:
                    mode = PorterDuff.Mode.DST_OVER;
                    break;
                case SRC_IN:
                    mode = PorterDuff.Mode.SRC_IN;
                    break;
                case DST_IN:
                    mode = PorterDuff.Mode.DST_IN;
                    break;
                case SRC_OUT:
                    mode = PorterDuff.Mode.SRC_OUT;
                    break;
                case DST_OUT:
                    mode = PorterDuff.Mode.DST_OUT;
                    break;
                case SRC_ATOP:
                    mode = PorterDuff.Mode.SRC_ATOP;
                    break;
                case DST_ATOP:
                    mode = PorterDuff.Mode.DST_ATOP;
                    break;
                case XOR:
                    mode = PorterDuff.Mode.XOR;
                    break;
                case PLUS:
                    mode = PorterDuff.Mode.ADD;
                    break;
                case MODULATE:
                    mode = PorterDuff.Mode.MULTIPLY;
                    break;
                case SCREEN:
                    mode = PorterDuff.Mode.SCREEN;
                    break;
                case OVERLAY:
                    mode = PorterDuff.Mode.OVERLAY;
                    break;
                case DARKEN:
                    mode = PorterDuff.Mode.DARKEN;
                    break;
                case LIGHTEN:
                    mode = PorterDuff.Mode.LIGHTEN;
                    break;
                default:
                    mode = null;
                    break;
            }
            if (mode != null) {
                porterDuffXfermode = new PorterDuffXfermode(mode);
            }
            paint.setXfermode(porterDuffXfermode);
            return;
        }
        paint.setXfermode(null);
    }
}

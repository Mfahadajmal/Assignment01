package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ElevationOverlayProvider {
    private static final int OVERLAY_ACCENT_COLOR_ALPHA = (int) Math.round(5.1000000000000005d);
    public final int colorSurface;
    private final float displayDensity;
    private final int elevationOverlayAccentColor;
    private final int elevationOverlayColor;
    public final boolean elevationOverlayEnabled;

    public ElevationOverlayProvider(Context context) {
        boolean resolveBoolean = DrawableUtils$OutlineCompatR.resolveBoolean(context, R.attr.elevationOverlayEnabled, false);
        int color = FileUtils.getColor(context, R.attr.elevationOverlayColor, 0);
        int color2 = FileUtils.getColor(context, R.attr.elevationOverlayAccentColor, 0);
        int color3 = FileUtils.getColor(context, R.attr.colorSurface, 0);
        float f = context.getResources().getDisplayMetrics().density;
        this.elevationOverlayEnabled = resolveBoolean;
        this.elevationOverlayColor = color;
        this.elevationOverlayAccentColor = color2;
        this.colorSurface = color3;
        this.displayDensity = f;
    }

    public final int compositeOverlayIfNeeded(int i, float f) {
        float f2;
        int i2;
        if (this.elevationOverlayEnabled && ColorUtils.setAlphaComponent(i, PrivateKeyType.INVALID) == this.colorSurface) {
            if (this.displayDensity > 0.0f && f > 0.0f) {
                f2 = Math.min(((((float) Math.log1p(f / r1)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
            } else {
                f2 = 0.0f;
            }
            int alpha = Color.alpha(i);
            int layer = FileUtils.layer(ColorUtils.setAlphaComponent(i, PrivateKeyType.INVALID), this.elevationOverlayColor, f2);
            if (f2 > 0.0f && (i2 = this.elevationOverlayAccentColor) != 0) {
                layer = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i2, OVERLAY_ACCENT_COLOR_ALPHA), layer);
            }
            return ColorUtils.setAlphaComponent(layer, alpha);
        }
        return i;
    }
}

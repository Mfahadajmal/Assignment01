package androidx.core.graphics;

import android.graphics.Color;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ColorUtils {
    public static final ThreadLocal TEMP_ARRAY = new ThreadLocal();

    public static int XYZToColor(double d, double d2, double d3) {
        double d4;
        double d5;
        double d6;
        double d7 = (((3.2406d * d) + ((-1.5372d) * d2)) + ((-0.4986d) * d3)) / 100.0d;
        if (d7 > 0.0031308d) {
            d4 = (Math.pow(d7, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d4 = d7 * 12.92d;
        }
        double d8 = ((((-0.9689d) * d) + (1.8758d * d2)) + (0.0415d * d3)) / 100.0d;
        if (d8 > 0.0031308d) {
            d5 = (Math.pow(d8, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d5 = d8 * 12.92d;
        }
        double d9 = (((0.0557d * d) + ((-0.204d) * d2)) + (1.057d * d3)) / 100.0d;
        if (d9 > 0.0031308d) {
            d6 = (Math.pow(d9, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d6 = d9 * 12.92d;
        }
        return Color.rgb(constrain$ar$ds((int) Math.round(d4 * 255.0d)), constrain$ar$ds((int) Math.round(d5 * 255.0d)), constrain$ar$ds((int) Math.round(d6 * 255.0d)));
    }

    public static int compositeColors(int i, int i2) {
        int alpha = Color.alpha(i2);
        int alpha2 = Color.alpha(i);
        int red = Color.red(i);
        int red2 = Color.red(i2);
        int i3 = 255 - (((255 - alpha) * (255 - alpha2)) / PrivateKeyType.INVALID);
        return Color.argb(i3, compositeComponent(red, alpha2, red2, alpha, i3), compositeComponent(Color.green(i), alpha2, Color.green(i2), alpha, i3), compositeComponent(Color.blue(i), alpha2, Color.blue(i2), alpha, i3));
    }

    private static int compositeComponent(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((i * PrivateKeyType.INVALID) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * PrivateKeyType.INVALID);
    }

    private static int constrain$ar$ds(int i) {
        if (i < 0) {
            return 0;
        }
        return Math.min(i, PrivateKeyType.INVALID);
    }

    public static int setAlphaComponent(int i, int i2) {
        if (i2 >= 0 && i2 <= 255) {
            return (i & 16777215) | (i2 << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }
}

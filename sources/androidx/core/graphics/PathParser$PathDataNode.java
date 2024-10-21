package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import androidx.core.app.NotificationCompatBuilder$Api21Impl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PathParser$PathDataNode {
    public final float[] mParams;
    public char mType;

    public PathParser$PathDataNode(char c, float[] fArr) {
        this.mType = c;
        this.mParams = fArr;
    }

    public static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
        double d;
        double d2;
        double radians = Math.toRadians(f7);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d3 = f;
        double d4 = f2;
        double d5 = d4;
        double d6 = f4;
        double d7 = d6 * sin;
        double d8 = ((-f3) * sin) + (d6 * cos);
        double d9 = f6;
        double d10 = (((-f) * sin) + (d4 * cos)) / d9;
        double d11 = d8 / d9;
        double d12 = d10 - d11;
        double d13 = (d3 * cos) + (d4 * sin);
        double d14 = f5;
        double d15 = d13 / d14;
        double d16 = ((f3 * cos) + d7) / d14;
        double d17 = d15 - d16;
        double d18 = (d17 * d17) + (d12 * d12);
        if (d18 == 0.0d) {
            Log.w("PathParser", " Points are coincident");
            return;
        }
        double d19 = (1.0d / d18) - 0.25d;
        if (d19 < 0.0d) {
            Log.w("PathParser", "Points are too far apart " + d18);
            float sqrt = (float) (Math.sqrt(d18) / 1.99999d);
            drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
            return;
        }
        double sqrt2 = Math.sqrt(d19);
        double d20 = (d10 + d11) / 2.0d;
        double d21 = d17 * sqrt2;
        double d22 = (d15 + d16) / 2.0d;
        double d23 = sqrt2 * d12;
        if (z == z2) {
            d = d22 - d23;
            d2 = d20 + d21;
        } else {
            d = d22 + d23;
            d2 = d20 - d21;
        }
        double atan2 = Math.atan2(d10 - d2, d15 - d);
        double atan22 = Math.atan2(d11 - d2, d16 - d) - atan2;
        if (z2 != (atan22 >= 0.0d)) {
            atan22 += atan22 > 0.0d ? -6.283185307179586d : 6.283185307179586d;
        }
        double d24 = d * d14;
        double d25 = d2 * d9;
        double d26 = d24 * cos;
        double d27 = d25 * sin;
        double d28 = d24 * sin;
        double d29 = d25 * cos;
        int ceil = (int) Math.ceil(Math.abs((atan22 * 4.0d) / 3.141592653589793d));
        double cos2 = Math.cos(radians);
        double sin2 = Math.sin(radians);
        double cos3 = Math.cos(atan2);
        double sin3 = Math.sin(atan2);
        double d30 = -d14;
        double d31 = d30 * cos2;
        double d32 = d9 * sin2;
        double d33 = d30 * sin2;
        double d34 = d9 * cos2;
        double d35 = (sin3 * d33) + (cos3 * d34);
        double d36 = (d31 * sin3) - (d32 * cos3);
        double d37 = d3;
        int i = 0;
        double d38 = atan2;
        while (i < ceil) {
            double d39 = d33;
            double d40 = d38 + (atan22 / ceil);
            double sin4 = Math.sin(d40);
            double cos4 = Math.cos(d40);
            double d41 = d40 - d38;
            double tan = Math.tan(d41 / 2.0d);
            double sin5 = (Math.sin(d41) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
            double d42 = d29;
            double d43 = d28;
            double d44 = d5 + (d35 * sin5);
            double d45 = d26;
            path.rLineTo(0.0f, 0.0f);
            double d46 = d28 + d29 + (d14 * sin2 * cos4) + (d34 * sin4);
            double d47 = atan22;
            double d48 = ((d26 - d27) + ((d14 * cos2) * cos4)) - (d32 * sin4);
            d35 = (sin4 * d39) + (cos4 * d34);
            double d49 = (d31 * sin4) - (d32 * cos4);
            path.cubicTo((float) (d37 + (d36 * sin5)), (float) d44, (float) (d48 - (sin5 * d49)), (float) (d46 - (sin5 * d35)), (float) d48, (float) d46);
            i++;
            d29 = d42;
            d28 = d43;
            ceil = ceil;
            d38 = d40;
            d14 = d14;
            d33 = d39;
            d37 = d48;
            d36 = d49;
            atan22 = d47;
            d5 = d46;
            d26 = d45;
        }
    }

    public PathParser$PathDataNode(PathParser$PathDataNode pathParser$PathDataNode) {
        this.mType = pathParser$PathDataNode.mType;
        float[] fArr = pathParser$PathDataNode.mParams;
        this.mParams = NotificationCompatBuilder$Api21Impl.copyOfRange$ar$ds(fArr, fArr.length);
    }
}

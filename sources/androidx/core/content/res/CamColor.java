package androidx.core.content.res;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CamColor {
    public final float mAstar;
    public final float mBstar;
    public final float mChroma;
    public final float mHue;
    public final float mJ;
    public final float mJstar;

    public CamColor(float f, float f2, float f3, float f4, float f5, float f6) {
        this.mHue = f;
        this.mChroma = f2;
        this.mJ = f3;
        this.mJstar = f4;
        this.mAstar = f5;
        this.mBstar = f6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CamColor fromColor(int i) {
        float f;
        ViewingConditions viewingConditions = ViewingConditions.DEFAULT;
        float linearized = CamUtils.linearized(Color.red(i));
        float linearized2 = CamUtils.linearized(Color.green(i));
        float linearized3 = CamUtils.linearized(Color.blue(i));
        float[][] fArr = CamUtils.SRGB_TO_XYZ;
        float[] fArr2 = fArr[0];
        float f2 = (fArr2[0] * linearized) + (fArr2[1] * linearized2);
        float[] fArr3 = fArr[1];
        float f3 = (fArr3[0] * linearized) + (fArr3[1] * linearized2);
        float[] fArr4 = fArr[2];
        float[] fArr5 = {f2 + (fArr2[2] * linearized3), f3 + (fArr3[2] * linearized3), (linearized * fArr4[0]) + (linearized2 * fArr4[1]) + (linearized3 * fArr4[2])};
        float[][] fArr6 = CamUtils.XYZ_TO_CAM16RGB;
        float f4 = fArr5[0];
        float[] fArr7 = fArr6[0];
        float f5 = fArr7[0] * f4;
        float f6 = fArr5[1];
        float f7 = fArr7[1] * f6;
        float f8 = fArr5[2];
        float f9 = fArr7[2] * f8;
        float[] fArr8 = fArr6[1];
        float f10 = fArr8[0] * f4;
        float f11 = fArr8[1] * f6;
        float f12 = fArr8[2] * f8;
        float[] fArr9 = fArr6[2];
        float f13 = f4 * fArr9[0];
        float f14 = f6 * fArr9[1];
        float f15 = f8 * fArr9[2];
        float[] fArr10 = viewingConditions.mRgbD;
        float f16 = fArr10[0] * (f5 + f7 + f9);
        float f17 = fArr10[1] * (f10 + f11 + f12);
        float f18 = fArr10[2] * (f13 + f14 + f15);
        float pow = (float) Math.pow((viewingConditions.mFl * Math.abs(f16)) / 100.0d, 0.42d);
        float pow2 = (float) Math.pow((viewingConditions.mFl * Math.abs(f17)) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow((viewingConditions.mFl * Math.abs(f18)) / 100.0d, 0.42d);
        float signum = ((Math.signum(f16) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f17) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f18) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d = signum3;
        float atan2 = (((float) Math.atan2(((float) ((signum + signum2) - (d + d))) / 9.0f, ((float) (((signum * 11.0d) + (signum2 * (-12.0d))) + d)) / 11.0f)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f19 = signum2 * 20.0f;
        float f20 = (3.1415927f * atan2) / 180.0f;
        float f21 = (((((40.0f * signum) + f19) + signum3) / 20.0f) * viewingConditions.mNbb) / viewingConditions.mAw;
        float f22 = viewingConditions.mC;
        float pow4 = (float) Math.pow(f21, viewingConditions.mZ * 0.69f);
        float f23 = viewingConditions.mC;
        float f24 = pow4 * 100.0f;
        float sqrt = ((float) Math.sqrt(f24 / 100.0f)) * 5.7971015f * (viewingConditions.mAw + 4.0f) * viewingConditions.mFlRoot;
        if (atan2 < 20.14d) {
            f = 360.0f + atan2;
        } else {
            f = atan2;
        }
        float f25 = (((signum * 20.0f) + f19) + (signum3 * 21.0f)) / 20.0f;
        float cos = ((float) (Math.cos(((f * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f * 3846.1538f * viewingConditions.mNcb;
        float sqrt2 = (float) Math.sqrt((r0 * r0) + (r6 * r6));
        float pow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, viewingConditions.mN), 0.73d)) * ((float) Math.pow((cos * sqrt2) / (f25 + 0.305f), 0.9d)) * ((float) Math.sqrt(f24 / 100.0d));
        float f26 = viewingConditions.mFlRoot * pow5;
        float f27 = viewingConditions.mC;
        float sqrt3 = (float) Math.sqrt((r6 * 0.69f) / (viewingConditions.mAw + 4.0f));
        double d2 = f20;
        float log = ((float) Math.log((0.0228f * f26) + 1.0f)) * 43.85965f;
        float cos2 = (float) Math.cos(d2);
        float sin = (float) Math.sin(d2);
        fArr5[0] = atan2;
        fArr5[1] = pow5;
        float[] fArr11 = {f24, sqrt, f26, sqrt3 * 50.0f, (1.7f * f24) / ((0.007f * f24) + 1.0f), cos2 * log, log * sin};
        return new CamColor(atan2, pow5, fArr11[0], fArr11[4], fArr11[5], fArr11[6]);
    }

    public static CamColor fromJch(float f, float f2, float f3) {
        ViewingConditions viewingConditions = ViewingConditions.DEFAULT;
        float f4 = viewingConditions.mC;
        double d = f / 100.0d;
        Math.sqrt(d);
        float f5 = viewingConditions.mAw;
        float f6 = viewingConditions.mFlRoot * f2;
        float sqrt = (float) Math.sqrt(d);
        float f7 = viewingConditions.mC;
        Math.sqrt(((f2 / sqrt) * 0.69f) / (viewingConditions.mAw + 4.0f));
        double d2 = (3.1415927f * f3) / 180.0f;
        float log = ((float) Math.log((f6 * 0.0228d) + 1.0d)) * 43.85965f;
        return new CamColor(f3, f2, f, (1.7f * f) / ((0.007f * f) + 1.0f), log * ((float) Math.cos(d2)), log * ((float) Math.sin(d2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int viewed(ViewingConditions viewingConditions) {
        float f = this.mChroma;
        float f2 = 0.0f;
        if (f != 0.0d) {
            double d = this.mJ;
            if (d != 0.0d) {
                f2 = f / ((float) Math.sqrt(d / 100.0d));
            }
        }
        float pow = (float) Math.pow(f2 / Math.pow(1.64d - Math.pow(0.29d, viewingConditions.mN), 0.73d), 1.1111111111111112d);
        double d2 = (this.mHue * 3.1415927f) / 180.0f;
        double cos = Math.cos(2.0d + d2) + 3.8d;
        float f3 = viewingConditions.mC;
        float pow2 = viewingConditions.mAw * ((float) Math.pow(this.mJ / 100.0d, 1.4492753673265821d / viewingConditions.mZ));
        float f4 = ((float) cos) * 0.25f * 3846.1538f * viewingConditions.mNcb;
        float f5 = pow2 / viewingConditions.mNbb;
        float sin = (float) Math.sin(d2);
        float cos2 = (float) Math.cos(d2);
        float f6 = (((0.305f + f5) * 23.0f) * pow) / (((f4 * 23.0f) + ((11.0f * pow) * cos2)) + ((108.0f * pow) * sin));
        float f7 = cos2 * f6;
        float f8 = f6 * sin;
        float f9 = f5 * 460.0f;
        float max = (float) Math.max(0.0d, (Math.abs(r2) * 27.13d) / (400.0d - Math.abs(r2)));
        float signum = Math.signum((((451.0f * f7) + f9) + (288.0f * f8)) / 1403.0f) * (100.0f / viewingConditions.mFl);
        float pow3 = (float) Math.pow(max, 2.380952380952381d);
        float max2 = (float) Math.max(0.0d, (Math.abs(r8) * 27.13d) / (400.0d - Math.abs(r8)));
        float signum2 = Math.signum(((f9 - (891.0f * f7)) - (261.0f * f8)) / 1403.0f) * (100.0f / viewingConditions.mFl);
        float pow4 = (float) Math.pow(max2, 2.380952380952381d);
        float f10 = ((f9 - (f7 * 220.0f)) - (f8 * 6300.0f)) / 1403.0f;
        float max3 = (float) Math.max(0.0d, (Math.abs(f10) * 27.13d) / (400.0d - Math.abs(f10)));
        float signum3 = Math.signum(f10) * (100.0f / viewingConditions.mFl);
        float pow5 = (float) Math.pow(max3, 2.380952380952381d);
        float[] fArr = viewingConditions.mRgbD;
        float f11 = (signum * pow3) / fArr[0];
        float f12 = (signum2 * pow4) / fArr[1];
        float f13 = (signum3 * pow5) / fArr[2];
        float[][] fArr2 = CamUtils.CAM16RGB_TO_XYZ;
        float[] fArr3 = fArr2[0];
        float f14 = fArr3[0] * f11;
        float f15 = fArr3[1] * f12;
        float f16 = fArr3[2] * f13;
        float[] fArr4 = fArr2[1];
        float f17 = fArr4[0] * f11;
        float f18 = fArr4[1] * f12;
        float f19 = fArr4[2] * f13;
        float[] fArr5 = fArr2[2];
        return ColorUtils.XYZToColor(f14 + f15 + f16, f17 + f18 + f19, (f11 * fArr5[0]) + (f12 * fArr5[1]) + (f13 * fArr5[2]));
    }
}

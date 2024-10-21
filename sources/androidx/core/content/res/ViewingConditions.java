package androidx.core.content.res;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ViewingConditions {
    static final ViewingConditions DEFAULT;
    public final float mAw;
    public final float mC = 0.69f;
    public final float mFl;
    public final float mFlRoot;
    public final float mN;
    public final float mNbb;
    public final float mNcb;
    public final float[] mRgbD;
    public final float mZ;

    static {
        float[] fArr = CamUtils.WHITE_POINT_D65;
        double yFromLStar$ar$ds = CamUtils.yFromLStar$ar$ds();
        float[][] fArr2 = CamUtils.XYZ_TO_CAM16RGB;
        float f = fArr[0];
        float[] fArr3 = fArr2[0];
        float f2 = fArr3[0] * f;
        float f3 = fArr[1];
        float f4 = fArr3[1] * f3;
        float f5 = fArr[2];
        float f6 = fArr3[2] * f5;
        float[] fArr4 = fArr2[1];
        float f7 = fArr4[0] * f;
        float f8 = fArr4[1] * f3;
        float f9 = fArr4[2] * f5;
        float[] fArr5 = fArr2[2];
        float f10 = f * fArr5[0];
        float f11 = f3 * fArr5[1];
        float f12 = f5 * fArr5[2];
        float f13 = (float) ((yFromLStar$ar$ds * 63.66197723675813d) / 100.0d);
        float exp = 1.0f - (((float) Math.exp(((-f13) - 42.0f) / 92.0f)) * 0.2777778f);
        double d = exp;
        if (d > 1.0d) {
            exp = 1.0f;
        } else if (d < 0.0d) {
            exp = 0.0f;
        }
        float f14 = f7 + f8 + f9;
        float f15 = f2 + f4 + f6;
        float[] fArr6 = {(((100.0f / f15) * exp) + 1.0f) - exp, (((100.0f / f14) * exp) + 1.0f) - exp, (((100.0f / ((f10 + f11) + f12)) * exp) + 1.0f) - exp};
        float f16 = 1.0f / ((5.0f * f13) + 1.0f);
        float f17 = f16 * f16 * f16 * f16;
        float f18 = 1.0f - f17;
        float f19 = f17 * f13;
        float cbrt = (float) Math.cbrt(f13 * 5.0d);
        float yFromLStar$ar$ds2 = CamUtils.yFromLStar$ar$ds() / fArr[1];
        double d2 = yFromLStar$ar$ds2;
        float sqrt = (float) Math.sqrt(d2);
        float pow = (float) Math.pow(d2, 0.2d);
        float f20 = f19 + (0.1f * f18 * f18 * cbrt);
        float pow2 = (float) Math.pow(((fArr6[0] * f20) * f15) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow(((fArr6[1] * f20) * f14) / 100.0d, 0.42d);
        float pow4 = (float) Math.pow(((fArr6[2] * f20) * r5) / 100.0d, 0.42d);
        float[] fArr7 = {pow2, pow3, pow4};
        float f21 = fArr7[0];
        float f22 = (f21 * 400.0f) / (f21 + 27.13f);
        float f23 = fArr7[1];
        float f24 = (400.0f * pow4) / (pow4 + 27.13f);
        float[] fArr8 = {f22, (f23 * 400.0f) / (f23 + 27.13f), f24};
        float f25 = fArr8[0];
        float f26 = 0.725f / pow;
        DEFAULT = new ViewingConditions(yFromLStar$ar$ds2, (f25 + f25 + fArr8[1] + (f24 * 0.05f)) * f26, f26, f26, 0.69f, fArr6, f20, (float) Math.pow(f20, 0.25d), sqrt + 1.48f);
    }

    private ViewingConditions(float f, float f2, float f3, float f4, float f5, float[] fArr, float f6, float f7, float f8) {
        this.mN = f;
        this.mAw = f2;
        this.mNbb = f3;
        this.mNcb = f4;
        this.mRgbD = fArr;
        this.mFl = f6;
        this.mFlRoot = f7;
        this.mZ = f8;
    }
}

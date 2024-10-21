package com.google.android.accessibility.brailleime.input;

import android.graphics.PointF;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleInputPlaneTablet$$ExternalSyntheticLambda1 implements Comparator {
    public final /* synthetic */ BrailleInputPlane BrailleInputPlaneTablet$$ExternalSyntheticLambda1$ar$f$0;
    public final /* synthetic */ boolean f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BrailleInputPlaneTablet$$ExternalSyntheticLambda1(BrailleInputPlane brailleInputPlane, boolean z, int i) {
        this.switching_field = i;
        this.BrailleInputPlaneTablet$$ExternalSyntheticLambda1$ar$f$0 = brailleInputPlane;
        this.f$1 = z;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        if (this.switching_field != 0) {
            PointF pointF = (PointF) obj;
            PointF pointF2 = (PointF) obj2;
            BrailleInputPlanePhone brailleInputPlanePhone = (BrailleInputPlanePhone) this.BrailleInputPlaneTablet$$ExternalSyntheticLambda1$ar$f$0;
            int i = brailleInputPlanePhone.orientation;
            boolean z = this.f$1;
            if (i == 1) {
                if (brailleInputPlanePhone.isTableTopMode) {
                    f7 = pointF2.y;
                    f8 = pointF.y;
                } else {
                    f7 = pointF.x;
                    f8 = pointF2.x;
                }
                int compare = Float.compare(f7, f8);
                if (compare == 0) {
                    if (brailleInputPlanePhone.isTableTopMode) {
                        if (z) {
                            return Float.compare(pointF.x, pointF2.x);
                        }
                        return Float.compare(pointF2.x, pointF.x);
                    }
                    return Float.compare(pointF2.y, pointF.y);
                }
                return compare;
            }
            if (brailleInputPlanePhone.isTableTopMode) {
                f5 = pointF.x;
                f6 = pointF2.x;
            } else {
                f5 = pointF.y;
                f6 = pointF2.y;
            }
            int compare2 = Float.compare(f5, f6);
            if (compare2 == 0) {
                if (brailleInputPlanePhone.isTableTopMode) {
                    if (z) {
                        return Float.compare(pointF.y, pointF2.y);
                    }
                    return Float.compare(pointF2.y, pointF.y);
                }
                return Float.compare(pointF.x, pointF2.x);
            }
            return compare2;
        }
        PointF pointF3 = (PointF) obj;
        PointF pointF4 = (PointF) obj2;
        BrailleInputPlaneTablet brailleInputPlaneTablet = (BrailleInputPlaneTablet) this.BrailleInputPlaneTablet$$ExternalSyntheticLambda1$ar$f$0;
        int i2 = brailleInputPlaneTablet.orientation;
        boolean z2 = this.f$1;
        if (i2 == 1) {
            if (brailleInputPlaneTablet.isTableTopMode) {
                f3 = pointF3.x;
                f4 = pointF4.x;
            } else {
                f3 = pointF3.y;
                f4 = pointF4.y;
            }
            int compare3 = Float.compare(f3, f4);
            if (compare3 == 0) {
                if (brailleInputPlaneTablet.isTableTopMode) {
                    if (z2) {
                        return Float.compare(pointF3.y, pointF4.y);
                    }
                    return Float.compare(pointF4.y, pointF3.y);
                }
                if (z2) {
                    return Float.compare(pointF3.x, pointF4.x);
                }
                return Float.compare(pointF4.y, pointF3.y);
            }
            return compare3;
        }
        if (brailleInputPlaneTablet.isTableTopMode) {
            f = pointF3.x;
            f2 = pointF4.x;
        } else {
            f = pointF3.y;
            f2 = pointF4.y;
        }
        int compare4 = Float.compare(f, f2);
        if (compare4 == 0) {
            if (brailleInputPlaneTablet.isTableTopMode) {
                if (z2) {
                    return Float.compare(pointF3.y, pointF4.y);
                }
                return Float.compare(pointF4.y, pointF3.y);
            }
            return Float.compare(pointF3.x, pointF4.x);
        }
        return compare4;
    }
}

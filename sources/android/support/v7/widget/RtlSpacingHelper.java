package android.support.v7.widget;

/* compiled from: PG */
/* loaded from: classes.dex */
final class RtlSpacingHelper {
    public int mLeft = 0;
    public int mRight = 0;
    public int mStart = Integer.MIN_VALUE;
    public int mEnd = Integer.MIN_VALUE;
    public int mExplicitLeft = 0;
    public int mExplicitRight = 0;
    public boolean mIsRtl = false;
    public boolean mIsRelative = false;

    public final void setRelative(int i, int i2) {
        boolean z;
        boolean z2;
        int i3;
        this.mStart = i;
        this.mEnd = i2;
        this.mIsRelative = true;
        boolean z3 = this.mIsRtl;
        boolean z4 = false;
        if (i2 != Integer.MIN_VALUE) {
            z = false;
        } else {
            z = true;
        }
        if (i == Integer.MIN_VALUE) {
            z4 = true;
        }
        if (true != z3) {
            z2 = z4;
        } else {
            z2 = z;
        }
        if (true != z2) {
            if (true != z3) {
                i3 = i;
            } else {
                i3 = i2;
            }
            this.mLeft = i3;
        }
        if (true == z3) {
            z = z4;
        }
        if (true != z) {
            if (true != z3) {
                i = i2;
            }
            this.mRight = i;
        }
    }
}

package android.support.v7.widget;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewBoundsCheck$BoundFlags {
    int mBoundFlags = 0;
    int mChildEnd;
    int mChildStart;
    int mRvEnd;
    int mRvStart;

    static final int compare$ar$ds(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        if (i == i2) {
            return 2;
        }
        return 4;
    }

    public final void addFlags(int i) {
        this.mBoundFlags = i | this.mBoundFlags;
    }

    public final boolean boundsMatch() {
        int i = this.mBoundFlags;
        if ((i & 7) != 0 && (compare$ar$ds(this.mChildStart, this.mRvStart) & i) == 0) {
            return false;
        }
        if ((i & BrailleInputEvent.CMD_CONTROL_NEXT) != 0 && ((compare$ar$ds(this.mChildStart, this.mRvEnd) << 4) & i) == 0) {
            return false;
        }
        if ((i & 1792) != 0 && ((compare$ar$ds(this.mChildEnd, this.mRvStart) << 8) & i) == 0) {
            return false;
        }
        if ((i & 28672) != 0 && (i & (compare$ar$ds(this.mChildEnd, this.mRvEnd) << 12)) == 0) {
            return false;
        }
        return true;
    }

    public final void resetFlags() {
        this.mBoundFlags = 0;
    }

    public final void setBounds(int i, int i2, int i3, int i4) {
        this.mRvStart = i;
        this.mRvEnd = i2;
        this.mChildStart = i3;
        this.mChildEnd = i4;
    }
}

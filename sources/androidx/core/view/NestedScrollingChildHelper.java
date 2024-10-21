package androidx.core.view;

import android.view.View;
import android.view.ViewParent;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NestedScrollingChildHelper {
    public boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(View view) {
        this.mView = view;
    }

    private final ViewParent getNestedScrollingParentForType(int i) {
        if (i != 0) {
            return this.mNestedScrollingParentNonTouch;
        }
        return this.mNestedScrollingParentTouch;
    }

    private final int[] getTempNestedScrollConsumed() {
        if (this.mTempNestedScrollConsumed == null) {
            this.mTempNestedScrollConsumed = new int[2];
        }
        return this.mTempNestedScrollConsumed;
    }

    private final void setNestedScrollingParentForType(int i, ViewParent viewParent) {
        if (i != 0) {
            this.mNestedScrollingParentNonTouch = viewParent;
        } else {
            this.mNestedScrollingParentTouch = viewParent;
        }
    }

    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        ViewParent viewParent;
        if (this.mIsNestedScrollingEnabled && (viewParent = this.mNestedScrollingParentTouch) != null) {
            return ResourcesCompat.Api23Impl.onNestedFling(viewParent, this.mView, f, f2, z);
        }
        return false;
    }

    public final boolean dispatchNestedPreFling(float f, float f2) {
        ViewParent viewParent;
        if (this.mIsNestedScrollingEnabled && (viewParent = this.mNestedScrollingParentTouch) != null) {
            return ResourcesCompat.Api23Impl.onNestedPreFling(viewParent, this.mView, f, f2);
        }
        return false;
    }

    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return dispatchNestedPreScroll(i, i2, iArr, iArr2, 0);
    }

    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return dispatchNestedScrollInternal(i, i2, i3, i4, iArr, 0, null);
    }

    public final boolean dispatchNestedScrollInternal(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        ViewParent nestedScrollingParentForType;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int[] iArr3;
        int[] iArr4 = iArr;
        if (!this.mIsNestedScrollingEnabled || (nestedScrollingParentForType = getNestedScrollingParentForType(i5)) == null) {
            return false;
        }
        if (i == 0) {
            if (i2 == 0) {
                if (i3 == 0) {
                    if (i4 != 0) {
                        i6 = 0;
                        i7 = 0;
                        i8 = 0;
                    } else {
                        if (iArr4 != null) {
                            iArr4[0] = 0;
                            iArr4[1] = 0;
                        }
                        return false;
                    }
                } else {
                    i8 = i3;
                    i6 = 0;
                    i7 = 0;
                }
            } else {
                i7 = i2;
                i8 = i3;
                i6 = 0;
            }
        } else {
            i6 = i;
            i7 = i2;
            i8 = i3;
        }
        if (iArr4 != null) {
            this.mView.getLocationInWindow(iArr4);
            i9 = iArr4[0];
            i10 = iArr4[1];
        } else {
            iArr4 = null;
            i9 = 0;
            i10 = 0;
        }
        if (iArr2 == null) {
            int[] tempNestedScrollConsumed = getTempNestedScrollConsumed();
            tempNestedScrollConsumed[0] = 0;
            tempNestedScrollConsumed[1] = 0;
            iArr3 = tempNestedScrollConsumed;
        } else {
            iArr3 = iArr2;
        }
        ResourcesCompat.Api23Impl.onNestedScroll(nestedScrollingParentForType, this.mView, i6, i7, i8, i4, i5, iArr3);
        if (iArr4 != null) {
            this.mView.getLocationInWindow(iArr4);
            iArr4[0] = iArr4[0] - i9;
            iArr4[1] = iArr4[1] - i10;
        }
        return true;
    }

    public final boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public final void setNestedScrollingEnabled(boolean z) {
        if (this.mIsNestedScrollingEnabled) {
            ViewCompat.Api21Impl.stopNestedScroll(this.mView);
        }
        this.mIsNestedScrollingEnabled = z;
    }

    public final boolean startNestedScroll(int i) {
        return startNestedScroll(i, 0);
    }

    public final void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent nestedScrollingParentForType;
        int i4;
        int i5;
        int i6;
        if (!this.mIsNestedScrollingEnabled || (nestedScrollingParentForType = getNestedScrollingParentForType(i3)) == null) {
            return false;
        }
        if (i != 0) {
            i4 = i;
        } else {
            if (i2 == 0) {
                if (iArr2 != null) {
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                }
                return false;
            }
            i4 = 0;
        }
        if (iArr2 != null) {
            this.mView.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            iArr2 = null;
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            iArr = getTempNestedScrollConsumed();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        ResourcesCompat.Api23Impl.onNestedPreScroll(nestedScrollingParentForType, this.mView, i4, i2, iArr, i3);
        if (iArr2 != null) {
            this.mView.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public final boolean hasNestedScrollingParent(int i) {
        return getNestedScrollingParentForType(i) != null;
    }

    public final boolean startNestedScroll(int i, int i2) {
        if (hasNestedScrollingParent(i2)) {
            return true;
        }
        if (!this.mIsNestedScrollingEnabled) {
            return false;
        }
        View view = this.mView;
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (ResourcesCompat.Api23Impl.onStartNestedScroll(parent, view, this.mView, i, i2)) {
                setNestedScrollingParentForType(i2, parent);
                ResourcesCompat.Api23Impl.onNestedScrollAccepted(parent, view, this.mView, i, i2);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public final void stopNestedScroll(int i) {
        ViewParent nestedScrollingParentForType = getNestedScrollingParentForType(i);
        if (nestedScrollingParentForType != null) {
            ResourcesCompat.Api23Impl.onStopNestedScroll(nestedScrollingParentForType, this.mView, i);
            setNestedScrollingParentForType(i, null);
        }
    }
}

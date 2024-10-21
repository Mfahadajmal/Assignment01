package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChildHelper {
    final FloatingActionButton.ShadowDelegateImpl mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public View mViewInRemoveView;
    public int mRemoveStatus = 0;
    final Bucket mBucket = new Bucket();
    final List mHiddenViews = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Bucket {
        long mData = 0;
        Bucket mNext;

        private final void ensureNext() {
            if (this.mNext == null) {
                this.mNext = new Bucket();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void clear(int i) {
            if (i >= 64) {
                Bucket bucket = this.mNext;
                if (bucket != null) {
                    bucket.clear(i - 64);
                    return;
                }
                return;
            }
            this.mData &= ~(1 << i);
        }

        final int countOnesBefore(int i) {
            Bucket bucket = this.mNext;
            if (bucket == null) {
                if (i >= 64) {
                    return Long.bitCount(this.mData);
                }
                return Long.bitCount(this.mData & ((1 << i) - 1));
            }
            if (i < 64) {
                return Long.bitCount(this.mData & ((1 << i) - 1));
            }
            return bucket.countOnesBefore(i - 64) + Long.bitCount(this.mData);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean get(int i) {
            if (i >= 64) {
                ensureNext();
                return this.mNext.get(i - 64);
            }
            if ((this.mData & (1 << i)) != 0) {
                return true;
            }
            return false;
        }

        final void insert(int i, boolean z) {
            boolean z2;
            if (i >= 64) {
                ensureNext();
                this.mNext.insert(i - 64, z);
                return;
            }
            long j = this.mData;
            if ((Long.MIN_VALUE & j) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            long j2 = (1 << i) - 1;
            long j3 = j & j2;
            long j4 = j & (~j2);
            this.mData = (j4 + j4) | j3;
            if (z) {
                set(i);
            } else {
                clear(i);
            }
            if (!z2 && this.mNext == null) {
                return;
            }
            ensureNext();
            this.mNext.insert(0, z2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean remove(int i) {
            boolean z;
            if (i >= 64) {
                ensureNext();
                return this.mNext.remove(i - 64);
            }
            long j = 1 << i;
            long j2 = this.mData;
            if ((j2 & j) != 0) {
                z = true;
            } else {
                z = false;
            }
            long j3 = j2 & (~j);
            this.mData = j3;
            long j4 = j - 1;
            this.mData = (j4 & j3) | Long.rotateRight((~j4) & j3, 1);
            Bucket bucket = this.mNext;
            if (bucket != null) {
                if (bucket.get(0)) {
                    set(63);
                }
                this.mNext.remove(0);
            }
            return z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void reset() {
            this.mData = 0L;
            Bucket bucket = this.mNext;
            if (bucket != null) {
                bucket.reset();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void set(int i) {
            if (i >= 64) {
                ensureNext();
                this.mNext.set(i - 64);
            } else {
                this.mData |= 1 << i;
            }
        }

        public final String toString() {
            if (this.mNext == null) {
                return Long.toBinaryString(this.mData);
            }
            return this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
        }
    }

    public ChildHelper(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
    }

    private final int getOffset(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            int countOnesBefore = i - (i2 - this.mBucket.countOnesBefore(i2));
            if (countOnesBefore == 0) {
                while (this.mBucket.get(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += countOnesBefore;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addView(View view, int i, boolean z) {
        int offset;
        if (i < 0) {
            offset = this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getChildCount();
        } else {
            offset = getOffset(i);
        }
        this.mBucket.insert(offset, z);
        if (z) {
            hideViewInternal(view);
        }
        ((RecyclerView) this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).addView(view, offset);
        RecyclerView.getChildViewHolderInt(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int offset;
        if (i < 0) {
            offset = this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getChildCount();
        } else {
            offset = getOffset(i);
        }
        this.mBucket.insert(offset, z);
        if (z) {
            hideViewInternal(view);
        }
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt + ((RecyclerView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).exceptionLabel());
            }
            childViewHolderInt.clearTmpDetachFlag();
        }
        ((RecyclerView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).attachViewToParent(view, offset, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void detachViewFromParent(int i) {
        Bucket bucket = this.mBucket;
        int offset = getOffset(i);
        bucket.remove(offset);
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        View childAt = shadowDelegateImpl.getChildAt(offset);
        if (childAt != null) {
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
            if (childViewHolderInt != null) {
                if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt + ((RecyclerView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).exceptionLabel());
                }
                childViewHolderInt.addFlags(256);
            }
        } else {
            int i2 = RecyclerView.RecyclerView$ar$NoOp;
        }
        ((RecyclerView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).detachViewFromParent(offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final View getChildAt(int i) {
        return this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getChildAt(getOffset(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getChildCount() {
        return this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getChildCount() - this.mHiddenViews.size();
    }

    public final View getUnfilteredChildAt(int i) {
        return this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getChildAt(i);
    }

    public final int getUnfilteredChildCount() {
        return this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getChildCount();
    }

    public final void hideViewInternal(View view) {
        this.mHiddenViews.add(view);
        RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            int i = childViewHolderInt.mPendingAccessibilityState;
            if (i != -1) {
                childViewHolderInt.mWasImportantForAccessibilityBeforeHidden = i;
            } else {
                childViewHolderInt.mWasImportantForAccessibilityBeforeHidden = childViewHolderInt.itemView.getImportantForAccessibility();
            }
            ((RecyclerView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).setChildImportantForAccessibilityInternal$ar$ds(childViewHolderInt, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int indexOfChild(View view) {
        int indexOfChild = this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.indexOfChild(view);
        if (indexOfChild == -1 || this.mBucket.get(indexOfChild)) {
            return -1;
        }
        return indexOfChild - this.mBucket.countOnesBefore(indexOfChild);
    }

    public final boolean isHidden(View view) {
        return this.mHiddenViews.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeViewAt(int i) {
        int i2 = this.mRemoveStatus;
        if (i2 != 1) {
            if (i2 != 2) {
                try {
                    int offset = getOffset(i);
                    View childAt = this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getChildAt(offset);
                    if (childAt != null) {
                        this.mRemoveStatus = 1;
                        this.mViewInRemoveView = childAt;
                        if (this.mBucket.remove(offset)) {
                            unhideViewInternal$ar$ds(childAt);
                        }
                        this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.removeViewAt(offset);
                    }
                    return;
                } finally {
                    this.mRemoveStatus = 0;
                    this.mViewInRemoveView = null;
                }
            }
            throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
        }
        throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
    }

    public final String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    public final void unhideViewInternal$ar$ds(View view) {
        if (this.mHiddenViews.remove(view)) {
            this.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onLeftHiddenState(view);
        }
    }
}

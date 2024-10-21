package androidx.constraintlayout.core.widgets;

import _COROUTINE._BOUNDARY;
import android.support.v7.widget.TooltipCompat$Api26Impl;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintAnchor {
    public int mFinalValue;
    public boolean mHasFinalValue;
    public final ConstraintWidget mOwner;
    public SolverVariable mSolverVariable;
    public ConstraintAnchor mTarget;
    public final int mType$ar$edu$21f04e0f_0;
    public HashSet mDependents = null;
    public int mMargin = 0;
    int mGoneMargin = Integer.MIN_VALUE;

    public ConstraintAnchor(ConstraintWidget constraintWidget, int i) {
        this.mOwner = constraintWidget;
        this.mType$ar$edu$21f04e0f_0 = i;
    }

    public final void connect$ar$ds(ConstraintAnchor constraintAnchor, int i, int i2, boolean z) {
        if (constraintAnchor == null) {
            reset();
            return;
        }
        this.mTarget = constraintAnchor;
        if (constraintAnchor.mDependents == null) {
            constraintAnchor.mDependents = new HashSet();
        }
        HashSet hashSet = this.mTarget.mDependents;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.mMargin = i;
        this.mGoneMargin = i2;
    }

    public final void findDependents(int i, ArrayList arrayList, WidgetGroup widgetGroup) {
        HashSet hashSet = this.mDependents;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                TooltipCompat$Api26Impl.findDependents(((ConstraintAnchor) it.next()).mOwner, i, arrayList, widgetGroup);
            }
        }
    }

    public final int getFinalValue() {
        if (!this.mHasFinalValue) {
            return 0;
        }
        return this.mFinalValue;
    }

    public final int getMargin() {
        ConstraintAnchor constraintAnchor;
        if (this.mOwner.mVisibility == 8) {
            return 0;
        }
        int i = this.mGoneMargin;
        if (i != Integer.MIN_VALUE && (constraintAnchor = this.mTarget) != null && constraintAnchor.mOwner.mVisibility == 8) {
            return i;
        }
        return this.mMargin;
    }

    public final boolean hasCenteredDependents() {
        ConstraintAnchor constraintAnchor;
        HashSet hashSet = this.mDependents;
        if (hashSet == null) {
            return false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ConstraintAnchor constraintAnchor2 = (ConstraintAnchor) it.next();
            int i = constraintAnchor2.mType$ar$edu$21f04e0f_0 - 1;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            constraintAnchor = null;
                        } else {
                            constraintAnchor = constraintAnchor2.mOwner.mTop;
                        }
                    } else {
                        constraintAnchor = constraintAnchor2.mOwner.mLeft;
                    }
                } else {
                    constraintAnchor = constraintAnchor2.mOwner.mBottom;
                }
            } else {
                constraintAnchor = constraintAnchor2.mOwner.mRight;
            }
            if (constraintAnchor.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public final boolean hasDependents() {
        HashSet hashSet = this.mDependents;
        if (hashSet == null || hashSet.size() <= 0) {
            return false;
        }
        return true;
    }

    public final boolean isConnected() {
        if (this.mTarget != null) {
            return true;
        }
        return false;
    }

    public final void reset() {
        HashSet hashSet;
        ConstraintAnchor constraintAnchor = this.mTarget;
        if (constraintAnchor != null && (hashSet = constraintAnchor.mDependents) != null) {
            hashSet.remove(this);
            if (this.mTarget.mDependents.size() == 0) {
                this.mTarget.mDependents = null;
            }
        }
        this.mDependents = null;
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = Integer.MIN_VALUE;
        this.mHasFinalValue = false;
        this.mFinalValue = 0;
    }

    public final void resetSolverVariable$ar$ds$cb2131bd_0() {
        SolverVariable solverVariable = this.mSolverVariable;
        if (solverVariable == null) {
            this.mSolverVariable = new SolverVariable(1);
        } else {
            solverVariable.reset();
        }
    }

    public final void setFinalValue(int i) {
        this.mFinalValue = i;
        this.mHasFinalValue = true;
    }

    public final String toString() {
        return this.mOwner.mDebugName + ":" + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_2(this.mType$ar$edu$21f04e0f_0);
    }
}

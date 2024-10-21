package androidx.constraintlayout.core.widgets;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Barrier extends HelperWidget {
    public int mBarrierType = 0;
    public boolean mAllowsGoneWidget = true;
    public int mMargin = 0;
    boolean mResolved = false;

    /* JADX WARN: Code restructure failed: missing block: B:105:0x00f3, code lost:
    
        if (r6 != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x00f7, code lost:
    
        if (r7 != false) goto L83;
     */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0194  */
    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addToSolver(androidx.constraintlayout.core.LinearSystem r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 524
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Barrier.addToSolver(androidx.constraintlayout.core.LinearSystem, boolean):void");
    }

    public final boolean allSolved() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        boolean z = true;
        while (true) {
            i = this.mWidgetsCount;
            if (i4 >= i) {
                break;
            }
            ConstraintWidget constraintWidget = this.mWidgets[i4];
            if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i2 = this.mBarrierType) == 0 || i2 == 1) && !constraintWidget.isResolvedHorizontally()) || (((i3 = this.mBarrierType) == 2 || i3 == 3) && !constraintWidget.isResolvedVertically()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int i5 = 0;
        boolean z2 = false;
        for (int i6 = 0; i6 < this.mWidgetsCount; i6++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i6];
            if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                if (!z2) {
                    int i7 = this.mBarrierType;
                    if (i7 == 0) {
                        i5 = constraintWidget2.getAnchor$ar$edu$6d5b24e9_0(2).getFinalValue();
                    } else if (i7 == 1) {
                        i5 = constraintWidget2.getAnchor$ar$edu$6d5b24e9_0(4).getFinalValue();
                    } else if (i7 == 2) {
                        i5 = constraintWidget2.getAnchor$ar$edu$6d5b24e9_0(3).getFinalValue();
                    } else if (i7 == 3) {
                        i5 = constraintWidget2.getAnchor$ar$edu$6d5b24e9_0(5).getFinalValue();
                    }
                }
                int i8 = this.mBarrierType;
                if (i8 == 0) {
                    i5 = Math.min(i5, constraintWidget2.getAnchor$ar$edu$6d5b24e9_0(2).getFinalValue());
                } else if (i8 == 1) {
                    i5 = Math.max(i5, constraintWidget2.getAnchor$ar$edu$6d5b24e9_0(4).getFinalValue());
                } else if (i8 == 2) {
                    i5 = Math.min(i5, constraintWidget2.getAnchor$ar$edu$6d5b24e9_0(3).getFinalValue());
                } else if (i8 == 3) {
                    i5 = Math.max(i5, constraintWidget2.getAnchor$ar$edu$6d5b24e9_0(5).getFinalValue());
                }
                z2 = true;
            }
        }
        int i9 = i5 + this.mMargin;
        int i10 = this.mBarrierType;
        if (i10 != 0 && i10 != 1) {
            setFinalVertical(i9, i9);
        } else {
            setFinalHorizontal(i9, i9);
        }
        this.mResolved = true;
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean allowedInBarrier() {
        return true;
    }

    public final int getOrientation() {
        int i = this.mBarrierType;
        if (i != 0 && i != 1) {
            if (i == 2 || i == 3) {
                return 1;
            }
            return -1;
        }
        return 0;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean isResolvedHorizontally() {
        return this.mResolved;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean isResolvedVertically() {
        return this.mResolved;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final String toString() {
        String str = "[Barrier] " + this.mDebugName + " {";
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (i > 0) {
                str = String.valueOf(str).concat(", ");
            }
            str = String.valueOf(str).concat(String.valueOf(constraintWidget.mDebugName));
        }
        return String.valueOf(str).concat("}");
    }
}

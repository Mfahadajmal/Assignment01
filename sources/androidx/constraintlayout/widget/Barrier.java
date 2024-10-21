package androidx.constraintlayout.widget;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Barrier extends ConstraintHelper {
    public androidx.constraintlayout.core.widgets.Barrier mBarrier;
    public int mIndicatedType;
    private int mResolvedType;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected final void init$ar$ds() {
        androidx.constraintlayout.core.widgets.Barrier barrier = new androidx.constraintlayout.core.widgets.Barrier();
        this.mBarrier = barrier;
        this.mHelperWidget$ar$class_merging = barrier;
        validateParams();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0010, code lost:
    
        if (r0 == 6) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0019, code lost:
    
        if (r0 == 6) goto L5;
     */
    @Override // androidx.constraintlayout.widget.ConstraintHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void resolveRtl(androidx.constraintlayout.core.widgets.ConstraintWidget r6, boolean r7) {
        /*
            r5 = this;
            int r0 = r5.mIndicatedType
            r5.mResolvedType = r0
            r1 = 6
            r2 = 5
            r3 = 1
            r4 = 0
            if (r7 == 0) goto L13
            if (r0 != r2) goto L10
        Lc:
            r5.mResolvedType = r3
            r0 = r3
            goto L1c
        L10:
            if (r0 != r1) goto L1c
            goto L15
        L13:
            if (r0 != r2) goto L19
        L15:
            r5.mResolvedType = r4
            r0 = r4
            goto L1c
        L19:
            if (r0 != r1) goto L1c
            goto Lc
        L1c:
            boolean r7 = r6 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r7 == 0) goto L24
            androidx.constraintlayout.core.widgets.Barrier r6 = (androidx.constraintlayout.core.widgets.Barrier) r6
            r6.mBarrierType = r0
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.Barrier.resolveRtl(androidx.constraintlayout.core.widgets.ConstraintWidget, boolean):void");
    }

    public final void setMargin(int i) {
        this.mBarrier.mMargin = i;
    }
}

package android.support.constraint.solver.widgets;

import _COROUTINE._BOUNDARY;
import android.support.constraint.solver.SolverVariable;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintAnchor {
    final ConstraintWidget mOwner;
    public SolverVariable mSolverVariable;
    ConstraintAnchor mTarget;
    final int mType$ar$edu$107da2f9_0;
    public int mMargin = 0;
    int mGoneMargin = -1;
    public int mStrength$ar$edu = 1;
    public int mConnectionType$ar$edu = 1;
    public int mConnectionCreator = 0;

    public ConstraintAnchor(ConstraintWidget constraintWidget, int i) {
        this.mOwner = constraintWidget;
        this.mType$ar$edu$107da2f9_0 = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x005e, code lost:
    
        if (r15 != 9) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0076, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0073, code lost:
    
        if (r15 != 8) goto L52;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void connect$ar$edu$ar$ds(android.support.constraint.solver.widgets.ConstraintAnchor r10, int r11, int r12, int r13, int r14, boolean r15) {
        /*
            r9 = this;
            r0 = -1
            r1 = 2
            r2 = 0
            r3 = 1
            if (r10 != 0) goto L12
            r10 = 0
            r9.mTarget = r10
            r9.mMargin = r2
            r9.mGoneMargin = r0
            r9.mStrength$ar$edu = r3
            r9.mConnectionCreator = r1
            return
        L12:
            if (r15 != 0) goto L7c
            int r15 = r10.mType$ar$edu$107da2f9_0
            int r4 = r9.mType$ar$edu$107da2f9_0
            r5 = 6
            if (r15 != r4) goto L34
            r15 = 7
            if (r4 != r15) goto L20
            goto L7b
        L20:
            if (r4 != r5) goto L7c
            android.support.constraint.solver.widgets.ConstraintWidget r15 = r10.mOwner
            boolean r15 = r15.hasBaseline()
            if (r15 == 0) goto L7b
            android.support.constraint.solver.widgets.ConstraintWidget r15 = r9.mOwner
            boolean r15 = r15.hasBaseline()
            if (r15 == 0) goto L7b
            goto L7c
        L34:
            int r4 = r4 + r0
            r0 = 8
            r6 = 4
            if (r4 == r3) goto L63
            r7 = 9
            r8 = 3
            if (r4 == r1) goto L4d
            if (r4 == r8) goto L63
            if (r4 == r6) goto L4d
            if (r4 == r5) goto L46
            goto L7b
        L46:
            if (r15 == r5) goto L7b
            if (r15 == r0) goto L7b
            if (r15 == r7) goto L7b
            goto L7c
        L4d:
            if (r15 == r8) goto L55
            r0 = 5
            if (r15 != r0) goto L53
            goto L55
        L53:
            r0 = r2
            goto L56
        L55:
            r0 = r3
        L56:
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r10.mOwner
            boolean r1 = r1 instanceof android.support.constraint.solver.widgets.Guideline
            if (r1 == 0) goto L61
            if (r0 != 0) goto L79
            if (r15 != r7) goto L76
            goto L79
        L61:
            r3 = r0
            goto L79
        L63:
            if (r15 == r1) goto L6a
            if (r15 != r6) goto L68
            goto L6a
        L68:
            r1 = r2
            goto L6b
        L6a:
            r1 = r3
        L6b:
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r10.mOwner
            boolean r4 = r4 instanceof android.support.constraint.solver.widgets.Guideline
            if (r4 == 0) goto L78
            if (r1 != 0) goto L79
            if (r15 != r0) goto L76
            goto L79
        L76:
            r3 = r2
            goto L79
        L78:
            r3 = r1
        L79:
            if (r3 != 0) goto L7c
        L7b:
            return
        L7c:
            r9.mTarget = r10
            if (r11 <= 0) goto L83
            r9.mMargin = r11
            goto L85
        L83:
            r9.mMargin = r2
        L85:
            r9.mGoneMargin = r12
            r9.mStrength$ar$edu = r13
            r9.mConnectionCreator = r14
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintAnchor.connect$ar$edu$ar$ds(android.support.constraint.solver.widgets.ConstraintAnchor, int, int, int, int, boolean):void");
    }

    public final int getMargin() {
        ConstraintAnchor constraintAnchor;
        if (this.mOwner.mVisibility == 8) {
            return 0;
        }
        int i = this.mGoneMargin;
        if (i >= 0 && (constraintAnchor = this.mTarget) != null && constraintAnchor.mOwner.mVisibility == 8) {
            return i;
        }
        return this.mMargin;
    }

    public final boolean isConnected() {
        if (this.mTarget != null) {
            return true;
        }
        return false;
    }

    public final void reset() {
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = -1;
        this.mStrength$ar$edu = 2;
        this.mConnectionCreator = 0;
        this.mConnectionType$ar$edu = 1;
    }

    public final void resetSolverVariable$ar$ds() {
        SolverVariable solverVariable = this.mSolverVariable;
        if (solverVariable == null) {
            this.mSolverVariable = new SolverVariable(1);
        } else {
            solverVariable.reset();
        }
    }

    public final String toString() {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder("null:");
        sb.append(_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_2(this.mType$ar$edu$107da2f9_0));
        ConstraintAnchor constraintAnchor = this.mTarget;
        sb.append(constraintAnchor != null ? " connected to ".concat(String.valueOf(constraintAnchor.toString(hashSet))) : "");
        return sb.toString();
    }

    private final String toString(HashSet hashSet) {
        if (!hashSet.add(this)) {
            return "<-";
        }
        StringBuilder sb = new StringBuilder("null:");
        sb.append(_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_2(this.mType$ar$edu$107da2f9_0));
        ConstraintAnchor constraintAnchor = this.mTarget;
        sb.append(constraintAnchor != null ? " connected to ".concat(String.valueOf(constraintAnchor.toString(hashSet))) : "");
        return sb.toString();
    }
}

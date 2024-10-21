package android.support.constraint.solver.widgets;

import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Snapshot {
    public ArrayList mConnections = new ArrayList();
    public int mHeight;
    public int mWidth;
    public int mX;
    public int mY;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Connection {
        public ConstraintAnchor mAnchor;
        public int mCreator;
        public int mMargin;
        public int mStrengh$ar$edu;
        public ConstraintAnchor mTarget;

        public Connection(ConstraintAnchor constraintAnchor) {
            this.mAnchor = constraintAnchor;
            this.mTarget = constraintAnchor.mTarget;
            this.mMargin = constraintAnchor.getMargin();
            this.mStrengh$ar$edu = constraintAnchor.mStrength$ar$edu;
            this.mCreator = constraintAnchor.mConnectionCreator;
        }
    }

    public Snapshot(ConstraintWidget constraintWidget) {
        this.mX = constraintWidget.mX;
        this.mY = constraintWidget.mY;
        this.mWidth = constraintWidget.getWidth();
        this.mHeight = constraintWidget.getHeight();
        ArrayList arrayList = constraintWidget.mAnchors;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.mConnections.add(new Connection((ConstraintAnchor) arrayList.get(i)));
        }
    }
}

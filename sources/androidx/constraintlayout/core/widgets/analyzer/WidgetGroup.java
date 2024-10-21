package androidx.constraintlayout.core.widgets.analyzer;

import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.ViewUtils;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WidgetGroup {
    static int sCount;
    public final int mId;
    public int mOrientation;
    final ArrayList mWidgets = new ArrayList();
    ArrayList mResults = null;
    private int mMoveTo = -1;

    public WidgetGroup(int i) {
        int i2 = sCount;
        sCount = i2 + 1;
        this.mId = i2;
        this.mOrientation = i;
    }

    public final boolean add(ConstraintWidget constraintWidget) {
        if (this.mWidgets.contains(constraintWidget)) {
            return false;
        }
        this.mWidgets.add(constraintWidget);
        return true;
    }

    public final void cleanup(ArrayList arrayList) {
        int size = this.mWidgets.size();
        if (this.mMoveTo != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                WidgetGroup widgetGroup = (WidgetGroup) arrayList.get(i);
                if (this.mMoveTo == widgetGroup.mId) {
                    moveTo(this.mOrientation, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final int measureWrap(LinearSystem linearSystem, int i) {
        int objectVariableValue$ar$ds$8c6d81d4_0;
        int objectVariableValue$ar$ds$8c6d81d4_02;
        if (this.mWidgets.size() == 0) {
            return 0;
        }
        ArrayList arrayList = this.mWidgets;
        ConstraintWidget constraintWidget = ((ConstraintWidget) arrayList.get(0)).mParent;
        linearSystem.reset();
        constraintWidget.addToSolver(linearSystem, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ((ConstraintWidget) arrayList.get(i2)).addToSolver(linearSystem, false);
        }
        if (i == 0) {
            ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget;
            if (constraintWidgetContainer.mHorizontalChainsSize > 0) {
                ListPopupWindow.Api29Impl.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 0);
            }
        }
        if (i == 1) {
            ConstraintWidgetContainer constraintWidgetContainer2 = (ConstraintWidgetContainer) constraintWidget;
            if (constraintWidgetContainer2.mVerticalChainsSize > 0) {
                ListPopupWindow.Api29Impl.applyChainConstraints(constraintWidgetContainer2, linearSystem, arrayList, 1);
            }
        }
        try {
            linearSystem.minimize();
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + Arrays.toString(e.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
        this.mResults = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.mResults.add(new ViewUtils.Api29Impl((ConstraintWidget) arrayList.get(i3), linearSystem));
        }
        if (i == 0) {
            ConstraintWidgetContainer constraintWidgetContainer3 = (ConstraintWidgetContainer) constraintWidget;
            objectVariableValue$ar$ds$8c6d81d4_0 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidgetContainer3.mLeft);
            objectVariableValue$ar$ds$8c6d81d4_02 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidgetContainer3.mRight);
            linearSystem.reset();
        } else {
            ConstraintWidgetContainer constraintWidgetContainer4 = (ConstraintWidgetContainer) constraintWidget;
            objectVariableValue$ar$ds$8c6d81d4_0 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidgetContainer4.mTop);
            objectVariableValue$ar$ds$8c6d81d4_02 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidgetContainer4.mBottom);
            linearSystem.reset();
        }
        return objectVariableValue$ar$ds$8c6d81d4_02 - objectVariableValue$ar$ds$8c6d81d4_0;
    }

    public final void moveTo(int i, WidgetGroup widgetGroup) {
        ArrayList arrayList = this.mWidgets;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i2);
            widgetGroup.add(constraintWidget);
            if (i == 0) {
                constraintWidget.horizontalGroup = widgetGroup.mId;
            } else {
                constraintWidget.verticalGroup = widgetGroup.mId;
            }
        }
        this.mMoveTo = widgetGroup.mId;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        int i = this.mOrientation;
        if (i == 0) {
            str = "Horizontal";
        } else if (i == 1) {
            str = "Vertical";
        } else {
            str = "Both";
        }
        sb.append(str);
        sb.append(" [");
        sb.append(this.mId);
        sb.append("] <");
        String sb2 = sb.toString();
        ArrayList arrayList = this.mWidgets;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb2 = sb2 + " " + ((ConstraintWidget) arrayList.get(i2)).mDebugName;
        }
        return String.valueOf(sb2).concat(" >");
    }
}

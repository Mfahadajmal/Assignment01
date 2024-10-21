package android.support.v7.widget;

import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TooltipCompat$Api26Impl {
    public static WidgetGroup findDependents(ConstraintWidget constraintWidget, int i, ArrayList arrayList, WidgetGroup widgetGroup) {
        int i2;
        int i3;
        int i4;
        if (i == 0) {
            i2 = constraintWidget.horizontalGroup;
        } else {
            i2 = constraintWidget.verticalGroup;
        }
        if (i2 != -1 && (widgetGroup == null || i2 != widgetGroup.mId)) {
            int i5 = 0;
            while (true) {
                if (i5 >= arrayList.size()) {
                    break;
                }
                WidgetGroup widgetGroup2 = (WidgetGroup) arrayList.get(i5);
                if (widgetGroup2.mId == i2) {
                    if (widgetGroup != null) {
                        widgetGroup.moveTo(i, widgetGroup2);
                        arrayList.remove(widgetGroup);
                    }
                    widgetGroup = widgetGroup2;
                } else {
                    i5++;
                }
            }
        } else if (i2 != -1) {
            return widgetGroup;
        }
        if (widgetGroup == null) {
            if (constraintWidget instanceof HelperWidget) {
                HelperWidget helperWidget = (HelperWidget) constraintWidget;
                int i6 = 0;
                while (true) {
                    if (i6 < helperWidget.mWidgetsCount) {
                        ConstraintWidget constraintWidget2 = helperWidget.mWidgets[i6];
                        if (i == 0) {
                            i4 = constraintWidget2.horizontalGroup;
                            if (i4 != -1) {
                                break;
                            }
                            i6++;
                        } else {
                            i4 = constraintWidget2.verticalGroup;
                            if (i4 != -1) {
                                break;
                            }
                            i6++;
                        }
                    } else {
                        i4 = -1;
                        break;
                    }
                }
                if (i4 != -1) {
                    int i7 = 0;
                    while (true) {
                        if (i7 >= arrayList.size()) {
                            break;
                        }
                        WidgetGroup widgetGroup3 = (WidgetGroup) arrayList.get(i7);
                        if (widgetGroup3.mId == i4) {
                            widgetGroup = widgetGroup3;
                            break;
                        }
                        i7++;
                    }
                }
            }
            if (widgetGroup == null) {
                widgetGroup = new WidgetGroup(i);
            }
            arrayList.add(widgetGroup);
        }
        if (widgetGroup.add(constraintWidget)) {
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                ConstraintAnchor constraintAnchor = guideline.mAnchor;
                if (guideline.mOrientation == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                constraintAnchor.findDependents(i3, arrayList, widgetGroup);
            }
            if (i == 0) {
                constraintWidget.horizontalGroup = widgetGroup.mId;
                constraintWidget.mLeft.findDependents(0, arrayList, widgetGroup);
                constraintWidget.mRight.findDependents(0, arrayList, widgetGroup);
            } else {
                constraintWidget.verticalGroup = widgetGroup.mId;
                constraintWidget.mTop.findDependents(1, arrayList, widgetGroup);
                constraintWidget.mBaseline.findDependents(1, arrayList, widgetGroup);
                constraintWidget.mBottom.findDependents(1, arrayList, widgetGroup);
            }
            constraintWidget.mCenter.findDependents(i, arrayList, widgetGroup);
        }
        return widgetGroup;
    }

    public static WidgetGroup findGroup(ArrayList arrayList, int i) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            WidgetGroup widgetGroup = (WidgetGroup) arrayList.get(i2);
            if (i == widgetGroup.mId) {
                return widgetGroup;
            }
        }
        return null;
    }

    public static void setTooltipText(View view, CharSequence charSequence) {
        view.setTooltipText(charSequence);
    }

    public static boolean validInGroup$ar$edu(int i, int i2, int i3, int i4) {
        boolean z;
        boolean z2;
        if (i3 != 1 && i3 != 2 && (i3 != 4 || i == 2)) {
            z = false;
        } else {
            z = true;
        }
        if (i4 != 1 && i4 != 2 && (i4 != 4 || i2 == 2)) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (!z && !z2) {
            return false;
        }
        return true;
    }
}

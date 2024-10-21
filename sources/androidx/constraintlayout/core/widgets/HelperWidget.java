package androidx.constraintlayout.core.widgets;

import android.support.v7.widget.TooltipCompat$Api26Impl;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class HelperWidget extends ConstraintWidget {
    public ConstraintWidget[] mWidgets = new ConstraintWidget[4];
    public int mWidgetsCount = 0;

    public final void addDependents(ArrayList arrayList, int i, WidgetGroup widgetGroup) {
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            widgetGroup.add(this.mWidgets[i2]);
        }
        for (int i3 = 0; i3 < this.mWidgetsCount; i3++) {
            TooltipCompat$Api26Impl.findDependents(this.mWidgets[i3], i, arrayList, widgetGroup);
        }
    }
}

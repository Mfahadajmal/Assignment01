package android.support.v7.widget;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewUtils {
    static final boolean SDK_LEVEL_SUPPORTS_AUTOSIZE;
    public static Method sComputeFitSystemWindowsMethod;
    public static boolean sInitComputeFitSystemWindowsMethod;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        public Api29Impl(ConstraintWidget constraintWidget, LinearSystem linearSystem) {
            new WeakReference(constraintWidget);
            LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidget.mLeft);
            LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidget.mTop);
            LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidget.mRight);
            LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidget.mBottom);
            LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(constraintWidget.mBaseline);
        }

        public static void computeFitSystemWindows(View view, Rect rect, Rect rect2) {
            Insets of;
            WindowInsets.Builder systemWindowInsets;
            WindowInsets build;
            Insets systemWindowInsets2;
            int i;
            int i2;
            int i3;
            int i4;
            WindowInsets.Builder builder = new WindowInsets.Builder();
            of = Insets.of(rect);
            systemWindowInsets = builder.setSystemWindowInsets(of);
            build = systemWindowInsets.build();
            systemWindowInsets2 = view.computeSystemWindowInsets(build, rect2).getSystemWindowInsets();
            i = systemWindowInsets2.left;
            i2 = systemWindowInsets2.top;
            i3 = systemWindowInsets2.right;
            i4 = systemWindowInsets2.bottom;
            rect.set(i, i2, i3, i4);
        }
    }

    static {
        boolean z;
        if (Build.VERSION.SDK_INT >= 27) {
            z = true;
        } else {
            z = false;
        }
        SDK_LEVEL_SUPPORTS_AUTOSIZE = z;
    }

    public static boolean isLayoutRtl(View view) {
        if (view.getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }
}

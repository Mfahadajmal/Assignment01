package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.appcompat.R$styleable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThemeUtils {
    private static final ThreadLocal TL_TYPED_VALUE = new ThreadLocal();
    static final int[] DISABLED_STATE_SET = {-16842910};
    static final int[] FOCUSED_STATE_SET = {R.attr.state_focused};
    static final int[] PRESSED_STATE_SET = {R.attr.state_pressed};
    static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    static final int[] EMPTY_STATE_SET = new int[0];
    private static final int[] TEMP_ARRAY = new int[1];

    public static void checkAppCompatTheme(View view, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R$styleable.AppCompatTheme);
        try {
            if (!obtainStyledAttributes.hasValue(BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL)) {
                Log.e("ThemeUtils", "View " + view.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int getDisabledThemeAttrColor(Context context, int i) {
        ColorStateList themeAttrColorStateList = getThemeAttrColorStateList(context, i);
        if (themeAttrColorStateList != null && themeAttrColorStateList.isStateful()) {
            return themeAttrColorStateList.getColorForState(DISABLED_STATE_SET, themeAttrColorStateList.getDefaultColor());
        }
        ThreadLocal threadLocal = TL_TYPED_VALUE;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValue, true);
        float f = typedValue.getFloat();
        return ColorUtils.setAlphaComponent(getThemeAttrColor(context, i), Math.round(Color.alpha(r4) * f));
    }

    public static int getThemeAttrColor(Context context, int i) {
        int[] iArr = TEMP_ARRAY;
        iArr[0] = i;
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging(context, null, iArr);
        try {
            return obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging.getColor$ar$ds(0);
        } finally {
            obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging.recycle();
        }
    }

    public static ColorStateList getThemeAttrColorStateList(Context context, int i) {
        int[] iArr = TEMP_ARRAY;
        iArr[0] = i;
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging(context, null, iArr);
        try {
            return obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging.getColorStateList(0);
        } finally {
            obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging.recycle();
        }
    }
}

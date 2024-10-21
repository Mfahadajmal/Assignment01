package com.google.android.material.drawable;

import _COROUTINE._BOUNDARY;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import androidx.core.app.NotificationCompatBuilder$Api21Impl;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.animation.PathInterpolatorCompat$Api21Impl;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.marvin.talkback.R;
import com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener;
import com.google.android.material.internal.ViewUtils$RelativePadding;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DrawableUtils$OutlineCompatL {
    public DrawableUtils$OutlineCompatL(View view) {
        PathInterpolatorCompat$Api21Impl.createPathInterpolator(0.1f, 0.1f, 0.0f, 1.0f);
        Context context = view.getContext();
        DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationMedium2, 300);
        DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationShort3, 150);
        DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationShort2, 100);
    }

    public static void doOnApplyWindowInsets(View view, final ViewUtils$OnApplyWindowInsetsListener viewUtils$OnApplyWindowInsetsListener) {
        final ViewUtils$RelativePadding viewUtils$RelativePadding = new ViewUtils$RelativePadding(view.getPaddingStart(), view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(view, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.internal.ViewUtils$2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
                ViewUtils$OnApplyWindowInsetsListener.this.onApplyWindowInsets$ar$ds(view2, windowInsetsCompat, new ViewUtils$RelativePadding(viewUtils$RelativePadding));
                return windowInsetsCompat;
            }
        });
        if (view.isAttachedToWindow()) {
            ViewCompat.Api20Impl.requestApplyInsets(view);
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.android.material.internal.ViewUtils$3
                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewAttachedToWindow(View view2) {
                    view2.removeOnAttachStateChangeListener(this);
                    ViewCompat.Api20Impl.requestApplyInsets(view2);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewDetachedFromWindow(View view2) {
                }
            });
        }
    }

    public static float dpToPx(Context context, int i) {
        return TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static ViewGroup getContentView(View view) {
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(android.R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView != view && (rootView instanceof ViewGroup)) {
            return (ViewGroup) rootView;
        }
        return null;
    }

    public static StatsStorage getContentViewOverlay$ar$class_merging$ar$class_merging(View view) {
        ViewGroup contentView = getContentView(view);
        if (contentView == null) {
            return null;
        }
        return new StatsStorage((View) contentView);
    }

    private static float getLegacyControlPoint(String[] strArr, int i) {
        float parseFloat = Float.parseFloat(strArr[i]);
        if (parseFloat >= 0.0f && parseFloat <= 1.0f) {
            return parseFloat;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
    }

    private static String getLegacyEasingContent(String str, String str2) {
        return str.substring(str2.length() + 1, str.length() - 1);
    }

    public static boolean isLayoutRtl(View view) {
        if (view.getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    private static boolean isLegacyEasingType(String str, String str2) {
        if (str.startsWith(str2.concat("(")) && str.endsWith(")")) {
            return true;
        }
        return false;
    }

    public static float lerp(float f, float f2, float f3) {
        return ((1.0f - f3) * f) + (f3 * f2);
    }

    public static TimeInterpolator resolveThemeInterpolator(Context context, int i, TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i, typedValue, true)) {
            return timeInterpolator;
        }
        if (typedValue.type == 3) {
            String valueOf = String.valueOf(typedValue.string);
            if (!isLegacyEasingType(valueOf, "cubic-bezier") && !isLegacyEasingType(valueOf, "path")) {
                return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
            }
            if (isLegacyEasingType(valueOf, "cubic-bezier")) {
                String[] split = getLegacyEasingContent(valueOf, "cubic-bezier").split(",");
                int length = split.length;
                if (length == 4) {
                    return PathInterpolatorCompat$Api21Impl.createPathInterpolator(getLegacyControlPoint(split, 0), getLegacyControlPoint(split, 1), getLegacyControlPoint(split, 2), getLegacyControlPoint(split, 3));
                }
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(length, "Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: "));
            }
            if (isLegacyEasingType(valueOf, "path")) {
                String legacyEasingContent = getLegacyEasingContent(valueOf, "path");
                Path path = new Path();
                try {
                    NotificationCompatBuilder$Api21Impl.nodesToPath(NotificationCompatBuilder$Api21Impl.createNodesFromPathData(legacyEasingContent), path);
                    return PathInterpolatorCompat$Api21Impl.createPathInterpolator(path);
                } catch (RuntimeException e) {
                    throw new RuntimeException("Error in parsing ".concat(String.valueOf(legacyEasingContent)), e);
                }
            }
            throw new IllegalArgumentException("Invalid motion easing type: ".concat(String.valueOf(valueOf)));
        }
        throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setConvexPath(Outline outline, Path path) {
        outline.setConvexPath(path);
    }
}

package com.google.android.accessibility.selecttospeak.overlayui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowMetrics;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OverlayUiUtil {
    public static final OverlayUiUtil INSTANCE = new OverlayUiUtil();

    static {
        new Rect(0, 0, 0, 0);
    }

    private OverlayUiUtil() {
    }

    public static final void addCallback$ar$ds(Animator animator, final Function0 function0, final Function1 function1) {
        animator.getClass();
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.accessibility.selecttospeak.overlayui.OverlayUiUtil$addCallback$1
            private boolean isCancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator2) {
                animator2.getClass();
                this.isCancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator2) {
                animator2.getClass();
                Function1 function12 = function1;
                if (function12 != null) {
                    function12.invoke(Boolean.valueOf(this.isCancelled));
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator2) {
                animator2.getClass();
                Function0 function02 = Function0.this;
                if (function02 != null) {
                    function02.invoke();
                }
            }
        });
    }

    public static /* synthetic */ void addCallback$default$ar$ds(OverlayUiUtil overlayUiUtil, Animator animator, Function0 function0, Function1 function1, int i) {
        if (1 == (i & 1)) {
            function0 = null;
        }
        if ((i & 2) != 0) {
            function1 = null;
        }
        addCallback$ar$ds(animator, function0, function1);
    }

    public static final Point getDisplaySizePx$ar$ds(Context context) {
        int i;
        int i2;
        WindowMetrics currentWindowMetrics;
        Rect bounds;
        Object systemService = context.getSystemService("window");
        systemService.getClass();
        WindowManager windowManager = (WindowManager) systemService;
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            currentWindowMetrics = windowManager.getCurrentWindowMetrics();
            currentWindowMetrics.getClass();
            bounds = currentWindowMetrics.getBounds();
            bounds.getClass();
            i = bounds.width();
            i2 = bounds.height();
        } else {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.heightPixels;
            i = i3;
            i2 = i4;
        }
        return new Point(i, i2);
    }
}

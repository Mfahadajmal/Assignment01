package androidx.transition;

import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ViewUtils {
    public static final ViewUtilsApi19 IMPL;
    static final Property TRANSITION_ALPHA;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            IMPL = new ViewUtilsApi29();
        } else {
            IMPL = new ViewUtilsApi23();
        }
        TRANSITION_ALPHA = new Property(Float.class) { // from class: androidx.transition.ViewUtils.1
            @Override // android.util.Property
            public final /* bridge */ /* synthetic */ Object get(Object obj) {
                return Float.valueOf(ViewUtils.getTransitionAlpha((View) obj));
            }

            @Override // android.util.Property
            public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
                ViewUtils.setTransitionAlpha((View) obj, ((Float) obj2).floatValue());
            }
        };
        new Property(Rect.class) { // from class: androidx.transition.ViewUtils.2
            @Override // android.util.Property
            public final /* synthetic */ Object get(Object obj) {
                return ((View) obj).getClipBounds();
            }

            @Override // android.util.Property
            public final /* synthetic */ void set(Object obj, Object obj2) {
                ((View) obj).setClipBounds((Rect) obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getTransitionAlpha(View view) {
        return IMPL.getTransitionAlpha(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4) {
        IMPL.setLeftTopRightBottom(view, i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setTransitionAlpha(View view, float f) {
        IMPL.setTransitionAlpha(view, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setTransitionVisibility(View view, int i) {
        IMPL.setTransitionVisibility(view, i);
    }
}

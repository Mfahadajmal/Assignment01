package androidx.transition;

import android.graphics.Matrix;
import android.os.Build;
import android.os.Trace;
import android.support.v7.app.AppCompatDelegateImpl;
import android.view.View;
import java.lang.reflect.Field;

/* compiled from: PG */
/* loaded from: classes.dex */
class ViewUtilsApi19 {
    private static boolean sTryHiddenTransitionAlpha = true;
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        public static final void beginAsyncSection$ar$ds(String str, int i) {
            if (Build.VERSION.SDK_INT < 29) {
                String truncatedTraceSectionLabel = AppCompatDelegateImpl.Api21Impl.truncatedTraceSectionLabel(str);
                try {
                    if (AppCompatDelegateImpl.Api21Impl.sAsyncTraceBeginMethod == null) {
                        AppCompatDelegateImpl.Api21Impl.sAsyncTraceBeginMethod = Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
                    }
                    AppCompatDelegateImpl.Api21Impl.sAsyncTraceBeginMethod.invoke(null, Long.valueOf(AppCompatDelegateImpl.Api21Impl.sTraceTagApp), truncatedTraceSectionLabel, Integer.valueOf(i));
                    return;
                } catch (Exception e) {
                    AppCompatDelegateImpl.Api21Impl.handleException$ar$ds(e);
                    return;
                }
            }
            Trace.beginAsyncSection(AppCompatDelegateImpl.Api21Impl.truncatedTraceSectionLabel(str), i);
        }

        public static final void endAsyncSection$ar$ds(String str, int i) {
            if (Build.VERSION.SDK_INT >= 29) {
                Trace.endAsyncSection(AppCompatDelegateImpl.Api21Impl.truncatedTraceSectionLabel(str), i);
                return;
            }
            String truncatedTraceSectionLabel = AppCompatDelegateImpl.Api21Impl.truncatedTraceSectionLabel(str);
            try {
                if (AppCompatDelegateImpl.Api21Impl.sAsyncTraceEndMethod == null) {
                    AppCompatDelegateImpl.Api21Impl.sAsyncTraceEndMethod = Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                }
                AppCompatDelegateImpl.Api21Impl.sAsyncTraceEndMethod.invoke(null, Long.valueOf(AppCompatDelegateImpl.Api21Impl.sTraceTagApp), truncatedTraceSectionLabel, Integer.valueOf(i));
            } catch (Exception e) {
                AppCompatDelegateImpl.Api21Impl.handleException$ar$ds(e);
            }
        }

        static float getTransitionAlpha(View view) {
            float transitionAlpha;
            transitionAlpha = view.getTransitionAlpha();
            return transitionAlpha;
        }

        static void setTransitionAlpha(View view, float f) {
            view.setTransitionAlpha(f);
        }
    }

    public float getTransitionAlpha(View view) {
        if (sTryHiddenTransitionAlpha) {
            try {
                return Api29Impl.getTransitionAlpha(view);
            } catch (NoSuchMethodError unused) {
                sTryHiddenTransitionAlpha = false;
            }
        }
        return view.getAlpha();
    }

    public void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4) {
        throw null;
    }

    public void setTransitionAlpha(View view, float f) {
        if (sTryHiddenTransitionAlpha) {
            try {
                Api29Impl.setTransitionAlpha(view, f);
                return;
            } catch (NoSuchMethodError unused) {
                sTryHiddenTransitionAlpha = false;
            }
        }
        view.setAlpha(f);
    }

    public void setTransitionVisibility(View view, int i) {
        if (!sViewFlagsFieldFetched) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                sViewFlagsField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            sViewFlagsFieldFetched = true;
        }
        Field field = sViewFlagsField;
        if (field != null) {
            try {
                sViewFlagsField.setInt(view, i | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public void transformMatrixToGlobal(View view, Matrix matrix) {
        throw null;
    }

    public void transformMatrixToLocal(View view, Matrix matrix) {
        throw null;
    }
}

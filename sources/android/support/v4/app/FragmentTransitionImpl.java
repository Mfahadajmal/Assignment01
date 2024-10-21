package android.support.v4.app;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class FragmentTransitionImpl {
    public static void bfsAddViewChildren(List list, View view) {
        int size = list.size();
        if (!containedBeforeIndex(list, view, size)) {
            if (ViewCompat.Api21Impl.getTransitionName(view) != null) {
                list.add(view);
            }
            for (int i = size; i < list.size(); i++) {
                View view2 = (View) list.get(i);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (!containedBeforeIndex(list, childAt, size) && ViewCompat.Api21Impl.getTransitionName(childAt) != null) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    private static boolean containedBeforeIndex(List list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    public static final void getBoundsOnScreen$ar$ds(View view, Rect rect) {
        if (!view.isAttachedToWindow()) {
            return;
        }
        RectF rectF = new RectF();
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getMatrix().mapRect(rectF);
        rectF.offset(view.getLeft(), view.getTop());
        Object parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            rectF.offset(-view2.getScrollX(), -view2.getScrollY());
            view2.getMatrix().mapRect(rectF);
            rectF.offset(view2.getLeft(), view2.getTop());
            parent = view2.getParent();
        }
        view.getRootView().getLocationOnScreen(new int[2]);
        rectF.offset(r1[0], r1[1]);
        rect.set(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public static boolean isNullOrEmpty(List list) {
        if (list != null && !list.isEmpty()) {
            return false;
        }
        return true;
    }

    public abstract void addTarget(Object obj, View view);

    public abstract void addTargets(Object obj, ArrayList arrayList);

    public abstract void beginDelayedTransition(ViewGroup viewGroup, Object obj);

    public abstract boolean canHandle(Object obj);

    public abstract Object cloneTransition(Object obj);

    public Object controlDelayedTransition(ViewGroup viewGroup, Object obj) {
        return null;
    }

    public boolean isSeekingSupported() {
        throw null;
    }

    public abstract Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3);

    public abstract Object mergeTransitionsTogether$ar$ds(Object obj, Object obj2);

    public abstract void scheduleHideFragmentView(Object obj, View view, ArrayList arrayList);

    public abstract void scheduleRemoveTargets$ar$ds(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2);

    public abstract void setEpicenter(Object obj, Rect rect);

    public abstract void setEpicenter(Object obj, View view);

    public void setListenerForTransitionEnd(Fragment fragment, Object obj, CancellationSignal cancellationSignal, Runnable runnable) {
        throw null;
    }

    public void setListenerForTransitionEnd$ar$ds(Object obj, CancellationSignal cancellationSignal, Runnable runnable, Runnable runnable2) {
        runnable2.run();
    }

    public abstract void setSharedElementTargets(Object obj, View view, ArrayList arrayList);

    public abstract void swapSharedElementTargets(Object obj, ArrayList arrayList, ArrayList arrayList2);

    public abstract Object wrapTransitionInSet(Object obj);

    public boolean isSeekingSupported(Object obj) {
        throw null;
    }

    public void animateToEnd(Object obj) {
    }

    public void animateToStart(Object obj, Runnable runnable) {
    }

    public void setCurrentPlayTime(Object obj, float f) {
    }
}

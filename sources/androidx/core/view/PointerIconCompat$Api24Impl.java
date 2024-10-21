package androidx.core.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.PointerIcon;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LifecycleRegistryOwner;
import androidx.lifecycle.ReportFragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PointerIconCompat$Api24Impl {
    static PointerIcon create(Bitmap bitmap, float f, float f2) {
        return PointerIcon.create(bitmap, f, f2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void dispatch$lifecycle_runtime_release$ar$ds(Activity activity, Lifecycle.Event event) {
        event.getClass();
        if (activity instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) activity).getLifecycle().handleLifecycleEvent(event);
        } else if (activity instanceof LifecycleOwner) {
            Lifecycle lifecycle = ((LifecycleOwner) activity).getLifecycle();
            if (lifecycle instanceof LifecycleRegistry) {
                ((LifecycleRegistry) lifecycle).handleLifecycleEvent(event);
            }
        }
    }

    public static PointerIcon getSystemIcon(Context context, int i) {
        return PointerIcon.getSystemIcon(context, i);
    }

    public static final void injectIfNeededIn$ar$ds(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            ReportFragment.LifecycleCallbacks.Companion companion = ReportFragment.LifecycleCallbacks.Companion;
            ReportFragment.LifecycleCallbacks.Companion.registerIn$ar$ds(activity);
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new ReportFragment(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    static PointerIcon load(Resources resources, int i) {
        return PointerIcon.load(resources, i);
    }
}

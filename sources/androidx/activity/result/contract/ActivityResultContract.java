package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ActivityResultContract<I, O> {
    public abstract Intent createIntent$ar$ds(Object obj);

    public AccessibilityNodeInfoCompat.CollectionItemInfoCompat getSynchronousResult$ar$class_merging$ar$class_merging(Context context, Object obj) {
        return null;
    }

    public abstract Object parseResult(int i, Intent intent);
}

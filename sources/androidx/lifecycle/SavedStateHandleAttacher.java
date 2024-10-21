package androidx.lifecycle;

import androidx.core.view.MenuItemCompat$Api26Impl;
import androidx.lifecycle.Lifecycle;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SavedStateHandleAttacher implements LifecycleEventObserver {
    private final Object SavedStateHandleAttacher$ar$provider;
    private final /* synthetic */ int switching_field;

    public SavedStateHandleAttacher(Object obj, int i) {
        this.switching_field = i;
        this.SavedStateHandleAttacher$ar$provider = obj;
    }

    /* JADX WARN: Type inference failed for: r3v11, types: [java.lang.Object, androidx.lifecycle.GeneratedAdapter] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.Object, androidx.lifecycle.GeneratedAdapter] */
    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        GeneratedAdapter[] generatedAdapterArr;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                this.SavedStateHandleAttacher$ar$provider.callMethods$ar$ds();
                this.SavedStateHandleAttacher$ar$provider.callMethods$ar$ds();
                return;
            }
            new MenuItemCompat$Api26Impl();
            int i2 = 0;
            while (true) {
                generatedAdapterArr = (GeneratedAdapter[]) this.SavedStateHandleAttacher$ar$provider;
                if (i2 >= generatedAdapterArr.length) {
                    break;
                }
                generatedAdapterArr[i2].callMethods$ar$ds();
                i2++;
            }
            for (GeneratedAdapter generatedAdapter : generatedAdapterArr) {
                generatedAdapter.callMethods$ar$ds();
            }
            return;
        }
        if (event == Lifecycle.Event.ON_CREATE) {
            lifecycleOwner.getLifecycle().removeObserver(this);
            ((SavedStateHandlesProvider) this.SavedStateHandleAttacher$ar$provider).performRestore();
        } else {
            Objects.toString(event);
            throw new IllegalStateException("Next event must be ON_CREATE, it was ".concat(event.toString()));
        }
    }
}

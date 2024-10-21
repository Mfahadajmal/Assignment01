package androidx.loader.app;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import java.io.PrintWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LoaderManager {
    public static LoaderManager getInstance(LifecycleOwner lifecycleOwner) {
        return new LoaderManagerImpl(lifecycleOwner, ((ViewModelStoreOwner) lifecycleOwner).getViewModelStore());
    }

    @Deprecated
    public abstract void dump$ar$ds(String str, PrintWriter printWriter);
}

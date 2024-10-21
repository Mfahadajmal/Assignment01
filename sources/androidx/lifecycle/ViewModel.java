package androidx.lifecycle;

import androidx.lifecycle.viewmodel.internal.ViewModelImpl;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ViewModel {
    public final ViewModelImpl impl = new ViewModelImpl();

    public final void addCloseable$ar$ds(AutoCloseable autoCloseable) {
        AutoCloseable autoCloseable2;
        ViewModelImpl viewModelImpl = this.impl;
        if (viewModelImpl.isCleared) {
            ViewModelImpl.closeWithRuntimeException$ar$ds(autoCloseable);
            return;
        }
        synchronized (viewModelImpl.lock$ar$class_merging$2cccb82d_0) {
            autoCloseable2 = (AutoCloseable) viewModelImpl.keyToCloseables.put("androidx.lifecycle.savedstate.vm.tag", autoCloseable);
        }
        ViewModelImpl.closeWithRuntimeException$ar$ds(autoCloseable2);
    }

    public final void clear$lifecycle_viewmodel_release() {
        ViewModelImpl viewModelImpl = this.impl;
        if (!viewModelImpl.isCleared) {
            viewModelImpl.isCleared = true;
            synchronized (viewModelImpl.lock$ar$class_merging$2cccb82d_0) {
                Iterator it = viewModelImpl.keyToCloseables.values().iterator();
                while (it.hasNext()) {
                    ViewModelImpl.closeWithRuntimeException$ar$ds((AutoCloseable) it.next());
                }
                Iterator it2 = viewModelImpl.closeables.iterator();
                while (it2.hasNext()) {
                    ViewModelImpl.closeWithRuntimeException$ar$ds((AutoCloseable) it2.next());
                }
                viewModelImpl.closeables.clear();
            }
        }
        onCleared();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCleared() {
    }
}

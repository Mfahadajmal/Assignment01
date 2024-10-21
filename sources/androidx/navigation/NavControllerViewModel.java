package androidx.navigation;

import androidx.core.view.ViewConfigurationCompat$Api34Impl;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManagerImpl;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavControllerViewModel extends ViewModel implements NavViewModelStoreProvider {
    public static final ViewConfigurationCompat$Api34Impl Companion$ar$class_merging$7eaf809c_0 = new ViewConfigurationCompat$Api34Impl();
    public static final ViewModelProvider.Factory FACTORY = new LoaderManagerImpl.LoaderViewModel.AnonymousClass1(2);
    private final Map viewModelStores = new LinkedHashMap();

    public final void clear(String str) {
        ViewModelStore viewModelStore = (ViewModelStore) this.viewModelStores.remove(str);
        if (viewModelStore != null) {
            viewModelStore.clear();
        }
    }

    @Override // androidx.navigation.NavViewModelStoreProvider
    public final ViewModelStore getViewModelStore(String str) {
        ViewModelStore viewModelStore = (ViewModelStore) this.viewModelStores.get(str);
        if (viewModelStore == null) {
            ViewModelStore viewModelStore2 = new ViewModelStore();
            this.viewModelStores.put(str, viewModelStore2);
            return viewModelStore2;
        }
        return viewModelStore;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public final void onCleared() {
        Iterator it = this.viewModelStores.values().iterator();
        while (it.hasNext()) {
            ((ViewModelStore) it.next()).clear();
        }
        this.viewModelStores.clear();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NavControllerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} ViewModelStores (");
        Iterator it = this.viewModelStores.keySet().iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}

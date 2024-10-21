package android.support.v4.app;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManagerImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentManagerViewModel extends ViewModel {
    public static final ViewModelProvider.Factory FACTORY = new LoaderManagerImpl.LoaderViewModel.AnonymousClass1(1);
    public final boolean mStateAutomaticallySaved;
    public final HashMap mRetainedFragments = new HashMap();
    public final HashMap mChildNonConfigs = new HashMap();
    public final HashMap mViewModelStores = new HashMap();
    public boolean mHasBeenCleared = false;
    public boolean mIsStateSaved = false;

    public FragmentManagerViewModel(boolean z) {
        this.mStateAutomaticallySaved = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addRetainedFragment(Fragment fragment) {
        if (!this.mIsStateSaved && !this.mRetainedFragments.containsKey(fragment.mWho)) {
            this.mRetainedFragments.put(fragment.mWho, fragment);
            if (FragmentManager.isLoggingEnabled(2)) {
                Objects.toString(fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearNonConfigState(Fragment fragment, boolean z) {
        if (FragmentManager.isLoggingEnabled(3)) {
            Objects.toString(fragment);
        }
        clearNonConfigStateInternal(fragment.mWho, z);
    }

    public final void clearNonConfigStateInternal(String str, boolean z) {
        FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) this.mChildNonConfigs.get(str);
        if (fragmentManagerViewModel != null) {
            if (z) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(fragmentManagerViewModel.mChildNonConfigs.keySet());
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    fragmentManagerViewModel.clearNonConfigStateInternal((String) arrayList.get(i), true);
                }
            }
            fragmentManagerViewModel.onCleared();
            this.mChildNonConfigs.remove(str);
        }
        ViewModelStore viewModelStore = (ViewModelStore) this.mViewModelStores.get(str);
        if (viewModelStore != null) {
            viewModelStore.clear();
            this.mViewModelStores.remove(str);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) obj;
            if (this.mRetainedFragments.equals(fragmentManagerViewModel.mRetainedFragments) && this.mChildNonConfigs.equals(fragmentManagerViewModel.mChildNonConfigs) && this.mViewModelStores.equals(fragmentManagerViewModel.mViewModelStores)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((this.mRetainedFragments.hashCode() * 31) + this.mChildNonConfigs.hashCode()) * 31) + this.mViewModelStores.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public final void onCleared() {
        if (FragmentManager.isLoggingEnabled(3)) {
            toString();
        }
        this.mHasBeenCleared = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeRetainedFragment(Fragment fragment) {
        if (!this.mIsStateSaved && this.mRetainedFragments.remove(fragment.mWho) != null && FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean shouldDestroy(Fragment fragment) {
        if (this.mRetainedFragments.containsKey(fragment.mWho) && this.mStateAutomaticallySaved) {
            return this.mHasBeenCleared;
        }
        return true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.mRetainedFragments.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.mChildNonConfigs.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.mViewModelStores.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}

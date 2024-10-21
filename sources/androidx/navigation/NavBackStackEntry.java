package androidx.navigation;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.activity.ComponentActivity$fullyDrawnReporter$2$1;
import androidx.core.view.ViewConfigurationCompat$Api28Impl;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.Iterator;
import java.util.Set;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavBackStackEntry implements LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner {
    public static final ViewConfigurationCompat$Api28Impl Companion$ar$class_merging$a8236c35_0 = new ViewConfigurationCompat$Api28Impl();
    public LifecycleRegistry _lifecycle;
    public final Context context;
    private final Lazy defaultFactory$delegate;
    private final ViewModelProvider.Factory defaultViewModelProviderFactory;
    public NavDestination destination;
    public Lifecycle.State hostLifecycleState;
    public final String id;
    private final Bundle immutableArgs;
    public Lifecycle.State maxLifecycle;
    private final Bundle savedState;
    public boolean savedStateRegistryAttached;
    public final SavedStateRegistryController savedStateRegistryController;
    private final NavViewModelStoreProvider viewModelStoreProvider;

    public NavBackStackEntry(Context context, NavDestination navDestination, Bundle bundle, Lifecycle.State state, NavViewModelStoreProvider navViewModelStoreProvider, String str, Bundle bundle2) {
        this.context = context;
        this.destination = navDestination;
        this.immutableArgs = bundle;
        this.hostLifecycleState = state;
        this.viewModelStoreProvider = navViewModelStoreProvider;
        this.id = str;
        this.savedState = bundle2;
        this._lifecycle = new LifecycleRegistry(this);
        this.savedStateRegistryController = new SavedStateRegistryController(this);
        SynchronizedLazyImpl synchronizedLazyImpl = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 8));
        this.defaultFactory$delegate = synchronizedLazyImpl;
        this.maxLifecycle = Lifecycle.State.INITIALIZED;
        this.defaultViewModelProviderFactory = (SavedStateViewModelFactory) synchronizedLazyImpl.getValue();
    }

    public final boolean equals(Object obj) {
        Set<String> keySet;
        Object obj2;
        if (obj == null || !(obj instanceof NavBackStackEntry)) {
            return false;
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
        if (!Intrinsics.areEqual(this.id, navBackStackEntry.id) || !Intrinsics.areEqual(this.destination, navBackStackEntry.destination) || !Intrinsics.areEqual(this._lifecycle, navBackStackEntry._lifecycle) || !Intrinsics.areEqual(getSavedStateRegistry(), navBackStackEntry.getSavedStateRegistry())) {
            return false;
        }
        if (!Intrinsics.areEqual(this.immutableArgs, navBackStackEntry.immutableArgs)) {
            Bundle bundle = this.immutableArgs;
            if (bundle == null || (keySet = bundle.keySet()) == null) {
                return false;
            }
            if (!keySet.isEmpty()) {
                for (String str : keySet) {
                    Object obj3 = this.immutableArgs.get(str);
                    Bundle bundle2 = navBackStackEntry.immutableArgs;
                    if (bundle2 != null) {
                        obj2 = bundle2.get(str);
                    } else {
                        obj2 = null;
                    }
                    if (!Intrinsics.areEqual(obj3, obj2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final Bundle getArguments() {
        Bundle bundle = this.immutableArgs;
        if (bundle == null) {
            return null;
        }
        return new Bundle(bundle);
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public final CreationExtras getDefaultViewModelCreationExtras() {
        Application application = null;
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras((byte[]) null);
        Context applicationContext = this.context.getApplicationContext();
        if (applicationContext instanceof Application) {
            application = (Application) applicationContext;
        }
        if (application != null) {
            mutableCreationExtras.set(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY, application);
        }
        mutableCreationExtras.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        mutableCreationExtras.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mutableCreationExtras.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, arguments);
        }
        return mutableCreationExtras;
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public final ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return this.defaultViewModelProviderFactory;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this._lifecycle;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistryController.savedStateRegistry;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public final ViewModelStore getViewModelStore() {
        if (this.savedStateRegistryAttached) {
            if (this._lifecycle.state != Lifecycle.State.DESTROYED) {
                NavViewModelStoreProvider navViewModelStoreProvider = this.viewModelStoreProvider;
                if (navViewModelStoreProvider != null) {
                    return navViewModelStoreProvider.getViewModelStore(this.id);
                }
                throw new IllegalStateException("You must call setViewModelStore() on your NavHostController before accessing the ViewModelStore of a navigation graph.");
            }
            throw new IllegalStateException("You cannot access the NavBackStackEntry's ViewModels after the NavBackStackEntry is destroyed.");
        }
        throw new IllegalStateException("You cannot access the NavBackStackEntry's ViewModels until it is added to the NavController's back stack (i.e., the Lifecycle of the NavBackStackEntry reaches the CREATED state).");
    }

    public final int hashCode() {
        Set<String> keySet;
        int i;
        int hashCode = (this.id.hashCode() * 31) + this.destination.hashCode();
        Bundle bundle = this.immutableArgs;
        if (bundle != null && (keySet = bundle.keySet()) != null) {
            Iterator<T> it = keySet.iterator();
            while (it.hasNext()) {
                int i2 = hashCode * 31;
                Object obj = this.immutableArgs.get((String) it.next());
                if (obj != null) {
                    i = obj.hashCode();
                } else {
                    i = 0;
                }
                hashCode = i2 + i;
            }
        }
        return (((hashCode * 31) + this._lifecycle.hashCode()) * 31) + getSavedStateRegistry().hashCode();
    }

    public final void setMaxLifecycle(Lifecycle.State state) {
        state.getClass();
        this.maxLifecycle = state;
        updateState();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(" + this.id + ')');
        sb.append(" destination=");
        sb.append(this.destination);
        return sb.toString();
    }

    public final void updateState() {
        if (!this.savedStateRegistryAttached) {
            this.savedStateRegistryController.performAttach();
            this.savedStateRegistryAttached = true;
            if (this.viewModelStoreProvider != null) {
                SavedStateHandleSupport.enableSavedStateHandles(this);
            }
            this.savedStateRegistryController.performRestore(this.savedState);
        }
        if (this.hostLifecycleState.ordinal() < this.maxLifecycle.ordinal()) {
            this._lifecycle.setCurrentState(this.hostLifecycleState);
        } else {
            this._lifecycle.setCurrentState(this.maxLifecycle);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NavBackStackEntry(NavBackStackEntry navBackStackEntry, Bundle bundle) {
        this(navBackStackEntry.context, navBackStackEntry.destination, bundle, navBackStackEntry.hostLifecycleState, navBackStackEntry.viewModelStoreProvider, navBackStackEntry.id, navBackStackEntry.savedState);
        navBackStackEntry.getClass();
        this.hostLifecycleState = navBackStackEntry.hostLifecycleState;
        setMaxLifecycle(navBackStackEntry.maxLifecycle);
    }
}

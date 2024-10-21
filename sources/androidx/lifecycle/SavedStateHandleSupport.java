package androidx.lifecycle;

import android.os.Bundle;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SavedStateHandleSupport {
    public static final CreationExtras.Key SAVED_STATE_REGISTRY_OWNER_KEY = new SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1();
    public static final CreationExtras.Key VIEW_MODEL_STORE_OWNER_KEY = new SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1();
    public static final CreationExtras.Key DEFAULT_ARGS_KEY = new SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1();

    public static final SavedStateHandle createSavedStateHandle(CreationExtras creationExtras) {
        SavedStateHandlesProvider savedStateHandlesProvider;
        Bundle bundle;
        SavedStateRegistryOwner savedStateRegistryOwner = (SavedStateRegistryOwner) creationExtras.get(SAVED_STATE_REGISTRY_OWNER_KEY);
        if (savedStateRegistryOwner != null) {
            ViewModelStoreOwner viewModelStoreOwner = (ViewModelStoreOwner) creationExtras.get(VIEW_MODEL_STORE_OWNER_KEY);
            if (viewModelStoreOwner != null) {
                Bundle bundle2 = (Bundle) creationExtras.get(DEFAULT_ARGS_KEY);
                String str = (String) creationExtras.get(ViewModelProvider.NewInstanceFactory.VIEW_MODEL_KEY);
                if (str != null) {
                    SavedStateRegistry.SavedStateProvider savedStateProvider$ar$ds = savedStateRegistryOwner.getSavedStateRegistry().getSavedStateProvider$ar$ds();
                    if (savedStateProvider$ar$ds instanceof SavedStateHandlesProvider) {
                        savedStateHandlesProvider = (SavedStateHandlesProvider) savedStateProvider$ar$ds;
                    } else {
                        savedStateHandlesProvider = null;
                    }
                    if (savedStateHandlesProvider != null) {
                        SavedStateHandlesVM savedStateHandlesVM = getSavedStateHandlesVM(viewModelStoreOwner);
                        SavedStateHandle savedStateHandle = (SavedStateHandle) savedStateHandlesVM.handles.get(str);
                        if (savedStateHandle == null) {
                            Class[] clsArr = SavedStateHandle.ACCEPTABLE_CLASSES;
                            savedStateHandlesProvider.performRestore();
                            Bundle bundle3 = savedStateHandlesProvider.restoredState;
                            if (bundle3 != null) {
                                bundle = bundle3.getBundle(str);
                            } else {
                                bundle = null;
                            }
                            Bundle bundle4 = savedStateHandlesProvider.restoredState;
                            if (bundle4 != null) {
                                bundle4.remove(str);
                            }
                            Bundle bundle5 = savedStateHandlesProvider.restoredState;
                            if (bundle5 != null && bundle5.isEmpty()) {
                                savedStateHandlesProvider.restoredState = null;
                            }
                            SavedStateHandle createHandle$ar$ds = VelocityTrackerCompat.Api34Impl.createHandle$ar$ds(bundle, bundle2);
                            savedStateHandlesVM.handles.put(str, createHandle$ar$ds);
                            return createHandle$ar$ds;
                        }
                        return savedStateHandle;
                    }
                    throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
                }
                throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
            }
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
    }

    public static final void enableSavedStateHandles(SavedStateRegistryOwner savedStateRegistryOwner) {
        Lifecycle.State currentState = savedStateRegistryOwner.getLifecycle().getCurrentState();
        if (currentState != Lifecycle.State.INITIALIZED && currentState != Lifecycle.State.CREATED) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (savedStateRegistryOwner.getSavedStateRegistry().getSavedStateProvider$ar$ds() == null) {
            SavedStateHandlesProvider savedStateHandlesProvider = new SavedStateHandlesProvider(savedStateRegistryOwner.getSavedStateRegistry(), (ViewModelStoreOwner) savedStateRegistryOwner);
            savedStateRegistryOwner.getSavedStateRegistry().registerSavedStateProvider("androidx.lifecycle.internal.SavedStateHandlesProvider", savedStateHandlesProvider);
            savedStateRegistryOwner.getLifecycle().addObserver(new SavedStateHandleAttacher(savedStateHandlesProvider, 0));
        }
    }

    public static final SavedStateHandlesVM getSavedStateHandlesVM(ViewModelStoreOwner viewModelStoreOwner) {
        return (SavedStateHandlesVM) new ViewModelProvider(viewModelStoreOwner, new ViewModelProvider.Factory() { // from class: androidx.lifecycle.SavedStateHandleSupport$savedStateHandlesVM$1
            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public final /* synthetic */ ViewModel create(Class cls) {
                return ViewCompat.Api21Impl.$default$create$ar$ds();
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public final /* synthetic */ ViewModel create(KClass kClass, CreationExtras creationExtras) {
                ViewModel create;
                create = create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass), creationExtras);
                return create;
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public final ViewModel create(Class cls, CreationExtras creationExtras) {
                return new SavedStateHandlesVM();
            }
        }).impl$ar$class_merging$ar$class_merging.getViewModel$lifecycle_viewmodel_release(OnDeviceShadowRemovalLogEvent.getKotlinClass(SavedStateHandlesVM.class), "androidx.lifecycle.internal.SavedStateHandlesVM");
    }
}

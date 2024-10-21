package androidx.lifecycle;

import android.os.Bundle;
import androidx.core.view.VelocityTrackerCompat;
import androidx.lifecycle.LegacySavedStateHandleController;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.viewmodel.internal.ViewModelImpl;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LegacySavedStateHandleController {
    public static final LegacySavedStateHandleController INSTANCE = new LegacySavedStateHandleController();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnRecreation implements SavedStateRegistry.AutoRecreated {
        @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
        public final void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner) {
            if (savedStateRegistryOwner instanceof ViewModelStoreOwner) {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) savedStateRegistryOwner).getViewModelStore();
                SavedStateRegistry savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
                Iterator it = viewModelStore.keys().iterator();
                while (it.hasNext()) {
                    ViewModel viewModel = viewModelStore.get((String) it.next());
                    viewModel.getClass();
                    LegacySavedStateHandleController.attachHandleIfNeeded(viewModel, savedStateRegistry, savedStateRegistryOwner.getLifecycle());
                }
                if (!viewModelStore.keys().isEmpty()) {
                    savedStateRegistry.runOnNextRecreation(OnRecreation.class);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
        }
    }

    private LegacySavedStateHandleController() {
    }

    public static final void attachHandleIfNeeded(ViewModel viewModel, SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        AutoCloseable autoCloseable;
        lifecycle.getClass();
        ViewModelImpl viewModelImpl = viewModel.impl;
        synchronized (viewModelImpl.lock$ar$class_merging$2cccb82d_0) {
            autoCloseable = (AutoCloseable) viewModelImpl.keyToCloseables.get("androidx.lifecycle.savedstate.vm.tag");
        }
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) autoCloseable;
        if (savedStateHandleController != null && !savedStateHandleController.isAttached) {
            savedStateHandleController.attachToLifecycle(savedStateRegistry, lifecycle);
            tryToAddRecreator$ar$ds(savedStateRegistry, lifecycle);
        }
    }

    public static final SavedStateHandleController create(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle, String str, Bundle bundle) {
        Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey(str);
        Class[] clsArr = SavedStateHandle.ACCEPTABLE_CLASSES;
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, VelocityTrackerCompat.Api34Impl.createHandle$ar$ds(consumeRestoredStateForKey, bundle));
        savedStateHandleController.attachToLifecycle(savedStateRegistry, lifecycle);
        tryToAddRecreator$ar$ds(savedStateRegistry, lifecycle);
        return savedStateHandleController;
    }

    private static final void tryToAddRecreator$ar$ds(final SavedStateRegistry savedStateRegistry, final Lifecycle lifecycle) {
        Lifecycle.State currentState = lifecycle.getCurrentState();
        if (currentState != Lifecycle.State.INITIALIZED && !currentState.isAtLeast(Lifecycle.State.STARTED)) {
            lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.lifecycle.LegacySavedStateHandleController$tryToAddRecreator$1
                @Override // androidx.lifecycle.LifecycleEventObserver
                public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_START) {
                        Lifecycle.this.removeObserver(this);
                        savedStateRegistry.runOnNextRecreation(LegacySavedStateHandleController.OnRecreation.class);
                    }
                }
            });
        } else {
            savedStateRegistry.runOnNextRecreation(OnRecreation.class);
        }
    }
}

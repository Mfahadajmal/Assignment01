package androidx.savedstate;

import android.os.Bundle;
import androidx.activity.ComponentActivity$$ExternalSyntheticLambda2;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import androidx.preference.PreferenceCategory;
import androidx.savedstate.SavedStateRegistry;
import java.util.Map;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SavedStateRegistryController {
    public static final PreferenceCategory.Api28Impl Companion$ar$class_merging$5b67b4d4_0 = new PreferenceCategory.Api28Impl();
    private boolean attached;
    private final SavedStateRegistryOwner owner;
    public final SavedStateRegistry savedStateRegistry = new SavedStateRegistry();

    public SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner) {
        this.owner = savedStateRegistryOwner;
    }

    public static final SavedStateRegistryController create(SavedStateRegistryOwner savedStateRegistryOwner) {
        return new SavedStateRegistryController(savedStateRegistryOwner);
    }

    public final void performAttach() {
        Lifecycle lifecycle = this.owner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.INITIALIZED) {
            lifecycle.addObserver(new Recreator(this.owner));
            SavedStateRegistry savedStateRegistry = this.savedStateRegistry;
            lifecycle.getClass();
            if (!savedStateRegistry.attached) {
                lifecycle.addObserver(new ComponentActivity$$ExternalSyntheticLambda2(savedStateRegistry, 4));
                savedStateRegistry.attached = true;
                this.attached = true;
                return;
            }
            throw new IllegalStateException("SavedStateRegistry was already attached.");
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
    }

    public final void performRestore(Bundle bundle) {
        Bundle bundle2;
        if (!this.attached) {
            performAttach();
        }
        Lifecycle lifecycle = this.owner.getLifecycle();
        if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            SavedStateRegistry savedStateRegistry = this.savedStateRegistry;
            if (savedStateRegistry.attached) {
                if (!savedStateRegistry.isRestored) {
                    if (bundle != null) {
                        bundle2 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
                    } else {
                        bundle2 = null;
                    }
                    savedStateRegistry.restoredState = bundle2;
                    savedStateRegistry.isRestored = true;
                    return;
                }
                throw new IllegalStateException("SavedStateRegistry was already restored.");
            }
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).");
        }
        Lifecycle.State currentState = lifecycle.getCurrentState();
        Objects.toString(currentState);
        throw new IllegalStateException("performRestore cannot be called when owner is ".concat(String.valueOf(currentState)));
    }

    public final void performSave(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        SavedStateRegistry savedStateRegistry = this.savedStateRegistry;
        Bundle bundle3 = savedStateRegistry.restoredState;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        SafeIterableMap.IteratorWithAdditions iteratorWithAdditions = savedStateRegistry.components.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext()) {
            Map.Entry next = iteratorWithAdditions.next();
            bundle2.putBundle((String) next.getKey(), ((SavedStateRegistry.SavedStateProvider) next.getValue()).saveState());
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
        }
    }
}

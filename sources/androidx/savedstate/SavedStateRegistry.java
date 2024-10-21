package androidx.savedstate;

import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.savedstate.Recreator;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SavedStateRegistry {
    public boolean attached;
    public final SafeIterableMap components = new SafeIterableMap();
    public boolean isAllowingSavingState = true;
    public boolean isRestored;
    private Recreator.SavedStateProvider recreatorProvider;
    public Bundle restoredState;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AutoRecreated {
        void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface SavedStateProvider {
        Bundle saveState();
    }

    public final Bundle consumeRestoredStateForKey(String str) {
        if (this.isRestored) {
            Bundle bundle = this.restoredState;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = bundle.getBundle(str);
            Bundle bundle3 = this.restoredState;
            if (bundle3 != null) {
                bundle3.remove(str);
            }
            Bundle bundle4 = this.restoredState;
            if (bundle4 == null || bundle4.isEmpty()) {
                this.restoredState = null;
            }
            return bundle2;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    public final SavedStateProvider getSavedStateProvider$ar$ds() {
        Iterator it = this.components.iterator();
        while (it.hasNext()) {
            Map.Entry next = ((SafeIterableMap.ListIterator) it).next();
            next.getClass();
            String str = (String) next.getKey();
            SavedStateProvider savedStateProvider = (SavedStateProvider) next.getValue();
            if (Intrinsics.areEqual(str, "androidx.lifecycle.internal.SavedStateHandlesProvider")) {
                return savedStateProvider;
            }
        }
        return null;
    }

    public final void registerSavedStateProvider(String str, SavedStateProvider savedStateProvider) {
        savedStateProvider.getClass();
        if (((SavedStateProvider) this.components.putIfAbsent(str, savedStateProvider)) == null) {
        } else {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public final void runOnNextRecreation(Class cls) {
        if (this.isAllowingSavingState) {
            Recreator.SavedStateProvider savedStateProvider = this.recreatorProvider;
            if (savedStateProvider == null) {
                savedStateProvider = new Recreator.SavedStateProvider(this);
            }
            this.recreatorProvider = savedStateProvider;
            try {
                cls.getDeclaredConstructor(null);
                Recreator.SavedStateProvider savedStateProvider2 = this.recreatorProvider;
                if (savedStateProvider2 != null) {
                    String name = cls.getName();
                    name.getClass();
                    savedStateProvider2.classes.add(name);
                    return;
                }
                return;
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Class " + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
            }
        }
        throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }
}

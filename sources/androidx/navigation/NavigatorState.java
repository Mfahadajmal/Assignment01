package androidx.navigation;

import android.os.Bundle;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class NavigatorState {
    public final StateFlowImpl _backStack$ar$class_merging;
    public final StateFlowImpl _transitionsInProgress$ar$class_merging;
    public final StateFlow backStack;
    public final ReentrantLock backStackLock = new ReentrantLock(true);
    public boolean isNavigating;
    public final StateFlow transitionsInProgress;

    public NavigatorState() {
        StateFlowImpl MutableStateFlow$ar$class_merging = StateFlowKt.MutableStateFlow$ar$class_merging(EmptyList.INSTANCE);
        this._backStack$ar$class_merging = MutableStateFlow$ar$class_merging;
        StateFlowImpl MutableStateFlow$ar$class_merging2 = StateFlowKt.MutableStateFlow$ar$class_merging(EmptySet.INSTANCE);
        this._transitionsInProgress$ar$class_merging = MutableStateFlow$ar$class_merging2;
        this.backStack = new ReadonlyStateFlow(MutableStateFlow$ar$class_merging);
        this.transitionsInProgress = new ReadonlyStateFlow(MutableStateFlow$ar$class_merging2);
    }

    public abstract NavBackStackEntry createBackStackEntry(NavDestination navDestination, Bundle bundle);

    public void markTransitionComplete(NavBackStackEntry navBackStackEntry) {
        throw null;
    }

    public final void onLaunchSingleTop(NavBackStackEntry navBackStackEntry) {
        int i;
        ReentrantLock reentrantLock = this.backStackLock;
        reentrantLock.lock();
        try {
            List mutableList = OnDeviceLanguageIdentificationLogEvent.toMutableList((Collection) this.backStack.getValue());
            ListIterator listIterator = mutableList.listIterator(mutableList.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (Intrinsics.areEqual(((NavBackStackEntry) listIterator.previous()).id, navBackStackEntry.id)) {
                        i = listIterator.nextIndex();
                        break;
                    }
                } else {
                    i = -1;
                    break;
                }
            }
            mutableList.set(i, navBackStackEntry);
            this._backStack$ar$class_merging.setValue(mutableList);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void pop(NavBackStackEntry navBackStackEntry, boolean z) {
        ReentrantLock reentrantLock = this.backStackLock;
        reentrantLock.lock();
        try {
            StateFlowImpl stateFlowImpl = this._backStack$ar$class_merging;
            Iterable iterable = (Iterable) stateFlowImpl.getValue();
            ArrayList arrayList = new ArrayList();
            for (Object obj : iterable) {
                if (Intrinsics.areEqual((NavBackStackEntry) obj, navBackStackEntry)) {
                    break;
                } else {
                    arrayList.add(obj);
                }
            }
            stateFlowImpl.setValue(arrayList);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void popWithTransition(NavBackStackEntry navBackStackEntry, boolean z) {
        throw null;
    }

    public void prepareForTransition(NavBackStackEntry navBackStackEntry) {
        throw null;
    }

    public void push(NavBackStackEntry navBackStackEntry) {
        throw null;
    }

    public final void pushWithTransition(NavBackStackEntry navBackStackEntry) {
        navBackStackEntry.getClass();
        Iterable iterable = (Iterable) this._transitionsInProgress$ar$class_merging.getValue();
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((NavBackStackEntry) it.next()) == navBackStackEntry) {
                    Iterable iterable2 = (Iterable) this.backStack.getValue();
                    if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                        Iterator it2 = iterable2.iterator();
                        while (it2.hasNext()) {
                            if (((NavBackStackEntry) it2.next()) == navBackStackEntry) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.lastOrNull((List) this.backStack.getValue());
        if (navBackStackEntry2 != null) {
            StateFlowImpl stateFlowImpl = this._transitionsInProgress$ar$class_merging;
            stateFlowImpl.setValue(OnDeviceObjectCreateLogEvent.plus((Set) stateFlowImpl.getValue(), navBackStackEntry2));
        }
        StateFlowImpl stateFlowImpl2 = this._transitionsInProgress$ar$class_merging;
        stateFlowImpl2.setValue(OnDeviceObjectCreateLogEvent.plus((Set) stateFlowImpl2.getValue(), navBackStackEntry));
        push(navBackStackEntry);
    }
}

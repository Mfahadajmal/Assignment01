package androidx.navigation.fragment;

import _COROUTINE._BOUNDARY;
import android.support.v4.app.Fragment;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavigatorState;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentNavigator$onAttach$2 {
    final /* synthetic */ NavigatorState $state;
    final /* synthetic */ FragmentNavigator this$0;

    public FragmentNavigator$onAttach$2(NavigatorState navigatorState, FragmentNavigator fragmentNavigator) {
        this.$state = navigatorState;
        this.this$0 = fragmentNavigator;
    }

    public final void onBackStackChangeCommitted(Fragment fragment, boolean z) {
        Object obj;
        Object obj2;
        boolean z2;
        fragment.getClass();
        List plus = OnDeviceLanguageIdentificationLogEvent.plus((Collection) this.$state.backStack.getValue(), (Iterable) this.$state.transitionsInProgress.getValue());
        ListIterator listIterator = plus.listIterator(plus.size());
        while (true) {
            obj = null;
            if (listIterator.hasPrevious()) {
                obj2 = listIterator.previous();
                if (Intrinsics.areEqual(((NavBackStackEntry) obj2).id, fragment.getTag())) {
                    break;
                }
            } else {
                obj2 = null;
                break;
            }
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj2;
        boolean z3 = true;
        if (z && this.this$0.pendingOps.isEmpty() && fragment.isRemoving()) {
            z2 = true;
        } else {
            z2 = false;
        }
        Iterator it = this.this$0.pendingOps.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Intrinsics.areEqual(((Pair) next).first, fragment.getTag())) {
                obj = next;
                break;
            }
        }
        Pair pair = (Pair) obj;
        if (pair != null) {
            this.this$0.pendingOps.remove(pair);
        }
        if (pair == null || !((Boolean) pair.second).booleanValue()) {
            z3 = false;
        }
        if (!z && !z3 && navBackStackEntry == null) {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(fragment, "The fragment ", " is unknown to the FragmentNavigator. Please use the navigate() function to add fragments to the FragmentNavigator managed FragmentManager."));
        }
        if (navBackStackEntry != null) {
            this.this$0.attachClearViewModel$navigation_fragment_release(fragment, navBackStackEntry, this.$state);
            if (z2) {
                this.$state.popWithTransition(navBackStackEntry, false);
            }
        }
    }

    public final void onBackStackChangeStarted(Fragment fragment, boolean z) {
        Object obj;
        fragment.getClass();
        if (z) {
            List list = (List) this.$state.backStack.getValue();
            ListIterator listIterator = list.listIterator(list.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    obj = listIterator.previous();
                    if (Intrinsics.areEqual(((NavBackStackEntry) obj).id, fragment.getTag())) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
            if (navBackStackEntry != null) {
                this.$state.prepareForTransition(navBackStackEntry);
            }
        }
    }
}

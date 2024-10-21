package androidx.navigation;

import _COROUTINE._BOUNDARY;
import android.os.Bundle;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavDestination;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.GeneratorSequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Navigator<D extends NavDestination> {
    private NavigatorState _state;
    public boolean isAttached;

    /* compiled from: PG */
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes.dex */
    public @interface Name {
        String value();
    }

    public abstract NavDestination createDestination();

    public final NavigatorState getState() {
        NavigatorState navigatorState = this._state;
        if (navigatorState != null) {
            return navigatorState;
        }
        throw new IllegalStateException("You cannot access the Navigator's state until the Navigator is attached");
    }

    public void navigate$ar$ds$c4edcefd_0(List list, NavOptions navOptions) {
        list.getClass();
        Iterator it = OnDeviceSmartReplyLogEvent.filterNotNull(new GeneratorSequence(OnDeviceLanguageIdentificationLogEvent.asSequence(list), (Function1) new Navigator$navigate$1(this, navOptions, 0), 3)).iterator();
        while (it.hasNext()) {
            getState().push((NavBackStackEntry) it.next());
        }
    }

    public void onAttach(NavigatorState navigatorState) {
        this._state = navigatorState;
        this.isAttached = true;
    }

    public void onLaunchSingleTop(NavBackStackEntry navBackStackEntry) {
        navBackStackEntry.getClass();
        NavDestination navDestination = navBackStackEntry.destination;
        if (true != (navDestination instanceof NavDestination)) {
            navDestination = null;
        }
        if (navDestination == null) {
            return;
        }
        navigate$ar$ds(navDestination, null, WindowInsetsCompat.TypeImpl30.navOptions(ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$4aa1c371_0));
        getState().onLaunchSingleTop(navBackStackEntry);
    }

    public Bundle onSaveState() {
        return null;
    }

    public boolean popBackStack() {
        return true;
    }

    public void popBackStack(NavBackStackEntry navBackStackEntry, boolean z) {
        navBackStackEntry.getClass();
        List list = (List) getState().backStack.getValue();
        if (list.contains(navBackStackEntry)) {
            ListIterator listIterator = list.listIterator(list.size());
            NavBackStackEntry navBackStackEntry2 = null;
            while (popBackStack()) {
                navBackStackEntry2 = (NavBackStackEntry) listIterator.previous();
                if (Intrinsics.areEqual(navBackStackEntry2, navBackStackEntry)) {
                    break;
                }
            }
            if (navBackStackEntry2 != null) {
                getState().pop(navBackStackEntry2, z);
                return;
            }
            return;
        }
        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(list, navBackStackEntry, "popBackStack was called with ", " which does not exist in back stack "));
    }

    public void onRestoreState(Bundle bundle) {
    }

    public NavDestination navigate$ar$ds(NavDestination navDestination, Bundle bundle, NavOptions navOptions) {
        return navDestination;
    }
}

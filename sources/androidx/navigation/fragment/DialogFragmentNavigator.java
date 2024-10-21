package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentOnAttachListener;
import android.util.AttributeSet;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorState;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: PG */
@Navigator.Name("dialog")
/* loaded from: classes.dex */
public final class DialogFragmentNavigator extends Navigator<Destination> {
    private final Context context;
    private final FragmentManager fragmentManager;
    public final Set restoredTagsAwaitingAttach = new LinkedHashSet();
    public final ComponentActivity.AnonymousClass4 observer$ar$class_merging$d98aab5e_0 = new ComponentActivity.AnonymousClass4(this, 2);
    public final Map transitioningFragments = new LinkedHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Destination extends NavDestination {
        private String _className;

        public Destination(Navigator navigator) {
            super(navigator);
        }

        @Override // androidx.navigation.NavDestination
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof Destination) && super.equals(obj) && Intrinsics.areEqual(this._className, ((Destination) obj)._className)) {
                return true;
            }
            return false;
        }

        public final String getClassName() {
            String str = this._className;
            if (str != null) {
                return str;
            }
            throw new IllegalStateException("DialogFragment class was not set");
        }

        @Override // androidx.navigation.NavDestination
        public final int hashCode() {
            int i;
            int hashCode = super.hashCode() * 31;
            String str = this._className;
            if (str != null) {
                i = str.hashCode();
            } else {
                i = 0;
            }
            return hashCode + i;
        }

        @Override // androidx.navigation.NavDestination
        public final void onInflate(Context context, AttributeSet attributeSet) {
            super.onInflate(context, attributeSet);
            TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R$styleable.DialogFragmentNavigator);
            obtainAttributes.getClass();
            String string = obtainAttributes.getString(0);
            if (string != null) {
                this._className = string;
            }
            obtainAttributes.recycle();
        }
    }

    public DialogFragmentNavigator(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    private final DialogFragment createDialogFragment(NavBackStackEntry navBackStackEntry) {
        NavDestination navDestination = navBackStackEntry.destination;
        navDestination.getClass();
        Destination destination = (Destination) navDestination;
        String className = destination.getClassName();
        if (className.charAt(0) == '.') {
            className = String.valueOf(this.context.getPackageName()).concat(className);
        }
        FragmentManager fragmentManager = this.fragmentManager;
        Context context = this.context;
        FragmentFactory fragmentFactory = fragmentManager.getFragmentFactory();
        context.getClassLoader();
        Fragment instantiate$ar$ds = fragmentFactory.instantiate$ar$ds(className);
        instantiate$ar$ds.getClass();
        if (DialogFragment.class.isAssignableFrom(instantiate$ar$ds.getClass())) {
            DialogFragment dialogFragment = (DialogFragment) instantiate$ar$ds;
            dialogFragment.setArguments(navBackStackEntry.getArguments());
            dialogFragment.getLifecycle().addObserver(this.observer$ar$class_merging$d98aab5e_0);
            this.transitioningFragments.put(navBackStackEntry.id, dialogFragment);
            return dialogFragment;
        }
        throw new IllegalArgumentException("Dialog destination " + destination.getClassName() + " is not an instance of DialogFragment");
    }

    @Override // androidx.navigation.Navigator
    public final /* synthetic */ NavDestination createDestination() {
        return new Destination(this);
    }

    @Override // androidx.navigation.Navigator
    public final void navigate$ar$ds$c4edcefd_0(List list, NavOptions navOptions) {
        list.getClass();
        if (!this.fragmentManager.isStateSaved()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                NavBackStackEntry navBackStackEntry = (NavBackStackEntry) it.next();
                createDialogFragment(navBackStackEntry).show(this.fragmentManager, navBackStackEntry.id);
                NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.lastOrNull((List) getState().backStack.getValue());
                boolean contains = OnDeviceLanguageIdentificationLogEvent.contains((Iterable) getState().transitionsInProgress.getValue(), navBackStackEntry2);
                getState().pushWithTransition(navBackStackEntry);
                if (navBackStackEntry2 != null && !contains) {
                    getState().markTransitionComplete(navBackStackEntry2);
                }
            }
        }
    }

    @Override // androidx.navigation.Navigator
    public final void onAttach(NavigatorState navigatorState) {
        Lifecycle lifecycle;
        super.onAttach(navigatorState);
        for (NavBackStackEntry navBackStackEntry : (List) navigatorState.backStack.getValue()) {
            DialogFragment dialogFragment = (DialogFragment) this.fragmentManager.findFragmentByTag(navBackStackEntry.id);
            if (dialogFragment != null && (lifecycle = dialogFragment.getLifecycle()) != null) {
                lifecycle.addObserver(this.observer$ar$class_merging$d98aab5e_0);
            } else {
                this.restoredTagsAwaitingAttach.add(navBackStackEntry.id);
            }
        }
        this.fragmentManager.addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: androidx.navigation.fragment.DialogFragmentNavigator$$ExternalSyntheticLambda0
            @Override // android.support.v4.app.FragmentOnAttachListener
            public final void onAttachFragment$ar$ds(Fragment fragment) {
                String tag = fragment.getTag();
                DialogFragmentNavigator dialogFragmentNavigator = DialogFragmentNavigator.this;
                if (TypeIntrinsics.asMutableCollection(dialogFragmentNavigator.restoredTagsAwaitingAttach).remove(tag)) {
                    fragment.getLifecycle().addObserver(dialogFragmentNavigator.observer$ar$class_merging$d98aab5e_0);
                }
                Map map = dialogFragmentNavigator.transitioningFragments;
                TypeIntrinsics.asMutableMap(map).remove(fragment.getTag());
            }
        });
    }

    @Override // androidx.navigation.Navigator
    public final void onLaunchSingleTop(NavBackStackEntry navBackStackEntry) {
        navBackStackEntry.getClass();
        if (this.fragmentManager.isStateSaved()) {
            return;
        }
        DialogFragment dialogFragment = (DialogFragment) this.transitioningFragments.get(navBackStackEntry.id);
        if (dialogFragment == null) {
            Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag(navBackStackEntry.id);
            if (findFragmentByTag instanceof DialogFragment) {
                dialogFragment = (DialogFragment) findFragmentByTag;
            } else {
                dialogFragment = null;
            }
        }
        if (dialogFragment != null) {
            dialogFragment.getLifecycle().removeObserver(this.observer$ar$class_merging$d98aab5e_0);
            dialogFragment.dismiss();
        }
        createDialogFragment(navBackStackEntry).show(this.fragmentManager, navBackStackEntry.id);
        NavigatorState state = getState();
        List list = (List) state.backStack.getValue();
        ListIterator listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) listIterator.previous();
            if (Intrinsics.areEqual(navBackStackEntry2.id, navBackStackEntry.id)) {
                StateFlowImpl stateFlowImpl = state._transitionsInProgress$ar$class_merging;
                stateFlowImpl.setValue(OnDeviceObjectCreateLogEvent.plus(OnDeviceObjectCreateLogEvent.plus((Set) stateFlowImpl.getValue(), navBackStackEntry2), navBackStackEntry));
                state.onLaunchSingleTop(navBackStackEntry);
                return;
            }
        }
        throw new NoSuchElementException("List contains no element matching the predicate.");
    }

    @Override // androidx.navigation.Navigator
    public final void popBackStack(NavBackStackEntry navBackStackEntry, boolean z) {
        navBackStackEntry.getClass();
        if (this.fragmentManager.isStateSaved()) {
            return;
        }
        List list = (List) getState().backStack.getValue();
        int indexOf = list.indexOf(navBackStackEntry);
        Iterator it = OnDeviceLanguageIdentificationLogEvent.reversed(list.subList(indexOf, list.size())).iterator();
        while (it.hasNext()) {
            Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag(((NavBackStackEntry) it.next()).id);
            if (findFragmentByTag != null) {
                ((DialogFragment) findFragmentByTag).dismiss();
            }
        }
        popWithTransition(indexOf, navBackStackEntry, z);
    }

    public final void popWithTransition(int i, NavBackStackEntry navBackStackEntry, boolean z) {
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.getOrNull((List) getState().backStack.getValue(), i - 1);
        boolean contains = OnDeviceLanguageIdentificationLogEvent.contains((Iterable) getState().transitionsInProgress.getValue(), navBackStackEntry2);
        getState().popWithTransition(navBackStackEntry, z);
        if (navBackStackEntry2 != null && !contains) {
            getState().markTransitionComplete(navBackStackEntry2);
        }
    }
}

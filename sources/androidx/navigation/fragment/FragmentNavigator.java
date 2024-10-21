package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.BackStackRecord;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentOnAttachListener;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import androidx.activity.ComponentActivity$$ExternalSyntheticLambda2;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.app.NotificationCompatBuilder$Api28Impl;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactory;
import androidx.navigation.ActivityNavigator$hostActivity$1;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorState;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0;
import com.google.android.accessibility.selecttospeak.tts.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KClass;
import kotlin.sequences.GeneratorSequence;

/* compiled from: PG */
@Navigator.Name("fragment")
/* loaded from: classes.dex */
public class FragmentNavigator extends Navigator<Destination> {
    private final int containerId;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final Set savedIds = new LinkedHashSet();
    public final List pendingOps = new ArrayList();
    public final LifecycleEventObserver fragmentObserver = new ComponentActivity$$ExternalSyntheticLambda2(this, 3);
    public final Function1 fragmentViewObserver = new OnBackPressedDispatcher.AnonymousClass1(this, 11);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ClearEntryStateViewModel extends ViewModel {
        public WeakReference completeTransition;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.lifecycle.ViewModel
        public final void onCleared() {
            WeakReference weakReference = this.completeTransition;
            if (weakReference == null) {
                Intrinsics.throwUninitializedPropertyAccessException("completeTransition");
                weakReference = null;
            }
            Function0 function0 = (Function0) weakReference.get();
            if (function0 != null) {
                function0.invoke();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Destination extends NavDestination {
        public String _className;

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
            TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R$styleable.FragmentNavigator);
            obtainAttributes.getClass();
            String string = obtainAttributes.getString(0);
            if (string != null) {
                this._className = string;
            }
            obtainAttributes.recycle();
        }

        @Override // androidx.navigation.NavDestination
        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" class=");
            String str = this._className;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            return sb.toString();
        }
    }

    public FragmentNavigator(Context context, FragmentManager fragmentManager, int i) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.containerId = i;
    }

    static /* synthetic */ void addPendingOps$default$ar$ds(FragmentNavigator fragmentNavigator, String str, boolean z, int i) {
        int lastIndex;
        boolean z2 = true;
        if ((i & 4) != 0) {
            List list = fragmentNavigator.pendingOps;
            OnBackPressedDispatcher.AnonymousClass1 anonymousClass1 = new OnBackPressedDispatcher.AnonymousClass1(str, 10);
            IntIterator it = new IntRange(0, OnDeviceLanguageIdentificationLogEvent.getLastIndex(list)).iterator();
            int i2 = 0;
            while (it.hasNext()) {
                int nextInt = it.nextInt();
                Object obj = list.get(nextInt);
                if (!((Boolean) anonymousClass1.invoke(obj)).booleanValue()) {
                    if (i2 != nextInt) {
                        list.set(i2, obj);
                    }
                    i2++;
                }
            }
            if (i2 < list.size() && i2 <= (lastIndex = OnDeviceLanguageIdentificationLogEvent.getLastIndex(list))) {
                while (true) {
                    list.remove(lastIndex);
                    if (lastIndex == i2) {
                        break;
                    } else {
                        lastIndex--;
                    }
                }
            }
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        fragmentNavigator.pendingOps.add(new Pair(str, Boolean.valueOf(z & z2)));
    }

    private final FragmentTransaction createFragmentTransaction(NavBackStackEntry navBackStackEntry, NavOptions navOptions) {
        int i;
        int i2;
        int i3;
        int i4;
        NavDestination navDestination = navBackStackEntry.destination;
        navDestination.getClass();
        Bundle arguments = navBackStackEntry.getArguments();
        String str = ((Destination) navDestination)._className;
        if (str != null) {
            int i5 = 0;
            if (str.charAt(0) == '.') {
                str = String.valueOf(this.context.getPackageName()).concat(str);
            }
            FragmentManager fragmentManager = this.fragmentManager;
            Context context = this.context;
            FragmentFactory fragmentFactory = fragmentManager.getFragmentFactory();
            context.getClassLoader();
            Fragment instantiate$ar$ds = fragmentFactory.instantiate$ar$ds(str);
            instantiate$ar$ds.getClass();
            instantiate$ar$ds.setArguments(arguments);
            BackStackRecord backStackRecord = new BackStackRecord(this.fragmentManager);
            if (navOptions != null) {
                i = navOptions.enterAnim;
            } else {
                i = -1;
            }
            if (navOptions != null) {
                i2 = navOptions.exitAnim;
            } else {
                i2 = -1;
            }
            if (navOptions != null) {
                i3 = navOptions.popEnterAnim;
            } else {
                i3 = -1;
            }
            if (navOptions != null) {
                i4 = navOptions.popExitAnim;
            } else {
                i4 = -1;
            }
            if (i == -1) {
                if (i2 == -1) {
                    if (i3 == -1) {
                        if (i4 != -1) {
                            i = -1;
                            i2 = -1;
                            i3 = -1;
                        }
                        backStackRecord.replace$ar$ds(this.containerId, instantiate$ar$ds, navBackStackEntry.id);
                        backStackRecord.setPrimaryNavigationFragment(instantiate$ar$ds);
                        backStackRecord.setReorderingAllowed$ar$ds();
                        return backStackRecord;
                    }
                    i = -1;
                    i2 = -1;
                } else {
                    i = -1;
                }
            }
            if (i == -1) {
                i = 0;
            }
            if (i2 == -1) {
                i2 = 0;
            }
            if (i3 == -1) {
                i3 = 0;
            }
            if (i4 != -1) {
                i5 = i4;
            }
            backStackRecord.mEnterAnim = i;
            backStackRecord.mExitAnim = i2;
            backStackRecord.mPopEnterAnim = i3;
            backStackRecord.mPopExitAnim = i5;
            backStackRecord.replace$ar$ds(this.containerId, instantiate$ar$ds, navBackStackEntry.id);
            backStackRecord.setPrimaryNavigationFragment(instantiate$ar$ds);
            backStackRecord.setReorderingAllowed$ar$ds();
            return backStackRecord;
        }
        throw new IllegalStateException("Fragment class was not set");
    }

    public final void attachClearViewModel$navigation_fragment_release(Fragment fragment, NavBackStackEntry navBackStackEntry, NavigatorState navigatorState) {
        ViewModelStore viewModelStore = fragment.getViewModelStore();
        viewModelStore.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ActivityNavigator$hostActivity$1 activityNavigator$hostActivity$1 = ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$a8ba3a7a_0;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ClearEntryStateViewModel.class);
        if (!linkedHashMap.containsKey(orCreateKotlinClass)) {
            linkedHashMap.put(orCreateKotlinClass, new NodeBrailler(orCreateKotlinClass, activityNavigator$hostActivity$1));
            Collection values = linkedHashMap.values();
            values.getClass();
            NodeBrailler[] nodeBraillerArr = (NodeBrailler[]) values.toArray(new NodeBrailler[0]);
            ((ClearEntryStateViewModel) new ViewModelProvider(viewModelStore, new InitializerViewModelFactory((NodeBrailler[]) Arrays.copyOf(nodeBraillerArr, nodeBraillerArr.length)), CreationExtras.Empty.INSTANCE).get(ClearEntryStateViewModel.class)).completeTransition = new WeakReference(new FragmentNavigator$attachClearViewModel$1(navigatorState, this, fragment, 0));
            return;
        }
        throw new IllegalArgumentException("A `initializer` with the same `clazz` has already been added: " + ViewCompat.Api30Impl.getCanonicalName(orCreateKotlinClass) + '.');
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
                boolean isEmpty = ((List) getState().backStack.getValue()).isEmpty();
                if (navOptions != null && !isEmpty && navOptions.restoreState && this.savedIds.remove(navBackStackEntry.id)) {
                    FragmentManager fragmentManager = this.fragmentManager;
                    fragmentManager.enqueueAction(new FragmentManager.RestoreBackStackState(navBackStackEntry.id), false);
                    getState().pushWithTransition(navBackStackEntry);
                } else {
                    FragmentTransaction createFragmentTransaction = createFragmentTransaction(navBackStackEntry, navOptions);
                    if (!isEmpty) {
                        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.lastOrNull((List) getState().backStack.getValue());
                        if (navBackStackEntry2 != null) {
                            addPendingOps$default$ar$ds(this, navBackStackEntry2.id, false, 6);
                        }
                        addPendingOps$default$ar$ds(this, navBackStackEntry.id, false, 6);
                        createFragmentTransaction.addToBackStack$ar$ds(navBackStackEntry.id);
                    }
                    createFragmentTransaction.commit();
                    getState().pushWithTransition(navBackStackEntry);
                }
            }
        }
    }

    @Override // androidx.navigation.Navigator
    public final void onAttach(final NavigatorState navigatorState) {
        super.onAttach(navigatorState);
        this.fragmentManager.addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda0
            @Override // android.support.v4.app.FragmentOnAttachListener
            public final void onAttachFragment$ar$ds(Fragment fragment) {
                Object obj;
                NavigatorState navigatorState2 = NavigatorState.this;
                List list = (List) navigatorState2.backStack.getValue();
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
                    FragmentNavigator fragmentNavigator = this;
                    fragment.getViewLifecycleOwnerLiveData().observe(fragment, new SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(new ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2(fragmentNavigator, fragment, navBackStackEntry, 1), 1));
                    fragment.getLifecycle().addObserver(fragmentNavigator.fragmentObserver);
                    fragmentNavigator.attachClearViewModel$navigation_fragment_release(fragment, navBackStackEntry, navigatorState2);
                }
            }
        });
        this.fragmentManager.mBackStackChangeListeners.add(new FragmentNavigator$onAttach$2(navigatorState, this));
    }

    @Override // androidx.navigation.Navigator
    public final void onLaunchSingleTop(NavBackStackEntry navBackStackEntry) {
        navBackStackEntry.getClass();
        if (this.fragmentManager.isStateSaved()) {
            return;
        }
        FragmentTransaction createFragmentTransaction = createFragmentTransaction(navBackStackEntry, null);
        List list = (List) getState().backStack.getValue();
        if (list.size() > 1) {
            NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.getOrNull(list, OnDeviceLanguageIdentificationLogEvent.getLastIndex(list) - 1);
            if (navBackStackEntry2 != null) {
                addPendingOps$default$ar$ds(this, navBackStackEntry2.id, false, 6);
            }
            addPendingOps$default$ar$ds(this, navBackStackEntry.id, true, 4);
            this.fragmentManager.popBackStack$ar$ds(navBackStackEntry.id);
            addPendingOps$default$ar$ds(this, navBackStackEntry.id, false, 2);
            createFragmentTransaction.addToBackStack$ar$ds(navBackStackEntry.id);
        }
        createFragmentTransaction.commit();
        getState().onLaunchSingleTop(navBackStackEntry);
    }

    @Override // androidx.navigation.Navigator
    public final void onRestoreState(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("androidx-nav-fragment:navigator:savedIds");
        if (stringArrayList != null) {
            this.savedIds.clear();
            OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(this.savedIds, stringArrayList);
        }
    }

    @Override // androidx.navigation.Navigator
    public final Bundle onSaveState() {
        if (this.savedIds.isEmpty()) {
            return null;
        }
        return NotificationCompatBuilder$Api28Impl.bundleOf(new Pair("androidx-nav-fragment:navigator:savedIds", new ArrayList(this.savedIds)));
    }

    @Override // androidx.navigation.Navigator
    public final void popBackStack(NavBackStackEntry navBackStackEntry, boolean z) {
        navBackStackEntry.getClass();
        if (this.fragmentManager.isStateSaved()) {
            return;
        }
        List list = (List) getState().backStack.getValue();
        int indexOf = list.indexOf(navBackStackEntry);
        List subList = list.subList(indexOf, list.size());
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.first(list);
        if (z) {
            for (NavBackStackEntry navBackStackEntry3 : OnDeviceLanguageIdentificationLogEvent.reversed(subList)) {
                if (Intrinsics.areEqual(navBackStackEntry3, navBackStackEntry2)) {
                    Objects.toString(navBackStackEntry3);
                } else {
                    FragmentManager fragmentManager = this.fragmentManager;
                    fragmentManager.enqueueAction(new FragmentManager.SaveBackStackState(navBackStackEntry3.id), false);
                    this.savedIds.add(navBackStackEntry3.id);
                }
            }
        } else {
            this.fragmentManager.popBackStack$ar$ds(navBackStackEntry.id);
        }
        NavBackStackEntry navBackStackEntry4 = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.getOrNull(list, indexOf - 1);
        if (navBackStackEntry4 != null) {
            addPendingOps$default$ar$ds(this, navBackStackEntry4.id, false, 6);
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : subList) {
            NavBackStackEntry navBackStackEntry5 = (NavBackStackEntry) obj;
            GeneratorSequence generatorSequence = new GeneratorSequence(OnDeviceLanguageIdentificationLogEvent.asSequence(this.pendingOps), (Function1) ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$e724a7f4_0, 3);
            String str = navBackStackEntry5.id;
            Iterator it = generatorSequence.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (i < 0) {
                    OnDeviceLanguageIdentificationLogEvent.throwIndexOverflow();
                }
                if (Intrinsics.areEqual(str, next)) {
                    if (i >= 0) {
                    }
                } else {
                    i++;
                }
            }
            if (!Intrinsics.areEqual(navBackStackEntry5.id, navBackStackEntry2.id)) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            addPendingOps$default$ar$ds(this, ((NavBackStackEntry) it2.next()).id, true, 4);
        }
        getState().popWithTransition(navBackStackEntry, z);
    }
}

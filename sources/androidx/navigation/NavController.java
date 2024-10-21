package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.activity.ComponentActivity$$ExternalSyntheticLambda2;
import androidx.activity.ComponentActivity$fullyDrawnReporter$2$1;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.view.ViewConfigurationCompat$Api28Impl;
import androidx.core.view.ViewParentCompat$Api21Impl;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.fragment.DialogFragmentNavigator;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.GeneratorSequence;
import kotlin.sequences.TakeWhileSequence$iterator$1;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavController {
    public static final boolean deepLinkSaveState = true;
    public final StateFlowImpl _currentBackStack$ar$class_merging;
    private final MutableSharedFlow _currentBackStackEntryFlow;
    public NavGraph _graph;
    public NavigatorProvider _navigatorProvider;
    public final StateFlowImpl _visibleEntries$ar$class_merging;
    private Activity activity;
    public Function1 addToBackStackHandler;
    public final ArrayDeque backQueue;
    private final List backStackEntriesToDispatch;
    public final Map backStackMap;
    public final Map backStackStates;
    public Parcelable[] backStackToRestore;
    private final Map childToParentEntries;
    public final Context context;
    public boolean deepLinkHandled;
    private int dispatchReentrantCount;
    private boolean enableOnBackPressedCallback;
    public final Map entrySavedState;
    public Lifecycle.State hostLifecycleState;
    public final LifecycleObserver lifecycleObserver;
    public LifecycleOwner lifecycleOwner;
    private final Lazy navInflater$delegate;
    public final Map navigatorState;
    public Bundle navigatorStateToRestore;
    private final OnBackPressedCallback onBackPressedCallback;
    private final CopyOnWriteArrayList onDestinationChangedListeners;
    private final Map parentToChildCount;
    public Function1 popFromBackStackHandler;
    public NavControllerViewModel viewModel;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NavControllerNavigatorState extends NavigatorState {
        private final Navigator navigator;
        final /* synthetic */ NavController this$0;

        public NavControllerNavigatorState(NavController navController, Navigator navigator) {
            navigator.getClass();
            this.this$0 = navController;
            this.navigator = navigator;
        }

        public final void addInternal(NavBackStackEntry navBackStackEntry) {
            navBackStackEntry.getClass();
            ReentrantLock reentrantLock = this.backStackLock;
            reentrantLock.lock();
            try {
                StateFlowImpl stateFlowImpl = this._backStack$ar$class_merging;
                stateFlowImpl.setValue(OnDeviceLanguageIdentificationLogEvent.plus((Collection) stateFlowImpl.getValue(), navBackStackEntry));
            } finally {
                reentrantLock.unlock();
            }
        }

        @Override // androidx.navigation.NavigatorState
        public final NavBackStackEntry createBackStackEntry(NavDestination navDestination, Bundle bundle) {
            ViewConfigurationCompat$Api28Impl viewConfigurationCompat$Api28Impl = NavBackStackEntry.Companion$ar$class_merging$a8236c35_0;
            NavController navController = this.this$0;
            return ViewConfigurationCompat$Api28Impl.create$default$ar$ds$ar$class_merging(viewConfigurationCompat$Api28Impl, navController.context, navDestination, bundle, navController.getHostLifecycleState$navigation_runtime_release(), this.this$0.viewModel);
        }

        @Override // androidx.navigation.NavigatorState
        public final void markTransitionComplete(NavBackStackEntry navBackStackEntry) {
            NavControllerViewModel navControllerViewModel;
            boolean z;
            navBackStackEntry.getClass();
            boolean areEqual = Intrinsics.areEqual(this.this$0.entrySavedState.get(navBackStackEntry), true);
            Set set = (Set) this._transitionsInProgress$ar$class_merging.getValue();
            set.getClass();
            LinkedHashSet linkedHashSet = new LinkedHashSet(OnDeviceLanguageIdentificationLogEvent.mapCapacity(set.size()));
            boolean z2 = false;
            for (Object obj : set) {
                if (!z2 && Intrinsics.areEqual(obj, navBackStackEntry)) {
                    z2 = true;
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    linkedHashSet.add(obj);
                }
            }
            this._transitionsInProgress$ar$class_merging.setValue(linkedHashSet);
            this.this$0.entrySavedState.remove(navBackStackEntry);
            if (!this.this$0.backQueue.contains(navBackStackEntry)) {
                this.this$0.unlinkChildFromParent$navigation_runtime_release$ar$ds(navBackStackEntry);
                if (navBackStackEntry._lifecycle.state.isAtLeast(Lifecycle.State.CREATED)) {
                    navBackStackEntry.setMaxLifecycle(Lifecycle.State.DESTROYED);
                }
                ArrayDeque arrayDeque = this.this$0.backQueue;
                if (!arrayDeque.isEmpty()) {
                    Iterator<E> it = arrayDeque.iterator();
                    while (it.hasNext()) {
                        if (Intrinsics.areEqual(((NavBackStackEntry) it.next()).id, navBackStackEntry.id)) {
                            break;
                        }
                    }
                }
                if (!areEqual && (navControllerViewModel = this.this$0.viewModel) != null) {
                    navControllerViewModel.clear(navBackStackEntry.id);
                }
                this.this$0.updateBackStackLifecycle$navigation_runtime_release();
                NavController navController = this.this$0;
                navController._visibleEntries$ar$class_merging.tryEmit(navController.populateVisibleEntries$navigation_runtime_release());
                return;
            }
            if (!this.isNavigating) {
                this.this$0.updateBackStackLifecycle$navigation_runtime_release();
                NavController navController2 = this.this$0;
                navController2._currentBackStack$ar$class_merging.tryEmit(OnDeviceLanguageIdentificationLogEvent.toMutableList((Collection) navController2.backQueue));
                NavController navController3 = this.this$0;
                navController3._visibleEntries$ar$class_merging.tryEmit(navController3.populateVisibleEntries$navigation_runtime_release());
            }
        }

        @Override // androidx.navigation.NavigatorState
        public final void pop(final NavBackStackEntry navBackStackEntry, final boolean z) {
            Navigator navigator = this.this$0._navigatorProvider.getNavigator(navBackStackEntry.destination.navigatorName);
            if (Intrinsics.areEqual(navigator, this.navigator)) {
                NavController navController = this.this$0;
                Function1 function1 = navController.popFromBackStackHandler;
                if (function1 != null) {
                    function1.invoke(navBackStackEntry);
                    super.pop(navBackStackEntry, z);
                    return;
                }
                Function0 function0 = new Function0() { // from class: androidx.navigation.NavController$NavControllerNavigatorState$pop$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final /* bridge */ /* synthetic */ Object invoke() {
                        super/*androidx.navigation.NavigatorState*/.pop(navBackStackEntry, z);
                        return Unit.INSTANCE;
                    }
                };
                int indexOf = navController.backQueue.indexOf(navBackStackEntry);
                if (indexOf < 0) {
                    Objects.toString(navBackStackEntry);
                    return;
                }
                int i = indexOf + 1;
                ArrayDeque arrayDeque = navController.backQueue;
                if (i != arrayDeque.size) {
                    navController.popBackStackInternal(((NavBackStackEntry) arrayDeque.get(i)).destination.id, true, false);
                }
                NavController.popEntryFromBackStack$default$ar$ds(navController, navBackStackEntry);
                function0.invoke();
                navController.updateOnBackPressedCallbackEnabled();
                navController.dispatchOnDestinationChanged();
                return;
            }
            Object obj = this.this$0.navigatorState.get(navigator);
            obj.getClass();
            ((NavControllerNavigatorState) obj).pop(navBackStackEntry, z);
        }

        @Override // androidx.navigation.NavigatorState
        public final void popWithTransition(NavBackStackEntry navBackStackEntry, boolean z) {
            Object obj;
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
                                }
                            }
                        }
                    }
                }
            }
            StateFlowImpl stateFlowImpl = this._transitionsInProgress$ar$class_merging;
            stateFlowImpl.setValue(OnDeviceObjectCreateLogEvent.plus((Set) stateFlowImpl.getValue(), navBackStackEntry));
            List list = (List) this.backStack.getValue();
            ListIterator listIterator = list.listIterator(list.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    obj = listIterator.previous();
                    NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) obj;
                    if (!Intrinsics.areEqual(navBackStackEntry2, navBackStackEntry) && ((List) this.backStack.getValue()).lastIndexOf(navBackStackEntry2) < ((List) this.backStack.getValue()).lastIndexOf(navBackStackEntry)) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            NavBackStackEntry navBackStackEntry3 = (NavBackStackEntry) obj;
            if (navBackStackEntry3 != null) {
                StateFlowImpl stateFlowImpl2 = this._transitionsInProgress$ar$class_merging;
                stateFlowImpl2.setValue(OnDeviceObjectCreateLogEvent.plus((Set) stateFlowImpl2.getValue(), navBackStackEntry3));
            }
            pop(navBackStackEntry, z);
            this.this$0.entrySavedState.put(navBackStackEntry, Boolean.valueOf(z));
        }

        @Override // androidx.navigation.NavigatorState
        public final void prepareForTransition(NavBackStackEntry navBackStackEntry) {
            this._transitionsInProgress$ar$class_merging.setValue(OnDeviceObjectCreateLogEvent.plus((Set) this._transitionsInProgress$ar$class_merging.getValue(), navBackStackEntry));
            if (this.this$0.backQueue.contains(navBackStackEntry)) {
                navBackStackEntry.setMaxLifecycle(Lifecycle.State.STARTED);
                return;
            }
            throw new IllegalStateException("Cannot transition entry that is not in the back stack");
        }

        @Override // androidx.navigation.NavigatorState
        public final void push(NavBackStackEntry navBackStackEntry) {
            navBackStackEntry.getClass();
            Navigator navigator = this.this$0._navigatorProvider.getNavigator(navBackStackEntry.destination.navigatorName);
            if (Intrinsics.areEqual(navigator, this.navigator)) {
                Function1 function1 = this.this$0.addToBackStackHandler;
                if (function1 != null) {
                    function1.invoke(navBackStackEntry);
                    addInternal(navBackStackEntry);
                    return;
                } else {
                    Objects.toString(navBackStackEntry.destination);
                    return;
                }
            }
            Object obj = this.this$0.navigatorState.get(navigator);
            if (obj != null) {
                ((NavControllerNavigatorState) obj).push(navBackStackEntry);
                return;
            }
            throw new IllegalStateException("NavigatorBackStack for " + navBackStackEntry.destination.navigatorName + " should already be created");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnDestinationChangedListener {
        void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle);
    }

    public NavController(Context context) {
        Object obj;
        this.context = context;
        Iterator it = OnDeviceSmartReplyLogEvent.generateSequence(context, ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$ba4de846_0).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((Context) obj) instanceof Activity) {
                    break;
                }
            }
        }
        this.activity = (Activity) obj;
        this.backQueue = new ArrayDeque();
        this._currentBackStack$ar$class_merging = StateFlowKt.MutableStateFlow$ar$class_merging(EmptyList.INSTANCE);
        this._visibleEntries$ar$class_merging = StateFlowKt.MutableStateFlow$ar$class_merging(EmptyList.INSTANCE);
        this.childToParentEntries = new LinkedHashMap();
        this.parentToChildCount = new LinkedHashMap();
        this.backStackMap = new LinkedHashMap();
        this.backStackStates = new LinkedHashMap();
        this.onDestinationChangedListeners = new CopyOnWriteArrayList();
        this.hostLifecycleState = Lifecycle.State.INITIALIZED;
        this.lifecycleObserver = new ComponentActivity$$ExternalSyntheticLambda2(this, 2);
        this.onBackPressedCallback = new OnBackPressedCallback() { // from class: androidx.navigation.NavController$onBackPressedCallback$1
            {
                super(false);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public final void handleOnBackPressed() {
                NavController navController = NavController.this;
                if (navController.backQueue.isEmpty()) {
                    return;
                }
                NavDestination currentDestination = navController.getCurrentDestination();
                currentDestination.getClass();
                navController.popBackStack$ar$ds$8ce35bcf_0(currentDestination.id, true);
            }
        };
        this.enableOnBackPressedCallback = true;
        this._navigatorProvider = new NavigatorProvider();
        this.navigatorState = new LinkedHashMap();
        this.entrySavedState = new LinkedHashMap();
        NavigatorProvider navigatorProvider = this._navigatorProvider;
        navigatorProvider.addNavigator$ar$ds(new NavGraphNavigator(navigatorProvider));
        this._navigatorProvider.addNavigator$ar$ds(new ActivityNavigator(this.context));
        this.backStackEntriesToDispatch = new ArrayList();
        this.navInflater$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 9));
        this._currentBackStackEntryFlow = new SharedFlowImpl(BufferOverflow.DROP_OLDEST$ar$edu);
    }

    private static final NavDestination findDestination$ar$ds(NavDestination navDestination, int i) {
        NavGraph navGraph;
        if (navDestination.id == i) {
            return navDestination;
        }
        if (navDestination instanceof NavGraph) {
            navGraph = (NavGraph) navDestination;
        } else {
            navGraph = navDestination.parent;
            navGraph.getClass();
        }
        return navGraph.findNode(i);
    }

    private final void linkChildToParent(NavBackStackEntry navBackStackEntry, NavBackStackEntry navBackStackEntry2) {
        this.childToParentEntries.put(navBackStackEntry, navBackStackEntry2);
        if (this.parentToChildCount.get(navBackStackEntry2) == null) {
            this.parentToChildCount.put(navBackStackEntry2, new AtomicInteger(0));
        }
        Object obj = this.parentToChildCount.get(navBackStackEntry2);
        obj.getClass();
        ((AtomicInteger) obj).incrementAndGet();
    }

    private final void navigateInternal$ar$ds(Navigator navigator, List list, NavOptions navOptions, Function1 function1) {
        this.addToBackStackHandler = function1;
        navigator.navigate$ar$ds$c4edcefd_0(list, navOptions);
        this.addToBackStackHandler = null;
    }

    static /* synthetic */ void popEntryFromBackStack$default$ar$ds(NavController navController, NavBackStackEntry navBackStackEntry) {
        navController.popEntryFromBackStack(navBackStackEntry, false, new ArrayDeque());
    }

    private final boolean restoreStateInternal$ar$ds(int i, final Bundle bundle, NavOptions navOptions) {
        NavDestination graph;
        NavBackStackEntry navBackStackEntry;
        NavDestination navDestination;
        Map map = this.backStackMap;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf)) {
            return false;
        }
        String str = (String) this.backStackMap.get(valueOf);
        Collection values = this.backStackMap.values();
        OnBackPressedDispatcher.AnonymousClass1 anonymousClass1 = new OnBackPressedDispatcher.AnonymousClass1(str, 6);
        values.getClass();
        OnDeviceLanguageIdentificationLogEvent.filterInPlace$CollectionsKt__MutableCollectionsKt$ar$ds(values, anonymousClass1, true);
        ArrayDeque<NavBackStackEntryState> arrayDeque = (ArrayDeque) TypeIntrinsics.asMutableMap(this.backStackStates).remove(str);
        final ArrayList arrayList = new ArrayList();
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) this.backQueue.lastOrNull();
        if (navBackStackEntry2 == null || (graph = navBackStackEntry2.destination) == null) {
            graph = getGraph();
        }
        if (arrayDeque != null) {
            for (NavBackStackEntryState navBackStackEntryState : arrayDeque) {
                NavDestination findDestination$ar$ds = findDestination$ar$ds(graph, navBackStackEntryState.destinationId);
                if (findDestination$ar$ds != null) {
                    arrayList.add(navBackStackEntryState.instantiate(this.context, findDestination$ar$ds, getHostLifecycleState$navigation_runtime_release(), this.viewModel));
                    graph = findDestination$ar$ds;
                } else {
                    ViewParentCompat$Api21Impl viewParentCompat$Api21Impl = NavDestination.Companion$ar$class_merging$b389cfd1_0;
                    throw new IllegalStateException("Restore State failed: destination " + ViewParentCompat$Api21Impl.getDisplayName$ar$ds(this.context, navBackStackEntryState.destinationId) + " cannot be found from the current destination " + graph);
                }
            }
        }
        ArrayList<List> arrayList2 = new ArrayList();
        ArrayList<NavBackStackEntry> arrayList3 = new ArrayList();
        for (Object obj : arrayList) {
            if (!(((NavBackStackEntry) obj).destination instanceof NavGraph)) {
                arrayList3.add(obj);
            }
        }
        for (NavBackStackEntry navBackStackEntry3 : arrayList3) {
            List list = (List) OnDeviceLanguageIdentificationLogEvent.lastOrNull(arrayList2);
            String str2 = null;
            if (list != null && (navBackStackEntry = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.last(list)) != null && (navDestination = navBackStackEntry.destination) != null) {
                str2 = navDestination.navigatorName;
            }
            if (Intrinsics.areEqual(str2, navBackStackEntry3.destination.navigatorName)) {
                list.add(navBackStackEntry3);
            } else {
                arrayList2.add(OnDeviceLanguageIdentificationLogEvent.mutableListOf(navBackStackEntry3));
            }
        }
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        for (List list2 : arrayList2) {
            Navigator navigator = this._navigatorProvider.getNavigator(((NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.first(list2)).destination.navigatorName);
            final Ref$IntRef ref$IntRef = new Ref$IntRef();
            navigateInternal$ar$ds(navigator, list2, navOptions, new Function1() { // from class: androidx.navigation.NavController$executeRestoreState$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    List list3;
                    NavBackStackEntry navBackStackEntry4 = (NavBackStackEntry) obj2;
                    navBackStackEntry4.getClass();
                    Ref$BooleanRef.this.element = true;
                    int indexOf = arrayList.indexOf(navBackStackEntry4);
                    if (indexOf != -1) {
                        int i2 = indexOf + 1;
                        list3 = arrayList.subList(ref$IntRef.element, i2);
                        ref$IntRef.element = i2;
                    } else {
                        list3 = EmptyList.INSTANCE;
                    }
                    this.addEntryToBackStack(navBackStackEntry4.destination, bundle, navBackStackEntry4, list3);
                    return Unit.INSTANCE;
                }
            });
        }
        return ref$BooleanRef.element;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x018b, code lost:
    
        r5 = r11.context;
        r4 = androidx.navigation.NavBackStackEntry.Companion$ar$class_merging$a8236c35_0;
        r6 = r11._graph;
        r6.getClass();
        r6.getClass();
        r3 = androidx.core.view.ViewConfigurationCompat$Api28Impl.create$default$ar$ds$ar$class_merging(r4, r5, r6, r6.addInDefaultArgs(r13), getHostLifecycleState$navigation_runtime_release(), r11.viewModel);
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01a5, code lost:
    
        r1.addFirst(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01a8, code lost:
    
        r13 = r1.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01b0, code lost:
    
        if (r13.hasNext() == false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01b2, code lost:
    
        r15 = (androidx.navigation.NavBackStackEntry) r13.next();
        r0 = r11.navigatorState.get(r11._navigatorProvider.getNavigator(r15.destination.navigatorName));
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01c8, code lost:
    
        if (r0 == null) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01ca, code lost:
    
        ((androidx.navigation.NavController.NavControllerNavigatorState) r0).addInternal(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01ea, code lost:
    
        throw new java.lang.IllegalStateException("NavigatorBackStack for " + r12.navigatorName + " should already be created");
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01eb, code lost:
    
        r11.backQueue.addAll(r1);
        r11.backQueue.add(r14);
        r12 = com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.plus(r1, r14).iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0201, code lost:
    
        if (r12.hasNext() == false) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0203, code lost:
    
        r13 = (androidx.navigation.NavBackStackEntry) r12.next();
        r14 = r13.destination.parent;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x020d, code lost:
    
        if (r14 == null) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x020f, code lost:
    
        linkChildToParent(r13, getBackStackEntry(r14.id));
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0219, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x015b, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x00a1, code lost:
    
        r2 = ((androidx.navigation.NavBackStackEntry) r1.first()).destination;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
    
        r1 = new kotlin.collections.ArrayDeque();
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0036, code lost:
    
        if ((r12 instanceof androidx.navigation.NavGraph) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
    
        r2.getClass();
        r2 = r2.parent;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003e, code lost:
    
        if (r2 == null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
    
        r4 = r15.listIterator(r15.size());
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004c, code lost:
    
        if (r4.hasPrevious() == false) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004e, code lost:
    
        r5 = r4.previous();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005b, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(((androidx.navigation.NavBackStackEntry) r5).destination, r2) == false) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005f, code lost:
    
        r5 = (androidx.navigation.NavBackStackEntry) r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
    
        if (r5 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
    
        r5 = androidx.core.view.ViewConfigurationCompat$Api28Impl.create$default$ar$ds$ar$class_merging(androidx.navigation.NavBackStackEntry.Companion$ar$class_merging$a8236c35_0, r11.context, r2, r13, getHostLifecycleState$navigation_runtime_release(), r11.viewModel);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0073, code lost:
    
        r1.addFirst(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007c, code lost:
    
        if (r11.backQueue.isEmpty() != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0004, code lost:
    
        if ((r0 instanceof androidx.navigation.fragment.DialogFragmentNavigator.Destination) == false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0088, code lost:
    
        if (((androidx.navigation.NavBackStackEntry) r11.backQueue.last()).destination != r2) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008a, code lost:
    
        popEntryFromBackStack$default$ar$ds(r11, (androidx.navigation.NavBackStackEntry) r11.backQueue.last());
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005e, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0095, code lost:
    
        if (r2 == null) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0097, code lost:
    
        if (r2 != r12) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009d, code lost:
    
        if (r1.isEmpty() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009f, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a9, code lost:
    
        if (r2 == null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b1, code lost:
    
        if (findDestination(r2.id) == r2) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b3, code lost:
    
        r2 = r2.parent;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b5, code lost:
    
        if (r2 == null) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r11.backQueue.isEmpty() != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b7, code lost:
    
        if (r13 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00be, code lost:
    
        if (r13.isEmpty() != true) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c0, code lost:
    
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c3, code lost:
    
        r5 = r15.listIterator(r15.size());
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00cf, code lost:
    
        if (r5.hasPrevious() == false) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d1, code lost:
    
        r6 = r5.previous();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00de, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(((androidx.navigation.NavBackStackEntry) r6).destination, r2) == false) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e2, code lost:
    
        r6 = (androidx.navigation.NavBackStackEntry) r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00e4, code lost:
    
        if (r6 != null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e6, code lost:
    
        r6 = androidx.core.view.ViewConfigurationCompat$Api28Impl.create$default$ar$ds$ar$class_merging(androidx.navigation.NavBackStackEntry.Companion$ar$class_merging$a8236c35_0, r11.context, r2, r2.addInDefaultArgs(r4), getHostLifecycleState$navigation_runtime_release(), r11.viewModel);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00f9, code lost:
    
        r1.addFirst(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e1, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00c2, code lost:
    
        r4 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001a, code lost:
    
        if ((((androidx.navigation.NavBackStackEntry) r11.backQueue.last()).destination instanceof androidx.navigation.fragment.DialogFragmentNavigator.Destination) == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0101, code lost:
    
        if (r1.isEmpty() == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0104, code lost:
    
        r0 = ((androidx.navigation.NavBackStackEntry) r1.first()).destination;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0112, code lost:
    
        if (r11.backQueue.isEmpty() != false) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0120, code lost:
    
        if ((((androidx.navigation.NavBackStackEntry) r11.backQueue.last()).destination instanceof androidx.navigation.NavGraph) == false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0122, code lost:
    
        r2 = ((androidx.navigation.NavBackStackEntry) r11.backQueue.last()).destination;
        r2.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0138, code lost:
    
        if (((androidx.navigation.NavGraph) r2).findNode(r0.id, false) != null) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x013a, code lost:
    
        popEntryFromBackStack$default$ar$ds(r11, (androidx.navigation.NavBackStackEntry) r11.backQueue.last());
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0146, code lost:
    
        r0 = (androidx.navigation.NavBackStackEntry) r11.backQueue.firstOrNull();
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x014e, code lost:
    
        if (r0 != null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0150, code lost:
    
        r0 = (androidx.navigation.NavBackStackEntry) r1.firstOrNull();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0156, code lost:
    
        if (r0 == null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0158, code lost:
    
        r0 = r0.destination;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002c, code lost:
    
        if (popBackStackInternal(((androidx.navigation.NavBackStackEntry) r11.backQueue.last()).destination.id, true, false) != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0162, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, r11._graph) != false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0164, code lost:
    
        r15 = r15.listIterator(r15.size());
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0170, code lost:
    
        if (r15.hasPrevious() == false) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0172, code lost:
    
        r0 = r15.previous();
        r2 = ((androidx.navigation.NavBackStackEntry) r0).destination;
        r4 = r11._graph;
        r4.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0184, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r2, r4) == false) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0186, code lost:
    
        r3 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0187, code lost:
    
        r3 = (androidx.navigation.NavBackStackEntry) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0189, code lost:
    
        if (r3 != null) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addEntryToBackStack(androidx.navigation.NavDestination r12, android.os.Bundle r13, androidx.navigation.NavBackStackEntry r14, java.util.List r15) {
        /*
            Method dump skipped, instructions count: 538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.addEntryToBackStack(androidx.navigation.NavDestination, android.os.Bundle, androidx.navigation.NavBackStackEntry, java.util.List):void");
    }

    public final void addOnDestinationChangedListener(OnDestinationChangedListener onDestinationChangedListener) {
        this.onDestinationChangedListeners.add(onDestinationChangedListener);
        if (!this.backQueue.isEmpty()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.backQueue.last();
            onDestinationChangedListener.onDestinationChanged(this, navBackStackEntry.destination, navBackStackEntry.getArguments());
        }
    }

    public final boolean dispatchOnDestinationChanged() {
        while (!this.backQueue.isEmpty() && (((NavBackStackEntry) this.backQueue.last()).destination instanceof NavGraph)) {
            popEntryFromBackStack$default$ar$ds(this, (NavBackStackEntry) this.backQueue.last());
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.backQueue.lastOrNull();
        if (navBackStackEntry != null) {
            this.backStackEntriesToDispatch.add(navBackStackEntry);
        }
        this.dispatchReentrantCount++;
        updateBackStackLifecycle$navigation_runtime_release();
        int i = this.dispatchReentrantCount - 1;
        this.dispatchReentrantCount = i;
        if (i == 0) {
            List list = this.backStackEntriesToDispatch;
            List<NavBackStackEntry> mutableList = OnDeviceLanguageIdentificationLogEvent.toMutableList((Collection) list);
            list.clear();
            for (NavBackStackEntry navBackStackEntry2 : mutableList) {
                Iterator it = this.onDestinationChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDestinationChangedListener) it.next()).onDestinationChanged(this, navBackStackEntry2.destination, navBackStackEntry2.getArguments());
                }
                this._currentBackStackEntryFlow.tryEmit(navBackStackEntry2);
            }
            this._currentBackStack$ar$class_merging.tryEmit(OnDeviceLanguageIdentificationLogEvent.toMutableList((Collection) this.backQueue));
            this._visibleEntries$ar$class_merging.tryEmit(populateVisibleEntries$navigation_runtime_release());
        }
        if (navBackStackEntry != null) {
            return true;
        }
        return false;
    }

    public final NavDestination findDestination(int i) {
        NavDestination navDestination;
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            return null;
        }
        if (navGraph.id == i) {
            return navGraph;
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.backQueue.lastOrNull();
        if (navBackStackEntry == null || (navDestination = navBackStackEntry.destination) == null) {
            navDestination = this._graph;
            navDestination.getClass();
        }
        return findDestination$ar$ds(navDestination, i);
    }

    public final NavBackStackEntry getBackStackEntry(int i) {
        Object obj;
        ArrayDeque arrayDeque = this.backQueue;
        ListIterator<E> listIterator = arrayDeque.listIterator(arrayDeque.size);
        while (true) {
            if (listIterator.hasPrevious()) {
                obj = listIterator.previous();
                if (((NavBackStackEntry) obj).destination.id == i) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
        if (navBackStackEntry != null) {
            return navBackStackEntry;
        }
        throw new IllegalArgumentException("No destination with ID " + i + " is on the NavController's back stack. The current destination is " + getCurrentDestination());
    }

    public final NavBackStackEntry getCurrentBackStackEntry() {
        return (NavBackStackEntry) this.backQueue.lastOrNull();
    }

    public final NavDestination getCurrentDestination() {
        NavBackStackEntry currentBackStackEntry = getCurrentBackStackEntry();
        if (currentBackStackEntry != null) {
            return currentBackStackEntry.destination;
        }
        return null;
    }

    public final NavGraph getGraph() {
        NavGraph navGraph = this._graph;
        if (navGraph != null) {
            return navGraph;
        }
        throw new IllegalStateException("You must call setGraph() before calling getGraph()");
    }

    public final Lifecycle.State getHostLifecycleState$navigation_runtime_release() {
        if (this.lifecycleOwner == null) {
            return Lifecycle.State.CREATED;
        }
        return this.hostLifecycleState;
    }

    public final NavInflater getNavInflater() {
        return (NavInflater) this.navInflater$delegate.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0103  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void navigate$ar$ds$e57d0073_0(final androidx.navigation.NavDestination r13, android.os.Bundle r14, androidx.navigation.NavOptions r15) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.navigate$ar$ds$e57d0073_0(androidx.navigation.NavDestination, android.os.Bundle, androidx.navigation.NavOptions):void");
    }

    public final void popBackStack$ar$ds$8ce35bcf_0(int i, boolean z) {
        if (popBackStackInternal(i, z, false)) {
            dispatchOnDestinationChanged();
        }
    }

    public final boolean popBackStackInternal(int i, boolean z, final boolean z2) {
        NavDestination navDestination;
        String str;
        String str2;
        if (this.backQueue.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = OnDeviceLanguageIdentificationLogEvent.reversed(this.backQueue).iterator();
        while (true) {
            if (it.hasNext()) {
                NavDestination navDestination2 = ((NavBackStackEntry) it.next()).destination;
                Navigator navigator = this._navigatorProvider.getNavigator(navDestination2.navigatorName);
                if (z || navDestination2.id != i) {
                    arrayList.add(navigator);
                }
                if (navDestination2.id == i) {
                    navDestination = navDestination2;
                    break;
                }
            } else {
                navDestination = null;
                break;
            }
        }
        if (navDestination == null) {
            ViewParentCompat$Api21Impl viewParentCompat$Api21Impl = NavDestination.Companion$ar$class_merging$b389cfd1_0;
            ViewParentCompat$Api21Impl.getDisplayName$ar$ds(this.context, i);
            return false;
        }
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        final ArrayDeque arrayDeque = new ArrayDeque();
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (it2.hasNext()) {
                Navigator navigator2 = (Navigator) it2.next();
                final Ref$BooleanRef ref$BooleanRef2 = new Ref$BooleanRef();
                NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.backQueue.last();
                this.popFromBackStackHandler = new Function1() { // from class: androidx.navigation.NavController$executePopOperations$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) obj;
                        navBackStackEntry2.getClass();
                        Ref$BooleanRef.this.element = true;
                        ref$BooleanRef.element = true;
                        this.popEntryFromBackStack(navBackStackEntry2, z2, arrayDeque);
                        return Unit.INSTANCE;
                    }
                };
                navigator2.popBackStack(navBackStackEntry, z2);
                str = null;
                this.popFromBackStackHandler = null;
                if (!ref$BooleanRef2.element) {
                    break;
                }
            } else {
                str = null;
                break;
            }
        }
        if (z2) {
            if (!z) {
                TakeWhileSequence$iterator$1 takeWhileSequence$iterator$1 = new TakeWhileSequence$iterator$1(new GeneratorSequence(OnDeviceSmartReplyLogEvent.generateSequence(navDestination, ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$18898783_0), (Function1) new OnBackPressedDispatcher.AnonymousClass1(this, 4), 2), 0);
                while (takeWhileSequence$iterator$1.hasNext()) {
                    NavDestination navDestination3 = (NavDestination) takeWhileSequence$iterator$1.next();
                    Map map = this.backStackMap;
                    Integer valueOf = Integer.valueOf(navDestination3.id);
                    NavBackStackEntryState navBackStackEntryState = (NavBackStackEntryState) arrayDeque.firstOrNull();
                    if (navBackStackEntryState != null) {
                        str2 = navBackStackEntryState.id;
                    } else {
                        str2 = str;
                    }
                    map.put(valueOf, str2);
                }
            }
            if (!arrayDeque.isEmpty()) {
                NavBackStackEntryState navBackStackEntryState2 = (NavBackStackEntryState) arrayDeque.first();
                TakeWhileSequence$iterator$1 takeWhileSequence$iterator$12 = new TakeWhileSequence$iterator$1(new GeneratorSequence(OnDeviceSmartReplyLogEvent.generateSequence(findDestination(navBackStackEntryState2.destinationId), ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$3fc7c434_0), (Function1) new OnBackPressedDispatcher.AnonymousClass1(this, 5), 2), 0);
                while (takeWhileSequence$iterator$12.hasNext()) {
                    this.backStackMap.put(Integer.valueOf(((NavDestination) takeWhileSequence$iterator$12.next()).id), navBackStackEntryState2.id);
                }
                Map map2 = this.backStackMap;
                if (map2.values().contains(navBackStackEntryState2.id)) {
                    this.backStackStates.put(navBackStackEntryState2.id, arrayDeque);
                }
            }
        }
        updateOnBackPressedCallbackEnabled();
        return ref$BooleanRef.element;
    }

    public final void popEntryFromBackStack(NavBackStackEntry navBackStackEntry, boolean z, ArrayDeque arrayDeque) {
        NavControllerViewModel navControllerViewModel;
        Set set;
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) this.backQueue.last();
        if (Intrinsics.areEqual(navBackStackEntry2, navBackStackEntry)) {
            this.backQueue.removeLast();
            NavControllerNavigatorState navControllerNavigatorState = (NavControllerNavigatorState) this.navigatorState.get(this._navigatorProvider.getNavigator(navBackStackEntry2.destination.navigatorName));
            boolean z2 = true;
            if ((navControllerNavigatorState == null || (set = (Set) navControllerNavigatorState.transitionsInProgress.getValue()) == null || !set.contains(navBackStackEntry2)) && !this.parentToChildCount.containsKey(navBackStackEntry2)) {
                z2 = false;
            }
            if (navBackStackEntry2._lifecycle.state.isAtLeast(Lifecycle.State.CREATED)) {
                if (z) {
                    navBackStackEntry2.setMaxLifecycle(Lifecycle.State.CREATED);
                    arrayDeque.addFirst(new NavBackStackEntryState(navBackStackEntry2));
                }
                if (!z2) {
                    navBackStackEntry2.setMaxLifecycle(Lifecycle.State.DESTROYED);
                    unlinkChildFromParent$navigation_runtime_release$ar$ds(navBackStackEntry2);
                } else {
                    navBackStackEntry2.setMaxLifecycle(Lifecycle.State.CREATED);
                }
            }
            if (!z && !z2 && (navControllerViewModel = this.viewModel) != null) {
                navControllerViewModel.clear(navBackStackEntry2.id);
                return;
            }
            return;
        }
        throw new IllegalStateException("Attempted to pop " + navBackStackEntry.destination + ", which is not the top of the back stack (" + navBackStackEntry2.destination + ')');
    }

    public final List populateVisibleEntries$navigation_runtime_release() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.navigatorState.values().iterator();
        while (it.hasNext()) {
            Iterable iterable = (Iterable) ((NavControllerNavigatorState) it.next()).transitionsInProgress.getValue();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : iterable) {
                NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
                if (!arrayList.contains(navBackStackEntry) && !navBackStackEntry.maxLifecycle.isAtLeast(Lifecycle.State.STARTED)) {
                    arrayList2.add(obj);
                }
            }
            OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(arrayList, arrayList2);
        }
        ArrayDeque arrayDeque = this.backQueue;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayDeque) {
            NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) obj2;
            if (!arrayList.contains(navBackStackEntry2) && navBackStackEntry2.maxLifecycle.isAtLeast(Lifecycle.State.STARTED)) {
                arrayList3.add(obj2);
            }
        }
        OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(arrayList, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : arrayList) {
            if (!(((NavBackStackEntry) obj3).destination instanceof NavGraph)) {
                arrayList4.add(obj3);
            }
        }
        return arrayList4;
    }

    public final void removeOnDestinationChangedListener(OnDestinationChangedListener onDestinationChangedListener) {
        this.onDestinationChangedListeners.remove(onDestinationChangedListener);
    }

    public final void setGraph(int i) {
        setGraph(getNavInflater().inflate(i), null);
    }

    public final void unlinkChildFromParent$navigation_runtime_release$ar$ds(NavBackStackEntry navBackStackEntry) {
        Integer num;
        navBackStackEntry.getClass();
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) this.childToParentEntries.remove(navBackStackEntry);
        if (navBackStackEntry2 == null) {
            return;
        }
        AtomicInteger atomicInteger = (AtomicInteger) this.parentToChildCount.get(navBackStackEntry2);
        if (atomicInteger != null) {
            num = Integer.valueOf(atomicInteger.decrementAndGet());
        } else {
            num = null;
        }
        if (num != null && num.intValue() == 0) {
            NavControllerNavigatorState navControllerNavigatorState = (NavControllerNavigatorState) this.navigatorState.get(this._navigatorProvider.getNavigator(navBackStackEntry2.destination.navigatorName));
            if (navControllerNavigatorState != null) {
                navControllerNavigatorState.markTransitionComplete(navBackStackEntry2);
            }
            this.parentToChildCount.remove(navBackStackEntry2);
        }
    }

    public final void updateBackStackLifecycle$navigation_runtime_release() {
        AtomicInteger atomicInteger;
        Set set;
        List<NavBackStackEntry> mutableList = OnDeviceLanguageIdentificationLogEvent.toMutableList((Collection) this.backQueue);
        if (!mutableList.isEmpty()) {
            NavDestination navDestination = ((NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.last(mutableList)).destination;
            ArrayList arrayList = new ArrayList();
            if (navDestination instanceof DialogFragmentNavigator.Destination) {
                Iterator it = OnDeviceLanguageIdentificationLogEvent.reversed(mutableList).iterator();
                while (it.hasNext()) {
                    NavDestination navDestination2 = ((NavBackStackEntry) it.next()).destination;
                    arrayList.add(navDestination2);
                    if (!(navDestination2 instanceof DialogFragmentNavigator.Destination) && !(navDestination2 instanceof NavGraph)) {
                        break;
                    }
                }
            }
            HashMap hashMap = new HashMap();
            for (NavBackStackEntry navBackStackEntry : OnDeviceLanguageIdentificationLogEvent.reversed(mutableList)) {
                Lifecycle.State state = navBackStackEntry.maxLifecycle;
                NavDestination navDestination3 = navBackStackEntry.destination;
                if (navDestination != null && navDestination3.id == navDestination.id) {
                    if (state != Lifecycle.State.RESUMED) {
                        NavControllerNavigatorState navControllerNavigatorState = (NavControllerNavigatorState) this.navigatorState.get(this._navigatorProvider.getNavigator(navBackStackEntry.destination.navigatorName));
                        Boolean bool = null;
                        if (navControllerNavigatorState != null && (set = (Set) navControllerNavigatorState.transitionsInProgress.getValue()) != null) {
                            bool = Boolean.valueOf(set.contains(navBackStackEntry));
                        }
                        if (!Intrinsics.areEqual(bool, true) && ((atomicInteger = (AtomicInteger) this.parentToChildCount.get(navBackStackEntry)) == null || atomicInteger.get() != 0)) {
                            hashMap.put(navBackStackEntry, Lifecycle.State.RESUMED);
                        } else {
                            hashMap.put(navBackStackEntry, Lifecycle.State.STARTED);
                        }
                    }
                    NavDestination navDestination4 = (NavDestination) OnDeviceLanguageIdentificationLogEvent.firstOrNull(arrayList);
                    if (navDestination4 != null && navDestination4.id == navDestination3.id) {
                        OnDeviceLanguageIdentificationLogEvent.removeFirst(arrayList);
                    }
                    navDestination = navDestination.parent;
                } else if (!arrayList.isEmpty() && navDestination3.id == ((NavDestination) OnDeviceLanguageIdentificationLogEvent.first(arrayList)).id) {
                    NavDestination navDestination5 = (NavDestination) OnDeviceLanguageIdentificationLogEvent.removeFirst(arrayList);
                    if (state == Lifecycle.State.RESUMED) {
                        navBackStackEntry.setMaxLifecycle(Lifecycle.State.STARTED);
                    } else {
                        Lifecycle.State state2 = Lifecycle.State.STARTED;
                        if (state != state2) {
                            hashMap.put(navBackStackEntry, state2);
                        }
                    }
                    NavGraph navGraph = navDestination5.parent;
                    if (navGraph != null && !arrayList.contains(navGraph)) {
                        arrayList.add(navGraph);
                    }
                } else {
                    navBackStackEntry.setMaxLifecycle(Lifecycle.State.CREATED);
                }
            }
            for (NavBackStackEntry navBackStackEntry2 : mutableList) {
                Lifecycle.State state3 = (Lifecycle.State) hashMap.get(navBackStackEntry2);
                if (state3 != null) {
                    navBackStackEntry2.setMaxLifecycle(state3);
                } else {
                    navBackStackEntry2.updateState();
                }
            }
        }
    }

    public final void updateOnBackPressedCallbackEnabled() {
        boolean z = false;
        if (this.enableOnBackPressedCallback) {
            ArrayDeque arrayDeque = this.backQueue;
            if (!arrayDeque.isEmpty()) {
                Iterator<E> it = arrayDeque.iterator();
                int i = 0;
                while (it.hasNext()) {
                    if ((!(((NavBackStackEntry) it.next()).destination instanceof NavGraph)) && (i = i + 1) < 0) {
                        OnDeviceLanguageIdentificationLogEvent.throwCountOverflow();
                    }
                }
                if (i > 1) {
                    z = true;
                }
            }
        }
        this.onBackPressedCallback.setEnabled(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setGraph(androidx.navigation.NavGraph r14, android.os.Bundle r15) {
        /*
            Method dump skipped, instructions count: 1157
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.setGraph(androidx.navigation.NavGraph, android.os.Bundle):void");
    }

    public NavController(Context context, byte[] bArr) {
        this(context);
    }
}

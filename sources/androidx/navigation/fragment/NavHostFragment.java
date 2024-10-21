package androidx.navigation.fragment;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.BackStackRecord;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainerView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager$$ExternalSyntheticLambda0;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewConfigurationCompat$Api34Impl;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavBackStackEntryState;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.savedstate.SavedStateRegistry;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NavHostFragment extends Fragment {
    private boolean defaultNavHost;
    public int graphId;
    private final Lazy navHostController$delegate = new SynchronizedLazyImpl(new Function0() { // from class: androidx.navigation.fragment.NavHostFragment$navHostController$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            Lifecycle lifecycle;
            Context context = NavHostFragment.this.getContext();
            if (context != null) {
                Bundle bundle = null;
                final NavController navController = new NavController(context, null);
                NavHostFragment navHostFragment = NavHostFragment.this;
                if (!Intrinsics.areEqual(navHostFragment, navController.lifecycleOwner)) {
                    LifecycleOwner lifecycleOwner = navController.lifecycleOwner;
                    if (lifecycleOwner != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
                        lifecycle.removeObserver(navController.lifecycleObserver);
                    }
                    navController.lifecycleOwner = navHostFragment;
                    navHostFragment.getLifecycle().addObserver(navController.lifecycleObserver);
                }
                ViewModelStore viewModelStore = navHostFragment.getViewModelStore();
                viewModelStore.getClass();
                if (!Intrinsics.areEqual(navController.viewModel, ViewConfigurationCompat$Api34Impl.getInstance$ar$ds(viewModelStore))) {
                    if (navController.backQueue.isEmpty()) {
                        navController.viewModel = ViewConfigurationCompat$Api34Impl.getInstance$ar$ds(viewModelStore);
                    } else {
                        throw new IllegalStateException("ViewModelStore should be set before setGraph call");
                    }
                }
                NavigatorProvider navigatorProvider = navController._navigatorProvider;
                Context requireContext = navHostFragment.requireContext();
                FragmentManager childFragmentManager = navHostFragment.getChildFragmentManager();
                childFragmentManager.getClass();
                navigatorProvider.addNavigator$ar$ds(new DialogFragmentNavigator(requireContext, childFragmentManager));
                NavigatorProvider navigatorProvider2 = navController._navigatorProvider;
                Context requireContext2 = navHostFragment.requireContext();
                FragmentManager childFragmentManager2 = navHostFragment.getChildFragmentManager();
                childFragmentManager2.getClass();
                navigatorProvider2.addNavigator$ar$ds(new FragmentNavigator(requireContext2, childFragmentManager2, navHostFragment.getContainerId()));
                Bundle consumeRestoredStateForKey = navHostFragment.getSavedStateRegistry().consumeRestoredStateForKey("android-support-nav:fragment:navControllerState");
                int i = 0;
                if (consumeRestoredStateForKey != null) {
                    consumeRestoredStateForKey.setClassLoader(navController.context.getClassLoader());
                    navController.navigatorStateToRestore = consumeRestoredStateForKey.getBundle("android-support-nav:controller:navigatorState");
                    navController.backStackToRestore = consumeRestoredStateForKey.getParcelableArray("android-support-nav:controller:backStack");
                    navController.backStackStates.clear();
                    int[] intArray = consumeRestoredStateForKey.getIntArray("android-support-nav:controller:backStackDestIds");
                    ArrayList<String> stringArrayList = consumeRestoredStateForKey.getStringArrayList("android-support-nav:controller:backStackIds");
                    if (intArray != null && stringArrayList != null) {
                        int i2 = 0;
                        int i3 = 0;
                        while (i2 < intArray.length) {
                            navController.backStackMap.put(Integer.valueOf(intArray[i2]), stringArrayList.get(i3));
                            i2++;
                            i3++;
                        }
                    }
                    ArrayList<String> stringArrayList2 = consumeRestoredStateForKey.getStringArrayList("android-support-nav:controller:backStackStates");
                    if (stringArrayList2 != null) {
                        for (String str : stringArrayList2) {
                            Parcelable[] parcelableArray = consumeRestoredStateForKey.getParcelableArray("android-support-nav:controller:backStackStates:".concat(String.valueOf(str)));
                            if (parcelableArray != null) {
                                Map map = navController.backStackStates;
                                str.getClass();
                                ArrayDeque arrayDeque = new ArrayDeque(parcelableArray.length);
                                ArrayIterator arrayIterator = new ArrayIterator(parcelableArray);
                                while (arrayIterator.hasNext()) {
                                    Parcelable parcelable = (Parcelable) arrayIterator.next();
                                    parcelable.getClass();
                                    arrayDeque.add((NavBackStackEntryState) parcelable);
                                }
                                map.put(str, arrayDeque);
                            }
                        }
                    }
                    navController.deepLinkHandled = consumeRestoredStateForKey.getBoolean("android-support-nav:controller:deepLinkHandled");
                }
                navHostFragment.getSavedStateRegistry().registerSavedStateProvider("android-support-nav:fragment:navControllerState", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.navigation.fragment.NavHostFragment$navHostController$2$$ExternalSyntheticLambda0
                    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
                    public final Bundle saveState() {
                        Bundle bundle2;
                        ArrayList<String> arrayList = new ArrayList<>();
                        Bundle bundle3 = new Bundle();
                        NavController navController2 = NavController.this;
                        for (Map.Entry entry : navController2._navigatorProvider.getNavigators().entrySet()) {
                            String str2 = (String) entry.getKey();
                            Bundle onSaveState = ((Navigator) entry.getValue()).onSaveState();
                            if (onSaveState != null) {
                                arrayList.add(str2);
                                bundle3.putBundle(str2, onSaveState);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            bundle2 = new Bundle();
                            bundle3.putStringArrayList("android-support-nav:controller:navigatorState:names", arrayList);
                            bundle2.putBundle("android-support-nav:controller:navigatorState", bundle3);
                        } else {
                            bundle2 = null;
                        }
                        if (!navController2.backQueue.isEmpty()) {
                            if (bundle2 == null) {
                                bundle2 = new Bundle();
                            }
                            ArrayDeque arrayDeque2 = navController2.backQueue;
                            Parcelable[] parcelableArr = new Parcelable[arrayDeque2.size];
                            Iterator it = arrayDeque2.iterator();
                            int i4 = 0;
                            while (it.hasNext()) {
                                parcelableArr[i4] = new NavBackStackEntryState((NavBackStackEntry) it.next());
                                i4++;
                            }
                            bundle2.putParcelableArray("android-support-nav:controller:backStack", parcelableArr);
                        }
                        if (!navController2.backStackMap.isEmpty()) {
                            if (bundle2 == null) {
                                bundle2 = new Bundle();
                            }
                            int[] iArr = new int[navController2.backStackMap.size()];
                            ArrayList<String> arrayList2 = new ArrayList<>();
                            int i5 = 0;
                            for (Map.Entry entry2 : navController2.backStackMap.entrySet()) {
                                int intValue = ((Number) entry2.getKey()).intValue();
                                String str3 = (String) entry2.getValue();
                                iArr[i5] = intValue;
                                arrayList2.add(str3);
                                i5++;
                            }
                            bundle2.putIntArray("android-support-nav:controller:backStackDestIds", iArr);
                            bundle2.putStringArrayList("android-support-nav:controller:backStackIds", arrayList2);
                        }
                        if (!navController2.backStackStates.isEmpty()) {
                            if (bundle2 == null) {
                                bundle2 = new Bundle();
                            }
                            ArrayList<String> arrayList3 = new ArrayList<>();
                            for (Map.Entry entry3 : navController2.backStackStates.entrySet()) {
                                String str4 = (String) entry3.getKey();
                                ArrayDeque arrayDeque3 = (ArrayDeque) entry3.getValue();
                                arrayList3.add(str4);
                                Parcelable[] parcelableArr2 = new Parcelable[arrayDeque3.size];
                                int i6 = 0;
                                for (Object obj : arrayDeque3) {
                                    int i7 = i6 + 1;
                                    if (i6 < 0) {
                                        OnDeviceLanguageIdentificationLogEvent.throwIndexOverflow();
                                    }
                                    parcelableArr2[i6] = (NavBackStackEntryState) obj;
                                    i6 = i7;
                                }
                                bundle2.putParcelableArray("android-support-nav:controller:backStackStates:".concat(String.valueOf(str4)), parcelableArr2);
                            }
                            bundle2.putStringArrayList("android-support-nav:controller:backStackStates", arrayList3);
                        }
                        if (navController2.deepLinkHandled) {
                            if (bundle2 == null) {
                                bundle2 = new Bundle();
                            }
                            bundle2.putBoolean("android-support-nav:controller:deepLinkHandled", navController2.deepLinkHandled);
                        }
                        if (bundle2 == null) {
                            Bundle bundle4 = Bundle.EMPTY;
                            bundle4.getClass();
                            return bundle4;
                        }
                        return bundle2;
                    }
                });
                Bundle consumeRestoredStateForKey2 = navHostFragment.getSavedStateRegistry().consumeRestoredStateForKey("android-support-nav:fragment:graphId");
                if (consumeRestoredStateForKey2 != null) {
                    navHostFragment.graphId = consumeRestoredStateForKey2.getInt("android-support-nav:fragment:graphId");
                }
                navHostFragment.getSavedStateRegistry().registerSavedStateProvider("android-support-nav:fragment:graphId", new FragmentManager$$ExternalSyntheticLambda0(navHostFragment, 5));
                int i4 = navHostFragment.graphId;
                if (i4 != 0) {
                    navController.setGraph(i4);
                } else {
                    Bundle arguments = navHostFragment.getArguments();
                    if (arguments != null) {
                        i = arguments.getInt("android-support-nav:fragment:graphId");
                    }
                    if (arguments != null) {
                        bundle = arguments.getBundle("android-support-nav:fragment:startDestinationArgs");
                    }
                    if (i != 0) {
                        navController.setGraph(navController.getNavInflater().inflate(i), bundle);
                    }
                }
                return navController;
            }
            throw new IllegalStateException("NavController cannot be created before the fragment is attached");
        }
    });
    private View viewParent;

    public final int getContainerId() {
        int id = getId();
        if (id != 0 && id != -1) {
            return id;
        }
        return R.id.nav_host_fragment_container;
    }

    public final NavController getNavHostController$navigation_fragment_release$ar$class_merging() {
        return (NavController) this.navHostController$delegate.getValue();
    }

    @Override // android.support.v4.app.Fragment
    public final void onAttach(Context context) {
        context.getClass();
        super.onAttach(context);
        if (this.defaultNavHost) {
            new BackStackRecord(getParentFragmentManager()).setPrimaryNavigationFragment(this).commit();
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        getNavHostController$navigation_fragment_release$ar$class_merging();
        if (bundle != null && bundle.getBoolean("android-support-nav:fragment:defaultHost", false)) {
            this.defaultNavHost = true;
            new BackStackRecord(getParentFragmentManager()).setPrimaryNavigationFragment(this).commit();
        }
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater.getClass();
        Context context = layoutInflater.getContext();
        context.getClass();
        FragmentContainerView fragmentContainerView = new FragmentContainerView(context);
        fragmentContainerView.setId(getContainerId());
        return fragmentContainerView;
    }

    @Override // android.support.v4.app.Fragment
    public final void onDestroyView() {
        super.onDestroyView();
        View view = this.viewParent;
        if (view != null) {
            NavController findViewNavController$ar$ds = Navigation.findViewNavController$ar$ds(view);
            if (findViewNavController$ar$ds != null) {
                if (findViewNavController$ar$ds == getNavHostController$navigation_fragment_release$ar$class_merging()) {
                    Navigation.setViewNavController(view, null);
                }
            } else {
                throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(view, "View ", " does not have a NavController set"));
            }
        }
        this.viewParent = null;
    }

    @Override // android.support.v4.app.Fragment
    public final void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        context.getClass();
        attributeSet.getClass();
        super.onInflate(context, attributeSet, bundle);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.navigation.R$styleable.NavHost);
        obtainStyledAttributes.getClass();
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            this.graphId = resourceId;
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.NavHostFragment);
        obtainStyledAttributes2.getClass();
        if (obtainStyledAttributes2.getBoolean(0, false)) {
            this.defaultNavHost = true;
        }
        obtainStyledAttributes2.recycle();
    }

    @Override // android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        bundle.getClass();
        if (this.defaultNavHost) {
            bundle.putBoolean("android-support-nav:fragment:defaultHost", true);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        view.getClass();
        if (view instanceof ViewGroup) {
            Navigation.setViewNavController(view, getNavHostController$navigation_fragment_release$ar$class_merging());
            if (view.getParent() != null) {
                Object parent = view.getParent();
                parent.getClass();
                View view2 = (View) parent;
                this.viewParent = view2;
                view2.getClass();
                if (view2.getId() == getId()) {
                    View view3 = this.viewParent;
                    view3.getClass();
                    Navigation.setViewNavController(view3, getNavHostController$navigation_fragment_release$ar$class_merging());
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(view, "created host view ", " is not a ViewGroup"));
    }
}

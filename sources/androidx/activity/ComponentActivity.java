package androidx.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager$$ExternalSyntheticLambda0;
import android.support.v4.app.OnMultiWindowModeChangedProvider;
import android.support.v4.app.OnPictureInPictureModeChangedProvider;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatTextHelper;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.PointerIconCompat$Api24Impl;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.DialogFragmentNavigator;
import androidx.navigation.fragment.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0;
import androidx.preference.PreferenceDataStore;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.libraries.phenotype.client.lockdown.FlagExemptionsReader;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ComponentActivity extends SupportActivity implements LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, OnConfigurationChangedProvider, OnTrimMemoryProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, MenuHost {
    private ViewModelStore _viewModelStore;
    public final ActivityResultRegistry activityResultRegistry;
    private final Lazy defaultViewModelProviderFactory$delegate;
    private boolean dispatchingOnMultiWindowModeChanged;
    private boolean dispatchingOnPictureInPictureModeChanged;
    private final Lazy fullyDrawnReporter$delegate;
    private final Lazy onBackPressedDispatcher$delegate;
    private final CopyOnWriteArrayList onConfigurationChangedListeners;
    private final CopyOnWriteArrayList onMultiWindowModeChangedListeners;
    public final CopyOnWriteArrayList onNewIntentListeners;
    private final CopyOnWriteArrayList onPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList onTrimMemoryListeners;
    private final CopyOnWriteArrayList onUserLeaveHintListeners;
    public final ReportFullyDrawnExecutorImpl reportFullyDrawnExecutor$ar$class_merging;
    private final SavedStateRegistryController savedStateRegistryController;
    public final ContextAwareHelper contextAwareHelper = new ContextAwareHelper();
    private final OptionalMethod menuHostHelper$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod(new SearchView$SearchAutoComplete.AnonymousClass1(this, 5, null));

    /* compiled from: PG */
    /* renamed from: androidx.activity.ComponentActivity$4 */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 implements LifecycleEventObserver {
        final /* synthetic */ Object ComponentActivity$4$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass4(Object obj, int i) {
            this.switching_field = i;
            this.ComponentActivity$4$ar$this$0 = obj;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            View view;
            int i;
            int i2 = this.switching_field;
            if (i2 != 0) {
                if (i2 != 1) {
                    int ordinal = event.ordinal();
                    if (ordinal != 0) {
                        Object obj = null;
                        if (ordinal != 2) {
                            if (ordinal != 4) {
                                if (ordinal == 5) {
                                    DialogFragment dialogFragment = (DialogFragment) lifecycleOwner;
                                    for (Object obj2 : (Iterable) ((Navigator) this.ComponentActivity$4$ar$this$0).getState().transitionsInProgress.getValue()) {
                                        if (Intrinsics.areEqual(((NavBackStackEntry) obj2).id, dialogFragment.getTag())) {
                                            obj = obj2;
                                        }
                                    }
                                    NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
                                    if (navBackStackEntry != null) {
                                        ((Navigator) this.ComponentActivity$4$ar$this$0).getState().markTransitionComplete(navBackStackEntry);
                                    }
                                    dialogFragment.getLifecycle().removeObserver(this);
                                    return;
                                }
                                return;
                            }
                            DialogFragment dialogFragment2 = (DialogFragment) lifecycleOwner;
                            if (!dialogFragment2.requireDialog().isShowing()) {
                                List list = (List) ((Navigator) this.ComponentActivity$4$ar$this$0).getState().backStack.getValue();
                                ListIterator listIterator = list.listIterator(list.size());
                                while (true) {
                                    if (listIterator.hasPrevious()) {
                                        if (Intrinsics.areEqual(((NavBackStackEntry) listIterator.previous()).id, dialogFragment2.getTag())) {
                                            i = listIterator.nextIndex();
                                            break;
                                        }
                                    } else {
                                        i = -1;
                                        break;
                                    }
                                }
                                NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) OnDeviceLanguageIdentificationLogEvent.getOrNull(list, i);
                                if (!Intrinsics.areEqual(OnDeviceLanguageIdentificationLogEvent.lastOrNull(list), navBackStackEntry2)) {
                                    Objects.toString(dialogFragment2);
                                }
                                if (navBackStackEntry2 != null) {
                                    ((DialogFragmentNavigator) this.ComponentActivity$4$ar$this$0).popWithTransition(i, navBackStackEntry2, false);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        DialogFragment dialogFragment3 = (DialogFragment) lifecycleOwner;
                        for (Object obj3 : (Iterable) ((Navigator) this.ComponentActivity$4$ar$this$0).getState().transitionsInProgress.getValue()) {
                            if (Intrinsics.areEqual(((NavBackStackEntry) obj3).id, dialogFragment3.getTag())) {
                                obj = obj3;
                            }
                        }
                        NavBackStackEntry navBackStackEntry3 = (NavBackStackEntry) obj;
                        if (navBackStackEntry3 != null) {
                            ((Navigator) this.ComponentActivity$4$ar$this$0).getState().markTransitionComplete(navBackStackEntry3);
                            return;
                        }
                        return;
                    }
                    DialogFragment dialogFragment4 = (DialogFragment) lifecycleOwner;
                    Iterable iterable = (Iterable) ((Navigator) this.ComponentActivity$4$ar$this$0).getState().backStack.getValue();
                    if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                        Iterator it = iterable.iterator();
                        while (it.hasNext()) {
                            if (Intrinsics.areEqual(((NavBackStackEntry) it.next()).id, dialogFragment4.getTag())) {
                                return;
                            }
                        }
                    }
                    dialogFragment4.dismiss();
                    return;
                }
                if (event == Lifecycle.Event.ON_STOP && (view = ((Fragment) this.ComponentActivity$4$ar$this$0).mView) != null) {
                    view.cancelPendingInputEvents();
                    return;
                }
                return;
            }
            ((ComponentActivity) this.ComponentActivity$4$ar$this$0).ensureViewModelStore();
            ((SupportActivity) this.ComponentActivity$4$ar$this$0).getLifecycle().removeObserver(this);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        public final OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity activity) {
            activity.getClass();
            OnBackInvokedDispatcher onBackInvokedDispatcher = activity.getOnBackInvokedDispatcher();
            onBackInvokedDispatcher.getClass();
            return onBackInvokedDispatcher;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ReportFullyDrawnExecutorImpl implements ViewTreeObserver.OnDrawListener, Runnable, Executor {
        public Runnable currentRunnable;
        private final long endWatchTimeMillis = SystemClock.uptimeMillis() + 10000;
        private boolean onDrawScheduled;

        public ReportFullyDrawnExecutorImpl() {
        }

        public final void activityDestroyed() {
            ComponentActivity.this.getWindow().getDecorView().removeCallbacks(this);
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            runnable.getClass();
            this.currentRunnable = runnable;
            View decorView = ComponentActivity.this.getWindow().getDecorView();
            decorView.getClass();
            if (this.onDrawScheduled) {
                if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                    decorView.invalidate();
                    return;
                } else {
                    decorView.postInvalidate();
                    return;
                }
            }
            decorView.postOnAnimation(new SearchView$SearchAutoComplete.AnonymousClass1(this, 6, null));
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public final void onDraw() {
            boolean z;
            Runnable runnable = this.currentRunnable;
            if (runnable != null) {
                runnable.run();
                this.currentRunnable = null;
                TaskQueue fullyDrawnReporter$ar$class_merging$ar$class_merging = ComponentActivity.this.getFullyDrawnReporter$ar$class_merging$ar$class_merging();
                synchronized (fullyDrawnReporter$ar$class_merging$ar$class_merging.TaskQueue$ar$pendingTasks) {
                    z = fullyDrawnReporter$ar$class_merging$ar$class_merging.isActive;
                }
                if (z) {
                    this.onDrawScheduled = false;
                    ComponentActivity.this.getWindow().getDecorView().post(this);
                    return;
                }
                return;
            }
            if (SystemClock.uptimeMillis() > this.endWatchTimeMillis) {
                this.onDrawScheduled = false;
                ComponentActivity.this.getWindow().getDecorView().post(this);
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        public final void viewCreated(View view) {
            if (!this.onDrawScheduled) {
                this.onDrawScheduled = true;
                view.getViewTreeObserver().addOnDrawListener(this);
            }
        }
    }

    public ComponentActivity() {
        SavedStateRegistryController savedStateRegistryController = new SavedStateRegistryController(this);
        this.savedStateRegistryController = savedStateRegistryController;
        this.reportFullyDrawnExecutor$ar$class_merging = new ReportFullyDrawnExecutorImpl();
        this.fullyDrawnReporter$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 2));
        new AtomicInteger();
        this.activityResultRegistry = new ActivityResultRegistry(this);
        this.onConfigurationChangedListeners = new CopyOnWriteArrayList();
        this.onTrimMemoryListeners = new CopyOnWriteArrayList();
        this.onNewIntentListeners = new CopyOnWriteArrayList();
        this.onMultiWindowModeChangedListeners = new CopyOnWriteArrayList();
        this.onPictureInPictureModeChangedListeners = new CopyOnWriteArrayList();
        this.onUserLeaveHintListeners = new CopyOnWriteArrayList();
        if (getLifecycle() != null) {
            getLifecycle().addObserver(new ComponentActivity$$ExternalSyntheticLambda2(this, 1));
            getLifecycle().addObserver(new ComponentActivity$$ExternalSyntheticLambda2(this, 0));
            getLifecycle().addObserver(new AnonymousClass4(this, 0));
            savedStateRegistryController.performAttach();
            SavedStateHandleSupport.enableSavedStateHandles(this);
            getSavedStateRegistry().registerSavedStateProvider("android:support:activity-result", new FragmentManager$$ExternalSyntheticLambda0(this, 3));
            addOnContextAvailableListener(new ComponentActivity$$ExternalSyntheticLambda4(this, 0));
            this.defaultViewModelProviderFactory$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 1));
            this.onBackPressedDispatcher$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 3));
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    public static final void _init_$lambda$5$ar$ds(ComponentActivity componentActivity) {
        Bundle consumeRestoredStateForKey = componentActivity.getSavedStateRegistry().consumeRestoredStateForKey("android:support:activity-result");
        if (consumeRestoredStateForKey != null) {
            ActivityResultRegistry activityResultRegistry = componentActivity.activityResultRegistry;
            ArrayList<Integer> integerArrayList = consumeRestoredStateForKey.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = consumeRestoredStateForKey.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList != null && integerArrayList != null) {
                ArrayList<String> stringArrayList2 = consumeRestoredStateForKey.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                if (stringArrayList2 != null) {
                    activityResultRegistry.launchedKeys.addAll(stringArrayList2);
                }
                Bundle bundle = consumeRestoredStateForKey.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
                if (bundle != null) {
                    activityResultRegistry.pendingResults.putAll(bundle);
                }
                int size = stringArrayList.size();
                for (int i = 0; i < size; i++) {
                    String str = stringArrayList.get(i);
                    if (activityResultRegistry.keyToRc.containsKey(str)) {
                        Integer num = (Integer) activityResultRegistry.keyToRc.remove(str);
                        if (!activityResultRegistry.pendingResults.containsKey(str)) {
                            TypeIntrinsics.asMutableMap(activityResultRegistry.rcToKey).remove(num);
                        }
                    }
                    Integer num2 = integerArrayList.get(i);
                    num2.getClass();
                    int intValue = num2.intValue();
                    String str2 = stringArrayList.get(i);
                    str2.getClass();
                    activityResultRegistry.bindRcKey(intValue, str2);
                }
            }
        }
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        decorView.getClass();
        this.reportFullyDrawnExecutor$ar$class_merging.viewCreated(decorView);
        super.addContentView(view, layoutParams);
    }

    @Override // androidx.core.view.MenuHost
    public final void addMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        shadowDelegateImpl.getClass();
        this.menuHostHelper$ar$class_merging$ar$class_merging$ar$class_merging.addMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(shadowDelegateImpl);
    }

    public final void addObserverForBackInvoker(OnBackPressedDispatcher onBackPressedDispatcher) {
        getLifecycle().addObserver(new FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0(onBackPressedDispatcher, this, 1));
    }

    @Override // androidx.core.content.OnConfigurationChangedProvider
    public final void addOnConfigurationChangedListener(Consumer consumer) {
        consumer.getClass();
        this.onConfigurationChangedListeners.add(consumer);
    }

    public final void addOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener) {
        ContextAwareHelper contextAwareHelper = this.contextAwareHelper;
        Context context = contextAwareHelper.context;
        if (context != null) {
            onContextAvailableListener.onContextAvailable(context);
        }
        contextAwareHelper.listeners.add(onContextAvailableListener);
    }

    @Override // android.support.v4.app.OnMultiWindowModeChangedProvider
    public final void addOnMultiWindowModeChangedListener(Consumer consumer) {
        consumer.getClass();
        this.onMultiWindowModeChangedListeners.add(consumer);
    }

    @Override // android.support.v4.app.OnPictureInPictureModeChangedProvider
    public final void addOnPictureInPictureModeChangedListener(Consumer consumer) {
        consumer.getClass();
        this.onPictureInPictureModeChangedListeners.add(consumer);
    }

    @Override // androidx.core.content.OnTrimMemoryProvider
    public final void addOnTrimMemoryListener(Consumer consumer) {
        consumer.getClass();
        this.onTrimMemoryListeners.add(consumer);
    }

    public final void ensureViewModelStore() {
        if (this._viewModelStore == null) {
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = (OnDeviceTextDetectionLoadLogEvent) getLastNonConfigurationInstance();
            if (onDeviceTextDetectionLoadLogEvent != null) {
                this._viewModelStore = (ViewModelStore) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
            }
            if (this._viewModelStore == null) {
                this._viewModelStore = new ViewModelStore();
            }
        }
    }

    @Override // androidx.activity.result.ActivityResultRegistryOwner
    public final ActivityResultRegistry getActivityResultRegistry() {
        return this.activityResultRegistry;
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public final CreationExtras getDefaultViewModelCreationExtras() {
        Bundle bundle = null;
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras((byte[]) null);
        if (getApplication() != null) {
            CreationExtras.Key key = ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;
            Application application = getApplication();
            application.getClass();
            mutableCreationExtras.set(key, application);
        }
        mutableCreationExtras.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        mutableCreationExtras.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        }
        if (bundle != null) {
            mutableCreationExtras.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, bundle);
        }
        return mutableCreationExtras;
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public final ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return (ViewModelProvider.Factory) this.defaultViewModelProviderFactory$delegate.getValue();
    }

    public final TaskQueue getFullyDrawnReporter$ar$class_merging$ar$class_merging() {
        return (TaskQueue) this.fullyDrawnReporter$delegate.getValue();
    }

    @Override // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return (OnBackPressedDispatcher) this.onBackPressedDispatcher$delegate.getValue();
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistryController.savedStateRegistry;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public final ViewModelStore getViewModelStore() {
        if (getApplication() != null) {
            ensureViewModelStore();
            ViewModelStore viewModelStore = this._viewModelStore;
            viewModelStore.getClass();
            return viewModelStore;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    public final void initializeViewTreeOwners() {
        View decorView = getWindow().getDecorView();
        decorView.getClass();
        ViewCompat.Api24Impl.set(decorView, this);
        View decorView2 = getWindow().getDecorView();
        decorView2.getClass();
        ViewCompat.Api26Impl.set(decorView2, this);
        View decorView3 = getWindow().getDecorView();
        decorView3.getClass();
        PreferenceDataStore.set(decorView3, this);
        View decorView4 = getWindow().getDecorView();
        decorView4.getClass();
        AppCompatTextHelper.Api24Impl.set(decorView4, this);
        View decorView5 = getWindow().getDecorView();
        decorView5.getClass();
        decorView5.setTag(R.id.report_drawn, this);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.activityResultRegistry.dispatchResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        configuration.getClass();
        super.onConfigurationChanged(configuration);
        Iterator it = this.onConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(configuration);
        }
    }

    @Override // android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.savedStateRegistryController.performRestore(bundle);
        ContextAwareHelper contextAwareHelper = this.contextAwareHelper;
        contextAwareHelper.context = this;
        Iterator it = contextAwareHelper.listeners.iterator();
        while (it.hasNext()) {
            ((OnContextAvailableListener) it.next()).onContextAvailable(this);
        }
        super.onCreate(bundle);
        PointerIconCompat$Api24Impl pointerIconCompat$Api24Impl = ReportFragment.Companion$ar$class_merging$868eb497_0;
        PointerIconCompat$Api24Impl.injectIfNeededIn$ar$ds(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        menu.getClass();
        if (i == 0) {
            super.onCreatePanelMenu(0, menu);
            this.menuHostHelper$ar$class_merging$ar$class_merging$ar$class_merging.onCreateMenu(menu, getMenuInflater());
            return true;
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        menuItem.getClass();
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.menuHostHelper$ar$class_merging$ar$class_merging$ar$class_merging.onMenuItemSelected(menuItem);
        }
        return false;
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z) {
        if (this.dispatchingOnMultiWindowModeChanged) {
            return;
        }
        Iterator it = this.onMultiWindowModeChangedListeners.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(new FlagExemptionsReader(z));
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        intent.getClass();
        super.onNewIntent(intent);
        Iterator it = this.onNewIntentListeners.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onPanelClosed(int i, Menu menu) {
        menu.getClass();
        Iterator it = ((CopyOnWriteArrayList) this.menuHostHelper$ar$class_merging$ar$class_merging$ar$class_merging.OptionalMethod$ar$methodName).iterator();
        while (it.hasNext()) {
            ((FloatingActionButton.ShadowDelegateImpl) it.next()).onMenuClosed(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z) {
        if (this.dispatchingOnPictureInPictureModeChanged) {
            return;
        }
        Iterator it = this.onPictureInPictureModeChangedListeners.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(new FlagExemptionsReader(z));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        menu.getClass();
        if (i == 0) {
            super.onPreparePanel(0, view, menu);
            this.menuHostHelper$ar$class_merging$ar$class_merging$ar$class_merging.onPrepareMenu(menu);
            return true;
        }
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        strArr.getClass();
        iArr.getClass();
        if (!this.activityResultRegistry.dispatchResult(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent;
        Object obj = this._viewModelStore;
        if (obj == null && (onDeviceTextDetectionLoadLogEvent = (OnDeviceTextDetectionLoadLogEvent) getLastNonConfigurationInstance()) != null) {
            obj = onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        }
        if (obj == null) {
            return null;
        }
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent2 = new OnDeviceTextDetectionLoadLogEvent();
        onDeviceTextDetectionLoadLogEvent2.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = obj;
        return onDeviceTextDetectionLoadLogEvent2;
    }

    @Override // android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.getClass();
        if (getLifecycle() instanceof LifecycleRegistry) {
            Lifecycle lifecycle = getLifecycle();
            lifecycle.getClass();
            ((LifecycleRegistry) lifecycle).setCurrentState(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.savedStateRegistryController.performSave(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Iterator it = this.onTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(Integer.valueOf(i));
        }
    }

    @Override // android.app.Activity
    protected final void onUserLeaveHint() {
        super.onUserLeaveHint();
        Iterator it = this.onUserLeaveHintListeners.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    @Override // androidx.core.view.MenuHost
    public final void removeMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        shadowDelegateImpl.getClass();
        this.menuHostHelper$ar$class_merging$ar$class_merging$ar$class_merging.removeMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(shadowDelegateImpl);
    }

    @Override // androidx.core.content.OnConfigurationChangedProvider
    public final void removeOnConfigurationChangedListener(Consumer consumer) {
        consumer.getClass();
        this.onConfigurationChangedListeners.remove(consumer);
    }

    @Override // android.support.v4.app.OnMultiWindowModeChangedProvider
    public final void removeOnMultiWindowModeChangedListener(Consumer consumer) {
        consumer.getClass();
        this.onMultiWindowModeChangedListeners.remove(consumer);
    }

    @Override // android.support.v4.app.OnPictureInPictureModeChangedProvider
    public final void removeOnPictureInPictureModeChangedListener(Consumer consumer) {
        consumer.getClass();
        this.onPictureInPictureModeChangedListeners.remove(consumer);
    }

    @Override // androidx.core.content.OnTrimMemoryProvider
    public final void removeOnTrimMemoryListener(Consumer consumer) {
        consumer.getClass();
        this.onTrimMemoryListeners.remove(consumer);
    }

    @Override // android.app.Activity
    public final void reportFullyDrawn() {
        try {
            if (AppCompatDelegateImpl.Api21Impl.isEnabled()) {
                AppCompatDelegateImpl.Api21Impl.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            getFullyDrawnReporter$ar$class_merging$ar$class_merging().fullyDrawnReported();
        } finally {
            Trace.endSection();
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        decorView.getClass();
        this.reportFullyDrawnExecutor$ar$class_merging.viewCreated(decorView);
        super.setContentView(i);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i) {
        intent.getClass();
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) {
        intentSender.getClass();
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        configuration.getClass();
        this.dispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.dispatchingOnMultiWindowModeChanged = false;
            Iterator it = this.onMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(new FlagExemptionsReader(z));
            }
        } catch (Throwable th) {
            this.dispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        configuration.getClass();
        this.dispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.dispatchingOnPictureInPictureModeChanged = false;
            Iterator it = this.onPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(new FlagExemptionsReader(z));
            }
        } catch (Throwable th) {
            this.dispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        intent.getClass();
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        intentSender.getClass();
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        decorView.getClass();
        this.reportFullyDrawnExecutor$ar$class_merging.viewCreated(decorView);
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        decorView.getClass();
        this.reportFullyDrawnExecutor$ar$class_merging.viewCreated(decorView);
        super.setContentView(view, layoutParams);
    }
}

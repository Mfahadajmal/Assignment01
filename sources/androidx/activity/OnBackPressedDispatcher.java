package androidx.activity;

import android.accessibilityservice.AccessibilityService;
import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar$Api33Impl$$ExternalSyntheticLambda0;
import android.view.View;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLink;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0;
import androidx.room.DatabaseConfiguration;
import androidx.room.RoomConnectionManager;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.model.WorkSpecDao_Impl;
import com.google.android.accessibility.selecttospeak.logging.S2sHaTsActivity;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlaysAnimations;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {
    private boolean backInvokedCallbackRegistered;
    private final Runnable fallbackOnBackPressed;
    private boolean hasEnabledCallbacks;
    public OnBackPressedCallback inProgressCallback;
    private OnBackInvokedDispatcher invokedDispatcher;
    private OnBackInvokedCallback onBackInvokedCallback;
    public final ArrayDeque onBackPressedCallbacks;

    /* compiled from: PG */
    /* renamed from: androidx.activity.OnBackPressedDispatcher$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends Lambda implements Function1 {
        final /* synthetic */ Object OnBackPressedDispatcher$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Object obj, int i) {
            super(1);
            this.switching_field = i;
            this.OnBackPressedDispatcher$1$ar$this$0 = obj;
        }

        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Iterable] */
        /* JADX WARN: Type inference failed for: r5v14, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Object invoke(Object obj) {
            int i = 0;
            Object obj2 = null;
            switch (this.switching_field) {
                case 0:
                    ((BackEventCompat) obj).getClass();
                    OnBackPressedDispatcher onBackPressedDispatcher = (OnBackPressedDispatcher) this.OnBackPressedDispatcher$1$ar$this$0;
                    ArrayDeque arrayDeque = onBackPressedDispatcher.onBackPressedCallbacks;
                    ListIterator<E> listIterator = arrayDeque.listIterator(arrayDeque.size);
                    while (true) {
                        if (listIterator.hasPrevious()) {
                            Object previous = listIterator.previous();
                            if (((OnBackPressedCallback) previous).isEnabled) {
                                obj2 = previous;
                            }
                        }
                    }
                    OnBackPressedCallback onBackPressedCallback = (OnBackPressedCallback) obj2;
                    if (onBackPressedDispatcher.inProgressCallback != null) {
                        onBackPressedDispatcher.onBackCancelled();
                    }
                    onBackPressedDispatcher.inProgressCallback = onBackPressedCallback;
                    if (onBackPressedCallback != null) {
                        onBackPressedCallback.handleOnBackStarted$ar$ds();
                    }
                    return Unit.INSTANCE;
                case 1:
                    Map.Entry entry = (Map.Entry) obj;
                    entry.getClass();
                    return Boolean.valueOf(OnDeviceLanguageIdentificationLogEvent.contains(this.OnBackPressedDispatcher$1$ar$this$0, ViewCompat.Api21Impl.getTransitionName((View) entry.getValue())));
                case 2:
                    BackEventCompat backEventCompat = (BackEventCompat) obj;
                    backEventCompat.getClass();
                    OnBackPressedDispatcher onBackPressedDispatcher2 = (OnBackPressedDispatcher) this.OnBackPressedDispatcher$1$ar$this$0;
                    OnBackPressedCallback onBackPressedCallback2 = onBackPressedDispatcher2.inProgressCallback;
                    if (onBackPressedCallback2 == null) {
                        ArrayDeque arrayDeque2 = onBackPressedDispatcher2.onBackPressedCallbacks;
                        ListIterator<E> listIterator2 = arrayDeque2.listIterator(arrayDeque2.size);
                        while (true) {
                            if (listIterator2.hasPrevious()) {
                                Object previous2 = listIterator2.previous();
                                if (((OnBackPressedCallback) previous2).isEnabled) {
                                    obj2 = previous2;
                                }
                            }
                        }
                        onBackPressedCallback2 = (OnBackPressedCallback) obj2;
                    }
                    if (onBackPressedCallback2 != null) {
                        onBackPressedCallback2.handleOnBackProgressed(backEventCompat);
                    }
                    return Unit.INSTANCE;
                case 3:
                    this.OnBackPressedDispatcher$1$ar$this$0.cancel(false);
                    return Unit.INSTANCE;
                case 4:
                    ((NavDestination) obj).getClass();
                    return Boolean.valueOf(!((NavController) this.OnBackPressedDispatcher$1$ar$this$0).backStackMap.containsKey(Integer.valueOf(r5.id)));
                case 5:
                    ((NavDestination) obj).getClass();
                    return Boolean.valueOf(!((NavController) this.OnBackPressedDispatcher$1$ar$this$0).backStackMap.containsKey(Integer.valueOf(r5.id)));
                case 6:
                    return Boolean.valueOf(Intrinsics.areEqual((String) obj, this.OnBackPressedDispatcher$1$ar$this$0));
                case 7:
                    ((String) obj).getClass();
                    return Boolean.valueOf(!((Bundle) this.OnBackPressedDispatcher$1$ar$this$0).containsKey(r5));
                case 8:
                    ((String) obj).getClass();
                    Collection values = ((NavDeepLink) this.OnBackPressedDispatcher$1$ar$this$0).getQueryArgsMap().values();
                    ArrayList arrayList = new ArrayList();
                    Iterator it = values.iterator();
                    while (it.hasNext()) {
                        OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(arrayList, ((NavDeepLink.ParamQuery) it.next()).arguments);
                    }
                    return Boolean.valueOf(!OnDeviceLanguageIdentificationLogEvent.plus((Collection) OnDeviceLanguageIdentificationLogEvent.plus((Collection) r0.pathArgs, (Iterable) arrayList), (Iterable) r0.getFragArgs()).contains(r5));
                case 9:
                    ((String) obj).getClass();
                    return Boolean.valueOf(!((Bundle) this.OnBackPressedDispatcher$1$ar$this$0).containsKey(r5));
                case 10:
                    Pair pair = (Pair) obj;
                    pair.getClass();
                    return Boolean.valueOf(Intrinsics.areEqual(pair.first, this.OnBackPressedDispatcher$1$ar$this$0));
                case 11:
                    NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
                    navBackStackEntry.getClass();
                    return new FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0(this.OnBackPressedDispatcher$1$ar$this$0, navBackStackEntry, i);
                case 12:
                    ((ViewModelStore) obj).getClass();
                    return ((WorkSpecDao_Impl.AnonymousClass25) this.OnBackPressedDispatcher$1$ar$this$0).call();
                case 13:
                    SupportSQLiteDatabase supportSQLiteDatabase = (SupportSQLiteDatabase) obj;
                    supportSQLiteDatabase.getClass();
                    ((RoomConnectionManager) this.OnBackPressedDispatcher$1$ar$this$0).supportDatabase = supportSQLiteDatabase;
                    return Unit.INSTANCE;
                case 14:
                    DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) obj;
                    databaseConfiguration.getClass();
                    return ((RoomDatabase) this.OnBackPressedDispatcher$1$ar$this$0).createOpenHelper(databaseConfiguration);
                case 15:
                    ((CancellableContinuationImpl) this.OnBackPressedDispatcher$1$ar$this$0).resume((MultiFlavorDetectorCreator) obj, null);
                    return Unit.INSTANCE;
                case 16:
                    ScreenCaptureController.CaptureListener captureListener = (ScreenCaptureController.CaptureListener) obj;
                    captureListener.getClass();
                    SpannableUtils$NonCopyableTextSpan.takeScreenshot((AccessibilityService) this.OnBackPressedDispatcher$1$ar$this$0, captureListener);
                    return Unit.INSTANCE;
                case 17:
                    ((Boolean) obj).booleanValue();
                    ((S2sHaTsActivity) this.OnBackPressedDispatcher$1$ar$this$0).cleanUpAndFinish();
                    return Unit.INSTANCE;
                case 18:
                    ((Animator) this.OnBackPressedDispatcher$1$ar$this$0).cancel();
                    return Unit.INSTANCE;
                case 19:
                    if (!((Boolean) obj).booleanValue()) {
                        ((ControlOverlays) this.OnBackPressedDispatcher$1$ar$this$0).collapse$ar$ds();
                    }
                    return Unit.INSTANCE;
                default:
                    ((Boolean) obj).booleanValue();
                    ((ControlOverlaysAnimations) this.OnBackPressedDispatcher$1$ar$this$0).analytics.onUiReady();
                    return Unit.INSTANCE;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        public final OnBackInvokedCallback createOnBackInvokedCallback(Function0<Unit> function0) {
            function0.getClass();
            return new Toolbar$Api33Impl$$ExternalSyntheticLambda0(function0, 2);
        }

        public final void registerOnBackInvokedCallback(Object obj, int i, Object obj2) {
            obj.getClass();
            obj2.getClass();
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i, (OnBackInvokedCallback) obj2);
        }

        public final void unregisterOnBackInvokedCallback(Object obj, Object obj2) {
            obj.getClass();
            obj2.getClass();
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        public final OnBackInvokedCallback createOnBackAnimationCallback(final Function1<? super BackEventCompat, Unit> function1, final Function1<? super BackEventCompat, Unit> function12, final Function0<Unit> function0, final Function0<Unit> function02) {
            function1.getClass();
            function12.getClass();
            function0.getClass();
            function02.getClass();
            return new OnBackAnimationCallback() { // from class: androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1
                public final void onBackCancelled() {
                    function02.invoke();
                }

                public final void onBackInvoked() {
                    function0.invoke();
                }

                public final void onBackProgressed(BackEvent backEvent) {
                    backEvent.getClass();
                    function12.invoke(new BackEventCompat(backEvent));
                }

                public final void onBackStarted(BackEvent backEvent) {
                    backEvent.getClass();
                    Function1.this.invoke(new BackEventCompat(backEvent));
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LifecycleOnBackPressedCancellable implements LifecycleEventObserver, Cancellable {
        private Cancellable currentCancellable;
        private final Lifecycle lifecycle;
        private final OnBackPressedCallback onBackPressedCallback;
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public LifecycleOnBackPressedCancellable(OnBackPressedDispatcher onBackPressedDispatcher, Lifecycle lifecycle, OnBackPressedCallback onBackPressedCallback) {
            lifecycle.getClass();
            this.this$0 = onBackPressedDispatcher;
            this.lifecycle = lifecycle;
            this.onBackPressedCallback = onBackPressedCallback;
            lifecycle.addObserver(this);
        }

        @Override // androidx.activity.Cancellable
        public final void cancel() {
            this.lifecycle.removeObserver(this);
            this.onBackPressedCallback.removeCancellable(this);
            Cancellable cancellable = this.currentCancellable;
            if (cancellable != null) {
                cancellable.cancel();
            }
            this.currentCancellable = null;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                OnBackPressedDispatcher onBackPressedDispatcher = this.this$0;
                OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
                onBackPressedDispatcher.onBackPressedCallbacks.add(onBackPressedCallback);
                OnBackPressedCancellable onBackPressedCancellable = new OnBackPressedCancellable(onBackPressedCallback);
                onBackPressedCallback.addCancellable(onBackPressedCancellable);
                onBackPressedDispatcher.updateEnabledCallbacks();
                onBackPressedCallback.enabledChangedCallback = new OnBackPressedDispatcher$addCancellableCallback$1(onBackPressedDispatcher, 0);
                this.currentCancellable = onBackPressedCancellable;
                return;
            }
            if (event == Lifecycle.Event.ON_STOP) {
                Cancellable cancellable = this.currentCancellable;
                if (cancellable != null) {
                    cancellable.cancel();
                    return;
                }
                return;
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                cancel();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OnBackPressedCancellable implements Cancellable {
        private final OnBackPressedCallback onBackPressedCallback;

        public OnBackPressedCancellable(OnBackPressedCallback onBackPressedCallback) {
            this.onBackPressedCallback = onBackPressedCallback;
        }

        @Override // androidx.activity.Cancellable
        public final void cancel() {
            OnBackPressedDispatcher.this.onBackPressedCallbacks.remove(this.onBackPressedCallback);
            if (Intrinsics.areEqual(OnBackPressedDispatcher.this.inProgressCallback, this.onBackPressedCallback)) {
                this.onBackPressedCallback.handleOnBackCancelled();
                OnBackPressedDispatcher.this.inProgressCallback = null;
            }
            this.onBackPressedCallback.removeCancellable(this);
            Function0 function0 = this.onBackPressedCallback.enabledChangedCallback;
            if (function0 != null) {
                function0.invoke();
            }
            this.onBackPressedCallback.enabledChangedCallback = null;
        }
    }

    public OnBackPressedDispatcher() {
        this(null);
    }

    private final void updateBackInvokedCallbackState(boolean z) {
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.invokedDispatcher;
        OnBackInvokedCallback onBackInvokedCallback = this.onBackInvokedCallback;
        if (onBackInvokedDispatcher != null && onBackInvokedCallback != null) {
            if (z) {
                if (!this.backInvokedCallbackRegistered) {
                    Api33Impl.INSTANCE.registerOnBackInvokedCallback(onBackInvokedDispatcher, 0, onBackInvokedCallback);
                    this.backInvokedCallbackRegistered = true;
                    return;
                }
                return;
            }
            if (this.backInvokedCallbackRegistered) {
                Api33Impl.INSTANCE.unregisterOnBackInvokedCallback(onBackInvokedDispatcher, onBackInvokedCallback);
                this.backInvokedCallbackRegistered = false;
            }
        }
    }

    public final void addCallback(LifecycleOwner lifecycleOwner, OnBackPressedCallback onBackPressedCallback) {
        lifecycleOwner.getClass();
        onBackPressedCallback.getClass();
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        onBackPressedCallback.addCancellable(new LifecycleOnBackPressedCancellable(this, lifecycle, onBackPressedCallback));
        updateEnabledCallbacks();
        onBackPressedCallback.enabledChangedCallback = new OnBackPressedDispatcher$addCancellableCallback$1(this, 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object] */
    public final void onBackCancelled() {
        OnBackPressedCallback onBackPressedCallback;
        OnBackPressedCallback onBackPressedCallback2 = this.inProgressCallback;
        if (onBackPressedCallback2 == null) {
            ArrayDeque arrayDeque = this.onBackPressedCallbacks;
            ListIterator listIterator = arrayDeque.listIterator(arrayDeque.size);
            while (true) {
                if (listIterator.hasPrevious()) {
                    onBackPressedCallback = listIterator.previous();
                    if (((OnBackPressedCallback) onBackPressedCallback).isEnabled) {
                        break;
                    }
                } else {
                    onBackPressedCallback = 0;
                    break;
                }
            }
            onBackPressedCallback2 = onBackPressedCallback;
        }
        this.inProgressCallback = null;
        if (onBackPressedCallback2 != null) {
            onBackPressedCallback2.handleOnBackCancelled();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object] */
    public final void onBackPressed() {
        OnBackPressedCallback onBackPressedCallback;
        OnBackPressedCallback onBackPressedCallback2 = this.inProgressCallback;
        if (onBackPressedCallback2 == null) {
            ArrayDeque arrayDeque = this.onBackPressedCallbacks;
            ListIterator listIterator = arrayDeque.listIterator(arrayDeque.size);
            while (true) {
                if (listIterator.hasPrevious()) {
                    onBackPressedCallback = listIterator.previous();
                    if (((OnBackPressedCallback) onBackPressedCallback).isEnabled) {
                        break;
                    }
                } else {
                    onBackPressedCallback = 0;
                    break;
                }
            }
            onBackPressedCallback2 = onBackPressedCallback;
        }
        this.inProgressCallback = null;
        if (onBackPressedCallback2 != null) {
            onBackPressedCallback2.handleOnBackPressed();
            return;
        }
        Runnable runnable = this.fallbackOnBackPressed;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void setOnBackInvokedDispatcher(OnBackInvokedDispatcher onBackInvokedDispatcher) {
        this.invokedDispatcher = onBackInvokedDispatcher;
        updateBackInvokedCallbackState(this.hasEnabledCallbacks);
    }

    public final void updateEnabledCallbacks() {
        boolean z = this.hasEnabledCallbacks;
        ArrayDeque arrayDeque = this.onBackPressedCallbacks;
        boolean z2 = false;
        if (!arrayDeque.isEmpty()) {
            Iterator<E> it = arrayDeque.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((OnBackPressedCallback) it.next()).isEnabled) {
                    z2 = true;
                    break;
                }
            }
        }
        this.hasEnabledCallbacks = z2;
        if (z2 != z && Build.VERSION.SDK_INT >= 33) {
            updateBackInvokedCallbackState(z2);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        OnBackInvokedCallback createOnBackInvokedCallback;
        this.fallbackOnBackPressed = runnable;
        this.onBackPressedCallbacks = new ArrayDeque();
        if (Build.VERSION.SDK_INT >= 33) {
            if (Build.VERSION.SDK_INT >= 34) {
                createOnBackInvokedCallback = Api34Impl.INSTANCE.createOnBackAnimationCallback(new AnonymousClass1(this, 0), new AnonymousClass1(this, 2), new ComponentActivity$fullyDrawnReporter$2$1(this, 4), new ComponentActivity$fullyDrawnReporter$2$1(this, 5));
            } else {
                createOnBackInvokedCallback = Api33Impl.INSTANCE.createOnBackInvokedCallback(new ComponentActivity$fullyDrawnReporter$2$1(this, 6));
            }
            this.onBackInvokedCallback = createOnBackInvokedCallback;
        }
    }
}

package androidx.core.view;

import android.content.ClipData;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContentInfo;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.View$OnUnhandledKeyEventListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.Insets;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.autofill.AutofillIdCompat;
import androidx.core.view.contentcapture.ContentCaptureSessionCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewCompat {
    private static boolean sAccessibilityDelegateCheckFailed = false;
    private static Field sAccessibilityDelegateField;
    private static WeakHashMap sViewPropertyAnimatorMap;
    public static final int[] ACCESSIBILITY_ACTIONS_RESOURCE_IDS = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
    private static final OnReceiveContentViewBehavior NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR = new OnReceiveContentViewBehavior() { // from class: androidx.core.view.ViewCompat$$ExternalSyntheticLambda0
        @Override // androidx.core.view.OnReceiveContentViewBehavior
        public final ContentInfoCompat onReceiveContent(ContentInfoCompat contentInfoCompat) {
            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            return contentInfoCompat;
        }
    };
    private static final AccessibilityPaneVisibilityManager sAccessibilityPaneVisibilityManager = new AccessibilityPaneVisibilityManager();

    /* compiled from: PG */
    /* renamed from: androidx.core.view.ViewCompat$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends AccessibilityViewProperty {
        public AnonymousClass1(Class cls) {
            super(R.id.tag_screen_reader_focusable, cls);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* bridge */ /* synthetic */ Object frameworkGet(View view) {
            return Boolean.valueOf(Api28Impl.isScreenReaderFocusable(view));
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* bridge */ /* synthetic */ void frameworkSet(View view, Object obj) {
            Api28Impl.setScreenReaderFocusable(view, ((Boolean) obj).booleanValue());
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* bridge */ /* synthetic */ boolean shouldUpdate(Object obj, Object obj2) {
            if (!booleanNullToFalseEquals$ar$ds((Boolean) obj, (Boolean) obj2)) {
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: androidx.core.view.ViewCompat$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 extends AccessibilityViewProperty {
        public AnonymousClass2(Class cls) {
            super(R.id.tag_accessibility_pane_title, cls, 8, 28);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* synthetic */ Object frameworkGet(View view) {
            return Api28Impl.getAccessibilityPaneTitle(view);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* synthetic */ void frameworkSet(View view, Object obj) {
            Api28Impl.setAccessibilityPaneTitle(view, (CharSequence) obj);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* bridge */ /* synthetic */ boolean shouldUpdate(Object obj, Object obj2) {
            if (!TextUtils.equals((CharSequence) obj, (CharSequence) obj2)) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* renamed from: androidx.core.view.ViewCompat$3, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass3 extends AccessibilityViewProperty {
        public AnonymousClass3(Class cls) {
            super(R.id.tag_state_description, cls, 64, 30);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* synthetic */ Object frameworkGet(View view) {
            return Api30Impl.getStateDescription(view);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* synthetic */ void frameworkSet(View view, Object obj) {
            Api30Impl.setStateDescription(view, (CharSequence) obj);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* bridge */ /* synthetic */ boolean shouldUpdate(Object obj, Object obj2) {
            if (!TextUtils.equals((CharSequence) obj, (CharSequence) obj2)) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* renamed from: androidx.core.view.ViewCompat$4, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass4 extends AccessibilityViewProperty {
        public AnonymousClass4(Class cls) {
            super(R.id.tag_accessibility_heading, cls);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* bridge */ /* synthetic */ Object frameworkGet(View view) {
            return Boolean.valueOf(Api28Impl.isAccessibilityHeading(view));
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* bridge */ /* synthetic */ void frameworkSet(View view, Object obj) {
            Api28Impl.setAccessibilityHeading(view, ((Boolean) obj).booleanValue());
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public final /* bridge */ /* synthetic */ boolean shouldUpdate(Object obj, Object obj2) {
            if (!booleanNullToFalseEquals$ar$ds((Boolean) obj, (Boolean) obj2)) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class AccessibilityViewProperty {
        private final int mContentChangeType;
        private final int mFrameworkMinimumSdk;
        private final int mTagKey;
        private final Class mType;

        public AccessibilityViewProperty(int i, Class cls, int i2, int i3) {
            this.mTagKey = i;
            this.mType = cls;
            this.mContentChangeType = i2;
            this.mFrameworkMinimumSdk = i3;
        }

        static final boolean booleanNullToFalseEquals$ar$ds(Boolean bool, Boolean bool2) {
            boolean z;
            boolean z2;
            if (bool != null && bool.booleanValue()) {
                z = true;
            } else {
                z = false;
            }
            if (bool2 != null && bool2.booleanValue()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z == z2) {
                return true;
            }
            return false;
        }

        private final boolean frameworkAvailable() {
            if (Build.VERSION.SDK_INT >= this.mFrameworkMinimumSdk) {
                return true;
            }
            return false;
        }

        public abstract Object frameworkGet(View view);

        public abstract void frameworkSet(View view, Object obj);

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Object get(View view) {
            if (frameworkAvailable()) {
                return frameworkGet(view);
            }
            Object tag = view.getTag(this.mTagKey);
            if (this.mType.isInstance(tag)) {
                return tag;
            }
            return null;
        }

        public final void set(View view, Object obj) {
            if (frameworkAvailable()) {
                frameworkSet(view, obj);
            } else if (shouldUpdate(get(view), obj)) {
                ViewCompat.ensureAccessibilityDelegateCompat(view);
                view.setTag(this.mTagKey, obj);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, this.mContentChangeType);
            }
        }

        public boolean shouldUpdate(Object obj, Object obj2) {
            throw null;
        }

        public AccessibilityViewProperty(int i, Class cls) {
            this(i, cls, 0, 28);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api20Impl {
        static WindowInsets dispatchApplyWindowInsets(View view, WindowInsets windowInsets) {
            return view.dispatchApplyWindowInsets(windowInsets);
        }

        static WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return view.onApplyWindowInsets(windowInsets);
        }

        public static void requestApplyInsets(View view) {
            view.requestApplyInsets();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api21Impl {
        public static ViewModel $default$create$ar$ds() {
            throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
        }

        static void callCompatInsetAnimationCallback(WindowInsets windowInsets, View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        public static WindowInsetsCompat computeSystemWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, Rect rect) {
            WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
            if (windowInsets != null) {
                return WindowInsetsCompat.toWindowInsetsCompat(view.computeSystemWindowInsets(windowInsets, rect), view);
            }
            rect.setEmpty();
            return windowInsetsCompat;
        }

        static boolean dispatchNestedFling(View view, float f, float f2, boolean z) {
            return view.dispatchNestedFling(f, f2, z);
        }

        static boolean dispatchNestedPreFling(View view, float f, float f2) {
            return view.dispatchNestedPreFling(f, f2);
        }

        static boolean dispatchNestedPreScroll(View view, int i, int i2, int[] iArr, int[] iArr2) {
            return view.dispatchNestedPreScroll(i, i2, iArr, iArr2);
        }

        static boolean dispatchNestedScroll(View view, int i, int i2, int i3, int i4, int[] iArr) {
            return view.dispatchNestedScroll(i, i2, i3, i4, iArr);
        }

        public static ColorStateList getBackgroundTintList(View view) {
            return view.getBackgroundTintList();
        }

        public static PorterDuff.Mode getBackgroundTintMode(View view) {
            return view.getBackgroundTintMode();
        }

        public static float getElevation(View view) {
            return view.getElevation();
        }

        public static WindowInsetsCompat getRootWindowInsets(View view) {
            if (!WindowInsetsCompat.Api21ReflectionHolder.sReflectionSucceeded || !view.isAttachedToWindow()) {
                return null;
            }
            try {
                Object obj = WindowInsetsCompat.Api21ReflectionHolder.sViewAttachInfoField.get(view.getRootView());
                if (obj == null) {
                    return null;
                }
                Rect rect = (Rect) WindowInsetsCompat.Api21ReflectionHolder.sStableInsets.get(obj);
                Rect rect2 = (Rect) WindowInsetsCompat.Api21ReflectionHolder.sContentInsets.get(obj);
                if (rect == null || rect2 == null) {
                    return null;
                }
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat();
                collectionItemInfoCompat.setStableInsets$ar$ds(Insets.of(rect));
                collectionItemInfoCompat.setSystemWindowInsets$ar$ds(Insets.of(rect2));
                WindowInsetsCompat m31build = collectionItemInfoCompat.m31build();
                m31build.setRootWindowInsets(m31build);
                m31build.copyRootViewBounds(view.getRootView());
                return m31build;
            } catch (IllegalAccessException e) {
                Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. ".concat(String.valueOf(e.getMessage())), e);
                return null;
            }
        }

        public static String getTransitionName(View view) {
            return view.getTransitionName();
        }

        static float getTranslationZ(View view) {
            return view.getTranslationZ();
        }

        public static float getZ(View view) {
            return view.getZ();
        }

        static boolean hasNestedScrollingParent(View view) {
            return view.hasNestedScrollingParent();
        }

        static boolean isImportantForAccessibility(View view) {
            return view.isImportantForAccessibility();
        }

        public static boolean isNestedScrollingEnabled(View view) {
            return view.isNestedScrollingEnabled();
        }

        public static void setBackgroundTintList(View view, ColorStateList colorStateList) {
            view.setBackgroundTintList(colorStateList);
        }

        public static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
            view.setBackgroundTintMode(mode);
        }

        public static void setElevation(View view, float f) {
            view.setElevation(f);
        }

        static void setNestedScrollingEnabled(View view, boolean z) {
            view.setNestedScrollingEnabled(z);
        }

        public static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            if (Build.VERSION.SDK_INT < 30) {
                view.setTag(R.id.tag_on_apply_window_listener, onApplyWindowInsetsListener);
            }
            if (onApplyWindowInsetsListener == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback));
            } else {
                view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: androidx.core.view.ViewCompat.Api21Impl.1
                    WindowInsetsCompat mLastInsets = null;

                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view2);
                        if (Build.VERSION.SDK_INT < 30) {
                            Api21Impl.callCompatInsetAnimationCallback(windowInsets, view);
                            if (windowInsetsCompat.equals(this.mLastInsets)) {
                                return onApplyWindowInsetsListener.onApplyWindowInsets(view2, windowInsetsCompat).toWindowInsets();
                            }
                        }
                        this.mLastInsets = windowInsetsCompat;
                        WindowInsetsCompat onApplyWindowInsets = onApplyWindowInsetsListener.onApplyWindowInsets(view2, windowInsetsCompat);
                        if (Build.VERSION.SDK_INT >= 30) {
                            return onApplyWindowInsets.toWindowInsets();
                        }
                        Api20Impl.requestApplyInsets(view2);
                        return onApplyWindowInsets.toWindowInsets();
                    }
                });
            }
        }

        public static void setTransitionName(View view, String str) {
            view.setTransitionName(str);
        }

        static void setTranslationZ(View view, float f) {
            view.setTranslationZ(f);
        }

        static void setZ(View view, float f) {
            view.setZ(f);
        }

        static boolean startNestedScroll(View view, int i) {
            return view.startNestedScroll(i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void stopNestedScroll(View view) {
            view.stopNestedScroll();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api23Impl {
        public static WindowInsetsCompat getRootWindowInsets(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets);
            windowInsetsCompat.setRootWindowInsets(windowInsetsCompat);
            windowInsetsCompat.copyRootViewBounds(view.getRootView());
            return windowInsetsCompat;
        }

        static int getScrollIndicators(View view) {
            return view.getScrollIndicators();
        }

        static void setScrollIndicators(View view, int i) {
            view.setScrollIndicators(i);
        }

        public static void setScrollIndicators(View view, int i, int i2) {
            view.setScrollIndicators(i, i2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api24Impl {
        static void cancelDragAndDrop(View view) {
            view.cancelDragAndDrop();
        }

        static void dispatchFinishTemporaryDetach(View view) {
            view.dispatchFinishTemporaryDetach();
        }

        static void dispatchStartTemporaryDetach(View view) {
            view.dispatchStartTemporaryDetach();
        }

        public static final void set(View view, LifecycleOwner lifecycleOwner) {
            view.getClass();
            view.setTag(R.id.view_tree_lifecycle_owner, lifecycleOwner);
        }

        static void setPointerIcon(View view, PointerIcon pointerIcon) {
            view.setPointerIcon(pointerIcon);
        }

        static boolean startDragAndDrop(View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i) {
            return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i);
        }

        static void updateDragShadow(View view, View.DragShadowBuilder dragShadowBuilder) {
            view.updateDragShadow(dragShadowBuilder);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api26Impl {
        static void addKeyboardNavigationClusters(View view, Collection<View> collection, int i) {
            view.addKeyboardNavigationClusters(collection, i);
        }

        public static AutofillId getAutofillId(View view) {
            return view.getAutofillId();
        }

        public static int getImportantForAutofill(View view) {
            return view.getImportantForAutofill();
        }

        static int getNextClusterForwardId(View view) {
            return view.getNextClusterForwardId();
        }

        static boolean hasExplicitFocusable(View view) {
            return view.hasExplicitFocusable();
        }

        static boolean isFocusedByDefault(View view) {
            return view.isFocusedByDefault();
        }

        static boolean isImportantForAutofill(View view) {
            return view.isImportantForAutofill();
        }

        static boolean isKeyboardNavigationCluster(View view) {
            return view.isKeyboardNavigationCluster();
        }

        static View keyboardNavigationClusterSearch(View view, View view2, int i) {
            return view.keyboardNavigationClusterSearch(view2, i);
        }

        static boolean restoreDefaultFocus(View view) {
            return view.restoreDefaultFocus();
        }

        public static final void set(View view, ViewModelStoreOwner viewModelStoreOwner) {
            view.getClass();
            view.setTag(R.id.view_tree_view_model_store_owner, viewModelStoreOwner);
        }

        static void setAutofillHints(View view, String... strArr) {
            view.setAutofillHints(strArr);
        }

        static void setFocusedByDefault(View view, boolean z) {
            view.setFocusedByDefault(z);
        }

        public static void setImportantForAutofill(View view, int i) {
            view.setImportantForAutofill(i);
        }

        static void setKeyboardNavigationCluster(View view, boolean z) {
            view.setKeyboardNavigationCluster(z);
        }

        static void setNextClusterForwardId(View view, int i) {
            view.setNextClusterForwardId(i);
        }

        static void setTooltipText(View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api28Impl {
        static void addOnUnhandledKeyEventListener(View view, final OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null) {
                simpleArrayMap = new SimpleArrayMap();
                view.setTag(R.id.tag_unhandled_key_listeners, simpleArrayMap);
            }
            onUnhandledKeyEventListenerCompat.getClass();
            View$OnUnhandledKeyEventListener view$OnUnhandledKeyEventListener = new View$OnUnhandledKeyEventListener() { // from class: androidx.core.view.ViewCompat$Api28Impl$$ExternalSyntheticLambda0
                public final boolean onUnhandledKeyEvent(View view2, KeyEvent keyEvent) {
                    return ViewCompat.OnUnhandledKeyEventListenerCompat.this.onUnhandledKeyEvent$ar$ds();
                }
            };
            simpleArrayMap.put(onUnhandledKeyEventListenerCompat, view$OnUnhandledKeyEventListener);
            view.addOnUnhandledKeyEventListener(view$OnUnhandledKeyEventListener);
        }

        public static final ViewModel createViewModel(ViewModelProvider.Factory factory, KClass kClass, CreationExtras creationExtras) {
            try {
                try {
                    return factory.create(kClass, creationExtras);
                } catch (AbstractMethodError unused) {
                    return factory.create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass), creationExtras);
                }
            } catch (AbstractMethodError unused2) {
                return factory.create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass));
            }
        }

        static CharSequence getAccessibilityPaneTitle(View view) {
            return view.getAccessibilityPaneTitle();
        }

        static boolean isAccessibilityHeading(View view) {
            return view.isAccessibilityHeading();
        }

        static boolean isScreenReaderFocusable(View view) {
            return view.isScreenReaderFocusable();
        }

        static void removeOnUnhandledKeyEventListener(View view, OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            View$OnUnhandledKeyEventListener view$OnUnhandledKeyEventListener;
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap != null && (view$OnUnhandledKeyEventListener = (View$OnUnhandledKeyEventListener) simpleArrayMap.get(onUnhandledKeyEventListenerCompat)) != null) {
                view.removeOnUnhandledKeyEventListener(view$OnUnhandledKeyEventListener);
            }
        }

        static <T> T requireViewById(View view, int i) {
            return (T) view.requireViewById(i);
        }

        static void setAccessibilityHeading(View view, boolean z) {
            view.setAccessibilityHeading(z);
        }

        static void setAccessibilityPaneTitle(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        public static void setAutofillId(View view, AutofillIdCompat autofillIdCompat) {
            if (autofillIdCompat == null) {
                view.setAutofillId(null);
                return;
            }
            throw null;
        }

        static void setScreenReaderFocusable(View view, boolean z) {
            view.setScreenReaderFocusable(z);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        static View.AccessibilityDelegate getAccessibilityDelegate(View view) {
            return view.getAccessibilityDelegate();
        }

        static ContentCaptureSession getContentCaptureSession(View view) {
            return view.getContentCaptureSession();
        }

        static List<Rect> getSystemGestureExclusionRects(View view) {
            return view.getSystemGestureExclusionRects();
        }

        static void saveAttributeDataForStyleable(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i, i2);
        }

        static void setContentCaptureSession(View view, ContentCaptureSessionCompat contentCaptureSessionCompat) {
            if (contentCaptureSessionCompat == null) {
                view.setContentCaptureSession(null);
                return;
            }
            throw null;
        }

        public static void setSystemGestureExclusionRects(View view, List<Rect> list) {
            view.setSystemGestureExclusionRects(list);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api30Impl {
        public static final String getCanonicalName(KClass kClass) {
            return kClass.getQualifiedName();
        }

        static int getImportantForContentCapture(View view) {
            return view.getImportantForContentCapture();
        }

        static CharSequence getStateDescription(View view) {
            return view.getStateDescription();
        }

        static boolean isImportantForContentCapture(View view) {
            return view.isImportantForContentCapture();
        }

        static void setImportantForContentCapture(View view, int i) {
            view.setImportantForContentCapture(i);
        }

        static void setStateDescription(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api31Impl {
        public static String[] getReceiveContentMimeTypes(View view) {
            return view.getReceiveContentMimeTypes();
        }

        public static ContentInfoCompat performReceiveContent(View view, ContentInfoCompat contentInfoCompat) {
            ContentInfo contentInfo = contentInfoCompat.toContentInfo();
            ContentInfo performReceiveContent = view.performReceiveContent(contentInfo);
            if (performReceiveContent == null) {
                return null;
            }
            if (performReceiveContent == contentInfo) {
                return contentInfoCompat;
            }
            return ContentInfoCompat.toContentInfoCompat(performReceiveContent);
        }

        public static void setOnReceiveContentListener(View view, String[] strArr, OnReceiveContentListener onReceiveContentListener) {
            if (onReceiveContentListener == null) {
                view.setOnReceiveContentListener(strArr, null);
            } else {
                view.setOnReceiveContentListener(strArr, new OnReceiveContentListenerAdapter(onReceiveContentListener));
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OnReceiveContentListenerAdapter implements android.view.OnReceiveContentListener {
        private final OnReceiveContentListener mJetpackListener;

        public OnReceiveContentListenerAdapter(OnReceiveContentListener onReceiveContentListener) {
            this.mJetpackListener = onReceiveContentListener;
        }

        public final ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
            OnReceiveContentListener onReceiveContentListener = this.mJetpackListener;
            ContentInfoCompat contentInfoCompat = ContentInfoCompat.toContentInfoCompat(contentInfo);
            ContentInfoCompat onReceiveContent = onReceiveContentListener.onReceiveContent(view, contentInfoCompat);
            if (onReceiveContent == null) {
                return null;
            }
            if (onReceiveContent == contentInfoCompat) {
                return contentInfo;
            }
            return onReceiveContent.toContentInfo();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent$ar$ds();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class UnhandledKeyEventManager {
        public static final ArrayList sViewsWithListeners = new ArrayList();
        public WeakHashMap mViewsContainingListeners = null;
        private SparseArray mCapturedKeys = null;
        public WeakReference mLastDispatchedPreViewKeyEvent = null;

        static UnhandledKeyEventManager at(View view) {
            UnhandledKeyEventManager unhandledKeyEventManager = (UnhandledKeyEventManager) view.getTag(R.id.tag_unhandled_key_event_manager);
            if (unhandledKeyEventManager == null) {
                UnhandledKeyEventManager unhandledKeyEventManager2 = new UnhandledKeyEventManager();
                view.setTag(R.id.tag_unhandled_key_event_manager, unhandledKeyEventManager2);
                return unhandledKeyEventManager2;
            }
            return unhandledKeyEventManager;
        }

        public static final boolean onUnhandledKeyEvent$ar$ds$72fb9117_0(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
            if (arrayList != null) {
                int size = arrayList.size();
                do {
                    size--;
                    if (size < 0) {
                        return false;
                    }
                } while (!((OnUnhandledKeyEventListenerCompat) arrayList.get(size)).onUnhandledKeyEvent$ar$ds());
                return true;
            }
            return false;
        }

        public final View dispatchInOrder(View view, KeyEvent keyEvent) {
            View dispatchInOrder;
            WeakHashMap weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    int childCount = viewGroup.getChildCount();
                    do {
                        childCount--;
                        if (childCount >= 0) {
                            dispatchInOrder = dispatchInOrder(viewGroup.getChildAt(childCount), keyEvent);
                        }
                    } while (dispatchInOrder == null);
                    return dispatchInOrder;
                }
                if (onUnhandledKeyEvent$ar$ds$72fb9117_0(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        public final SparseArray getCapturedKeys() {
            if (this.mCapturedKeys == null) {
                this.mCapturedKeys = new SparseArray();
            }
            return this.mCapturedKeys;
        }
    }

    public static void addAccessibilityAction(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat) {
        ensureAccessibilityDelegateCompat(view);
        removeActionWithId(accessibilityActionCompat.getId(), view);
        getActionList(view).add(accessibilityActionCompat);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    @Deprecated
    public static AccessibilityNodeInfoCompat.CollectionItemInfoCompat animate$ar$class_merging$ar$class_merging(View view) {
        if (sViewPropertyAnimatorMap == null) {
            sViewPropertyAnimatorMap = new WeakHashMap();
        }
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = (AccessibilityNodeInfoCompat.CollectionItemInfoCompat) sViewPropertyAnimatorMap.get(view);
        if (collectionItemInfoCompat == null) {
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat2 = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(view);
            sViewPropertyAnimatorMap.put(view, collectionItemInfoCompat2);
            return collectionItemInfoCompat2;
        }
        return collectionItemInfoCompat;
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets dispatchApplyWindowInsets = Api20Impl.dispatchApplyWindowInsets(view, windowInsets);
            if (!dispatchApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(dispatchApplyWindowInsets, view);
            }
        }
        return windowInsetsCompat;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean dispatchUnhandledKeyEventBeforeCallback(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        UnhandledKeyEventManager at = UnhandledKeyEventManager.at(view);
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = at.mViewsContainingListeners;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (!UnhandledKeyEventManager.sViewsWithListeners.isEmpty()) {
                synchronized (UnhandledKeyEventManager.sViewsWithListeners) {
                    if (at.mViewsContainingListeners == null) {
                        at.mViewsContainingListeners = new WeakHashMap();
                    }
                    int size = UnhandledKeyEventManager.sViewsWithListeners.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        View view2 = (View) ((WeakReference) UnhandledKeyEventManager.sViewsWithListeners.get(size)).get();
                        if (view2 == null) {
                            UnhandledKeyEventManager.sViewsWithListeners.remove(size);
                        } else {
                            at.mViewsContainingListeners.put(view2, Boolean.TRUE);
                            for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                at.mViewsContainingListeners.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
        View dispatchInOrder = at.dispatchInOrder(view, keyEvent);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (dispatchInOrder != null && !KeyEvent.isModifierKey(keyCode)) {
                at.getCapturedKeys().put(keyCode, new WeakReference(dispatchInOrder));
            }
        }
        if (dispatchInOrder == null) {
            return false;
        }
        return true;
    }

    public static boolean dispatchUnhandledKeyEventBeforeHierarchy(View view, KeyEvent keyEvent) {
        int indexOfKey;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        UnhandledKeyEventManager at = UnhandledKeyEventManager.at(view);
        WeakReference weakReference = at.mLastDispatchedPreViewKeyEvent;
        if (weakReference != null && weakReference.get() == keyEvent) {
            return false;
        }
        at.mLastDispatchedPreViewKeyEvent = new WeakReference(keyEvent);
        SparseArray capturedKeys = at.getCapturedKeys();
        WeakReference weakReference2 = null;
        if (keyEvent.getAction() == 1 && (indexOfKey = capturedKeys.indexOfKey(keyEvent.getKeyCode())) >= 0) {
            weakReference2 = (WeakReference) capturedKeys.valueAt(indexOfKey);
            capturedKeys.removeAt(indexOfKey);
        }
        if (weakReference2 == null) {
            weakReference2 = (WeakReference) capturedKeys.get(keyEvent.getKeyCode());
        }
        if (weakReference2 == null) {
            return false;
        }
        View view2 = (View) weakReference2.get();
        if (view2 == null || !view2.isAttachedToWindow()) {
            return true;
        }
        UnhandledKeyEventManager.onUnhandledKeyEvent$ar$ds$72fb9117_0(view2, keyEvent);
        return true;
    }

    static void ensureAccessibilityDelegateCompat(View view) {
        AccessibilityDelegateCompat accessibilityDelegate = getAccessibilityDelegate(view);
        if (accessibilityDelegate == null) {
            accessibilityDelegate = new AccessibilityDelegateCompat();
        }
        setAccessibilityDelegate(view, accessibilityDelegate);
    }

    public static AccessibilityDelegateCompat getAccessibilityDelegate(View view) {
        View.AccessibilityDelegate accessibilityDelegateInternal = getAccessibilityDelegateInternal(view);
        if (accessibilityDelegateInternal == null) {
            return null;
        }
        if (accessibilityDelegateInternal instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter) {
            return ((AccessibilityDelegateCompat.AccessibilityDelegateAdapter) accessibilityDelegateInternal).mCompat;
        }
        return new AccessibilityDelegateCompat(accessibilityDelegateInternal);
    }

    public static View.AccessibilityDelegate getAccessibilityDelegateInternal(View view) {
        if (Build.VERSION.SDK_INT < 29) {
            if (sAccessibilityDelegateCheckFailed) {
                return null;
            }
            if (sAccessibilityDelegateField == null) {
                try {
                    Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                    sAccessibilityDelegateField = declaredField;
                    declaredField.setAccessible(true);
                } catch (Throwable unused) {
                    sAccessibilityDelegateCheckFailed = true;
                    return null;
                }
            }
            Object obj = sAccessibilityDelegateField.get(view);
            if (!(obj instanceof View.AccessibilityDelegate)) {
                return null;
            }
            return (View.AccessibilityDelegate) obj;
        }
        return Api29Impl.getAccessibilityDelegate(view);
    }

    public static CharSequence getAccessibilityPaneTitle(View view) {
        return (CharSequence) new AnonymousClass2(CharSequence.class).get(view);
    }

    public static List getActionList(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList();
            view.setTag(R.id.tag_accessibility_actions, arrayList2);
            return arrayList2;
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static OnReceiveContentViewBehavior getFallback(View view) {
        if (view instanceof OnReceiveContentViewBehavior) {
            return (OnReceiveContentViewBehavior) view;
        }
        return NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR;
    }

    public static String[] getOnReceiveContentMimeTypes(View view) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.getReceiveContentMimeTypes(view);
        }
        return (String[]) view.getTag(R.id.tag_on_receive_content_mime_types);
    }

    static void notifyViewAccessibilityStateChangedIfNeeded(View view, int i) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = false;
            if (getAccessibilityPaneTitle(view) != null && view.isShown() && view.getWindowVisibility() == 0) {
                z = true;
            }
            int i2 = 32;
            if (view.getAccessibilityLiveRegion() == 0 && !z) {
                if (i == 32) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain();
                    view.onInitializeAccessibilityEvent(obtain);
                    obtain.setEventType(32);
                    obtain.setContentChangeTypes(32);
                    obtain.setSource(view);
                    view.onPopulateAccessibilityEvent(obtain);
                    obtain.getText().add(getAccessibilityPaneTitle(view));
                    accessibilityManager.sendAccessibilityEvent(obtain);
                    return;
                }
                if (view.getParent() != null) {
                    try {
                        view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i);
                        return;
                    } catch (AbstractMethodError e) {
                        Log.e("ViewCompat", String.valueOf(view.getParent().getClass().getSimpleName()).concat(" does not fully implement ViewParent"), e);
                        return;
                    }
                }
                return;
            }
            if (true != z) {
                i2 = 2048;
            }
            AccessibilityEvent obtain2 = AccessibilityEvent.obtain();
            obtain2.setEventType(i2);
            obtain2.setContentChangeTypes(i);
            if (z) {
                obtain2.getText().add(getAccessibilityPaneTitle(view));
                setImportantForAccessibilityIfNeeded(view);
            }
            view.sendAccessibilityEventUnchecked(obtain2);
        }
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets onApplyWindowInsets = Api20Impl.onApplyWindowInsets(view, windowInsets);
            if (!onApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(onApplyWindowInsets, view);
            }
        }
        return windowInsetsCompat;
    }

    public static ContentInfoCompat performReceiveContent(View view, ContentInfoCompat contentInfoCompat) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.performReceiveContent(view, contentInfoCompat);
        }
        OnReceiveContentListener onReceiveContentListener = (OnReceiveContentListener) view.getTag(R.id.tag_on_receive_content_listener);
        if (onReceiveContentListener != null) {
            ContentInfoCompat onReceiveContent = onReceiveContentListener.onReceiveContent(view, contentInfoCompat);
            if (onReceiveContent == null) {
                return null;
            }
            return getFallback(view).onReceiveContent(onReceiveContent);
        }
        return getFallback(view).onReceiveContent(contentInfoCompat);
    }

    public static void removeAccessibilityAction(View view, int i) {
        removeActionWithId(i, view);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    private static void removeActionWithId(int i, View view) {
        List actionList = getActionList(view);
        for (int i2 = 0; i2 < actionList.size(); i2++) {
            if (((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i2)).getId() == i) {
                actionList.remove(i2);
                return;
            }
        }
    }

    public static void saveAttributeDataForStyleable(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.saveAttributeDataForStyleable(view, context, iArr, attributeSet, typedArray, i, i2);
        }
    }

    public static void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        View.AccessibilityDelegate accessibilityDelegate;
        if (accessibilityDelegateCompat == null && (getAccessibilityDelegateInternal(view) instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter)) {
            accessibilityDelegateCompat = new AccessibilityDelegateCompat();
        }
        setImportantForAccessibilityIfNeeded(view);
        if (accessibilityDelegateCompat == null) {
            accessibilityDelegate = null;
        } else {
            accessibilityDelegate = accessibilityDelegateCompat.mBridge;
        }
        view.setAccessibilityDelegate(accessibilityDelegate);
    }

    public static void setAccessibilityHeading(View view, boolean z) {
        new AnonymousClass4(Boolean.class).set(view, Boolean.valueOf(z));
    }

    public static void setAccessibilityPaneTitle(View view, CharSequence charSequence) {
        new AnonymousClass2(CharSequence.class).set(view, charSequence);
        if (charSequence != null) {
            AccessibilityPaneVisibilityManager accessibilityPaneVisibilityManager = sAccessibilityPaneVisibilityManager;
            WeakHashMap weakHashMap = accessibilityPaneVisibilityManager.mPanesToVisible;
            boolean z = false;
            if (view.isShown() && view.getWindowVisibility() == 0) {
                z = true;
            }
            weakHashMap.put(view, Boolean.valueOf(z));
            view.addOnAttachStateChangeListener(accessibilityPaneVisibilityManager);
            if (view.isAttachedToWindow()) {
                accessibilityPaneVisibilityManager.registerForLayoutCallback(view);
                return;
            }
            return;
        }
        AccessibilityPaneVisibilityManager accessibilityPaneVisibilityManager2 = sAccessibilityPaneVisibilityManager;
        accessibilityPaneVisibilityManager2.mPanesToVisible.remove(view);
        view.removeOnAttachStateChangeListener(accessibilityPaneVisibilityManager2);
        view.getViewTreeObserver().removeOnGlobalLayoutListener(accessibilityPaneVisibilityManager2);
    }

    private static void setImportantForAccessibilityIfNeeded(View view) {
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
    }

    public static void setPointerIcon$ar$class_merging$ar$class_merging(View view, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat) {
        Object obj;
        if (collectionItemInfoCompat != null) {
            obj = collectionItemInfoCompat.mInfo;
        } else {
            obj = null;
        }
        Api24Impl.setPointerIcon(view, ViewCompat$$ExternalSyntheticApiModelOutline0.m13m(obj));
    }

    public static void setStateDescription(View view, CharSequence charSequence) {
        new AnonymousClass3(CharSequence.class).set(view, charSequence);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AccessibilityPaneVisibilityManager implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        public final WeakHashMap mPanesToVisible = new WeakHashMap();

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            int i;
            if (Build.VERSION.SDK_INT < 28) {
                for (Map.Entry entry : this.mPanesToVisible.entrySet()) {
                    View view = (View) entry.getKey();
                    boolean booleanValue = ((Boolean) entry.getValue()).booleanValue();
                    boolean z = false;
                    if (view.isShown() && view.getWindowVisibility() == 0) {
                        z = true;
                    }
                    if (booleanValue != z) {
                        if (true != z) {
                            i = 32;
                        } else {
                            i = 16;
                        }
                        ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, i);
                        entry.setValue(Boolean.valueOf(z));
                    }
                }
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            registerForLayoutCallback(view);
        }

        public final void registerForLayoutCallback(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
        }
    }
}

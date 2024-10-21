package android.support.v7.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.PowerManager;
import android.os.Trace;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.view.SupportActionModeWrapper;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.Toolbar$Api33Impl$$ExternalSyntheticLambda0;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.ViewUtils;
import android.support.v7.widget.WithHint;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Size;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat$Api23Impl;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.PopupWindowCompat$Api23Impl;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.tracing.TraceApi29Impl;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda5;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2;
import com.google.android.accessibility.selecttospeak.tts.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ExecutionList;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.util.Collection;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    ActionBar mActionBar;
    private PanelMenuPresenterCallback mActionMenuPresenterCallback$ar$class_merging;
    ActionMode mActionMode;
    public PopupWindow mActionModePopup;
    public ActionBarContextView mActionModeView;
    private int mActivityHandlesConfigFlags;
    private boolean mActivityHandlesConfigFlagsChecked;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    private AppCompatWindowCallback mAppCompatWindowCallback;
    private AutoNightModeManager mAutoBatteryNightModeManager;
    private AutoNightModeManager mAutoTimeNightModeManager;
    private OnBackInvokedCallback mBackCallback;
    public boolean mBaseContextAttached;
    private boolean mClosingActionMenu;
    final Context mContext;
    private boolean mCreated;
    public DecorContentParent mDecorContentParent;
    boolean mDestroyed;
    private OnBackInvokedDispatcher mDispatcher;
    private Configuration mEffectiveConfiguration;
    private boolean mEnableDefaultActionBarUp;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    boolean mHasActionBar;
    final Object mHost;
    public int mInvalidatePanelMenuFeatures;
    public boolean mInvalidatePanelMenuPosted;
    boolean mIsFloating;
    private int mLocalNightMode;
    private boolean mLongPressBackDown;
    MenuInflater mMenuInflater;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    public PanelFeatureState mPreparedPanel;
    public Runnable mShowActionModePopup;
    public View mStatusGuard;
    ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    public Rect mTempRect1;
    public Rect mTempRect2;
    public int mThemeResId;
    private CharSequence mTitle;
    private TextView mTitleView;
    public Window mWindow;
    boolean mWindowNoTitle;
    private static final SimpleArrayMap sLocalNightModes = new SimpleArrayMap();
    private static final int[] sWindowBackgroundStyleable = {R.attr.windowBackground};
    public static final boolean sCanReturnDifferentContext = !"robolectric".equals(Build.FINGERPRINT);
    public AccessibilityNodeInfoCompat.CollectionItemInfoCompat mFadeAnim$ar$class_merging$ar$class_merging = null;
    public boolean mHandleNativeActionModes = true;
    private final Runnable mInvalidatePanelMenuRunnable = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 10, null);

    /* compiled from: PG */
    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$3 */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements OnApplyWindowInsetsListener {
        public AnonymousClass3() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            boolean z;
            View view2;
            WindowInsetsCompat windowInsetsCompat2;
            boolean z2;
            int systemWindowInsetLeft;
            int systemWindowInsetRight;
            int color;
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            int systemWindowInsetTop2 = windowInsetsCompat.getSystemWindowInsetTop();
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            ActionBarContextView actionBarContextView = appCompatDelegateImpl.mActionModeView;
            int i = 8;
            if (actionBarContextView != null && (actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) appCompatDelegateImpl.mActionModeView.getLayoutParams();
                if (appCompatDelegateImpl.mActionModeView.isShown()) {
                    if (appCompatDelegateImpl.mTempRect1 == null) {
                        appCompatDelegateImpl.mTempRect1 = new Rect();
                        appCompatDelegateImpl.mTempRect2 = new Rect();
                    }
                    Rect rect = appCompatDelegateImpl.mTempRect1;
                    Rect rect2 = appCompatDelegateImpl.mTempRect2;
                    rect.set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                    ViewGroup viewGroup = appCompatDelegateImpl.mSubDecor;
                    if (Build.VERSION.SDK_INT >= 29) {
                        ViewUtils.Api29Impl.computeFitSystemWindows(viewGroup, rect, rect2);
                    } else {
                        if (!ViewUtils.sInitComputeFitSystemWindowsMethod) {
                            ViewUtils.sInitComputeFitSystemWindowsMethod = true;
                            try {
                                ViewUtils.sComputeFitSystemWindowsMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                                if (!ViewUtils.sComputeFitSystemWindowsMethod.isAccessible()) {
                                    ViewUtils.sComputeFitSystemWindowsMethod.setAccessible(true);
                                }
                            } catch (NoSuchMethodException unused) {
                            }
                        }
                        Method method = ViewUtils.sComputeFitSystemWindowsMethod;
                        if (method != null) {
                            try {
                                method.invoke(viewGroup, rect, rect2);
                            } catch (Exception unused2) {
                            }
                        }
                    }
                    int i2 = rect.top;
                    int i3 = rect.left;
                    int i4 = rect.right;
                    WindowInsetsCompat rootWindowInsets = ViewCompat.Api23Impl.getRootWindowInsets(appCompatDelegateImpl.mSubDecor);
                    if (rootWindowInsets == null) {
                        systemWindowInsetLeft = 0;
                    } else {
                        systemWindowInsetLeft = rootWindowInsets.getSystemWindowInsetLeft();
                    }
                    if (rootWindowInsets == null) {
                        systemWindowInsetRight = 0;
                    } else {
                        systemWindowInsetRight = rootWindowInsets.getSystemWindowInsetRight();
                    }
                    if (marginLayoutParams.topMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4) {
                        z2 = false;
                    } else {
                        marginLayoutParams.topMargin = i2;
                        marginLayoutParams.leftMargin = i3;
                        marginLayoutParams.rightMargin = i4;
                        z2 = true;
                    }
                    if (i2 > 0 && appCompatDelegateImpl.mStatusGuard == null) {
                        appCompatDelegateImpl.mStatusGuard = new View(appCompatDelegateImpl.mContext);
                        appCompatDelegateImpl.mStatusGuard.setVisibility(8);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                        layoutParams.leftMargin = systemWindowInsetLeft;
                        layoutParams.rightMargin = systemWindowInsetRight;
                        appCompatDelegateImpl.mSubDecor.addView(appCompatDelegateImpl.mStatusGuard, -1, layoutParams);
                    } else {
                        View view3 = appCompatDelegateImpl.mStatusGuard;
                        if (view3 != null) {
                            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view3.getLayoutParams();
                            if (marginLayoutParams2.height != marginLayoutParams.topMargin || marginLayoutParams2.leftMargin != systemWindowInsetLeft || marginLayoutParams2.rightMargin != systemWindowInsetRight) {
                                marginLayoutParams2.height = marginLayoutParams.topMargin;
                                marginLayoutParams2.leftMargin = systemWindowInsetLeft;
                                marginLayoutParams2.rightMargin = systemWindowInsetRight;
                                appCompatDelegateImpl.mStatusGuard.setLayoutParams(marginLayoutParams2);
                            }
                        }
                    }
                    View view4 = appCompatDelegateImpl.mStatusGuard;
                    if (view4 != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z && view4.getVisibility() != 0) {
                        View view5 = appCompatDelegateImpl.mStatusGuard;
                        if ((view5.getWindowSystemUiVisibility() & 8192) != 0) {
                            color = ContextCompat$Api23Impl.getColor(appCompatDelegateImpl.mContext, com.google.android.marvin.talkback.R.color.abc_decor_view_status_guard_light);
                        } else {
                            color = ContextCompat$Api23Impl.getColor(appCompatDelegateImpl.mContext, com.google.android.marvin.talkback.R.color.abc_decor_view_status_guard);
                        }
                        view5.setBackgroundColor(color);
                    }
                    if (!appCompatDelegateImpl.mOverlayActionMode && z) {
                        systemWindowInsetTop2 = 0;
                    }
                } else if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z2 = true;
                    z = false;
                } else {
                    z2 = false;
                    z = false;
                }
                if (z2) {
                    appCompatDelegateImpl.mActionModeView.setLayoutParams(marginLayoutParams);
                }
            } else {
                z = false;
            }
            View view6 = appCompatDelegateImpl.mStatusGuard;
            if (view6 != null) {
                if (true == z) {
                    i = 0;
                }
                view6.setVisibility(i);
            }
            if (systemWindowInsetTop != systemWindowInsetTop2) {
                int systemWindowInsetLeft2 = windowInsetsCompat.getSystemWindowInsetLeft();
                int systemWindowInsetRight2 = windowInsetsCompat.getSystemWindowInsetRight();
                int systemWindowInsetBottom = windowInsetsCompat.getSystemWindowInsetBottom();
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(windowInsetsCompat);
                collectionItemInfoCompat.setSystemWindowInsets$ar$ds(Insets.of(systemWindowInsetLeft2, systemWindowInsetTop2, systemWindowInsetRight2, systemWindowInsetBottom));
                windowInsetsCompat2 = collectionItemInfoCompat.m31build();
                view2 = view;
            } else {
                view2 = view;
                windowInsetsCompat2 = windowInsetsCompat;
            }
            return ViewCompat.onApplyWindowInsets(view2, windowInsetsCompat2);
        }
    }

    /* compiled from: PG */
    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$7 */
    /* loaded from: classes.dex */
    public final class AnonymousClass7 extends ViewPropertyAnimatorListenerAdapter {
        public AnonymousClass7() {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public final void onAnimationEnd$ar$ds() {
            AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
            AppCompatDelegateImpl.this.mFadeAnim$ar$class_merging$ar$class_merging.setListener$ar$ds$34caea9b_0(null);
            AppCompatDelegateImpl.this.mFadeAnim$ar$class_merging$ar$class_merging = null;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public final void onAnimationStart$ar$ds() {
            AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
            if (AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) {
                ViewCompat.Api20Impl.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        private final ActionMode.Callback mWrapped;

        /* compiled from: PG */
        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9$1 */
        /* loaded from: classes.dex */
        final class AnonymousClass1 extends ViewPropertyAnimatorListenerAdapter {
            public AnonymousClass1() {
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public final void onAnimationEnd$ar$ds() {
                AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.mActionModePopup;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.mActionModeView.getParent() instanceof View) {
                    ViewCompat.Api20Impl.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
                }
                AppCompatDelegateImpl.this.mActionModeView.killMode();
                AppCompatDelegateImpl.this.mFadeAnim$ar$class_merging$ar$class_merging.setListener$ar$ds$34caea9b_0(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.mFadeAnim$ar$class_merging$ar$class_merging = null;
                ViewCompat.Api20Impl.requestApplyInsets(appCompatDelegateImpl2.mSubDecor);
            }
        }

        public ActionModeCallbackWrapperV9(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        @Override // android.support.v7.view.ActionMode.Callback
        public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.support.v7.view.ActionMode.Callback
        public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        @Override // android.support.v7.view.ActionMode.Callback
        public final void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.mActionModePopup != null) {
                appCompatDelegateImpl.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.mShowActionModePopup);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.mActionModeView != null) {
                appCompatDelegateImpl2.endOnGoingFadeAnimation();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat animate$ar$class_merging$ar$class_merging = ViewCompat.animate$ar$class_merging$ar$class_merging(appCompatDelegateImpl3.mActionModeView);
                animate$ar$class_merging$ar$class_merging.alpha$ar$ds(0.0f);
                appCompatDelegateImpl3.mFadeAnim$ar$class_merging$ar$class_merging = animate$ar$class_merging$ar$class_merging;
                AppCompatDelegateImpl.this.mFadeAnim$ar$class_merging$ar$class_merging.setListener$ar$ds$34caea9b_0(new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.app.AppCompatDelegateImpl.ActionModeCallbackWrapperV9.1
                    public AnonymousClass1() {
                    }

                    @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
                    public final void onAnimationEnd$ar$ds() {
                        AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                        AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
                        PopupWindow popupWindow = appCompatDelegateImpl4.mActionModePopup;
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        } else if (appCompatDelegateImpl4.mActionModeView.getParent() instanceof View) {
                            ViewCompat.Api20Impl.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
                        }
                        AppCompatDelegateImpl.this.mActionModeView.killMode();
                        AppCompatDelegateImpl.this.mFadeAnim$ar$class_merging$ar$class_merging.setListener$ar$ds$34caea9b_0(null);
                        AppCompatDelegateImpl appCompatDelegateImpl22 = AppCompatDelegateImpl.this;
                        appCompatDelegateImpl22.mFadeAnim$ar$class_merging$ar$class_merging = null;
                        ViewCompat.Api20Impl.requestApplyInsets(appCompatDelegateImpl22.mSubDecor);
                    }
                });
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl4.mActionMode = null;
            ViewCompat.Api20Impl.requestApplyInsets(appCompatDelegateImpl4.mSubDecor);
            AppCompatDelegateImpl.this.updateBackInvokedCallbackState();
        }

        @Override // android.support.v7.view.ActionMode.Callback
        public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            ViewCompat.Api20Impl.requestApplyInsets(AppCompatDelegateImpl.this.mSubDecor);
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api21Impl {
        public static Method sAsyncTraceBeginMethod;
        public static Method sAsyncTraceEndMethod;
        private static Method sIsTagEnabledMethod;
        public static long sTraceTagApp;

        public static void beginSection(String str) {
            Trace.beginSection(truncatedTraceSectionLabel(str));
        }

        public static AlertDialog createTipAlertDialog(Context context, String str, String str2, BiConsumer biConsumer) {
            AlertDialog.Builder alertDialogBuilder = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(context);
            View inflate = LayoutInflater.from(context).inflate(com.google.android.marvin.talkback.R.layout.dialog_dont_show_again_checkbox, (ViewGroup) null);
            alertDialogBuilder.setTitle(str).setMessage(str2).setView(inflate).setPositiveButton(R.string.ok, new ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3(biConsumer, context, (CheckBox) inflate.findViewById(com.google.android.marvin.talkback.R.id.dont_show_again), 1));
            return alertDialogBuilder.create();
        }

        public static String generateLayoutPointsString(List list, int i, Size size) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("orientation", i);
            jSONObject.put("screen_width", size.getWidth());
            jSONObject.put("screen_height", size.getHeight());
            JSONArray jSONArray = new JSONArray();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                PointF pointF = (PointF) it.next();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", pointF.x);
                jSONObject2.put("y", pointF.y);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("points", jSONArray);
            return jSONObject.toString();
        }

        public static int getPhoneCalibrationPreferenceKey(boolean z, boolean z2) {
            if (z2) {
                if (z) {
                    return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_phone_eightDot_tabletop;
                }
                return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_phone_eightDot_screenaway;
            }
            if (z) {
                return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_phone_tabletop;
            }
            return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_phone_screenaway;
        }

        public static int getTabletCalibrationPreferenceKey(boolean z, int i, boolean z2) {
            if (z) {
                if (i == 1) {
                    if (true != z2) {
                        return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_tablet_tabletop_portrait;
                    }
                    return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_tablet_eightDot_tabletop_portrait;
                }
                if (true != z2) {
                    return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_tablet_tabletop_landscape;
                }
                return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_tablet_eightDot_tabletop_landscape;
            }
            if (i == 1) {
                if (true != z2) {
                    return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_tablet_screenaway_portrait;
                }
                return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_tablet_eightDot_screenaway_portrait;
            }
            if (true != z2) {
                return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_tablet_screenaway_landscape;
            }
            return com.google.android.marvin.talkback.R.string.pref_brailleime_calibration_points_tablet_eightDot_screenaway_landscape;
        }

        public static void handleException$ar$ds(Exception exc) {
            if (exc instanceof InvocationTargetException) {
                Throwable cause = exc.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                throw new RuntimeException(cause);
            }
        }

        public static boolean isEnabled() {
            if (Build.VERSION.SDK_INT < 29) {
                try {
                    if (sIsTagEnabledMethod == null) {
                        sTraceTagApp = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                        sIsTagEnabledMethod = Trace.class.getMethod("isTagEnabled", Long.TYPE);
                    }
                    return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
                } catch (Exception e) {
                    handleException$ar$ds(e);
                    return false;
                }
            }
            return TraceApi29Impl.isEnabled();
        }

        public static boolean isPhoneSizedDevice(Resources resources) {
            return resources.getBoolean(com.google.android.marvin.talkback.R.bool.is_phone_sized);
        }

        static boolean isPowerSaveMode(PowerManager powerManager) {
            return powerManager.isPowerSaveMode();
        }

        public static PointF mapLandscapeToPortraitForPhone(PointF pointF, Size size, Size size2) {
            Matrix matrix = new Matrix();
            matrix.postRotate(-90.0f);
            matrix.postTranslate(0.0f, size2.getHeight());
            matrix.preScale(size2.getHeight() / size.getWidth(), size2.getWidth() / size.getHeight());
            float[] fArr = new float[2];
            matrix.mapPoints(fArr, 0, new float[]{pointF.x, pointF.y}, 0, 1);
            return new PointF(fArr[0], fArr[1]);
        }

        public static List parseLayoutPointsString(String str) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONObject(str).getJSONArray("points");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new PointF((float) jSONArray.getJSONObject(i).getDouble("x"), (float) jSONArray.getJSONObject(i).getDouble("y")));
            }
            return arrayList;
        }

        public static void setupLanguageListPreference(final Context context, ListPreference listPreference, final Function function, final BiConsumer biConsumer, final Preference.OnPreferenceChangeListener onPreferenceChangeListener) {
            Object apply;
            List readAvailablePreferredCodes = BrailleUserPreferences.readAvailablePreferredCodes(context);
            listPreference.setEntryValues((CharSequence[]) Collection.EL.stream(readAvailablePreferredCodes).map(new BtConnectManager$$ExternalSyntheticLambda1(8)).toArray(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(3)));
            listPreference.setEntries((CharSequence[]) Collection.EL.stream(readAvailablePreferredCodes).map(new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 0)).toArray(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(4)));
            apply = function.apply(context);
            listPreference.setValue(((BrailleLanguages.Code) apply).name());
            listPreference.setSummaryProvider(new Preference.SummaryProvider() { // from class: com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda7
                @Override // androidx.preference.Preference.SummaryProvider
                public final CharSequence provideSummary(Preference preference) {
                    Object apply2;
                    Function function2 = Function.this;
                    Context context2 = context;
                    apply2 = function2.apply(context2);
                    return ((BrailleLanguages.Code) apply2).getUserFacingName(context2);
                }
            });
            listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda8
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    BiConsumer.this.accept(context, BrailleLanguages.Code.valueOf(obj.toString()));
                    onPreferenceChangeListener.onPreferenceChange(preference, obj);
                    return true;
                }
            });
        }

        public static void setupPreferredCodePreference(Context context, Preference preference, Preference.OnPreferenceChangeListener onPreferenceChangeListener) {
            if (BrailleUserPreferences.readAvailablePreferredCodes(context).isEmpty()) {
                BrailleUserPreferences.writePreferredCodes(context, ContextDataProvider.newArrayList(BrailleLanguages.getDefaultCode(context)));
            }
            preference.setSummaryProvider(new BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2(context, 1));
            preference.setOnPreferenceChangeListener(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(onPreferenceChangeListener, 5));
        }

        public static CharSequence toCharacterTitleCase(CharSequence charSequence) {
            if (TextUtils.isEmpty(charSequence)) {
                return charSequence;
            }
            return String.valueOf(String.valueOf(charSequence.charAt(0)).toUpperCase(Locale.getDefault())).concat(String.valueOf(String.valueOf(charSequence.subSequence(1, charSequence.length()))));
        }

        static String toLanguageTag(Locale locale) {
            return locale.toLanguageTag();
        }

        public static String truncatedTraceSectionLabel(String str) {
            if (str.length() <= 127) {
                return str;
            }
            return str.substring(0, BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api24Impl {
        public static SpeechController.SpeakOptions $default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(int i, SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable) {
            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
            int i2 = i - 1;
            int i3 = 1;
            if (i2 != 0) {
                if (i2 != 1) {
                    i3 = 0;
                } else {
                    i3 = 3;
                }
            }
            speakOptions.mQueueMode = i3;
            speakOptions.mFlags = 28;
            speakOptions.mCompletedAction = utteranceCompleteRunnable;
            return speakOptions;
        }

        public static void $default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent, CharSequence charSequence, int i) {
            onDeviceTextDetectionLoadLogEvent.speak(charSequence, 0, $default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(i, null));
        }

        public Api24Impl() {
        }

        public static void generateConfigDelta_locale(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            LocaleList locales;
            LocaleList locales2;
            boolean equals;
            locales = configuration.getLocales();
            locales2 = configuration2.getLocales();
            equals = locales.equals(locales2);
            if (!equals) {
                configuration3.setLocales(locales2);
                configuration3.locale = configuration2.locale;
            }
        }

        static LocaleListCompat getLocales(Configuration configuration) {
            LocaleList locales;
            String languageTags;
            locales = configuration.getLocales();
            languageTags = locales.toLanguageTags();
            return LocaleListCompat.forLanguageTags(languageTags);
        }

        public static void setDefaultLocales(LocaleListCompat localeListCompat) {
            LocaleList forLanguageTags;
            forLanguageTags = LocaleList.forLanguageTags(localeListCompat.toLanguageTags());
            LocaleList.setDefault(forLanguageTags);
        }

        static void setLocales(Configuration configuration, LocaleListCompat localeListCompat) {
            LocaleList forLanguageTags;
            forLanguageTags = LocaleList.forLanguageTags(localeListCompat.toLanguageTags());
            configuration.setLocales(forLanguageTags);
        }

        public Api24Impl(byte[] bArr) {
            this();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Api33Impl {
        /* renamed from: BrailleLanguages$Code$1-IA$ar$MethodMerging */
        public static /* synthetic */ boolean m1BrailleLanguages$Code$1IA$ar$MethodMerging(char c) {
            if (c >= 'a' && c <= 'z') {
                return true;
            }
            if (c >= 'A' && c <= 'Z') {
                return true;
            }
            return false;
        }

        static OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity activity) {
            OnBackInvokedDispatcher onBackInvokedDispatcher;
            onBackInvokedDispatcher = activity.getOnBackInvokedDispatcher();
            return onBackInvokedDispatcher;
        }

        public static String getTextFieldText(InputConnection inputConnection) {
            ExtractedText extractedText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0);
            if (extractedText != null && extractedText.text != null) {
                return extractedText.text.toString();
            }
            return "";
        }

        public static boolean isMultiLineField(int i) {
            if ((i & 393216) == 131072) {
                return true;
            }
            return false;
        }

        public static void onCreateInputConnection$ar$ds(InputConnection inputConnection, EditorInfo editorInfo, View view) {
            if (inputConnection != null && editorInfo.hintText == null) {
                for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                    if (parent instanceof WithHint) {
                        editorInfo.hintText = ((WithHint) parent).getHint();
                        return;
                    }
                }
            }
        }

        static OnBackInvokedCallback registerOnBackPressedCallback(Object obj, AppCompatDelegateImpl appCompatDelegateImpl) {
            appCompatDelegateImpl.getClass();
            Toolbar$Api33Impl$$ExternalSyntheticLambda0 toolbar$Api33Impl$$ExternalSyntheticLambda0 = new Toolbar$Api33Impl$$ExternalSyntheticLambda0(appCompatDelegateImpl, 1);
            ContextUtils$$ExternalSyntheticApiModelOutline0.m279m(obj).registerOnBackInvokedCallback(1000000, toolbar$Api33Impl$$ExternalSyntheticLambda0);
            return toolbar$Api33Impl$$ExternalSyntheticLambda0;
        }

        public static boolean shouldEmitPerCharacterFeedback$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
            if (phenotypeProcessReaper.pollingMinutes == 3 && AppCompatDelegate.Api33Impl.isTextField((EditorInfo) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider)) {
                return false;
            }
            return true;
        }

        public static /* synthetic */ String toStringGenerated69893d619e9da448(int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return "TEMPORARILY_UNMETERED";
                            }
                            return "METERED";
                        }
                        return "NOT_ROAMING";
                    }
                    return "UNMETERED";
                }
                return "CONNECTED";
            }
            return "NOT_REQUIRED";
        }

        static void unregisterOnBackInvokedCallback(Object obj, Object obj2) {
            ContextUtils$$ExternalSyntheticApiModelOutline0.m279m(obj).unregisterOnBackInvokedCallback(ContextUtils$$ExternalSyntheticApiModelOutline0.m278m(obj2));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AppCompatWindowCallback extends WindowCallbackWrapper {
        public boolean mDispatchKeyEventBypassEnabled;
        private boolean mOnContentChangedBypassEnabled;
        public boolean mOnPanelClosedBypassEnabled;

        public AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        public final void bypassOnContentChanged(Window.Callback callback) {
            try {
                this.mOnContentChangedBypassEnabled = true;
                callback.onContentChanged();
            } finally {
                this.mOnContentChangedBypassEnabled = false;
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (this.mDispatchKeyEventBypassEnabled) {
                return this.mWrapped.dispatchKeyEvent(keyEvent);
            }
            if (!AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent) && !super.dispatchKeyEvent(keyEvent)) {
                return false;
            }
            return true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
        
            if (r3.performShortcut(r2, r7, 0) != false) goto L63;
         */
        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean dispatchKeyShortcutEvent(android.view.KeyEvent r7) {
            /*
                r6 = this;
                boolean r0 = super.dispatchKeyShortcutEvent(r7)
                r1 = 1
                if (r0 != 0) goto L69
                android.support.v7.app.AppCompatDelegateImpl r0 = android.support.v7.app.AppCompatDelegateImpl.this
                int r2 = r7.getKeyCode()
                android.support.v7.app.ActionBar r3 = r0.getSupportActionBar()
                r4 = 0
                if (r3 == 0) goto L3c
                android.support.v7.app.WindowDecorActionBar r3 = (android.support.v7.app.WindowDecorActionBar) r3
                android.support.v7.app.WindowDecorActionBar$ActionModeImpl r3 = r3.mActionMode
                if (r3 != 0) goto L1b
                goto L3c
            L1b:
                if (r7 == 0) goto L22
                int r5 = r7.getDeviceId()
                goto L23
            L22:
                r5 = -1
            L23:
                android.support.v7.view.menu.MenuBuilder r3 = r3.mMenu
                android.view.KeyCharacterMap r5 = android.view.KeyCharacterMap.load(r5)
                int r5 = r5.getKeyboardType()
                if (r5 == r1) goto L31
                r5 = r1
                goto L32
            L31:
                r5 = r4
            L32:
                r3.setQwertyMode(r5)
                boolean r2 = r3.performShortcut(r2, r7, r4)
                if (r2 == 0) goto L3c
                goto L69
            L3c:
                android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState r2 = r0.mPreparedPanel
                if (r2 == 0) goto L51
                int r3 = r7.getKeyCode()
                boolean r2 = r0.performPanelShortcut$ar$ds(r2, r3, r7)
                if (r2 == 0) goto L51
                android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState r7 = r0.mPreparedPanel
                if (r7 == 0) goto L69
                r7.isHandled = r1
                goto L69
            L51:
                android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState r2 = r0.mPreparedPanel
                if (r2 != 0) goto L68
                android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState r2 = r0.getPanelState$ar$ds(r4)
                r0.preparePanel(r2, r7)
                int r3 = r7.getKeyCode()
                boolean r7 = r0.performPanelShortcut$ar$ds(r2, r3, r7)
                r2.isPrepared = r4
                if (r7 != 0) goto L69
            L68:
                return r4
            L69:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.AppCompatWindowCallback.dispatchKeyShortcutEvent(android.view.KeyEvent):boolean");
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final void onContentChanged() {
            if (this.mOnContentChangedBypassEnabled) {
                this.mWrapped.onContentChanged();
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean onCreatePanelMenu(int i, Menu menu) {
            if (i == 0) {
                if (!(menu instanceof MenuBuilder)) {
                    return false;
                }
                i = 0;
            }
            return super.onCreatePanelMenu(i, menu);
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean onMenuOpened(int i, Menu menu) {
            ActionBar supportActionBar;
            super.onMenuOpened(i, menu);
            if (i == 108 && (supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar()) != null) {
                supportActionBar.dispatchMenuVisibilityChanged(true);
            }
            return true;
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final void onPanelClosed(int i, Menu menu) {
            if (this.mOnPanelClosedBypassEnabled) {
                this.mWrapped.onPanelClosed(i, menu);
                return;
            }
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (i == 108) {
                ActionBar supportActionBar = appCompatDelegateImpl.getSupportActionBar();
                if (supportActionBar != null) {
                    supportActionBar.dispatchMenuVisibilityChanged(false);
                    return;
                }
                return;
            }
            if (i == 0) {
                PanelFeatureState panelState$ar$ds = appCompatDelegateImpl.getPanelState$ar$ds(0);
                if (panelState$ar$ds.isOpen) {
                    appCompatDelegateImpl.closePanel(panelState$ar$ds, false);
                }
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder;
            if (menu instanceof MenuBuilder) {
                menuBuilder = (MenuBuilder) menu;
            } else {
                menuBuilder = null;
            }
            if (i == 0) {
                if (menuBuilder == null) {
                    return false;
                }
                i = 0;
            }
            if (menuBuilder != null) {
                menuBuilder.mOverrideVisibleItems = true;
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (menuBuilder != null) {
                menuBuilder.mOverrideVisibleItems = false;
            }
            return onPreparePanel;
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
            MenuBuilder menuBuilder = AppCompatDelegateImpl.this.getPanelState$ar$ds(0).menu;
            if (menuBuilder != null) {
                super.onProvideKeyboardShortcuts(list, menuBuilder, i);
            } else {
                super.onProvideKeyboardShortcuts(list, menu, i);
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        @Override // android.support.v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public final android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.mHandleNativeActionModes && i == 0) {
                SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(appCompatDelegateImpl.mContext, callback);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                android.support.v7.view.ActionMode actionMode = appCompatDelegateImpl2.mActionMode;
                if (actionMode != null) {
                    actionMode.finish();
                }
                ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = new ActionModeCallbackWrapperV9(callbackWrapper);
                ActionBar supportActionBar = appCompatDelegateImpl2.getSupportActionBar();
                if (supportActionBar != null) {
                    WindowDecorActionBar windowDecorActionBar = (WindowDecorActionBar) supportActionBar;
                    WindowDecorActionBar.ActionModeImpl actionModeImpl = windowDecorActionBar.mActionMode;
                    if (actionModeImpl != null) {
                        actionModeImpl.finish();
                    }
                    windowDecorActionBar.mOverlayLayout.setHideOnContentScrollEnabled(false);
                    windowDecorActionBar.mContextView.killMode();
                    WindowDecorActionBar.ActionModeImpl actionModeImpl2 = new WindowDecorActionBar.ActionModeImpl(windowDecorActionBar.mContextView.getContext(), actionModeCallbackWrapperV9);
                    actionModeImpl2.mMenu.stopDispatchingItemsChanged();
                    try {
                        if (actionModeImpl2.mCallback.onCreateActionMode(actionModeImpl2, actionModeImpl2.mMenu)) {
                            windowDecorActionBar.mActionMode = actionModeImpl2;
                            actionModeImpl2.invalidate();
                            windowDecorActionBar.mContextView.initForMode(actionModeImpl2);
                            windowDecorActionBar.animateToMode(true);
                        } else {
                            actionModeImpl2 = null;
                        }
                        appCompatDelegateImpl2.mActionMode = actionModeImpl2;
                    } finally {
                        actionModeImpl2.mMenu.startDispatchingItemsChanged();
                    }
                }
                if (appCompatDelegateImpl2.mActionMode == null) {
                    appCompatDelegateImpl2.mActionMode = appCompatDelegateImpl2.startSupportActionModeFromWindow(actionModeCallbackWrapperV9);
                }
                appCompatDelegateImpl2.updateBackInvokedCallbackState();
                android.support.v7.view.ActionMode actionMode2 = appCompatDelegateImpl2.mActionMode;
                if (actionMode2 != null) {
                    return callbackWrapper.getActionModeWrapper(actionMode2);
                }
                return null;
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AutoBatteryNightModeManager extends AutoNightModeManager {
        public final PowerManager mPowerManager;

        public AutoBatteryNightModeManager(Context context) {
            super();
            this.mPowerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // android.support.v7.app.AppCompatDelegateImpl.AutoNightModeManager
        public final IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // android.support.v7.app.AppCompatDelegateImpl.AutoNightModeManager
        public final int getApplyableNightMode() {
            throw null;
        }

        @Override // android.support.v7.app.AppCompatDelegateImpl.AutoNightModeManager
        public final void onChange() {
            AppCompatDelegateImpl.this.applyDayNight$ar$ds();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class AutoNightModeManager {
        private BroadcastReceiver mReceiver;

        /* compiled from: PG */
        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$AutoNightModeManager$1 */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 extends BroadcastReceiver {
            public AnonymousClass1() {
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                AutoNightModeManager.this.onChange();
            }
        }

        public AutoNightModeManager() {
        }

        final void cleanup() {
            BroadcastReceiver broadcastReceiver = this.mReceiver;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.mContext.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.mReceiver = null;
            }
        }

        public abstract IntentFilter createIntentFilterForBroadcastReceiver();

        public abstract int getApplyableNightMode();

        public abstract void onChange();

        final void setup() {
            cleanup();
            IntentFilter createIntentFilterForBroadcastReceiver = createIntentFilterForBroadcastReceiver();
            if (createIntentFilterForBroadcastReceiver.countActions() == 0) {
                return;
            }
            if (this.mReceiver == null) {
                this.mReceiver = new BroadcastReceiver() { // from class: android.support.v7.app.AppCompatDelegateImpl.AutoNightModeManager.1
                    public AnonymousClass1() {
                    }

                    @Override // android.content.BroadcastReceiver
                    public final void onReceive(Context context, Intent intent) {
                        AutoNightModeManager.this.onChange();
                    }
                };
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.mContext.registerReceiver(this.mReceiver, createIntentFilterForBroadcastReceiver);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AutoTimeNightModeManager extends AutoNightModeManager {
        private final OptionalMethod mTwilightManager$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

        public AutoTimeNightModeManager(OptionalMethod optionalMethod) {
            super();
            this.mTwilightManager$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = optionalMethod;
        }

        @Override // android.support.v7.app.AppCompatDelegateImpl.AutoNightModeManager
        public final IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // android.support.v7.app.AppCompatDelegateImpl.AutoNightModeManager
        public final int getApplyableNightMode() {
            Location location;
            long j;
            boolean z;
            boolean z2;
            OptionalMethod optionalMethod = this.mTwilightManager$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            long j2 = ((TwilightManager$TwilightState) optionalMethod.OptionalMethod$ar$returnType).nextUpdate;
            long currentTimeMillis = System.currentTimeMillis();
            Object obj = optionalMethod.OptionalMethod$ar$returnType;
            if (j2 > currentTimeMillis) {
                z2 = ((TwilightManager$TwilightState) obj).isNight;
            } else {
                Location location2 = null;
                if (ActivityCompat.Api32Impl.checkSelfPermission((Context) optionalMethod.OptionalMethod$ar$methodName, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                    location = optionalMethod.getLastKnownLocationForProvider("network");
                } else {
                    location = null;
                }
                if (ActivityCompat.Api32Impl.checkSelfPermission((Context) optionalMethod.OptionalMethod$ar$methodName, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                    location2 = optionalMethod.getLastKnownLocationForProvider("gps");
                }
                if (location2 == null || location == null ? location2 != null : location2.getTime() > location.getTime()) {
                    location = location2;
                }
                if (location != null) {
                    Object obj2 = optionalMethod.OptionalMethod$ar$returnType;
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (TwilightCalculator.sInstance == null) {
                        TwilightCalculator.sInstance = new TwilightCalculator();
                    }
                    TwilightCalculator twilightCalculator = TwilightCalculator.sInstance;
                    twilightCalculator.calculateTwilight(currentTimeMillis2 - 86400000, location.getLatitude(), location.getLongitude());
                    twilightCalculator.calculateTwilight(currentTimeMillis2, location.getLatitude(), location.getLongitude());
                    int i = twilightCalculator.state;
                    long j3 = twilightCalculator.sunrise;
                    long j4 = twilightCalculator.sunset;
                    twilightCalculator.calculateTwilight(currentTimeMillis2 + 86400000, location.getLatitude(), location.getLongitude());
                    long j5 = twilightCalculator.sunrise;
                    if (j3 != -1 && j4 != -1) {
                        if (currentTimeMillis2 <= j4) {
                            if (currentTimeMillis2 > j3) {
                                j5 = j4;
                            } else {
                                j5 = j3;
                            }
                        }
                        j = j5 + 60000;
                    } else {
                        j = currentTimeMillis2 + 43200000;
                    }
                    if (1 != i) {
                        z = false;
                    } else {
                        z = true;
                    }
                    TwilightManager$TwilightState twilightManager$TwilightState = (TwilightManager$TwilightState) obj2;
                    twilightManager$TwilightState.isNight = z;
                    twilightManager$TwilightState.nextUpdate = j;
                    z2 = ((TwilightManager$TwilightState) obj).isNight;
                } else {
                    int i2 = Calendar.getInstance().get(11);
                    if (i2 >= 6 && i2 < 22) {
                        return 1;
                    }
                    return 2;
                }
            }
            if (!z2) {
                return 1;
            }
            return 2;
        }

        @Override // android.support.v7.app.AppCompatDelegateImpl.AutoNightModeManager
        public final void onChange() {
            AppCompatDelegateImpl.this.applyDayNight$ar$ds();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (!AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent) && !super.dispatchKeyEvent(keyEvent)) {
                return false;
            }
            return true;
        }

        @Override // android.view.ViewGroup
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                    AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                    appCompatDelegateImpl.closePanel(appCompatDelegateImpl.getPanelState$ar$ds(0), true);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public final void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatDelegate.Api33Impl.getDrawable(getContext(), i));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        final int featureId;
        Bundle frozenActionViewState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        public MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView = false;
        boolean refreshMenuContent;
        View shownPanelView;
        int windowAnimations;

        public PanelFeatureState(int i) {
            this.featureId = i;
        }

        final void setMenu(MenuBuilder menuBuilder) {
            ListMenuPresenter listMenuPresenter;
            MenuBuilder menuBuilder2 = this.menu;
            if (menuBuilder != menuBuilder2) {
                if (menuBuilder2 != null) {
                    menuBuilder2.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menuBuilder;
                if (menuBuilder != null && (listMenuPresenter = this.listMenuPresenter) != null) {
                    menuBuilder.addMenuPresenter(listMenuPresenter);
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ Object AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0;
        private final /* synthetic */ int switching_field;

        public PanelMenuPresenterCallback(Object obj, int i) {
            this.switching_field = i;
            this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0 = obj;
        }

        @Override // android.support.v7.view.menu.MenuPresenter.Callback
        public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder menuBuilder2;
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (menuBuilder instanceof SubMenuBuilder) {
                        menuBuilder.getRootMenu().close(false);
                    }
                    MenuPresenter.Callback callback = ((BaseMenuPresenter) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0).mCallback;
                    if (callback != null) {
                        callback.onCloseMenu(menuBuilder, z);
                        return;
                    }
                    return;
                }
                ((AppCompatDelegateImpl) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0).checkCloseActionMenu(menuBuilder);
                return;
            }
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            if (rootMenu != menuBuilder) {
                menuBuilder2 = rootMenu;
            } else {
                menuBuilder2 = menuBuilder;
            }
            PanelFeatureState findMenuPanel = ((AppCompatDelegateImpl) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0).findMenuPanel(menuBuilder2);
            if (findMenuPanel != null) {
                if (rootMenu != menuBuilder) {
                    ((AppCompatDelegateImpl) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0).callOnPanelClosed(findMenuPanel.featureId, findMenuPanel, rootMenu);
                    ((AppCompatDelegateImpl) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0).closePanel(findMenuPanel, true);
                    return;
                }
                ((AppCompatDelegateImpl) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0).closePanel(findMenuPanel, z);
            }
        }

        @Override // android.support.v7.view.menu.MenuPresenter.Callback
        public final boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback windowCallback;
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    Object obj = this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0;
                    ActionMenuPresenter actionMenuPresenter = (ActionMenuPresenter) obj;
                    if (menuBuilder != actionMenuPresenter.mMenu) {
                        actionMenuPresenter.mOpenSubMenuId = ((SubMenuBuilder) menuBuilder).mItem.mId;
                        MenuPresenter.Callback callback = ((BaseMenuPresenter) obj).mCallback;
                        if (callback != null) {
                            return callback.onOpenSubMenu(menuBuilder);
                        }
                        return false;
                    }
                    return false;
                }
                Window.Callback windowCallback2 = ((AppCompatDelegateImpl) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0).getWindowCallback();
                if (windowCallback2 != null) {
                    windowCallback2.onMenuOpened(108, menuBuilder);
                }
                return true;
            }
            if (menuBuilder == menuBuilder.getRootMenu()) {
                AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0;
                if (appCompatDelegateImpl.mHasActionBar && (windowCallback = appCompatDelegateImpl.getWindowCallback()) != null && !((AppCompatDelegateImpl) this.AppCompatDelegateImpl$PanelMenuPresenterCallback$ar$this$0).mDestroyed) {
                    windowCallback.onMenuOpened(108, menuBuilder);
                }
            }
            return true;
        }
    }

    public AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback, Object obj) {
        AppCompatActivity appCompatActivity = null;
        this.mLocalNightMode = -100;
        this.mContext = context;
        this.mAppCompatCallback = appCompatCallback;
        this.mHost = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (context instanceof AppCompatActivity) {
                        appCompatActivity = (AppCompatActivity) context;
                        break;
                    } else if (!(context instanceof ContextWrapper)) {
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                } else {
                    break;
                }
            }
            if (appCompatActivity != null) {
                this.mLocalNightMode = ((AppCompatDelegateImpl) appCompatActivity.getDelegate()).mLocalNightMode;
            }
        }
        if (this.mLocalNightMode == -100) {
            SimpleArrayMap simpleArrayMap = sLocalNightModes;
            Integer num = (Integer) simpleArrayMap.get(this.mHost.getClass().getName());
            if (num != null) {
                this.mLocalNightMode = num.intValue();
                simpleArrayMap.remove(this.mHost.getClass().getName());
            }
        }
        if (window != null) {
            attachToWindow(window);
        }
        AppCompatDrawableManager.preload();
    }

    private final boolean applyApplicationSpecificConfig(boolean z) {
        return applyApplicationSpecificConfig(z, true);
    }

    private final void attachToWindow(Window window) {
        if (this.mWindow == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof AppCompatWindowCallback)) {
                AppCompatWindowCallback appCompatWindowCallback = new AppCompatWindowCallback(callback);
                this.mAppCompatWindowCallback = appCompatWindowCallback;
                window.setCallback(appCompatWindowCallback);
                ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging(this.mContext, null, sWindowBackgroundStyleable);
                Drawable drawableIfKnown = obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging.getDrawableIfKnown(0);
                if (drawableIfKnown != null) {
                    window.setBackgroundDrawable(drawableIfKnown);
                }
                obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging.recycle();
                this.mWindow = window;
                if (Build.VERSION.SDK_INT >= 33 && this.mDispatcher == null) {
                    Object obj = this.mHost;
                    if ((obj instanceof Activity) && ((Activity) obj).getWindow() != null) {
                        this.mDispatcher = Api33Impl.getOnBackInvokedDispatcher((Activity) this.mHost);
                    } else {
                        this.mDispatcher = null;
                    }
                    updateBackInvokedCallbackState();
                    return;
                }
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    private final void ensureWindow() {
        if (this.mWindow == null) {
            Object obj = this.mHost;
            if (obj instanceof Activity) {
                attachToWindow(((Activity) obj).getWindow());
            }
        }
        if (this.mWindow != null) {
        } else {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private final AutoNightModeManager getAutoBatteryNightModeManager(Context context) {
        if (this.mAutoBatteryNightModeManager == null) {
            this.mAutoBatteryNightModeManager = new AutoBatteryNightModeManager(context);
        }
        return this.mAutoBatteryNightModeManager;
    }

    private final AutoNightModeManager getAutoTimeNightModeManager(Context context) {
        if (this.mAutoTimeNightModeManager == null) {
            if (OptionalMethod.sInstance$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging == null) {
                Context applicationContext = context.getApplicationContext();
                OptionalMethod.sInstance$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.mAutoTimeNightModeManager = new AutoTimeNightModeManager(OptionalMethod.sInstance$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        }
        return this.mAutoTimeNightModeManager;
    }

    private final void invalidatePanelMenu(int i) {
        this.mInvalidatePanelMenuFeatures = (1 << i) | this.mInvalidatePanelMenuFeatures;
        if (!this.mInvalidatePanelMenuPosted) {
            View decorView = this.mWindow.getDecorView();
            Runnable runnable = this.mInvalidatePanelMenuRunnable;
            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            decorView.postOnAnimation(runnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x014c, code lost:
    
        if (r13.shownPanelView != null) goto L170;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void openPanel(android.support.v7.app.AppCompatDelegateImpl.PanelFeatureState r13, android.view.KeyEvent r14) {
        /*
            Method dump skipped, instructions count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.openPanel(android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    private final void throwFeatureRequestIfSubDecorInstalled() {
        if (!this.mSubDecorInstalled) {
        } else {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(R.id.content)).addView(view, layoutParams);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    public final void applyDayNight$ar$ds() {
        applyApplicationSpecificConfig(true);
    }

    public final LocaleListCompat calculateApplicationLocales(Context context) {
        LocaleListCompat localeListCompat;
        LocaleListCompat create;
        Locale locale;
        if (Build.VERSION.SDK_INT >= 33 || (localeListCompat = AppCompatDelegate.sRequestedAppLocales) == null) {
            return null;
        }
        LocaleListCompat locales = Api24Impl.getLocales(context.getApplicationContext().getResources().getConfiguration());
        if (localeListCompat.isEmpty()) {
            create = LocaleListCompat.sEmptyLocaleList;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int i = 0; i < localeListCompat.size() + locales.size(); i++) {
                if (i < localeListCompat.size()) {
                    locale = localeListCompat.get(i);
                } else {
                    locale = locales.get(i - localeListCompat.size());
                }
                if (locale != null) {
                    linkedHashSet.add(locale);
                }
            }
            create = LocaleListCompat.create((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]));
        }
        if (create.isEmpty()) {
            return locales;
        }
        return create;
    }

    public final int calculateNightMode() {
        int i = this.mLocalNightMode;
        if (i != -100) {
            return i;
        }
        return AppCompatDelegate.sDefaultNightMode;
    }

    final void callOnPanelClosed(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            menu = panelFeatureState.menu;
        }
        if (panelFeatureState.isOpen && !this.mDestroyed) {
            AppCompatWindowCallback appCompatWindowCallback = this.mAppCompatWindowCallback;
            Window.Callback callback = this.mWindow.getCallback();
            try {
                appCompatWindowCallback.mOnPanelClosedBypassEnabled = true;
                callback.onPanelClosed(i, menu);
            } finally {
                appCompatWindowCallback.mOnPanelClosedBypassEnabled = false;
            }
        }
    }

    final void checkCloseActionMenu(MenuBuilder menuBuilder) {
        if (this.mClosingActionMenu) {
            return;
        }
        this.mClosingActionMenu = true;
        this.mDecorContentParent.dismissPopups();
        Window.Callback windowCallback = getWindowCallback();
        if (windowCallback != null && !this.mDestroyed) {
            windowCallback.onPanelClosed(108, menuBuilder);
        }
        this.mClosingActionMenu = false;
    }

    final void closePanel(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        DecorContentParent decorContentParent;
        if (z && panelFeatureState.featureId == 0 && (decorContentParent = this.mDecorContentParent) != null && decorContentParent.isOverflowMenuShowing()) {
            checkCloseActionMenu(panelFeatureState.menu);
            return;
        }
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        if (windowManager != null && panelFeatureState.isOpen && (viewGroup = panelFeatureState.decorView) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, null);
            }
        }
        panelFeatureState.isPrepared = false;
        panelFeatureState.isHandled = false;
        panelFeatureState.isOpen = false;
        panelFeatureState.shownPanelView = null;
        panelFeatureState.refreshDecorView = true;
        if (this.mPreparedPanel == panelFeatureState) {
            this.mPreparedPanel = null;
        }
        if (panelFeatureState.featureId == 0) {
            updateBackInvokedCallbackState();
        }
    }

    public final Configuration createOverrideAppConfiguration(Context context, int i, LocaleListCompat localeListCompat, Configuration configuration, boolean z) {
        int i2;
        if (i != 1) {
            if (i != 2) {
                if (z) {
                    i2 = 0;
                } else {
                    i2 = context.getApplicationContext().getResources().getConfiguration().uiMode & 48;
                }
            } else {
                i2 = 32;
            }
        } else {
            i2 = 16;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & (-49));
        if (localeListCompat != null) {
            Api24Impl.setLocales(configuration2, localeListCompat);
        }
        return configuration2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x00c2, code lost:
    
        if (r10.equals("Spinner") != false) goto L194;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View createView$ar$ds(java.lang.String r10, android.content.Context r11, android.util.AttributeSet r12) {
        /*
            Method dump skipped, instructions count: 650
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.createView$ar$ds(java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final boolean dispatchKeyEvent(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    public final void doInvalidatePanelMenu(int i) {
        PanelFeatureState panelState$ar$ds = getPanelState$ar$ds(i);
        if (panelState$ar$ds.menu != null) {
            Bundle bundle = new Bundle();
            panelState$ar$ds.menu.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                panelState$ar$ds.frozenActionViewState = bundle;
            }
            panelState$ar$ds.menu.stopDispatchingItemsChanged();
            panelState$ar$ds.menu.clear();
        }
        panelState$ar$ds.refreshMenuContent = true;
        panelState$ar$ds.refreshDecorView = true;
        if ((i == 108 || i == 0) && this.mDecorContentParent != null) {
            PanelFeatureState panelState$ar$ds2 = getPanelState$ar$ds(0);
            panelState$ar$ds2.isPrepared = false;
            preparePanel(panelState$ar$ds2, null);
        }
    }

    public final void endOnGoingFadeAnimation() {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = this.mFadeAnim$ar$class_merging$ar$class_merging;
        if (collectionItemInfoCompat != null) {
            collectionItemInfoCompat.cancel();
        }
    }

    public final void ensureSubDecor() {
        ViewGroup viewGroup;
        CharSequence charSequence;
        Context context;
        if (!this.mSubDecorInstalled) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
            if (obtainStyledAttributes.hasValue(BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL)) {
                if (obtainStyledAttributes.getBoolean(BrailleInputEvent.CMD_NEXT_READING_CONTROL, false)) {
                    requestWindowFeature$ar$ds(1);
                } else if (obtainStyledAttributes.getBoolean(BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL, false)) {
                    requestWindowFeature$ar$ds(108);
                }
                if (obtainStyledAttributes.getBoolean(BrailleInputEvent.CMD_OPEN_TALKBACK_MENU, false)) {
                    requestWindowFeature$ar$ds(109);
                }
                if (obtainStyledAttributes.getBoolean(BrailleInputEvent.CMD_SWITCH_TO_NEXT_INPUT_LANGUAGE, false)) {
                    requestWindowFeature$ar$ds(10);
                }
                this.mIsFloating = obtainStyledAttributes.getBoolean(0, false);
                obtainStyledAttributes.recycle();
                ensureWindow();
                this.mWindow.getDecorView();
                LayoutInflater from = LayoutInflater.from(this.mContext);
                if (!this.mWindowNoTitle) {
                    if (this.mIsFloating) {
                        viewGroup = (ViewGroup) from.inflate(com.google.android.marvin.talkback.R.layout.abc_dialog_title_material, (ViewGroup) null);
                        this.mOverlayActionBar = false;
                        this.mHasActionBar = false;
                    } else if (this.mHasActionBar) {
                        TypedValue typedValue = new TypedValue();
                        this.mContext.getTheme().resolveAttribute(com.google.android.marvin.talkback.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            context = new ContextThemeWrapper(this.mContext, typedValue.resourceId);
                        } else {
                            context = this.mContext;
                        }
                        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(com.google.android.marvin.talkback.R.layout.abc_screen_toolbar, (ViewGroup) null);
                        DecorContentParent decorContentParent = (DecorContentParent) viewGroup.findViewById(com.google.android.marvin.talkback.R.id.decor_content_parent);
                        this.mDecorContentParent = decorContentParent;
                        decorContentParent.setWindowCallback(getWindowCallback());
                        if (this.mOverlayActionBar) {
                            this.mDecorContentParent.initFeature(109);
                        }
                        if (this.mFeatureProgress) {
                            this.mDecorContentParent.initFeature(2);
                        }
                        if (this.mFeatureIndeterminateProgress) {
                            this.mDecorContentParent.initFeature(5);
                        }
                    } else {
                        viewGroup = null;
                    }
                } else {
                    viewGroup = this.mOverlayActionMode ? (ViewGroup) from.inflate(com.google.android.marvin.talkback.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) from.inflate(com.google.android.marvin.talkback.R.layout.abc_screen_simple, (ViewGroup) null);
                }
                if (viewGroup != null) {
                    ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(viewGroup, new OnApplyWindowInsetsListener() { // from class: android.support.v7.app.AppCompatDelegateImpl.3
                        public AnonymousClass3() {
                        }

                        @Override // androidx.core.view.OnApplyWindowInsetsListener
                        public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                            boolean z;
                            View view2;
                            WindowInsetsCompat windowInsetsCompat2;
                            boolean z2;
                            int systemWindowInsetLeft;
                            int systemWindowInsetRight;
                            int color;
                            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
                            int systemWindowInsetTop2 = windowInsetsCompat.getSystemWindowInsetTop();
                            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                            ActionBarContextView actionBarContextView = appCompatDelegateImpl.mActionModeView;
                            int i = 8;
                            if (actionBarContextView != null && (actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) appCompatDelegateImpl.mActionModeView.getLayoutParams();
                                if (appCompatDelegateImpl.mActionModeView.isShown()) {
                                    if (appCompatDelegateImpl.mTempRect1 == null) {
                                        appCompatDelegateImpl.mTempRect1 = new Rect();
                                        appCompatDelegateImpl.mTempRect2 = new Rect();
                                    }
                                    Rect rect = appCompatDelegateImpl.mTempRect1;
                                    Rect rect2 = appCompatDelegateImpl.mTempRect2;
                                    rect.set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                                    ViewGroup viewGroup2 = appCompatDelegateImpl.mSubDecor;
                                    if (Build.VERSION.SDK_INT >= 29) {
                                        ViewUtils.Api29Impl.computeFitSystemWindows(viewGroup2, rect, rect2);
                                    } else {
                                        if (!ViewUtils.sInitComputeFitSystemWindowsMethod) {
                                            ViewUtils.sInitComputeFitSystemWindowsMethod = true;
                                            try {
                                                ViewUtils.sComputeFitSystemWindowsMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                                                if (!ViewUtils.sComputeFitSystemWindowsMethod.isAccessible()) {
                                                    ViewUtils.sComputeFitSystemWindowsMethod.setAccessible(true);
                                                }
                                            } catch (NoSuchMethodException unused) {
                                            }
                                        }
                                        Method method = ViewUtils.sComputeFitSystemWindowsMethod;
                                        if (method != null) {
                                            try {
                                                method.invoke(viewGroup2, rect, rect2);
                                            } catch (Exception unused2) {
                                            }
                                        }
                                    }
                                    int i2 = rect.top;
                                    int i3 = rect.left;
                                    int i4 = rect.right;
                                    WindowInsetsCompat rootWindowInsets = ViewCompat.Api23Impl.getRootWindowInsets(appCompatDelegateImpl.mSubDecor);
                                    if (rootWindowInsets == null) {
                                        systemWindowInsetLeft = 0;
                                    } else {
                                        systemWindowInsetLeft = rootWindowInsets.getSystemWindowInsetLeft();
                                    }
                                    if (rootWindowInsets == null) {
                                        systemWindowInsetRight = 0;
                                    } else {
                                        systemWindowInsetRight = rootWindowInsets.getSystemWindowInsetRight();
                                    }
                                    if (marginLayoutParams.topMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4) {
                                        z2 = false;
                                    } else {
                                        marginLayoutParams.topMargin = i2;
                                        marginLayoutParams.leftMargin = i3;
                                        marginLayoutParams.rightMargin = i4;
                                        z2 = true;
                                    }
                                    if (i2 > 0 && appCompatDelegateImpl.mStatusGuard == null) {
                                        appCompatDelegateImpl.mStatusGuard = new View(appCompatDelegateImpl.mContext);
                                        appCompatDelegateImpl.mStatusGuard.setVisibility(8);
                                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                                        layoutParams.leftMargin = systemWindowInsetLeft;
                                        layoutParams.rightMargin = systemWindowInsetRight;
                                        appCompatDelegateImpl.mSubDecor.addView(appCompatDelegateImpl.mStatusGuard, -1, layoutParams);
                                    } else {
                                        View view3 = appCompatDelegateImpl.mStatusGuard;
                                        if (view3 != null) {
                                            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view3.getLayoutParams();
                                            if (marginLayoutParams2.height != marginLayoutParams.topMargin || marginLayoutParams2.leftMargin != systemWindowInsetLeft || marginLayoutParams2.rightMargin != systemWindowInsetRight) {
                                                marginLayoutParams2.height = marginLayoutParams.topMargin;
                                                marginLayoutParams2.leftMargin = systemWindowInsetLeft;
                                                marginLayoutParams2.rightMargin = systemWindowInsetRight;
                                                appCompatDelegateImpl.mStatusGuard.setLayoutParams(marginLayoutParams2);
                                            }
                                        }
                                    }
                                    View view4 = appCompatDelegateImpl.mStatusGuard;
                                    if (view4 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (z && view4.getVisibility() != 0) {
                                        View view5 = appCompatDelegateImpl.mStatusGuard;
                                        if ((view5.getWindowSystemUiVisibility() & 8192) != 0) {
                                            color = ContextCompat$Api23Impl.getColor(appCompatDelegateImpl.mContext, com.google.android.marvin.talkback.R.color.abc_decor_view_status_guard_light);
                                        } else {
                                            color = ContextCompat$Api23Impl.getColor(appCompatDelegateImpl.mContext, com.google.android.marvin.talkback.R.color.abc_decor_view_status_guard);
                                        }
                                        view5.setBackgroundColor(color);
                                    }
                                    if (!appCompatDelegateImpl.mOverlayActionMode && z) {
                                        systemWindowInsetTop2 = 0;
                                    }
                                } else if (marginLayoutParams.topMargin != 0) {
                                    marginLayoutParams.topMargin = 0;
                                    z2 = true;
                                    z = false;
                                } else {
                                    z2 = false;
                                    z = false;
                                }
                                if (z2) {
                                    appCompatDelegateImpl.mActionModeView.setLayoutParams(marginLayoutParams);
                                }
                            } else {
                                z = false;
                            }
                            View view6 = appCompatDelegateImpl.mStatusGuard;
                            if (view6 != null) {
                                if (true == z) {
                                    i = 0;
                                }
                                view6.setVisibility(i);
                            }
                            if (systemWindowInsetTop != systemWindowInsetTop2) {
                                int systemWindowInsetLeft2 = windowInsetsCompat.getSystemWindowInsetLeft();
                                int systemWindowInsetRight2 = windowInsetsCompat.getSystemWindowInsetRight();
                                int systemWindowInsetBottom = windowInsetsCompat.getSystemWindowInsetBottom();
                                AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(windowInsetsCompat);
                                collectionItemInfoCompat.setSystemWindowInsets$ar$ds(Insets.of(systemWindowInsetLeft2, systemWindowInsetTop2, systemWindowInsetRight2, systemWindowInsetBottom));
                                windowInsetsCompat2 = collectionItemInfoCompat.m31build();
                                view2 = view;
                            } else {
                                view2 = view;
                                windowInsetsCompat2 = windowInsetsCompat;
                            }
                            return ViewCompat.onApplyWindowInsets(view2, windowInsetsCompat2);
                        }
                    });
                    if (this.mDecorContentParent == null) {
                        this.mTitleView = (TextView) viewGroup.findViewById(com.google.android.marvin.talkback.R.id.title);
                    }
                    try {
                        Method method = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", null);
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        method.invoke(viewGroup, null);
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                    }
                    ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(com.google.android.marvin.talkback.R.id.action_bar_activity_content);
                    ViewGroup viewGroup2 = (ViewGroup) this.mWindow.findViewById(R.id.content);
                    if (viewGroup2 != null) {
                        while (viewGroup2.getChildCount() > 0) {
                            View childAt = viewGroup2.getChildAt(0);
                            viewGroup2.removeViewAt(0);
                            contentFrameLayout.addView(childAt);
                        }
                        viewGroup2.setId(-1);
                        contentFrameLayout.setId(R.id.content);
                        if (viewGroup2 instanceof FrameLayout) {
                            ((FrameLayout) viewGroup2).setForeground(null);
                        }
                    }
                    this.mWindow.setContentView(viewGroup);
                    contentFrameLayout.mAttachListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
                    this.mSubDecor = viewGroup;
                    Object obj = this.mHost;
                    if (obj instanceof Activity) {
                        charSequence = ((Activity) obj).getTitle();
                    } else {
                        charSequence = this.mTitle;
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        DecorContentParent decorContentParent2 = this.mDecorContentParent;
                        if (decorContentParent2 != null) {
                            decorContentParent2.setWindowTitle(charSequence);
                        } else {
                            ActionBar actionBar = this.mActionBar;
                            if (actionBar != null) {
                                actionBar.setWindowTitle(charSequence);
                            } else {
                                TextView textView = this.mTitleView;
                                if (textView != null) {
                                    textView.setText(charSequence);
                                }
                            }
                        }
                    }
                    ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.mSubDecor.findViewById(R.id.content);
                    View decorView = this.mWindow.getDecorView();
                    contentFrameLayout2.mDecorPadding.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
                    if (contentFrameLayout2.isLaidOut()) {
                        contentFrameLayout2.requestLayout();
                    }
                    TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
                    if (contentFrameLayout2.mMinWidthMajor == null) {
                        contentFrameLayout2.mMinWidthMajor = new TypedValue();
                    }
                    obtainStyledAttributes2.getValue(BrailleInputEvent.CMD_TOGGLE_VOICE_FEEDBACK, contentFrameLayout2.mMinWidthMajor);
                    if (contentFrameLayout2.mMinWidthMinor == null) {
                        contentFrameLayout2.mMinWidthMinor = new TypedValue();
                    }
                    obtainStyledAttributes2.getValue(BrailleInputEvent.CMD_PREVIOUS_READING_CONTROL, contentFrameLayout2.mMinWidthMinor);
                    if (obtainStyledAttributes2.hasValue(BrailleInputEvent.CMD_TALKBACK_SETTINGS)) {
                        if (contentFrameLayout2.mFixedWidthMajor == null) {
                            contentFrameLayout2.mFixedWidthMajor = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(BrailleInputEvent.CMD_TALKBACK_SETTINGS, contentFrameLayout2.mFixedWidthMajor);
                    }
                    if (obtainStyledAttributes2.hasValue(BrailleInputEvent.CMD_TURN_OFF_BRAILLE_DISPLAY)) {
                        if (contentFrameLayout2.mFixedWidthMinor == null) {
                            contentFrameLayout2.mFixedWidthMinor = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(BrailleInputEvent.CMD_TURN_OFF_BRAILLE_DISPLAY, contentFrameLayout2.mFixedWidthMinor);
                    }
                    if (obtainStyledAttributes2.hasValue(BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE)) {
                        if (contentFrameLayout2.mFixedHeightMajor == null) {
                            contentFrameLayout2.mFixedHeightMajor = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE, contentFrameLayout2.mFixedHeightMajor);
                    }
                    if (obtainStyledAttributes2.hasValue(BrailleInputEvent.CMD_BRAILLE_DISPLAY_SETTINGS)) {
                        if (contentFrameLayout2.mFixedHeightMinor == null) {
                            contentFrameLayout2.mFixedHeightMinor = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(BrailleInputEvent.CMD_BRAILLE_DISPLAY_SETTINGS, contentFrameLayout2.mFixedHeightMinor);
                    }
                    obtainStyledAttributes2.recycle();
                    contentFrameLayout2.requestLayout();
                    this.mSubDecorInstalled = true;
                    PanelFeatureState panelState$ar$ds = getPanelState$ar$ds(0);
                    if (!this.mDestroyed && panelState$ar$ds.menu == null) {
                        invalidatePanelMenu(108);
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }");
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    final PanelFeatureState findMenuPanel(Menu menu) {
        int i;
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        if (panelFeatureStateArr != null) {
            i = panelFeatureStateArr.length;
        } else {
            i = 0;
        }
        for (int i2 = 0; i2 < i; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.menu == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final View findViewById(int i) {
        ensureSubDecor();
        return this.mWindow.findViewById(i);
    }

    public final Context getActionBarThemedContext() {
        Context context;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            context = supportActionBar.getThemedContext();
        } else {
            context = null;
        }
        if (context == null) {
            return this.mContext;
        }
        return context;
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final Context getContextForDelegate() {
        return this.mContext;
    }

    public final PanelFeatureState getPanelState$ar$ds(int i) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.mPanels = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
        if (panelFeatureState == null) {
            PanelFeatureState panelFeatureState2 = new PanelFeatureState(i);
            panelFeatureStateArr[i] = panelFeatureState2;
            return panelFeatureState2;
        }
        return panelFeatureState;
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    public final void initWindowDecorActionBar() {
        ensureSubDecor();
        if (this.mHasActionBar && this.mActionBar == null) {
            Object obj = this.mHost;
            if (obj instanceof Activity) {
                this.mActionBar = new WindowDecorActionBar((Activity) obj, this.mOverlayActionBar);
            } else if (obj instanceof Dialog) {
                this.mActionBar = new WindowDecorActionBar((Dialog) obj);
            }
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
            }
        }
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (from.getFactory() == null) {
            from.setFactory2(this);
        } else {
            from.getFactory2();
        }
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void invalidateOptionsMenu() {
        if (this.mActionBar != null) {
            getSupportActionBar();
            invalidatePanelMenu(0);
        }
    }

    public final int mapNightMode(Context context, int i) {
        if (i == -100) {
            return -1;
        }
        if (i != -1) {
            if (i != 0) {
                if (i != 1 && i != 2) {
                    if (i == 3) {
                        if (!Api21Impl.isPowerSaveMode(((AutoBatteryNightModeManager) getAutoBatteryNightModeManager(context)).mPowerManager)) {
                            return 1;
                        }
                        return 2;
                    }
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
            } else {
                if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                return getAutoTimeNightModeManager(context).getApplyableNightMode();
            }
        }
        return i;
    }

    public final boolean onBackPressed() {
        WindowDecorActionBar windowDecorActionBar;
        ToolbarWidgetWrapper toolbarWidgetWrapper;
        boolean z = this.mLongPressBackDown;
        this.mLongPressBackDown = false;
        PanelFeatureState panelState$ar$ds = getPanelState$ar$ds(0);
        if (panelState$ar$ds.isOpen) {
            if (!z) {
                closePanel(panelState$ar$ds, true);
            }
            return true;
        }
        android.support.v7.view.ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || (toolbarWidgetWrapper = (windowDecorActionBar = (WindowDecorActionBar) supportActionBar).mDecorToolbar$ar$class_merging) == null || !toolbarWidgetWrapper.hasExpandedActionView()) {
            return false;
        }
        windowDecorActionBar.mDecorToolbar$ar$class_merging.collapseActionView();
        return true;
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void onConfigurationChanged$ar$ds() {
        ActionBar supportActionBar;
        if (this.mHasActionBar && this.mSubDecorInstalled && (supportActionBar = getSupportActionBar()) != null) {
            WindowDecorActionBar windowDecorActionBar = (WindowDecorActionBar) supportActionBar;
            windowDecorActionBar.setHasEmbeddedTabs(new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(windowDecorActionBar.mContext).hasEmbeddedTabs());
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        applyApplicationSpecificConfig(false, false);
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void onCreate$ar$ds() {
        String str;
        this.mBaseContextAttached = true;
        applyApplicationSpecificConfig(false);
        ensureWindow();
        Object obj = this.mHost;
        if (obj instanceof Activity) {
            try {
                str = ActivityCompat.Api28Impl.getParentActivityName((Activity) obj);
            } catch (IllegalArgumentException unused) {
                str = null;
            }
            if (str != null) {
                ActionBar actionBar = this.mActionBar;
                if (actionBar == null) {
                    this.mEnableDefaultActionBarUp = true;
                } else {
                    actionBar.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
            synchronized (AppCompatDelegate.sActivityDelegatesLock) {
                AppCompatDelegate.removeDelegateFromActives(this);
                AppCompatDelegate.sActivityDelegates.add(new WeakReference(this));
            }
        }
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        this.mCreated = true;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView$ar$ds(str, context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    @Override // android.support.v7.app.AppCompatDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onDestroy() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mHost
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = android.support.v7.app.AppCompatDelegate.sActivityDelegatesLock
            monitor-enter(r0)
            android.support.v7.app.AppCompatDelegate.removeDelegateFromActives(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r1
        L11:
            boolean r0 = r3.mInvalidatePanelMenuPosted
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.mWindow
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.mInvalidatePanelMenuRunnable
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.mDestroyed = r0
            int r0 = r3.mLocalNightMode
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.mHost
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            java.lang.Object r0 = r3.mHost
            androidx.collection.SimpleArrayMap r1 = android.support.v7.app.AppCompatDelegateImpl.sLocalNightModes
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            int r2 = r3.mLocalNightMode
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.put(r0, r2)
            goto L5c
        L4d:
            java.lang.Object r0 = r3.mHost
            androidx.collection.SimpleArrayMap r1 = android.support.v7.app.AppCompatDelegateImpl.sLocalNightModes
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            r1.remove(r0)
        L5c:
            android.support.v7.app.AppCompatDelegateImpl$AutoNightModeManager r0 = r3.mAutoTimeNightModeManager
            if (r0 == 0) goto L63
            r0.cleanup()
        L63:
            android.support.v7.app.AppCompatDelegateImpl$AutoNightModeManager r0 = r3.mAutoBatteryNightModeManager
            if (r0 == 0) goto L6a
            r0.cleanup()
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.onDestroy():void");
    }

    @Override // android.support.v7.view.menu.MenuBuilder.Callback
    public final boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        PanelFeatureState findMenuPanel;
        Window.Callback windowCallback = getWindowCallback();
        if (windowCallback != null && !this.mDestroyed && (findMenuPanel = findMenuPanel(menuBuilder.getRootMenu())) != null) {
            return windowCallback.onMenuItemSelected(findMenuPanel.featureId, menuItem);
        }
        return false;
    }

    @Override // android.support.v7.view.menu.MenuBuilder.Callback
    public final void onMenuModeChange(MenuBuilder menuBuilder) {
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null && decorContentParent.canShowOverflowMenu() && (!ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || this.mDecorContentParent.isOverflowMenuShowPending())) {
            Window.Callback windowCallback = getWindowCallback();
            if (this.mDecorContentParent.isOverflowMenuShowing()) {
                this.mDecorContentParent.hideOverflowMenu();
                if (!this.mDestroyed) {
                    windowCallback.onPanelClosed(108, getPanelState$ar$ds(0).menu);
                    return;
                }
                return;
            }
            if (windowCallback != null && !this.mDestroyed) {
                if (this.mInvalidatePanelMenuPosted && (1 & this.mInvalidatePanelMenuFeatures) != 0) {
                    this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                    this.mInvalidatePanelMenuRunnable.run();
                }
                PanelFeatureState panelState$ar$ds = getPanelState$ar$ds(0);
                MenuBuilder menuBuilder2 = panelState$ar$ds.menu;
                if (menuBuilder2 != null && !panelState$ar$ds.refreshMenuContent && windowCallback.onPreparePanel(0, panelState$ar$ds.createdPanelView, menuBuilder2)) {
                    windowCallback.onMenuOpened(108, panelState$ar$ds.menu);
                    this.mDecorContentParent.showOverflowMenu();
                    return;
                }
                return;
            }
            return;
        }
        PanelFeatureState panelState$ar$ds2 = getPanelState$ar$ds(0);
        panelState$ar$ds2.refreshDecorView = true;
        closePanel(panelState$ar$ds2, false);
        openPanel(panelState$ar$ds2, null);
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void onStop() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
    }

    public final boolean performPanelShortcut$ar$ds(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent) {
        MenuBuilder menuBuilder;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((!panelFeatureState.isPrepared && !preparePanel(panelFeatureState, keyEvent)) || (menuBuilder = panelFeatureState.menu) == null) {
            return false;
        }
        return menuBuilder.performShortcut(i, keyEvent, 1);
    }

    public final boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        boolean z;
        DecorContentParent decorContentParent;
        DecorContentParent decorContentParent2;
        Resources.Theme theme;
        int i;
        boolean z2;
        DecorContentParent decorContentParent3;
        DecorContentParent decorContentParent4;
        if (this.mDestroyed) {
            return false;
        }
        if (panelFeatureState.isPrepared) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            closePanel(panelFeatureState2, false);
        }
        Window.Callback windowCallback = getWindowCallback();
        if (windowCallback != null) {
            panelFeatureState.createdPanelView = windowCallback.onCreatePanelView(panelFeatureState.featureId);
        }
        int i2 = panelFeatureState.featureId;
        if (i2 != 0 && i2 != 108) {
            z = false;
        } else {
            z = true;
        }
        if (z && (decorContentParent4 = this.mDecorContentParent) != null) {
            decorContentParent4.setMenuPrepared();
        }
        if (panelFeatureState.createdPanelView == null) {
            MenuBuilder menuBuilder = panelFeatureState.menu;
            if (menuBuilder == null || panelFeatureState.refreshMenuContent) {
                if (menuBuilder == null) {
                    Context context = this.mContext;
                    int i3 = panelFeatureState.featureId;
                    if ((i3 == 0 || i3 == 108) && this.mDecorContentParent != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme2 = context.getTheme();
                        theme2.resolveAttribute(com.google.android.marvin.talkback.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            theme = context.getResources().newTheme();
                            theme.setTo(theme2);
                            theme.applyStyle(typedValue.resourceId, true);
                            theme.resolveAttribute(com.google.android.marvin.talkback.R.attr.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme2.resolveAttribute(com.google.android.marvin.talkback.R.attr.actionBarWidgetTheme, typedValue, true);
                            theme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (theme == null) {
                                theme = context.getResources().newTheme();
                                theme.setTo(theme2);
                            }
                            theme.applyStyle(typedValue.resourceId, true);
                        }
                        if (theme != null) {
                            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
                            contextThemeWrapper.getTheme().setTo(theme);
                            context = contextThemeWrapper;
                        }
                    }
                    MenuBuilder menuBuilder2 = new MenuBuilder(context);
                    menuBuilder2.mCallback = this;
                    panelFeatureState.setMenu(menuBuilder2);
                    if (panelFeatureState.menu == null) {
                        return false;
                    }
                }
                if (z && (decorContentParent2 = this.mDecorContentParent) != null) {
                    if (this.mActionMenuPresenterCallback$ar$class_merging == null) {
                        this.mActionMenuPresenterCallback$ar$class_merging = new PanelMenuPresenterCallback(this, 1);
                    }
                    decorContentParent2.setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback$ar$class_merging);
                }
                panelFeatureState.menu.stopDispatchingItemsChanged();
                if (!windowCallback.onCreatePanelMenu(panelFeatureState.featureId, panelFeatureState.menu)) {
                    panelFeatureState.setMenu(null);
                    if (z && (decorContentParent = this.mDecorContentParent) != null) {
                        decorContentParent.setMenu(null, this.mActionMenuPresenterCallback$ar$class_merging);
                    }
                    return false;
                }
                panelFeatureState.refreshMenuContent = false;
            }
            panelFeatureState.menu.stopDispatchingItemsChanged();
            Bundle bundle = panelFeatureState.frozenActionViewState;
            if (bundle != null) {
                panelFeatureState.menu.restoreActionViewStates(bundle);
                panelFeatureState.frozenActionViewState = null;
            }
            if (!windowCallback.onPreparePanel(0, panelFeatureState.createdPanelView, panelFeatureState.menu)) {
                if (z && (decorContentParent3 = this.mDecorContentParent) != null) {
                    decorContentParent3.setMenu(null, this.mActionMenuPresenterCallback$ar$class_merging);
                }
                panelFeatureState.menu.startDispatchingItemsChanged();
                return false;
            }
            if (keyEvent != null) {
                i = keyEvent.getDeviceId();
            } else {
                i = -1;
            }
            if (KeyCharacterMap.load(i).getKeyboardType() != 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            panelFeatureState.qwertyMode = z2;
            panelFeatureState.menu.setQwertyMode(z2);
            panelFeatureState.menu.startDispatchingItemsChanged();
        }
        panelFeatureState.isPrepared = true;
        panelFeatureState.isHandled = false;
        this.mPreparedPanel = panelFeatureState;
        return true;
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void requestWindowFeature$ar$ds(int i) {
        if (i == 8) {
            i = 108;
        } else if (i == 9) {
            i = 109;
        }
        if (this.mWindowNoTitle && i == 108) {
            return;
        }
        if (this.mHasActionBar && i == 1) {
            this.mHasActionBar = false;
        }
        if (i != 1) {
            if (i != 2) {
                if (i != 5) {
                    if (i != 10) {
                        if (i != 108) {
                            if (i != 109) {
                                this.mWindow.requestFeature(i);
                                return;
                            } else {
                                throwFeatureRequestIfSubDecorInstalled();
                                this.mOverlayActionBar = true;
                                return;
                            }
                        }
                        throwFeatureRequestIfSubDecorInstalled();
                        this.mHasActionBar = true;
                        return;
                    }
                    throwFeatureRequestIfSubDecorInstalled();
                    this.mOverlayActionMode = true;
                    return;
                }
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return;
            }
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureProgress = true;
            return;
        }
        throwFeatureRequestIfSubDecorInstalled();
        this.mWindowNoTitle = true;
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void setContentView(int i) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(i, viewGroup);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent == null) {
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setWindowTitle(charSequence);
                return;
            }
            TextView textView = this.mTitleView;
            if (textView != null) {
                textView.setText(charSequence);
                return;
            }
            return;
        }
        decorContentParent.setWindowTitle(charSequence);
    }

    public final boolean shouldAnimateActionModeView() {
        ViewGroup viewGroup;
        if (this.mSubDecorInstalled && (viewGroup = this.mSubDecor) != null && viewGroup.isLaidOut()) {
            return true;
        }
        return false;
    }

    final android.support.v7.view.ActionMode startSupportActionModeFromWindow(ActionMode.Callback callback) {
        Context context;
        endOnGoingFadeAnimation();
        android.support.v7.view.ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        if (this.mActionModeView == null) {
            if (this.mIsFloating) {
                TypedValue typedValue = new TypedValue();
                Resources.Theme theme = this.mContext.getTheme();
                theme.resolveAttribute(com.google.android.marvin.talkback.R.attr.actionBarTheme, typedValue, true);
                if (typedValue.resourceId != 0) {
                    Resources.Theme newTheme = this.mContext.getResources().newTheme();
                    newTheme.setTo(theme);
                    newTheme.applyStyle(typedValue.resourceId, true);
                    context = new ContextThemeWrapper(this.mContext, 0);
                    context.getTheme().setTo(newTheme);
                } else {
                    context = this.mContext;
                }
                this.mActionModeView = new ActionBarContextView(context);
                PopupWindow popupWindow = new PopupWindow(context, (AttributeSet) null, com.google.android.marvin.talkback.R.attr.actionModePopupWindowStyle);
                this.mActionModePopup = popupWindow;
                PopupWindowCompat$Api23Impl.setWindowLayoutType(popupWindow, 2);
                this.mActionModePopup.setContentView(this.mActionModeView);
                this.mActionModePopup.setWidth(-1);
                context.getTheme().resolveAttribute(com.google.android.marvin.talkback.R.attr.actionBarSize, typedValue, true);
                this.mActionModeView.mContentHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
                this.mActionModePopup.setHeight(-2);
                this.mShowActionModePopup = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 11, null);
            } else {
                ViewStubCompat viewStubCompat = (ViewStubCompat) this.mSubDecor.findViewById(com.google.android.marvin.talkback.R.id.action_mode_bar_stub);
                if (viewStubCompat != null) {
                    viewStubCompat.mInflater = LayoutInflater.from(getActionBarThemedContext());
                    this.mActionModeView = (ActionBarContextView) viewStubCompat.inflate();
                }
            }
        }
        if (this.mActionModeView != null) {
            endOnGoingFadeAnimation();
            this.mActionModeView.killMode();
            StandaloneActionMode standaloneActionMode = new StandaloneActionMode(this.mActionModeView.getContext(), this.mActionModeView, callback);
            if (callback.onCreateActionMode(standaloneActionMode, standaloneActionMode.mMenu)) {
                standaloneActionMode.invalidate();
                this.mActionModeView.initForMode(standaloneActionMode);
                this.mActionMode = standaloneActionMode;
                if (shouldAnimateActionModeView()) {
                    this.mActionModeView.setAlpha(0.0f);
                    AccessibilityNodeInfoCompat.CollectionItemInfoCompat animate$ar$class_merging$ar$class_merging = ViewCompat.animate$ar$class_merging$ar$class_merging(this.mActionModeView);
                    animate$ar$class_merging$ar$class_merging.alpha$ar$ds(1.0f);
                    this.mFadeAnim$ar$class_merging$ar$class_merging = animate$ar$class_merging$ar$class_merging;
                    animate$ar$class_merging$ar$class_merging.setListener$ar$ds$34caea9b_0(new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.app.AppCompatDelegateImpl.7
                        public AnonymousClass7() {
                        }

                        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
                        public final void onAnimationEnd$ar$ds() {
                            AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
                            AppCompatDelegateImpl.this.mFadeAnim$ar$class_merging$ar$class_merging.setListener$ar$ds$34caea9b_0(null);
                            AppCompatDelegateImpl.this.mFadeAnim$ar$class_merging$ar$class_merging = null;
                        }

                        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
                        public final void onAnimationStart$ar$ds() {
                            AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
                            if (AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) {
                                ViewCompat.Api20Impl.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
                            }
                        }
                    });
                } else {
                    this.mActionModeView.setAlpha(1.0f);
                    this.mActionModeView.setVisibility(0);
                    if (this.mActionModeView.getParent() instanceof View) {
                        ViewCompat.Api20Impl.requestApplyInsets((View) this.mActionModeView.getParent());
                    }
                }
                if (this.mActionModePopup != null) {
                    this.mWindow.getDecorView().post(this.mShowActionModePopup);
                }
            } else {
                this.mActionMode = null;
            }
        }
        updateBackInvokedCallbackState();
        return this.mActionMode;
    }

    final void updateBackInvokedCallbackState() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (this.mDispatcher != null && (getPanelState$ar$ds(0).isOpen || this.mActionMode != null)) {
                if (this.mBackCallback == null) {
                    this.mBackCallback = Api33Impl.registerOnBackPressedCallback(this.mDispatcher, this);
                }
            } else {
                OnBackInvokedCallback onBackInvokedCallback = this.mBackCallback;
                if (onBackInvokedCallback != null) {
                    Api33Impl.unregisterOnBackInvokedCallback(this.mDispatcher, onBackInvokedCallback);
                    this.mBackCallback = null;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x017c, code lost:
    
        if (r2 != null) goto L219;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x017f, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0181, code lost:
    
        if (r2 != null) goto L219;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean applyApplicationSpecificConfig(boolean r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 467
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.applyApplicationSpecificConfig(boolean, boolean):boolean");
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return createView$ar$ds(str, context, attributeSet);
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void setContentView(View view) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override // android.support.v7.app.AppCompatDelegate
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }
}

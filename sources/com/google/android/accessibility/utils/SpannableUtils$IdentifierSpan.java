package com.google.android.accessibility.utils;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityButtonController;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.inputmethodservice.Keyboard;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.LocaleSpan;
import android.text.style.TtsSpan;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabWidget;
import android.widget.ToggleButton;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.content.ContextCompat$Api24Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragmentCompat;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.viewpager.widget.ViewPager;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.brailleime.input.BrailleInputPlanePhone$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.LanguageActor;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.talkback.compositor.HintEventInterpretation;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.contextmenu.ListSubMenu;
import com.google.android.accessibility.talkback.contextmenu.OnContextMenuItemClickListener;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorAccessibilityHints$HintInfo;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$AutomaticImageCaptioningState;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$FeatureSwitchDialogResources;
import com.google.android.accessibility.talkback.menurules.NodeMenuRule;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.accessibility.talkback.permission.PermissionRequestActivity;
import com.google.android.accessibility.talkback.trainingcommon.ExternalDrawableResource;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.accessibility.talkback.trainingcommon.content.Text;
import com.google.android.accessibility.talkback.trainingcommon.tv.TvPageConfig;
import com.google.android.accessibility.talkback.trainingcommon.tv.VendorConfig;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.caption.Result;
import com.google.android.accessibility.utils.compat.CompatUtils;
import com.google.android.accessibility.utils.compat.view.MotionEventCompatUtils;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.traversal.ReorderedChildrenIterator;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import googledata.experiments.mobile.accessibility_suite.features.ImageCaption;
import j$.util.Optional;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SpannableUtils$IdentifierSpan {
    public SpannableUtils$IdentifierSpan() {
    }

    public static void addItemOrSubMenuForCurrentNode$ar$objectUnboxing$ar$ds$ar$class_merging$ar$class_merging(ContextMenu contextMenu, int i, int i2, int i3, WindowTrackerFactory windowTrackerFactory, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        NodeMenuRule menuRuleById;
        contextMenu.removeItem(i);
        if (i == R.id.labeling_breakout_add_label) {
            contextMenu.removeItem(R.id.labeling_breakout_edit_label);
            i = R.id.labeling_breakout_add_label;
        }
        NodeMenuRule menuRuleById2 = ((NodeMenuRuleCreator) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).getMenuRuleById(i);
        if (menuRuleById2 != null && menuRuleById2.isEnabled((Context) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider) && accessibilityNodeInfoCompat != null && (menuRuleById = ((NodeMenuRuleCreator) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).getMenuRuleById(i)) != null && menuRuleById.isEnabled((Context) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider) && menuRuleById.accept((Context) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider, accessibilityNodeInfoCompat)) {
            List<ContextMenuItem> menuItemsForNode = menuRuleById.getMenuItemsForNode((Context) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider, accessibilityNodeInfoCompat, true);
            if (!menuItemsForNode.isEmpty()) {
                if (menuRuleById.isSubMenu()) {
                    ListSubMenu addSubMenu = contextMenu.addSubMenu(0, i, i3, (CharSequence) ((AccessibilityService) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider).getString(i2));
                    for (ContextMenuItem contextMenuItem : menuItemsForNode) {
                        WindowTrackerFactory.setNodeMenuDefaultCloseRules(contextMenuItem);
                        addSubMenu.add(contextMenuItem);
                    }
                    return;
                }
                WindowTrackerFactory.setNodeMenuDefaultCloseRules((ContextMenuItem) menuItemsForNode.get(0));
                contextMenu.add((ContextMenuItem) menuItemsForNode.get(0));
            }
        }
    }

    public static void addPreferencesFromResource(PreferenceFragmentCompat preferenceFragmentCompat, int i) {
        preferenceFragmentCompat.getPreferenceManager().setStorageDeviceProtected();
        preferenceFragmentCompat.addPreferencesFromResource(i);
    }

    public static boolean allowLinksOutOfSettings(Context context) {
        if (Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0) != 1) {
            return false;
        }
        return true;
    }

    public static boolean areMultipleImesEnabled(Context context) {
        if (getEnabledInputMethodList(context).size() > 1) {
            return true;
        }
        return false;
    }

    public static void assignWebIntentToPreference(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference, String str) {
        UiModeManager uiModeManager;
        List<ResolveInfo> queryIntentActivities;
        if (!allowLinksOutOfSettings(preferenceFragmentCompat.getContext())) {
            return;
        }
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW", parse);
        FragmentActivity activity = preferenceFragmentCompat.getActivity();
        if (activity != null && (((uiModeManager = (UiModeManager) activity.getSystemService("uimode")) != null && uiModeManager.getCurrentModeType() == 4) || (queryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0)) == null || queryIntentActivities.isEmpty())) {
            Intent intent2 = new Intent(activity, (Class<?>) WebActivity.class);
            intent2.setData(parse);
            intent = intent2;
        }
        preference.setIntent(intent);
    }

    public static void attachSettingsHighlightBundle(Intent intent, ComponentName componentName) {
        Bundle bundle = new Bundle();
        bundle.putString(":settings:fragment_args_key", componentName.flattenToString());
        intent.putExtra(":settings:show_fragment_args", bundle);
    }

    public static float clampValue(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        if (f > f3) {
            return f3;
        }
        return f;
    }

    public static int clampValue$ar$ds(int i, int i2) {
        if (i < 0) {
            return 0;
        }
        if (i > i2) {
            return i2;
        }
        return i;
    }

    public static void computeTitleForMenuItem$ar$objectUnboxing$ar$ds(ContextMenuItem contextMenuItem, int i, int i2, int i3, TalkBackService talkBackService) {
        String string;
        if (getBooleanPref(getSharedPreferences(talkBackService), talkBackService.getResources(), i2, i3)) {
            string = talkBackService.getString(R.string.value_on);
        } else {
            string = talkBackService.getString(R.string.value_off);
        }
        contextMenuItem.title = talkBackService.getString(i, new Object[]{string});
    }

    public static String constructCaptionTextForAuto(Context context, Result result, Result result2, Result result3) {
        StringBuilder sb = new StringBuilder();
        if (!Result.isEmpty(result2)) {
            sb.append(context.getString(R.string.detected_icon_label, result2.text));
        }
        if (!Result.isEmpty(result)) {
            sb.append(constructImageDescriptionText(context, result));
        }
        if (!Result.isEmpty(result3)) {
            sb.append(context.getString(R.string.detected_recognized_text, result3.text));
        }
        if (TextUtils.isEmpty(sb)) {
            return "";
        }
        return context.getString(R.string.detected_result, sb);
    }

    public static String constructImageDescriptionText(Context context, Result result) {
        String string = context.getString(R.string.detected_image_description, result.text);
        if (result.confidence < ImageCaption.getImageDescriptionHighQualityThreshold(context)) {
            if (result.confidence >= ImageCaption.getImageDescriptionLowQualityThreshold(context)) {
                return context.getString(R.string.medium_confidence_image_description, string);
            }
            return context.getString(R.string.low_confidence_image_description, string);
        }
        return string;
    }

    public static String contentChangeTypesToString(int i) {
        return flagsToString(i, new AccessibilityEventUtils$$ExternalSyntheticLambda0(0));
    }

    public static MotionEvent convertHoverToTouch(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        CompatUtils.invoke(motionEvent, null, MotionEventCompatUtils.METHOD_setSource, 4098);
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action != 9) {
                if (action == 10) {
                    obtain.setAction(1);
                }
            } else {
                obtain.setAction(0);
            }
        } else {
            obtain.setAction(2);
        }
        return obtain;
    }

    public static AccessibilityEvent copy(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return null;
        }
        if (isAtLeastR()) {
            return new AccessibilityEvent(accessibilityEvent);
        }
        return AccessibilityEvent.obtain(accessibilityEvent);
    }

    public static NotificationCompat$Builder createDefaultNotificationBuilder(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        NotificationChannel notificationChannel = new NotificationChannel("TalkBackChannel", context.getString(R.string.talkback_notification_channel_name), 4);
        notificationChannel.setDescription(context.getString(R.string.talkback_notification_channel_description));
        notificationChannel.setSound(null, null);
        notificationManager.createNotificationChannel(notificationChannel);
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context, "TalkBackChannel");
        notificationCompat$Builder.setSmallIcon$ar$ds(R.drawable.quantum_gm_ic_accessibility_new_vd_theme_24);
        return notificationCompat$Builder;
    }

    public static Filter createNodeFilter(int i, final Map map) {
        switch (i) {
            case 262145:
                i = 1048579;
                break;
            case 262146:
                i = 1048578;
                break;
            case 262147:
                i = 1048577;
                break;
        }
        Filter filter = null;
        if (isHtmlTarget(i)) {
            LogUtils.w("NavigationTarget", "Cannot define node filter for html target.", new Object[0]);
            return null;
        }
        Filter filter2 = new Filter() { // from class: com.google.android.accessibility.talkback.focusmanagement.NavigationTarget$1
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat != null && AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat, map)) {
                    return true;
                }
                return false;
            }
        };
        if (i != 202) {
            switch (i) {
                case 1048577:
                    filter = AccessibilityNodeInfoUtils.FILTER_HEADING.or(AccessibilityNodeInfoUtils.FILTER_CONTAINER_WITH_UNFOCUSABLE_HEADING);
                    break;
                case 1048578:
                    final Filter filter3 = AccessibilityNodeInfoUtils.FILTER_CONTROL;
                    filter = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.9
                        public AnonymousClass9() {
                        }

                        @Override // com.google.android.accessibility.utils.Filter
                        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                            if (accessibilityNodeInfoCompat == null) {
                                return false;
                            }
                            if (Filter.this.accept(accessibilityNodeInfoCompat)) {
                                return true;
                            }
                            return AccessibilityNodeInfoUtils.hasMatchingDescendant(accessibilityNodeInfoCompat, Filter.this.and(AccessibilityNodeInfoUtils.FILTER_NON_FOCUSABLE_VISIBLE_NODE));
                        }
                    };
                    break;
                case 1048579:
                    filter = AccessibilityNodeInfoUtils.FILTER_LINK;
                    break;
            }
        } else {
            filter = AccessibilityNodeInfoUtils.FILTER_CONTAINER;
        }
        if (filter != null) {
            return filter2.and(filter);
        }
        return filter2;
    }

    public static Notification createNotification(Context context, String str, String str2, String str3, PendingIntent pendingIntent, boolean z) {
        NotificationCompat$Builder createDefaultNotificationBuilder = createDefaultNotificationBuilder(context);
        createDefaultNotificationBuilder.setTicker$ar$ds(str);
        createDefaultNotificationBuilder.setContentTitle$ar$ds(str2);
        createDefaultNotificationBuilder.setContentText$ar$ds(str3);
        createDefaultNotificationBuilder.mContentIntent = pendingIntent;
        createDefaultNotificationBuilder.setAutoCancel$ar$ds(z);
        createDefaultNotificationBuilder.setOngoing$ar$ds(true);
        createDefaultNotificationBuilder.setWhen$ar$ds(0L);
        return createDefaultNotificationBuilder.build();
    }

    public static Intent createPracticeGesturesIntent(Context context) {
        TrainingConfig.TrainingId trainingId;
        if (FeatureSupport.isMultiFingerGestureSupported()) {
            trainingId = TrainingConfig.TrainingId.TRAINING_ID_TUTORIAL_PRACTICE_GESTURE;
        } else {
            trainingId = TrainingConfig.TrainingId.TRAINING_ID_TUTORIAL_PRACTICE_GESTURE_PRE_R;
        }
        return TrainingActivity.createTrainingIntent(context, trainingId);
    }

    public static Intent createTutorialIntent(Context context) {
        if (FormFactorUtils.getInstance().isAndroidWear) {
            return TrainingActivity.createTrainingIntent(context, TrainingConfig.TrainingId.TRAINING_ID_TUTORIAL_FOR_WATCH);
        }
        if (FormFactorUtils.getInstance().isAndroidTv) {
            return TrainingActivity.createTrainingIntent(context, TrainingConfig.TrainingId.TRAINING_ID_TUTORIAL_FOR_TV);
        }
        return TrainingActivity.createTrainingIntent(context, TrainingConfig.TrainingId.TRAINING_ID_TUTORIAL);
    }

    private static Optional customizeAndCreatePageIfEnabled(VendorConfig vendorConfig, TvPageConfig tvPageConfig, PageConfig.PageId pageId) {
        TvPageConfig tvPageConfig2;
        String str;
        String str2;
        ExternalDrawableResource externalDrawableResource;
        if (vendorConfig != null && (tvPageConfig2 = (TvPageConfig) vendorConfig.defaultPages().get(pageId)) != null) {
            TvPageConfig.Builder builder = TvPageConfig.builder();
            builder.setEnabled$ar$ds$5aaec64b_0(tvPageConfig2.enabled());
            if (tvPageConfig2.title() != null) {
                str = tvPageConfig2.title();
            } else {
                str = tvPageConfig.title;
            }
            builder.title = str;
            if (tvPageConfig2.summary() != null) {
                str2 = tvPageConfig2.summary();
            } else {
                str2 = tvPageConfig.summary;
            }
            builder.summary = str2;
            if (tvPageConfig2.image() != null) {
                externalDrawableResource = tvPageConfig2.image();
            } else {
                externalDrawableResource = tvPageConfig.image;
            }
            builder.TvPageConfig$Builder$ar$image = externalDrawableResource;
            tvPageConfig = builder.build();
        }
        if (tvPageConfig.enabled) {
            return Optional.of(tvPageConfigToPageConfigBuilder(pageId, tvPageConfig));
        }
        return Optional.empty();
    }

    public static int dpToPx(Context context, int i) {
        return Math.round(i * context.getResources().getDisplayMetrics().density);
    }

    public static boolean eventMatchesAnyType(AccessibilityEvent accessibilityEvent, int i) {
        if (accessibilityEvent != null && (accessibilityEvent.getEventType() & i) != 0) {
            return true;
        }
        return false;
    }

    public static Notification extractNotification(AccessibilityEvent accessibilityEvent) {
        Parcelable parcelableData = accessibilityEvent.getParcelableData();
        if (!(parcelableData instanceof Notification)) {
            return null;
        }
        return (Notification) parcelableData;
    }

    public static Preference findPreference(FragmentActivity fragmentActivity, String str) {
        for (Fragment fragment : fragmentActivity.getSupportFragmentManager().getFragments()) {
            if (fragment instanceof PreferenceFragmentCompat) {
                return ((PreferenceFragmentCompat) fragment).findPreference(str);
            }
            if (fragment instanceof PreferenceDialogFragmentCompat) {
                return ((PreferenceDialogFragmentCompat) fragment).getPreference();
            }
        }
        return null;
    }

    public static String fingerprintGestureIdToString(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 8) {
                        return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "(unhandled ", ")");
                    }
                    return "FINGERPRINT_GESTURE_SWIPE_DOWN";
                }
                return "FINGERPRINT_GESTURE_SWIPE_UP";
            }
            return "FINGERPRINT_GESTURE_SWIPE_LEFT";
        }
        return "FINGERPRINT_GESTURE_SWIPE_RIGHT";
    }

    public static String flagsToString(int i, Function function) {
        if (i == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 1; i2 != 0; i2 += i2) {
            if ((i & i2) != 0) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append((String) function.apply(Integer.valueOf(i2)));
            }
        }
        return sb.toString();
    }

    public static void forEachWindowInfoListOnAllDisplays(AccessibilityService accessibilityService, Consumer consumer) {
        SparseArray windowsOnAllDisplays = getWindowsOnAllDisplays(accessibilityService);
        int size = windowsOnAllDisplays.size();
        for (int i = 0; i < size; i++) {
            consumer.accept((List) windowsOnAllDisplays.valueAt(i));
        }
    }

    public static int fromInt$ar$edu(int i) {
        int[] values$ar$edu$592e4ba4_0 = values$ar$edu$592e4ba4_0();
        for (int i2 = 0; i2 < 3; i2++) {
            int i3 = values$ar$edu$592e4ba4_0[i2];
            int i4 = i3 - 1;
            if (i3 != 0) {
                if (i4 == i) {
                    return i3;
                }
            } else {
                throw null;
            }
        }
        return 1;
    }

    public static int fromInt$ar$edu$ad7c944d_0(int i) {
        int[] values$ar$edu$3d020c88_0 = values$ar$edu$3d020c88_0();
        for (int i2 = 0; i2 < 6; i2++) {
            int i3 = values$ar$edu$3d020c88_0[i2];
            int i4 = i3 - 1;
            if (i3 != 0) {
                if (i4 == i) {
                    return i3;
                }
            } else {
                throw null;
            }
        }
        return 1;
    }

    public static String gestureIdToString(int i) {
        switch (i) {
            case -5:
                return "GESTURE_TAP_HOLD_AND_2ND_FINGER_BACKWARD_DOUBLE_TAP";
            case -4:
                return "GESTURE_TAP_HOLD_AND_2ND_FINGER_FORWARD_DOUBLE_TAP";
            case -3:
                return "GESTURE_FAKED_SPLIT_TYPING";
            case -2:
            case -1:
            case 0:
            case 36:
            case 37:
            default:
                return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "(unhandled ", ")");
            case 1:
                return "GESTURE_SWIPE_UP";
            case 2:
                return "GESTURE_SWIPE_DOWN";
            case 3:
                return "GESTURE_SWIPE_LEFT";
            case 4:
                return "GESTURE_SWIPE_RIGHT";
            case 5:
                return "GESTURE_SWIPE_LEFT_AND_RIGHT";
            case 6:
                return "GESTURE_SWIPE_RIGHT_AND_LEFT";
            case 7:
                return "GESTURE_SWIPE_UP_AND_DOWN";
            case 8:
                return "GESTURE_SWIPE_DOWN_AND_UP";
            case 9:
                return "GESTURE_SWIPE_LEFT_AND_UP";
            case 10:
                return "GESTURE_SWIPE_LEFT_AND_DOWN";
            case 11:
                return "GESTURE_SWIPE_RIGHT_AND_UP";
            case 12:
                return "GESTURE_SWIPE_RIGHT_AND_DOWN";
            case 13:
                return "GESTURE_SWIPE_UP_AND_LEFT";
            case 14:
                return "GESTURE_SWIPE_UP_AND_RIGHT";
            case 15:
                return "GESTURE_SWIPE_DOWN_AND_LEFT";
            case 16:
                return "GESTURE_SWIPE_DOWN_AND_RIGHT";
            case 17:
                return "GESTURE_DOUBLE_TAP";
            case 18:
                return "GESTURE_DOUBLE_TAP_AND_HOLD";
            case 19:
                return "GESTURE_2_FINGER_SINGLE_TAP";
            case 20:
                return "GESTURE_2_FINGER_DOUBLE_TAP";
            case 21:
                return "GESTURE_2_FINGER_TRIPLE_TAP";
            case 22:
                return "GESTURE_3_FINGER_SINGLE_TAP";
            case 23:
                return "GESTURE_3_FINGER_DOUBLE_TAP";
            case 24:
                return "GESTURE_3_FINGER_TRIPLE_TAP";
            case 25:
                return "GESTURE_2_FINGER_SWIPE_UP";
            case 26:
                return "GESTURE_2_FINGER_SWIPE_DOWN";
            case 27:
                return "GESTURE_2_FINGER_SWIPE_LEFT";
            case 28:
                return "GESTURE_2_FINGER_SWIPE_RIGHT";
            case 29:
                return "GESTURE_3_FINGER_SWIPE_UP";
            case 30:
                return "GESTURE_3_FINGER_SWIPE_DOWN";
            case 31:
                return "GESTURE_3_FINGER_SWIPE_LEFT";
            case 32:
                return "GESTURE_3_FINGER_SWIPE_RIGHT";
            case 33:
                return "GESTURE_4_FINGER_SWIPE_UP";
            case 34:
                return "GESTURE_4_FINGER_SWIPE_DOWN";
            case 35:
                return "GESTURE_4_FINGER_SWIPE_LEFT";
            case 38:
                return "GESTURE_4_FINGER_DOUBLE_TAP";
            case 39:
                return "GESTURE_4_FINGER_TRIPLE_TAP";
            case 40:
                return "GESTURE_2_FINGER_DOUBLE_TAP_AND_HOLD";
            case 41:
                return "GESTURE_3_FINGER_DOUBLE_TAP_AND_HOLD";
            case 42:
                return "GESTURE_4_FINGER_DOUBLE_TAP_AND_HOLD";
            case 43:
                return "GESTURE_2_FINGER_TRIPLE_TAP_AND_HOLD";
            case 44:
                return "GESTURE_3_FINGER_SINGLE_TAP_AND_HOLD";
            case 45:
                return "GESTURE_3_FINGER_TRIPLE_TAP_AND_HOLD";
        }
    }

    public static Intent getAccessibilitySettingsAndHighLightTalkBackIntent() {
        Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
        intent.addFlags(276856832);
        attachSettingsHighlightBundle(intent, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE);
        return intent;
    }

    public static int[] getAllEventTypes() {
        return new int[]{16384, 16777216, 524288, 262144, 64, 1024, 512, 2097152, 1048576, 32768, 65536, 1, 8388608, 8, BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE, 256, 2, 4096, 4, 16, 8192, 131072, 4194304, 2048, 32};
    }

    public static AccessibilityNodeInfoCompat getAnchor(AccessibilityWindowInfo accessibilityWindowInfo) {
        AccessibilityNodeInfo anchor;
        if (accessibilityWindowInfo == null) {
            return null;
        }
        try {
            anchor = accessibilityWindowInfo.getAnchor();
            return AccessibilityNodeInfoUtils.toCompat(anchor);
        } catch (SecurityException e) {
            LogUtils.e("AccessibilityWindowInfoUtils", "SecurityException occurred at AccessibilityWindowInfoUtils#getAnchor(): %s", e);
            return null;
        }
    }

    public static AccessibilityWindowInfo getAnchoredWindow(AccessibilityNodeInfo accessibilityNodeInfo) {
        AccessibilityWindowInfo window = AccessibilityNodeInfoUtils.getWindow(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && window != null) {
            for (int i = 0; i < window.getChildCount(); i++) {
                AccessibilityWindowInfo child = window.getChild(i);
                AccessibilityNodeInfoCompat anchor = getAnchor(child);
                if (anchor != null && accessibilityNodeInfo.equals(anchor.mInfo)) {
                    return child;
                }
            }
        }
        return null;
    }

    public static ImageCaptionConstants$AutomaticImageCaptioningState getAutomaticImageCaptioningState(Context context, SharedPreferences sharedPreferences, ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources) {
        if (getBooleanPref(sharedPreferences, context.getResources(), imageCaptionConstants$FeatureSwitchDialogResources.switchKey, imageCaptionConstants$FeatureSwitchDialogResources.switchDefaultValue)) {
            if (ImageCaption.enableAutomaticCaptioningForAllImages(context) && !getBooleanPref(sharedPreferences, context.getResources(), imageCaptionConstants$FeatureSwitchDialogResources.switchOnUnlabelledOnlyKey, imageCaptionConstants$FeatureSwitchDialogResources.switchOnUnlabelledOnlyDefaultValue)) {
                return ImageCaptionConstants$AutomaticImageCaptioningState.ON_ALL_IMAGES;
            }
            return ImageCaptionConstants$AutomaticImageCaptioningState.ON_UNLABELLED_ONLY;
        }
        return ImageCaptionConstants$AutomaticImageCaptioningState.OFF;
    }

    public static boolean getBooleanPref(SharedPreferences sharedPreferences, Resources resources, int i, int i2) {
        return sharedPreferences.getBoolean(resources.getString(i), resources.getBoolean(i2));
    }

    public static Rect getBounds(AccessibilityWindowInfo accessibilityWindowInfo) {
        Rect rect = new Rect();
        accessibilityWindowInfo.getBoundsInScreen(rect);
        return rect;
    }

    public static String getDefaultLocale() {
        return getLanguageLocale(Locale.getDefault().toLanguageTag());
    }

    public static LinkedHashMap getDefaultPageBuilders(Context context, VendorConfig vendorConfig) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TvPageConfig.Builder builder = TvPageConfig.builder();
        builder.title = context.getString(R.string.tv_training_overview_step_title);
        builder.summary = context.getString(R.string.tv_training_overview_step_summary);
        customizeAndCreatePageIfEnabled(vendorConfig, builder.build(), PageConfig.PageId.PAGE_ID_TV_OVERVIEW).ifPresent(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(linkedHashMap, 16));
        TvPageConfig.Builder builder2 = TvPageConfig.builder();
        builder2.title = context.getString(R.string.tv_training_remote_step_title);
        builder2.summary = context.getString(R.string.tv_training_remote_step_summary);
        customizeAndCreatePageIfEnabled(vendorConfig, builder2.build(), PageConfig.PageId.PAGE_ID_TV_REMOTE).ifPresent(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(linkedHashMap, 17));
        TvPageConfig.Builder builder3 = TvPageConfig.builder();
        builder3.title = context.getString(R.string.tv_training_shortcut_step_title);
        builder3.summary = context.getString(R.string.tv_training_shortcut_step_summary);
        customizeAndCreatePageIfEnabled(vendorConfig, builder3.build(), PageConfig.PageId.PAGE_ID_TV_SHORTCUT).ifPresent(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(linkedHashMap, 18));
        return linkedHashMap;
    }

    public static Context getDefaultScreenDensityContext(Context context) {
        int i;
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        i = DisplayMetrics.DENSITY_DEVICE_STABLE;
        configuration.densityDpi = i;
        configuration.setTo(configuration);
        return context.createConfigurationContext(configuration);
    }

    public static int getDisplayId(AccessibilityEvent accessibilityEvent) {
        AccessibilityWindowInfo window;
        if (isAtLeastR() && (window = AccessibilityNodeInfoUtils.getWindow(accessibilityEvent.getSource())) != null) {
            return getDisplayId(window);
        }
        return 0;
    }

    public static String getEnabledImeId(Context context, String str) {
        for (InputMethodInfo inputMethodInfo : getEnabledInputMethodList(context)) {
            if (inputMethodInfo.getPackageName().equals(str)) {
                return inputMethodInfo.getId();
            }
        }
        return "";
    }

    public static List getEnabledInputMethodList(Context context) {
        List<InputMethodInfo> enabledInputMethodList;
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null && (enabledInputMethodList = inputMethodManager.getEnabledInputMethodList()) != null) {
            return enabledInputMethodList;
        }
        return new ArrayList();
    }

    public static CharSequence getEventAggregateText(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Iterator<CharSequence> it = accessibilityEvent.getText().iterator();
        while (it.hasNext()) {
            StringBuilderUtils.appendWithSeparator$ar$ds(spannableStringBuilder, false, it.next());
        }
        return spannableStringBuilder;
    }

    public static CharSequence getEventTextOrDescription(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return null;
        }
        CharSequence contentDescription = accessibilityEvent.getContentDescription();
        if (!TextUtils.isEmpty(contentDescription)) {
            return contentDescription;
        }
        return getEventAggregateText(accessibilityEvent);
    }

    public static float getFloatFromStringPref(SharedPreferences sharedPreferences, Resources resources, int i, int i2) {
        return Float.parseFloat(sharedPreferences.getString(resources.getString(i), resources.getString(i2)));
    }

    private static float getGlobalFloat(Context context, String str) {
        return Settings.Global.getFloat(context.getContentResolver(), str, -1.0f);
    }

    public static int getIntFromStringPref(SharedPreferences sharedPreferences, Resources resources, int i, int i2) {
        return Integer.parseInt(sharedPreferences.getString(resources.getString(i), resources.getString(i2)));
    }

    public static int getIntPref(SharedPreferences sharedPreferences, Resources resources, int i, int i2) {
        return sharedPreferences.getInt(resources.getString(i), resources.getInteger(i2));
    }

    public static String getLanguageLocale(String str) {
        int indexOf;
        if (str != null && (indexOf = str.indexOf(45)) > 0) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    public static List getMenuItems(final Context context, final Pipeline.FeedbackReturner feedbackReturner, ActorState actorState) {
        Set installedLanguages = ((LanguageActor) actorState.getLanguageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().FloatingActionButton$ShadowDelegateImpl$ar$this$0).getInstalledLanguages();
        if (installedLanguages == null) {
            return null;
        }
        final TreeSet treeSet = new TreeSet(new BrailleInputPlanePhone$$ExternalSyntheticLambda1(context, 3));
        treeSet.addAll(installedLanguages);
        OnContextMenuItemClickListener onContextMenuItemClickListener = new OnContextMenuItemClickListener(context, feedbackReturner, treeSet) { // from class: com.google.android.accessibility.talkback.contextmenu.LanguageMenuProcessor$LanguageMenuItemClickListener
            private final Context context;
            private final Set languages;
            private final Pipeline.FeedbackReturner pipeline;

            {
                this.context = context;
                this.languages = treeSet;
                this.pipeline = feedbackReturner;
            }

            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                Locale locale;
                if (menuItem == null) {
                    return false;
                }
                Set set = this.languages;
                CharSequence title = menuItem.getTitle();
                Iterator it = set.iterator();
                while (true) {
                    if (it.hasNext()) {
                        locale = (Locale) it.next();
                        if (TextUtils.equals(title, LanguageActor.getLocaleString(this.context, locale))) {
                            break;
                        }
                    } else {
                        locale = null;
                        break;
                    }
                }
                Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                Feedback.Part.Builder builder = Feedback.Part.builder();
                builder.language = new Feedback.Language(3, locale);
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, builder);
                return true;
            }

            @Override // com.google.android.accessibility.talkback.contextmenu.OnContextMenuItemClickListener
            public final /* synthetic */ void clear() {
            }
        };
        ArrayList arrayList = new ArrayList();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            ContextMenuItem createMenuItem = ContextMenu.createMenuItem(context, R.id.group_language, 0, 0, LanguageActor.getLocaleString(context, (Locale) it.next()));
            createMenuItem.listener = onContextMenuItemClickListener;
            arrayList.add(createMenuItem);
        }
        return arrayList;
    }

    public static AccessibilityWindowInfo getOnscreenInputWindowInfo(AccessibilityService accessibilityService) {
        for (AccessibilityWindowInfo accessibilityWindowInfo : getWindows(accessibilityService)) {
            if (accessibilityWindowInfo != null && accessibilityWindowInfo.getType() == 2) {
                return accessibilityWindowInfo;
            }
        }
        return null;
    }

    private static PackageInfo getPackageInfo(Context context, CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(charSequence.toString(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static PageConfig getPageConfigForVendorPage(VendorConfig vendorConfig, int i) {
        try {
            return ((PageConfig.Builder) getVendorPageBuilders(vendorConfig).get(i)).build();
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    public static AccessibilityWindowInfo getPipWindow(AccessibilityService accessibilityService) {
        boolean isInPictureInPictureMode;
        for (AccessibilityWindowInfo accessibilityWindowInfo : getWindows(accessibilityService)) {
            isInPictureInPictureMode = accessibilityWindowInfo.isInPictureInPictureMode();
            if (isInPictureInPictureMode) {
                return accessibilityWindowInfo;
            }
        }
        return null;
    }

    public static boolean getPreferenceValueBool(SharedPreferences sharedPreferences, Resources resources, String str, boolean z) {
        String stringPref = getStringPref(sharedPreferences, resources, R.string.pref_verbosity_preset_key, 0);
        if (stringPref == null) {
            return sharedPreferences.getBoolean(str, z);
        }
        return getPreferenceVerbosityBool(sharedPreferences, resources, stringPref, str, z);
    }

    public static String getPreferenceValueString(SharedPreferences sharedPreferences, Resources resources, String str, String str2) {
        String stringPref = getStringPref(sharedPreferences, resources, R.string.pref_verbosity_preset_key, 0);
        if (stringPref == null) {
            return sharedPreferences.getString(str, str2);
        }
        return getPreferenceVerbosityString(sharedPreferences, resources, stringPref, str, str2);
    }

    public static boolean getPreferenceVerbosityBool(SharedPreferences sharedPreferences, Resources resources, String str, String str2, boolean z) {
        if (str.equals(resources.getString(R.string.pref_verbosity_preset_value_high))) {
            return true;
        }
        if (str.equals(resources.getString(R.string.pref_verbosity_preset_value_low))) {
            return false;
        }
        return sharedPreferences.getBoolean(toVerbosityPrefKey(str, str2), z);
    }

    public static String getPreferenceVerbosityString(SharedPreferences sharedPreferences, Resources resources, String str, String str2, String str3) {
        int length;
        if (str.equals(resources.getString(R.string.pref_verbosity_preset_value_high))) {
            if (!str2.equals(resources.getString(R.string.pref_keyboard_echo_on_screen_key)) && !str2.equals(resources.getString(R.string.pref_keyboard_echo_physical_key))) {
                if (str2.equals(resources.getString(R.string.pref_capital_letters_key))) {
                    String[] stringArray = resources.getStringArray(R.array.pref_capital_letters_values);
                    if (stringArray != null && stringArray.length != 0) {
                        return stringArray[1];
                    }
                } else {
                    LogUtils.e("VerbosityPreferences", "Unhandled key \"%s\"", str2);
                }
            } else {
                String[] stringArray2 = resources.getStringArray(R.array.pref_keyboard_echo_values);
                if (stringArray2 != null && (length = stringArray2.length) != 0) {
                    return stringArray2[length - 1];
                }
            }
            return null;
        }
        if (str.equals(resources.getString(R.string.pref_verbosity_preset_value_low))) {
            if (!str2.equals(resources.getString(R.string.pref_keyboard_echo_on_screen_key)) && !str2.equals(resources.getString(R.string.pref_keyboard_echo_physical_key))) {
                if (str2.equals(resources.getString(R.string.pref_capital_letters_key))) {
                    String[] stringArray3 = resources.getStringArray(R.array.pref_capital_letters_values);
                    if (stringArray3 != null && stringArray3.length != 0) {
                        return stringArray3[0];
                    }
                } else {
                    LogUtils.e("VerbosityPreferences", "Unhandled key \"%s\"", str2);
                }
            } else {
                String[] stringArray4 = resources.getStringArray(R.array.pref_keyboard_echo_values);
                if (stringArray4 != null && stringArray4.length != 0) {
                    return stringArray4[0];
                }
            }
            return null;
        }
        return sharedPreferences.getString(toVerbosityPrefKey(str, str2), str3);
    }

    public static Region getRegionInScreen(AccessibilityWindowInfo accessibilityWindowInfo) {
        if (isAtLeastR()) {
            Region region = new Region();
            accessibilityWindowInfo.getRegionInScreen(region);
            return region;
        }
        return new Region(getBounds(accessibilityWindowInfo));
    }

    public static int getRole(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return getRole(AccessibilityNodeInfoUtils.toCompat(accessibilityNodeInfo));
    }

    public static AccessibilityNodeInfo getRoot(AccessibilityWindowInfo accessibilityWindowInfo) {
        if (accessibilityWindowInfo == null) {
            return null;
        }
        try {
            return accessibilityWindowInfo.getRoot();
        } catch (SecurityException e) {
            LogUtils.e("AccessibilityWindowInfoUtils", "SecurityException occurred at AccessibilityWindowInfoUtils#getRoot(): %s", e);
            return null;
        }
    }

    public static AccessibilityNodeInfoCompat getRootCompat(AccessibilityWindowInfo accessibilityWindowInfo) {
        return AccessibilityNodeInfoUtils.toCompat(getRoot(accessibilityWindowInfo));
    }

    public static AccessibilityNodeInfoCompat getRootInActiveWindow(AccessibilityService accessibilityService) {
        AccessibilityNodeInfo rootInActiveWindow;
        if (accessibilityService != null && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
            return AccessibilityNodeInfoUtils.toCompat(rootInActiveWindow);
        }
        return null;
    }

    public static Point getScreenPixelSizeWithoutWindowDecor(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return new Point(dpToPx(context, configuration.screenWidthDp), dpToPx(context, configuration.screenHeightDp));
    }

    public static int getScrollDeltaX(AccessibilityEvent accessibilityEvent) {
        int scrollDeltaX;
        if (isAtLeastP()) {
            scrollDeltaX = accessibilityEvent.getScrollDeltaX();
            return scrollDeltaX;
        }
        return -1;
    }

    public static int getScrollDeltaY(AccessibilityEvent accessibilityEvent) {
        int scrollDeltaY;
        if (isAtLeastP()) {
            scrollDeltaY = accessibilityEvent.getScrollDeltaY();
            return scrollDeltaY;
        }
        return -1;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        Context createDeviceProtectedStorageContext = ContextCompat$Api24Impl.createDeviceProtectedStorageContext(context);
        if (createDeviceProtectedStorageContext != null) {
            return PreferenceManager.getDefaultSharedPreferences(createDeviceProtectedStorageContext);
        }
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0098, code lost:
    
        if (com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, "androidx.appcompat.app.AlertDialog") == false) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x009d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getSourceRole(android.view.accessibility.AccessibilityEvent r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            java.lang.CharSequence r1 = r4.getClassName()
            java.lang.String r2 = "android.widget.Toast$TN"
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            r3 = 23
            if (r2 != 0) goto L9a
            java.lang.String r2 = "android.widget.Toast"
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L1c
            goto L9a
        L1c:
            java.lang.Class<android.app.ActionBar$Tab> r2 = android.app.ActionBar.Tab.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L28
            r0 = 19
            goto L9b
        L28:
            java.lang.Class<androidx.drawerlayout.widget.DrawerLayout> r2 = androidx.drawerlayout.widget.DrawerLayout.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            r3 = 20
            if (r2 != 0) goto L9a
            java.lang.String r2 = "androidx.core.widget.DrawerLayout"
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L3b
            goto L9a
        L3b:
            java.lang.Class<android.widget.SlidingDrawer> r2 = android.widget.SlidingDrawer.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L46
            r0 = 21
            goto L9b
        L46:
            java.lang.String r2 = "com.android.internal.view.menu.IconMenuView"
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L51
            r0 = 22
            goto L9b
        L51:
            java.lang.Class<android.widget.DatePicker> r2 = android.widget.DatePicker.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L5c
            r0 = 27
            goto L9b
        L5c:
            java.lang.Class<android.widget.TimePicker> r2 = android.widget.TimePicker.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L67
            r0 = 28
            goto L9b
        L67:
            java.lang.Class<android.widget.NumberPicker> r2 = android.widget.NumberPicker.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L72
            r0 = 29
            goto L9b
        L72:
            java.lang.Class<android.app.DatePickerDialog> r2 = android.app.DatePickerDialog.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L7d
            r0 = 25
            goto L9b
        L7d:
            java.lang.Class<android.app.TimePickerDialog> r2 = android.app.TimePickerDialog.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r2 == 0) goto L88
            r0 = 26
            goto L9b
        L88:
            java.lang.Class<android.app.AlertDialog> r2 = android.app.AlertDialog.class
            boolean r2 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            r3 = 24
            if (r2 != 0) goto L9a
            java.lang.String r2 = "androidx.appcompat.app.AlertDialog"
            boolean r1 = com.google.android.accessibility.utils.ClassLoadingCache.checkInstanceOf(r1, r2)
            if (r1 == 0) goto L9b
        L9a:
            r0 = r3
        L9b:
            if (r0 == 0) goto L9e
            return r0
        L9e:
            android.view.accessibility.AccessibilityNodeInfo r4 = r4.getSource()
            int r4 = getRole(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getSourceRole(android.view.accessibility.AccessibilityEvent):int");
    }

    public static String getStringPref(SharedPreferences sharedPreferences, Resources resources, int i, int i2) {
        String string;
        String string2 = resources.getString(i);
        if (i2 == 0) {
            string = null;
        } else {
            string = resources.getString(i2);
        }
        return sharedPreferences.getString(string2, string);
    }

    public static int getTalkBackFocusStrokeWidth(SharedPreferences sharedPreferences, Resources resources) {
        if (sharedPreferences.getBoolean(resources.getString(R.string.pref_thick_border_key), false)) {
            return resources.getDimensionPixelSize(R.dimen.accessibility_thick_focus_highlight_stroke_width);
        }
        return resources.getDimensionPixelSize(R.dimen.accessibility_focus_highlight_stroke_width);
    }

    public static CharSequence getTitle(AccessibilityWindowInfo accessibilityWindowInfo) {
        CharSequence title;
        if (accessibilityWindowInfo == null) {
            return null;
        }
        title = accessibilityWindowInfo.getTitle();
        return title;
    }

    public static int getType(AccessibilityWindowInfo accessibilityWindowInfo) {
        if (accessibilityWindowInfo == null) {
            return -1;
        }
        int type = accessibilityWindowInfo.getType();
        if (shouldCheckRootForGmsAppWindow(type)) {
            AccessibilityNodeInfo root = accessibilityWindowInfo.getRoot();
            if (isGmsAppWindow(type, root == null ? null : root.getPackageName())) {
                return 1;
            }
        }
        return type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List getVendorPageBuilders(VendorConfig vendorConfig) {
        ArrayList arrayList = new ArrayList();
        if (vendorConfig != null) {
            ImmutableList vendorPages = vendorConfig.vendorPages();
            int size = vendorPages.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                PageConfig.Builder tvPageConfigToPageConfigBuilder = tvPageConfigToPageConfigBuilder(PageConfig.PageId.PAGE_ID_TV_VENDOR, (TvPageConfig) vendorPages.get(i));
                tvPageConfigToPageConfigBuilder.vendorPageIndex = i2;
                arrayList.add(tvPageConfigToPageConfigBuilder);
                i++;
                i2++;
            }
        }
        return arrayList;
    }

    public static long getVersionCodeCompat(Context context, CharSequence charSequence) {
        long longVersionCode;
        if (TextUtils.isEmpty(charSequence)) {
            return -1L;
        }
        PackageInfo packageInfo = getPackageInfo(context, charSequence);
        if (packageInfo == null) {
            LogUtils.e("PackageManagerUtils", "Could not find package: %s", charSequence);
            return -1L;
        }
        if (isAtLeastP()) {
            longVersionCode = packageInfo.getLongVersionCode();
            return longVersionCode;
        }
        return packageInfo.versionCode;
    }

    public static String getVersionName(Context context) {
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (packageInfo == null) {
            LogUtils.e("PackageManagerUtils", "Could not find package: %s", packageName);
            return null;
        }
        return packageInfo.versionName;
    }

    public static int getWindowId(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return -1;
        }
        int windowId = accessibilityEvent.getWindowId();
        if (windowId != -1) {
            return windowId;
        }
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        if (source == null) {
            return -1;
        }
        return source.getWindowId();
    }

    public static List getWindows(AccessibilityService accessibilityService) {
        try {
            return accessibilityService.getWindows();
        } catch (SecurityException e) {
            LogUtils.e("A11yServiceCompatUtils", "SecurityException occurred at AccessibilityService#getWindows(): %s", e);
            return Collections.emptyList();
        }
    }

    public static SparseArray getWindowsOnAllDisplays(AccessibilityService accessibilityService) {
        SparseArray windowsOnAllDisplays;
        if (isAtLeastR()) {
            try {
                windowsOnAllDisplays = accessibilityService.getWindowsOnAllDisplays();
                return windowsOnAllDisplays;
            } catch (SecurityException e) {
                LogUtils.e("A11yServiceCompatUtils", "SecurityException occurred at AccessibilityService#getWindowsOnAllDisplays(): %s", e);
                return new SparseArray();
            }
        }
        SparseArray sparseArray = new SparseArray();
        sparseArray.put(0, getWindows(accessibilityService));
        return sparseArray;
    }

    public static boolean hasGmsCorePackage(Context context) {
        if (getPackageInfo(context, "com.google.android.gms") != null) {
            return true;
        }
        return false;
    }

    public static boolean hasPostNotificationPermission(Context context) {
        if (!isAtLeastT() || EditorInfoCompat.checkSelfPermission(context, "android.permission.POST_NOTIFICATIONS") == 0) {
            return true;
        }
        return false;
    }

    public static boolean hasTargetClickableSpanInNodeTree(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Class cls) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        return searchSpannableStringsForClickableSpanInNodeTree(accessibilityNodeInfoCompat, new HashSet(), null, cls);
    }

    public static long hashBytes(byte[] bArr) {
        int length = bArr.length;
        int i = 37;
        char c = 0;
        if (length <= 32) {
            if (length <= 16) {
                if (length >= 8) {
                    long j = (length + length) - 7286425919675154353L;
                    long load64 = load64(bArr, 0) - 7286425919675154353L;
                    long load642 = load64(bArr, length - 8);
                    return hashLength16((Long.rotateRight(load642, 37) * j) + load64, (Long.rotateRight(load64, 25) + load642) * j, j);
                }
                if (length >= 4) {
                    return hashLength16(length + ((load32(bArr, 0) & 4294967295L) << 3), load32(bArr, length - 4) & 4294967295L, (length + length) - 7286425919675154353L);
                }
                if (length <= 0) {
                    return -7286425919675154353L;
                }
                return (-7286425919675154353L) * shiftMix((((bArr[0] & 255) + ((bArr[length >> 1] & 255) << 8)) * (-7286425919675154353L)) ^ ((length + ((bArr[length - 1] & 255) << 2)) * (-4348849565147123417L)));
            }
            long load643 = load64(bArr, 0) * (-5435081209227447693L);
            long load644 = load64(bArr, 8);
            long j2 = (length + length) - 7286425919675154353L;
            long load645 = load64(bArr, length - 8) * j2;
            return hashLength16(Long.rotateRight(load643 + load644, 43) + Long.rotateRight(load645, 30) + (load64(bArr, length - 16) * (-7286425919675154353L)), load643 + Long.rotateRight(load644 - 7286425919675154353L, 18) + load645, j2);
        }
        int i2 = 64;
        if (length <= 64) {
            long load646 = load64(bArr, 0) * (-7286425919675154353L);
            long load647 = load64(bArr, 8);
            long j3 = (length + length) - 7286425919675154353L;
            long load648 = load64(bArr, length - 8) * j3;
            long load649 = load64(bArr, length - 16) * (-7286425919675154353L);
            long rotateRight = Long.rotateRight(load646 + load647, 43) + Long.rotateRight(load648, 30);
            long rotateRight2 = Long.rotateRight(load647 - 7286425919675154353L, 18) + load646;
            long load6410 = load64(bArr, 16) * j3;
            long load6411 = load64(bArr, 24);
            long j4 = rotateRight + load649;
            long load6412 = j4 + load64(bArr, length - 32);
            long j5 = load6412 * j3;
            return hashLength16(Long.rotateRight(load6410 + load6411, 43) + Long.rotateRight(j5, 30) + ((hashLength16(j4, rotateRight2 + load648, j3) + load64(bArr, length - 24)) * j3), load6410 + Long.rotateRight(load6411 + load646, 18) + j5, j3);
        }
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long load6413 = load64(bArr, 0) + 95310865018149119L;
        long shiftMix = shiftMix(-7956866745689871395L) * (-7286425919675154353L);
        long j6 = 2480279821605975764L;
        int i3 = 0;
        while (true) {
            int i4 = length - 1;
            int i5 = (i4 >> 6) * i2;
            long rotateRight3 = Long.rotateRight(load6413 + j6 + jArr[c] + load64(bArr, i3 + 8), i) * (-5435081209227447693L);
            long rotateRight4 = Long.rotateRight(j6 + jArr[1] + load64(bArr, i3 + 48), 42) * (-5435081209227447693L);
            long j7 = rotateRight3 ^ jArr2[1];
            long load6414 = jArr[c] + load64(bArr, i3 + 40);
            long rotateRight5 = Long.rotateRight(shiftMix + jArr2[c], 33) * (-5435081209227447693L);
            char c2 = c;
            weakHashLength32WithSeeds(bArr, i3, jArr[1] * (-5435081209227447693L), j7 + jArr2[c], jArr);
            long j8 = rotateRight4 + load6414;
            weakHashLength32WithSeeds(bArr, i3 + 32, rotateRight5 + jArr2[1], load64(bArr, i3 + 16) + j8, jArr2);
            int i6 = i3 + 64;
            if (i6 == i5) {
                int i7 = i4 & 63;
                int i8 = i5 + i7;
                long j9 = j7 & 255;
                long j10 = (j9 + j9) - 5435081209227447693L;
                long j11 = jArr2[c2] + i7;
                long j12 = jArr[c2] + j11;
                jArr[c2] = j12;
                jArr2[c2] = j11 + j12;
                long rotateRight6 = Long.rotateRight(rotateRight5 + j8 + j12 + load64(bArr, i8 - 55), 37) * j10;
                long rotateRight7 = Long.rotateRight(j8 + jArr[1] + load64(bArr, i8 - 15), 42) * j10;
                long j13 = jArr2[1] * 9;
                long load6415 = (jArr[c2] * 9) + load64(bArr, i8 - 23);
                long rotateRight8 = Long.rotateRight(j7 + jArr2[c2], 33) * j10;
                long j14 = rotateRight6 ^ j13;
                weakHashLength32WithSeeds(bArr, i8 - 63, jArr[1] * j10, j14 + jArr2[c2], jArr);
                long j15 = rotateRight7 + load6415;
                weakHashLength32WithSeeds(bArr, i8 - 31, jArr2[1] + rotateRight8, j15 + load64(bArr, i8 - 47), jArr2);
                return hashLength16(hashLength16(jArr[c2], jArr2[c2], j10) + (shiftMix(j15) * (-4348849565147123417L)) + j14, hashLength16(jArr[1], jArr2[1], j10) + rotateRight8, j10);
            }
            i3 = i6;
            shiftMix = j7;
            i2 = 64;
            c = c2;
            load6413 = rotateRight5;
            i = 37;
            j6 = j8;
        }
    }

    private static long hashLength16(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    public static void hidePreference(Context context, PreferenceGroup preferenceGroup, int i) {
        int[] iArr = {i};
        if (context == null) {
            return;
        }
        HashSet hashSet = new HashSet();
        hashSet.add(context.getString(iArr[0]));
        hidePreferences(preferenceGroup, hashSet);
    }

    private static void hidePreferences(PreferenceGroup preferenceGroup, Set set) {
        int i = 0;
        while (i < preferenceGroup.getPreferenceCount()) {
            Preference preference = preferenceGroup.getPreference(i);
            if (set.contains(preference.getKey())) {
                preferenceGroup.removePreference$ar$ds(preference);
                i--;
            } else if (preference instanceof PreferenceGroup) {
                hidePreferences((PreferenceGroup) preference, set);
            }
            i++;
        }
    }

    public static Feedback.Part.Builder hintInfoToFeedback$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(ProcessorAccessibilityHints$HintInfo processorAccessibilityHints$HintInfo, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        EventInterpretation eventInterpretation;
        HintEventInterpretation hintEventInterpretation;
        int i;
        int i2;
        int i3;
        boolean z = false;
        int i4 = 2;
        Feedback.Part.Builder interrupt = Feedback.interrupt(0, 2);
        EventInterpretation eventInterpretation2 = null;
        if (processorAccessibilityHints$HintInfo.pendingHintSource != null) {
            int i5 = processorAccessibilityHints$HintInfo.pendingHintEventType;
            if (i5 != 8) {
                if (i5 != 16) {
                    i3 = 1;
                } else {
                    i3 = 6;
                }
            } else {
                i3 = 2;
            }
            hintEventInterpretation = new HintEventInterpretation(i3);
            eventInterpretation = new EventInterpretation(1073741937);
        } else if (processorAccessibilityHints$HintInfo.pendingSelectorHint != null) {
            hintEventInterpretation = new HintEventInterpretation(4);
            CharSequence charSequence = processorAccessibilityHints$HintInfo.pendingSelectorHint;
            hintEventInterpretation.checkIsWritable();
            hintEventInterpretation.mText = charSequence;
            eventInterpretation = new EventInterpretation(1073741937);
        } else if (processorAccessibilityHints$HintInfo.spellingSuggestionHint) {
            hintEventInterpretation = new HintEventInterpretation(5);
            eventInterpretation = new EventInterpretation(1073741937);
        } else {
            eventInterpretation = null;
            hintEventInterpretation = null;
        }
        if (hintEventInterpretation != null) {
            boolean z2 = processorAccessibilityHints$HintInfo.nodeHintForceFeedbackEvenIfAudioPlaybackActive;
            hintEventInterpretation.checkIsWritable();
            hintEventInterpretation.forceFeedbackEvenIfAudioPlaybackActive = z2;
            boolean z3 = processorAccessibilityHints$HintInfo.nodeHintForceFeedbackEvenIfMicrophoneActive;
            hintEventInterpretation.checkIsWritable();
            hintEventInterpretation.forceFeedbackEvenIfMicrophoneActive = z3;
            eventInterpretation.checkIsWritable();
            eventInterpretation.mHint = hintEventInterpretation;
            eventInterpretation2 = eventInterpretation;
        }
        if (eventInterpretation2 != null) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = processorAccessibilityHints$HintInfo.pendingHintSource;
            if (accessibilityNodeInfoCompat != null && ((i2 = processorAccessibilityHints$HintInfo.pendingHintEventType) == 8 || i2 == 32768)) {
                z = true;
            }
            HintEventInterpretation hintEventInterpretation2 = eventInterpretation2.mHint;
            int i6 = hintEventInterpretation2.mHintType;
            CharSequence parseTTSText = hapticPatternParser$$ExternalSyntheticLambda1.parseTTSText(accessibilityNodeInfoCompat, eventInterpretation2.mEvent, eventInterpretation2);
            if (!TextUtils.isEmpty(parseTTSText)) {
                if (true != hintEventInterpretation2.forceFeedbackEvenIfAudioPlaybackActive) {
                    i = 2;
                } else {
                    i = 6;
                }
                if (hintEventInterpretation2.forceFeedbackEvenIfMicrophoneActive) {
                    i |= 8;
                }
                Feedback.Speech.Builder builder = Feedback.Speech.builder();
                builder.setAction$ar$ds$c7b78277_0(Feedback.Speech.Action.SPEAK);
                SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                speakOptions.mFlags = i;
                builder.hintSpeakOptions = speakOptions;
                builder.hint = parseTTSText;
                if (true != z) {
                    i4 = 1;
                }
                builder.setHintInterruptLevel$ar$ds(i4);
                interrupt.speech = builder.build();
                if (i6 == 5 || i6 == 6) {
                    return interrupt.sound(R.raw.typo).vibration(R.array.typo_pattern);
                }
            }
        }
        return interrupt;
    }

    public static boolean isAccessibilityButtonAvailableCompat(AccessibilityButtonController accessibilityButtonController) {
        boolean isAccessibilityButtonAvailable;
        try {
            isAccessibilityButtonAvailable = accessibilityButtonController.isAccessibilityButtonAvailable();
            return isAccessibilityButtonAvailable;
        } catch (NullPointerException e) {
            LogUtils.e("A11yServiceCompatUtils", e.toString(), new Object[0]);
            return false;
        }
    }

    public static boolean isAccessibilityServiceEnabled(Context context, String str) {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        if (accessibilityManager != null && (enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(-1)) != null) {
            Iterator<AccessibilityServiceInfo> it = enabledAccessibilityServiceList.iterator();
            while (it.hasNext()) {
                if (it.next().getId().contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAnimationDisabled(Context context) {
        if (isAtLeastP() && getGlobalFloat(context, "window_animation_scale") == 0.0f && getGlobalFloat(context, "transition_animation_scale") == 0.0f && getGlobalFloat(context, "animator_duration_scale") == 0.0f) {
            return true;
        }
        return false;
    }

    public static boolean isAtLeastOMR1() {
        if (Build.VERSION.SDK_INT >= 27) {
            return true;
        }
        return false;
    }

    public static boolean isAtLeastP() {
        if (Build.VERSION.SDK_INT >= 28) {
            return true;
        }
        return false;
    }

    public static boolean isAtLeastQ() {
        if (Build.VERSION.SDK_INT >= 29) {
            return true;
        }
        return false;
    }

    public static boolean isAtLeastR() {
        if (Build.VERSION.SDK_INT >= 30) {
            return true;
        }
        return false;
    }

    public static boolean isAtLeastT() {
        if (Build.VERSION.SDK_INT >= 33) {
            return true;
        }
        return false;
    }

    public static boolean isAtLeastU() {
        if (Build.VERSION.SDK_INT >= 34) {
            return true;
        }
        return false;
    }

    public static boolean isCaptionable(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null || !isAtLeastR()) {
            return false;
        }
        int role = getRole(accessibilityNodeInfoCompat);
        if (role != 6 && role != 7 && !WebInterfaceUtils.containsImage(accessibilityNodeInfoCompat)) {
            if (accessibilityNodeInfoCompat.getChildCount() != 0) {
                return false;
            }
            return isSmallSizeNode(context, accessibilityNodeInfoCompat);
        }
        return true;
    }

    public static boolean isEmptyOrNotSpannableStringType(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return true;
        }
        if ((charSequence instanceof SpannedString) || (charSequence instanceof SpannableString)) {
            return false;
        }
        if (!(charSequence instanceof SpannableStringBuilder)) {
            return true;
        }
        return false;
    }

    public static boolean isFromOnScreenKeyboard(AccessibilityEvent accessibilityEvent) {
        if (getSourceRole(accessibilityEvent) == 24) {
            return false;
        }
        if (accessibilityEvent == null || AccessibilityNodeInfoUtils.getWindowType(AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource())) != 2) {
            CharSequence packageName = accessibilityEvent.getPackageName();
            if (packageName != null) {
                String charSequence = packageName.toString();
                if (charSequence.startsWith("com.android.inputmethod") || charSequence.startsWith("com.google.android.inputmethod") || charSequence.startsWith("com.google.android.apps.inputmethod")) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private static boolean isGmsAppWindow(int i, CharSequence charSequence) {
        if (shouldCheckRootForGmsAppWindow(i) && TextUtils.equals("com.google.android.gms", charSequence)) {
            return true;
        }
        return false;
    }

    public static boolean isHtmlMacroGranularity(int i) {
        if ((i & 262144) != 0) {
            return true;
        }
        return false;
    }

    public static boolean isHtmlTarget(int i) {
        if ((65536 & i) == 0 && !isHtmlMacroGranularity(i)) {
            return false;
        }
        return true;
    }

    public static boolean isInputWindowOnScreen(AccessibilityService accessibilityService) {
        if (getOnscreenInputWindowInfo(accessibilityService) != null) {
            return true;
        }
        return false;
    }

    public static boolean isMacroGranularity(int i) {
        if ((1048576 & i) == 0 && !isHtmlMacroGranularity(i) && i != 202) {
            return false;
        }
        return true;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null) {
            LogUtils.v("ResourceUtils", "No active network.", new Object[0]);
            return false;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        if (networkCapabilities == null) {
            LogUtils.v("ResourceUtils", "Can't get the capability of the active network.", new Object[0]);
            return false;
        }
        if (networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16)) {
            return true;
        }
        LogUtils.v("ResourceUtils", "No or unverified network capabilities", new Object[0]);
        return false;
    }

    public static boolean isPictureInPicture(AccessibilityWindowInfo accessibilityWindowInfo) {
        boolean isInPictureInPictureMode;
        if (accessibilityWindowInfo == null) {
            return false;
        }
        isInPictureInPictureMode = accessibilityWindowInfo.isInPictureInPictureMode();
        if (isInPictureInPictureMode) {
            return true;
        }
        return false;
    }

    public static boolean isSmallSizeNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Rect rect = new Rect();
        accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        if (!rect.isEmpty() && TextUtils.isEmpty(AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat)) && rect.height() <= context.getResources().getDisplayMetrics().density * 150.0f) {
            return true;
        }
        return false;
    }

    public static boolean isTalkBackPackage(CharSequence charSequence) {
        return TextUtils.equals(charSequence, "com.google.android.marvin.talkback");
    }

    public static boolean isWrappedWithTargetSpan$ar$ds(CharSequence charSequence, Class cls) {
        Spannable spannable;
        Object[] spans;
        if (!TextUtils.isEmpty(charSequence) && (charSequence instanceof Spannable) && !TextUtils.isEmpty(charSequence) && (spans = (spannable = (Spannable) charSequence).getSpans(0, charSequence.length(), cls)) != null && spans.length == 1) {
            Object obj = spans[0];
            if (spannable.getSpanStart(obj) == 0 && spannable.getSpanEnd(obj) == spannable.length()) {
                return true;
            }
        }
        return false;
    }

    private static int load32(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = bArr[i + 1] & 255;
        int i4 = bArr[i + 2] & 255;
        return ((bArr[i + 3] & 255) << 24) | (i3 << 8) | i2 | (i4 << 16);
    }

    private static long load64(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }

    public static void log(String str, int i, String str2, Object... objArr) {
        if (LogUtils.shouldLog$ar$ds()) {
            char[] cArr = new char[Math.max(0, i + i)];
            Arrays.fill(cArr, ' ');
            LogUtils.v(str, "%s %s", new String(cArr), String.format(str2, objArr));
        }
    }

    public static boolean needImageCaptionForUnlabelledView(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int role;
        if (isCaptionable(context, accessibilityNodeInfoCompat) && (role = getRole(accessibilityNodeInfoCompat)) != 10 && role != 18 && TextUtils.isEmpty(accessibilityNodeInfoCompat.getStateDescription())) {
            return TextUtils.isEmpty(AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat));
        }
        return false;
    }

    public static Feedback.Part.Builder nodeEventToHint$ar$class_merging$efaf24de_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, boolean z2, Context context, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        ProcessorAccessibilityHints$HintInfo processorAccessibilityHints$HintInfo = new ProcessorAccessibilityHints$HintInfo();
        processorAccessibilityHints$HintInfo.pendingHintSource = accessibilityNodeInfoCompat;
        if (i == 0) {
            i = 32768;
        }
        processorAccessibilityHints$HintInfo.pendingHintEventType = i;
        processorAccessibilityHints$HintInfo.nodeHintForceFeedbackEvenIfAudioPlaybackActive = z;
        processorAccessibilityHints$HintInfo.nodeHintForceFeedbackEvenIfMicrophoneActive = z2;
        return hintInfoToFeedback$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(processorAccessibilityHints$HintInfo, hapticPatternParser$$ExternalSyntheticLambda1);
    }

    public static boolean performAction(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Bundle bundle, Performance.EventId eventId) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        boolean performAction = accessibilityNodeInfoCompat.performAction(i, bundle);
        LogUtils.d("PerformActionUtils", "perform action=%d=%s returns %s with args=%s on node=%s for event=%s", Integer.valueOf(i), AccessibilityNodeInfoUtils.actionToString(i), Boolean.valueOf(performAction), bundle, accessibilityNodeInfoCompat, eventId);
        return performAction;
    }

    public static void putBooleanPref(SharedPreferences sharedPreferences, Resources resources, int i, boolean z) {
        storeBooleanAsync(sharedPreferences, resources.getString(i), z);
    }

    public static void putIntPref(SharedPreferences sharedPreferences, Resources resources, int i, int i2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(resources.getString(i), i2);
        edit.apply();
    }

    public static void putStringPref(SharedPreferences sharedPreferences, Resources resources, int i, String str) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(resources.getString(i), str);
        edit.apply();
    }

    public static int readOnScreenKeyboardEcho(SharedPreferences sharedPreferences, Resources resources) {
        return Integer.parseInt(getPreferenceValueString(sharedPreferences, resources, resources.getString(R.string.pref_keyboard_echo_on_screen_key), resources.getString(R.string.pref_keyboard_echo_default)));
    }

    public static int readPhysicalKeyboardEcho(SharedPreferences sharedPreferences, Resources resources) {
        return Integer.parseInt(getPreferenceValueString(sharedPreferences, resources, resources.getString(R.string.pref_keyboard_echo_physical_key), resources.getString(R.string.pref_keyboard_echo_default)));
    }

    public static void remove(SharedPreferences sharedPreferences, String... strArr) {
        for (String str : strArr) {
            if (sharedPreferences.contains(str)) {
                sharedPreferences.edit().remove(str).apply();
            }
        }
    }

    public static void requestPermissions(Context context, String... strArr) {
        Intent intent = new Intent(context, (Class<?>) PermissionRequestActivity.class);
        intent.putExtra("permissions", strArr);
        intent.addFlags(402653216);
        context.startActivity(intent);
    }

    public static void requestPostNotificationPermissionIfNeeded(Context context, BroadcastReceiver broadcastReceiver) {
        if (hasPostNotificationPermission(context)) {
            return;
        }
        if (broadcastReceiver != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("done");
            EditorInfoCompat.registerReceiver$ar$ds(context, broadcastReceiver, intentFilter, 2);
        }
        requestPermissions(context, "android.permission.POST_NOTIFICATIONS");
    }

    public static String roleToString(int i) {
        switch (i) {
            case 0:
                return "ROLE_NONE";
            case 1:
                return "ROLE_BUTTON";
            case 2:
                return "ROLE_CHECK_BOX";
            case 3:
                return "ROLE_DROP_DOWN_LIST";
            case 4:
                return "ROLE_EDIT_TEXT";
            case 5:
                return "ROLE_GRID";
            case 6:
                return "ROLE_IMAGE";
            case 7:
                return "ROLE_IMAGE_BUTTON";
            case 8:
                return "ROLE_LIST";
            case 9:
                return "ROLE_RADIO_BUTTON";
            case 10:
                return "ROLE_SEEK_CONTROL";
            case 11:
                return "ROLE_SWITCH";
            case 12:
                return "ROLE_TAB_BAR";
            case 13:
                return "ROLE_TOGGLE_BUTTON";
            case 14:
                return "ROLE_VIEW_GROUP";
            case 15:
                return "ROLE_WEB_VIEW";
            case 16:
                return "ROLE_PAGER";
            case 17:
                return "ROLE_CHECKED_TEXT_VIEW";
            case 18:
                return "ROLE_PROGRESS_BAR";
            case 19:
                return "ROLE_ACTION_BAR_TAB";
            case 20:
                return "ROLE_DRAWER_LAYOUT";
            case 21:
                return "ROLE_SLIDING_DRAWER";
            case 22:
                return "ROLE_ICON_MENU";
            case 23:
                return "ROLE_TOAST";
            case 24:
                return "ROLE_ALERT_DIALOG";
            case 25:
                return "ROLE_DATE_PICKER_DIALOG";
            case 26:
                return "ROLE_TIME_PICKER_DIALOG";
            case 27:
                return "ROLE_DATE_PICKER";
            case 28:
                return "ROLE_TIME_PICKER";
            case 29:
                return "ROLE_NUMBER_PICKER";
            case 30:
                return "ROLE_SCROLL_VIEW";
            case 31:
                return "ROLE_HORIZONTAL_SCROLL_VIEW";
            case 32:
                return "ROLE_KEYBOARD_KEY";
            case 33:
                return "ROLE_TALKBACK_EDIT_TEXT_OVERLAY";
            case 34:
                return "ROLE_TEXT_ENTRY_KEY";
            default:
                return "ROLE_STAGGERED_GRID";
        }
    }

    public static float scaleValue(float f, float f2, float f3, float f4, float f5) {
        if (f2 == f3) {
            return f4;
        }
        return f4 + (((f - f2) / (f3 - f2)) * (f5 - f4));
    }

    public static boolean searchSpannableStringsForClickableSpanInNodeTree(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Set set, List list, Class cls) {
        if (accessibilityNodeInfoCompat == null || !set.add(accessibilityNodeInfoCompat)) {
            return false;
        }
        CharSequence text = AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat);
        SpannableString spannableString = null;
        if (isEmptyOrNotSpannableStringType(text)) {
            LogUtils.v("SpannableUtils", "text(%s) isEmptyOrNotSpannableStringType", text);
        } else {
            SpannableString valueOf = SpannableString.valueOf(text);
            ClickableSpan[] clickableSpanArr = (ClickableSpan[]) valueOf.getSpans(0, valueOf.length(), cls);
            if (clickableSpanArr != null && clickableSpanArr.length != 0) {
                LogUtils.v("SpannableUtils", "text(%s) has SpannableString(%s)", text, valueOf);
                spannableString = valueOf;
            } else {
                LogUtils.v("SpannableUtils", "text(%s) has null or empty ClickableSpan[]", text);
            }
        }
        boolean z = !TextUtils.isEmpty(spannableString);
        if (z) {
            if (list == null) {
                return true;
            }
            list.add(spannableString);
        }
        if (!TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription())) {
            LogUtils.v("SpannableTraversalUtils", "Root has content description, skipping searching the children nodes.", new Object[0]);
            return z;
        }
        ReorderedChildrenIterator createAscendingIterator = ReorderedChildrenIterator.createAscendingIterator(accessibilityNodeInfoCompat);
        boolean z2 = false;
        while (createAscendingIterator.hasNext()) {
            AccessibilityNodeInfoCompat next = createAscendingIterator.next();
            if (AccessibilityNodeInfoUtils.FILTER_NON_FOCUSABLE_VISIBLE_NODE.accept(next)) {
                z2 |= searchSpannableStringsForClickableSpanInNodeTree(next, set, list, cls);
            }
            if (z2 && list == null) {
                return true;
            }
        }
        if (!z && !z2) {
            return false;
        }
        return true;
    }

    public static void setMenuItemDeferredType$ar$edu(ContextMenu contextMenu, int i, int i2) {
        ContextMenuItem findItemInMenuOrSubmenus = contextMenu.findItemInMenuOrSubmenus(i);
        if (findItemInMenuOrSubmenus != null) {
            findItemInMenuOrSubmenus.deferredType$ar$edu = i2;
        }
    }

    public static void setMenuItemShowsDialog(ContextMenu contextMenu, int i, boolean z) {
        ContextMenuItem findItemInMenuOrSubmenus = contextMenu.findItemInMenuOrSubmenus(i);
        if (findItemInMenuOrSubmenus != null) {
            findItemInMenuOrSubmenus.showsAlertDialog = z;
        }
    }

    public static void setSkipRefocusAndWindowAnnounce$ar$ds(ContextMenu contextMenu, int i) {
        ContextMenuItem findItemInMenuOrSubmenus = contextMenu.findItemInMenuOrSubmenus(i);
        if (findItemInMenuOrSubmenus != null) {
            findItemInMenuOrSubmenus.setSkipRefocusEvents$ar$ds();
            findItemInMenuOrSubmenus.setSkipWindowEvents$ar$ds();
        }
    }

    public static void setWindowTypeToDialog(Window window) {
        window.setType(2032);
    }

    private static long shiftMix(long j) {
        return j ^ (j >>> 47);
    }

    private static boolean shouldCheckRootForGmsAppWindow(int i) {
        if (Build.VERSION.SDK_INT == 33 && i == -1) {
            return true;
        }
        return false;
    }

    public static boolean shouldShowTraining(VendorConfig vendorConfig) {
        if (vendorConfig == null) {
            if ((!Build.BRAND.equals("google") || (!Build.PRODUCT.equals("sabrina") && !Build.PRODUCT.equals("boreal"))) && !Build.BRAND.equals("ADT-3") && !Build.BRAND.equals("ADT-4")) {
                return false;
            }
            return true;
        }
        return true;
    }

    public static AccessibilityNodeInfoCompat sourceCompat(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return null;
        }
        return AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
    }

    public static String spansToStringForLogging(CharSequence charSequence) {
        LocaleList locales;
        int size;
        int i;
        Locale locale;
        Locale locale2;
        if (LogUtils.shouldLog$ar$ds() && !isEmptyOrNotSpannableStringType(charSequence)) {
            Spanned spanned = (Spanned) charSequence;
            ParcelableSpan[] parcelableSpanArr = (ParcelableSpan[]) spanned.getSpans(0, charSequence.length(), ParcelableSpan.class);
            if (parcelableSpanArr.length != 0) {
                StringBuilder sb = new StringBuilder();
                for (ParcelableSpan parcelableSpan : parcelableSpanArr) {
                    sb.append("{");
                    sb.append(parcelableSpan.getClass().getSimpleName());
                    int spanStart = spanned.getSpanStart(parcelableSpan);
                    int spanEnd = spanned.getSpanEnd(parcelableSpan);
                    if (spanStart >= 0 && spanEnd >= 0 && spanStart != spanEnd) {
                        sb.append(" '");
                        sb.append((CharSequence) spanned, spanStart, spanEnd);
                        sb.append("'");
                        if (parcelableSpan instanceof LocaleSpan) {
                            locales = ((LocaleSpan) parcelableSpan).getLocales();
                            size = locales.size();
                            if (size > 0) {
                                sb.append(" locale=[");
                                int i2 = 0;
                                while (true) {
                                    i = size - 1;
                                    if (i2 >= i) {
                                        break;
                                    }
                                    locale2 = locales.get(i2);
                                    sb.append(locale2);
                                    sb.append(",");
                                    i2++;
                                }
                                locale = locales.get(i);
                                sb.append(locale);
                                sb.append("]");
                            }
                        } else if (parcelableSpan instanceof TtsSpan) {
                            TtsSpan ttsSpan = (TtsSpan) parcelableSpan;
                            sb.append(" ttsType=");
                            sb.append(ttsSpan.getType());
                            PersistableBundle args = ttsSpan.getArgs();
                            Set<String> keySet = args.keySet();
                            if (!keySet.isEmpty()) {
                                for (String str : keySet) {
                                    sb.append(" ");
                                    sb.append(str);
                                    sb.append("=");
                                    sb.append(args.get(str));
                                }
                            }
                        } else if (parcelableSpan instanceof URLSpan) {
                            sb.append(" url=");
                            sb.append(((URLSpan) parcelableSpan).getURL());
                        }
                        sb.append("}");
                    } else {
                        sb.append(" invalid index:[");
                        sb.append(spanStart);
                        sb.append(",");
                        sb.append(spanEnd);
                        sb.append("]}");
                    }
                }
                return sb.toString();
            }
            return null;
        }
        return null;
    }

    public static void storeBooleanAsync(SharedPreferences sharedPreferences, String str, boolean z) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static void stripTargetSpanFromText(CharSequence charSequence, Class cls) {
        if (!TextUtils.isEmpty(charSequence) && (charSequence instanceof SpannableString)) {
            SpannableString spannableString = (SpannableString) charSequence;
            Object[] spans = spannableString.getSpans(0, spannableString.length(), cls);
            if (spans != null) {
                for (Object obj : spans) {
                    if (obj != null) {
                        spannableString.removeSpan(obj);
                    }
                }
            }
        }
    }

    public static boolean supportDynamicFeatures() {
        if (FormFactorUtils.getInstance().isAndroidTv || FormFactorUtils.getInstance().isAndroidAuto || FormFactorUtils.getInstance().isAndroidWear) {
            return false;
        }
        for (String str : Build.SUPPORTED_32_BIT_ABIS) {
            if (str.contains("x86")) {
                return false;
            }
        }
        for (String str2 : Build.SUPPORTED_64_BIT_ABIS) {
            if (str2.contains("x86_64")) {
                return false;
            }
        }
        return true;
    }

    public static boolean supportMultipleAutoScroll() {
        return FormFactorUtils.getInstance().isAndroidWear;
    }

    public static boolean supportTextSuggestion() {
        if (!FormFactorUtils.getInstance().isAndroidWear) {
            return true;
        }
        return false;
    }

    public static Feedback toFeedback(Performance.EventId eventId, Feedback.Focus.Builder builder) {
        Feedback.Part.Builder builder2 = Feedback.Part.builder();
        builder2.focus = builder.build();
        return Feedback.create(eventId, builder2.build());
    }

    public static Feedback.Part toFeedbackPart(Feedback.Focus.Action action, ScreenState screenState) {
        Feedback.Part.Builder builder = Feedback.Part.builder();
        Feedback.Focus.Builder focus = Feedback.focus(action);
        focus.screenState = screenState;
        builder.focus = focus.build();
        return builder.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String toStringShort(android.view.accessibility.AccessibilityEvent r37) {
        /*
            Method dump skipped, instructions count: 465
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.toStringShort(android.view.accessibility.AccessibilityEvent):java.lang.String");
    }

    public static String toVerbosityPrefKey(String str, String str2) {
        return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str2, str, "_");
    }

    public static CharSequence trimText(CharSequence charSequence) {
        int length = charSequence.length() - 1;
        int i = 0;
        while (i <= length && Character.isWhitespace(charSequence.charAt(i))) {
            i++;
        }
        while (length > i && Character.isWhitespace(charSequence.charAt(length))) {
            length--;
        }
        return charSequence.subSequence(i, length + 1);
    }

    private static PageConfig.Builder tvPageConfigToPageConfigBuilder(PageConfig.PageId pageId, TvPageConfig tvPageConfig) {
        PageConfig.Builder builder = new PageConfig.Builder(pageId, tvPageConfig.title());
        builder.setOnlyOneFocus$ar$ds();
        builder.image = tvPageConfig.image();
        String summary = tvPageConfig.summary();
        summary.getClass();
        List list = builder.contents;
        Text.Paragraph.Builder builder2 = Text.Paragraph.builder();
        builder2.textString = summary;
        list.add(new Text(builder2.autoBuild()));
        return builder;
    }

    public static String typeToString(int i) {
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 4:
                        return "TYPE_VIEW_SELECTED";
                    case 8:
                        return "TYPE_VIEW_FOCUSED";
                    case 16:
                        return "TYPE_VIEW_TEXT_CHANGED";
                    case 32:
                        return "TYPE_WINDOW_STATE_CHANGED";
                    case 64:
                        return "TYPE_NOTIFICATION_STATE_CHANGED";
                    case BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE /* 128 */:
                        return "TYPE_VIEW_HOVER_ENTER";
                    case 256:
                        return "TYPE_VIEW_HOVER_EXIT";
                    case 512:
                        return "TYPE_TOUCH_EXPLORATION_GESTURE_START";
                    case 1024:
                        return "TYPE_TOUCH_EXPLORATION_GESTURE_END";
                    case 2048:
                        return "TYPE_WINDOW_CONTENT_CHANGED";
                    case 4096:
                        return "TYPE_VIEW_SCROLLED";
                    case 8192:
                        return "TYPE_VIEW_TEXT_SELECTION_CHANGED";
                    case 16384:
                        return "TYPE_ANNOUNCEMENT";
                    case 32768:
                        return "TYPE_VIEW_ACCESSIBILITY_FOCUSED";
                    case 65536:
                        return "TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED";
                    case 131072:
                        return "TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY";
                    case 262144:
                        return "TYPE_GESTURE_DETECTION_START";
                    case 524288:
                        return "TYPE_GESTURE_DETECTION_END";
                    case 1048576:
                        return "TYPE_TOUCH_INTERACTION_START";
                    case 2097152:
                        return "TYPE_TOUCH_INTERACTION_END";
                    case 4194304:
                        return "TYPE_WINDOWS_CHANGED";
                    case 8388608:
                        return "TYPE_VIEW_CONTEXT_CLICKED";
                    case 16777216:
                        return "TYPE_ASSIST_READING_CONTEXT";
                    default:
                        return "(unhandled)";
                }
            }
            return "TYPE_VIEW_LONG_CLICKED";
        }
        return "TYPE_VIEW_CLICKED";
    }

    public static int[] values$ar$edu$3d020c88_0() {
        return new int[]{1, 2, 3, 4, 5, 6};
    }

    public static int[] values$ar$edu$592e4ba4_0() {
        return new int[]{1, 2, 3};
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long load64 = j + load64(bArr, i);
        long load642 = load64(bArr, i + 8);
        long load643 = load64(bArr, i + 16);
        long load644 = load64(bArr, i + 24);
        long j3 = load642 + load64 + load643;
        long rotateRight = Long.rotateRight(j2 + load64 + load644, 21) + Long.rotateRight(j3, 44);
        jArr[0] = j3 + load644;
        jArr[1] = rotateRight + load64;
    }

    public static CharSequence wrapWithIdentifierSpan(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new SpannableUtils$IdentifierSpan(), 0, charSequence.length(), 0);
        return spannableString;
    }

    public static CharSequence wrapWithLocaleSpan(CharSequence charSequence, Locale locale) {
        if (locale != null) {
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(new LocaleSpan(locale), 0, spannableString.length(), 0);
            return spannableString;
        }
        return charSequence;
    }

    public static CharSequence wrapWithNonCopyableTextSpan(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new SpannableUtils$NonCopyableTextSpan(), 0, charSequence.length(), 33);
        return spannableString;
    }

    @Deprecated
    public Api$Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return buildClient(context, looper, clientSettings, obj, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
    }

    public SpannableUtils$IdentifierSpan(byte[] bArr, byte[] bArr2) {
    }

    public Api$Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException("buildClient must be implemented");
    }

    public SpannableUtils$IdentifierSpan(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this((byte[]) null, (byte[]) null);
    }

    public static int getRole(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return 0;
        }
        if (accessibilityNodeInfoCompat.isTextEntryKey()) {
            return 34;
        }
        CharSequence className = accessibilityNodeInfoCompat.getClassName();
        if (ClassLoadingCache.checkInstanceOf(className, "TalkbackEditTextOverlay")) {
            return 33;
        }
        if (ClassLoadingCache.checkInstanceOf(className, ImageView.class)) {
            return accessibilityNodeInfoCompat.isClickable() ? 7 : 6;
        }
        if (className != null && className.toString().equals("android.widget.Image")) {
            return accessibilityNodeInfoCompat.isClickable() ? 7 : 6;
        }
        if (ClassLoadingCache.checkInstanceOf(className, Switch.class)) {
            return 11;
        }
        if (ClassLoadingCache.checkInstanceOf(className, ToggleButton.class)) {
            return 13;
        }
        if (ClassLoadingCache.checkInstanceOf(className, RadioButton.class)) {
            return 9;
        }
        if (ClassLoadingCache.checkInstanceOf(className, CompoundButton.class)) {
            return 2;
        }
        if (ClassLoadingCache.checkInstanceOf(className, Button.class)) {
            return 1;
        }
        if (ClassLoadingCache.checkInstanceOf(className, CheckedTextView.class)) {
            return 17;
        }
        if (ClassLoadingCache.checkInstanceOf(className, EditText.class)) {
            return (!accessibilityNodeInfoCompat.isEnabled() || accessibilityNodeInfoCompat.isEditable()) ? 4 : 0;
        }
        if (ClassLoadingCache.checkInstanceOf(className, SeekBar.class)) {
            return 10;
        }
        if (AccessibilityNodeInfoUtils.hasValidRangeInfo(accessibilityNodeInfoCompat) && AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, android.R.id.accessibilityActionSetProgress)) {
            return 10;
        }
        if (ClassLoadingCache.checkInstanceOf(className, ProgressBar.class)) {
            return 18;
        }
        if (AccessibilityNodeInfoUtils.hasValidRangeInfo(accessibilityNodeInfoCompat) && !AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, android.R.id.accessibilityActionSetProgress)) {
            return 18;
        }
        if (ClassLoadingCache.checkInstanceOf(className, Keyboard.Key.class)) {
            return 32;
        }
        if (ClassLoadingCache.checkInstanceOf(className, WebView.class)) {
            return 15;
        }
        if (ClassLoadingCache.checkInstanceOf(className, TabWidget.class)) {
            return 12;
        }
        if (ClassLoadingCache.checkInstanceOf(className, HorizontalScrollView.class) && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() == null) {
            return 31;
        }
        if (ClassLoadingCache.checkInstanceOf(className, ScrollView.class)) {
            return 30;
        }
        if (!ClassLoadingCache.checkInstanceOf(className, ViewPager.class) && !ClassLoadingCache.checkInstanceOf(className, "android.support.v4.view.ViewPager") && !ClassLoadingCache.checkInstanceOf(className, "androidx.core.view.ViewPager") && !ClassLoadingCache.checkInstanceOf(className, "com.android.internal.widget.ViewPager")) {
            if (ClassLoadingCache.checkInstanceOf(className, NumberPicker.class)) {
                return 29;
            }
            if (ClassLoadingCache.checkInstanceOf(className, Spinner.class)) {
                return 3;
            }
            if (ClassLoadingCache.checkInstanceOf(className, GridView.class)) {
                return 5;
            }
            if (ClassLoadingCache.checkInstanceOf(className, AbsListView.class)) {
                return 8;
            }
            if (!AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_UP.getId()) && !AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_DOWN.getId()) && !AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_LEFT.getId()) && !AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_RIGHT.getId())) {
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionInfo$ar$class_merging = accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging();
                if (collectionInfo$ar$class_merging == null) {
                    return ClassLoadingCache.checkInstanceOf(className, ViewGroup.class) ? 14 : 0;
                }
                if (ClassLoadingCache.checkInstanceOf(className, "android.widget.listview")) {
                    return 8;
                }
                if (ClassLoadingCache.checkInstanceOf(className, "android.widget.gridview")) {
                    return 5;
                }
                if (ClassLoadingCache.checkInstanceOf(className, "androidx.recyclerview.widget.StaggeredGridLayoutManager")) {
                    return 35;
                }
                return (collectionInfo$ar$class_merging.getRowCount() <= 1 || collectionInfo$ar$class_merging.getColumnCount() <= 1) ? 8 : 5;
            }
        }
        return 16;
    }

    public SpannableUtils$IdentifierSpan(byte[] bArr, char[] cArr) {
        this();
    }

    public static int getDisplayId(AccessibilityWindowInfo accessibilityWindowInfo) {
        int displayId;
        if (!isAtLeastR()) {
            return 0;
        }
        displayId = accessibilityWindowInfo.getDisplayId();
        return displayId;
    }

    public static SharedPreferences getSharedPreferences(Context context, String str) {
        Context createDeviceProtectedStorageContext = ContextCompat$Api24Impl.createDeviceProtectedStorageContext(context);
        if (createDeviceProtectedStorageContext != null) {
            return createDeviceProtectedStorageContext.getSharedPreferences(str, 0);
        }
        return context.getSharedPreferences(str, 0);
    }

    public SpannableUtils$IdentifierSpan(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        new ArrayDeque();
    }

    public static int getType(AccessibilityWindowInfoCompat accessibilityWindowInfoCompat) {
        if (accessibilityWindowInfoCompat == null) {
            return -1;
        }
        int type = accessibilityWindowInfoCompat.getType();
        if (shouldCheckRootForGmsAppWindow(type)) {
            AccessibilityNodeInfoCompat root = accessibilityWindowInfoCompat.getRoot();
            if (isGmsAppWindow(type, root == null ? null : root.getPackageName())) {
                return 1;
            }
        }
        return type;
    }

    public static boolean performAction(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Performance.EventId eventId) {
        return performAction(accessibilityNodeInfoCompat, i, null, eventId);
    }

    public void onFail() {
    }

    public void onLabelsExported(File file) {
    }
}

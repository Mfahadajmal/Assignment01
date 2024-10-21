package com.google.android.accessibility.talkback.compositor;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.TimedFlags;
import com.google.android.accessibility.utils.input.WindowsDelegate;
import com.google.android.accessibility.utils.monitor.CollectionState;
import com.google.android.accessibility.utils.monitor.InputModeTracker;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;
import googledata.experiments.mobile.accessibility_suite.features.SpeechConfig;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GlobalVariables extends TimedFlags {
    public final CollectionState collectionState;
    public final boolean enableMediaControlHintForCall;
    public final boolean enableShortAndLongDurationsForSpecificApps;
    public final GestureShortcutProvider gestureShortcutProvider;
    public final InputModeTracker inputModeTracker;
    public KeyComboManager keyComboManager;
    public final Context mContext;
    public boolean mLastTextEditIsPassword;
    public boolean mSelectionModeActive;
    public final AccessibilityService mService;
    public WindowsDelegate mWindowsDelegate;
    public MagnificationState magnificationState;
    public WindowTrackerFactory nodeMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging;
    public SelectorController selectorController;
    public Locale userPreferredLocale;
    public boolean mUseSingleTap = false;
    public int mLastWindowId = -1;
    public int mCurrentWindowId = -1;
    public int currentDisplayId = -1;
    public boolean isCurrentFocusInScrollableNode = false;
    public boolean isLastFocusInScrollableNode = false;
    public boolean isFocusPage = false;
    public boolean isInterpretAsEntryKey = false;
    public boolean mShouldSpeakPasswords = true;
    public boolean usageHintEnabled = true;
    public boolean sayCapital = false;
    public boolean speakRoles = true;
    public boolean speakCollectionInfo = true;
    public int descriptionOrder = 0;
    public boolean speakElementIds = false;
    public boolean speakSystemWindowTitles = true;
    public boolean textChangeRateUnlimited = false;
    private final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();

    public GlobalVariables(AccessibilityService accessibilityService, InputModeTracker inputModeTracker, CollectionState collectionState, GestureShortcutProvider gestureShortcutProvider) {
        this.mContext = accessibilityService;
        this.mService = accessibilityService;
        this.inputModeTracker = inputModeTracker;
        this.collectionState = collectionState;
        this.gestureShortcutProvider = gestureShortcutProvider;
        this.enableMediaControlHintForCall = SpeechConfig.INSTANCE.get().enableMediaControlHintForCall(accessibilityService);
        this.enableShortAndLongDurationsForSpecificApps = SpeechConfig.INSTANCE.get().enableShortAndLongDurationsForSpecificApps(accessibilityService);
    }

    private static CharSequence conditionalAppend(CharSequence charSequence, CharSequence charSequence2) {
        return CompositorUtils.conditionalAppend(charSequence, charSequence2, CompositorUtils.separator);
    }

    private static CharSequence conditionalPrepend(CharSequence charSequence, CharSequence charSequence2) {
        return CompositorUtils.conditionalPrepend(charSequence, charSequence2, CompositorUtils.separator);
    }

    private static CharSequence dedupJoin(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return CompositorUtils.dedupJoin(charSequence, charSequence2, charSequence3);
    }

    private static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        return TextUtils.equals(charSequence, charSequence2);
    }

    private CharSequence prependCapital(CharSequence charSequence) {
        if (this.sayCapital) {
            return CompositorUtils.prependCapital(charSequence, this.mContext);
        }
        return charSequence;
    }

    private static int round(double d) {
        return (int) Math.round(d);
    }

    private static int roundForProgressInt(double d) {
        return (int) d;
    }

    private static int roundForProgressPercent(double d) {
        return AccessibilityNodeInfoUtils.roundForProgressPercent(d);
    }

    private CharSequence spelling(CharSequence charSequence) {
        return SpannableUtils$NonCopyableTextSpan.spelling(charSequence, this.mContext);
    }

    public final int getCollectionSelectionMode() {
        return this.collectionState.getSelectionMode();
    }

    public final CharSequence getGestureStringForReadingMenuNextSetting() {
        if (this.inputModeTracker.mInputMode == 1) {
            CharSequence keyComboStringRepresentation = getKeyComboStringRepresentation(R.string.keycombo_shortcut_global_scroll_backward_reading_menu);
            if (!TextUtils.isEmpty(keyComboStringRepresentation)) {
                return keyComboStringRepresentation;
            }
        }
        GestureShortcutProvider gestureShortcutProvider = this.gestureShortcutProvider;
        if (gestureShortcutProvider != null) {
            GestureShortcutMapping gestureShortcutMapping = (GestureShortcutMapping) gestureShortcutProvider;
            return gestureShortcutMapping.getGestureFromActionKey(gestureShortcutMapping.actionNextReadingMenuSetting);
        }
        return "";
    }

    public final CharSequence getGlobalAdjustableHint() {
        CharSequence readingMenuUpShortcut;
        CharSequence readingMenuDownShortcut;
        GestureShortcutProvider gestureShortcutProvider = this.gestureShortcutProvider;
        if (gestureShortcutProvider == null) {
            readingMenuUpShortcut = "";
        } else {
            readingMenuUpShortcut = gestureShortcutProvider.readingMenuUpShortcut();
        }
        GestureShortcutProvider gestureShortcutProvider2 = this.gestureShortcutProvider;
        if (gestureShortcutProvider2 == null) {
            readingMenuDownShortcut = "";
        } else {
            readingMenuDownShortcut = gestureShortcutProvider2.readingMenuDownShortcut();
        }
        if (!TextUtils.isEmpty(readingMenuUpShortcut) && !TextUtils.isEmpty(readingMenuDownShortcut)) {
            return this.mContext.getString(R.string.template_hint_adjustable_2gesture, readingMenuUpShortcut, readingMenuDownShortcut);
        }
        if (TextUtils.isEmpty(readingMenuUpShortcut) && TextUtils.isEmpty(readingMenuDownShortcut)) {
            if (this.formFactorUtils.isAndroidWear) {
                return "";
            }
            Context context = this.mContext;
            return context.getString(R.string.no_adjust_setting_gesture, ContextDataProvider.toLowerCase(context.getString(R.string.shortcut_selected_setting_next_action)));
        }
        Context context2 = this.mContext;
        if (true == TextUtils.isEmpty(readingMenuUpShortcut)) {
            readingMenuUpShortcut = readingMenuDownShortcut;
        }
        return context2.getString(R.string.template_hint_adjustable_1gesture, readingMenuUpShortcut);
    }

    public final int getGlobalInputMode() {
        return this.inputModeTracker.mInputMode;
    }

    public final long getKeyComboCodeForKey(int i) {
        return SpannableUtils$NonCopyableTextSpan.getKeyComboCodeForKey(i, this.keyComboManager, this.mContext);
    }

    public final CharSequence getKeyComboStringRepresentation(int i) {
        KeyComboManager keyComboManager = this.keyComboManager;
        if (keyComboManager == null) {
            return "";
        }
        long keyComboCodeForKey = SpannableUtils$NonCopyableTextSpan.getKeyComboCodeForKey(i, keyComboManager, this.mContext);
        return keyComboManager.getKeyComboStringRepresentation(KeyComboManager.getKeyComboCode(KeyComboManager.getModifier(keyComboCodeForKey) | keyComboManager.keyComboModel.getTriggerModifier(), (int) keyComboCodeForKey));
    }

    public final Locale getPreferredLocaleByNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Locale locale = this.userPreferredLocale;
        if (locale == null) {
            return AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
        }
        return locale;
    }

    public final boolean hasReadingMenuActionSettings() {
        SelectorController selectorController = this.selectorController;
        if (selectorController != null && selectorController.isSettingAvailable(SelectorController.Setting.ACTIONS)) {
            return true;
        }
        return false;
    }
}

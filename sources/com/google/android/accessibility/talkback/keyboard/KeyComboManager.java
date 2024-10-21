package com.google.android.accessibility.talkback.keyboard;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.core.app.NotificationCompat$BigTextStyle;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.preference.SwitchPreference;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl;
import com.google.android.accessibility.talkback.actor.FullScreenReadActor;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$ModifierKey;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.permission.PermissionRequestActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.ServiceKeyEventListener;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactory;
import com.google.android.marvin.talkback.R;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class KeyComboManager implements ServiceKeyEventListener {
    private TalkBackAnalytics analytics;
    public Context context;
    private long currentKeyComboCode;
    private long currentKeyComboTime;
    private Set currentKeysDown;
    public int hardwareKeyboardStatus;
    private boolean hasPartialMatch;
    private ProtoDataStoreFactory keyComboMapper$ar$class_merging$ar$class_merging;
    public KeyComboModel keyComboModel;
    public ServiceKeyEventListener keyEventDelegate;
    private Notification keymapNotification;
    public boolean matchKeyCombo;
    public NotificationManager notificationManager;
    public final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
    private Set passedKeys;
    private boolean performedCombo;
    private long previousKeyComboCode;
    private long previousKeyComboTime;
    private int serviceState;
    public SharedPreferences sharedPreferences;
    private A11yAlertDialogWrapper updateModifierKeysDialog;

    public KeyComboManager(Context context) {
        this.currentKeysDown = new HashSet();
        this.passedKeys = new HashSet();
        this.currentKeyComboCode = 0L;
        this.currentKeyComboTime = 0L;
        this.previousKeyComboCode = 0L;
        this.previousKeyComboTime = 0L;
        this.matchKeyCombo = true;
        this.serviceState = 0;
        this.hardwareKeyboardStatus = 0;
        this.onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 15);
        this.context = context;
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        if (!sharedPreferences.contains(this.context.getString(R.string.pref_select_keymap_key))) {
            sharedPreferences.edit().putString(this.context.getString(R.string.pref_select_keymap_key), this.context.getString(R.string.default_keymap_entry_value)).apply();
        }
        this.keyComboModel = createKeyComboModel();
    }

    private final void appendModifier(int i, int i2, String str, StringBuilder sb) {
        if ((i & i2) != 0) {
            appendPlusSignIfNotEmpty$ar$ds(sb);
            sb.append(str);
        }
    }

    private final void appendModifiers(int i, StringBuilder sb) {
        appendModifier(i, 2, this.context.getString(R.string.keycombo_key_modifier_alt), sb);
        appendModifier(i, 1, this.context.getString(R.string.keycombo_key_modifier_shift), sb);
        appendModifier(i, 4096, this.context.getString(R.string.keycombo_key_modifier_ctrl), sb);
        appendModifier(i, 65536, this.context.getString(R.string.keycombo_key_modifier_meta), sb);
    }

    private static final void appendPlusSignIfNotEmpty$ar$ds(StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append(" + ");
        }
    }

    private final KeyComboModel createKeyComboModel() {
        String keymap = getKeymap();
        if (keymap.equals(this.context.getString(R.string.classic_keymap_entry_value))) {
            return new ClassicKeyComboModel(this.context);
        }
        if (keymap.equals(this.context.getString(R.string.default_keymap_entry_value))) {
            return new DefaultKeyComboModel(this.context);
        }
        return null;
    }

    private static int getConvertedKeyCode(KeyEvent keyEvent) {
        if ((keyEvent.getModifiers() & 65536) == 0) {
            return keyEvent.getKeyCode();
        }
        if (keyEvent.getKeyCode() == 3) {
            return 66;
        }
        if (keyEvent.getKeyCode() == 4) {
            return 67;
        }
        return keyEvent.getKeyCode();
    }

    public static long getKeyComboCode(int i, int i2) {
        return (i << 32) + i2;
    }

    public static int getModifier(long j) {
        return (int) (j >> 32);
    }

    private final void interrupt(TalkBackPhysicalKeyboardShortcut talkBackPhysicalKeyboardShortcut) {
        ProtoDataStoreFactory protoDataStoreFactory = this.keyComboMapper$ar$class_merging$ar$class_merging;
        if (protoDataStoreFactory != null && talkBackPhysicalKeyboardShortcut != TalkBackPhysicalKeyboardShortcut.NAVIGATE_NEXT_DEFAULT && talkBackPhysicalKeyboardShortcut != TalkBackPhysicalKeyboardShortcut.NAVIGATE_PREVIOUS_DEFAULT && talkBackPhysicalKeyboardShortcut != TalkBackPhysicalKeyboardShortcut.NAVIGATE_NEXT && talkBackPhysicalKeyboardShortcut != TalkBackPhysicalKeyboardShortcut.NAVIGATE_PREVIOUS && ((FullScreenReadActor) protoDataStoreFactory.ProtoDataStoreFactory$ar$logger$ar$class_merging$b9e9d160_0).isActive()) {
            ((FullScreenReadActor) protoDataStoreFactory.ProtoDataStoreFactory$ar$logger$ar$class_merging$b9e9d160_0).interrupt();
        }
    }

    public final void dismissUpdateModifierKeysDialog() {
        A11yAlertDialogWrapper a11yAlertDialogWrapper = this.updateModifierKeysDialog;
        if (a11yAlertDialogWrapper != null && a11yAlertDialogWrapper.isShowing()) {
            this.updateModifierKeysDialog.dismiss();
        }
    }

    public final String getKeyComboStringRepresentation(long j) {
        if (j == 0) {
            return this.context.getString(R.string.keycombo_unassigned);
        }
        int triggerModifier = this.keyComboModel.getTriggerModifier();
        int i = ~triggerModifier;
        StringBuilder sb = new StringBuilder();
        int modifier = getModifier(j);
        if ((triggerModifier & modifier) != 0) {
            appendModifiers(triggerModifier, sb);
        }
        int i2 = (int) j;
        appendModifiers(modifier & i, sb);
        if (i2 > 0 && !KeyEvent.isModifierKey(i2)) {
            appendPlusSignIfNotEmpty$ar$ds(sb);
            if (i2 != 67) {
                switch (i2) {
                    case 19:
                        sb.append(this.context.getString(R.string.keycombo_key_arrow_up));
                        break;
                    case 20:
                        sb.append(this.context.getString(R.string.keycombo_key_arrow_down));
                        break;
                    case 21:
                        sb.append(this.context.getString(R.string.keycombo_key_arrow_left));
                        break;
                    case 22:
                        sb.append(this.context.getString(R.string.keycombo_key_arrow_right));
                        break;
                    default:
                        String keyCodeToString = KeyEvent.keyCodeToString(i2);
                        if (keyCodeToString != null) {
                            if (keyCodeToString.startsWith("KEYCODE_")) {
                                keyCodeToString = keyCodeToString.substring(8);
                            }
                            sb.append(keyCodeToString.replace('_', ' '));
                            break;
                        }
                        break;
                }
            } else {
                sb.append(this.context.getString(R.string.keycombo_key_backspace));
            }
        }
        return sb.toString();
    }

    public final String getKeymap() {
        return SpannableUtils$IdentifierSpan.getSharedPreferences(this.context).getString(this.context.getString(R.string.pref_select_keymap_key), this.context.getString(R.string.default_keymap_entry_value));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:197:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0564  */
    /* JADX WARN: Type inference failed for: r3v25, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v10, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v12, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v127, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v129, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v14, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v147, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v149, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v151, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v153, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v16, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v163, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v165, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v18, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v20, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v22, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v24, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v26, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v28, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v30, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v32, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v38, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v40, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v42, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v44, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v46, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v48, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v50, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v52, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v63, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onKeyEvent(android.view.KeyEvent r25, com.google.android.accessibility.utils.Performance.EventId r26) {
        /*
            Method dump skipped, instructions count: 1688
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.keyboard.KeyComboManager.onKeyEvent(android.view.KeyEvent, com.google.android.accessibility.utils.Performance$EventId):boolean");
    }

    public final void onServiceStateChanged(int i) {
        if (i == 0 && this.hardwareKeyboardStatus == 1) {
            TalkBackAnalytics talkBackAnalytics = this.analytics;
            KeyComboModel keyComboModel = this.keyComboModel;
            TalkBackAnalyticsImpl talkBackAnalyticsImpl = (TalkBackAnalyticsImpl) talkBackAnalytics;
            if (talkBackAnalyticsImpl.initialized) {
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = talkBackAnalyticsImpl.talkBackAnalyticsLogger;
                if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                    new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, keyComboModel instanceof ClassicKeyComboModel ? 1 : 0, 6)).execute(new Void[0]);
                }
            }
            TalkBackAnalytics talkBackAnalytics2 = this.analytics;
            int triggerModifier = this.keyComboModel.getTriggerModifier();
            TalkBackAnalyticsImpl talkBackAnalyticsImpl2 = (TalkBackAnalyticsImpl) talkBackAnalytics2;
            if (talkBackAnalyticsImpl2.initialized) {
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut2 = talkBackAnalyticsImpl2.talkBackAnalyticsLogger;
                TalkBackSettingEnums$ModifierKey talkBackSettingEnums$ModifierKey = TalkBackSettingEnums$ModifierKey.MODIFIER_KEY_UNSPECIFIED;
                if (triggerModifier != 2) {
                    if (triggerModifier == 65536) {
                        talkBackSettingEnums$ModifierKey = TalkBackSettingEnums$ModifierKey.MODIFIER_KEY_SEARCH;
                    }
                } else {
                    talkBackSettingEnums$ModifierKey = TalkBackSettingEnums$ModifierKey.MODIFIER_KEY_ALT;
                }
                if (talkBackAnalyticsLoggerWithClearcut2.dbHelper != null) {
                    new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut2, talkBackSettingEnums$ModifierKey.value, 11)).execute(new Void[0]);
                }
            }
        }
        this.serviceState = i;
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public final boolean processWhenServiceSuspended() {
        return true;
    }

    public final void refreshKeyComboModel() {
        this.keyComboModel = createKeyComboModel();
    }

    public final void showOrHideUpdateModifierKeysDialog() {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        if (SpannableUtils$IdentifierSpan.isAtLeastU() && this.keyComboModel.getTriggerModifier() == 65536 && !FormFactorUtils.getInstance().isAndroidTv) {
            int i = 1;
            if (this.hardwareKeyboardStatus != 1) {
                dismissUpdateModifierKeysDialog();
                return;
            }
            if (!this.sharedPreferences.getBoolean(this.context.getString(R.string.keycombo_update_modifier_keys_dialog_do_not_show_again), false)) {
                A11yAlertDialogWrapper a11yAlertDialogWrapper = this.updateModifierKeysDialog;
                if (a11yAlertDialogWrapper == null || !a11yAlertDialogWrapper.isShowing()) {
                    View inflate = LayoutInflater.from(this.context).inflate(R.layout.do_not_show_again_checkbox_dialog, (ViewGroup) null);
                    ((CheckBox) inflate.findViewById(R.id.dont_show_again)).setOnCheckedChangeListener(new SwitchPreference.Listener(this, 3));
                    ((TextView) inflate.findViewById(R.id.dialog_content)).setText(this.context.getString(R.string.keycombo_warning_dialog_message));
                    createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this.context, 2);
                    createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setView$ar$class_merging$ar$ds(inflate);
                    createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.keycombo_update_modifier_key_warning_title);
                    createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.keycombo_go_to_settings, new PermissionRequestActivity$$ExternalSyntheticLambda0(this, i));
                    createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(R.string.keycombo_update_modifier_keys_warning_negative_button, null);
                    A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
                    this.updateModifierKeysDialog = create;
                    SpannableUtils$IdentifierSpan.setWindowTypeToDialog(create.getWindow());
                    this.updateModifierKeysDialog.show();
                    ((TextView) this.updateModifierKeysDialog.getDialog().findViewById(R.id.alertTitle)).setHyphenationFrequency(1);
                }
            }
        }
    }

    public final void updateKeymapChangesNotificationVisibility() {
        KeyComboModel keyComboModel;
        if (this.hardwareKeyboardStatus == 1 && (keyComboModel = this.keyComboModel) != null && (keyComboModel instanceof ClassicKeyComboModel) && !FormFactorUtils.getInstance().isAndroidTv) {
            NotificationManager notificationManager = this.notificationManager;
            if (this.keymapNotification == null) {
                NotificationCompat$Builder createDefaultNotificationBuilder = SpannableUtils$IdentifierSpan.createDefaultNotificationBuilder(this.context);
                Intent intent = new Intent(this.context, (Class<?>) TalkBackKeymapChangesActivity.class);
                intent.addFlags(268435456);
                intent.addFlags(67108864);
                PendingIntent activity = PendingIntent.getActivity(this.context, 0, intent, 201326592);
                createDefaultNotificationBuilder.setTicker$ar$ds(this.context.getString(R.string.keycombo_keymap_changes_instruction_title));
                createDefaultNotificationBuilder.setContentTitle$ar$ds(this.context.getString(R.string.keycombo_keymap_changes_instruction_title));
                NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
                notificationCompat$BigTextStyle.bigText$ar$ds(this.context.getString(R.string.keycombo_keymap_changes_notifications_content));
                createDefaultNotificationBuilder.setStyle$ar$ds(notificationCompat$BigTextStyle);
                createDefaultNotificationBuilder.addAction$ar$ds(0, this.context.getString(R.string.keycombo_keymap_changes_notifications_action), activity);
                createDefaultNotificationBuilder.mContentIntent = activity;
                createDefaultNotificationBuilder.setAutoCancel$ar$ds(true);
                createDefaultNotificationBuilder.setOngoing$ar$ds(false);
                createDefaultNotificationBuilder.setWhen$ar$ds(0L);
                this.keymapNotification = createDefaultNotificationBuilder.build();
            }
            notificationManager.notify(6, this.keymapNotification);
            return;
        }
        this.notificationManager.cancel(6);
    }

    public static long getKeyComboCode(KeyEvent keyEvent) {
        if (keyEvent == null) {
            return 0L;
        }
        return getKeyComboCode(keyEvent.getModifiers() & 69635, getConvertedKeyCode(keyEvent));
    }

    public KeyComboManager(Context context, Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, SelectorController selectorController, ListMenuManager listMenuManager, FullScreenReadActor fullScreenReadActor, TalkBackAnalytics talkBackAnalytics, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this(context);
        this.notificationManager = (NotificationManager) context.getSystemService("notification");
        this.analytics = talkBackAnalytics;
        this.hardwareKeyboardStatus = context.getResources().getConfiguration().hardKeyboardHidden;
        this.keyComboMapper$ar$class_merging$ar$class_merging = new ProtoDataStoreFactory(context, feedbackReturner, actorState, selectorController, listMenuManager, fullScreenReadActor, hapticPatternParser$$ExternalSyntheticLambda1);
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.sharedPreferences = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        updateKeymapChangesNotificationVisibility();
        showOrHideUpdateModifierKeysDialog();
    }
}

package com.google.android.accessibility.talkback.preference.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.preference.Preference;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ServiceKeyEventListener;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class KeyboardShortcutDialogPreference extends TwoTargetIconPreference implements DialogInterface.OnKeyListener, ServiceKeyEventListener {
    private static final int KEY_EVENT_SOURCE_ACCESSIBILITY_SERVICE = 1;
    private static final int KEY_EVENT_SOURCE_ACTIVITY = 0;
    private TextView instructionText;
    private A11yAlertDialogWrapper keyAlreadyInUseDialog;
    private TextView keyAssignmentView;
    private KeyComboManager keyComboManager;
    private int keyEventSource;
    private AlertDialog setUpKeyComboDialog;
    private int temporaryKeyCode;
    private int temporaryModifier;

    public KeyboardShortcutDialogPreference(Context context) {
        super(context);
        this.keyEventSource = 0;
        init();
    }

    private void clearTemporaryKeyComboCode() {
        this.temporaryModifier = 0;
        this.temporaryKeyCode = 0;
    }

    static KeyComboManager getKeyComboManager(Context context) {
        if (TalkBackService.instance != null) {
            return TalkBackService.instance.keyComboManager;
        }
        return new KeyComboManager(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getKeyEventSourceForCurrentKeyComboModel() {
        if (this.keyComboManager.keyComboModel.getTriggerModifier() == 0) {
            return 0;
        }
        return 1;
    }

    private View getSetUpKeyComboDialogView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.keyboard_shortcut_dialog, (ViewGroup) null);
        String key = getKey();
        updateKeyComboManager();
        setTemporaryKeyComboCodeWithoutTriggerModifier(this.keyComboManager.keyComboModel.getKeyComboCodeForKey(key));
        this.keyAssignmentView = (TextView) inflate.findViewById(R.id.assigned_combination);
        TextView textView = (TextView) inflate.findViewById(R.id.instruction);
        this.instructionText = textView;
        textView.setText(this.keyComboManager.keyComboModel.getDescriptionOfEligibleKeyCombo());
        this.keyAssignmentView.setText(this.keyComboManager.getKeyComboStringRepresentation(getTemporaryKeyComboCodeWithTriggerModifier()));
        inflate.findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.KeyboardShortcutDialogPreference$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KeyboardShortcutDialogPreference.this.m150x4dbad321(view);
            }
        });
        this.keyComboManager.matchKeyCombo = false;
        return inflate;
    }

    private long getTemporaryKeyComboCodeWithTriggerModifier() {
        return KeyComboManager.getKeyComboCode(this.temporaryModifier, this.temporaryKeyCode);
    }

    private long getTemporaryKeyComboCodeWithoutTriggerModifier() {
        if (getTemporaryKeyComboCodeWithTriggerModifier() == 0) {
            return 0L;
        }
        int triggerModifier = this.keyComboManager.keyComboModel.getTriggerModifier();
        if (triggerModifier != 0 && (this.temporaryModifier & triggerModifier) == 0) {
            return -1L;
        }
        return KeyComboManager.getKeyComboCode((~triggerModifier) & this.temporaryModifier, this.temporaryKeyCode);
    }

    private void init() {
        setPersistent(true);
        updateKeyComboManager();
        setTemporaryKeyComboCodeWithoutTriggerModifier(this.keyComboManager.keyComboModel.getKeyComboCodeForKey(getKey()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showOverrideKeyComboDialog$0(DialogInterface.OnClickListener onClickListener, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        onClickListener.onClick(dialogInterface, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListener(String str, Object obj) {
        Preference.OnPreferenceChangeListener onPreferenceChangeListener;
        Preference findPreference = getPreferenceManager().findPreference(str);
        if (findPreference != null && (findPreference instanceof KeyboardShortcutDialogPreference) && (onPreferenceChangeListener = findPreference.getOnPreferenceChangeListener()) != null) {
            onPreferenceChangeListener.onPreferenceChange(findPreference, obj);
        }
    }

    private boolean onKeyEventInternal(KeyEvent keyEvent) {
        if (!processKeyEvent(keyEvent)) {
            return false;
        }
        if (keyEvent.getKeyCode() == 67 && keyEvent.hasNoModifiers()) {
            clearTemporaryKeyComboCode();
        } else {
            setTemporaryKeyComboCodeWithTriggerModifier(KeyComboManager.getKeyComboCode(keyEvent));
        }
        updateKeyAssignmentText();
        return true;
    }

    private void onSetUpKeyComboDialogClosed() {
        setTemporaryKeyComboCodeWithoutTriggerModifier(this.keyComboManager.keyComboModel.getKeyComboCodeForKey(getKey()));
        this.keyComboManager.matchKeyCombo = true;
        setKeyEventSource(0);
    }

    private boolean processKeyEvent(KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getRepeatCount() <= 1 && keyEvent.getKeyCode() != 4 && keyEvent.getKeyCode() != 3 && keyEvent.getKeyCode() != 5 && keyEvent.getKeyCode() != 6) {
            if (keyEvent.hasNoModifiers() && keyEvent.getKeyCode() == 66 && this.keyAlreadyInUseDialog == null) {
                processOKButtonClickListener();
                return false;
            }
            if (((keyEvent.getKeyCode() != 66 && keyEvent.getKeyCode() != 111) || !keyEvent.hasNoModifiers()) && keyEvent.getAction() == 0) {
                return true;
            }
        }
        return false;
    }

    private void processOKButtonClickListener() {
        long temporaryKeyComboCodeWithoutTriggerModifier = getTemporaryKeyComboCodeWithoutTriggerModifier();
        if (temporaryKeyComboCodeWithoutTriggerModifier != -1 && this.keyComboManager.keyComboModel.isEligibleKeyComboCode(temporaryKeyComboCodeWithoutTriggerModifier)) {
            String keyForKeyComboCode = this.keyComboManager.keyComboModel.getKeyForKeyComboCode(getTemporaryKeyComboCodeWithoutTriggerModifier());
            if (keyForKeyComboCode == null) {
                saveKeyCode();
                notifyChanged();
            } else if (!keyForKeyComboCode.equals(getKey())) {
                showOverrideKeyComboDialog(keyForKeyComboCode);
                return;
            }
            AlertDialog alertDialog = this.setUpKeyComboDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
                return;
            }
            return;
        }
        this.instructionText.setTextColor(-65536);
        PreferencesActivityUtils.announceText(this.instructionText.getText().toString(), getContext());
    }

    private void registerDialogKeyEvent(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        dialog.setOnKeyListener(this);
        setKeyEventSource(getKeyEventSourceForCurrentKeyComboModel());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveKeyCode() {
        this.keyComboManager.keyComboModel.saveKeyComboCode(getKey(), getTemporaryKeyComboCodeWithoutTriggerModifier());
        notifyListener(getKey(), Long.valueOf(getTemporaryKeyComboCodeWithoutTriggerModifier()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setKeyEventSource(int i) {
        if (this.keyEventSource == i) {
            return;
        }
        this.keyEventSource = i;
        if (i == 1) {
            this.keyComboManager.keyEventDelegate = this;
        } else {
            this.keyComboManager.keyEventDelegate = null;
        }
    }

    private void setTemporaryKeyComboCodeWithTriggerModifier(long j) {
        this.temporaryModifier = KeyComboManager.getModifier(j);
        this.temporaryKeyCode = (int) j;
    }

    private void setTemporaryKeyComboCodeWithoutTriggerModifier(long j) {
        this.temporaryModifier = KeyComboManager.getModifier(j);
        this.temporaryKeyCode = (int) j;
        int triggerModifier = this.keyComboManager.keyComboModel.getTriggerModifier();
        if (j != 0 && triggerModifier != 0) {
            this.temporaryModifier |= triggerModifier;
        }
    }

    private void showOverrideKeyComboDialog(CharSequence charSequence, CharSequence charSequence2, final DialogInterface.OnClickListener onClickListener) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        String string = getContext().getString(R.string.override_keycombo_message_two_params, charSequence, charSequence2);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(getContext(), 1);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.override_keycombo);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$51f49cd0_0$ar$ds(string);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.KeyboardShortcutDialogPreference$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                KeyboardShortcutDialogPreference.lambda$showOverrideKeyComboDialog$0(onClickListener, dialogInterface, i);
            }
        });
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.KeyboardShortcutDialogPreference$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                KeyboardShortcutDialogPreference.this.m151xfd16cda9(onClickListener, dialogInterface, i);
            }
        });
        A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
        this.keyAlreadyInUseDialog = create;
        create.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.google.android.accessibility.talkback.preference.base.KeyboardShortcutDialogPreference$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                KeyboardShortcutDialogPreference.this.m152xfc3d5d08(dialogInterface);
            }
        });
        this.keyAlreadyInUseDialog.show();
    }

    private void showSetUpKeyComboDialog() {
        AlertDialog create = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(getContext()).setView(getSetUpKeyComboDialogView()).create();
        this.setUpKeyComboDialog = create;
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.google.android.accessibility.talkback.preference.base.KeyboardShortcutDialogPreference$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                KeyboardShortcutDialogPreference.this.m154x3d359a49(dialogInterface);
            }
        });
        this.setUpKeyComboDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.google.android.accessibility.talkback.preference.base.KeyboardShortcutDialogPreference$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                KeyboardShortcutDialogPreference.this.m155x3c5c29a8(dialogInterface);
            }
        });
        this.setUpKeyComboDialog.show();
    }

    private void updateKeyAssignmentText() {
        this.keyAssignmentView.setText(getSummary());
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        return this.keyComboManager.getKeyComboStringRepresentation(getTemporaryKeyComboCodeWithTriggerModifier());
    }

    /* renamed from: lambda$getSetUpKeyComboDialogView$6$com-google-android-accessibility-talkback-preference-base-KeyboardShortcutDialogPreference, reason: not valid java name */
    public /* synthetic */ void m150x4dbad321(View view) {
        this.instructionText.setTextColor(-16777216);
        clearTemporaryKeyComboCode();
        updateKeyAssignmentText();
    }

    /* renamed from: lambda$showOverrideKeyComboDialog$1$com-google-android-accessibility-talkback-preference-base-KeyboardShortcutDialogPreference, reason: not valid java name */
    public /* synthetic */ void m151xfd16cda9(DialogInterface.OnClickListener onClickListener, DialogInterface dialogInterface, int i) {
        saveKeyCode();
        onClickListener.onClick(dialogInterface, i);
        AlertDialog alertDialog = this.setUpKeyComboDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.setUpKeyComboDialog.dismiss();
        }
    }

    /* renamed from: lambda$showOverrideKeyComboDialog$2$com-google-android-accessibility-talkback-preference-base-KeyboardShortcutDialogPreference, reason: not valid java name */
    public /* synthetic */ void m152xfc3d5d08(DialogInterface dialogInterface) {
        this.keyAlreadyInUseDialog = null;
    }

    /* renamed from: lambda$showSetUpKeyComboDialog$3$com-google-android-accessibility-talkback-preference-base-KeyboardShortcutDialogPreference, reason: not valid java name */
    public /* synthetic */ void m153x3e0f0aea(View view) {
        processOKButtonClickListener();
    }

    /* renamed from: lambda$showSetUpKeyComboDialog$4$com-google-android-accessibility-talkback-preference-base-KeyboardShortcutDialogPreference, reason: not valid java name */
    public /* synthetic */ void m154x3d359a49(DialogInterface dialogInterface) {
        Button button = this.setUpKeyComboDialog.getButton(-1);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.KeyboardShortcutDialogPreference$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    KeyboardShortcutDialogPreference.this.m153x3e0f0aea(view);
                }
            });
            button.setFocusableInTouchMode(true);
            button.requestFocus();
        }
        registerDialogKeyEvent(this.setUpKeyComboDialog);
    }

    /* renamed from: lambda$showSetUpKeyComboDialog$5$com-google-android-accessibility-talkback-preference-base-KeyboardShortcutDialogPreference, reason: not valid java name */
    public /* synthetic */ void m155x3c5c29a8(DialogInterface dialogInterface) {
        onSetUpKeyComboDialogClosed();
    }

    @Override // androidx.preference.Preference
    public void notifyChanged() {
        super.notifyChanged();
    }

    @Override // androidx.preference.Preference
    protected void onClick() {
        showSetUpKeyComboDialog();
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (this.keyEventSource != 0) {
            return false;
        }
        return onKeyEventInternal(keyEvent);
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public boolean onKeyEvent(KeyEvent keyEvent, Performance.EventId eventId) {
        if (this.keyEventSource != 1) {
            return false;
        }
        return onKeyEventInternal(keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onPrepareForRemoval() {
        super.onPrepareForRemoval();
    }

    public void onTriggerModifierChanged() {
        setTemporaryKeyComboCodeWithoutTriggerModifier(this.keyComboManager.keyComboModel.getKeyComboCodeForKey(getKey()));
        setSummary(getSummary());
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public boolean processWhenServiceSuspended() {
        return true;
    }

    public void setKeyComboCode(long j) {
        setTemporaryKeyComboCodeWithoutTriggerModifier(j);
    }

    public void updateKeyComboManager() {
        KeyComboManager keyComboManager = getKeyComboManager(getContext());
        this.keyComboManager = keyComboManager;
        if (keyComboManager != null) {
        } else {
            throw new IllegalStateException("KeyboardShortcutDialogPreference should never appear on systems where KeyComboManager is unavailable");
        }
    }

    public KeyboardShortcutDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.keyEventSource = 0;
        init();
    }

    public KeyboardShortcutDialogPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.keyEventSource = 0;
        init();
    }

    public KeyboardShortcutDialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.keyEventSource = 0;
        init();
    }

    private void showOverrideKeyComboDialog(final String str) {
        Preference findPreference;
        Preference findPreference2 = getPreferenceManager().findPreference(str);
        if (findPreference2 == null || (findPreference = getPreferenceManager().findPreference(getKey())) == null) {
            return;
        }
        CharSequence title = findPreference2.getTitle();
        CharSequence title2 = findPreference.getTitle();
        setKeyEventSource(0);
        showOverrideKeyComboDialog(title, title2, new DialogInterface.OnClickListener(this) { // from class: com.google.android.accessibility.talkback.preference.base.KeyboardShortcutDialogPreference.1
            final /* synthetic */ KeyboardShortcutDialogPreference this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    this.this$0.saveKeyCode();
                    this.this$0.keyComboManager.keyComboModel.clearKeyComboCode(str);
                    KeyboardShortcutDialogPreference keyboardShortcutDialogPreference = this.this$0;
                    keyboardShortcutDialogPreference.notifyListener(str, Long.valueOf(keyboardShortcutDialogPreference.keyComboManager.keyComboModel.getKeyComboCodeForKey(str)));
                    if (this.this$0.keyAlreadyInUseDialog != null) {
                        this.this$0.keyAlreadyInUseDialog.dismiss();
                        return;
                    }
                    return;
                }
                KeyboardShortcutDialogPreference keyboardShortcutDialogPreference2 = this.this$0;
                keyboardShortcutDialogPreference2.setKeyEventSource(keyboardShortcutDialogPreference2.getKeyEventSourceForCurrentKeyComboModel());
            }
        });
    }
}

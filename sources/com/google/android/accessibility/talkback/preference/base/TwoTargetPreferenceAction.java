package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TwoTargetPreferenceAction {
    private final Context context;
    private final Preference preference;

    public TwoTargetPreferenceAction(Context context, Preference preference) {
        this.context = context;
        this.preference = preference;
        preference.setLayoutResource(R.layout.preference_two_target);
        preference.setWidgetLayoutResource(R.layout.keyboard_shortcut_settings_preference_icon);
    }

    private static KeyComboManager getKeyComboManager(Context context) {
        if (TalkBackService.instance != null) {
            return TalkBackService.instance.keyComboManager;
        }
        return new KeyComboManager(context);
    }

    private boolean shouldHideSecondTarget() {
        int triggerModifier = getKeyComboManager(this.context).keyComboModel.getTriggerModifier();
        Long l = (Long) getKeyComboManager(this.context).keyComboModel.getKeyComboCodeMap().get(this.preference.getKey());
        if (l == null) {
            if (triggerModifier != 65536) {
                return true;
            }
            return false;
        }
        int modifier = KeyComboManager.getModifier(l.longValue()) & 65536;
        if (triggerModifier != 65536 && modifier != 65536) {
            return true;
        }
        return false;
    }

    private void showResetKeyboardShortcutDialog() {
        SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(this.context).setTitle(R.string.keycombo_warning_dialog_title).setMessage(R.string.keycombo_warning_dialog_message).setPositiveButton(R.string.keycombo_warning_dialog_close_button, (DialogInterface.OnClickListener) null).create().show();
    }

    /* renamed from: lambda$onBindViewHolder$1$com-google-android-accessibility-talkback-preference-base-TwoTargetPreferenceAction, reason: not valid java name */
    public /* synthetic */ void m180xd28f5a62(View view) {
        showResetKeyboardShortcutDialog();
    }

    public void onBindViewHolder(final PreferenceViewHolder preferenceViewHolder) {
        int i;
        View findViewById = preferenceViewHolder.findViewById(R.id.divider);
        View findViewById2 = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        boolean shouldHideSecondTarget = shouldHideSecondTarget();
        int i2 = 0;
        if (findViewById != null) {
            if (true != shouldHideSecondTarget) {
                i = 0;
            } else {
                i = 8;
            }
            findViewById.setVisibility(i);
        }
        if (findViewById2 != null) {
            if (true == shouldHideSecondTarget) {
                i2 = 8;
            }
            findViewById2.setVisibility(i2);
        }
        LinearLayout linearLayout = (LinearLayout) preferenceViewHolder.itemView.findViewById(R.id.main_frame);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TwoTargetPreferenceAction$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PreferenceViewHolder.this.itemView.performClick();
                }
            });
        }
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TwoTargetPreferenceAction$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TwoTargetPreferenceAction.this.m180xd28f5a62(view);
            }
        });
    }
}

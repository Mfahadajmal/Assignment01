package com.google.android.accessibility.talkback.preference.base;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.preference.PreferenceViewHolder;
import io.grpc.okhttp.OutboundFlowController;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SpeechRatePreference extends AccessibilitySeekBarPreference {
    private SeekBar seekBar;

    public SpeechRatePreference(Context context) {
        super(context);
        this.seekBar = null;
    }

    private void setSeekBarValue(int i) {
        if (this.seekBar == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE", ((int) (OutboundFlowController.forceRateToDefaultWhenClose(i / 100.0f) * 100.0f)) - getMin());
        this.seekBar.performAccessibilityAction(R.id.accessibilityActionSetProgress, bundle);
    }

    public SeekBar getSeekBar() {
        return this.seekBar;
    }

    /* renamed from: lambda$onBindViewHolder$0$com-google-android-accessibility-talkback-preference-base-SpeechRatePreference, reason: not valid java name */
    public /* synthetic */ boolean m160xf349c2aa(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        setSeekBarValue((int) Math.max(getValue() / 1.1f, 10.0f));
        return true;
    }

    /* renamed from: lambda$onBindViewHolder$1$com-google-android-accessibility-talkback-preference-base-SpeechRatePreference, reason: not valid java name */
    public /* synthetic */ boolean m161xd13d2889(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        setSeekBarValue((int) Math.min(getValue() * 1.1f, 600.0f));
        return true;
    }

    @Override // com.google.android.accessibility.talkback.preference.base.AccessibilitySeekBarPreference, androidx.preference.SeekBarPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        SeekBar seekBar = (SeekBar) preferenceViewHolder.findViewById(com.google.android.marvin.talkback.R.id.seekbar);
        this.seekBar = seekBar;
        if (seekBar == null) {
            return;
        }
        ViewCompat.setStateDescription(seekBar, getContext().getString(com.google.android.marvin.talkback.R.string.template_percent, String.valueOf(getValue())));
        ViewCompat.addAccessibilityAction(this.seekBar, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, r0.mId, null, new AccessibilityViewCommand() { // from class: com.google.android.accessibility.talkback.preference.base.SpeechRatePreference$$ExternalSyntheticLambda0
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                return SpeechRatePreference.this.m160xf349c2aa(view, commandArguments);
            }
        }, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.mViewCommandArgumentClass));
        ViewCompat.addAccessibilityAction(this.seekBar, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, r0.mId, null, new AccessibilityViewCommand() { // from class: com.google.android.accessibility.talkback.preference.base.SpeechRatePreference$$ExternalSyntheticLambda1
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                return SpeechRatePreference.this.m161xd13d2889(view, commandArguments);
            }
        }, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.mViewCommandArgumentClass));
    }

    public SpeechRatePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.seekBar = null;
    }
}

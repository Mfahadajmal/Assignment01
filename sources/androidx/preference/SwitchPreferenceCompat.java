package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import androidx.core.app.NotificationCompatBuilder$Api20Impl;
import androidx.preference.SwitchPreference;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SwitchPreferenceCompat extends TwoStatePreference {
    private final SwitchPreference.Listener mListener$ar$class_merging$e40dea77_0;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.switchPreferenceCompatStyle, 0);
        this.mListener$ar$class_merging$e40dea77_0 = new SwitchPreference.Listener((TwoStatePreference) this, 2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreferenceCompat, R.attr.switchPreferenceCompatStyle, 0);
        setSummaryOn(NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 7, 0));
        setSummaryOff(NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 6, 1));
        this.mSwitchOn = NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 9, 3);
        notifyChanged();
        this.mSwitchOff = NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 8, 4);
        notifyChanged();
        setDisableDependentsState(NotificationCompatBuilder$Api20Impl.getBoolean(obtainStyledAttributes, 5, 2, false));
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void syncSwitchView(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOnInternal(this.mSwitchOn);
            switchCompat.requestLayout();
            if (switchCompat.isChecked()) {
                switchCompat.setOnStateDescriptionOnRAndAbove();
            }
            switchCompat.setTextOffInternal(this.mSwitchOff);
            switchCompat.requestLayout();
            if (!switchCompat.isChecked()) {
                switchCompat.setOffStateDescriptionOnRAndAbove();
            }
            switchCompat.setOnCheckedChangeListener(this.mListener$ar$class_merging$e40dea77_0);
        }
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        syncSwitchView(preferenceViewHolder.findViewById(R.id.switchWidget));
        syncSummaryView(preferenceViewHolder);
    }

    @Override // androidx.preference.Preference
    public final void performClick(View view) {
        super.performClick(view);
        if (!((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            return;
        }
        syncSwitchView(view.findViewById(R.id.switchWidget));
        syncSummaryView(view.findViewById(android.R.id.summary));
    }
}

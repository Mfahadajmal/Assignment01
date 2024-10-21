package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.core.app.NotificationCompatBuilder$Api20Impl;
import androidx.preference.SwitchPreference;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CheckBoxPreference extends TwoStatePreference {
    private final SwitchPreference.Listener mListener$ar$class_merging$4b09e526_0;

    public CheckBoxPreference(Context context) {
        this(context, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void syncCheckboxView(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.mListener$ar$class_merging$4b09e526_0);
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        syncCheckboxView(preferenceViewHolder.findViewById(R.id.checkbox));
        syncSummaryView(preferenceViewHolder);
    }

    @Override // androidx.preference.Preference
    public final void performClick(View view) {
        super.performClick(view);
        if (!((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            return;
        }
        syncCheckboxView(view.findViewById(R.id.checkbox));
        syncSummaryView(view.findViewById(R.id.summary));
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, NotificationCompatBuilder$Api20Impl.getAttr(context, com.google.android.marvin.talkback.R.attr.checkBoxPreferenceStyle, R.attr.checkBoxPreferenceStyle));
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mListener$ar$class_merging$4b09e526_0 = new SwitchPreference.Listener((TwoStatePreference) this, 1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CheckBoxPreference, i, i2);
        setSummaryOn(NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 5, 0));
        setSummaryOff(NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 4, 1));
        setDisableDependentsState(NotificationCompatBuilder$Api20Impl.getBoolean(obtainStyledAttributes, 3, 2, false));
        obtainStyledAttributes.recycle();
    }
}

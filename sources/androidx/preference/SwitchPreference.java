package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.core.app.NotificationCompatBuilder$Api20Impl;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.marvin.talkback.R;
import com.google.android.material.chip.Chip;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {
    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Listener implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ Object SwitchPreference$Listener$ar$this$0;
        private final /* synthetic */ int switching_field;

        public Listener(TwoStatePreference twoStatePreference, int i) {
            this.switching_field = i;
            this.SwitchPreference$Listener$ar$this$0 = twoStatePreference;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [com.google.android.material.internal.MaterialCheckable, java.lang.Object] */
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            ?? r0 = this.SwitchPreference$Listener$ar$this$0;
                            Chip chip = (Chip) r0;
                            RetryingNameResolver.ResolutionResultListener resolutionResultListener = chip.onCheckedChangeListenerInternal$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                            if (resolutionResultListener != 0) {
                                resolutionResultListener.onCheckedChanged(r0, z);
                            }
                            CompoundButton.OnCheckedChangeListener onCheckedChangeListener = chip.onCheckedChangeListener;
                            if (onCheckedChangeListener != null) {
                                onCheckedChangeListener.onCheckedChanged(compoundButton, z);
                                return;
                            }
                            return;
                        }
                        KeyComboManager keyComboManager = (KeyComboManager) this.SwitchPreference$Listener$ar$this$0;
                        keyComboManager.sharedPreferences.edit().putBoolean(keyComboManager.context.getString(R.string.keycombo_update_modifier_keys_dialog_do_not_show_again), z).apply();
                        return;
                    }
                    if (!((Preference) this.SwitchPreference$Listener$ar$this$0).callChangeListener(Boolean.valueOf(z))) {
                        compoundButton.setChecked(!z);
                        return;
                    } else {
                        ((TwoStatePreference) this.SwitchPreference$Listener$ar$this$0).setChecked(z);
                        return;
                    }
                }
                if (!((Preference) this.SwitchPreference$Listener$ar$this$0).callChangeListener(Boolean.valueOf(z))) {
                    compoundButton.setChecked(!z);
                    return;
                } else {
                    ((TwoStatePreference) this.SwitchPreference$Listener$ar$this$0).setChecked(z);
                    return;
                }
            }
            if (!((Preference) this.SwitchPreference$Listener$ar$this$0).callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                ((TwoStatePreference) this.SwitchPreference$Listener$ar$this$0).setChecked(z);
            }
        }

        public /* synthetic */ Listener(Object obj, int i) {
            this.switching_field = i;
            this.SwitchPreference$Listener$ar$this$0 = obj;
        }
    }

    public SwitchPreference(Context context) {
        this(context, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void syncSwitchView(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            Switch r4 = (Switch) view;
            r4.setTextOn(this.mSwitchOn);
            r4.setTextOff(this.mSwitchOff);
            r4.setOnCheckedChangeListener(this.mListener);
        }
    }

    private void syncViewIfAccessibilityEnabled(View view) {
        if (!((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            return;
        }
        syncSwitchView(view.findViewById(android.R.id.switch_widget));
        syncSummaryView(view.findViewById(android.R.id.summary));
    }

    public CharSequence getSwitchTextOff() {
        return this.mSwitchOff;
    }

    public CharSequence getSwitchTextOn() {
        return this.mSwitchOn;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        syncSwitchView(preferenceViewHolder.findViewById(android.R.id.switch_widget));
        syncSummaryView(preferenceViewHolder);
    }

    @Override // androidx.preference.Preference
    public void performClick(View view) {
        super.performClick(view);
        syncViewIfAccessibilityEnabled(view);
    }

    public void setSwitchTextOff(int i) {
        setSwitchTextOff(getContext().getString(i));
    }

    public void setSwitchTextOn(int i) {
        setSwitchTextOn(getContext().getString(i));
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, NotificationCompatBuilder$Api20Impl.getAttr(context, R.attr.switchPreferenceStyle, android.R.attr.switchPreferenceStyle));
    }

    public void setSwitchTextOff(CharSequence charSequence) {
        this.mSwitchOff = charSequence;
        notifyChanged();
    }

    public void setSwitchTextOn(CharSequence charSequence) {
        this.mSwitchOn = charSequence;
        notifyChanged();
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mListener = new Listener((TwoStatePreference) this, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreference, i, i2);
        setSummaryOn(NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 7, 0));
        setSummaryOff(NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 6, 1));
        setSwitchTextOn(NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 9, 3));
        setSwitchTextOff(NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 8, 4));
        setDisableDependentsState(NotificationCompatBuilder$Api20Impl.getBoolean(obtainStyledAttributes, 5, 2, false));
        obtainStyledAttributes.recycle();
    }
}

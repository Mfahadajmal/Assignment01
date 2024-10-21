package com.android.settingslib.widget;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.ActionBarContextView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.R$styleable;
import androidx.preference.TwoStatePreference;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MainSwitchPreference extends TwoStatePreference implements OnMainSwitchChangeListener {
    public MainSwitchBar mMainSwitchBar;
    public final List mSwitchChangeListeners;

    public MainSwitchPreference(Context context) {
        super(context);
        this.mSwitchChangeListeners = new ArrayList();
        init(context, null);
    }

    private final void init(Context context, AttributeSet attributeSet) {
        setLayoutResource(R.layout.settingslib_main_switch_layout);
        this.mSwitchChangeListeners.add(this);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, 0, 0);
            setTitle(obtainStyledAttributes.getText(4));
            setIconSpaceReserved(obtainStyledAttributes.getBoolean(15, true));
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.mDividerAllowedAbove = false;
        preferenceViewHolder.mDividerAllowedBelow = false;
        MainSwitchBar mainSwitchBar = (MainSwitchBar) preferenceViewHolder.findViewById(R.id.settingslib_main_switch_bar);
        this.mMainSwitchBar = mainSwitchBar;
        mainSwitchBar.setOnClickListener(new ActionBarContextView.AnonymousClass1(this, 4, null));
        setIconSpaceReserved(isIconSpaceReserved());
        setChecked(isChecked());
        MainSwitchBar mainSwitchBar2 = this.mMainSwitchBar;
        if (mainSwitchBar2 != null) {
            mainSwitchBar2.setTitle(getTitle());
            MainSwitchBar mainSwitchBar3 = this.mMainSwitchBar;
            mainSwitchBar3.setVisibility(0);
            mainSwitchBar3.mSwitch.setOnCheckedChangeListener(mainSwitchBar3);
        }
        Iterator it = this.mSwitchChangeListeners.iterator();
        while (it.hasNext()) {
            this.mMainSwitchBar.addOnSwitchChangeListener((OnMainSwitchChangeListener) it.next());
        }
    }

    @Override // com.android.settingslib.widget.OnMainSwitchChangeListener
    public final void onSwitchChanged$ar$ds(boolean z) {
        super.setChecked(z);
    }

    @Override // androidx.preference.TwoStatePreference
    public final void setChecked(boolean z) {
        super.setChecked(z);
        MainSwitchBar mainSwitchBar = this.mMainSwitchBar;
        if (mainSwitchBar != null && mainSwitchBar.isChecked() != z) {
            this.mMainSwitchBar.setChecked(z);
        }
    }

    @Override // androidx.preference.Preference
    public final void setIconSpaceReserved(boolean z) {
        super.setIconSpaceReserved(z);
        MainSwitchBar mainSwitchBar = this.mMainSwitchBar;
        if (mainSwitchBar != null && mainSwitchBar.mTextView != null && !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mainSwitchBar.mTextView.getLayoutParams();
            int dimensionPixelSize = mainSwitchBar.getContext().getResources().getDimensionPixelSize(R.dimen.settingslib_switchbar_subsettings_margin_start);
            if (true != z) {
                dimensionPixelSize = 0;
            }
            layoutParams.setMarginStart(dimensionPixelSize);
            mainSwitchBar.mTextView.setLayoutParams(layoutParams);
        }
    }

    @Override // androidx.preference.Preference
    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        MainSwitchBar mainSwitchBar = this.mMainSwitchBar;
        if (mainSwitchBar != null) {
            mainSwitchBar.setTitle(charSequence);
        }
    }

    public MainSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSwitchChangeListeners = new ArrayList();
        init(context, attributeSet);
    }

    public MainSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSwitchChangeListeners = new ArrayList();
        init(context, attributeSet);
    }

    public MainSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mSwitchChangeListeners = new ArrayList();
        init(context, attributeSet);
    }
}

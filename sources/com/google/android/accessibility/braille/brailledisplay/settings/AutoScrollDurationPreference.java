package com.google.android.accessibility.braille.brailledisplay.settings;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.marvin.talkback.R;
import com.google.android.material.slider.Slider;
import io.grpc.internal.RetryingNameResolver;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AutoScrollDurationPreference extends Preference {
    public Slider slider;

    public AutoScrollDurationPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.auto_scroll_slider);
    }

    public final float getCurrentDuration() {
        return BrailleUserPreferences.readAutoScrollDuration(getContext()) / 1000.0f;
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        NumberFormat numberInstance;
        String format;
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setClickable(false);
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.slider_value);
        Slider slider = (Slider) preferenceViewHolder.findViewById(R.id.slider);
        this.slider = slider;
        slider.setValueTo$ar$ds();
        this.slider.setValueFrom$ar$ds();
        this.slider.setStepSize$ar$ds();
        this.slider.setValue(getCurrentDuration());
        this.slider.addOnChangeListener$ar$class_merging$ar$class_merging$ar$class_merging(new GooglePlayServicesUpdatedReceiver.Callback(this, textView));
        this.slider.setLabelFormatter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(new RetryingNameResolver.ResolutionResultListener(this, null));
        Context context = getContext();
        numberInstance = NumberFormat.getNumberInstance(Locale.getDefault());
        format = numberInstance.format(this.slider.getValue());
        textView.setText(context.getString(R.string.bd_auto_scroll_duration_value_text, String.valueOf(format)));
    }
}

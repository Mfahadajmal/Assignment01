package com.google.android.accessibility.talkback.preference.base;

import android.R;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BulletSpan;
import android.text.style.LeadingMarginSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.widget.FooterPreference;
import com.google.common.base.Splitter;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityFooterPreference extends FooterPreference {
    private static final int GAP_WIDTH = 30;

    public AccessibilityFooterPreference(Context context) {
        this(context, null);
    }

    @Override // com.android.settingslib.widget.FooterPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.itemView.findViewById(R.id.title);
        String charSequence = getTitle().toString();
        if (textView != null && !TextUtils.isEmpty(charSequence)) {
            SpannableString spannableString = new SpannableString(charSequence);
            List splitToList = Splitter.on("\n\n").splitToList(charSequence);
            if (splitToList.size() == 2) {
                Iterable<String> split = Splitter.on('\n').split((CharSequence) splitToList.get(1));
                int length = ((String) splitToList.get(0)).length() + 2;
                spannableString.setSpan(new LeadingMarginSpan.Standard(30, 30), length, spannableString.length(), 33);
                for (String str : split) {
                    spannableString.setSpan(new BulletSpan(30), length, str.length() + length, 33);
                    length += str.length() + 1;
                }
                textView.setText(spannableString);
            }
        }
    }

    public AccessibilityFooterPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}

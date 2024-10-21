package com.android.settingslib.widget;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FooterPreference extends Preference {
    public static final String KEY_FOOTER = "footer_preference";
    static final int ORDER_FOOTER = 2147483646;
    private CharSequence mContentDescription;
    int mIconVisibility;
    private CharSequence mLearnMoreContentDescription;
    View.OnClickListener mLearnMoreListener;
    private FooterLearnMoreSpan mLearnMoreSpan;
    private CharSequence mLearnMoreText;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FooterLearnMoreSpan extends URLSpan {
        private final View.OnClickListener mClickListener;

        public FooterLearnMoreSpan(View.OnClickListener onClickListener) {
            super("");
            this.mClickListener = onClickListener;
        }

        @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
        public final void onClick(View view) {
            View.OnClickListener onClickListener = this.mClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public FooterPreference(Context context) {
        this(context, null);
    }

    private void init() {
        setLayoutResource(R.layout.preference_footer);
        if (getIcon() == null) {
            setIcon(R.drawable.settingslib_ic_info_outline_24);
        }
        setOrder(ORDER_FOOTER);
        if (TextUtils.isEmpty(getKey())) {
            setKey(KEY_FOOTER);
        }
        setSelectable(false);
    }

    CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    CharSequence getLearnMoreContentDescription() {
        return this.mLearnMoreContentDescription;
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        return getTitle();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.itemView.findViewById(android.R.id.title);
        if (textView != null && !TextUtils.isEmpty(this.mContentDescription)) {
            textView.setContentDescription(this.mContentDescription);
        }
        TextView textView2 = (TextView) preferenceViewHolder.itemView.findViewById(R.id.settingslib_learn_more);
        if (textView2 != null) {
            if (this.mLearnMoreListener != null) {
                textView2.setVisibility(0);
                if (TextUtils.isEmpty(this.mLearnMoreText)) {
                    this.mLearnMoreText = textView2.getText();
                } else {
                    textView2.setText(this.mLearnMoreText);
                }
                SpannableString spannableString = new SpannableString(this.mLearnMoreText);
                FooterLearnMoreSpan footerLearnMoreSpan = this.mLearnMoreSpan;
                if (footerLearnMoreSpan != null) {
                    spannableString.removeSpan(footerLearnMoreSpan);
                }
                FooterLearnMoreSpan footerLearnMoreSpan2 = new FooterLearnMoreSpan(this.mLearnMoreListener);
                this.mLearnMoreSpan = footerLearnMoreSpan2;
                spannableString.setSpan(footerLearnMoreSpan2, 0, spannableString.length(), 0);
                textView2.setText(spannableString);
                if (!TextUtils.isEmpty(this.mLearnMoreContentDescription)) {
                    textView2.setContentDescription(this.mLearnMoreContentDescription);
                }
            } else {
                textView2.setVisibility(8);
            }
        }
        View findViewById = preferenceViewHolder.itemView.findViewById(R.id.icon_frame);
        if (findViewById != null) {
            findViewById.setVisibility(this.mIconVisibility);
        }
    }

    public void setContentDescription(CharSequence charSequence) {
        if (!TextUtils.equals(this.mContentDescription, charSequence)) {
            this.mContentDescription = charSequence;
            notifyChanged();
        }
    }

    public void setIconVisibility(int i) {
        if (this.mIconVisibility == i) {
            return;
        }
        this.mIconVisibility = i;
        notifyChanged();
    }

    public void setLearnMoreAction(View.OnClickListener onClickListener) {
        if (this.mLearnMoreListener != onClickListener) {
            this.mLearnMoreListener = onClickListener;
            notifyChanged();
        }
    }

    public void setLearnMoreContentDescription(CharSequence charSequence) {
        if (!TextUtils.equals(this.mContentDescription, charSequence)) {
            this.mLearnMoreContentDescription = charSequence;
            notifyChanged();
        }
    }

    public void setLearnMoreText(CharSequence charSequence) {
        if (!TextUtils.equals(this.mLearnMoreText, charSequence)) {
            this.mLearnMoreText = charSequence;
            notifyChanged();
        }
    }

    @Override // androidx.preference.Preference
    public void setSummary(int i) {
        setTitle(i);
    }

    public FooterPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.footerPreferenceStyle);
        this.mIconVisibility = 0;
        init();
    }

    @Override // androidx.preference.Preference
    public void setSummary(CharSequence charSequence) {
        setTitle(charSequence);
    }
}

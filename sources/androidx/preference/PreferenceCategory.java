package androidx.preference;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.app.NotificationCompatBuilder$Api20Impl;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PreferenceCategory extends PreferenceGroup {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api28Impl {
        static void setAccessibilityHeading(View view, boolean z) {
            view.setAccessibilityHeading(z);
        }
    }

    public PreferenceCategory(Context context) {
        this(context, null);
    }

    @Override // androidx.preference.Preference
    public final boolean isEnabled() {
        return false;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setAccessibilityHeading(preferenceViewHolder.itemView, true);
        }
    }

    @Override // androidx.preference.Preference
    public final boolean shouldDisableDependents() {
        if (!super.isEnabled()) {
            return true;
        }
        return false;
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, NotificationCompatBuilder$Api20Impl.getAttr(context, R.attr.preferenceCategoryStyle, android.R.attr.preferenceCategoryStyle));
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}

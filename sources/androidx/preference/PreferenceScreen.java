package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.app.NotificationCompatBuilder$Api20Impl;
import androidx.preference.PreferenceManager;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PreferenceScreen extends PreferenceGroup {
    public final boolean mShouldUseGeneratedIds;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, NotificationCompatBuilder$Api20Impl.getAttr(context, R.attr.preferenceScreenStyle, android.R.attr.preferenceScreenStyle));
        this.mShouldUseGeneratedIds = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.PreferenceGroup
    public final boolean isOnSameScreenAsChildren() {
        return false;
    }

    @Override // androidx.preference.Preference
    protected final void onClick() {
        PreferenceManager.OnNavigateToScreenListener onNavigateToScreenListener;
        if (getIntent() == null && getFragment() == null && getPreferenceCount() != 0 && (onNavigateToScreenListener = getPreferenceManager().mOnNavigateToScreenListener) != null) {
            onNavigateToScreenListener.onNavigateToScreen(this);
        }
    }
}

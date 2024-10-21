package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class NodeMenuRule implements NodeMenu {
    private final int showPrefKeyResId;
    private final int showPrefKeyResIdDefault;

    public NodeMenuRule(int i, int i2) {
        this.showPrefKeyResId = i;
        this.showPrefKeyResIdDefault = i2;
    }

    public abstract CharSequence getUserFriendlyMenuName(Context context);

    public final boolean isEnabled(Context context) {
        return SpannableUtils$IdentifierSpan.getSharedPreferences(context).getBoolean(context.getString(this.showPrefKeyResId), context.getResources().getBoolean(this.showPrefKeyResIdDefault));
    }

    public boolean isSubMenu() {
        return true;
    }
}

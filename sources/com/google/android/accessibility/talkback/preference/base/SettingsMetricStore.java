package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsDBHelper;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SettingsMetricStore {
    private final TalkBackAnalyticsDBHelper dbHelper;

    public SettingsMetricStore(Context context) {
        this.dbHelper = new TalkBackAnalyticsDBHelper(context);
    }

    public void onGupPreferenceClicked() {
        this.dbHelper.cacheMiscellaneousEvent(19);
    }
}

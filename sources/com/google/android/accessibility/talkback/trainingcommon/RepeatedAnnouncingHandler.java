package com.google.android.accessibility.talkback.trainingcommon;

import android.content.Context;
import android.os.Handler;
import android.view.accessibility.AccessibilityManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RepeatedAnnouncingHandler {
    public final AccessibilityManager accessibilityManager;
    public final String announcement;
    public final Runnable idleAnnouncementTask = new TrainingActivity$$ExternalSyntheticLambda1(this, 1, null);
    public final int initialDelayMs = 120000;
    public final int repeatedDelayMs = 30000;
    public final Handler handler = new Handler();

    public RepeatedAnnouncingHandler(Context context, String str) {
        this.announcement = str;
        this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }
}

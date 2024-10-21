package com.google.android.gms.googlehelp.internal.common;

import android.app.Activity;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.googlehelp.Help;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleHelpClient extends GoogleApi {
    public static final GoogleHelpApiImpl googleHelpApi$ar$class_merging = new GoogleHelpApiImpl();
    public final Activity callingActivity;

    public GoogleHelpClient(Activity activity) {
        super(activity, activity, Help.API$ar$class_merging$ar$class_merging$ar$class_merging, null, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.callingActivity = activity;
    }
}

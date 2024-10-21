package com.google.android.accessibility.switchaccesslegacy.migration;

import android.net.Uri;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DatabaseUtils$Preference {
    public static final Uri CONTENT_ALL_URI = new Uri.Builder().scheme("content").authority("com.google.android.marvin.talkback.providers.MigrationProvider").path("preference_all").build();
    public static final ImmutableList COLUMNS = ImmutableList.of((Object) "key", (Object) "value", (Object) "type");
}

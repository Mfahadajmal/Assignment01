package com.google.android.gms.common.internal;

import android.net.Uri;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsIntents {
    private static final Uri PLUS_BASE_URI;

    static {
        Uri parse = Uri.parse("https://plus.google.com/");
        PLUS_BASE_URI = parse;
        parse.buildUpon().appendPath("circles").appendPath("find").build();
    }
}

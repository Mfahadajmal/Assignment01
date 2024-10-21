package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import android.content.SharedPreferences;
import j$.util.concurrent.ConcurrentHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeStickyAccount {
    static {
        new ConcurrentHashMap();
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("PhenotypeStickyAccount", 0);
    }
}

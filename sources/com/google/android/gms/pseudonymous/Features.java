package com.google.android.gms.pseudonymous;

import com.google.android.gms.common.Feature;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Features {
    public static final Feature[] ALL_FEATURES;
    public static final Feature GET_LAST_RESET_TIME_API;

    static {
        Feature feature = new Feature("get_last_reset_time_api", 1L);
        GET_LAST_RESET_TIME_API = feature;
        ALL_FEATURES = new Feature[]{feature};
    }
}

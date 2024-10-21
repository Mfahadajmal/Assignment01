package com.google.android.gms.usagereporting;

import com.google.android.gms.common.Feature;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Features {
    public static final Feature[] ALL_FEATURES;
    public static final Feature EL_CAPITAN;
    public static final Feature USAGE_AND_DIAGNOSTICS_CONSENTS;
    public static final Feature USAGE_AND_DIAGNOSTICS_LISTENER;
    public static final Feature USAGE_AND_DIAGNOSTICS_SETTINGS_ACCESS;

    static {
        Feature feature = new Feature("usage_and_diagnostics_listener", 1L);
        USAGE_AND_DIAGNOSTICS_LISTENER = feature;
        Feature feature2 = new Feature("usage_and_diagnostics_consents", 1L);
        USAGE_AND_DIAGNOSTICS_CONSENTS = feature2;
        Feature feature3 = new Feature("usage_and_diagnostics_settings_access", 1L);
        USAGE_AND_DIAGNOSTICS_SETTINGS_ACCESS = feature3;
        Feature feature4 = new Feature("el_capitan", 1L);
        EL_CAPITAN = feature4;
        ALL_FEATURES = new Feature[]{feature, feature2, feature3, feature4};
    }
}

package org.chromium.net;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApiVersion {
    private static final int API_LEVEL = 31;
    private static final String CRONET_VERSION = "127.0.6483.0";
    private static final String LAST_CHANGE = "6e44da7d421d69e201ef9d9583b336aade070b5e-refs/branch-heads/6483@{#1}";
    private static final int MIN_COMPATIBLE_API_LEVEL = 3;

    private ApiVersion() {
    }

    public static int getApiLevel() {
        return 3;
    }

    public static String getCronetVersion() {
        return CRONET_VERSION;
    }

    public static String getCronetVersionWithLastChange() {
        return "127.0.6483.0@".concat("6e44da7d");
    }

    public static String getLastChange() {
        return LAST_CHANGE;
    }

    public static int getMaximumAvailableApiLevel() {
        return 31;
    }
}

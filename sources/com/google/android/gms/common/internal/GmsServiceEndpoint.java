package com.google.android.gms.common.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsServiceEndpoint {
    public final String startAction;
    public final boolean useDynamicLookup;
    public final String packageName = "com.google.android.gms";
    public final int bindFlags = GmsClientSupervisor.defaultBindFlags;

    public GmsServiceEndpoint(String str, String str2, int i, boolean z) {
        this.startAction = str2;
        this.useDynamicLookup = z;
    }
}

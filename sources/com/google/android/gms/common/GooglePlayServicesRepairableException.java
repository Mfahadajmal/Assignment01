package com.google.android.gms.common;

import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GooglePlayServicesRepairableException extends UserRecoverableException {
    public final int connectionStatusCode;

    public GooglePlayServicesRepairableException(int i, String str, Intent intent) {
        super(str, intent);
        this.connectionStatusCode = i;
    }
}

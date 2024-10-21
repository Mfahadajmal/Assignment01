package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SafeParcelReader$ParseException extends RuntimeException {
    public SafeParcelReader$ParseException(String str, Parcel parcel) {
        super(str + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
    }
}

package com.google.android.gms.pseudonymous;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SessionZwiebackToken extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SessionZwiebackToken> CREATOR = new ServiceDumpCreator(1);
    private final String logSource;
    private final String token;

    public SessionZwiebackToken(Parcel parcel) {
        this.token = parcel.readString();
        String readString = parcel.readString();
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(readString);
        this.logSource = readString;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SessionZwiebackToken)) {
            return false;
        }
        SessionZwiebackToken sessionZwiebackToken = (SessionZwiebackToken) obj;
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.token, sessionZwiebackToken.token) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.logSource, sessionZwiebackToken.logSource)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.token, this.logSource});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.token);
        parcel.writeString(this.logSource);
    }
}

package com.google.android.gms.auth;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TokenData extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<TokenData> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(11);
    public final Long expirationTimeSecs;
    public final List grantedScopes;
    public final boolean isCached;
    public final boolean isSnowballed;
    final int mVersionCode;
    public final String scopeData;
    public final String token;

    public TokenData(int i, String str, Long l, boolean z, boolean z2, List list, String str2) {
        this.mVersionCode = i;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds(str);
        this.token = str;
        this.expirationTimeSecs = l;
        this.isCached = z;
        this.isSnowballed = z2;
        this.grantedScopes = list;
        this.scopeData = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TokenData)) {
            return false;
        }
        TokenData tokenData = (TokenData) obj;
        if (!TextUtils.equals(this.token, tokenData.token) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.expirationTimeSecs, tokenData.expirationTimeSecs) || this.isCached != tokenData.isCached || this.isSnowballed != tokenData.isSnowballed || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.grantedScopes, tokenData.grantedScopes) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.scopeData, tokenData.scopeData)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.token, this.expirationTimeSecs, Boolean.valueOf(this.isCached), Boolean.valueOf(this.isSnowballed), this.grantedScopes, this.scopeData});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.mVersionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.token);
        Long l = this.expirationTimeSecs;
        if (l != null) {
            StrictModeUtils$VmPolicyBuilderCompatS.writeHeader(parcel, 3, 8);
            parcel.writeLong(l.longValue());
        }
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 4, this.isCached);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 5, this.isSnowballed);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringList$ar$ds(parcel, 6, this.grantedScopes);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 7, this.scopeData);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}

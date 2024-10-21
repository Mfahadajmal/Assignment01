package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(14);
    public final String displayName;
    public final String email;
    public final long expirationTimeSecs;
    private final Set extraRequestedScopes = new HashSet();
    public final String familyName;
    public final String givenName;
    public final String id;
    public final String idToken;
    final List mGrantedScopes;
    public final String obfuscatedIdentifier;
    public final Uri photoUrl;
    public String serverAuthCode;

    public GoogleSignInAccount(String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List list, String str7, String str8) {
        this.id = str;
        this.idToken = str2;
        this.email = str3;
        this.displayName = str4;
        this.photoUrl = uri;
        this.serverAuthCode = str5;
        this.expirationTimeSecs = j;
        this.obfuscatedIdentifier = str6;
        this.mGrantedScopes = list;
        this.givenName = str7;
        this.familyName = str8;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        if (!googleSignInAccount.obfuscatedIdentifier.equals(this.obfuscatedIdentifier) || !googleSignInAccount.getRequestedScopes().equals(getRequestedScopes())) {
            return false;
        }
        return true;
    }

    public final Set getRequestedScopes() {
        HashSet hashSet = new HashSet(this.mGrantedScopes);
        hashSet.addAll(this.extraRequestedScopes);
        return hashSet;
    }

    public final int hashCode() {
        return ((this.obfuscatedIdentifier.hashCode() + 527) * 31) + getRequestedScopes().hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.id;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, str);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.idToken);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 4, this.email);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 5, this.displayName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 6, this.photoUrl, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 7, this.serverAuthCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 8, this.expirationTimeSecs);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 9, this.obfuscatedIdentifier);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 10, this.mGrantedScopes);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 11, this.givenName);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 12, this.familyName);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}

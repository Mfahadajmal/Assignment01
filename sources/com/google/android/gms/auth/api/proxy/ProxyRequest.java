package com.google.android.gms.auth.api.proxy;

import _COROUTINE._BOUNDARY;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProxyRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ProxyRequest> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(12);
    public final byte[] body;
    final Bundle headers;
    public final int httpMethod;
    public final long timeoutMillis;
    public final String url;
    final int versionCode;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public final String googleUrl;
        public int httpMethod = 0;
        public final long timeoutMillis = 3000;
        public byte[] body = new byte[0];
        public final Bundle headers = new Bundle();

        public Builder(String str) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds(str);
            if (Patterns.WEB_URL.matcher(str).matches()) {
                this.googleUrl = str;
                return;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "The supplied url [ ", "] is not match Patterns.WEB_URL!"));
        }

        public final void putHeader$ar$ds(String str, String str2) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds$c11d1227_0(str, "Header name cannot be null or empty!");
            if (str2 == null) {
                str2 = "";
            }
            this.headers.putString(str, str2);
        }
    }

    public ProxyRequest(int i, String str, int i2, long j, byte[] bArr, Bundle bundle) {
        this.versionCode = i;
        this.url = str;
        this.httpMethod = i2;
        this.timeoutMillis = j;
        this.body = bArr;
        this.headers = bundle;
    }

    public final String toString() {
        return "ProxyRequest[ url: " + this.url + ", method: " + this.httpMethod + " ]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 1, this.url);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.httpMethod);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 3, this.timeoutMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 4, this.body);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 5, this.headers);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1000, this.versionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}

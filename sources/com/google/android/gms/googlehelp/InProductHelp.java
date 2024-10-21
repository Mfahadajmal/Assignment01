package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InProductHelp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<InProductHelp> CREATOR = new AdditionalConsentConfigCreator(6);
    public final String base64StartingFlow;
    public final int channel;
    public String contentUrl;
    public GoogleHelp googleHelp;
    public final int openingMode;
    public final String searchQuery;
    public final String symptom;

    public InProductHelp(GoogleHelp googleHelp, String str, String str2, int i, String str3, int i2, String str4) {
        this.googleHelp = googleHelp;
        this.searchQuery = str;
        this.contentUrl = str2;
        this.openingMode = i;
        this.symptom = str3;
        this.channel = i2;
        this.base64StartingFlow = str4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        AdditionalConsentConfigCreator.writeToParcel(this, parcel, i);
    }
}

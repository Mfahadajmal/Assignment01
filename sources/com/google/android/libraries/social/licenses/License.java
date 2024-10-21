package com.google.android.libraries.social.licenses;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class License implements Comparable<License>, Parcelable {
    public static final Parcelable.Creator<License> CREATOR = new ServiceDumpCreator(18);
    public final String libraryName;
    public final int licenseLength;
    public final long licenseOffset;
    public final String path;

    public License(String str, long j, int i) {
        this.libraryName = str;
        this.licenseOffset = j;
        this.licenseLength = i;
        this.path = "";
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(License license) {
        return this.libraryName.compareTo(license.libraryName);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof License)) {
            return false;
        }
        return this.libraryName.equals(((License) obj).libraryName);
    }

    public final int hashCode() {
        return this.libraryName.hashCode();
    }

    public final String toString() {
        return this.libraryName;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.libraryName);
        parcel.writeLong(this.licenseOffset);
        parcel.writeInt(this.licenseLength);
        parcel.writeString(this.path);
    }

    public License(Parcel parcel) {
        this.libraryName = parcel.readString();
        this.licenseOffset = parcel.readLong();
        this.licenseLength = parcel.readInt();
        this.path = parcel.readString();
    }
}

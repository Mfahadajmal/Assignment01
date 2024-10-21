package com.google.android.libraries.surveys;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.feedback.ServiceDumpCreator;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyMetadata implements Parcelable {
    public static final Parcelable.Creator<SurveyMetadata> CREATOR = new ServiceDumpCreator(19);
    private final int piiCollectionMode$ar$edu;
    public final String sessionId;
    public final String surveyId;
    public final String triggerId;

    public SurveyMetadata(String str, String str2, String str3, int i) {
        this.triggerId = str;
        this.surveyId = str2;
        this.sessionId = str3;
        this.piiCollectionMode$ar$edu = i;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof SurveyMetadata) {
            SurveyMetadata surveyMetadata = (SurveyMetadata) obj;
            if (Objects.equals(this.triggerId, surveyMetadata.triggerId) && Objects.equals(this.surveyId, surveyMetadata.surveyId) && Objects.equals(this.sessionId, surveyMetadata.sessionId) && this.piiCollectionMode$ar$edu == surveyMetadata.piiCollectionMode$ar$edu) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.triggerId, this.surveyId, this.sessionId, Integer.valueOf(this.piiCollectionMode$ar$edu));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str;
        parcel.writeString(this.triggerId);
        parcel.writeString(this.surveyId);
        parcel.writeString(this.sessionId);
        int i2 = this.piiCollectionMode$ar$edu;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    str = "CONFIDENTIAL";
                } else {
                    str = "STANDARD";
                }
            } else {
                str = "DISABLED";
            }
        } else {
            str = "NOT_SET";
        }
        parcel.writeString(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public SurveyMetadata(Parcel parcel) {
        char c;
        this.triggerId = parcel.readString();
        this.surveyId = parcel.readString();
        this.sessionId = parcel.readString();
        String readString = parcel.readString();
        int i = 3;
        switch (readString.hashCode()) {
            case -1446966090:
                if (readString.equals("NOT_SET")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 364290440:
                if (readString.equals("CONFIDENTIAL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1053567612:
                if (readString.equals("DISABLED")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2095255229:
                if (readString.equals("STANDARD")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            i = 1;
        } else if (c == 1) {
            i = 2;
        } else if (c != 2) {
            if (c != 3) {
                throw new IllegalArgumentException();
            }
            i = 4;
        }
        this.piiCollectionMode$ar$edu = i;
    }
}

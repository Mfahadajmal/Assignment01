package com.google.android.libraries.surveys.internal.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class QuestionMetrics implements Parcelable {
    public static final Parcelable.Creator<QuestionMetrics> CREATOR = new ServiceDumpRequestCreator(1);
    private long delayEndMs;
    private long delayStartMs;

    public QuestionMetrics() {
        this.delayStartMs = -1L;
        this.delayEndMs = -1L;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean isShown() {
        if (this.delayStartMs >= 0) {
            return true;
        }
        return false;
    }

    public final void markAsAnswered() {
        if (isShown()) {
            if (this.delayEndMs >= 0) {
                long j = SurveyUtils.TIMEOUT_MS;
                return;
            } else {
                this.delayEndMs = SystemClock.elapsedRealtime();
                return;
            }
        }
        Log.e("SurveyQuestionMetrics", "Question was marked as answered but was never marked as shown.");
    }

    public final void markAsShown() {
        if (isShown()) {
            return;
        }
        this.delayStartMs = SystemClock.elapsedRealtime();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.delayStartMs);
        parcel.writeLong(this.delayEndMs);
    }

    public QuestionMetrics(Parcel parcel) {
        this.delayStartMs = parcel.readLong();
        this.delayEndMs = parcel.readLong();
    }
}

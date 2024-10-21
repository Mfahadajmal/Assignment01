package com.google.android.libraries.surveys.internal.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;
import com.google.android.libraries.surveys.SurveyData;
import com.google.android.libraries.surveys.SurveyMetadata;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentInferenceLogEvent;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Protobuf;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$Session;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyDataImpl implements SurveyData {
    public static final Parcelable.Creator<SurveyDataImpl> CREATOR = new ServiceDumpRequestCreator(2);
    public final ImmutableList backendErrors;
    public final String noSurveyReason;
    public final Survey$Session session;
    public final String surveyId;
    public final Survey$Payload surveyPayload;
    public final String triggerId;
    public final long triggerTimeMs;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public ImmutableList backendErrors;
        public String noSurveyReason;
        public Survey$Session session;
        public final String surveyId;
        public final Survey$Payload surveyPayload;
        public final String triggerId;
        public long triggerTimeMs;

        public Builder(String str, String str2, Survey$Payload survey$Payload) {
            if (!TextUtils.isEmpty(str)) {
                if (survey$Payload != null) {
                    this.triggerId = str;
                    this.surveyId = str2;
                    this.surveyPayload = survey$Payload;
                    return;
                }
                throw new IllegalArgumentException("Payload is null.");
            }
            throw new IllegalArgumentException("Trigger ID cannot be null or empty.");
        }
    }

    public SurveyDataImpl(String str, String str2, long j, Survey$Session survey$Session, Survey$Payload survey$Payload, String str3, ImmutableList immutableList) {
        this.triggerId = str;
        this.surveyId = str2;
        this.triggerTimeMs = j;
        this.noSurveyReason = str3;
        this.backendErrors = immutableList;
        this.surveyPayload = survey$Payload;
        this.session = survey$Session;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String getSessionId() {
        Survey$Session survey$Session = this.session;
        if (survey$Session != null) {
            return survey$Session.sessionId_;
        }
        return null;
    }

    public final SurveyMetadata getSurveyMetadata() {
        int i;
        if (SurveyUtils.isPiiCollectionEnabled(this.surveyPayload)) {
            i = 3;
        } else {
            i = 2;
        }
        return new SurveyMetadata(this.triggerId, this.surveyId, getSessionId(), i);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.triggerId);
        parcel.writeString(this.surveyId);
        parcel.writeString(this.noSurveyReason);
        parcel.writeLong(this.triggerTimeMs);
        parcel.writeStringList(this.backendErrors);
        OnDeviceExplicitContentInferenceLogEvent.put(parcel, this.surveyPayload);
        OnDeviceExplicitContentInferenceLogEvent.put(parcel, this.session);
    }

    public SurveyDataImpl(Parcel parcel) {
        this.triggerId = parcel.readString();
        this.surveyId = parcel.readString();
        this.noSurveyReason = parcel.readString();
        this.triggerTimeMs = parcel.readLong();
        int i = ImmutableList.ImmutableList$ar$NoOp;
        ImmutableList immutableList = RegularImmutableList.EMPTY;
        this.backendErrors = immutableList;
        parcel.readStringList(immutableList);
        Survey$Payload survey$Payload = Survey$Payload.DEFAULT_INSTANCE;
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        this.surveyPayload = (Survey$Payload) OnDeviceExplicitContentInferenceLogEvent.get(parcel, survey$Payload, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        this.session = (Survey$Session) OnDeviceExplicitContentInferenceLogEvent.get(parcel, Survey$Session.DEFAULT_INSTANCE, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
    }
}

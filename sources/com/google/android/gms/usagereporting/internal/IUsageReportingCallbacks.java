package com.google.android.gms.usagereporting.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.usagereporting.UsageReportingOptInOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IUsageReportingCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Stub extends BaseStub implements IUsageReportingCallbacks {
        public Stub() {
            super("com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks");
        }

        public static void onCanLog$ar$ds() {
            throw new IllegalStateException("Not implemented.");
        }

        public static void onGetAppWhitelist$ar$ds() {
            throw new IllegalStateException("Not implemented.");
        }

        public static void onGetCheckboxSettingsPendingIntent$ar$ds() {
            throw new IllegalStateException("Not implemented");
        }

        public static void onGetElCapitanOptions$ar$ds() {
            throw new IllegalStateException("Not implemented.");
        }

        public static void onGetPassedWhitelists$ar$ds() {
            throw new IllegalStateException("Not implemented");
        }

        public static void onGetSafetyOptions$ar$ds() {
            throw new IllegalStateException("Not implemented.");
        }

        public static void onSetAppWhitelist$ar$ds() {
            throw new IllegalStateException("Not implemented.");
        }

        public static void onSetElCapitanOptions$ar$ds() {
            throw new IllegalStateException("Not implemented.");
        }

        public static void onSetOptInOptions$ar$ds() {
            throw new IllegalStateException("Not implemented.");
        }

        public static void onSetSafetyOptions$ar$ds() {
            throw new IllegalStateException("Not implemented.");
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            switch (i) {
                case 2:
                    Status status = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    UsageReportingOptInOptions usageReportingOptInOptions = (UsageReportingOptInOptions) Codecs.createParcelable(parcel, UsageReportingOptInOptions.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onGetOptInOptions(status, usageReportingOptInOptions);
                    return true;
                case 3:
                    Codecs.enforceNoDataAvail(parcel);
                    onSetOptInOptions$ar$ds();
                    return true;
                case 4:
                    Status status2 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onRegisterOptInOptionsChangedListener(status2);
                    return true;
                case 5:
                    Status status3 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onUnregisterOptInOptionsChangedListener(status3);
                    return true;
                case 6:
                    parcel.createStringArrayList();
                    Codecs.enforceNoDataAvail(parcel);
                    onGetAppWhitelist$ar$ds();
                    return true;
                case 7:
                    Codecs.enforceNoDataAvail(parcel);
                    onSetAppWhitelist$ar$ds();
                    return true;
                case 8:
                    Codecs.createBoolean(parcel);
                    Codecs.enforceNoDataAvail(parcel);
                    onCanLog$ar$ds();
                    return true;
                case 9:
                    Codecs.enforceNoDataAvail(parcel);
                    onGetPassedWhitelists$ar$ds();
                    return true;
                case 10:
                    Codecs.enforceNoDataAvail(parcel);
                    onGetCheckboxSettingsPendingIntent$ar$ds();
                    return true;
                case 11:
                    Codecs.enforceNoDataAvail(parcel);
                    onSetElCapitanOptions$ar$ds();
                    return true;
                case 12:
                    Codecs.enforceNoDataAvail(parcel);
                    onGetElCapitanOptions$ar$ds();
                    return true;
                case 13:
                    Codecs.enforceNoDataAvail(parcel);
                    onSetSafetyOptions$ar$ds();
                    return true;
                case 14:
                    Codecs.enforceNoDataAvail(parcel);
                    onGetSafetyOptions$ar$ds();
                    return true;
                default:
                    return false;
            }
        }

        public void onGetOptInOptions(Status status, UsageReportingOptInOptions usageReportingOptInOptions) {
            throw new IllegalStateException("Not implemented.");
        }

        public void onRegisterOptInOptionsChangedListener(Status status) {
            throw new IllegalStateException("Not implemented.");
        }

        public void onUnregisterOptInOptionsChangedListener(Status status) {
            throw new IllegalStateException("Not implemented.");
        }

        public Stub(byte[] bArr) {
            this();
        }
    }
}

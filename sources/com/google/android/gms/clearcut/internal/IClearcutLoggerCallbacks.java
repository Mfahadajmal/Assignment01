package com.google.android.gms.clearcut.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IClearcutLoggerCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Stub extends BaseStub implements IClearcutLoggerCallbacks {
        public Stub(byte[] bArr) {
            this();
        }

        public static void onForceUpload$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onGetCollectForDebugExpiryTime$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onGetLogEventParcelables$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onGetLogEventParcelablesDataBuffer$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onStartCollectForDebug$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onStopCollectForDebug$ar$ds() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            switch (i) {
                case 1:
                    Status status = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onLogEvent(status);
                    return true;
                case 2:
                    Codecs.enforceNoDataAvail(parcel);
                    onForceUpload$ar$ds();
                    return true;
                case 3:
                    parcel.readLong();
                    Codecs.enforceNoDataAvail(parcel);
                    onStartCollectForDebug$ar$ds();
                    return true;
                case 4:
                    Codecs.enforceNoDataAvail(parcel);
                    onStopCollectForDebug$ar$ds();
                    return true;
                case 5:
                    parcel.readLong();
                    Codecs.enforceNoDataAvail(parcel);
                    onGetCollectForDebugExpiryTime$ar$ds();
                    return true;
                case 6:
                    Codecs.enforceNoDataAvail(parcel);
                    onGetLogEventParcelables$ar$ds();
                    return true;
                case 7:
                    Codecs.enforceNoDataAvail(parcel);
                    onGetLogEventParcelablesDataBuffer$ar$ds();
                    return true;
                case 8:
                    Status status2 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onLogError(status2);
                    return true;
                default:
                    return false;
            }
        }

        public void onLogError(Status status) {
            throw new UnsupportedOperationException();
        }

        public void onLogEvent(Status status) {
            throw new UnsupportedOperationException();
        }

        public Stub() {
            super("com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
        }
    }
}

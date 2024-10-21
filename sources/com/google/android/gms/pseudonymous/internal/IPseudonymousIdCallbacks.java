package com.google.android.gms.pseudonymous.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.pseudonymous.PseudonymousIdToken;
import com.google.android.gms.pseudonymous.SessionZwiebackToken;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IPseudonymousIdCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Stub extends BaseStub implements IPseudonymousIdCallbacks {
        public Stub(byte[] bArr) {
            this();
        }

        public static void onGetLastResetWallTimeMs$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onSessionZwiebackTokensRetrieved$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onSetSessionZwiebackTokens$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onSetToken$ar$ds() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return false;
                            }
                            Codecs.enforceNoDataAvail(parcel);
                            onSetSessionZwiebackTokens$ar$ds();
                        } else {
                            parcel.createTypedArrayList(SessionZwiebackToken.CREATOR);
                            Codecs.enforceNoDataAvail(parcel);
                            onSessionZwiebackTokensRetrieved$ar$ds();
                        }
                    } else {
                        parcel.readLong();
                        Codecs.enforceNoDataAvail(parcel);
                        onGetLastResetWallTimeMs$ar$ds();
                    }
                } else {
                    Codecs.enforceNoDataAvail(parcel);
                    onSetToken$ar$ds();
                }
            } else {
                Status status = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                PseudonymousIdToken pseudonymousIdToken = (PseudonymousIdToken) Codecs.createParcelable(parcel, PseudonymousIdToken.CREATOR);
                Codecs.enforceNoDataAvail(parcel);
                onTokenRetrieved(status, pseudonymousIdToken);
            }
            return true;
        }

        public void onTokenRetrieved(Status status, PseudonymousIdToken pseudonymousIdToken) {
            throw null;
        }

        public Stub() {
            super("com.google.android.gms.pseudonymous.internal.IPseudonymousIdCallbacks");
        }
    }
}

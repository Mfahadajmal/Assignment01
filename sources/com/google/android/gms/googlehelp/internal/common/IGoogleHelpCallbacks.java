package com.google.android.gms.googlehelp.internal.common;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.InProductHelp;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGoogleHelpCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Stub extends BaseStub implements IGoogleHelpCallbacks {
        public Stub() {
            super("com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks");
        }

        public static void onAsyncPsbdSaved$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onAsyncPsdSaved$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onC2cSupportRequestFailed$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onC2cSupportRequestSuccess$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onChatSupportRequestFailed$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onChatSupportRequestSuccess$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onEscalationOptionsReceived$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onEscalationOptionsRequestFailed$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onPipClick$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onPipInCallingAppDisabled$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onPipInCallingAppHidden$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onPipShown$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onRealtimeSupportStatusRequestFailed$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onRealtimeSupportStatusSuccess$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onSuggestionsReceived$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onSuggestionsRequestFailed$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onTogglingPipProcessed$ar$ds() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            switch (i) {
                case 1:
                    GoogleHelp googleHelp = (GoogleHelp) Codecs.createParcelable(parcel, GoogleHelp.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onGoogleHelpProcessed(googleHelp);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    Codecs.enforceNoDataAvail(parcel);
                    onTogglingPipProcessed$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    onPipShown$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    onPipClick$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    onPipInCallingAppHidden$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    onPipInCallingAppDisabled$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    onAsyncPsdSaved$ar$ds();
                    return true;
                case 8:
                    onAsyncPsbdSaved$ar$ds();
                    return true;
                case 9:
                    parcel.readInt();
                    Codecs.enforceNoDataAvail(parcel);
                    onChatSupportRequestSuccess$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    onChatSupportRequestFailed$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    onC2cSupportRequestSuccess$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    onC2cSupportRequestFailed$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.createByteArray();
                    Codecs.enforceNoDataAvail(parcel);
                    onSuggestionsReceived$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    onSuggestionsRequestFailed$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.createByteArray();
                    Codecs.enforceNoDataAvail(parcel);
                    onEscalationOptionsReceived$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    onEscalationOptionsRequestFailed$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    InProductHelp inProductHelp = (InProductHelp) Codecs.createParcelable(parcel, InProductHelp.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onInProductHelpProcessed(inProductHelp);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.createByteArray();
                    Codecs.enforceNoDataAvail(parcel);
                    onRealtimeSupportStatusSuccess$ar$ds();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    onRealtimeSupportStatusRequestFailed$ar$ds();
                    parcel2.writeNoException();
                    return true;
                default:
                    return false;
            }
        }

        public void onGoogleHelpProcessed(GoogleHelp googleHelp) {
            throw new UnsupportedOperationException();
        }

        public void onInProductHelpProcessed(InProductHelp inProductHelp) {
            throw new UnsupportedOperationException();
        }

        public Stub(byte[] bArr) {
            this();
        }
    }
}

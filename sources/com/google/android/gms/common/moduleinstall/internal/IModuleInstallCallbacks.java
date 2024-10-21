package com.google.android.gms.common.moduleinstall.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IModuleInstallCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Stub extends BaseStub implements IModuleInstallCallbacks {
        public Stub(byte[] bArr) {
            this();
        }

        public static void onDeferredInstall$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onModuleInstallIntentResponse$ar$ds() {
            throw new UnsupportedOperationException();
        }

        public static void onModulesAvailabilityResponse$ar$ds() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return false;
                        }
                        Codecs.enforceNoDataAvail(parcel);
                        onDeferredInstall$ar$ds();
                    } else {
                        Codecs.enforceNoDataAvail(parcel);
                        onModuleInstallIntentResponse$ar$ds();
                    }
                } else {
                    Status status = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    ModuleInstallResponse moduleInstallResponse = (ModuleInstallResponse) Codecs.createParcelable(parcel, ModuleInstallResponse.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onModuleInstallResponse(status, moduleInstallResponse);
                }
            } else {
                Codecs.enforceNoDataAvail(parcel);
                onModulesAvailabilityResponse$ar$ds();
            }
            return true;
        }

        public void onModuleInstallResponse(Status status, ModuleInstallResponse moduleInstallResponse) {
            throw new UnsupportedOperationException();
        }

        public Stub() {
            super("com.google.android.gms.common.moduleinstall.internal.IModuleInstallCallbacks");
        }
    }
}

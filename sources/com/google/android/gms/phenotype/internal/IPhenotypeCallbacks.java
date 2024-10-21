package com.google.android.gms.phenotype.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.Configurations;
import com.google.android.gms.phenotype.DogfoodsToken;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.android.gms.phenotype.Flag;
import com.google.android.gms.phenotype.FlagOverrides;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IPhenotypeCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IPhenotypeCallbacks {
        private final AppLifecycleMonitor completionSource$ar$class_merging$ar$class_merging;

        public Stub(AppLifecycleMonitor appLifecycleMonitor) {
            this();
            this.completionSource$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            switch (i) {
                case 1:
                    Status status = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onRegistered(status);
                    return true;
                case 2:
                    Status status2 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onWeakRegistered(status2);
                    return true;
                case 3:
                    Status status3 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onUnregistered(status3);
                    return true;
                case 4:
                    Status status4 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Configurations configurations = (Configurations) Codecs.createParcelable(parcel, Configurations.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onGetConfigurationSnapshot(status4, configurations);
                    return true;
                case 5:
                    Status status5 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onCommitToConfiguration(status5);
                    return true;
                case 6:
                    Status status6 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    ExperimentTokens experimentTokens = (ExperimentTokens) Codecs.createParcelable(parcel, ExperimentTokens.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onExperimentTokensRetrieved(status6, experimentTokens);
                    return true;
                case 7:
                    Status status7 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    DogfoodsToken dogfoodsToken = (DogfoodsToken) Codecs.createParcelable(parcel, DogfoodsToken.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onDogfoodsTokenRetrieved(status7, dogfoodsToken);
                    return true;
                case 8:
                    Status status8 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onDogfoodsTokenSet(status8);
                    return true;
                case 9:
                    Status status9 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Flag flag = (Flag) Codecs.createParcelable(parcel, Flag.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onGetFlag(status9, flag);
                    return true;
                case 10:
                    Status status10 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Configurations configurations2 = (Configurations) Codecs.createParcelable(parcel, Configurations.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onGetCommittedConfiguration(status10, configurations2);
                    return true;
                case 11:
                    Status status11 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    parcel.readLong();
                    Codecs.enforceNoDataAvail(parcel);
                    onSyncAfter$ar$ds(status11);
                    return true;
                case 12:
                    Status status12 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onSetFlagOverride(status12);
                    return true;
                case 13:
                    Status status13 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    FlagOverrides flagOverrides = (FlagOverrides) Codecs.createParcelable(parcel, FlagOverrides.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onListOrDeleteFlagOverrides(status13, flagOverrides);
                    return true;
                case 14:
                    Status status14 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onSetAppSpecificProperties(status14);
                    return true;
                case 15:
                    Status status15 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onSetDimensions(status15);
                    return true;
                case 16:
                    Status status16 = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                    long readLong = parcel.readLong();
                    Codecs.enforceNoDataAvail(parcel);
                    onGetServingVersion(status16, readLong);
                    return true;
                default:
                    return false;
            }
        }

        public final void onCommitToConfiguration(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onDogfoodsTokenRetrieved(Status status, DogfoodsToken dogfoodsToken) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, dogfoodsToken, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onDogfoodsTokenSet(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onExperimentTokensRetrieved(Status status, ExperimentTokens experimentTokens) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, experimentTokens, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onGetCommittedConfiguration(Status status, Configurations configurations) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, configurations, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onGetConfigurationSnapshot(Status status, Configurations configurations) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, configurations, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onGetFlag(Status status, Flag flag) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, flag, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onGetServingVersion(Status status, long j) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, Long.valueOf(j), this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onListOrDeleteFlagOverrides(Status status, FlagOverrides flagOverrides) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, flagOverrides, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onRegistered(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onSetAppSpecificProperties(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onSetDimensions(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onSetFlagOverride(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onSyncAfter$ar$ds(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onUnregistered(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public final void onWeakRegistered(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.completionSource$ar$class_merging$ar$class_merging);
        }

        public Stub() {
            super("com.google.android.gms.phenotype.internal.IPhenotypeCallbacks");
        }
    }
}

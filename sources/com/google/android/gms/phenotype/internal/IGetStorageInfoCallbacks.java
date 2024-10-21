package com.google.android.gms.phenotype.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$StorageInfos;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGetStorageInfoCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IGetStorageInfoCallbacks {
        final /* synthetic */ AppLifecycleMonitor val$completionSource$ar$class_merging$ar$class_merging;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(AppLifecycleMonitor appLifecycleMonitor) {
            this();
            this.val$completionSource$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i == 2) {
                Status status = (Status) Codecs.createParcelable(parcel, Status.CREATOR);
                byte[] createByteArray = parcel.createByteArray();
                Codecs.enforceNoDataAvail(parcel);
                onResult(status, createByteArray);
                return true;
            }
            return false;
        }

        public final void onResult(Status status, byte[] bArr) {
            if (status.isSuccess()) {
                try {
                    StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, (StorageInfoProto$StorageInfos) GeneratedMessageLite.parseFrom(StorageInfoProto$StorageInfos.DEFAULT_INSTANCE, bArr, ExtensionRegistryLite.getGeneratedRegistry()), this.val$completionSource$ar$class_merging$ar$class_merging);
                    return;
                } catch (InvalidProtocolBufferException e) {
                    this.val$completionSource$ar$class_merging$ar$class_merging.setException(e);
                    return;
                }
            }
            StrictModeUtils$VmPolicyBuilderCompatS.setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(status, null, this.val$completionSource$ar$class_merging$ar$class_merging);
        }

        public Stub() {
            super("com.google.android.gms.phenotype.internal.IGetStorageInfoCallbacks");
        }
    }
}

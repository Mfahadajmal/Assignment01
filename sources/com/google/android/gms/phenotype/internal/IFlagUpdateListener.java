package com.google.android.gms.phenotype.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.phenotype.client.stable.FlagUpdateInfo;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Protobuf;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IFlagUpdateListener extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IFlagUpdateListener {
        final /* synthetic */ ListenerHolder val$listenerHolder;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(ListenerHolder listenerHolder) {
            this();
            this.val$listenerHolder = listenerHolder;
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i == 2) {
                byte[] createByteArray = parcel.createByteArray();
                Codecs.enforceNoDataAvail(parcel);
                onFlagUpdate(createByteArray);
                return true;
            }
            return false;
        }

        public final void onFlagUpdate(final byte[] bArr) {
            this.val$listenerHolder.notifyListener(new ListenerHolder.Notifier() { // from class: com.google.android.gms.phenotype.PhenotypeClient$6$1
                @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
                public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
                    WindowTrackerFactory windowTrackerFactory = (WindowTrackerFactory) obj;
                    try {
                        byte[] bArr2 = bArr;
                        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
                        Protobuf protobuf = Protobuf.INSTANCE;
                        windowTrackerFactory.onFlagUpdate((FlagUpdateInfo) GeneratedMessageLite.parseFrom(FlagUpdateInfo.DEFAULT_INSTANCE, bArr2, ExtensionRegistryLite.EMPTY_REGISTRY_LITE));
                    } catch (InvalidProtocolBufferException unused) {
                    }
                }
            });
        }

        public Stub() {
            super("com.google.android.gms.phenotype.internal.IFlagUpdateListener");
        }
    }
}

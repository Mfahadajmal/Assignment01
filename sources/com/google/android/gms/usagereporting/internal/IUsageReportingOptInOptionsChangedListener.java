package com.google.android.gms.usagereporting.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.usagereporting.UsageReportingApi$OptInOptionsChangedListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IUsageReportingOptInOptionsChangedListener extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IUsageReportingOptInOptionsChangedListener {
        private final ListenerHolder listenerHolder;

        public Stub() {
            super("com.google.android.gms.usagereporting.internal.IUsageReportingOptInOptionsChangedListener");
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i == 2) {
                onOptInOptionsChanged();
                return true;
            }
            return false;
        }

        public final void onOptInOptionsChanged() {
            this.listenerHolder.notifyListener(new ListenerHolder.Notifier() { // from class: com.google.android.gms.usagereporting.internal.UsageReportingClientImpl$UsageReportingOptInOptionsChangedListener$1
                @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
                public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
                    ((UsageReportingApi$OptInOptionsChangedListener) obj).onOptInOptionsChanged();
                }
            });
        }

        public Stub(ListenerHolder listenerHolder) {
            this();
            this.listenerHolder = listenerHolder;
        }
    }
}

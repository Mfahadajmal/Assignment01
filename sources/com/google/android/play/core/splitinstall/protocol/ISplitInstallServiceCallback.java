package com.google.android.play.core.splitinstall.protocol;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallService;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ISplitInstallServiceCallback extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Stub extends BaseStub implements ISplitInstallServiceCallback {
        public final AppLifecycleMonitor source$ar$class_merging$6cd5309_0$ar$class_merging;
        final /* synthetic */ SplitInstallService this$0;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(SplitInstallService splitInstallService, AppLifecycleMonitor appLifecycleMonitor) {
            this();
            this.this$0 = splitInstallService;
            this.source$ar$class_merging$6cd5309_0$ar$class_merging = appLifecycleMonitor;
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            switch (i) {
                case 2:
                    int readInt = parcel.readInt();
                    Bundle bundle = (Bundle) Codecs.createParcelable(parcel, Bundle.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onStartInstall(readInt, bundle);
                    return true;
                case 3:
                    parcel.readInt();
                    Codecs.enforceNoDataAvail(parcel);
                    onCompleteInstall$ar$ds();
                    return true;
                case 4:
                    parcel.readInt();
                    Codecs.enforceNoDataAvail(parcel);
                    onCancelInstall$ar$ds();
                    return true;
                case 5:
                    parcel.readInt();
                    Codecs.enforceNoDataAvail(parcel);
                    onGetSessionState$ar$ds();
                    return true;
                case 6:
                    Bundle bundle2 = (Bundle) Codecs.createParcelable(parcel, Bundle.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onError(bundle2);
                    return true;
                case 7:
                    ArrayList createTypedArrayList = parcel.createTypedArrayList(Bundle.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onGetSessionStates(createTypedArrayList);
                    return true;
                case 8:
                    Bundle bundle3 = (Bundle) Codecs.createParcelable(parcel, Bundle.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onDeferredUninstall(bundle3);
                    return true;
                case 9:
                    Codecs.enforceNoDataAvail(parcel);
                    onDeferredInstall$ar$ds$245a6134_0();
                    return true;
                case 10:
                    Codecs.enforceNoDataAvail(parcel);
                    onGetSplitsForAppUpdate$ar$ds();
                    return true;
                case 11:
                    Codecs.enforceNoDataAvail(parcel);
                    onCompleteInstallForAppUpdate$ar$ds();
                    return true;
                case 12:
                    Codecs.enforceNoDataAvail(parcel);
                    onDeferredLanguageInstall$ar$ds();
                    return true;
                case 13:
                    Codecs.enforceNoDataAvail(parcel);
                    onDeferredLanguageUninstall$ar$ds();
                    return true;
                default:
                    return false;
            }
        }

        public final void onCancelInstall$ar$ds() {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public final void onCompleteInstall$ar$ds() {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public final void onCompleteInstallForAppUpdate$ar$ds() {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public final void onDeferredInstall$ar$ds$245a6134_0() {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public final void onDeferredLanguageInstall$ar$ds() {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public final void onDeferredLanguageUninstall$ar$ds() {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public void onDeferredUninstall(Bundle bundle) {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public final void onError(Bundle bundle) {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
            int i = bundle.getInt("error_code");
            SplitInstallService.logger$ar$class_merging$ceb098d3_0$ar$class_merging.e$ar$ds$cdf76eb7_0("onError(%d)", Integer.valueOf(i));
            this.source$ar$class_merging$6cd5309_0$ar$class_merging.trySetException(new SplitInstallException(i));
        }

        public final void onGetSessionState$ar$ds() {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public void onGetSessionStates(List list) {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public final void onGetSplitsForAppUpdate$ar$ds() {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public void onStartInstall(int i, Bundle bundle) {
            this.this$0.connectionManager.unbindAndReleaseTask$ar$class_merging$ar$class_merging(this.source$ar$class_merging$6cd5309_0$ar$class_merging);
        }

        public Stub() {
            super("com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback");
        }
    }
}

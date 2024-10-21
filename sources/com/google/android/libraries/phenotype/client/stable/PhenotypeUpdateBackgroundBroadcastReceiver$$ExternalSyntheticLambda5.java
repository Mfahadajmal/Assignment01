package com.google.android.libraries.phenotype.client.stable;

import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda2;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.common.base.Pair;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5 implements AsyncFunction {
    public final /* synthetic */ Object PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5$ar$f$3;
    public final /* synthetic */ String f$0;
    public final /* synthetic */ PackageInfo f$1;
    public final /* synthetic */ PhenotypeContext f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5(PhenotypeContext phenotypeContext, ListenableFuture listenableFuture, PackageInfo packageInfo, String str, int i) {
        this.switching_field = i;
        this.f$2 = phenotypeContext;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5$ar$f$3 = listenableFuture;
        this.f$1 = packageInfo;
        this.f$0 = str;
    }

    /* JADX WARN: Type inference failed for: r9v13, types: [java.util.concurrent.Future, java.lang.Object] */
    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object obj) {
        if (this.switching_field != 0) {
            SnapshotProto$Snapshot snapshotProto$Snapshot = (SnapshotProto$Snapshot) ContextDataProvider.getDone(this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5$ar$f$3);
            if (snapshotProto$Snapshot.snapshotToken_.isEmpty()) {
                return ImmediateFuture.NULL;
            }
            String str = this.f$0;
            PackageInfo packageInfo = this.f$1;
            PhenotypeContext phenotypeContext = this.f$2;
            return AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(PhenotypeAccountStore.getAccountsStore$ar$class_merging(phenotypeContext).getData()), new AiCoreClientImpl$$ExternalSyntheticLambda2(packageInfo.configPackage, 7), phenotypeContext.getExecutor())), new PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5(str, packageInfo, phenotypeContext, snapshotProto$Snapshot, 0), phenotypeContext.getExecutor());
        }
        String str2 = this.f$0;
        if (!((String) obj).equals(str2)) {
            return ImmediateFuture.NULL;
        }
        if (PhenotypeUpdateBroadcastReceiver.packageAndAccountCallbacks.containsKey(new Pair(this.f$1.configPackage, str2))) {
            return ImmediateFuture.NULL;
        }
        return this.f$2.getPhenotypeClient$ar$class_merging$ar$class_merging$ar$class_merging().commitToConfiguration(((SnapshotProto$Snapshot) this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5$ar$f$3).snapshotToken_);
    }

    public /* synthetic */ PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5(String str, PackageInfo packageInfo, PhenotypeContext phenotypeContext, SnapshotProto$Snapshot snapshotProto$Snapshot, int i) {
        this.switching_field = i;
        this.f$0 = str;
        this.f$1 = packageInfo;
        this.f$2 = phenotypeContext;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5$ar$f$3 = snapshotProto$Snapshot;
    }
}

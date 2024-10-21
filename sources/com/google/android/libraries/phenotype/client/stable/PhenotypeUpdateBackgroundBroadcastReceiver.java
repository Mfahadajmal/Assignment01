package com.google.android.libraries.phenotype.client.stable;

import _COROUTINE._BOUNDARY;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.util.Log;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda2;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.experiments.mobile.base.AndroidBacking;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeUpdateBackgroundBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        PackageInfo packageInfo;
        String stringExtra = intent.getStringExtra("com.google.android.gms.phenotype.PACKAGE_NAME");
        if (stringExtra != null) {
            if (!stringExtra.contains("../") && !stringExtra.contains("/..")) {
                PhenotypeContext phenotypeContextFrom = PhenotypeContext.getPhenotypeContextFrom(context);
                if (phenotypeContextFrom == null) {
                    PhenotypeContext.isTestMode$ar$ds();
                    ContextDataProvider.checkState(false);
                    return;
                }
                Map registeredPackages = PackageInfo.getRegisteredPackages(context);
                if (!registeredPackages.isEmpty() && (packageInfo = (PackageInfo) registeredPackages.get(stringExtra)) != null && packageInfo.backing.equals(AndroidBacking.PROCESS_STABLE)) {
                    BroadcastReceiver.PendingResult goAsync = goAsync();
                    GwtFluentFutureCatchingSpecialization gwtFluentFutureCatchingSpecialization = (GwtFluentFutureCatchingSpecialization) ContextDataProvider.withTimeout(AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(PhenotypeAccountStore.getAccountsStore$ar$class_merging(phenotypeContextFrom).getData()), new AiCoreClientImpl$$ExternalSyntheticLambda2(stringExtra, 6), phenotypeContextFrom.getExecutor())), new PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2(packageInfo, stringExtra, phenotypeContextFrom, 0), phenotypeContextFrom.getExecutor()), 25L, TimeUnit.SECONDS, phenotypeContextFrom.getExecutor());
                    gwtFluentFutureCatchingSpecialization.addListener(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(gwtFluentFutureCatchingSpecialization, stringExtra, goAsync, 17), phenotypeContextFrom.getExecutor());
                    return;
                }
                return;
            }
            Log.w("PhenotypeBackgroundRecv", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(stringExtra, "Got an invalid config package for P/H that includes '..': ", ". Exiting."));
        }
    }
}

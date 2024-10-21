package com.google.android.libraries.phenotype.client.stable;

import _COROUTINE._BOUNDARY;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda1;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda2;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccountRemovedBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String stringExtra;
        String str;
        ListenableFuture listenableFuture;
        ListenableFuture listenableFuture2;
        if ("android.accounts.action.ACCOUNT_REMOVED".equals(intent.getAction()) && (stringExtra = intent.getStringExtra("accountType")) != null) {
            if ("com.google".equals(stringExtra) || "com.google.work".equals(stringExtra) || "cn.google".equals(stringExtra) || "__logged_out_type".equals(stringExtra)) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    str = extras.getString("authAccount");
                } else {
                    str = null;
                }
                if (str != null && (str.contains("../") || str.contains("/.."))) {
                    Log.w("AccountRemovedRecv", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Got an invalid account name for P/H that includes '..':", ". Exiting."));
                    return;
                }
                PhenotypeContext.isTestMode$ar$ds();
                PhenotypeContext phenotypeContextFrom = PhenotypeContext.getPhenotypeContextFrom(context);
                if (phenotypeContextFrom == null) {
                    Log.w("AccountRemovedRecv", "Did not set PhenotypeContext before Account Removed Broadcast. Exiting.");
                    return;
                }
                BroadcastReceiver.PendingResult goAsync = goAsync();
                ListenableFuture[] listenableFutureArr = new ListenableFuture[2];
                if (str != null) {
                    listenableFuture = AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(PhenotypeAccountStore.getAccountsStore$ar$class_merging(phenotypeContextFrom).updateData(new AiCoreClientImpl$$ExternalSyntheticLambda2(str, 5), phenotypeContextFrom.getExecutor())), new AiCoreBaseService$$ExternalSyntheticLambda1(phenotypeContextFrom, str, 3), phenotypeContextFrom.getExecutor());
                } else {
                    listenableFuture = ImmediateFuture.NULL;
                }
                listenableFutureArr[0] = AbstractCatchingFuture.create(listenableFuture, IOException.class, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(1), DirectExecutor.INSTANCE);
                if (str != null) {
                    listenableFuture2 = phenotypeContextFrom.getExecutor().submit((Runnable) new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(context, str, 5));
                } else {
                    listenableFuture2 = ImmediateFuture.NULL;
                }
                listenableFutureArr[1] = listenableFuture2;
                ContextDataProvider.whenAllComplete$ar$class_merging$c090da7e_0$ar$class_merging(listenableFutureArr).call(new WorkerWrapper$$ExternalSyntheticLambda0(goAsync, 9), DirectExecutor.INSTANCE);
            }
        }
    }
}

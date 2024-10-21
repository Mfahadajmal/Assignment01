package com.google.android.apps.aicore.client.api.internal;

import android.os.RemoteException;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter$Resolver;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AiCoreBaseService$$ExternalSyntheticLambda17 implements CallbackToFutureAdapter$Resolver {
    public final /* synthetic */ Object AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$0;
    public final /* synthetic */ Object AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$1;
    public final /* synthetic */ Object f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ AiCoreBaseService$$ExternalSyntheticLambda17(AiCoreBaseService aiCoreBaseService, AiCoreBaseService.InferenceServiceContext inferenceServiceContext, Object obj, int i) {
        this.switching_field = i;
        this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$0 = aiCoreBaseService;
        this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$1 = inferenceServiceContext;
        this.f$2 = obj;
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.util.concurrent.Executor, java.lang.Object] */
    @Override // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
    public final Object attachCompleter(final CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                ?? r1 = this.f$2;
                callbackToFutureAdapter$Completer.addCancellationListener(new TrainingActivity$$ExternalSyntheticLambda1(r1, 8), DirectExecutor.INSTANCE);
                final ListMenuManager$$ExternalSyntheticLambda3 listMenuManager$$ExternalSyntheticLambda3 = new ListMenuManager$$ExternalSyntheticLambda3(callbackToFutureAdapter$Completer, this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$0, 10);
                Object obj = this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$1;
                final FailureSignal failureSignal = (FailureSignal) obj;
                synchronized (failureSignal.lock) {
                    if (((FailureSignal) obj).isSignaled) {
                        listMenuManager$$ExternalSyntheticLambda3.run();
                    } else {
                        ((FailureSignal) obj).observers.add(listMenuManager$$ExternalSyntheticLambda3);
                    }
                }
                ContextDataProvider.addCallback(r1, new FutureCallback() { // from class: com.google.android.apps.aicore.client.api.internal.FailureSignal.1
                    @Override // com.google.common.util.concurrent.FutureCallback
                    public final void onFailure(Throwable th) {
                        callbackToFutureAdapter$Completer.setException$ar$ds(th);
                        failureSignal.removeObserver(listMenuManager$$ExternalSyntheticLambda3);
                    }

                    @Override // com.google.common.util.concurrent.FutureCallback
                    public final void onSuccess(Object obj2) {
                        callbackToFutureAdapter$Completer.set$ar$ds(obj2);
                        failureSignal.removeObserver(listMenuManager$$ExternalSyntheticLambda3);
                    }
                }, DirectExecutor.INSTANCE);
                return "FailureSignal.propagate";
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            callbackToFutureAdapter$Completer.addCancellationListener(new SearchView$SearchAutoComplete.AnonymousClass1(atomicBoolean, 20, null), androidx.work.DirectExecutor.INSTANCE);
            this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$0.execute(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(atomicBoolean, callbackToFutureAdapter$Completer, (Function0) this.f$2, 4));
            return this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$1;
        }
        Object obj2 = this.f$2;
        Object obj3 = this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$1;
        Object obj4 = this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$0;
        try {
            callbackToFutureAdapter$Completer.addCancellationListener(new TrainingActivity$$ExternalSyntheticLambda1(((AiCoreBaseService) obj4).runInferenceInternal$ar$class_merging$53e9187e_0$ar$class_merging(((AiCoreBaseService.InferenceServiceContext) obj3).getService(), obj2, new ApplicationModule(callbackToFutureAdapter$Completer)), 7), ((AiCoreBaseService) obj4).workerExecutor);
            return "runInferenceFuture";
        } catch (RemoteException e) {
            callbackToFutureAdapter$Completer.setException$ar$ds(new AiCoreException(2, 6, "Failed to run inference", e));
            return null;
        } catch (RuntimeException e2) {
            callbackToFutureAdapter$Completer.setException$ar$ds(new AiCoreException(2, 0, "Failed to run inference", e2));
            return null;
        }
    }

    public /* synthetic */ AiCoreBaseService$$ExternalSyntheticLambda17(FailureSignal failureSignal, ListenableFuture listenableFuture, Supplier supplier, int i) {
        this.switching_field = i;
        this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$1 = failureSignal;
        this.f$2 = listenableFuture;
        this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$0 = supplier;
    }

    public /* synthetic */ AiCoreBaseService$$ExternalSyntheticLambda17(Executor executor, Function0 function0, int i) {
        this.switching_field = i;
        this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$0 = executor;
        this.AiCoreBaseService$$ExternalSyntheticLambda17$ar$f$1 = "setForegroundAsync";
        this.f$2 = function0;
    }
}

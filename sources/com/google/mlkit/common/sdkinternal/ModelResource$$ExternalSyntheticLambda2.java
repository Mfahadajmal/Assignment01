package com.google.mlkit.common.sdkinternal;

import android.os.Trace;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.view.WindowCallbackWrapper;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import androidx.lifecycle.MutableLiveData;
import androidx.transition.ViewUtilsApi19;
import androidx.work.Operation;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.mlkit.common.MlKitException;
import java.util.concurrent.Callable;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ModelResource$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Object ModelResource$$ExternalSyntheticLambda2$ar$f$0;
    public final /* synthetic */ Object ModelResource$$ExternalSyntheticLambda2$ar$f$1;
    public final /* synthetic */ Object ModelResource$$ExternalSyntheticLambda2$ar$f$2;
    public final /* synthetic */ Object ModelResource$$ExternalSyntheticLambda2$ar$f$3;
    public final /* synthetic */ Object ModelResource$$ExternalSyntheticLambda2$ar$f$4;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ModelResource$$ExternalSyntheticLambda2(ViewUtilsApi19.Api29Impl api29Impl, String str, Function0 function0, MutableLiveData mutableLiveData, CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer, int i) {
        this.switching_field = i;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$4 = api29Impl;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$0 = str;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$3 = function0;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$2 = mutableLiveData;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$1 = callbackToFutureAdapter$Completer;
    }

    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Object, java.util.concurrent.Callable] */
    @Override // java.lang.Runnable
    public final void run() {
        boolean isEnabled;
        if (this.switching_field != 0) {
            Object obj = this.ModelResource$$ExternalSyntheticLambda2$ar$f$1;
            Object obj2 = this.ModelResource$$ExternalSyntheticLambda2$ar$f$2;
            ?? r2 = this.ModelResource$$ExternalSyntheticLambda2$ar$f$3;
            Object obj3 = this.ModelResource$$ExternalSyntheticLambda2$ar$f$0;
            isEnabled = AppCompatDelegateImpl.Api21Impl.isEnabled();
            if (isEnabled) {
                try {
                    AppCompatDelegateImpl.Api21Impl.beginSection((String) obj3);
                } finally {
                    if (isEnabled) {
                        Trace.endSection();
                    }
                }
            }
            try {
                r2.invoke();
                ((MutableLiveData) obj2).postValue(Operation.SUCCESS);
                ((CallbackToFutureAdapter$Completer) obj).set$ar$ds(Operation.SUCCESS);
            } catch (Throwable th) {
                ((MutableLiveData) obj2).postValue(new WindowCallbackWrapper.Api23Impl(th) { // from class: androidx.work.Operation$State$FAILURE
                    private final Throwable mThrowable;

                    {
                        this.mThrowable = th;
                    }

                    public final String toString() {
                        return "FAILURE (" + this.mThrowable.getMessage() + ")";
                    }
                });
                ((CallbackToFutureAdapter$Completer) obj).setException$ar$ds(th);
            }
            if (isEnabled) {
                return;
            } else {
                return;
            }
        }
        Object obj4 = this.ModelResource$$ExternalSyntheticLambda2$ar$f$1;
        AppLifecycleMonitor appLifecycleMonitor = (AppLifecycleMonitor) obj4;
        boolean isCancellationRequested = appLifecycleMonitor.isCancellationRequested();
        Object obj5 = this.ModelResource$$ExternalSyntheticLambda2$ar$f$4;
        ?? r4 = this.ModelResource$$ExternalSyntheticLambda2$ar$f$3;
        Object obj6 = this.ModelResource$$ExternalSyntheticLambda2$ar$f$2;
        Object obj7 = this.ModelResource$$ExternalSyntheticLambda2$ar$f$0;
        try {
            if (isCancellationRequested) {
                ((AppLifecycleMonitor) obj6).cancel();
                return;
            }
            try {
                if (!((ModelResource) obj7).isLoaded.get()) {
                    ((ModelResource) obj7).load();
                    ((ModelResource) obj7).isLoaded.set(true);
                }
                if (((AppLifecycleMonitor) obj4).isCancellationRequested()) {
                    ((AppLifecycleMonitor) obj6).cancel();
                    return;
                }
                Object call = r4.call();
                if (((AppLifecycleMonitor) obj4).isCancellationRequested()) {
                    ((AppLifecycleMonitor) obj6).cancel();
                } else {
                    ((AppLifecycleMonitor) obj5).setResult(call);
                }
            } catch (RuntimeException e) {
                throw new MlKitException("Internal error has occurred when executing ML Kit tasks", e);
            }
        } catch (Exception e2) {
            if (appLifecycleMonitor.isCancellationRequested()) {
                ((AppLifecycleMonitor) obj6).cancel();
            } else {
                ((AppLifecycleMonitor) obj5).setException(e2);
            }
        }
    }

    public /* synthetic */ ModelResource$$ExternalSyntheticLambda2(ModelResource modelResource, AppLifecycleMonitor appLifecycleMonitor, AppLifecycleMonitor appLifecycleMonitor2, Callable callable, AppLifecycleMonitor appLifecycleMonitor3, int i) {
        this.switching_field = i;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$0 = modelResource;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$1 = appLifecycleMonitor;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$2 = appLifecycleMonitor2;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$3 = callable;
        this.ModelResource$$ExternalSyntheticLambda2$ar$f$4 = appLifecycleMonitor3;
    }
}

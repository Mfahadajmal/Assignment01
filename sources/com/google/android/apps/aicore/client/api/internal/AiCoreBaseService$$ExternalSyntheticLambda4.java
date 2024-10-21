package com.google.android.apps.aicore.client.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter$Resolver;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.apps.aicore.aidl.IPrepareInferenceEngineCallback;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.common.util.concurrent.DirectExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AiCoreBaseService$$ExternalSyntheticLambda4 implements CallbackToFutureAdapter$Resolver {
    public final /* synthetic */ Object AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$0;
    public final /* synthetic */ Object AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ AiCoreBaseService$$ExternalSyntheticLambda4(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$0 = obj;
        this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$1 = obj2;
    }

    /* JADX WARN: Type inference failed for: r11v1, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object, java.lang.Runnable] */
    @Override // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
    public final Object attachCompleter(final CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer) {
        int i = this.switching_field;
        int i2 = 0;
        if (i != 0) {
            if (i != 1) {
                Object obj = this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$1;
                final AtomicBoolean atomicBoolean = new AtomicBoolean();
                final ?? r1 = this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$0;
                BroadcastReceiver anonymousClass1 = new BroadcastReceiver() { // from class: com.google.android.libraries.directboot.DirectBootUtils.1
                    final /* synthetic */ CallbackToFutureAdapter$Completer val$completer;
                    final /* synthetic */ AtomicBoolean val$invoked;
                    final /* synthetic */ Runnable val$runnable;

                    public AnonymousClass1(final AtomicBoolean atomicBoolean2, final Runnable r12, final CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer2) {
                        r1 = atomicBoolean2;
                        r2 = r12;
                        r3 = callbackToFutureAdapter$Completer2;
                    }

                    @Override // android.content.BroadcastReceiver
                    public final void onReceive(Context context, Intent intent) {
                        if (r1.compareAndSet(false, true)) {
                            context.unregisterReceiver(this);
                            r2.run();
                            r3.set$ar$ds(null);
                        }
                    }
                };
                Context context = (Context) obj;
                context.registerReceiver(anonymousClass1, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                if (DirectBootUtils.isUserUnlocked(context) && atomicBoolean2.compareAndSet(false, true)) {
                    context.unregisterReceiver(anonymousClass1);
                    r12.run();
                    callbackToFutureAdapter$Completer2.set$ar$ds(null);
                    return "DirectBootUtils.runWhenUnlocked";
                }
                callbackToFutureAdapter$Completer2.addCancellationListener(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0((Object) atomicBoolean2, obj, (Object) anonymousClass1, 15, (byte[]) null), DirectExecutor.INSTANCE);
                return "DirectBootUtils.runWhenUnlocked";
            }
            AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
            callbackToFutureAdapter$Completer2.addCancellationListener(new WorkerKt$$ExternalSyntheticLambda0(atomicBoolean2, i2), androidx.work.DirectExecutor.INSTANCE);
            this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$1.execute(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(atomicBoolean2, callbackToFutureAdapter$Completer2, (Function0) this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$0, 5));
            return Unit.INSTANCE;
        }
        IPrepareInferenceEngineCallback.Stub stub = new IPrepareInferenceEngineCallback.Stub(callbackToFutureAdapter$Completer2);
        Object obj2 = this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$1;
        Object obj3 = this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$0;
        try {
            callbackToFutureAdapter$Completer2.addCancellationListener(new TrainingActivity$$ExternalSyntheticLambda1(((AiCoreBaseService) obj3).prepareInferenceEngineInternal(((AiCoreBaseService.InferenceServiceContext) obj2).getService(), stub), 6), ((AiCoreBaseService) obj3).workerExecutor);
            return "prepareInferenceEngineFuture";
        } catch (RemoteException e) {
            callbackToFutureAdapter$Completer2.setException$ar$ds(new AiCoreException(3, 6, "Failed to prepare inference engine", e));
            return null;
        } catch (RuntimeException e2) {
            callbackToFutureAdapter$Completer2.setException$ar$ds(new AiCoreException(3, 0, "Failed to prepare inference engine", e2));
            return null;
        }
    }

    public /* synthetic */ AiCoreBaseService$$ExternalSyntheticLambda4(Executor executor, Function0 function0, int i) {
        this.switching_field = i;
        this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$1 = executor;
        this.AiCoreBaseService$$ExternalSyntheticLambda4$ar$f$0 = function0;
    }
}

package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3;
import androidx.room.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import androidx.work.impl.constraints.controllers.BaseConstraintController;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlaysAnimations;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import com.google.mlkit.logging.schema.SmartReplyGeneratorOptions;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProducerCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
final class NetworkRequestConstraintController$track$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Constraints $constraints;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NetworkRequestConstraintController this$0;

    /* compiled from: PG */
    /* renamed from: androidx.work.impl.constraints.NetworkRequestConstraintController$track$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends Lambda implements Function0 {
        final /* synthetic */ Object NetworkRequestConstraintController$track$1$1$ar$$networkCallback;
        final /* synthetic */ Object NetworkRequestConstraintController$track$1$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Object obj, Object obj2, int i) {
            super(0);
            this.switching_field = i;
            this.NetworkRequestConstraintController$track$1$1$ar$this$0 = obj;
            this.NetworkRequestConstraintController$track$1$1$ar$$networkCallback = obj2;
        }

        @Override // kotlin.jvm.functions.Function0
        public final /* synthetic */ Object invoke() {
            int i = this.switching_field;
            if (i != 0) {
                byte[] bArr = null;
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            ((ControlOverlaysAnimations) this.NetworkRequestConstraintController$track$1$1$ar$this$0).triggerButtonAppearanceUpdater.invoke(false);
                            ((ControlOverlaysAnimations) this.NetworkRequestConstraintController$track$1$1$ar$this$0).foregroundUpdater.invoke(this.NetworkRequestConstraintController$track$1$1$ar$$networkCallback);
                            return Unit.INSTANCE;
                        }
                        Object obj = this.NetworkRequestConstraintController$track$1$1$ar$$networkCallback;
                        WorkDatabase workDatabase = ((WorkManagerImpl) obj).mWorkDatabase;
                        workDatabase.getClass();
                        DelayedWorkTracker.AnonymousClass1 anonymousClass1 = new DelayedWorkTracker.AnonymousClass1(obj, this.NetworkRequestConstraintController$track$1$1$ar$this$0, 5, bArr);
                        workDatabase.beginTransaction();
                        try {
                            anonymousClass1.run();
                            workDatabase.setTransactionSuccessful();
                            workDatabase.endTransaction();
                            WorkManagerImpl workManagerImpl = (WorkManagerImpl) this.NetworkRequestConstraintController$track$1$1$ar$$networkCallback;
                            Schedulers.schedule(workManagerImpl.mConfiguration, workManagerImpl.mWorkDatabase, workManagerImpl.mSchedulers);
                            return Unit.INSTANCE;
                        } catch (Throwable th) {
                            workDatabase.endTransaction();
                            throw th;
                        }
                    }
                    Object obj2 = this.NetworkRequestConstraintController$track$1$1$ar$$networkCallback;
                    ConstraintTracker constraintTracker = ((BaseConstraintController) this.NetworkRequestConstraintController$track$1$1$ar$this$0).tracker;
                    synchronized (constraintTracker.lock) {
                        if (constraintTracker.listeners.remove(obj2) && constraintTracker.listeners.isEmpty()) {
                            constraintTracker.stopTracking();
                        }
                    }
                    return Unit.INSTANCE;
                }
                Object obj3 = this.NetworkRequestConstraintController$track$1$1$ar$$networkCallback;
                DefaultSpecialEffectsController$TransitionEffect defaultSpecialEffectsController$TransitionEffect = (DefaultSpecialEffectsController$TransitionEffect) obj3;
                Object obj4 = defaultSpecialEffectsController$TransitionEffect.controller;
                obj4.getClass();
                defaultSpecialEffectsController$TransitionEffect.transitionImpl.animateToStart(obj4, new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(obj3, this.NetworkRequestConstraintController$track$1$1$ar$this$0, 3, (byte[]) null));
                return Unit.INSTANCE;
            }
            Logger.get$ar$ds$16341a92_0();
            long j = WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs;
            ((NetworkRequestConstraintController) this.NetworkRequestConstraintController$track$1$1$ar$this$0).connManager.unregisterNetworkCallback((ConnectivityManager.NetworkCallback) this.NetworkRequestConstraintController$track$1$1$ar$$networkCallback);
            return Unit.INSTANCE;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Object obj, Object obj2, int i, byte[] bArr) {
            super(0);
            this.switching_field = i;
            this.NetworkRequestConstraintController$track$1$1$ar$$networkCallback = obj;
            this.NetworkRequestConstraintController$track$1$1$ar$this$0 = obj2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRequestConstraintController$track$1(Constraints constraints, NetworkRequestConstraintController networkRequestConstraintController, Continuation continuation) {
        super(2, continuation);
        this.$constraints = constraints;
        this.this$0 = networkRequestConstraintController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NetworkRequestConstraintController$track$1 networkRequestConstraintController$track$1 = new NetworkRequestConstraintController$track$1(this.$constraints, this.this$0, continuation);
        networkRequestConstraintController$track$1.L$0 = obj;
        return networkRequestConstraintController$track$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((NetworkRequestConstraintController$track$1) create((ProducerCoroutine) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            final ProducerCoroutine producerCoroutine = (ProducerCoroutine) this.L$0;
            NetworkRequest requiredNetworkRequest = this.$constraints.getRequiredNetworkRequest();
            if (requiredNetworkRequest == null) {
                producerCoroutine.getChannel().close(null);
                return Unit.INSTANCE;
            }
            final Job launch$default$ar$ds$ar$edu = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(producerCoroutine, null, 0, new TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(this.this$0, producerCoroutine, (Continuation) null, 2), 3);
            ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: androidx.work.impl.constraints.NetworkRequestConstraintController$track$1$networkCallback$1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    network.getClass();
                    networkCapabilities.getClass();
                    Job.this.cancel(null);
                    Logger.get$ar$ds$16341a92_0();
                    long j = WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs;
                    producerCoroutine.mo257trySendJP2dKIU(ConstraintsState$ConstraintsMet.INSTANCE);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public final void onLost(Network network) {
                    network.getClass();
                    Job.this.cancel(null);
                    Logger.get$ar$ds$16341a92_0();
                    long j = WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs;
                    producerCoroutine.mo257trySendJP2dKIU(new ConstraintsState$ConstraintsNotMet(7));
                }
            };
            Logger.get$ar$ds$16341a92_0();
            long j = WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs;
            this.this$0.connManager.registerNetworkCallback(requiredNetworkRequest, networkCallback);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, networkCallback, 0);
            this.label = 1;
            if (SmartReplyGeneratorOptions.awaitClose$ar$class_merging(producerCoroutine, anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return Unit.INSTANCE;
    }
}

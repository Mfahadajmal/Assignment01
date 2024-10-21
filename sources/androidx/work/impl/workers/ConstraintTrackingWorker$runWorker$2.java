package androidx.work.impl.workers;

import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.GridLayoutManager;
import androidx.lifecycle.ViewModelStore;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintTrackingWorker$runWorker$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ ListenableWorker $delegate;
    final /* synthetic */ ViewModelStore $workConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging;
    final /* synthetic */ WorkSpec $workSpec;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker$runWorker$2(ListenableWorker listenableWorker, ViewModelStore viewModelStore, WorkSpec workSpec, Continuation continuation) {
        super(2, continuation);
        this.$delegate = listenableWorker;
        this.$workConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging = viewModelStore;
        this.$workSpec = workSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ConstraintTrackingWorker$runWorker$2 constraintTrackingWorker$runWorker$2 = new ConstraintTrackingWorker$runWorker$2(this.$delegate, this.$workConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging, this.$workSpec, continuation);
        constraintTrackingWorker$runWorker$2.L$0 = obj;
        return constraintTrackingWorker$runWorker$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((ConstraintTrackingWorker$runWorker$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.coroutines.Job, java.lang.Object, kotlin.coroutines.intrinsics.CoroutineSingletons] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.google.common.util.concurrent.ListenableFuture] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Throwable th;
        AtomicInteger atomicInteger;
        ?? r1;
        CancellationException e;
        Job job;
        ?? r0 = CoroutineSingletons.COROUTINE_SUSPENDED;
        try {
            if (this.label != 0) {
                ?? r02 = this.L$2;
                r1 = this.L$1;
                atomicInteger = (AtomicInteger) this.L$0;
                try {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    job = r02;
                    r1 = r1;
                } catch (CancellationException e2) {
                    e = e2;
                    String str = ConstraintTrackingWorkerKt.TAG;
                    ListenableWorker listenableWorker = this.$delegate;
                    Logger.get$ar$ds$16341a92_0();
                    Objects.toString(listenableWorker.getClass());
                    int i = atomicInteger.get();
                    if (!r1.isCancelled()) {
                    }
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    String str2 = ConstraintTrackingWorkerKt.TAG;
                    ListenableWorker listenableWorker2 = this.$delegate;
                    Logger.get$ar$ds$16341a92_0();
                    Objects.toString(listenableWorker2.getClass());
                    throw th;
                }
            } else {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                AtomicInteger atomicInteger2 = new AtomicInteger(-256);
                ListenableWorker listenableWorker3 = this.$delegate;
                ViewModelStore viewModelStore = this.$workConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging;
                WorkSpec workSpec = this.$workSpec;
                ListenableFuture startWork = listenableWorker3.startWork();
                Job launch$default$ar$ds$ar$edu = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(coroutineScope, null, 0, new ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1(viewModelStore, workSpec, atomicInteger2, startWork, (Continuation) null, 0), 3);
                try {
                    this.L$0 = atomicInteger2;
                    this.L$1 = startWork;
                    this.L$2 = launch$default$ar$ds$ar$edu;
                    this.label = 1;
                    Object await = GridLayoutManager.Api21Impl.await(startWork, this);
                    if (await != r0) {
                        job = launch$default$ar$ds$ar$edu;
                        obj = await;
                        atomicInteger = atomicInteger2;
                        r1 = startWork;
                    } else {
                        return r0;
                    }
                } catch (CancellationException e3) {
                    atomicInteger = atomicInteger2;
                    r1 = startWork;
                    e = e3;
                    String str3 = ConstraintTrackingWorkerKt.TAG;
                    ListenableWorker listenableWorker4 = this.$delegate;
                    Logger.get$ar$ds$16341a92_0();
                    Objects.toString(listenableWorker4.getClass());
                    int i2 = atomicInteger.get();
                    if (!r1.isCancelled() && i2 != -256) {
                        throw new ConstraintTrackingWorker.ConstraintUnsatisfiedException(atomicInteger.get());
                    }
                    throw e;
                } catch (Throwable th3) {
                    th = th3;
                    String str22 = ConstraintTrackingWorkerKt.TAG;
                    ListenableWorker listenableWorker22 = this.$delegate;
                    Logger.get$ar$ds$16341a92_0();
                    Objects.toString(listenableWorker22.getClass());
                    throw th;
                }
            }
            AppCompatDelegateImpl.Api33Impl api33Impl = (AppCompatDelegateImpl.Api33Impl) obj;
            job.cancel(null);
            return api33Impl;
        } catch (Throwable th4) {
            r0.cancel(null);
            throw th4;
        }
    }
}

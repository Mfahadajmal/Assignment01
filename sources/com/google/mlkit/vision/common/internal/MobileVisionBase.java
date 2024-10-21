package com.google.mlkit.vision.common.internal;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils$$ExternalSyntheticLambda1;
import com.google.mlkit.vision.common.InputImage;
import java.io.Closeable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MobileVisionBase<DetectionResultT> implements Closeable, LifecycleObserver {
    public static final GmsLogger GMS_LOGGER = new GmsLogger("MobileVisionBase", "");
    private final AppLifecycleMonitor cancellationTokenSource$ar$class_merging$ar$class_merging$ar$class_merging;
    public final MLTask detectorTaskWithResource;
    private final Executor executor;
    private final AtomicBoolean isClosed = new AtomicBoolean(false);

    public MobileVisionBase(MLTask mLTask, Executor executor) {
        this.detectorTaskWithResource = mLTask;
        AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor((byte[]) null, (char[]) null);
        this.cancellationTokenSource$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.executor = executor;
        mLTask.refCount.incrementAndGet();
        mLTask.callAfterLoad$ar$class_merging$ar$class_merging(executor, new TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0(5), (AppLifecycleMonitor) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).addOnFailureListener$ar$ds(new OptionalModuleUtils$$ExternalSyntheticLambda1(2));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public synchronized void close() {
        boolean z = true;
        if (!this.isClosed.getAndSet(true)) {
            this.cancellationTokenSource$ar$class_merging$ar$class_merging$ar$class_merging.cancel();
            MLTask mLTask = this.detectorTaskWithResource;
            Executor executor = this.executor;
            if (mLTask.refCount.get() <= 0) {
                z = false;
            }
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(z);
            mLTask.taskQueue.submit(executor, new EventBus$$ExternalSyntheticLambda0(mLTask, new AppLifecycleMonitor((short[]) null), 18));
        }
    }

    public final synchronized Task processBase(InputImage inputImage) {
        if (!this.isClosed.get()) {
            if (inputImage.width >= 32 && inputImage.height >= 32) {
                return this.detectorTaskWithResource.callAfterLoad$ar$class_merging$ar$class_merging(this.executor, new ImageDescriptionProcessor$$ExternalSyntheticLambda0(this, inputImage, 4), (AppLifecycleMonitor) this.cancellationTokenSource$ar$class_merging$ar$class_merging$ar$class_merging.AppLifecycleMonitor$ar$tracker);
            }
            return SpannableUtils$NonCopyableTextSpan.forException(new MlKitException("InputImage width and height should be at least 32!", 3));
        }
        return SpannableUtils$NonCopyableTextSpan.forException(new MlKitException("This detector is already closed!", 14));
    }
}

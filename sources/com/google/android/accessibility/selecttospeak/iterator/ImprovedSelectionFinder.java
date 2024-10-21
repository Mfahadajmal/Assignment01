package com.google.android.accessibility.selecttospeak.iterator;

import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.room.TriggerBasedInvalidationTracker$onAllowRefresh$1;
import com.google.android.accessibility.utils.ocr.OcrController;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcherImpl;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImprovedSelectionFinder {
    public final CoroutineDispatcher dispatcher;
    public final WindowTrackerFactory factory$ar$class_merging$b4f5a501_0$ar$class_merging$ar$class_merging$ar$class_merging;
    public Job getSelectionJob;
    public final OptionalMethod legacySelectionFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public volatile SentenceIterator ocrIterator;
    public final LifecycleCoroutineScope scope;
    public Job screenshotJob;

    public ImprovedSelectionFinder(LifecycleCoroutineScope lifecycleCoroutineScope) {
        byte[] bArr = null;
        WindowTrackerFactory windowTrackerFactory = new WindowTrackerFactory(bArr, bArr, bArr);
        OptionalMethod optionalMethod = new OptionalMethod(TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$a9b4be51_0, (OcrController.OcrListener) null);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.getClass();
        ExecutorCoroutineDispatcherImpl executorCoroutineDispatcherImpl = new ExecutorCoroutineDispatcherImpl(newSingleThreadExecutor);
        this.scope = lifecycleCoroutineScope;
        this.factory$ar$class_merging$b4f5a501_0$ar$class_merging$ar$class_merging$ar$class_merging = windowTrackerFactory;
        this.legacySelectionFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = optionalMethod;
        this.dispatcher = executorCoroutineDispatcherImpl;
    }
}

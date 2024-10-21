package com.google.android.accessibility.selecttospeak.iterator;

import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImprovedSelectionFinder$takeScreenShot$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function0 $onCaptureEnd;
    final /* synthetic */ Function0 $onCaptureStart;
    final /* synthetic */ SelectToSpeakService $service;
    final /* synthetic */ Function1 $takeScreenShot;
    Object L$0;
    int label;
    final /* synthetic */ ImprovedSelectionFinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImprovedSelectionFinder$takeScreenShot$2(Function0 function0, Function0 function02, ImprovedSelectionFinder improvedSelectionFinder, SelectToSpeakService selectToSpeakService, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$onCaptureStart = function0;
        this.$onCaptureEnd = function02;
        this.this$0 = improvedSelectionFinder;
        this.$service = selectToSpeakService;
        this.$takeScreenShot = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ImprovedSelectionFinder$takeScreenShot$2(this.$onCaptureStart, this.$onCaptureEnd, this.this$0, this.$service, this.$takeScreenShot, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((ImprovedSelectionFinder$takeScreenShot$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r5.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L16
            if (r1 == r3) goto L10
            java.lang.Object r0 = r5.L$0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            goto L5e
        L10:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L14 com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException -> L41 java.util.concurrent.CancellationException -> L44
            goto L32
        L14:
            r6 = move-exception
            goto L3b
        L16:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            kotlin.jvm.functions.Function0 r6 = r5.$onCaptureStart
            r6.invoke()
            com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyData$1 r6 = new com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyData$1     // Catch: java.lang.Throwable -> L14 com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException -> L41 java.util.concurrent.CancellationException -> L44
            com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder r1 = r5.this$0     // Catch: java.lang.Throwable -> L14 com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException -> L41 java.util.concurrent.CancellationException -> L44
            kotlin.jvm.functions.Function1 r4 = r5.$takeScreenShot     // Catch: java.lang.Throwable -> L14 com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException -> L41 java.util.concurrent.CancellationException -> L44
            r6.<init>(r1, r4, r2, r3)     // Catch: java.lang.Throwable -> L14 com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException -> L41 java.util.concurrent.CancellationException -> L44
            r5.label = r3     // Catch: java.lang.Throwable -> L14 com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException -> L41 java.util.concurrent.CancellationException -> L44
            r3 = 2000(0x7d0, double:9.88E-321)
            java.lang.Object r6 = com.google.mlkit.logging.schema.ScannerAutoZoomEvent.withTimeoutOrNull(r3, r6, r5)     // Catch: java.lang.Throwable -> L14 com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException -> L41 java.util.concurrent.CancellationException -> L44
            if (r6 != r0) goto L32
            goto L65
        L32:
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6     // Catch: java.lang.Throwable -> L14 com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException -> L41 java.util.concurrent.CancellationException -> L44
            kotlin.jvm.functions.Function0 r1 = r5.$onCaptureEnd
            r1.invoke()
            r2 = r6
            goto L49
        L3b:
            kotlin.jvm.functions.Function0 r0 = r5.$onCaptureEnd
            r0.invoke()
            throw r6
        L41:
            kotlin.jvm.functions.Function0 r6 = r5.$onCaptureEnd
            goto L46
        L44:
            kotlin.jvm.functions.Function0 r6 = r5.$onCaptureEnd
        L46:
            r6.invoke()
        L49:
            if (r2 == 0) goto L66
            com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder r6 = r5.this$0
            com.google.android.accessibility.selecttospeak.SelectToSpeakService r1 = r5.$service
            r5.L$0 = r6
            r3 = 2
            r5.label = r3
            com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory r3 = r6.factory$ar$class_merging$b4f5a501_0$ar$class_merging$ar$class_merging$ar$class_merging
            java.lang.Object r1 = r3.createIterator(r1, r2, r5)
            if (r1 == r0) goto L65
            r0 = r6
            r6 = r1
        L5e:
            com.google.android.accessibility.selecttospeak.iterator.SentenceIterator r6 = (com.google.android.accessibility.selecttospeak.iterator.SentenceIterator) r6
            com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder r0 = (com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder) r0
            r0.ocrIterator = r6
            goto L66
        L65:
            return r0
        L66:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder$takeScreenShot$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

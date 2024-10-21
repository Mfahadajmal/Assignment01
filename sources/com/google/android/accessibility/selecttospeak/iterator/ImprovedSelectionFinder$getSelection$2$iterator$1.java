package com.google.android.accessibility.selecttospeak.iterator;

import android.graphics.Rect;
import com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$scheduleAutoCollapse$1;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ImprovedSelectionFinder$getSelection$2$iterator$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Rect $boundInScreen;
    final /* synthetic */ boolean $isContinuousReading;
    final /* synthetic */ List $windows;
    int label;
    final /* synthetic */ ImprovedSelectionFinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImprovedSelectionFinder$getSelection$2$iterator$1(ImprovedSelectionFinder improvedSelectionFinder, Rect rect, boolean z, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = improvedSelectionFinder;
        this.$boundInScreen = rect;
        this.$isContinuousReading = z;
        this.$windows = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ImprovedSelectionFinder$getSelection$2$iterator$1(this.this$0, this.$boundInScreen, this.$isContinuousReading, this.$windows, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((ImprovedSelectionFinder$getSelection$2$iterator$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SentenceIterator sentenceIterator;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            UIDelayedJobScheduler$scheduleAutoCollapse$1 uIDelayedJobScheduler$scheduleAutoCollapse$1 = new UIDelayedJobScheduler$scheduleAutoCollapse$1(this.this$0, (Continuation) null, 1);
            this.label = 1;
            if (ScannerAutoZoomEvent.withTimeoutOrNull(1000L, uIDelayedJobScheduler$scheduleAutoCollapse$1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        if (this.this$0.ocrIterator != null) {
            SentenceIterator sentenceIterator2 = this.this$0.ocrIterator;
            sentenceIterator2.getClass();
            Rect rect = this.$boundInScreen;
            if (this.$isContinuousReading) {
                sentenceIterator = new SentenceIterator(sentenceIterator2.list, sentenceIterator2.characterCount);
            } else {
                ArrayList arrayList = new ArrayList();
                sentenceIterator2.reset();
                rect.sort();
                int i = 0;
                while (sentenceIterator2.hasNext()) {
                    Sentence next = sentenceIterator2.next();
                    Iterator it = Sentence.bounds$default$ar$ds(next).iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Rect rect2 = (Rect) it.next();
                            rect2.sort();
                            if (Rect.intersects(rect2, rect)) {
                                arrayList.add(next);
                                i += next.text.length();
                                break;
                            }
                        }
                    }
                }
                sentenceIterator2.reset();
                sentenceIterator = new SentenceIterator(arrayList, i);
            }
            if (sentenceIterator.hasNext()) {
                return sentenceIterator;
            }
            ImprovedSelectionFinder improvedSelectionFinder = this.this$0;
            return improvedSelectionFinder.legacySelectionFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getSelected(null, this.$boundInScreen, this.$isContinuousReading, this.$windows);
        }
        Job job = this.this$0.screenshotJob;
        if (job != null) {
            job.cancel(null);
        }
        ImprovedSelectionFinder improvedSelectionFinder2 = this.this$0;
        return improvedSelectionFinder2.legacySelectionFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getSelected(null, this.$boundInScreen, this.$isContinuousReading, this.$windows);
    }
}

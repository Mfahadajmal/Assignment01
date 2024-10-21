package com.google.android.accessibility.selecttospeak.iterator;

import android.graphics.Rect;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationLoadLogEvent;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImprovedSelectionFinder$getSelection$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Rect $boundInScreen;
    final /* synthetic */ boolean $isContinuousReading;
    final /* synthetic */ Function1 $onFinished;
    final /* synthetic */ List $windows;
    int label;
    final /* synthetic */ ImprovedSelectionFinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImprovedSelectionFinder$getSelection$2(ImprovedSelectionFinder improvedSelectionFinder, Function1 function1, Rect rect, boolean z, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = improvedSelectionFinder;
        this.$onFinished = function1;
        this.$boundInScreen = rect;
        this.$isContinuousReading = z;
        this.$windows = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ImprovedSelectionFinder$getSelection$2(this.this$0, this.$onFinished, this.$boundInScreen, this.$isContinuousReading, this.$windows, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((ImprovedSelectionFinder$getSelection$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            ImprovedSelectionFinder improvedSelectionFinder = this.this$0;
            ImprovedSelectionFinder$getSelection$2$iterator$1 improvedSelectionFinder$getSelection$2$iterator$1 = new ImprovedSelectionFinder$getSelection$2$iterator$1(improvedSelectionFinder, this.$boundInScreen, this.$isContinuousReading, this.$windows, null);
            this.label = 1;
            obj = OnDeviceSubjectSegmentationLoadLogEvent.withContext(improvedSelectionFinder.dispatcher, improvedSelectionFinder$getSelection$2$iterator$1, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        this.$onFinished.invoke((SentenceIterator) obj);
        return Unit.INSTANCE;
    }
}

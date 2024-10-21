package com.google.android.accessibility.selecttospeak.overlayui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.room.SharedSQLiteStatement$stmt$2;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.iterator.Paragraph;
import com.google.android.accessibility.selecttospeak.iterator.Sentence;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIterator;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIteratorFactory;
import com.google.android.accessibility.selecttospeak.iterator.TextFinderFromTextView;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.text.Text$TextBlock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChainAnimatorCoroutineImpl$chainAnimations$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators;
    final /* synthetic */ Object ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd;
    final /* synthetic */ Object ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChainAnimatorCoroutineImpl$chainAnimations$1(SelectToSpeakService selectToSpeakService, WindowTrackerFactory windowTrackerFactory, Bitmap bitmap, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators = selectToSpeakService;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd = windowTrackerFactory;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0 = bitmap;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                Object obj2 = this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators;
                return new ChainAnimatorCoroutineImpl$chainAnimations$1((MetricsLogger) obj2, (Animator) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0, (Function0) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd, continuation, 2);
            }
            Object obj3 = this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators;
            return new ChainAnimatorCoroutineImpl$chainAnimations$1((SelectToSpeakService) obj3, (WindowTrackerFactory) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd, (Bitmap) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0, continuation, 1);
        }
        return new ChainAnimatorCoroutineImpl$chainAnimations$1((MetricsLogger) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0, (Function1) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd, (List) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators, continuation, 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return ((ChainAnimatorCoroutineImpl$chainAnimations$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }
            return ((ChainAnimatorCoroutineImpl$chainAnimations$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((ChainAnimatorCoroutineImpl$chainAnimations$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Paragraph paragraph;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                } else {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    Object obj2 = this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0;
                    ?? r3 = this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd;
                    this.label = 1;
                    if (MetricsLogger.start$ar$ds((Animator) obj2, r3, null, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
                return Unit.INSTANCE;
            }
            CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            try {
                try {
                    if (this.label != 0) {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    } else {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                        Object obj3 = this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators;
                        synchronized (MlKitContext.lock) {
                            if (MlKitContext.instance == null) {
                                MlKitContext.initialize$ar$ds$b6efebf2_0((Context) obj3);
                            }
                        }
                        Object obj4 = ((WindowTrackerFactory) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd).WindowTrackerFactory$ar$handlerProvider;
                        Object obj5 = this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0;
                        this.label = 1;
                        obj = ((ConsentRetrieverImpl.CollectionBasisResolverHolders) obj4).run((Bitmap) obj5, this);
                        if (obj == coroutineSingletons2) {
                            return coroutineSingletons2;
                        }
                    }
                    MultiFlavorDetectorCreator multiFlavorDetectorCreator = (MultiFlavorDetectorCreator) obj;
                    if (multiFlavorDetectorCreator == null) {
                        return null;
                    }
                    SentenceIteratorFactory.CharacterCounter characterCounter = new SentenceIteratorFactory.CharacterCounter((char[]) null);
                    ArrayList arrayList = new ArrayList();
                    Paragraph paragraph2 = null;
                    for (Text$TextBlock text$TextBlock : multiFlavorDetectorCreator.getTextBlocks()) {
                        text$TextBlock.getClass();
                        List generateSentences$ar$ds = SentenceIteratorFactory.generateSentences$ar$ds(OnDeviceStainRemovalLogEvent.replace$default$ar$ds(text$TextBlock.getTextInternal(), "\n", " "), characterCounter, new SharedSQLiteStatement$stmt$2(new TextFinderFromTextView(text$TextBlock, 1), 7));
                        Sentence sentence = (Sentence) OnDeviceLanguageIdentificationLogEvent.firstOrNull(generateSentences$ar$ds);
                        if (sentence != null) {
                            paragraph = sentence.paragraph;
                        } else {
                            paragraph = null;
                        }
                        arrayList.addAll(generateSentences$ar$ds);
                        if (paragraph2 != null) {
                            paragraph2.next = paragraph;
                        }
                        if (paragraph != null) {
                            paragraph.prev = paragraph2;
                        }
                        paragraph2 = paragraph;
                    }
                    return new SentenceIterator(arrayList, characterCounter.characterCount);
                } catch (IllegalStateException e) {
                    LogUtils.e("OcrIteratorFactoryImpl", "Failed initialize ocr.", e);
                    return null;
                }
            } catch (CancellationException e2) {
                LogUtils.e("OcrIteratorFactoryImpl", "Ocr failed to process.", e2);
                return null;
            }
        }
        CoroutineSingletons coroutineSingletons3 = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially((List<Animator>) this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators);
            ?? r32 = this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd;
            this.label = 1;
            if (MetricsLogger.start$ar$ds(animatorSet, null, r32, this) == coroutineSingletons3) {
                return coroutineSingletons3;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChainAnimatorCoroutineImpl$chainAnimations$1(MetricsLogger metricsLogger, Animator animator, Function0 function0, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators = metricsLogger;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0 = animator;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChainAnimatorCoroutineImpl$chainAnimations$1(MetricsLogger metricsLogger, Function1 function1, List list, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$this$0 = metricsLogger;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$onEnd = function1;
        this.ChainAnimatorCoroutineImpl$chainAnimations$1$ar$$animators = list;
    }
}

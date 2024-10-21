package com.google.android.accessibility.selecttospeak.logging;

import android.content.Context;
import androidx.navigation.ActivityNavigator$hostActivity$1;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService$$ExternalSyntheticLambda3;
import com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder;
import com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay;
import com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler;
import com.google.android.accessibility.talkback.HatsSurveyRequester;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.surveys.SurveyData;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import googledata.experiments.mobile.accessibility_suite.features.S2sHatsSurveyConfig;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sHatsSurveyRequester$requestSurveyData$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object S2sHatsSurveyRequester$requestSurveyData$1$ar$$context;
    final /* synthetic */ Object S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S2sHatsSurveyRequester$requestSurveyData$1(Context context, Function1 function1, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$context = context;
        this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish = function1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return new S2sHatsSurveyRequester$requestSurveyData$1((UIDelayedJobScheduler) this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish, (SelectToSpeakOverlay) this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$context, continuation, 2);
            }
            return new S2sHatsSurveyRequester$requestSurveyData$1((ImprovedSelectionFinder) this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$context, (Function1) this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish, continuation, 1);
        }
        return new S2sHatsSurveyRequester$requestSurveyData$1((Context) this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$context, (Function1) this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish, continuation, 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return ((S2sHatsSurveyRequester$requestSurveyData$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }
            return ((S2sHatsSurveyRequester$requestSurveyData$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((S2sHatsSurveyRequester$requestSurveyData$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                } else {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    Object obj2 = this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish;
                    ?? r2 = this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$context;
                    this.label = 1;
                    if (((UIDelayedJobScheduler) obj2).hideAllExcept(r2, 30L, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
                return Unit.INSTANCE;
            }
            CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            } else {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                ?? r6 = this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish;
                this.label = 1;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(this), 1);
                cancellableContinuationImpl.initCancellability();
                r6.invoke(new SelectToSpeakService$$ExternalSyntheticLambda3(cancellableContinuationImpl, 2));
                obj = cancellableContinuationImpl.getResult();
                if (obj == coroutineSingletons2) {
                    return coroutineSingletons2;
                }
            }
            return obj;
        }
        CoroutineSingletons coroutineSingletons3 = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            S2sHatsSurveyRequester s2sHatsSurveyRequester = S2sHatsSurveyRequester.INSTANCE;
            Object obj3 = this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$context;
            this.label = 1;
            CancellableContinuationImpl cancellableContinuationImpl2 = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(this), 1);
            cancellableContinuationImpl2.initCancellability();
            cancellableContinuationImpl2.invokeOnCancellation(ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$3cb683b7_0);
            Context context = (Context) obj3;
            SurveyRequest.Builder builder = new SurveyRequest.Builder(context, S2sHatsSurveyConfig.INSTANCE.get().triggerId(context));
            builder.requestSurveyCallback = new HatsSurveyRequester.AnonymousClass1(cancellableContinuationImpl2, 1);
            builder.apiKey = S2sHatsSurveyConfig.INSTANCE.get().apiKey(context);
            builder.enableProofMode = S2sHatsSurveyConfig.INSTANCE.get().enableProofMode(context);
            SurveyRequest build = builder.build();
            S2sHatsSurveyRequester.getSurveysClient$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(context);
            JankMetricService.requestSurvey$ar$ds(build);
            obj = cancellableContinuationImpl2.getResult();
            if (obj == coroutineSingletons3) {
                return coroutineSingletons3;
            }
        }
        this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish.invoke((SurveyData) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S2sHatsSurveyRequester$requestSurveyData$1(ImprovedSelectionFinder improvedSelectionFinder, Function1 function1, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$context = improvedSelectionFinder;
        this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S2sHatsSurveyRequester$requestSurveyData$1(UIDelayedJobScheduler uIDelayedJobScheduler, SelectToSpeakOverlay selectToSpeakOverlay, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$onFinish = uIDelayedJobScheduler;
        this.S2sHatsSurveyRequester$requestSurveyData$1$ar$$context = selectToSpeakOverlay;
    }
}

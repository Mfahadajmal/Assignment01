package com.google.android.accessibility.selecttospeak.logging;

import android.content.Context;
import androidx.lifecycle.LifecycleCoroutineScope;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc$$ExternalSyntheticLambda0;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.lang.ref.WeakReference;
import kotlin.coroutines.CombinedContext$toString$1;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sHatsSurveyRequester {
    private static Job surveyRequestJob;
    public static final S2sHatsSurveyRequester INSTANCE = new S2sHatsSurveyRequester();
    public static WeakReference surveysClient = new WeakReference(null);

    private S2sHatsSurveyRequester() {
    }

    public static final void cleanUp$ar$ds() {
        Job job = surveyRequestJob;
        if (job != null) {
            job.cancel(null);
        }
        surveyRequestJob = null;
        surveysClient = new WeakReference(null);
    }

    public static final JankMetricService getSurveysClient$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(Context context) {
        JankMetricService jankMetricService = (JankMetricService) surveysClient.get();
        if (jankMetricService != null) {
            return jankMetricService;
        }
        JankMetricService createNetworkChannelBasedClient$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = JankMetricService.createNetworkChannelBasedClient$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(context, new RemoteModelManager.RemoteModelManagerRegistration(new NetworkCallerGrpc$$ExternalSyntheticLambda0(CombinedContext$toString$1.INSTANCE$ar$class_merging$4b2d14aa_0, 1)));
        surveysClient = new WeakReference(createNetworkChannelBasedClient$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        return createNetworkChannelBasedClient$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public static final void requestSurveyData(Context context, LifecycleCoroutineScope lifecycleCoroutineScope, Function1 function1) {
        context.getClass();
        Job job = surveyRequestJob;
        if (job != null && job.isActive()) {
            return;
        }
        surveyRequestJob = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(lifecycleCoroutineScope, null, 0, new S2sHatsSurveyRequester$requestSurveyData$1(context, function1, (Continuation) null, 0), 3);
    }
}

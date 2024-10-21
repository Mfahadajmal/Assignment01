package com.google.android.accessibility.talkback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.BackStackRecord;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.core.util.Pair;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda6;
import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
import com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment$$ExternalSyntheticLambda6;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.SurveyData;
import com.google.android.libraries.surveys.SurveyMetadata;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.libraries.surveys.internal.controller.SurveyControllerImpl;
import com.google.android.libraries.surveys.internal.model.SurveyDataImpl;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$LibraryEvent;
import googledata.experiments.mobile.accessibility_suite.features.DirectionalNavigationConfig;
import googledata.experiments.mobile.accessibility_suite.features.HatsSurveyConfig;
import googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfig;
import googledata.experiments.mobile.surveys_android.features.Clearcut;
import j$.util.Optional;
import java.util.ArrayList;
import kotlinx.coroutines.CancellableContinuationImpl;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import org.chromium.net.CronetEngine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsSurveyRequester {
    public final Activity activity;
    public Optional cachedSurveyData;
    public final boolean proofMode;
    public SurveyMetadata surveyMetadata;
    public final JankMetricService surveysClient$ar$class_merging$ar$class_merging$ar$class_merging;
    public final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
    public boolean surveyAvailable = false;
    public boolean surveyShown = false;
    public Optional onSurveyAvailableListener = Optional.empty();

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.HatsSurveyRequester$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements SurveyRequest.RequestSurveyCallback {
        final /* synthetic */ Object HatsSurveyRequester$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.HatsSurveyRequester$1$ar$this$0 = obj;
        }

        @Override // com.google.android.libraries.surveys.SurveyRequest.RequestSurveyCallback
        public final void onRequestFailed(String str, SurveyRequest.ErrorType errorType) {
            if (this.switching_field != 0) {
                LogUtils.e("HatsSurveyRequester", "Survey error : Failed to fetch survey (trigger id: %s, error: %s.)", str, errorType);
                ((CancellableContinuationImpl) this.HatsSurveyRequester$1$ar$this$0).resume(null, S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$4ceee00e_0);
            } else {
                HatsSurveyRequester hatsSurveyRequester = (HatsSurveyRequester) this.HatsSurveyRequester$1$ar$this$0;
                hatsSurveyRequester.surveyAvailable = false;
                hatsSurveyRequester.cachedSurveyData = Optional.empty();
                LogUtils.w("HatsSurveyRequesterImpl", "Survey error : Failed to fetch survey (trigger id: %s, error: %s.)", str, errorType);
            }
        }

        @Override // com.google.android.libraries.surveys.SurveyRequest.RequestSurveyCallback
        public final void onRequestSuccess(SurveyData surveyData) {
            if (this.switching_field != 0) {
                ((CancellableContinuationImpl) this.HatsSurveyRequester$1$ar$this$0).resume(surveyData, S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE);
                return;
            }
            HatsSurveyRequester hatsSurveyRequester = (HatsSurveyRequester) this.HatsSurveyRequester$1$ar$this$0;
            if (hatsSurveyRequester.formFactorUtils.isAndroidTv) {
                hatsSurveyRequester.presentSurvey$ar$class_merging$ar$class_merging$ar$class_merging(hatsSurveyRequester.surveysClient$ar$class_merging$ar$class_merging$ar$class_merging, surveyData);
                return;
            }
            hatsSurveyRequester.surveyAvailable = true;
            hatsSurveyRequester.cachedSurveyData = Optional.ofNullable(surveyData);
            ((HatsSurveyRequester) this.HatsSurveyRequester$1$ar$this$0).onSurveyAvailableListener.ifPresent(new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(7));
        }
    }

    public HatsSurveyRequester(Activity activity) {
        this.activity = activity;
        Context applicationContext = activity.getApplicationContext();
        this.surveysClient$ar$class_merging$ar$class_merging$ar$class_merging = JankMetricService.createNetworkChannelBasedClient$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(applicationContext, new RemoteModelManager.RemoteModelManagerRegistration(new CronetEngine.Builder(applicationContext).build()));
        this.proofMode = HatsSurveyConfig.INSTANCE.get().enableProofMode(activity.getApplicationContext());
    }

    public final void dismissSurvey() {
        String str;
        SurveyMetadata surveyMetadata = this.surveyMetadata;
        if (surveyMetadata != null) {
            Activity activity = this.activity;
            SurveyControllerImpl surveyControllerImpl = SurveyControllerImpl.instance;
            SurveyStyle surveyStyle = surveyControllerImpl.surveyDataStore.getSurveyStyle(surveyMetadata.sessionId);
            if (surveyStyle != null && !surveyStyle.equals(SurveyStyle.EMBEDDED) && !SurveyUtils.isWatchPlatform(activity)) {
                SurveyDataImpl surveyData = surveyControllerImpl.surveyDataStore.getSurveyData(surveyMetadata.sessionId);
                Stopwatch stopwatch = new Stopwatch();
                synchronized (SurveyControllerImpl.isSurveyRunning) {
                    if (surveyData == null) {
                        Log.w("SurveyController", "surveyData was null, bailing out.");
                        return;
                    }
                    if (!TextUtils.equals(surveyMetadata.triggerId, surveyData.triggerId)) {
                        Log.w("SurveyController", "Trigger IDs didn't match, bailing out.");
                        return;
                    }
                    if (!TextUtils.equals(surveyMetadata.sessionId, surveyData.getSessionId())) {
                        Log.w("SurveyController", "Session IDs didn't match, bailing out.");
                        return;
                    }
                    if (!TextUtils.equals(surveyMetadata.surveyId, surveyData.surveyId)) {
                        Log.w("SurveyController", "Survey IDs didn't match, bailing out.");
                        return;
                    }
                    String str2 = surveyControllerImpl.surveyActivityClassName;
                    FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                    Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag("com.google.android.libraries.surveys.internal.view.SystemInfoDialogFragment");
                    if (findFragmentByTag != null) {
                        new BackStackRecord(supportFragmentManager).remove(findFragmentByTag).commitAllowingStateLoss$ar$ds();
                    }
                    Fragment findFragmentByTag2 = supportFragmentManager.findFragmentByTag("com.google.android.libraries.surveys.internal.PromptDialogFragment");
                    if (findFragmentByTag2 != null) {
                        new BackStackRecord(supportFragmentManager).remove(findFragmentByTag2).commitAllowingStateLoss$ar$ds();
                    } else {
                        Intent intent = new Intent();
                        intent.setClassName(activity, str2);
                        intent.putExtra("IsDismissing", true);
                        activity.startActivity(intent);
                    }
                    if (TextUtils.isEmpty(surveyControllerImpl.accountName)) {
                        str = null;
                    } else {
                        str = surveyControllerImpl.accountName;
                    }
                    String str3 = str;
                    CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
                        MetricsLogger metricsLogger = MetricsLogger.getInstance();
                        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$LibraryEvent.DEFAULT_INSTANCE.createBuilder();
                        UserVoiceSurveysLogging$LibraryEvent.DismissSurveyCallInfo dismissSurveyCallInfo = UserVoiceSurveysLogging$LibraryEvent.DismissSurveyCallInfo.DEFAULT_INSTANCE;
                        builder.copyOnWrite();
                        UserVoiceSurveysLogging$LibraryEvent userVoiceSurveysLogging$LibraryEvent = (UserVoiceSurveysLogging$LibraryEvent) builder.instance;
                        dismissSurveyCallInfo.getClass();
                        userVoiceSurveysLogging$LibraryEvent.event_ = dismissSurveyCallInfo;
                        userVoiceSurveysLogging$LibraryEvent.eventCase_ = 5;
                        metricsLogger.reportLibraryEvent((UserVoiceSurveysLogging$LibraryEvent) builder.build(), stopwatch.getStart(), stopwatch.getElapsed(), activity, str3);
                    }
                }
            }
        }
    }

    public final void onSurveyHidden() {
        this.surveyShown = false;
    }

    public final void presentSurvey$ar$class_merging$ar$class_merging$ar$class_merging(JankMetricService jankMetricService, SurveyData surveyData) {
        PresentSurveyRequest.SurveyEventListener surveyEventListener = new PresentSurveyRequest.SurveyEventListener() { // from class: com.google.android.accessibility.talkback.HatsSurveyRequester.2
            @Override // com.google.android.libraries.surveys.PresentSurveyRequest.SurveyEventListener
            public final void onPresentSurveyFailed$ar$ds(PresentSurveyRequest.ErrorType errorType) {
                LogUtils.w("HatsSurveyRequesterImpl", "Survey onPresentSurveyFailed : %s", errorType);
                HatsSurveyRequester.this.onSurveyHidden();
            }

            @Override // com.google.android.libraries.surveys.PresentSurveyRequest.SurveyEventListener
            public final void onSurveyClosed$ar$ds() {
                if (HatsSurveyRequester.this.activity.isFinishing()) {
                    return;
                }
                Toast.makeText(HatsSurveyRequester.this.activity.getApplicationContext(), R.string.survey_closed, 0).show();
                HatsSurveyRequester.this.onSurveyHidden();
            }

            @Override // com.google.android.libraries.surveys.PresentSurveyRequest.SurveyEventListener
            public final void onSurveyPrompted(SurveyMetadata surveyMetadata) {
                HatsSurveyRequester.this.surveyMetadata = surveyMetadata;
            }
        };
        PresentSurveyRequest.Builder builder = new PresentSurveyRequest.Builder(this.activity, surveyData);
        builder.surveyEventListener = surveyEventListener;
        builder.insertIntoParent$ar$ds(R.id.survey_prompt_parent_sheet, 340);
        ArrayList arrayList = new ArrayList();
        String versionName = SpannableUtils$IdentifierSpan.getVersionName(this.activity);
        if (versionName != null) {
            arrayList.add(new Pair("talkback_version", versionName));
        }
        if (this.formFactorUtils.isAndroidTv) {
            arrayList.add(new Pair("use_handler_thread", String.valueOf(TvNavigationConfig.useHandlerThread(this.activity))));
            arrayList.add(new Pair("handler_thread_priority", String.valueOf((int) TvNavigationConfig.INSTANCE.get().handlerThreadPriority(this.activity))));
            arrayList.add(new Pair("key_event_timeout_millis", String.valueOf(TvNavigationConfig.keyEventTimeoutMillis(this.activity))));
            arrayList.add(new Pair("let_system_handle_dpad_center_when_focus_not_in_sync", String.valueOf(TvNavigationConfig.letSystemHandleDpadCenterWhenFocusNotInSyncNew(this.activity))));
            arrayList.add(new Pair("allow_focus_resting", String.valueOf(DirectionalNavigationConfig.allowFocusResting(this.activity))));
        } else {
            arrayList.add(new Pair("multi_finger_gesture", String.valueOf(FeatureSupport.isMultiFingerGestureSupported())));
        }
        builder.psd = arrayList;
        JankMetricService.presentSurvey$ar$ds(builder.build());
        this.surveyShown = true;
    }

    public final void setOnSurveyAvailableListener$ar$class_merging(TalkBackPreferenceFragment$$ExternalSyntheticLambda6 talkBackPreferenceFragment$$ExternalSyntheticLambda6) {
        Optional ofNullable = Optional.ofNullable(talkBackPreferenceFragment$$ExternalSyntheticLambda6);
        this.onSurveyAvailableListener = ofNullable;
        if (this.surveyAvailable) {
            ofNullable.ifPresent(new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(6));
        }
    }
}

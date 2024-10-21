package com.google.android.libraries.surveys.internal.network.grpc;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.libraries.surveys.internal.config.SurveyConfigProvider;
import com.google.android.libraries.surveys.internal.network.NetworkExecutor;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Event;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$HttpEvent;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$ProductContext;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SensitiveClientContext;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Session;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SurveyRecordEventRequest;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SurveyRecordEventResponse;
import com.google.scone.proto.Service$SurveyRecordEventRequest;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$ProductContext;
import com.google.scone.proto.Survey$SensitiveClientContext;
import com.google.scone.proto.Survey$Session;
import google.internal.feedback.v1.Service$GetSurveyStartupConfigRequest;
import google.internal.feedback.v1.Service$GetSurveyStartupConfigResponse;
import google.internal.feedback.v1.SurveyServiceGrpc;
import googledata.experiments.mobile.surveys_android.features.Clearcut;
import googledata.experiments.mobile.surveys_android.features.HatsV1M2Bugfixes;
import io.grpc.CallCredentials$RequestInfo;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.MetadataUtils$HeaderAttachingClientInterceptor;
import java.util.Iterator;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkCallerGrpc extends NetworkCaller {
    private final StatsStorage authProvider$ar$class_merging$ar$class_merging$ar$class_merging;
    private final ManagedChannelFactory channelFactory;
    private ManagedChannel managedChannel;
    private String zwieback;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ManagedChannelFactory {
        ManagedChannel createChannel$ar$ds(String str);
    }

    public NetworkCallerGrpc(Context context, String str, String str2, String str3, ManagedChannelFactory managedChannelFactory, StatsStorage statsStorage) {
        super(context, str, str2, str3);
        this.channelFactory = managedChannelFactory;
        this.authProvider$ar$class_merging$ar$class_merging$ar$class_merging = statsStorage;
    }

    static final Metadata.Key getKeyCookieHeader$ar$ds() {
        return Metadata.Key.of("Cookie", Metadata.ASCII_STRING_MARSHALLER);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ae, code lost:
    
        r2 = new com.google.android.gms.auth.account.data.InternalGoogleAuthServiceClient(r1);
        com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds$c11d1227_0("oauth2:https://www.googleapis.com/auth/supportcontent", "Scope cannot be null!");
        r0 = new com.google.android.gms.common.api.internal.TaskApiCall.Builder();
        r0.TaskApiCall$Builder$ar$features = new com.google.android.gms.common.Feature[]{com.google.android.gms.auth.Features.GOOGLE_AUTH_SERVICE_TOKEN};
        r0.TaskApiCall$Builder$ar$execute = new com.google.android.gms.auth.account.data.InternalGoogleAuthServiceClient$$ExternalSyntheticLambda3(r2, r6, r7);
        r0.methodKey = 1512;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00da, code lost:
    
        r0 = (android.os.Bundle) com.google.android.gms.auth.GoogleAuthUtilLight.getResultFromTask$ar$ds(r2.doWrite(r0.build()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e0, code lost:
    
        if (r0 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e2, code lost:
    
        r0 = com.google.android.gms.auth.GoogleAuthUtilLight.extractTokenDataFrom$ar$ds(r1, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e7, code lost:
    
        com.google.android.gms.auth.GoogleAuthUtilLight.logger$ar$class_merging$adff595e_0$ar$class_merging.w("Service call returned null.", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f7, code lost:
    
        throw new java.io.IOException("Service unavailable.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f8, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f9, code lost:
    
        com.google.android.gms.auth.GoogleAuthUtilLight.logger$ar$class_merging$adff595e_0$ar$class_merging.w("%s failed via GoogleAuthServiceClient, falling back to previous approach:\n%s", "token retrieval", android.util.Log.getStackTraceString(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x016d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x016a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.auth.oauth2.OAuth2Credentials getAuthCredentials() {
        /*
            Method dump skipped, instructions count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc.getAuthCredentials():com.google.auth.oauth2.OAuth2Credentials");
    }

    public final Channel getChannel(OAuth2Credentials oAuth2Credentials) {
        String str;
        StatsStorage statsStorage;
        try {
            long j = SurveyUtils.TIMEOUT_MS;
            if (TextUtils.isEmpty(this.zwieback) && (statsStorage = SurveyConfigProvider.instance.pseudonymousIdProvider$ar$class_merging$ar$class_merging$ar$class_merging) != null) {
                this.zwieback = statsStorage.getPseudonymousId();
            }
            this.managedChannel = this.channelFactory.createChannel$ar$ds(SurveyConfigProvider.instance.getSconeApiEndpoint());
            String str2 = this.zwieback;
            Metadata metadata = new Metadata();
            CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (FlagsUtil.isBugfixEnabled(HatsV1M2Bugfixes.INSTANCE.get().onlySendZwiebackWhenNoGaiaIsPresent(FlagsUtil.phenotypeContext))) {
                if (oAuth2Credentials == null && !TextUtils.isEmpty(str2)) {
                    metadata.put(getKeyCookieHeader$ar$ds(), str2);
                }
            } else {
                metadata.put(getKeyCookieHeader$ar$ds(), str2);
            }
            if (!TextUtils.isEmpty(this.apiKey)) {
                metadata.put(Metadata.Key.of("X-Goog-Api-Key", Metadata.ASCII_STRING_MARSHALLER), this.apiKey);
            }
            Context context = this.context;
            try {
                str = SurveyUtils.getCertFingerprint(context.getPackageManager().getPackageInfo(context.getPackageName(), 64));
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("SurveyUtils", "Package not found.", e);
                str = null;
            }
            if (!TextUtils.isEmpty(str)) {
                metadata.put(Metadata.Key.of("X-Android-Cert", Metadata.ASCII_STRING_MARSHALLER), str);
            }
            String packageName = this.context.getPackageName();
            if (!TextUtils.isEmpty(packageName)) {
                metadata.put(Metadata.Key.of("X-Android-Package", Metadata.ASCII_STRING_MARSHALLER), packageName);
            }
            metadata.put(Metadata.Key.of("Authority", Metadata.ASCII_STRING_MARSHALLER), SurveyConfigProvider.instance.getSconeApiEndpoint());
            return OnDeviceFaceMeshCreateLogEvent.intercept(this.managedChannel, new MetadataUtils$HeaderAttachingClientInterceptor(metadata, 0));
        } catch (Exception e2) {
            Log.e("NetworkCallerGrpc", "Could not get managed channel.", e2);
            shutdownChannel();
            return null;
        }
    }

    public final void handleSurveyRecordEventResponse$ar$ds(Service$SurveyRecordEventRequest service$SurveyRecordEventRequest, Stopwatch stopwatch) {
        String str;
        int i;
        Survey$Event.SurveyAccepted surveyAccepted;
        Survey$Event.InvitationAnswered invitationAnswered;
        Survey$Event.QuestionAnswered questionAnswered;
        Survey$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer;
        Survey$Event.QuestionAnswered.MultipleSelectAnswer multipleSelectAnswer;
        Survey$Event.QuestionAnswered.RatingAnswer ratingAnswer;
        Survey$Event.QuestionAnswered.OpenTextAnswer openTextAnswer;
        long j = SurveyUtils.TIMEOUT_MS;
        if (TextUtils.isEmpty(this.accountName)) {
            str = null;
        } else {
            str = this.accountName;
        }
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
            return;
        }
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SurveyRecordEventRequest.DEFAULT_INSTANCE.createBuilder();
        if ((service$SurveyRecordEventRequest.bitField0_ & 1) != 0) {
            Survey$Event survey$Event = service$SurveyRecordEventRequest.event_;
            if (survey$Event == null) {
                survey$Event = Survey$Event.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.DEFAULT_INSTANCE.createBuilder();
            if ((survey$Event.bitField0_ & 1) != 0) {
                Duration duration = survey$Event.timeSinceTrigger_;
                if (duration == null) {
                    duration = Duration.DEFAULT_INSTANCE;
                }
                builder2.copyOnWrite();
                UserVoiceSurveysLogging$Event userVoiceSurveysLogging$Event = (UserVoiceSurveysLogging$Event) builder2.instance;
                duration.getClass();
                userVoiceSurveysLogging$Event.timeSinceTrigger_ = duration;
                userVoiceSurveysLogging$Event.bitField0_ |= 1;
            }
            int i2 = survey$Event.eventCase_;
            if (i2 != 0) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                if (i2 != 6) {
                                    i = 0;
                                } else {
                                    i = Survey$Event.EventCase.SURVEY_CLOSED$ar$edu;
                                }
                            } else {
                                i = Survey$Event.EventCase.QUESTION_ANSWERED$ar$edu;
                            }
                        } else {
                            i = Survey$Event.EventCase.INVITATION_ANSWERED$ar$edu;
                        }
                    } else {
                        i = Survey$Event.EventCase.SURVEY_ACCEPTED$ar$edu;
                    }
                } else {
                    i = Survey$Event.EventCase.SURVEY_SHOWN$ar$edu;
                }
            } else {
                i = Survey$Event.EventCase.EVENT_NOT_SET$ar$edu;
            }
            if (i != 0) {
                int i3 = i - 1;
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (i3 != 3) {
                                if (i3 == 4) {
                                    UserVoiceSurveysLogging$Event.SurveyClosed surveyClosed = UserVoiceSurveysLogging$Event.SurveyClosed.DEFAULT_INSTANCE;
                                    builder2.copyOnWrite();
                                    UserVoiceSurveysLogging$Event userVoiceSurveysLogging$Event2 = (UserVoiceSurveysLogging$Event) builder2.instance;
                                    surveyClosed.getClass();
                                    userVoiceSurveysLogging$Event2.event_ = surveyClosed;
                                    userVoiceSurveysLogging$Event2.eventCase_ = 6;
                                }
                            } else {
                                if (survey$Event.eventCase_ == 5) {
                                    questionAnswered = (Survey$Event.QuestionAnswered) survey$Event.event_;
                                } else {
                                    questionAnswered = Survey$Event.QuestionAnswered.DEFAULT_INSTANCE;
                                }
                                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.QuestionAnswered.DEFAULT_INSTANCE.createBuilder();
                                int i4 = questionAnswered.questionOrdinal_;
                                builder3.copyOnWrite();
                                ((UserVoiceSurveysLogging$Event.QuestionAnswered) builder3.instance).questionOrdinal_ = i4;
                                int forNumber$ar$edu$f9ea4f52_0 = Survey$Event.QuestionAnswered.AnswerCase.forNumber$ar$edu$f9ea4f52_0(questionAnswered.answerCase_);
                                int i5 = forNumber$ar$edu$f9ea4f52_0 - 1;
                                if (forNumber$ar$edu$f9ea4f52_0 != 0) {
                                    if (i5 != 0) {
                                        if (i5 != 1) {
                                            if (i5 != 2) {
                                                if (i5 == 3) {
                                                    SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.QuestionAnswered.OpenTextAnswer.DEFAULT_INSTANCE.createBuilder();
                                                    if (questionAnswered.answerCase_ == 5) {
                                                        openTextAnswer = (Survey$Event.QuestionAnswered.OpenTextAnswer) questionAnswered.answer_;
                                                    } else {
                                                        openTextAnswer = Survey$Event.QuestionAnswered.OpenTextAnswer.DEFAULT_INSTANCE;
                                                    }
                                                    String str2 = openTextAnswer.answer_;
                                                    builder4.copyOnWrite();
                                                    UserVoiceSurveysLogging$Event.QuestionAnswered.OpenTextAnswer openTextAnswer2 = (UserVoiceSurveysLogging$Event.QuestionAnswered.OpenTextAnswer) builder4.instance;
                                                    str2.getClass();
                                                    openTextAnswer2.answer_ = str2;
                                                    builder3.copyOnWrite();
                                                    UserVoiceSurveysLogging$Event.QuestionAnswered questionAnswered2 = (UserVoiceSurveysLogging$Event.QuestionAnswered) builder3.instance;
                                                    UserVoiceSurveysLogging$Event.QuestionAnswered.OpenTextAnswer openTextAnswer3 = (UserVoiceSurveysLogging$Event.QuestionAnswered.OpenTextAnswer) builder4.build();
                                                    openTextAnswer3.getClass();
                                                    questionAnswered2.answer_ = openTextAnswer3;
                                                    questionAnswered2.answerCase_ = 5;
                                                }
                                            } else {
                                                if (questionAnswered.answerCase_ == 4) {
                                                    ratingAnswer = (Survey$Event.QuestionAnswered.RatingAnswer) questionAnswered.answer_;
                                                } else {
                                                    ratingAnswer = Survey$Event.QuestionAnswered.RatingAnswer.DEFAULT_INSTANCE;
                                                }
                                                SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.QuestionAnswered.RatingAnswer.DEFAULT_INSTANCE.createBuilder();
                                                if ((ratingAnswer.bitField0_ & 1) != 0) {
                                                    Survey$Event.QuestionAnswered.Selection selection = ratingAnswer.answer_;
                                                    if (selection == null) {
                                                        selection = Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE;
                                                    }
                                                    UserVoiceSurveysLogging$Event.QuestionAnswered.Selection convertSelection = DefaultExperimentTokenDecorator.convertSelection(selection);
                                                    builder5.copyOnWrite();
                                                    UserVoiceSurveysLogging$Event.QuestionAnswered.RatingAnswer ratingAnswer2 = (UserVoiceSurveysLogging$Event.QuestionAnswered.RatingAnswer) builder5.instance;
                                                    convertSelection.getClass();
                                                    ratingAnswer2.answer_ = convertSelection;
                                                    ratingAnswer2.bitField0_ |= 1;
                                                }
                                                builder3.copyOnWrite();
                                                UserVoiceSurveysLogging$Event.QuestionAnswered questionAnswered3 = (UserVoiceSurveysLogging$Event.QuestionAnswered) builder3.instance;
                                                UserVoiceSurveysLogging$Event.QuestionAnswered.RatingAnswer ratingAnswer3 = (UserVoiceSurveysLogging$Event.QuestionAnswered.RatingAnswer) builder5.build();
                                                ratingAnswer3.getClass();
                                                questionAnswered3.answer_ = ratingAnswer3;
                                                questionAnswered3.answerCase_ = 4;
                                            }
                                        } else {
                                            if (questionAnswered.answerCase_ == 3) {
                                                multipleSelectAnswer = (Survey$Event.QuestionAnswered.MultipleSelectAnswer) questionAnswered.answer_;
                                            } else {
                                                multipleSelectAnswer = Survey$Event.QuestionAnswered.MultipleSelectAnswer.DEFAULT_INSTANCE;
                                            }
                                            SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.QuestionAnswered.MultipleSelectAnswer.DEFAULT_INSTANCE.createBuilder();
                                            if (multipleSelectAnswer.answer_.size() > 0) {
                                                Iterator<E> it = multipleSelectAnswer.answer_.iterator();
                                                while (it.hasNext()) {
                                                    UserVoiceSurveysLogging$Event.QuestionAnswered.Selection convertSelection2 = DefaultExperimentTokenDecorator.convertSelection((Survey$Event.QuestionAnswered.Selection) it.next());
                                                    builder6.copyOnWrite();
                                                    UserVoiceSurveysLogging$Event.QuestionAnswered.MultipleSelectAnswer multipleSelectAnswer2 = (UserVoiceSurveysLogging$Event.QuestionAnswered.MultipleSelectAnswer) builder6.instance;
                                                    convertSelection2.getClass();
                                                    Internal.ProtobufList protobufList = multipleSelectAnswer2.answer_;
                                                    if (!protobufList.isModifiable()) {
                                                        multipleSelectAnswer2.answer_ = GeneratedMessageLite.mutableCopy(protobufList);
                                                    }
                                                    multipleSelectAnswer2.answer_.add(convertSelection2);
                                                }
                                            }
                                            builder3.copyOnWrite();
                                            UserVoiceSurveysLogging$Event.QuestionAnswered questionAnswered4 = (UserVoiceSurveysLogging$Event.QuestionAnswered) builder3.instance;
                                            UserVoiceSurveysLogging$Event.QuestionAnswered.MultipleSelectAnswer multipleSelectAnswer3 = (UserVoiceSurveysLogging$Event.QuestionAnswered.MultipleSelectAnswer) builder6.build();
                                            multipleSelectAnswer3.getClass();
                                            questionAnswered4.answer_ = multipleSelectAnswer3;
                                            questionAnswered4.answerCase_ = 3;
                                        }
                                    } else {
                                        if (questionAnswered.answerCase_ == 2) {
                                            singleSelectAnswer = (Survey$Event.QuestionAnswered.SingleSelectAnswer) questionAnswered.answer_;
                                        } else {
                                            singleSelectAnswer = Survey$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE;
                                        }
                                        SystemHealthProto$PackedHistogram.Builder builder7 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE.createBuilder();
                                        if ((singleSelectAnswer.bitField0_ & 1) != 0) {
                                            Survey$Event.QuestionAnswered.Selection selection2 = singleSelectAnswer.answer_;
                                            if (selection2 == null) {
                                                selection2 = Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE;
                                            }
                                            UserVoiceSurveysLogging$Event.QuestionAnswered.Selection convertSelection3 = DefaultExperimentTokenDecorator.convertSelection(selection2);
                                            builder7.copyOnWrite();
                                            UserVoiceSurveysLogging$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer2 = (UserVoiceSurveysLogging$Event.QuestionAnswered.SingleSelectAnswer) builder7.instance;
                                            convertSelection3.getClass();
                                            singleSelectAnswer2.answer_ = convertSelection3;
                                            singleSelectAnswer2.bitField0_ |= 1;
                                        }
                                        builder3.copyOnWrite();
                                        UserVoiceSurveysLogging$Event.QuestionAnswered questionAnswered5 = (UserVoiceSurveysLogging$Event.QuestionAnswered) builder3.instance;
                                        UserVoiceSurveysLogging$Event.QuestionAnswered.SingleSelectAnswer singleSelectAnswer3 = (UserVoiceSurveysLogging$Event.QuestionAnswered.SingleSelectAnswer) builder7.build();
                                        singleSelectAnswer3.getClass();
                                        questionAnswered5.answer_ = singleSelectAnswer3;
                                        questionAnswered5.answerCase_ = 2;
                                    }
                                    builder2.copyOnWrite();
                                    UserVoiceSurveysLogging$Event userVoiceSurveysLogging$Event3 = (UserVoiceSurveysLogging$Event) builder2.instance;
                                    UserVoiceSurveysLogging$Event.QuestionAnswered questionAnswered6 = (UserVoiceSurveysLogging$Event.QuestionAnswered) builder3.build();
                                    questionAnswered6.getClass();
                                    userVoiceSurveysLogging$Event3.event_ = questionAnswered6;
                                    userVoiceSurveysLogging$Event3.eventCase_ = 5;
                                } else {
                                    throw null;
                                }
                            }
                        } else {
                            SystemHealthProto$PackedHistogram.Builder builder8 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.InvitationAnswered.DEFAULT_INSTANCE.createBuilder();
                            if (survey$Event.eventCase_ == 4) {
                                invitationAnswered = (Survey$Event.InvitationAnswered) survey$Event.event_;
                            } else {
                                invitationAnswered = Survey$Event.InvitationAnswered.DEFAULT_INSTANCE;
                            }
                            boolean z = invitationAnswered.accepted_;
                            builder8.copyOnWrite();
                            ((UserVoiceSurveysLogging$Event.InvitationAnswered) builder8.instance).accepted_ = z;
                            builder2.copyOnWrite();
                            UserVoiceSurveysLogging$Event userVoiceSurveysLogging$Event4 = (UserVoiceSurveysLogging$Event) builder2.instance;
                            UserVoiceSurveysLogging$Event.InvitationAnswered invitationAnswered2 = (UserVoiceSurveysLogging$Event.InvitationAnswered) builder8.build();
                            invitationAnswered2.getClass();
                            userVoiceSurveysLogging$Event4.event_ = invitationAnswered2;
                            userVoiceSurveysLogging$Event4.eventCase_ = 4;
                        }
                    } else {
                        if (survey$Event.eventCase_ == 3) {
                            surveyAccepted = (Survey$Event.SurveyAccepted) survey$Event.event_;
                        } else {
                            surveyAccepted = Survey$Event.SurveyAccepted.DEFAULT_INSTANCE;
                        }
                        SystemHealthProto$PackedHistogram.Builder builder9 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.SurveyAccepted.DEFAULT_INSTANCE.createBuilder();
                        if ((surveyAccepted.bitField0_ & 2) != 0) {
                            Survey$ProductContext survey$ProductContext = surveyAccepted.productContext_;
                            if (survey$ProductContext == null) {
                                survey$ProductContext = Survey$ProductContext.DEFAULT_INSTANCE;
                            }
                            SystemHealthProto$PackedHistogram.Builder builder10 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$ProductContext.DEFAULT_INSTANCE.createBuilder();
                            String str3 = survey$ProductContext.productVersion_;
                            builder10.copyOnWrite();
                            UserVoiceSurveysLogging$ProductContext userVoiceSurveysLogging$ProductContext = (UserVoiceSurveysLogging$ProductContext) builder10.instance;
                            str3.getClass();
                            userVoiceSurveysLogging$ProductContext.productVersion_ = str3;
                            if ((survey$ProductContext.bitField0_ & 1) != 0) {
                                SystemHealthProto$PackedHistogram.Builder builder11 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$ProductContext.SensitiveContext.DEFAULT_INSTANCE.createBuilder();
                                Survey$ProductContext.SensitiveContext sensitiveContext = survey$ProductContext.sensitiveContext_;
                                if (sensitiveContext == null) {
                                    sensitiveContext = Survey$ProductContext.SensitiveContext.DEFAULT_INSTANCE;
                                }
                                Internal.ProtobufList protobufList2 = sensitiveContext.experimentId_;
                                builder11.copyOnWrite();
                                UserVoiceSurveysLogging$ProductContext.SensitiveContext sensitiveContext2 = (UserVoiceSurveysLogging$ProductContext.SensitiveContext) builder11.instance;
                                Internal.ProtobufList protobufList3 = sensitiveContext2.experimentId_;
                                if (!protobufList3.isModifiable()) {
                                    sensitiveContext2.experimentId_ = GeneratedMessageLite.mutableCopy(protobufList3);
                                }
                                AbstractMessageLite.addAll(protobufList2, sensitiveContext2.experimentId_);
                                builder10.copyOnWrite();
                                UserVoiceSurveysLogging$ProductContext userVoiceSurveysLogging$ProductContext2 = (UserVoiceSurveysLogging$ProductContext) builder10.instance;
                                UserVoiceSurveysLogging$ProductContext.SensitiveContext sensitiveContext3 = (UserVoiceSurveysLogging$ProductContext.SensitiveContext) builder11.build();
                                sensitiveContext3.getClass();
                                userVoiceSurveysLogging$ProductContext2.sensitiveContext_ = sensitiveContext3;
                                userVoiceSurveysLogging$ProductContext2.bitField0_ |= 1;
                            }
                            builder9.copyOnWrite();
                            UserVoiceSurveysLogging$Event.SurveyAccepted surveyAccepted2 = (UserVoiceSurveysLogging$Event.SurveyAccepted) builder9.instance;
                            UserVoiceSurveysLogging$ProductContext userVoiceSurveysLogging$ProductContext3 = (UserVoiceSurveysLogging$ProductContext) builder10.build();
                            userVoiceSurveysLogging$ProductContext3.getClass();
                            surveyAccepted2.productContext_ = userVoiceSurveysLogging$ProductContext3;
                            surveyAccepted2.bitField0_ |= 1;
                        }
                        if ((surveyAccepted.bitField0_ & 4) != 0) {
                            Survey$SensitiveClientContext survey$SensitiveClientContext = surveyAccepted.sensitiveClientContext_;
                            if (survey$SensitiveClientContext == null) {
                                survey$SensitiveClientContext = Survey$SensitiveClientContext.DEFAULT_INSTANCE;
                            }
                            SystemHealthProto$PackedHistogram.Builder builder12 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SensitiveClientContext.DEFAULT_INSTANCE.createBuilder();
                            if ((survey$SensitiveClientContext.bitField0_ & 1) != 0) {
                                Survey$SensitiveClientContext.SensitiveDeviceInfo sensitiveDeviceInfo = survey$SensitiveClientContext.deviceInfo_;
                                if (sensitiveDeviceInfo == null) {
                                    sensitiveDeviceInfo = Survey$SensitiveClientContext.SensitiveDeviceInfo.DEFAULT_INSTANCE;
                                }
                                SystemHealthProto$PackedHistogram.Builder builder13 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.DEFAULT_INSTANCE.createBuilder();
                                if ((sensitiveDeviceInfo.bitField0_ & 2) != 0) {
                                    Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo sensitiveMobileInfo = sensitiveDeviceInfo.mobileInfo_;
                                    if (sensitiveMobileInfo == null) {
                                        sensitiveMobileInfo = Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.DEFAULT_INSTANCE;
                                    }
                                    SystemHealthProto$PackedHistogram.Builder builder14 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.DEFAULT_INSTANCE.createBuilder();
                                    if ((sensitiveMobileInfo.bitField0_ & 1) != 0) {
                                        Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo sensitiveTelephonyInfo = sensitiveMobileInfo.telephonyInfo_;
                                        if (sensitiveTelephonyInfo == null) {
                                            sensitiveTelephonyInfo = Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo.DEFAULT_INSTANCE;
                                        }
                                        SystemHealthProto$PackedHistogram.Builder builder15 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo.DEFAULT_INSTANCE.createBuilder();
                                        String str4 = sensitiveTelephonyInfo.phoneType_;
                                        builder15.copyOnWrite();
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo sensitiveTelephonyInfo2 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo) builder15.instance;
                                        str4.getClass();
                                        sensitiveTelephonyInfo2.phoneType_ = str4;
                                        String str5 = sensitiveTelephonyInfo.networkName_;
                                        builder15.copyOnWrite();
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo sensitiveTelephonyInfo3 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo) builder15.instance;
                                        str5.getClass();
                                        sensitiveTelephonyInfo3.networkName_ = str5;
                                        String str6 = sensitiveTelephonyInfo.networkType_;
                                        builder15.copyOnWrite();
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo sensitiveTelephonyInfo4 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo) builder15.instance;
                                        str6.getClass();
                                        sensitiveTelephonyInfo4.networkType_ = str6;
                                        String str7 = sensitiveTelephonyInfo.networkMccCode_;
                                        builder15.copyOnWrite();
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo sensitiveTelephonyInfo5 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo) builder15.instance;
                                        str7.getClass();
                                        sensitiveTelephonyInfo5.networkMccCode_ = str7;
                                        String str8 = sensitiveTelephonyInfo.networkMncCode_;
                                        builder15.copyOnWrite();
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo sensitiveTelephonyInfo6 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo) builder15.instance;
                                        str8.getClass();
                                        sensitiveTelephonyInfo6.networkMncCode_ = str8;
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo sensitiveTelephonyInfo7 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo) builder15.build();
                                        builder14.copyOnWrite();
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo sensitiveMobileInfo2 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo) builder14.instance;
                                        sensitiveTelephonyInfo7.getClass();
                                        sensitiveMobileInfo2.telephonyInfo_ = sensitiveTelephonyInfo7;
                                        sensitiveMobileInfo2.bitField0_ |= 1;
                                    }
                                    if ((sensitiveMobileInfo.bitField0_ & 2) != 0) {
                                        Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo sensitiveChimeraInfo = sensitiveMobileInfo.chimeraInfo_;
                                        if (sensitiveChimeraInfo == null) {
                                            sensitiveChimeraInfo = Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.DEFAULT_INSTANCE;
                                        }
                                        SystemHealthProto$PackedHistogram.Builder builder16 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.DEFAULT_INSTANCE.createBuilder();
                                        if (sensitiveChimeraInfo.moduleSetInfo_.size() > 0) {
                                            for (Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo sensitiveModuleSetInfo : sensitiveChimeraInfo.moduleSetInfo_) {
                                                SystemHealthProto$PackedHistogram.Builder builder17 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo.DEFAULT_INSTANCE.createBuilder();
                                                String str9 = sensitiveModuleSetInfo.moduleSetId_;
                                                builder17.copyOnWrite();
                                                UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo sensitiveModuleSetInfo2 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo) builder17.instance;
                                                str9.getClass();
                                                sensitiveModuleSetInfo2.moduleSetId_ = str9;
                                                String str10 = sensitiveModuleSetInfo.moduleSetVariant_;
                                                builder17.copyOnWrite();
                                                UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo sensitiveModuleSetInfo3 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo) builder17.instance;
                                                str10.getClass();
                                                sensitiveModuleSetInfo3.moduleSetVariant_ = str10;
                                                UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo sensitiveModuleSetInfo4 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo) builder17.build();
                                                builder16.copyOnWrite();
                                                UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo sensitiveChimeraInfo2 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo) builder16.instance;
                                                sensitiveModuleSetInfo4.getClass();
                                                Internal.ProtobufList protobufList4 = sensitiveChimeraInfo2.moduleSetInfo_;
                                                if (!protobufList4.isModifiable()) {
                                                    sensitiveChimeraInfo2.moduleSetInfo_ = GeneratedMessageLite.mutableCopy(protobufList4);
                                                }
                                                sensitiveChimeraInfo2.moduleSetInfo_.add(sensitiveModuleSetInfo4);
                                            }
                                        }
                                        builder14.copyOnWrite();
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo sensitiveMobileInfo3 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo) builder14.instance;
                                        UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo sensitiveChimeraInfo3 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo) builder16.build();
                                        sensitiveChimeraInfo3.getClass();
                                        sensitiveMobileInfo3.chimeraInfo_ = sensitiveChimeraInfo3;
                                        sensitiveMobileInfo3.bitField0_ |= 2;
                                    }
                                    builder13.copyOnWrite();
                                    UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo sensitiveDeviceInfo2 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo) builder13.instance;
                                    UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo sensitiveMobileInfo4 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo) builder14.build();
                                    sensitiveMobileInfo4.getClass();
                                    sensitiveDeviceInfo2.mobileInfo_ = sensitiveMobileInfo4;
                                    sensitiveDeviceInfo2.bitField0_ |= 2;
                                }
                                builder12.copyOnWrite();
                                UserVoiceSurveysLogging$SensitiveClientContext userVoiceSurveysLogging$SensitiveClientContext = (UserVoiceSurveysLogging$SensitiveClientContext) builder12.instance;
                                UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo sensitiveDeviceInfo3 = (UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo) builder13.build();
                                sensitiveDeviceInfo3.getClass();
                                userVoiceSurveysLogging$SensitiveClientContext.deviceInfo_ = sensitiveDeviceInfo3;
                                userVoiceSurveysLogging$SensitiveClientContext.bitField0_ |= 1;
                            }
                            builder9.copyOnWrite();
                            UserVoiceSurveysLogging$Event.SurveyAccepted surveyAccepted3 = (UserVoiceSurveysLogging$Event.SurveyAccepted) builder9.instance;
                            UserVoiceSurveysLogging$SensitiveClientContext userVoiceSurveysLogging$SensitiveClientContext2 = (UserVoiceSurveysLogging$SensitiveClientContext) builder12.build();
                            userVoiceSurveysLogging$SensitiveClientContext2.getClass();
                            surveyAccepted3.sensitiveClientContext_ = userVoiceSurveysLogging$SensitiveClientContext2;
                            surveyAccepted3.bitField0_ |= 2;
                        }
                        builder2.copyOnWrite();
                        UserVoiceSurveysLogging$Event userVoiceSurveysLogging$Event5 = (UserVoiceSurveysLogging$Event) builder2.instance;
                        UserVoiceSurveysLogging$Event.SurveyAccepted surveyAccepted4 = (UserVoiceSurveysLogging$Event.SurveyAccepted) builder9.build();
                        surveyAccepted4.getClass();
                        userVoiceSurveysLogging$Event5.event_ = surveyAccepted4;
                        userVoiceSurveysLogging$Event5.eventCase_ = 3;
                    }
                } else {
                    UserVoiceSurveysLogging$Event.SurveyShown surveyShown = UserVoiceSurveysLogging$Event.SurveyShown.DEFAULT_INSTANCE;
                    builder2.copyOnWrite();
                    UserVoiceSurveysLogging$Event userVoiceSurveysLogging$Event6 = (UserVoiceSurveysLogging$Event) builder2.instance;
                    surveyShown.getClass();
                    userVoiceSurveysLogging$Event6.event_ = surveyShown;
                    userVoiceSurveysLogging$Event6.eventCase_ = 2;
                }
                builder.copyOnWrite();
                UserVoiceSurveysLogging$SurveyRecordEventRequest userVoiceSurveysLogging$SurveyRecordEventRequest = (UserVoiceSurveysLogging$SurveyRecordEventRequest) builder.instance;
                UserVoiceSurveysLogging$Event userVoiceSurveysLogging$Event7 = (UserVoiceSurveysLogging$Event) builder2.build();
                userVoiceSurveysLogging$Event7.getClass();
                userVoiceSurveysLogging$SurveyRecordEventRequest.event_ = userVoiceSurveysLogging$Event7;
                userVoiceSurveysLogging$SurveyRecordEventRequest.bitField0_ |= 1;
            } else {
                throw null;
            }
        }
        if ((service$SurveyRecordEventRequest.bitField0_ & 2) != 0) {
            SystemHealthProto$PackedHistogram.Builder builder18 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Session.DEFAULT_INSTANCE.createBuilder();
            Survey$Session survey$Session = service$SurveyRecordEventRequest.session_;
            if (survey$Session == null) {
                survey$Session = Survey$Session.DEFAULT_INSTANCE;
            }
            String str11 = survey$Session.sessionId_;
            builder18.copyOnWrite();
            UserVoiceSurveysLogging$Session userVoiceSurveysLogging$Session = (UserVoiceSurveysLogging$Session) builder18.instance;
            str11.getClass();
            userVoiceSurveysLogging$Session.sessionId_ = str11;
            Survey$Session survey$Session2 = service$SurveyRecordEventRequest.session_;
            if (survey$Session2 == null) {
                survey$Session2 = Survey$Session.DEFAULT_INSTANCE;
            }
            ByteString byteString = survey$Session2.sessionToken_;
            builder18.copyOnWrite();
            UserVoiceSurveysLogging$Session userVoiceSurveysLogging$Session2 = (UserVoiceSurveysLogging$Session) builder18.instance;
            byteString.getClass();
            userVoiceSurveysLogging$Session2.sessionToken_ = byteString;
            UserVoiceSurveysLogging$Session userVoiceSurveysLogging$Session3 = (UserVoiceSurveysLogging$Session) builder18.build();
            builder.copyOnWrite();
            UserVoiceSurveysLogging$SurveyRecordEventRequest userVoiceSurveysLogging$SurveyRecordEventRequest2 = (UserVoiceSurveysLogging$SurveyRecordEventRequest) builder.instance;
            userVoiceSurveysLogging$Session3.getClass();
            userVoiceSurveysLogging$SurveyRecordEventRequest2.session_ = userVoiceSurveysLogging$Session3;
            userVoiceSurveysLogging$SurveyRecordEventRequest2.bitField0_ |= 2;
        }
        Context context = this.context;
        MetricsLogger metricsLogger = MetricsLogger.getInstance();
        SystemHealthProto$PackedHistogram.Builder builder19 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$HttpEvent.DEFAULT_INSTANCE.createBuilder();
        builder19.copyOnWrite();
        UserVoiceSurveysLogging$HttpEvent userVoiceSurveysLogging$HttpEvent = (UserVoiceSurveysLogging$HttpEvent) builder19.instance;
        UserVoiceSurveysLogging$SurveyRecordEventRequest userVoiceSurveysLogging$SurveyRecordEventRequest3 = (UserVoiceSurveysLogging$SurveyRecordEventRequest) builder.build();
        userVoiceSurveysLogging$SurveyRecordEventRequest3.getClass();
        userVoiceSurveysLogging$HttpEvent.request_ = userVoiceSurveysLogging$SurveyRecordEventRequest3;
        userVoiceSurveysLogging$HttpEvent.requestCase_ = 3;
        UserVoiceSurveysLogging$SurveyRecordEventResponse userVoiceSurveysLogging$SurveyRecordEventResponse = UserVoiceSurveysLogging$SurveyRecordEventResponse.DEFAULT_INSTANCE;
        builder19.copyOnWrite();
        UserVoiceSurveysLogging$HttpEvent userVoiceSurveysLogging$HttpEvent2 = (UserVoiceSurveysLogging$HttpEvent) builder19.instance;
        userVoiceSurveysLogging$SurveyRecordEventResponse.getClass();
        userVoiceSurveysLogging$HttpEvent2.response_ = userVoiceSurveysLogging$SurveyRecordEventResponse;
        userVoiceSurveysLogging$HttpEvent2.responseCase_ = 5;
        metricsLogger.reportHttpEvent((UserVoiceSurveysLogging$HttpEvent) builder19.build(), stopwatch.getStart(), stopwatch.getElapsed(), context, str);
    }

    /* renamed from: lambda$getSurveyStartupConfig$3$com-google-android-libraries-surveys-internal-network-grpc-NetworkCallerGrpc$ar$class_merging$ar$class_merging, reason: not valid java name */
    public final /* synthetic */ void m207x3afd4cf2(Service$GetSurveyStartupConfigRequest service$GetSurveyStartupConfigRequest, CallCredentials$RequestInfo callCredentials$RequestInfo) {
        MethodDescriptor methodDescriptor;
        try {
            OAuth2Credentials authCredentials = getAuthCredentials();
            SurveyConfigProvider surveyConfigProvider = SurveyConfigProvider.instance;
            boolean z = surveyConfigProvider.feedback1pEnabled;
            surveyConfigProvider.feedback1pEnabled = true;
            Channel channel = getChannel(authCredentials);
            SurveyConfigProvider.instance.feedback1pEnabled = z;
            if (channel == null) {
                Log.e("NetworkCallerGrpc", "Could not get channel for survey startup config.");
                SurveyConfigProvider.instance.feedback1pEnabled = false;
                return;
            }
            SurveyServiceGrpc.SurveyServiceFutureStub newFutureStub = SurveyServiceGrpc.newFutureStub(channel);
            Channel channel2 = newFutureStub.channel;
            MethodDescriptor methodDescriptor2 = SurveyServiceGrpc.getGetSurveyStartupConfigMethod;
            if (methodDescriptor2 == null) {
                synchronized (SurveyServiceGrpc.class) {
                    methodDescriptor = SurveyServiceGrpc.getGetSurveyStartupConfigMethod;
                    if (methodDescriptor == null) {
                        MethodDescriptor.Builder newBuilder = MethodDescriptor.newBuilder();
                        newBuilder.type = MethodDescriptor.MethodType.UNARY;
                        newBuilder.fullMethodName = MethodDescriptor.generateFullMethodName("google.internal.feedback.v1.SurveyService", "GetSurveyStartupConfig");
                        newBuilder.setSampledToLocalTracing$ar$ds();
                        Service$GetSurveyStartupConfigRequest service$GetSurveyStartupConfigRequest2 = Service$GetSurveyStartupConfigRequest.DEFAULT_INSTANCE;
                        ExtensionRegistryLite extensionRegistryLite = ProtoLiteUtils.globalRegistry;
                        newBuilder.requestMarshaller = new ProtoLiteUtils.MessageMarshaller(service$GetSurveyStartupConfigRequest2);
                        newBuilder.responseMarshaller = new ProtoLiteUtils.MessageMarshaller(Service$GetSurveyStartupConfigResponse.DEFAULT_INSTANCE);
                        methodDescriptor = newBuilder.build();
                        SurveyServiceGrpc.getGetSurveyStartupConfigMethod = methodDescriptor;
                    }
                }
                methodDescriptor2 = methodDescriptor;
            }
            ContextDataProvider.addCallback(ClientCalls.futureUnaryCall(channel2.newCall(methodDescriptor2, newFutureStub.callOptions), service$GetSurveyStartupConfigRequest), new ImageDescriptionProcessor.AnonymousClass1(this, callCredentials$RequestInfo, 2), NetworkExecutor.getNetworkExecutor());
        } catch (UnsupportedOperationException e) {
            Log.e("NetworkCallerGrpc", "The configured transport is not supported: ".concat(e.toString()));
            runOnRequestFailedCallback(SurveyRequest.ErrorType.UNSUPPORTED_CRONET_ENGINE);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0228  */
    /* renamed from: lambda$trigger$2$com-google-android-libraries-surveys-internal-network-grpc-NetworkCallerGrpc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ void m208xb4d41164(com.google.scone.proto.Service$SurveyTriggerRequest r9, com.google.android.libraries.surveys.internal.utils.Stopwatch r10) {
        /*
            Method dump skipped, instructions count: 553
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc.m208xb4d41164(com.google.scone.proto.Service$SurveyTriggerRequest, com.google.android.libraries.surveys.internal.utils.Stopwatch):void");
    }

    public final void shutdownChannel() {
        ManagedChannel managedChannel = this.managedChannel;
        if (managedChannel != null) {
            managedChannel.shutdown$ar$ds$17197e6c_0();
        }
    }
}

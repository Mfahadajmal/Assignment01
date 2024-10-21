package com.google.android.libraries.phenotype.client.stable;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.app.UiModeManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.browser.customtabs.CustomTabsIntent$Builder;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.privacy.policy.BrowserNotFoundException;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.resourceutils.ResourceUtils$SystemInfoLinkClickListener;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.libraries.surveys.internal.utils.TvLinksAccessibilityHelper;
import com.google.android.marvin.talkback.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.android.concurrent.FutureCallbackRegistry$$ExternalSyntheticLambda0;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$AnswerChoice;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$AnswerChoices;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Completion;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$DisplaySettings;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Event;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$HttpEvent;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Invitation;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$MultiSelect;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$OpenText;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Payload;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$PrivacySettings;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Question;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Rating;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Session;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SingleSelect;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SurveyTriggerRequest;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SurveyTriggerResponse;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$TextSubstitution;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$TriggerContext;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$UserEvent;
import com.google.scone.proto.Service$SurveyTriggerRequest;
import com.google.scone.proto.Service$SurveyTriggerResponse;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$AnswerChoices;
import com.google.scone.proto.Survey$ClientContext;
import com.google.scone.proto.Survey$Completion;
import com.google.scone.proto.Survey$DisplaySettings;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$Invitation;
import com.google.scone.proto.Survey$MultiSelect;
import com.google.scone.proto.Survey$OpenText;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$PrivacySettings;
import com.google.scone.proto.Survey$Question;
import com.google.scone.proto.Survey$Rating;
import com.google.scone.proto.Survey$Session;
import com.google.scone.proto.Survey$SingleSelect;
import com.google.scone.proto.Survey$TextSubstitution;
import com.google.scone.proto.Survey$TriggerContext;
import googledata.experiments.mobile.surveys_android.features.Clearcut;
import googledata.experiments.mobile.surveys_android.features.HatsV1M3Features;
import googledata.experiments.mobile.surveys_android.features.HatsV1M5Features;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DefaultExperimentTokenDecorator {
    private static volatile DefaultExperimentTokenDecorator instance;

    public DefaultExperimentTokenDecorator() {
    }

    private static void addItem(int i, String str, List list, Resources resources) {
        if (!TextUtils.isEmpty(str)) {
            list.add(new Pair(resources.getString(i), str));
        }
    }

    public static AlertDialog.Builder alertDialogBuilder(Context context) {
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            return new MaterialAlertDialogBuilder(new ContextThemeWrapper(context, R.style.SurveyMaterialComponentsTheme), R.style.SurveyMaterialAlertDialogStyle);
        }
        return new AlertDialog.Builder(context, R.style.SurveyAlertDialogTheme);
    }

    public static void appendEditTextHintWithHelperTextView(final EditText editText, final TextView textView) {
        ViewCompat.setAccessibilityDelegate(editText, new AccessibilityDelegateCompat() { // from class: com.google.android.libraries.surveys.internal.utils.AccessibilityUtils$1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                String str;
                String str2;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                String str3 = "";
                if (editText.getText() == null) {
                    str = "";
                } else {
                    str = editText.getText().toString();
                }
                EditText editText2 = editText;
                TextView textView2 = textView;
                if (editText2.getHint() == null) {
                    str2 = "";
                } else {
                    str2 = editText2.getHint().toString();
                }
                if (textView2.getText() != null) {
                    str3 = textView2.getText().toString();
                }
                CharSequence _BOUNDARY$ar$MethodOutlining$dc56d17a_6 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str3, str2, " ");
                accessibilityNodeInfoCompat.setHintText(_BOUNDARY$ar$MethodOutlining$dc56d17a_6);
                accessibilityNodeInfoCompat.setShowingHintText(str.isEmpty());
                if (str.isEmpty()) {
                    accessibilityNodeInfoCompat.setText(_BOUNDARY$ar$MethodOutlining$dc56d17a_6);
                } else {
                    accessibilityNodeInfoCompat.setText(str);
                }
            }
        });
    }

    public static void configureLegalText(final Activity activity, TextView textView, final String str, String str2, final String str3, final String str4, final ResourceUtils$SystemInfoLinkClickListener resourceUtils$SystemInfoLinkClickListener) {
        Resources resources = activity.getResources();
        if (((UiModeManager) ContextDataProvider.memoize(new FutureCallbackRegistry$$ExternalSyntheticLambda0(activity, 1)).get()).getCurrentModeType() == 3) {
            textView.setText(resources.getString(R.string.survey_legal_text_car));
        } else {
            String string = resources.getString(R.string.survey_account_and_system_info);
            String string2 = resources.getString(R.string.survey_privacy);
            String string3 = resources.getString(R.string.survey_terms);
            String string4 = resources.getString(R.string.survey_legal_text, string, string2, string3);
            if (str2 != null) {
                string4 = string4.replace("Google", str2);
            }
            SpannableString spannableString = new SpannableString(string4);
            setSpan(spannableString, string, new ClickableSpan() { // from class: com.google.android.libraries.surveys.internal.resourceutils.ResourceUtils$1
                @Override // android.text.style.ClickableSpan
                public final void onClick(View view) {
                    ResourceUtils$SystemInfoLinkClickListener.this.onClick();
                }
            });
            setSpan(spannableString, string2, new ClickableSpan() { // from class: com.google.android.libraries.surveys.internal.resourceutils.ResourceUtils$2
                @Override // android.text.style.ClickableSpan
                public final void onClick(View view) {
                    Stopwatch stopwatch = new Stopwatch();
                    String str5 = str3;
                    if (str5 != null) {
                        DefaultExperimentTokenDecorator.openInCustomTab(activity, str5);
                    } else {
                        try {
                            Activity activity2 = activity;
                            String str6 = str;
                            activity2.getClass();
                            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(activity2, null);
                            if (str6 != null && !str6.isEmpty()) {
                                Intent putExtra = new Intent("com.google.android.gms.accountsettings.action.VIEW_SETTINGS").setPackage("com.google.android.gms").putExtra("extra.accountName", str6).putExtra("extra.screenId", 500);
                                if (putExtra.resolveActivity(activity2.getPackageManager()) == null) {
                                    DefaultExperimentTokenDecorator.launchCustomTab$ar$objectUnboxing(activity2);
                                } else {
                                    ((Activity) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).startActivityForResult(putExtra, 0);
                                }
                            }
                            DefaultExperimentTokenDecorator.launchCustomTab$ar$objectUnboxing(activity2);
                        } catch (BrowserNotFoundException e) {
                            Log.e("ResourceUtils", "No app found to open URL: ".concat(String.valueOf(e.url)));
                        }
                    }
                    Activity activity3 = activity;
                    String str7 = str;
                    CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (!FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
                        return;
                    }
                    MetricsLogger metricsLogger = MetricsLogger.getInstance();
                    SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$UserEvent.DEFAULT_INSTANCE.createBuilder();
                    int i = UserVoiceSurveysLogging$UserEvent.EventTrigger.CLICK$ar$edu;
                    builder.copyOnWrite();
                    ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventTrigger_ = UserVoiceSurveysLogging$UserEvent.EventTrigger.getNumber$ar$edu$5137a177_0(i);
                    int i2 = UserVoiceSurveysLogging$UserEvent.EventType.PRIVACY_LINK_CLICKED$ar$edu;
                    builder.copyOnWrite();
                    ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventType_ = UserVoiceSurveysLogging$UserEvent.EventType.getNumber$ar$edu$62b19512_0(i2);
                    metricsLogger.reportUserEvent((UserVoiceSurveysLogging$UserEvent) builder.build(), stopwatch.getStart(), stopwatch.getElapsed(), activity3, str7);
                }
            });
            setSpan(spannableString, string3, new ClickableSpan() { // from class: com.google.android.libraries.surveys.internal.resourceutils.ResourceUtils$3
                @Override // android.text.style.ClickableSpan
                public final void onClick(View view) {
                    Stopwatch stopwatch = new Stopwatch();
                    String str5 = str4;
                    if (str5 == null) {
                        str5 = "https://www.google.com/policies/terms/";
                    }
                    DefaultExperimentTokenDecorator.openInCustomTab(activity, str5);
                    CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (!FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
                        return;
                    }
                    String str6 = str;
                    Context context = activity;
                    MetricsLogger metricsLogger = MetricsLogger.getInstance();
                    SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$UserEvent.DEFAULT_INSTANCE.createBuilder();
                    int i = UserVoiceSurveysLogging$UserEvent.EventTrigger.CLICK$ar$edu;
                    builder.copyOnWrite();
                    ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventTrigger_ = UserVoiceSurveysLogging$UserEvent.EventTrigger.getNumber$ar$edu$5137a177_0(i);
                    int i2 = UserVoiceSurveysLogging$UserEvent.EventType.TERMS_LINK_CLICKED$ar$edu;
                    builder.copyOnWrite();
                    ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventType_ = UserVoiceSurveysLogging$UserEvent.EventType.getNumber$ar$edu$62b19512_0(i2);
                    metricsLogger.reportUserEvent((UserVoiceSurveysLogging$UserEvent) builder.build(), stopwatch.getStart(), stopwatch.getElapsed(), context, str6);
                }
            });
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(spannableString);
        }
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(HatsV1M5Features.INSTANCE.get().enableTvAccessibilityHelper(FlagsUtil.phenotypeContext))) {
            String packageName = activity.getPackageName();
            CurrentProcess currentProcess2 = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            String[] split = TextUtils.split(HatsV1M5Features.INSTANCE.get().accessibilityHelperAllowlist(FlagsUtil.phenotypeContext), ",");
            int i = 0;
            while (true) {
                if (i >= split.length) {
                    break;
                }
                split[i] = split[i].trim();
                i++;
            }
            for (String str5 : split) {
                if (TextUtils.equals(str5, packageName)) {
                    ViewCompat.setAccessibilityDelegate(textView, new TvLinksAccessibilityHelper(textView));
                    return;
                }
            }
        }
    }

    private static UserVoiceSurveysLogging$AnswerChoices convertAnswerChoices(Survey$AnswerChoices survey$AnswerChoices) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$AnswerChoices.DEFAULT_INSTANCE.createBuilder();
        for (Survey$AnswerChoice survey$AnswerChoice : survey$AnswerChoices.answerChoice_) {
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$AnswerChoice.DEFAULT_INSTANCE.createBuilder();
            int i = survey$AnswerChoice.answerType_;
            builder2.copyOnWrite();
            ((UserVoiceSurveysLogging$AnswerChoice) builder2.instance).answerType_ = i;
            int i2 = survey$AnswerChoice.answerOrdinal_;
            builder2.copyOnWrite();
            ((UserVoiceSurveysLogging$AnswerChoice) builder2.instance).answerOrdinal_ = i2;
            String str = survey$AnswerChoice.text_;
            builder2.copyOnWrite();
            UserVoiceSurveysLogging$AnswerChoice userVoiceSurveysLogging$AnswerChoice = (UserVoiceSurveysLogging$AnswerChoice) builder2.instance;
            str.getClass();
            userVoiceSurveysLogging$AnswerChoice.text_ = str;
            boolean z = survey$AnswerChoice.screenIn_;
            builder2.copyOnWrite();
            ((UserVoiceSurveysLogging$AnswerChoice) builder2.instance).screenIn_ = z;
            builder.copyOnWrite();
            UserVoiceSurveysLogging$AnswerChoices userVoiceSurveysLogging$AnswerChoices = (UserVoiceSurveysLogging$AnswerChoices) builder.instance;
            UserVoiceSurveysLogging$AnswerChoice userVoiceSurveysLogging$AnswerChoice2 = (UserVoiceSurveysLogging$AnswerChoice) builder2.build();
            userVoiceSurveysLogging$AnswerChoice2.getClass();
            Internal.ProtobufList protobufList = userVoiceSurveysLogging$AnswerChoices.answerChoice_;
            if (!protobufList.isModifiable()) {
                userVoiceSurveysLogging$AnswerChoices.answerChoice_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            userVoiceSurveysLogging$AnswerChoices.answerChoice_.add(userVoiceSurveysLogging$AnswerChoice2);
        }
        return (UserVoiceSurveysLogging$AnswerChoices) builder.build();
    }

    public static UserVoiceSurveysLogging$Event.QuestionAnswered.Selection convertSelection(Survey$Event.QuestionAnswered.Selection selection) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE.createBuilder();
        int i = selection.answerType_;
        builder.copyOnWrite();
        ((UserVoiceSurveysLogging$Event.QuestionAnswered.Selection) builder.instance).answerType_ = i;
        int i2 = selection.answerOrdinal_;
        builder.copyOnWrite();
        ((UserVoiceSurveysLogging$Event.QuestionAnswered.Selection) builder.instance).answerOrdinal_ = i2;
        String str = selection.text_;
        builder.copyOnWrite();
        UserVoiceSurveysLogging$Event.QuestionAnswered.Selection selection2 = (UserVoiceSurveysLogging$Event.QuestionAnswered.Selection) builder.instance;
        str.getClass();
        selection2.text_ = str;
        return (UserVoiceSurveysLogging$Event.QuestionAnswered.Selection) builder.build();
    }

    public static void crashOnFailure(ListenableFuture listenableFuture) {
        listenableFuture.addListener(new FlagStore$$ExternalSyntheticLambda3(listenableFuture, 3), DirectExecutor.INSTANCE);
    }

    public static DefaultExperimentTokenDecorator get() {
        DefaultExperimentTokenDecorator defaultExperimentTokenDecorator = instance;
        if (defaultExperimentTokenDecorator == null) {
            synchronized (DefaultExperimentTokenDecorator.class) {
                if (instance == null) {
                    instance = new ExperimentTokenDecoratorImpl();
                }
                defaultExperimentTokenDecorator = instance;
            }
        }
        return defaultExperimentTokenDecorator;
    }

    public static Drawable getRecoloredDrawable(Drawable drawable, Context context, int i) {
        Drawable mutate = drawable.getConstantState().newDrawable(context.getResources()).mutate();
        mutate.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        return mutate;
    }

    public static List getSystemInfoDialogItems(Context context, String str, Bundle bundle) {
        Resources resources = context.getResources();
        Survey$ClientContext createClientContext = SurveyUtils.createClientContext(context);
        Survey$ClientContext.DeviceInfo deviceInfo = createClientContext.deviceInfo_;
        if (deviceInfo == null) {
            deviceInfo = Survey$ClientContext.DeviceInfo.DEFAULT_INSTANCE;
        }
        Duration duration = deviceInfo.timezoneOffset_;
        if (duration == null) {
            duration = Duration.DEFAULT_INSTANCE;
        }
        Survey$ClientContext.DeviceInfo.BrowserInfo browserInfo = deviceInfo.browserInfo_;
        if (browserInfo == null) {
            browserInfo = Survey$ClientContext.DeviceInfo.BrowserInfo.DEFAULT_INSTANCE;
        }
        Survey$ClientContext.DeviceInfo.MobileInfo mobileInfo = deviceInfo.mobileInfo_;
        if (mobileInfo == null) {
            mobileInfo = Survey$ClientContext.DeviceInfo.MobileInfo.DEFAULT_INSTANCE;
        }
        Survey$ClientContext.LibraryInfo libraryInfo = createClientContext.libraryInfo_;
        if (libraryInfo == null) {
            libraryInfo = Survey$ClientContext.LibraryInfo.DEFAULT_INSTANCE;
        }
        ArrayList arrayList = new ArrayList(15);
        long nanos = TimeUnit.SECONDS.toNanos(duration.seconds_) + duration.nanos_;
        addItem(R.string.survey_email_address, str, arrayList, resources);
        addItem(R.string.survey_timezone_offset, String.format("%02d:%02d:%02d", Long.valueOf(TimeUnit.NANOSECONDS.toHours(nanos)), Long.valueOf(TimeUnit.NANOSECONDS.toMinutes(nanos) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(nanos))), Long.valueOf(TimeUnit.NANOSECONDS.toSeconds(nanos) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(nanos)))), arrayList, resources);
        addItem(R.string.survey_user_agent, browserInfo.userAgent_, arrayList, resources);
        addItem(R.string.survey_url, browserInfo.initialUrl_, arrayList, resources);
        addItem(R.string.survey_device_model, mobileInfo.deviceModel_, arrayList, resources);
        addItem(R.string.survey_brand, mobileInfo.deviceBrand_, arrayList, resources);
        addItem(R.string.survey_operating_system_version, mobileInfo.osVersion_, arrayList, resources);
        addItem(R.string.survey_app_name, mobileInfo.appName_, arrayList, resources);
        addItem(R.string.survey_app_id, mobileInfo.appId_, arrayList, resources);
        addItem(R.string.survey_app_version, mobileInfo.appVersion_, arrayList, resources);
        addItem(R.string.survey_google_play_services_version, mobileInfo.gmsCoreVersion_, arrayList, resources);
        int forNumber$ar$edu$c540fb10_0 = Survey$ClientContext.DeviceInfo.MobileInfo.OsType.forNumber$ar$edu$c540fb10_0(mobileInfo.osType_);
        if (forNumber$ar$edu$c540fb10_0 == 0) {
            forNumber$ar$edu$c540fb10_0 = Survey$ClientContext.DeviceInfo.MobileInfo.OsType.UNRECOGNIZED$ar$edu$7c36702f_0;
        }
        String stringGeneratedcf6bed038c46f567 = Survey$ClientContext.DeviceInfo.MobileInfo.OsType.toStringGeneratedcf6bed038c46f567(forNumber$ar$edu$c540fb10_0);
        if (forNumber$ar$edu$c540fb10_0 != 0) {
            addItem(R.string.survey_operating_system, stringGeneratedcf6bed038c46f567, arrayList, resources);
            int forNumber$ar$edu$7920202b_0 = Survey$ClientContext.LibraryInfo.Platform.forNumber$ar$edu$7920202b_0(libraryInfo.platform_);
            if (forNumber$ar$edu$7920202b_0 == 0) {
                forNumber$ar$edu$7920202b_0 = Survey$ClientContext.LibraryInfo.Platform.UNRECOGNIZED$ar$edu$2fe3ac50_0;
            }
            String stringGeneratedfd6efeaa6957c3cd = Survey$ClientContext.LibraryInfo.Platform.toStringGeneratedfd6efeaa6957c3cd(forNumber$ar$edu$7920202b_0);
            if (forNumber$ar$edu$7920202b_0 != 0) {
                addItem(R.string.survey_platform, stringGeneratedfd6efeaa6957c3cd, arrayList, resources);
                addItem(R.string.survey_library_version, libraryInfo.libraryVersion_, arrayList, resources);
                StringBuilder sb = new StringBuilder();
                for (String str2 : bundle.keySet()) {
                    sb.append(String.format("%s %s %s\n", str2, context.getString(R.string.survey_rightwards_arrow), bundle.get(str2)));
                }
                addItem(R.string.survey_application_data, sb.toString(), arrayList, resources);
                return arrayList;
            }
            throw null;
        }
        throw null;
    }

    public static void launchCustomTab$ar$objectUnboxing(Context context) {
        try {
            CustomTabsIntent$Builder customTabsIntent$Builder = new CustomTabsIntent$Builder();
            customTabsIntent$Builder.mDefaultColorSchemeBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.setToolbarColor$ar$ds(Color.parseColor("#eeeeee"));
            customTabsIntent$Builder.build$ar$class_merging$d5984029_0$ar$class_merging().launchUrl(context, Uri.parse("https://www.google.com/policies/privacy/"));
        } catch (ActivityNotFoundException unused) {
            throw new BrowserNotFoundException();
        }
    }

    public static void logInternal$ar$ds(Level level, Executor executor, Throwable th, String str, Object... objArr) {
        executor.execute(TracePropagation.propagateRunnable(new WindowInsetsAnimationCompat$Impl21.Impl21OnApplyWindowInsetsListener.AnonymousClass3(level, th, str, objArr, 3)));
    }

    public static void openInCustomTab(Context context, String str) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
            onDeviceTextDetectionLoadLogEvent.setToolbarColor$ar$ds(Color.parseColor("#eeeeee"));
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat build$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceTextDetectionLoadLogEvent.build$ar$class_merging$ar$class_merging$ar$class_merging();
            CustomTabsIntent$Builder customTabsIntent$Builder = new CustomTabsIntent$Builder();
            customTabsIntent$Builder.mDefaultColorSchemeBundle = build$ar$class_merging$ar$class_merging$ar$class_merging.toBundle();
            try {
                customTabsIntent$Builder.build$ar$class_merging$d5984029_0$ar$class_merging().launchUrl(context, Uri.parse(str));
                return;
            } catch (ActivityNotFoundException unused) {
                Log.e("ResourceUtils", "No app found to open URL: ".concat(String.valueOf(str)));
                return;
            }
        }
        Toast.makeText(context, context.getString(R.string.survey_network_request_failed), 0).show();
    }

    public static void recolorImageViewIcon(ImageView imageView, Context context, int i) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            imageView.setImageDrawable(getRecoloredDrawable(drawable, context, i));
        }
    }

    public static void reportHttpEventForSurveyTrigger(Service$SurveyTriggerRequest service$SurveyTriggerRequest, Service$SurveyTriggerResponse service$SurveyTriggerResponse, Stopwatch stopwatch, Context context, String str) {
        char c;
        int i;
        int i2;
        Survey$SingleSelect survey$SingleSelect;
        Survey$MultiSelect survey$MultiSelect;
        Survey$Rating survey$Rating;
        Survey$OpenText survey$OpenText;
        Survey$TextSubstitution.AnswerPipe answerPipe;
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SurveyTriggerRequest.DEFAULT_INSTANCE.createBuilder();
            if ((service$SurveyTriggerRequest.bitField0_ & 1) != 0) {
                Survey$TriggerContext survey$TriggerContext = service$SurveyTriggerRequest.triggerContext_;
                if (survey$TriggerContext == null) {
                    survey$TriggerContext = Survey$TriggerContext.DEFAULT_INSTANCE;
                }
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$TriggerContext.DEFAULT_INSTANCE.createBuilder();
                String str2 = survey$TriggerContext.triggerId_;
                builder2.copyOnWrite();
                UserVoiceSurveysLogging$TriggerContext userVoiceSurveysLogging$TriggerContext = (UserVoiceSurveysLogging$TriggerContext) builder2.instance;
                str2.getClass();
                userVoiceSurveysLogging$TriggerContext.triggerId_ = str2;
                Internal.ProtobufList protobufList = survey$TriggerContext.language_;
                builder2.copyOnWrite();
                UserVoiceSurveysLogging$TriggerContext userVoiceSurveysLogging$TriggerContext2 = (UserVoiceSurveysLogging$TriggerContext) builder2.instance;
                Internal.ProtobufList protobufList2 = userVoiceSurveysLogging$TriggerContext2.language_;
                if (!protobufList2.isModifiable()) {
                    userVoiceSurveysLogging$TriggerContext2.language_ = GeneratedMessageLite.mutableCopy(protobufList2);
                }
                AbstractMessageLite.addAll(protobufList, userVoiceSurveysLogging$TriggerContext2.language_);
                boolean z = survey$TriggerContext.testingMode_;
                builder2.copyOnWrite();
                ((UserVoiceSurveysLogging$TriggerContext) builder2.instance).testingMode_ = z;
                UserVoiceSurveysLogging$TriggerContext userVoiceSurveysLogging$TriggerContext3 = (UserVoiceSurveysLogging$TriggerContext) builder2.build();
                builder.copyOnWrite();
                UserVoiceSurveysLogging$SurveyTriggerRequest userVoiceSurveysLogging$SurveyTriggerRequest = (UserVoiceSurveysLogging$SurveyTriggerRequest) builder.instance;
                userVoiceSurveysLogging$TriggerContext3.getClass();
                userVoiceSurveysLogging$SurveyTriggerRequest.triggerContext_ = userVoiceSurveysLogging$TriggerContext3;
                userVoiceSurveysLogging$SurveyTriggerRequest.bitField0_ |= 1;
            }
            SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SurveyTriggerResponse.DEFAULT_INSTANCE.createBuilder();
            String str3 = service$SurveyTriggerResponse.noAvailableSurveyReason_;
            builder3.copyOnWrite();
            UserVoiceSurveysLogging$SurveyTriggerResponse userVoiceSurveysLogging$SurveyTriggerResponse = (UserVoiceSurveysLogging$SurveyTriggerResponse) builder3.instance;
            str3.getClass();
            userVoiceSurveysLogging$SurveyTriggerResponse.noAvailableSurveyReason_ = str3;
            String str4 = service$SurveyTriggerResponse.surveyId_;
            builder3.copyOnWrite();
            UserVoiceSurveysLogging$SurveyTriggerResponse userVoiceSurveysLogging$SurveyTriggerResponse2 = (UserVoiceSurveysLogging$SurveyTriggerResponse) builder3.instance;
            str4.getClass();
            userVoiceSurveysLogging$SurveyTriggerResponse2.surveyId_ = str4;
            if ((service$SurveyTriggerResponse.bitField0_ & 1) != 0) {
                Survey$Session survey$Session = service$SurveyTriggerResponse.session_;
                if (survey$Session == null) {
                    survey$Session = Survey$Session.DEFAULT_INSTANCE;
                }
                SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Session.DEFAULT_INSTANCE.createBuilder();
                String str5 = survey$Session.sessionId_;
                builder4.copyOnWrite();
                UserVoiceSurveysLogging$Session userVoiceSurveysLogging$Session = (UserVoiceSurveysLogging$Session) builder4.instance;
                str5.getClass();
                userVoiceSurveysLogging$Session.sessionId_ = str5;
                ByteString byteString = survey$Session.sessionToken_;
                builder4.copyOnWrite();
                UserVoiceSurveysLogging$Session userVoiceSurveysLogging$Session2 = (UserVoiceSurveysLogging$Session) builder4.instance;
                byteString.getClass();
                userVoiceSurveysLogging$Session2.sessionToken_ = byteString;
                builder3.copyOnWrite();
                UserVoiceSurveysLogging$SurveyTriggerResponse userVoiceSurveysLogging$SurveyTriggerResponse3 = (UserVoiceSurveysLogging$SurveyTriggerResponse) builder3.instance;
                UserVoiceSurveysLogging$Session userVoiceSurveysLogging$Session3 = (UserVoiceSurveysLogging$Session) builder4.build();
                userVoiceSurveysLogging$Session3.getClass();
                userVoiceSurveysLogging$SurveyTriggerResponse3.session_ = userVoiceSurveysLogging$Session3;
                userVoiceSurveysLogging$SurveyTriggerResponse3.bitField0_ |= 1;
            }
            if ((service$SurveyTriggerResponse.bitField0_ & 2) != 0) {
                Survey$Payload survey$Payload = service$SurveyTriggerResponse.surveyPayload_;
                if (survey$Payload == null) {
                    survey$Payload = Survey$Payload.DEFAULT_INSTANCE;
                }
                SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Payload.DEFAULT_INSTANCE.createBuilder();
                if ((survey$Payload.bitField0_ & 1) != 0) {
                    Survey$Invitation survey$Invitation = survey$Payload.invitation_;
                    if (survey$Invitation == null) {
                        survey$Invitation = Survey$Invitation.DEFAULT_INSTANCE;
                    }
                    SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Invitation.DEFAULT_INSTANCE.createBuilder();
                    boolean z2 = survey$Invitation.showInvitation_;
                    builder6.copyOnWrite();
                    ((UserVoiceSurveysLogging$Invitation) builder6.instance).showInvitation_ = z2;
                    String str6 = survey$Invitation.invitationMessage_;
                    builder6.copyOnWrite();
                    UserVoiceSurveysLogging$Invitation userVoiceSurveysLogging$Invitation = (UserVoiceSurveysLogging$Invitation) builder6.instance;
                    str6.getClass();
                    userVoiceSurveysLogging$Invitation.invitationMessage_ = str6;
                    builder5.copyOnWrite();
                    UserVoiceSurveysLogging$Payload userVoiceSurveysLogging$Payload = (UserVoiceSurveysLogging$Payload) builder5.instance;
                    UserVoiceSurveysLogging$Invitation userVoiceSurveysLogging$Invitation2 = (UserVoiceSurveysLogging$Invitation) builder6.build();
                    userVoiceSurveysLogging$Invitation2.getClass();
                    userVoiceSurveysLogging$Payload.invitation_ = userVoiceSurveysLogging$Invitation2;
                    userVoiceSurveysLogging$Payload.bitField0_ |= 1;
                }
                if ((survey$Payload.bitField0_ & 2) != 0) {
                    Survey$Completion survey$Completion = survey$Payload.completion_;
                    if (survey$Completion == null) {
                        survey$Completion = Survey$Completion.DEFAULT_INSTANCE;
                    }
                    SystemHealthProto$PackedHistogram.Builder builder7 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Completion.DEFAULT_INSTANCE.createBuilder();
                    String str7 = survey$Completion.completionText_;
                    builder7.copyOnWrite();
                    UserVoiceSurveysLogging$Completion userVoiceSurveysLogging$Completion = (UserVoiceSurveysLogging$Completion) builder7.instance;
                    str7.getClass();
                    userVoiceSurveysLogging$Completion.completionText_ = str7;
                    String str8 = survey$Completion.followUpText_;
                    builder7.copyOnWrite();
                    UserVoiceSurveysLogging$Completion userVoiceSurveysLogging$Completion2 = (UserVoiceSurveysLogging$Completion) builder7.instance;
                    str8.getClass();
                    userVoiceSurveysLogging$Completion2.followUpText_ = str8;
                    String str9 = survey$Completion.followUpUrl_;
                    builder7.copyOnWrite();
                    UserVoiceSurveysLogging$Completion userVoiceSurveysLogging$Completion3 = (UserVoiceSurveysLogging$Completion) builder7.instance;
                    str9.getClass();
                    userVoiceSurveysLogging$Completion3.followUpUrl_ = str9;
                    CurrentProcess currentProcess2 = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (FlagsUtil.isFeatureEnabled(HatsV1M3Features.enableUserPromptedSurveys(FlagsUtil.phenotypeContext)) && survey$Completion.allowedCompletionStyle_.size() > 0) {
                        Internal.IntList intList = survey$Completion.allowedCompletionStyle_;
                        builder7.copyOnWrite();
                        UserVoiceSurveysLogging$Completion userVoiceSurveysLogging$Completion4 = (UserVoiceSurveysLogging$Completion) builder7.instance;
                        Internal.IntList intList2 = userVoiceSurveysLogging$Completion4.allowedCompletionStyle_;
                        if (!intList2.isModifiable()) {
                            userVoiceSurveysLogging$Completion4.allowedCompletionStyle_ = GeneratedMessageLite.mutableCopy(intList2);
                        }
                        Iterator<E> it = intList.iterator();
                        while (it.hasNext()) {
                            userVoiceSurveysLogging$Completion4.allowedCompletionStyle_.addInt(((Integer) it.next()).intValue());
                        }
                    }
                    builder5.copyOnWrite();
                    UserVoiceSurveysLogging$Payload userVoiceSurveysLogging$Payload2 = (UserVoiceSurveysLogging$Payload) builder5.instance;
                    UserVoiceSurveysLogging$Completion userVoiceSurveysLogging$Completion5 = (UserVoiceSurveysLogging$Completion) builder7.build();
                    userVoiceSurveysLogging$Completion5.getClass();
                    userVoiceSurveysLogging$Payload2.completion_ = userVoiceSurveysLogging$Completion5;
                    userVoiceSurveysLogging$Payload2.bitField0_ |= 2;
                }
                if ((survey$Payload.bitField0_ & 4) != 0) {
                    Survey$DisplaySettings survey$DisplaySettings = survey$Payload.displaySettings_;
                    if (survey$DisplaySettings == null) {
                        survey$DisplaySettings = Survey$DisplaySettings.DEFAULT_INSTANCE;
                    }
                    SystemHealthProto$PackedHistogram.Builder builder8 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$DisplaySettings.DEFAULT_INSTANCE.createBuilder();
                    int i3 = survey$DisplaySettings.surveyLogo_;
                    builder8.copyOnWrite();
                    ((UserVoiceSurveysLogging$DisplaySettings) builder8.instance).surveyLogo_ = i3;
                    if ((survey$DisplaySettings.bitField0_ & 1) != 0) {
                        Survey$DisplaySettings.PromptDelay promptDelay = survey$DisplaySettings.promptDelay_;
                        if (promptDelay == null) {
                            promptDelay = Survey$DisplaySettings.PromptDelay.DEFAULT_INSTANCE;
                        }
                        SystemHealthProto$PackedHistogram.Builder builder9 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$DisplaySettings.PromptDelay.DEFAULT_INSTANCE.createBuilder();
                        Duration duration = promptDelay.minDelay_;
                        if (duration == null) {
                            duration = Duration.DEFAULT_INSTANCE;
                        }
                        builder9.copyOnWrite();
                        UserVoiceSurveysLogging$DisplaySettings.PromptDelay promptDelay2 = (UserVoiceSurveysLogging$DisplaySettings.PromptDelay) builder9.instance;
                        duration.getClass();
                        promptDelay2.minDelay_ = duration;
                        promptDelay2.bitField0_ |= 1;
                        Duration duration2 = promptDelay.maxDelay_;
                        if (duration2 == null) {
                            duration2 = Duration.DEFAULT_INSTANCE;
                        }
                        builder9.copyOnWrite();
                        UserVoiceSurveysLogging$DisplaySettings.PromptDelay promptDelay3 = (UserVoiceSurveysLogging$DisplaySettings.PromptDelay) builder9.instance;
                        duration2.getClass();
                        promptDelay3.maxDelay_ = duration2;
                        promptDelay3.bitField0_ |= 2;
                        builder8.copyOnWrite();
                        UserVoiceSurveysLogging$DisplaySettings userVoiceSurveysLogging$DisplaySettings = (UserVoiceSurveysLogging$DisplaySettings) builder8.instance;
                        UserVoiceSurveysLogging$DisplaySettings.PromptDelay promptDelay4 = (UserVoiceSurveysLogging$DisplaySettings.PromptDelay) builder9.build();
                        promptDelay4.getClass();
                        userVoiceSurveysLogging$DisplaySettings.promptDelay_ = promptDelay4;
                        userVoiceSurveysLogging$DisplaySettings.bitField0_ |= 1;
                    }
                    CurrentProcess currentProcess3 = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (FlagsUtil.isFeatureEnabled(HatsV1M3Features.enableUserPromptedSurveys(FlagsUtil.phenotypeContext)) && survey$DisplaySettings.allowedPromptStyle_.size() > 0) {
                        Internal.IntList intList3 = survey$DisplaySettings.allowedPromptStyle_;
                        builder8.copyOnWrite();
                        UserVoiceSurveysLogging$DisplaySettings userVoiceSurveysLogging$DisplaySettings2 = (UserVoiceSurveysLogging$DisplaySettings) builder8.instance;
                        Internal.IntList intList4 = userVoiceSurveysLogging$DisplaySettings2.allowedPromptStyle_;
                        if (!intList4.isModifiable()) {
                            userVoiceSurveysLogging$DisplaySettings2.allowedPromptStyle_ = GeneratedMessageLite.mutableCopy(intList4);
                        }
                        Iterator<E> it2 = intList3.iterator();
                        while (it2.hasNext()) {
                            userVoiceSurveysLogging$DisplaySettings2.allowedPromptStyle_.addInt(((Integer) it2.next()).intValue());
                        }
                    }
                    builder5.copyOnWrite();
                    UserVoiceSurveysLogging$Payload userVoiceSurveysLogging$Payload3 = (UserVoiceSurveysLogging$Payload) builder5.instance;
                    UserVoiceSurveysLogging$DisplaySettings userVoiceSurveysLogging$DisplaySettings3 = (UserVoiceSurveysLogging$DisplaySettings) builder8.build();
                    userVoiceSurveysLogging$DisplaySettings3.getClass();
                    userVoiceSurveysLogging$Payload3.displaySettings_ = userVoiceSurveysLogging$DisplaySettings3;
                    userVoiceSurveysLogging$Payload3.bitField0_ |= 4;
                }
                if ((survey$Payload.bitField0_ & 8) != 0) {
                    Survey$PrivacySettings survey$PrivacySettings = survey$Payload.privacySettings_;
                    if (survey$PrivacySettings == null) {
                        survey$PrivacySettings = Survey$PrivacySettings.DEFAULT_INSTANCE;
                    }
                    SystemHealthProto$PackedHistogram.Builder builder10 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$PrivacySettings.DEFAULT_INSTANCE.createBuilder();
                    boolean z3 = survey$PrivacySettings.piiCollectionEnabled_;
                    builder10.copyOnWrite();
                    ((UserVoiceSurveysLogging$PrivacySettings) builder10.instance).piiCollectionEnabled_ = z3;
                    boolean z4 = survey$PrivacySettings.productCallbacksEnabled_;
                    builder10.copyOnWrite();
                    ((UserVoiceSurveysLogging$PrivacySettings) builder10.instance).productCallbacksEnabled_ = z4;
                    builder5.copyOnWrite();
                    UserVoiceSurveysLogging$Payload userVoiceSurveysLogging$Payload4 = (UserVoiceSurveysLogging$Payload) builder5.instance;
                    UserVoiceSurveysLogging$PrivacySettings userVoiceSurveysLogging$PrivacySettings = (UserVoiceSurveysLogging$PrivacySettings) builder10.build();
                    userVoiceSurveysLogging$PrivacySettings.getClass();
                    userVoiceSurveysLogging$Payload4.privacySettings_ = userVoiceSurveysLogging$PrivacySettings;
                    userVoiceSurveysLogging$Payload4.bitField0_ |= 8;
                }
                if (survey$Payload.question_.size() > 0) {
                    for (Survey$Question survey$Question : survey$Payload.question_) {
                        SystemHealthProto$PackedHistogram.Builder builder11 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Question.DEFAULT_INSTANCE.createBuilder();
                        int i4 = survey$Question.questionOrdinal_;
                        builder11.copyOnWrite();
                        ((UserVoiceSurveysLogging$Question) builder11.instance).questionOrdinal_ = i4;
                        String str10 = survey$Question.questionText_;
                        builder11.copyOnWrite();
                        UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question = (UserVoiceSurveysLogging$Question) builder11.instance;
                        str10.getClass();
                        userVoiceSurveysLogging$Question.questionText_ = str10;
                        String str11 = survey$Question.questionHtml_;
                        builder11.copyOnWrite();
                        UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question2 = (UserVoiceSurveysLogging$Question) builder11.instance;
                        str11.getClass();
                        userVoiceSurveysLogging$Question2.questionHtml_ = str11;
                        int i5 = survey$Question.questionType_;
                        builder11.copyOnWrite();
                        ((UserVoiceSurveysLogging$Question) builder11.instance).questionType_ = i5;
                        boolean z5 = survey$Question.screeningEnabled_;
                        builder11.copyOnWrite();
                        ((UserVoiceSurveysLogging$Question) builder11.instance).screeningEnabled_ = z5;
                        if (survey$Question.textSubstitution_.size() > 0) {
                            for (Survey$TextSubstitution survey$TextSubstitution : survey$Question.textSubstitution_) {
                                SystemHealthProto$PackedHistogram.Builder builder12 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$TextSubstitution.DEFAULT_INSTANCE.createBuilder();
                                String str12 = survey$TextSubstitution.matchText_;
                                builder12.copyOnWrite();
                                UserVoiceSurveysLogging$TextSubstitution userVoiceSurveysLogging$TextSubstitution = (UserVoiceSurveysLogging$TextSubstitution) builder12.instance;
                                str12.getClass();
                                userVoiceSurveysLogging$TextSubstitution.matchText_ = str12;
                                if (survey$TextSubstitution.replacementOperationCase_ == 2) {
                                    SystemHealthProto$PackedHistogram.Builder builder13 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$TextSubstitution.AnswerPipe.DEFAULT_INSTANCE.createBuilder();
                                    if (survey$TextSubstitution.replacementOperationCase_ == 2) {
                                        answerPipe = (Survey$TextSubstitution.AnswerPipe) survey$TextSubstitution.replacementOperation_;
                                    } else {
                                        answerPipe = Survey$TextSubstitution.AnswerPipe.DEFAULT_INSTANCE;
                                    }
                                    int i6 = answerPipe.fromQuestionOrdinal_;
                                    builder13.copyOnWrite();
                                    ((UserVoiceSurveysLogging$TextSubstitution.AnswerPipe) builder13.instance).fromQuestionOrdinal_ = i6;
                                    builder12.copyOnWrite();
                                    UserVoiceSurveysLogging$TextSubstitution userVoiceSurveysLogging$TextSubstitution2 = (UserVoiceSurveysLogging$TextSubstitution) builder12.instance;
                                    UserVoiceSurveysLogging$TextSubstitution.AnswerPipe answerPipe2 = (UserVoiceSurveysLogging$TextSubstitution.AnswerPipe) builder13.build();
                                    answerPipe2.getClass();
                                    userVoiceSurveysLogging$TextSubstitution2.replacementOperation_ = answerPipe2;
                                    userVoiceSurveysLogging$TextSubstitution2.replacementOperationCase_ = 2;
                                }
                                builder11.copyOnWrite();
                                UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question3 = (UserVoiceSurveysLogging$Question) builder11.instance;
                                UserVoiceSurveysLogging$TextSubstitution userVoiceSurveysLogging$TextSubstitution3 = (UserVoiceSurveysLogging$TextSubstitution) builder12.build();
                                userVoiceSurveysLogging$TextSubstitution3.getClass();
                                Internal.ProtobufList protobufList3 = userVoiceSurveysLogging$Question3.textSubstitution_;
                                if (!protobufList3.isModifiable()) {
                                    userVoiceSurveysLogging$Question3.textSubstitution_ = GeneratedMessageLite.mutableCopy(protobufList3);
                                }
                                userVoiceSurveysLogging$Question3.textSubstitution_.add(userVoiceSurveysLogging$TextSubstitution3);
                            }
                        }
                        int i7 = survey$Question.questionCase_;
                        if (i7 == 0) {
                            i2 = Survey$Question.QuestionCase.QUESTION_NOT_SET$ar$edu;
                        } else if (i7 == 4) {
                            i2 = Survey$Question.QuestionCase.SINGLE_SELECT$ar$edu;
                        } else if (i7 == 5) {
                            i2 = Survey$Question.QuestionCase.MULTI_SELECT$ar$edu;
                        } else if (i7 != 6) {
                            i2 = i7 != 7 ? 0 : Survey$Question.QuestionCase.OPEN_TEXT$ar$edu$c9b707fe_0;
                        } else {
                            i2 = Survey$Question.QuestionCase.RATING$ar$edu$c9b707fe_0;
                        }
                        int i8 = i2 - 1;
                        if (i2 != 0) {
                            if (i8 == 0) {
                                if (survey$Question.questionCase_ == 4) {
                                    survey$SingleSelect = (Survey$SingleSelect) survey$Question.question_;
                                } else {
                                    survey$SingleSelect = Survey$SingleSelect.DEFAULT_INSTANCE;
                                }
                                SystemHealthProto$PackedHistogram.Builder builder14 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$SingleSelect.DEFAULT_INSTANCE.createBuilder();
                                int i9 = survey$SingleSelect.iconType_;
                                builder14.copyOnWrite();
                                ((UserVoiceSurveysLogging$SingleSelect) builder14.instance).iconType_ = i9;
                                if ((survey$SingleSelect.bitField0_ & 1) != 0) {
                                    Survey$AnswerChoices survey$AnswerChoices = survey$SingleSelect.answerChoices_;
                                    if (survey$AnswerChoices == null) {
                                        survey$AnswerChoices = Survey$AnswerChoices.DEFAULT_INSTANCE;
                                    }
                                    UserVoiceSurveysLogging$AnswerChoices convertAnswerChoices = convertAnswerChoices(survey$AnswerChoices);
                                    builder14.copyOnWrite();
                                    UserVoiceSurveysLogging$SingleSelect userVoiceSurveysLogging$SingleSelect = (UserVoiceSurveysLogging$SingleSelect) builder14.instance;
                                    convertAnswerChoices.getClass();
                                    userVoiceSurveysLogging$SingleSelect.answerChoices_ = convertAnswerChoices;
                                    userVoiceSurveysLogging$SingleSelect.bitField0_ |= 1;
                                }
                                builder11.copyOnWrite();
                                UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question4 = (UserVoiceSurveysLogging$Question) builder11.instance;
                                UserVoiceSurveysLogging$SingleSelect userVoiceSurveysLogging$SingleSelect2 = (UserVoiceSurveysLogging$SingleSelect) builder14.build();
                                userVoiceSurveysLogging$SingleSelect2.getClass();
                                userVoiceSurveysLogging$Question4.question_ = userVoiceSurveysLogging$SingleSelect2;
                                userVoiceSurveysLogging$Question4.questionCase_ = 4;
                            } else if (i8 == 1) {
                                if (survey$Question.questionCase_ == 5) {
                                    survey$MultiSelect = (Survey$MultiSelect) survey$Question.question_;
                                } else {
                                    survey$MultiSelect = Survey$MultiSelect.DEFAULT_INSTANCE;
                                }
                                SystemHealthProto$PackedHistogram.Builder builder15 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$MultiSelect.DEFAULT_INSTANCE.createBuilder();
                                if ((survey$MultiSelect.bitField0_ & 1) != 0) {
                                    Survey$AnswerChoices survey$AnswerChoices2 = survey$MultiSelect.answerChoices_;
                                    if (survey$AnswerChoices2 == null) {
                                        survey$AnswerChoices2 = Survey$AnswerChoices.DEFAULT_INSTANCE;
                                    }
                                    UserVoiceSurveysLogging$AnswerChoices convertAnswerChoices2 = convertAnswerChoices(survey$AnswerChoices2);
                                    builder15.copyOnWrite();
                                    UserVoiceSurveysLogging$MultiSelect userVoiceSurveysLogging$MultiSelect = (UserVoiceSurveysLogging$MultiSelect) builder15.instance;
                                    convertAnswerChoices2.getClass();
                                    userVoiceSurveysLogging$MultiSelect.answerChoices_ = convertAnswerChoices2;
                                    userVoiceSurveysLogging$MultiSelect.bitField0_ |= 1;
                                }
                                builder11.copyOnWrite();
                                UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question5 = (UserVoiceSurveysLogging$Question) builder11.instance;
                                UserVoiceSurveysLogging$MultiSelect userVoiceSurveysLogging$MultiSelect2 = (UserVoiceSurveysLogging$MultiSelect) builder15.build();
                                userVoiceSurveysLogging$MultiSelect2.getClass();
                                userVoiceSurveysLogging$Question5.question_ = userVoiceSurveysLogging$MultiSelect2;
                                userVoiceSurveysLogging$Question5.questionCase_ = 5;
                            } else if (i8 == 2) {
                                if (survey$Question.questionCase_ == 6) {
                                    survey$Rating = (Survey$Rating) survey$Question.question_;
                                } else {
                                    survey$Rating = Survey$Rating.DEFAULT_INSTANCE;
                                }
                                SystemHealthProto$PackedHistogram.Builder builder16 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$Rating.DEFAULT_INSTANCE.createBuilder();
                                int i10 = survey$Rating.ratingType_;
                                builder16.copyOnWrite();
                                ((UserVoiceSurveysLogging$Rating) builder16.instance).ratingType_ = i10;
                                int i11 = survey$Rating.numRatingChoices_;
                                builder16.copyOnWrite();
                                ((UserVoiceSurveysLogging$Rating) builder16.instance).numRatingChoices_ = i11;
                                String str13 = survey$Rating.minOrdinalLabel_;
                                builder16.copyOnWrite();
                                UserVoiceSurveysLogging$Rating userVoiceSurveysLogging$Rating = (UserVoiceSurveysLogging$Rating) builder16.instance;
                                str13.getClass();
                                userVoiceSurveysLogging$Rating.minOrdinalLabel_ = str13;
                                String str14 = survey$Rating.maxOrdinalLabel_;
                                builder16.copyOnWrite();
                                UserVoiceSurveysLogging$Rating userVoiceSurveysLogging$Rating2 = (UserVoiceSurveysLogging$Rating) builder16.instance;
                                str14.getClass();
                                userVoiceSurveysLogging$Rating2.maxOrdinalLabel_ = str14;
                                if (survey$Rating.screenInOrdinal_.size() > 0) {
                                    Internal.IntList intList5 = survey$Rating.screenInOrdinal_;
                                    builder16.copyOnWrite();
                                    UserVoiceSurveysLogging$Rating userVoiceSurveysLogging$Rating3 = (UserVoiceSurveysLogging$Rating) builder16.instance;
                                    Internal.IntList intList6 = userVoiceSurveysLogging$Rating3.screenInOrdinal_;
                                    if (!intList6.isModifiable()) {
                                        userVoiceSurveysLogging$Rating3.screenInOrdinal_ = GeneratedMessageLite.mutableCopy(intList6);
                                    }
                                    AbstractMessageLite.addAll(intList5, userVoiceSurveysLogging$Rating3.screenInOrdinal_);
                                }
                                builder11.copyOnWrite();
                                UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question6 = (UserVoiceSurveysLogging$Question) builder11.instance;
                                UserVoiceSurveysLogging$Rating userVoiceSurveysLogging$Rating4 = (UserVoiceSurveysLogging$Rating) builder16.build();
                                userVoiceSurveysLogging$Rating4.getClass();
                                userVoiceSurveysLogging$Question6.question_ = userVoiceSurveysLogging$Rating4;
                                userVoiceSurveysLogging$Question6.questionCase_ = 6;
                            } else if (i8 == 3) {
                                if (survey$Question.questionCase_ == 7) {
                                    survey$OpenText = (Survey$OpenText) survey$Question.question_;
                                } else {
                                    survey$OpenText = Survey$OpenText.DEFAULT_INSTANCE;
                                }
                                SystemHealthProto$PackedHistogram.Builder builder17 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$OpenText.DEFAULT_INSTANCE.createBuilder();
                                String str15 = survey$OpenText.placeholder_;
                                builder17.copyOnWrite();
                                UserVoiceSurveysLogging$OpenText userVoiceSurveysLogging$OpenText = (UserVoiceSurveysLogging$OpenText) builder17.instance;
                                str15.getClass();
                                userVoiceSurveysLogging$OpenText.placeholder_ = str15;
                                String str16 = survey$OpenText.footer_;
                                builder17.copyOnWrite();
                                UserVoiceSurveysLogging$OpenText userVoiceSurveysLogging$OpenText2 = (UserVoiceSurveysLogging$OpenText) builder17.instance;
                                str16.getClass();
                                userVoiceSurveysLogging$OpenText2.footer_ = str16;
                                builder11.copyOnWrite();
                                UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question7 = (UserVoiceSurveysLogging$Question) builder11.instance;
                                UserVoiceSurveysLogging$OpenText userVoiceSurveysLogging$OpenText3 = (UserVoiceSurveysLogging$OpenText) builder17.build();
                                userVoiceSurveysLogging$OpenText3.getClass();
                                userVoiceSurveysLogging$Question7.question_ = userVoiceSurveysLogging$OpenText3;
                                userVoiceSurveysLogging$Question7.questionCase_ = 7;
                            }
                            builder5.copyOnWrite();
                            UserVoiceSurveysLogging$Payload userVoiceSurveysLogging$Payload5 = (UserVoiceSurveysLogging$Payload) builder5.instance;
                            UserVoiceSurveysLogging$Question userVoiceSurveysLogging$Question8 = (UserVoiceSurveysLogging$Question) builder11.build();
                            userVoiceSurveysLogging$Question8.getClass();
                            Internal.ProtobufList protobufList4 = userVoiceSurveysLogging$Payload5.question_;
                            if (!protobufList4.isModifiable()) {
                                userVoiceSurveysLogging$Payload5.question_ = GeneratedMessageLite.mutableCopy(protobufList4);
                            }
                            userVoiceSurveysLogging$Payload5.question_.add(userVoiceSurveysLogging$Question8);
                        } else {
                            throw null;
                        }
                    }
                }
                if (survey$Payload.requiredCapability_.size() > 0) {
                    Iterator<E> it3 = survey$Payload.requiredCapability_.iterator();
                    while (it3.hasNext()) {
                        int intValue = ((Integer) it3.next()).intValue();
                        builder5.copyOnWrite();
                        UserVoiceSurveysLogging$Payload userVoiceSurveysLogging$Payload6 = (UserVoiceSurveysLogging$Payload) builder5.instance;
                        Internal.IntList intList7 = userVoiceSurveysLogging$Payload6.requiredCapability_;
                        if (!intList7.isModifiable()) {
                            userVoiceSurveysLogging$Payload6.requiredCapability_ = GeneratedMessageLite.mutableCopy(intList7);
                        }
                        userVoiceSurveysLogging$Payload6.requiredCapability_.addInt(intValue);
                    }
                }
                builder3.copyOnWrite();
                UserVoiceSurveysLogging$SurveyTriggerResponse userVoiceSurveysLogging$SurveyTriggerResponse4 = (UserVoiceSurveysLogging$SurveyTriggerResponse) builder3.instance;
                UserVoiceSurveysLogging$Payload userVoiceSurveysLogging$Payload7 = (UserVoiceSurveysLogging$Payload) builder5.build();
                userVoiceSurveysLogging$Payload7.getClass();
                userVoiceSurveysLogging$SurveyTriggerResponse4.payload_ = userVoiceSurveysLogging$Payload7;
                userVoiceSurveysLogging$SurveyTriggerResponse4.bitField0_ |= 2;
            }
            if (service$SurveyTriggerResponse.error_.size() > 0) {
                for (String str17 : service$SurveyTriggerResponse.error_) {
                    switch (str17.hashCode()) {
                        case -2076636191:
                            if (str17.equals("FAILED_TO_FETCH_SURVEY")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -2056938406:
                            if (str17.equals("UNSUPPORTED_CRONET_ENGINE")) {
                                c = 4;
                                break;
                            }
                            break;
                        case -1938530698:
                            if (str17.equals("BACKEND_TIMEOUT")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 1577734254:
                            if (str17.equals("NO_AVAILABLE_SURVEY")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 2076341913:
                            if (str17.equals("TRIGGER_ID_NOT_SET")) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                    c = 65535;
                    if (c == 0) {
                        i = UserVoiceSurveysLogging$SurveyTriggerResponse.TriggerError.BACKEND_TIMEOUT$ar$edu;
                    } else if (c == 1) {
                        i = UserVoiceSurveysLogging$SurveyTriggerResponse.TriggerError.FAILED_TO_FETCH_SURVEY$ar$edu;
                    } else if (c == 2) {
                        i = UserVoiceSurveysLogging$SurveyTriggerResponse.TriggerError.NO_AVAILABLE_SURVEY$ar$edu;
                    } else if (c != 3) {
                        i = c != 4 ? UserVoiceSurveysLogging$SurveyTriggerResponse.TriggerError.UNKNOWN$ar$edu$5d5b468a_0 : UserVoiceSurveysLogging$SurveyTriggerResponse.TriggerError.UNSUPPORTED_CRONET_ENGINE$ar$edu;
                    } else {
                        i = UserVoiceSurveysLogging$SurveyTriggerResponse.TriggerError.TRIGGER_ID_NOT_SET$ar$edu;
                    }
                    builder3.copyOnWrite();
                    UserVoiceSurveysLogging$SurveyTriggerResponse userVoiceSurveysLogging$SurveyTriggerResponse5 = (UserVoiceSurveysLogging$SurveyTriggerResponse) builder3.instance;
                    if (i != 0) {
                        Internal.IntList intList8 = userVoiceSurveysLogging$SurveyTriggerResponse5.triggerError_;
                        if (!intList8.isModifiable()) {
                            userVoiceSurveysLogging$SurveyTriggerResponse5.triggerError_ = GeneratedMessageLite.mutableCopy(intList8);
                        }
                        userVoiceSurveysLogging$SurveyTriggerResponse5.triggerError_.addInt(UserVoiceSurveysLogging$SurveyTriggerResponse.TriggerError.getNumber$ar$edu$5d5b468a_0(i));
                    } else {
                        throw null;
                    }
                }
            }
            MetricsLogger metricsLogger = MetricsLogger.getInstance();
            SystemHealthProto$PackedHistogram.Builder builder18 = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$HttpEvent.DEFAULT_INSTANCE.createBuilder();
            builder18.copyOnWrite();
            UserVoiceSurveysLogging$HttpEvent userVoiceSurveysLogging$HttpEvent = (UserVoiceSurveysLogging$HttpEvent) builder18.instance;
            UserVoiceSurveysLogging$SurveyTriggerRequest userVoiceSurveysLogging$SurveyTriggerRequest2 = (UserVoiceSurveysLogging$SurveyTriggerRequest) builder.build();
            userVoiceSurveysLogging$SurveyTriggerRequest2.getClass();
            userVoiceSurveysLogging$HttpEvent.request_ = userVoiceSurveysLogging$SurveyTriggerRequest2;
            userVoiceSurveysLogging$HttpEvent.requestCase_ = 2;
            builder18.copyOnWrite();
            UserVoiceSurveysLogging$HttpEvent userVoiceSurveysLogging$HttpEvent2 = (UserVoiceSurveysLogging$HttpEvent) builder18.instance;
            UserVoiceSurveysLogging$SurveyTriggerResponse userVoiceSurveysLogging$SurveyTriggerResponse6 = (UserVoiceSurveysLogging$SurveyTriggerResponse) builder3.build();
            userVoiceSurveysLogging$SurveyTriggerResponse6.getClass();
            userVoiceSurveysLogging$HttpEvent2.response_ = userVoiceSurveysLogging$SurveyTriggerResponse6;
            userVoiceSurveysLogging$HttpEvent2.responseCase_ = 4;
            metricsLogger.reportHttpEvent((UserVoiceSurveysLogging$HttpEvent) builder18.build(), stopwatch.getStart(), stopwatch.getElapsed(), context, str);
        }
    }

    public static void reportUserEventForAccountAndSystemInfoLinkClicked(Stopwatch stopwatch, Context context, String str) {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
            return;
        }
        MetricsLogger metricsLogger = MetricsLogger.getInstance();
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$UserEvent.DEFAULT_INSTANCE.createBuilder();
        int i = UserVoiceSurveysLogging$UserEvent.EventTrigger.CLICK$ar$edu;
        builder.copyOnWrite();
        ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventTrigger_ = UserVoiceSurveysLogging$UserEvent.EventTrigger.getNumber$ar$edu$5137a177_0(i);
        int i2 = UserVoiceSurveysLogging$UserEvent.EventType.ACCOUNT_AND_SYSTEM_INFO_LINK_CLICKED$ar$edu;
        builder.copyOnWrite();
        ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventType_ = UserVoiceSurveysLogging$UserEvent.EventType.getNumber$ar$edu$62b19512_0(i2);
        metricsLogger.reportUserEvent((UserVoiceSurveysLogging$UserEvent) builder.build(), stopwatch.getStart(), stopwatch.getElapsed(), context, str);
    }

    public static void reportUserEventForCloseButtonClicked(Stopwatch stopwatch, Context context, String str) {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
            return;
        }
        MetricsLogger metricsLogger = MetricsLogger.getInstance();
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$UserEvent.DEFAULT_INSTANCE.createBuilder();
        int i = UserVoiceSurveysLogging$UserEvent.EventTrigger.CLICK$ar$edu;
        builder.copyOnWrite();
        ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventTrigger_ = UserVoiceSurveysLogging$UserEvent.EventTrigger.getNumber$ar$edu$5137a177_0(i);
        int i2 = UserVoiceSurveysLogging$UserEvent.EventType.CLOSE_BUTTON_CLICKED$ar$edu;
        builder.copyOnWrite();
        ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventType_ = UserVoiceSurveysLogging$UserEvent.EventType.getNumber$ar$edu$62b19512_0(i2);
        metricsLogger.reportUserEvent((UserVoiceSurveysLogging$UserEvent) builder.build(), stopwatch.getStart(), stopwatch.getElapsed(), context, str);
    }

    public static void reportUserEventForNextButtonClicked(Stopwatch stopwatch, Context context, String str) {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isFeatureEnabled(Clearcut.enableLoggingViaClearcut(FlagsUtil.phenotypeContext))) {
            return;
        }
        MetricsLogger metricsLogger = MetricsLogger.getInstance();
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$UserEvent.DEFAULT_INSTANCE.createBuilder();
        int i = UserVoiceSurveysLogging$UserEvent.EventTrigger.CLICK$ar$edu;
        builder.copyOnWrite();
        ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventTrigger_ = UserVoiceSurveysLogging$UserEvent.EventTrigger.getNumber$ar$edu$5137a177_0(i);
        int i2 = UserVoiceSurveysLogging$UserEvent.EventType.NEXT_BUTTON_CLICKED$ar$edu;
        builder.copyOnWrite();
        ((UserVoiceSurveysLogging$UserEvent) builder.instance).eventType_ = UserVoiceSurveysLogging$UserEvent.EventType.getNumber$ar$edu$62b19512_0(i2);
        metricsLogger.reportUserEvent((UserVoiceSurveysLogging$UserEvent) builder.build(), stopwatch.getStart(), stopwatch.getElapsed(), context, str);
    }

    private static void setSpan(Spannable spannable, String str, ClickableSpan clickableSpan) {
        int indexOf = spannable.toString().indexOf(str);
        int length = str.length() + indexOf;
        if (indexOf >= 0) {
            spannable.setSpan(clickableSpan, indexOf, length, 0);
        }
    }

    public final void log(Level level, Executor executor, String str, Object... objArr) {
        logInternal$ar$ds(level, executor, null, str, objArr);
    }

    public DefaultExperimentTokenDecorator(byte[] bArr) {
    }
}

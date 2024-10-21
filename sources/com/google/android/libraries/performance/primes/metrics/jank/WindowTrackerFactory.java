package com.google.android.libraries.performance.primes.metrics.jank;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.collection.ArraySet;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.talkback.TalkBackPreferencesActivity;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.overlayui.ChainAnimatorCoroutineImpl$chainAnimations$1;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsDBHelper;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.talkback.labeling.CustomLabelManager;
import com.google.android.accessibility.talkback.labeling.DirectLabelFetchRequest;
import com.google.android.accessibility.talkback.labeling.LabelDialogManager$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.labeling.LabelDialogManager$BaseEditLabelDialog;
import com.google.android.accessibility.talkback.labeling.LabelTask;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.talkback.logging.TrainingMetricHolder;
import com.google.android.accessibility.talkback.menurules.NodeMenuRule;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.accessibility.talkback.permission.PermissionRequestActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.preference.base.VerbosityPrefFragment;
import com.google.android.accessibility.talkback.training.OnboardingConfigs;
import com.google.android.accessibility.talkback.training.TutorialConfigUtils;
import com.google.android.accessibility.talkback.training.VoiceCommandAndHelpConfigs;
import com.google.android.accessibility.talkback.trainingcommon.NavigationButtonBar;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda6;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingMetricStore$Type;
import com.google.android.accessibility.talkback.trainingcommon.tv.VendorConfig;
import com.google.android.accessibility.talkback.trainingcommon.tv.VendorConfigReader;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.accessibility.utils.output.FeedbackController;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.gms.clearcut.PIILevel;
import com.google.android.gms.clearcut.RestrictedByteStringClearcutLogger;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpClient;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.battery.HashingNameSanitizer;
import com.google.android.libraries.performance.primes.metrics.timer.TimerEvent;
import com.google.android.libraries.phenotype.client.stable.FlagUpdateInfo;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.marvin.talkback.R;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationLoadLogEvent;
import com.google.protobuf.ByteString;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$QosTierConfiguration$QosTier;
import io.grpc.internal.RetryingNameResolver;
import j$.util.DesugarCollections;
import j$.util.Optional;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.ExecutorCoroutineDispatcherImpl;
import org.chromium.base.BundleUtils$$ExternalSyntheticApiModelOutline0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowTrackerFactory {
    public static WindowTrackerFactory trainingActivityInterfaceInjector$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Object WindowTrackerFactory$ar$executorProvider;
    public final Object WindowTrackerFactory$ar$handlerProvider;

    public WindowTrackerFactory(Activity activity) {
        this.WindowTrackerFactory$ar$executorProvider = activity;
        this.WindowTrackerFactory$ar$handlerProvider = new ConfigurationsModule$$ExternalSyntheticLambda0(activity, 1);
    }

    public static boolean addLabel$ar$ds(Context context, final String str, final Pipeline.FeedbackReturner feedbackReturner) {
        if (context == null) {
            return false;
        }
        WindowTrackerFactory windowTrackerFactory = new WindowTrackerFactory(context, (HapticPatternParser$$ExternalSyntheticLambda1) null);
        Object obj = windowTrackerFactory.WindowTrackerFactory$ar$executorProvider;
        final CustomLabelManager customLabelManager = (CustomLabelManager) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
        final Context context2 = (Context) obj;
        LabelDialogManager$BaseEditLabelDialog labelDialogManager$BaseEditLabelDialog = new LabelDialogManager$BaseEditLabelDialog(context2, str, customLabelManager, feedbackReturner) { // from class: com.google.android.accessibility.talkback.labeling.LabelDialogManager$AddLabelDialog
            private final String resourceName;

            {
                this.resourceName = str;
            }

            @Override // com.google.android.accessibility.talkback.labeling.LabelDialogManager$BaseEditLabelDialog
            protected final String getCustomizedMessage() {
                return this.context.getString(R.string.label_dialog_text, this.resourceName);
            }

            @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
            public final String getMessageString() {
                return null;
            }

            @Override // com.google.android.accessibility.talkback.labeling.LabelDialogManager$BaseEditLabelDialog
            public final void onPositiveAction() {
                EditText editText = this.editField;
                if (editText != null) {
                    this.labelManager.addLabel(this.resourceName, editText.getText().toString());
                }
            }
        };
        labelDialogManager$BaseEditLabelDialog.setSoftInputMode(true);
        labelDialogManager$BaseEditLabelDialog.setRestoreFocus(true);
        labelDialogManager$BaseEditLabelDialog.showDialog();
        labelDialogManager$BaseEditLabelDialog.setButtonEnabled(-1, false);
        return true;
    }

    public static boolean editLabel$ar$class_merging$ar$class_merging(Context context, long j, boolean z, Pipeline.FeedbackReturner feedbackReturner, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        if (context == null || j == -1) {
            return false;
        }
        WindowTrackerFactory windowTrackerFactory = new WindowTrackerFactory(context, hapticPatternParser$$ExternalSyntheticLambda1);
        Object obj = windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
        LabelDialogManager$$ExternalSyntheticLambda0 labelDialogManager$$ExternalSyntheticLambda0 = new LabelDialogManager$$ExternalSyntheticLambda0(windowTrackerFactory, feedbackReturner, z);
        if (((TalkBackLabelManager) obj).isInitialized()) {
            CustomLabelManager customLabelManager = (CustomLabelManager) obj;
            new LabelTask(new DirectLabelFetchRequest(customLabelManager.client$ar$class_merging$ae701839_0, j, labelDialogManager$$ExternalSyntheticLambda0), customLabelManager.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
            return true;
        }
        return true;
    }

    public static WindowTrackerFactory getInstance$ar$class_merging$14dd424f_0$ar$class_merging$ar$class_merging() {
        WindowTrackerFactory windowTrackerFactory = trainingActivityInterfaceInjector$ar$class_merging$ar$class_merging$ar$class_merging;
        if (windowTrackerFactory != null) {
            return windowTrackerFactory;
        }
        throw new IllegalStateException("Instance is not initialized with TrainingConfigMapper and PageConfigMapper.");
    }

    public static final TrainingConfig getTraining$ar$ds(TrainingConfig.TrainingId trainingId, Context context) {
        PageConfig.Builder initialPageBuilder;
        PageConfig.Builder initialPageBuilder2;
        PageConfig.Builder initialPageBuilder3;
        PageConfig.Builder initialPageBuilder4;
        PageConfig.Builder initialPageBuilder5;
        PageConfig.Builder initialPageBuilder6;
        PageConfig.Builder initialPageBuilder7;
        PageConfig.Builder initialPageBuilder8;
        PageConfig.Builder initialPageBuilder9;
        PageConfig.Builder initialPageBuilder10;
        PageConfig.Builder initialPageBuilder11;
        PageConfig.Builder initialPageBuilder12;
        PageConfig.Builder initialPageBuilder13;
        PageConfig.Builder initialPageBuilder14;
        PageConfig.Builder initialPageBuilder15;
        PageConfig.Builder initialPageBuilder16;
        PageConfig.Builder initialPageBuilder17;
        PageConfig.Builder initialPageBuilder18;
        switch (trainingId.ordinal()) {
            case 0:
                TrainingConfig.Builder constructOnBoardingConfigBuilder = OnboardingConfigs.constructOnBoardingConfigBuilder();
                constructOnBoardingConfigBuilder.setSupportNavigateUpArrow$ar$ds();
                constructOnBoardingConfigBuilder.setExitButtonOnlyShowOnLastPage$ar$ds();
                return constructOnBoardingConfigBuilder.build();
            case 1:
                return OnboardingConfigs.constructOnBoardingConfigBuilder().build();
            case 2:
                return OnboardingConfigs.ON_BOARDING_FOR_MULTIFINGER_GESTURES;
            case 3:
            default:
                return null;
            case 4:
                VendorConfig retrieveConfig = VendorConfigReader.retrieveConfig(context);
                if (!SpannableUtils$IdentifierSpan.shouldShowTraining(retrieveConfig)) {
                    return null;
                }
                TrainingConfig.Builder builder = new TrainingConfig.Builder(R.string.talkback_tutorial_title);
                builder.addPages$ar$ds((PageConfig.Builder[]) SpannableUtils$IdentifierSpan.getDefaultPageBuilders(context, retrieveConfig).values().toArray(new PageConfig.Builder[0]));
                builder.addPages$ar$ds((PageConfig.Builder[]) SpannableUtils$IdentifierSpan.getVendorPageBuilders(retrieveConfig).toArray(new PageConfig.Builder[0]));
                builder.buttons = ImmutableList.of((Object) 0, (Object) 1, (Object) 2);
                builder.setExitButtonOnlyShowOnLastPage$ar$ds();
                builder.isPrevButtonShownOnFirstPage = true;
                return builder.build();
            case 5:
                boolean isGestureNavigateEnabled = FeatureSupport.isGestureNavigateEnabled(context);
                boolean isMultiFingerGestureSupported = FeatureSupport.isMultiFingerGestureSupported();
                boolean z = !SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(context);
                ArrayList arrayList = new ArrayList();
                arrayList.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_WELCOME_TO_TALKBACK));
                arrayList.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_EXPLORE_BY_TOUCH));
                arrayList.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_SCROLLING));
                if (!z) {
                    if (isGestureNavigateEnabled) {
                        initialPageBuilder9 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_GESTURES_PAGE_FOR_GESTURE_NAVIGATION_USER);
                    } else {
                        initialPageBuilder9 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_GESTURES_PAGE_FOR_3_BUTTON_NAVIGATION_USER);
                    }
                    arrayList.add(initialPageBuilder9);
                }
                arrayList.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADJUSTING_VOLUME));
                if (isMultiFingerGestureSupported) {
                    initialPageBuilder = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_MENUS);
                } else {
                    initialPageBuilder = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_MENUS_PRE_R);
                }
                arrayList.add(initialPageBuilder);
                TrainingConfig.Builder builder2 = new TrainingConfig.Builder(R.string.welcome_to_talkback_title);
                builder2.addPages$ar$ds((PageConfig.Builder[]) arrayList.toArray(new PageConfig.Builder[0]));
                builder2.addPage$ar$ds(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_TUTORIAL_FINISHED), true, false, true);
                arrayList.clear();
                arrayList.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_USING_TEXT_BOXES));
                arrayList.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_TYPING_TEXT));
                arrayList.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_MOVING_CURSOR));
                if (isMultiFingerGestureSupported) {
                    initialPageBuilder2 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_SELECTING_TEXT);
                } else {
                    initialPageBuilder2 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_SELECTING_TEXT_PRE_R);
                }
                arrayList.add(initialPageBuilder2);
                builder2.addPages$ar$ds((PageConfig.Builder[]) arrayList.toArray(new PageConfig.Builder[0]));
                if (isMultiFingerGestureSupported) {
                    initialPageBuilder3 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_COPY_CUT_PASTE);
                } else {
                    initialPageBuilder3 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_COPY_CUT_PASTE_PRE_R);
                }
                builder2.addPageEndOfSection(initialPageBuilder3);
                arrayList.clear();
                if (isMultiFingerGestureSupported) {
                    initialPageBuilder4 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_READ_BY_CHARACTER);
                } else {
                    initialPageBuilder4 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_READ_BY_CHARACTER_PRE_R);
                }
                arrayList.add(initialPageBuilder4);
                if (isMultiFingerGestureSupported) {
                    initialPageBuilder5 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_CONTROLS);
                } else {
                    initialPageBuilder5 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_CONTROLS_PRE_R);
                }
                arrayList.add(initialPageBuilder5);
                if (isMultiFingerGestureSupported) {
                    initialPageBuilder6 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_LINKS);
                } else {
                    initialPageBuilder6 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_LINKS_PRE_R);
                }
                arrayList.add(initialPageBuilder6);
                builder2.addPages$ar$ds((PageConfig.Builder[]) arrayList.toArray(new PageConfig.Builder[0]));
                if (isMultiFingerGestureSupported) {
                    initialPageBuilder7 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_HEADINGS);
                } else {
                    initialPageBuilder7 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_HEADINGS_PRE_R);
                }
                builder2.addPageEndOfSection(initialPageBuilder7);
                builder2.addPageEndOfSection(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_VOICE_COMMANDS));
                builder2.addPages$ar$ds(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_MAKING_CALLS), TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_SENDING_MESSAGES), TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_READING_WEB_EMAILS));
                if (isMultiFingerGestureSupported) {
                    builder2.addPages$ar$ds(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_LOOKOUT));
                    builder2.addPageEndOfSection(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_CHECKING_NOTIFICATIONS));
                } else {
                    builder2.addPageEndOfSection(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_LOOKOUT));
                }
                if (isMultiFingerGestureSupported) {
                    initialPageBuilder8 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_PRACTICE_GESTURES);
                } else {
                    initialPageBuilder8 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_PRACTICE_GESTURES_PRE_R);
                }
                builder2.addPageEndOfSection(initialPageBuilder8).buttons = NavigationButtonBar.DEFAULT_BUTTONS;
                return builder2.build();
            case 6:
                boolean isGestureNavigateEnabled2 = FeatureSupport.isGestureNavigateEnabled(context);
                boolean isMultiFingerGestureSupported2 = FeatureSupport.isMultiFingerGestureSupported();
                TrainingConfig.Builder addPageWithoutNumberAndNavigationBar = new TrainingConfig.Builder(R.string.talkback_tutorial_title).addPageWithoutNumberAndNavigationBar(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_TUTORIAL_INDEX));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_WELCOME_TO_TALKBACK));
                arrayList2.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_EXPLORE_BY_TOUCH));
                arrayList2.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_SCROLLING));
                if (isGestureNavigateEnabled2) {
                    initialPageBuilder10 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_GESTURES_PAGE_FOR_GESTURE_NAVIGATION_USER);
                } else {
                    initialPageBuilder10 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_GESTURES_PAGE_FOR_3_BUTTON_NAVIGATION_USER);
                }
                arrayList2.add(initialPageBuilder10);
                arrayList2.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADJUSTING_VOLUME));
                addPageWithoutNumberAndNavigationBar.addPages$ar$ds((PageConfig.Builder[]) arrayList2.toArray(new PageConfig.Builder[0]));
                if (isMultiFingerGestureSupported2) {
                    initialPageBuilder11 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_MENUS);
                } else {
                    initialPageBuilder11 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_MENUS_PRE_R);
                }
                addPageWithoutNumberAndNavigationBar.addPageEndOfSection(initialPageBuilder11);
                arrayList2.clear();
                arrayList2.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_USING_TEXT_BOXES));
                arrayList2.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_TYPING_TEXT));
                arrayList2.add(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_MOVING_CURSOR));
                if (isMultiFingerGestureSupported2) {
                    initialPageBuilder12 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_SELECTING_TEXT);
                } else {
                    initialPageBuilder12 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_SELECTING_TEXT_PRE_R);
                }
                arrayList2.add(initialPageBuilder12);
                Optional correctSpellCheckTutorialPageId = TutorialConfigUtils.getCorrectSpellCheckTutorialPageId(context);
                if (isMultiFingerGestureSupported2) {
                    initialPageBuilder13 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_COPY_CUT_PASTE);
                } else {
                    initialPageBuilder13 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_COPY_CUT_PASTE_PRE_R);
                }
                if (correctSpellCheckTutorialPageId.isPresent()) {
                    arrayList2.add(initialPageBuilder13);
                    addPageWithoutNumberAndNavigationBar.addPages$ar$ds((PageConfig.Builder[]) arrayList2.toArray(new PageConfig.Builder[0]));
                    addPageWithoutNumberAndNavigationBar.addPageEndOfSection(TutorialConfigUtils.getInitialPageBuilder((PageConfig.PageId) correctSpellCheckTutorialPageId.get()));
                } else {
                    addPageWithoutNumberAndNavigationBar.addPages$ar$ds((PageConfig.Builder[]) arrayList2.toArray(new PageConfig.Builder[0]));
                    addPageWithoutNumberAndNavigationBar.addPageEndOfSection(initialPageBuilder13);
                }
                arrayList2.clear();
                if (isMultiFingerGestureSupported2) {
                    initialPageBuilder14 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_READ_BY_CHARACTER);
                } else {
                    initialPageBuilder14 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_READ_BY_CHARACTER_PRE_R);
                }
                arrayList2.add(initialPageBuilder14);
                if (isMultiFingerGestureSupported2) {
                    initialPageBuilder15 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_CONTROLS);
                } else {
                    initialPageBuilder15 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_CONTROLS_PRE_R);
                }
                arrayList2.add(initialPageBuilder15);
                if (isMultiFingerGestureSupported2) {
                    initialPageBuilder16 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_LINKS);
                } else {
                    initialPageBuilder16 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_LINKS_PRE_R);
                }
                arrayList2.add(initialPageBuilder16);
                addPageWithoutNumberAndNavigationBar.addPages$ar$ds((PageConfig.Builder[]) arrayList2.toArray(new PageConfig.Builder[0]));
                if (isMultiFingerGestureSupported2) {
                    initialPageBuilder17 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_HEADINGS);
                } else {
                    initialPageBuilder17 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_JUMP_BETWEEN_HEADINGS_PRE_R);
                }
                addPageWithoutNumberAndNavigationBar.addPageEndOfSection(initialPageBuilder17);
                addPageWithoutNumberAndNavigationBar.addPageEndOfSection(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_VOICE_COMMANDS));
                addPageWithoutNumberAndNavigationBar.addPages$ar$ds(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_MAKING_CALLS), TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_SENDING_MESSAGES), TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_READING_WEB_EMAILS));
                if (isMultiFingerGestureSupported2) {
                    addPageWithoutNumberAndNavigationBar.addPages$ar$ds(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_LOOKOUT));
                    addPageWithoutNumberAndNavigationBar.addPageEndOfSection(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_CHECKING_NOTIFICATIONS));
                } else {
                    addPageWithoutNumberAndNavigationBar.addPageEndOfSection(TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_ADDITIONAL_TIPS_LOOKOUT));
                }
                if (isMultiFingerGestureSupported2) {
                    initialPageBuilder18 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_PRACTICE_GESTURES);
                } else {
                    initialPageBuilder18 = TutorialConfigUtils.getInitialPageBuilder(PageConfig.PageId.PAGE_ID_PRACTICE_GESTURES_PRE_R);
                }
                TrainingConfig.Builder addPageEndOfSection = addPageWithoutNumberAndNavigationBar.addPageEndOfSection(initialPageBuilder18);
                addPageEndOfSection.buttons = NavigationButtonBar.DEFAULT_BUTTONS;
                addPageEndOfSection.setSupportNavigateUpArrow$ar$ds();
                addPageEndOfSection.setExitButtonOnlyShowOnLastPage$ar$ds();
                return addPageWithoutNumberAndNavigationBar.build();
            case 7:
                return TutorialConfigUtils.TUTORIAL_PRACTICE_GESTURE;
            case 8:
                return TutorialConfigUtils.TUTORIAL_PRACTICE_GESTURE_PRE_R;
            case 9:
                TrainingConfig.Builder addPageWithoutNumberAndNavigationBar2 = new TrainingConfig.Builder(R.string.typo_correction_title).addPageWithoutNumberAndNavigationBar(TutorialConfigUtils.getInitialPageBuilder((PageConfig.PageId) TutorialConfigUtils.getCorrectSpellCheckTutorialPageId(context).get()));
                addPageWithoutNumberAndNavigationBar2.buttons = NavigationButtonBar.DEFAULT_BUTTONS;
                addPageWithoutNumberAndNavigationBar2.setSupportNavigateUpArrow$ar$ds();
                return addPageWithoutNumberAndNavigationBar2.build();
            case 10:
                return VoiceCommandAndHelpConfigs.VOICE_COMMAND_HELP;
        }
    }

    public static final boolean isItemSupported$ar$ds(MenuItem menuItem) {
        if (menuItem == null) {
            return false;
        }
        int itemId = menuItem.getItemId();
        if (itemId != R.id.read_from_top && itemId != R.id.read_from_current && itemId != R.id.repeat_last_utterance && itemId != R.id.spell_last_utterance && itemId != R.id.copy_last_utterance_to_clipboard && itemId != R.id.verbosity && itemId != R.id.audio_ducking && itemId != R.id.sound_feedback && itemId != R.id.vibration_feedback && itemId != R.id.talkback_settings && itemId != R.id.tts_settings && itemId != R.id.enable_dimming && itemId != R.id.disable_dimming && itemId != R.id.screen_search && itemId != R.id.voice_commands && itemId != R.id.braille_display_settings) {
            return false;
        }
        return true;
    }

    public static void setNodeMenuDefaultCloseRules(ContextMenuItem contextMenuItem) {
        contextMenuItem.needRestoreFocus = true;
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final void switchValueAndEcho(int i, int i2, int i3) {
        String string;
        boolean z = !SpannableUtils$IdentifierSpan.getBooleanPref(SpannableUtils$IdentifierSpan.getSharedPreferences((Context) this.WindowTrackerFactory$ar$executorProvider), ((TalkBackService) this.WindowTrackerFactory$ar$executorProvider).getResources(), i2, i3);
        SpannableUtils$IdentifierSpan.putBooleanPref(SpannableUtils$IdentifierSpan.getSharedPreferences((Context) this.WindowTrackerFactory$ar$executorProvider), ((TalkBackService) this.WindowTrackerFactory$ar$executorProvider).getResources(), i2, z);
        Object obj = this.WindowTrackerFactory$ar$executorProvider;
        if (z) {
            string = ((TalkBackService) obj).getString(R.string.value_on);
        } else {
            string = ((TalkBackService) obj).getString(R.string.value_off);
        }
        String string2 = ((TalkBackService) obj).getString(i, new Object[]{string});
        if (!TextUtils.isEmpty(string2)) {
            ?? r5 = this.WindowTrackerFactory$ar$handlerProvider;
            Logger logger = Performance.DEFAULT_LOGGER;
            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
            speakOptions.mFlags = 286;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r5, (Performance.EventId) null, Feedback.speech(string2, speakOptions));
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.content.SharedPreferences, java.lang.Object] */
    public final boolean contains(String str) {
        return this.WindowTrackerFactory$ar$executorProvider.contains(getPrefixedKey(str));
    }

    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Object, kotlin.coroutines.CoroutineContext] */
    public final Object createIterator(SelectToSpeakService selectToSpeakService, Bitmap bitmap, Continuation continuation) {
        return OnDeviceSubjectSegmentationLoadLogEvent.withContext(this.WindowTrackerFactory$ar$executorProvider, new ChainAnimatorCoroutineImpl$chainAnimations$1(selectToSpeakService, this, bitmap, (Continuation) null, 1), continuation);
    }

    protected final Intent createSettingsIntent() {
        Intent intent = new Intent((Context) this.WindowTrackerFactory$ar$executorProvider, (Class<?>) TalkBackPreferencesActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(32768);
        return intent;
    }

    public final void e(String str, Object... objArr) {
        Log.e((String) this.WindowTrackerFactory$ar$handlerProvider, format(str, objArr));
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.Map, java.lang.Object] */
    public final void failCalls(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.WindowTrackerFactory$ar$executorProvider) {
            hashMap = new HashMap((Map) this.WindowTrackerFactory$ar$executorProvider);
        }
        synchronized (this.WindowTrackerFactory$ar$handlerProvider) {
            hashMap2 = new HashMap((Map) this.WindowTrackerFactory$ar$handlerProvider);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).forceFailureUnlessReady(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((AppLifecycleMonitor) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }

    protected final String format(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return ((String) this.WindowTrackerFactory$ar$executorProvider).concat(str);
    }

    public final FeedbackController getFeedBackController() {
        return ((TalkBackService) this.WindowTrackerFactory$ar$executorProvider).getFeedbackController();
    }

    public final Object getFirst() {
        Object first;
        synchronized (this.WindowTrackerFactory$ar$handlerProvider) {
            first = ((ArrayDeque) this.WindowTrackerFactory$ar$executorProvider).getFirst();
        }
        return first;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.content.SharedPreferences, java.lang.Object] */
    public final Long getKeyComboCode(String str) {
        return Long.valueOf(this.WindowTrackerFactory$ar$executorProvider.getLong(getPrefixedKey(str), 0L));
    }

    public final CharSequence getNodeText() {
        Object obj = this.WindowTrackerFactory$ar$handlerProvider;
        if (obj != null) {
            return ((AccessibilityNode) obj).getNodeText();
        }
        return null;
    }

    public final String getPrefixedKey(String str) {
        Object obj = this.WindowTrackerFactory$ar$handlerProvider;
        if (obj == null) {
            return str;
        }
        return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str, (String) obj, "|");
    }

    public final void handlePlayServicesUnavailable(int i, GoogleHelp googleHelp) {
        Intent data = new Intent("android.intent.action.VIEW").setData(googleHelp.fallbackSupportUri);
        char[] cArr = null;
        if (i != 7) {
            if (!((Activity) this.WindowTrackerFactory$ar$executorProvider).getPackageManager().queryIntentActivities(data, 0).isEmpty()) {
                new TracingHandler(Looper.getMainLooper()).post(new ListMenuManager$$ExternalSyntheticLambda3(this, data, 14, cArr));
                return;
            }
        } else {
            i = 7;
        }
        Object obj = this.WindowTrackerFactory$ar$executorProvider;
        if (true == GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating((Context) obj, i)) {
            i = 18;
        }
        GoogleApiAvailability.INSTANCE.showErrorDialogFragment$ar$ds((Activity) obj, i, 0, null);
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.List, java.lang.Object] */
    public final boolean hasMatchedResult() {
        Object obj = this.WindowTrackerFactory$ar$handlerProvider;
        if (obj != null && !TextUtils.isEmpty(((AccessibilityNode) obj).getNodeText()) && !this.WindowTrackerFactory$ar$executorProvider.isEmpty()) {
            return true;
        }
        return false;
    }

    public final boolean isEmpty() {
        return ((ArrayDeque) this.WindowTrackerFactory$ar$executorProvider).isEmpty();
    }

    public final int isGooglePlayServicesAvailable() {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable((Context) this.WindowTrackerFactory$ar$executorProvider, 11925000);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.google.common.base.Supplier, java.lang.Object] */
    public final void launch(Intent intent) {
        if (intent.getAction().equals("com.google.android.gms.googlehelp.HELP") && intent.hasExtra("EXTRA_GOOGLE_HELP")) {
            int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable();
            if (isGooglePlayServicesAvailable == 0) {
                Object obj = this.WindowTrackerFactory$ar$handlerProvider.get();
                GoogleHelpClient googleHelpClient = (GoogleHelpClient) obj;
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(googleHelpClient.callingActivity);
                GoogleApiClient googleApiClient = ((GoogleApi) obj).wrapper;
                PendingResultUtil.toVoidTask(googleApiClient.enqueue(new GoogleHelpApiImpl.AnonymousClass1(GoogleHelpClient.googleHelpApi$ar$class_merging, googleApiClient, intent, null, new WeakReference(googleHelpClient.callingActivity))));
                return;
            }
            handlePlayServicesUnavailable(isGooglePlayServicesAvailable, (GoogleHelp) intent.getParcelableExtra("EXTRA_GOOGLE_HELP"));
            return;
        }
        throw new IllegalArgumentException("The intent you are trying to launch is not GoogleHelp intent! This class only supports GoogleHelp intents.");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Collection, java.lang.Object] */
    public final List matchResults() {
        return new ArrayList((Collection) this.WindowTrackerFactory$ar$executorProvider);
    }

    public final void onExit() {
        int i;
        TrainingActivity trainingActivity = (TrainingActivity) ((WeakReference) this.WindowTrackerFactory$ar$handlerProvider).get();
        if (trainingActivity == null) {
            return;
        }
        Object obj = this.WindowTrackerFactory$ar$executorProvider;
        PageConfig.PageId currentPageId = trainingActivity.getCurrentPageId();
        if (((AccessibilityButtonMonitor) obj).backToLinkIndexPage()) {
            trainingActivity.metricStore$ar$class_merging$ar$class_merging.onTrainingPageCompleted(currentPageId);
            return;
        }
        if (!((AccessibilityButtonMonitor) this.WindowTrackerFactory$ar$executorProvider).isLastPage()) {
            WindowTrackerFactory windowTrackerFactory = trainingActivity.metricStore$ar$class_merging$ar$class_merging;
            if (windowTrackerFactory != null) {
                windowTrackerFactory.onTutorialEvent(11);
            }
            trainingActivity.getSupportFragmentManager();
            ApplicationModule materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging = A11yAlertDialogWrapper.materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging(trainingActivity);
            materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.exit_tutorial_title);
            if (true != trainingActivity.isOnboarding()) {
                i = R.string.exit_tutorial_content;
            } else {
                i = R.string.exit_onboarding_content;
            }
            materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setMessage$ar$class_merging$ar$ds(i);
            materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setCancelable$ar$class_merging$ar$ds();
            materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.training_finish_button, new PermissionRequestActivity$$ExternalSyntheticLambda0(trainingActivity, 5));
            materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(R.string.keep_reviewing_tutorial_button, new TrainingActivity$$ExternalSyntheticLambda6(0));
            materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.create().show();
            return;
        }
        trainingActivity.metricStore$ar$class_merging$ar$class_merging.onTrainingPageCompleted(currentPageId);
        trainingActivity.notifyTrainingFinishByUser();
        trainingActivity.setResult(-1);
        trainingActivity.finish();
    }

    public final void onFlagUpdate(FlagUpdateInfo flagUpdateInfo) {
        Function function = (Function) ((AtomicReference) this.WindowTrackerFactory$ar$handlerProvider).get();
        if (function != null && ((Boolean) function.apply(new ArraySet(flagUpdateInfo.configPackages_))).booleanValue()) {
            ((PhenotypeProcessReaper) this.WindowTrackerFactory$ar$executorProvider).scheduleReap();
        }
    }

    /* JADX WARN: Type inference failed for: r5v13, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v14, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v15, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v16, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v17, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v3, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v5, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    public final boolean onMenuItemClicked(MenuItem menuItem) {
        if (!isItemSupported$ar$ds(menuItem)) {
            return false;
        }
        Logger logger = Performance.DEFAULT_LOGGER;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.read_from_top) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) null, Feedback.continuousRead$ar$edu(1));
        } else if (itemId == R.id.read_from_current) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) null, Feedback.continuousRead$ar$edu(2));
        } else if (itemId == R.id.repeat_last_utterance) {
            ?? r5 = this.WindowTrackerFactory$ar$handlerProvider;
            Feedback.Part.Builder builder = Feedback.Part.builder();
            builder.speech = Feedback.Speech.create(Feedback.Speech.Action.REPEAT_SAVED);
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r5, (Performance.EventId) null, builder);
        } else if (itemId == R.id.spell_last_utterance) {
            ?? r52 = this.WindowTrackerFactory$ar$handlerProvider;
            Feedback.Part.Builder builder2 = Feedback.Part.builder();
            builder2.speech = Feedback.Speech.create(Feedback.Speech.Action.SPELL_SAVED);
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r52, (Performance.EventId) null, builder2);
        } else if (itemId == R.id.copy_last_utterance_to_clipboard) {
            ?? r53 = this.WindowTrackerFactory$ar$handlerProvider;
            Feedback.Part.Builder builder3 = Feedback.Part.builder();
            builder3.speech = Feedback.Speech.create(Feedback.Speech.Action.COPY_SAVED);
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r53, (Performance.EventId) null, builder3);
        } else if (itemId == R.id.verbosity) {
            Intent createSettingsIntent = createSettingsIntent();
            createSettingsIntent.putExtra("FragmentName", VerbosityPrefFragment.class.getName());
            ((TalkBackService) this.WindowTrackerFactory$ar$executorProvider).startActivity(createSettingsIntent);
        } else if (itemId == R.id.audio_ducking) {
            switchValueAndEcho(R.string.audio_focus_state, R.string.pref_use_audio_focus_key, R.bool.pref_use_audio_focus_default);
        } else if (itemId == R.id.sound_feedback) {
            switchValueAndEcho(R.string.sound_feedback_state, R.string.pref_soundback_key, R.bool.pref_soundback_default);
        } else if (itemId == R.id.vibration_feedback) {
            switchValueAndEcho(R.string.vibration_feedback_state, R.string.pref_vibration_key, R.bool.pref_vibration_default);
        } else if (itemId == R.id.talkback_settings) {
            ((TalkBackService) this.WindowTrackerFactory$ar$executorProvider).startActivity(createSettingsIntent());
        } else if (itemId == R.id.tts_settings) {
            Intent intent = new Intent("com.android.settings.TTS_SETTINGS");
            intent.addFlags(276824064);
            ((TalkBackService) this.WindowTrackerFactory$ar$executorProvider).startActivity(intent);
        } else if (itemId == R.id.enable_dimming) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) null, Feedback.dimScreen$ar$edu(1));
        } else if (itemId == R.id.disable_dimming) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) null, Feedback.dimScreen$ar$edu(2));
        } else if (itemId == R.id.screen_search) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) null, Feedback.universalSearch$ar$edu(1));
        } else if (itemId == R.id.voice_commands) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) null, Feedback.voiceRecognition$ar$edu$6decc7d7_0(1, true));
        } else if (itemId == R.id.braille_display_settings) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) null, Feedback.triggerIntent$ar$edu(4));
        }
        return true;
    }

    public final void onNext() {
        TrainingActivity trainingActivity = (TrainingActivity) ((WeakReference) this.WindowTrackerFactory$ar$handlerProvider).get();
        if (trainingActivity != null) {
            trainingActivity.metricStore$ar$class_merging$ar$class_merging.onTrainingPageCompleted(trainingActivity.getCurrentPageId());
        }
        AccessibilityButtonMonitor accessibilityButtonMonitor = (AccessibilityButtonMonitor) this.WindowTrackerFactory$ar$executorProvider;
        if (!accessibilityButtonMonitor.isLastPage()) {
            accessibilityButtonMonitor.switchPage(accessibilityButtonMonitor.mButtonState + 1);
            Object obj = accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mHandler;
            if (obj != null) {
                ((WindowTrackerFactory) obj).onTutorialEvent(9);
            }
        }
    }

    public final void onSpeechCompleted() {
        ((TalkBackService.ProximitySensorListener) this.WindowTrackerFactory$ar$handlerProvider).setProximitySensorStateByScreen();
    }

    public final void onSpeechPaused() {
        ((TalkBackService.ProximitySensorListener) this.WindowTrackerFactory$ar$handlerProvider).setProximitySensorStateByScreen();
    }

    public final void onSpeechStarting() {
        ((TalkBackService.ProximitySensorListener) this.WindowTrackerFactory$ar$handlerProvider).setProximitySensorState(true);
    }

    public final void onTrainingPageCompleted(PageConfig.PageId pageId) {
        ((TrainingMetricHolder) this.WindowTrackerFactory$ar$handlerProvider).completedPages.add(pageId);
    }

    public final void onTutorialEvent(int i) {
        SQLiteDatabase safeGetWritableDatabase = ((TalkBackAnalyticsDBHelper) this.WindowTrackerFactory$ar$executorProvider).safeGetWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String valueOf = String.valueOf(i);
        contentValues.put("trainingSection", Integer.valueOf(i));
        contentValues.put("compound", valueOf);
        contentValues.put("count", (Integer) 1);
        TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase, "TrainingSectionEntry", valueOf, contentValues);
    }

    /* JADX WARN: Type inference failed for: r8v1, types: [com.google.common.base.Function, java.lang.Object] */
    public final VibrationEffect parse(int[] iArr) {
        VibrationEffect.Composition startComposition;
        VibrationEffect compose;
        boolean areAllPrimitivesSupported;
        int length = iArr.length;
        int i = 0;
        while (true) {
            if (i < length) {
                if (iArr[i] == -9999) {
                    break;
                }
                i++;
            } else {
                i = -1;
                break;
            }
        }
        if (i >= 0 && SpannableUtils$IdentifierSpan.isAtLeastR()) {
            try {
                startComposition = VibrationEffect.startComposition();
                for (int i2 = i + 1; i2 < iArr.length; i2 += 3) {
                    int i3 = iArr[i2];
                    areAllPrimitivesSupported = ((Vibrator) ((HapticPatternParser$$ExternalSyntheticLambda1) this.WindowTrackerFactory$ar$handlerProvider).HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).areAllPrimitivesSupported(i3);
                    if (areAllPrimitivesSupported) {
                        startComposition.addPrimitive(i3, iArr[i2 + 1] / 255.0f, iArr[i2 + 2]);
                    } else {
                        throw new ParseException("At least one composition primitives not supported by vibrator", i2);
                    }
                }
                compose = startComposition.compose();
                return compose;
            } catch (ParseException e) {
                LogUtils.e("HapticPatternParser", e, "Failed to parse haptic pattern", new Object[0]);
            }
        }
        if (i < 0) {
            i = iArr.length;
        }
        long[] jArr = new long[i];
        for (int i4 = 0; i4 < i; i4++) {
            jArr[i4] = iArr[i4];
        }
        return BundleUtils$$ExternalSyntheticApiModelOutline0.m267m(this.WindowTrackerFactory$ar$executorProvider.apply(jArr));
    }

    public final void prepareRuleMenuForNode(ContextMenu contextMenu, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        if (accessibilityNodeInfoCompat != null) {
            contextMenu.clear();
            NodeMenuRule menuRuleById = ((NodeMenuRuleCreator) this.WindowTrackerFactory$ar$handlerProvider).getMenuRuleById(i);
            if (menuRuleById != null && menuRuleById.accept((Context) this.WindowTrackerFactory$ar$executorProvider, accessibilityNodeInfoCompat)) {
                List<ContextMenuItem> menuItemsForNode = menuRuleById.getMenuItemsForNode((Context) this.WindowTrackerFactory$ar$executorProvider, accessibilityNodeInfoCompat, true);
                if (!menuItemsForNode.isEmpty()) {
                    for (ContextMenuItem contextMenuItem : menuItemsForNode) {
                        setNodeMenuDefaultCloseRules(contextMenuItem);
                        contextMenu.add(contextMenuItem);
                    }
                }
            }
        }
    }

    public final Object removeFirst() {
        Object removeFirst;
        synchronized (this.WindowTrackerFactory$ar$handlerProvider) {
            removeFirst = ((ArrayDeque) this.WindowTrackerFactory$ar$executorProvider).removeFirst();
        }
        return removeFirst;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.content.SharedPreferences, java.lang.Object] */
    public final void saveKeyCombo(String str, long j) {
        this.WindowTrackerFactory$ar$executorProvider.edit().putLong(getPrefixedKey(str), j).apply();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, com.google.android.datatransport.Transformer] */
    public final void send(Event event) {
        final SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan = new SpannableUtils$IdentifierSpan();
        RestrictedByteStringClearcutLogger.LogEventBuilder logEventBuilder = new RestrictedByteStringClearcutLogger.LogEventBuilder((RestrictedByteStringClearcutLogger) this.WindowTrackerFactory$ar$executorProvider, ByteString.copyFrom((byte[]) this.WindowTrackerFactory$ar$handlerProvider.apply(event.payload)));
        int i = 1;
        if (event.priority$ar$edu - 1 != 1) {
            i = 0;
        }
        logEventBuilder.qosTier$ar$edu = ClientAnalytics$QosTierConfiguration$QosTier.forNumber$ar$edu$febe9175_0(i);
        logEventBuilder.logAsync().setResultCallback(new ResultCallback() { // from class: com.google.android.datatransport.runtime.ClearcutTransport$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.common.api.ResultCallback
            public final void onResult(Result result) {
                Status status = (Status) result;
                if (status.isSuccess()) {
                    return;
                }
                new ExecutionException(String.format("%s: %d", status.statusMessage, Integer.valueOf(status.statusCode)), null);
            }
        });
    }

    public final int size() {
        return ((ArrayDeque) this.WindowTrackerFactory$ar$executorProvider).size();
    }

    public final void w(String str, Object... objArr) {
        Log.w((String) this.WindowTrackerFactory$ar$handlerProvider, format(str, objArr));
    }

    public WindowTrackerFactory(SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan, SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan2) {
        this.WindowTrackerFactory$ar$executorProvider = spannableUtils$IdentifierSpan2;
        this.WindowTrackerFactory$ar$handlerProvider = spannableUtils$IdentifierSpan;
    }

    public WindowTrackerFactory(TimerEvent timerEvent, String str) {
        this.WindowTrackerFactory$ar$handlerProvider = timerEvent;
        this.WindowTrackerFactory$ar$executorProvider = str;
    }

    public WindowTrackerFactory(Object obj, Performance.EventId eventId) {
        this.WindowTrackerFactory$ar$handlerProvider = obj;
        this.WindowTrackerFactory$ar$executorProvider = eventId;
    }

    public WindowTrackerFactory(Object obj, Object obj2) {
        this.WindowTrackerFactory$ar$executorProvider = obj;
        this.WindowTrackerFactory$ar$handlerProvider = obj2;
    }

    public WindowTrackerFactory(Context context) {
        this.WindowTrackerFactory$ar$handlerProvider = new HashingNameSanitizer();
        this.WindowTrackerFactory$ar$executorProvider = context;
    }

    public WindowTrackerFactory(Context context, TrainingMetricStore$Type trainingMetricStore$Type) {
        this.WindowTrackerFactory$ar$executorProvider = new TalkBackAnalyticsDBHelper(context);
        this.WindowTrackerFactory$ar$handlerProvider = new TrainingMetricHolder(trainingMetricStore$Type);
    }

    public WindowTrackerFactory(final Context context, final RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.WindowTrackerFactory$ar$executorProvider = context;
        final Handler handler = new Handler();
        this.WindowTrackerFactory$ar$handlerProvider = new ContentObserver(handler) { // from class: com.google.android.accessibility.braille.brailledisplay.platform.lib.SetupWizardFinishReceiver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                if (SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(context)) {
                    resolutionResultListener.onFinished();
                }
            }
        };
    }

    public WindowTrackerFactory(Context context, byte[] bArr) {
        AudioManager audioManager;
        int streamMinVolume;
        this.WindowTrackerFactory$ar$handlerProvider = context;
        FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
        this.WindowTrackerFactory$ar$executorProvider = formFactorUtils;
        if (!formFactorUtils.isAndroidWear || (audioManager = (AudioManager) context.getSystemService("audio")) == null) {
            return;
        }
        int max = Math.max(audioManager.getStreamMaxVolume(10), 1);
        streamMinVolume = audioManager.getStreamMinVolume(10);
        int max2 = Math.max(streamMinVolume, 1);
        int streamVolume = audioManager.getStreamVolume(10);
        if (max > max2) {
            max2 = formFactorUtils.isAndroidWear ? max2 + (((max - max2) * 30) / 100) : max2;
            if (streamVolume < max2) {
                audioManager.setStreamVolume(10, max2, 0);
            }
        }
    }

    public WindowTrackerFactory(Vibrator vibrator) {
        AccessibilityEventUtils$$ExternalSyntheticLambda0 accessibilityEventUtils$$ExternalSyntheticLambda0 = new AccessibilityEventUtils$$ExternalSyntheticLambda0(4);
        vibrator.getClass();
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = new HapticPatternParser$$ExternalSyntheticLambda1(vibrator);
        this.WindowTrackerFactory$ar$executorProvider = accessibilityEventUtils$$ExternalSyntheticLambda0;
        this.WindowTrackerFactory$ar$handlerProvider = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    public WindowTrackerFactory(TrainingActivity trainingActivity, AccessibilityButtonMonitor accessibilityButtonMonitor) {
        this.WindowTrackerFactory$ar$executorProvider = accessibilityButtonMonitor;
        accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mCallback = this;
        this.WindowTrackerFactory$ar$handlerProvider = new WeakReference(trainingActivity);
    }

    /* JADX WARN: Type inference failed for: r7v3, types: [com.google.common.base.Supplier, java.lang.Object] */
    public WindowTrackerFactory(Context context, Transformer transformer) {
        SnackbarManager piiLevelSet$ar$class_merging$ar$class_merging$ar$class_merging = new SnackbarManager(context, (byte[]) null).setPiiLevelSet$ar$class_merging$ar$class_merging$ar$class_merging(PIILevel.deidentified);
        Object obj = piiLevelSet$ar$class_merging$ar$class_merging$ar$class_merging.SnackbarManager$ar$handler;
        Context context2 = (Context) obj;
        RestrictedByteStringClearcutLogger restrictedByteStringClearcutLogger = new RestrictedByteStringClearcutLogger(context2, (String) piiLevelSet$ar$class_merging$ar$class_merging$ar$class_merging.lock, (EnumSet) piiLevelSet$ar$class_merging$ar$class_merging$ar$class_merging.SnackbarManager$ar$currentSnackbar, piiLevelSet$ar$class_merging$ar$class_merging$ar$class_merging.SnackbarManager$ar$nextSnackbar);
        try {
            throw new IllegalArgumentException(String.format("Clearcut does not support setting log source int values (%s, %d). Use log source name instead.", "FIREBASE_ML_SDK", Integer.valueOf(Integer.parseInt("FIREBASE_ML_SDK"))));
        } catch (NumberFormatException unused) {
            this.WindowTrackerFactory$ar$executorProvider = restrictedByteStringClearcutLogger;
            this.WindowTrackerFactory$ar$handlerProvider = transformer;
        }
    }

    public WindowTrackerFactory(Provider provider, Provider provider2) {
        this.WindowTrackerFactory$ar$handlerProvider = provider;
        provider2.getClass();
        this.WindowTrackerFactory$ar$executorProvider = provider2;
    }

    public /* synthetic */ WindowTrackerFactory(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = null;
        this.WindowTrackerFactory$ar$handlerProvider = new ConsentRetrieverImpl.CollectionBasisResolverHolders(bArr4, bArr4);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.getClass();
        this.WindowTrackerFactory$ar$executorProvider = new ExecutorCoroutineDispatcherImpl(newSingleThreadExecutor);
    }

    public WindowTrackerFactory(TalkBackService.ProximitySensorListener proximitySensorListener, SpeechController speechController) {
        this.WindowTrackerFactory$ar$handlerProvider = proximitySensorListener;
        this.WindowTrackerFactory$ar$executorProvider = speechController;
        ((SpeechControllerImpl) speechController).mObservers.add(this);
    }

    public WindowTrackerFactory(AccessibilityNode accessibilityNode, List list) {
        this.WindowTrackerFactory$ar$handlerProvider = accessibilityNode;
        this.WindowTrackerFactory$ar$executorProvider = new ArrayList(list);
    }

    public WindowTrackerFactory(byte[] bArr, byte[] bArr2) {
        this.WindowTrackerFactory$ar$executorProvider = new ArrayList();
        this.WindowTrackerFactory$ar$handlerProvider = new SparseIntArray();
    }

    public WindowTrackerFactory() {
        this.WindowTrackerFactory$ar$executorProvider = DesugarCollections.synchronizedMap(new WeakHashMap());
        this.WindowTrackerFactory$ar$handlerProvider = DesugarCollections.synchronizedMap(new WeakHashMap());
    }

    public WindowTrackerFactory(byte[] bArr) {
        this.WindowTrackerFactory$ar$executorProvider = new ArrayDeque();
        this.WindowTrackerFactory$ar$handlerProvider = new Object();
    }

    public WindowTrackerFactory(Context context, String str) {
        this.WindowTrackerFactory$ar$executorProvider = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.WindowTrackerFactory$ar$handlerProvider = str;
    }

    private WindowTrackerFactory(Context context, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.WindowTrackerFactory$ar$executorProvider = context;
        this.WindowTrackerFactory$ar$handlerProvider = new CustomLabelManager(context, hapticPatternParser$$ExternalSyntheticLambda1);
    }

    public WindowTrackerFactory(String... strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        String str = strArr[0];
        if (sb.length() > 1) {
            sb.append(",");
        }
        sb.append(str);
        sb.append("] ");
        this.WindowTrackerFactory$ar$executorProvider = sb.toString();
        this.WindowTrackerFactory$ar$handlerProvider = "Auth";
        new GmsLogger("Auth", null);
        for (int i = 2; i <= 7 && !Log.isLoggable((String) this.WindowTrackerFactory$ar$handlerProvider, i); i++) {
        }
    }
}

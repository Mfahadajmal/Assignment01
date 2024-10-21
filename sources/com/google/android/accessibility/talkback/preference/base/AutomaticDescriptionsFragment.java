package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.dynamicfeature.Downloader;
import com.google.android.accessibility.talkback.dynamicfeature.IconDetectionModuleDownloadPrompter;
import com.google.android.accessibility.talkback.dynamicfeature.ImageDescriptionModuleDownloadPrompter;
import com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter;
import com.google.android.accessibility.talkback.dynamicfeature.SplitApkDownloader;
import com.google.android.accessibility.talkback.imagecaption.FeatureSwitchDialog;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$AutomaticImageCaptioningState;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$DownloadDialogResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$DownloadStateListenerResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$FeatureSwitchDialogResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$ImageCaptionPreferenceKeys;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$UninstallDialogResources;
import com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import googledata.experiments.mobile.accessibility_suite.features.GeminiConfig;
import j$.util.Optional;
import j$.util.function.Consumer$CC;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AutomaticDescriptionsFragment extends TalkbackBaseFragment {
    private static final String TAG = "AutomaticDescriptionsFragment";
    private AiCoreEndpoint aiCoreEndpoint;
    private Context context;
    private Downloader downloader;
    private ListenableFuture<Boolean> hasAiCoreFuture;
    private IconDetectionModuleDownloadPrompter iconDetectionModuleDownloadPrompter;
    private ImageDescriptionModuleDownloadPrompter imageDescriptionModuleDownloadPrompter;
    private SharedPreferences prefs;
    private Optional<Boolean> useAiCore;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements FutureCallback<Boolean> {
        final /* synthetic */ AutomaticDescriptionsFragment this$0;
        final /* synthetic */ Preference val$imageDescriptionPreference;

        public AnonymousClass1(AutomaticDescriptionsFragment automaticDescriptionsFragment, Preference preference) {
            this.val$imageDescriptionPreference = preference;
            this.this$0 = automaticDescriptionsFragment;
        }

        /* renamed from: lambda$onFailure$2$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment$1, reason: not valid java name */
        public /* synthetic */ void m121x7dea9e98(Preference preference) {
            this.this$0.setupPreferenceForGarcon(preference);
        }

        /* renamed from: lambda$onSuccess$0$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment$1, reason: not valid java name */
        public /* synthetic */ void m122xe89c540f(Preference preference) {
            this.this$0.setupPreferenceForGeminiNano(preference);
        }

        /* renamed from: lambda$onSuccess$1$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment$1, reason: not valid java name */
        public /* synthetic */ void m123xaec6dcd0(Preference preference) {
            this.this$0.setupPreferenceForGarcon(preference);
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
            this.this$0.useAiCore = Optional.of(false);
            FragmentActivity activity = this.this$0.getActivity();
            final Preference preference = this.val$imageDescriptionPreference;
            activity.runOnUiThread(new Runnable() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AutomaticDescriptionsFragment.AnonymousClass1.this.m121x7dea9e98(preference);
                }
            });
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onSuccess(Boolean bool) {
            if (!bool.booleanValue() || !GeminiConfig.enableOnDeviceGeminiImageCaptioning(this.this$0.context)) {
                this.this$0.useAiCore = Optional.of(false);
                AutomaticDescriptionsFragment automaticDescriptionsFragment = this.this$0;
                final Preference preference = this.val$imageDescriptionPreference;
                automaticDescriptionsFragment.getActivity().runOnUiThread(new Runnable() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        AutomaticDescriptionsFragment.AnonymousClass1.this.m123xaec6dcd0(preference);
                    }
                });
                return;
            }
            this.this$0.useAiCore = Optional.of(true);
            AutomaticDescriptionsFragment automaticDescriptionsFragment2 = this.this$0;
            final Preference preference2 = this.val$imageDescriptionPreference;
            automaticDescriptionsFragment2.getActivity().runOnUiThread(new Runnable() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AutomaticDescriptionsFragment.AnonymousClass1.this.m122xe89c540f(preference2);
                }
            });
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$8, reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$accessibility$talkback$imagecaption$ImageCaptionConstants$AutomaticImageCaptioningState;

        static {
            int[] iArr = new int[ImageCaptionConstants$AutomaticImageCaptioningState.values().length];
            $SwitchMap$com$google$android$accessibility$talkback$imagecaption$ImageCaptionConstants$AutomaticImageCaptioningState = iArr;
            try {
                iArr[ImageCaptionConstants$AutomaticImageCaptioningState.ON_ALL_IMAGES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$android$accessibility$talkback$imagecaption$ImageCaptionConstants$AutomaticImageCaptioningState[ImageCaptionConstants$AutomaticImageCaptioningState.ON_UNLABELLED_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$android$accessibility$talkback$imagecaption$ImageCaptionConstants$AutomaticImageCaptioningState[ImageCaptionConstants$AutomaticImageCaptioningState.OFF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AutomaticDescriptionUninstallStateListener {
        private final TalkBackAnalytics.ImageCaptionLogKeys logKeys;
        private final Preference preference;
        private final ImageCaptionConstants$ImageCaptionPreferenceKeys preferenceKeys;
        private final ImageCaptionConstants$UninstallDialogResources uninstallDialogResources;

        private AutomaticDescriptionUninstallStateListener(Preference preference, ImageCaptionConstants$ImageCaptionPreferenceKeys imageCaptionConstants$ImageCaptionPreferenceKeys, TalkBackAnalytics.ImageCaptionLogKeys imageCaptionLogKeys, ImageCaptionConstants$UninstallDialogResources imageCaptionConstants$UninstallDialogResources) {
            this.preference = preference;
            this.preferenceKeys = imageCaptionConstants$ImageCaptionPreferenceKeys;
            this.logKeys = imageCaptionLogKeys;
            this.uninstallDialogResources = imageCaptionConstants$UninstallDialogResources;
        }

        public void onAccepted() {
            TalkBackAnalytics.ImageCaptionLogKeys imageCaptionLogKeys = this.logKeys;
            AutomaticDescriptionsFragment automaticDescriptionsFragment = AutomaticDescriptionsFragment.this;
            TalkBackAnalyticsImpl.onImageCaptionEventFromSettings(automaticDescriptionsFragment.prefs, automaticDescriptionsFragment.context, imageCaptionLogKeys.uninstallRequest);
            AutomaticDescriptionsFragment.this.updatePreferenceSummary(this.preference, R.string.summary_pref_auto_image_captioning_disabled);
            AutomaticDescriptionsFragment.this.prefs.edit().putBoolean(AutomaticDescriptionsFragment.this.context.getString(this.preferenceKeys.uninstalledKey), true).putBoolean(AutomaticDescriptionsFragment.this.context.getString(this.preferenceKeys.installedKey), false).putBoolean(AutomaticDescriptionsFragment.this.context.getString(this.preferenceKeys.switchKey), false).remove(AutomaticDescriptionsFragment.this.context.getString(this.preferenceKeys.switchOnUnlabelledOnlyKey)).apply();
            AutomaticDescriptionsFragment.showToast(AutomaticDescriptionsFragment.this.context, this.uninstallDialogResources.deletedHintRes);
        }

        public void onRejected() {
            TalkBackAnalytics.ImageCaptionLogKeys imageCaptionLogKeys = this.logKeys;
            AutomaticDescriptionsFragment automaticDescriptionsFragment = AutomaticDescriptionsFragment.this;
            TalkBackAnalyticsImpl.onImageCaptionEventFromSettings(automaticDescriptionsFragment.prefs, automaticDescriptionsFragment.context, imageCaptionLogKeys.uninstallDeny);
        }
    }

    public AutomaticDescriptionsFragment() {
        super(R.xml.automatic_descriptions_preferences);
        this.useAiCore = Optional.empty();
    }

    private boolean displayDialogForGeminiNano(final Preference preference) {
        AiCoreEndpoint aiCoreEndpoint = this.aiCoreEndpoint;
        if (aiCoreEndpoint != null) {
            if (aiCoreEndpoint.needAiCoreUpdate()) {
                this.aiCoreEndpoint.displayAiCoreUpdateDialog();
                return true;
            }
            if (this.aiCoreEndpoint.needAstreaUpdate()) {
                this.aiCoreEndpoint.displayAstreaUpdateDialog();
                return true;
            }
            AiCoreEndpoint aiCoreEndpoint2 = this.aiCoreEndpoint;
            if (aiCoreEndpoint2.status == 1 && !aiCoreEndpoint2.hasRequestDownload) {
                aiCoreEndpoint2.displayAiFeatureDownloadDialog(new Consumer() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AutomaticDescriptionsFragment.this.m114xac2060a4(preference, (Void) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }
                });
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean getBooleanPref(int i, int i2) {
        return SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, this.context.getResources(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getSummaryFromFeatureSwitchDialog(Context context, SharedPreferences sharedPreferences, ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources) {
        int ordinal = SpannableUtils$IdentifierSpan.getAutomaticImageCaptioningState(context, sharedPreferences, imageCaptionConstants$FeatureSwitchDialogResources).ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    return -1;
                }
                if (imageCaptionConstants$FeatureSwitchDialogResources == ImageCaptionConstants$FeatureSwitchDialogResources.ICON_DETECTION) {
                    return R.string.summary_pref_auto_icon_detection_enabled_unlabelled_only;
                }
                return R.string.summary_pref_auto_image_captioning_enabled_unlabelled_only;
            }
            if (imageCaptionConstants$FeatureSwitchDialogResources == ImageCaptionConstants$FeatureSwitchDialogResources.ICON_DETECTION) {
                return R.string.summary_pref_auto_icon_detection_enabled;
            }
            return R.string.summary_pref_auto_image_captioning_enabled;
        }
        if (imageCaptionConstants$FeatureSwitchDialogResources == ImageCaptionConstants$FeatureSwitchDialogResources.ICON_DETECTION) {
            return R.string.summary_pref_auto_icon_detection_disabled;
        }
        return R.string.summary_pref_auto_image_captioning_disabled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putBooleanPref(int i, boolean z) {
        SpannableUtils$IdentifierSpan.putBooleanPref(this.prefs, this.context.getResources(), i, z);
    }

    private void removePreference(Preference preference) {
        getPreferenceScreen().removePreference$ar$ds(preference);
    }

    private void setupDetailedImageDescriptionPreference(final ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources) {
        final SwitchPreference switchPreference = (SwitchPreference) findPreferenceByResId(R.string.pref_detailed_image_description_key);
        if (switchPreference == null) {
            return;
        }
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$$ExternalSyntheticLambda5
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return AutomaticDescriptionsFragment.this.m115xb4794fec(imageCaptionConstants$FeatureSwitchDialogResources, switchPreference, preference, obj);
            }
        });
    }

    private void setupIconDetectionPreference() {
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_icon_detection_key);
        if (findPreferenceByResId == null) {
            return;
        }
        if (!ImageCaptioner.supportsIconDetection$ar$ds()) {
            removePreference(findPreferenceByResId);
            return;
        }
        if (this.iconDetectionModuleDownloadPrompter == null) {
            this.iconDetectionModuleDownloadPrompter = new IconDetectionModuleDownloadPrompter(this.context, this.downloader);
        }
        this.iconDetectionModuleDownloadPrompter.downloadStateListener = new AutomaticDescriptionDownloadStateListener(findPreferenceByResId, ImageCaptionConstants$DownloadStateListenerResources.ICON_DETECTION, ImageCaptionConstants$ImageCaptionPreferenceKeys.ICON_DETECTION, ImageCaptionConstants$DownloadDialogResources.ICON_DETECTION.moduleSizeInMb, TalkBackAnalytics.ImageCaptionLogKeys.ICON_DETECTION);
        this.iconDetectionModuleDownloadPrompter.uninstallStateListener$ar$class_merging = new AutomaticDescriptionUninstallStateListener(findPreferenceByResId, ImageCaptionConstants$ImageCaptionPreferenceKeys.ICON_DETECTION, TalkBackAnalytics.ImageCaptionLogKeys.IMAGE_DESCRIPTION, ImageCaptionConstants$UninstallDialogResources.ICON_DETECTION);
        setupPreferenceForDynamicFeature(findPreferenceByResId, this.iconDetectionModuleDownloadPrompter, ImageCaptionConstants$FeatureSwitchDialogResources.ICON_DETECTION);
    }

    private void setupImageDescriptionPreference() {
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_image_description_key);
        if (findPreferenceByResId == null) {
            return;
        }
        if (!ImageCaptioner.supportsImageDescription(this.context)) {
            removePreference(findPreferenceByResId);
        } else {
            ContextDataProvider.addCallback(this.hasAiCoreFuture, new AnonymousClass1(this, findPreferenceByResId), DirectExecutor.INSTANCE);
        }
    }

    private void setupPreferenceForDynamicFeature(final Preference preference, final ModuleDownloadPrompter moduleDownloadPrompter, final ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources) {
        if (moduleDownloadPrompter.isModuleDownloading()) {
            preference.setSummary(R.string.summary_pref_module_downloading);
        } else if (moduleDownloadPrompter.isModuleAvailable() && !moduleDownloadPrompter.isUninstalled()) {
            if (!this.prefs.contains(this.context.getString(imageCaptionConstants$FeatureSwitchDialogResources.switchKey))) {
                putBooleanPref(imageCaptionConstants$FeatureSwitchDialogResources.switchKey, true);
            }
            preference.setSummary(getSummaryFromFeatureSwitchDialog(this.context, this.prefs, imageCaptionConstants$FeatureSwitchDialogResources));
        } else {
            preference.setSummary(R.string.summary_pref_auto_image_captioning_disabled);
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$$ExternalSyntheticLambda4
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return AutomaticDescriptionsFragment.this.m116x12e2e5c0(moduleDownloadPrompter, imageCaptionConstants$FeatureSwitchDialogResources, preference, preference2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupPreferenceForGarcon(Preference preference) {
        if (this.imageDescriptionModuleDownloadPrompter == null) {
            this.imageDescriptionModuleDownloadPrompter = new ImageDescriptionModuleDownloadPrompter(this.context, this.downloader);
        }
        this.imageDescriptionModuleDownloadPrompter.downloadStateListener = new AutomaticDescriptionDownloadStateListener(preference, ImageCaptionConstants$DownloadStateListenerResources.IMAGE_DESCRIPTION, ImageCaptionConstants$ImageCaptionPreferenceKeys.IMAGE_DESCRIPTION, ImageCaptionConstants$DownloadDialogResources.IMAGE_DESCRIPTION.moduleSizeInMb, TalkBackAnalytics.ImageCaptionLogKeys.IMAGE_DESCRIPTION);
        this.imageDescriptionModuleDownloadPrompter.uninstallStateListener$ar$class_merging = new AutomaticDescriptionUninstallStateListener(preference, ImageCaptionConstants$ImageCaptionPreferenceKeys.IMAGE_DESCRIPTION, TalkBackAnalytics.ImageCaptionLogKeys.IMAGE_DESCRIPTION, ImageCaptionConstants$UninstallDialogResources.IMAGE_DESCRIPTION);
        setupPreferenceForDynamicFeature(preference, this.imageDescriptionModuleDownloadPrompter, ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupPreferenceForGeminiNano(final Preference preference) {
        if (getBooleanPref(ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION_AICORE_OPT_IN.switchKey, ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION_AICORE_OPT_IN.switchDefaultValue)) {
            AiCoreEndpoint aiCoreEndpoint = this.aiCoreEndpoint;
            if (aiCoreEndpoint != null && aiCoreEndpoint.isAiFeatureDownloading()) {
                this.aiCoreEndpoint.downloadCallback = new AnonymousClass3(this, preference);
                preference.setSummary(R.string.summary_pref_module_downloading);
                preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$$ExternalSyntheticLambda0
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference2) {
                        return AutomaticDescriptionsFragment.this.m117x6ba7f013(preference2);
                    }
                });
                return;
            }
            final ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources = ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION_AICORE_SCOPE;
            preference.setSummary(getSummaryFromFeatureSwitchDialog(this.context, this.prefs, imageCaptionConstants$FeatureSwitchDialogResources));
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$$ExternalSyntheticLambda1
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return AutomaticDescriptionsFragment.this.m118x54afb514(preference, imageCaptionConstants$FeatureSwitchDialogResources, preference2);
                }
            });
            return;
        }
        final ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources2 = ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION_AICORE_OPT_IN;
        final FeatureSwitchDialog featureSwitchDialog = new FeatureSwitchDialog(this, this.context, imageCaptionConstants$FeatureSwitchDialogResources2, false, R.string.enable_gemini) { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment.5
            final /* synthetic */ AutomaticDescriptionsFragment this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.accessibility.talkback.imagecaption.FeatureSwitchDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
            public void handleDialogClick(int i) {
                if (i != -2) {
                    if (i != -1) {
                        return;
                    }
                    SpannableUtils$IdentifierSpan.putBooleanPref(this.this$0.prefs, this.context.getResources(), imageCaptionConstants$FeatureSwitchDialogResources2.switchKey, true);
                    this.this$0.setupPreferenceForGeminiNano(preference);
                    AutomaticDescriptionsFragment automaticDescriptionsFragment = this.this$0;
                    TalkBackAnalyticsImpl.onGeminiOptInFromSettings(automaticDescriptionsFragment.prefs, this.context, 2, false);
                    return;
                }
                AutomaticDescriptionsFragment automaticDescriptionsFragment2 = this.this$0;
                TalkBackAnalyticsImpl.onGeminiOptInFromSettings(automaticDescriptionsFragment2.prefs, this.context, 3, false);
            }
        };
        preference.setSummary(R.string.summary_pref_auto_image_captioning_disabled);
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$$ExternalSyntheticLambda2
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return AutomaticDescriptionsFragment.this.m119x3db77a15(preference, featureSwitchDialog, preference2);
            }
        });
    }

    private void setupTextRecognitionPreference() {
        final Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_text_recognition_key);
        if (findPreferenceByResId == null) {
            return;
        }
        findPreferenceByResId.setSummary(getSummaryFromFeatureSwitchDialog(this.context, this.prefs, ImageCaptionConstants$FeatureSwitchDialogResources.TEXT_RECOGNITION));
        findPreferenceByResId.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$$ExternalSyntheticLambda6
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return AutomaticDescriptionsFragment.this.m120x5f42e546(findPreferenceByResId, preference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showToast(Context context, int i) {
        Toast.makeText(context, i, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreferenceSummary(Preference preference, int i) {
        if (isVisible() && preference.isVisible()) {
            preference.setSummary(i);
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    protected CharSequence getTitle() {
        return getText(R.string.title_pref_icon_image_description);
    }

    /* renamed from: lambda$displayDialogForGeminiNano$4$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment, reason: not valid java name */
    public /* synthetic */ void m114xac2060a4(Preference preference, Void r2) {
        setupPreferenceForGeminiNano(preference);
    }

    /* renamed from: lambda$setupDetailedImageDescriptionPreference$5$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment, reason: not valid java name */
    public /* synthetic */ boolean m115xb4794fec(ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources, final SwitchPreference switchPreference, Preference preference, Object obj) {
        if (!Boolean.TRUE.equals(obj)) {
            return true;
        }
        new FeatureSwitchDialog(this, this.context, imageCaptionConstants$FeatureSwitchDialogResources, false, R.string.enable_gemini) { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment.6
            final /* synthetic */ AutomaticDescriptionsFragment this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.accessibility.talkback.imagecaption.FeatureSwitchDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
            public void handleDialogClick(int i) {
                super.handleDialogClick(i);
                if (i != -2) {
                    if (i != -1) {
                        return;
                    }
                    switchPreference.setChecked(true);
                    AutomaticDescriptionsFragment automaticDescriptionsFragment = this.this$0;
                    TalkBackAnalyticsImpl.onGeminiOptInFromSettings(automaticDescriptionsFragment.prefs, this.context, 2, true);
                    return;
                }
                LogUtils.v(AutomaticDescriptionsFragment.TAG, "Does not accept the Opt-in.", new Object[0]);
                AutomaticDescriptionsFragment automaticDescriptionsFragment2 = this.this$0;
                TalkBackAnalyticsImpl.onGeminiOptInFromSettings(automaticDescriptionsFragment2.prefs, this.context, 3, true);
            }
        }.showDialog();
        TalkBackAnalyticsImpl.onGeminiOptInFromSettings(this.prefs, this.context, 1, true);
        return false;
    }

    /* renamed from: lambda$setupPreferenceForDynamicFeature$0$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment, reason: not valid java name */
    public /* synthetic */ boolean m116x12e2e5c0(final ModuleDownloadPrompter moduleDownloadPrompter, final ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources, final Preference preference, Preference preference2) {
        if (moduleDownloadPrompter.needDownloadDialog$ar$edu(2)) {
            moduleDownloadPrompter.showDownloadDialog$ar$edu(2);
            return true;
        }
        new FeatureSwitchDialog(this, this.context, imageCaptionConstants$FeatureSwitchDialogResources, true) { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment.2
            final /* synthetic */ AutomaticDescriptionsFragment this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.accessibility.talkback.imagecaption.FeatureSwitchDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
            public void handleDialogClick(int i) {
                super.handleDialogClick(i);
                if (i != -2) {
                    if (i != -1) {
                        return;
                    }
                    Preference preference3 = preference;
                    Context context = this.context;
                    AutomaticDescriptionsFragment automaticDescriptionsFragment = this.this$0;
                    preference3.setSummary(AutomaticDescriptionsFragment.getSummaryFromFeatureSwitchDialog(context, automaticDescriptionsFragment.prefs, imageCaptionConstants$FeatureSwitchDialogResources));
                    return;
                }
                LogUtils.v(AutomaticDescriptionsFragment.TAG, "Requests a uninstallation.", new Object[0]);
                moduleDownloadPrompter.uninstallDialog.showDialog();
            }
        }.showDialog();
        return true;
    }

    /* renamed from: lambda$setupPreferenceForGeminiNano$1$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment, reason: not valid java name */
    public /* synthetic */ boolean m117x6ba7f013(Preference preference) {
        showToast(this.context, R.string.message_aifeature_downloading);
        return true;
    }

    /* renamed from: lambda$setupPreferenceForGeminiNano$2$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment, reason: not valid java name */
    public /* synthetic */ boolean m118x54afb514(final Preference preference, ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources, Preference preference2) {
        if (displayDialogForGeminiNano(preference)) {
            return true;
        }
        FeatureSwitchDialog featureSwitchDialog = new FeatureSwitchDialog(this, this.context, imageCaptionConstants$FeatureSwitchDialogResources, false) { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment.4
            final /* synthetic */ AutomaticDescriptionsFragment this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.accessibility.talkback.imagecaption.FeatureSwitchDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
            public void handleDialogClick(int i) {
                super.handleDialogClick(i);
                if (i == -1) {
                    this.this$0.setupPreferenceForGeminiNano(preference);
                }
            }
        };
        featureSwitchDialog.setIncludeNegativeButton(false);
        featureSwitchDialog.showDialog();
        return true;
    }

    /* renamed from: lambda$setupPreferenceForGeminiNano$3$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment, reason: not valid java name */
    public /* synthetic */ boolean m119x3db77a15(Preference preference, FeatureSwitchDialog featureSwitchDialog, Preference preference2) {
        if (displayDialogForGeminiNano(preference)) {
            return true;
        }
        featureSwitchDialog.showDialog();
        TalkBackAnalyticsImpl.onGeminiOptInFromSettings(this.prefs, this.context, 1, false);
        return true;
    }

    /* renamed from: lambda$setupTextRecognitionPreference$6$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment, reason: not valid java name */
    public /* synthetic */ boolean m120x5f42e546(final Preference preference, Preference preference2) {
        new FeatureSwitchDialog(this, this.context, ImageCaptionConstants$FeatureSwitchDialogResources.TEXT_RECOGNITION, false) { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment.7
            final /* synthetic */ AutomaticDescriptionsFragment this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.accessibility.talkback.imagecaption.FeatureSwitchDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
            public void handleDialogClick(int i) {
                super.handleDialogClick(i);
                preference.setSummary(AutomaticDescriptionsFragment.getSummaryFromFeatureSwitchDialog(this.context, this.this$0.prefs, ImageCaptionConstants$FeatureSwitchDialogResources.TEXT_RECOGNITION));
            }
        }.showDialog();
        return true;
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        ListenableFuture<Boolean> submit;
        super.onCreatePreferences(bundle, str);
        Context context = getContext();
        this.context = context;
        if (context == null) {
            return;
        }
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        SplitApkDownloader splitApkDownloader = SplitApkDownloader.getInstance(this.context);
        this.downloader = splitApkDownloader;
        splitApkDownloader.updateAllDownloadStatus();
        if (this.aiCoreEndpoint == null) {
            this.aiCoreEndpoint = new AiCoreEndpoint(this.context, false);
        }
        AiCoreEndpoint aiCoreEndpoint = this.aiCoreEndpoint;
        if (!GeminiConfig.enableOnDeviceGeminiImageCaptioning(aiCoreEndpoint.context)) {
            submit = ContextDataProvider.immediateFuture(false);
        } else if (aiCoreEndpoint.needAiCoreUpdate()) {
            submit = ContextDataProvider.immediateFuture(true);
        } else if (aiCoreEndpoint.hasAiCoreCached()) {
            submit = ContextDataProvider.immediateFuture(true);
        } else if (aiCoreEndpoint.listFeatures.isDone()) {
            submit = ContextDataProvider.immediateFuture(Boolean.valueOf(aiCoreEndpoint.hasAiCore));
        } else {
            submit = ContextDataProvider.listeningDecorator(Executors.newSingleThreadExecutor()).submit((Callable) new WorkerWrapper$$ExternalSyntheticLambda0(aiCoreEndpoint, 6));
        }
        this.hasAiCoreFuture = submit;
        setupIconDetectionPreference();
        setupImageDescriptionPreference();
        setupTextRecognitionPreference();
        setupDetailedImageDescriptionPreference(ImageCaptionConstants$FeatureSwitchDialogResources.DETAILED_IMAGE_DESCRIPTION);
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        IconDetectionModuleDownloadPrompter iconDetectionModuleDownloadPrompter = this.iconDetectionModuleDownloadPrompter;
        if (iconDetectionModuleDownloadPrompter != null) {
            iconDetectionModuleDownloadPrompter.shutdown();
            this.iconDetectionModuleDownloadPrompter = null;
        }
        ImageDescriptionModuleDownloadPrompter imageDescriptionModuleDownloadPrompter = this.imageDescriptionModuleDownloadPrompter;
        if (imageDescriptionModuleDownloadPrompter != null) {
            imageDescriptionModuleDownloadPrompter.shutdown();
            this.imageDescriptionModuleDownloadPrompter = null;
        }
        AiCoreEndpoint aiCoreEndpoint = this.aiCoreEndpoint;
        if (aiCoreEndpoint != null) {
            aiCoreEndpoint.downloadCallback = null;
            aiCoreEndpoint.onUnbind();
            this.aiCoreEndpoint = null;
        }
        super.onDestroy();
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment, android.support.v4.app.Fragment
    public void onResume() {
        Preference findPreferenceByResId;
        super.onResume();
        if (this.useAiCore.isPresent() && (findPreferenceByResId = findPreferenceByResId(R.string.pref_image_description_key)) != null && ImageCaptioner.supportsIconDetection$ar$ds()) {
            if (this.useAiCore.get().booleanValue()) {
                setupPreferenceForGeminiNano(findPreferenceByResId);
            } else {
                setupPreferenceForGarcon(findPreferenceByResId);
            }
        }
    }

    void setAiCoreEndpoint(AiCoreEndpoint aiCoreEndpoint) {
        AiCoreEndpoint aiCoreEndpoint2 = this.aiCoreEndpoint;
        if (aiCoreEndpoint2 != null) {
            aiCoreEndpoint2.onUnbind();
        }
        this.aiCoreEndpoint = aiCoreEndpoint;
    }

    void setModuleDownloadPrompter(IconDetectionModuleDownloadPrompter iconDetectionModuleDownloadPrompter, ImageDescriptionModuleDownloadPrompter imageDescriptionModuleDownloadPrompter) {
        this.iconDetectionModuleDownloadPrompter = iconDetectionModuleDownloadPrompter;
        this.imageDescriptionModuleDownloadPrompter = imageDescriptionModuleDownloadPrompter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AutomaticDescriptionDownloadStateListener implements ModuleDownloadPrompter.DownloadStateListener {
        private final ImageCaptionConstants$DownloadStateListenerResources listenerResources;
        private final TalkBackAnalytics.ImageCaptionLogKeys logKeys;
        private final int moduleSize;
        private final Preference preference;
        private final ImageCaptionConstants$ImageCaptionPreferenceKeys preferenceKeys;

        private AutomaticDescriptionDownloadStateListener(Preference preference, ImageCaptionConstants$DownloadStateListenerResources imageCaptionConstants$DownloadStateListenerResources, ImageCaptionConstants$ImageCaptionPreferenceKeys imageCaptionConstants$ImageCaptionPreferenceKeys, int i, TalkBackAnalytics.ImageCaptionLogKeys imageCaptionLogKeys) {
            this.preference = preference;
            this.listenerResources = imageCaptionConstants$DownloadStateListenerResources;
            this.preferenceKeys = imageCaptionConstants$ImageCaptionPreferenceKeys;
            this.moduleSize = i;
            this.logKeys = imageCaptionLogKeys;
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public void onAccepted() {
            TalkBackAnalytics.ImageCaptionLogKeys imageCaptionLogKeys = this.logKeys;
            AutomaticDescriptionsFragment automaticDescriptionsFragment = AutomaticDescriptionsFragment.this;
            TalkBackAnalyticsImpl.onImageCaptionEventFromSettings(automaticDescriptionsFragment.prefs, automaticDescriptionsFragment.context, imageCaptionLogKeys.installRequest);
            AutomaticDescriptionsFragment.this.updatePreferenceSummary(this.preference, R.string.summary_pref_module_downloading);
            AutomaticDescriptionsFragment.this.putBooleanPref(this.preferenceKeys.uninstalledKey, false);
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public void onFailed(int i) {
            AutomaticDescriptionsFragment.this.updatePreferenceSummary(this.preference, R.string.summary_pref_auto_image_captioning_disabled);
            if (!TalkBackService.isServiceActive()) {
                AutomaticDescriptionsFragment automaticDescriptionsFragment = AutomaticDescriptionsFragment.this;
                TalkBackAnalyticsImpl.onImageCaptionEventFromSettings(automaticDescriptionsFragment.prefs, automaticDescriptionsFragment.context, this.logKeys.installFail);
                if (i == 102) {
                    AutomaticDescriptionsFragment.showToast(AutomaticDescriptionsFragment.this.context, R.string.download_network_error_hint);
                } else {
                    if (i != 103) {
                        AutomaticDescriptionsFragment.showToast(AutomaticDescriptionsFragment.this.context, this.listenerResources.downloadFailedHint);
                        return;
                    }
                    AutomaticDescriptionsFragment automaticDescriptionsFragment2 = AutomaticDescriptionsFragment.this;
                    AutomaticDescriptionsFragment.showToast(automaticDescriptionsFragment2.context, automaticDescriptionsFragment2.getString(R.string.download_storage_error_hint, Integer.valueOf(this.moduleSize)));
                }
            }
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public void onInstalled() {
            ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources;
            if (this.listenerResources == ImageCaptionConstants$DownloadStateListenerResources.ICON_DETECTION) {
                imageCaptionConstants$FeatureSwitchDialogResources = ImageCaptionConstants$FeatureSwitchDialogResources.ICON_DETECTION;
            } else {
                imageCaptionConstants$FeatureSwitchDialogResources = ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION;
            }
            AutomaticDescriptionsFragment automaticDescriptionsFragment = AutomaticDescriptionsFragment.this;
            automaticDescriptionsFragment.updatePreferenceSummary(this.preference, AutomaticDescriptionsFragment.getSummaryFromFeatureSwitchDialog(automaticDescriptionsFragment.context, automaticDescriptionsFragment.prefs, imageCaptionConstants$FeatureSwitchDialogResources));
            if (!TalkBackService.isServiceActive()) {
                AutomaticDescriptionsFragment automaticDescriptionsFragment2 = AutomaticDescriptionsFragment.this;
                TalkBackAnalyticsImpl.onImageCaptionEventFromSettings(automaticDescriptionsFragment2.prefs, automaticDescriptionsFragment2.context, this.logKeys.installSuccess);
                AutomaticDescriptionsFragment.showToast(AutomaticDescriptionsFragment.this.context, this.listenerResources.downloadSuccessfulHint);
            }
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public void onRejected() {
            TalkBackAnalytics.ImageCaptionLogKeys imageCaptionLogKeys = this.logKeys;
            AutomaticDescriptionsFragment automaticDescriptionsFragment = AutomaticDescriptionsFragment.this;
            TalkBackAnalyticsImpl.onImageCaptionEventFromSettings(automaticDescriptionsFragment.prefs, automaticDescriptionsFragment.context, imageCaptionLogKeys.installDeny);
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public void onDialogDismissed(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass3 implements AiCoreEndpoint.AiFeatureDownloadCallback {
        final /* synthetic */ AutomaticDescriptionsFragment this$0;
        final /* synthetic */ Preference val$optInPreference;

        public AnonymousClass3(AutomaticDescriptionsFragment automaticDescriptionsFragment, Preference preference) {
            this.val$optInPreference = preference;
            this.this$0 = automaticDescriptionsFragment;
        }

        /* renamed from: lambda$onDownloadCompleted$0$com-google-android-accessibility-talkback-preference-base-AutomaticDescriptionsFragment$3, reason: not valid java name */
        public /* synthetic */ void m124x7242f5b1(Preference preference) {
            this.this$0.setupPreferenceForGeminiNano(preference);
        }

        @Override // com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint.AiFeatureDownloadCallback
        public void onDownloadCompleted() {
            if (this.this$0.getActivity() == null) {
                LogUtils.d(AutomaticDescriptionsFragment.TAG, "AiFeature download completed - The activity is null.", new Object[0]);
                return;
            }
            AutomaticDescriptionsFragment automaticDescriptionsFragment = this.this$0;
            final Preference preference = this.val$optInPreference;
            automaticDescriptionsFragment.getActivity().runOnUiThread(new Runnable() { // from class: com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AutomaticDescriptionsFragment.AnonymousClass3.this.m124x7242f5b1(preference);
                }
            });
        }

        @Override // com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint.AiFeatureDownloadCallback
        public void onDownloadProgress(long j, long j2) {
        }
    }
}

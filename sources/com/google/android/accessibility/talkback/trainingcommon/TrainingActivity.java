package com.google.android.accessibility.talkback.trainingcommon;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.activity.OnBackPressedCallback;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsDBHelper;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingType;
import com.google.android.accessibility.talkback.analytics.TrainingUsageProto$PageDuration;
import com.google.android.accessibility.talkback.analytics.TrainingUsageProto$TrainingMetricWrapper;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.keyboard.TalkBackPhysicalKeyboardShortcut;
import com.google.android.accessibility.talkback.logging.TrainingMetricHolder;
import com.google.android.accessibility.talkback.permission.PermissionRequestActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.accessibility.utils.AccessibilityServiceCompatUtils$Constants;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import j$.time.Duration;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TrainingActivity extends FragmentActivity {
    private final ImmutableMap docPageToMetric;
    public TrainingIpcClient ipcClient;
    public WindowTrackerFactory metricStore$ar$class_merging$ar$class_merging;
    private ViewGroup navigationBarContainer;
    public NavigationButtonBar navigationButtonBar;
    public WindowTrackerFactory navigationController$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public AccessibilityButtonMonitor pageController$ar$class_merging;
    private TalkBackEnabledReceiver talkBackEnabledReceiver;
    private boolean talkbackEnabledReceiverRegistered;
    private TrainingConfig training;
    private boolean trainingInSessionLogged;
    private boolean trainingLogged;
    public final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
    private final WindowTrackerFactory trainingActivityInterfaceInjector$ar$class_merging$ar$class_merging$ar$class_merging = WindowTrackerFactory.getInstance$ar$class_merging$14dd424f_0$ar$class_merging$ar$class_merging();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TalkBackEnabledReceiver extends BroadcastReceiver {
        static final IntentFilter TALKBACK_ENABLED_INTENT_FILTER = new IntentFilter("com.google.android.accessibility.talkback.ENABLED");
        private final HapticPatternParser$$ExternalSyntheticLambda1 callback$ar$class_merging$b8efd5fb_0$ar$class_merging$ar$class_merging;

        public TalkBackEnabledReceiver(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
            this.callback$ar$class_merging$b8efd5fb_0$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            ((TrainingActivity) this.callback$ar$class_merging$b8efd5fb_0$ar$class_merging$ar$class_merging.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).ipcClient.bindService();
        }
    }

    public TrainingActivity() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0(Integer.valueOf(R.string.talkback_tutorial_title), 2);
        builder.put$ar$ds$de9b9d28_0(Integer.valueOf(R.string.new_feature_in_talkback_title), 1);
        builder.put$ar$ds$de9b9d28_0(Integer.valueOf(R.string.welcome_to_talkback_title), 3);
        builder.put$ar$ds$de9b9d28_0(Integer.valueOf(R.string.using_text_boxes_title), 4);
        builder.put$ar$ds$de9b9d28_0(Integer.valueOf(R.string.read_by_character_title), 5);
        builder.put$ar$ds$de9b9d28_0(Integer.valueOf(R.string.voice_commands_title), 6);
        builder.put$ar$ds$de9b9d28_0(Integer.valueOf(R.string.making_calls_title), 7);
        builder.put$ar$ds$de9b9d28_0(Integer.valueOf(R.string.practice_gestures_title), 8);
        this.docPageToMetric = builder.buildOrThrow();
    }

    public static Intent createTrainingIntent(Context context, TrainingConfig.TrainingId trainingId) {
        return createTrainingIntent(context, trainingId, false);
    }

    protected static final TrainingConfig.TrainingId getTrainingIdFromIntent$ar$ds(Intent intent) {
        return (TrainingConfig.TrainingId) intent.getSerializableExtra("training");
    }

    private final void initialize(Intent intent) {
        TrainingConfig trainingConfig = null;
        int i = 0;
        if (!isTalkBackEnabled(this) && !isOnboarding() && SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(this)) {
            final SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(getApplicationContext());
            if (!sharedPreferences.getBoolean(getString(R.string.accessibility_tutorial_talkback_is_off_dialog_do_not_show_again), false)) {
                View inflate = LayoutInflater.from(this).inflate(R.layout.do_not_show_again_checkbox_dialog, (ViewGroup) null);
                CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.dont_show_again);
                checkBox.setText(R.string.do_not_show_again_check_box);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda2
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        sharedPreferences.edit().putBoolean(TrainingActivity.this.getString(R.string.accessibility_tutorial_talkback_is_off_dialog_do_not_show_again), z).apply();
                    }
                });
                ((TextView) inflate.findViewById(R.id.dialog_content)).setText(getString(R.string.talkback_inactive_warning_message));
                getSupportFragmentManager();
                ApplicationModule materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging = A11yAlertDialogWrapper.materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging(this);
                materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setView$ar$class_merging$ar$ds(inflate);
                materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.talkback_inactive_title);
                materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setCancelable$ar$class_merging$ar$ds();
                materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(R.string.talkback_inactive_go_to_settings_button, new PermissionRequestActivity$$ExternalSyntheticLambda0(this, 4));
                materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.talkback_inactive_warning_positive_button, null);
                materialDialogBuilder$ar$class_merging$83563745_0$ar$ds$ar$class_merging.create().show();
            }
        }
        if (Objects.equals(intent.getAction(), "com.google.android.accessibility.talkback.training.START")) {
            if (this.formFactorUtils.isAndroidTv) {
                trainingConfig = WindowTrackerFactory.getTraining$ar$ds(TrainingConfig.TrainingId.TRAINING_ID_TUTORIAL_FOR_TV, this);
            }
        } else {
            TrainingConfig.TrainingId trainingIdFromIntent$ar$ds = getTrainingIdFromIntent$ar$ds(intent);
            if (trainingIdFromIntent$ar$ds != null) {
                trainingConfig = WindowTrackerFactory.getTraining$ar$ds(trainingIdFromIntent$ar$ds, this);
            }
        }
        if (trainingConfig == null) {
            finishOnAbort(false);
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("training_show_exit_banner", false);
        if (this.ipcClient == null) {
            this.ipcClient = new TrainingIpcClient(getApplicationContext(), this, booleanExtra, new TrainingActivity$$ExternalSyntheticLambda1(this, 2));
        }
        this.ipcClient.bindService();
        setContentView(R.layout.training_activity);
        this.training = trainingConfig;
        this.pageController$ar$class_merging = new AccessibilityButtonMonitor(trainingConfig, this, this.ipcClient.serviceData, this.metricStore$ar$class_merging$ar$class_merging);
        this.navigationController$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory(this, this.pageController$ar$class_merging);
        this.navigationBarContainer = (ViewGroup) findViewById(R.id.nav_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.training_toolbar);
        if (toolbar != null) {
            if (this.training.isSupportNavigateUpArrow()) {
                try {
                    setActionBar(toolbar);
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_gm_grey_24dp);
                    toolbar.setNavigationContentDescription(R.string.training_navigate_up);
                    WindowTrackerFactory windowTrackerFactory = this.navigationController$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    windowTrackerFactory.getClass();
                    toolbar.setNavigationOnClickListener(new TrainingActivity$$ExternalSyntheticLambda0(windowTrackerFactory, i));
                    getActionBar().setDisplayShowTitleEnabled(false);
                } catch (NullPointerException e) {
                    LogUtils.e("TrainingActivity", "TrainingActivity crashed when setting action bar. %s", e.getMessage());
                    ((LinearLayout) findViewById(R.id.training_toolbar_layout)).removeView(toolbar);
                }
            } else {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.training_toolbar_layout);
                if (linearLayout != null) {
                    linearLayout.removeView(toolbar);
                }
            }
        }
        this.pageController$ar$class_merging.switchPage(0);
    }

    private static boolean isTalkBackEnabled(Context context) {
        return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
    }

    private final void prepareAnalytics() {
        TrainingMetricStore$Type trainingMetricStore$Type;
        if (isOnboarding()) {
            trainingMetricStore$Type = TrainingMetricStore$Type.ONBOARDING;
        } else {
            trainingMetricStore$Type = TrainingMetricStore$Type.TUTORIAL;
        }
        this.metricStore$ar$class_merging$ar$class_merging = new WindowTrackerFactory((Context) this, trainingMetricStore$Type);
        this.trainingLogged = false;
    }

    private final void registerTalkBackEnabledReceiver() {
        if (!isTalkBackEnabled(getApplicationContext())) {
            EditorInfoCompat.registerReceiver$ar$ds(this, this.talkBackEnabledReceiver, TalkBackEnabledReceiver.TALKBACK_ENABLED_INTENT_FILTER, 2);
            this.talkbackEnabledReceiverRegistered = true;
        }
    }

    public final void finishOnAbort(boolean z) {
        if (z) {
            notifyTrainingFinishByUser();
        }
        Intent intent = new Intent();
        intent.putExtra("user_initiated", z);
        setResult(0, intent);
        finish();
    }

    public final PageConfig.PageId getCurrentPageId() {
        PageConfig currentPageConfig;
        if (this.training == null) {
            currentPageConfig = null;
        } else {
            currentPageConfig = this.pageController$ar$class_merging.getCurrentPageConfig();
        }
        if (currentPageConfig == null) {
            return null;
        }
        return currentPageConfig.getPageId();
    }

    public final boolean isOnboarding() {
        TrainingConfig.TrainingId trainingIdFromIntent$ar$ds = getTrainingIdFromIntent$ar$ds(getIntent());
        if (trainingIdFromIntent$ar$ds == null) {
            return false;
        }
        if (trainingIdFromIntent$ar$ds != TrainingConfig.TrainingId.TRAINING_ID_ON_BOARDING_TALKBACK && trainingIdFromIntent$ar$ds != TrainingConfig.TrainingId.TRAINING_ID_FIRST_RUN_AFTER_UPDATED_ON_BOARDING_TALKBACK && trainingIdFromIntent$ar$ds != TrainingConfig.TrainingId.TRAINING_ID_ON_BOARDING_FOR_MULTIFINGER_GESTURES) {
            return false;
        }
        return true;
    }

    public final void notifyTrainingFinishByUser() {
        sendMessageToService(Message.obtain((Handler) null, 2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        prepareAnalytics();
        initialize(getIntent());
        this.talkBackEnabledReceiver = new TalkBackEnabledReceiver(new HapticPatternParser$$ExternalSyntheticLambda1(this));
        getWindow().addFlags(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
        if (bundle == null) {
            getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback() { // from class: com.google.android.accessibility.talkback.trainingcommon.TrainingActivity.1
                @Override // androidx.activity.OnBackPressedCallback
                public final void handleOnBackPressed() {
                    boolean backToLinkIndexPage;
                    WindowTrackerFactory windowTrackerFactory = TrainingActivity.this.navigationController$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    TrainingActivity trainingActivity = (TrainingActivity) ((WeakReference) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).get();
                    if (trainingActivity != null) {
                        AccessibilityButtonMonitor accessibilityButtonMonitor = (AccessibilityButtonMonitor) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider;
                        if (accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$accessibilityButtonCallback == null) {
                            backToLinkIndexPage = accessibilityButtonMonitor.previousPage();
                        } else {
                            backToLinkIndexPage = accessibilityButtonMonitor.backToLinkIndexPage();
                        }
                        if (!backToLinkIndexPage) {
                            trainingActivity.finishOnAbort(true);
                        }
                    }
                }
            });
        }
    }

    public final void onIpcServerDestroyed() {
        LogUtils.w("TrainingActivity", "Server destroyed.", new Object[0]);
        registerTalkBackEnabledReceiver();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        prepareAnalytics();
        initialize(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0081, code lost:
    
        if (r6.trainingLogged == false) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onPageSwitched(int r7, android.util.Pair r8, java.util.function.Function r9) {
        /*
            Method dump skipped, instructions count: 422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.trainingcommon.TrainingActivity.onPageSwitched(int, android.util.Pair, java.util.function.Function):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onPause() {
        TrainingMetricStore$Type trainingMetricStore$Type;
        Duration duration;
        ImmutableMap immutableMap;
        ImmutableSet immutableSet;
        int i;
        int i2;
        super.onPause();
        WindowTrackerFactory windowTrackerFactory = this.metricStore$ar$class_merging$ar$class_merging;
        TrainingMetricHolder trainingMetricHolder = (TrainingMetricHolder) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
        trainingMetricHolder.onTrainingPageLeft(getCurrentPageId());
        if (trainingMetricHolder.countingCompleteDuration) {
            trainingMetricHolder.completedTrainingTime = trainingMetricHolder.completedTrainingTime.plus(Duration.ofMillis(SystemClock.uptimeMillis() - trainingMetricHolder.startTrainingTime));
        }
        Object obj = windowTrackerFactory.WindowTrackerFactory$ar$executorProvider;
        Object obj2 = windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
        TrainingMetricHolder.TrainingMetric.Builder builder = new TrainingMetricHolder.TrainingMetric.Builder(null);
        builder.setStayingPageDuration$ar$ds(RegularImmutableMap.EMPTY);
        builder.setTrainingCompletedDuration$ar$ds(Duration.ZERO);
        builder.setCompletedPages$ar$ds(RegularImmutableSet.EMPTY);
        TrainingMetricHolder trainingMetricHolder2 = (TrainingMetricHolder) obj2;
        TrainingMetricStore$Type trainingMetricStore$Type2 = trainingMetricHolder2.trainingType;
        if (trainingMetricStore$Type2 != null) {
            builder.type = trainingMetricStore$Type2;
            builder.trainingStarted = trainingMetricHolder2.hasStartedTraining;
            builder.set$0 = (byte) 1;
            if (!trainingMetricHolder2.pageStayingDurationMap.isEmpty()) {
                builder.setStayingPageDuration$ar$ds(ImmutableMap.copyOf(trainingMetricHolder2.pageStayingDurationMap));
            }
            if (trainingMetricHolder2.hasStartedTraining) {
                trainingMetricHolder2.hasStartedTraining = false;
            }
            if (trainingMetricHolder2.hasCompletedTraining) {
                builder.setTrainingCompletedDuration$ar$ds(trainingMetricHolder2.completedTrainingTime);
                trainingMetricHolder2.hasCompletedTraining = false;
                trainingMetricHolder2.completedTrainingTime = Duration.ZERO;
            }
            if (!trainingMetricHolder2.completedPages.isEmpty()) {
                builder.setCompletedPages$ar$ds(ImmutableSet.copyOf((Collection) trainingMetricHolder2.completedPages));
            }
            trainingMetricHolder2.pageEnterTimeMap.clear();
            trainingMetricHolder2.pageStayingDurationMap.clear();
            trainingMetricHolder2.completedPages.clear();
            if (builder.set$0 == 1 && (trainingMetricStore$Type = builder.type) != null && (duration = builder.trainingCompletedDuration) != null && (immutableMap = builder.stayingPageDuration) != null && (immutableSet = builder.completedPages) != null) {
                TrainingMetricHolder.TrainingMetric trainingMetric = new TrainingMetricHolder.TrainingMetric(trainingMetricStore$Type, builder.trainingStarted, duration, immutableMap, immutableSet);
                SQLiteDatabase safeGetWritableDatabase = ((TalkBackAnalyticsDBHelper) obj).safeGetWritableDatabase();
                ContentValues contentValues = new ContentValues();
                ImmutableMap immutableMap2 = trainingMetric.stayingPageDuration;
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) TrainingUsageProto$TrainingMetricWrapper.DEFAULT_INSTANCE.createBuilder();
                TrainingMetricStore$Type trainingMetricStore$Type3 = trainingMetric.type;
                TalkBackPhysicalKeyboardShortcut talkBackPhysicalKeyboardShortcut = TalkBackPhysicalKeyboardShortcut.ACTION_UNKNOWN;
                GestureShortcutMapping.TalkbackAction talkbackAction = GestureShortcutMapping.TalkbackAction.UNASSIGNED_ACTION;
                CursorGranularity cursorGranularity = CursorGranularity.DEFAULT;
                int ordinal = trainingMetricStore$Type3.ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        i = TrainingProto$TrainingType.TYPE_UNKNOWN$ar$edu;
                    } else {
                        i = TrainingProto$TrainingType.TYPE_ONBOARDING$ar$edu;
                    }
                } else {
                    i = TrainingProto$TrainingType.TYPE_TUTORIAL$ar$edu;
                }
                builder2.copyOnWrite();
                TrainingUsageProto$TrainingMetricWrapper trainingUsageProto$TrainingMetricWrapper = (TrainingUsageProto$TrainingMetricWrapper) builder2.instance;
                if (i != 0) {
                    trainingUsageProto$TrainingMetricWrapper.trainingType_ = i - 1;
                    trainingUsageProto$TrainingMetricWrapper.bitField0_ |= 1;
                    boolean z = trainingMetric.trainingStarted;
                    builder2.copyOnWrite();
                    TrainingUsageProto$TrainingMetricWrapper trainingUsageProto$TrainingMetricWrapper2 = (TrainingUsageProto$TrainingMetricWrapper) builder2.instance;
                    trainingUsageProto$TrainingMetricWrapper2.bitField0_ |= 2;
                    trainingUsageProto$TrainingMetricWrapper2.trainingStarted_ = z;
                    int seconds = (int) trainingMetric.trainingCompletedDuration.toSeconds();
                    builder2.copyOnWrite();
                    TrainingUsageProto$TrainingMetricWrapper trainingUsageProto$TrainingMetricWrapper3 = (TrainingUsageProto$TrainingMetricWrapper) builder2.instance;
                    trainingUsageProto$TrainingMetricWrapper3.bitField0_ |= 4;
                    trainingUsageProto$TrainingMetricWrapper3.trainingCompletedDurationInSecond_ = seconds;
                    UnmodifiableIterator listIterator = immutableMap2.entrySet().listIterator();
                    while (listIterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) listIterator.next();
                        PageConfig.PageId pageId = (PageConfig.PageId) entry.getKey();
                        Duration duration2 = (Duration) entry.getValue();
                        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) TrainingUsageProto$PageDuration.DEFAULT_INSTANCE.createBuilder();
                        switch (pageId.ordinal()) {
                            case 1:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_FINISHED$ar$edu;
                                break;
                            case 2:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WELCOME_TO_UPDATED_TALKBACK_FOR_MULTIFINGER_GESTURES$ar$edu;
                                break;
                            case 3:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WELCOME_TO_TALKBACK$ar$edu;
                                break;
                            case 4:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_EXPLORE_BY_TOUCH$ar$edu;
                                break;
                            case 5:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_SCROLLING$ar$edu;
                                break;
                            case 6:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_GESTURES_PAGE_FOR_GESTURE_NAVIGATION_USER$ar$edu;
                                break;
                            case 7:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_GESTURES_PAGE_FOR_3_BUTTON_NAVIGATION_USER$ar$edu;
                                break;
                            case 8:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_ADJUSTING_VOLUME$ar$edu;
                                break;
                            case 9:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_MENUS$ar$edu;
                                break;
                            case 10:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_MENUS_PRE_R$ar$edu;
                                break;
                            case 11:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TUTORIAL_FINISHED$ar$edu;
                                break;
                            case 12:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TUTORIAL_INDEX$ar$edu;
                                break;
                            case 13:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_USING_TEXT_BOXES$ar$edu;
                                break;
                            case 14:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TYPING_TEXT$ar$edu;
                                break;
                            case 15:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_MOVING_CURSOR$ar$edu;
                                break;
                            case 16:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_SELECTING_TEXT$ar$edu;
                                break;
                            case 17:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_SELECTING_TEXT_PRE_R$ar$edu;
                                break;
                            case 18:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_COPY_CUT_PASTE$ar$edu;
                                break;
                            case 19:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_COPY_CUT_PASTE_PRE_R$ar$edu;
                                break;
                            case 20:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TYPO_CORRECTION$ar$edu;
                                break;
                            case 21:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TYPO_CORRECTION_PRE_R$ar$edu;
                                break;
                            case 22:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TYPO_CORRECTION_NOT_ENGLISH$ar$edu;
                                break;
                            case 23:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TYPO_CORRECTION_NOT_ENGLISH_PRE_R$ar$edu;
                                break;
                            case 24:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_READ_BY_CHARACTER$ar$edu;
                                break;
                            case 25:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_READ_BY_CHARACTER_PRE_R$ar$edu;
                                break;
                            case 26:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_JUMP_BETWEEN_CONTROLS$ar$edu;
                                break;
                            case 27:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_JUMP_BETWEEN_CONTROLS_PRE_R$ar$edu;
                                break;
                            case 28:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_JUMP_BETWEEN_LINKS$ar$edu;
                                break;
                            case 29:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_JUMP_BETWEEN_LINKS_PRE_R$ar$edu;
                                break;
                            case 30:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_JUMP_BETWEEN_HEADINGS$ar$edu;
                                break;
                            case 31:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_JUMP_BETWEEN_HEADINGS_PRE_R$ar$edu;
                                break;
                            case 32:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_VOICE_COMMANDS$ar$edu;
                                break;
                            case 33:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_PRACTICE_GESTURES$ar$edu;
                                break;
                            case 34:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_PRACTICE_GESTURES_PRE_R$ar$edu;
                                break;
                            case 35:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_VOICE_COMMAND_OVERVIEW$ar$edu;
                                break;
                            case 36:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_VOICE_COMMAND_READING_CONTROLS$ar$edu;
                                break;
                            case 37:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_VOICE_COMMAND_FIND_ITEMS$ar$edu;
                                break;
                            case 38:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_VOICE_COMMAND_FIND_ITEMS_FOR_WATCH$ar$edu;
                                break;
                            case 39:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_VOICE_COMMAND_TEXT_EDITING$ar$edu;
                                break;
                            case 40:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_VOICE_COMMAND_DEVICE_NAVIGATION$ar$edu;
                                break;
                            case 41:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_VOICE_COMMAND_OTHER_COMMANDS$ar$edu;
                                break;
                            case 42:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_ADDITIONAL_TIPS_MAKING_CALLS$ar$edu;
                                break;
                            case 43:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_ADDITIONAL_TIPS_SENDING_MESSAGES$ar$edu;
                                break;
                            case 44:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_ADDITIONAL_TIPS_READING_WEB_EMAILS$ar$edu;
                                break;
                            case 45:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_ADDITIONAL_TIPS_LOOKOUT$ar$edu;
                                break;
                            case 46:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_ADDITIONAL_TIPS_CHECKING_NOTIFICATIONS$ar$edu;
                                break;
                            case 47:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_UPDATE_WELCOME_15_0_1$ar$edu;
                                break;
                            case 48:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_DETAILED_IMAGE_DESCRIPTIONS_15_0_1$ar$edu;
                                break;
                            case 49:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_GOOGLE_DISABILITY_SUPPORT_15_0_1$ar$edu;
                                break;
                            case 50:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_PUNCTUATION_AND_SYMBOLS_15_0_1$ar$edu;
                                break;
                            case 51:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_NEW_BRAILLE_SHORTCUTS_15_0_1$ar$edu;
                                break;
                            case 52:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WELCOME_TO_TALKBACK_WATCH$ar$edu;
                                break;
                            case 53:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WATCH_SCROLLING$ar$edu;
                                break;
                            case 54:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WATCH_GO_BACK$ar$edu;
                                break;
                            case 55:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WATCH_VOLUME_UP$ar$edu;
                                break;
                            case 56:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WATCH_VOLUME_DOWN$ar$edu;
                                break;
                            case 57:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WATCH_OPEN_TALKBACK_MENU$ar$edu;
                                break;
                            case 58:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_WATCH_END_TUTORIAL$ar$edu;
                                break;
                            case 59:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TV_OVERVIEW$ar$edu;
                                break;
                            case 60:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TV_REMOTE$ar$edu;
                                break;
                            case 61:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TV_SHORTCUT$ar$edu;
                                break;
                            case 62:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_TV_VENDOR$ar$edu;
                                break;
                            default:
                                i2 = TrainingProto$TrainingPageId.PAGE_ID_UNKNOWN$ar$edu;
                                break;
                        }
                        builder3.copyOnWrite();
                        TrainingUsageProto$PageDuration trainingUsageProto$PageDuration = (TrainingUsageProto$PageDuration) builder3.instance;
                        int i3 = i2 - 1;
                        if (i2 != 0) {
                            trainingUsageProto$PageDuration.pageId_ = i3;
                            trainingUsageProto$PageDuration.bitField0_ |= 1;
                            int seconds2 = (int) duration2.toSeconds();
                            builder3.copyOnWrite();
                            TrainingUsageProto$PageDuration trainingUsageProto$PageDuration2 = (TrainingUsageProto$PageDuration) builder3.instance;
                            trainingUsageProto$PageDuration2.bitField0_ |= 2;
                            trainingUsageProto$PageDuration2.durationInSecond_ = seconds2;
                            boolean contains = trainingMetric.completedPages.contains(pageId);
                            builder3.copyOnWrite();
                            TrainingUsageProto$PageDuration trainingUsageProto$PageDuration3 = (TrainingUsageProto$PageDuration) builder3.instance;
                            trainingUsageProto$PageDuration3.bitField0_ |= 4;
                            trainingUsageProto$PageDuration3.completed_ = contains;
                            builder2.copyOnWrite();
                            TrainingUsageProto$TrainingMetricWrapper trainingUsageProto$TrainingMetricWrapper4 = (TrainingUsageProto$TrainingMetricWrapper) builder2.instance;
                            TrainingUsageProto$PageDuration trainingUsageProto$PageDuration4 = (TrainingUsageProto$PageDuration) builder3.build();
                            trainingUsageProto$PageDuration4.getClass();
                            Internal.ProtobufList protobufList = trainingUsageProto$TrainingMetricWrapper4.pageDuration_;
                            if (!protobufList.isModifiable()) {
                                trainingUsageProto$TrainingMetricWrapper4.pageDuration_ = GeneratedMessageLite.mutableCopy(protobufList);
                            }
                            trainingUsageProto$TrainingMetricWrapper4.pageDuration_.add(trainingUsageProto$PageDuration4);
                        } else {
                            throw null;
                        }
                    }
                    byte[] byteArray = ((TrainingUsageProto$TrainingMetricWrapper) builder2.build()).toByteArray();
                    contentValues.put("trainingMetric", byteArray);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase, "TrainingEntry", Arrays.toString(byteArray), contentValues);
                    passPageIdToService(PageConfig.PageId.PAGE_ID_FINISHED);
                    TrainingIpcClient trainingIpcClient = this.ipcClient;
                    if (trainingIpcClient != null && trainingIpcClient.serviceMessenger != null) {
                        trainingIpcClient.context.unbindService(trainingIpcClient);
                        trainingIpcClient.sendMessage(Message.obtain((Handler) null, 4));
                        trainingIpcClient.serviceMessenger = null;
                    }
                    unregisterTalkBackEnabledReceiver();
                    return;
                }
                throw null;
            }
            StringBuilder sb = new StringBuilder();
            if (builder.type == null) {
                sb.append(" type");
            }
            if (builder.set$0 == 0) {
                sb.append(" trainingStarted");
            }
            if (builder.trainingCompletedDuration == null) {
                sb.append(" trainingCompletedDuration");
            }
            if (builder.stayingPageDuration == null) {
                sb.append(" stayingPageDuration");
            }
            if (builder.completedPages == null) {
                sb.append(" completedPages");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        throw new NullPointerException("Null type");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        TrainingMetricHolder trainingMetricHolder = (TrainingMetricHolder) this.metricStore$ar$class_merging$ar$class_merging.WindowTrackerFactory$ar$handlerProvider;
        trainingMetricHolder.onTrainingPageEntered(getCurrentPageId());
        if (trainingMetricHolder.countingCompleteDuration) {
            trainingMetricHolder.startTrainingTime = SystemClock.uptimeMillis();
        }
        TrainingIpcClient trainingIpcClient = this.ipcClient;
        if (trainingIpcClient != null) {
            trainingIpcClient.bindService();
        }
        registerTalkBackEnabledReceiver();
    }

    public final void passPageIdToService(PageConfig.PageId pageId) {
        if (pageId == null) {
            return;
        }
        Message obtain = Message.obtain((Handler) null, 0);
        Bundle bundle = new Bundle();
        bundle.putSerializable("training_page_id", pageId);
        obtain.setData(bundle);
        sendMessageToService(obtain);
    }

    public final void sendMessageToService(Message message) {
        TrainingIpcClient trainingIpcClient;
        if (isTalkBackEnabled(getApplicationContext()) && (trainingIpcClient = this.ipcClient) != null) {
            trainingIpcClient.sendMessage(message);
        }
    }

    public final void unregisterTalkBackEnabledReceiver() {
        if (this.talkbackEnabledReceiverRegistered) {
            unregisterReceiver(this.talkBackEnabledReceiver);
            this.talkbackEnabledReceiverRegistered = false;
        }
    }

    public static Intent createTrainingIntent(Context context, TrainingConfig.TrainingId trainingId, boolean z) {
        Intent intent = new Intent(context, (Class<?>) TrainingActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(67108864);
        if (FormFactorUtils.getInstance().isAndroidWear) {
            intent.addFlags(8388608);
        }
        intent.putExtra("training", trainingId);
        intent.putExtra("training_show_exit_banner", z);
        intent.setPackage("com.google.android.marvin.talkback");
        return intent;
    }
}

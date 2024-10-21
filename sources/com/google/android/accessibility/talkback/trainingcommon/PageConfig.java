package com.google.android.accessibility.talkback.trainingcommon;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.google.android.accessibility.talkback.training.OnboardingConfigs;
import com.google.android.accessibility.talkback.training.TutorialConfigUtils;
import com.google.android.accessibility.talkback.training.VoiceCommandAndHelpConfigs;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.talkback.trainingcommon.content.ClickableChip;
import com.google.android.accessibility.talkback.trainingcommon.content.Divider;
import com.google.android.accessibility.talkback.trainingcommon.content.EditTextBox;
import com.google.android.accessibility.talkback.trainingcommon.content.ExitBanner;
import com.google.android.accessibility.talkback.trainingcommon.content.Heading;
import com.google.android.accessibility.talkback.trainingcommon.content.PageButton;
import com.google.android.accessibility.talkback.trainingcommon.content.Text;
import com.google.android.accessibility.talkback.trainingcommon.content.TextWithIcon;
import com.google.android.accessibility.talkback.trainingcommon.content.Tip;
import com.google.android.accessibility.talkback.trainingcommon.tv.VendorConfigReader;
import com.google.android.accessibility.utils.AccessibilityServiceCompatUtils$Constants;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import googledata.experiments.mobile.accessibility_suite.features.TalkbackMistriggeringRecoveryConfig;
import j$.util.function.Predicate$CC;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PageConfig {
    public final ImmutableMap getCaptureFingerprintGestureIdToAnnouncements;
    public final ImmutableMap getCaptureGestureIdToAnnouncements;
    private final ImmutableList getContents;
    private final IdleAnnouncementConfig getIdleAnnouncementConfig;
    private final ExternalDrawableResource getImage;
    private final ImmutableList getNavigationButtons;
    private final PageId getPageId;
    private final int getPageNameResId;
    private final String getPageNameString;
    private final int getVendorPageIndex;
    private final boolean hasNavigationButtonBar;
    private final boolean isEndOfSection;
    private final boolean isOnlyOneFocus;
    private final boolean showPageNumber;
    private final PageAndContentPredicate showingPredicate;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IdleAnnouncementConfig {
        public IdleAnnouncementConfig() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof IdleAnnouncementConfig) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return -349392937;
        }

        public final String toString() {
            return "IdleAnnouncementConfig{announcement=2132086892, initialDelay=120000, repeatedDelay=30000}";
        }

        public IdleAnnouncementConfig(byte[] bArr) {
            this();
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ALWAYS_SHOW' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PageAndContentPredicate {
        private static final /* synthetic */ PageAndContentPredicate[] $VALUES;
        public static final PageAndContentPredicate ACCESSIBILITY_SERVICE_TOGGLE_VIA_SHORTCUT;
        public static final PageAndContentPredicate ALWAYS_NOT_SHOW;
        public static final PageAndContentPredicate ALWAYS_SHOW;
        public static final PageAndContentPredicate GESTURE_CHANGED;
        public static final PageAndContentPredicate ICON_DETECTION_AND_IMAGE_DESCRIPTION_UNAVAILABLE;
        public static final PageAndContentPredicate ICON_DETECTION_AVAILABLE_BUT_IMAGE_DESCRIPTION_UNAVAILABLE;
        public static final PageAndContentPredicate IMAGE_DESCRIPTION_UNAVAILABLE;
        public static final PageAndContentPredicate SUPPORT_EXIT_BANNER;
        public static final PageAndContentPredicate SUPPORT_SYSTEM_ACTIONS;
        public static final PageAndContentPredicate TALKBACK_ON;
        private final ImmutablePredicate predicate;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        interface ImmutablePredicate extends Predicate {
        }

        static {
            final int i = 1;
            final int i2 = 0;
            PageAndContentPredicate pageAndContentPredicate = new PageAndContentPredicate("ALWAYS_SHOW", 0, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate2 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate3 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate4 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate5 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate6 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate7 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate8 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            ALWAYS_SHOW = pageAndContentPredicate;
            PageAndContentPredicate pageAndContentPredicate2 = new PageAndContentPredicate("ALWAYS_NOT_SHOW", 1, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i2) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i2) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i2) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i2) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate3 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate4 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate5 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate6 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate7 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate8 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            ALWAYS_NOT_SHOW = pageAndContentPredicate2;
            final int i3 = 2;
            PageAndContentPredicate pageAndContentPredicate3 = new PageAndContentPredicate("TALKBACK_ON", 2, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i3) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i3) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i3) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i3) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate32 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate4 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate5 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate6 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate7 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate8 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            TALKBACK_ON = pageAndContentPredicate3;
            final int i4 = 3;
            PageAndContentPredicate pageAndContentPredicate4 = new PageAndContentPredicate("GESTURE_CHANGED", 3, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i4) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i4) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i4) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i4) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate32 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate42 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate5 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate6 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate7 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate8 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            GESTURE_CHANGED = pageAndContentPredicate4;
            final int i5 = 4;
            PageAndContentPredicate pageAndContentPredicate5 = new PageAndContentPredicate("ACCESSIBILITY_SERVICE_TOGGLE_VIA_SHORTCUT", 4, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i5) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i5) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i5) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i5) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate32 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate42 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate52 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate6 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate7 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate8 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            ACCESSIBILITY_SERVICE_TOGGLE_VIA_SHORTCUT = pageAndContentPredicate5;
            final int i6 = 5;
            PageAndContentPredicate pageAndContentPredicate6 = new PageAndContentPredicate("SUPPORT_SYSTEM_ACTIONS", 5, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i6) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i6) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i6) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i6) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate32 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate42 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate52 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate62 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate7 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate8 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            SUPPORT_SYSTEM_ACTIONS = pageAndContentPredicate6;
            final int i7 = 6;
            PageAndContentPredicate pageAndContentPredicate7 = new PageAndContentPredicate("SUPPORT_EXIT_BANNER", 6, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i7) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i7) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i7) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i7) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate32 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate42 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate52 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate62 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate72 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate8 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            SUPPORT_EXIT_BANNER = pageAndContentPredicate7;
            final int i8 = 7;
            PageAndContentPredicate pageAndContentPredicate8 = new PageAndContentPredicate("IMAGE_DESCRIPTION_UNAVAILABLE", 7, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i8) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i8) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i8) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i8) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate32 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate42 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate52 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate62 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate72 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate82 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            IMAGE_DESCRIPTION_UNAVAILABLE = pageAndContentPredicate8;
            final int i9 = 8;
            PageAndContentPredicate pageAndContentPredicate9 = new PageAndContentPredicate("ICON_DETECTION_AND_IMAGE_DESCRIPTION_UNAVAILABLE", 8, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i9) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i9) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i9) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i9) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate32 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate42 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate52 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate62 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate72 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate82 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            ICON_DETECTION_AND_IMAGE_DESCRIPTION_UNAVAILABLE = pageAndContentPredicate9;
            final int i10 = 9;
            PageAndContentPredicate pageAndContentPredicate10 = new PageAndContentPredicate("ICON_DETECTION_AVAILABLE_BUT_IMAGE_DESCRIPTION_UNAVAILABLE", 9, new ImmutablePredicate() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageConfig$PageAndContentPredicate$$ExternalSyntheticLambda1
                public final /* synthetic */ Predicate and(Predicate predicate) {
                    switch (i10) {
                        case 0:
                            return Predicate$CC.$default$and(this, predicate);
                        case 1:
                            return Predicate$CC.$default$and(this, predicate);
                        case 2:
                            return Predicate$CC.$default$and(this, predicate);
                        case 3:
                            return Predicate$CC.$default$and(this, predicate);
                        case 4:
                            return Predicate$CC.$default$and(this, predicate);
                        case 5:
                            return Predicate$CC.$default$and(this, predicate);
                        case 6:
                            return Predicate$CC.$default$and(this, predicate);
                        case 7:
                            return Predicate$CC.$default$and(this, predicate);
                        case 8:
                            return Predicate$CC.$default$and(this, predicate);
                        default:
                            return Predicate$CC.$default$and(this, predicate);
                    }
                }

                public final /* synthetic */ Predicate negate() {
                    switch (i10) {
                        case 0:
                            return Predicate$CC.$default$negate(this);
                        case 1:
                            return Predicate$CC.$default$negate(this);
                        case 2:
                            return Predicate$CC.$default$negate(this);
                        case 3:
                            return Predicate$CC.$default$negate(this);
                        case 4:
                            return Predicate$CC.$default$negate(this);
                        case 5:
                            return Predicate$CC.$default$negate(this);
                        case 6:
                            return Predicate$CC.$default$negate(this);
                        case 7:
                            return Predicate$CC.$default$negate(this);
                        case 8:
                            return Predicate$CC.$default$negate(this);
                        default:
                            return Predicate$CC.$default$negate(this);
                    }
                }

                public final /* synthetic */ Predicate or(Predicate predicate) {
                    switch (i10) {
                        case 0:
                            return Predicate$CC.$default$or(this, predicate);
                        case 1:
                            return Predicate$CC.$default$or(this, predicate);
                        case 2:
                            return Predicate$CC.$default$or(this, predicate);
                        case 3:
                            return Predicate$CC.$default$or(this, predicate);
                        case 4:
                            return Predicate$CC.$default$or(this, predicate);
                        case 5:
                            return Predicate$CC.$default$or(this, predicate);
                        case 6:
                            return Predicate$CC.$default$or(this, predicate);
                        case 7:
                            return Predicate$CC.$default$or(this, predicate);
                        case 8:
                            return Predicate$CC.$default$or(this, predicate);
                        default:
                            return Predicate$CC.$default$or(this, predicate);
                    }
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    switch (i10) {
                        case 0:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate22 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return false;
                        case 1:
                            PageConfig.PageAndContentPredicate pageAndContentPredicate32 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return true;
                        case 2:
                            Context context = ((TrainingIpcClient.ServiceData) obj).context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate42 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            return SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString());
                        case 3:
                            return ((TrainingIpcClient.ServiceData) obj).isAnyGestureChanged;
                        case 4:
                            TrainingIpcClient.ServiceData serviceData = (TrainingIpcClient.ServiceData) obj;
                            Context context2 = serviceData.context;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate52 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!TextUtils.isEmpty(Settings.Secure.getString(context2.getContentResolver(), "accessibility_button_targets")) && Settings.Secure.getInt(serviceData.context.getContentResolver(), "accessibility_button_mode", 0) != 1) {
                                return true;
                            }
                            return false;
                        case 5:
                            return FeatureSupport.supportGetSystemActions(((TrainingIpcClient.ServiceData) obj).context);
                        case 6:
                            TrainingIpcClient.ServiceData serviceData2 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate62 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData2.showExitBanner) {
                                if (TalkbackMistriggeringRecoveryConfig.INSTANCE.get().showExitBanner(serviceData2.context)) {
                                    return true;
                                }
                            }
                            return false;
                        case 7:
                            return ((TrainingIpcClient.ServiceData) obj).isImageDescriptionUnavailable;
                        case 8:
                            TrainingIpcClient.ServiceData serviceData3 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate72 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (serviceData3.isIconDetectionUnavailable && serviceData3.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                        default:
                            TrainingIpcClient.ServiceData serviceData4 = (TrainingIpcClient.ServiceData) obj;
                            PageConfig.PageAndContentPredicate pageAndContentPredicate82 = PageConfig.PageAndContentPredicate.ALWAYS_SHOW;
                            if (!serviceData4.isIconDetectionUnavailable && serviceData4.isImageDescriptionUnavailable) {
                                return true;
                            }
                            return false;
                    }
                }
            });
            ICON_DETECTION_AVAILABLE_BUT_IMAGE_DESCRIPTION_UNAVAILABLE = pageAndContentPredicate10;
            $VALUES = new PageAndContentPredicate[]{pageAndContentPredicate, pageAndContentPredicate2, pageAndContentPredicate3, pageAndContentPredicate4, pageAndContentPredicate5, pageAndContentPredicate6, pageAndContentPredicate7, pageAndContentPredicate8, pageAndContentPredicate9, pageAndContentPredicate10};
        }

        private PageAndContentPredicate(String str, int i, ImmutablePredicate immutablePredicate) {
            this.predicate = immutablePredicate;
        }

        public static PageAndContentPredicate[] values() {
            return (PageAndContentPredicate[]) $VALUES.clone();
        }

        public final boolean test(TrainingIpcClient.ServiceData serviceData) {
            if (serviceData != null && this.predicate.test(serviceData)) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum PageId {
        PAGE_ID_UNKNOWN,
        PAGE_ID_FINISHED,
        PAGE_ID_WELCOME_TO_UPDATED_TALKBACK_FOR_MULTIFINGER_GESTURES,
        PAGE_ID_WELCOME_TO_TALKBACK,
        PAGE_ID_EXPLORE_BY_TOUCH,
        PAGE_ID_SCROLLING,
        PAGE_ID_GESTURES_PAGE_FOR_GESTURE_NAVIGATION_USER,
        PAGE_ID_GESTURES_PAGE_FOR_3_BUTTON_NAVIGATION_USER,
        PAGE_ID_ADJUSTING_VOLUME,
        PAGE_ID_MENUS,
        PAGE_ID_MENUS_PRE_R,
        PAGE_ID_TUTORIAL_FINISHED,
        PAGE_ID_TUTORIAL_INDEX,
        PAGE_ID_USING_TEXT_BOXES,
        PAGE_ID_TYPING_TEXT,
        PAGE_ID_MOVING_CURSOR,
        PAGE_ID_SELECTING_TEXT,
        PAGE_ID_SELECTING_TEXT_PRE_R,
        PAGE_ID_COPY_CUT_PASTE,
        PAGE_ID_COPY_CUT_PASTE_PRE_R,
        PAGE_ID_TYPO_CORRECTION,
        PAGE_ID_TYPO_CORRECTION_PRE_R,
        PAGE_ID_TYPO_CORRECTION_NOT_ENGLISH,
        PAGE_ID_TYPO_CORRECTION_NOT_ENGLISH_PRE_R,
        PAGE_ID_READ_BY_CHARACTER,
        PAGE_ID_READ_BY_CHARACTER_PRE_R,
        PAGE_ID_JUMP_BETWEEN_CONTROLS,
        PAGE_ID_JUMP_BETWEEN_CONTROLS_PRE_R,
        PAGE_ID_JUMP_BETWEEN_LINKS,
        PAGE_ID_JUMP_BETWEEN_LINKS_PRE_R,
        PAGE_ID_JUMP_BETWEEN_HEADINGS,
        PAGE_ID_JUMP_BETWEEN_HEADINGS_PRE_R,
        PAGE_ID_VOICE_COMMANDS,
        PAGE_ID_PRACTICE_GESTURES,
        PAGE_ID_PRACTICE_GESTURES_PRE_R,
        PAGE_ID_VOICE_COMMAND_OVERVIEW,
        PAGE_ID_VOICE_COMMAND_READING_CONTROLS,
        PAGE_ID_VOICE_COMMAND_FIND_ITEMS,
        PAGE_ID_VOICE_COMMAND_FIND_ITEMS_FOR_WATCH,
        PAGE_ID_VOICE_COMMAND_TEXT_EDITING,
        PAGE_ID_VOICE_COMMAND_DEVICE_NAVIGATION,
        PAGE_ID_VOICE_COMMAND_OTHER_COMMANDS,
        PAGE_ID_ADDITIONAL_TIPS_MAKING_CALLS,
        PAGE_ID_ADDITIONAL_TIPS_SENDING_MESSAGES,
        PAGE_ID_ADDITIONAL_TIPS_READING_WEB_EMAILS,
        PAGE_ID_ADDITIONAL_TIPS_LOOKOUT,
        PAGE_ID_ADDITIONAL_TIPS_CHECKING_NOTIFICATIONS,
        PAGE_ID_UPDATE_WELCOME,
        PAGE_ID_DETAILED_IMAGE_DESCRIPTIONS,
        PAGE_ID_GOOGLE_DISABILITY_SUPPORT,
        PAGE_ID_PUNCTUATION_AND_SYMBOLS,
        PAGE_ID_NEW_BRAILLE_SHORTCUTS,
        PAGE_ID_WELCOME_TO_TALKBACK_WATCH,
        PAGE_ID_WATCH_SCROLLING,
        PAGE_ID_WATCH_GO_BACK,
        PAGE_ID_WATCH_VOLUME_UP,
        PAGE_ID_WATCH_VOLUME_DOWN,
        PAGE_ID_WATCH_OPEN_TALKBACK_MENU,
        PAGE_ID_WATCH_END_TUTORIAL,
        PAGE_ID_TV_OVERVIEW,
        PAGE_ID_TV_REMOTE,
        PAGE_ID_TV_SHORTCUT,
        PAGE_ID_TV_VENDOR
    }

    public PageConfig() {
    }

    public static PageConfig getPage(PageId pageId, Context context, int i) {
        WindowTrackerFactory.getInstance$ar$class_merging$14dd424f_0$ar$class_merging$ar$class_merging();
        int ordinal = pageId.ordinal();
        switch (ordinal) {
            case 2:
                return OnboardingConfigs.welcomeToUpdatedTalkBackForMultiFingerGestures.build();
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
                break;
            case 35:
                return VoiceCommandAndHelpConfigs.VoiceCommandOverView.build();
            case 36:
                return VoiceCommandAndHelpConfigs.VoiceCommandReadingControls.build();
            case 37:
                return VoiceCommandAndHelpConfigs.VoiceCommandFindItems.build();
            default:
                switch (ordinal) {
                    case 39:
                        return VoiceCommandAndHelpConfigs.VoiceCommandTextEditing.build();
                    case 40:
                        return VoiceCommandAndHelpConfigs.VoiceCommandDeviceNavigation.build();
                    case 41:
                        return VoiceCommandAndHelpConfigs.VoiceCommandOtherCommands.build();
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                        break;
                    case 47:
                        return OnboardingConfigs.updateWelcome.build();
                    case 48:
                        return OnboardingConfigs.imageDescription.build();
                    case 49:
                        return OnboardingConfigs.googleDisabilitySupport.build();
                    case 50:
                        return OnboardingConfigs.punctuationAndSymbols.build();
                    case 51:
                        return OnboardingConfigs.newBrailleShortcuts.build();
                    default:
                        switch (ordinal) {
                            case 59:
                            case 60:
                            case 61:
                                Builder builder = (Builder) SpannableUtils$IdentifierSpan.getDefaultPageBuilders(context, VendorConfigReader.retrieveConfig(context)).get(pageId);
                                if (builder == null) {
                                    return null;
                                }
                                return builder.build();
                            case 62:
                                return SpannableUtils$IdentifierSpan.getPageConfigForVendorPage(VendorConfigReader.retrieveConfig(context), i);
                            default:
                                return null;
                        }
                }
        }
        return TutorialConfigUtils.getInitialPageBuilder(pageId).build();
    }

    public final boolean equals(Object obj) {
        String str;
        IdleAnnouncementConfig idleAnnouncementConfig;
        if (obj == this) {
            return true;
        }
        if (obj instanceof PageConfig) {
            PageConfig pageConfig = (PageConfig) obj;
            if (this.getPageId.equals(pageConfig.getPageId()) && this.getPageNameResId == pageConfig.getPageNameResId() && ((str = this.getPageNameString) != null ? str.equals(pageConfig.getPageNameString()) : pageConfig.getPageNameString() == null) && this.getVendorPageIndex == pageConfig.getVendorPageIndex() && ContextDataProvider.equalsImpl(this.getContents, pageConfig.getContents())) {
                pageConfig.getNavigationButtons();
                ExternalDrawableResource externalDrawableResource = this.getImage;
                if (externalDrawableResource != null ? externalDrawableResource.equals(pageConfig.getImage()) : pageConfig.getImage() == null) {
                    if (ContextDataProvider.equalsImpl(this.getCaptureGestureIdToAnnouncements, pageConfig.getCaptureGestureIdToAnnouncements()) && ContextDataProvider.equalsImpl(this.getCaptureFingerprintGestureIdToAnnouncements, pageConfig.getCaptureFingerprintGestureIdToAnnouncements()) && this.isOnlyOneFocus == pageConfig.isOnlyOneFocus() && this.showingPredicate.equals(pageConfig.showingPredicate()) && this.hasNavigationButtonBar == pageConfig.hasNavigationButtonBar() && this.showPageNumber == pageConfig.showPageNumber() && this.isEndOfSection == pageConfig.isEndOfSection() && ((idleAnnouncementConfig = this.getIdleAnnouncementConfig) != null ? idleAnnouncementConfig.equals(pageConfig.getIdleAnnouncementConfig()) : pageConfig.getIdleAnnouncementConfig() == null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final ImmutableMap getCaptureFingerprintGestureIdToAnnouncements() {
        return this.getCaptureFingerprintGestureIdToAnnouncements;
    }

    public final ImmutableMap getCaptureGestureIdToAnnouncements() {
        return this.getCaptureGestureIdToAnnouncements;
    }

    public final ImmutableList getContents() {
        return this.getContents;
    }

    public final IdleAnnouncementConfig getIdleAnnouncementConfig() {
        return this.getIdleAnnouncementConfig;
    }

    public final ExternalDrawableResource getImage() {
        return this.getImage;
    }

    public final ImmutableList getNavigationButtons() {
        return null;
    }

    public final PageId getPageId() {
        return this.getPageId;
    }

    public final int getPageNameResId() {
        return this.getPageNameResId;
    }

    public final String getPageNameString() {
        return this.getPageNameString;
    }

    public final int getVendorPageIndex() {
        return this.getVendorPageIndex;
    }

    public final boolean hasNavigationButtonBar() {
        return this.hasNavigationButtonBar;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        int i;
        int i2;
        int i3;
        int hashCode3 = this.getPageId.hashCode() ^ 1000003;
        String str = this.getPageNameString;
        int i4 = 0;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        int hashCode4 = (((((((hashCode3 * 1000003) ^ this.getPageNameResId) * 1000003) ^ hashCode) * 1000003) ^ this.getVendorPageIndex) * 1000003) ^ this.getContents.hashCode();
        ExternalDrawableResource externalDrawableResource = this.getImage;
        if (externalDrawableResource == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = externalDrawableResource.hashCode();
        }
        int hashCode5 = ((((((hashCode4 * (-721379959)) ^ hashCode2) * 1000003) ^ this.getCaptureGestureIdToAnnouncements.hashCode()) * 1000003) ^ this.getCaptureFingerprintGestureIdToAnnouncements.hashCode()) * 1000003;
        int i5 = 1231;
        if (true != this.isOnlyOneFocus) {
            i = 1237;
        } else {
            i = 1231;
        }
        int hashCode6 = (((hashCode5 ^ i) * 1000003) ^ this.showingPredicate.hashCode()) * 1000003;
        if (true != this.hasNavigationButtonBar) {
            i2 = 1237;
        } else {
            i2 = 1231;
        }
        int i6 = (hashCode6 ^ i2) * 1000003;
        if (true != this.showPageNumber) {
            i3 = 1237;
        } else {
            i3 = 1231;
        }
        int i7 = (i6 ^ i3) * 1000003;
        if (true != this.isEndOfSection) {
            i5 = 1237;
        }
        int i8 = (i7 ^ i5) * (-721379959);
        if (this.getIdleAnnouncementConfig != null) {
            i4 = -349392937;
        }
        return ((i8 ^ i4) * 583896283) ^ 1237;
    }

    public final boolean isEndOfSection() {
        return this.isEndOfSection;
    }

    public final boolean isOnlyOneFocus() {
        return this.isOnlyOneFocus;
    }

    public final boolean showPageNumber() {
        return this.showPageNumber;
    }

    public final PageAndContentPredicate showingPredicate() {
        return this.showingPredicate;
    }

    public final String toString() {
        IdleAnnouncementConfig idleAnnouncementConfig = this.getIdleAnnouncementConfig;
        PageAndContentPredicate pageAndContentPredicate = this.showingPredicate;
        ImmutableMap immutableMap = this.getCaptureFingerprintGestureIdToAnnouncements;
        ImmutableMap immutableMap2 = this.getCaptureGestureIdToAnnouncements;
        ExternalDrawableResource externalDrawableResource = this.getImage;
        ImmutableList immutableList = this.getContents;
        return "PageConfig{getPageId=" + this.getPageId.toString() + ", getPageNameResId=" + this.getPageNameResId + ", getPageNameString=" + this.getPageNameString + ", getVendorPageIndex=" + this.getVendorPageIndex + ", getContents=" + immutableList.toString() + ", getNavigationButtons=null, getImage=" + String.valueOf(externalDrawableResource) + ", getCaptureGestureIdToAnnouncements=" + immutableMap2.toString() + ", getCaptureFingerprintGestureIdToAnnouncements=" + immutableMap.toString() + ", isOnlyOneFocus=" + this.isOnlyOneFocus + ", showingPredicate=" + pageAndContentPredicate.toString() + ", hasNavigationButtonBar=" + this.hasNavigationButtonBar + ", showPageNumber=" + this.showPageNumber + ", isEndOfSection=" + this.isEndOfSection + ", getSwipeDismissListener=null, getIdleAnnouncementConfig=" + String.valueOf(idleAnnouncementConfig) + ", getExtraTitleMarginTop=0, getExtraNavigationButtonMarginTop=0, clearTitleHorizontalMargin=false}";
    }

    public PageConfig(PageId pageId, int i, String str, int i2, ImmutableList immutableList, ImmutableList immutableList2, ExternalDrawableResource externalDrawableResource, ImmutableMap immutableMap, ImmutableMap immutableMap2, boolean z, PageAndContentPredicate pageAndContentPredicate, boolean z2, boolean z3, boolean z4, IdleAnnouncementConfig idleAnnouncementConfig) {
        this();
        if (pageId == null) {
            throw new NullPointerException("Null getPageId");
        }
        this.getPageId = pageId;
        this.getPageNameResId = i;
        this.getPageNameString = str;
        this.getVendorPageIndex = i2;
        if (immutableList != null) {
            this.getContents = immutableList;
            this.getNavigationButtons = null;
            this.getImage = externalDrawableResource;
            if (immutableMap != null) {
                this.getCaptureGestureIdToAnnouncements = immutableMap;
                if (immutableMap2 != null) {
                    this.getCaptureFingerprintGestureIdToAnnouncements = immutableMap2;
                    this.isOnlyOneFocus = z;
                    if (pageAndContentPredicate != null) {
                        this.showingPredicate = pageAndContentPredicate;
                        this.hasNavigationButtonBar = z2;
                        this.showPageNumber = z3;
                        this.isEndOfSection = z4;
                        this.getIdleAnnouncementConfig = idleAnnouncementConfig;
                        return;
                    }
                    throw new NullPointerException("Null showingPredicate");
                }
                throw new NullPointerException("Null getCaptureFingerprintGestureIdToAnnouncements");
            }
            throw new NullPointerException("Null getCaptureGestureIdToAnnouncements");
        }
        throw new NullPointerException("Null getContents");
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private final Map captureFingerprintGestureIdToAnnouncements;
        private final Map captureGestureIdToAnnouncements;
        public final List contents;
        public boolean hasNavigationButtonBar;
        public IdleAnnouncementConfig idleAnnouncementConfig;
        public ExternalDrawableResource image;
        private boolean isEndOfSection;
        private boolean isOnlyOneFocus;
        private final PageId pageId;
        private final int pageNameResId;
        private final String pageNameString;
        private boolean showPageNumber;
        public int vendorPageIndex;

        public Builder(PageId pageId, int i) {
            this.contents = new ArrayList();
            this.captureGestureIdToAnnouncements = new HashMap();
            this.captureFingerprintGestureIdToAnnouncements = new HashMap();
            this.vendorPageIndex = -1;
            this.hasNavigationButtonBar = true;
            this.showPageNumber = true;
            this.isEndOfSection = false;
            this.pageId = pageId;
            this.pageNameResId = i;
            this.pageNameString = null;
        }

        public final void addButton$ar$ds(int i) {
            this.contents.add(new PageButton(i, null));
        }

        public final void addButton$ar$ds$de42c80f_0(PageButton.ButtonOnClickListener buttonOnClickListener) {
            this.contents.add(new PageButton(R.string.get_the_app_button, buttonOnClickListener));
        }

        public final void addDivider$ar$ds() {
            this.contents.add(new Divider());
        }

        public final void addEditTextWithContent$ar$ds(int i) {
            this.contents.add(new EditTextBox(i, -1));
        }

        public final void addEditTextWithHint$ar$ds(int i) {
            this.contents.add(new EditTextBox(-1, i));
        }

        public final void addExitBanner$ar$ds(PageAndContentPredicate pageAndContentPredicate) {
            ExitBanner exitBanner = new ExitBanner();
            exitBanner.setShowingPredicate(pageAndContentPredicate);
            this.contents.add(exitBanner);
        }

        public final void addHeading$ar$ds(int i) {
            this.contents.add(new Heading(i));
        }

        public final void addLink$ar$ds(int i, int i2, int i3, int i4) {
            this.contents.add(new ClickableChip(i, i2, i3, new int[]{i4}));
        }

        public final void addSubText$ar$ds(int i) {
            Text.Paragraph.Builder builder = Text.Paragraph.builder(i);
            builder.setSubText$ar$ds(true);
            this.contents.add(new Text(builder.autoBuild()));
        }

        public final void addText$ar$ds(int i) {
            this.contents.add(new Text(Text.Paragraph.builder(i).autoBuild()));
        }

        public final void addText$ar$ds$ac980ab3_0(int i, ImmutableList immutableList) {
            Text.Paragraph.Builder builder = Text.Paragraph.builder(i);
            builder.setTextArgResIds$ar$ds(immutableList);
            this.contents.add(new Text(builder.autoBuild()));
        }

        public final void addTextWithBullet$ar$ds(int i) {
            Text.Paragraph.Builder builder = Text.Paragraph.builder(i);
            builder.setBulletPoint$ar$ds(true);
            this.contents.add(new Text(builder.autoBuild()));
        }

        public final void addTextWithBullet$ar$ds$8797f945_0(int i) {
            Text.Paragraph.Builder builder = Text.Paragraph.builder(i);
            builder.setBulletPoint$ar$ds(true);
            builder.setSubText$ar$ds(true);
            this.contents.add(new Text(builder.autoBuild()));
        }

        public final void addTextWithIcon$ar$ds(int i, int i2) {
            this.contents.add(new TextWithIcon(i, i2));
        }

        public final void addTextWithLink$ar$ds(int i) {
            Text.Paragraph.Builder builder = Text.Paragraph.builder(i);
            builder.setLink$ar$ds(true);
            this.contents.add(new Text(builder.autoBuild()));
        }

        public final void addTextWithLink$ar$ds$31d16ee8_0(int i, String str) {
            Text.Paragraph.Builder builder = Text.Paragraph.builder(i);
            builder.setLink$ar$ds(true);
            builder.urlLink = str;
            this.contents.add(new Text(builder.autoBuild()));
        }

        public final void addTextWithTtsSpan$ar$ds(int i, int i2) {
            Text.Paragraph.Builder builder = Text.Paragraph.builder(i);
            builder.setTextTtsSpanResId$ar$ds(i2);
            this.contents.add(new Text(builder.autoBuild()));
        }

        public final void addTip$ar$ds(int i) {
            this.contents.add(new Tip(i, -1));
        }

        public final PageConfig build() {
            List list = this.contents;
            PageId pageId = this.pageId;
            int i = this.pageNameResId;
            String str = this.pageNameString;
            int i2 = this.vendorPageIndex;
            ImmutableList copyOf = ImmutableList.copyOf((Collection) list);
            Map map = this.captureFingerprintGestureIdToAnnouncements;
            return new PageConfig(pageId, i, str, i2, copyOf, null, this.image, ImmutableMap.copyOf(this.captureGestureIdToAnnouncements), ImmutableMap.copyOf(map), this.isOnlyOneFocus, PageAndContentPredicate.ALWAYS_SHOW, this.hasNavigationButtonBar, this.showPageNumber, this.isEndOfSection, this.idleAnnouncementConfig);
        }

        public final void captureAllGestures$ar$ds() {
            this.captureGestureIdToAnnouncements.put(1, 0);
            this.captureGestureIdToAnnouncements.put(2, 0);
            this.captureGestureIdToAnnouncements.put(7, 0);
            this.captureGestureIdToAnnouncements.put(8, 0);
            this.captureGestureIdToAnnouncements.put(5, 0);
            this.captureGestureIdToAnnouncements.put(6, 0);
            this.captureGestureIdToAnnouncements.put(13, 0);
            this.captureGestureIdToAnnouncements.put(14, 0);
            this.captureGestureIdToAnnouncements.put(15, 0);
            this.captureGestureIdToAnnouncements.put(16, 0);
            this.captureGestureIdToAnnouncements.put(9, 0);
            this.captureGestureIdToAnnouncements.put(10, 0);
            this.captureGestureIdToAnnouncements.put(11, 0);
            this.captureGestureIdToAnnouncements.put(12, 0);
            this.captureGestureIdToAnnouncements.put(19, 0);
            this.captureGestureIdToAnnouncements.put(20, 0);
            this.captureGestureIdToAnnouncements.put(40, 0);
            this.captureGestureIdToAnnouncements.put(21, 0);
            this.captureGestureIdToAnnouncements.put(43, 0);
            this.captureGestureIdToAnnouncements.put(22, 0);
            this.captureGestureIdToAnnouncements.put(23, 0);
            this.captureGestureIdToAnnouncements.put(41, 0);
            this.captureGestureIdToAnnouncements.put(24, 0);
            this.captureGestureIdToAnnouncements.put(29, 0);
            this.captureGestureIdToAnnouncements.put(30, 0);
            this.captureGestureIdToAnnouncements.put(31, 0);
            this.captureGestureIdToAnnouncements.put(32, 0);
            this.captureGestureIdToAnnouncements.put(45, 0);
            this.captureGestureIdToAnnouncements.put(37, 0);
            this.captureGestureIdToAnnouncements.put(38, 0);
            this.captureGestureIdToAnnouncements.put(42, 0);
            this.captureGestureIdToAnnouncements.put(39, 0);
            this.captureGestureIdToAnnouncements.put(33, 0);
            this.captureGestureIdToAnnouncements.put(34, 0);
            this.captureGestureIdToAnnouncements.put(35, 0);
            this.captureGestureIdToAnnouncements.put(36, 0);
            this.captureFingerprintGestureIdToAnnouncements.put(4, 0);
            this.captureFingerprintGestureIdToAnnouncements.put(8, 0);
            this.captureFingerprintGestureIdToAnnouncements.put(2, 0);
            this.captureFingerprintGestureIdToAnnouncements.put(1, 0);
        }

        public final void captureGesture$ar$ds(int i, int i2) {
            this.captureGestureIdToAnnouncements.put(Integer.valueOf(i), Integer.valueOf(i2));
        }

        public final void hidePageNumber$ar$ds() {
            this.showPageNumber = false;
        }

        public final void setEndOfSection$ar$ds() {
            this.isEndOfSection = true;
        }

        public final void setOnlyOneFocus$ar$ds() {
            this.isOnlyOneFocus = true;
        }

        public Builder(PageId pageId, String str) {
            this.contents = new ArrayList();
            this.captureGestureIdToAnnouncements = new HashMap();
            this.captureFingerprintGestureIdToAnnouncements = new HashMap();
            this.vendorPageIndex = -1;
            this.hasNavigationButtonBar = true;
            this.showPageNumber = true;
            this.isEndOfSection = false;
            this.pageId = pageId;
            this.pageNameResId = -1;
            this.pageNameString = str;
        }
    }
}

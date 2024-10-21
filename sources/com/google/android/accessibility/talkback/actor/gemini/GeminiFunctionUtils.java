package com.google.android.accessibility.talkback.actor.gemini;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.mlkit.shared.logger.MLKitLoggingOptions;
import googledata.experiments.mobile.accessibility_suite.features.GeminiConfig;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.lang.ref.WeakReference;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeminiFunctionUtils {
    public static final DescribeImageCandidate confirmDownloadOrPerformImageCaptioning;
    static final Map decisionMap;
    public static final DescribeImageCandidate geminiOnDevice;
    public static final DescribeImageCandidate geminiServerSide;
    public static WeakReference imageCaptioner;
    public static final List imageDescriptionOption1;
    public static final List imageDescriptionOption10;
    public static final List imageDescriptionOption11;
    public static final List imageDescriptionOption12;
    public static final List imageDescriptionOption13;
    public static final List imageDescriptionOption14;
    public static final List imageDescriptionOption15;
    public static final List imageDescriptionOption16;
    public static final List imageDescriptionOption17;
    public static final List imageDescriptionOption18;
    public static final List imageDescriptionOption19;
    public static final List imageDescriptionOption2;
    public static final List imageDescriptionOption20;
    public static final List imageDescriptionOption21;
    public static final List imageDescriptionOption22;
    public static final List imageDescriptionOption23;
    public static final List imageDescriptionOption24;
    public static final List imageDescriptionOption25;
    public static final List imageDescriptionOption3;
    public static final List imageDescriptionOption4;
    public static final List imageDescriptionOption5;
    public static final List imageDescriptionOption6;
    public static final List imageDescriptionOption7;
    public static final List imageDescriptionOption8;
    public static final List imageDescriptionOption9;
    public static final DescribeImageCandidate nonGeminiImageCaptioning;
    public static final DescribeImageCandidate optInOnDevice;
    public static final DescribeImageCandidate optInServerSide;
    public static final DescribeImageCandidate showDetailedDescriptionUIFallBack;
    public static final DescribeImageCandidate showDetailedDescriptionWhenGeminiFeatured;
    public static final DescribeImageCandidate showDetailedDescriptionWhenGeminiOnDeviceFeatured;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$8, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass8 extends ClickableSpan {
        final /* synthetic */ Context val$context;
        final /* synthetic */ BaseDialog val$dialog;

        public AnonymousClass8(Context context, BaseDialog baseDialog) {
            this.val$context = context;
            this.val$dialog = baseDialog;
        }

        @Override // android.text.style.ClickableSpan
        public final void onClick(View view) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://policies.google.com/terms"));
            intent.addFlags(268435456);
            this.val$context.startActivity(intent);
            this.val$dialog.dismissDialog();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class DescribeImageCandidate extends Filter {
        final FeedbackCandidate getter;
        public final String name;

        public DescribeImageCandidate(FeedbackCandidate feedbackCandidate, String str) {
            this.getter = feedbackCandidate;
            this.name = str;
        }

        @Override // com.google.android.accessibility.utils.Filter
        public boolean accept(Context context) {
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DescribeImageDecision {
        final int aiCoreReady$ar$edu;
        final int aiCoreSupport$ar$edu;
        final int index;
        final int networkAvailable$ar$edu;
        final int onDeviceGeminiOptedIn$ar$edu;
        final int serverSideGeminiOptedIn$ar$edu;
        final int serverSideGeminiRejected$ar$edu;

        public DescribeImageDecision(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.index = i;
            this.aiCoreSupport$ar$edu = i2;
            this.aiCoreReady$ar$edu = i3;
            this.networkAvailable$ar$edu = i4;
            this.serverSideGeminiOptedIn$ar$edu = i5;
            this.serverSideGeminiRejected$ar$edu = i6;
            this.onDeviceGeminiOptedIn$ar$edu = i7;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface FeedbackCandidate {
        Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);
    }

    static {
        final int i = 1;
        DescribeImageCandidate describeImageCandidate = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i2 = i;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }, "confirmDownloadOrPerformImageCaptioning");
        confirmDownloadOrPerformImageCaptioning = describeImageCandidate;
        DescribeImageCandidate describeImageCandidate2 = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i2 = i;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.1
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate
            public final boolean accept(Context context) {
                return GeminiFunctionUtils.imageCaptioner != null && ((ImageCaptioner) GeminiFunctionUtils.imageCaptioner.get()).descriptionLibraryReady();
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate, com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                return accept((Context) obj);
            }
        };
        nonGeminiImageCaptioning = describeImageCandidate2;
        final int i2 = 0;
        DescribeImageCandidate describeImageCandidate3 = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i22 = i2;
                if (i22 != 0) {
                    if (i22 != 1) {
                        if (i22 != 2) {
                            if (i22 != 3) {
                                if (i22 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate
            public final boolean accept(Context context) {
                return GeminiConfig.enableServerSideGeminiImageCaptioning(context);
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate, com.google.android.accessibility.utils.Filter
            public final /* synthetic */ boolean accept(Object obj) {
                return GeminiConfig.enableServerSideGeminiImageCaptioning((Context) obj);
            }
        };
        optInServerSide = describeImageCandidate3;
        final int i3 = 2;
        DescribeImageCandidate describeImageCandidate4 = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i22 = i3;
                if (i22 != 0) {
                    if (i22 != 1) {
                        if (i22 != 2) {
                            if (i22 != 3) {
                                if (i22 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }, "showDetailedDescriptionUIFallBack");
        showDetailedDescriptionUIFallBack = describeImageCandidate4;
        DescribeImageCandidate describeImageCandidate5 = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i22 = i3;
                if (i22 != 0) {
                    if (i22 != 1) {
                        if (i22 != 2) {
                            if (i22 != 3) {
                                if (i22 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.3
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate
            public final boolean accept(Context context) {
                return GeminiConfig.enableServerSideGeminiImageCaptioning(context) || GeminiConfig.enableOnDeviceGeminiImageCaptioning(context);
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate, com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                return accept((Context) obj);
            }
        };
        showDetailedDescriptionWhenGeminiFeatured = describeImageCandidate5;
        DescribeImageCandidate describeImageCandidate6 = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i22 = i3;
                if (i22 != 0) {
                    if (i22 != 1) {
                        if (i22 != 2) {
                            if (i22 != 3) {
                                if (i22 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.4
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate
            public final boolean accept(Context context) {
                return GeminiConfig.enableOnDeviceGeminiImageCaptioning(context);
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate, com.google.android.accessibility.utils.Filter
            public final /* synthetic */ boolean accept(Object obj) {
                return GeminiConfig.enableOnDeviceGeminiImageCaptioning((Context) obj);
            }
        };
        showDetailedDescriptionWhenGeminiOnDeviceFeatured = describeImageCandidate6;
        final int i4 = 3;
        DescribeImageCandidate describeImageCandidate7 = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i22 = i4;
                if (i22 != 0) {
                    if (i22 != 1) {
                        if (i22 != 2) {
                            if (i22 != 3) {
                                if (i22 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.5
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate
            public final boolean accept(Context context) {
                return GeminiConfig.enableServerSideGeminiImageCaptioning(context);
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate, com.google.android.accessibility.utils.Filter
            public final /* synthetic */ boolean accept(Object obj) {
                return GeminiConfig.enableServerSideGeminiImageCaptioning((Context) obj);
            }
        };
        geminiServerSide = describeImageCandidate7;
        final int i5 = 4;
        DescribeImageCandidate describeImageCandidate8 = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i22 = i5;
                if (i22 != 0) {
                    if (i22 != 1) {
                        if (i22 != 2) {
                            if (i22 != 3) {
                                if (i22 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.6
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate
            public final boolean accept(Context context) {
                return GeminiConfig.enableOnDeviceGeminiImageCaptioning(context);
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate, com.google.android.accessibility.utils.Filter
            public final /* synthetic */ boolean accept(Object obj) {
                return GeminiConfig.enableOnDeviceGeminiImageCaptioning((Context) obj);
            }
        };
        optInOnDevice = describeImageCandidate8;
        final int i6 = 5;
        DescribeImageCandidate describeImageCandidate9 = new DescribeImageCandidate(new FeedbackCandidate() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.FeedbackCandidate
            public final Feedback.Part.Builder get(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                int i22 = i6;
                if (i22 != 0) {
                    if (i22 != 1) {
                        if (i22 != 2) {
                            if (i22 != 3) {
                                if (i22 != 4) {
                                    return Feedback.performDetailedOnDeviceImageCaption(accessibilityNodeInfoCompat);
                                }
                                Feedback.Part.Builder builder = Feedback.Part.builder();
                                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                                builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(8);
                                builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                                builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
                                return builder;
                            }
                            return Feedback.performDetailedImageCaption(accessibilityNodeInfoCompat);
                        }
                        Feedback.Part.Builder builder2 = Feedback.Part.builder();
                        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_02 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                        builder$ar$class_merging$19c02b89_02.setAction$ar$edu$a06fe909_0$ar$ds(9);
                        builder$ar$class_merging$19c02b89_02.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                        builder2.imageCaption = builder$ar$class_merging$19c02b89_02.m225build();
                        return builder2;
                    }
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_03 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                    builder$ar$class_merging$19c02b89_03.setAction$ar$edu$a06fe909_0$ar$ds(2);
                    builder$ar$class_merging$19c02b89_03.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                    builder3.imageCaption = builder$ar$class_merging$19c02b89_03.m225build();
                    return builder3;
                }
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_04 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
                builder$ar$class_merging$19c02b89_04.setAction$ar$edu$a06fe909_0$ar$ds(6);
                builder$ar$class_merging$19c02b89_04.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
                builder4.imageCaption = builder$ar$class_merging$19c02b89_04.m225build();
                return builder4;
            }
        }) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.7
            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate
            public final boolean accept(Context context) {
                return GeminiConfig.enableOnDeviceGeminiImageCaptioning(context);
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils.DescribeImageCandidate, com.google.android.accessibility.utils.Filter
            public final /* synthetic */ boolean accept(Object obj) {
                return GeminiConfig.enableOnDeviceGeminiImageCaptioning((Context) obj);
            }
        };
        geminiOnDevice = describeImageCandidate9;
        ImmutableList of = ImmutableList.of((Object) describeImageCandidate2, (Object) describeImageCandidate3, (Object) describeImageCandidate4);
        imageDescriptionOption1 = of;
        ImmutableList of2 = ImmutableList.of((Object) describeImageCandidate);
        imageDescriptionOption2 = of2;
        ImmutableList of3 = ImmutableList.of((Object) describeImageCandidate2, (Object) describeImageCandidate7, (Object) describeImageCandidate4);
        imageDescriptionOption3 = of3;
        ImmutableList of4 = ImmutableList.of((Object) describeImageCandidate3, (Object) describeImageCandidate);
        imageDescriptionOption4 = of4;
        ImmutableList of5 = ImmutableList.of((Object) describeImageCandidate);
        imageDescriptionOption5 = of5;
        ImmutableList of6 = ImmutableList.of((Object) describeImageCandidate7, (Object) describeImageCandidate);
        imageDescriptionOption6 = of6;
        ImmutableList of7 = ImmutableList.of((Object) describeImageCandidate3, (Object) describeImageCandidate8, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption7 = of7;
        ImmutableList of8 = ImmutableList.of((Object) describeImageCandidate3, (Object) describeImageCandidate9, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption8 = of8;
        ImmutableList of9 = ImmutableList.of((Object) describeImageCandidate5, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption9 = of9;
        ImmutableList of10 = ImmutableList.of((Object) describeImageCandidate9, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption10 = of10;
        ImmutableList of11 = ImmutableList.of((Object) describeImageCandidate7, (Object) describeImageCandidate8, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption11 = of11;
        ImmutableList of12 = ImmutableList.of((Object) describeImageCandidate7, (Object) describeImageCandidate9, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption12 = of12;
        ImmutableList of13 = ImmutableList.of((Object) describeImageCandidate3, (Object) describeImageCandidate8, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption13 = of13;
        ImmutableList of14 = ImmutableList.of((Object) describeImageCandidate3, (Object) describeImageCandidate9, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption14 = of14;
        ImmutableList of15 = ImmutableList.of((Object) describeImageCandidate5, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption15 = of15;
        ImmutableList of16 = ImmutableList.of((Object) describeImageCandidate8, (Object) describeImageCandidate3, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption16 = of16;
        ImmutableList of17 = ImmutableList.of((Object) describeImageCandidate8, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption17 = of17;
        ImmutableList of18 = ImmutableList.of((Object) describeImageCandidate8, (Object) describeImageCandidate7, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption18 = of18;
        ImmutableList of19 = ImmutableList.of((Object) describeImageCandidate9, (Object) describeImageCandidate5, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption19 = of19;
        ImmutableList of20 = ImmutableList.of((Object) describeImageCandidate3, (Object) describeImageCandidate8, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption20 = of20;
        ImmutableList of21 = ImmutableList.of((Object) describeImageCandidate3, (Object) describeImageCandidate9, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption21 = of21;
        ImmutableList of22 = ImmutableList.of((Object) describeImageCandidate6, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption22 = of22;
        ImmutableList of23 = ImmutableList.of((Object) describeImageCandidate9, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption23 = of23;
        ImmutableList of24 = ImmutableList.of((Object) describeImageCandidate7, (Object) describeImageCandidate3, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption24 = of24;
        ImmutableList of25 = ImmutableList.of((Object) describeImageCandidate7, (Object) describeImageCandidate9, (Object) describeImageCandidate2, (Object) describeImageCandidate4);
        imageDescriptionOption25 = of25;
        decisionMap = (Map) Stream.CC.of((Object[]) new AbstractMap.SimpleEntry[]{new AbstractMap.SimpleEntry(new DescribeImageDecision(1, 2, 3, 2, 2, 2, 3), of), new AbstractMap.SimpleEntry(new DescribeImageDecision(2, 2, 3, 2, 2, 1, 3), of2), new AbstractMap.SimpleEntry(new DescribeImageDecision(3, 2, 3, 2, 1, 3, 3), of3), new AbstractMap.SimpleEntry(new DescribeImageDecision(4, 2, 3, 1, 2, 2, 3), of4), new AbstractMap.SimpleEntry(new DescribeImageDecision(5, 2, 3, 1, 2, 1, 3), of5), new AbstractMap.SimpleEntry(new DescribeImageDecision(6, 2, 3, 1, 1, 3, 3), of6), new AbstractMap.SimpleEntry(new DescribeImageDecision(7, 1, 2, 2, 2, 2, 2), of7), new AbstractMap.SimpleEntry(new DescribeImageDecision(8, 1, 2, 2, 2, 2, 1), of8), new AbstractMap.SimpleEntry(new DescribeImageDecision(9, 1, 2, 2, 2, 1, 2), of9), new AbstractMap.SimpleEntry(new DescribeImageDecision(10, 1, 2, 3, 2, 1, 1), of10), new AbstractMap.SimpleEntry(new DescribeImageDecision(11, 1, 2, 3, 1, 3, 2), of11), new AbstractMap.SimpleEntry(new DescribeImageDecision(12, 1, 2, 3, 1, 3, 1), of12), new AbstractMap.SimpleEntry(new DescribeImageDecision(13, 1, 2, 1, 2, 2, 2), of13), new AbstractMap.SimpleEntry(new DescribeImageDecision(14, 1, 2, 1, 2, 2, 1), of14), new AbstractMap.SimpleEntry(new DescribeImageDecision(15, 1, 2, 1, 2, 1, 2), of15), new AbstractMap.SimpleEntry(new DescribeImageDecision(16, 1, 1, 2, 2, 2, 2), of16), new AbstractMap.SimpleEntry(new DescribeImageDecision(17, 1, 1, 2, 2, 1, 2), of17), new AbstractMap.SimpleEntry(new DescribeImageDecision(18, 1, 1, 2, 1, 3, 2), of18), new AbstractMap.SimpleEntry(new DescribeImageDecision(19, 1, 1, 2, 3, 3, 1), of19), new AbstractMap.SimpleEntry(new DescribeImageDecision(20, 1, 1, 1, 2, 2, 2), of20), new AbstractMap.SimpleEntry(new DescribeImageDecision(21, 1, 1, 1, 2, 2, 1), of21), new AbstractMap.SimpleEntry(new DescribeImageDecision(22, 1, 1, 1, 2, 1, 2), of22), new AbstractMap.SimpleEntry(new DescribeImageDecision(23, 1, 1, 1, 2, 1, 1), of23), new AbstractMap.SimpleEntry(new DescribeImageDecision(24, 1, 1, 1, 1, 3, 2), of24), new AbstractMap.SimpleEntry(new DescribeImageDecision(25, 1, 1, 1, 1, 3, 1), of25)}).collect(Collectors.toMap(new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(6), new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(5)));
    }

    public static Feedback.Part.Builder getPreferredImageDescriptionFeedback(Context context, ActorState actorState, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        List<DescribeImageCandidate> list;
        boolean z;
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        Iterator it = decisionMap.keySet().iterator();
        while (true) {
            if (it.hasNext()) {
                DescribeImageDecision describeImageDecision = (DescribeImageDecision) it.next();
                if (matchDecisionWithConfiguration$ar$edu(describeImageDecision.aiCoreSupport$ar$edu, actorState.getGeminiState$ar$class_merging$ar$class_merging$ar$class_merging().hasAiCore()) && matchDecisionWithConfiguration$ar$edu(describeImageDecision.aiCoreReady$ar$edu, actorState.getGeminiState$ar$class_merging$ar$class_merging$ar$class_merging().isAiFeatureAvailable()) && matchDecisionWithConfiguration$ar$edu(describeImageDecision.networkAvailable$ar$edu, SpannableUtils$IdentifierSpan.isNetworkConnected(context)) && matchDecisionWithConfiguration$ar$edu(describeImageDecision.serverSideGeminiOptedIn$ar$edu, SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_detailed_image_description_key, R.bool.pref_detailed_image_description_default)) && matchDecisionWithConfiguration$ar$edu(describeImageDecision.onDeviceGeminiOptedIn$ar$edu, SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_auto_on_devices_image_description_key, R.bool.pref_auto_on_device_image_description_default))) {
                    int i = describeImageDecision.serverSideGeminiRejected$ar$edu;
                    if (sharedPreferences.getInt(context.getString(R.string.pref_gemini_repeat_opt_in_count_key), 0) > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (matchDecisionWithConfiguration$ar$edu(i, z)) {
                        list = (List) decisionMap.get(describeImageDecision);
                        LogUtils.d("GeminiFunctionUtils", "getPreferredImageDescriptionFeedback/decision index:%d", Integer.valueOf(describeImageDecision.index));
                        break;
                    }
                }
            } else {
                list = null;
                break;
            }
        }
        if (list == null) {
            LogUtils.e("GeminiFunctionUtils", "getPreferredImageDescriptionFeedback/candidates is empty", new Object[0]);
            return null;
        }
        for (DescribeImageCandidate describeImageCandidate : list) {
            LogUtils.d("GeminiFunctionUtils", "getPreferredImageDescriptionFeedback/name:%s", describeImageCandidate.name);
            if (describeImageCandidate.accept(context)) {
                LogUtils.v("GeminiFunctionUtils", "getPreferredImageDescriptionFeedback/accepted", new Object[0]);
                return describeImageCandidate.getter.get(accessibilityNodeInfoCompat);
            }
        }
        return null;
    }

    private static boolean matchDecisionWithConfiguration$ar$edu(int i, boolean z) {
        boolean z2;
        if (i != 3) {
            if (i != 1) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z2 != z) {
                return false;
            }
        }
        return true;
    }
}

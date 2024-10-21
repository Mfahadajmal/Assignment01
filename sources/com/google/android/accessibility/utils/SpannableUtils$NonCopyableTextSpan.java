package com.google.android.accessibility.utils;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityService$TakeScreenshotCallback;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Insets;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.LocaleSpan;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.widget.Button;
import androidx.core.content.ContextCompat$Api24Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda5;
import com.google.android.accessibility.selecttospeak.AccessibilityNodeInfoCompatWithVisibility;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda8;
import com.google.android.accessibility.selecttospeak.logging.S2sHaTsActivity;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.PackageNameProvider;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.actor.search.StringMatcher$MatchResult;
import com.google.android.accessibility.talkback.compositor.AccessibilityFocusEventInterpretation;
import com.google.android.accessibility.talkback.compositor.CompositorUtils;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.input.TextEventInterpretation;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.material.AlertDialogAdaptiveContrastUtil$V7AlertDialogAdaptiveContrastBuilder;
import com.google.android.accessibility.utils.monitor.CollectionState;
import com.google.android.accessibility.utils.output.SpeechCleanupUtils;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.OnCanceledCompletionListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.gms.tasks.TaskImpl;
import com.google.android.gms.tasks.TaskTracing;
import com.google.android.gms.tasks.Tasks$AwaitListener;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;
import com.google.android.libraries.clock.impl.SystemClockImpl$ElapsedRealtimeNanosImpl;
import com.google.android.libraries.mdi.Download$ClientFileGroup;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter;
import com.google.android.libraries.surveys.SurveyData;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.android.marvin.talkback.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ExecutionList;
import com.google.common.util.concurrent.ListenableFuture;
import googledata.experiments.mobile.accessibility_suite.features.GeminiConfig;
import googledata.experiments.mobile.accessibility_suite.features.SpeechConfig;
import j$.time.Instant;
import j$.time.temporal.ChronoUnit;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.functions.Function1;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SpannableUtils$NonCopyableTextSpan {
    public static AccessibilityNodeInfoCompat lastUpdatedNode;
    public static long timeStamp;

    public static boolean $default$returnFeedback(Pipeline.FeedbackReturner feedbackReturner, Performance.EventId eventId, Feedback.EditText.Builder builder) {
        Feedback.Part.Builder builder2 = Feedback.Part.builder();
        builder2.edit = builder.build();
        return $default$returnFeedback(feedbackReturner, eventId, builder2);
    }

    public SpannableUtils$NonCopyableTextSpan() {
    }

    public static void addListener$ar$class_merging$33f29cd9_0(Task task, Tasks$AwaitListener tasks$AwaitListener) {
        task.addOnSuccessListener$ar$ds(TaskExecutors.DIRECT, tasks$AwaitListener);
        task.addOnFailureListener$ar$ds$7efc8a85_0(TaskExecutors.DIRECT, tasks$AwaitListener);
        TaskImpl taskImpl = (TaskImpl) task;
        taskImpl.listenerQueue$ar$class_merging.add(new OnCanceledCompletionListener(TaskTracing.traceExecutor(TaskExecutors.DIRECT), tasks$AwaitListener, 0));
        taskImpl.flushIfComplete();
    }

    public static AlertDialog.Builder alertDialogBuilder(Context context) {
        if (supportMaterialComponent(context)) {
            return new MaterialAlertDialogBuilder(new ContextThemeWrapper(context, R.style.A11yMaterialComponentsTheme), R.style.A11yMaterialAlertDialogStyle);
        }
        return new AlertDialogAdaptiveContrastUtil$V7AlertDialogAdaptiveContrastBuilder(context);
    }

    private static float area(Rect rect) {
        return rect.width() * rect.height();
    }

    public static void attemptTtsShutdown(TextToSpeech textToSpeech) {
        try {
            textToSpeech.shutdown();
        } catch (Exception unused) {
        }
    }

    public static Object await(Task task, long j, TimeUnit timeUnit) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotMainThread();
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotGoogleApiHandlerThread();
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return getResultOrThrowExecutionException(task);
        }
        Tasks$AwaitListener tasks$AwaitListener = new Tasks$AwaitListener();
        addListener$ar$class_merging$33f29cd9_0(task, tasks$AwaitListener);
        if (tasks$AwaitListener.latch.await(j, timeUnit)) {
            return getResultOrThrowExecutionException(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static float calculate(Rect rect, Rect rect2) {
        if (!rect.isEmpty() && !rect2.isEmpty()) {
            Rect rect3 = new Rect(rect);
            if (rect3.intersect(rect2)) {
                Rect rect4 = new Rect(rect);
                rect4.union(rect2);
                return area(rect3) / area(rect4);
            }
            return 0.0f;
        }
        return 0.0f;
    }

    public static Object castOrNull(Object obj, Class cls) {
        if (obj != null && cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }

    public static /* synthetic */ void chainAnimations$default$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(MetricsLogger metricsLogger, List list, Function1 function1, int i) {
        if ((i & 4) != 0) {
            function1 = null;
        }
        metricsLogger.chainAnimations$ar$ds(list, function1);
    }

    public static boolean compare(Download$ClientFileGroup download$ClientFileGroup, Download$ClientFileGroup download$ClientFileGroup2) {
        if (!fieldEquals(download$ClientFileGroup, download$ClientFileGroup2, new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(14), new AccessibilityEventUtils$$ExternalSyntheticLambda0(7)) || !fieldEquals(download$ClientFileGroup, download$ClientFileGroup2, new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(15), new AccessibilityEventUtils$$ExternalSyntheticLambda0(8)) || !fieldEquals(download$ClientFileGroup, download$ClientFileGroup2, new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(16), new AccessibilityEventUtils$$ExternalSyntheticLambda0(9)) || !fieldEquals(download$ClientFileGroup, download$ClientFileGroup2, new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(17), new AccessibilityEventUtils$$ExternalSyntheticLambda0(10)) || !fieldEquals(download$ClientFileGroup, download$ClientFileGroup2, new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(18), new AccessibilityEventUtils$$ExternalSyntheticLambda0(11)) || download$ClientFileGroup.file_.size() != download$ClientFileGroup2.file_.size()) {
            return false;
        }
        return true;
    }

    public static ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(Context context, int i) {
        if (i == 1) {
            return new ApplicationModule(new AlertDialogAdaptiveContrastUtil$V7AlertDialogAdaptiveContrastBuilder(context));
        }
        return new ApplicationModule(alertDialogBuilder(context));
    }

    public static Button createButton$ar$edu(Context context, int i) {
        if (supportMaterialComponent(context)) {
            if (i == 1) {
                return new MaterialButton(new ContextThemeWrapper(context, R.style.A11yMaterialComponentsTheme), null, R.attr.materialButtonStyle);
            }
            if (i == 2) {
                return new MaterialButton(new ContextThemeWrapper(context, R.style.A11yMaterialComponentsTheme), null, R.attr.materialButtonOutlinedStyle);
            }
        }
        return new Button(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0052, code lost:
    
        if ((r6 - r0[r1]) >= (r0[r2] - r6)) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.accessibility.selecttospeak.feedback.SpeechRateController createSpeechRateController$ar$ds(android.content.Context r6) {
        /*
            r6.getClass()
            android.content.SharedPreferences r0 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getSharedPreferences(r6)
            r1 = 2132085033(0x7f150929, float:1.9810253E38)
            java.lang.String r6 = r6.getString(r1)
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = java.lang.Float.floatToIntBits(r1)
            int r6 = r0.getInt(r6, r1)
            float[] r0 = com.google.android.accessibility.selecttospeak.feedback.SpeechRateController.SPEECH_RATE_LIST
            float r6 = java.lang.Float.intBitsToFloat(r6)
            r1 = 0
            r2 = r0[r1]
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r2 >= 0) goto L26
            goto L55
        L26:
            r2 = 10
            r3 = r0[r2]
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 <= 0) goto L30
        L2e:
            r1 = r2
            goto L55
        L30:
            int r3 = r1 + 1
            if (r3 >= r2) goto L49
            int r3 = r2 - r1
            int r3 = r3 / 2
            int r3 = r3 + r1
            r4 = r0[r3]
            int r5 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r5 >= 0) goto L41
            r2 = r3
            goto L30
        L41:
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 <= 0) goto L47
            r1 = r3
            goto L30
        L47:
            r1 = r3
            goto L55
        L49:
            r3 = r0[r1]
            float r3 = r6 - r3
            r0 = r0[r2]
            float r0 = r0 - r6
            int r6 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r6 < 0) goto L55
            goto L2e
        L55:
            com.google.android.accessibility.selecttospeak.feedback.SpeechRateController r6 = new com.google.android.accessibility.selecttospeak.feedback.SpeechRateController
            float[] r0 = com.google.android.accessibility.selecttospeak.feedback.SpeechRateController.SPEECH_RATE_LIST
            r0 = r0[r1]
            r6.<init>(r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.createSpeechRateController$ar$ds(android.content.Context):com.google.android.accessibility.selecttospeak.feedback.SpeechRateController");
    }

    public static Bitmap cropBitmap(Bitmap bitmap, Rect rect) {
        Rect rect2 = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (!rect.isEmpty() && rect2.intersect(rect)) {
            return Bitmap.createBitmap(bitmap, rect2.left, rect2.top, rect2.width(), rect2.height());
        }
        return null;
    }

    public static CharSequence defaultRoleDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        if (!globalVariables.speakRoles) {
            return "";
        }
        CharSequence nodeRoleDescription = getNodeRoleDescription(accessibilityNodeInfoCompat, context, globalVariables);
        if (!TextUtils.isEmpty(nodeRoleDescription)) {
            return nodeRoleDescription;
        }
        return getNodeRoleName(accessibilityNodeInfoCompat, context);
    }

    public static long elapsedRealtimeNanos$ar$ds() {
        if (SystemClockImpl$ElapsedRealtimeNanosImpl.ELAPSED_REALTIME_NANOS_EXISTS) {
            return SystemClock.elapsedRealtimeNanos();
        }
        return SystemClock.elapsedRealtime() * 1000000;
    }

    public static boolean enableAggressiveChunking(Context context) {
        if (SpannableUtils$IdentifierSpan.isAtLeastQ() && SpeechConfig.removeUnnecessarySpans(context) && SpeechConfig.INSTANCE.get().enableAggressiveChunking(context)) {
            return true;
        }
        return false;
    }

    public static byte[] encodeImageToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)) {
            LogUtils.e("GeminiDataFieldUtils", "Bitmap compression failed!", new Object[0]);
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static boolean fieldEquals(Download$ClientFileGroup download$ClientFileGroup, Download$ClientFileGroup download$ClientFileGroup2, Predicate predicate, Function function) {
        if (!predicate.apply(download$ClientFileGroup)) {
            if (!predicate.apply(download$ClientFileGroup2)) {
                return true;
            }
            return false;
        }
        return function.apply(download$ClientFileGroup).equals(function.apply(download$ClientFileGroup2));
    }

    public static List findMatches(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String[] split = str2.replaceAll("\\s+", " ").split(" ");
            StringBuilder sb = new StringBuilder();
            for (String str3 : split) {
                if (sb.length() == 0) {
                    sb.append(Pattern.quote(str3));
                } else {
                    sb.append("\\s+");
                    sb.append(Pattern.quote(str3));
                }
            }
            Matcher matcher = Pattern.compile(sb.toString(), 66).matcher(str);
            ArrayList arrayList = new ArrayList();
            while (matcher.find()) {
                arrayList.add(new StringMatcher$MatchResult(matcher.start(), matcher.end()));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public static boolean findNodeDFS(AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility, Filter filter, Filter filter2, Filter filter3, Collection collection, Collection collection2) {
        boolean z;
        if (accessibilityNodeInfoCompatWithVisibility == null) {
            return false;
        }
        if (collection2.contains(accessibilityNodeInfoCompatWithVisibility)) {
            LogUtils.w("CoreSample", "Node already visited: %s", accessibilityNodeInfoCompatWithVisibility);
            return false;
        }
        collection2.add(AccessibilityNodeInfoCompatWithVisibility.obtain(accessibilityNodeInfoCompatWithVisibility));
        if (!filter.accept(accessibilityNodeInfoCompatWithVisibility)) {
            if (WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompatWithVisibility)) {
                z = true;
            } else {
                LogUtils.v("CoreSample", "Node ignored. Non-web element without bound intersection. %s", accessibilityNodeInfoCompatWithVisibility);
                return false;
            }
        } else {
            z = false;
        }
        int childCount = accessibilityNodeInfoCompatWithVisibility.getChildCount();
        boolean z2 = false;
        for (int i = 0; i < childCount; i++) {
            z2 |= findNodeDFS(accessibilityNodeInfoCompatWithVisibility.getChild(i), filter, filter2, filter3, collection, collection2);
        }
        if (!accessibilityNodeInfoCompatWithVisibility.isImportantForAccessibility()) {
            if (!z2) {
                if (z) {
                    z = true;
                }
            }
            LogUtils.v("CoreSample", "Node ignored. hasAcceptableDescendant=%s, isWebElementsWithoutBoundIntersection=%s. %s", Boolean.valueOf(z2), Boolean.valueOf(z), accessibilityNodeInfoCompatWithVisibility);
            return z2;
        }
        accessibilityNodeInfoCompatWithVisibility.isImage = filter3.accept(accessibilityNodeInfoCompatWithVisibility);
        boolean accept = filter2.or(filter3).accept(accessibilityNodeInfoCompatWithVisibility);
        if (accept) {
            LogUtils.d("CoreSample", "Node selected: %s", accessibilityNodeInfoCompatWithVisibility);
            collection.add(accessibilityNodeInfoCompatWithVisibility);
        } else {
            LogUtils.v("CoreSample", "Node ignored. Not actionable. %s", accessibilityNodeInfoCompatWithVisibility);
        }
        return accept;
    }

    public static Task forException(Exception exc) {
        TaskImpl taskImpl = new TaskImpl();
        taskImpl.setException(exc);
        return taskImpl;
    }

    public static Task forResult(Object obj) {
        TaskImpl taskImpl = new TaskImpl();
        taskImpl.setResult(obj);
        return taskImpl;
    }

    public static CharSequence getAccessibilityNodeErrorText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context) {
        if (!accessibilityNodeInfoCompat.mInfo.isContentInvalid()) {
            return "";
        }
        CharSequence error = accessibilityNodeInfoCompat.getError();
        if (TextUtils.isEmpty(error)) {
            return "";
        }
        return context.getString(R.string.template_node_error_with_error_message, error);
    }

    public static List getAllDisplays(Context context) {
        List asList = Arrays.asList(((DisplayManager) context.getSystemService(DisplayManager.class)).getDisplays());
        asList.removeAll(Collections.singletonList(null));
        return asList;
    }

    public static CharSequence getCollapsedOrExpandedStateText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context) {
        if (AccessibilityNodeInfoUtils.isExpandable(accessibilityNodeInfoCompat)) {
            return context.getString(R.string.value_collapsed);
        }
        if (AccessibilityNodeInfoUtils.isCollapsible(accessibilityNodeInfoCompat)) {
            return context.getString(R.string.value_expanded);
        }
        return "";
    }

    public static CharSequence getCollectionGridItemCount(CollectionState collectionState, Context context) {
        if (hasBothCount(collectionState)) {
            return CompositorUtils.joinCharSequences(quantityCharSequenceForRow(collectionState, context), quantityCharSequenceForColumn(collectionState, context));
        }
        if (hasRowCount(collectionState)) {
            return CompositorUtils.joinCharSequences(quantityCharSequenceForRow(collectionState, context));
        }
        if (hasColumnCount(collectionState)) {
            return CompositorUtils.joinCharSequences(quantityCharSequenceForColumn(collectionState, context));
        }
        return "";
    }

    public static CharSequence getCollectionGridPagerEnter(CollectionState collectionState, Context context) {
        CharSequence charSequence;
        CharSequence collectionName = getCollectionName(R.string.in_grid_pager, R.string.in_grid_pager_with_name, collectionState.getCollectionName(), context);
        CharSequence collectionLevel = getCollectionLevel(collectionState, context);
        if (hasBothCount(collectionState)) {
            charSequence = CompositorUtils.joinCharSequences(context.getString(R.string.row_index_template, Integer.valueOf(getCollectionTableItemRowIndex(collectionState) + 1)), String.valueOf(context.getString(R.string.column_index_template, Integer.valueOf(getCollectionTableItemColumnIndex(collectionState)))).concat("1"));
        } else {
            charSequence = null;
        }
        return CompositorUtils.joinCharSequences(collectionName, collectionLevel, charSequence, quantityCharSequence(R.plurals.template_list_row_count, collectionState.getCollectionRowCount(), collectionState.getCollectionRowCount(), context), quantityCharSequence(R.plurals.template_list_column_count, collectionState.getCollectionColumnCount(), collectionState.getCollectionColumnCount(), context));
    }

    public static CharSequence getCollectionHorizontalPagerEnter(CollectionState collectionState, Context context) {
        String str;
        int collectionTableItemColumnIndex = getCollectionTableItemColumnIndex(collectionState);
        CharSequence[] charSequenceArr = new CharSequence[3];
        charSequenceArr[0] = getCollectionName(R.string.in_horizontal_pager, R.string.in_horizontal_pager_with_name, collectionState.getCollectionName(), context);
        charSequenceArr[1] = getCollectionLevel(collectionState, context);
        if (collectionTableItemColumnIndex >= 0) {
            str = context.getString(R.string.template_viewpager_index_count, Integer.valueOf(collectionTableItemColumnIndex + 1), Integer.valueOf(collectionState.getCollectionColumnCount()));
        } else {
            str = null;
        }
        charSequenceArr[2] = str;
        return CompositorUtils.joinCharSequences(charSequenceArr);
    }

    public static CharSequence getCollectionLevel(CollectionState collectionState, Context context) {
        int i = collectionState.mCollectionLevel;
        if (i >= 0) {
            return context.getString(R.string.template_collection_level, Integer.valueOf(i + 1));
        }
        return "";
    }

    public static CharSequence getCollectionListItemCount(CollectionState collectionState, Context context) {
        if (hasBothCount(collectionState)) {
            if (isVerticalAligned(collectionState) && collectionState.getCollectionRowCount() >= 0) {
                return quantityCharSequence(R.plurals.template_list_total_count, collectionState.getCollectionRowCount(), collectionState.getCollectionRowCount(), context);
            }
            if (collectionState.getCollectionAlignment() == 1 && collectionState.getCollectionColumnCount() >= 0) {
                return quantityCharSequence(R.plurals.template_list_total_count, collectionState.getCollectionColumnCount(), collectionState.getCollectionColumnCount(), context);
            }
            return "";
        }
        return "";
    }

    public static CharSequence getCollectionName(int i, int i2, CharSequence charSequence, Context context) {
        if (TextUtils.isEmpty(charSequence)) {
            return context.getString(i);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            return context.getString(i2, charSequence);
        }
        return "";
    }

    public static CharSequence getCollectionNameWithRoleDescriptionEnter(CollectionState collectionState, Context context) {
        if (TextUtils.isEmpty(collectionState.getCollectionRoleDescription())) {
            return "";
        }
        if (collectionState.getCollectionName() != null) {
            return context.getString(R.string.in_collection_role_description_with_name, collectionState.getCollectionRoleDescription(), collectionState.getCollectionName());
        }
        return context.getString(R.string.in_collection_role_description, collectionState.getCollectionRoleDescription());
    }

    public static int getCollectionTableItemColumnIndex(CollectionState collectionState) {
        CollectionState.TableItemState tableItemState = collectionState.getTableItemState();
        if (tableItemState != null) {
            return tableItemState.columnIndex;
        }
        return -1;
    }

    public static int getCollectionTableItemRowIndex(CollectionState collectionState) {
        CollectionState.TableItemState tableItemState = collectionState.getTableItemState();
        if (tableItemState != null) {
            return tableItemState.rowIndex;
        }
        return -1;
    }

    public static CharSequence getCollectionVerticalPagerEnter(CollectionState collectionState, Context context) {
        String str;
        int collectionTableItemRowIndex = getCollectionTableItemRowIndex(collectionState);
        CharSequence[] charSequenceArr = new CharSequence[3];
        charSequenceArr[0] = getCollectionName(R.string.in_vertical_pager, R.string.in_vertical_pager_with_name, collectionState.getCollectionName(), context);
        charSequenceArr[1] = getCollectionLevel(collectionState, context);
        if (collectionTableItemRowIndex >= 0) {
            str = context.getString(R.string.template_viewpager_index_count, Integer.valueOf(collectionTableItemRowIndex + 1), Integer.valueOf(collectionState.getCollectionRowCount()));
        } else {
            str = null;
        }
        charSequenceArr[2] = str;
        return CompositorUtils.joinCharSequences(charSequenceArr);
    }

    public static CharSequence getDescriptionFromLabelNode$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair, GlobalVariables globalVariables) {
        AccessibilityNodeInfoCompat labeledBy = accessibilityNodeInfoCompat.getLabeledBy();
        if (labeledBy == null) {
            return "";
        }
        return getNodeTextOrLabelOrIdDescription$ar$class_merging$ar$class_merging(labeledBy, context, runnableExecutorPair, globalVariables);
    }

    public static Rect getDisplaySizeRect(Context context) {
        WindowMetrics currentWindowMetrics;
        Rect bounds;
        Object systemService = context.getSystemService("window");
        systemService.getClass();
        WindowManager windowManager = (WindowManager) systemService;
        if (Build.VERSION.SDK_INT >= 30) {
            currentWindowMetrics = windowManager.getCurrentWindowMetrics();
            currentWindowMetrics.getClass();
            bounds = currentWindowMetrics.getBounds();
            bounds.getClass();
            return bounds;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static CharSequence getEventAggregateText(AccessibilityEvent accessibilityEvent, Locale locale) {
        CharSequence eventAggregateText = SpannableUtils$IdentifierSpan.getEventAggregateText(accessibilityEvent);
        if (SpannableUtils$IdentifierSpan.isTalkBackPackage(accessibilityEvent.getPackageName())) {
            if (eventAggregateText == null) {
                return "";
            }
            return eventAggregateText;
        }
        if (eventAggregateText == null) {
            return "";
        }
        return SpannableUtils$IdentifierSpan.wrapWithLocaleSpan(eventAggregateText, locale);
    }

    public static CharSequence getEventContentDescription(AccessibilityEvent accessibilityEvent, Locale locale) {
        CharSequence contentDescription = accessibilityEvent.getContentDescription();
        if (SpannableUtils$IdentifierSpan.isTalkBackPackage(accessibilityEvent.getPackageName())) {
            if (contentDescription == null) {
                return "";
            }
            return contentDescription;
        }
        if (contentDescription == null) {
            return "";
        }
        return SpannableUtils$IdentifierSpan.wrapWithLocaleSpan(contentDescription, locale);
    }

    public static CharSequence getEventContentDescriptionOrEventAggregateText(AccessibilityEvent accessibilityEvent, Locale locale) {
        CharSequence eventContentDescription = getEventContentDescription(accessibilityEvent, locale);
        if (TextUtils.isEmpty(eventContentDescription)) {
            return getEventAggregateText(accessibilityEvent, locale);
        }
        return eventContentDescription;
    }

    public static CharSequence getEventTraversedText(EventInterpretation eventInterpretation, Locale locale) {
        CharSequence charSequence = safeTextInterpretation(eventInterpretation).mTraversedText;
        if (SpannableUtils$IdentifierSpan.isTalkBackPackage(eventInterpretation.mPackageName)) {
            return charSequence;
        }
        if (charSequence == null) {
            return "";
        }
        return SpannableUtils$IdentifierSpan.wrapWithLocaleSpan(charSequence, locale);
    }

    public static long getKeyComboCodeForKey(int i, KeyComboManager keyComboManager, Context context) {
        if (keyComboManager == null) {
            return 0L;
        }
        return keyComboManager.keyComboModel.getKeyComboCodeForKey(context.getString(i));
    }

    public static CharSequence getNodeCheckedStateText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, Locale locale) {
        CharSequence nodeStateDescription = getNodeStateDescription(accessibilityNodeInfoCompat, context, locale);
        if (accessibilityNodeInfoCompat.isCheckable() && TextUtils.isEmpty(nodeStateDescription)) {
            int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat);
            boolean isChecked = accessibilityNodeInfoCompat.isChecked();
            if (role != 11 && role != 13) {
                if (isChecked) {
                    return context.getString(R.string.value_checked);
                }
                return context.getString(R.string.value_not_checked);
            }
            if (isChecked) {
                return context.getString(R.string.value_on);
            }
            return context.getString(R.string.value_off);
        }
        return "";
    }

    public static CharSequence getNodeContentDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, Locale locale) {
        return prepareSpans(accessibilityNodeInfoCompat.getContentDescription(), accessibilityNodeInfoCompat, context, locale);
    }

    public static CharSequence getNodeHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(AccessibilityNodeInfoUtils.getHintText(accessibilityNodeInfoCompat));
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.google.android.accessibility.utils.labeling.LabelManager, java.lang.Object] */
    public static CharSequence getNodeLabelText$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        Label labelForViewIdFromCache;
        String str;
        ?? r2 = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable;
        if (r2 == 0 || (labelForViewIdFromCache = r2.getLabelForViewIdFromCache(accessibilityNodeInfoCompat.getViewIdResourceName())) == null || (str = labelForViewIdFromCache.mText) == null) {
            return null;
        }
        return str;
    }

    public static CharSequence getNodeRoleDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(prepareSpans(accessibilityNodeInfoCompat.getRoleDescription(), accessibilityNodeInfoCompat, context, globalVariables.userPreferredLocale));
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    public static CharSequence getNodeRoleName(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context) {
        String string;
        switch (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat)) {
            case 1:
            case 7:
                string = context.getString(R.string.value_button);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 2:
                string = context.getString(R.string.value_checkbox);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 3:
                string = context.getString(R.string.value_spinner);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 4:
                string = context.getString(R.string.value_edit_box);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 5:
                string = context.getString(R.string.value_gridview);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 6:
                string = context.getString(R.string.value_image);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 8:
                string = context.getString(R.string.value_listview);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 9:
                string = context.getString(R.string.value_radio_button);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 10:
                string = context.getString(R.string.value_seek_bar);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 11:
            case 13:
                string = context.getString(R.string.value_switch);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 12:
                string = context.getString(R.string.value_tabwidget);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 14:
            case 17:
            default:
                return "";
            case 15:
                string = context.getString(R.string.value_webview);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 16:
                string = context.getString(R.string.value_pager);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
            case 18:
                string = context.getString(R.string.value_progress_bar);
                return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(string);
        }
    }

    public static CharSequence getNodeStateDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, Locale locale) {
        return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(prepareSpans(AccessibilityNodeInfoUtils.getState(accessibilityNodeInfoCompat), accessibilityNodeInfoCompat, context, locale));
    }

    public static CharSequence getNodeText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, Locale locale) {
        return prepareSpans(AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat), accessibilityNodeInfoCompat, context, locale);
    }

    public static CharSequence getNodeTextDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        if (globalVariables.mLastTextEditIsPassword && !globalVariables.mShouldSpeakPasswords && (AccessibilityNodeInfoUtils.isKeyboard(accessibilityNodeInfoCompat) || AccessibilityNodeInfoUtils.isPinKey(accessibilityNodeInfoCompat))) {
            return context.getString(R.string.symbol_bullet);
        }
        CharSequence nodeContentDescription = getNodeContentDescription(accessibilityNodeInfoCompat, context, globalVariables.userPreferredLocale);
        if (!TextUtils.isEmpty(nodeContentDescription)) {
            if (globalVariables.sayCapital) {
                return CompositorUtils.prependCapital(nodeContentDescription, context);
            }
            return nodeContentDescription;
        }
        CharSequence nodeText = getNodeText(accessibilityNodeInfoCompat, context, globalVariables.userPreferredLocale);
        if (globalVariables.sayCapital) {
            return CompositorUtils.prependCapital(nodeText, context);
        }
        return nodeText;
    }

    public static CharSequence getNodeTextOrLabelDescription$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair, GlobalVariables globalVariables) {
        CharSequence nodeTextDescription = getNodeTextDescription(accessibilityNodeInfoCompat, context, globalVariables);
        if (!TextUtils.isEmpty(nodeTextDescription)) {
            return nodeTextDescription;
        }
        CharSequence nodeLabelText$ar$class_merging$ar$class_merging = getNodeLabelText$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, runnableExecutorPair);
        if (!TextUtils.isEmpty(nodeLabelText$ar$class_merging$ar$class_merging)) {
            if (globalVariables.sayCapital) {
                return CompositorUtils.prependCapital(nodeLabelText$ar$class_merging$ar$class_merging, context);
            }
            return nodeLabelText$ar$class_merging$ar$class_merging;
        }
        Locale locale = globalVariables.userPreferredLocale;
        if (locale == null) {
            locale = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        if (SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_auto_text_recognition_key, R.bool.pref_auto_text_recognition_default) && runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor != null) {
            AppLifecycleMonitor.getCaptionResults$ar$ds(accessibilityNodeInfoCompat);
        }
        if (SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_auto_icon_detection_key, R.bool.pref_auto_icon_detection_default) && runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor != null) {
            if (!locale.equals(runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next)) {
                if (runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next != null) {
                    ((AppLifecycleMonitor) ((AppLifecycleMonitor) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor).AppLifecycleMonitor$ar$tracker).clear();
                }
                runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next = locale;
            }
            AppLifecycleMonitor.getCaptionResults$ar$ds(accessibilityNodeInfoCompat);
        }
        if (SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_auto_image_description_key, R.bool.pref_auto_image_description_default) && runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor != null) {
            AppLifecycleMonitor.getCaptionResults$ar$ds(accessibilityNodeInfoCompat);
        }
        String constructCaptionTextForAuto = SpannableUtils$IdentifierSpan.constructCaptionTextForAuto(context, null, null, null);
        if (!TextUtils.isEmpty(constructCaptionTextForAuto)) {
            return constructCaptionTextForAuto;
        }
        return "";
    }

    public static CharSequence getNodeTextOrLabelOrIdDescription$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair, GlobalVariables globalVariables) {
        CharSequence nodeTextOrLabelDescription$ar$class_merging$ar$class_merging = getNodeTextOrLabelDescription$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, context, runnableExecutorPair, globalVariables);
        if (TextUtils.isEmpty(nodeTextOrLabelDescription$ar$class_merging$ar$class_merging)) {
            if (globalVariables.speakElementIds) {
                return AccessibilityNodeInfoUtils.getViewIdText(accessibilityNodeInfoCompat);
            }
            return "";
        }
        return nodeTextOrLabelDescription$ar$class_merging$ar$class_merging;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static CharSequence getNotificationCategoryStateText(Context context, Notification notification) {
        char c;
        if (notification != null && notification.category != null) {
            String str = notification.category;
            switch (str.hashCode()) {
                case -1001078227:
                    if (str.equals("progress")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -897050771:
                    if (str.equals("social")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 100709:
                    if (str.equals("err")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 108417:
                    if (str.equals("msg")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 114381:
                    if (str.equals("sys")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 3045982:
                    if (str.equals("call")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 92895825:
                    if (str.equals("alarm")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 96619420:
                    if (str.equals("email")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 96891546:
                    if (str.equals("event")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 106940687:
                    if (str.equals("promo")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1052964649:
                    if (str.equals("transport")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 1984153269:
                    if (str.equals("service")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    return context.getString(R.string.notification_category_call);
                case 1:
                    return context.getString(R.string.notification_category_msg);
                case 2:
                    return context.getString(R.string.notification_category_email);
                case 3:
                    return context.getString(R.string.notification_category_event);
                case 4:
                    return context.getString(R.string.notification_category_promo);
                case 5:
                    return context.getString(R.string.notification_category_alarm);
                case 6:
                    return context.getString(R.string.notification_category_progress);
                case 7:
                    return context.getString(R.string.notification_category_social);
                case '\b':
                    return context.getString(R.string.notification_category_err);
                case '\t':
                    return context.getString(R.string.notification_category_transport);
                case '\n':
                    return context.getString(R.string.notification_category_sys);
                case 11:
                    return context.getString(R.string.notification_category_service);
                default:
                    return "";
            }
        }
        return "";
    }

    public static CharSequence getPagerIndexCount(AccessibilityEvent accessibilityEvent, Context context, GlobalVariables globalVariables) {
        int fromIndex = accessibilityEvent.getFromIndex();
        int itemCount = accessibilityEvent.getItemCount();
        if (fromIndex >= 0 && itemCount > 0) {
            int i = fromIndex + 1;
            AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
            CharSequence charSequence = null;
            if (compat != null && SpannableUtils$IdentifierSpan.getRole(compat) == 16) {
                int childCount = compat.getChildCount();
                int i2 = 0;
                CharSequence charSequence2 = null;
                while (true) {
                    if (i2 < childCount) {
                        AccessibilityNodeInfoCompat child = compat.getChild(i2);
                        if (child != null && child.isVisibleToUser()) {
                            if (charSequence2 != null) {
                                break;
                            }
                            charSequence2 = AccessibilityNodeInfoUtils.getNodeText(child);
                        }
                        i2++;
                    } else {
                        charSequence = charSequence2;
                        break;
                    }
                }
            }
            if (!TextUtils.isEmpty(charSequence) && !globalVariables.isFocusPage) {
                return CompositorUtils.joinCharSequences(charSequence, context.getString(R.string.template_viewpager_index_count_short, Integer.valueOf(i), Integer.valueOf(itemCount)));
            }
            return context.getString(R.string.template_viewpager_index_count, Integer.valueOf(i), Integer.valueOf(itemCount));
        }
        return "";
    }

    public static SharedPreferences getPipelineSharedPreference$ar$ds(Context context) {
        Context createDeviceProtectedStorageContext = ContextCompat$Api24Impl.createDeviceProtectedStorageContext(context);
        if (createDeviceProtectedStorageContext != null) {
            SharedPreferences sharedPreferences = createDeviceProtectedStorageContext.getSharedPreferences("SELECT_TO_SPEAK_PIPELINE", 0);
            sharedPreferences.getClass();
            return sharedPreferences;
        }
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("SELECT_TO_SPEAK_PIPELINE", 0);
        sharedPreferences2.getClass();
        return sharedPreferences2;
    }

    public static int getProgressBarChangeEarcon(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Locale locale) {
        int eventType = accessibilityEvent.getEventType();
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 18 || (eventType != 2048 && eventType != 4)) {
            return -1;
        }
        CharSequence eventContentDescriptionOrEventAggregateText = getEventContentDescriptionOrEventAggregateText(accessibilityEvent, locale);
        if (accessibilityEvent.getSource() != null && !accessibilityNodeInfoCompat.isFocused() && !accessibilityNodeInfoCompat.isAccessibilityFocused() && accessibilityNodeInfoCompat.getLiveRegion() == 0) {
            return -1;
        }
        if (!accessibilityEvent.getText().isEmpty() && !accessibilityNodeInfoCompat.getText().equals(eventContentDescriptionOrEventAggregateText)) {
            return -1;
        }
        return R.raw.scroll_tone;
    }

    public static double getProgressBarChangeEarconRate(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        float progressPercent;
        double d;
        int eventType = accessibilityEvent.getEventType();
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 18) {
            if (eventType == 2048 || eventType == 4) {
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat rangeInfo$ar$class_merging = accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging();
                if (rangeInfo$ar$class_merging == null) {
                    float f = 0.0f;
                    if (accessibilityEvent != null) {
                        f = 100.0f * Math.max(0.0f, Math.min(1.0f, accessibilityEvent.getCurrentItemIndex() / accessibilityEvent.getItemCount()));
                    }
                    d = f;
                } else {
                    if (rangeInfo$ar$class_merging.getType() == 2) {
                        progressPercent = rangeInfo$ar$class_merging.getCurrent();
                    } else {
                        progressPercent = AccessibilityNodeInfoUtils.getProgressPercent(accessibilityNodeInfoCompat);
                    }
                    d = progressPercent;
                }
                return Math.pow(2.0d, (d / 50.0d) - 1.0d);
            }
            return 1.0d;
        }
        return 1.0d;
    }

    public static double getProgressBarChangeEarconVolume(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int eventType = accessibilityEvent.getEventType();
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 18) {
            if (eventType == 2048 || eventType == 4) {
                return 0.5d;
            }
            return 1.0d;
        }
        return 1.0d;
    }

    public static Object getResultOrThrowExecutionException(Task task) {
        if (!task.isSuccessful()) {
            if (((TaskImpl) task).canceled) {
                throw new CancellationException("Task is already canceled");
            }
            throw new ExecutionException(task.getException());
        }
        return task.getResult();
    }

    public static CharSequence getSelectedStateText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context) {
        if (accessibilityNodeInfoCompat.isSelected()) {
            return context.getString(R.string.value_selected);
        }
        return "";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.accessibility.utils.labeling.LabelManager, java.lang.Object] */
    public static CharSequence getUnlabelledNodeDescription$ar$class_merging$ar$class_merging(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair, GlobalVariables globalVariables) {
        boolean z;
        Locale localesByNode;
        ?? r0 = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable;
        if (r0 != 0 && r0.stateReader().needsLabel(accessibilityNodeInfoCompat)) {
            z = true;
        } else {
            z = false;
        }
        boolean isCheckable = accessibilityNodeInfoCompat.isCheckable();
        LogUtils.v("AccessibilityNodeFeedbackUtils", StringBuilderUtils.joinFields(" getUnlabelledNodeDescription, ", StringBuilderUtils.optionalTag("needsLabel", z), StringBuilderUtils.optionalTag("srcIsCheckable", isCheckable), StringBuilderUtils.optionalText("role", SpannableUtils$IdentifierSpan.roleToString(i))), new Object[0]);
        if (z && !isCheckable && i != 10 && i != 18) {
            CharSequence defaultRoleDescription = defaultRoleDescription(accessibilityNodeInfoCompat, context, globalVariables);
            if (globalVariables != null) {
                localesByNode = globalVariables.userPreferredLocale;
            } else {
                localesByNode = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
            }
            CharSequence nodeStateDescription = getNodeStateDescription(accessibilityNodeInfoCompat, context, localesByNode);
            CharSequence nodeTextOrLabelOrIdDescription$ar$class_merging$ar$class_merging = getNodeTextOrLabelOrIdDescription$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, context, runnableExecutorPair, globalVariables);
            CharSequence nodeHint = getNodeHint(accessibilityNodeInfoCompat);
            CharSequence descriptionFromLabelNode$ar$class_merging$ar$class_merging = getDescriptionFromLabelNode$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, context, runnableExecutorPair, globalVariables);
            if (TextUtils.isEmpty(defaultRoleDescription) && TextUtils.isEmpty(nodeStateDescription) && TextUtils.isEmpty(nodeTextOrLabelOrIdDescription$ar$class_merging$ar$class_merging) && TextUtils.isEmpty(nodeHint) && TextUtils.isEmpty(descriptionFromLabelNode$ar$class_merging$ar$class_merging)) {
                LogUtils.v("AccessibilityNodeFeedbackUtils", " getUnlabelledNodeDescription return Unlabelled because no text info", new Object[0]);
                return context.getString(R.string.value_unlabelled);
            }
            return "";
        }
        return "";
    }

    public static Insets getWindowInsets(WindowMetrics windowMetrics) {
        Insets insets;
        WindowInsets windowInsets;
        int systemBars;
        int displayCutout;
        Insets insetsIgnoringVisibility;
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            insets = Insets.NONE;
            return insets;
        }
        windowInsets = windowMetrics.getWindowInsets();
        systemBars = WindowInsets.Type.systemBars();
        displayCutout = WindowInsets.Type.displayCutout();
        insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(systemBars | displayCutout);
        return insetsIgnoringVisibility;
    }

    public static boolean hasAnyCount(CollectionState collectionState) {
        if (!hasRowCount(collectionState) && !hasColumnCount(collectionState)) {
            return false;
        }
        return true;
    }

    public static boolean hasBothCount(CollectionState collectionState) {
        if (hasRowCount(collectionState) && hasColumnCount(collectionState)) {
            return true;
        }
        return false;
    }

    private static boolean hasColumnCount(CollectionState collectionState) {
        if (collectionState.getCollectionColumnCount() >= 0) {
            return true;
        }
        return false;
    }

    public static boolean hasQueueModeFlagSet(int i, int i2) {
        if ((i & i2) == i2) {
            return true;
        }
        return false;
    }

    private static boolean hasRowCount(CollectionState collectionState) {
        if (collectionState.getCollectionRowCount() >= 0) {
            return true;
        }
        return false;
    }

    public static Instant instant$ar$ds() {
        return Instant.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public static boolean isAtLeastT() {
        if (Build.VERSION.SDK_INT >= 33) {
            return true;
        }
        return false;
    }

    public static boolean isGeminiVoiceCommandEnabled(Context context) {
        if (!GeminiConfig.INSTANCE.get().enableGeminiVoiceCommand(context)) {
            return false;
        }
        return true;
    }

    private static boolean isRoughEqual(int i, int i2) {
        if (i < i2 + 5 && i > i2 - 5) {
            return true;
        }
        return false;
    }

    public static boolean isRunningInGmsCore(Context context) {
        if ("com.google.android.gms".equals(context.getPackageName())) {
            return true;
        }
        return false;
    }

    public static boolean isScreenLayoutRTL(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration == null || (configuration.screenLayout & 192) != 128) {
            return false;
        }
        return true;
    }

    public static boolean isSystemBar(Context context, AccessibilityWindowInfo accessibilityWindowInfo) {
        WindowManager windowManager;
        WindowMetrics currentWindowMetrics;
        Rect bounds;
        WindowInsets windowInsets;
        int systemBars;
        Insets insetsIgnoringVisibility;
        Rect bounds2;
        Display defaultDisplay;
        if (accessibilityWindowInfo.getType() != 3) {
            return false;
        }
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            WindowManager windowManager2 = (WindowManager) context.getSystemService("window");
            Display display = null;
            if (windowManager2 == null) {
                defaultDisplay = null;
            } else {
                defaultDisplay = windowManager2.getDefaultDisplay();
            }
            if (defaultDisplay != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getRealMetrics(displayMetrics);
                Rect rect = new Rect();
                accessibilityWindowInfo.getBoundsInScreen(rect);
                int rotation = defaultDisplay.getRotation();
                if (rotation != 1) {
                }
            }
            WindowManager windowManager3 = (WindowManager) context.getSystemService("window");
            if (windowManager3 != null) {
                display = windowManager3.getDefaultDisplay();
            }
            if (display != null) {
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                display.getRealMetrics(displayMetrics2);
                Rect rect2 = new Rect();
                accessibilityWindowInfo.getBoundsInScreen(rect2);
                if (isRoughEqual(rect2.top, 0) && isRoughEqual(rect2.left, 0) && isRoughEqual(rect2.right, displayMetrics2.widthPixels) && rect2.bottom < displayMetrics2.heightPixels / 5) {
                    return true;
                }
            }
            return false;
        }
        int displayId = SpannableUtils$IdentifierSpan.getDisplayId(accessibilityWindowInfo);
        if (displayId == 0) {
            windowManager = (WindowManager) context.getSystemService(WindowManager.class);
        } else {
            windowManager = (WindowManager) context.createDisplayContext(((DisplayManager) context.getSystemService(DisplayManager.class)).getDisplay(displayId)).getSystemService(WindowManager.class);
        }
        currentWindowMetrics = windowManager.getCurrentWindowMetrics();
        bounds = currentWindowMetrics.getBounds();
        Rect rect3 = new Rect(bounds);
        windowInsets = currentWindowMetrics.getWindowInsets();
        systemBars = WindowInsets.Type.systemBars();
        insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(systemBars);
        rect3.inset(insetsIgnoringVisibility);
        Rect rect4 = new Rect();
        accessibilityWindowInfo.getBoundsInScreen(rect4);
        bounds2 = currentWindowMetrics.getBounds();
        if (rect4.intersect(bounds2) && Rect.intersects(rect3, rect4) && rect3.contains(rect4)) {
            return false;
        }
        return true;
    }

    public static boolean isVerticalAligned(CollectionState collectionState) {
        if (collectionState.getCollectionAlignment() == 0) {
            return true;
        }
        return false;
    }

    public static void launchActivity$ar$ds(Context context, SurveyData surveyData) {
        context.getClass();
        Intent intent = new Intent(context, (Class<?>) S2sHaTsActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("KEY_SURVEY_DATA", surveyData);
        context.startActivity(intent);
    }

    public static /* synthetic */ int m(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static CharSequence notifyErrorStateText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context) {
        if (accessibilityNodeInfoCompat != null && !TextUtils.isEmpty(accessibilityNodeInfoCompat.getError())) {
            return context.getString(R.string.template_text_error, accessibilityNodeInfoCompat.getError());
        }
        return "";
    }

    public static CharSequence notifyMaxLengthReachedStateText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context) {
        if (accessibilityNodeInfoCompat == null || TextUtils.isEmpty(accessibilityNodeInfoCompat.getText())) {
            return "";
        }
        int maxTextLength = accessibilityNodeInfoCompat.getMaxTextLength();
        int length = accessibilityNodeInfoCompat.getText().length();
        if (maxTextLength < 0 || length < maxTextLength) {
            return "";
        }
        return context.getString(R.string.value_text_max_length);
    }

    private static CharSequence prepareSpans(CharSequence charSequence, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, Locale locale) {
        if (!accessibilityNodeInfoCompat.isPassword()) {
            charSequence = SpeechCleanupUtils.collapseRepeatedCharactersAndCleanUp(context, charSequence);
        }
        if (!SpannableUtils$IdentifierSpan.isTalkBackPackage(accessibilityNodeInfoCompat.getPackageName())) {
            if (locale == null) {
                locale = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
            }
            if (!AccessibilityNodeInfoUtils.isKeyboard(accessibilityNodeInfoCompat) && locale != null) {
                if (charSequence instanceof Spannable) {
                    Spannable spannable = (Spannable) charSequence;
                    for (LocaleSpan localeSpan : (LocaleSpan[]) spannable.getSpans(0, charSequence.length(), LocaleSpan.class)) {
                        spannable.removeSpan(localeSpan);
                    }
                }
                if (charSequence == null) {
                    return "";
                }
                return SpannableUtils$IdentifierSpan.wrapWithLocaleSpan(charSequence, locale);
            }
        }
        if (charSequence == null) {
            return "";
        }
        return charSequence;
    }

    private static CharSequence quantityCharSequence(int i, int i2, int i3, Context context) {
        return context.getResources().getQuantityString(i, i2, Integer.valueOf(i3));
    }

    private static CharSequence quantityCharSequenceForColumn(CollectionState collectionState, Context context) {
        return quantityCharSequence(R.plurals.template_list_column_count, collectionState.getCollectionColumnCount(), collectionState.getCollectionColumnCount(), context);
    }

    private static CharSequence quantityCharSequenceForRow(CollectionState collectionState, Context context) {
        return quantityCharSequence(R.plurals.template_list_row_count, collectionState.getCollectionRowCount(), collectionState.getCollectionRowCount(), context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void recordDuration$ar$ds(NoPiiString noPiiString, long j, long j2, ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension) {
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension;
        if (extensionTalkback$TalkbackExtension != null) {
            SchedulerOptions.Builder builder = (SchedulerOptions.Builder) ExtensionMetric$MetricExtension.DEFAULT_INSTANCE.createBuilder();
            builder.setExtension$ar$ds(ExtensionTalkback$TalkbackExtension.metricExtension, extensionTalkback$TalkbackExtension);
            extensionMetric$MetricExtension = (ExtensionMetric$MetricExtension) builder.build();
        } else {
            extensionMetric$MetricExtension = null;
        }
        Primes.get().primesApi.recordDuration(noPiiString, j, j2, extensionMetric$MetricExtension);
    }

    public static boolean report$ar$ds() {
        LogUtils.w("GestureReporter", "Not support ReportingGesture", new Object[0]);
        return false;
    }

    public static AccessibilityFocusEventInterpretation safeAccessibilityFocusInterpretation(EventInterpretation eventInterpretation) {
        AccessibilityFocusEventInterpretation accessibilityFocusEventInterpretation;
        if (eventInterpretation != null && (accessibilityFocusEventInterpretation = eventInterpretation.mAccessibilityFocus) != null) {
            return accessibilityFocusEventInterpretation;
        }
        LogUtils.w("AccessibilityInterpretationUtils", "Falling back to safe AccessibilityFocusEventInterpretation", new Object[0]);
        return new AccessibilityFocusEventInterpretation(1073741924);
    }

    public static TextEventInterpretation safeTextInterpretation(EventInterpretation eventInterpretation) {
        TextEventInterpretation textEventInterpretation;
        if (eventInterpretation != null && (textEventInterpretation = eventInterpretation.mText) != null) {
            return textEventInterpretation;
        }
        LogUtils.w("AccessibilityInterpretationUtils", "Falling back to safe TextEventInterpretation", new Object[0]);
        return new TextEventInterpretation(1073741924);
    }

    public static boolean shouldMuteFeedbackForFocusedNode$ar$ds(ScreenState screenState) {
        CharSequence packageName;
        if (FormFactorUtils.getInstance().isAndroidWear && screenState.interpretFirstTimeWhenWakeUp) {
            AccessibilityWindowInfo accessibilityWindowInfo = screenState.activeWindow;
            if (accessibilityWindowInfo == null) {
                LogUtils.v("FocusActorHelper", "The active window is null so it is not the Home screen.", new Object[0]);
            } else if (PackageNameProvider.isInitialized) {
                List<ResolveInfo> list = PackageNameProvider.HOME_ACTIVITY_RESOLVED_INFO;
                if (!FormFactorUtils.getInstance().isAndroidAuto && (!accessibilityWindowInfo.isActive() || !accessibilityWindowInfo.isFocused())) {
                    LogUtils.v("FocusActorHelper", "The window is not active or focused so it is not Home screen.", new Object[0]);
                } else {
                    AccessibilityNodeInfo root = accessibilityWindowInfo.getRoot();
                    if (root != null && (packageName = root.getPackageName()) != null) {
                        for (ResolveInfo resolveInfo : list) {
                            if (resolveInfo.activityInfo != null && TextUtils.equals(packageName, resolveInfo.activityInfo.packageName)) {
                                LogUtils.v("FocusActorHelper", "The active window is the Home screen.", new Object[0]);
                                return true;
                            }
                        }
                    }
                    LogUtils.v("FocusActorHelper", "The active window is not the Home screen.", new Object[0]);
                }
            } else {
                throw new IllegalStateException("We should initialize PackageNameProvider before querying.");
            }
        }
        return false;
    }

    public static CharSequence spelling(CharSequence charSequence, Context context) {
        if (charSequence.length() <= 1) {
            return "";
        }
        return (CharSequence) Stream.CC.of(charSequence).map(new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 12)).collect(Collectors.joining());
    }

    public static boolean supportMaterialComponent(Context context) {
        if (!context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.type.automotive") && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            return true;
        }
        return false;
    }

    public static void takeScreenshot(AccessibilityService accessibilityService, final ScreenCaptureController.CaptureListener captureListener) {
        Executor mainExecutor;
        mainExecutor = accessibilityService.getMainExecutor();
        if (!SpannableUtils$IdentifierSpan.isAtLeastR()) {
            LogUtils.e("ScreenshotCapture", "Taking screenshot but platform's not support", new Object[0]);
            captureListener.onScreenCaptureFinished(null, false);
        } else {
            accessibilityService.takeScreenshot(0, mainExecutor, new AccessibilityService$TakeScreenshotCallback() { // from class: com.google.android.accessibility.utils.screencapture.ScreenshotCapture$1
                public final void onFailure(int i) {
                    LogUtils.e("ScreenshotCapture", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Taking screenshot but failed [error:", "]"), new Object[0]);
                    ScreenCaptureController.CaptureListener.this.onScreenCaptureFinished(null, false);
                }

                public final void onSuccess(AccessibilityService.ScreenshotResult screenshotResult) {
                    HardwareBuffer hardwareBuffer;
                    ColorSpace colorSpace;
                    Bitmap wrapHardwareBuffer;
                    boolean z = true;
                    try {
                        hardwareBuffer = screenshotResult.getHardwareBuffer();
                        try {
                            colorSpace = screenshotResult.getColorSpace();
                            wrapHardwareBuffer = Bitmap.wrapHardwareBuffer(hardwareBuffer, colorSpace);
                            if (hardwareBuffer != null) {
                                hardwareBuffer.close();
                            }
                            if (wrapHardwareBuffer != null) {
                                Bitmap copy = wrapHardwareBuffer.copy(Bitmap.Config.ARGB_8888, wrapHardwareBuffer.isMutable());
                                wrapHardwareBuffer.recycle();
                                wrapHardwareBuffer = copy;
                            }
                            ScreenCaptureController.CaptureListener captureListener2 = ScreenCaptureController.CaptureListener.this;
                            if (wrapHardwareBuffer == null) {
                                z = false;
                            }
                            captureListener2.onScreenCaptureFinished(wrapHardwareBuffer, z);
                        } finally {
                        }
                    } catch (IllegalArgumentException e) {
                        LogUtils.e("ScreenshotCapture", "Taken screenshot could not be converted to Bitmap, %s", e);
                        ScreenCaptureController.CaptureListener.this.onScreenCaptureFinished(null, false);
                    }
                }
            });
        }
    }

    public static Feedback toFeedback(Performance.EventId eventId, int i) {
        Feedback.Part.Builder builder = Feedback.Part.builder();
        builder.systemAction = new Feedback.SystemAction(i);
        return Feedback.create(eventId, builder.build());
    }

    public static Feedback toFeedback$ar$edu(Performance.EventId eventId, int i) {
        Feedback.Part.Builder builder = Feedback.Part.builder();
        builder.continuousRead = Feedback.continuousRead$ar$edu(i).build().continuousRead;
        return Feedback.create(eventId, builder.build());
    }

    public static Feedback toFeedback$ar$edu$4db91719_0(Performance.EventId eventId, int i) {
        Feedback.Part.Builder builder = Feedback.Part.builder();
        builder.dimScreen = Feedback.dimScreen$ar$edu(i).build().dimScreen;
        return Feedback.create(eventId, builder.build());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.libraries.gmstasks.TaskFutures$TaggedFuture, com.google.common.util.concurrent.ListenableFuture] */
    public static ListenableFuture toListenableFuture(Task task) {
        final ?? r0 = new AbstractFuture(task, null) { // from class: com.google.android.libraries.gmstasks.TaskFutures$TaggedFuture
            Runnable canceller = null;
            Object tag;

            {
                this.tag = task;
            }

            @Override // com.google.common.util.concurrent.AbstractFuture
            protected final void afterDone() {
                this.tag = null;
                this.canceller = null;
            }

            @Override // com.google.common.util.concurrent.AbstractFuture
            public final String pendingToString() {
                Object obj = this.tag;
                if (obj == null) {
                    return "";
                }
                return obj.toString();
            }

            @Override // com.google.common.util.concurrent.AbstractFuture
            public final boolean set(Object obj) {
                return super.set(obj);
            }

            @Override // com.google.common.util.concurrent.AbstractFuture
            public final boolean setException(Throwable th) {
                return super.setException(th);
            }
        };
        task.addOnCompleteListener$ar$ds$6dfdfa2c_0(DirectExecutor.INSTANCE, new OnCompleteListener() { // from class: com.google.android.libraries.gmstasks.TaskFutures$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                boolean z = ((TaskImpl) task2).canceled;
                TaskFutures$TaggedFuture taskFutures$TaggedFuture = TaskFutures$TaggedFuture.this;
                if (z) {
                    taskFutures$TaggedFuture.cancel(false);
                    return;
                }
                if (task2.isSuccessful()) {
                    taskFutures$TaggedFuture.set(task2.getResult());
                    return;
                }
                Exception exception = task2.getException();
                if (exception != null) {
                    taskFutures$TaggedFuture.setException(exception);
                    return;
                }
                throw new IllegalStateException();
            }
        });
        return r0;
    }

    public boolean shouldSpeakPasswords() {
        throw null;
    }

    public SpannableUtils$NonCopyableTextSpan(byte[] bArr) {
    }

    public static boolean $default$returnFeedback(Pipeline.FeedbackReturner feedbackReturner, Performance.EventId eventId, Feedback.Focus.Builder builder) {
        Feedback.Part.Builder builder2 = Feedback.Part.builder();
        builder2.focus = builder.build();
        return $default$returnFeedback(feedbackReturner, eventId, builder2);
    }

    public SpannableUtils$NonCopyableTextSpan(Application application) {
        ClearcutMetricTransmitter.Builder builder = new ClearcutMetricTransmitter.Builder();
        builder.context = application;
        builder.logSource = "TALKBACK_ANDROID_PRIMES";
        Primes.initialize$ar$class_merging$fd7e8a43_0$ar$ds(BatteryMetricService.newInstance$ar$class_merging(application, new PrimesController$$ExternalSyntheticLambda8(builder.build(), application, 2)));
        Primes.get().startMemoryMonitor();
        Primes.get().startCrashMonitor();
    }

    public static Feedback toFeedback(Performance.EventId eventId, Feedback.EditText.Builder builder) {
        Feedback.Part.Builder builder2 = Feedback.Part.builder();
        builder2.edit = builder.build();
        return Feedback.create(eventId, builder2.build());
    }

    public static boolean $default$returnFeedback(Pipeline.FeedbackReturner feedbackReturner, Performance.EventId eventId, Feedback.FocusDirection.Builder builder) {
        Feedback.Part.Builder builder2 = Feedback.Part.builder();
        builder2.focusDirection = builder.build();
        return $default$returnFeedback(feedbackReturner, eventId, builder2);
    }

    public static Feedback toFeedback(Performance.EventId eventId, Feedback.Focus.Builder builder) {
        Feedback.Part.Builder builder2 = Feedback.Part.builder();
        builder2.focus = builder.build();
        return Feedback.create(eventId, builder2.build());
    }

    public static boolean $default$returnFeedback(Pipeline.FeedbackReturner feedbackReturner, Performance.EventId eventId, Feedback.Part.Builder builder) {
        return feedbackReturner.returnFeedback(Feedback.create(eventId, builder.build()));
    }

    public static Feedback toFeedback(Performance.EventId eventId, Feedback.FocusDirection focusDirection) {
        Feedback.Part.Builder builder = Feedback.Part.builder();
        builder.focusDirection = focusDirection;
        return Feedback.create(eventId, builder.build());
    }
}

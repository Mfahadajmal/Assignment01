package com.google.android.libraries.performance.primes.metriccapture;

import _COROUTINE._BOUNDARY;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuLogProto$AccessibilityMenuExtension;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuSettingsProto$AccessibilityMenuSettings;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuShortcutProto$AccessibilityMenuShortcut;
import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$A11ymenuSettings;
import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.compositor.CompositorUtils;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.roledescription.RoleDescriptionExtractor;
import com.google.android.accessibility.talkback.labeling.CustomLabelManager;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.AnalyticsCommon;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.traversal.ReorderedChildrenIterator;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.consentverifier.BaseProtoCollectionBasis;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricService;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricService;
import com.google.android.marvin.talkback.R;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ExecutionList;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import logs.proto.wireless.performance.mobile.ProcessProto$AndroidProcessStats;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStatsCapture {
    public final Object ProcessStatsCapture$ar$context;
    public final Object ProcessStatsCapture$ar$foregroundStateCapture;
    public final Object ProcessStatsCapture$ar$oomScoreAdjCapture;
    public final Object ProcessStatsCapture$ar$processImportanceCapture;

    public ProcessStatsCapture(Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair, GlobalVariables globalVariables, RoleDescriptionExtractor roleDescriptionExtractor) {
        this.ProcessStatsCapture$ar$context = context;
        this.ProcessStatsCapture$ar$processImportanceCapture = runnableExecutorPair;
        this.ProcessStatsCapture$ar$oomScoreAdjCapture = globalVariables;
        this.ProcessStatsCapture$ar$foregroundStateCapture = roleDescriptionExtractor;
    }

    private final CharSequence getAppendedTreeDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityEvent accessibilityEvent, boolean z) {
        CharSequence conditionalPrepend;
        CharSequence nodeHint;
        if (((GlobalVariables) this.ProcessStatsCapture$ar$oomScoreAdjCapture).descriptionOrder != 1) {
            conditionalPrepend = CompositorUtils.conditionalAppend(treeNodesDescription(accessibilityNodeInfoCompat, accessibilityEvent, z), nodeStatusDescription(accessibilityNodeInfoCompat), CompositorUtils.separator);
        } else {
            conditionalPrepend = CompositorUtils.conditionalPrepend(nodeStatusDescription(accessibilityNodeInfoCompat), treeNodesDescription(accessibilityNodeInfoCompat, accessibilityEvent, z), CompositorUtils.separator);
        }
        CharSequence accessibilityNodeErrorText = SpannableUtils$NonCopyableTextSpan.getAccessibilityNodeErrorText(accessibilityNodeInfoCompat, (Context) this.ProcessStatsCapture$ar$context);
        CharSequence charSequence = "";
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4 && accessibilityNodeInfoCompat.isShowingHintText()) {
            nodeHint = "";
        } else {
            nodeHint = SpannableUtils$NonCopyableTextSpan.getNodeHint(accessibilityNodeInfoCompat);
        }
        Object obj = this.ProcessStatsCapture$ar$context;
        Object obj2 = this.ProcessStatsCapture$ar$oomScoreAdjCapture;
        CharSequence tooltipText = accessibilityNodeInfoCompat.getTooltipText();
        if (!TextUtils.isEmpty(tooltipText) && !TextUtils.equals(tooltipText, SpannableUtils$NonCopyableTextSpan.getNodeTextDescription(accessibilityNodeInfoCompat, (Context) obj, (GlobalVariables) obj2))) {
            charSequence = tooltipText;
        }
        LogUtils.v("TreeNodesDescription", StringBuilderUtils.joinFields(String.format("    getAppendedTreeDescription: (%s)  ", Integer.valueOf(accessibilityNodeInfoCompat.hashCode())), String.format(", treeDescription={%s} ,", conditionalPrepend), StringBuilderUtils.optionalText("accessibilityNodeError", accessibilityNodeErrorText), StringBuilderUtils.optionalText("accessibilityNodeHint", nodeHint), StringBuilderUtils.optionalText("tooltip", charSequence)), new Object[0]);
        return CompositorUtils.joinCharSequences(conditionalPrepend, accessibilityNodeErrorText, nodeHint, charSequence);
    }

    public static final ProcessProto$AndroidProcessStats getProcessStatsWithImportanceMetric$ar$ds(String str, boolean z, RunningAppProcessInfoResponse runningAppProcessInfoResponse) {
        Optional optional;
        Optional optional2;
        Optional optional3;
        RandomAccessFile randomAccessFile;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) ProcessProto$AndroidProcessStats.DEFAULT_INSTANCE.createBuilder();
        long elapsedCpuTime = Process.getElapsedCpuTime();
        builder.copyOnWrite();
        ProcessProto$AndroidProcessStats processProto$AndroidProcessStats = (ProcessProto$AndroidProcessStats) builder.instance;
        processProto$AndroidProcessStats.bitField0_ |= 1;
        processProto$AndroidProcessStats.processElapsedTimeMs_ = elapsedCpuTime;
        builder.copyOnWrite();
        ProcessProto$AndroidProcessStats processProto$AndroidProcessStats2 = (ProcessProto$AndroidProcessStats) builder.instance;
        processProto$AndroidProcessStats2.bitField0_ |= 2;
        processProto$AndroidProcessStats2.isInForeground_ = z;
        int activeCount = Thread.activeCount();
        builder.copyOnWrite();
        ProcessProto$AndroidProcessStats processProto$AndroidProcessStats3 = (ProcessProto$AndroidProcessStats) builder.instance;
        processProto$AndroidProcessStats3.bitField0_ |= 4;
        processProto$AndroidProcessStats3.threadCount_ = activeCount;
        final int myPid = Process.myPid();
        String format = String.format(Locale.US, "/proc/%d/oom_score_adj", Integer.valueOf(myPid));
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                randomAccessFile = new RandomAccessFile(format, "r");
            } catch (IOException unused) {
                optional = Absent.INSTANCE;
            }
            try {
                optional = Optional.fromNullable(randomAccessFile.readLine()).transform(new AccessibilityEventUtils$$ExternalSyntheticLambda0(17));
                randomAccessFile.close();
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                if (optional.isPresent()) {
                    int intValue = ((Integer) optional.get()).intValue();
                    builder.copyOnWrite();
                    ProcessProto$AndroidProcessStats processProto$AndroidProcessStats4 = (ProcessProto$AndroidProcessStats) builder.instance;
                    processProto$AndroidProcessStats4.bitField0_ |= 16;
                    processProto$AndroidProcessStats4.oomScoreAdj_ = intValue;
                }
                if (!runningAppProcessInfoResponse.getStatus) {
                    optional3 = Absent.INSTANCE;
                } else {
                    ImmutableList runningAppProcessInfos = runningAppProcessInfoResponse.getRunningAppProcessInfos();
                    Predicate predicate = new Predicate() { // from class: com.google.android.libraries.performance.primes.metriccapture.ProcessImportanceCapture$$ExternalSyntheticLambda0
                        @Override // com.google.common.base.Predicate
                        public final boolean apply(Object obj) {
                            if (((ActivityManager.RunningAppProcessInfo) obj).pid == myPid) {
                                return true;
                            }
                            return false;
                        }
                    };
                    Iterator<E> it = runningAppProcessInfos.iterator();
                    it.getClass();
                    while (true) {
                        if (it.hasNext()) {
                            Object next = it.next();
                            if (predicate.apply(next)) {
                                optional2 = Optional.of(next);
                                break;
                            }
                        } else {
                            optional2 = Absent.INSTANCE;
                            break;
                        }
                    }
                    optional3 = (Optional) optional2.transform(new AccessibilityEventUtils$$ExternalSyntheticLambda0(14)).or(Absent.INSTANCE);
                }
                if (optional3.isPresent()) {
                    String flattenToString = ((ComponentName) optional3.get()).flattenToString();
                    builder.copyOnWrite();
                    ProcessProto$AndroidProcessStats processProto$AndroidProcessStats5 = (ProcessProto$AndroidProcessStats) builder.instance;
                    flattenToString.getClass();
                    processProto$AndroidProcessStats5.bitField0_ |= 32;
                    processProto$AndroidProcessStats5.importanceReasonComponent_ = flattenToString;
                }
                return (ProcessProto$AndroidProcessStats) builder.build();
            } catch (Throwable th) {
                try {
                    randomAccessFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th3;
        }
    }

    private final CharSequence nodeStatusDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String string;
        Context context = (Context) this.ProcessStatsCapture$ar$context;
        CharSequence collapsedOrExpandedStateText = SpannableUtils$NonCopyableTextSpan.getCollapsedOrExpandedStateText(accessibilityNodeInfoCompat, context);
        boolean isEmpty = TextUtils.isEmpty(SpannableUtils$NonCopyableTextSpan.getNodeStateDescription(accessibilityNodeInfoCompat, context, ((GlobalVariables) this.ProcessStatsCapture$ar$oomScoreAdjCapture).getPreferredLocaleByNode(accessibilityNodeInfoCompat)));
        int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat);
        boolean isCheckable = accessibilityNodeInfoCompat.isCheckable();
        boolean isChecked = accessibilityNodeInfoCompat.isChecked();
        LogUtils.v("TreeNodesDescription", "      nodeStatusDescription: %s", String.format("role=%s", SpannableUtils$IdentifierSpan.roleToString(role)) + String.format(", collapsedOrExpandedState=%s", collapsedOrExpandedStateText) + String.format(", stateDescriptionIsEmpty=%s", Boolean.valueOf(isEmpty)) + String.format(", srcIsCheckable=%b", Boolean.valueOf(isCheckable)) + String.format(", srcIsChecked=%b", Boolean.valueOf(isChecked)));
        if (isEmpty && isCheckable && role != 11 && role != 13 && (role != 17 || isChecked)) {
            if (accessibilityNodeInfoCompat.isChecked()) {
                string = ((Context) this.ProcessStatsCapture$ar$context).getString(R.string.value_checked);
            } else {
                string = ((Context) this.ProcessStatsCapture$ar$context).getString(R.string.value_not_checked);
            }
            return CompositorUtils.joinCharSequences(collapsedOrExpandedStateText, string);
        }
        return collapsedOrExpandedStateText;
    }

    private final CharSequence treeNodesDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityEvent accessibilityEvent, boolean z) {
        boolean z2;
        int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat);
        ArrayList arrayList = new ArrayList();
        arrayList.add(((RoleDescriptionExtractor) this.ProcessStatsCapture$ar$foregroundStateCapture).nodeRoleDescriptionText(accessibilityNodeInfoCompat, accessibilityEvent));
        boolean isEmpty = TextUtils.isEmpty(SpannableUtils$NonCopyableTextSpan.getNodeContentDescription(accessibilityNodeInfoCompat, (Context) this.ProcessStatsCapture$ar$context, ((GlobalVariables) this.ProcessStatsCapture$ar$oomScoreAdjCapture).getPreferredLocaleByNode(accessibilityNodeInfoCompat)));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" (%s)", Integer.valueOf(accessibilityNodeInfoCompat.hashCode())));
        sb.append(String.format(", role=%s", SpannableUtils$IdentifierSpan.roleToString(role)));
        sb.append(String.format(", isContentDescriptionEmpty=%b", Boolean.valueOf(isEmpty)));
        sb.append(String.format(", shouldAppendChildNode=%b", Boolean.valueOf(z)));
        if (role != 15 && (role == 5 || role == 8 || role == 16 || isEmpty)) {
            ReorderedChildrenIterator createAscendingIterator = ReorderedChildrenIterator.createAscendingIterator(accessibilityNodeInfoCompat);
            if (!createAscendingIterator.hasNext()) {
                sb.append(", hasNoNextChildNode");
            }
            while (createAscendingIterator.hasNext()) {
                AccessibilityNodeInfoCompat next = createAscendingIterator.next();
                if (next == null) {
                    sb.append(String.format("error: sourceNode (%s) has a null child.", Integer.valueOf(accessibilityNodeInfoCompat.hashCode())));
                } else {
                    boolean isVisible = AccessibilityNodeInfoUtils.isVisible(next);
                    boolean isAccessibilityFocusable = AccessibilityNodeInfoUtils.isAccessibilityFocusable(next);
                    sb.append(String.format("\n        childNode:(%s)", Integer.valueOf(next.hashCode())));
                    sb.append(String.format(", isVisible=%b", Boolean.valueOf(isVisible)));
                    sb.append(String.format(", isAccessibilityFocusable=%b", Boolean.valueOf(isAccessibilityFocusable)));
                    if (isVisible) {
                        if (isAccessibilityFocusable) {
                            if (z) {
                                z2 = true;
                            }
                        } else {
                            z2 = z;
                        }
                        CharSequence appendedTreeDescription = getAppendedTreeDescription(next, accessibilityEvent, z2);
                        sb.append(String.format("\n        > appendChildNodeDescription= {%s}", appendedTreeDescription));
                        arrayList.add(appendedTreeDescription);
                    }
                }
            }
        }
        LogUtils.v("TreeNodesDescription", "      treeNodesDescription:  %s", sb.toString());
        return CompositorUtils.joinCharSequences$ar$ds(arrayList, CompositorUtils.separator);
    }

    public final CharSequence aggregateNodeTreeDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityEvent accessibilityEvent) {
        boolean z;
        boolean z2;
        String str;
        int i = ((GlobalVariables) this.ProcessStatsCapture$ar$oomScoreAdjCapture).descriptionOrder;
        AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
        if (compat != null && compat.getLiveRegion() != 0) {
            z = true;
        } else {
            z = false;
        }
        if (accessibilityEvent.getEventType() == 2048 && z) {
            z2 = true;
        } else {
            z2 = false;
        }
        CharSequence appendedTreeDescription = getAppendedTreeDescription(accessibilityNodeInfoCompat, accessibilityEvent, z2);
        CharSequence descriptionFromLabelNode$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.getDescriptionFromLabelNode$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, (Context) this.ProcessStatsCapture$ar$context, (ExecutionList.RunnableExecutorPair) this.ProcessStatsCapture$ar$processImportanceCapture, (GlobalVariables) this.ProcessStatsCapture$ar$oomScoreAdjCapture);
        LogUtils.v("TreeNodesDescription", "  treeDescriptionWithLabel: %s", String.format(", appendedTreeDescription={%s}", appendedTreeDescription) + String.format(", labelDescription=%s", descriptionFromLabelNode$ar$class_merging$ar$class_merging) + String.format(", shouldAppendChildNode=%s", Boolean.valueOf(z2)));
        if (!TextUtils.isEmpty(descriptionFromLabelNode$ar$class_merging$ar$class_merging)) {
            appendedTreeDescription = ((Context) this.ProcessStatsCapture$ar$context).getString(R.string.template_labeled_item, appendedTreeDescription, descriptionFromLabelNode$ar$class_merging$ar$class_merging);
        }
        Object obj = this.ProcessStatsCapture$ar$context;
        if (!accessibilityNodeInfoCompat.isHeading() && (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() ? !(accessibilityNodeInfoCompat.isEnabled() || (!WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat) && !AccessibilityNodeInfoUtils.isActionableForAccessibility(accessibilityNodeInfoCompat))) : !accessibilityNodeInfoCompat.isEnabled())) {
            str = ((Context) obj).getString(R.string.value_disabled);
        } else {
            str = "";
        }
        CharSequence selectedStateText = SpannableUtils$NonCopyableTextSpan.getSelectedStateText(accessibilityNodeInfoCompat, (Context) this.ProcessStatsCapture$ar$context);
        LogUtils.v("TreeNodesDescription", "aggregateNodeTreeDescription: %s", String.format(" (%s)", Integer.valueOf(accessibilityNodeInfoCompat.hashCode())) + String.format(", treeDescriptionWithLabel={%s}", appendedTreeDescription) + String.format(", selectedState=%s", selectedStateText) + String.format(", descriptionOrder=%s", Integer.valueOf(i)));
        if (i != 1) {
            return CompositorUtils.joinCharSequences(appendedTreeDescription, selectedStateText, str);
        }
        return CompositorUtils.joinCharSequences(selectedStateText, appendedTreeDescription, str);
    }

    public final ProcessProto$AndroidProcessStats getAndroidProcessStats() {
        RunningAppProcessInfoResponse runningAppProcesses = ProcessStats.getRunningAppProcesses((Context) this.ProcessStatsCapture$ar$context);
        return getProcessStatsWithImportanceMetric$ar$ds(null, ((OptionalMethod) this.ProcessStatsCapture$ar$foregroundStateCapture).isInForeground(runningAppProcesses), runningAppProcesses);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.SharedPreferences, java.lang.Object] */
    public final void increaseEventTimes(String str) {
        ?? r0 = this.ProcessStatsCapture$ar$oomScoreAdjCapture;
        if (r0 == 0) {
            return;
        }
        this.ProcessStatsCapture$ar$oomScoreAdjCapture.edit().putInt(str, r0.getInt(str, 0) + 1).apply();
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v8, types: [javax.inject.Provider, java.lang.Object] */
    public final void initialize() {
        if (((AtomicBoolean) this.ProcessStatsCapture$ar$oomScoreAdjCapture).getAndSet(true)) {
            return;
        }
        if (!((Optional) this.ProcessStatsCapture$ar$foregroundStateCapture).isPresent()) {
            ((CrashMetricService) ((Optional) this.ProcessStatsCapture$ar$context).get().get()).setPrimesExceptionHandlerAsDefaultHandler();
        }
        ((MemoryMetricService) ((Optional) this.ProcessStatsCapture$ar$processImportanceCapture).get().get()).startMonitoring();
    }

    public final void notifyFailure$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan) {
        if (spannableUtils$IdentifierSpan != null) {
            ((Handler) this.ProcessStatsCapture$ar$oomScoreAdjCapture).post(new VoiceActionMonitor$$ExternalSyntheticLambda0(spannableUtils$IdentifierSpan, 19));
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6, types: [android.content.SharedPreferences, java.lang.Object] */
    public final void sendAccessbilityMenuLogs() {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) AccessibilityMenuLogProto$AccessibilityMenuExtension.DEFAULT_INSTANCE.createBuilder();
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) AccessibilityMenuSettingsProto$AccessibilityMenuSettings.DEFAULT_INSTANCE.createBuilder();
        for (int i : A11ymenuSettingsEnums$A11ymenuSettings.values$ar$edu$34ea8c43_0()) {
            ?? r7 = this.ProcessStatsCapture$ar$oomScoreAdjCapture;
            String stringGeneratedc97e382a2b02f4b7 = A11ymenuSettingsEnums$A11ymenuSettings.toStringGeneratedc97e382a2b02f4b7(i);
            if (i != 0) {
                int i2 = r7.getInt(stringGeneratedc97e382a2b02f4b7, 0);
                int i3 = A11ymenuSettingsEnums$A11ymenuSettings.UNSPECIFIED_SETTINGS$ar$edu;
                if (i != 0) {
                    if (i != i3 && i2 != 0) {
                        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) AccessibilityMenuSettingsProto$AccessibilityMenuSettings.SettingsRecord.DEFAULT_INSTANCE.createBuilder();
                        builder3.copyOnWrite();
                        AccessibilityMenuSettingsProto$AccessibilityMenuSettings.SettingsRecord settingsRecord = (AccessibilityMenuSettingsProto$AccessibilityMenuSettings.SettingsRecord) builder3.instance;
                        settingsRecord.bitField0_ |= 2;
                        settingsRecord.count_ = i2;
                        builder3.copyOnWrite();
                        AccessibilityMenuSettingsProto$AccessibilityMenuSettings.SettingsRecord settingsRecord2 = (AccessibilityMenuSettingsProto$AccessibilityMenuSettings.SettingsRecord) builder3.instance;
                        int i4 = i - 1;
                        if (i != 0) {
                            settingsRecord2.settings_ = i4;
                            settingsRecord2.bitField0_ |= 1;
                            builder2.copyOnWrite();
                            AccessibilityMenuSettingsProto$AccessibilityMenuSettings accessibilityMenuSettingsProto$AccessibilityMenuSettings = (AccessibilityMenuSettingsProto$AccessibilityMenuSettings) builder2.instance;
                            AccessibilityMenuSettingsProto$AccessibilityMenuSettings.SettingsRecord settingsRecord3 = (AccessibilityMenuSettingsProto$AccessibilityMenuSettings.SettingsRecord) builder3.build();
                            settingsRecord3.getClass();
                            Internal.ProtobufList protobufList = accessibilityMenuSettingsProto$AccessibilityMenuSettings.settingsRecord_;
                            if (!protobufList.isModifiable()) {
                                accessibilityMenuSettingsProto$AccessibilityMenuSettings.settingsRecord_ = GeneratedMessageLite.mutableCopy(protobufList);
                            }
                            accessibilityMenuSettingsProto$AccessibilityMenuSettings.settingsRecord_.add(settingsRecord3);
                        } else {
                            throw null;
                        }
                    }
                } else {
                    throw null;
                }
            } else {
                throw null;
            }
        }
        builder.copyOnWrite();
        AccessibilityMenuLogProto$AccessibilityMenuExtension accessibilityMenuLogProto$AccessibilityMenuExtension = (AccessibilityMenuLogProto$AccessibilityMenuExtension) builder.instance;
        AccessibilityMenuSettingsProto$AccessibilityMenuSettings accessibilityMenuSettingsProto$AccessibilityMenuSettings2 = (AccessibilityMenuSettingsProto$AccessibilityMenuSettings) builder2.build();
        accessibilityMenuSettingsProto$AccessibilityMenuSettings2.getClass();
        accessibilityMenuLogProto$AccessibilityMenuExtension.a11YmenuSettings_ = accessibilityMenuSettingsProto$AccessibilityMenuSettings2;
        accessibilityMenuLogProto$AccessibilityMenuExtension.bitField0_ |= 1;
        SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.DEFAULT_INSTANCE.createBuilder();
        for (A11ymenuSettingsEnums$ShortcutId a11ymenuSettingsEnums$ShortcutId : A11ymenuSettingsEnums$ShortcutId.values()) {
            int i5 = this.ProcessStatsCapture$ar$oomScoreAdjCapture.getInt(a11ymenuSettingsEnums$ShortcutId.name(), 0);
            if (!a11ymenuSettingsEnums$ShortcutId.equals(A11ymenuSettingsEnums$ShortcutId.UNSPECIFIED_ID) && i5 != 0) {
                SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.ShortcutRecord.DEFAULT_INSTANCE.createBuilder();
                builder5.copyOnWrite();
                AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.ShortcutRecord shortcutRecord = (AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.ShortcutRecord) builder5.instance;
                shortcutRecord.bitField0_ |= 2;
                shortcutRecord.count_ = i5;
                builder5.copyOnWrite();
                AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.ShortcutRecord shortcutRecord2 = (AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.ShortcutRecord) builder5.instance;
                shortcutRecord2.shortcutId_ = a11ymenuSettingsEnums$ShortcutId.value;
                shortcutRecord2.bitField0_ |= 1;
                builder4.copyOnWrite();
                AccessibilityMenuShortcutProto$AccessibilityMenuShortcut accessibilityMenuShortcutProto$AccessibilityMenuShortcut = (AccessibilityMenuShortcutProto$AccessibilityMenuShortcut) builder4.instance;
                AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.ShortcutRecord shortcutRecord3 = (AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.ShortcutRecord) builder5.build();
                shortcutRecord3.getClass();
                Internal.ProtobufList protobufList2 = accessibilityMenuShortcutProto$AccessibilityMenuShortcut.shortcutRecord_;
                if (!protobufList2.isModifiable()) {
                    accessibilityMenuShortcutProto$AccessibilityMenuShortcut.shortcutRecord_ = GeneratedMessageLite.mutableCopy(protobufList2);
                }
                accessibilityMenuShortcutProto$AccessibilityMenuShortcut.shortcutRecord_.add(shortcutRecord3);
            }
        }
        builder.copyOnWrite();
        AccessibilityMenuLogProto$AccessibilityMenuExtension accessibilityMenuLogProto$AccessibilityMenuExtension2 = (AccessibilityMenuLogProto$AccessibilityMenuExtension) builder.instance;
        AccessibilityMenuShortcutProto$AccessibilityMenuShortcut accessibilityMenuShortcutProto$AccessibilityMenuShortcut2 = (AccessibilityMenuShortcutProto$AccessibilityMenuShortcut) builder4.build();
        accessibilityMenuShortcutProto$AccessibilityMenuShortcut2.getClass();
        accessibilityMenuLogProto$AccessibilityMenuExtension2.a11YmenuShortcut_ = accessibilityMenuShortcutProto$AccessibilityMenuShortcut2;
        accessibilityMenuLogProto$AccessibilityMenuExtension2.bitField0_ |= 2;
        ((AnalyticsCommon) this.ProcessStatsCapture$ar$foregroundStateCapture).doInBackground((AccessibilityMenuLogProto$AccessibilityMenuExtension) builder.build());
        this.ProcessStatsCapture$ar$oomScoreAdjCapture.edit().clear().apply();
    }

    public ProcessStatsCapture(DisplayStats displayStats, BatteryMetricService batteryMetricService, OptionalMethod optionalMethod, Context context) {
        this.ProcessStatsCapture$ar$oomScoreAdjCapture = displayStats;
        this.ProcessStatsCapture$ar$processImportanceCapture = batteryMetricService;
        this.ProcessStatsCapture$ar$foregroundStateCapture = optionalMethod;
        this.ProcessStatsCapture$ar$context = context;
    }

    public ProcessStatsCapture(Context context) {
        this.ProcessStatsCapture$ar$foregroundStateCapture = Executors.newSingleThreadExecutor();
        this.ProcessStatsCapture$ar$oomScoreAdjCapture = new Handler();
        this.ProcessStatsCapture$ar$processImportanceCapture = new CustomLabelManager(context);
        this.ProcessStatsCapture$ar$context = context;
    }

    public ProcessStatsCapture(final Context context, byte[] bArr) {
        this.ProcessStatsCapture$ar$oomScoreAdjCapture = context.getSharedPreferences("A11Y_MENU", 0);
        this.ProcessStatsCapture$ar$processImportanceCapture = new SnackbarManager(context, "A11Y_MENU", null).build();
        this.ProcessStatsCapture$ar$context = context;
        this.ProcessStatsCapture$ar$foregroundStateCapture = new AnalyticsCommon(context) { // from class: com.google.android.accessibility.accessibilitymenu.analytics.Analytics$1
            @Override // com.google.android.accessibility.utils.AnalyticsCommon
            public final /* bridge */ /* synthetic */ void sendLog(Object obj) {
                AccessibilityMenuLogProto$AccessibilityMenuExtension accessibilityMenuLogProto$AccessibilityMenuExtension = (AccessibilityMenuLogProto$AccessibilityMenuExtension) obj;
                if (accessibilityMenuLogProto$AccessibilityMenuExtension != null) {
                    AccessibilityMenuSettingsProto$AccessibilityMenuSettings accessibilityMenuSettingsProto$AccessibilityMenuSettings = accessibilityMenuLogProto$AccessibilityMenuExtension.a11YmenuSettings_;
                    if (accessibilityMenuSettingsProto$AccessibilityMenuSettings == null) {
                        accessibilityMenuSettingsProto$AccessibilityMenuSettings = AccessibilityMenuSettingsProto$AccessibilityMenuSettings.DEFAULT_INSTANCE;
                    }
                    if (accessibilityMenuSettingsProto$AccessibilityMenuSettings.settingsRecord_.size() == 0) {
                        AccessibilityMenuShortcutProto$AccessibilityMenuShortcut accessibilityMenuShortcutProto$AccessibilityMenuShortcut = accessibilityMenuLogProto$AccessibilityMenuExtension.a11YmenuShortcut_;
                        if (accessibilityMenuShortcutProto$AccessibilityMenuShortcut == null) {
                            accessibilityMenuShortcutProto$AccessibilityMenuShortcut = AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.DEFAULT_INSTANCE;
                        }
                        if (accessibilityMenuShortcutProto$AccessibilityMenuShortcut.shortcutRecord_.size() == 0) {
                            return;
                        }
                    }
                    ProcessStatsCapture processStatsCapture = ProcessStatsCapture.this;
                    ((ClearcutLogger) processStatsCapture.ProcessStatsCapture$ar$processImportanceCapture).newEvent$ar$class_merging(accessibilityMenuLogProto$AccessibilityMenuExtension, CollectionBasisLogVerifier.newInstance$ar$class_merging$30765897_0((Context) processStatsCapture.ProcessStatsCapture$ar$context, new BaseProtoCollectionBasis() { // from class: logs.proto.accessibility.accessibilitymenu.A11yMenuCollectionBasisHelper$AccessibilityMenuExtension
                        @Override // com.google.android.libraries.consentverifier.BaseProtoCollectionBasis
                        public final void singleCollectionBasis$ar$ds() {
                        }
                    })).logAsync();
                }
            }
        };
    }

    public ProcessStatsCapture(Optional optional, Optional optional2, Optional optional3) {
        this.ProcessStatsCapture$ar$oomScoreAdjCapture = new AtomicBoolean(false);
        this.ProcessStatsCapture$ar$context = optional;
        this.ProcessStatsCapture$ar$foregroundStateCapture = optional2;
        this.ProcessStatsCapture$ar$processImportanceCapture = optional3;
    }
}

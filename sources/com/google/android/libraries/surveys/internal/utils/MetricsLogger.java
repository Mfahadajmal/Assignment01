package com.google.android.libraries.surveys.internal.utils;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Region;
import android.net.Uri;
import android.util.SparseIntArray;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.navigation.Navigator$navigate$1;
import com.google.android.accessibility.selecttospeak.overlayui.ChainAnimatorCoroutineImpl$chainAnimations$1;
import com.google.android.accessibility.selecttospeak.overlayui.OverlayUiUtil;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.libraries.accessibility.utils.screenunderstanding.Annotation;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.storage.file.common.LockScope;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$HttpEvent;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$LibraryEvent;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$UserEvent;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry;
import java.security.SecureRandom;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuationImpl;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricsLogger {
    private static MetricsLogger instance;
    public final Object MetricsLogger$ar$logSessionId;
    public Object MetricsLogger$ar$loggerProvider$ar$class_merging;

    public MetricsLogger(LifecycleCoroutineScope lifecycleCoroutineScope) {
        this.MetricsLogger$ar$logSessionId = lifecycleCoroutineScope;
    }

    public static MetricsLogger getInstance() {
        if (instance == null) {
            instance = new MetricsLogger();
        }
        return instance;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void reportEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram.Builder r11, com.google.protobuf.Timestamp r12, com.google.protobuf.Duration r13, android.content.Context r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 724
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.utils.MetricsLogger.reportEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram$Builder, com.google.protobuf.Timestamp, com.google.protobuf.Duration, android.content.Context, java.lang.String):void");
    }

    public static final Object start$ar$ds(Animator animator, Function0 function0, Function1 function1, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        OverlayUiUtil overlayUiUtil = OverlayUiUtil.INSTANCE;
        OverlayUiUtil.addCallback$ar$ds(animator, function0, new Navigator$navigate$1(cancellableContinuationImpl, function1, 3));
        animator.start();
        cancellableContinuationImpl.invokeOnCancellation(new OnBackPressedDispatcher.AnonymousClass1(animator, 18));
        Object result = cancellableContinuationImpl.getResult();
        if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return result;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.coroutines.Job, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
    public final void chainAnimations$ar$ds(List list, Function1 function1) {
        ?? r0 = this.MetricsLogger$ar$loggerProvider$ar$class_merging;
        if (r0 != 0) {
            r0.cancel(null);
        }
        this.MetricsLogger$ar$loggerProvider$ar$class_merging = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(this.MetricsLogger$ar$logSessionId, null, 0, new ChainAnimatorCoroutineImpl$chainAnimations$1(this, function1, list, (Continuation) null, 0), 3);
    }

    public final void flush() {
        synchronized (this.MetricsLogger$ar$logSessionId) {
            ((SparseIntArray) this.MetricsLogger$ar$logSessionId).clear();
        }
    }

    public final int getApkVersionAvailability$ar$ds(int i) {
        int i2;
        synchronized (this.MetricsLogger$ar$logSessionId) {
            i2 = ((SparseIntArray) this.MetricsLogger$ar$logSessionId).get(i, -1);
        }
        return i2;
    }

    public final void reportHttpEvent(UserVoiceSurveysLogging$HttpEvent userVoiceSurveysLogging$HttpEvent, Timestamp timestamp, Duration duration, Context context, String str) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry = (UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry) builder.instance;
        userVoiceSurveysLogging$HttpEvent.getClass();
        userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.httpEvent_ = userVoiceSurveysLogging$HttpEvent;
        userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.bitField0_ |= 2;
        reportEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder, timestamp, duration, context, str);
    }

    public final void reportLibraryEvent(UserVoiceSurveysLogging$LibraryEvent userVoiceSurveysLogging$LibraryEvent, Timestamp timestamp, Duration duration, Context context, String str) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry = (UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry) builder.instance;
        userVoiceSurveysLogging$LibraryEvent.getClass();
        userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.libraryEvent_ = userVoiceSurveysLogging$LibraryEvent;
        userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.bitField0_ |= 8;
        reportEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder, timestamp, duration, context, str);
    }

    public final void reportUserEvent(UserVoiceSurveysLogging$UserEvent userVoiceSurveysLogging$UserEvent, Timestamp timestamp, Duration duration, Context context, String str) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry = (UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry) builder.instance;
        userVoiceSurveysLogging$UserEvent.getClass();
        userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.userEvent_ = userVoiceSurveysLogging$UserEvent;
        userVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.bitField0_ |= 4;
        reportEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder, timestamp, duration, context, str);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.coroutines.Job, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
    public final void runAnimation$ar$ds(Animator animator, Function0 function0) {
        ?? r0 = this.MetricsLogger$ar$loggerProvider$ar$class_merging;
        if (r0 != 0) {
            r0.cancel(null);
        }
        this.MetricsLogger$ar$loggerProvider$ar$class_merging = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(this.MetricsLogger$ar$logSessionId, null, 0, new ChainAnimatorCoroutineImpl$chainAnimations$1(this, animator, function0, (Continuation) null, 2), 3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateLatestResultsState(boolean z, Region region) {
        synchronized (this) {
            if (!z && region != null) {
                ImmutableList.Builder builder = new ImmutableList.Builder();
                UnmodifiableListIterator it = ((ImmutableList) this.MetricsLogger$ar$loggerProvider$ar$class_merging).iterator();
                while (it.hasNext()) {
                    Annotation annotation = (Annotation) it.next();
                    if (region.quickReject(annotation.getBounds())) {
                        builder.add$ar$ds$4f674a09_0(annotation);
                    }
                }
                this.MetricsLogger$ar$loggerProvider$ar$class_merging = builder.build();
                return;
            }
            if (!((ImmutableList) this.MetricsLogger$ar$loggerProvider$ar$class_merging).isEmpty()) {
                this.MetricsLogger$ar$loggerProvider$ar$class_merging = RegularImmutableList.EMPTY;
            }
        }
    }

    public MetricsLogger(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.MetricsLogger$ar$logSessionId = new SparseIntArray();
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(googleApiAvailabilityLight);
        this.MetricsLogger$ar$loggerProvider$ar$class_merging = googleApiAvailabilityLight;
    }

    public MetricsLogger(char[] cArr) {
        this.MetricsLogger$ar$logSessionId = new UiChangesTracker();
        int i = ImmutableList.ImmutableList$ar$NoOp;
        this.MetricsLogger$ar$loggerProvider$ar$class_merging = RegularImmutableList.EMPTY;
    }

    public MetricsLogger(Context context) {
        this.MetricsLogger$ar$loggerProvider$ar$class_merging = new LockScope();
        DisplayStats.checkArgument(context != null, "Context cannot be null", new Object[0]);
        this.MetricsLogger$ar$logSessionId = context.getApplicationContext();
    }

    public MetricsLogger(byte[] bArr) {
        this.MetricsLogger$ar$loggerProvider$ar$class_merging = new Uri.Builder().scheme("file").authority("").path("/");
        this.MetricsLogger$ar$logSessionId = new ImmutableList.Builder();
    }

    private MetricsLogger() {
        long nextLong = new SecureRandom().nextLong();
        this.MetricsLogger$ar$logSessionId = System.currentTimeMillis() + "_" + (nextLong == Long.MIN_VALUE ? 0L : Math.abs(nextLong));
    }
}

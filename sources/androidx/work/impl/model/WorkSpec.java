package androidx.work.impl.model;

import _COROUTINE._BOUNDARY;
import android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.impl.foreground.SystemForegroundService;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkSpec {
    public static final AppCompatTextHelper.Api24Impl Companion$ar$class_merging$5aed0038_0$ar$class_merging = new AppCompatTextHelper.Api24Impl();
    public long backoffDelayDuration;
    public int backoffPolicy$ar$edu;
    public Constraints constraints;
    public boolean expedited;
    public long flexDuration;
    public final int generation;
    public final String id;
    public long initialDelay;
    public Data input;
    public String inputMergerClassName;
    public long intervalDuration;
    public long lastEnqueueTime;
    public long minimumRetentionDuration;
    public long nextScheduleTimeOverride;
    public int nextScheduleTimeOverrideGeneration;
    public int outOfQuotaPolicy$ar$edu;
    public Data output;
    public int periodCount;
    public int runAttemptCount;
    public long scheduleRequestedAt;
    public int state$ar$edu;
    public final int stopReason;
    public String traceTag;
    public String workerClassName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IdAndState {
        public String id;
        public int state$ar$edu;

        public IdAndState(String str, int i) {
            str.getClass();
            this.id = str;
            this.state$ar$edu = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IdAndState)) {
                return false;
            }
            IdAndState idAndState = (IdAndState) obj;
            if (Intrinsics.areEqual(this.id, idAndState.id) && this.state$ar$edu == idAndState.state$ar$edu) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return (this.id.hashCode() * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.state$ar$edu);
        }

        public final String toString() {
            return "IdAndState(id=" + this.id + ", state=" + ((Object) AppCompatReceiveContentHelper$OnDropApi24Impl.toStringGeneratedffb196af7127d286(this.state$ar$edu)) + ')';
        }
    }

    static {
        Logger.tagWithPrefix("WorkSpec");
    }

    public WorkSpec(String str, int i, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints, int i2, int i3, long j4, long j5, long j6, long j7, boolean z, int i4, int i5, int i6, long j8, int i7, int i8, String str4) {
        str.getClass();
        if (i != 0) {
            str2.getClass();
            str3.getClass();
            data.getClass();
            data2.getClass();
            constraints.getClass();
            if (i3 != 0 && i4 != 0) {
                this.id = str;
                this.state$ar$edu = i;
                this.workerClassName = str2;
                this.inputMergerClassName = str3;
                this.input = data;
                this.output = data2;
                this.initialDelay = j;
                this.intervalDuration = j2;
                this.flexDuration = j3;
                this.constraints = constraints;
                this.runAttemptCount = i2;
                this.backoffPolicy$ar$edu = i3;
                this.backoffDelayDuration = j4;
                this.lastEnqueueTime = j5;
                this.minimumRetentionDuration = j6;
                this.scheduleRequestedAt = j7;
                this.expedited = z;
                this.outOfQuotaPolicy$ar$edu = i4;
                this.periodCount = i5;
                this.generation = i6;
                this.nextScheduleTimeOverride = j8;
                this.nextScheduleTimeOverrideGeneration = i7;
                this.stopReason = i8;
                this.traceTag = str4;
                return;
            }
        }
        throw null;
    }

    public final long calculateNextRunTime() {
        long j;
        long scalb;
        boolean isBackedOff = isBackedOff();
        int i = this.runAttemptCount;
        int i2 = this.backoffPolicy$ar$edu;
        long j2 = this.backoffDelayDuration;
        long j3 = this.lastEnqueueTime;
        int i3 = this.periodCount;
        boolean isPeriodic = isPeriodic();
        long j4 = this.initialDelay;
        long j5 = this.flexDuration;
        long j6 = this.intervalDuration;
        long j7 = this.nextScheduleTimeOverride;
        if (i2 != 0) {
            if (j7 != Long.MAX_VALUE && isPeriodic) {
                if (i3 != 0) {
                    return OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(j7, j3 + 900000);
                }
                return j7;
            }
            if (isBackedOff) {
                if (i2 == 2) {
                    scalb = j2 * i;
                } else {
                    scalb = Math.scalb((float) j2, i - 1);
                }
                j = j3 + OnDeviceSmartReplyLogEvent.SmartReply.coerceAtMost(scalb, 18000000L);
            } else if (isPeriodic) {
                if (i3 == 0) {
                    j = j3 + j4;
                    i3 = 0;
                } else {
                    j = j3 + j6;
                }
                if (j5 != j6 && i3 == 0) {
                    j += j6 - j5;
                }
            } else {
                if (j3 == -1) {
                    return Long.MAX_VALUE;
                }
                return j3 + j4;
            }
            return j;
        }
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkSpec)) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) obj;
        if (Intrinsics.areEqual(this.id, workSpec.id) && this.state$ar$edu == workSpec.state$ar$edu && Intrinsics.areEqual(this.workerClassName, workSpec.workerClassName) && Intrinsics.areEqual(this.inputMergerClassName, workSpec.inputMergerClassName) && Intrinsics.areEqual(this.input, workSpec.input) && Intrinsics.areEqual(this.output, workSpec.output) && this.initialDelay == workSpec.initialDelay && this.intervalDuration == workSpec.intervalDuration && this.flexDuration == workSpec.flexDuration && Intrinsics.areEqual(this.constraints, workSpec.constraints) && this.runAttemptCount == workSpec.runAttemptCount && this.backoffPolicy$ar$edu == workSpec.backoffPolicy$ar$edu && this.backoffDelayDuration == workSpec.backoffDelayDuration && this.lastEnqueueTime == workSpec.lastEnqueueTime && this.minimumRetentionDuration == workSpec.minimumRetentionDuration && this.scheduleRequestedAt == workSpec.scheduleRequestedAt && this.expedited == workSpec.expedited && this.outOfQuotaPolicy$ar$edu == workSpec.outOfQuotaPolicy$ar$edu && this.periodCount == workSpec.periodCount && this.generation == workSpec.generation && this.nextScheduleTimeOverride == workSpec.nextScheduleTimeOverride && this.nextScheduleTimeOverrideGeneration == workSpec.nextScheduleTimeOverrideGeneration && this.stopReason == workSpec.stopReason && Intrinsics.areEqual(this.traceTag, workSpec.traceTag)) {
            return true;
        }
        return false;
    }

    public final boolean hasConstraints() {
        if (!Intrinsics.areEqual(Constraints.NONE, this.constraints)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2 = (((((((((this.id.hashCode() * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.state$ar$edu)) * 31) + this.workerClassName.hashCode()) * 31) + this.inputMergerClassName.hashCode()) * 31) + this.input.hashCode()) * 31) + this.output.hashCode();
        int m = SystemForegroundService.Api31Impl.m(this.initialDelay);
        int m2 = SystemForegroundService.Api31Impl.m(this.intervalDuration);
        int m3 = (((((((((((hashCode2 * 31) + m) * 31) + m2) * 31) + SystemForegroundService.Api31Impl.m(this.flexDuration)) * 31) + this.constraints.hashCode()) * 31) + this.runAttemptCount) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.backoffPolicy$ar$edu);
        int m4 = SystemForegroundService.Api31Impl.m(this.backoffDelayDuration);
        int m5 = SystemForegroundService.Api31Impl.m(this.lastEnqueueTime);
        int m6 = SystemForegroundService.Api31Impl.m(this.minimumRetentionDuration);
        int m7 = SystemForegroundService.Api31Impl.m(this.scheduleRequestedAt);
        int ArtificialStackFrames$ar$MethodMerging$dc56d17a_11 = (((((((((((((m3 * 31) + m4) * 31) + m5) * 31) + m6) * 31) + m7) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.expedited)) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.outOfQuotaPolicy$ar$edu)) * 31) + this.periodCount;
        int m8 = (((((ArtificialStackFrames$ar$MethodMerging$dc56d17a_11 * 31) + this.generation) * 31) + SystemForegroundService.Api31Impl.m(this.nextScheduleTimeOverride)) * 31) + this.nextScheduleTimeOverrideGeneration;
        String str = this.traceTag;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        return (((m8 * 31) + this.stopReason) * 31) + hashCode;
    }

    public final boolean isBackedOff() {
        if (this.state$ar$edu == 1 && this.runAttemptCount > 0) {
            return true;
        }
        return false;
    }

    public final boolean isPeriodic() {
        if (this.intervalDuration != 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "{WorkSpec: " + this.id + '}';
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ WorkSpec(java.lang.String r37, int r38, java.lang.String r39, java.lang.String r40, androidx.work.Data r41, androidx.work.Data r42, long r43, long r45, long r47, androidx.work.Constraints r49, int r50, int r51, long r52, long r54, long r56, long r58, boolean r60, int r61, int r62, long r63, int r65, int r66, java.lang.String r67, int r68) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpec.<init>(java.lang.String, int, java.lang.String, java.lang.String, androidx.work.Data, androidx.work.Data, long, long, long, androidx.work.Constraints, int, int, long, long, long, long, boolean, int, int, long, int, int, java.lang.String, int):void");
    }
}

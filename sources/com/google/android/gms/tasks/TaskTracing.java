package com.google.android.gms.tasks;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskTracing {
    private static final SpannableUtils$NonCopyableTextSpan NOOP_TRACE_ENGINE$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$NonCopyableTextSpan traceEngine$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    static {
        SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = new SpannableUtils$NonCopyableTextSpan();
        NOOP_TRACE_ENGINE$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        traceEngine$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
    }

    public static Executor traceExecutor(Executor executor) {
        return executor;
    }
}

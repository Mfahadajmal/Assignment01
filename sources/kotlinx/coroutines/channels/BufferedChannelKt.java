package kotlinx.coroutines.channels;

import com.google.mlkit.logging.schema.TextDetectionOptionalModuleLogEvent;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BufferedChannelKt {
    public static final ConcurrentLinkedListNode NULL_SEGMENT$ar$class_merging = new ConcurrentLinkedListNode(-1, null, null, 0);
    public static final int SEGMENT_SIZE = TextDetectionOptionalModuleLogEvent.systemProp$default$ar$ds("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12);
    public static final int EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = TextDetectionOptionalModuleLogEvent.systemProp$default$ar$ds("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12);
    public static final Symbol BUFFERED = new Symbol("BUFFERED");
    public static final Symbol IN_BUFFER = new Symbol("SHOULD_BUFFER");
    public static final Symbol RESUMING_BY_RCV = new Symbol("S_RESUMING_BY_RCV");
    public static final Symbol RESUMING_BY_EB = new Symbol("RESUMING_BY_EB");
    public static final Symbol POISONED = new Symbol("POISONED");
    public static final Symbol DONE_RCV = new Symbol("DONE_RCV");
    public static final Symbol INTERRUPTED_SEND = new Symbol("INTERRUPTED_SEND");
    public static final Symbol INTERRUPTED_RCV = new Symbol("INTERRUPTED_RCV");
    public static final Symbol CHANNEL_CLOSED = new Symbol("CHANNEL_CLOSED");
    public static final Symbol SUSPEND = new Symbol("SUSPEND");
    public static final Symbol SUSPEND_NO_WAITER = new Symbol("SUSPEND_NO_WAITER");
    public static final Symbol FAILED = new Symbol("FAILED");
    public static final Symbol NO_RECEIVE_RESULT = new Symbol("NO_RECEIVE_RESULT");
    public static final Symbol CLOSE_HANDLER_CLOSED = new Symbol("CLOSE_HANDLER_CLOSED");
    public static final Symbol CLOSE_HANDLER_INVOKED = new Symbol("CLOSE_HANDLER_INVOKED");
    public static final Symbol NO_CLOSE_CAUSE = new Symbol("NO_CLOSE_CAUSE");

    public static final long constructEBCompletedAndPauseFlag(long j, boolean z) {
        long j2;
        if (true != z) {
            j2 = 0;
        } else {
            j2 = 4611686018427387904L;
        }
        return j2 + j;
    }

    public static final long constructSendersAndCloseStatus(long j, int i) {
        return (i << 60) + j;
    }

    public static final boolean tryResume0$ar$class_merging(CancellableContinuationImpl cancellableContinuationImpl, Object obj, Function1 function1) {
        Object tryResume$ar$ds = cancellableContinuationImpl.tryResume$ar$ds(obj, function1);
        if (tryResume$ar$ds != null) {
            cancellableContinuationImpl.completeResume(tryResume$ar$ds);
            return true;
        }
        return false;
    }
}

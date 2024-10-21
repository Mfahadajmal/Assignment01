package kotlinx.coroutines.channels;

import _COROUTINE._BOUNDARY;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConflatedBufferedChannel extends BufferedChannel {
    private final int capacity;
    private final int onBufferOverflow$ar$edu;

    public ConflatedBufferedChannel(int i, int i2, Function1 function1) {
        super(i, null);
        this.capacity = i;
        this.onBufferOverflow$ar$edu = i2;
        if (i2 != BufferOverflow.SUSPEND$ar$edu) {
            if (i > 0) {
            } else {
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Buffered channel capacity must be at least 1, but ", " was specified"));
            }
        } else {
            throw new IllegalArgumentException("This implementation does not support suspension for senders, use " + Reflection.getOrCreateKotlinClass(BufferedChannel.class).getSimpleName() + " instead");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
    
        return r15;
     */
    /* renamed from: trySendImpl-Mj0NB7M, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object m259trySendImplMj0NB7M(java.lang.Object r14, boolean r15) {
        /*
            r13 = this;
            int r15 = r13.onBufferOverflow$ar$edu
            int r0 = kotlinx.coroutines.channels.BufferOverflow.DROP_LATEST$ar$edu
            if (r15 != r0) goto L17
            java.lang.Object r14 = super.mo257trySendJP2dKIU(r14)
            boolean r15 = r14 instanceof kotlinx.coroutines.channels.ChannelResult.Failed
            if (r15 == 0) goto Lae
            boolean r15 = r14 instanceof kotlinx.coroutines.channels.ChannelResult.Closed
            if (r15 == 0) goto L14
            goto Lae
        L14:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L17:
            kotlinx.atomicfu.AtomicRef r15 = r13.sendSegment
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            java.lang.Object r15 = r15.value
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r15 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r15
        L1f:
            kotlinx.atomicfu.AtomicLong r0 = r13.sendersAndCloseStatus
            long r0 = r0.getAndIncrement()
            r2 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r9 = r0 & r2
            boolean r11 = super.isClosedForSend0(r0)
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r0 = (long) r0
            long r2 = r9 / r0
            long r0 = r9 % r0
            int r12 = (int) r0
            long r0 = r15.id
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L51
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r0 = super.findSegmentSend$ar$class_merging(r2, r15)
            if (r0 != 0) goto L50
            if (r11 == 0) goto L1f
            java.lang.Throwable r14 = r13.getSendException()
            kotlinx.coroutines.channels.ChannelResult$Closed r15 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r15.<init>(r14)
            goto L99
        L50:
            r15 = r0
        L51:
            r0 = r13
            r1 = r15
            r2 = r12
            r3 = r14
            r4 = r9
            r6 = r8
            r7 = r11
            int r0 = super.updateCellSend$ar$class_merging(r1, r2, r3, r4, r6, r7)
            if (r0 == 0) goto La9
            r1 = 1
            if (r0 == r1) goto La6
            r1 = 2
            if (r0 == r1) goto L8b
            r1 = 3
            if (r0 == r1) goto L83
            r1 = 4
            if (r0 == r1) goto L6e
            r15.cleanPrev()
            goto L1f
        L6e:
            long r0 = r13.getReceiversCounter$kotlinx_coroutines_core()
            int r14 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r14 >= 0) goto L79
            r15.cleanPrev()
        L79:
            java.lang.Throwable r14 = r13.getSendException()
            kotlinx.coroutines.channels.ChannelResult$Closed r15 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r15.<init>(r14)
            goto L99
        L83:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "unexpected"
            r14.<init>(r15)
            throw r14
        L8b:
            if (r11 == 0) goto L9b
            r15.onSlotCleaned()
            java.lang.Throwable r14 = r13.getSendException()
            kotlinx.coroutines.channels.ChannelResult$Closed r15 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r15.<init>(r14)
        L99:
            r14 = r15
            goto Lae
        L9b:
            long r14 = r15.id
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r0 = (long) r0
            long r14 = r14 * r0
            long r0 = (long) r12
            long r14 = r14 + r0
            r13.dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(r14)
        La6:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            goto Lae
        La9:
            r15.cleanPrev()
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
        Lae:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ConflatedBufferedChannel.m259trySendImplMj0NB7M(java.lang.Object, boolean):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    protected final boolean isConflatedDropOldest() {
        if (this.onBufferOverflow$ar$edu == BufferOverflow.DROP_OLDEST$ar$edu) {
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public final Object send(Object obj, Continuation continuation) {
        Object m259trySendImplMj0NB7M = m259trySendImplMj0NB7M(obj, true);
        if (!(m259trySendImplMj0NB7M instanceof ChannelResult.Closed)) {
            return Unit.INSTANCE;
        }
        throw getSendException();
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public final Object mo257trySendJP2dKIU(Object obj) {
        return m259trySendImplMj0NB7M(obj, false);
    }
}

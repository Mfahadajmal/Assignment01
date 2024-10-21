package kotlinx.coroutines.channels;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectImplementation;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BufferedChannel implements Channel {
    private final AtomicRef _closeCause;
    private final AtomicLong bufferEnd;
    private final AtomicRef bufferEndSegment;
    private final int capacity;
    private final AtomicRef closeHandler;
    private final AtomicLong completedExpandBuffersAndPauseFlag;
    public final Function1 onUndeliveredElement = null;
    public final AtomicRef receiveSegment;
    public final AtomicLong receivers;
    public final AtomicRef sendSegment;
    public final AtomicLong sendersAndCloseStatus;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BufferedChannelIterator implements Waiter {
        public CancellableContinuationImpl continuation;
        public Object receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;

        public BufferedChannelIterator() {
        }

        public final Object hasNext(Continuation continuation) {
            ConcurrentLinkedListNode concurrentLinkedListNode;
            boolean z;
            ConcurrentLinkedListNode concurrentLinkedListNode2;
            BufferedChannel bufferedChannel = BufferedChannel.this;
            ConcurrentLinkedListNode concurrentLinkedListNode3 = (ConcurrentLinkedListNode) bufferedChannel.receiveSegment.value;
            while (!bufferedChannel.isClosedForReceive()) {
                long andIncrement = bufferedChannel.receivers.getAndIncrement();
                long j = andIncrement / BufferedChannelKt.SEGMENT_SIZE;
                int i = (int) (andIncrement % BufferedChannelKt.SEGMENT_SIZE);
                if (concurrentLinkedListNode3.id != j) {
                    ConcurrentLinkedListNode findSegmentReceive$ar$class_merging = bufferedChannel.findSegmentReceive$ar$class_merging(j, concurrentLinkedListNode3);
                    if (findSegmentReceive$ar$class_merging != null) {
                        concurrentLinkedListNode = findSegmentReceive$ar$class_merging;
                    } else {
                        continue;
                    }
                } else {
                    concurrentLinkedListNode = concurrentLinkedListNode3;
                }
                Object updateCellReceive$ar$class_merging = bufferedChannel.updateCellReceive$ar$class_merging(concurrentLinkedListNode, i, andIncrement, null);
                if (updateCellReceive$ar$class_merging != BufferedChannelKt.SUSPEND) {
                    if (updateCellReceive$ar$class_merging == BufferedChannelKt.FAILED) {
                        if (andIncrement < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                            concurrentLinkedListNode.cleanPrev();
                        }
                        concurrentLinkedListNode3 = concurrentLinkedListNode;
                    } else {
                        if (updateCellReceive$ar$class_merging == BufferedChannelKt.SUSPEND_NO_WAITER) {
                            BufferedChannel bufferedChannel2 = BufferedChannel.this;
                            CancellableContinuationImpl orCreateCancellableContinuation = OnDeviceTranslationLogEvent.getOrCreateCancellableContinuation(OnDevicePoseDetectionLogEvent.intercepted(continuation));
                            try {
                                this.continuation = orCreateCancellableContinuation;
                                Object updateCellReceive$ar$class_merging2 = bufferedChannel2.updateCellReceive$ar$class_merging(concurrentLinkedListNode, i, andIncrement, this);
                                if (updateCellReceive$ar$class_merging2 == BufferedChannelKt.SUSPEND) {
                                    invokeOnCancellation$ar$class_merging$3a98f194_0(concurrentLinkedListNode, i);
                                } else {
                                    if (updateCellReceive$ar$class_merging2 == BufferedChannelKt.FAILED) {
                                        if (andIncrement < bufferedChannel2.getSendersCounter$kotlinx_coroutines_core()) {
                                            concurrentLinkedListNode.cleanPrev();
                                        }
                                        ConcurrentLinkedListNode concurrentLinkedListNode4 = (ConcurrentLinkedListNode) bufferedChannel2.receiveSegment.value;
                                        while (true) {
                                            if (bufferedChannel2.isClosedForReceive()) {
                                                CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
                                                cancellableContinuationImpl.getClass();
                                                this.continuation = null;
                                                this.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
                                                Throwable closeCause = BufferedChannel.this.getCloseCause();
                                                if (closeCause == null) {
                                                    cancellableContinuationImpl.resumeWith(false);
                                                } else {
                                                    if (DebugKt.RECOVER_STACK_TRACES) {
                                                        closeCause = StackTraceRecoveryKt.recoverFromStackFrame(closeCause, cancellableContinuationImpl);
                                                    }
                                                    cancellableContinuationImpl.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(closeCause));
                                                }
                                            } else {
                                                long andIncrement2 = bufferedChannel2.receivers.getAndIncrement();
                                                long j2 = BufferedChannelKt.SEGMENT_SIZE;
                                                long j3 = andIncrement2 / j2;
                                                int i2 = (int) (andIncrement2 % j2);
                                                if (concurrentLinkedListNode4.id != j3) {
                                                    ConcurrentLinkedListNode findSegmentReceive$ar$class_merging2 = bufferedChannel2.findSegmentReceive$ar$class_merging(j3, concurrentLinkedListNode4);
                                                    if (findSegmentReceive$ar$class_merging2 != null) {
                                                        concurrentLinkedListNode2 = findSegmentReceive$ar$class_merging2;
                                                    } else {
                                                        continue;
                                                    }
                                                } else {
                                                    concurrentLinkedListNode2 = concurrentLinkedListNode4;
                                                }
                                                Object updateCellReceive$ar$class_merging3 = bufferedChannel2.updateCellReceive$ar$class_merging(concurrentLinkedListNode2, i2, andIncrement2, this);
                                                if (updateCellReceive$ar$class_merging3 == BufferedChannelKt.SUSPEND) {
                                                    invokeOnCancellation$ar$class_merging$3a98f194_0(concurrentLinkedListNode2, i2);
                                                    break;
                                                }
                                                if (updateCellReceive$ar$class_merging3 == BufferedChannelKt.FAILED) {
                                                    if (andIncrement2 < bufferedChannel2.getSendersCounter$kotlinx_coroutines_core()) {
                                                        concurrentLinkedListNode2.cleanPrev();
                                                    }
                                                    concurrentLinkedListNode4 = concurrentLinkedListNode2;
                                                } else if (updateCellReceive$ar$class_merging3 != BufferedChannelKt.SUSPEND_NO_WAITER) {
                                                    concurrentLinkedListNode2.cleanPrev();
                                                    this.receiveResult = updateCellReceive$ar$class_merging3;
                                                    this.continuation = null;
                                                    z = true;
                                                } else {
                                                    throw new IllegalStateException("unexpected");
                                                }
                                            }
                                        }
                                    } else {
                                        concurrentLinkedListNode.cleanPrev();
                                        this.receiveResult = updateCellReceive$ar$class_merging2;
                                        this.continuation = null;
                                        z = true;
                                    }
                                    orCreateCancellableContinuation.resume(z, null);
                                }
                                Object result = orCreateCancellableContinuation.getResult();
                                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                return result;
                            } catch (Throwable th) {
                                orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                throw th;
                            }
                        }
                        concurrentLinkedListNode.cleanPrev();
                        this.receiveResult = updateCellReceive$ar$class_merging;
                        return true;
                    }
                } else {
                    throw new IllegalStateException("unreachable");
                }
            }
            this.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
            Throwable closeCause2 = BufferedChannel.this.getCloseCause();
            if (closeCause2 == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(closeCause2);
        }

        @Override // kotlinx.coroutines.Waiter
        public final void invokeOnCancellation$ar$class_merging$3a98f194_0(ConcurrentLinkedListNode concurrentLinkedListNode, int i) {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.invokeOnCancellation$ar$class_merging$3a98f194_0(concurrentLinkedListNode, i);
            }
        }

        public final Object next() {
            Object obj = this.receiveResult;
            if (obj != BufferedChannelKt.NO_RECEIVE_RESULT) {
                this.receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;
                if (obj != BufferedChannelKt.CHANNEL_CLOSED) {
                    return obj;
                }
                throw StackTraceRecoveryKt.recoverStackTrace(BufferedChannel.this.getReceiveException());
            }
            throw new IllegalStateException("`hasNext()` has not been invoked");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SendBroadcast implements Waiter {
        @Override // kotlinx.coroutines.Waiter
        public final void invokeOnCancellation$ar$class_merging$3a98f194_0(ConcurrentLinkedListNode concurrentLinkedListNode, int i) {
            throw null;
        }
    }

    public BufferedChannel(int i, Function1 function1) {
        this.capacity = i;
        if (i >= 0) {
            long j = 0;
            this.sendersAndCloseStatus = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0L);
            this.receivers = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0L);
            int i2 = BufferedChannelKt.SEGMENT_SIZE;
            if (i != 0) {
                if (i != Integer.MAX_VALUE) {
                    j = i;
                } else {
                    j = Long.MAX_VALUE;
                }
            }
            this.bufferEnd = OnDeviceSubjectSegmentationCreateLogEvent.atomic(j);
            this.completedExpandBuffersAndPauseFlag = OnDeviceSubjectSegmentationCreateLogEvent.atomic(getBufferEndCounter());
            ConcurrentLinkedListNode concurrentLinkedListNode = new ConcurrentLinkedListNode(0L, null, this, 3);
            this.sendSegment = OnDeviceSubjectSegmentationCreateLogEvent.atomic(concurrentLinkedListNode);
            this.receiveSegment = OnDeviceSubjectSegmentationCreateLogEvent.atomic(concurrentLinkedListNode);
            if (isRendezvousOrUnlimited()) {
                concurrentLinkedListNode = BufferedChannelKt.NULL_SEGMENT$ar$class_merging;
                concurrentLinkedListNode.getClass();
            }
            this.bufferEndSegment = OnDeviceSubjectSegmentationCreateLogEvent.atomic(concurrentLinkedListNode);
            this._closeCause = OnDeviceSubjectSegmentationCreateLogEvent.atomic(BufferedChannelKt.NO_CLOSE_CAUSE);
            this.closeHandler = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
            return;
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Invalid channel capacity: ", ", should be >=0"));
    }

    private final boolean bufferOrRendezvousSend(long j) {
        if (j >= getBufferEndCounter() && j >= getReceiversCounter$kotlinx_coroutines_core() + this.capacity) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0069, code lost:
    
        r1 = r1.getPrev();
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00d1, code lost:
    
        r2 = r2.getPrev();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlinx.coroutines.internal.ConcurrentLinkedListNode completeClose$ar$class_merging(long r12) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.completeClose$ar$class_merging(long):kotlinx.coroutines.internal.ConcurrentLinkedListNode");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x000d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void expandBuffer() {
        /*
            Method dump skipped, instructions count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.expandBuffer():void");
    }

    private final long getBufferEndCounter() {
        return this.bufferEnd.value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void incCompletedExpandBufferAttempts(long j) {
        if ((this.completedExpandBuffersAndPauseFlag.addAndGet(j) & 4611686018427387904L) == 0) {
            return;
        }
        do {
        } while ((this.completedExpandBuffersAndPauseFlag.value & 4611686018427387904L) != 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:140:0x0106, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x009d, code lost:
    
        r9 = r9.getPrev();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isClosed(long r9, boolean r11) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.isClosed(long, boolean):boolean");
    }

    private final boolean isClosedForReceive0(long j) {
        return isClosed(j, true);
    }

    private final boolean isRendezvousOrUnlimited() {
        long bufferEndCounter = getBufferEndCounter();
        if (bufferEndCounter != 0 && bufferEndCounter != Long.MAX_VALUE) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x000f, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void moveSegmentBufferEndToSpecifiedOrLast$ar$class_merging(long r5, kotlinx.coroutines.internal.ConcurrentLinkedListNode r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.id
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto Lf
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r0 = r7.getNext()
            if (r0 != 0) goto Ld
            goto Lf
        Ld:
            r7 = r0
            goto L0
        Lf:
            boolean r5 = r7.isRemoved()
            if (r5 == 0) goto L1e
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r5 = r7.getNext()
            if (r5 != 0) goto L1c
            goto L1e
        L1c:
            r7 = r5
            goto Lf
        L1e:
            kotlinx.atomicfu.AtomicRef r5 = r4.bufferEndSegment
        L20:
            java.lang.Object r6 = r5.value
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r6 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r6
            long r0 = r6.id
            long r2 = r7.id
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L2d
            goto L42
        L2d:
            boolean r0 = r7.tryIncPointers$kotlinx_coroutines_core()
            if (r0 == 0) goto Lf
            boolean r0 = r5.compareAndSet(r6, r7)
            if (r0 == 0) goto L43
            boolean r5 = r6.decPointers$kotlinx_coroutines_core()
            if (r5 == 0) goto L42
            r6.remove()
        L42:
            return
        L43:
            boolean r6 = r7.decPointers$kotlinx_coroutines_core()
            if (r6 == 0) goto L20
            r7.remove()
            goto L20
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.moveSegmentBufferEndToSpecifiedOrLast$ar$class_merging(long, kotlinx.coroutines.internal.ConcurrentLinkedListNode):void");
    }

    private final Object onClosedSend(Object obj, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Throwable sendException = getSendException();
        if (DebugKt.RECOVER_STACK_TRACES) {
            sendException = StackTraceRecoveryKt.recoverFromStackFrame(sendException, cancellableContinuationImpl);
        }
        cancellableContinuationImpl.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(sendException));
        Object result = cancellableContinuationImpl.getResult();
        if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return result;
        }
        return Unit.INSTANCE;
    }

    private final void onClosedSendOnNoWaiterSuspend$ar$class_merging(Object obj, CancellableContinuationImpl cancellableContinuationImpl) {
        Throwable sendException = getSendException();
        if (DebugKt.RECOVER_STACK_TRACES) {
            sendException = StackTraceRecoveryKt.recoverFromStackFrame(sendException, cancellableContinuationImpl);
        }
        cancellableContinuationImpl.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(sendException));
    }

    private static final void prepareSenderForSuspension$ar$class_merging$ar$ds(Waiter waiter, ConcurrentLinkedListNode concurrentLinkedListNode, int i) {
        waiter.invokeOnCancellation$ar$class_merging$3a98f194_0(concurrentLinkedListNode, i + BufferedChannelKt.SEGMENT_SIZE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* renamed from: receiveCatching-JP2dKIU$suspendImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object m253receiveCatchingJP2dKIU$suspendImpl(kotlinx.coroutines.channels.BufferedChannel r13, kotlin.coroutines.Continuation r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1
            if (r0 == 0) goto L13
            r0 = r14
            kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1 r0 = (kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1 r0 = new kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1
            r0.<init>(r13, r14)
        L18:
            r6 = r0
            java.lang.Object r14 = r6.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r14)
            kotlinx.coroutines.channels.ChannelResult r14 = (kotlinx.coroutines.channels.ChannelResult) r14
            java.lang.Object r13 = r14.holder
            goto L9d
        L2d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L35:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r14)
            kotlinx.atomicfu.AtomicRef r14 = r13.receiveSegment
            java.lang.Object r14 = r14.value
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r14 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r14
        L3e:
            boolean r1 = r13.isClosedForReceive()
            if (r1 == 0) goto L4f
            java.lang.Throwable r13 = r13.getCloseCause()
            kotlinx.coroutines.channels.ChannelResult$Closed r14 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r14.<init>(r13)
            r13 = r14
            goto L9d
        L4f:
            kotlinx.atomicfu.AtomicLong r1 = r13.receivers
            long r4 = r1.getAndIncrement()
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r7 = (long) r1
            long r7 = r4 / r7
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r9 = (long) r1
            long r9 = r4 % r9
            int r3 = (int) r9
            long r9 = r14.id
            int r1 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r1 == 0) goto L6d
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r1 = r13.findSegmentReceive$ar$class_merging(r7, r14)
            if (r1 == 0) goto L3e
            r14 = r1
        L6d:
            r12 = 0
            r7 = r13
            r8 = r14
            r9 = r3
            r10 = r4
            java.lang.Object r1 = r7.updateCellReceive$ar$class_merging(r8, r9, r10, r12)
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND
            if (r1 == r7) goto L9e
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED
            if (r1 != r7) goto L8a
            long r7 = r13.getSendersCounter$kotlinx_coroutines_core()
            int r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r1 >= 0) goto L3e
            r14.cleanPrev()
            goto L3e
        L8a:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND_NO_WAITER
            if (r1 != r7) goto L99
            r6.label = r2
            r1 = r13
            r2 = r14
            java.lang.Object r13 = r1.m255receiveCatchingOnNoWaiterSuspendGKJJFZk$ar$class_merging(r2, r3, r4, r6)
            if (r13 != r0) goto L9d
            return r0
        L99:
            r14.cleanPrev()
            r13 = r1
        L9d:
            return r13
        L9e:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "unexpected"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m253receiveCatchingJP2dKIU$suspendImpl(kotlinx.coroutines.channels.BufferedChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void resumeReceiverOnClosedChannel(Waiter waiter) {
        resumeWaiterOnClosedChannel(waiter, true);
    }

    private final void resumeSenderOnCancelledChannel(Waiter waiter) {
        resumeWaiterOnClosedChannel(waiter, false);
    }

    private final void resumeWaiterOnClosedChannel(Waiter waiter, boolean z) {
        Throwable sendException;
        if (!(waiter instanceof SendBroadcast)) {
            if (waiter instanceof CancellableContinuationImpl) {
                Continuation continuation = (Continuation) waiter;
                if (z) {
                    sendException = getReceiveException();
                } else {
                    sendException = getSendException();
                }
                continuation.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(sendException));
                return;
            }
            if (waiter instanceof ReceiveCatching) {
                ((ReceiveCatching) waiter).cont.resumeWith(new ChannelResult(new ChannelResult.Closed(getCloseCause())));
                return;
            }
            if (waiter instanceof BufferedChannelIterator) {
                BufferedChannelIterator bufferedChannelIterator = (BufferedChannelIterator) waiter;
                CancellableContinuationImpl cancellableContinuationImpl = bufferedChannelIterator.continuation;
                cancellableContinuationImpl.getClass();
                bufferedChannelIterator.continuation = null;
                bufferedChannelIterator.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
                Throwable closeCause = BufferedChannel.this.getCloseCause();
                if (closeCause == null) {
                    cancellableContinuationImpl.resumeWith(false);
                    return;
                }
                if (DebugKt.RECOVER_STACK_TRACES) {
                    closeCause = StackTraceRecoveryKt.recoverFromStackFrame(closeCause, cancellableContinuationImpl);
                }
                cancellableContinuationImpl.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(closeCause));
                return;
            }
            if (waiter instanceof SelectImplementation) {
                int i = BufferedChannelKt.SEGMENT_SIZE;
                SelectImplementation.trySelect$ar$ds();
                return;
            }
            Objects.toString(waiter);
            throw new IllegalStateException("Unexpected waiter: ".concat(String.valueOf(waiter)));
        }
        throw null;
    }

    private final boolean tryResumeReceiver(Object obj, Object obj2) {
        if (obj instanceof SelectImplementation) {
            return SelectImplementation.trySelect$ar$ds();
        }
        if (obj instanceof ReceiveCatching) {
            obj.getClass();
            return BufferedChannelKt.tryResume0$ar$class_merging(((ReceiveCatching) obj).cont, new ChannelResult(obj2), null);
        }
        if (obj instanceof BufferedChannelIterator) {
            obj.getClass();
            BufferedChannelIterator bufferedChannelIterator = (BufferedChannelIterator) obj;
            CancellableContinuationImpl cancellableContinuationImpl = bufferedChannelIterator.continuation;
            cancellableContinuationImpl.getClass();
            bufferedChannelIterator.continuation = null;
            bufferedChannelIterator.receiveResult = obj2;
            return BufferedChannelKt.tryResume0$ar$class_merging(cancellableContinuationImpl, true, null);
        }
        if (obj instanceof CancellableContinuationImpl) {
            obj.getClass();
            return BufferedChannelKt.tryResume0$ar$class_merging((CancellableContinuationImpl) obj, obj2, null);
        }
        Objects.toString(obj);
        throw new IllegalStateException("Unexpected receiver type: ".concat(String.valueOf(obj)));
    }

    private static final boolean tryResumeSender$ar$class_merging$ar$ds(Object obj) {
        if (obj instanceof CancellableContinuationImpl) {
            obj.getClass();
            return BufferedChannelKt.tryResume0$default$ar$class_merging$ar$ds((CancellableContinuationImpl) obj, Unit.INSTANCE);
        }
        if (!(obj instanceof SelectImplementation)) {
            if (obj instanceof SendBroadcast) {
                throw null;
            }
            Objects.toString(obj);
            throw new IllegalStateException("Unexpected waiter: ".concat(String.valueOf(obj)));
        }
        obj.getClass();
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0031 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0000 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int updateCellSendSlow$ar$class_merging(kotlinx.coroutines.internal.ConcurrentLinkedListNode r6, int r7, java.lang.Object r8, long r9, java.lang.Object r11, boolean r12) {
        /*
            r5 = this;
        L0:
            java.lang.Object r0 = r6.getState$kotlinx_coroutines_core(r7)
            r1 = 4
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L35
            boolean r0 = r5.bufferOrRendezvousSend(r9)
            r4 = 0
            if (r0 == 0) goto L1b
            if (r12 != 0) goto L29
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r4, r0)
            if (r0 == 0) goto L0
            return r3
        L1b:
            if (r12 != 0) goto L29
            if (r11 != 0) goto L21
            r6 = 3
            return r6
        L21:
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r4, r11)
            if (r0 == 0) goto L0
            r6 = 2
            return r6
        L29:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r4, r0)
            if (r0 == 0) goto L0
            r6.onCancelledRequest(r7, r2)
            return r1
        L35:
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            if (r0 != r4) goto L42
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r0, r1)
            if (r0 == 0) goto L0
            return r3
        L42:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            r10 = 5
            if (r0 == r9) goto L83
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.POISONED
            if (r0 == r9) goto L7f
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            if (r0 != r9) goto L56
            r6.cleanElement$kotlinx_coroutines_core(r7)
            r5.isClosedForSend()
            return r1
        L56:
            boolean r9 = kotlinx.coroutines.DebugKt.ASSERTIONS_ENABLED
            r6.cleanElement$kotlinx_coroutines_core(r7)
            boolean r9 = r0 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r9 == 0) goto L63
            kotlinx.coroutines.channels.WaiterEB r0 = (kotlinx.coroutines.channels.WaiterEB) r0
            kotlinx.coroutines.Waiter r0 = r0.waiter
        L63:
            boolean r8 = r5.tryResumeReceiver(r0, r8)
            if (r8 == 0) goto L6f
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            r6.setState$kotlinx_coroutines_core(r7, r8)
            goto L7a
        L6f:
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            java.lang.Object r8 = r6.getAndSetState$kotlinx_coroutines_core(r7, r8)
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            if (r8 != r9) goto L7b
            r2 = r10
        L7a:
            return r2
        L7b:
            r6.onCancelledRequest(r7, r3)
            return r10
        L7f:
            r6.cleanElement$kotlinx_coroutines_core(r7)
            return r10
        L83:
            r6.cleanElement$kotlinx_coroutines_core(r7)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.updateCellSendSlow$ar$class_merging(kotlinx.coroutines.internal.ConcurrentLinkedListNode, int, java.lang.Object, long, java.lang.Object, boolean):int");
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        closeOrCancelImpl(cancellationException, true);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean close(Throwable th) {
        return closeOrCancelImpl(th, false);
    }

    protected final boolean closeOrCancelImpl(Throwable th, boolean z) {
        long j;
        long constructSendersAndCloseStatus;
        Object obj;
        Symbol symbol;
        long j2;
        long j3;
        if (z) {
            AtomicLong atomicLong = this.sendersAndCloseStatus;
            do {
                j3 = atomicLong.value;
                if (((int) (j3 >> 60)) != 0) {
                    break;
                }
            } while (!atomicLong.compareAndSet(j3, BufferedChannelKt.constructSendersAndCloseStatus(j3 & 1152921504606846975L, 1)));
        }
        boolean compareAndSet = this._closeCause.compareAndSet(BufferedChannelKt.NO_CLOSE_CAUSE, th);
        if (z) {
            AtomicLong atomicLong2 = this.sendersAndCloseStatus;
            do {
                j2 = atomicLong2.value;
            } while (!atomicLong2.compareAndSet(j2, BufferedChannelKt.constructSendersAndCloseStatus(j2 & 1152921504606846975L, 3)));
        } else {
            AtomicLong atomicLong3 = this.sendersAndCloseStatus;
            do {
                j = atomicLong3.value;
                int i = (int) (j >> 60);
                if (i != 0) {
                    if (i != 1) {
                        break;
                    }
                    constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(j & 1152921504606846975L, 3);
                } else {
                    constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(j & 1152921504606846975L, 2);
                }
            } while (!atomicLong3.compareAndSet(j, constructSendersAndCloseStatus));
        }
        isClosedForSend();
        if (compareAndSet) {
            AtomicRef atomicRef = this.closeHandler;
            do {
                obj = atomicRef.value;
                if (obj == null) {
                    symbol = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
                } else {
                    symbol = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
                }
            } while (!atomicRef.compareAndSet(obj, symbol));
            if (obj != null) {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity$ar$ds(obj, 1);
                ((Function1) obj).invoke(getCloseCause());
                return true;
            }
        }
        return compareAndSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long j) {
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        ConcurrentLinkedListNode concurrentLinkedListNode = (ConcurrentLinkedListNode) this.receiveSegment.value;
        while (true) {
            AtomicLong atomicLong = this.receivers;
            int i = this.capacity;
            long j2 = atomicLong.value;
            if (j < Math.max(i + j2, getBufferEndCounter())) {
                return;
            }
            if (this.receivers.compareAndSet(j2, 1 + j2)) {
                long j3 = j2 / BufferedChannelKt.SEGMENT_SIZE;
                int i2 = (int) (j2 % BufferedChannelKt.SEGMENT_SIZE);
                if (concurrentLinkedListNode.id != j3) {
                    ConcurrentLinkedListNode findSegmentReceive$ar$class_merging = findSegmentReceive$ar$class_merging(j3, concurrentLinkedListNode);
                    if (findSegmentReceive$ar$class_merging != null) {
                        concurrentLinkedListNode = findSegmentReceive$ar$class_merging;
                    }
                }
                if (updateCellReceive$ar$class_merging(concurrentLinkedListNode, i2, j2, null) == BufferedChannelKt.FAILED) {
                    if (j2 < getSendersCounter$kotlinx_coroutines_core()) {
                        concurrentLinkedListNode.cleanPrev();
                    }
                } else {
                    concurrentLinkedListNode.cleanPrev();
                }
            }
        }
    }

    public final ConcurrentLinkedListNode findSegmentReceive$ar$class_merging(long j, ConcurrentLinkedListNode concurrentLinkedListNode) {
        Object findSegmentInternal$ar$class_merging;
        long j2;
        long j3;
        BufferedChannelKt$createSegmentFunction$1 bufferedChannelKt$createSegmentFunction$1 = BufferedChannelKt$createSegmentFunction$1.INSTANCE;
        loop0: while (true) {
            findSegmentInternal$ar$class_merging = ConcurrentLinkedListKt.findSegmentInternal$ar$class_merging(concurrentLinkedListNode, j, bufferedChannelKt$createSegmentFunction$1);
            if (!SegmentOrClosed.m262isClosedimpl(findSegmentInternal$ar$class_merging)) {
                ConcurrentLinkedListNode m261getSegmentimpl$ar$class_merging = SegmentOrClosed.m261getSegmentimpl$ar$class_merging(findSegmentInternal$ar$class_merging);
                while (true) {
                    AtomicRef atomicRef = this.receiveSegment;
                    ConcurrentLinkedListNode concurrentLinkedListNode2 = (ConcurrentLinkedListNode) atomicRef.value;
                    if (concurrentLinkedListNode2.id >= m261getSegmentimpl$ar$class_merging.id) {
                        break loop0;
                    }
                    if (m261getSegmentimpl$ar$class_merging.tryIncPointers$kotlinx_coroutines_core()) {
                        if (atomicRef.compareAndSet(concurrentLinkedListNode2, m261getSegmentimpl$ar$class_merging)) {
                            if (concurrentLinkedListNode2.decPointers$kotlinx_coroutines_core()) {
                                concurrentLinkedListNode2.remove();
                            }
                        } else if (m261getSegmentimpl$ar$class_merging.decPointers$kotlinx_coroutines_core()) {
                            m261getSegmentimpl$ar$class_merging.remove();
                        }
                    }
                }
            } else {
                break;
            }
        }
        if (SegmentOrClosed.m262isClosedimpl(findSegmentInternal$ar$class_merging)) {
            isClosedForSend();
            if (concurrentLinkedListNode.id * BufferedChannelKt.SEGMENT_SIZE >= getSendersCounter$kotlinx_coroutines_core()) {
                return null;
            }
            concurrentLinkedListNode.cleanPrev();
            return null;
        }
        ConcurrentLinkedListNode m261getSegmentimpl$ar$class_merging2 = SegmentOrClosed.m261getSegmentimpl$ar$class_merging(findSegmentInternal$ar$class_merging);
        if (!isRendezvousOrUnlimited() && j <= getBufferEndCounter() / BufferedChannelKt.SEGMENT_SIZE) {
            AtomicRef atomicRef2 = this.bufferEndSegment;
            while (true) {
                ConcurrentLinkedListNode concurrentLinkedListNode3 = (ConcurrentLinkedListNode) atomicRef2.value;
                if (concurrentLinkedListNode3.id >= m261getSegmentimpl$ar$class_merging2.id || !m261getSegmentimpl$ar$class_merging2.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if (atomicRef2.compareAndSet(concurrentLinkedListNode3, m261getSegmentimpl$ar$class_merging2)) {
                    if (concurrentLinkedListNode3.decPointers$kotlinx_coroutines_core()) {
                        concurrentLinkedListNode3.remove();
                    }
                } else if (m261getSegmentimpl$ar$class_merging2.decPointers$kotlinx_coroutines_core()) {
                    m261getSegmentimpl$ar$class_merging2.remove();
                }
            }
        }
        long j4 = m261getSegmentimpl$ar$class_merging2.id;
        if (j4 > j) {
            long j5 = BufferedChannelKt.SEGMENT_SIZE;
            AtomicLong atomicLong = this.receivers;
            do {
                j2 = j4 * j5;
                j3 = atomicLong.value;
                if (j3 >= j2) {
                    break;
                }
            } while (!this.receivers.compareAndSet(j3, j2));
            if (m261getSegmentimpl$ar$class_merging2.id * BufferedChannelKt.SEGMENT_SIZE >= getSendersCounter$kotlinx_coroutines_core()) {
                return null;
            }
            m261getSegmentimpl$ar$class_merging2.cleanPrev();
            return null;
        }
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        return m261getSegmentimpl$ar$class_merging2;
    }

    public final ConcurrentLinkedListNode findSegmentSend$ar$class_merging(long j, ConcurrentLinkedListNode concurrentLinkedListNode) {
        Object findSegmentInternal$ar$class_merging;
        long j2;
        long j3;
        BufferedChannelKt$createSegmentFunction$1 bufferedChannelKt$createSegmentFunction$1 = BufferedChannelKt$createSegmentFunction$1.INSTANCE;
        loop0: while (true) {
            findSegmentInternal$ar$class_merging = ConcurrentLinkedListKt.findSegmentInternal$ar$class_merging(concurrentLinkedListNode, j, bufferedChannelKt$createSegmentFunction$1);
            if (!SegmentOrClosed.m262isClosedimpl(findSegmentInternal$ar$class_merging)) {
                ConcurrentLinkedListNode m261getSegmentimpl$ar$class_merging = SegmentOrClosed.m261getSegmentimpl$ar$class_merging(findSegmentInternal$ar$class_merging);
                while (true) {
                    AtomicRef atomicRef = this.sendSegment;
                    ConcurrentLinkedListNode concurrentLinkedListNode2 = (ConcurrentLinkedListNode) atomicRef.value;
                    if (concurrentLinkedListNode2.id >= m261getSegmentimpl$ar$class_merging.id) {
                        break loop0;
                    }
                    if (m261getSegmentimpl$ar$class_merging.tryIncPointers$kotlinx_coroutines_core()) {
                        if (atomicRef.compareAndSet(concurrentLinkedListNode2, m261getSegmentimpl$ar$class_merging)) {
                            if (concurrentLinkedListNode2.decPointers$kotlinx_coroutines_core()) {
                                concurrentLinkedListNode2.remove();
                            }
                        } else if (m261getSegmentimpl$ar$class_merging.decPointers$kotlinx_coroutines_core()) {
                            m261getSegmentimpl$ar$class_merging.remove();
                        }
                    }
                }
            } else {
                break;
            }
        }
        if (SegmentOrClosed.m262isClosedimpl(findSegmentInternal$ar$class_merging)) {
            isClosedForSend();
            if (concurrentLinkedListNode.id * BufferedChannelKt.SEGMENT_SIZE >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
            concurrentLinkedListNode.cleanPrev();
            return null;
        }
        ConcurrentLinkedListNode m261getSegmentimpl$ar$class_merging2 = SegmentOrClosed.m261getSegmentimpl$ar$class_merging(findSegmentInternal$ar$class_merging);
        long j4 = m261getSegmentimpl$ar$class_merging2.id;
        if (j4 > j) {
            long j5 = BufferedChannelKt.SEGMENT_SIZE;
            AtomicLong atomicLong = this.sendersAndCloseStatus;
            do {
                long j6 = j4 * j5;
                j2 = atomicLong.value;
                j3 = 1152921504606846975L & j2;
                if (j3 >= j6) {
                    break;
                }
            } while (!this.sendersAndCloseStatus.compareAndSet(j2, BufferedChannelKt.constructSendersAndCloseStatus(j3, (int) (j2 >> 60))));
            if (m261getSegmentimpl$ar$class_merging2.id * BufferedChannelKt.SEGMENT_SIZE >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
            m261getSegmentimpl$ar$class_merging2.cleanPrev();
            return null;
        }
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        return m261getSegmentimpl$ar$class_merging2;
    }

    protected final Throwable getCloseCause() {
        return (Throwable) this._closeCause.value;
    }

    public final Throwable getReceiveException() {
        Throwable closeCause = getCloseCause();
        if (closeCause == null) {
            return new ClosedReceiveChannelException();
        }
        return closeCause;
    }

    public final long getReceiversCounter$kotlinx_coroutines_core() {
        return this.receivers.value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Throwable getSendException() {
        Throwable closeCause = getCloseCause();
        if (closeCause == null) {
            return new ClosedSendChannelException();
        }
        return closeCause;
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        return this.sendersAndCloseStatus.value & 1152921504606846975L;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final void invokeOnClose(Function1 function1) {
        if (this.closeHandler.compareAndSet(null, function1)) {
            return;
        }
        AtomicRef atomicRef = this.closeHandler;
        do {
            Object obj = atomicRef.value;
            if (obj != BufferedChannelKt.CLOSE_HANDLER_CLOSED) {
                if (obj == BufferedChannelKt.CLOSE_HANDLER_INVOKED) {
                    throw new IllegalStateException("Another handler was already registered and successfully invoked");
                }
                Objects.toString(obj);
                throw new IllegalStateException("Another handler is already registered: ".concat(String.valueOf(obj)));
            }
        } while (!this.closeHandler.compareAndSet(BufferedChannelKt.CLOSE_HANDLER_CLOSED, BufferedChannelKt.CLOSE_HANDLER_INVOKED));
        function1.invoke(getCloseCause());
    }

    public final boolean isClosedForReceive() {
        return isClosedForReceive0(this.sendersAndCloseStatus.value);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return isClosedForSend0(this.sendersAndCloseStatus.value);
    }

    public final boolean isClosedForSend0(long j) {
        return isClosed(j, false);
    }

    protected boolean isConflatedDropOldest() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final BufferedChannelIterator iterator$ar$class_merging() {
        return new BufferedChannelIterator();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    public final Object mo254receiveCatchingJP2dKIU(Continuation continuation) {
        return m253receiveCatchingJP2dKIU$suspendImpl(this, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* renamed from: receiveCatchingOnNoWaiterSuspend-GKJJFZk$ar$class_merging, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m255receiveCatchingOnNoWaiterSuspendGKJJFZk$ar$class_merging(kotlinx.coroutines.internal.ConcurrentLinkedListNode r10, int r11, long r12, kotlin.coroutines.Continuation r14) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m255receiveCatchingOnNoWaiterSuspendGKJJFZk$ar$class_merging(kotlinx.coroutines.internal.ConcurrentLinkedListNode, int, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:123:0x01a3, code lost:
    
        if (r0 == kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x003a, code lost:
    
        if (r0 == kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0075, code lost:
    
        if (r0 == kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x01b0, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d8, code lost:
    
        r7 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00da, code lost:
    
        onClosedSendOnNoWaiterSuspend$ar$class_merging(r21, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00dd, code lost:
    
        r1 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x018a, code lost:
    
        if (r0 != kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00e0, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e9, code lost:
    
        r1 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x011a, code lost:
    
        if (r18 >= getReceiversCounter$kotlinx_coroutines_core()) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x011c, code lost:
    
        r22.cleanPrev();
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x011f, code lost:
    
        r1 = r17;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0186  */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v39 */
    @Override // kotlinx.coroutines.channels.SendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object send(java.lang.Object r21, kotlin.coroutines.Continuation r22) {
        /*
            Method dump skipped, instructions count: 433
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.send(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x019a, code lost:
    
        r4 = r4.getNext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x019e, code lost:
    
        if (r4 != null) goto L97;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 474
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.toString():java.lang.String");
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public final Object mo256tryReceivePtdJZtk() {
        long j = this.receivers.value;
        long j2 = this.sendersAndCloseStatus.value;
        if (isClosedForReceive0(j2)) {
            return new ChannelResult.Closed(getCloseCause());
        }
        if (j >= (j2 & 1152921504606846975L)) {
            return ChannelResult.failed;
        }
        AtomicRef atomicRef = this.receiveSegment;
        Symbol symbol = BufferedChannelKt.INTERRUPTED_RCV;
        ConcurrentLinkedListNode concurrentLinkedListNode = (ConcurrentLinkedListNode) atomicRef.value;
        while (!isClosedForReceive()) {
            long andIncrement = this.receivers.getAndIncrement();
            long j3 = BufferedChannelKt.SEGMENT_SIZE;
            long j4 = andIncrement / j3;
            int i = (int) (andIncrement % j3);
            if (concurrentLinkedListNode.id != j4) {
                ConcurrentLinkedListNode findSegmentReceive$ar$class_merging = findSegmentReceive$ar$class_merging(j4, concurrentLinkedListNode);
                if (findSegmentReceive$ar$class_merging != null) {
                    concurrentLinkedListNode = findSegmentReceive$ar$class_merging;
                } else {
                    continue;
                }
            }
            Object updateCellReceive$ar$class_merging = updateCellReceive$ar$class_merging(concurrentLinkedListNode, i, andIncrement, symbol);
            if (updateCellReceive$ar$class_merging == BufferedChannelKt.SUSPEND) {
                waitExpandBufferCompletion$kotlinx_coroutines_core(andIncrement);
                concurrentLinkedListNode.onSlotCleaned();
                return ChannelResult.failed;
            }
            if (updateCellReceive$ar$class_merging == BufferedChannelKt.FAILED) {
                if (andIncrement < getSendersCounter$kotlinx_coroutines_core()) {
                    concurrentLinkedListNode.cleanPrev();
                }
            } else {
                if (updateCellReceive$ar$class_merging != BufferedChannelKt.SUSPEND_NO_WAITER) {
                    concurrentLinkedListNode.cleanPrev();
                    return updateCellReceive$ar$class_merging;
                }
                throw new IllegalStateException("unexpected");
            }
        }
        return new ChannelResult.Closed(getCloseCause());
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU, reason: not valid java name */
    public Object mo257trySendJP2dKIU(Object obj) {
        ConcurrentLinkedListNode concurrentLinkedListNode;
        long j = this.sendersAndCloseStatus.value;
        if (!isClosedForSend0(j) && !bufferOrRendezvousSend(j & 1152921504606846975L)) {
            return ChannelResult.failed;
        }
        AtomicRef atomicRef = this.sendSegment;
        Symbol symbol = BufferedChannelKt.INTERRUPTED_SEND;
        ConcurrentLinkedListNode concurrentLinkedListNode2 = (ConcurrentLinkedListNode) atomicRef.value;
        while (true) {
            long andIncrement = this.sendersAndCloseStatus.getAndIncrement();
            long j2 = andIncrement & 1152921504606846975L;
            boolean isClosedForSend0 = isClosedForSend0(andIncrement);
            long j3 = BufferedChannelKt.SEGMENT_SIZE;
            long j4 = j2 / j3;
            int i = (int) (j2 % j3);
            if (concurrentLinkedListNode2.id != j4) {
                ConcurrentLinkedListNode findSegmentSend$ar$class_merging = findSegmentSend$ar$class_merging(j4, concurrentLinkedListNode2);
                if (findSegmentSend$ar$class_merging == null) {
                    if (isClosedForSend0) {
                        return new ChannelResult.Closed(getSendException());
                    }
                } else {
                    concurrentLinkedListNode = findSegmentSend$ar$class_merging;
                }
            } else {
                concurrentLinkedListNode = concurrentLinkedListNode2;
            }
            int updateCellSend$ar$class_merging = updateCellSend$ar$class_merging(concurrentLinkedListNode, i, obj, j2, symbol, isClosedForSend0);
            if (updateCellSend$ar$class_merging != 0) {
                if (updateCellSend$ar$class_merging != 1) {
                    if (updateCellSend$ar$class_merging != 2) {
                        if (updateCellSend$ar$class_merging != 3) {
                            if (updateCellSend$ar$class_merging != 4) {
                                concurrentLinkedListNode.cleanPrev();
                                concurrentLinkedListNode2 = concurrentLinkedListNode;
                            } else {
                                if (j2 < getReceiversCounter$kotlinx_coroutines_core()) {
                                    concurrentLinkedListNode.cleanPrev();
                                }
                                return new ChannelResult.Closed(getSendException());
                            }
                        } else {
                            throw new IllegalStateException("unexpected");
                        }
                    } else {
                        if (isClosedForSend0) {
                            concurrentLinkedListNode.onSlotCleaned();
                            return new ChannelResult.Closed(getSendException());
                        }
                        concurrentLinkedListNode.onSlotCleaned();
                        return ChannelResult.failed;
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                concurrentLinkedListNode.cleanPrev();
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:?, code lost:
    
        return kotlinx.coroutines.channels.BufferedChannelKt.FAILED;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object updateCellReceive$ar$class_merging(kotlinx.coroutines.internal.ConcurrentLinkedListNode r6, int r7, long r8, java.lang.Object r10) {
        /*
            r5 = this;
            java.lang.Object r0 = r6.getState$kotlinx_coroutines_core(r7)
            r1 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            if (r0 != 0) goto L27
            kotlinx.atomicfu.AtomicLong r0 = r5.sendersAndCloseStatus
            long r3 = r0.value
            long r3 = r3 & r1
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 >= 0) goto L15
            goto L3b
        L15:
            if (r10 != 0) goto L1a
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND_NO_WAITER
            return r6
        L1a:
            r0 = 0
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r0, r10)
            if (r0 == 0) goto L3b
            r5.expandBuffer()
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND
            return r6
        L27:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r0 != r3) goto L3b
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r0, r3)
            if (r0 == 0) goto L3b
            r5.expandBuffer()
            java.lang.Object r6 = r6.retrieveElement$kotlinx_coroutines_core(r7)
            return r6
        L3b:
            java.lang.Object r0 = r6.getState$kotlinx_coroutines_core(r7)
            if (r0 == 0) goto Laa
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            if (r0 != r3) goto L46
            goto Laa
        L46:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r0 != r3) goto L5b
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r0, r3)
            if (r0 == 0) goto L3b
            r5.expandBuffer()
            java.lang.Object r6 = r6.retrieveElement$kotlinx_coroutines_core(r7)
            goto Ld1
        L5b:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            if (r0 != r3) goto L63
        L5f:
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED
            goto Ld1
        L63:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.POISONED
            if (r0 != r3) goto L68
            goto L5f
        L68:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            if (r0 != r3) goto L72
            r5.expandBuffer()
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED
            goto Ld1
        L72:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            if (r0 == r3) goto L3b
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            boolean r3 = r6.casState$kotlinx_coroutines_core(r7, r0, r3)
            if (r3 == 0) goto L3b
            boolean r8 = r0 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r8 == 0) goto L86
            kotlinx.coroutines.channels.WaiterEB r0 = (kotlinx.coroutines.channels.WaiterEB) r0
            kotlinx.coroutines.Waiter r0 = r0.waiter
        L86:
            boolean r9 = tryResumeSender$ar$class_merging$ar$ds(r0)
            if (r9 == 0) goto L99
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            r6.setState$kotlinx_coroutines_core(r7, r8)
            r5.expandBuffer()
            java.lang.Object r6 = r6.retrieveElement$kotlinx_coroutines_core(r7)
            goto Ld1
        L99:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            r6.setState$kotlinx_coroutines_core(r7, r9)
            r9 = 0
            r6.onCancelledRequest(r7, r9)
            if (r8 == 0) goto La7
            r5.expandBuffer()
        La7:
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED
            goto Ld1
        Laa:
            kotlinx.atomicfu.AtomicLong r3 = r5.sendersAndCloseStatus
            long r3 = r3.value
            long r3 = r3 & r1
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 >= 0) goto Lc1
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.POISONED
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r0, r3)
            if (r0 == 0) goto L3b
            r5.expandBuffer()
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED
            goto Ld1
        Lc1:
            if (r10 != 0) goto Lc6
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND_NO_WAITER
            goto Ld1
        Lc6:
            boolean r0 = r6.casState$kotlinx_coroutines_core(r7, r0, r10)
            if (r0 == 0) goto L3b
            r5.expandBuffer()
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND
        Ld1:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.updateCellReceive$ar$class_merging(kotlinx.coroutines.internal.ConcurrentLinkedListNode, int, long, java.lang.Object):java.lang.Object");
    }

    public final int updateCellSend$ar$class_merging(ConcurrentLinkedListNode concurrentLinkedListNode, int i, Object obj, long j, Object obj2, boolean z) {
        concurrentLinkedListNode.setElementLazy(i, obj);
        if (z) {
            return updateCellSendSlow$ar$class_merging(concurrentLinkedListNode, i, obj, j, obj2, true);
        }
        Object state$kotlinx_coroutines_core = concurrentLinkedListNode.getState$kotlinx_coroutines_core(i);
        if (state$kotlinx_coroutines_core == null) {
            if (bufferOrRendezvousSend(j)) {
                if (concurrentLinkedListNode.casState$kotlinx_coroutines_core(i, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (concurrentLinkedListNode.casState$kotlinx_coroutines_core(i, null, obj2)) {
                    return 2;
                }
            }
        } else if (state$kotlinx_coroutines_core instanceof Waiter) {
            concurrentLinkedListNode.cleanElement$kotlinx_coroutines_core(i);
            if (tryResumeReceiver(state$kotlinx_coroutines_core, obj)) {
                concurrentLinkedListNode.setState$kotlinx_coroutines_core(i, BufferedChannelKt.DONE_RCV);
                return 0;
            }
            if (concurrentLinkedListNode.getAndSetState$kotlinx_coroutines_core(i, BufferedChannelKt.INTERRUPTED_RCV) == BufferedChannelKt.INTERRUPTED_RCV) {
                return 5;
            }
            concurrentLinkedListNode.onCancelledRequest(i, true);
            return 5;
        }
        return updateCellSendSlow$ar$class_merging(concurrentLinkedListNode, i, obj, j, obj2, false);
    }

    public final void waitExpandBufferCompletion$kotlinx_coroutines_core(long j) {
        long j2;
        long j3;
        if (isRendezvousOrUnlimited()) {
            return;
        }
        do {
        } while (getBufferEndCounter() <= j);
        int i = BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
        for (int i2 = 0; i2 < i; i2++) {
            long bufferEndCounter = getBufferEndCounter();
            if (bufferEndCounter == (4611686018427387903L & this.completedExpandBuffersAndPauseFlag.value) && bufferEndCounter == getBufferEndCounter()) {
                return;
            }
        }
        AtomicLong atomicLong = this.completedExpandBuffersAndPauseFlag;
        do {
            j2 = atomicLong.value;
        } while (!atomicLong.compareAndSet(j2, BufferedChannelKt.constructEBCompletedAndPauseFlag(j2 & 4611686018427387903L, true)));
        while (true) {
            long bufferEndCounter2 = getBufferEndCounter();
            long j4 = this.completedExpandBuffersAndPauseFlag.value;
            long j5 = j4 & 4611686018427387903L;
            long j6 = 4611686018427387904L & j4;
            if (bufferEndCounter2 == j5 && bufferEndCounter2 == getBufferEndCounter()) {
                break;
            } else if (j6 == 0) {
                this.completedExpandBuffersAndPauseFlag.compareAndSet(j4, BufferedChannelKt.constructEBCompletedAndPauseFlag(j5, true));
            }
        }
        AtomicLong atomicLong2 = this.completedExpandBuffersAndPauseFlag;
        do {
            j3 = atomicLong2.value;
        } while (!atomicLong2.compareAndSet(j3, BufferedChannelKt.constructEBCompletedAndPauseFlag(j3 & 4611686018427387903L, false)));
    }
}

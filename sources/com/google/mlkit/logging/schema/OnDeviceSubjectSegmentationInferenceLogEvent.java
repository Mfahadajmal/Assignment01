package com.google.mlkit.logging.schema;

import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.BlockingCoroutine;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.LazyStandaloneCoroutine;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.ThreadLocalEventLoop;

/* compiled from: PG */
/* loaded from: classes.dex */
public class OnDeviceSubjectSegmentationInferenceLogEvent {
    public static /* synthetic */ Job launch$default$ar$ds$ar$edu(CoroutineScope coroutineScope, CoroutineContext coroutineContext, int i, Function2 function2, int i2) {
        AbstractCoroutine standaloneCoroutine;
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            i = CoroutineStart.DEFAULT$ar$edu$74eab234_0;
        }
        CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
        if (i == CoroutineStart.LAZY$ar$edu) {
            standaloneCoroutine = new LazyStandaloneCoroutine(newCoroutineContext, function2);
        } else {
            standaloneCoroutine = new StandaloneCoroutine(newCoroutineContext, true);
        }
        CoroutineStart.invoke$ar$edu(i, function2, standaloneCoroutine, standaloneCoroutine);
        return standaloneCoroutine;
    }

    public static /* synthetic */ void runBlocking$default$ar$ds(Function2 function2) {
        long j;
        CompletedExceptionally completedExceptionally;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        Thread currentThread = Thread.currentThread();
        ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
        EventLoop eventLoop$kotlinx_coroutines_core$ar$ds = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core$ar$ds();
        BlockingCoroutine blockingCoroutine = new BlockingCoroutine(CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, emptyCoroutineContext.plus(eventLoop$kotlinx_coroutines_core$ar$ds)), currentThread, eventLoop$kotlinx_coroutines_core$ar$ds);
        CoroutineStart.invoke$ar$edu(CoroutineStart.DEFAULT$ar$edu$74eab234_0, function2, blockingCoroutine, blockingCoroutine);
        EventLoop eventLoop = blockingCoroutine.eventLoop;
        if (eventLoop != null) {
            eventLoop.incrementUseCount(false);
        }
        while (!Thread.interrupted()) {
            try {
                EventLoop eventLoop2 = blockingCoroutine.eventLoop;
                if (eventLoop2 != null) {
                    j = eventLoop2.processNextEvent();
                } else {
                    j = Long.MAX_VALUE;
                }
                if (!blockingCoroutine.isCompleted()) {
                    LockSupport.parkNanos(blockingCoroutine, j);
                } else {
                    EventLoop eventLoop3 = blockingCoroutine.eventLoop;
                    if (eventLoop3 != null) {
                        eventLoop3.decrementUseCount(false);
                    }
                    Object unboxState = JobSupportKt.unboxState(blockingCoroutine.getState$kotlinx_coroutines_core());
                    if (unboxState instanceof CompletedExceptionally) {
                        completedExceptionally = (CompletedExceptionally) unboxState;
                    } else {
                        completedExceptionally = null;
                    }
                    if (completedExceptionally == null) {
                        return;
                    } else {
                        throw completedExceptionally.cause;
                    }
                }
            } catch (Throwable th) {
                EventLoop eventLoop4 = blockingCoroutine.eventLoop;
                if (eventLoop4 != null) {
                    eventLoop4.decrementUseCount(false);
                }
                throw th;
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        blockingCoroutine.cancelImpl$kotlinx_coroutines_core(interruptedException);
        throw interruptedException;
    }
}

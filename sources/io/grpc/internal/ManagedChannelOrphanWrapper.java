package io.grpc.internal;

import io.grpc.ManagedChannel;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ManagedChannelOrphanWrapper extends ForwardingManagedChannel {
    private final ManagedChannelReference phantom;
    private static final ReferenceQueue refqueue = new ReferenceQueue();
    private static final ConcurrentMap refs = new ConcurrentHashMap();
    public static final Logger logger = Logger.getLogger(ManagedChannelOrphanWrapper.class.getName());

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ManagedChannelReference extends WeakReference {
        private static final boolean ENABLE_ALLOCATION_TRACKING = Boolean.parseBoolean(System.getProperty("io.grpc.ManagedChannel.enableAllocationTracking", "true"));
        public static final /* synthetic */ int ManagedChannelOrphanWrapper$ManagedChannelReference$ar$NoOp = 0;
        private static final RuntimeException missingCallSite;
        private final Reference allocationSite;
        private final String channelStr;
        private final ReferenceQueue refqueue;
        private final ConcurrentMap refs;
        public final AtomicBoolean shutdown;

        static {
            RuntimeException runtimeException = new RuntimeException("ManagedChannel allocation site not recorded.  Set -Dio.grpc.ManagedChannel.enableAllocationTracking=true to enable it");
            runtimeException.setStackTrace(new StackTraceElement[0]);
            missingCallSite = runtimeException;
        }

        public ManagedChannelReference(ManagedChannelOrphanWrapper managedChannelOrphanWrapper, ManagedChannel managedChannel, ReferenceQueue referenceQueue, ConcurrentMap concurrentMap) {
            super(managedChannelOrphanWrapper, referenceQueue);
            RuntimeException runtimeException;
            this.shutdown = new AtomicBoolean();
            if (ENABLE_ALLOCATION_TRACKING) {
                runtimeException = new RuntimeException("ManagedChannel allocation site");
            } else {
                runtimeException = missingCallSite;
            }
            this.allocationSite = new SoftReference(runtimeException);
            this.channelStr = managedChannel.toString();
            this.refqueue = referenceQueue;
            this.refs = concurrentMap;
            concurrentMap.put(this, this);
            cleanQueue$ar$ds(referenceQueue);
        }

        static void cleanQueue$ar$ds(ReferenceQueue referenceQueue) {
            while (true) {
                ManagedChannelReference managedChannelReference = (ManagedChannelReference) referenceQueue.poll();
                if (managedChannelReference != null) {
                    RuntimeException runtimeException = (RuntimeException) managedChannelReference.allocationSite.get();
                    managedChannelReference.clearInternal();
                    if (!managedChannelReference.shutdown.get()) {
                        Level level = Level.SEVERE;
                        if (ManagedChannelOrphanWrapper.logger.isLoggable(level)) {
                            LogRecord logRecord = new LogRecord(level, "*~*~*~ Previous channel {0} was garbage collected without being shut down! ~*~*~*" + System.getProperty("line.separator") + "    Make sure to call shutdown()/shutdownNow()");
                            logRecord.setLoggerName(ManagedChannelOrphanWrapper.logger.getName());
                            logRecord.setParameters(new Object[]{managedChannelReference.channelStr});
                            logRecord.setThrown(runtimeException);
                            ManagedChannelOrphanWrapper.logger.log(logRecord);
                        }
                    }
                } else {
                    return;
                }
            }
        }

        private final void clearInternal() {
            super.clear();
            this.refs.remove(this);
            this.allocationSite.clear();
        }

        @Override // java.lang.ref.Reference
        public final void clear() {
            clearInternal();
            cleanQueue$ar$ds(this.refqueue);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManagedChannelOrphanWrapper(ManagedChannel managedChannel) {
        super(managedChannel);
        ReferenceQueue referenceQueue = refqueue;
        ConcurrentMap concurrentMap = refs;
        this.phantom = new ManagedChannelReference(this, managedChannel, referenceQueue, concurrentMap);
    }

    @Override // io.grpc.internal.ForwardingManagedChannel, io.grpc.ManagedChannel
    public final void shutdown$ar$ds$17197e6c_0() {
        int i = ManagedChannelReference.ManagedChannelOrphanWrapper$ManagedChannelReference$ar$NoOp;
        ManagedChannelReference managedChannelReference = this.phantom;
        if (!managedChannelReference.shutdown.getAndSet(true)) {
            managedChannelReference.clear();
        }
        ((ManagedChannelImpl) this.delegate).shutdown$ar$ds$17197e6c_0();
    }
}

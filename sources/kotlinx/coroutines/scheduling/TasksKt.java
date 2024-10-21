package kotlinx.coroutines.scheduling;

import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.TextDetectionOptionalModuleLogEvent;
import com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions;
import java.util.concurrent.TimeUnit;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TasksKt {
    public static final TaskContextImpl BlockingContext$ar$class_merging;
    public static final int CORE_POOL_SIZE;
    public static final String DEFAULT_SCHEDULER_NAME;
    public static final long IDLE_WORKER_KEEP_ALIVE_NS;
    public static final int MAX_POOL_SIZE;
    public static final TaskContextImpl NonBlockingContext$ar$class_merging;
    public static final long WORK_STEALING_TIME_RESOLUTION_NS;
    public static final SchedulerTimeSource schedulerTimeSource;

    static {
        long systemProp;
        long systemProp2;
        String systemProp3 = SystemPropsKt__SystemPropsKt.systemProp("kotlinx.coroutines.scheduler.default.name");
        if (systemProp3 == null) {
            systemProp3 = "DefaultDispatcher";
        }
        DEFAULT_SCHEDULER_NAME = systemProp3;
        systemProp = TextDetectionOptionalModuleOptions.systemProp("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 1L, Long.MAX_VALUE);
        WORK_STEALING_TIME_RESOLUTION_NS = systemProp;
        CORE_POOL_SIZE = TextDetectionOptionalModuleLogEvent.systemProp$default$ar$ds("kotlinx.coroutines.scheduler.core.pool.size", OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS, 2), 1, 0, 8);
        MAX_POOL_SIZE = TextDetectionOptionalModuleLogEvent.systemProp$default$ar$ds("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        systemProp2 = TextDetectionOptionalModuleOptions.systemProp("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE);
        IDLE_WORKER_KEEP_ALIVE_NS = timeUnit.toNanos(systemProp2);
        schedulerTimeSource = NanoTimeSource.INSTANCE;
        NonBlockingContext$ar$class_merging = new TaskContextImpl(0);
        BlockingContext$ar$class_merging = new TaskContextImpl(1);
    }
}

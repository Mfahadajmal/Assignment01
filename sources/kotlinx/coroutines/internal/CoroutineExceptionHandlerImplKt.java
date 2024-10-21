package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.Collection;
import java.util.ServiceLoader;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CoroutineExceptionHandlerImplKt {
    public static final Collection platformExceptionHandlers = OnDeviceSmartReplyLogEvent.toList(OnDeviceSmartReplyLogEvent.asSequence(ServiceLoader.load(CoroutineExceptionHandler.class, CoroutineExceptionHandler.class.getClassLoader()).iterator()));

    public static final void propagateExceptionFinalResort(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }
}

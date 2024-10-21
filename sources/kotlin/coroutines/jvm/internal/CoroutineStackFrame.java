package kotlin.coroutines.jvm.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CoroutineStackFrame {
    CoroutineStackFrame getCallerFrame();

    StackTraceElement getStackTraceElement();
}

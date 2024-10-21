package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JobCancellationException extends CancellationException implements CopyableThrowable {
    public final transient Job job;

    public JobCancellationException(String str, Throwable th, Job job) {
        super(str);
        this.job = job;
        if (th != null) {
            initCause(th);
        }
    }

    @Override // kotlinx.coroutines.CopyableThrowable
    public final /* bridge */ /* synthetic */ Throwable createCopy() {
        if (DebugKt.DEBUG) {
            String message = getMessage();
            message.getClass();
            return new JobCancellationException(message, this, this.job);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof JobCancellationException) {
            JobCancellationException jobCancellationException = (JobCancellationException) obj;
            if (Intrinsics.areEqual(jobCancellationException.getMessage(), getMessage()) && Intrinsics.areEqual(jobCancellationException.job, this.job)) {
                if (Intrinsics.areEqual(jobCancellationException.getCause(), getCause())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        if (DebugKt.DEBUG) {
            return super.fillInStackTrace();
        }
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final int hashCode() {
        int i;
        String message = getMessage();
        message.getClass();
        int hashCode = (message.hashCode() * 31) + this.job.hashCode();
        Throwable cause = getCause();
        if (cause != null) {
            i = cause.hashCode();
        } else {
            i = 0;
        }
        return (hashCode * 31) + i;
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return super.toString() + "; job=" + this.job;
    }
}

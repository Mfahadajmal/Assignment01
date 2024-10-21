package kotlin;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Result implements Serializable {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable th) {
            this.exception = th;
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof Failure) && Intrinsics.areEqual(this.exception, ((Failure) obj).exception)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return this.exception.hashCode();
        }

        public final String toString() {
            return "Failure(" + this.exception + ")";
        }
    }

    /* renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m248exceptionOrNullimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }

    /* renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m249isSuccessimpl(Object obj) {
        if (!(obj instanceof Failure)) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        throw null;
    }
}

package kotlinx.coroutines.channels;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.ShadowRemovalOptionalModuleLogEvent;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChannelResult {
    public static final ShadowRemovalOptionalModuleLogEvent Companion$ar$class_merging$a93a83b5_0 = new ShadowRemovalOptionalModuleLogEvent();
    public static final Failed failed = new Failed();
    public final Object holder;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Closed extends Failed {
        public final Throwable cause;

        public Closed(Throwable th) {
            this.cause = th;
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof Closed) && Intrinsics.areEqual(this.cause, ((Closed) obj).cause)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        public final String toString() {
            return "Closed(" + this.cause + ")";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Failed {
        public String toString() {
            return "Failed";
        }
    }

    public /* synthetic */ ChannelResult(Object obj) {
        this.holder = obj;
    }

    /* renamed from: getOrNull-impl, reason: not valid java name */
    public static final Object m258getOrNullimpl(Object obj) {
        if (!(obj instanceof Failed)) {
            return obj;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof ChannelResult) && Intrinsics.areEqual(this.holder, ((ChannelResult) obj).holder)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.holder;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.holder;
        if (obj instanceof Closed) {
            return obj.toString();
        }
        return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(obj, "Value(", ")");
    }
}

package androidx.work.impl.utils;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkRequestCompat {
    public final Object wrapped;

    public NetworkRequestCompat() {
        this((byte[]) null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof NetworkRequestCompat) && Intrinsics.areEqual(this.wrapped, ((NetworkRequestCompat) obj).wrapped)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.wrapped;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "NetworkRequestCompat(wrapped=" + this.wrapped + ')';
    }

    public NetworkRequestCompat(Object obj) {
        this.wrapped = obj;
    }

    public /* synthetic */ NetworkRequestCompat(byte[] bArr) {
        this((Object) null);
    }
}

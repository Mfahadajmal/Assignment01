package kotlinx.coroutines.internal;

import java.util.ArrayList;
import kotlinx.coroutines.DebugKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InlineList {
    /* renamed from: plus-FjFbRPM, reason: not valid java name */
    public static final Object m260plusFjFbRPM(Object obj, Object obj2) {
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
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

package kotlinx.coroutines.flow;

import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StateFlowKt {
    public static final Symbol NONE = new Symbol("NONE");
    public static final Symbol PENDING = new Symbol("PENDING");

    public static final StateFlowImpl MutableStateFlow$ar$class_merging(Object obj) {
        if (obj == null) {
            obj = NullSurrogateKt.NULL;
        }
        return new StateFlowImpl(obj);
    }
}

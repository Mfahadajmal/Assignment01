package j$.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

/* renamed from: j$.util.Comparator$-EL, reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class Comparator$EL {
    public static Comparator reversed(Comparator comparator) {
        return Collections.reverseOrder(comparator);
    }

    public static Comparator thenComparing(Comparator comparator, Function function, Comparator comparator2) {
        Comparator comparing = Comparator$CC.comparing(function, comparator2);
        comparing.getClass();
        return new C0026c(comparator, comparing, 0);
    }

    public static Comparator thenComparingDouble(Comparator comparator, ToDoubleFunction toDoubleFunction) {
        toDoubleFunction.getClass();
        return new C0026c(comparator, new C0025b(1, toDoubleFunction), 0);
    }
}

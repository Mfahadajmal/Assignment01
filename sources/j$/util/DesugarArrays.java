package j$.util;

import j$.util.stream.IntStream;
import j$.util.stream.Stream;

/* loaded from: classes2.dex */
public final /* synthetic */ class DesugarArrays {
    public static IntStream stream(int[] iArr) {
        return j$.util.stream.G.q(Spliterators.f(iArr, 0, iArr.length));
    }

    public static <T> Stream<T> stream(T[] tArr) {
        return j$.util.stream.G.s(Spliterators.g(tArr, 0, tArr.length, 1040), false);
    }
}

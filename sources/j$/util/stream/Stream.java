package j$.util.stream;

import j$.util.DesugarArrays;
import j$.util.Optional;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

/* loaded from: classes2.dex */
public interface Stream<T> extends InterfaceC0056h {
    boolean anyMatch(Predicate<? super T> predicate);

    <R, A> R collect(Collector<? super T, A, R> collector);

    Stream<T> distinct();

    Stream<T> filter(Predicate<? super T> predicate);

    Optional<T> findFirst();

    void forEach(Consumer<? super T> consumer);

    <R> Stream<R> map(Function<? super T, ? extends R> function);

    DoubleStream mapToDouble(ToDoubleFunction<? super T> toDoubleFunction);

    IntStream mapToInt(ToIntFunction<? super T> toIntFunction);

    Optional<T> min(Comparator<? super T> comparator);

    <A> A[] toArray(IntFunction<A[]> intFunction);

    /* renamed from: j$.util.stream.Stream$-CC, reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.stream.b1, java.lang.Object, j$.util.Spliterator] */
        public static <T> Stream<T> of(T t) {
            ?? obj = new Object();
            obj.b = t;
            obj.a = -2;
            return G.s(obj, false);
        }

        public static <T> Stream<T> of(T... tArr) {
            return DesugarArrays.stream(tArr);
        }
    }
}

package j$.util.stream;

import j$.util.Map;
import j$.util.stream.Collector;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public final class Collectors {
    static final Set a;
    static final Set b;
    static final Set c;
    static final Set d;

    static {
        Collector.Characteristics characteristics = Collector.Characteristics.CONCURRENT;
        Collector.Characteristics characteristics2 = Collector.Characteristics.UNORDERED;
        Collector.Characteristics characteristics3 = Collector.Characteristics.IDENTITY_FINISH;
        Collections.unmodifiableSet(EnumSet.of(characteristics, characteristics2, characteristics3));
        Collections.unmodifiableSet(EnumSet.of(characteristics, characteristics2));
        a = Collections.unmodifiableSet(EnumSet.of(characteristics3));
        b = Collections.unmodifiableSet(EnumSet.of(characteristics2, characteristics3));
        c = Collections.emptySet();
        d = Collections.unmodifiableSet(EnumSet.of(characteristics2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Function function, Function function2, Map map, Object obj) {
        Object apply = function.apply(obj);
        Object apply2 = function2.apply(obj);
        apply2.getClass();
        Object b2 = Map.EL.b(map, apply, apply2);
        if (b2 == null) {
        } else {
            throw new IllegalStateException(String.format("Duplicate key %s (attempted merging values %s and %s)", apply, b2, apply2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(java.util.Map map, java.util.Map map2) {
        for (Map.Entry entry : map2.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            value.getClass();
            Object b2 = Map.EL.b(map, key, value);
            if (b2 != null) {
                throw new IllegalStateException(String.format("Duplicate key %s (attempted merging values %s and %s)", key, b2, value));
            }
        }
    }

    public static Collector<CharSequence, ?, String> joining() {
        return new C0062k(new C0058i(24), new C0058i(25), new C0058i(7), new C0058i(26), c);
    }

    public static <T, C extends Collection<T>> Collector<T, ?, C> toCollection(Supplier<C> supplier) {
        return new C0062k(supplier, new C0058i(15), new C0058i(1), a);
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new C0062k(new C0058i(16), new C0058i(17), new C0058i(4), a);
    }

    public static <T, K, U> Collector<T, ?, java.util.Map<K, U>> toMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2) {
        return new C0062k(new C0058i(23), new C0060j(0, function, function2), new C0058i(0), a);
    }

    public static <T> Collector<T, ?, Set<T>> toSet() {
        return new C0062k(new C0058i(18), new C0058i(19), new C0058i(6), b);
    }

    public static <T> Collector<T, ?, Set<T>> toUnmodifiableSet() {
        return new C0062k(new C0058i(18), new C0058i(19), new C0058i(2), new C0058i(3), d);
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence charSequence) {
        return new C0062k(new C0042a(1, charSequence), new C0058i(20), new C0058i(21), new C0058i(22), c);
    }
}

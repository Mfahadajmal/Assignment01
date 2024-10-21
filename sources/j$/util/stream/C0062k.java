package j$.util.stream;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.stream.k, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0062k implements Collector {
    private final Supplier a;
    private final BiConsumer b;
    private final BinaryOperator c;
    private final Function d;
    private final Set e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0062k(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator, Set set) {
        this(supplier, biConsumer, binaryOperator, new C0058i(5), set);
        Set set2 = Collectors.a;
    }

    @Override // j$.util.stream.Collector
    public final Set a() {
        return this.e;
    }

    @Override // j$.util.stream.Collector
    public final BiConsumer b() {
        return this.b;
    }

    @Override // j$.util.stream.Collector
    public final BinaryOperator c() {
        return this.c;
    }

    @Override // j$.util.stream.Collector
    public final Supplier d() {
        return this.a;
    }

    @Override // j$.util.stream.Collector
    public final Function e() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0062k(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator, Function function, Set set) {
        this.a = supplier;
        this.b = biConsumer;
        this.c = binaryOperator;
        this.d = function;
        this.e = set;
    }
}

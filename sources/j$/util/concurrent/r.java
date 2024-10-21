package j$.util.concurrent;

import j$.util.Collection;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* loaded from: classes2.dex */
final class r extends b implements Collection {
    private static final long serialVersionUID = 2249069246763182397L;

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(java.util.Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.util.concurrent.b, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.a.containsValue(obj);
    }

    @Override // java.lang.Iterable, j$.util.Collection
    public final void forEach(Consumer consumer) {
        consumer.getClass();
        k[] kVarArr = this.a.a;
        if (kVarArr == null) {
            return;
        }
        o oVar = new o(kVarArr, kVarArr.length, 0, kVarArr.length);
        while (true) {
            k c = oVar.c();
            if (c == null) {
                return;
            } else {
                consumer.accept(c.c);
            }
        }
    }

    @Override // j$.util.concurrent.b, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        int length;
        ConcurrentHashMap concurrentHashMap = this.a;
        k[] kVarArr = concurrentHashMap.a;
        if (kVarArr == null) {
            length = 0;
        } else {
            length = kVarArr.length;
        }
        return new h(kVarArr, length, length, concurrentHashMap, 1);
    }

    @Override // java.util.Collection, j$.util.Collection
    public final /* synthetic */ Stream parallelStream() {
        return Collection.CC.$default$parallelStream(this);
    }

    @Override // j$.util.concurrent.b, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        AbstractC0027a abstractC0027a;
        if (obj == null) {
            return false;
        }
        Object it = iterator();
        do {
            abstractC0027a = (AbstractC0027a) it;
            if (!abstractC0027a.hasNext()) {
                return false;
            }
        } while (!obj.equals(((h) it).next()));
        abstractC0027a.remove();
        return true;
    }

    @Override // j$.util.concurrent.b, java.util.Collection
    public final boolean removeAll(java.util.Collection collection) {
        collection.getClass();
        Object it = iterator();
        boolean z = false;
        while (true) {
            AbstractC0027a abstractC0027a = (AbstractC0027a) it;
            if (!abstractC0027a.hasNext()) {
                return z;
            }
            if (collection.contains(((h) it).next())) {
                abstractC0027a.remove();
                z = true;
            }
        }
    }

    @Override // java.util.Collection, j$.util.Collection
    public final boolean removeIf(Predicate predicate) {
        ConcurrentHashMap concurrentHashMap = this.a;
        concurrentHashMap.getClass();
        predicate.getClass();
        k[] kVarArr = concurrentHashMap.a;
        boolean z = false;
        if (kVarArr != null) {
            o oVar = new o(kVarArr, kVarArr.length, 0, kVarArr.length);
            while (true) {
                k c = oVar.c();
                if (c == null) {
                    break;
                }
                Object obj = c.b;
                Object obj2 = c.c;
                if (predicate.test(obj2) && concurrentHashMap.g(obj, null, obj2) != null) {
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection
    public final Spliterator spliterator() {
        ConcurrentHashMap concurrentHashMap = this.a;
        long j = concurrentHashMap.j();
        k[] kVarArr = concurrentHashMap.a;
        int length = kVarArr == null ? 0 : kVarArr.length;
        return new i(kVarArr, length, 0, length, j < 0 ? 0L : j, 1);
    }

    @Override // java.util.Collection, j$.util.Collection
    public final /* synthetic */ Stream stream() {
        return Collection.CC.$default$stream(this);
    }

    @Override // java.util.Collection, j$.util.Collection
    public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
        Object[] array;
        array = toArray((Object[]) intFunction.apply(0));
        return array;
    }
}

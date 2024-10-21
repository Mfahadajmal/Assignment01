package j$.util.stream;

import j$.util.Collection;
import j$.util.Spliterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: classes2.dex */
final class N implements K {
    private final Collection a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public N(Collection collection) {
        this.a = collection;
    }

    @Override // j$.util.stream.K
    public final K a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.K
    public final long f() {
        return this.a.size();
    }

    @Override // j$.util.stream.K
    public final void forEach(Consumer consumer) {
        Collection.EL.forEach(this.a, consumer);
    }

    @Override // j$.util.stream.K
    public final Object[] i(IntFunction intFunction) {
        java.util.Collection collection = this.a;
        return collection.toArray((Object[]) intFunction.apply(collection.size()));
    }

    @Override // j$.util.stream.K
    public final void k(Object[] objArr, int i) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ int m() {
        return 0;
    }

    @Override // j$.util.stream.K
    public final Spliterator spliterator() {
        return Collection.EL.stream(this.a).spliterator();
    }

    public final String toString() {
        java.util.Collection collection = this.a;
        return String.format("CollectionNode[%d][%s]", Integer.valueOf(collection.size()), collection);
    }
}

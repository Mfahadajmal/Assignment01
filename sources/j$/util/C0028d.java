package j$.util;

import j$.util.Collection;
import j$.util.stream.Stream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public class C0028d implements java.util.Collection, Serializable, Collection {
    private static final long serialVersionUID = 3053995032091335093L;
    final java.util.Collection a;
    final Serializable b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0028d(java.util.Collection collection) {
        collection.getClass();
        this.a = collection;
        this.b = this;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        synchronized (this.b) {
            objectOutputStream.defaultWriteObject();
        }
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        boolean add;
        synchronized (this.b) {
            add = this.a.add(obj);
        }
        return add;
    }

    @Override // java.util.Collection
    public final boolean addAll(java.util.Collection collection) {
        boolean addAll;
        synchronized (this.b) {
            addAll = this.a.addAll(collection);
        }
        return addAll;
    }

    @Override // java.util.Collection
    public final void clear() {
        synchronized (this.b) {
            this.a.clear();
        }
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        boolean contains;
        synchronized (this.b) {
            contains = this.a.contains(obj);
        }
        return contains;
    }

    @Override // java.util.Collection
    public final boolean containsAll(java.util.Collection collection) {
        boolean containsAll;
        synchronized (this.b) {
            containsAll = this.a.containsAll(collection);
        }
        return containsAll;
    }

    @Override // java.lang.Iterable, j$.util.Collection
    public final void forEach(Consumer consumer) {
        synchronized (this.b) {
            Collection.EL.forEach(this.a, consumer);
        }
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.b) {
            isEmpty = this.a.isEmpty();
        }
        return isEmpty;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.a.iterator();
    }

    @Override // java.util.Collection, j$.util.Collection
    public final Stream parallelStream() {
        return Collection.EL.a(this.a);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        boolean remove;
        synchronized (this.b) {
            remove = this.a.remove(obj);
        }
        return remove;
    }

    @Override // java.util.Collection
    public final boolean removeAll(java.util.Collection collection) {
        boolean removeAll;
        synchronized (this.b) {
            removeAll = this.a.removeAll(collection);
        }
        return removeAll;
    }

    @Override // java.util.Collection, j$.util.Collection
    public final boolean removeIf(Predicate predicate) {
        boolean $default$removeIf;
        synchronized (this.b) {
            java.util.Collection collection = this.a;
            if (collection instanceof Collection) {
                $default$removeIf = ((Collection) collection).removeIf(predicate);
            } else {
                $default$removeIf = Collection.CC.$default$removeIf(collection, predicate);
            }
        }
        return $default$removeIf;
    }

    @Override // java.util.Collection
    public final boolean retainAll(java.util.Collection collection) {
        boolean retainAll;
        synchronized (this.b) {
            retainAll = this.a.retainAll(collection);
        }
        return retainAll;
    }

    @Override // java.util.Collection
    public final int size() {
        int size;
        synchronized (this.b) {
            size = this.a.size();
        }
        return size;
    }

    @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection
    public final Spliterator spliterator() {
        return Collection.EL.b(this.a);
    }

    @Override // java.util.Collection, j$.util.Collection
    public final Stream stream() {
        return Collection.EL.stream(this.a);
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        Object[] array;
        synchronized (this.b) {
            array = this.a.toArray();
        }
        return array;
    }

    public final String toString() {
        String obj;
        synchronized (this.b) {
            obj = this.a.toString();
        }
        return obj;
    }

    @Override // java.util.Collection, j$.util.Collection
    public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
        Object[] array;
        array = toArray((Object[]) intFunction.apply(0));
        return array;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0028d(java.util.Collection collection, Serializable serializable) {
        collection.getClass();
        this.a = collection;
        serializable.getClass();
        this.b = serializable;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        Object[] array;
        synchronized (this.b) {
            array = this.a.toArray(objArr);
        }
        return array;
    }
}

package j$.util;

import j$.util.List;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.UnaryOperator;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public class C0029e extends C0028d implements java.util.List, List {
    private static final long serialVersionUID = -7754090372962971524L;
    final java.util.List c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0029e(java.util.List list) {
        super(list);
        this.c = list;
    }

    private Object readResolve() {
        java.util.List list = this.c;
        if (list instanceof RandomAccess) {
            return new C0029e(list);
        }
        return this;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        synchronized (this.b) {
            this.c.add(i, obj);
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i, java.util.Collection collection) {
        boolean addAll;
        synchronized (this.b) {
            addAll = this.c.addAll(i, collection);
        }
        return addAll;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        boolean equals;
        if (this == obj) {
            return true;
        }
        synchronized (this.b) {
            equals = this.c.equals(obj);
        }
        return equals;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Object obj;
        synchronized (this.b) {
            obj = this.c.get(i);
        }
        return obj;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int hashCode;
        synchronized (this.b) {
            hashCode = this.c.hashCode();
        }
        return hashCode;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        int indexOf;
        synchronized (this.b) {
            indexOf = this.c.indexOf(obj);
        }
        return indexOf;
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        int lastIndexOf;
        synchronized (this.b) {
            lastIndexOf = this.c.lastIndexOf(obj);
        }
        return lastIndexOf;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return this.c.listIterator();
    }

    @Override // java.util.List
    public final Object remove(int i) {
        Object remove;
        synchronized (this.b) {
            remove = this.c.remove(i);
        }
        return remove;
    }

    @Override // java.util.List, j$.util.List
    public final void replaceAll(UnaryOperator unaryOperator) {
        synchronized (this.b) {
            java.util.List list = this.c;
            if (list instanceof List) {
                ((List) list).replaceAll(unaryOperator);
            } else {
                List.CC.$default$replaceAll(list, unaryOperator);
            }
        }
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        Object obj2;
        synchronized (this.b) {
            obj2 = this.c.set(i, obj);
        }
        return obj2;
    }

    @Override // java.util.List, j$.util.List
    public final void sort(Comparator comparator) {
        synchronized (this.b) {
            List.EL.sort(this.c, comparator);
        }
    }

    @Override // java.util.List
    public java.util.List subList(int i, int i2) {
        C0029e c0029e;
        synchronized (this.b) {
            c0029e = new C0029e(this.c.subList(i, i2), this.b);
        }
        return c0029e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0029e(java.util.List list, Serializable serializable) {
        super(list, serializable);
        this.c = list;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        return this.c.listIterator(i);
    }
}

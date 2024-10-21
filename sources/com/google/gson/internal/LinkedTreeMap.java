package com.google.gson.internal;

import android.support.v7.widget.GapWorker;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LinkedTreeMap extends AbstractMap implements Serializable {
    public static final Comparator NATURAL_ORDER = new GapWorker.AnonymousClass1(11);
    private final boolean allowNullValues;
    private final Comparator comparator;
    private EntrySet entrySet;
    final Node header;
    private KeySet keySet;
    int modCount;
    Node root;
    int size;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EntrySet extends AbstractSet {

        /* compiled from: PG */
        /* renamed from: com.google.gson.internal.LinkedTreeMap$EntrySet$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 extends LinkedTreeMapIterator {
            public AnonymousClass1(EntrySet entrySet) {
                super();
            }

            @Override // java.util.Iterator
            public final /* synthetic */ Object next() {
                return nextNode();
            }
        }

        public EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            LinkedTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            if ((obj instanceof Map.Entry) && LinkedTreeMap.this.findByEntry((Map.Entry) obj) != null) {
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator iterator() {
            return new AnonymousClass1(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            Node findByEntry;
            if ((obj instanceof Map.Entry) && (findByEntry = LinkedTreeMap.this.findByEntry((Map.Entry) obj)) != null) {
                LinkedTreeMap.this.removeInternal(findByEntry, true);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return LinkedTreeMap.this.size;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class KeySet extends AbstractSet {
        public KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            LinkedTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            return LinkedTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator iterator() {
            return new LinkedTreeMapIterator(this) { // from class: com.google.gson.internal.LinkedTreeMap.KeySet.1
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                @Override // java.util.Iterator
                public final Object next() {
                    return nextNode().key;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            if (LinkedTreeMap.this.removeInternalByKey(obj) != null) {
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return LinkedTreeMap.this.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class LinkedTreeMapIterator implements Iterator {
        int expectedModCount;
        Node lastReturned = null;
        Node next;

        public LinkedTreeMapIterator() {
            this.next = LinkedTreeMap.this.header.next;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.next != LinkedTreeMap.this.header) {
                return true;
            }
            return false;
        }

        public final Node nextNode() {
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            Node node = this.next;
            if (node != linkedTreeMap.header) {
                if (linkedTreeMap.modCount == this.expectedModCount) {
                    this.next = node.next;
                    this.lastReturned = node;
                    return node;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            Node node = this.lastReturned;
            if (node != null) {
                LinkedTreeMap.this.removeInternal(node, true);
                this.lastReturned = null;
                this.expectedModCount = LinkedTreeMap.this.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Node implements Map.Entry {
        final boolean allowNullValue;
        int height;
        final Object key;
        Node left;
        Node next;
        Node parent;
        Node prev;
        Node right;
        Object value;

        public Node(boolean z) {
            this.key = null;
            this.allowNullValue = z;
            this.prev = this;
            this.next = this;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object obj2 = this.key;
                if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                    Object obj3 = this.value;
                    if (obj3 == null) {
                        if (entry.getValue() == null) {
                            return true;
                        }
                    } else if (obj3.equals(entry.getValue())) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            int hashCode;
            Object obj = this.key;
            int i = 0;
            if (obj == null) {
                hashCode = 0;
            } else {
                hashCode = obj.hashCode();
            }
            Object obj2 = this.value;
            if (obj2 != null) {
                i = obj2.hashCode();
            }
            return hashCode ^ i;
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            if (obj == null && !this.allowNullValue) {
                throw new NullPointerException("value == null");
            }
            Object obj2 = this.value;
            this.value = obj;
            return obj2;
        }

        public final String toString() {
            return String.valueOf(this.key) + "=" + String.valueOf(this.value);
        }

        public Node(boolean z, Node node, Object obj, Node node2, Node node3) {
            this.parent = node;
            this.key = obj;
            this.allowNullValue = z;
            this.height = 1;
            this.next = node2;
            this.prev = node3;
            node3.next = this;
            node2.prev = this;
        }
    }

    public LinkedTreeMap() {
        this(NATURAL_ORDER, true);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization is unsupported");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002f, code lost:
    
        if (r10 == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x005a, code lost:
    
        if (r10 == false) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0080 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0080 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void rebalance(com.google.gson.internal.LinkedTreeMap.Node r9, boolean r10) {
        /*
            r8 = this;
        L0:
            if (r9 == 0) goto L84
            com.google.gson.internal.LinkedTreeMap$Node r0 = r9.left
            com.google.gson.internal.LinkedTreeMap$Node r1 = r9.right
            r2 = 0
            if (r0 == 0) goto Lc
            int r3 = r0.height
            goto Ld
        Lc:
            r3 = r2
        Ld:
            if (r1 == 0) goto L12
            int r4 = r1.height
            goto L13
        L12:
            r4 = r2
        L13:
            int r5 = r3 - r4
            r6 = -2
            r7 = 1
            if (r5 != r6) goto L42
            com.google.gson.internal.LinkedTreeMap$Node r0 = r1.left
            com.google.gson.internal.LinkedTreeMap$Node r3 = r1.right
            if (r3 == 0) goto L22
            int r3 = r3.height
            goto L23
        L22:
            r3 = r2
        L23:
            if (r0 == 0) goto L28
            int r0 = r0.height
            goto L29
        L28:
            r0 = r2
        L29:
            int r0 = r0 - r3
            r3 = -1
            if (r0 == r3) goto L3a
            if (r0 != 0) goto L32
            if (r10 != 0) goto L33
            goto L3b
        L32:
            r7 = r10
        L33:
            r8.rotateRight(r1)
            r8.rotateLeft(r9)
            goto L3f
        L3a:
            r2 = r10
        L3b:
            r8.rotateLeft(r9)
            r7 = r2
        L3f:
            if (r7 != 0) goto L84
            goto L80
        L42:
            r1 = 2
            if (r5 != r1) goto L6d
            com.google.gson.internal.LinkedTreeMap$Node r1 = r0.left
            com.google.gson.internal.LinkedTreeMap$Node r3 = r0.right
            if (r3 == 0) goto L4e
            int r3 = r3.height
            goto L4f
        L4e:
            r3 = r2
        L4f:
            if (r1 == 0) goto L54
            int r1 = r1.height
            goto L55
        L54:
            r1 = r2
        L55:
            int r1 = r1 - r3
            if (r1 == r7) goto L65
            if (r1 != 0) goto L5d
            if (r10 != 0) goto L5e
            goto L66
        L5d:
            r7 = r10
        L5e:
            r8.rotateLeft(r0)
            r8.rotateRight(r9)
            goto L6a
        L65:
            r2 = r10
        L66:
            r8.rotateRight(r9)
            r7 = r2
        L6a:
            if (r7 == 0) goto L80
            goto L84
        L6d:
            if (r5 != 0) goto L76
            int r3 = r3 + 1
            r9.height = r3
            if (r10 == 0) goto L80
            goto L84
        L76:
            int r0 = java.lang.Math.max(r3, r4)
            int r0 = r0 + r7
            r9.height = r0
            if (r10 != 0) goto L80
            goto L84
        L80:
            com.google.gson.internal.LinkedTreeMap$Node r9 = r9.parent
            goto L0
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedTreeMap.rebalance(com.google.gson.internal.LinkedTreeMap$Node, boolean):void");
    }

    private final void replaceInParent(Node node, Node node2) {
        Node node3 = node.parent;
        node.parent = null;
        if (node2 != null) {
            node2.parent = node3;
        }
        if (node3 != null) {
            if (node3.left == node) {
                node3.left = node2;
                return;
            } else {
                node3.right = node2;
                return;
            }
        }
        this.root = node2;
    }

    private final void rotateLeft(Node node) {
        int i;
        int i2;
        Node node2 = node.left;
        Node node3 = node.right;
        Node node4 = node3.left;
        Node node5 = node3.right;
        node.right = node4;
        if (node4 != null) {
            node4.parent = node;
        }
        replaceInParent(node, node3);
        node3.left = node;
        node.parent = node3;
        int i3 = 0;
        if (node2 != null) {
            i = node2.height;
        } else {
            i = 0;
        }
        if (node4 != null) {
            i2 = node4.height;
        } else {
            i2 = 0;
        }
        int max = Math.max(i, i2) + 1;
        node.height = max;
        if (node5 != null) {
            i3 = node5.height;
        }
        node3.height = Math.max(max, i3) + 1;
    }

    private final void rotateRight(Node node) {
        int i;
        int i2;
        Node node2 = node.left;
        Node node3 = node.right;
        Node node4 = node2.left;
        Node node5 = node2.right;
        node.left = node5;
        if (node5 != null) {
            node5.parent = node;
        }
        replaceInParent(node, node2);
        node2.right = node;
        node.parent = node2;
        int i3 = 0;
        if (node3 != null) {
            i = node3.height;
        } else {
            i = 0;
        }
        if (node5 != null) {
            i2 = node5.height;
        } else {
            i2 = 0;
        }
        int max = Math.max(i, i2) + 1;
        node.height = max;
        if (node4 != null) {
            i3 = node4.height;
        }
        node2.height = Math.max(max, i3) + 1;
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        Node node = this.header;
        node.prev = node;
        node.next = node;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        if (findByObject(obj) != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        EntrySet entrySet = this.entrySet;
        if (entrySet != null) {
            return entrySet;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    final Node find(Object obj, boolean z) {
        int i;
        Node node;
        Comparable comparable;
        Node node2;
        Comparator comparator = this.comparator;
        Node node3 = this.root;
        if (node3 != null) {
            if (comparator == NATURAL_ORDER) {
                comparable = (Comparable) obj;
            } else {
                comparable = null;
            }
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(node3.key);
                } else {
                    i = comparator.compare(obj, node3.key);
                }
                if (i == 0) {
                    return node3;
                }
                if (i < 0) {
                    node2 = node3.left;
                } else {
                    node2 = node3.right;
                }
                if (node2 == null) {
                    break;
                }
                node3 = node2;
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        Node node4 = this.header;
        if (node3 == null) {
            if (comparator == NATURAL_ORDER && !(obj instanceof Comparable)) {
                throw new ClassCastException(String.valueOf(obj.getClass().getName()).concat(" is not Comparable"));
            }
            node = new Node(this.allowNullValues, null, obj, node4, node4.prev);
            this.root = node;
        } else {
            node = new Node(this.allowNullValues, node3, obj, node4, node4.prev);
            if (i < 0) {
                node3.left = node;
            } else {
                node3.right = node;
            }
            rebalance(node3, true);
        }
        this.size++;
        this.modCount++;
        return node;
    }

    final Node findByEntry(Map.Entry entry) {
        Node findByObject = findByObject(entry.getKey());
        if (findByObject != null && Objects.equals(findByObject.value, entry.getValue())) {
            return findByObject;
        }
        return null;
    }

    final Node findByObject(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return find(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Node findByObject = findByObject(obj);
        if (findByObject != null) {
            return findByObject.value;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        KeySet keySet = this.keySet;
        if (keySet != null) {
            return keySet;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj != null) {
            if (obj2 == null && !this.allowNullValues) {
                throw new NullPointerException("value == null");
            }
            Node find = find(obj, true);
            Object obj3 = find.value;
            find.value = obj2;
            return obj3;
        }
        throw new NullPointerException("key == null");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Node removeInternalByKey = removeInternalByKey(obj);
        if (removeInternalByKey != null) {
            return removeInternalByKey.value;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:
    
        removeInternal(r0, false);
        r8 = r7.left;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0034, code lost:
    
        if (r8 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
    
        r1 = r8.height;
        r0.left = r8;
        r8.parent = r0;
        r7.left = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        r8 = r7.right;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
    
        if (r8 == null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
    
        r2 = r8.height;
        r0.right = r8;
        r8.parent = r0;
        r7.right = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
    
        r0.height = java.lang.Math.max(r1, r2) + 1;
        replaceInParent(r7, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003f, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0026, code lost:
    
        r5 = r0;
        r0 = r0.left;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x002b, code lost:
    
        if (r0 == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x002e, code lost:
    
        r0 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001c, code lost:
    
        if (r8.height > r0.height) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        r0 = r8;
        r8 = r8.right;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0023, code lost:
    
        if (r8 == null) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void removeInternal(com.google.gson.internal.LinkedTreeMap.Node r7, boolean r8) {
        /*
            r6 = this;
            if (r8 == 0) goto Lc
            com.google.gson.internal.LinkedTreeMap$Node r8 = r7.prev
            com.google.gson.internal.LinkedTreeMap$Node r0 = r7.next
            r8.next = r0
            com.google.gson.internal.LinkedTreeMap$Node r0 = r7.next
            r0.prev = r8
        Lc:
            com.google.gson.internal.LinkedTreeMap$Node r8 = r7.left
            com.google.gson.internal.LinkedTreeMap$Node r0 = r7.right
            com.google.gson.internal.LinkedTreeMap$Node r1 = r7.parent
            r2 = 0
            r3 = 0
            if (r8 == 0) goto L58
            if (r0 == 0) goto L58
            int r1 = r8.height
            int r4 = r0.height
            if (r1 <= r4) goto L26
        L1e:
            com.google.gson.internal.LinkedTreeMap$Node r0 = r8.right
            r5 = r0
            r0 = r8
            r8 = r5
            if (r8 == 0) goto L2f
            goto L1e
        L26:
            com.google.gson.internal.LinkedTreeMap$Node r8 = r0.left
            r5 = r0
            r0 = r8
            r8 = r5
            if (r0 == 0) goto L2e
            goto L26
        L2e:
            r0 = r8
        L2f:
            r6.removeInternal(r0, r2)
            com.google.gson.internal.LinkedTreeMap$Node r8 = r7.left
            if (r8 == 0) goto L3f
            int r1 = r8.height
            r0.left = r8
            r8.parent = r0
            r7.left = r3
            goto L40
        L3f:
            r1 = r2
        L40:
            com.google.gson.internal.LinkedTreeMap$Node r8 = r7.right
            if (r8 == 0) goto L4c
            int r2 = r8.height
            r0.right = r8
            r8.parent = r0
            r7.right = r3
        L4c:
            int r8 = java.lang.Math.max(r1, r2)
            int r8 = r8 + 1
            r0.height = r8
            r6.replaceInParent(r7, r0)
            return
        L58:
            if (r8 == 0) goto L60
            r6.replaceInParent(r7, r8)
            r7.left = r3
            goto L6b
        L60:
            if (r0 == 0) goto L68
            r6.replaceInParent(r7, r0)
            r7.right = r3
            goto L6b
        L68:
            r6.replaceInParent(r7, r3)
        L6b:
            r6.rebalance(r1, r2)
            int r7 = r6.size
            int r7 = r7 + (-1)
            r6.size = r7
            int r7 = r6.modCount
            int r7 = r7 + 1
            r6.modCount = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedTreeMap.removeInternal(com.google.gson.internal.LinkedTreeMap$Node, boolean):void");
    }

    final Node removeInternalByKey(Object obj) {
        Node findByObject = findByObject(obj);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.size;
    }

    public LinkedTreeMap(Comparator comparator, boolean z) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = comparator;
        this.allowNullValues = z;
        this.header = new Node(z);
    }
}

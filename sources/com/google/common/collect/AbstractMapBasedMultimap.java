package com.google.common.collect;

import com.google.common.flogger.context.ContextDataProvider;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractMapBasedMultimap extends AbstractMultimap implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    public transient Map map;
    public transient int totalSize;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AsMap extends Maps$ViewCachingAbstractMap {
        final transient Map submap;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class AsMapEntries extends Maps$EntrySet {
            public AsMapEntries() {
            }

            @Override // com.google.common.collect.Maps$EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public final boolean contains(Object obj) {
                Set entrySet = AsMap.this.submap.entrySet();
                entrySet.getClass();
                try {
                    return entrySet.contains(obj);
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public final Iterator iterator() {
                return new AsMapIterator();
            }

            @Override // com.google.common.collect.Maps$EntrySet
            public final Map map() {
                return AsMap.this;
            }

            @Override // com.google.common.collect.Maps$EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public final boolean remove(Object obj) {
                Object obj2;
                if (!contains(obj)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                entry.getClass();
                AsMap asMap = AsMap.this;
                Object key = entry.getKey();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                Map map = abstractMapBasedMultimap.map;
                map.getClass();
                try {
                    obj2 = map.remove(key);
                } catch (ClassCastException | NullPointerException unused) {
                    obj2 = null;
                }
                Collection collection = (Collection) obj2;
                if (collection != null) {
                    int size = collection.size();
                    collection.clear();
                    abstractMapBasedMultimap.totalSize -= size;
                    return true;
                }
                return true;
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class AsMapIterator implements Iterator {
            Collection collection;
            final Iterator delegateIterator;

            public AsMapIterator() {
                this.delegateIterator = AsMap.this.submap.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public final /* bridge */ /* synthetic */ Object next() {
                Map.Entry entry = (Map.Entry) this.delegateIterator.next();
                this.collection = (Collection) entry.getValue();
                Object key = entry.getKey();
                return new ImmutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, (Collection) entry.getValue()));
            }

            @Override // java.util.Iterator
            public final void remove() {
                boolean z;
                if (this.collection != null) {
                    z = true;
                } else {
                    z = false;
                }
                ContextDataProvider.checkState(z, "no calls to next() since the last call to remove()");
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.this.totalSize -= this.collection.size();
                this.collection.clear();
                this.collection = null;
            }
        }

        public AsMap(Map map) {
            this.submap = map;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final void clear() {
            Map map = this.submap;
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            if (map == abstractMapBasedMultimap.map) {
                abstractMapBasedMultimap.clear();
            } else {
                ContextDataProvider.clear(new AsMapIterator());
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final boolean containsKey(Object obj) {
            Map map = this.submap;
            map.getClass();
            try {
                return map.containsKey(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }

        @Override // com.google.common.collect.Maps$ViewCachingAbstractMap
        public final Set createEntrySet() {
            return new AsMapEntries();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final boolean equals(Object obj) {
            if (this != obj && !this.submap.equals(obj)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            Collection collection = (Collection) ContextDataProvider.safeGet(this.submap, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.wrapCollection(obj, collection);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final int hashCode() {
            return this.submap.hashCode();
        }

        @Override // com.google.common.collect.Maps$ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public final Set keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object remove(Object obj) {
            Collection collection = (Collection) this.submap.remove(obj);
            if (collection == null) {
                return null;
            }
            Collection createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll(collection);
            AbstractMapBasedMultimap.this.totalSize -= collection.size();
            collection.clear();
            return createCollection;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final int size() {
            return this.submap.size();
        }

        @Override // java.util.AbstractMap
        public final String toString() {
            return this.submap.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class KeySet extends Maps$KeySet {
        public KeySet(Map map) {
            super(map);
        }

        @Override // com.google.common.collect.Maps$KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            ContextDataProvider.clear(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean containsAll(Collection collection) {
            return this.map.keySet().containsAll(collection);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public final boolean equals(Object obj) {
            if (this != obj && !this.map.keySet().equals(obj)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public final int hashCode() {
            return this.map.keySet().hashCode();
        }

        @Override // com.google.common.collect.Maps$KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator iterator() {
            final Iterator it = this.map.entrySet().iterator();
            return new Iterator(this) { // from class: com.google.common.collect.AbstractMapBasedMultimap.KeySet.1
                Map.Entry entry;
                final /* synthetic */ KeySet this$1;

                {
                    this.this$1 = this;
                }

                @Override // java.util.Iterator
                public final boolean hasNext() {
                    return it.hasNext();
                }

                @Override // java.util.Iterator
                public final Object next() {
                    Map.Entry entry = (Map.Entry) it.next();
                    this.entry = entry;
                    return entry.getKey();
                }

                @Override // java.util.Iterator
                public final void remove() {
                    boolean z;
                    if (this.entry != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ContextDataProvider.checkState(z, "no calls to next() since the last call to remove()");
                    Collection collection = (Collection) this.entry.getValue();
                    it.remove();
                    AbstractMapBasedMultimap.this.totalSize -= collection.size();
                    collection.clear();
                    this.entry = null;
                }
            };
        }

        @Override // com.google.common.collect.Maps$KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            Collection collection = (Collection) this.map.remove(obj);
            if (collection != null) {
                int size = collection.size();
                collection.clear();
                AbstractMapBasedMultimap.this.totalSize -= size;
                if (size > 0) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RandomAccessWrappedList extends WrappedList implements RandomAccess {
        public RandomAccessWrappedList(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, List list, WrappedCollection wrappedCollection) {
            super(obj, list, wrappedCollection);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class WrappedCollection extends AbstractCollection {
        final WrappedCollection ancestor;
        final Collection ancestorDelegate;
        Collection delegate;
        final Object key;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class WrappedIterator implements Iterator {
            final Iterator delegateIterator;
            final Collection originalDelegate;

            public WrappedIterator(Iterator it) {
                this.originalDelegate = WrappedCollection.this.delegate;
                this.delegateIterator = it;
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                validateIterator();
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public final Object next() {
                validateIterator();
                return this.delegateIterator.next();
            }

            @Override // java.util.Iterator
            public final void remove() {
                this.delegateIterator.remove();
                WrappedCollection wrappedCollection = WrappedCollection.this;
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                abstractMapBasedMultimap.totalSize--;
                wrappedCollection.removeIfEmpty();
            }

            final void validateIterator() {
                WrappedCollection.this.refreshIfEmpty();
                if (WrappedCollection.this.delegate == this.originalDelegate) {
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            public WrappedIterator() {
                Iterator it;
                this.originalDelegate = WrappedCollection.this.delegate;
                Collection collection = WrappedCollection.this.delegate;
                if (collection instanceof List) {
                    it = ((List) collection).listIterator();
                } else {
                    it = collection.iterator();
                }
                this.delegateIterator = it;
            }
        }

        public WrappedCollection(Object obj, Collection collection, WrappedCollection wrappedCollection) {
            Collection collection2;
            this.key = obj;
            this.delegate = collection;
            this.ancestor = wrappedCollection;
            if (wrappedCollection == null) {
                collection2 = null;
            } else {
                collection2 = wrappedCollection.delegate;
            }
            this.ancestorDelegate = collection2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean add(Object obj) {
            refreshIfEmpty();
            boolean isEmpty = this.delegate.isEmpty();
            boolean add = this.delegate.add(obj);
            if (add) {
                AbstractMapBasedMultimap.this.totalSize++;
                if (isEmpty) {
                    addToMap();
                    return true;
                }
            }
            return add;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean addAll(Collection collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.delegate.addAll(collection);
            if (addAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                if (size == 0) {
                    addToMap();
                    return true;
                }
                return addAll;
            }
            return addAll;
        }

        final void addToMap() {
            WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.addToMap();
                return;
            }
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            abstractMapBasedMultimap.map.put(this.key, this.delegate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final void clear() {
            int size = size();
            if (size == 0) {
                return;
            }
            this.delegate.clear();
            AbstractMapBasedMultimap.this.totalSize -= size;
            removeIfEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            refreshIfEmpty();
            return this.delegate.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean containsAll(Collection collection) {
            refreshIfEmpty();
            return this.delegate.containsAll(collection);
        }

        @Override // java.util.Collection
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            refreshIfEmpty();
            return this.delegate.equals(obj);
        }

        @Override // java.util.Collection
        public final int hashCode() {
            refreshIfEmpty();
            return this.delegate.hashCode();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            refreshIfEmpty();
            return new WrappedIterator();
        }

        final void refreshIfEmpty() {
            WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.refreshIfEmpty();
                WrappedCollection wrappedCollection2 = this.ancestor;
                if (wrappedCollection2.delegate != this.ancestorDelegate) {
                    throw new ConcurrentModificationException();
                }
                return;
            }
            if (this.delegate.isEmpty()) {
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                Collection collection = (Collection) abstractMapBasedMultimap.map.get(this.key);
                if (collection != null) {
                    this.delegate = collection;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean remove(Object obj) {
            refreshIfEmpty();
            boolean remove = this.delegate.remove(obj);
            if (remove) {
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                abstractMapBasedMultimap.totalSize--;
                removeIfEmpty();
            }
            return remove;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean removeAll(Collection collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.delegate.removeAll(collection);
            if (removeAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                removeIfEmpty();
            }
            return removeAll;
        }

        final void removeIfEmpty() {
            WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.removeIfEmpty();
            } else if (this.delegate.isEmpty()) {
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                abstractMapBasedMultimap.map.remove(this.key);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean retainAll(Collection collection) {
            collection.getClass();
            int size = size();
            boolean retainAll = this.delegate.retainAll(collection);
            if (retainAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                removeIfEmpty();
            }
            return retainAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final int size() {
            refreshIfEmpty();
            return this.delegate.size();
        }

        @Override // java.util.AbstractCollection
        public final String toString() {
            refreshIfEmpty();
            return this.delegate.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMapBasedMultimap(Map map) {
        ContextDataProvider.checkArgument(map.isEmpty());
        this.map = map;
    }

    public final void clear() {
        Iterator it = this.map.values().iterator();
        while (it.hasNext()) {
            ((Collection) it.next()).clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    @Override // com.google.common.collect.AbstractMultimap
    public final Map createAsMap() {
        return new AsMap(this.map);
    }

    public abstract Collection createCollection();

    @Override // com.google.common.collect.AbstractMultimap
    public final Set createKeySet() {
        return new KeySet(this.map);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public final void put$ar$ds(Object obj, Object obj2) {
        Collection collection = (Collection) this.map.get(obj);
        if (collection == null) {
            Collection createCollection = createCollection();
            if (createCollection.add(obj2)) {
                this.totalSize++;
                this.map.put(obj, createCollection);
                return;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        }
        if (collection.add(obj2)) {
            this.totalSize++;
        }
    }

    public Collection wrapCollection(Object obj, Collection collection) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List wrapList(Object obj, List list, WrappedCollection wrappedCollection) {
        if (list instanceof RandomAccess) {
            return new RandomAccessWrappedList(this, obj, list, wrappedCollection);
        }
        return new WrappedList(obj, list, wrappedCollection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class WrappedList extends WrappedCollection implements List {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class WrappedListIterator extends WrappedCollection.WrappedIterator implements ListIterator {
            public WrappedListIterator() {
                super();
            }

            private final ListIterator getDelegateListIterator() {
                validateIterator();
                return (ListIterator) this.delegateIterator;
            }

            @Override // java.util.ListIterator
            public final void add(Object obj) {
                boolean isEmpty = WrappedList.this.isEmpty();
                getDelegateListIterator().add(obj);
                WrappedList wrappedList = WrappedList.this;
                AbstractMapBasedMultimap.this.totalSize++;
                if (isEmpty) {
                    wrappedList.addToMap();
                }
            }

            @Override // java.util.ListIterator
            public final boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            @Override // java.util.ListIterator
            public final int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            @Override // java.util.ListIterator
            public final Object previous() {
                return getDelegateListIterator().previous();
            }

            @Override // java.util.ListIterator
            public final int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            @Override // java.util.ListIterator
            public final void set(Object obj) {
                getDelegateListIterator().set(obj);
            }

            public WrappedListIterator(int i) {
                super(WrappedList.this.getListDelegate().listIterator(i));
            }
        }

        public WrappedList(Object obj, List list, WrappedCollection wrappedCollection) {
            super(obj, list, wrappedCollection);
        }

        @Override // java.util.List
        public final void add(int i, Object obj) {
            refreshIfEmpty();
            boolean isEmpty = this.delegate.isEmpty();
            getListDelegate().add(i, obj);
            AbstractMapBasedMultimap.this.totalSize++;
            if (isEmpty) {
                addToMap();
            }
        }

        @Override // java.util.List
        public final boolean addAll(int i, Collection collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = getListDelegate().addAll(i, collection);
            if (addAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                if (size == 0) {
                    addToMap();
                    return true;
                }
                return addAll;
            }
            return addAll;
        }

        @Override // java.util.List
        public final Object get(int i) {
            refreshIfEmpty();
            return getListDelegate().get(i);
        }

        final List getListDelegate() {
            return (List) this.delegate;
        }

        @Override // java.util.List
        public final int indexOf(Object obj) {
            refreshIfEmpty();
            return getListDelegate().indexOf(obj);
        }

        @Override // java.util.List
        public final int lastIndexOf(Object obj) {
            refreshIfEmpty();
            return getListDelegate().lastIndexOf(obj);
        }

        @Override // java.util.List
        public final ListIterator listIterator() {
            refreshIfEmpty();
            return new WrappedListIterator();
        }

        @Override // java.util.List
        public final Object remove(int i) {
            refreshIfEmpty();
            Object remove = getListDelegate().remove(i);
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            abstractMapBasedMultimap.totalSize--;
            removeIfEmpty();
            return remove;
        }

        @Override // java.util.List
        public final Object set(int i, Object obj) {
            refreshIfEmpty();
            return getListDelegate().set(i, obj);
        }

        @Override // java.util.List
        public final List subList(int i, int i2) {
            refreshIfEmpty();
            List subList = getListDelegate().subList(i, i2);
            WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection == null) {
                wrappedCollection = this;
            }
            return AbstractMapBasedMultimap.this.wrapList(this.key, subList, wrappedCollection);
        }

        @Override // java.util.List
        public final ListIterator listIterator(int i) {
            refreshIfEmpty();
            return new WrappedListIterator(i);
        }
    }
}

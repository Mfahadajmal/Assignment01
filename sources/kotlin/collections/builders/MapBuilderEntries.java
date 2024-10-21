package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.builders.MapBuilder;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MapBuilderEntries extends AbstractMapBuilderEntrySet {
    private final MapBuilder backing;

    public MapBuilderEntries(MapBuilder mapBuilder) {
        this.backing = mapBuilder;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        ((Map.Entry) obj).getClass();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        collection.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.backing.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        collection.getClass();
        return this.backing.containsAllEntries$kotlin_stdlib(collection);
    }

    @Override // kotlin.collections.builders.AbstractMapBuilderEntrySet
    public final boolean containsEntry(Map.Entry entry) {
        return this.backing.containsEntry$kotlin_stdlib(entry);
    }

    @Override // kotlin.collections.AbstractMutableSet
    public final int getSize() {
        return this.backing.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.backing.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new MapBuilder.EntriesItr(this.backing);
    }

    @Override // kotlin.collections.builders.AbstractMapBuilderEntrySet
    public final boolean remove(Map.Entry entry) {
        entry.getClass();
        MapBuilder mapBuilder = this.backing;
        mapBuilder.checkIsMutable$kotlin_stdlib();
        int findKey = mapBuilder.findKey(entry.getKey());
        if (findKey >= 0) {
            Object[] objArr = mapBuilder.valuesArray;
            objArr.getClass();
            if (Intrinsics.areEqual(objArr[findKey], entry.getValue())) {
                mapBuilder.removeKeyAt(findKey);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        collection.getClass();
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.retainAll(collection);
    }
}

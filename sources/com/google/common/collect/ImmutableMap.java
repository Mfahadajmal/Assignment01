package com.google.common.collect;

import com.google.common.base.Absent;
import com.google.common.collect.ImmutableCollection;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.util.Map;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable, j$.util.Map<K, V> {
    private static final long serialVersionUID = 912559;
    private transient ImmutableSet entrySet;
    private transient ImmutableSet keySet;
    private transient ImmutableCollection values;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object ImmutableMap$Builder$ar$alternatingKeysAndValues;
        public Object ImmutableMap$Builder$ar$duplicateKey;
        public int size;

        public Builder(byte[] bArr) {
        }

        private final ImmutableMap build(boolean z) {
            Object obj;
            Object obj2;
            if (z && (obj2 = this.ImmutableMap$Builder$ar$duplicateKey) != null) {
                throw ((OptionalMethod) obj2).exception();
            }
            RegularImmutableMap create = RegularImmutableMap.create(this.size, (Object[]) this.ImmutableMap$Builder$ar$alternatingKeysAndValues, this);
            if (z && (obj = this.ImmutableMap$Builder$ar$duplicateKey) != null) {
                throw ((OptionalMethod) obj).exception();
            }
            return create;
        }

        public final ImmutableMap buildKeepingLast() {
            return build(false);
        }

        public final ImmutableMap buildOrThrow() {
            return build(true);
        }

        public final void ensureCapacity(int i) {
            Object[] objArr = (Object[]) this.ImmutableMap$Builder$ar$alternatingKeysAndValues;
            int length = objArr.length;
            int i2 = i + i;
            if (i2 > length) {
                this.ImmutableMap$Builder$ar$alternatingKeysAndValues = Arrays.copyOf(objArr, ImmutableCollection.Builder.expandedCapacity(length, i2));
            }
        }

        public final void put$ar$ds$de9b9d28_0(Object obj, Object obj2) {
            ensureCapacity(this.size + 1);
            ContextDataProvider.checkEntryNotNull(obj, obj2);
            Object obj3 = this.ImmutableMap$Builder$ar$alternatingKeysAndValues;
            int i = this.size;
            int i2 = i + i;
            Object[] objArr = (Object[]) obj3;
            objArr[i2] = obj;
            objArr[i2 + 1] = obj2;
            this.size = i + 1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void putAll$ar$ds$2e89a4e2_0(Iterable iterable) {
            if (iterable instanceof Collection) {
                ensureCapacity(this.size + iterable.size());
            }
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                put$ar$ds$de9b9d28_0(entry.getKey(), entry.getValue());
            }
        }

        public Builder(byte[] bArr, byte[] bArr2) {
            this((byte[]) null);
            Absent absent = Absent.INSTANCE;
            this.ImmutableMap$Builder$ar$alternatingKeysAndValues = absent;
            this.ImmutableMap$Builder$ar$duplicateKey = absent;
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this((byte[]) null);
        }

        public Builder() {
            this(4);
        }

        public Builder(int i) {
            this.ImmutableMap$Builder$ar$alternatingKeysAndValues = new Object[i + i];
            this.size = 0;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object keys;
        private final Object values;

        public SerializedForm(ImmutableMap immutableMap) {
            Object[] objArr = new Object[immutableMap.size()];
            Object[] objArr2 = new Object[immutableMap.size()];
            UnmodifiableIterator listIterator = immutableMap.entrySet().listIterator();
            int i = 0;
            while (listIterator.hasNext()) {
                Map.Entry entry = (Map.Entry) listIterator.next();
                objArr[i] = entry.getKey();
                objArr2[i] = entry.getValue();
                i++;
            }
            this.keys = objArr;
            this.values = objArr2;
        }

        final Object readResolve() {
            Object obj = this.keys;
            if (!(obj instanceof ImmutableSet)) {
                Object obj2 = this.values;
                Object[] objArr = (Object[]) obj;
                Builder builder = new Builder(objArr.length);
                for (int i = 0; i < objArr.length; i++) {
                    builder.put$ar$ds$de9b9d28_0(objArr[i], ((Object[]) obj2)[i]);
                }
                return builder.buildOrThrow();
            }
            ImmutableSet immutableSet = (ImmutableSet) obj;
            ImmutableCollection immutableCollection = (ImmutableCollection) this.values;
            Builder builder2 = new Builder(immutableSet.size());
            UnmodifiableIterator listIterator = immutableSet.listIterator();
            UnmodifiableIterator listIterator2 = immutableCollection.listIterator();
            while (listIterator.hasNext()) {
                builder2.put$ar$ds$de9b9d28_0(listIterator.next(), listIterator2.next());
            }
            return builder2.buildOrThrow();
        }
    }

    public static Builder builderWithExpectedSize(int i) {
        ContextDataProvider.checkNonnegative$ar$ds(i, "expectedSize");
        return new Builder(i);
    }

    public static ImmutableMap copyOf(Map map) {
        int i;
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap)) {
            ImmutableMap immutableMap = (ImmutableMap) map;
            immutableMap.isPartialView$ar$ds();
            return immutableMap;
        }
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        if (entrySet instanceof Collection) {
            i = entrySet.size();
        } else {
            i = 4;
        }
        Builder builder = new Builder(i);
        builder.putAll$ar$ds$2e89a4e2_0(entrySet);
        return builder.buildOrThrow();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        if (get(obj) != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public abstract ImmutableSet createEntrySet();

    public abstract ImmutableSet createKeySet();

    public abstract ImmutableCollection createValues();

    @Override // java.util.Map
    public final ImmutableSet entrySet() {
        ImmutableSet immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        return ContextDataProvider.equalsImpl(this, obj);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map, j$.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        if (obj3 != null) {
            return obj3;
        }
        return obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return ContextDataProvider.hashCodeImpl(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public abstract void isPartialView$ar$ds();

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        ImmutableSet immutableSet = this.keySet;
        if (immutableSet == null) {
            ImmutableSet createKeySet = createKeySet();
            this.keySet = createKeySet;
            return createKeySet;
        }
        return immutableSet;
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(java.util.Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return Map.CC.$default$putIfAbsent(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ boolean remove(Object obj, Object obj2) {
        return Map.CC.$default$remove(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object replace(Object obj, Object obj2) {
        return Map.CC.$default$replace(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    public final String toString() {
        return ContextDataProvider.toStringImpl(this);
    }

    @Override // java.util.Map
    public final ImmutableCollection values() {
        ImmutableCollection immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableCollection createValues = createValues();
        this.values = createValues;
        return createValues;
    }

    public Object writeReplace() {
        return new SerializedForm(this);
    }

    @Override // java.util.Map
    @Deprecated
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }
}

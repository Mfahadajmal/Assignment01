package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.DesugarCollections;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class MetadataProcessor {
    public static final MetadataProcessor EMPTY_PROCESSOR = new MetadataProcessor() { // from class: com.google.common.flogger.backend.MetadataProcessor.1
        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final int keyCount() {
            return 0;
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final Set keySet() {
            return Collections.emptySet();
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final void process(MetadataHandler metadataHandler, Object obj) {
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LightweightProcessor extends MetadataProcessor {
        public final int keyCount;
        public final int[] keyMap;
        private final Metadata logged;
        private final Metadata scope;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class ValueIterator implements Iterator {
            private final MetadataKey key;
            private int mask;
            private int nextIndex;

            public ValueIterator(MetadataKey metadataKey, int i) {
                this.key = metadataKey;
                int i2 = i & 31;
                this.nextIndex = i2;
                this.mask = i >>> (i2 + 5);
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                if (this.nextIndex >= 0) {
                    return true;
                }
                return false;
            }

            @Override // java.util.Iterator
            public final Object next() {
                Object cast = this.key.cast(LightweightProcessor.this.getValue(this.nextIndex));
                int i = this.mask;
                if (i != 0) {
                    int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i) + 1;
                    this.mask >>>= numberOfTrailingZeros;
                    this.nextIndex += numberOfTrailingZeros;
                } else {
                    this.nextIndex = -1;
                }
                return cast;
            }

            @Override // java.util.Iterator
            public final void remove() {
                throw new UnsupportedOperationException();
            }
        }

        public LightweightProcessor(Metadata metadata, Metadata metadata2) {
            boolean z;
            int i;
            this.scope = metadata;
            this.logged = metadata2;
            int size = metadata2.size();
            if (size <= 28) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z, "metadata size too large");
            int[] iArr = new int[size];
            this.keyMap = iArr;
            long j = 0;
            int i2 = 0;
            int i3 = 0;
            while (i2 < iArr.length) {
                MetadataKey key = getKey(i2);
                long j2 = key.bloomFilterMask | j;
                if (j2 == j) {
                    int i4 = 0;
                    while (true) {
                        if (i4 < i3) {
                            if (key.equals(getKey(iArr[i4] & 31))) {
                                break;
                            } else {
                                i4++;
                            }
                        } else {
                            i4 = -1;
                            break;
                        }
                    }
                    if (i4 != -1) {
                        if (key.canRepeat) {
                            i = iArr[i4] | (1 << (i2 + 4));
                        } else {
                            i = i2;
                        }
                        iArr[i4] = i;
                        i2++;
                        j = j2;
                    }
                }
                iArr[i3] = i2;
                i3++;
                i2++;
                j = j2;
            }
            this.keyCount = i3;
        }

        public final MetadataKey getKey(int i) {
            Metadata metadata = this.scope;
            int size = metadata.size();
            if (i >= size) {
                metadata = this.logged;
                i -= size;
            }
            return metadata.getKey(i);
        }

        public final Object getValue(int i) {
            Metadata metadata = this.scope;
            int size = metadata.size();
            if (i >= size) {
                metadata = this.logged;
                i -= size;
            }
            return metadata.getValue(i);
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final int keyCount() {
            return this.keyCount;
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final Set keySet() {
            return new AbstractSet() { // from class: com.google.common.flogger.backend.MetadataProcessor.LightweightProcessor.1
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public final Iterator iterator() {
                    return new Iterator() { // from class: com.google.common.flogger.backend.MetadataProcessor.LightweightProcessor.1.1
                        private int i = 0;

                        @Override // java.util.Iterator
                        public final boolean hasNext() {
                            if (this.i < LightweightProcessor.this.keyCount) {
                                return true;
                            }
                            return false;
                        }

                        @Override // java.util.Iterator
                        public final /* bridge */ /* synthetic */ Object next() {
                            int i = this.i;
                            this.i = i + 1;
                            LightweightProcessor lightweightProcessor = LightweightProcessor.this;
                            return lightweightProcessor.getKey(lightweightProcessor.keyMap[i] & 31);
                        }

                        @Override // java.util.Iterator
                        public final void remove() {
                            throw new UnsupportedOperationException();
                        }
                    };
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public final int size() {
                    return LightweightProcessor.this.keyCount;
                }
            };
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final void process(MetadataHandler metadataHandler, Object obj) {
            for (int i = 0; i < this.keyCount; i++) {
                int i2 = this.keyMap[i];
                MetadataKey key = getKey(i2 & 31);
                if (!key.canRepeat) {
                    metadataHandler.handle(key, key.cast(getValue(i2)), obj);
                } else {
                    metadataHandler.handleRepeated(key, new ValueIterator(key, i2), obj);
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SimpleProcessor extends MetadataProcessor {
        private final Map map;

        public SimpleProcessor(Metadata metadata, Metadata metadata2) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            addTo(linkedHashMap, metadata);
            addTo(linkedHashMap, metadata2);
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (((MetadataKey) entry.getKey()).canRepeat) {
                    entry.setValue(DesugarCollections.unmodifiableList((List) entry.getValue()));
                }
            }
            this.map = DesugarCollections.unmodifiableMap(linkedHashMap);
        }

        private static void addTo(Map map, Metadata metadata) {
            for (int i = 0; i < metadata.size(); i++) {
                MetadataKey key = metadata.getKey(i);
                Object obj = map.get(key);
                if (key.canRepeat) {
                    List list = (List) obj;
                    if (list == null) {
                        list = new ArrayList();
                        map.put(key, list);
                    }
                    list.add(key.cast(metadata.getValue(i)));
                } else {
                    map.put(key, key.cast(metadata.getValue(i)));
                }
            }
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final int keyCount() {
            return this.map.size();
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final Set keySet() {
            return this.map.keySet();
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public final void process(MetadataHandler metadataHandler, Object obj) {
            for (Map.Entry entry : this.map.entrySet()) {
                MetadataKey metadataKey = (MetadataKey) entry.getKey();
                Object value = entry.getValue();
                if (metadataKey.canRepeat) {
                    metadataHandler.handleRepeated(metadataKey, ((List) value).iterator(), obj);
                } else {
                    metadataHandler.handle(metadataKey, value, obj);
                }
            }
        }
    }

    public abstract int keyCount();

    public abstract Set keySet();

    public abstract void process(MetadataHandler metadataHandler, Object obj);
}

package com.google.common.collect;

import com.google.common.flogger.context.ContextDataProvider;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayListMultimap extends AbstractListMultimap {
    private static final long serialVersionUID = 0;
    transient int expectedValuesPerKey;

    public ArrayListMultimap() {
        super(new CompactHashMap(null), null);
        ContextDataProvider.checkNonnegative$ar$ds(3, "expectedValuesPerKey");
        this.expectedValuesPerKey = 3;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.expectedValuesPerKey = 3;
        int readInt = objectInputStream.readInt();
        CompactHashMap compactHashMap = new CompactHashMap();
        this.map = compactHashMap;
        this.totalSize = 0;
        for (V v : compactHashMap.values()) {
            ContextDataProvider.checkArgument(!v.isEmpty());
            this.totalSize += v.size();
        }
        for (int i = 0; i < readInt; i++) {
            List list = get(objectInputStream.readObject());
            int readInt2 = objectInputStream.readInt();
            for (int i2 = 0; i2 < readInt2; i2++) {
                list.add(objectInputStream.readObject());
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        ContextDataProvider.writeMultimap(this, objectOutputStream);
    }

    @Override // com.google.common.collect.AbstractListMultimap, com.google.common.collect.AbstractMapBasedMultimap
    public final /* bridge */ /* synthetic */ Collection createCollection() {
        return new ArrayList(this.expectedValuesPerKey);
    }
}

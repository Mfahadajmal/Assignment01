package com.google.common.collect;

import _COROUTINE._BOUNDARY;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ImmutableSetMultimap<K, V> extends ImmutableMultimap<K, V> implements Multimap<K, V> {
    private static final long serialVersionUID = 0;
    private final transient ImmutableSet<V> emptySet;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SetFieldSettersHolder {
        static final SplitInstallSharedPreferences EMPTY_SET_FIELD_SETTER$ar$class_merging$ar$class_merging$ar$class_merging = ContextDataProvider.getFieldSetter$ar$class_merging$ar$class_merging$ar$class_merging(ImmutableSetMultimap.class, "emptySet");
    }

    public ImmutableSetMultimap(ImmutableMap immutableMap, int i) {
        super(immutableMap, 0);
        this.emptySet = emptySet(null);
    }

    private static ImmutableSet emptySet(Comparator comparator) {
        if (comparator == null) {
            return RegularImmutableSet.EMPTY;
        }
        return ImmutableSortedSet.emptySet(comparator);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        ImmutableSet.Builder builder;
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            ImmutableMap.Builder builder2 = new ImmutableMap.Builder();
            int i = 0;
            for (int i2 = 0; i2 < readInt; i2++) {
                Object readObject = objectInputStream.readObject();
                readObject.getClass();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    if (comparator == null) {
                        builder = new ImmutableSet.Builder();
                    } else {
                        builder = new ImmutableSortedSet.Builder(comparator);
                    }
                    for (int i3 = 0; i3 < readInt2; i3++) {
                        Object readObject2 = objectInputStream.readObject();
                        readObject2.getClass();
                        builder.add$ar$ds$187ad64f_0(readObject2);
                    }
                    ImmutableSet build = builder.build();
                    if (build.size() == readInt2) {
                        builder2.put$ar$ds$de9b9d28_0(readObject, build);
                        i += readInt2;
                    } else {
                        throw new InvalidObjectException("Duplicate key-value pairs exist for key ".concat(readObject.toString()));
                    }
                } else {
                    throw new InvalidObjectException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readInt2, "Invalid value count "));
                }
            }
            try {
                ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER$ar$class_merging$ar$class_merging$ar$class_merging.set(this, builder2.buildOrThrow());
                try {
                    ((Field) ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER$ar$class_merging$ar$class_merging$ar$class_merging.SplitInstallSharedPreferences$ar$context).set(this, Integer.valueOf(i));
                    SetFieldSettersHolder.EMPTY_SET_FIELD_SETTER$ar$class_merging$ar$class_merging$ar$class_merging.set(this, emptySet(comparator));
                    return;
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                }
            } catch (IllegalArgumentException e2) {
                throw ((InvalidObjectException) new InvalidObjectException(e2.getMessage()).initCause(e2));
            }
        }
        throw new InvalidObjectException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readInt, "Invalid key count "));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        Comparator comparator;
        objectOutputStream.defaultWriteObject();
        ImmutableSet<V> immutableSet = this.emptySet;
        if (immutableSet instanceof ImmutableSortedSet) {
            comparator = ((ImmutableSortedSet) immutableSet).comparator;
        } else {
            comparator = null;
        }
        objectOutputStream.writeObject(comparator);
        ContextDataProvider.writeMultimap(this, objectOutputStream);
    }
}

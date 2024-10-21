package kotlin.collections.builders;

import java.util.Map;
import kotlin.collections.AbstractMutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractMapBuilderEntrySet extends AbstractMutableSet {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        entry.getClass();
        return containsEntry(entry);
    }

    public abstract boolean containsEntry(Map.Entry entry);

    public /* bridge */ boolean remove(Map.Entry entry) {
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof Map.Entry) {
            return remove((Map.Entry) obj);
        }
        return false;
    }
}

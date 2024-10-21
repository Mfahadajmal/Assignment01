package kotlin.collections;

import java.util.AbstractSet;
import java.util.Set;
import kotlin.jvm.internal.markers.KMutableCollection;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractMutableSet extends AbstractSet implements Set, KMutableCollection {
    public abstract int getSize();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }
}

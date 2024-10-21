package androidx.navigation;

import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavGraph$iterator$1 implements Iterator, KMappedMarker {
    private int index = -1;
    final /* synthetic */ NavGraph this$0;
    private boolean wentToNext;

    public NavGraph$iterator$1(NavGraph navGraph) {
        this.this$0 = navGraph;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.index + 1 < this.this$0.nodes.size()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final NavDestination next() {
        if (hasNext()) {
            this.wentToNext = true;
            NavGraph navGraph = this.this$0;
            int i = this.index + 1;
            this.index = i;
            Object valueAt = navGraph.nodes.valueAt(i);
            valueAt.getClass();
            return (NavDestination) valueAt;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.wentToNext) {
            NavGraph navGraph = this.this$0;
            int i = this.index;
            SparseArrayCompat sparseArrayCompat = navGraph.nodes;
            ((NavDestination) sparseArrayCompat.valueAt(i)).parent = null;
            int i2 = this.index;
            Object[] objArr = sparseArrayCompat.values;
            Object obj = objArr[i2];
            Object obj2 = SparseArrayCompatKt.DELETED;
            if (obj != obj2) {
                objArr[i2] = obj2;
                sparseArrayCompat.garbage = true;
            }
            this.index = i2 - 1;
            this.wentToNext = false;
            return;
        }
        throw new IllegalStateException("You must call next() before you can remove an element");
    }
}

package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InactiveNodeList implements Incomplete {
    public final NodeList list;

    public InactiveNodeList(NodeList nodeList) {
        this.list = nodeList;
    }

    @Override // kotlinx.coroutines.Incomplete
    public final NodeList getList() {
        return this.list;
    }

    @Override // kotlinx.coroutines.Incomplete
    public final boolean isActive() {
        return false;
    }

    public final String toString() {
        if (DebugKt.DEBUG) {
            return this.list.getString("New");
        }
        return super.toString();
    }
}

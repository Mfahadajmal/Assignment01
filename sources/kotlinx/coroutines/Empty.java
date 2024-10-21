package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Empty implements Incomplete {
    public final boolean isActive;

    public Empty(boolean z) {
        this.isActive = z;
    }

    @Override // kotlinx.coroutines.Incomplete
    public final NodeList getList() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public final boolean isActive() {
        return this.isActive;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("Empty{");
        if (true != this.isActive) {
            str = "New";
        } else {
            str = "Active";
        }
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}

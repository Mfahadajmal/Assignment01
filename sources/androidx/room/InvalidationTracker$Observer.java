package androidx.room;

import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class InvalidationTracker$Observer {
    public final String[] tables;

    public InvalidationTracker$Observer(String[] strArr) {
        this.tables = strArr;
    }

    public abstract void onInvalidated(Set set);
}

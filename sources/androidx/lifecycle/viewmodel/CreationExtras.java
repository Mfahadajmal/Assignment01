package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CreationExtras {
    public final Map map = new LinkedHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // androidx.lifecycle.viewmodel.CreationExtras
        public final Object get(Key key) {
            throw null;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Key {
    }

    public abstract Object get(Key key);
}

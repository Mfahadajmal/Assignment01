package androidx.lifecycle.viewmodel;

import androidx.lifecycle.viewmodel.CreationExtras;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MutableCreationExtras extends CreationExtras {
    public MutableCreationExtras() {
        this((byte[]) null);
    }

    @Override // androidx.lifecycle.viewmodel.CreationExtras
    public final Object get(CreationExtras.Key key) {
        return this.map.get(key);
    }

    public final void set(CreationExtras.Key key, Object obj) {
        this.map.put(key, obj);
    }

    public MutableCreationExtras(CreationExtras creationExtras) {
        creationExtras.getClass();
        this.map.putAll(creationExtras.map);
    }

    public /* synthetic */ MutableCreationExtras(byte[] bArr) {
        this(CreationExtras.Empty.INSTANCE);
    }
}

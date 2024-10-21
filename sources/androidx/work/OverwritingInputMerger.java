package androidx.work;

import androidx.lifecycle.ViewModelStore;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OverwritingInputMerger extends InputMerger {
    @Override // androidx.work.InputMerger
    public final Data merge(List list) {
        ViewModelStore viewModelStore = new ViewModelStore((byte[]) null, (byte[]) null);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            linkedHashMap.putAll(((Data) it.next()).getKeyValueMap());
        }
        viewModelStore.putAll$ar$ds(linkedHashMap);
        return viewModelStore.build();
    }
}

package androidx.work;

import androidx.lifecycle.ViewModelStore;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayCreatingInputMerger extends InputMerger {
    @Override // androidx.work.InputMerger
    public final Data merge(List list) {
        Class<?> cls;
        Object newInstance;
        ViewModelStore viewModelStore = new ViewModelStore((byte[]) null, (byte[]) null);
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            for (Map.Entry entry : ((Data) it.next()).getKeyValueMap().entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                if (value == null || (cls = value.getClass()) == null) {
                    cls = String.class;
                }
                Object obj = hashMap.get(str);
                if (obj == null) {
                    if (cls.isArray()) {
                        value.getClass();
                    } else {
                        newInstance = Array.newInstance(cls, 1);
                        Array.set(newInstance, 0, value);
                        newInstance.getClass();
                        value = newInstance;
                    }
                } else {
                    Class<?> cls2 = obj.getClass();
                    if (Intrinsics.areEqual(cls2, cls)) {
                        value.getClass();
                        int length = Array.getLength(obj);
                        Class<?> cls3 = obj.getClass();
                        int length2 = Array.getLength(value);
                        Class<?> componentType = cls3.getComponentType();
                        componentType.getClass();
                        Object newInstance2 = Array.newInstance(componentType, length + length2);
                        System.arraycopy(obj, 0, newInstance2, 0, length);
                        System.arraycopy(value, 0, newInstance2, length, length2);
                        newInstance2.getClass();
                        value = newInstance2;
                    } else if (Intrinsics.areEqual(cls2.getComponentType(), cls)) {
                        int length3 = Array.getLength(obj);
                        newInstance = Array.newInstance(cls, length3 + 1);
                        System.arraycopy(obj, 0, newInstance, 0, length3);
                        Array.set(newInstance, length3, value);
                        newInstance.getClass();
                        value = newInstance;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                hashMap.put(str, value);
            }
        }
        viewModelStore.putAll$ar$ds(hashMap);
        return viewModelStore.build();
    }
}

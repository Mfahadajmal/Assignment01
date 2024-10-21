package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionToArray {
    private static final Object[] EMPTY = new Object[0];

    public static final Object[] toArray(Collection collection) {
        int size = collection.size();
        if (size == 0) {
            return EMPTY;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            return EMPTY;
        }
        Object[] objArr = new Object[size];
        int i = 0;
        while (true) {
            objArr[i] = it.next();
            i++;
            if (i >= objArr.length) {
                if (!it.hasNext()) {
                    return objArr;
                }
                int i2 = ((i * 3) + 1) >>> 1;
                if (i2 <= i) {
                    i2 = 2147483645;
                    if (i >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArr = Arrays.copyOf(objArr, i2);
                objArr.getClass();
            } else if (!it.hasNext()) {
                Object[] copyOf = Arrays.copyOf(objArr, i);
                copyOf.getClass();
                return copyOf;
            }
        }
    }

    public static final Object[] toArray(Collection collection, Object[] objArr) {
        Object[] objArr2;
        int size = collection.size();
        int i = 0;
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                if (size <= objArr.length) {
                    objArr2 = objArr;
                } else {
                    Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                    newInstance.getClass();
                    objArr2 = (Object[]) newInstance;
                }
                while (true) {
                    objArr2[i] = it.next();
                    i++;
                    if (i >= objArr2.length) {
                        if (!it.hasNext()) {
                            return objArr2;
                        }
                        int i2 = ((i * 3) + 1) >>> 1;
                        if (i2 <= i) {
                            i2 = 2147483645;
                            if (i >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArr2 = Arrays.copyOf(objArr2, i2);
                        objArr2.getClass();
                    } else if (!it.hasNext()) {
                        if (objArr2 == objArr) {
                            objArr[i] = null;
                            return objArr;
                        }
                        Object[] copyOf = Arrays.copyOf(objArr2, i);
                        copyOf.getClass();
                        return copyOf;
                    }
                }
            } else if (objArr.length <= 0) {
                return objArr;
            }
        } else if (objArr.length <= 0) {
            return objArr;
        }
        objArr[0] = null;
        return objArr;
    }
}

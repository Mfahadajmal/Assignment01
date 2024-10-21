package j$.util.concurrent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class g extends k {
    final k[] e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(k[] kVarArr) {
        super(-1, null, null);
        this.e = kVarArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.util.concurrent.k
    public final k a(int i, Object obj) {
        int length;
        k k;
        Object obj2;
        k[] kVarArr = this.e;
        loop0: while (obj != null && kVarArr != null && (length = kVarArr.length) != 0 && (k = ConcurrentHashMap.k(kVarArr, (length - 1) & i)) != null) {
            do {
                int i2 = k.a;
                if (i2 == i && ((obj2 = k.b) == obj || (obj2 != null && obj.equals(obj2)))) {
                    return k;
                }
                if (i2 >= 0) {
                    k = k.d;
                } else {
                    if (!(k instanceof g)) {
                        return k.a(i, obj);
                    }
                    kVarArr = ((g) k).e;
                }
            } while (k != null);
        }
        return null;
    }
}

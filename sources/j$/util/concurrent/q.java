package j$.util.concurrent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class q extends k {
    q e;
    q f;
    q g;
    q h;
    boolean i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(int i, Object obj, Object obj2, k kVar, q qVar) {
        super(i, obj, obj2, kVar);
        this.e = qVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.util.concurrent.k
    public final k a(int i, Object obj) {
        return b(i, obj, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final q b(int i, Object obj, Class cls) {
        int i2;
        if (obj != null) {
            q qVar = this;
            do {
                q qVar2 = qVar.f;
                q qVar3 = qVar.g;
                int i3 = qVar.a;
                if (i3 <= i) {
                    if (i3 >= i) {
                        Object obj2 = qVar.b;
                        if (obj2 != obj && (obj2 == null || !obj.equals(obj2))) {
                            if (qVar2 != null) {
                                if (qVar3 != null) {
                                    if (cls != null || (cls = ConcurrentHashMap.c(obj)) != null) {
                                        int i4 = ConcurrentHashMap.g;
                                        if (obj2 != null && obj2.getClass() == cls) {
                                            i2 = ((Comparable) obj).compareTo(obj2);
                                        } else {
                                            i2 = 0;
                                        }
                                        if (i2 != 0) {
                                            if (i2 >= 0) {
                                                qVar2 = qVar3;
                                            }
                                        }
                                    }
                                    q b = qVar3.b(i, obj, cls);
                                    if (b != null) {
                                        return b;
                                    }
                                }
                            }
                        } else {
                            return qVar;
                        }
                    }
                    qVar = qVar3;
                }
                qVar = qVar2;
            } while (qVar != null);
            return null;
        }
        return null;
    }
}

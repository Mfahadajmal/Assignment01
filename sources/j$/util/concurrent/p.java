package j$.util.concurrent;

import java.util.concurrent.locks.LockSupport;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class p extends k {
    private static final j$.sun.misc.a h;
    private static final long i;
    q e;
    volatile q f;
    volatile Thread g;
    volatile int lockState;

    static {
        j$.sun.misc.a h2 = j$.sun.misc.a.h();
        h = h2;
        i = h2.j(p.class, "lockState");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(q qVar) {
        super(-2, null, null);
        int i2;
        int i3;
        q qVar2;
        this.f = qVar;
        q qVar3 = null;
        while (qVar != null) {
            q qVar4 = (q) qVar.d;
            qVar.g = null;
            qVar.f = null;
            if (qVar3 == null) {
                qVar.e = null;
                qVar.i = false;
            } else {
                Object obj = qVar.b;
                int i4 = qVar.a;
                q qVar5 = qVar3;
                Class<?> cls = null;
                while (true) {
                    Object obj2 = qVar5.b;
                    int i5 = qVar5.a;
                    if (i5 > i4) {
                        i3 = -1;
                    } else if (i5 < i4) {
                        i3 = 1;
                    } else {
                        if (cls != null || (cls = ConcurrentHashMap.c(obj)) != null) {
                            int i6 = ConcurrentHashMap.g;
                            if (obj2 != null && obj2.getClass() == cls) {
                                i2 = ((Comparable) obj).compareTo(obj2);
                            } else {
                                i2 = 0;
                            }
                            if (i2 != 0) {
                                i3 = i2;
                            }
                        }
                        i3 = i(obj, obj2);
                    }
                    if (i3 <= 0) {
                        qVar2 = qVar5.f;
                    } else {
                        qVar2 = qVar5.g;
                    }
                    if (qVar2 == null) {
                        break;
                    } else {
                        qVar5 = qVar2;
                    }
                }
                qVar.e = qVar5;
                if (i3 <= 0) {
                    qVar5.f = qVar;
                } else {
                    qVar5.g = qVar;
                }
                qVar = c(qVar3, qVar);
            }
            qVar3 = qVar;
            qVar = qVar4;
        }
        this.e = qVar3;
    }

    static q b(q qVar, q qVar2) {
        while (qVar2 != null && qVar2 != qVar) {
            q qVar3 = qVar2.e;
            if (qVar3 == null) {
                qVar2.i = false;
                return qVar2;
            }
            if (qVar2.i) {
                qVar2.i = false;
                return qVar;
            }
            q qVar4 = qVar3.f;
            if (qVar4 == qVar2) {
                qVar4 = qVar3.g;
                if (qVar4 != null && qVar4.i) {
                    qVar4.i = false;
                    qVar3.i = true;
                    qVar = g(qVar, qVar3);
                    qVar3 = qVar2.e;
                    qVar4 = qVar3 == null ? null : qVar3.g;
                }
                if (qVar4 == null) {
                    qVar2 = qVar3;
                } else {
                    q qVar5 = qVar4.f;
                    q qVar6 = qVar4.g;
                    if ((qVar6 != null && qVar6.i) || (qVar5 != null && qVar5.i)) {
                        if (qVar6 == null || !qVar6.i) {
                            if (qVar5 != null) {
                                qVar5.i = false;
                            }
                            qVar4.i = true;
                            qVar = h(qVar, qVar4);
                            qVar3 = qVar2.e;
                            qVar4 = qVar3 != null ? qVar3.g : null;
                        }
                        if (qVar4 != null) {
                            qVar4.i = qVar3 == null ? false : qVar3.i;
                            q qVar7 = qVar4.g;
                            if (qVar7 != null) {
                                qVar7.i = false;
                            }
                        }
                        if (qVar3 != null) {
                            qVar3.i = false;
                            qVar = g(qVar, qVar3);
                        }
                        qVar2 = qVar;
                    }
                    qVar4.i = true;
                    qVar2 = qVar3;
                }
            } else {
                if (qVar4 != null && qVar4.i) {
                    qVar4.i = false;
                    qVar3.i = true;
                    qVar = h(qVar, qVar3);
                    qVar3 = qVar2.e;
                    qVar4 = qVar3 == null ? null : qVar3.f;
                }
                if (qVar4 == null) {
                    qVar2 = qVar3;
                } else {
                    q qVar8 = qVar4.f;
                    q qVar9 = qVar4.g;
                    if ((qVar8 != null && qVar8.i) || (qVar9 != null && qVar9.i)) {
                        if (qVar8 == null || !qVar8.i) {
                            if (qVar9 != null) {
                                qVar9.i = false;
                            }
                            qVar4.i = true;
                            qVar = g(qVar, qVar4);
                            qVar3 = qVar2.e;
                            qVar4 = qVar3 != null ? qVar3.f : null;
                        }
                        if (qVar4 != null) {
                            qVar4.i = qVar3 == null ? false : qVar3.i;
                            q qVar10 = qVar4.f;
                            if (qVar10 != null) {
                                qVar10.i = false;
                            }
                        }
                        if (qVar3 != null) {
                            qVar3.i = false;
                            qVar = h(qVar, qVar3);
                        }
                        qVar2 = qVar;
                    }
                    qVar4.i = true;
                    qVar2 = qVar3;
                }
            }
        }
        return qVar;
    }

    static q c(q qVar, q qVar2) {
        q qVar3;
        qVar2.i = true;
        while (true) {
            q qVar4 = qVar2.e;
            if (qVar4 == null) {
                qVar2.i = false;
                return qVar2;
            }
            if (!qVar4.i || (qVar3 = qVar4.e) == null) {
                break;
            }
            q qVar5 = qVar3.f;
            if (qVar4 == qVar5) {
                qVar5 = qVar3.g;
                if (qVar5 == null || !qVar5.i) {
                    if (qVar2 == qVar4.g) {
                        qVar = g(qVar, qVar4);
                        q qVar6 = qVar4.e;
                        qVar3 = qVar6 == null ? null : qVar6.e;
                        qVar4 = qVar6;
                        qVar2 = qVar4;
                    }
                    if (qVar4 != null) {
                        qVar4.i = false;
                        if (qVar3 != null) {
                            qVar3.i = true;
                            qVar = h(qVar, qVar3);
                        }
                    }
                } else {
                    qVar5.i = false;
                    qVar4.i = false;
                    qVar3.i = true;
                    qVar2 = qVar3;
                }
            } else if (qVar5 == null || !qVar5.i) {
                if (qVar2 == qVar4.f) {
                    qVar = h(qVar, qVar4);
                    q qVar7 = qVar4.e;
                    qVar3 = qVar7 == null ? null : qVar7.e;
                    qVar4 = qVar7;
                    qVar2 = qVar4;
                }
                if (qVar4 != null) {
                    qVar4.i = false;
                    if (qVar3 != null) {
                        qVar3.i = true;
                        qVar = g(qVar, qVar3);
                    }
                }
            } else {
                qVar5.i = false;
                qVar4.i = false;
                qVar3.i = true;
                qVar2 = qVar3;
            }
        }
        return qVar;
    }

    private final void d() {
        if (!h.c(this, i, 0, 1)) {
            boolean z = false;
            while (true) {
                int i2 = this.lockState;
                if ((i2 & (-3)) == 0) {
                    if (h.c(this, i, i2, 1)) {
                        break;
                    }
                } else if ((i2 & 2) == 0) {
                    if (h.c(this, i, i2, i2 | 2)) {
                        this.g = Thread.currentThread();
                        z = true;
                    }
                } else if (z) {
                    LockSupport.park(this);
                }
            }
            if (z) {
                this.g = null;
            }
        }
    }

    static q g(q qVar, q qVar2) {
        q qVar3;
        if (qVar2 != null && (qVar3 = qVar2.g) != null) {
            q qVar4 = qVar3.f;
            qVar2.g = qVar4;
            if (qVar4 != null) {
                qVar4.e = qVar2;
            }
            q qVar5 = qVar2.e;
            qVar3.e = qVar5;
            if (qVar5 == null) {
                qVar3.i = false;
                qVar = qVar3;
            } else if (qVar5.f == qVar2) {
                qVar5.f = qVar3;
            } else {
                qVar5.g = qVar3;
            }
            qVar3.f = qVar2;
            qVar2.e = qVar3;
        }
        return qVar;
    }

    static q h(q qVar, q qVar2) {
        q qVar3;
        if (qVar2 != null && (qVar3 = qVar2.f) != null) {
            q qVar4 = qVar3.g;
            qVar2.f = qVar4;
            if (qVar4 != null) {
                qVar4.e = qVar2;
            }
            q qVar5 = qVar2.e;
            qVar3.e = qVar5;
            if (qVar5 == null) {
                qVar3.i = false;
                qVar = qVar3;
            } else if (qVar5.g == qVar2) {
                qVar5.g = qVar3;
            } else {
                qVar5.f = qVar3;
            }
            qVar3.g = qVar2;
            qVar2.e = qVar3;
        }
        return qVar;
    }

    static int i(Object obj, Object obj2) {
        int compareTo;
        return (obj == null || obj2 == null || (compareTo = obj.getClass().getName().compareTo(obj2.getClass().getName())) == 0) ? System.identityHashCode(obj) <= System.identityHashCode(obj2) ? -1 : 1 : compareTo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.util.concurrent.k
    public final k a(int i2, Object obj) {
        Object obj2;
        Thread thread;
        Thread thread2;
        q qVar = null;
        if (obj != null) {
            k kVar = this.f;
            while (kVar != null) {
                int i3 = this.lockState;
                if ((i3 & 3) == 0) {
                    j$.sun.misc.a aVar = h;
                    long j = i;
                    if (aVar.c(this, j, i3, i3 + 4)) {
                        try {
                            q qVar2 = this.e;
                            if (qVar2 != null) {
                                qVar = qVar2.b(i2, obj, null);
                            }
                            if (aVar.f(this, j) == 6 && (thread2 = this.g) != null) {
                                LockSupport.unpark(thread2);
                            }
                            return qVar;
                        } catch (Throwable th) {
                            if (h.f(this, i) == 6 && (thread = this.g) != null) {
                                LockSupport.unpark(thread);
                            }
                            throw th;
                        }
                    }
                } else {
                    if (kVar.a == i2 && ((obj2 = kVar.b) == obj || (obj2 != null && obj.equals(obj2)))) {
                        return kVar;
                    }
                    kVar = kVar.d;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b3, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0070, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final j$.util.concurrent.q e(int r16, java.lang.Object r17, java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 188
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.p.e(int, java.lang.Object, java.lang.Object):j$.util.concurrent.q");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0091 A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00b5 A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bd A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c0 A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ad A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f(j$.util.concurrent.q r11) {
        /*
            Method dump skipped, instructions count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.p.f(j$.util.concurrent.q):boolean");
    }
}

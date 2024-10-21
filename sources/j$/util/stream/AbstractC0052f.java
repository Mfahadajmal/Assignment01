package j$.util.stream;

/* renamed from: j$.util.stream.f, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
abstract class AbstractC0052f {
    protected int a;
    protected int b;
    protected long[] c;

    public abstract void clear();

    public final long f() {
        int i = this.b;
        return i == 0 ? this.a : this.c[i] + this.a;
    }
}

package j$.util.stream;

import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

/* renamed from: j$.util.stream.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0066m extends E0 {
    public final /* synthetic */ int b;
    Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0066m(AbstractC0044b abstractC0044b, H0 h0, int i) {
        super(h0);
        this.b = i;
        this.c = abstractC0044b;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.b) {
            case 0:
                if (!((HashSet) this.c).contains(obj)) {
                    ((HashSet) this.c).add(obj);
                    this.a.accept((H0) obj);
                    return;
                }
                return;
            case 1:
                if (((Predicate) ((C0089y) this.c).l).test(obj)) {
                    this.a.accept((H0) obj);
                    return;
                }
                return;
            case 2:
                this.a.accept((H0) ((Function) ((C0089y) this.c).l).apply(obj));
                return;
            case 3:
                this.a.accept(((A0) this.c).k.applyAsInt(obj));
                return;
            default:
                this.a.accept(((B0) this.c).k.applyAsDouble(obj));
                return;
        }
    }

    @Override // j$.util.stream.E0, j$.util.stream.H0
    public void c() {
        switch (this.b) {
            case 0:
                this.c = null;
                this.a.c();
                return;
            default:
                super.c();
                return;
        }
    }

    @Override // j$.util.stream.E0, j$.util.stream.H0
    public void e(long j) {
        switch (this.b) {
            case 0:
                this.c = new HashSet();
                this.a.e(-1L);
                return;
            case 1:
                this.a.e(-1L);
                return;
            default:
                super.e(j);
                return;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0066m(H0 h0) {
        super(h0);
        this.b = 0;
    }
}

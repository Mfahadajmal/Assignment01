package j$.util;

import j$.util.function.Consumer$CC;
import java.util.Map;
import java.util.function.Consumer;

/* renamed from: j$.util.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final /* synthetic */ class C0037m implements Consumer {
    public final /* synthetic */ Consumer a;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.a.accept(new C0038n((Map.Entry) obj));
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }
}
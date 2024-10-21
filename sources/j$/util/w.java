package j$.util;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes2.dex */
public final /* synthetic */ class w implements IntConsumer {
    public final /* synthetic */ Consumer a;

    public /* synthetic */ w(Consumer consumer) {
        this.a = consumer;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.a.accept(Integer.valueOf(i));
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.nio.file.attribute.a.b(this, intConsumer);
    }
}

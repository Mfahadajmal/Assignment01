package com.google.android.accessibility.talkback;

import android.os.BaseBundle;
import j$.util.function.BiConsumer$CC;
import java.util.function.BiConsumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ Object TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Map, java.lang.Object] */
    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        if (this.switching_field != 0) {
            this.TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0$ar$f$0.put((String) obj, (String) obj2);
        } else {
            ((BaseBundle) this.TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0$ar$f$0).putString((String) obj, (String) obj2);
        }
    }

    public final /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        if (this.switching_field != 0) {
            return BiConsumer$CC.$default$andThen(this, biConsumer);
        }
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }
}

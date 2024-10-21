package com.google.common.collect;

import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.gms.common.api.GoogleApi;
import com.google.common.collect.ImmutableMap;
import j$.util.function.BiConsumer$CC;
import j$.util.stream.Collector;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda51 implements BiConsumer {
    public final /* synthetic */ Object CollectCollectors$$ExternalSyntheticLambda51$ar$f$0;
    public final /* synthetic */ Object CollectCollectors$$ExternalSyntheticLambda51$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ CollectCollectors$$ExternalSyntheticLambda51(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.CollectCollectors$$ExternalSyntheticLambda51$ar$f$0 = obj;
        this.CollectCollectors$$ExternalSyntheticLambda51$ar$f$1 = obj2;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, java.util.function.Function] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, java.util.function.Function] */
    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        Object apply;
        Object apply2;
        if (this.switching_field != 0) {
            String str = (String) obj;
            GestureShortcutMapping.TalkBackGesture prioritizedGesture = ((GoogleApi.Settings.Builder) obj2).getPrioritizedGesture();
            if (prioritizedGesture == null) {
                return;
            }
            Object obj3 = this.CollectCollectors$$ExternalSyntheticLambda51$ar$f$1;
            Object obj4 = this.CollectCollectors$$ExternalSyntheticLambda51$ar$f$0;
            if (prioritizedGesture.gestureType == 2) {
                ((HashMap) obj3).put(str, GestureShortcutMapping.getFingerprintGestureString(((GestureShortcutMapping) obj4).context, prioritizedGesture.gestureId));
                return;
            } else {
                ((HashMap) obj3).put(str, GestureShortcutMapping.getGestureString(((GestureShortcutMapping) obj4).context, prioritizedGesture.gestureId));
                return;
            }
        }
        Collector collector = CollectCollectors.TO_IMMUTABLE_LIST;
        apply = this.CollectCollectors$$ExternalSyntheticLambda51$ar$f$0.apply(obj2);
        apply2 = this.CollectCollectors$$ExternalSyntheticLambda51$ar$f$1.apply(obj2);
        ((ImmutableMap.Builder) obj).put$ar$ds$de9b9d28_0(apply, apply2);
    }

    public final /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        if (this.switching_field != 0) {
            return BiConsumer$CC.$default$andThen(this, biConsumer);
        }
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }
}

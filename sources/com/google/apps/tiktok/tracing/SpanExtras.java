package com.google.apps.tiktok.tracing;

import androidx.collection.SimpleArrayMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SpanExtras {
    public static final ContextDataProvider NO_TRACING_KEY$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new ContextDataProvider((char[]) null);
    private final SpanExtras delegate;
    public boolean isFrozen = false;
    public final SimpleArrayMap map;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SpanExtrasImpl extends SpanExtras {
        public static final SpanExtras EMPTY_EXTRAS;
        static final SpanExtras NO_TRACING_EXTRAS;

        static {
            SpanExtras freeze = new SpanExtrasImpl(null, new SimpleArrayMap(0)).freeze();
            EMPTY_EXTRAS = freeze;
            SpanExtrasImpl spanExtrasImpl = new SpanExtrasImpl(freeze, new SimpleArrayMap());
            ContextDataProvider contextDataProvider = SpanExtras.NO_TRACING_KEY$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            ContextDataProvider.checkState(!spanExtrasImpl.isFrozen, "Can't mutate after handing to trace");
            ContextDataProvider.checkState(true ^ spanExtrasImpl.containsKey$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(contextDataProvider), "Key already present");
            spanExtrasImpl.map.put(contextDataProvider, true);
            NO_TRACING_EXTRAS = spanExtrasImpl.freeze();
        }

        public SpanExtrasImpl(SpanExtras spanExtras, SimpleArrayMap simpleArrayMap) {
            super(spanExtras, simpleArrayMap);
        }
    }

    public SpanExtras(SpanExtras spanExtras, SimpleArrayMap simpleArrayMap) {
        if (spanExtras != null) {
            ContextDataProvider.checkArgument(spanExtras.isFrozen);
        }
        this.delegate = spanExtras;
        this.map = simpleArrayMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SpanExtras copyCombine(SpanExtras spanExtras, SpanExtras spanExtras2) {
        boolean z;
        if (spanExtras.isEmpty()) {
            return spanExtras2;
        }
        if (spanExtras2.isEmpty()) {
            return spanExtras;
        }
        ImmutableSet<SpanExtras> of = ImmutableSet.of((Object) spanExtras, (Object) spanExtras2);
        if (of.isEmpty()) {
            return SpanExtrasImpl.EMPTY_EXTRAS;
        }
        if (of.size() == 1) {
            return (SpanExtras) of.iterator().next();
        }
        int i = 0;
        for (SpanExtras spanExtras3 : of) {
            do {
                i += spanExtras3.map.size;
                spanExtras3 = spanExtras3.delegate;
            } while (spanExtras3 != null);
        }
        if (i == 0) {
            return SpanExtrasImpl.EMPTY_EXTRAS;
        }
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap(i);
        for (SpanExtras spanExtras4 : of) {
            do {
                int i2 = 0;
                while (true) {
                    SimpleArrayMap simpleArrayMap2 = spanExtras4.map;
                    if (i2 >= simpleArrayMap2.size) {
                        break;
                    }
                    if (simpleArrayMap.put((ContextDataProvider) simpleArrayMap2.keyAt(i2), spanExtras4.map.valueAt(i2)) == null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ContextDataProvider.checkArgument(z, "Duplicate bindings: %s", spanExtras4.map.keyAt(i2));
                    i2++;
                }
                spanExtras4 = spanExtras4.delegate;
            } while (spanExtras4 != null);
        }
        return new SpanExtrasImpl(null, simpleArrayMap).freeze();
    }

    final boolean containsKey$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(ContextDataProvider contextDataProvider) {
        if (this.map.containsKey(contextDataProvider)) {
            return true;
        }
        SpanExtras spanExtras = this.delegate;
        if (spanExtras != null && spanExtras.containsKey$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(contextDataProvider)) {
            return true;
        }
        return false;
    }

    final SpanExtras freeze() {
        if (!this.isFrozen) {
            this.isFrozen = true;
            SpanExtras spanExtras = this.delegate;
            if (spanExtras != null && this.map.isEmpty()) {
                return spanExtras;
            }
            return this;
        }
        throw new IllegalStateException("Already frozen");
    }

    public final boolean isEmpty() {
        if (this == SpanExtrasImpl.EMPTY_EXTRAS) {
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SpanExtras<");
        for (SpanExtras spanExtras = this; spanExtras != null; spanExtras = spanExtras.delegate) {
            for (int i = 0; i < spanExtras.map.size; i++) {
                sb.append(this.map.valueAt(i));
                sb.append("], ");
            }
        }
        sb.append(">");
        return sb.toString();
    }
}

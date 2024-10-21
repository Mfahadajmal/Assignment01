package kotlin.random;

import java.io.Serializable;
import kotlin.internal.PlatformImplementationsKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Random {
    public static final Default Default = new Default();
    public static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS$ar$class_merging$ar$class_merging.defaultPlatformRandom();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Default extends Random implements Serializable {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class Serialized implements Serializable {
            public static final Serialized INSTANCE = new Serialized();
            private static final long serialVersionUID = 0;

            private Serialized() {
            }

            private final Object readResolve() {
                return Random.Default;
            }
        }

        private final Object writeReplace() {
            return Serialized.INSTANCE;
        }

        @Override // kotlin.random.Random
        public final int nextInt$ar$ds() {
            return Random.defaultRandom.nextInt$ar$ds();
        }
    }

    public int nextInt$ar$ds() {
        throw null;
    }
}

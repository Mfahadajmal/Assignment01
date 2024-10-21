package kotlin.random;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractPlatformRandom extends Random {
    public abstract java.util.Random getImpl();

    @Override // kotlin.random.Random
    public final int nextInt$ar$ds() {
        return getImpl().nextInt(2147418112);
    }
}

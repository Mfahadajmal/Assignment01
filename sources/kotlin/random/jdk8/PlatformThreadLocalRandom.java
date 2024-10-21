package kotlin.random.jdk8;

import j$.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import kotlin.random.AbstractPlatformRandom;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PlatformThreadLocalRandom extends AbstractPlatformRandom {
    @Override // kotlin.random.AbstractPlatformRandom
    public final Random getImpl() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        current.getClass();
        return current;
    }
}

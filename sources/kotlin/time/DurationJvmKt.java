package kotlin.time;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DurationJvmKt {
    public static final boolean durationAssertionsEnabled = false;

    static {
        ThreadLocal[] threadLocalArr = new ThreadLocal[4];
        for (int i = 0; i < 4; i++) {
            threadLocalArr[i] = new ThreadLocal();
        }
    }
}

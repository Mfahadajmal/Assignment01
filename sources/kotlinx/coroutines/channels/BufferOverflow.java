package kotlinx.coroutines.channels;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BufferOverflow {
    public static final int SUSPEND$ar$edu = 1;
    public static final int DROP_OLDEST$ar$edu = 2;
    public static final int DROP_LATEST$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$f49796eb_0 = {SUSPEND$ar$edu, DROP_OLDEST$ar$edu, DROP_LATEST$ar$edu};

    public static /* synthetic */ String toStringGeneratedd0f0220c226c70ff(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return "null";
                }
                return "DROP_LATEST";
            }
            return "DROP_OLDEST";
        }
        return "SUSPEND";
    }

    public static int[] values$ar$edu$8cf50437_0() {
        return new int[]{SUSPEND$ar$edu, DROP_OLDEST$ar$edu, DROP_LATEST$ar$edu};
    }
}

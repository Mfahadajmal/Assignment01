package google.internal.feedback.v1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$ClientContext$LibraryInfo$Platform {
    public static final int PLATFORM_UNKNOWN$ar$edu$ca458a2c_0 = 2;
    public static final int PLATFORM_WEB$ar$edu$ca458a2c_0 = 3;
    public static final int PLATFORM_ANDROID$ar$edu$ca458a2c_0 = 4;
    public static final int PLATFORM_IOS$ar$edu$ca458a2c_0 = 5;
    public static final int UNRECOGNIZED$ar$edu$ca458a2c_0 = 1;
    private static final /* synthetic */ int[] $VALUES$ar$edu$65f29ca0_0 = {PLATFORM_UNKNOWN$ar$edu$ca458a2c_0, PLATFORM_WEB$ar$edu$ca458a2c_0, PLATFORM_ANDROID$ar$edu$ca458a2c_0, PLATFORM_IOS$ar$edu$ca458a2c_0, UNRECOGNIZED$ar$edu$ca458a2c_0};

    public static int getNumber$ar$edu$ca458a2c_0(int i) {
        if (i != UNRECOGNIZED$ar$edu$ca458a2c_0) {
            return i - 2;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static int[] values$ar$edu$64c3b657_0() {
        return new int[]{PLATFORM_UNKNOWN$ar$edu$ca458a2c_0, PLATFORM_WEB$ar$edu$ca458a2c_0, PLATFORM_ANDROID$ar$edu$ca458a2c_0, PLATFORM_IOS$ar$edu$ca458a2c_0, UNRECOGNIZED$ar$edu$ca458a2c_0};
    }
}

package androidx.work;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Logger {
    public static final Object sLock = new Object();
    public static volatile Logger sLogger;

    public Logger() {
    }

    public static void get$ar$ds$16341a92_0() {
        synchronized (sLock) {
            if (sLogger == null) {
                sLogger = new Logger(3);
            }
        }
    }

    public static String tagWithPrefix(String str) {
        StringBuilder sb = new StringBuilder(23);
        sb.append("WM-");
        if (str.length() >= 20) {
            sb.append(str.substring(0, 20));
        } else {
            sb.append(str);
        }
        return sb.toString();
    }

    public Logger(int i) {
        this();
    }
}

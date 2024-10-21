package kotlinx.coroutines.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SystemPropsKt__SystemPropsKt {
    public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static final String systemProp(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }
}

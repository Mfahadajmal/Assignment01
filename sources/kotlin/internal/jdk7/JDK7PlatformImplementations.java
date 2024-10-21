package kotlin.internal.jdk7;

import com.google.mlkit.logging.schema.OnDeviceSegmentationLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JDK7PlatformImplementations extends OnDeviceSegmentationLogEvent {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ReflectSdkVersion {
        public static final Integer sdkVersion;

        static {
            Integer num;
            Object obj;
            Integer num2 = null;
            try {
                obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Throwable unused) {
            }
            if (obj instanceof Integer) {
                num = (Integer) obj;
                if (num != null && num.intValue() > 0) {
                    num2 = num;
                }
                sdkVersion = num2;
            }
            num = null;
            if (num != null) {
                num2 = num;
            }
            sdkVersion = num2;
        }
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceSegmentationLogEvent
    public final void addSuppressed(Throwable th, Throwable th2) {
        if (ReflectSdkVersion.sdkVersion != null && ReflectSdkVersion.sdkVersion.intValue() < 19) {
            super.addSuppressed(th, th2);
        } else {
            th.addSuppressed(th2);
        }
    }
}

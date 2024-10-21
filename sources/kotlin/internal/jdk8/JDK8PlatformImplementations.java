package kotlin.internal.jdk8;

import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JDK8PlatformImplementations extends JDK7PlatformImplementations {

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
    public final Random defaultPlatformRandom() {
        if (ReflectSdkVersion.sdkVersion != null && ReflectSdkVersion.sdkVersion.intValue() < 34) {
            return new FallbackThreadLocalRandom();
        }
        return new PlatformThreadLocalRandom();
    }
}

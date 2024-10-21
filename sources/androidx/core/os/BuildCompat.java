package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BuildCompat {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final int getExtensionVersion(int i) {
            return SdkExtensions.getExtensionVersion(i);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.INSTANCE.getExtensionVersion(30);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.INSTANCE.getExtensionVersion(31);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.INSTANCE.getExtensionVersion(33);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.INSTANCE.getExtensionVersion(1000000);
        }
    }

    public static final boolean isAtLeastPreReleaseCodename(String str, String str2) {
        if (Intrinsics.areEqual("REL", str2)) {
            return false;
        }
        String upperCase = str2.toUpperCase(Locale.ROOT);
        upperCase.getClass();
        String upperCase2 = str.toUpperCase(Locale.ROOT);
        upperCase2.getClass();
        if (upperCase.compareTo(upperCase2) < 0) {
            return false;
        }
        return true;
    }

    public static final boolean isAtLeastS() {
        if (Build.VERSION.SDK_INT >= 31) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 30) {
            return false;
        }
        String str = Build.VERSION.CODENAME;
        str.getClass();
        if (isAtLeastPreReleaseCodename("S", str)) {
            return true;
        }
        return false;
    }

    public static final boolean isAtLeastV() {
        if (Build.VERSION.SDK_INT >= 35) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 34) {
            return false;
        }
        String str = Build.VERSION.CODENAME;
        str.getClass();
        if (isAtLeastPreReleaseCodename("VanillaIceCream", str)) {
            return true;
        }
        return false;
    }
}

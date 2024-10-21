package androidx.core.widget;

import android.widget.PopupWindow;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PopupWindowCompat$Api23Impl {
    public static final Object findAndInstantiateDatabaseImpl$ar$ds(Class cls) {
        String str;
        Package r0 = cls.getPackage();
        r0.getClass();
        String name = r0.getName();
        String canonicalName = cls.getCanonicalName();
        canonicalName.getClass();
        name.getClass();
        if (name.length() != 0) {
            canonicalName = canonicalName.substring(name.length() + 1);
            canonicalName.getClass();
        }
        canonicalName.getClass();
        String replace = canonicalName.replace('.', '_');
        replace.getClass();
        String concat = replace.concat("_Impl");
        try {
            if (name.length() == 0) {
                str = concat;
            } else {
                str = name + '.' + concat;
            }
            Class<?> cls2 = Class.forName(str, true, cls.getClassLoader());
            cls2.getClass();
            return cls2.getDeclaredConstructor(null).newInstance(null);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find implementation for " + cls.getCanonicalName() + ". " + concat + " does not exist. Is Room annotation processor correctly configured?", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot access the constructor ".concat(String.valueOf(cls.getCanonicalName())), e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("Failed to create an instance of ".concat(String.valueOf(cls.getCanonicalName())), e3);
        }
    }

    static boolean getOverlapAnchor(PopupWindow popupWindow) {
        return popupWindow.getOverlapAnchor();
    }

    static int getWindowLayoutType(PopupWindow popupWindow) {
        return popupWindow.getWindowLayoutType();
    }

    public static final boolean isAssignableFrom(KClass kClass, KClass kClass2) {
        kClass.getClass();
        return OnDeviceShadowRemovalLogEvent.getJavaClass(kClass).isAssignableFrom(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass2));
    }

    public static void setOverlapAnchor(PopupWindow popupWindow, boolean z) {
        popupWindow.setOverlapAnchor(z);
    }

    public static void setWindowLayoutType(PopupWindow popupWindow, int i) {
        popupWindow.setWindowLayoutType(i);
    }
}

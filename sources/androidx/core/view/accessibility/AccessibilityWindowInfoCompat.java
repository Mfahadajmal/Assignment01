package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build;
import android.os.LocaleList;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.os.LocaleListCompat;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityWindowInfoCompat {
    public final Object mInfo;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Api21Impl {
        static void getBoundsInScreen(AccessibilityWindowInfo accessibilityWindowInfo, Rect rect) {
            accessibilityWindowInfo.getBoundsInScreen(rect);
        }

        static AccessibilityWindowInfo getChild(AccessibilityWindowInfo accessibilityWindowInfo, int i) {
            return accessibilityWindowInfo.getChild(i);
        }

        static int getChildCount(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getChildCount();
        }

        static int getId(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getId();
        }

        static int getLayer(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getLayer();
        }

        static AccessibilityWindowInfo getParent(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getParent();
        }

        static AccessibilityNodeInfo getRoot(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getRoot();
        }

        static int getType(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getType();
        }

        static boolean isAccessibilityFocused(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isAccessibilityFocused();
        }

        static boolean isActive(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isActive();
        }

        static boolean isFocused(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isFocused();
        }

        static AccessibilityWindowInfo obtain() {
            return AccessibilityWindowInfo.obtain();
        }

        static AccessibilityWindowInfo obtain(AccessibilityWindowInfo accessibilityWindowInfo) {
            return AccessibilityWindowInfo.obtain(accessibilityWindowInfo);
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api30Impl {
        static AccessibilityWindowInfo instantiateAccessibilityWindowInfo() {
            return new AccessibilityWindowInfo();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api34Impl {
        static LocaleList getLocales(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getLocales();
        }

        public static long getTransitionTimeMillis(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getTransitionTimeMillis();
        }
    }

    private AccessibilityWindowInfoCompat(Object obj) {
        this.mInfo = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AccessibilityWindowInfoCompat wrapNonNullInstance(Object obj) {
        if (obj != null) {
            return new AccessibilityWindowInfoCompat(obj);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityWindowInfoCompat)) {
            return false;
        }
        Object obj2 = this.mInfo;
        Object obj3 = ((AccessibilityWindowInfoCompat) obj).mInfo;
        if (obj2 == null) {
            if (obj3 == null) {
                return true;
            }
            return false;
        }
        return obj2.equals(obj3);
    }

    public final void getBoundsInScreen(Rect rect) {
        Api21Impl.getBoundsInScreen((AccessibilityWindowInfo) this.mInfo, rect);
    }

    public final int getId() {
        return Api21Impl.getId((AccessibilityWindowInfo) this.mInfo);
    }

    public final AccessibilityNodeInfoCompat getRoot() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(Api21Impl.getRoot((AccessibilityWindowInfo) this.mInfo));
    }

    public final int getType() {
        return Api21Impl.getType((AccessibilityWindowInfo) this.mInfo);
    }

    public final int hashCode() {
        Object obj = this.mInfo;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final boolean isActive() {
        return Api21Impl.isActive((AccessibilityWindowInfo) this.mInfo);
    }

    public final String toString() {
        String str;
        boolean z;
        long j;
        LocaleListCompat localeListCompat;
        StringBuilder sb = new StringBuilder("AccessibilityWindowInfo[id=");
        Rect rect = new Rect();
        getBoundsInScreen(rect);
        sb.append(getId());
        sb.append(", type=");
        int type = getType();
        boolean z2 = true;
        if (type != 1) {
            if (type != 2) {
                if (type != 3) {
                    if (type != 4) {
                        str = "<UNKNOWN>";
                    } else {
                        str = "TYPE_ACCESSIBILITY_OVERLAY";
                    }
                } else {
                    str = "TYPE_SYSTEM";
                }
            } else {
                str = "TYPE_INPUT_METHOD";
            }
        } else {
            str = "TYPE_APPLICATION";
        }
        sb.append(str);
        sb.append(", layer=");
        sb.append(Api21Impl.getLayer((AccessibilityWindowInfo) this.mInfo));
        sb.append(", bounds=");
        sb.append(rect);
        sb.append(", focused=");
        sb.append(Api21Impl.isFocused((AccessibilityWindowInfo) this.mInfo));
        sb.append(", active=");
        sb.append(isActive());
        sb.append(", hasParent=");
        if (wrapNonNullInstance(Api21Impl.getParent((AccessibilityWindowInfo) this.mInfo)) != null) {
            z = true;
        } else {
            z = false;
        }
        sb.append(z);
        sb.append(", hasChildren=");
        if (Api21Impl.getChildCount((AccessibilityWindowInfo) this.mInfo) <= 0) {
            z2 = false;
        }
        sb.append(z2);
        sb.append(", transitionTime=");
        if (Build.VERSION.SDK_INT >= 34) {
            j = Api34Impl.getTransitionTimeMillis((AccessibilityWindowInfo) this.mInfo);
        } else {
            j = 0;
        }
        sb.append(j);
        sb.append(", locales=");
        if (Build.VERSION.SDK_INT >= 34) {
            localeListCompat = LocaleListCompat.wrap(Api34Impl.getLocales((AccessibilityWindowInfo) this.mInfo));
        } else {
            localeListCompat = LocaleListCompat.sEmptyLocaleList;
        }
        sb.append(localeListCompat);
        sb.append(']');
        return sb.toString();
    }

    public AccessibilityWindowInfoCompat() {
        this.mInfo = Build.VERSION.SDK_INT >= 30 ? Api30Impl.instantiateAccessibilityWindowInfo() : null;
    }
}

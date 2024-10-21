package androidx.navigation;

import _COROUTINE._BOUNDARY;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavigatorProvider {
    public static final AccessibilityNodeInfoCompat.Api33Impl Companion$ar$class_merging$99173bec_0 = new AccessibilityNodeInfoCompat.Api33Impl();
    public static final Map annotationNames = new LinkedHashMap();
    private final Map _navigators = new LinkedHashMap();

    public final void addNavigator$ar$ds(Navigator navigator) {
        String nameForNavigator$navigation_common_release = Companion$ar$class_merging$99173bec_0.getNameForNavigator$navigation_common_release(navigator.getClass());
        if (AccessibilityNodeInfoCompat.Api33Impl.validateName$navigation_common_release$ar$ds(nameForNavigator$navigation_common_release)) {
            Navigator navigator2 = (Navigator) this._navigators.get(nameForNavigator$navigation_common_release);
            if (Intrinsics.areEqual(navigator2, navigator)) {
                return;
            }
            if (navigator2 != null && navigator2.isAttached) {
                throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(navigator2, navigator, "Navigator ", " is replacing an already attached "));
            }
            if (!navigator.isAttached) {
                return;
            }
            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(navigator, "Navigator ", " is already attached to another NavController"));
        }
        throw new IllegalArgumentException("navigator name cannot be an empty string");
    }

    public final Navigator getNavigator(String str) {
        if (AccessibilityNodeInfoCompat.Api33Impl.validateName$navigation_common_release$ar$ds(str)) {
            Navigator navigator = (Navigator) this._navigators.get(str);
            if (navigator != null) {
                return navigator;
            }
            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Could not find Navigator with name \"", "\". You must call NavController.addNavigator() for each navigation type."));
        }
        throw new IllegalArgumentException("navigator name cannot be an empty string");
    }

    public final Map getNavigators() {
        return OnDeviceLanguageIdentificationLogEvent.toMap(this._navigators);
    }
}

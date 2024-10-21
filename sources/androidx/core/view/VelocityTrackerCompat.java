package androidx.core.view;

import android.os.Bundle;
import android.view.VelocityTracker;
import androidx.lifecycle.SavedStateHandle;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VelocityTrackerCompat {
    public static final Map sFallbackTrackers = DesugarCollections.synchronizedMap(new WeakHashMap());

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api34Impl {
        public static final SavedStateHandle createHandle$ar$ds(Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new SavedStateHandle();
                }
                HashMap hashMap = new HashMap();
                for (String str : bundle2.keySet()) {
                    str.getClass();
                    hashMap.put(str, bundle2.get(str));
                }
                return new SavedStateHandle(hashMap);
            }
            ClassLoader classLoader = SavedStateHandle.class.getClassLoader();
            classLoader.getClass();
            bundle.setClassLoader(classLoader);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            if (parcelableArrayList != null && parcelableArrayList2 != null && parcelableArrayList.size() == parcelableArrayList2.size()) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int size = parcelableArrayList.size();
                for (int i = 0; i < size; i++) {
                    Object obj = parcelableArrayList.get(i);
                    obj.getClass();
                    linkedHashMap.put((String) obj, parcelableArrayList2.get(i));
                }
                return new SavedStateHandle(linkedHashMap);
            }
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static float getAxisVelocity(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getAxisVelocity(i);
        }

        static boolean isAxisSupported(VelocityTracker velocityTracker, int i) {
            return velocityTracker.isAxisSupported(i);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int i, int i2) {
            return velocityTracker.getAxisVelocity(i, i2);
        }
    }

    public static VelocityTrackerFallback getFallbackTrackerOrNull(VelocityTracker velocityTracker) {
        return (VelocityTrackerFallback) sFallbackTrackers.get(velocityTracker);
    }
}

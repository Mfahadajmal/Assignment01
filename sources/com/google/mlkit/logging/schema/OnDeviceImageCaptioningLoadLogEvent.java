package com.google.mlkit.logging.schema;

import com.google.android.accessibility.brailleime.input.BrailleInputPlanePhone$$ExternalSyntheticLambda1;
import io.grpc.ServiceProviders$PriorityAccessor;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceImageCaptioningLoadLogEvent {
    private static OnDeviceImageCaptioningLoadLogEvent instance$ar$class_merging$ea171439_0$ar$class_merging;

    public OnDeviceImageCaptioningLoadLogEvent() {
        new HashSet();
    }

    public static synchronized void getDefaultRegistry$ar$ds() {
        synchronized (OnDeviceImageCaptioningLoadLogEvent.class) {
            if (instance$ar$class_merging$ea171439_0$ar$class_merging == null) {
                instance$ar$class_merging$ea171439_0$ar$class_merging = new OnDeviceImageCaptioningLoadLogEvent();
            }
        }
    }

    public static boolean isAndroid(ClassLoader classLoader) {
        try {
            Class.forName("android.app.Application", false, classLoader);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.ServiceLoader, java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ServiceLoader] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.util.List, java.util.ArrayList] */
    public static List loadAll(Class cls, Iterable iterable, ClassLoader classLoader, ServiceProviders$PriorityAccessor serviceProviders$PriorityAccessor) {
        ?? load;
        if (isAndroid(classLoader)) {
            load = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                Class cls2 = (Class) it.next();
                Object obj = null;
                try {
                    obj = cls2.asSubclass(cls).getConstructor(null).newInstance(null);
                } catch (ClassCastException unused) {
                } catch (Throwable th) {
                    throw new ServiceConfigurationError(String.format("Provider %s could not be instantiated %s", cls2.getName(), th), th);
                }
                if (obj != null) {
                    load.add(obj);
                }
            }
        } else {
            load = ServiceLoader.load(cls, classLoader);
            if (!load.iterator().hasNext()) {
                load = ServiceLoader.load(cls);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : load) {
            serviceProviders$PriorityAccessor.isAvailable$ar$ds$a901c8e_0(obj2);
            arrayList.add(obj2);
        }
        Collections.sort(arrayList, Collections.reverseOrder(new BrailleInputPlanePhone$$ExternalSyntheticLambda1(serviceProviders$PriorityAccessor, 4)));
        return DesugarCollections.unmodifiableList(arrayList);
    }
}

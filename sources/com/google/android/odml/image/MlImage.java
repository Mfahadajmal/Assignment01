package com.google.android.odml.image;

import android.graphics.Rect;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MlImage implements Closeable {
    private final Map containerMap;
    private int referenceCount;
    private final Rect roi;

    MlImage(ImageContainer imageContainer, int i, Rect rect, long j, int i2, int i3) {
        HashMap hashMap = new HashMap();
        this.containerMap = hashMap;
        hashMap.put(imageContainer.getImageProperties(), imageContainer);
        Rect rect2 = new Rect();
        this.roi = rect2;
        rect2.set(rect);
        this.referenceCount = 1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        int i = this.referenceCount - 1;
        this.referenceCount = i;
        if (i == 0) {
            Iterator it = this.containerMap.values().iterator();
            while (it.hasNext()) {
                ((ImageContainer) it.next()).close();
            }
        }
    }

    ImageContainer getContainer() {
        return (ImageContainer) this.containerMap.values().iterator().next();
    }

    ImageContainer getContainer(int i) {
        for (Map.Entry entry : this.containerMap.entrySet()) {
            if (((ImageProperties) entry.getKey()).getStorageType() == i) {
                return (ImageContainer) entry.getValue();
            }
        }
        return null;
    }

    ImageContainer getContainer(ImageProperties imageProperties) {
        return (ImageContainer) this.containerMap.get(imageProperties);
    }
}

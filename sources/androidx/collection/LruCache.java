package androidx.collection;

import android.graphics.PorterDuff;
import android.support.v7.widget.DropDownListView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.LinkedHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LruCache {
    private int evictionCount;
    private int hitCount;
    private final DropDownListView.Api21Impl lock$ar$class_merging;
    private final AccessibilityNodeInfoCompat.CollectionItemInfoCompat map$ar$class_merging$ar$class_merging;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache() {
        this(6);
    }

    protected static final void entryRemoved$ar$ds(Object obj, Object obj2) {
        obj.getClass();
        obj2.getClass();
    }

    public static int generateCacheKey(int i, PorterDuff.Mode mode) {
        return ((i + 31) * 31) + mode.hashCode();
    }

    private static final void safeSizeOf$ar$ds(Object obj, Object obj2) {
        obj.getClass();
        obj2.getClass();
    }

    public final Object get(Object obj) {
        synchronized (this.lock$ar$class_merging) {
            Object obj2 = ((LinkedHashMap) this.map$ar$class_merging$ar$class_merging.mInfo).get(obj);
            if (obj2 != null) {
                this.hitCount++;
                return obj2;
            }
            this.missCount++;
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ad, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object put(java.lang.Object r5, java.lang.Object r6) {
        /*
            r4 = this;
            r6.getClass()
            android.support.v7.widget.DropDownListView$Api21Impl r0 = r4.lock$ar$class_merging
            monitor-enter(r0)
            int r1 = r4.putCount     // Catch: java.lang.Throwable -> Lb9
            int r1 = r1 + 1
            r4.putCount = r1     // Catch: java.lang.Throwable -> Lb9
            int r1 = r4.size     // Catch: java.lang.Throwable -> Lb9
            safeSizeOf$ar$ds(r5, r6)     // Catch: java.lang.Throwable -> Lb9
            int r1 = r1 + 1
            r4.size = r1     // Catch: java.lang.Throwable -> Lb9
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r1 = r4.map$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> Lb9
            java.lang.Object r6 = r1.put(r5, r6)     // Catch: java.lang.Throwable -> Lb9
            if (r6 == 0) goto L26
            int r1 = r4.size     // Catch: java.lang.Throwable -> Lb9
            safeSizeOf$ar$ds(r5, r6)     // Catch: java.lang.Throwable -> Lb9
            int r1 = r1 + (-1)
            r4.size = r1     // Catch: java.lang.Throwable -> Lb9
        L26:
            monitor-exit(r0)
            if (r6 == 0) goto L2c
            entryRemoved$ar$ds(r5, r6)
        L2c:
            int r5 = r4.maxSize
        L2e:
            android.support.v7.widget.DropDownListView$Api21Impl r0 = r4.lock$ar$class_merging
            monitor-enter(r0)
            int r1 = r4.size     // Catch: java.lang.Throwable -> Lb6
            if (r1 < 0) goto Lae
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r1 = r4.map$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> Lb6
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lb6
            if (r1 == 0) goto L41
            int r1 = r4.size     // Catch: java.lang.Throwable -> Lb6
            if (r1 != 0) goto Lae
        L41:
            int r1 = r4.size     // Catch: java.lang.Throwable -> Lb6
            if (r1 <= r5) goto Lac
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r1 = r4.map$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> Lb6
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lb6
            if (r1 == 0) goto L4e
            goto Lac
        L4e:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r1 = r4.map$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> Lb6
            java.lang.Object r1 = r1.mInfo     // Catch: java.lang.Throwable -> Lb6
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1     // Catch: java.lang.Throwable -> Lb6
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> Lb6
            r1.getClass()     // Catch: java.lang.Throwable -> Lb6
            boolean r2 = r1 instanceof java.util.List     // Catch: java.lang.Throwable -> Lb6
            r3 = 0
            if (r2 == 0) goto L6f
            java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> Lb6
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lb6
            if (r2 == 0) goto L69
            goto L7e
        L69:
            r2 = 0
            java.lang.Object r3 = r1.get(r2)     // Catch: java.lang.Throwable -> Lb6
            goto L7e
        L6f:
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> Lb6
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> Lb6
            if (r2 != 0) goto L7a
            goto L7e
        L7a:
            java.lang.Object r3 = r1.next()     // Catch: java.lang.Throwable -> Lb6
        L7e:
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch: java.lang.Throwable -> Lb6
            if (r3 != 0) goto L84
            monitor-exit(r0)
            goto Lad
        L84:
            java.lang.Object r1 = r3.getKey()     // Catch: java.lang.Throwable -> Lb6
            java.lang.Object r2 = r3.getValue()     // Catch: java.lang.Throwable -> Lb6
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r3 = r4.map$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> Lb6
            r1.getClass()     // Catch: java.lang.Throwable -> Lb6
            java.lang.Object r3 = r3.mInfo     // Catch: java.lang.Throwable -> Lb6
            java.util.LinkedHashMap r3 = (java.util.LinkedHashMap) r3     // Catch: java.lang.Throwable -> Lb6
            r3.remove(r1)     // Catch: java.lang.Throwable -> Lb6
            int r3 = r4.size     // Catch: java.lang.Throwable -> Lb6
            safeSizeOf$ar$ds(r1, r2)     // Catch: java.lang.Throwable -> Lb6
            int r3 = r3 + (-1)
            r4.size = r3     // Catch: java.lang.Throwable -> Lb6
            int r3 = r4.evictionCount     // Catch: java.lang.Throwable -> Lb6
            int r3 = r3 + 1
            r4.evictionCount = r3     // Catch: java.lang.Throwable -> Lb6
            monitor-exit(r0)
            entryRemoved$ar$ds(r1, r2)
            goto L2e
        Lac:
            monitor-exit(r0)
        Lad:
            return r6
        Lae:
            java.lang.String r5 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lb6
            r6.<init>(r5)     // Catch: java.lang.Throwable -> Lb6
            throw r6     // Catch: java.lang.Throwable -> Lb6
        Lb6:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        Lb9:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final String toString() {
        int i;
        String str;
        synchronized (this.lock$ar$class_merging) {
            int i2 = this.hitCount;
            int i3 = this.missCount + i2;
            if (i3 != 0) {
                i = (i2 * 100) / i3;
            } else {
                i = 0;
            }
            str = "LruCache[maxSize=" + this.maxSize + ",hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + i + "%]";
        }
        return str;
    }

    public LruCache(int i) {
        this.maxSize = i;
        this.map$ar$class_merging$ar$class_merging = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(0);
        this.lock$ar$class_merging = new DropDownListView.Api21Impl();
    }
}

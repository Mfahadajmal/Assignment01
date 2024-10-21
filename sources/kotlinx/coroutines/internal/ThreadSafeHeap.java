package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import kotlinx.atomicfu.AtomicInt;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ThreadSafeHeap {
    private final AtomicInt _size = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
    public ThreadSafeHeapNode[] a;

    private final void swap(int i, int i2) {
        ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.a;
        threadSafeHeapNodeArr.getClass();
        ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i2];
        threadSafeHeapNode.getClass();
        ThreadSafeHeapNode threadSafeHeapNode2 = threadSafeHeapNodeArr[i];
        threadSafeHeapNode2.getClass();
        threadSafeHeapNodeArr[i] = threadSafeHeapNode;
        threadSafeHeapNodeArr[i2] = threadSafeHeapNode2;
        threadSafeHeapNode.setIndex(i);
        threadSafeHeapNode2.setIndex(i2);
    }

    public final ThreadSafeHeapNode firstImpl() {
        ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.a;
        if (threadSafeHeapNodeArr != null) {
            return threadSafeHeapNodeArr[0];
        }
        return null;
    }

    public final int getSize() {
        return this._size.value;
    }

    public final boolean isEmpty() {
        if (getSize() == 0) {
            return true;
        }
        return false;
    }

    public final ThreadSafeHeapNode peek() {
        ThreadSafeHeapNode firstImpl;
        synchronized (this) {
            firstImpl = firstImpl();
        }
        return firstImpl;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0063, code lost:
    
        if (((java.lang.Comparable) r5).compareTo(r6) < 0) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.internal.ThreadSafeHeapNode removeAtImpl(int r8) {
        /*
            r7 = this;
            boolean r0 = kotlinx.coroutines.DebugKt.ASSERTIONS_ENABLED
            kotlinx.coroutines.internal.ThreadSafeHeapNode[] r0 = r7.a
            r0.getClass()
            int r1 = r7.getSize()
            r2 = -1
            int r1 = r1 + r2
            r7.setSize(r1)
            int r1 = r7.getSize()
            if (r8 >= r1) goto L7e
            int r1 = r7.getSize()
            r7.swap(r8, r1)
            int r1 = r8 + (-1)
            if (r8 <= 0) goto L3c
            int r1 = r1 / 2
            r3 = r0[r8]
            r3.getClass()
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r0[r1]
            r4.getClass()
            int r3 = r3.compareTo(r4)
            if (r3 >= 0) goto L3c
            r7.swap(r8, r1)
            r7.siftUpFrom(r1)
            goto L7e
        L3c:
            int r1 = r8 + r8
            int r3 = r1 + 1
            int r4 = r7.getSize()
            if (r3 >= r4) goto L7e
            kotlinx.coroutines.internal.ThreadSafeHeapNode[] r4 = r7.a
            r4.getClass()
            int r1 = r1 + 2
            int r5 = r7.getSize()
            if (r1 >= r5) goto L66
            r5 = r4[r1]
            r5.getClass()
            java.lang.Comparable r5 = (java.lang.Comparable) r5
            r6 = r4[r3]
            r6.getClass()
            int r5 = r5.compareTo(r6)
            if (r5 >= 0) goto L66
            goto L67
        L66:
            r1 = r3
        L67:
            r3 = r4[r8]
            r3.getClass()
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r4[r1]
            r4.getClass()
            int r3 = r3.compareTo(r4)
            if (r3 <= 0) goto L7e
            r7.swap(r8, r1)
            r8 = r1
            goto L3c
        L7e:
            int r8 = r7.getSize()
            r8 = r0[r8]
            r8.getClass()
            r1 = 0
            r8.setHeap(r1)
            r8.setIndex(r2)
            int r2 = r7.getSize()
            r0[r2] = r1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ThreadSafeHeap.removeAtImpl(int):kotlinx.coroutines.internal.ThreadSafeHeapNode");
    }

    public final void setSize(int i) {
        this._size.setValue(i);
    }

    public final void siftUpFrom(int i) {
        while (i > 0) {
            ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.a;
            threadSafeHeapNodeArr.getClass();
            int i2 = (i - 1) >> 1;
            ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i2];
            threadSafeHeapNode.getClass();
            ThreadSafeHeapNode threadSafeHeapNode2 = threadSafeHeapNodeArr[i];
            threadSafeHeapNode2.getClass();
            if (((Comparable) threadSafeHeapNode).compareTo(threadSafeHeapNode2) > 0) {
                swap(i, i2);
                i = i2;
            } else {
                return;
            }
        }
    }
}

package org.chromium.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.chromium.base.ThreadUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ObserverList<E> implements Iterable<E> {
    private int mCount;
    public int mIterationDepth;
    public boolean mNeedsCompact;
    public final List mObservers = new ArrayList();
    private boolean mEnableThreadAsserts = true;
    private final ThreadUtils.ThreadChecker mThreadChecker = new ThreadUtils.ThreadChecker();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ObserverListIterator implements Iterator {
        private int mIndex;
        private boolean mIsExhausted;
        private int mListEndMarker;

        public ObserverListIterator() {
            ObserverList.this.mIterationDepth++;
            this.mListEndMarker = ObserverList.this.mObservers.size();
        }

        private final void compactListIfNeeded() {
            if (!this.mIsExhausted) {
                this.mIsExhausted = true;
                ObserverList observerList = ObserverList.this;
                int i = observerList.mIterationDepth - 1;
                observerList.mIterationDepth = i;
                if (i <= 0 && observerList.mNeedsCompact) {
                    observerList.mNeedsCompact = false;
                    int size = observerList.mObservers.size();
                    while (true) {
                        size--;
                        if (size >= 0) {
                            if (observerList.mObservers.get(size) == null) {
                                observerList.mObservers.remove(size);
                            }
                        } else {
                            return;
                        }
                    }
                }
            }
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            int i = this.mIndex;
            while (i < this.mListEndMarker && ObserverList.this.getObserverAt(i) == null) {
                i++;
            }
            if (i < this.mListEndMarker) {
                return true;
            }
            compactListIfNeeded();
            return false;
        }

        @Override // java.util.Iterator
        public final Object next() {
            while (true) {
                int i = this.mIndex;
                if (i >= this.mListEndMarker || ObserverList.this.getObserverAt(i) != null) {
                    break;
                }
                this.mIndex++;
            }
            int i2 = this.mIndex;
            if (i2 < this.mListEndMarker) {
                ObserverList observerList = ObserverList.this;
                this.mIndex = i2 + 1;
                return observerList.getObserverAt(i2);
            }
            compactListIfNeeded();
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public final void addObserver$ar$ds(Object obj) {
        if (obj != null && !this.mObservers.contains(obj)) {
            this.mObservers.add(obj);
            this.mCount++;
        }
    }

    public final void disableThreadAsserts() {
        this.mEnableThreadAsserts = false;
    }

    public final Object getObserverAt(int i) {
        return this.mObservers.get(i);
    }

    public final boolean isEmpty() {
        if (this.mCount == 0) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new ObserverListIterator();
    }

    public final boolean removeObserver(Object obj) {
        int indexOf;
        if (obj != null && (indexOf = this.mObservers.indexOf(obj)) != -1) {
            if (this.mIterationDepth == 0) {
                this.mObservers.remove(indexOf);
            } else {
                this.mNeedsCompact = true;
                this.mObservers.set(indexOf, null);
            }
            this.mCount--;
            return true;
        }
        return false;
    }
}

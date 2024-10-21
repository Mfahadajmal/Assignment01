package androidx.lifecycle;

import _COROUTINE._BOUNDARY;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LiveData<T> {
    public static final Object NOT_SET = new Object();
    public int mActiveCount;
    public boolean mChangingActiveState;
    public volatile Object mData;
    public final Object mDataLock;
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    public final SafeIterableMap mObservers;
    public volatile Object mPendingData;
    public final Runnable mPostValueRunnable;
    public int mVersion;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AlwaysActiveObserver extends ObserverWrapper {
        public AlwaysActiveObserver(LiveData liveData, Observer observer) {
            super(observer);
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public final boolean shouldBeActive() {
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LifecycleBoundObserver extends ObserverWrapper implements LifecycleEventObserver {
        final LifecycleOwner mOwner;

        public LifecycleBoundObserver(LifecycleOwner lifecycleOwner, Observer observer) {
            super(observer);
            this.mOwner = lifecycleOwner;
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public final void detachObserver() {
            this.mOwner.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public final boolean isAttachedTo(LifecycleOwner lifecycleOwner) {
            if (this.mOwner == lifecycleOwner) {
                return true;
            }
            return false;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State currentState = this.mOwner.getLifecycle().getCurrentState();
            if (currentState != Lifecycle.State.DESTROYED) {
                Lifecycle.State state = null;
                while (state != currentState) {
                    activeStateChanged(shouldBeActive());
                    state = currentState;
                    currentState = this.mOwner.getLifecycle().getCurrentState();
                }
                return;
            }
            LiveData.this.removeObserver(this.mObserver);
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public final boolean shouldBeActive() {
            return this.mOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    public LiveData() {
        this.mDataLock = new Object();
        this.mObservers = new SafeIterableMap();
        this.mActiveCount = 0;
        Object obj = NOT_SET;
        this.mPendingData = obj;
        this.mPostValueRunnable = new SearchView$SearchAutoComplete.AnonymousClass1(this, 13);
        this.mData = obj;
        this.mVersion = -1;
    }

    public static void assertMainThread(String str) {
        if (ArchTaskExecutor.getInstance().isMainThread()) {
        } else {
            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Cannot invoke ", " on a background thread"));
        }
    }

    private final void considerNotify(ObserverWrapper observerWrapper) {
        if (observerWrapper.mActive) {
            if (!observerWrapper.shouldBeActive()) {
                observerWrapper.activeStateChanged(false);
                return;
            }
            int i = observerWrapper.mLastVersion;
            int i2 = this.mVersion;
            if (i < i2) {
                observerWrapper.mLastVersion = i2;
                observerWrapper.mObserver.onChanged(this.mData);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void dispatchingValue(ObserverWrapper observerWrapper) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        while (true) {
            this.mDispatchInvalidated = false;
            if (observerWrapper != null) {
                considerNotify(observerWrapper);
            } else {
                SafeIterableMap.IteratorWithAdditions iteratorWithAdditions = this.mObservers.iteratorWithAdditions();
                while (iteratorWithAdditions.hasNext()) {
                    considerNotify((ObserverWrapper) iteratorWithAdditions.next().getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            }
            if (this.mDispatchInvalidated) {
                observerWrapper = null;
            } else {
                this.mDispatchingValue = false;
                return;
            }
        }
    }

    public final Object getValue() {
        Object obj = this.mData;
        if (obj != NOT_SET) {
            return obj;
        }
        return null;
    }

    public final void observe(LifecycleOwner lifecycleOwner, Observer observer) {
        assertMainThread("observe");
        if (lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) {
            LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(lifecycleOwner, observer);
            ObserverWrapper observerWrapper = (ObserverWrapper) this.mObservers.putIfAbsent(observer, lifecycleBoundObserver);
            if (observerWrapper != null && !observerWrapper.isAttachedTo(lifecycleOwner)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            }
            if (observerWrapper != null) {
                return;
            }
            lifecycleOwner.getLifecycle().addObserver(lifecycleBoundObserver);
        }
    }

    public void removeObserver(Observer observer) {
        assertMainThread("removeObserver");
        ObserverWrapper observerWrapper = (ObserverWrapper) this.mObservers.remove(observer);
        if (observerWrapper == null) {
            return;
        }
        observerWrapper.detachObserver();
        observerWrapper.activeStateChanged(false);
    }

    public void setValue(Object obj) {
        throw null;
    }

    public LiveData(Object obj) {
        this.mDataLock = new Object();
        this.mObservers = new SafeIterableMap();
        this.mActiveCount = 0;
        this.mPendingData = NOT_SET;
        this.mPostValueRunnable = new SearchView$SearchAutoComplete.AnonymousClass1(this, 13);
        this.mData = obj;
        this.mVersion = 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class ObserverWrapper {
        boolean mActive;
        int mLastVersion = -1;
        final Observer mObserver;

        public ObserverWrapper(Observer observer) {
            this.mObserver = observer;
        }

        public final void activeStateChanged(boolean z) {
            int i;
            boolean z2;
            boolean z3;
            if (z != this.mActive) {
                this.mActive = z;
                LiveData liveData = LiveData.this;
                if (true != z) {
                    i = -1;
                } else {
                    i = 1;
                }
                int i2 = liveData.mActiveCount;
                liveData.mActiveCount = i + i2;
                if (!liveData.mChangingActiveState) {
                    liveData.mChangingActiveState = true;
                    while (true) {
                        try {
                            int i3 = liveData.mActiveCount;
                            if (i2 == i3) {
                                break;
                            }
                            if (i2 == 0) {
                                if (i3 > 0) {
                                    i2 = 0;
                                    z2 = true;
                                } else {
                                    i2 = 0;
                                    z2 = false;
                                }
                            } else {
                                z2 = false;
                            }
                            if (i2 > 0 && i3 == 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (z2) {
                                liveData.onActive();
                            } else if (z3) {
                                liveData.onInactive();
                            }
                            i2 = i3;
                        } finally {
                            liveData.mChangingActiveState = false;
                        }
                    }
                }
                if (this.mActive) {
                    LiveData.this.dispatchingValue(this);
                }
            }
        }

        public boolean isAttachedTo(LifecycleOwner lifecycleOwner) {
            return false;
        }

        public abstract boolean shouldBeActive();

        public void detachObserver() {
        }
    }

    protected void onActive() {
    }

    protected void onInactive() {
    }
}

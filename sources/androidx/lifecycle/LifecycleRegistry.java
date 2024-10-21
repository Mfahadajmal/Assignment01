package androidx.lifecycle;

import _COROUTINE._BOUNDARY;
import android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.core.view.DisplayCutoutCompat;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LifecycleRegistry extends Lifecycle {
    public static final DisplayCutoutCompat.Api28Impl Companion$ar$class_merging$95917659_0 = new DisplayCutoutCompat.Api28Impl();
    private int addingObserverCounter;
    private boolean handlingEvent;
    private final WeakReference lifecycleOwner;
    private boolean newEventOccurred;
    private FastSafeIterableMap observerMap = new FastSafeIterableMap();
    public Lifecycle.State state = Lifecycle.State.INITIALIZED;
    private final ArrayList parentStates = new ArrayList();
    private final StateFlowImpl _currentStateFlow$ar$class_merging = StateFlowKt.MutableStateFlow$ar$class_merging(Lifecycle.State.INITIALIZED);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ObserverWithState {
        public Object LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
        public Object LifecycleRegistry$ObserverWithState$ar$state;

        public ObserverWithState() {
        }

        public final void clear() {
            Object obj = this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
            if (obj != null) {
                Arrays.fill((int[]) obj, -1);
            }
            this.LifecycleRegistry$ObserverWithState$ar$state = null;
        }

        /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, androidx.lifecycle.LifecycleEventObserver] */
        public final void dispatchEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State targetState = event.getTargetState();
            this.LifecycleRegistry$ObserverWithState$ar$state = DisplayCutoutCompat.Api28Impl.min$lifecycle_runtime_release$ar$ds((Lifecycle.State) this.LifecycleRegistry$ObserverWithState$ar$state, targetState);
            this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver.onStateChanged(lifecycleOwner, event);
            this.LifecycleRegistry$ObserverWithState$ar$state = targetState;
        }

        public final void ensureSize(int i) {
            Object obj = this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
            if (obj == null) {
                int[] iArr = new int[Math.max(i, 10) + 1];
                this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver = iArr;
                Arrays.fill(iArr, -1);
                return;
            }
            int[] iArr2 = (int[]) obj;
            int length = iArr2.length;
            if (i >= length) {
                while (length <= i) {
                    length += length;
                }
                int[] iArr3 = new int[length];
                this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver = iArr3;
                int length2 = iArr2.length;
                System.arraycopy(obj, 0, iArr3, 0, length2);
                int[] iArr4 = (int[]) this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
                Arrays.fill(iArr4, length2, iArr4.length, -1);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x006c  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x005e  */
        /* JADX WARN: Type inference failed for: r0v13, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v7, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v7, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v1, types: [java.util.List, java.lang.Object] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int invalidateAfter(int r5) {
            /*
                r4 = this;
                java.lang.Object r0 = r4.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver
                r1 = -1
                if (r0 != 0) goto L7
                goto L7f
            L7:
                int[] r0 = (int[]) r0
                int r0 = r0.length
                if (r5 >= r0) goto L7f
                java.lang.Object r0 = r4.LifecycleRegistry$ObserverWithState$ar$state
                if (r0 != 0) goto L12
            L10:
                r0 = r1
                goto L5c
            L12:
                int r0 = r0.size()
                int r0 = r0 + r1
            L17:
                if (r0 < 0) goto L29
                java.lang.Object r2 = r4.LifecycleRegistry$ObserverWithState$ar$state
                java.lang.Object r2 = r2.get(r0)
                android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r2 = (android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r2
                int r3 = r2.mPosition
                if (r3 != r5) goto L26
                goto L2a
            L26:
                int r0 = r0 + (-1)
                goto L17
            L29:
                r2 = 0
            L2a:
                if (r2 == 0) goto L31
                java.lang.Object r0 = r4.LifecycleRegistry$ObserverWithState$ar$state
                r0.remove(r2)
            L31:
                java.lang.Object r0 = r4.LifecycleRegistry$ObserverWithState$ar$state
                int r0 = r0.size()
                r2 = 0
            L38:
                if (r2 >= r0) goto L4a
                java.lang.Object r3 = r4.LifecycleRegistry$ObserverWithState$ar$state
                java.lang.Object r3 = r3.get(r2)
                android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r3 = (android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r3
                int r3 = r3.mPosition
                if (r3 < r5) goto L47
                goto L4b
            L47:
                int r2 = r2 + 1
                goto L38
            L4a:
                r2 = r1
            L4b:
                if (r2 == r1) goto L10
                java.lang.Object r0 = r4.LifecycleRegistry$ObserverWithState$ar$state
                java.lang.Object r0 = r0.get(r2)
                android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = (android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r0
                java.lang.Object r3 = r4.LifecycleRegistry$ObserverWithState$ar$state
                r3.remove(r2)
                int r0 = r0.mPosition
            L5c:
                if (r0 != r1) goto L6c
                java.lang.Object r0 = r4.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver
                int[] r0 = (int[]) r0
                int r2 = r0.length
                java.util.Arrays.fill(r0, r5, r2, r1)
                java.lang.Object r5 = r4.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver
                int[] r5 = (int[]) r5
                int r5 = r5.length
                return r5
            L6c:
                int r0 = r0 + 1
                java.lang.Object r2 = r4.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver
                int[] r2 = (int[]) r2
                int r2 = r2.length
                int r0 = java.lang.Math.min(r0, r2)
                java.lang.Object r2 = r4.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver
                int[] r2 = (int[]) r2
                java.util.Arrays.fill(r2, r5, r0, r1)
                return r0
            L7f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.LifecycleRegistry.ObserverWithState.invalidateAfter(int):int");
        }

        /* JADX WARN: Type inference failed for: r0v4, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v3, types: [java.util.List, java.lang.Object] */
        public final void offsetForAddition(int i, int i2) {
            Object obj = this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
            if (obj != null && i < ((int[]) obj).length) {
                int i3 = i + i2;
                ensureSize(i3);
                Object obj2 = this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
                System.arraycopy(obj2, i, obj2, i3, (((int[]) obj2).length - i) - i2);
                Arrays.fill((int[]) this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver, i, i3, -1);
                ?? r0 = this.LifecycleRegistry$ObserverWithState$ar$state;
                if (r0 != 0) {
                    for (int size = r0.size() - 1; size >= 0; size--) {
                        StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.LifecycleRegistry$ObserverWithState$ar$state.get(size);
                        int i4 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition;
                        if (i4 >= i) {
                            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition = i4 + i2;
                        }
                    }
                }
            }
        }

        /* JADX WARN: Type inference failed for: r1v3, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v6, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v9, types: [java.util.List, java.lang.Object] */
        public final void offsetForRemoval(int i, int i2) {
            Object obj = this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
            if (obj != null && i < ((int[]) obj).length) {
                int i3 = i + i2;
                ensureSize(i3);
                Object obj2 = this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
                System.arraycopy(obj2, i3, obj2, i, (((int[]) obj2).length - i) - i2);
                int[] iArr = (int[]) this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver;
                int length = iArr.length;
                Arrays.fill(iArr, length - i2, length, -1);
                ?? r1 = this.LifecycleRegistry$ObserverWithState$ar$state;
                if (r1 != 0) {
                    for (int size = r1.size() - 1; size >= 0; size--) {
                        StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.LifecycleRegistry$ObserverWithState$ar$state.get(size);
                        int i4 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition;
                        if (i4 >= i) {
                            if (i4 < i3) {
                                this.LifecycleRegistry$ObserverWithState$ar$state.remove(size);
                            } else {
                                staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition = i4 - i2;
                            }
                        }
                    }
                }
            }
        }

        public ObserverWithState(LifecycleObserver lifecycleObserver, Lifecycle.State state) {
            Object reflectiveGenericLifecycleObserver;
            state.getClass();
            Lifecycling lifecycling = Lifecycling.INSTANCE;
            boolean z = lifecycleObserver instanceof LifecycleEventObserver;
            boolean z2 = lifecycleObserver instanceof DefaultLifecycleObserver;
            if (z && z2) {
                reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) lifecycleObserver, (LifecycleEventObserver) lifecycleObserver);
            } else if (z2) {
                reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) lifecycleObserver, null);
            } else if (z) {
                reflectiveGenericLifecycleObserver = (LifecycleEventObserver) lifecycleObserver;
            } else {
                Class<?> cls = lifecycleObserver.getClass();
                if (Lifecycling.INSTANCE.getObserverConstructorType(cls) == 2) {
                    Object obj = Lifecycling.classToAdapters.get(cls);
                    obj.getClass();
                    List list = (List) obj;
                    if (list.size() == 1) {
                        reflectiveGenericLifecycleObserver = new SavedStateHandleAttacher(Lifecycling.createGeneratedAdapter$ar$ds((Constructor) list.get(0), lifecycleObserver), 2);
                    } else {
                        int size = list.size();
                        GeneratedAdapter[] generatedAdapterArr = new GeneratedAdapter[size];
                        for (int i = 0; i < size; i++) {
                            generatedAdapterArr[i] = Lifecycling.createGeneratedAdapter$ar$ds((Constructor) list.get(i), lifecycleObserver);
                        }
                        reflectiveGenericLifecycleObserver = new SavedStateHandleAttacher(generatedAdapterArr, 1);
                    }
                } else {
                    reflectiveGenericLifecycleObserver = new ReflectiveGenericLifecycleObserver(lifecycleObserver);
                }
            }
            this.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver = reflectiveGenericLifecycleObserver;
            this.LifecycleRegistry$ObserverWithState$ar$state = state;
        }
    }

    public LifecycleRegistry(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = new WeakReference(lifecycleOwner);
    }

    private final Lifecycle.State calculateTargetState(LifecycleObserver lifecycleObserver) {
        SafeIterableMap.Entry entry;
        Object obj;
        FastSafeIterableMap fastSafeIterableMap = this.observerMap;
        Lifecycle.State state = null;
        if (fastSafeIterableMap.contains(lifecycleObserver)) {
            entry = ((SafeIterableMap.Entry) fastSafeIterableMap.mHashMap.get(lifecycleObserver)).mPrevious;
        } else {
            entry = null;
        }
        if (entry != null) {
            obj = ((ObserverWithState) entry.mValue).LifecycleRegistry$ObserverWithState$ar$state;
        } else {
            obj = null;
        }
        if (!this.parentStates.isEmpty()) {
            state = (Lifecycle.State) this.parentStates.get(r0.size() - 1);
        }
        return DisplayCutoutCompat.Api28Impl.min$lifecycle_runtime_release$ar$ds(DisplayCutoutCompat.Api28Impl.min$lifecycle_runtime_release$ar$ds(this.state, (Lifecycle.State) obj), state);
    }

    private static final void enforceMainThreadIfNeeded$ar$ds(String str) {
        if (ArchTaskExecutor.getInstance().isMainThread()) {
        } else {
            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Method ", " must be called on the main thread"));
        }
    }

    private final void moveToState(Lifecycle.State state) {
        Lifecycle.State state2 = this.state;
        if (state2 != state) {
            if (state2 == Lifecycle.State.INITIALIZED && state == Lifecycle.State.DESTROYED) {
                throw new IllegalStateException("State must be at least CREATED to move to " + state + ", but was " + this.state + " in component " + this.lifecycleOwner.get());
            }
            this.state = state;
            if (!this.handlingEvent && this.addingObserverCounter == 0) {
                this.handlingEvent = true;
                sync();
                this.handlingEvent = false;
                if (this.state == Lifecycle.State.DESTROYED) {
                    this.observerMap = new FastSafeIterableMap();
                    return;
                }
                return;
            }
            this.newEventOccurred = true;
        }
    }

    private final void popParentState() {
        this.parentStates.remove(r0.size() - 1);
    }

    private final void pushParentState(Lifecycle.State state) {
        this.parentStates.add(state);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002f, code lost:
    
        r6.newEventOccurred = false;
        r6._currentStateFlow$ar$class_merging.setValue(r6.state);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void sync() {
        /*
            Method dump skipped, instructions count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.LifecycleRegistry.sync():void");
    }

    @Override // androidx.lifecycle.Lifecycle
    public final void addObserver(LifecycleObserver lifecycleObserver) {
        Lifecycle.State state;
        Object obj;
        LifecycleOwner lifecycleOwner;
        boolean z;
        lifecycleObserver.getClass();
        enforceMainThreadIfNeeded$ar$ds("addObserver");
        if (this.state == Lifecycle.State.DESTROYED) {
            state = Lifecycle.State.DESTROYED;
        } else {
            state = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, state);
        FastSafeIterableMap fastSafeIterableMap = this.observerMap;
        SafeIterableMap.Entry entry = fastSafeIterableMap.get(lifecycleObserver);
        if (entry != null) {
            obj = entry.mValue;
        } else {
            fastSafeIterableMap.mHashMap.put(lifecycleObserver, fastSafeIterableMap.put(lifecycleObserver, observerWithState));
            obj = null;
        }
        if (((ObserverWithState) obj) == null && (lifecycleOwner = (LifecycleOwner) this.lifecycleOwner.get()) != null) {
            if (this.addingObserverCounter == 0 && !this.handlingEvent) {
                z = false;
            } else {
                z = true;
            }
            Lifecycle.State calculateTargetState = calculateTargetState(lifecycleObserver);
            this.addingObserverCounter++;
            while (((Lifecycle.State) observerWithState.LifecycleRegistry$ObserverWithState$ar$state).compareTo(calculateTargetState) < 0 && this.observerMap.contains(lifecycleObserver)) {
                pushParentState((Lifecycle.State) observerWithState.LifecycleRegistry$ObserverWithState$ar$state);
                Lifecycle.Event upFrom$ar$ds = Lifecycle.Event.Companion.upFrom$ar$ds((Lifecycle.State) observerWithState.LifecycleRegistry$ObserverWithState$ar$state);
                if (upFrom$ar$ds != null) {
                    observerWithState.dispatchEvent(lifecycleOwner, upFrom$ar$ds);
                    popParentState();
                    calculateTargetState = calculateTargetState(lifecycleObserver);
                } else {
                    Object obj2 = observerWithState.LifecycleRegistry$ObserverWithState$ar$state;
                    Objects.toString(obj2);
                    throw new IllegalStateException("no event up from ".concat(String.valueOf(obj2)));
                }
            }
            if (!z) {
                sync();
            }
            this.addingObserverCounter--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public final Lifecycle.State getCurrentState() {
        return this.state;
    }

    public final void handleLifecycleEvent(Lifecycle.Event event) {
        event.getClass();
        enforceMainThreadIfNeeded$ar$ds("handleLifecycleEvent");
        moveToState(event.getTargetState());
    }

    @Override // androidx.lifecycle.Lifecycle
    public final void removeObserver(LifecycleObserver lifecycleObserver) {
        lifecycleObserver.getClass();
        enforceMainThreadIfNeeded$ar$ds("removeObserver");
        this.observerMap.remove(lifecycleObserver);
    }

    public final void setCurrentState(Lifecycle.State state) {
        state.getClass();
        enforceMainThreadIfNeeded$ar$ds("setCurrentState");
        moveToState(state);
    }
}

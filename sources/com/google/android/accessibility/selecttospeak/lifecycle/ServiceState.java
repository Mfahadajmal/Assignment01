package com.google.android.accessibility.selecttospeak.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ServiceState implements LifecycleOwner {
    public boolean isZombieState;
    public final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private int current$ar$edu = State.INACTIVE$ar$edu;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class State {
        public static final int INACTIVE$ar$edu = 1;
        public static final int IDLE$ar$edu = 2;
        public static final int DRAWING$ar$edu = 3;
        public static final int PROCESSING_SELECTION$ar$edu = 4;
        public static final int PROCESSING_CONTINUOUS_READING$ar$edu = 5;
        public static final int READING_SELECTION$ar$edu = 6;
        public static final int CONTINUOUS_READING$ar$edu = 7;
        public static final int CONTINUOUS_READING_PAUSED$ar$edu = 8;
        private static final /* synthetic */ int[] $VALUES$ar$edu$43a49122_0 = {INACTIVE$ar$edu, IDLE$ar$edu, DRAWING$ar$edu, PROCESSING_SELECTION$ar$edu, PROCESSING_CONTINUOUS_READING$ar$edu, READING_SELECTION$ar$edu, CONTINUOUS_READING$ar$edu, CONTINUOUS_READING_PAUSED$ar$edu};

        public static int[] values$ar$edu$c4a67464_0() {
            return new int[]{INACTIVE$ar$edu, IDLE$ar$edu, DRAWING$ar$edu, PROCESSING_SELECTION$ar$edu, PROCESSING_CONTINUOUS_READING$ar$edu, READING_SELECTION$ar$edu, CONTINUOUS_READING$ar$edu, CONTINUOUS_READING_PAUSED$ar$edu};
        }
    }

    public final int getCurrent$ar$edu() {
        if (this.isZombieState) {
            return State.INACTIVE$ar$edu;
        }
        return this.current$ar$edu;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    public final boolean isIdle() {
        if (getCurrent$ar$edu() == State.IDLE$ar$edu) {
            return true;
        }
        return false;
    }

    public final boolean isInactive() {
        if (getCurrent$ar$edu() == State.INACTIVE$ar$edu) {
            return true;
        }
        return false;
    }

    public final void setCurrent$ar$edu(int i) {
        if (i != 0) {
            int i2 = State.INACTIVE$ar$edu;
            int i3 = i - 1;
            if (i3 != 0) {
                if (i3 == 1) {
                    this.lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
                }
            } else {
                this.lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
            }
            this.current$ar$edu = i;
            return;
        }
        throw null;
    }
}

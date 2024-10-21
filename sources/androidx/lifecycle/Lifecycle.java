package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Lifecycle {
    public final AtomicReference internalScopeRef = new AtomicReference(null);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY;

        public static final Companion Companion = new Companion();

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Companion {
            public static final Event upFrom$ar$ds(State state) {
                state.getClass();
                int ordinal = state.ordinal();
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            return null;
                        }
                        return Event.ON_RESUME;
                    }
                    return Event.ON_START;
                }
                return Event.ON_CREATE;
            }
        }

        public final State getTargetState() {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            if (ordinal != 4) {
                                if (ordinal == 5) {
                                    return State.DESTROYED;
                                }
                                toString();
                                throw new IllegalArgumentException(toString().concat(" has no target state"));
                            }
                        }
                    } else {
                        return State.RESUMED;
                    }
                }
                return State.STARTED;
            }
            return State.CREATED;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        public final boolean isAtLeast(State state) {
            state.getClass();
            if (compareTo(state) >= 0) {
                return true;
            }
            return false;
        }
    }

    public abstract void addObserver(LifecycleObserver lifecycleObserver);

    public abstract State getCurrentState();

    public abstract void removeObserver(LifecycleObserver lifecycleObserver);
}

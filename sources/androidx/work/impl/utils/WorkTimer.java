package androidx.work.impl.utils;

import androidx.lifecycle.ViewModelStore;
import androidx.work.Logger;
import androidx.work.impl.ToContinuation;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkTimer {
    private static final String TAG = Logger.tagWithPrefix("WorkTimer");
    public final ViewModelStore mRunnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Map mTimerMap = new HashMap();
    public final Map mListeners = new HashMap();
    public final Object mLock = new Object();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TimeLimitExceededListener {
        void onTimeLimitExceeded(WorkGenerationalId workGenerationalId);
    }

    public WorkTimer(ViewModelStore viewModelStore) {
        this.mRunnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = viewModelStore;
    }

    public final void stopTimer(WorkGenerationalId workGenerationalId) {
        synchronized (this.mLock) {
            if (((ToContinuation) this.mTimerMap.remove(workGenerationalId)) != null) {
                Logger.get$ar$ds$16341a92_0();
                Objects.toString(workGenerationalId);
                this.mListeners.remove(workGenerationalId);
            }
        }
    }
}

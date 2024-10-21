package androidx.work.impl.utils;

import androidx.lifecycle.ViewModelStore;
import androidx.work.Logger;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StopWorkRunnable implements Runnable {
    private final Processor processor;
    private final int reason;
    private final boolean stopInForeground;
    private final ViewModelStore token$ar$class_merging$78dbe0c7_0$ar$class_merging;

    public StopWorkRunnable(Processor processor, ViewModelStore viewModelStore, boolean z, int i) {
        this.processor = processor;
        this.token$ar$class_merging$78dbe0c7_0$ar$class_merging = viewModelStore;
        this.stopInForeground = z;
        this.reason = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WorkerWrapper cleanUpWorkerUnsafe;
        if (this.stopInForeground) {
            Processor processor = this.processor;
            ViewModelStore viewModelStore = this.token$ar$class_merging$78dbe0c7_0$ar$class_merging;
            int i = this.reason;
            String str = ((WorkGenerationalId) viewModelStore.ViewModelStore$ar$map).workSpecId;
            synchronized (processor.mLock) {
                cleanUpWorkerUnsafe = processor.cleanUpWorkerUnsafe(str);
            }
            Processor.interrupt$ar$ds(cleanUpWorkerUnsafe, i);
        } else {
            Processor processor2 = this.processor;
            ViewModelStore viewModelStore2 = this.token$ar$class_merging$78dbe0c7_0$ar$class_merging;
            int i2 = this.reason;
            String str2 = ((WorkGenerationalId) viewModelStore2.ViewModelStore$ar$map).workSpecId;
            synchronized (processor2.mLock) {
                if (processor2.mForegroundWorkMap.get(str2) != null) {
                    Logger.get$ar$ds$16341a92_0();
                } else {
                    Set set = (Set) processor2.mWorkRuns.get(str2);
                    if (set != null && set.contains(viewModelStore2)) {
                        Processor.interrupt$ar$ds(processor2.cleanUpWorkerUnsafe(str2), i2);
                    }
                }
            }
        }
        Logger.get$ar$ds$16341a92_0();
        Logger.tagWithPrefix("StopWorkRunnable");
        String str3 = ((WorkGenerationalId) this.token$ar$class_merging$78dbe0c7_0$ar$class_merging.ViewModelStore$ar$map).workSpecId;
    }
}

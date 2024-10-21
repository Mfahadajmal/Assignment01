package androidx.work;

import android.support.v7.view.WindowCallbackWrapper;
import androidx.lifecycle.ViewModelStore;
import androidx.preference.Preference;
import androidx.transition.ViewUtilsApi19;
import androidx.transition.ViewUtilsApi21;
import java.util.concurrent.Executor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Configuration {
    public final Executor executor = ViewUtilsApi21.Api29Impl.createDefaultExecutor(false);
    public final CoroutineContext workerCoroutineContext = Dispatchers.Default;
    public final Executor taskExecutor = ViewUtilsApi21.Api29Impl.createDefaultExecutor(true);
    public final WindowCallbackWrapper.Api26Impl clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging = new WindowCallbackWrapper.Api26Impl(null, null);
    public final WorkerFactory workerFactory = DefaultWorkerFactory.INSTANCE;
    public final InputMergerFactory inputMergerFactory = NoOpInputMergerFactory.INSTANCE;
    public final ViewModelStore runnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new ViewModelStore((short[]) null);
    public final int minimumLoggingLevel = 4;
    public final int maxJobSchedulerId = Preference.DEFAULT_ORDER;
    public final int maxSchedulerLimit = 20;
    public final int contentUriTriggerWorkersLimit = 8;
    public final boolean isMarkingJobsAsImportantWhileForeground = true;
    public final ViewUtilsApi19.Api29Impl tracer$ar$class_merging$ar$class_merging = new ViewUtilsApi19.Api29Impl();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public final int loggingLevel = 4;
        public final int maxJobSchedulerId = Preference.DEFAULT_ORDER;
        public final int maxSchedulerLimit = 20;
        public final int contentUriTriggerWorkersLimit = 8;
        public final boolean markJobsAsImportantWhileForeground = true;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Provider {
        Configuration getWorkManagerConfiguration();
    }

    public Configuration(Builder builder) {
    }
}

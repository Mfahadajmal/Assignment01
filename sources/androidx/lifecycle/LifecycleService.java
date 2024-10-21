package androidx.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.lifecycle.Lifecycle;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LifecycleService extends Service implements LifecycleOwner {
    private final ExecutionList.RunnableExecutorPair dispatcher$ar$class_merging$ar$class_merging = new ExecutionList.RunnableExecutorPair((LifecycleOwner) this);

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return (Lifecycle) this.dispatcher$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        intent.getClass();
        this.dispatcher$ar$class_merging$ar$class_merging.postDispatchRunnable(Lifecycle.Event.ON_START);
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.dispatcher$ar$class_merging$ar$class_merging.postDispatchRunnable(Lifecycle.Event.ON_CREATE);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        ExecutionList.RunnableExecutorPair runnableExecutorPair = this.dispatcher$ar$class_merging$ar$class_merging;
        runnableExecutorPair.postDispatchRunnable(Lifecycle.Event.ON_STOP);
        runnableExecutorPair.postDispatchRunnable(Lifecycle.Event.ON_DESTROY);
        super.onDestroy();
    }

    @Override // android.app.Service
    public final void onStart(Intent intent, int i) {
        this.dispatcher$ar$class_merging$ar$class_merging.postDispatchRunnable(Lifecycle.Event.ON_START);
        super.onStart(intent, i);
    }
}

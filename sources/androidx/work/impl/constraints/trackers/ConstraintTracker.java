package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ConstraintTracker {
    public final Context appContext;
    public Object currentState;
    public final LinkedHashSet listeners;
    public final Object lock;
    private final WorkManagerTaskExecutor taskExecutor$ar$class_merging;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConstraintTracker(Context context, WorkManagerTaskExecutor workManagerTaskExecutor) {
        this.taskExecutor$ar$class_merging = workManagerTaskExecutor;
        Context applicationContext = context.getApplicationContext();
        applicationContext.getClass();
        this.appContext = applicationContext;
        this.lock = new Object();
        this.listeners = new LinkedHashSet();
    }

    public abstract Object readSystemState();

    public final void setState(Object obj) {
        synchronized (this.lock) {
            Object obj2 = this.currentState;
            if (obj2 != null && Intrinsics.areEqual(obj2, obj)) {
                return;
            }
            this.currentState = obj;
            this.taskExecutor$ar$class_merging.getMainThreadExecutor().execute(new DelayedWorkTracker.AnonymousClass1(OnDeviceLanguageIdentificationLogEvent.toList(this.listeners), this, 3, (char[]) null));
        }
    }

    public abstract void startTracking();

    public abstract void stopTracking();
}

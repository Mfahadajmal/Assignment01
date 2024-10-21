package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.lifecycle.ViewModelStore;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.constraints.ConstraintsState$ConstraintsNotMet;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemForegroundDispatcher implements OnConstraintsStateChangedListener, ExecutionListener {
    static final String TAG = Logger.tagWithPrefix("SystemFgDispatcher");
    public Callback mCallback;
    public final ViewModelStore mConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context mContext;
    WorkGenerationalId mCurrentForegroundId;
    final Map mForegroundInfoById;
    public final Object mLock = new Object();
    public final WorkManagerTaskExecutor mTaskExecutor$ar$class_merging;
    public final Map mTrackedWorkSpecs;
    public final WorkManagerImpl mWorkManagerImpl;
    public final Map mWorkSpecById;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Callback {
        void cancelNotification(int i);

        void notify(int i, Notification notification);

        void startForeground(int i, int i2, Notification notification);

        void stop();
    }

    public SystemForegroundDispatcher(Context context) {
        this.mContext = context;
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
        this.mWorkManagerImpl = workManagerImpl;
        this.mTaskExecutor$ar$class_merging = workManagerImpl.mWorkTaskExecutor$ar$class_merging;
        this.mCurrentForegroundId = null;
        this.mForegroundInfoById = new LinkedHashMap();
        this.mTrackedWorkSpecs = new HashMap();
        this.mWorkSpecById = new HashMap();
        this.mConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging = new ViewModelStore(workManagerImpl.mTrackers$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        workManagerImpl.mProcessor.addExecutionListener(this);
    }

    public final void handleNotify(Intent intent) {
        if (this.mCallback != null) {
            int i = 0;
            int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
            int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
            WorkGenerationalId workGenerationalId = new WorkGenerationalId(intent.getStringExtra("KEY_WORKSPEC_ID"), intent.getIntExtra("KEY_GENERATION", 0));
            Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
            Logger.get$ar$ds$16341a92_0();
            if (notification != null) {
                ForegroundInfo foregroundInfo = new ForegroundInfo(intExtra, notification, intExtra2);
                this.mForegroundInfoById.put(workGenerationalId, foregroundInfo);
                ForegroundInfo foregroundInfo2 = (ForegroundInfo) this.mForegroundInfoById.get(this.mCurrentForegroundId);
                if (foregroundInfo2 == null) {
                    this.mCurrentForegroundId = workGenerationalId;
                } else {
                    this.mCallback.notify(intExtra, notification);
                    if (Build.VERSION.SDK_INT >= 29) {
                        Iterator it = this.mForegroundInfoById.entrySet().iterator();
                        while (it.hasNext()) {
                            i |= ((ForegroundInfo) ((Map.Entry) it.next()).getValue()).mForegroundServiceType;
                        }
                        foregroundInfo = new ForegroundInfo(foregroundInfo2.mNotificationId, foregroundInfo2.mNotification, i);
                    } else {
                        foregroundInfo = foregroundInfo2;
                    }
                }
                this.mCallback.startForeground(foregroundInfo.mNotificationId, foregroundInfo.mForegroundServiceType, foregroundInfo.mNotification);
                return;
            }
            throw new IllegalArgumentException("Notification passed in the intent was null.");
        }
        throw new IllegalStateException("handleNotify was called on the destroyed dispatcher");
    }

    @Override // androidx.work.impl.constraints.OnConstraintsStateChangedListener
    public final void onConstraintsStateChanged$ar$class_merging(WorkSpec workSpec, SystemJobService.Api24Impl api24Impl) {
        if (api24Impl instanceof ConstraintsState$ConstraintsNotMet) {
            Logger.get$ar$ds$16341a92_0();
            WorkManagerImpl workManagerImpl = this.mWorkManagerImpl;
            WorkGenerationalId generationalId = AppCompatTextHelper.Api24Impl.generationalId(workSpec);
            WorkManagerTaskExecutor workManagerTaskExecutor = workManagerImpl.mWorkTaskExecutor$ar$class_merging;
            Processor processor = workManagerImpl.mProcessor;
            ViewModelStore viewModelStore = new ViewModelStore(generationalId, (byte[]) null);
            processor.getClass();
            workManagerTaskExecutor.executeOnTaskThread(new StopWorkRunnable(processor, viewModelStore, true, -512));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onDestroy() {
        this.mCallback = null;
        synchronized (this.mLock) {
            Iterator it = this.mTrackedWorkSpecs.values().iterator();
            while (it.hasNext()) {
                ((Job) it.next()).cancel(null);
            }
        }
        this.mWorkManagerImpl.mProcessor.removeExecutionListener(this);
    }

    @Override // androidx.work.impl.ExecutionListener
    public final void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        Job job;
        Map.Entry entry;
        synchronized (this.mLock) {
            if (((WorkSpec) this.mWorkSpecById.remove(workGenerationalId)) != null) {
                job = (Job) this.mTrackedWorkSpecs.remove(workGenerationalId);
            } else {
                job = null;
            }
            if (job != null) {
                job.cancel(null);
            }
        }
        ForegroundInfo foregroundInfo = (ForegroundInfo) this.mForegroundInfoById.remove(workGenerationalId);
        if (workGenerationalId.equals(this.mCurrentForegroundId)) {
            if (this.mForegroundInfoById.size() > 0) {
                Iterator it = this.mForegroundInfoById.entrySet().iterator();
                Object next = it.next();
                while (true) {
                    entry = (Map.Entry) next;
                    if (!it.hasNext()) {
                        break;
                    } else {
                        next = it.next();
                    }
                }
                this.mCurrentForegroundId = (WorkGenerationalId) entry.getKey();
                if (this.mCallback != null) {
                    ForegroundInfo foregroundInfo2 = (ForegroundInfo) entry.getValue();
                    this.mCallback.startForeground(foregroundInfo2.mNotificationId, foregroundInfo2.mForegroundServiceType, foregroundInfo2.mNotification);
                    this.mCallback.cancelNotification(foregroundInfo2.mNotificationId);
                }
            } else {
                this.mCurrentForegroundId = null;
            }
        }
        Callback callback = this.mCallback;
        if (foregroundInfo != null && callback != null) {
            Logger.get$ar$ds$16341a92_0();
            int i = foregroundInfo.mNotificationId;
            Objects.toString(workGenerationalId);
            int i2 = foregroundInfo.mForegroundServiceType;
            callback.cancelNotification(foregroundInfo.mNotificationId);
        }
    }
}

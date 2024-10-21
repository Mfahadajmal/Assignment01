package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.WindowCallbackWrapper;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkName;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CommandHandler implements ExecutionListener {
    public static final String TAG = Logger.tagWithPrefix("CommandHandler");
    public final WindowCallbackWrapper.Api26Impl mClock$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Context mContext;
    public final WorkName mStartStopTokens$ar$class_merging$ar$class_merging;
    public final Map mPendingDelayMet = new HashMap();
    public final Object mLock = new Object();

    public CommandHandler(Context context, WindowCallbackWrapper.Api26Impl api26Impl, WorkName workName) {
        this.mContext = context;
        this.mClock$ar$class_merging$ar$class_merging$ar$class_merging = api26Impl;
        this.mStartStopTokens$ar$class_merging$ar$class_merging = workName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent createConstraintsChangedIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent createDelayMetIntent(Context context, WorkGenerationalId workGenerationalId) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        writeWorkGenerationalId$ar$ds(intent, workGenerationalId);
        return intent;
    }

    public static Intent createScheduleWorkIntent(Context context, WorkGenerationalId workGenerationalId) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        writeWorkGenerationalId$ar$ds(intent, workGenerationalId);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WorkGenerationalId readWorkGenerationalId(Intent intent) {
        return new WorkGenerationalId(intent.getStringExtra("KEY_WORKSPEC_ID"), intent.getIntExtra("KEY_WORKSPEC_GENERATION", 0));
    }

    public static void writeWorkGenerationalId$ar$ds(Intent intent, WorkGenerationalId workGenerationalId) {
        intent.putExtra("KEY_WORKSPEC_ID", workGenerationalId.workSpecId);
        intent.putExtra("KEY_WORKSPEC_GENERATION", workGenerationalId.generation);
    }

    @Override // androidx.work.impl.ExecutionListener
    public final void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        synchronized (this.mLock) {
            DelayMetCommandHandler delayMetCommandHandler = (DelayMetCommandHandler) this.mPendingDelayMet.remove(workGenerationalId);
            this.mStartStopTokens$ar$class_merging$ar$class_merging.remove$ar$class_merging$ar$class_merging(workGenerationalId);
            if (delayMetCommandHandler != null) {
                Logger.get$ar$ds$16341a92_0();
                Objects.toString(delayMetCommandHandler.mWorkGenerationalId);
                delayMetCommandHandler.cleanUp();
                if (z) {
                    delayMetCommandHandler.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(delayMetCommandHandler.mDispatcher, createScheduleWorkIntent(delayMetCommandHandler.mContext, delayMetCommandHandler.mWorkGenerationalId), delayMetCommandHandler.mStartId));
                }
                if (delayMetCommandHandler.mHasConstraints) {
                    delayMetCommandHandler.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(delayMetCommandHandler.mDispatcher, createConstraintsChangedIntent(delayMetCommandHandler.mContext), delayMetCommandHandler.mStartId));
                }
            }
        }
    }
}

package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import androidx.lifecycle.ViewModelStore;
import androidx.room.RoomDatabase;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Alarms {
    public static final /* synthetic */ int Alarms$ar$NoOp = 0;
    private static final String TAG = Logger.tagWithPrefix("Alarms");

    public static void cancelExactAlarm(Context context, WorkGenerationalId workGenerationalId, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, CommandHandler.createDelayMetIntent(context, workGenerationalId), 603979776);
        if (service != null && alarmManager != null) {
            Logger.get$ar$ds$16341a92_0();
            Objects.toString(workGenerationalId);
            alarmManager.cancel(service);
        }
    }

    public static void setAlarm(Context context, WorkDatabase workDatabase, WorkGenerationalId workGenerationalId, long j) {
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        SystemIdInfo $default$getSystemIdInfo = SystemJobService.Api28Impl.$default$getSystemIdInfo(systemIdInfoDao, workGenerationalId);
        if ($default$getSystemIdInfo != null) {
            cancelExactAlarm(context, workGenerationalId, $default$getSystemIdInfo.systemId);
            setExactAlarm(context, workGenerationalId, $default$getSystemIdInfo.systemId, j);
            return;
        }
        ViewModelStore viewModelStore = new ViewModelStore(workDatabase, (byte[]) null);
        Object runInTransaction = ((RoomDatabase) viewModelStore.ViewModelStore$ar$map).runInTransaction(new WorkerWrapper$$ExternalSyntheticLambda0(viewModelStore, 3));
        runInTransaction.getClass();
        int intValue = ((Number) runInTransaction).intValue();
        systemIdInfoDao.insertSystemIdInfo(SystemJobService.Api31Impl.systemIdInfo(workGenerationalId, intValue));
        setExactAlarm(context, workGenerationalId, intValue, j);
    }

    private static void setExactAlarm(Context context, WorkGenerationalId workGenerationalId, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, CommandHandler.createDelayMetIntent(context, workGenerationalId), 201326592);
        if (alarmManager != null) {
            alarmManager.setExact(0, j, service);
        }
    }
}

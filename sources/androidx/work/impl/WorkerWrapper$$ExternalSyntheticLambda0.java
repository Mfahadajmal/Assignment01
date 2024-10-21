package androidx.work.impl;

import android.content.BroadcastReceiver;
import android.os.Binder;
import android.os.Process;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.content.ModernAsyncTask;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.DeferrableExecutor;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.storage.protostore.XDataStore;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.GoogleLogger;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import java.util.concurrent.Callable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class WorkerWrapper$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ Object WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public WorkerWrapper$$ExternalSyntheticLambda0(ModernAsyncTask modernAsyncTask, int i) {
        this.switching_field = i;
        this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0 = modernAsyncTask;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.Callable
    public final Object call() {
        long currentTimeMillis;
        Object obj = null;
        boolean z = false;
        switch (this.switching_field) {
            case 0:
                WorkerWrapper workerWrapper = (WorkerWrapper) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0;
                if (workerWrapper.workSpec.state$ar$edu != 1) {
                    String str = WorkerWrapperKt.TAG;
                    Logger.get$ar$ds$16341a92_0();
                } else {
                    WorkSpec workSpec = workerWrapper.workSpec;
                    if (!workSpec.isPeriodic() && !workSpec.isBackedOff()) {
                        return false;
                    }
                    WorkSpec workSpec2 = workerWrapper.workSpec;
                    currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis >= workSpec2.calculateNextRunTime()) {
                        return false;
                    }
                    Logger.get$ar$ds$16341a92_0();
                    String str2 = WorkerWrapperKt.TAG;
                    String str3 = workerWrapper.workSpec.workerClassName;
                }
                return true;
            case 1:
                ((ModernAsyncTask) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).mTaskInvoked.set(true);
                try {
                    Process.setThreadPriority(10);
                    obj = ((ModernAsyncTask) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).doInBackground();
                    Binder.flushPendingCommands();
                    return obj;
                } catch (Throwable th) {
                    try {
                        ((ModernAsyncTask) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).mCancelled.set(true);
                        throw th;
                    } finally {
                        ((ModernAsyncTask) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).postResult(obj);
                    }
                }
            case 2:
                WorkerWrapper workerWrapper2 = (WorkerWrapper) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0;
                if (workerWrapper2.workSpecDao.getState$ar$edu$fd856834_0(workerWrapper2.workSpecId) == 1) {
                    workerWrapper2.workSpecDao.setState$ar$edu$ar$ds(2, workerWrapper2.workSpecId);
                    WorkSpecDao workSpecDao = workerWrapper2.workSpecDao;
                    String str4 = workerWrapper2.workSpecId;
                    WorkSpecDao_Impl workSpecDao_Impl = (WorkSpecDao_Impl) workSpecDao;
                    workSpecDao_Impl.__db.assertNotSuspendingTransaction();
                    FrameworkSQLiteStatement acquire$ar$class_merging = workSpecDao_Impl.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire$ar$class_merging();
                    acquire$ar$class_merging.bindString(1, str4);
                    try {
                        ((WorkSpecDao_Impl) workSpecDao).__db.beginTransaction();
                        try {
                            acquire$ar$class_merging.executeUpdateDelete();
                            ((WorkSpecDao_Impl) workSpecDao).__db.setTransactionSuccessful();
                            workSpecDao_Impl.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release$ar$class_merging(acquire$ar$class_merging);
                            workerWrapper2.workSpecDao.setStopReason(workerWrapper2.workSpecId, -256);
                            z = true;
                        } finally {
                            ((WorkSpecDao_Impl) workSpecDao).__db.endTransaction();
                        }
                    } catch (Throwable th2) {
                        workSpecDao_Impl.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release$ar$class_merging(acquire$ar$class_merging);
                        throw th2;
                    }
                }
                return Boolean.valueOf(z);
            case 3:
                return Integer.valueOf(AppCompatTextHelper.Api28Impl.nextId((WorkDatabase) ((ViewModelStore) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).ViewModelStore$ar$map, "next_alarm_manager_id"));
            case 4:
                return ((ImageCaptioner) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).m95x7b45e1cf();
            case 5:
                ImageCaptioner imageCaptioner = (ImageCaptioner) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0;
                imageCaptioner.isImageDescriptionProcessorInitializing = false;
                ImageDescriptionProcessor imageDescriptionProcessor = imageCaptioner.imageDescriptionProcessor;
                if (imageDescriptionProcessor != null) {
                    imageDescriptionProcessor.stop();
                    imageCaptioner.imageDescriptionProcessor = null;
                    LogUtils.v("ImageCaptioner", "ImageDescriptionProcessor stopped.", new Object[0]);
                }
                return true;
            case 6:
                ImmutableList immutableList = (ImmutableList) ((AiCoreEndpoint) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).listFeatures.get();
                int size = immutableList.size();
                for (int i = 0; i < size; i++) {
                    AiFeature aiFeature = (AiFeature) immutableList.get(i);
                    if (aiFeature.getType() == 19 && aiFeature.getId() == 223) {
                        LogUtils.d("AiCoreEndpointGemini", "hasAiCoreFeatureFuture - has TB feature", new Object[0]);
                        return true;
                    }
                }
                LogUtils.d("AiCoreEndpointGemini", "hasAiCoreFeatureFuture - Not support", new Object[0]);
                return false;
            case 7:
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/DeferrableExecutor", "unblockAfterMaxDelay", BrailleInputEvent.CMD_LINK_NEXT, "DeferrableExecutor.java")).log("DeferrableExecutor unblocked after max task delay");
                ((DeferrableExecutor) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).unblock();
                return null;
            case 8:
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/DeferrableExecutor", "unblockAfterResume", BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE, "DeferrableExecutor.java")).log("DeferrableExecutor unblocked after onResume");
                ((DeferrableExecutor) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).unblock();
                return null;
            case 9:
                ((BroadcastReceiver.PendingResult) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).finish();
                return null;
            case 10:
                Object obj2 = this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0;
                synchronized (((XDataStore.InitializationTasks) obj2).this$0.lock) {
                    ((XDataStore.InitializationTasks) obj2).tasks = null;
                }
                return null;
            case 11:
                return LibraryVersion.INSTANCE.getVersion(((MLKitStatsLogger) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).libraryName);
            default:
                return ((SharedPrefManager) this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0).getMlSdkInstanceId();
        }
    }

    public /* synthetic */ WorkerWrapper$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.WorkerWrapper$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }
}

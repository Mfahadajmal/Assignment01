package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.greedy.GreedyScheduler;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.utils.PackageManagerHelper;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.FunctionReference;
import kotlinx.coroutines.scheduling.WorkQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class WorkManagerImplExtKt$WorkManagerImpl$1 extends FunctionReference implements Function6 {
    public static final WorkManagerImplExtKt$WorkManagerImpl$1 INSTANCE = new WorkManagerImplExtKt$WorkManagerImpl$1();

    public WorkManagerImplExtKt$WorkManagerImpl$1() {
        super(6, WorkManagerImplExtKt.class, "createSchedulers", "createSchedulers(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/constraints/trackers/Trackers;Landroidx/work/impl/Processor;)Ljava/util/List;", 1);
    }

    @Override // kotlin.jvm.functions.Function6
    public final /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        int i = Schedulers.Schedulers$ar$NoOp;
        Configuration configuration = (Configuration) obj2;
        Context context = (Context) obj;
        SystemJobScheduler systemJobScheduler = new SystemJobScheduler(context, (WorkDatabase) obj4, configuration);
        PackageManagerHelper.setComponentEnabled(context, SystemJobService.class, true);
        Logger.get$ar$ds$16341a92_0();
        Processor processor = (Processor) obj6;
        WorkManagerTaskExecutor workManagerTaskExecutor = (WorkManagerTaskExecutor) obj3;
        return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.asList(new Scheduler[]{systemJobScheduler, new GreedyScheduler(context, configuration, (WorkQueue) obj5, processor, new WorkName(processor, workManagerTaskExecutor), workManagerTaskExecutor)});
    }
}

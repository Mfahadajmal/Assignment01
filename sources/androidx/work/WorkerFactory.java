package androidx.work;

import android.content.Context;
import android.util.Log;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class WorkerFactory {
    public final ListenableWorker createWorkerWithDefaultFallback(Context context, String str, WorkerParameters workerParameters) {
        str.getClass();
        try {
            Class<? extends U> asSubclass = Class.forName(str).asSubclass(ListenableWorker.class);
            asSubclass.getClass();
            try {
                Object newInstance = asSubclass.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(context, workerParameters);
                newInstance.getClass();
                ListenableWorker listenableWorker = (ListenableWorker) newInstance;
                if (!listenableWorker.mUsed) {
                    return listenableWorker;
                }
                throw new IllegalStateException("WorkerFactory (" + getClass().getName() + ") returned an instance of a ListenableWorker (" + str + ") which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.");
            } catch (Throwable th) {
                Logger.get$ar$ds$16341a92_0();
                Log.e(WorkerFactoryKt.TAG, "Could not instantiate ".concat(str), th);
                throw th;
            }
        } catch (Throwable th2) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(WorkerFactoryKt.TAG, "Invalid class: ".concat(str), th2);
            throw th2;
        }
    }
}

package androidx.loader.content;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3;
import android.util.Log;
import androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ModernAsyncTask {
    private static Handler sHandler;
    public volatile int mStatus$ar$edu = 1;
    public final AtomicBoolean mCancelled = new AtomicBoolean();
    public final AtomicBoolean mTaskInvoked = new AtomicBoolean();
    public final FutureTask mFuture = new FutureTask(new WorkerWrapper$$ExternalSyntheticLambda0(this, 1)) { // from class: androidx.loader.content.ModernAsyncTask.2
        @Override // java.util.concurrent.FutureTask
        protected final void done() {
            try {
                ModernAsyncTask.this.postResultIfNotInvoked(get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (CancellationException unused) {
                ModernAsyncTask.this.postResultIfNotInvoked(null);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    };

    public abstract Object doInBackground();

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public void onCancelled(Object obj) {
        throw null;
    }

    public void onPostExecute(Object obj) {
        throw null;
    }

    public final void postResult(Object obj) {
        Handler handler;
        synchronized (ModernAsyncTask.class) {
            if (sHandler == null) {
                sHandler = new Handler(Looper.getMainLooper());
            }
            handler = sHandler;
        }
        handler.post(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(this, obj, 19, (char[]) null));
    }

    final void postResultIfNotInvoked(Object obj) {
        if (!this.mTaskInvoked.get()) {
            postResult(obj);
        }
    }
}

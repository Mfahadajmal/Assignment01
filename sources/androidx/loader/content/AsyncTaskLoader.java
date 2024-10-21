package androidx.loader.content;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.SystemClock;
import androidx.core.os.OperationCanceledException;
import androidx.loader.app.LoaderManagerImpl;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.social.licenses.License;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AsyncTaskLoader extends Loader {
    private List licenses;
    public volatile LoadTask mCancellingTask;
    private Executor mExecutor;
    public volatile LoadTask mTask;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LoadTask extends ModernAsyncTask implements Runnable {
        public boolean waiting;

        public LoadTask() {
        }

        @Override // androidx.loader.content.ModernAsyncTask
        public final Object doInBackground() {
            try {
                return AsyncTaskLoader.this.loadInBackground();
            } catch (OperationCanceledException e) {
                if (isCancelled()) {
                    return null;
                }
                throw e;
            }
        }

        @Override // androidx.loader.content.ModernAsyncTask
        public final void onCancelled(Object obj) {
            AsyncTaskLoader.this.dispatchOnCancelled$ar$ds(this);
        }

        @Override // androidx.loader.content.ModernAsyncTask
        public final void onPostExecute(Object obj) {
            AsyncTaskLoader asyncTaskLoader = AsyncTaskLoader.this;
            if (asyncTaskLoader.mTask == this) {
                if (asyncTaskLoader.mAbandoned) {
                    return;
                }
                SystemClock.uptimeMillis();
                asyncTaskLoader.mTask = null;
                asyncTaskLoader.deliverResult(obj);
                return;
            }
            asyncTaskLoader.dispatchOnCancelled$ar$ds(this);
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.waiting = false;
            AsyncTaskLoader.this.executePendingTask();
        }
    }

    public AsyncTaskLoader(Context context) {
        super(context);
    }

    final void dispatchOnCancelled$ar$ds(LoadTask loadTask) {
        if (this.mCancellingTask == loadTask) {
            SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            executePendingTask();
        }
    }

    final void executePendingTask() {
        if (this.mCancellingTask == null && this.mTask != null) {
            boolean z = this.mTask.waiting;
            if (this.mExecutor == null) {
                this.mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
            LoadTask loadTask = this.mTask;
            Executor executor = this.mExecutor;
            if (loadTask.mStatus$ar$edu != 1) {
                int i = loadTask.mStatus$ar$edu;
                int i2 = i - 1;
                if (i != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            throw new IllegalStateException("We should never reach this state");
                        }
                        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                    }
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                }
                throw null;
            }
            loadTask.mStatus$ar$edu = 2;
            executor.execute(loadTask.mFuture);
        }
    }

    public final /* bridge */ /* synthetic */ Object loadInBackground() {
        boolean z;
        TreeSet treeSet = new TreeSet();
        String[] split = CurrentProcess.getTextFromResource$ar$ds(this.mContext.getApplicationContext(), "third_party_license_metadata", 0L, -1).split("\n");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str : split) {
            int indexOf = str.indexOf(32);
            String[] split2 = str.substring(0, indexOf).split(":");
            if (split2.length == 2 && indexOf > 0) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "Invalid license meta-data line:\n".concat(String.valueOf(str)));
            arrayList.add(new License(str.substring(indexOf + 1), Long.parseLong(split2[0]), Integer.parseInt(split2[1])));
        }
        Collections.sort(arrayList);
        treeSet.addAll(arrayList);
        return DesugarCollections.unmodifiableList(new ArrayList(treeSet));
    }

    @Override // androidx.loader.content.Loader
    public final void onCancelLoad$ar$ds() {
        if (this.mTask != null) {
            if (!this.mStarted) {
                this.mContentChanged = true;
            }
            if (this.mCancellingTask != null) {
                boolean z = this.mTask.waiting;
            } else {
                boolean z2 = this.mTask.waiting;
                LoadTask loadTask = this.mTask;
                loadTask.mCancelled.set(true);
                if (loadTask.mFuture.cancel(false)) {
                    this.mCancellingTask = this.mTask;
                }
            }
            this.mTask = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.loader.content.Loader
    public final void onForceLoad() {
        onCancelLoad$ar$ds();
        this.mTask = new LoadTask();
        executePendingTask();
    }

    @Override // androidx.loader.content.Loader
    public final void onStartLoading() {
        List list = this.licenses;
        if (list != null) {
            deliverResult(list);
        } else {
            onForceLoad();
        }
    }

    @Override // androidx.loader.content.Loader
    public final void onStopLoading() {
        onCancelLoad$ar$ds();
    }

    public AsyncTaskLoader(Context context, byte[] bArr) {
        this(context.getApplicationContext());
    }

    public final void deliverResult(List list) {
        this.licenses = list;
        LoaderManagerImpl.LoaderInfo loaderInfo = this.mListener$ar$class_merging$a2c4ce4e_0;
        if (loaderInfo != null) {
            if (LoaderManagerImpl.isLoggingEnabled(2)) {
                Objects.toString(loaderInfo);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                loaderInfo.setValue(list);
            } else {
                loaderInfo.postValue(list);
            }
        }
    }
}

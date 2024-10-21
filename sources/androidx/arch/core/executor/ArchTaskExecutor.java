package androidx.arch.core.executor;

import android.os.Looper;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArchTaskExecutor extends AppCompatTextViewAutoSizeHelper.Api23Impl {
    public static final Executor sIOThreadExecutor = new AnonymousClass2(0);
    private static volatile ArchTaskExecutor sInstance;
    private final AppCompatTextViewAutoSizeHelper.Api23Impl mDefaultTaskExecutor$ar$class_merging;
    public final AppCompatTextViewAutoSizeHelper.Api23Impl mDelegate$ar$class_merging;

    /* compiled from: PG */
    /* renamed from: androidx.arch.core.executor.ArchTaskExecutor$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements Executor {
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(int i) {
            this.switching_field = i;
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            if (this.switching_field != 0) {
                new Thread(runnable).start();
            } else {
                ((DefaultTaskExecutor) ArchTaskExecutor.getInstance().mDelegate$ar$class_merging).mDiskIO.execute(runnable);
            }
        }
    }

    private ArchTaskExecutor() {
        DefaultTaskExecutor defaultTaskExecutor = new DefaultTaskExecutor();
        this.mDefaultTaskExecutor$ar$class_merging = defaultTaskExecutor;
        this.mDelegate$ar$class_merging = defaultTaskExecutor;
    }

    public static ArchTaskExecutor getInstance() {
        if (sInstance == null) {
            synchronized (ArchTaskExecutor.class) {
                if (sInstance == null) {
                    sInstance = new ArchTaskExecutor();
                }
            }
        }
        return sInstance;
    }

    public final boolean isMainThread() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return true;
        }
        return false;
    }

    public final void postToMainThread(Runnable runnable) {
        AppCompatTextViewAutoSizeHelper.Api23Impl api23Impl = this.mDelegate$ar$class_merging;
        DefaultTaskExecutor defaultTaskExecutor = (DefaultTaskExecutor) api23Impl;
        if (defaultTaskExecutor.mMainHandler == null) {
            synchronized (defaultTaskExecutor.mLock) {
                if (((DefaultTaskExecutor) api23Impl).mMainHandler == null) {
                    ((DefaultTaskExecutor) api23Impl).mMainHandler = DefaultTaskExecutor.createAsync(Looper.getMainLooper());
                }
            }
        }
        defaultTaskExecutor.mMainHandler.post(runnable);
    }
}

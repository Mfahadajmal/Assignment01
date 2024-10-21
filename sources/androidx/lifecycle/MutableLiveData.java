package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MutableLiveData<T> extends LiveData<T> {
    public MutableLiveData() {
    }

    public final void postValue(Object obj) {
        Object obj2;
        Object obj3;
        synchronized (this.mDataLock) {
            obj2 = this.mPendingData;
            obj3 = LiveData.NOT_SET;
            this.mPendingData = obj;
        }
        if (obj2 != obj3) {
            return;
        }
        ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
    }

    @Override // androidx.lifecycle.LiveData
    public void setValue(Object obj) {
        LiveData.assertMainThread("setValue");
        this.mVersion++;
        this.mData = obj;
        dispatchingValue(null);
    }

    public MutableLiveData(Object obj) {
        super(obj);
    }
}

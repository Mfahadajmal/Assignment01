package androidx.work.impl.utils.taskexecutor;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools$SimplePool;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.room.InvalidationTracker$Observer;
import androidx.work.impl.utils.SerialExecutorImpl;
import com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import com.google.mlkit.logging.schema.RemoteConfigLogEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkManagerTaskExecutor {
    public final Object WorkManagerTaskExecutor$ar$mBackgroundExecutor;
    public final Object WorkManagerTaskExecutor$ar$mMainThreadExecutor;
    public final Object WorkManagerTaskExecutor$ar$mMainThreadHandler;
    public final Object WorkManagerTaskExecutor$ar$mTaskDispatcher;

    public WorkManagerTaskExecutor(ViewModelStore viewModelStore, ViewModelProvider.Factory factory, CreationExtras creationExtras) {
        this.WorkManagerTaskExecutor$ar$mTaskDispatcher = viewModelStore;
        this.WorkManagerTaskExecutor$ar$mMainThreadExecutor = factory;
        this.WorkManagerTaskExecutor$ar$mBackgroundExecutor = creationExtras;
        this.WorkManagerTaskExecutor$ar$mMainThreadHandler = new ViewCompat.Api29Impl();
    }

    public final void addNode(Object obj) {
        if (!((SimpleArrayMap) this.WorkManagerTaskExecutor$ar$mMainThreadExecutor).containsKey(obj)) {
            ((SimpleArrayMap) this.WorkManagerTaskExecutor$ar$mMainThreadExecutor).put(obj, null);
        }
    }

    public final void dfs(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (!hashSet.contains(obj)) {
            hashSet.add(obj);
            ArrayList arrayList2 = (ArrayList) ((SimpleArrayMap) this.WorkManagerTaskExecutor$ar$mMainThreadExecutor).get(obj);
            if (arrayList2 != null) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    dfs(arrayList2.get(i), arrayList, hashSet);
                }
            }
            hashSet.remove(obj);
            arrayList.add(obj);
            return;
        }
        throw new RuntimeException("This graph contains cyclic dependencies");
    }

    public final /* synthetic */ void executeOnTaskThread(Runnable runnable) {
        ((SerialExecutorImpl) this.WorkManagerTaskExecutor$ar$mBackgroundExecutor).execute(runnable);
    }

    public final ArrayList getIncomingEdgesInternal(Object obj) {
        return (ArrayList) ((SimpleArrayMap) this.WorkManagerTaskExecutor$ar$mMainThreadExecutor).get(obj);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.concurrent.Executor, java.lang.Object] */
    public final Executor getMainThreadExecutor() {
        return this.WorkManagerTaskExecutor$ar$mMainThreadExecutor;
    }

    public final /* synthetic */ SerialExecutorImpl getSerialTaskExecutor$ar$class_merging() {
        return (SerialExecutorImpl) this.WorkManagerTaskExecutor$ar$mBackgroundExecutor;
    }

    public final CoroutineDispatcher getTaskCoroutineDispatcher() {
        return (CoroutineDispatcher) this.WorkManagerTaskExecutor$ar$mTaskDispatcher;
    }

    /* JADX WARN: Type inference failed for: r2v11, types: [androidx.lifecycle.ViewModelProvider$Factory, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.util.Map, java.lang.Object] */
    public final ViewModel getViewModel$lifecycle_viewmodel_release(KClass kClass, String str) {
        ViewModel viewModel;
        boolean isInstance;
        synchronized (this.WorkManagerTaskExecutor$ar$mMainThreadHandler) {
            viewModel = ((ViewModelStore) this.WorkManagerTaskExecutor$ar$mTaskDispatcher).get(str);
            Class cls = ((ClassReference) kClass).jClass;
            Map map = ClassReference.FUNCTION_CLASSES;
            map.getClass();
            Integer num = (Integer) map.get(cls);
            if (num != null) {
                isInstance = TypeIntrinsics.isFunctionOfArity(viewModel, num.intValue());
            } else {
                if (cls.isPrimitive()) {
                    cls = OnDeviceShadowRemovalLogEvent.getJavaObjectType(OnDeviceShadowRemovalLogEvent.getKotlinClass(cls));
                }
                isInstance = cls.isInstance(viewModel);
            }
            if (isInstance) {
                Object obj = this.WorkManagerTaskExecutor$ar$mMainThreadExecutor;
                if (obj instanceof ViewModelProvider.OnRequeryFactory) {
                    viewModel.getClass();
                    ((ViewModelProvider.OnRequeryFactory) obj).onRequery(viewModel);
                }
                viewModel.getClass();
            } else {
                MutableCreationExtras mutableCreationExtras = new MutableCreationExtras((CreationExtras) this.WorkManagerTaskExecutor$ar$mBackgroundExecutor);
                mutableCreationExtras.set(SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1.INSTANCE$ar$class_merging$39cd80ef_0, str);
                viewModel = ViewCompat.Api28Impl.createViewModel(this.WorkManagerTaskExecutor$ar$mMainThreadExecutor, kClass, mutableCreationExtras);
                Object obj2 = this.WorkManagerTaskExecutor$ar$mTaskDispatcher;
                viewModel.getClass();
                ViewModel viewModel2 = (ViewModel) ((ViewModelStore) obj2).ViewModelStore$ar$map.put(str, viewModel);
                if (viewModel2 != null) {
                    viewModel2.clear$lifecycle_viewmodel_release();
                }
            }
        }
        return viewModel;
    }

    public WorkManagerTaskExecutor(InvalidationTracker$Observer invalidationTracker$Observer, int[] iArr, String[] strArr) {
        invalidationTracker$Observer.getClass();
        iArr.getClass();
        strArr.getClass();
        this.WorkManagerTaskExecutor$ar$mBackgroundExecutor = invalidationTracker$Observer;
        this.WorkManagerTaskExecutor$ar$mMainThreadHandler = iArr;
        this.WorkManagerTaskExecutor$ar$mTaskDispatcher = strArr;
        int length = iArr.length;
        String[] strArr2 = strArr;
        int length2 = strArr2.length;
        if (length == length2) {
            this.WorkManagerTaskExecutor$ar$mMainThreadExecutor = length2 == 0 ? EmptySet.INSTANCE : OnDeviceObjectCreateLogEvent.setOf(strArr2[0]);
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public WorkManagerTaskExecutor(byte[] bArr) {
        this.WorkManagerTaskExecutor$ar$mTaskDispatcher = new Pools$SimplePool(10);
        this.WorkManagerTaskExecutor$ar$mMainThreadExecutor = new SimpleArrayMap();
        this.WorkManagerTaskExecutor$ar$mBackgroundExecutor = new ArrayList();
        this.WorkManagerTaskExecutor$ar$mMainThreadHandler = new HashSet();
    }

    public WorkManagerTaskExecutor() {
        this.WorkManagerTaskExecutor$ar$mMainThreadExecutor = new ArrayMap();
        this.WorkManagerTaskExecutor$ar$mMainThreadHandler = new SparseArray();
        this.WorkManagerTaskExecutor$ar$mTaskDispatcher = new LongSparseArray();
        this.WorkManagerTaskExecutor$ar$mBackgroundExecutor = new ArrayMap();
    }

    public WorkManagerTaskExecutor(Executor executor) {
        this.WorkManagerTaskExecutor$ar$mMainThreadHandler = new Handler(Looper.getMainLooper());
        this.WorkManagerTaskExecutor$ar$mMainThreadExecutor = new Executor() { // from class: androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor.1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                ((Handler) WorkManagerTaskExecutor.this.WorkManagerTaskExecutor$ar$mMainThreadHandler).post(runnable);
            }
        };
        SerialExecutorImpl serialExecutorImpl = new SerialExecutorImpl(executor, 0);
        this.WorkManagerTaskExecutor$ar$mBackgroundExecutor = serialExecutorImpl;
        this.WorkManagerTaskExecutor$ar$mTaskDispatcher = RemoteConfigLogEvent.from(serialExecutorImpl);
    }
}

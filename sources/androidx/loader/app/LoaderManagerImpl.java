package androidx.loader.app;

import android.support.v4.app.FragmentManagerViewModel;
import android.util.Log;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.navigation.NavControllerViewModel;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.social.licenses.LicenseMenuFragment;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LoaderManagerImpl extends LoaderManager {
    public final LifecycleOwner mLifecycleOwner;
    public final LoaderViewModel mLoaderViewModel;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LoaderInfo extends MutableLiveData {
        public final int mId = 54321;
        private LifecycleOwner mLifecycleOwner;
        public final Loader mLoader;
        public LoaderObserver mObserver;

        public LoaderInfo(Loader loader) {
            this.mLoader = loader;
            if (loader.mListener$ar$class_merging$a2c4ce4e_0 == null) {
                loader.mListener$ar$class_merging$a2c4ce4e_0 = this;
                loader.mId = 54321;
                return;
            }
            throw new IllegalStateException("There is already a listener registered");
        }

        public final void destroy$ar$ds() {
            if (LoaderManagerImpl.isLoggingEnabled(3)) {
                toString();
            }
            this.mLoader.onCancelLoad$ar$ds();
            this.mLoader.mAbandoned = true;
            LoaderObserver loaderObserver = this.mObserver;
            if (loaderObserver != null) {
                removeObserver(loaderObserver);
                if (loaderObserver.mDeliveredData) {
                    if (LoaderManagerImpl.isLoggingEnabled(2)) {
                        Objects.toString(loaderObserver.mLoader);
                    }
                    LicenseMenuFragment licenseMenuFragment = loaderObserver.mCallback$ar$class_merging$19a0d48d_0;
                    licenseMenuFragment.listAdapter.clear();
                    licenseMenuFragment.listAdapter.notifyDataSetChanged();
                }
            }
            Loader loader = this.mLoader;
            LoaderInfo loaderInfo = loader.mListener$ar$class_merging$a2c4ce4e_0;
            if (loaderInfo != null) {
                if (loaderInfo == this) {
                    loader.mListener$ar$class_merging$a2c4ce4e_0 = null;
                    loader.mReset = true;
                    loader.mStarted = false;
                    loader.mAbandoned = false;
                    loader.mContentChanged = false;
                    return;
                }
                throw new IllegalArgumentException("Attempting to unregister the wrong listener");
            }
            throw new IllegalStateException("No listener register");
        }

        public final void markForRedelivery() {
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            LoaderObserver loaderObserver = this.mObserver;
            if (lifecycleOwner != null && loaderObserver != null) {
                super.removeObserver(loaderObserver);
                observe(lifecycleOwner, loaderObserver);
            }
        }

        @Override // androidx.lifecycle.LiveData
        protected final void onActive() {
            if (LoaderManagerImpl.isLoggingEnabled(2)) {
                toString();
            }
            Loader loader = this.mLoader;
            loader.mStarted = true;
            loader.mReset = false;
            loader.mAbandoned = false;
            loader.onStartLoading();
        }

        @Override // androidx.lifecycle.LiveData
        protected final void onInactive() {
            if (LoaderManagerImpl.isLoggingEnabled(2)) {
                toString();
            }
            Loader loader = this.mLoader;
            loader.mStarted = false;
            loader.onStopLoading();
        }

        @Override // androidx.lifecycle.LiveData
        public final void removeObserver(Observer observer) {
            super.removeObserver(observer);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        public final void setCallback$ar$class_merging$ar$ds(LifecycleOwner lifecycleOwner, LicenseMenuFragment licenseMenuFragment) {
            LoaderObserver loaderObserver = new LoaderObserver(this.mLoader, licenseMenuFragment);
            observe(lifecycleOwner, loaderObserver);
            Observer observer = this.mObserver;
            if (observer != null) {
                removeObserver(observer);
            }
            this.mLifecycleOwner = lifecycleOwner;
            this.mObserver = loaderObserver;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.mId);
            sb.append(" : ");
            sb.append(this.mLoader.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.mLoader)));
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LoaderObserver implements Observer {
        public final LicenseMenuFragment mCallback$ar$class_merging$19a0d48d_0;
        public boolean mDeliveredData = false;
        public final Loader mLoader;

        public LoaderObserver(Loader loader, LicenseMenuFragment licenseMenuFragment) {
            this.mLoader = loader;
            this.mCallback$ar$class_merging$19a0d48d_0 = licenseMenuFragment;
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            if (LoaderManagerImpl.isLoggingEnabled(2)) {
                Objects.toString(this.mLoader);
                Loader.dataToString$ar$ds(obj);
            }
            this.mDeliveredData = true;
            LicenseMenuFragment licenseMenuFragment = this.mCallback$ar$class_merging$19a0d48d_0;
            licenseMenuFragment.listAdapter.clear();
            licenseMenuFragment.listAdapter.addAll((List) obj);
            licenseMenuFragment.listAdapter.notifyDataSetChanged();
        }

        public final String toString() {
            return this.mCallback$ar$class_merging$19a0d48d_0.toString();
        }
    }

    public LoaderManagerImpl(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mLoaderViewModel = (LoaderViewModel) new ViewModelProvider(viewModelStore, LoaderViewModel.FACTORY).get(LoaderViewModel.class);
    }

    public static boolean isLoggingEnabled(int i) {
        if (Log.isLoggable("LoaderManager", i)) {
            return true;
        }
        return false;
    }

    @Override // androidx.loader.app.LoaderManager
    @Deprecated
    public final void dump$ar$ds(String str, PrintWriter printWriter) {
        boolean z;
        LoaderViewModel loaderViewModel = this.mLoaderViewModel;
        if (loaderViewModel.mLoaders.size() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            String valueOf = String.valueOf(str);
            for (int i = 0; i < loaderViewModel.mLoaders.size(); i++) {
                String concat = valueOf.concat("    ");
                LoaderInfo loaderInfo = (LoaderInfo) loaderViewModel.mLoaders.valueAt(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(loaderViewModel.mLoaders.keyAt(i));
                printWriter.print(": ");
                printWriter.println(loaderInfo.toString());
                printWriter.print(concat);
                printWriter.print("mId=");
                printWriter.print(loaderInfo.mId);
                printWriter.print(" mArgs=");
                printWriter.println((Object) null);
                printWriter.print(concat);
                printWriter.print("mLoader=");
                printWriter.println(loaderInfo.mLoader);
                Loader loader = loaderInfo.mLoader;
                String concat2 = String.valueOf(concat).concat("  ");
                printWriter.print(concat2);
                printWriter.print("mId=");
                printWriter.print(loader.mId);
                printWriter.print(" mListener=");
                printWriter.println(loader.mListener$ar$class_merging$a2c4ce4e_0);
                if (loader.mStarted || loader.mContentChanged) {
                    printWriter.print(concat2);
                    printWriter.print("mStarted=");
                    printWriter.print(loader.mStarted);
                    printWriter.print(" mContentChanged=");
                    printWriter.print(loader.mContentChanged);
                    printWriter.print(" mProcessingChange=");
                    printWriter.println(false);
                }
                if (loader.mAbandoned || loader.mReset) {
                    printWriter.print(concat2);
                    printWriter.print("mAbandoned=");
                    printWriter.print(loader.mAbandoned);
                    printWriter.print(" mReset=");
                    printWriter.println(loader.mReset);
                }
                AsyncTaskLoader asyncTaskLoader = (AsyncTaskLoader) loader;
                if (asyncTaskLoader.mTask != null) {
                    printWriter.print(concat2);
                    printWriter.print("mTask=");
                    printWriter.print(asyncTaskLoader.mTask);
                    printWriter.print(" waiting=");
                    boolean z2 = asyncTaskLoader.mTask.waiting;
                    printWriter.println(false);
                }
                if (asyncTaskLoader.mCancellingTask != null) {
                    printWriter.print(concat2);
                    printWriter.print("mCancellingTask=");
                    printWriter.print(asyncTaskLoader.mCancellingTask);
                    printWriter.print(" waiting=");
                    boolean z3 = asyncTaskLoader.mCancellingTask.waiting;
                    printWriter.println(false);
                }
                if (loaderInfo.mObserver != null) {
                    printWriter.print(concat);
                    printWriter.print("mCallbacks=");
                    printWriter.println(loaderInfo.mObserver);
                    LoaderObserver loaderObserver = loaderInfo.mObserver;
                    printWriter.print(String.valueOf(concat).concat("  "));
                    printWriter.print("mDeliveredData=");
                    printWriter.println(loaderObserver.mDeliveredData);
                }
                printWriter.print(concat);
                printWriter.print("mData=");
                Loader loader2 = loaderInfo.mLoader;
                printWriter.println(Loader.dataToString$ar$ds(loaderInfo.getValue()));
                printWriter.print(concat);
                printWriter.print("mStarted=");
                if (loaderInfo.mActiveCount > 0) {
                    z = true;
                } else {
                    z = false;
                }
                printWriter.println(z);
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        sb.append(this.mLifecycleOwner.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this.mLifecycleOwner)));
        sb.append("}}");
        return sb.toString();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LoaderViewModel extends ViewModel {
        public static final ViewModelProvider.Factory FACTORY = new AnonymousClass1(0);
        public final SparseArrayCompat mLoaders = new SparseArrayCompat();
        public boolean mCreatingLoader = false;

        public final void finishCreatingLoader() {
            this.mCreatingLoader = false;
        }

        public final LoaderInfo getLoader$ar$ds() {
            return (LoaderInfo) SparseArrayCompatKt.commonGet(this.mLoaders, 54321);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.lifecycle.ViewModel
        public final void onCleared() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                ((LoaderInfo) this.mLoaders.valueAt(i)).destroy$ar$ds();
            }
            SparseArrayCompat sparseArrayCompat = this.mLoaders;
            int i2 = sparseArrayCompat.size;
            Object[] objArr = sparseArrayCompat.values;
            for (int i3 = 0; i3 < i2; i3++) {
                objArr[i3] = null;
            }
            sparseArrayCompat.size = 0;
            sparseArrayCompat.garbage = false;
        }

        /* compiled from: PG */
        /* renamed from: androidx.loader.app.LoaderManagerImpl$LoaderViewModel$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements ViewModelProvider.Factory {
            private final /* synthetic */ int switching_field;

            public AnonymousClass1(int i) {
                this.switching_field = i;
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public final ViewModel create(Class cls) {
                int i = this.switching_field;
                if (i != 0) {
                    return i != 1 ? new NavControllerViewModel() : new FragmentManagerViewModel(true);
                }
                return new LoaderViewModel();
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public final /* synthetic */ ViewModel create(Class cls, CreationExtras creationExtras) {
                ViewModel create;
                ViewModel create2;
                ViewModel create3;
                int i = this.switching_field;
                if (i == 0) {
                    create = create(cls);
                    return create;
                }
                if (i != 1) {
                    create3 = create(cls);
                    return create3;
                }
                create2 = create(cls);
                return create2;
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public final /* synthetic */ ViewModel create(KClass kClass, CreationExtras creationExtras) {
                ViewModel create;
                ViewModel create2;
                ViewModel create3;
                int i = this.switching_field;
                if (i == 0) {
                    create = create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass), creationExtras);
                    return create;
                }
                if (i != 1) {
                    create3 = create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass), creationExtras);
                    return create3;
                }
                create2 = create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass), creationExtras);
                return create2;
            }
        }
    }
}

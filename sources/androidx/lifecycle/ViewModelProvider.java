package androidx.lifecycle;

import android.app.Application;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.internal.JvmViewModelProviders;
import androidx.lifecycle.viewmodel.internal.ViewModelProviders;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewModelProvider {
    public final WorkManagerTaskExecutor impl$ar$class_merging$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AndroidViewModelFactory extends NewInstanceFactory {
        public static AndroidViewModelFactory _instance;
        private final Application application;
        public static final ViewCompat.Api20Impl Companion$ar$class_merging$f1357dbe_0 = new ViewCompat.Api20Impl();
        public static final CreationExtras.Key APPLICATION_KEY = new SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1();

        public AndroidViewModelFactory(Application application) {
            this.application = application;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls) {
            Application application = this.application;
            if (application != null) {
                return create(cls, application);
            }
            throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
        }

        public AndroidViewModelFactory() {
            this(null);
        }

        private final ViewModel create(Class cls, Application application) {
            if (AndroidViewModel.class.isAssignableFrom(cls)) {
                try {
                    ViewModel viewModel = (ViewModel) cls.getConstructor(Application.class).newInstance(application);
                    viewModel.getClass();
                    return viewModel;
                } catch (IllegalAccessException e) {
                    Objects.toString(cls);
                    throw new RuntimeException("Cannot create an instance of ".concat(cls.toString()), e);
                } catch (InstantiationException e2) {
                    Objects.toString(cls);
                    throw new RuntimeException("Cannot create an instance of ".concat(cls.toString()), e2);
                } catch (NoSuchMethodException e3) {
                    Objects.toString(cls);
                    throw new RuntimeException("Cannot create an instance of ".concat(cls.toString()), e3);
                } catch (InvocationTargetException e4) {
                    Objects.toString(cls);
                    throw new RuntimeException("Cannot create an instance of ".concat(cls.toString()), e4);
                }
            }
            return super.create(cls);
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls, CreationExtras creationExtras) {
            if (this.application != null) {
                return create(cls);
            }
            Application application = (Application) creationExtras.get(APPLICATION_KEY);
            if (application != null) {
                return create(cls, application);
            }
            if (!AndroidViewModel.class.isAssignableFrom(cls)) {
                return super.create(cls);
            }
            throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Factory {
        ViewModel create(Class cls);

        ViewModel create(Class cls, CreationExtras creationExtras);

        ViewModel create(KClass kClass, CreationExtras creationExtras);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NewInstanceFactory implements Factory {
        public static final ViewCompat.Api23Impl Companion$ar$class_merging$b1c2d844_0 = new ViewCompat.Api23Impl();
        public static final CreationExtras.Key VIEW_MODEL_KEY = SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1.INSTANCE$ar$class_merging$39cd80ef_0;
        public static NewInstanceFactory _instance;

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel create(Class cls) {
            return JvmViewModelProviders.createViewModel$ar$ds(cls);
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel create(Class cls, CreationExtras creationExtras) {
            return create(cls);
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(KClass kClass, CreationExtras creationExtras) {
            return create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass), creationExtras);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ViewModelProvider(ViewModelStore viewModelStore, Factory factory) {
        this(viewModelStore, factory, (byte[]) null);
        viewModelStore.getClass();
    }

    public final ViewModel get(Class cls) {
        KClass kotlinClass = OnDeviceShadowRemovalLogEvent.getKotlinClass(cls);
        String canonicalName = ViewCompat.Api30Impl.getCanonicalName(kotlinClass);
        if (canonicalName != null) {
            return this.impl$ar$class_merging$ar$class_merging.getViewModel$lifecycle_viewmodel_release(kotlinClass, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName));
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public ViewModelProvider(ViewModelStore viewModelStore, Factory factory, CreationExtras creationExtras) {
        viewModelStore.getClass();
        factory.getClass();
        creationExtras.getClass();
        this.impl$ar$class_merging$ar$class_merging = new WorkManagerTaskExecutor(viewModelStore, factory, creationExtras);
    }

    public /* synthetic */ ViewModelProvider(ViewModelStore viewModelStore, Factory factory, byte[] bArr) {
        this(viewModelStore, factory, CreationExtras.Empty.INSTANCE);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ViewModelProvider(androidx.lifecycle.ViewModelStoreOwner r3) {
        /*
            r2 = this;
            r3.getClass()
            boolean r0 = r3 instanceof androidx.lifecycle.HasDefaultViewModelProviderFactory
            androidx.lifecycle.ViewModelStore r1 = r3.getViewModelStore()
            if (r0 == 0) goto L13
            r0 = r3
            androidx.lifecycle.HasDefaultViewModelProviderFactory r0 = (androidx.lifecycle.HasDefaultViewModelProviderFactory) r0
            androidx.lifecycle.ViewModelProvider$Factory r0 = r0.getDefaultViewModelProviderFactory()
            goto L15
        L13:
            androidx.lifecycle.viewmodel.internal.DefaultViewModelProviderFactory r0 = androidx.lifecycle.viewmodel.internal.DefaultViewModelProviderFactory.INSTANCE
        L15:
            androidx.lifecycle.viewmodel.CreationExtras r3 = androidx.lifecycle.viewmodel.internal.ViewModelProviders.getDefaultCreationExtras$lifecycle_viewmodel_release$ar$ds(r3)
            r2.<init>(r1, r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.ViewModelProvider.<init>(androidx.lifecycle.ViewModelStoreOwner):void");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ViewModelProvider(ViewModelStoreOwner viewModelStoreOwner, Factory factory) {
        this(viewModelStoreOwner.getViewModelStore(), factory, ViewModelProviders.getDefaultCreationExtras$lifecycle_viewmodel_release$ar$ds(viewModelStoreOwner));
        viewModelStoreOwner.getClass();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class OnRequeryFactory {
        public void onRequery(ViewModel viewModel) {
        }
    }
}

package androidx.lifecycle.viewmodel.internal;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultViewModelProviderFactory implements ViewModelProvider.Factory {
    public static final DefaultViewModelProviderFactory INSTANCE = new DefaultViewModelProviderFactory();

    private DefaultViewModelProviderFactory() {
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final /* synthetic */ ViewModel create(Class cls) {
        return ViewCompat.Api21Impl.$default$create$ar$ds();
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final /* synthetic */ ViewModel create(Class cls, CreationExtras creationExtras) {
        ViewModel create;
        create = create(cls);
        return create;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(KClass kClass, CreationExtras creationExtras) {
        return JvmViewModelProviders.createViewModel$ar$ds(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass));
    }
}

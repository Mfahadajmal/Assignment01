package androidx.lifecycle.viewmodel;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InitializerViewModelFactory implements ViewModelProvider.Factory {
    private final NodeBrailler[] initializers$ar$class_merging;

    public InitializerViewModelFactory(NodeBrailler... nodeBraillerArr) {
        nodeBraillerArr.getClass();
        this.initializers$ar$class_merging = nodeBraillerArr;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final /* synthetic */ ViewModel create(Class cls) {
        return ViewCompat.Api21Impl.$default$create$ar$ds();
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final /* synthetic */ ViewModel create(KClass kClass, CreationExtras creationExtras) {
        ViewModel create;
        create = create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass), creationExtras);
        return create;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class cls, CreationExtras creationExtras) {
        NodeBrailler nodeBrailler;
        NodeBrailler[] nodeBraillerArr = this.initializers$ar$class_merging;
        int length = nodeBraillerArr.length;
        KClass kotlinClass = OnDeviceShadowRemovalLogEvent.getKotlinClass(cls);
        NodeBrailler[] nodeBraillerArr2 = (NodeBrailler[]) Arrays.copyOf(nodeBraillerArr, length);
        nodeBraillerArr2.getClass();
        int i = 0;
        while (true) {
            if (i >= nodeBraillerArr2.length) {
                nodeBrailler = null;
                break;
            }
            nodeBrailler = nodeBraillerArr2[i];
            if (Intrinsics.areEqual(nodeBrailler.NodeBrailler$ar$context, kotlinClass)) {
                break;
            }
            i++;
        }
        ViewModel viewModel = nodeBrailler != null ? (ViewModel) nodeBrailler.NodeBrailler$ar$rules.invoke(creationExtras) : null;
        if (viewModel != null) {
            return viewModel;
        }
        throw new IllegalArgumentException("No initializer set for given class ".concat(String.valueOf(ViewCompat.Api30Impl.getCanonicalName(kotlinClass))));
    }
}

package com.google.mlkit.vision.text.internal;

import com.google.common.collect.ImmutableList;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.internal.CommonComponentRegistrar$$ExternalSyntheticLambda1;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TextRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        Component.Builder builder = Component.builder(TextRecognizerTaskInstanceMap.class);
        builder.add$ar$ds$327096f_0(new Dependency(MlKitContext.class, 1, 0));
        builder.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(10));
        Component build = builder.build();
        Component.Builder builder2 = Component.builder(TextRecognizerImplFactory.class);
        builder2.add$ar$ds$327096f_0(new Dependency(TextRecognizerTaskInstanceMap.class, 1, 0));
        builder2.add$ar$ds$327096f_0(new Dependency(ExecutorSelector.class, 1, 0));
        builder2.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(11));
        return ImmutableList.of((Object) build, (Object) builder2.build());
    }
}

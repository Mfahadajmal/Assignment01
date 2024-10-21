package com.google.mlkit.vision.common.internal;

import com.google.common.collect.ImmutableList;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.internal.CommonComponentRegistrar$$ExternalSyntheticLambda1;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class VisionCommonRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        Component.Builder builder = Component.builder(MultiFlavorDetectorCreator.class);
        builder.add$ar$ds$327096f_0(new Dependency(MultiFlavorDetectorCreator.Registration.class, 2, 0));
        builder.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(9));
        return ImmutableList.of((Object) builder.build());
    }
}

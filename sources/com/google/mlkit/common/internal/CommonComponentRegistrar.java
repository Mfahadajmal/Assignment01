package com.google.mlkit.common.internal;

import com.google.common.collect.ImmutableList;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.common.sdkinternal.Cleaner;
import com.google.mlkit.common.sdkinternal.CloseGuard$Factory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CommonComponentRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        Component component = SharedPrefManager.COMPONENT;
        Component.Builder builder = Component.builder(ModelFileHelper.class);
        builder.add$ar$ds$327096f_0(new Dependency(MlKitContext.class, 1, 0));
        builder.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(1));
        Component build = builder.build();
        Component.Builder builder2 = Component.builder(MlKitThreadPool.class);
        builder2.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(0));
        Component build2 = builder2.build();
        Component.Builder builder3 = Component.builder(RemoteModelManager.class);
        builder3.add$ar$ds$327096f_0(new Dependency(RemoteModelManager.RemoteModelManagerRegistration.class, 2, 0));
        builder3.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(2));
        Component build3 = builder3.build();
        Component.Builder builder4 = Component.builder(ExecutorSelector.class);
        builder4.add$ar$ds$327096f_0(new Dependency(MlKitThreadPool.class, 1, 1));
        builder4.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(3));
        Component build4 = builder4.build();
        Component.Builder builder5 = Component.builder(Cleaner.class);
        builder5.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(4));
        Component build5 = builder5.build();
        Component.Builder builder6 = Component.builder(CloseGuard$Factory.class);
        builder6.add$ar$ds$327096f_0(new Dependency(Cleaner.class, 1, 0));
        builder6.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(5));
        Component build6 = builder6.build();
        Component.Builder builder7 = Component.builder(CustomRemoteModelManager.class);
        builder7.add$ar$ds$327096f_0(new Dependency(MlKitContext.class, 1, 0));
        builder7.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(6));
        Component build7 = builder7.build();
        Component.Builder intoSetBuilder = Component.intoSetBuilder(RemoteModelManager.RemoteModelManagerRegistration.class);
        intoSetBuilder.add$ar$ds$327096f_0(new Dependency(CustomRemoteModelManager.class, 1, 1));
        intoSetBuilder.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(7));
        return ImmutableList.of((Object) component, (Object) build, (Object) build2, (Object) build3, (Object) build4, (Object) build5, (Object) build6, (Object) build7, (Object) intoSetBuilder.build());
    }
}

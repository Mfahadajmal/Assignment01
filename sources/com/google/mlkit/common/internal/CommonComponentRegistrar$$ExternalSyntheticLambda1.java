package com.google.mlkit.common.internal;

import android.content.Context;
import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import com.google.firebase.components.Qualified;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.common.sdkinternal.Cleaner;
import com.google.mlkit.common.sdkinternal.CloseGuard$Factory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.text.internal.TextRecognizerImplFactory;
import com.google.mlkit.vision.text.internal.TextRecognizerTaskInstanceMap;
import java.lang.ref.ReferenceQueue;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class CommonComponentRegistrar$$ExternalSyntheticLambda1 implements ComponentFactory {
    private final /* synthetic */ int switching_field;

    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.Set, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Set, java.lang.Object] */
    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        Set of;
        Set of2;
        switch (this.switching_field) {
            case 0:
                return new MlKitThreadPool();
            case 1:
                return new ModelFileHelper();
            case 2:
                of = componentContainer.setOf(new Qualified(Qualified.Unqualified.class, RemoteModelManager.RemoteModelManagerRegistration.class));
                return new RemoteModelManager(of);
            case 3:
                return new ExecutorSelector(componentContainer.getProvider(MlKitThreadPool.class));
            case 4:
                Cleaner cleaner = new Cleaner();
                BrailleIme$21$$ExternalSyntheticLambda1 brailleIme$21$$ExternalSyntheticLambda1 = new BrailleIme$21$$ExternalSyntheticLambda1(9);
                cleaner.Cleaner$ar$cleanables.add(new Cleaner.CleanableImpl(cleaner, (ReferenceQueue) cleaner.Cleaner$ar$referenceQueue, cleaner.Cleaner$ar$cleanables, brailleIme$21$$ExternalSyntheticLambda1));
                Cleaner.lambda$create$0(new EventBus$$ExternalSyntheticLambda0(cleaner.Cleaner$ar$referenceQueue, cleaner.Cleaner$ar$cleanables, 16, null)).start();
                return cleaner;
            case 5:
                return new CloseGuard$Factory();
            case 6:
                return new CustomRemoteModelManager();
            case 7:
                return new RemoteModelManager.RemoteModelManagerRegistration(CustomRemoteModel.class, componentContainer.getProvider(CustomRemoteModelManager.class));
            case 8:
                return new SharedPrefManager((Context) componentContainer.get(Context.class));
            case 9:
                of2 = componentContainer.setOf(new Qualified(Qualified.Unqualified.class, MultiFlavorDetectorCreator.Registration.class));
                return new MultiFlavorDetectorCreator(of2);
            case 10:
                return new TextRecognizerTaskInstanceMap((MlKitContext) componentContainer.get(MlKitContext.class));
            default:
                return new TextRecognizerImplFactory((TextRecognizerTaskInstanceMap) componentContainer.get(TextRecognizerTaskInstanceMap.class), (ExecutorSelector) componentContainer.get(ExecutorSelector.class));
        }
    }
}

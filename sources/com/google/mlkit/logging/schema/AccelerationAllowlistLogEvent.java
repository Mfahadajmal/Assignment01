package com.google.mlkit.logging.schema;

import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.internal.TextRecognizerImpl;
import com.google.mlkit.vision.text.internal.TextRecognizerImplFactory;
import com.google.mlkit.vision.text.internal.TextRecognizerTaskWithResource;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccelerationAllowlistLogEvent {
    /* JADX WARN: Type inference failed for: r0v5, types: [com.google.firebase.inject.Provider, java.lang.Object] */
    public static TextRecognizer getClient(TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        TextRecognizerImplFactory textRecognizerImplFactory = (TextRecognizerImplFactory) MlKitContext.getInstance().get(TextRecognizerImplFactory.class);
        TextRecognizerTaskWithResource textRecognizerTaskWithResource = (TextRecognizerTaskWithResource) ((LazyInstanceMap) textRecognizerImplFactory.TextRecognizerImplFactory$ar$taskInstanceMap).get(textRecognizerOptionsInterface);
        Object obj = textRecognizerImplFactory.TextRecognizerImplFactory$ar$executorSelector;
        textRecognizerOptionsInterface.getExecutor$ar$ds();
        return new TextRecognizerImpl(textRecognizerTaskWithResource, (Executor) ((ExecutorSelector) obj).ExecutorSelector$ar$defaultExecutorProvider.get(), CustomRemoteModelManager.getLoggerInstance(textRecognizerOptionsInterface.getLoggingLibraryName()), textRecognizerOptionsInterface);
    }
}

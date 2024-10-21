package com.google.mlkit.vision.text.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextRecognizerTaskInstanceMap extends LazyInstanceMap {
    private final MlKitContext mlKitContext;

    public TextRecognizerTaskInstanceMap(MlKitContext mlKitContext) {
        this.mlKitContext = mlKitContext;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        TextRecognitionDelegate decoupledTextRecognitionDelegate;
        TextRecognizerOptionsInterface textRecognizerOptionsInterface = (TextRecognizerOptionsInterface) obj;
        MLKitStatsLogger loggerInstance = CustomRemoteModelManager.getLoggerInstance(textRecognizerOptionsInterface.getLoggingLibraryName());
        Context applicationContext = this.mlKitContext.getApplicationContext();
        int i = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        if (GooglePlayServicesUtilLight.getApkVersion(applicationContext) < 204700000 && !textRecognizerOptionsInterface.getIsThickClient()) {
            decoupledTextRecognitionDelegate = new LegacyTextRecognitionDelegate(applicationContext);
        } else {
            decoupledTextRecognitionDelegate = new DecoupledTextRecognitionDelegate(applicationContext, textRecognizerOptionsInterface, loggerInstance);
        }
        return new TextRecognizerTaskWithResource(loggerInstance, decoupledTextRecognitionDelegate, textRecognizerOptionsInterface);
    }
}

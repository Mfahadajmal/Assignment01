package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.logging.schema.MLKitEnum$ErrorCode;
import com.google.mlkit.logging.schema.OnDeviceDocumentEnhancementLogEvent;
import com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.aidls.ImageMetadataParcel;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.aidls.ICommonTextRecognizerCreator;
import com.google.mlkit.vision.text.aidls.ICommonTextRecognizerCreator$Stub$Proxy;
import com.google.mlkit.vision.text.aidls.ITextRecognizer;
import com.google.mlkit.vision.text.aidls.ITextRecognizerCreator;
import com.google.mlkit.vision.text.aidls.ITextRecognizerCreator$Stub$Proxy;
import com.google.mlkit.vision.text.aidls.TextRecognizerOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
final class DecoupledTextRecognitionDelegate implements TextRecognitionDelegate {
    private final Context context;
    private boolean initialized;
    private final MLKitStatsLogger mlKitStatsLogger;
    private boolean optionalModuleDownloadRequested;
    private ITextRecognizer textRecognizer;
    private final TextRecognizerOptionsInterface textRecognizerOptions;

    public DecoupledTextRecognitionDelegate(Context context, TextRecognizerOptionsInterface textRecognizerOptionsInterface, MLKitStatsLogger mLKitStatsLogger) {
        this.context = context;
        this.textRecognizerOptions = textRecognizerOptionsInterface;
        this.mlKitStatsLogger = mLKitStatsLogger;
    }

    private static TextRecognizerOptions getTextRecognizerOptions(TextRecognizerOptionsInterface textRecognizerOptionsInterface, String str) {
        boolean z = false;
        if ((textRecognizerOptionsInterface instanceof CustomPowerHintOption) && ((CustomPowerHintOption) textRecognizerOptionsInterface).enableLowLatencyInBackground()) {
            z = true;
        }
        boolean z2 = z;
        String configLabel = textRecognizerOptionsInterface.getConfigLabel();
        String loggingLibraryNameForOptionalModule = textRecognizerOptionsInterface.getLoggingLibraryNameForOptionalModule();
        textRecognizerOptionsInterface.getLoggingLanguageOption$ar$ds();
        int i = TextDetectionOptionalModuleOptions.DetectionType.LATIN$ar$edu;
        int i2 = i - 1;
        if (i != 0) {
            return new TextRecognizerOptions(configLabel, loggingLibraryNameForOptionalModule, str, true, i2, textRecognizerOptionsInterface.getLanguageHint(), z2);
        }
        throw null;
    }

    @Override // com.google.mlkit.vision.text.internal.TextRecognitionDelegate
    public final void load() {
        String str;
        ITextRecognizer newTextRecognizer;
        if (this.textRecognizer != null) {
            return;
        }
        try {
            TextRecognizerOptionsInterface textRecognizerOptionsInterface = this.textRecognizerOptions;
            boolean z = textRecognizerOptionsInterface instanceof CustomModelPathOption;
            ITextRecognizerCreator iTextRecognizerCreator = null;
            ITextRecognizerCreator iTextRecognizerCreator$Stub$Proxy = null;
            ICommonTextRecognizerCreator iCommonTextRecognizerCreator$Stub$Proxy = null;
            if (z) {
                str = ((CustomModelPathOption) textRecognizerOptionsInterface).getModelPath();
            } else {
                str = null;
            }
            if (this.textRecognizerOptions.getIsThickClient()) {
                IBinder instantiate = DynamiteModule.load(this.context, DynamiteModule.PREFER_LOCAL, this.textRecognizerOptions.getModuleId()).instantiate("com.google.mlkit.vision.text.bundled.common.BundledTextRecognizerCreator");
                if (instantiate != null) {
                    IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizerCreator");
                    if (queryLocalInterface instanceof ITextRecognizerCreator) {
                        iTextRecognizerCreator$Stub$Proxy = (ITextRecognizerCreator) queryLocalInterface;
                    } else {
                        iTextRecognizerCreator$Stub$Proxy = new ITextRecognizerCreator$Stub$Proxy(instantiate);
                    }
                }
                newTextRecognizer = iTextRecognizerCreator$Stub$Proxy.newTextRecognizerWithOptions(new IObjectWrapper.Stub(this.context), getTextRecognizerOptions(this.textRecognizerOptions, str));
            } else if (z) {
                IBinder instantiate2 = DynamiteModule.load(this.context, DynamiteModule.PREFER_REMOTE, this.textRecognizerOptions.getModuleId()).instantiate("com.google.android.gms.vision.text.mlkit.CommonTextRecognizerCreator");
                if (instantiate2 != null) {
                    IInterface queryLocalInterface2 = instantiate2.queryLocalInterface("com.google.mlkit.vision.text.aidls.ICommonTextRecognizerCreator");
                    if (queryLocalInterface2 instanceof ICommonTextRecognizerCreator) {
                        iCommonTextRecognizerCreator$Stub$Proxy = (ICommonTextRecognizerCreator) queryLocalInterface2;
                    } else {
                        iCommonTextRecognizerCreator$Stub$Proxy = new ICommonTextRecognizerCreator$Stub$Proxy(instantiate2);
                    }
                }
                newTextRecognizer = iCommonTextRecognizerCreator$Stub$Proxy.newTextRecognizerWithOptions$ar$ds(new IObjectWrapper.Stub(this.context), getTextRecognizerOptions(this.textRecognizerOptions, str));
            } else {
                IBinder instantiate3 = DynamiteModule.load(this.context, DynamiteModule.PREFER_REMOTE, this.textRecognizerOptions.getModuleId()).instantiate("com.google.android.gms.vision.text.mlkit.TextRecognizerCreator");
                if (instantiate3 != null) {
                    IInterface queryLocalInterface3 = instantiate3.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizerCreator");
                    if (queryLocalInterface3 instanceof ITextRecognizerCreator) {
                        iTextRecognizerCreator = (ITextRecognizerCreator) queryLocalInterface3;
                    } else {
                        iTextRecognizerCreator = new ITextRecognizerCreator$Stub$Proxy(instantiate3);
                    }
                }
                this.textRecognizerOptions.getLoggingLanguageOption$ar$ds();
                newTextRecognizer = iTextRecognizerCreator.newTextRecognizer(new IObjectWrapper.Stub(this.context));
            }
            this.textRecognizer = newTextRecognizer;
            OnDeviceDocumentEnhancementLogEvent.logLoadEvent(this.mlKitStatsLogger, this.textRecognizerOptions.getIsThickClient(), MLKitEnum$ErrorCode.NO_ERROR);
        } catch (RemoteException e) {
            OnDeviceDocumentEnhancementLogEvent.logLoadEvent(this.mlKitStatsLogger, this.textRecognizerOptions.getIsThickClient(), MLKitEnum$ErrorCode.OPTIONAL_MODULE_INIT_ERROR);
            throw new MlKitException("Failed to create text recognizer ".concat(this.textRecognizerOptions.getLoggingLibraryName()), e);
        } catch (DynamiteModule.LoadingException e2) {
            OnDeviceDocumentEnhancementLogEvent.logLoadEvent(this.mlKitStatsLogger, this.textRecognizerOptions.getIsThickClient(), MLKitEnum$ErrorCode.OPTIONAL_MODULE_NOT_AVAILABLE);
            if (!this.textRecognizerOptions.getIsThickClient()) {
                if (!this.optionalModuleDownloadRequested) {
                    OptionalModuleUtils.requestDownload(this.context, OnDeviceDocumentEnhancementLogEvent.getOptionalFeatures(this.textRecognizerOptions));
                    this.optionalModuleDownloadRequested = true;
                }
                throw new MlKitException("Waiting for the text optional module to be downloaded. Please wait.", 14);
            }
            throw new MlKitException(String.format("Failed to load text module %s. %s", this.textRecognizerOptions.getLoggingLibraryName(), e2.getMessage()), e2);
        }
    }

    @Override // com.google.mlkit.vision.text.internal.TextRecognitionDelegate
    public final void release() {
        ITextRecognizer iTextRecognizer = this.textRecognizer;
        if (iTextRecognizer != null) {
            try {
                iTextRecognizer.release();
            } catch (RemoteException e) {
                Log.e("DecoupledTextDelegate", "Failed to release text recognizer ".concat(this.textRecognizerOptions.getLoggingLibraryName()), e);
            }
            this.textRecognizer = null;
        }
        this.initialized = false;
    }

    @Override // com.google.mlkit.vision.text.internal.TextRecognitionDelegate
    public final MultiFlavorDetectorCreator run$ar$class_merging(InputImage inputImage) {
        if (this.textRecognizer == null) {
            load();
        }
        ITextRecognizer iTextRecognizer = this.textRecognizer;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(iTextRecognizer);
        if (!this.initialized) {
            try {
                iTextRecognizer.init();
                this.initialized = true;
            } catch (RemoteException e) {
                throw new MlKitException("Failed to init text recognizer ".concat(this.textRecognizerOptions.getLoggingLibraryName()), e);
            }
        }
        ImageMetadataParcel imageMetadataParcel = new ImageMetadataParcel(-1, inputImage.width, inputImage.height, 0, SystemClock.elapsedRealtime());
        ImageUtils imageUtils = ImageUtils.INSTANCE;
        Bitmap bitmap = inputImage.bitmap;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(bitmap);
        try {
            return new MultiFlavorDetectorCreator(iTextRecognizer.process(new IObjectWrapper.Stub(bitmap), imageMetadataParcel));
        } catch (RemoteException e2) {
            throw new MlKitException("Failed to run text recognizer ".concat(this.textRecognizerOptions.getLoggingLibraryName()), e2);
        }
    }
}

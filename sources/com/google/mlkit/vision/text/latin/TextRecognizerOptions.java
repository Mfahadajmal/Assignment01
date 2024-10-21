package com.google.mlkit.vision.text.latin;

import _COROUTINE._BOUNDARY;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextRecognizerOptions implements TextRecognizerOptionsInterface {
    public static final TextRecognizerOptions DEFAULT_OPTIONS = new TextRecognizerOptions();
    final AtomicReference isThickClientCache = new AtomicReference();
    private final Executor executor = null;
    private final String configLabel = "taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile";

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextRecognizerOptions)) {
            return false;
        }
        Executor executor = ((TextRecognizerOptions) obj).executor;
        return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String getConfigLabel() {
        return this.configLabel;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final boolean getIsThickClient() {
        boolean z;
        AtomicReference atomicReference = this.isThickClientCache;
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        if (DynamiteModule.getLocalVersion(MlKitContext.getInstance().getApplicationContext(), "com.google.mlkit.dynamite.text.latin") > 0) {
            z = true;
        } else {
            z = false;
        }
        atomicReference.set(Boolean.valueOf(z));
        return z;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String getLanguageHint() {
        return "en";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int getLoggingEventId() {
        if (getIsThickClient()) {
            return 24317;
        }
        return 24306;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String getLoggingLibraryName() {
        if (true != getIsThickClient()) {
            return "play-services-mlkit-text-recognition";
        }
        return "text-recognition";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String getLoggingLibraryNameForOptionalModule() {
        return "optional-module-text-latin";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String getModuleId() {
        if (true != getIsThickClient()) {
            return "com.google.android.gms.vision.ocr";
        }
        return "com.google.mlkit.dynamite.text.latin";
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{null});
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final void getExecutor$ar$ds() {
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final void getLoggingLanguageOption$ar$ds() {
    }
}

package com.google.mlkit.logging.schema;

import _COROUTINE._BOUNDARY;
import com.google.firebase.encoders.proto.ProtoEnum;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceTextRecognizerOptions {
    public LanguageOption languageOption;
    private Boolean isUsingLegacyApi = null;
    private String sdkVersion = null;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum LanguageOption implements ProtoEnum {
        TYPE_UNKNOWN(0),
        LATIN(1),
        LATIN_AND_CHINESE(2),
        LATIN_AND_DEVANAGARI(3),
        LATIN_AND_JAPANESE(4),
        LATIN_AND_KOREAN(5),
        CREDIT_CARD(6),
        DOCUMENT(7),
        PIXEL_AI(8);

        private final int value;

        LanguageOption(int i) {
            this.value = i;
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public final int getNumber() {
            return this.value;
        }
    }

    public OnDeviceTextRecognizerOptions(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        this.languageOption = (LanguageOption) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OnDeviceTextRecognizerOptions)) {
            return false;
        }
        OnDeviceTextRecognizerOptions onDeviceTextRecognizerOptions = (OnDeviceTextRecognizerOptions) obj;
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.languageOption, onDeviceTextRecognizerOptions.languageOption)) {
            Boolean bool = onDeviceTextRecognizerOptions.isUsingLegacyApi;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
                String str = onDeviceTextRecognizerOptions.sdkVersion;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.languageOption, null, null});
    }
}

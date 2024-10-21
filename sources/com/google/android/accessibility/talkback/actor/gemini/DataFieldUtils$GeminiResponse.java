package com.google.android.accessibility.talkback.actor.gemini;

import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DataFieldUtils$GeminiResponse {
    public final String blockReason;
    public final String finishReason;
    private final ImmutableList safetyRatings;
    public final String text;

    public DataFieldUtils$GeminiResponse() {
    }

    public static Compositor$HandleEventOptions builder$ar$class_merging$d9081d28_0() {
        Compositor$HandleEventOptions compositor$HandleEventOptions = new Compositor$HandleEventOptions(null, null);
        int i = ImmutableList.ImmutableList$ar$NoOp;
        compositor$HandleEventOptions.setSafetyRatings$ar$ds(RegularImmutableList.EMPTY);
        return compositor$HandleEventOptions;
    }

    public final String blockReason() {
        return this.blockReason;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataFieldUtils$GeminiResponse) {
            DataFieldUtils$GeminiResponse dataFieldUtils$GeminiResponse = (DataFieldUtils$GeminiResponse) obj;
            String str = this.text;
            if (str != null ? str.equals(dataFieldUtils$GeminiResponse.text()) : dataFieldUtils$GeminiResponse.text() == null) {
                String str2 = this.finishReason;
                if (str2 != null ? str2.equals(dataFieldUtils$GeminiResponse.finishReason()) : dataFieldUtils$GeminiResponse.finishReason() == null) {
                    String str3 = this.blockReason;
                    if (str3 != null ? str3.equals(dataFieldUtils$GeminiResponse.blockReason()) : dataFieldUtils$GeminiResponse.blockReason() == null) {
                        if (ContextDataProvider.equalsImpl(this.safetyRatings, dataFieldUtils$GeminiResponse.safetyRatings())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final String finishReason() {
        return this.finishReason;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        String str = this.text;
        int i = 0;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        String str2 = this.finishReason;
        if (str2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = str2.hashCode();
        }
        int i2 = hashCode ^ 1000003;
        String str3 = this.blockReason;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return (((((i2 * 1000003) ^ hashCode2) * 1000003) ^ i) * 1000003) ^ this.safetyRatings.hashCode();
    }

    public final ImmutableList safetyRatings() {
        return this.safetyRatings;
    }

    public final String text() {
        return this.text;
    }

    public final String toString() {
        return "GeminiResponse{text=" + this.text + ", finishReason=" + this.finishReason + ", blockReason=" + this.blockReason + ", safetyRatings=" + String.valueOf(this.safetyRatings) + "}";
    }

    public DataFieldUtils$GeminiResponse(String str, String str2, String str3, ImmutableList immutableList) {
        this();
        this.text = str;
        this.finishReason = str2;
        this.blockReason = str3;
        this.safetyRatings = immutableList;
    }
}

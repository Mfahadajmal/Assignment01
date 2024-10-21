package com.google.android.accessibility.utils.caption;

import android.text.TextUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Result {
    public final float confidence;
    public final CharSequence text;
    public final ImageCaptionUtils$CaptionType type;

    public Result() {
    }

    public static boolean isEmpty(Result result) {
        if (result != null && !TextUtils.isEmpty(result.text())) {
            return false;
        }
        return true;
    }

    public final float confidence() {
        return this.confidence;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Result)) {
            return false;
        }
        Result result = (Result) obj;
        if (type() == result.type() && TextUtils.equals(text(), result.text()) && confidence() == result.confidence()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(type(), text(), Float.valueOf(confidence()));
    }

    public final CharSequence text() {
        return this.text;
    }

    public final String toString() {
        return "type: " + type().toString() + ", result: " + String.valueOf(text()) + ", confidence: " + confidence();
    }

    public final ImageCaptionUtils$CaptionType type() {
        return this.type;
    }

    public Result(ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType, CharSequence charSequence, float f) {
        this();
        if (imageCaptionUtils$CaptionType == null) {
            throw new NullPointerException("Null type");
        }
        this.type = imageCaptionUtils$CaptionType;
        this.text = charSequence;
        this.confidence = f;
    }
}

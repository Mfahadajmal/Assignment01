package com.google.android.accessibility.talkback.compositor;

import com.google.android.accessibility.talkback.actor.gemini.DataFieldUtils$GeminiResponse;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Compositor$HandleEventOptions {
    public Object Compositor$HandleEventOptions$ar$eventInterpretation;
    public Object Compositor$HandleEventOptions$ar$eventObject;
    public Object Compositor$HandleEventOptions$ar$onCompleteRunnable;
    public Object Compositor$HandleEventOptions$ar$sourceNode;

    public Compositor$HandleEventOptions() {
    }

    public final DataFieldUtils$GeminiResponse build() {
        Object obj = this.Compositor$HandleEventOptions$ar$eventObject;
        if (obj != null) {
            Object obj2 = this.Compositor$HandleEventOptions$ar$onCompleteRunnable;
            return new DataFieldUtils$GeminiResponse((String) obj2, (String) this.Compositor$HandleEventOptions$ar$eventInterpretation, (String) this.Compositor$HandleEventOptions$ar$sourceNode, (ImmutableList) obj);
        }
        throw new IllegalStateException("Missing required properties: safetyRatings");
    }

    public final void setSafetyRatings$ar$ds(ImmutableList immutableList) {
        if (immutableList != null) {
            this.Compositor$HandleEventOptions$ar$eventObject = immutableList;
            return;
        }
        throw new NullPointerException("Null safetyRatings");
    }

    public Compositor$HandleEventOptions(byte[] bArr, byte[] bArr2) {
        this();
    }
}

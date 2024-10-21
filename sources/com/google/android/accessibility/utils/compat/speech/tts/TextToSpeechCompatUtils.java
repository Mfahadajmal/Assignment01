package com.google.android.accessibility.utils.compat.speech.tts;

import android.speech.tts.TextToSpeech;
import com.google.android.accessibility.utils.compat.CompatUtils;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextToSpeechCompatUtils {
    public static final Method METHOD_getCurrentEngine = CompatUtils.getMethod(TextToSpeech.class, "getCurrentEngine", new Class[0]);
}

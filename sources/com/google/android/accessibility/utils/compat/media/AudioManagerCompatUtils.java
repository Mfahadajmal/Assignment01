package com.google.android.accessibility.utils.compat.media;

import android.media.AudioManager;
import com.google.android.accessibility.utils.compat.CompatUtils;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AudioManagerCompatUtils {
    public static final /* synthetic */ int AudioManagerCompatUtils$ar$NoOp = 0;
    private static final Method METHOD_forceVolumeControlStream = CompatUtils.getMethod(AudioManager.class, "forceVolumeControlStream", Integer.TYPE);

    public static void forceVolumeControlStream(AudioManager audioManager, int i) {
        CompatUtils.invoke(audioManager, null, METHOD_forceVolumeControlStream, Integer.valueOf(i));
    }
}

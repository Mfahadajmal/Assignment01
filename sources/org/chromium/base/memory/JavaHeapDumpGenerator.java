package org.chromium.base.memory;

import android.os.Debug;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JavaHeapDumpGenerator {
    private JavaHeapDumpGenerator() {
    }

    public static boolean generateHprof(String str) {
        try {
            Debug.dumpHprofData(str);
            return true;
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }
}

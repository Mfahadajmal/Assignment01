package com.google.android.libraries.performance.primes.metrics.jank;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.system.Os;
import android.view.WindowManager;
import com.google.android.libraries.storage.file.openers.ReadFileOpener;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DisplayStats {
    public static volatile long maxFrameRenderTimeNs;
    private static volatile float refreshRate;

    public DisplayStats() {
    }

    public static IOException attachFileDebugInfoV2$ar$class_merging$ar$class_merging$ar$class_merging(OptionalMethod optionalMethod, Uri uri, IOException iOException, String str) {
        try {
            ReadFileOpener readFileOpener = new ReadFileOpener();
            readFileOpener.shortCircuit = true;
            File file = (File) optionalMethod.open(uri, readFileOpener);
            if (file.exists()) {
                if (file.isFile()) {
                    if (file.canRead()) {
                        if (file.canWrite()) {
                            return attachParentStacktrace(file, iOException, str);
                        }
                        return attachParentStacktrace(file, iOException, str);
                    }
                    if (file.canWrite()) {
                        return attachParentStacktrace(file, iOException, str);
                    }
                    return attachParentStacktrace(file, iOException, str);
                }
                if (file.canRead()) {
                    if (file.canWrite()) {
                        return attachParentStacktrace(file, iOException, str);
                    }
                    return attachParentStacktrace(file, iOException, str);
                }
                if (file.canWrite()) {
                    return attachParentStacktrace(file, iOException, str);
                }
                return attachParentStacktrace(file, iOException, str);
            }
            return attachParentStacktrace(file, iOException, str);
        } catch (IOException unused) {
            return new IOException(iOException);
        }
    }

    private static IOException attachFilesystemMessage(File file, IOException iOException, String str) {
        String concat;
        try {
            concat = "Inoperable file:" + String.format(Locale.US, " canonical[%s] freeSpace[%d] protoName[%s]", file.getCanonicalPath(), Long.valueOf(file.getFreeSpace()), str);
            try {
                concat = concat + String.format(Locale.US, " mode[%d]", Integer.valueOf(Os.stat(file.getCanonicalPath()).st_mode));
            } catch (Exception unused) {
            }
        } catch (IOException unused2) {
            concat = String.valueOf("Inoperable file:").concat(" failed");
        }
        return new IOException(concat, iOException);
    }

    private static IOException attachParentStacktrace(File file, IOException iOException, String str) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return attachFilesystemMessage(file, iOException, str);
        }
        if (parentFile.exists()) {
            if (parentFile.isDirectory()) {
                if (parentFile.canRead()) {
                    if (parentFile.canWrite()) {
                        return attachFilesystemMessage(file, iOException, str);
                    }
                    return attachFilesystemMessage(file, iOException, str);
                }
                if (parentFile.canWrite()) {
                    return attachFilesystemMessage(file, iOException, str);
                }
                return attachFilesystemMessage(file, iOException, str);
            }
            if (parentFile.canRead()) {
                if (parentFile.canWrite()) {
                    return attachFilesystemMessage(file, iOException, str);
                }
                return attachFilesystemMessage(file, iOException, str);
            }
            if (parentFile.canWrite()) {
                return attachFilesystemMessage(file, iOException, str);
            }
            return attachFilesystemMessage(file, iOException, str);
        }
        return attachFilesystemMessage(file, iOException, str);
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (z) {
        } else {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static Optional getRefreshRate(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Absent.INSTANCE;
        }
        float f = refreshRate;
        if (f == 0.0f) {
            synchronized (DisplayStats.class) {
                f = refreshRate;
                if (f == 0.0f) {
                    float refreshRate2 = ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getRefreshRate();
                    refreshRate = refreshRate2;
                    f = refreshRate2;
                }
            }
        }
        return Optional.of(Float.valueOf(f));
    }

    public DisplayStats(char[] cArr) {
    }
}

package com.google.common.flogger.backend;

import com.google.common.flogger.AbstractLogger;
import com.google.common.flogger.LogSite;
import com.google.common.flogger.backend.android.AndroidPlatform;
import com.google.common.flogger.backend.android.AndroidPlatformHolder;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.flogger.context.NoOpContextDataProvider;
import com.google.common.flogger.context.Tags;
import com.google.common.flogger.util.RecursionDepth;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Platform {
    private static String ANDROID_PLATFORM = "com.google.common.flogger.backend.android.AndroidPlatform";
    private static final String[] AVAILABLE_PLATFORMS = {"com.google.common.flogger.backend.android.AndroidPlatform", "com.google.common.flogger.backend.google.GooglePlatform", "com.google.common.flogger.backend.system.DefaultPlatform"};
    private static String DEFAULT_PLATFORM = "com.google.common.flogger.backend.system.DefaultPlatform";
    private static String GOOGLE_PLATFORM = "com.google.common.flogger.backend.google.GooglePlatform";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LazyHolder {
        public static final Platform INSTANCE = loadFirstAvailablePlatform(Platform.AVAILABLE_PLATFORMS);

        private static Platform loadFirstAvailablePlatform(String[] strArr) {
            AndroidPlatform androidPlatform;
            try {
                androidPlatform = AndroidPlatformHolder.INSTANCE;
            } catch (NoClassDefFoundError unused) {
                androidPlatform = null;
            }
            if (androidPlatform != null) {
                return androidPlatform;
            }
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                try {
                    return (Platform) Class.forName(str).getConstructor(null).newInstance(null);
                } catch (Throwable th) {
                    th = th;
                    if (th instanceof InvocationTargetException) {
                        th = th.getCause();
                    }
                    sb.append('\n');
                    sb.append(str);
                    sb.append(": ");
                    sb.append(th);
                }
            }
            throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class LogCallerFinder {
        public abstract LogSite findLogSite(Class<?> cls, int i);

        public abstract String findLoggingClass(Class<? extends AbstractLogger<?>> cls);
    }

    public static LoggerBackend getBackend(String str) {
        return LazyHolder.INSTANCE.getBackendImpl(str);
    }

    public static LogCallerFinder getCallerFinder() {
        return LazyHolder.INSTANCE.getCallerFinderImpl();
    }

    public static String getConfigInfo() {
        return LazyHolder.INSTANCE.getConfigInfoImpl();
    }

    public static ContextDataProvider getContextDataProvider() {
        return LazyHolder.INSTANCE.getContextDataProviderImpl();
    }

    public static int getCurrentRecursionDepth() {
        return ((RecursionDepth) RecursionDepth.holder.get()).value;
    }

    public static long getCurrentTimeNanos() {
        return LazyHolder.INSTANCE.getCurrentTimeNanosImpl();
    }

    public static Metadata getInjectedMetadata() {
        return getContextDataProvider().getMetadata();
    }

    public static Tags getInjectedTags() {
        return getContextDataProvider().getTags();
    }

    public static boolean shouldForceLogging(String str, Level level, boolean z) {
        getContextDataProvider().shouldForceLogging$ar$ds(str, level, z);
        return false;
    }

    protected abstract LoggerBackend getBackendImpl(String str);

    protected abstract LogCallerFinder getCallerFinderImpl();

    protected abstract String getConfigInfoImpl();

    protected ContextDataProvider getContextDataProviderImpl() {
        return NoOpContextDataProvider.NO_OP_INSTANCE;
    }

    protected long getCurrentTimeNanosImpl() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }
}

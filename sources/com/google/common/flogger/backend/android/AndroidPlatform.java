package com.google.common.flogger.backend.android;

import android.os.Build;
import com.google.common.flogger.AbstractLogger;
import com.google.common.flogger.LogSite;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.backend.android.ProxyAndroidLoggerBackend;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.flogger.util.CallerFinder;
import com.google.common.flogger.util.StackGetter;
import dalvik.system.VMStack;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidPlatform extends Platform {
    private static final Platform.LogCallerFinder CALLER_FINDER;
    private static final boolean IS_ROBOLECTRIC;
    private static final boolean USE_FAST_ANDROID_STACK = InternalStackVerifier.isVmStackPresent();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InternalStackVerifier {
        InternalStackVerifier() {
        }

        static boolean isVmStackPresent() {
            return AndroidPlatform.isVmStackPresent();
        }
    }

    static {
        boolean z = true;
        if (Build.FINGERPRINT != null && !"robolectric".equals(Build.FINGERPRINT)) {
            z = false;
        }
        IS_ROBOLECTRIC = z;
        CALLER_FINDER = new Platform.LogCallerFinder() { // from class: com.google.common.flogger.backend.android.AndroidPlatform.1
            @Override // com.google.common.flogger.backend.Platform.LogCallerFinder
            public LogSite findLogSite(Class<?> cls, int i) {
                return LogSite.INVALID;
            }

            @Override // com.google.common.flogger.backend.Platform.LogCallerFinder
            public String findLoggingClass(Class<? extends AbstractLogger<?>> cls) {
                if (AndroidPlatform.USE_FAST_ANDROID_STACK) {
                    try {
                        if (cls.equals(AndroidPlatform.getStackClass1())) {
                            return VMStack.getStackClass2().getName();
                        }
                    } catch (Throwable unused) {
                    }
                }
                if (AndroidPlatform.IS_ROBOLECTRIC) {
                    StackGetter stackGetter = CallerFinder.STACK_GETTER;
                    cls.getClass();
                    StackTraceElement callerOf$ar$ds = CallerFinder.STACK_GETTER.callerOf$ar$ds(cls);
                    if (callerOf$ar$ds != null) {
                        return callerOf$ar$ds.getClassName();
                    }
                    return null;
                }
                return null;
            }
        };
    }

    static Class<?> getStackClass1() {
        return VMStack.getStackClass2();
    }

    static boolean isVmStackPresent() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", null);
            return InternalStackVerifier.class.getName().equals(staticGetLoggingClassName());
        } catch (Throwable unused) {
            return false;
        }
    }

    static String staticGetLoggingClassName() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.google.common.flogger.backend.Platform
    protected LoggerBackend getBackendImpl(String str) {
        if (ProxyAndroidLoggerBackend.backendFactory.get() != null) {
            return ((AndroidBackendFactory) ProxyAndroidLoggerBackend.backendFactory.get()).create(str);
        }
        int length = str.length();
        while (true) {
            length--;
            if (length >= 0) {
                char charAt = str.charAt(length);
                if (charAt == '$') {
                    str = str.replace('$', '.');
                    break;
                }
                if (charAt == '.') {
                    break;
                }
            } else {
                break;
            }
        }
        ProxyAndroidLoggerBackend proxyAndroidLoggerBackend = new ProxyAndroidLoggerBackend(str);
        ProxyAndroidLoggerBackend.LazyProxyQueueHolder.backendlessProxyQueue.offer(proxyAndroidLoggerBackend);
        if (ProxyAndroidLoggerBackend.backendFactory.get() != null) {
            while (true) {
                ProxyAndroidLoggerBackend proxyAndroidLoggerBackend2 = (ProxyAndroidLoggerBackend) ProxyAndroidLoggerBackend.LazyProxyQueueHolder.backendlessProxyQueue.poll();
                if (proxyAndroidLoggerBackend2 == null) {
                    break;
                }
                proxyAndroidLoggerBackend2.backend = ((AndroidBackendFactory) ProxyAndroidLoggerBackend.backendFactory.get()).create(proxyAndroidLoggerBackend2.getLoggerName());
            }
            ProxyAndroidLoggerBackend.flushBuffer();
        }
        return proxyAndroidLoggerBackend;
    }

    @Override // com.google.common.flogger.backend.Platform
    protected Platform.LogCallerFinder getCallerFinderImpl() {
        return CALLER_FINDER;
    }

    @Override // com.google.common.flogger.backend.Platform
    protected String getConfigInfoImpl() {
        return "platform: Android";
    }

    @Override // com.google.common.flogger.backend.Platform
    protected ContextDataProvider getContextDataProviderImpl() {
        return ProxyContextDataProvider.INSTANCE;
    }
}

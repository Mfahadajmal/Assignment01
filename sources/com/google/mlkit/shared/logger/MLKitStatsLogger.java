package com.google.mlkit.shared.logger;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompatBuilder$Api29Impl;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.common.sdkinternal.CloseGuard$Factory;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.logging.schema.MLKitEnum$EventName;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MLKitStatsLogger {
    private static final ImmutableMap LIBRARY_NAME_MODULE_ID_MAP;
    private static ImmutableList languages;
    public final String appPackageName;
    public final String appVersion;
    public final String libraryName;
    public final LoggingTransportInterface loggingTransport;
    public final Task mlSdkInstanceIdTask;
    private final Task mlSdkVersionTask;
    public final int optionalModuleVersion;
    public final SharedPrefManager sharedPrefManager;
    public final Map logTimestamps = new HashMap();
    public final Map durationsMsMap = new HashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface LogEventProvider {
        SchemaLogEvent provideLogEvent$ar$class_merging();
    }

    static {
        ContextDataProvider.checkEntryNotNull("optional-module-barcode", "com.google.android.gms.vision.barcode");
        LIBRARY_NAME_MODULE_ID_MAP = RegularImmutableMap.create(1, new Object[]{"optional-module-barcode", "com.google.android.gms.vision.barcode"});
    }

    public MLKitStatsLogger(Context context, SharedPrefManager sharedPrefManager, LoggingTransportInterface loggingTransportInterface, String str) {
        String str2;
        int i;
        this.appPackageName = context.getPackageName();
        GmsLogger gmsLogger = CommonUtils.LOGGER;
        try {
            str2 = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            GmsLogger gmsLogger2 = CommonUtils.LOGGER;
            String concat = "Exception thrown when trying to get app version ".concat(e.toString());
            if (gmsLogger2.canLog(6)) {
                Log.e("CommonUtils", gmsLogger2.prefix(concat));
            }
            str2 = "";
        }
        this.appVersion = str2;
        this.sharedPrefManager = sharedPrefManager;
        this.loggingTransport = loggingTransportInterface;
        CloseGuard$Factory.getInstance$ar$ds$cb56d710_0();
        this.libraryName = str;
        this.mlSdkVersionTask = MLTaskExecutor.getInstance().scheduleCallable(new WorkerWrapper$$ExternalSyntheticLambda0(this, 11));
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.mlSdkInstanceIdTask = mLTaskExecutor.scheduleCallable(new WorkerWrapper$$ExternalSyntheticLambda0(sharedPrefManager, 12));
        ImmutableMap immutableMap = LIBRARY_NAME_MODULE_ID_MAP;
        if (immutableMap.containsKey(str)) {
            i = DynamiteModule.getRemoteVersion(context, (String) immutableMap.get(str), false);
        } else {
            i = -1;
        }
        this.optionalModuleVersion = i;
    }

    public static synchronized ImmutableList getLanguages() {
        synchronized (MLKitStatsLogger.class) {
            ImmutableList immutableList = languages;
            if (immutableList != null) {
                return immutableList;
            }
            LocaleListCompat locales = NotificationCompatBuilder$Api29Impl.getLocales(Resources.getSystem().getConfiguration());
            ImmutableList.Builder builder = new ImmutableList.Builder();
            for (int i = 0; i < locales.size(); i++) {
                Locale locale = locales.get(i);
                GmsLogger gmsLogger = CommonUtils.LOGGER;
                builder.add$ar$ds$4f674a09_0(locale.toLanguageTag());
            }
            ImmutableList build = builder.build();
            languages = build;
            return build;
        }
    }

    public static long percentile(List list, double d) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    public final void logEventWithEventName$ar$class_merging(SchemaLogEvent schemaLogEvent, MLKitEnum$EventName mLKitEnum$EventName) {
        String version;
        if (this.mlSdkVersionTask.isSuccessful()) {
            version = (String) this.mlSdkVersionTask.getResult();
        } else {
            version = LibraryVersion.INSTANCE.getVersion(this.libraryName);
        }
        MLTaskExecutor.WorkerThreadExecutor.INSTANCE.execute(new WindowInsetsAnimationCompat$Impl21.Impl21OnApplyWindowInsetsListener.AnonymousClass3(this, schemaLogEvent, mLKitEnum$EventName, version, 4));
    }

    public final void logThrottledEventWithEventName(LogEventProvider logEventProvider, MLKitEnum$EventName mLKitEnum$EventName) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!shouldSendLog$ar$ds(mLKitEnum$EventName, elapsedRealtime)) {
            return;
        }
        this.logTimestamps.put(mLKitEnum$EventName, Long.valueOf(elapsedRealtime));
        logEventWithEventName$ar$class_merging(logEventProvider.provideLogEvent$ar$class_merging(), mLKitEnum$EventName);
    }

    public final boolean shouldSendLog$ar$ds(MLKitEnum$EventName mLKitEnum$EventName, long j) {
        if (this.logTimestamps.get(mLKitEnum$EventName) == null || j - ((Long) this.logTimestamps.get(mLKitEnum$EventName)).longValue() > TimeUnit.SECONDS.toMillis(30L)) {
            return true;
        }
        return false;
    }
}

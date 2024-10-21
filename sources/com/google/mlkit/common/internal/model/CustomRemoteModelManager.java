package com.google.mlkit.common.internal.model;

import com.google.gson.JsonElement;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.internal.ReflectionAccessFilterHelper$AccessChecker;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonWriter;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.shared.logger.MLKitLoggingOptions;
import com.google.mlkit.shared.logger.MLKitLoggingTransport;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.shared.logger.firelog.MLKitStatsLoggerProvider$InstanceMap;
import java.lang.reflect.AccessibleObject;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CustomRemoteModelManager {
    public static volatile CustomRemoteModelManager INSTANCE$ar$class_merging$bc738628_0$ar$class_merging$ar$class_merging;
    private static MLKitStatsLoggerProvider$InstanceMap instanceMap;

    public CustomRemoteModelManager(byte[] bArr) {
    }

    public static boolean canAccess(AccessibleObject accessibleObject, Object obj) {
        return ReflectionAccessFilterHelper$AccessChecker.INSTANCE.canAccess(accessibleObject, obj);
    }

    public static void checkArgument(boolean z) {
        if (z) {
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String createUrl(String str) {
        return "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat(str);
    }

    public static int getFilterResult$ar$edu(List list, Class cls) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int check$ar$edu$ar$ds = ((ReflectionAccessFilter) it.next()).check$ar$edu$ar$ds();
            if (check$ar$edu$ar$ds != 2) {
                return check$ar$edu$ar$ds;
            }
        }
        return 1;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.mlkit.shared.logger.firelog.MLKitStatsLoggerProvider$InstanceMap] */
    public static synchronized MLKitStatsLogger getLoggerInstance(MLKitLoggingOptions mLKitLoggingOptions) {
        MLKitStatsLogger mLKitStatsLogger;
        synchronized (CustomRemoteModelManager.class) {
            if (instanceMap == null) {
                instanceMap = new LazyInstanceMap() { // from class: com.google.mlkit.shared.logger.firelog.MLKitStatsLoggerProvider$InstanceMap
                    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
                    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
                        MLKitLoggingOptions mLKitLoggingOptions2 = (MLKitLoggingOptions) obj;
                        MlKitContext mlKitContext = MlKitContext.getInstance();
                        return new MLKitStatsLogger(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new MLKitLoggingTransport(MlKitContext.getInstance().getApplicationContext(), mLKitLoggingOptions2), mLKitLoggingOptions2.getLibraryName());
                    }
                };
            }
            mLKitStatsLogger = (MLKitStatsLogger) instanceMap.get(mLKitLoggingOptions);
        }
        return mLKitStatsLogger;
    }

    public static void write(JsonElement jsonElement, JsonWriter jsonWriter) {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter, jsonElement);
    }

    public CustomRemoteModelManager(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this(null);
    }

    public CustomRemoteModelManager() {
        getLoggerInstance("common");
    }

    public static synchronized MLKitStatsLogger getLoggerInstance(String str) {
        MLKitStatsLogger loggerInstance;
        synchronized (CustomRemoteModelManager.class) {
            MLKitLoggingOptions.Builder builder = new MLKitLoggingOptions.Builder(null);
            builder.MLKitLoggingOptions$Builder$ar$libraryName = str;
            builder.enableFirelog = true;
            builder.firelogEventType = 1;
            builder.set$0 = (byte) 3;
            Object obj = builder.MLKitLoggingOptions$Builder$ar$libraryName;
            if (obj == null) {
                StringBuilder sb = new StringBuilder();
                if (builder.MLKitLoggingOptions$Builder$ar$libraryName == null) {
                    sb.append(" libraryName");
                }
                if ((1 & builder.set$0) == 0) {
                    sb.append(" enableFirelog");
                }
                if ((builder.set$0 & 2) == 0) {
                    sb.append(" firelogEventType");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }
            loggerInstance = getLoggerInstance(new MLKitLoggingOptions((String) obj, true, 1));
        }
        return loggerInstance;
    }
}

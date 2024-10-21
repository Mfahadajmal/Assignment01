package org.chromium.net.impl;

import J.N;
import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.TranslatorOptions;
import com.google.mlkit.logging.schema.acceleration.GPUInfo;
import com.google.protobuf.ByteString;
import com.google.protobuf.MapFieldLite;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import org.chromium.base.ContextUtils;
import org.chromium.net.httpflags.BaseFeature$ParsedFlagName;
import org.chromium.net.httpflags.BaseFeatureOverrides;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CronetLibraryLoader {
    public static ExecutorSelector sHttpFlags$ar$class_merging;
    private static boolean sInitialized;
    public static CronetInitializedInfo sInitializedInfo;
    private static final Object sLoadLock = new Object();
    private static final String LIBRARY_NAME = "cronet.".concat(ImplVersion.getCronetVersion());
    public static final String TAG = "CronetLibraryLoader";
    private static final HandlerThread sInitThread = new HandlerThread("CronetInit");
    public static final ConditionVariable sWaitForLibLoad = new ConditionVariable();
    public static final ConditionVariable sHttpFlagsLoaded = new ConditionVariable();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetInitializedInfo {
        public int httpFlagsLatencyMillis = -1;
        public List httpFlagsNames;
        public Boolean httpFlagsSuccessful;
        public List httpFlagsValues;
    }

    public static boolean ensureInitialized(Context context, CronetEngineBuilderImpl cronetEngineBuilderImpl, boolean z) {
        int i;
        synchronized (sLoadLock) {
            if (sInitialized) {
                return false;
            }
            ContextUtils.sApplicationContext = context;
            HandlerThread handlerThread = sInitThread;
            if (!handlerThread.isAlive()) {
                handlerThread.start();
                postToInitThread(new Runnable() { // from class: org.chromium.net.impl.CronetLibraryLoader$$ExternalSyntheticLambda0
                    /* JADX WARN: Removed duplicated region for block: B:155:0x00bd  */
                    /* JADX WARN: Removed duplicated region for block: B:157:0x00bf  */
                    /* JADX WARN: Removed duplicated region for block: B:56:0x0209 A[Catch: RuntimeException -> 0x0216, TRY_LEAVE, TryCatch #0 {RuntimeException -> 0x0216, blocks: (B:11:0x0101, B:12:0x010d, B:14:0x0113, B:16:0x011e, B:19:0x0126, B:21:0x012b, B:22:0x0132, B:25:0x013c, B:27:0x0142, B:30:0x014b, B:37:0x0152, B:45:0x0177, B:56:0x0209, B:61:0x018c, B:62:0x01a2, B:64:0x01a3, B:66:0x01a9, B:67:0x01b0, B:69:0x01ae, B:70:0x01b4, B:72:0x01bc, B:73:0x01c0, B:74:0x01c4, B:76:0x01ca, B:77:0x01d4, B:79:0x01d8, B:81:0x01de, B:82:0x01e9, B:84:0x01ed, B:86:0x01f3, B:87:0x01fd, B:91:0x0204, B:92:0x0166, B:93:0x0169, B:94:0x016c, B:95:0x016f, B:96:0x0172, B:97:0x0175), top: B:10:0x0101 }] */
                    /* JADX WARN: Removed duplicated region for block: B:59:0x0212 A[SYNTHETIC] */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void run() {
                        /*
                            Method dump skipped, instructions count: 841
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.impl.CronetLibraryLoader$$ExternalSyntheticLambda0.run():void");
                    }
                });
            }
            if (!z) {
                if (cronetEngineBuilderImpl.libraryLoader() != null) {
                    cronetEngineBuilderImpl.libraryLoader().loadLibrary(LIBRARY_NAME);
                } else {
                    System.loadLibrary(LIBRARY_NAME);
                }
            }
            N.MAuYp$hS();
            String cronetVersion = ImplVersion.getCronetVersion();
            if (cronetVersion.equals(N.M6xubM8G())) {
                String.format(Locale.US, "Cronet version: %s, arch: %s", cronetVersion, System.getProperty("os.arch"));
                if (Log.isLoggable("chromium", 2)) {
                    i = -2;
                } else {
                    if (Log.isLoggable("chromium", 3)) {
                        i = -1;
                    }
                    sWaitForLibLoad.open();
                    sInitialized = true;
                    return true;
                }
                N.Mrxu2pQS(i);
                sWaitForLibLoad.open();
                sInitialized = true;
                return true;
            }
            throw new RuntimeException(String.format("Expected Cronet version number %s, actual version number %s.", cronetVersion, N.M6xubM8G()));
        }
    }

    private static void ensureInitializedFromNative() {
        ensureInitialized(ContextUtils.sApplicationContext, null, true);
    }

    private static byte[] getBaseFeatureOverrides() {
        BaseFeature$ParsedFlagName baseFeature$ParsedFlagName;
        ByteString copyFrom;
        ExecutorSelector httpFlags$ar$class_merging = getHttpFlags$ar$class_merging();
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : httpFlags$ar$class_merging.flags().entrySet()) {
            try {
                String str = (String) entry.getKey();
                ExecutorSelector executorSelector = (ExecutorSelector) entry.getValue();
                if (!str.startsWith("ChromiumBaseFeature_")) {
                    baseFeature$ParsedFlagName = null;
                } else {
                    String substring = str.substring(20);
                    BaseFeature$ParsedFlagName baseFeature$ParsedFlagName2 = new BaseFeature$ParsedFlagName();
                    int indexOf = substring.indexOf("_PARAM_");
                    if (indexOf < 0) {
                        baseFeature$ParsedFlagName2.featureName = substring;
                    } else {
                        baseFeature$ParsedFlagName2.featureName = substring.substring(0, indexOf);
                        baseFeature$ParsedFlagName2.paramName = substring.substring(indexOf + 7);
                    }
                    baseFeature$ParsedFlagName = baseFeature$ParsedFlagName2;
                }
                if (baseFeature$ParsedFlagName != null) {
                    SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) hashMap.get(baseFeature$ParsedFlagName.featureName);
                    if (builder == null) {
                        builder = BaseFeatureOverrides.FeatureState.DEFAULT_INSTANCE.createBuilder();
                        hashMap.put(baseFeature$ParsedFlagName.featureName, builder);
                    }
                    String str2 = baseFeature$ParsedFlagName.paramName;
                    if (str2 == null) {
                        int type$ar$edu$2edc95a9_0 = executorSelector.getType$ar$edu$2edc95a9_0();
                        if (type$ar$edu$2edc95a9_0 == 1) {
                            boolean boolValue = executorSelector.getBoolValue();
                            builder.copyOnWrite();
                            BaseFeatureOverrides.FeatureState featureState = (BaseFeatureOverrides.FeatureState) builder.instance;
                            int i = BaseFeatureOverrides.FeatureState.ENABLED_FIELD_NUMBER;
                            featureState.bitField0_ |= 1;
                            featureState.enabled_ = boolValue;
                        } else {
                            throw new IllegalArgumentException("HTTP flag has type " + ((Object) GPUInfo.toStringGenerated5ff6403310ffba06(type$ar$edu$2edc95a9_0)) + ", but only boolean flags are supported as base::Feature overrides");
                        }
                    } else {
                        int type$ar$edu$2edc95a9_02 = executorSelector.getType$ar$edu$2edc95a9_0() - 1;
                        if (type$ar$edu$2edc95a9_02 != 0) {
                            if (type$ar$edu$2edc95a9_02 != 1) {
                                if (type$ar$edu$2edc95a9_02 != 2) {
                                    if (type$ar$edu$2edc95a9_02 != 3) {
                                        copyFrom = executorSelector.getBytesValue();
                                    } else {
                                        copyFrom = ByteString.copyFrom(executorSelector.getStringValue(), StandardCharsets.UTF_8);
                                    }
                                } else {
                                    copyFrom = ByteString.copyFrom(Float.toString(executorSelector.getFloatValue()), StandardCharsets.UTF_8);
                                }
                            } else {
                                copyFrom = ByteString.copyFrom(Long.toString(executorSelector.getIntValue(), 10), StandardCharsets.UTF_8);
                            }
                        } else {
                            String str3 = "true";
                            if (true != executorSelector.getBoolValue()) {
                                str3 = "false";
                            }
                            copyFrom = ByteString.copyFrom(str3, StandardCharsets.UTF_8);
                        }
                        copyFrom.getClass();
                        builder.copyOnWrite();
                        BaseFeatureOverrides.FeatureState featureState2 = (BaseFeatureOverrides.FeatureState) builder.instance;
                        int i2 = BaseFeatureOverrides.FeatureState.ENABLED_FIELD_NUMBER;
                        MapFieldLite<String, ByteString> mapFieldLite = featureState2.params_;
                        if (!mapFieldLite.isMutable) {
                            featureState2.params_ = mapFieldLite.mutableCopy();
                        }
                        featureState2.params_.put(str2, copyFrom);
                    }
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Could not parse HTTP flag `" + ((String) entry.getKey()) + "` as a base::Feature override", e);
            }
        }
        SystemHealthProto$PackedHistogram.Builder createBuilder = BaseFeatureOverrides.DEFAULT_INSTANCE.createBuilder();
        for (Map.Entry entry2 : hashMap.entrySet()) {
            String str4 = (String) entry2.getKey();
            BaseFeatureOverrides.FeatureState featureState3 = (BaseFeatureOverrides.FeatureState) ((SystemHealthProto$PackedHistogram.Builder) entry2.getValue()).build();
            str4.getClass();
            featureState3.getClass();
            createBuilder.copyOnWrite();
            BaseFeatureOverrides baseFeatureOverrides = (BaseFeatureOverrides) createBuilder.instance;
            MapFieldLite<String, BaseFeatureOverrides.FeatureState> mapFieldLite2 = baseFeatureOverrides.featureStates_;
            if (!mapFieldLite2.isMutable) {
                baseFeatureOverrides.featureStates_ = mapFieldLite2.mutableCopy();
            }
            baseFeatureOverrides.featureStates_.put(str4, featureState3);
        }
        return ((BaseFeatureOverrides) createBuilder.build()).toByteArray();
    }

    private static String getDefaultUserAgent() {
        return UserAgent.from(ContextUtils.sApplicationContext);
    }

    public static ExecutorSelector getHttpFlags$ar$class_merging() {
        sHttpFlagsLoaded.block();
        return sHttpFlags$ar$class_merging;
    }

    public static void postToInitThread(Runnable runnable) {
        HandlerThread handlerThread = sInitThread;
        if (handlerThread.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            new Handler(handlerThread.getLooper()).post(runnable);
        }
    }

    private static void setNetworkThreadPriorityOnNetworkThread(int i) {
        TranslatorOptions.d(TAG, _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Setting network thread priority to "), new Object[0]);
        Process.setThreadPriority(i);
    }
}

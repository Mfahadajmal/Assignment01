package com.google.android.accessibility.utils;

import android.app.ActivityManager;
import android.os.RemoteException;
import android.os.VibrationEffect;
import android.util.Log;
import androidx.work.ListenableWorker$Result$Success;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.apps.aicore.aidl.AIFeature;
import com.google.android.apps.aicore.aidl.IAICoreService;
import com.google.android.apps.aicore.client.api.AiCoreClient;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.mdi.Download$ClientFileGroup;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.GoogleLogger;
import com.google.devrel.primes.hashing.Hashing;
import logs.proto.wireless.performance.mobile.PrimesTracing$Trace;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$TraceMetadata;
import logs.proto.wireless.performance.mobile.SystemHealthProto$TraceMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AccessibilityEventUtils$$ExternalSyntheticLambda0 implements Function {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ AccessibilityEventUtils$$ExternalSyntheticLambda0(int i) {
        this.switching_field = i;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        VibrationEffect createWaveform;
        switch (this.switching_field) {
            case 0:
                int intValue = ((Integer) obj).intValue();
                if (intValue == 0) {
                    return null;
                }
                if (intValue != 0) {
                    if (intValue != 1) {
                        if (intValue != 2) {
                            switch (intValue) {
                                case 4:
                                    return "CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION";
                                case 8:
                                    return "CONTENT_CHANGE_TYPE_PANE_TITLE";
                                case 16:
                                    return "CONTENT_CHANGE_TYPE_PANE_APPEARED";
                                case 32:
                                    return "CONTENT_CHANGE_TYPE_PANE_DISAPPEARED";
                                case 64:
                                    return "CONTENT_CHANGE_TYPE_STATE_DESCRIPTION";
                                case BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE /* 128 */:
                                    return "CONTENT_CHANGE_TYPE_DRAG_STARTED";
                                case 256:
                                    return "CONTENT_CHANGE_TYPE_DRAG_DROPPED";
                                case 512:
                                    return "CONTENT_CHANGE_TYPE_DRAG_CANCELLED";
                                case 1024:
                                    return "CONTENT_CHANGE_TYPE_CONTENT_INVALID";
                                case 2048:
                                    return "CONTENT_CHANGE_TYPE_ERROR";
                                case 4096:
                                    return "CONTENT_CHANGE_TYPE_ENABLED";
                                default:
                                    return Integer.toHexString(intValue);
                            }
                        }
                        return "CONTENT_CHANGE_TYPE_TEXT";
                    }
                    return "CONTENT_CHANGE_TYPE_SUBTREE";
                }
                return "CONTENT_CHANGE_TYPE_UNDEFINED";
            case 1:
                LogUtils.e("ArateaEndpoint", "RPC exception, ".concat(String.valueOf(((Throwable) obj).getMessage())), new Object[0]);
                return null;
            case 2:
                int intValue2 = ((Integer) obj).intValue();
                if (intValue2 == 0) {
                    return null;
                }
                if (intValue2 != 1) {
                    if (intValue2 != 2) {
                        if (intValue2 != 4) {
                            if (intValue2 != 8) {
                                if (intValue2 != 16) {
                                    if (intValue2 != 32) {
                                        if (intValue2 != 64) {
                                            if (intValue2 != 128) {
                                                if (intValue2 != 256) {
                                                    if (intValue2 != 512) {
                                                        if (intValue2 != 1024) {
                                                            return Integer.toHexString(intValue2);
                                                        }
                                                        return "WINDOWS_CHANGE_PIP";
                                                    }
                                                    return "WINDOWS_CHANGE_CHILDREN";
                                                }
                                                return "WINDOWS_CHANGE_PARENT";
                                            }
                                            return "WINDOWS_CHANGE_ACCESSIBILITY_FOCUSED";
                                        }
                                        return "WINDOWS_CHANGE_FOCUSED";
                                    }
                                    return "WINDOWS_CHANGE_ACTIVE";
                                }
                                return "WINDOWS_CHANGE_LAYER";
                            }
                            return "WINDOWS_CHANGE_BOUNDS";
                        }
                        return "WINDOWS_CHANGE_TITLE";
                    }
                    return "WINDOWS_CHANGE_REMOVED";
                }
                return "WINDOWS_CHANGE_ADDED";
            case 3:
                switch (((Integer) obj).intValue()) {
                    case 2:
                        return "FLAG_NO_HISTORY";
                    case 4:
                        return "FLAG_FORCE_FEEDBACK_EVEN_IF_AUDIO_PLAYBACK_ACTIVE";
                    case 8:
                        return "FLAG_FORCE_FEEDBACK_EVEN_IF_MICROPHONE_ACTIVE";
                    case 16:
                        return "FLAG_FORCE_FEEDBACK_EVEN_IF_SSB_ACTIVE";
                    case 32:
                        return "FLAG_FORCE_FEEDBACK_EVEN_IF_PHONE_CALL_ACTIVE";
                    case 64:
                        return "FLAG_ADVANCE_CONTINUOUS_READING";
                    case BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE /* 128 */:
                        return "FLAG_NO_SPEECH";
                    case 256:
                        return "FLAG_SKIP_DUPLICATE";
                    case 512:
                        return "FLAG_CLEAR_QUEUED_UTTERANCES_WITH_SAME_UTTERANCE_GROUP";
                    case 1024:
                        return "FLAG_INTERRUPT_CURRENT_UTTERANCE_WITH_SAME_UTTERANCE_GROUP";
                    case 2048:
                        return "FLAG_NO_DEVICE_SLEEP";
                    case 4096:
                        return "FLAG_FORCE_FEEDBACK";
                    case 8192:
                        return "FLAG_SOURCE_IS_VOLUME_CONTROL";
                    case 16384:
                        return "FLAG_CHUNKING_APPLIED";
                    default:
                        return null;
                }
            case 4:
                createWaveform = VibrationEffect.createWaveform((long[]) obj, -1);
                return createWaveform;
            case 5:
                return ((AiCoreClient.ServiceContext) obj).getService();
            case 6:
                IAICoreService iAICoreService = (IAICoreService) obj;
                String str = AiCoreClientImpl.TAG;
                try {
                    ImmutableList.Builder builder = new ImmutableList.Builder();
                    for (AIFeature aIFeature : iAICoreService.listFeatures()) {
                        builder.add$ar$ds$4f674a09_0(new AiFeature(aIFeature.name, aIFeature.modelName, aIFeature.type, aIFeature.variant, aIFeature.id));
                    }
                    return builder.build();
                } catch (RemoteException e) {
                    Log.e(AiCoreClientImpl.TAG, "AiCore service failed to list features.", e);
                    int i = ImmutableList.ImmutableList$ar$NoOp;
                    return RegularImmutableList.EMPTY;
                }
            case 7:
                return ((Download$ClientFileGroup) obj).ownerPackage_;
            case 8:
                return ((Download$ClientFileGroup) obj).groupName_;
            case 9:
                return ((Download$ClientFileGroup) obj).account_;
            case 10:
                return Integer.valueOf(((Download$ClientFileGroup) obj).versionNumber_);
            case 11:
                Download$ClientFileGroup.Status forNumber = Download$ClientFileGroup.Status.forNumber(((Download$ClientFileGroup) obj).status_);
                if (forNumber == null) {
                    return Download$ClientFileGroup.Status.UNSPECIFIED;
                }
                return forNumber;
            case 12:
                return new ListenableWorker$Result$Success();
            case 13:
                return ((NoPiiString) obj).value;
            case 14:
                return Optional.fromNullable(((ActivityManager.RunningAppProcessInfo) obj).importanceReasonComponent);
            case 15:
                PrimesTracing$Trace primesTracing$Trace = (PrimesTracing$Trace) obj;
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$TraceMetric.DEFAULT_INSTANCE.createBuilder();
                builder2.copyOnWrite();
                SystemHealthProto$TraceMetric systemHealthProto$TraceMetric = (SystemHealthProto$TraceMetric) builder2.instance;
                primesTracing$Trace.getClass();
                systemHealthProto$TraceMetric.trace_ = primesTracing$Trace;
                systemHealthProto$TraceMetric.bitField0_ |= 1;
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$TraceMetadata.DEFAULT_INSTANCE.createBuilder();
                builder3.copyOnWrite();
                SystemHealthProto$TraceMetadata systemHealthProto$TraceMetadata = (SystemHealthProto$TraceMetadata) builder3.instance;
                systemHealthProto$TraceMetadata.bitField0_ |= 2;
                systemHealthProto$TraceMetadata.isPartialTrace_ = true;
                SystemHealthProto$TraceMetadata systemHealthProto$TraceMetadata2 = (SystemHealthProto$TraceMetadata) builder3.build();
                builder2.copyOnWrite();
                SystemHealthProto$TraceMetric systemHealthProto$TraceMetric2 = (SystemHealthProto$TraceMetric) builder2.instance;
                systemHealthProto$TraceMetadata2.getClass();
                systemHealthProto$TraceMetric2.traceMetadata_ = systemHealthProto$TraceMetadata2;
                systemHealthProto$TraceMetric2.bitField0_ |= 2;
                return (SystemHealthProto$TraceMetric) builder2.build();
            case 16:
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause((RuntimeException) obj)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryMetricServiceImpl", "lambda$record$5", 422, "MemoryMetricServiceImpl.java")).log("Metric extension provider failed.");
                return null;
            case 17:
                return Integer.valueOf(Integer.parseInt((String) obj));
            case 18:
                return false;
            case 19:
                Log.e("CheckboxChecker", "fetching usage reporting opt-in failed", (Throwable) obj);
                return true;
            default:
                return Hashing.hash((String) obj);
        }
    }
}

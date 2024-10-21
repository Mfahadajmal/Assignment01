package com.google.android.libraries.mdi.download.debug.common.debuginfo;

import android.support.v4.app.Fragment;
import com.google.common.android.concurrent.FutureCallbackRegistry$Callback;
import com.google.common.flogger.GoogleLogger;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugInfoFragmentActionProviderImpl implements MddDebugInfoFragmentActionProvider {
    public static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/mdi/download/debug/common/debuginfo/MddDebugInfoFragmentActionProviderImpl");
    public final FutureCallbackRegistry$Callback debugInfoFragmentActionCallback = new FutureCallbackRegistry$Callback() { // from class: com.google.android.libraries.mdi.download.debug.common.debuginfo.MddDebugInfoFragmentActionProviderImpl.1
        @Override // com.google.common.android.concurrent.FutureCallbackRegistry$Callback
        public final /* bridge */ /* synthetic */ void onFailure(Object obj, Throwable th) {
            String str = (String) obj;
            GoogleLogger.Api api = (GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugInfoFragmentActionProviderImpl.logger.atSevere()).withCause(th)).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/debuginfo/MddDebugInfoFragmentActionProviderImpl$1", "onFailure", 46, "MddDebugInfoFragmentActionProviderImpl.java");
            if (str == null) {
                str = "UNKNOWN";
            }
            api.log("MDD Debug Action: %s failed", str);
        }

        @Override // com.google.common.android.concurrent.FutureCallbackRegistry$Callback
        public final /* bridge */ /* synthetic */ void onSuccess(Object obj, Object obj2) {
            String str = (String) obj;
            if (str == null) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugInfoFragmentActionProviderImpl.logger.atWarning()).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/debuginfo/MddDebugInfoFragmentActionProviderImpl$1", "onSuccess", 35, "MddDebugInfoFragmentActionProviderImpl.java")).log("MDD Debug Action completed, but no action was given. No additional operation will be performed");
            } else {
                ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugInfoFragmentActionProviderImpl.logger.atFine()).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/debuginfo/MddDebugInfoFragmentActionProviderImpl$1", "onSuccess", 40, "MddDebugInfoFragmentActionProviderImpl.java")).log("MDD Debug Action succeeded: %s", str);
            }
        }
    };
    public final Fragment fragment;
    public OptionalMethod futureRegistry$ar$class_merging$ar$class_merging;

    public MddDebugInfoFragmentActionProviderImpl(Fragment fragment) {
        this.fragment = fragment;
    }
}

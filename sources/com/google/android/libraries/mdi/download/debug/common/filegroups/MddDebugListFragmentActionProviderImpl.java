package com.google.android.libraries.mdi.download.debug.common.filegroups;

import android.support.v4.app.Fragment;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProvider;
import com.google.common.android.concurrent.FutureCallbackRegistry$Callback;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugListFragmentActionProviderImpl implements MddDebugListFragmentActionProvider, MddDebugListFragmentActionProvider.UiCallbacks {
    public static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugListFragmentActionProviderImpl");
    public final Fragment fragment;
    public OptionalMethod futureRegistry$ar$class_merging$ar$class_merging;
    public FileGroupListViewModel listViewModel;
    public final FutureCallbackRegistry$Callback listFragmentActionCallback = new FutureCallbackRegistry$Callback() { // from class: com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProviderImpl.1
        @Override // com.google.common.android.concurrent.FutureCallbackRegistry$Callback
        public final /* bridge */ /* synthetic */ void onFailure(Object obj, Throwable th) {
            String str = (String) obj;
            if (str == null) {
                str = "UNKNOWN";
            }
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugListFragmentActionProviderImpl.logger.atSevere()).withCause(th)).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugListFragmentActionProviderImpl$1", "onFailure", 53, "MddDebugListFragmentActionProviderImpl.java")).log("MDD Debug Action: %s failed", str);
            if (str.hashCode() == -1167398896 && str.equals("MDD.DEBUG.UPDATE_ACTION")) {
                MddDebugListFragmentActionProvider.UiCallbacks uiCallbacks = MddDebugListFragmentActionProviderImpl.this.callbacks;
                if (uiCallbacks != null) {
                    uiCallbacks.onShowFileGroupStatus("Unable to get file groups, check log for details\n(You may need to force refresh tracked file groups)");
                }
                MddDebugListFragmentActionProviderImpl.this.onShowRefreshing$ar$ds();
            }
        }

        @Override // com.google.common.android.concurrent.FutureCallbackRegistry$Callback
        public final /* bridge */ /* synthetic */ void onSuccess(Object obj, Object obj2) {
            String str = (String) obj;
            if (str == null) {
                str = "UNKNOWN";
            }
            ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugListFragmentActionProviderImpl.logger.atFine()).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugListFragmentActionProviderImpl$1", "onSuccess", 35, "MddDebugListFragmentActionProviderImpl.java")).log("MDD Debug Action: %s succeeded", str);
            if (str.hashCode() == -1167398896 && str.equals("MDD.DEBUG.UPDATE_ACTION")) {
                MddDebugListFragmentActionProviderImpl.this.onShowRefreshing$ar$ds();
            }
        }
    };
    public MddDebugListFragmentActionProvider.UiCallbacks callbacks = null;

    public MddDebugListFragmentActionProviderImpl(Fragment fragment) {
        ContextDataProvider.checkArgument(true, (Object) "This helper class requires a fragment that implements IMddDebugListFragment!");
        this.fragment = fragment;
    }

    @Override // com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProvider.UiCallbacks
    public final void onShowFileGroupStatus(String str) {
        throw null;
    }

    public final void onShowRefreshing$ar$ds() {
        MddDebugListFragmentActionProvider.UiCallbacks uiCallbacks = this.callbacks;
        if (uiCallbacks != null) {
            ((MddDebugListFragmentUiProviderImpl) uiCallbacks).fragment.requireActivity().runOnUiThread(new TrainingActivity$$ExternalSyntheticLambda1(uiCallbacks, 15));
        }
    }

    @Override // com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProvider
    public final void updateFileGroupList() {
        throw null;
    }
}

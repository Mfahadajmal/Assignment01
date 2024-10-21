package com.google.android.libraries.mdi.download.debug.common.filegroups;

import android.support.v4.app.Fragment;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentHelper;
import com.google.android.libraries.mdi.download.debug.dagger.MddDebugListFragment;
import com.google.android.marvin.talkback.R;
import com.google.common.android.concurrent.FutureCallbackRegistry$Callback;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugMainFragmentActionProviderImpl implements MddDebugMainFragmentActionProvider {
    public static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugMainFragmentActionProviderImpl");
    public final Fragment fragment;
    public OptionalMethod futureRegistry$ar$class_merging$ar$class_merging;
    public final FutureCallbackRegistry$Callback mainFragmentActionCallback = new FutureCallbackRegistry$Callback() { // from class: com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentActionProviderImpl.1
        @Override // com.google.common.android.concurrent.FutureCallbackRegistry$Callback
        public final /* bridge */ /* synthetic */ void onFailure(Object obj, Throwable th) {
            String str;
            MddDebugMainFragmentHelper.ActionInfo actionInfo = (MddDebugMainFragmentHelper.ActionInfo) obj;
            GoogleLogger.Api api = (GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugMainFragmentActionProviderImpl.logger.atSevere()).withCause(th)).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugMainFragmentActionProviderImpl$1", "onFailure", 65, "MddDebugMainFragmentActionProviderImpl.java");
            if (actionInfo != null) {
                str = actionInfo.action();
            } else {
                str = "UNKNOWN";
            }
            api.log("MDD Debug Action: %s failed", str);
        }

        @Override // com.google.common.android.concurrent.FutureCallbackRegistry$Callback
        public final /* bridge */ /* synthetic */ void onSuccess(Object obj, Object obj2) {
            char c;
            MddDebugListFragmentHelper mddDebugListFragmentHelper;
            MddDebugMainFragmentHelper.ActionInfo actionInfo = (MddDebugMainFragmentHelper.ActionInfo) obj;
            if (actionInfo == null) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugMainFragmentActionProviderImpl.logger.atWarning()).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugMainFragmentActionProviderImpl$1", "onSuccess", 43, "MddDebugMainFragmentActionProviderImpl.java")).log("MDD Debug Action completed, but no ActionInfo was given. No additional operation will be performed");
                return;
            }
            ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugMainFragmentActionProviderImpl.logger.atFine()).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugMainFragmentActionProviderImpl$1", "onSuccess", 48, "MddDebugMainFragmentActionProviderImpl.java")).log("MDD Debug Action succeeded: %s", actionInfo);
            String action = actionInfo.action();
            int hashCode = action.hashCode();
            if (hashCode != -1460314357) {
                if (hashCode != -882694556) {
                    if (hashCode == 1160160022 && action.equals("MDD.DEBUG.REFRESH_ACTION")) {
                        c = 1;
                    }
                    c = 65535;
                } else {
                    if (action.equals("MDD.DEBUG.CLEAR_ACTION")) {
                        c = 0;
                    }
                    c = 65535;
                }
            } else {
                if (action.equals("MDD.DEBUG.TRIGGER_MDD_ACTION")) {
                    c = 2;
                }
                c = 65535;
            }
            if (c == 0 || c == 1 || c == 2) {
                MddDebugListFragment mddDebugListFragment = (MddDebugListFragment) MddDebugMainFragmentActionProviderImpl.this.fragment.getChildFragmentManager().findFragmentById(R.id.file_group_list_container);
                if (mddDebugListFragment != null) {
                    mddDebugListFragmentHelper = mddDebugListFragment.getHelper();
                } else {
                    mddDebugListFragmentHelper = null;
                }
                if (mddDebugListFragmentHelper != null) {
                    mddDebugListFragmentHelper.actionProvider.updateFileGroupList();
                }
            }
        }
    };

    public MddDebugMainFragmentActionProviderImpl(Fragment fragment) {
        this.fragment = fragment;
    }

    public final ListenableFuture performAction(MddDebugMainFragmentHelper.ActionInfo actionInfo) {
        char c;
        String str = actionInfo.action;
        int hashCode = str.hashCode();
        if (hashCode != -1460314357) {
            if (hashCode != -882694556) {
                if (hashCode == 1160160022 && str.equals("MDD.DEBUG.REFRESH_ACTION")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (str.equals("MDD.DEBUG.CLEAR_ACTION")) {
                    c = 0;
                }
                c = 65535;
            }
        } else {
            if (str.equals("MDD.DEBUG.TRIGGER_MDD_ACTION")) {
                c = 2;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    return ImmediateFuture.NULL;
                }
                actionInfo.tag.getClass();
                throw null;
            }
            throw null;
        }
        throw null;
    }
}

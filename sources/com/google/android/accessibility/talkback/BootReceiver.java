package com.google.android.accessibility.talkback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.play.core.splitcompat.SplitCompat;
import googledata.experiments.mobile.accessibility_suite.features.TalkbackMistriggeringRecoveryConfig;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        char c;
        TalkBackExitController talkBackExitController;
        TalkBackService talkBackService = TalkBackService.instance;
        if (talkBackService != null) {
            Logger logger = Performance.DEFAULT_LOGGER;
            String action = intent.getAction();
            int hashCode = action.hashCode();
            if (hashCode != -905063602) {
                if (hashCode != 798292259) {
                    if (hashCode == 1947666138 && action.equals("android.intent.action.ACTION_SHUTDOWN")) {
                        c = 2;
                    }
                    c = 65535;
                } else {
                    if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                        c = 1;
                    }
                    c = 65535;
                }
            } else {
                if (action.equals("android.intent.action.LOCKED_BOOT_COMPLETED")) {
                    c = 0;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c == 2 && (talkBackExitController = talkBackService.talkBackExitController) != null && talkBackExitController.trainingState$ar$class_merging != null && TalkbackMistriggeringRecoveryConfig.automaticTurnOff(talkBackExitController.service)) {
                        LogUtils.d("TalkBackExitController", "onShutDown: ", new Object[0]);
                        talkBackExitController.turnOffTalkBackIfTutorialActive(3);
                        return;
                    }
                    return;
                }
                Pipeline pipeline = talkBackService.pipeline;
                if (pipeline != null) {
                    pipeline.onBoot(false);
                }
                TalkBackLabelManager talkBackLabelManager = talkBackService.labelManager;
                if (talkBackLabelManager != null) {
                    talkBackLabelManager.onUnlockedBoot();
                }
                if (!SplitCompat.installActivity(talkBackService)) {
                    Log.e("TalkBackService", "SplitCompatUtils.installActivity() failed");
                    return;
                }
                return;
            }
            if (talkBackService.serviceState == 0) {
                talkBackService.lockedBootCompletedPending = true;
            } else {
                talkBackService.onLockedBootCompletedInternal();
            }
        }
    }
}

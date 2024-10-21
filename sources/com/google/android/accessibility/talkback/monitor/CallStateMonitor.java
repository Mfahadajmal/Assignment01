package com.google.android.accessibility.talkback.monitor;

import _COROUTINE._BOUNDARY;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.training.OnboardingInitiator;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver;
import com.google.android.accessibility.utils.monitor.ScreenMonitor;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallStateMonitor extends SameThreadBroadcastReceiver {
    public static final IntentFilter STATE_CHANGED_FILTER = new IntentFilter("android.intent.action.PHONE_STATE");
    public boolean isStarted;
    private int lastCallState;
    public final TalkBackService service;
    public final boolean supportTelephony;
    private final TelephonyManager telephonyManager;
    public final BroadcastReceiver permissionRequestReceiver = new BroadcastReceiver() { // from class: com.google.android.accessibility.talkback.monitor.CallStateMonitor.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String[] stringArrayExtra = intent.getStringArrayExtra("permissions");
            int[] intArrayExtra = intent.getIntArrayExtra("grant_results");
            if (stringArrayExtra != null && intArrayExtra != null) {
                for (int i = 0; i < stringArrayExtra.length; i++) {
                    if (TextUtils.equals(stringArrayExtra[i], "android.permission.READ_PHONE_STATE")) {
                        context.unregisterReceiver(CallStateMonitor.this.permissionRequestReceiver);
                        if (intArrayExtra[i] == 0) {
                            CallStateMonitor.this.startMonitoring();
                        }
                    }
                }
            }
        }
    };
    public final List callStateChangedListeners = new ArrayList();

    public CallStateMonitor(TalkBackService talkBackService) {
        this.service = talkBackService;
        this.supportTelephony = talkBackService.getPackageManager().hasSystemFeature("android.hardware.telephony");
        this.telephonyManager = (TelephonyManager) talkBackService.getSystemService("phone");
    }

    private static String callStateToString(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return "(unhandled)";
                }
                return "CALL_STATE_OFFHOOK";
            }
            return "CALL_STATE_RINGING";
        }
        return "CALL_STATE_IDLE";
    }

    private final int getCallState() {
        boolean isCallStatePermissionGranted = isCallStatePermissionGranted();
        if (this.supportTelephony) {
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && !isCallStatePermissionGranted) {
                isCallStatePermissionGranted = false;
            } else {
                return this.telephonyManager.getCallState();
            }
        }
        LogUtils.w("CallStateMonitor", "CALL_STATE_IDLE supportTelephony: " + this.supportTelephony + " callStatePermissionGranted: " + isCallStatePermissionGranted, new Object[0]);
        return 0;
    }

    private final boolean isCallStatePermissionGranted() {
        if (EditorInfoCompat.checkSelfPermission(this.service, "android.permission.READ_PHONE_STATE") == 0) {
            return true;
        }
        return false;
    }

    public final int getCurrentCallState() {
        if (this.isStarted) {
            return this.lastCallState;
        }
        return getCallState();
    }

    public final boolean isPhoneCallActive() {
        int currentCallState = getCurrentCallState();
        if (currentCallState == 1 || currentCallState == 2) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver
    public final void onReceiveIntent(Intent intent) {
        int i;
        if (!TalkBackService.isServiceActive()) {
            LogUtils.w("CallStateMonitor", "Service not initialized during broadcast.", new Object[0]);
            return;
        }
        int i2 = this.lastCallState;
        String stringExtra = intent.getStringExtra("state");
        if (TelephonyManager.EXTRA_STATE_IDLE.equals(stringExtra)) {
            i = 0;
        } else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(stringExtra)) {
            i = 2;
        } else if (TelephonyManager.EXTRA_STATE_RINGING.equals(stringExtra)) {
            i = 1;
        } else {
            return;
        }
        if (i != i2) {
            LogUtils.v("CallStateMonitor", "Call state changed: %s -> %s", callStateToString(this.lastCallState), callStateToString(i));
            this.lastCallState = i;
            Iterator it = this.callStateChangedListeners.iterator();
            while (it.hasNext()) {
                ((HapticPatternParser$$ExternalSyntheticLambda1) it.next()).onCallStateChanged$ar$ds(i);
            }
        }
    }

    public final void requestPhonePermissionIfNeeded(SharedPreferences sharedPreferences) {
        if (this.supportTelephony && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && OnboardingInitiator.hasOnboardingForNewFeaturesBeenShown(sharedPreferences, this.service) && !isCallStatePermissionGranted() && !ScreenMonitor.isDeviceLocked(this.service)) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("done");
            EditorInfoCompat.registerReceiver$ar$ds(this.service, this.permissionRequestReceiver, intentFilter, 2);
            SpannableUtils$IdentifierSpan.requestPermissions(this.service, "android.permission.READ_PHONE_STATE");
        }
    }

    public final void startMonitoring() {
        if (!this.isStarted && this.supportTelephony) {
            if (isCallStatePermissionGranted()) {
                LogUtils.d("CallStateMonitor", "Start monitoring call state.", new Object[0]);
                this.lastCallState = getCallState();
                EditorInfoCompat.registerReceiver$ar$ds(this.service, this, STATE_CHANGED_FILTER, 2);
                this.isStarted = true;
                return;
            }
            LogUtils.w("CallStateMonitor", "Fail to start monitoring phone state: READ_PHONE_STATE permission is not granted.", new Object[0]);
        }
    }
}

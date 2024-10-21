package com.google.android.accessibility.utils.monitor;

import android.os.SystemClock;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeechStateMonitor {
    public final HashMap packageToLastState = new HashMap();
    public long lastSpeechUptimeMillisec = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StateTimeStamp {
        public final int state;
        public final long uptimeMillisec;

        public StateTimeStamp(int i, long j) {
            this.state = i;
            this.uptimeMillisec = j;
        }
    }

    public final boolean isStateValid(int i) {
        for (Map.Entry entry : this.packageToLastState.entrySet()) {
            if (SystemClock.uptimeMillis() - ((StateTimeStamp) entry.getValue()).uptimeMillisec < 300000 && ((StateTimeStamp) entry.getValue()).state == i) {
                return true;
            }
        }
        if (!this.packageToLastState.isEmpty()) {
            Iterator it = this.packageToLastState.entrySet().iterator();
            while (it.hasNext()) {
                if (SystemClock.uptimeMillis() - ((StateTimeStamp) ((Map.Entry) it.next()).getValue()).uptimeMillisec < 300000) {
                    return false;
                }
            }
            this.packageToLastState.clear();
            return false;
        }
        return false;
    }
}

package com.google.android.accessibility.utils.volumebutton;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import com.google.android.accessibility.utils.Performance;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VolumeButtonPatternDetector {
    private static final long LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.google.android.accessibility.utils.volumebutton.VolumeButtonPatternDetector.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what == 1) {
                VolumeButtonPatternDetector.this.checkMatchers();
            }
        }
    };
    public OnPatternMatchListener mListener;
    private final List patternMatchers;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnPatternMatchListener {
        void onPatternMatched(int i, int i2, Performance.EventId eventId);
    }

    public VolumeButtonPatternDetector() {
        ArrayList arrayList = new ArrayList();
        this.patternMatchers = arrayList;
        arrayList.add(new SingleVolumeButtonPressPatternMatcher(1, 24));
        arrayList.add(new SingleVolumeButtonPressPatternMatcher(1, 25));
        arrayList.add(new SingleVolumeButtonPressPatternMatcher(1, 79));
        arrayList.add(new SingleVolumeButtonPressPatternMatcher(2, 24));
        arrayList.add(new SingleVolumeButtonPressPatternMatcher(2, 25));
        arrayList.add(new SingleVolumeButtonPressPatternMatcher(2, 79));
    }

    public final void checkMatchers() {
        for (VolumeButtonPatternMatcher volumeButtonPatternMatcher : this.patternMatchers) {
            if (volumeButtonPatternMatcher.checkMatch()) {
                int i = volumeButtonPatternMatcher.mPatternCode;
                Performance performance = Performance.instance;
                Performance.EventId eventId = new Performance.EventId(SystemClock.uptimeMillis(), 3, i);
                if (performance.trackEvents()) {
                    performance.onEventReceived(eventId, new String[]{Integer.toString(i)});
                }
                int i2 = volumeButtonPatternMatcher.mPatternCode;
                int i3 = volumeButtonPatternMatcher.mButtonCombination;
                OnPatternMatchListener onPatternMatchListener = this.mListener;
                if (onPatternMatchListener != null) {
                    onPatternMatchListener.onPatternMatched(i2, i3, eventId);
                }
                volumeButtonPatternMatcher.clear();
            }
        }
    }

    public final boolean onKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 24 && keyCode != 25 && keyCode != 79) {
            return false;
        }
        Iterator it = this.patternMatchers.iterator();
        while (it.hasNext()) {
            ((VolumeButtonPatternMatcher) it.next()).onKeyEvent(keyEvent);
        }
        checkMatchers();
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, LONG_PRESS_TIMEOUT);
        return true;
    }
}

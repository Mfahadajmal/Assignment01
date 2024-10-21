package com.google.android.accessibility.utils.volumebutton;

import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingleVolumeButtonPressPatternMatcher extends VolumeButtonPatternMatcher {
    private static final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private VolumeButtonAction mAction;
    private final int patternCode;

    public SingleVolumeButtonPressPatternMatcher(int i, int i2) {
        super(i, i2);
        this.patternCode = i != 1 ? 2 : i;
    }

    @Override // com.google.android.accessibility.utils.volumebutton.VolumeButtonPatternMatcher
    public final boolean checkMatch() {
        long j;
        VolumeButtonAction volumeButtonAction = this.mAction;
        if (volumeButtonAction == null) {
            return false;
        }
        if (this.patternCode == 1) {
            if (volumeButtonAction.pressed || volumeButtonAction.endTimestamp - volumeButtonAction.startTimestamp >= LONG_PRESS_TIMEOUT) {
                return false;
            }
            return true;
        }
        if (volumeButtonAction.pressed) {
            j = SystemClock.uptimeMillis();
        } else {
            j = volumeButtonAction.endTimestamp;
        }
        if (j - volumeButtonAction.startTimestamp < LONG_PRESS_TIMEOUT) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.utils.volumebutton.VolumeButtonPatternMatcher
    public final void clear() {
        this.mAction = null;
    }

    @Override // com.google.android.accessibility.utils.volumebutton.VolumeButtonPatternMatcher
    public final void onKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == this.mButtonCombination) {
            if (keyEvent.getAction() == 0) {
                if (keyEvent != null && keyEvent.getAction() == 0) {
                    VolumeButtonAction volumeButtonAction = new VolumeButtonAction();
                    keyEvent.getKeyCode();
                    volumeButtonAction.startTimestamp = keyEvent.getEventTime();
                    volumeButtonAction.endTimestamp = keyEvent.getEventTime();
                    volumeButtonAction.pressed = true;
                    this.mAction = volumeButtonAction;
                    return;
                }
                throw new IllegalArgumentException();
            }
            VolumeButtonAction volumeButtonAction2 = this.mAction;
            if (volumeButtonAction2 != null) {
                volumeButtonAction2.pressed = false;
                volumeButtonAction2.endTimestamp = keyEvent.getEventTime();
            }
        }
    }
}

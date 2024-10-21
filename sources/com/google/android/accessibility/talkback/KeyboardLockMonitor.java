package com.google.android.accessibility.talkback;

import android.view.KeyEvent;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ServiceKeyEventListener;
import com.google.android.play.core.splitcompat.ingestion.Verifier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class KeyboardLockMonitor implements ServiceKeyEventListener {
    private final Verifier compositor$ar$class_merging$e4d5cfcc_0;

    public KeyboardLockMonitor(Verifier verifier) {
        this.compositor$ar$class_merging$e4d5cfcc_0 = verifier;
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public final boolean onKeyEvent(KeyEvent keyEvent, Performance.EventId eventId) {
        if (keyEvent.getAction() == 1) {
            if (keyEvent.getKeyCode() == 115) {
                if (keyEvent.isCapsLockOn()) {
                    this.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741929, eventId);
                    return false;
                }
                this.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741930, eventId);
                return false;
            }
            if (keyEvent.getKeyCode() == 143) {
                if (keyEvent.isNumLockOn()) {
                    this.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741931, eventId);
                    return false;
                }
                this.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741932, eventId);
                return false;
            }
            if (keyEvent.getKeyCode() == 116) {
                if (keyEvent.isScrollLockOn()) {
                    this.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741933, eventId);
                    return false;
                }
                this.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741934, eventId);
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public final boolean processWhenServiceSuspended() {
        return false;
    }
}

package com.google.android.accessibility.utils;

import android.view.KeyEvent;
import com.google.android.accessibility.utils.Performance;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ServiceKeyEventListener {
    boolean onKeyEvent(KeyEvent keyEvent, Performance.EventId eventId);

    boolean processWhenServiceSuspended();
}

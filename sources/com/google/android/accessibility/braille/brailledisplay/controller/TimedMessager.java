package com.google.android.accessibility.braille.brailledisplay.controller;

import android.os.Handler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimedMessager {
    public final FloatingActionButton.ShadowDelegateImpl callback$ar$class_merging$8a5b80ac_0$ar$class_merging$ar$class_merging;
    public final Handler handler = new Handler();
    public boolean timedMessageDisplaying;

    public TimedMessager(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.callback$ar$class_merging$8a5b80ac_0$ar$class_merging$ar$class_merging = shadowDelegateImpl;
    }
}

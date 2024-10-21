package com.google.android.accessibility.utils.volumebutton;

import android.view.KeyEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class VolumeButtonPatternMatcher {
    public final int mButtonCombination;
    public final int mPatternCode;

    public VolumeButtonPatternMatcher(int i, int i2) {
        this.mPatternCode = i;
        this.mButtonCombination = i2;
    }

    public abstract boolean checkMatch();

    public abstract void clear();

    public abstract void onKeyEvent(KeyEvent keyEvent);
}

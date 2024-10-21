package com.google.android.accessibility.braille.interfaces;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TalkBackForBrailleIme {
    int getServiceStatus$ar$edu();

    void interruptSpeak();

    boolean isCurrentGranularityTypoCorrection();

    boolean performAction$ar$edu$3bc9316c_0(int i, Object... objArr);

    void resetGranularity();

    boolean shouldUseCharacterGranularity();
}

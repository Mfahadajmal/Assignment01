package com.google.android.accessibility.talkback.keyboard;

import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface KeyComboModel {
    void clearKeyComboCode(String str);

    long getDefaultKeyComboCode(String str);

    String getDescriptionOfEligibleKeyCombo();

    long getKeyComboCodeForKey(String str);

    Map getKeyComboCodeMap();

    String getKeyForKeyComboCode(long j);

    String getPreferenceKeyForTriggerModifier();

    int getTriggerModifier();

    boolean isEligibleKeyComboCode(long j);

    void notifyTriggerModifierChanged();

    void saveKeyComboCode(String str, long j);

    void updateVersion(int i);
}

package com.google.android.accessibility.talkback.keyboard;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClassicKeyComboModel implements KeyComboModel {
    private final Context context;
    private final Map keyComboCodeMap = new TreeMap();
    private final WindowTrackerFactory persister$ar$class_merging$ar$class_merging$ar$class_merging;

    public ClassicKeyComboModel(Context context) {
        this.context = context;
        this.persister$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory(context, (String) null);
        addCombo(context.getString(R.string.keycombo_shortcut_navigate_next_default));
        addCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_default));
        addCombo(context.getString(R.string.keycombo_shortcut_navigate_first));
        addCombo(context.getString(R.string.keycombo_shortcut_navigate_last));
        addCombo(context.getString(R.string.keycombo_shortcut_perform_click));
        addCombo(context.getString(R.string.keycombo_shortcut_global_back));
        addCombo(context.getString(R.string.keycombo_shortcut_global_home));
        addCombo(context.getString(R.string.keycombo_shortcut_global_recents));
        addCombo(context.getString(R.string.keycombo_shortcut_global_notifications));
        addCombo(context.getString(R.string.keycombo_shortcut_global_play_pause_media));
        addCombo(context.getString(R.string.keycombo_shortcut_global_scroll_forward_reading_menu));
        addCombo(context.getString(R.string.keycombo_shortcut_global_scroll_backward_reading_menu));
        addCombo(context.getString(R.string.keycombo_shortcut_global_adjust_reading_settings_previous));
        addCombo(context.getString(R.string.keycombo_shortcut_global_adjust_reading_setting_next));
        addCombo(context.getString(R.string.keycombo_shortcut_granularity_increase));
        addCombo(context.getString(R.string.keycombo_shortcut_granularity_decrease));
        addCombo(context.getString(R.string.keycombo_shortcut_other_read_from_top));
        addCombo(context.getString(R.string.keycombo_shortcut_other_read_from_cursor_item));
        addCombo(context.getString(R.string.keycombo_shortcut_other_toggle_search));
        addCombo(context.getString(R.string.keycombo_shortcut_other_talkback_context_menu));
        addCombo(context.getString(R.string.keycombo_shortcut_other_custom_actions));
        addCombo(context.getString(R.string.keycombo_shortcut_other_language_options));
        addCombo(context.getString(R.string.keycombo_shortcut_other_copy_last_spoken_phrase));
    }

    private final void addCombo(String str) {
        if (!this.persister$ar$class_merging$ar$class_merging$ar$class_merging.contains(str)) {
            this.persister$ar$class_merging$ar$class_merging$ar$class_merging.saveKeyCombo(str, getDefaultKeyComboCode(str));
        }
        Long keyComboCode = this.persister$ar$class_merging$ar$class_merging$ar$class_merging.getKeyComboCode(str);
        keyComboCode.longValue();
        this.keyComboCodeMap.put(str, keyComboCode);
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final void clearKeyComboCode(String str) {
        saveKeyComboCode(str, 0L);
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final long getDefaultKeyComboCode(String str) {
        int i;
        if (str != null) {
            int i2 = 3;
            if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_default))) {
                i = 22;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_default))) {
                i = 21;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_first))) {
                i = 19;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_last))) {
                i = 20;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_perform_click))) {
                i = 66;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_back))) {
                i = 67;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_home))) {
                i = 36;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_recents))) {
                i = 46;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_notifications))) {
                i = 42;
            } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_other_toggle_search))) {
                i = 76;
            } else if (SpannableUtils$IdentifierSpan.isAtLeastR() && str.equals(this.context.getString(R.string.keycombo_shortcut_global_play_pause_media))) {
                i = 62;
            } else {
                int i3 = 4097;
                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_global_scroll_forward_reading_menu))) {
                    if (!str.equals(this.context.getString(R.string.keycombo_shortcut_global_scroll_backward_reading_menu))) {
                        i3 = 4096;
                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_global_adjust_reading_settings_previous))) {
                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_global_adjust_reading_setting_next))) {
                                if (str.equals(this.context.getString(R.string.keycombo_shortcut_granularity_increase))) {
                                    i = 70;
                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_granularity_decrease))) {
                                    i = 69;
                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_other_talkback_context_menu))) {
                                    i = 35;
                                } else {
                                    return 0L;
                                }
                            }
                        }
                    }
                    i = 19;
                    i2 = i3;
                }
                i = 20;
                i2 = i3;
            }
            return KeyComboManager.getKeyComboCode(i2, i);
        }
        return 0L;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final String getDescriptionOfEligibleKeyCombo() {
        return this.context.getString(R.string.keycombo_assign_dialog_instruction);
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final long getKeyComboCodeForKey(String str) {
        if (str != null && this.keyComboCodeMap.containsKey(str)) {
            return ((Long) this.keyComboCodeMap.get(str)).longValue();
        }
        return 0L;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final Map getKeyComboCodeMap() {
        return this.keyComboCodeMap;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final String getKeyForKeyComboCode(long j) {
        if (j != 0) {
            for (Map.Entry entry : this.keyComboCodeMap.entrySet()) {
                if (((Long) entry.getValue()).longValue() == j) {
                    return (String) entry.getKey();
                }
            }
            return null;
        }
        return null;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final String getPreferenceKeyForTriggerModifier() {
        return null;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final int getTriggerModifier() {
        return 0;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final boolean isEligibleKeyComboCode(long j) {
        int i;
        if (j == 0) {
            return true;
        }
        int modifier = KeyComboManager.getModifier(j);
        if ((modifier & 4098) != 0 && (modifier | 4099) == 4099 && (i = (int) j) != 0 && i != 59 && i != 60 && i != 57 && i != 58 && i != 113 && i != 114) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final void saveKeyComboCode(String str, long j) {
        this.persister$ar$class_merging$ar$class_merging$ar$class_merging.saveKeyCombo(str, j);
        if (this.keyComboCodeMap.containsKey(str)) {
            this.keyComboCodeMap.put(str, Long.valueOf(j));
        }
    }

    public final void updateKeyCombo(String str, long j) {
        long defaultKeyComboCode = getDefaultKeyComboCode(str);
        if (getKeyForKeyComboCode(defaultKeyComboCode) == null) {
            if ((!this.persister$ar$class_merging$ar$class_merging$ar$class_merging.contains(str) || j == this.persister$ar$class_merging$ar$class_merging$ar$class_merging.getKeyComboCode(str).longValue()) && defaultKeyComboCode != 0) {
                saveKeyComboCode(str, defaultKeyComboCode);
            }
        }
    }

    public final void updateKeyCombo$ar$ds(String str, int i) {
        updateKeyCombo(str, KeyComboManager.getKeyComboCode(2, i));
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final void updateVersion(int i) {
        if (i < 40400000) {
            updateKeyCombo$ar$ds(this.context.getString(R.string.keycombo_shortcut_granularity_increase), 81);
            updateKeyCombo$ar$ds(this.context.getString(R.string.keycombo_shortcut_granularity_decrease), 69);
        }
        if (i < 40500000) {
            updateKeyCombo(this.context.getString(R.string.keycombo_shortcut_other_talkback_context_menu), 0L);
        }
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final void notifyTriggerModifierChanged() {
    }
}

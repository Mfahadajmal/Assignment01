package com.google.android.accessibility.talkback.keyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultKeyComboModel implements KeyComboModel {
    private final Context context;
    private final WindowTrackerFactory persister$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Map keyComboCodeMap = new TreeMap();
    private int triggerModifier = 2;

    public DefaultKeyComboModel(Context context) {
        this.context = context;
        this.persister$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory(context, "default_key_combo_model");
        loadTriggerModifierFromPreferences();
        addKeyCombo(context.getString(R.string.keycombo_shortcut_perform_click));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_perform_long_click));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_other_read_from_top));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_other_read_from_cursor_item));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_other_talkback_context_menu));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_other_custom_actions));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_other_language_options));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_other_toggle_search));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_back));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_default));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_default));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_up));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_down));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_first));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_last));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_word));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_word));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_character));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_character));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_button));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_button));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_control));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_control));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_checkbox));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_checkbox));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_aria_landmark));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_aria_landmark));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_edit_field));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_edit_field));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_focusable_item));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_focusable_item));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_graphic));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_graphic));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_heading));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_heading));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_heading_1));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_heading_1));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_heading_2));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_heading_2));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_heading_3));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_heading_3));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_heading_4));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_heading_4));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_heading_5));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_heading_5));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_heading_6));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_heading_6));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_list_item));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_list_item));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_link));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_link));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_list));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_list));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_table));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_table));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_combobox));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_combobox));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_next_window));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_navigate_previous_window));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_home));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_recents));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_notifications));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_play_pause_media));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_scroll_forward_reading_menu));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_scroll_backward_reading_menu));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_adjust_reading_settings_previous));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_adjust_reading_setting_next));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_other_copy_last_spoken_phrase));
        addKeyCombo(context.getString(R.string.keycombo_shortcut_global_hide_or_show_screen));
    }

    private final void addKeyCombo(String str) {
        if (!this.persister$ar$class_merging$ar$class_merging$ar$class_merging.contains(str)) {
            this.persister$ar$class_merging$ar$class_merging$ar$class_merging.saveKeyCombo(str, getDefaultKeyComboCode(str));
        }
        this.keyComboCodeMap.put(str, this.persister$ar$class_merging$ar$class_merging$ar$class_merging.getKeyComboCode(str));
    }

    private final void loadTriggerModifierFromPreferences() {
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(this.context);
        if (!sharedPreferences.contains(getPreferenceKeyForTriggerModifier())) {
            sharedPreferences.edit().putString(getPreferenceKeyForTriggerModifier(), this.context.getString(R.string.trigger_modifier_alt_entry_value)).apply();
        }
        String string = sharedPreferences.getString(getPreferenceKeyForTriggerModifier(), this.context.getString(R.string.trigger_modifier_alt_entry_value));
        if (string.equals(this.context.getString(R.string.trigger_modifier_alt_entry_value))) {
            this.triggerModifier = 2;
        } else if (string.equals(this.context.getString(R.string.trigger_modifier_meta_entry_value))) {
            this.triggerModifier = 65536;
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [android.content.SharedPreferences, java.lang.Object] */
    private final void moveKeyComboPreferenceValue(String str, String str2) {
        if (!this.persister$ar$class_merging$ar$class_merging$ar$class_merging.contains(str)) {
            return;
        }
        saveKeyComboCode(str2, this.persister$ar$class_merging$ar$class_merging$ar$class_merging.getKeyComboCode(str).longValue());
        WindowTrackerFactory windowTrackerFactory = this.persister$ar$class_merging$ar$class_merging$ar$class_merging;
        windowTrackerFactory.WindowTrackerFactory$ar$executorProvider.edit().remove(windowTrackerFactory.getPrefixedKey(str)).apply();
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final void clearKeyComboCode(String str) {
        saveKeyComboCode(str, 0L);
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final long getDefaultKeyComboCode(String str) {
        if (str != null) {
            int i = 66;
            int i2 = 0;
            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_perform_click))) {
                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_perform_long_click))) {
                    if (!str.equals(this.context.getString(R.string.keycombo_shortcut_other_read_from_top))) {
                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_other_read_from_cursor_item))) {
                            i = 62;
                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_other_talkback_context_menu))) {
                                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_other_custom_actions))) {
                                    if (str.equals(this.context.getString(R.string.keycombo_shortcut_other_language_options))) {
                                        i2 = 4096;
                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_other_toggle_search))) {
                                        i = 76;
                                    } else {
                                        if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_home))) {
                                            i2 = 4096;
                                        } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_recents))) {
                                            i = 46;
                                        } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_back))) {
                                            i = 67;
                                        } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_notifications))) {
                                            i = 42;
                                        } else if (!SpannableUtils$IdentifierSpan.isAtLeastR() || !str.equals(this.context.getString(R.string.keycombo_shortcut_global_play_pause_media))) {
                                            i = 20;
                                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_global_scroll_forward_reading_menu))) {
                                                if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_scroll_backward_reading_menu))) {
                                                    i2 = 4097;
                                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_global_adjust_reading_settings_previous))) {
                                                    i2 = 1;
                                                } else if (!str.equals(this.context.getString(R.string.keycombo_shortcut_global_adjust_reading_setting_next))) {
                                                    if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_default))) {
                                                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_default))) {
                                                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_up))) {
                                                                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_down))) {
                                                                    if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_first))) {
                                                                        i2 = 4096;
                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_last))) {
                                                                        i2 = 4096;
                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_word))) {
                                                                        i2 = 4097;
                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_word))) {
                                                                        i2 = 4097;
                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_character))) {
                                                                        i2 = 1;
                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_character))) {
                                                                        i2 = 1;
                                                                    } else {
                                                                        int i3 = 30;
                                                                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_button))) {
                                                                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_button))) {
                                                                                i3 = 31;
                                                                                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_control))) {
                                                                                    if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_control))) {
                                                                                        i3 = 52;
                                                                                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_checkbox))) {
                                                                                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_checkbox))) {
                                                                                                i3 = 32;
                                                                                                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_aria_landmark))) {
                                                                                                    if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_aria_landmark))) {
                                                                                                        i3 = 33;
                                                                                                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_edit_field))) {
                                                                                                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_edit_field))) {
                                                                                                                i3 = 34;
                                                                                                                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_focusable_item))) {
                                                                                                                    if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_focusable_item))) {
                                                                                                                        i3 = 35;
                                                                                                                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_graphic))) {
                                                                                                                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_graphic))) {
                                                                                                                                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_heading))) {
                                                                                                                                    if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_heading))) {
                                                                                                                                        i2 = 1;
                                                                                                                                    } else {
                                                                                                                                        i3 = 8;
                                                                                                                                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_heading_1))) {
                                                                                                                                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_heading_1))) {
                                                                                                                                                i3 = 9;
                                                                                                                                                if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_heading_2))) {
                                                                                                                                                    if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_heading_2))) {
                                                                                                                                                        i3 = 10;
                                                                                                                                                        if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_heading_3))) {
                                                                                                                                                            if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_heading_3))) {
                                                                                                                                                                if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_heading_4))) {
                                                                                                                                                                    i = 11;
                                                                                                                                                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_heading_4))) {
                                                                                                                                                                    i = 11;
                                                                                                                                                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_heading_5))) {
                                                                                                                                                                    i = 12;
                                                                                                                                                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_heading_5))) {
                                                                                                                                                                    i = 12;
                                                                                                                                                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_heading_6))) {
                                                                                                                                                                    i = 13;
                                                                                                                                                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_heading_6))) {
                                                                                                                                                                    i = 13;
                                                                                                                                                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_list_item))) {
                                                                                                                                                                    i = 37;
                                                                                                                                                                } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_list_item))) {
                                                                                                                                                                    i = 37;
                                                                                                                                                                } else if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_link))) {
                                                                                                                                                                    if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_link))) {
                                                                                                                                                                        i2 = 1;
                                                                                                                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_list))) {
                                                                                                                                                                        i = 43;
                                                                                                                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_list))) {
                                                                                                                                                                        i = 43;
                                                                                                                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_table))) {
                                                                                                                                                                        i = 48;
                                                                                                                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_table))) {
                                                                                                                                                                        i = 48;
                                                                                                                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_combobox))) {
                                                                                                                                                                        i = 54;
                                                                                                                                                                    } else if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_combobox))) {
                                                                                                                                                                        i = 54;
                                                                                                                                                                    } else if (!str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_next_window))) {
                                                                                                                                                                        if (str.equals(this.context.getString(R.string.keycombo_shortcut_navigate_previous_window))) {
                                                                                                                                                                            i2 = 4096;
                                                                                                                                                                        } else {
                                                                                                                                                                            return 0L;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            i2 = 1;
                                                                        }
                                                                        i = i3;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        i = 21;
                                                    }
                                                    i = 22;
                                                }
                                                i = 19;
                                            }
                                        }
                                        i = 36;
                                    }
                                    i = 40;
                                }
                            }
                        }
                        i2 = 4097;
                    }
                    i2 = 4096;
                }
                i2 = 1;
            }
            return KeyComboManager.getKeyComboCode(i2, i);
        }
        return 0L;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final String getDescriptionOfEligibleKeyCombo() {
        String str;
        Context context = this.context;
        String string = SpannableUtils$IdentifierSpan.getSharedPreferences(context).getString(getPreferenceKeyForTriggerModifier(), context.getString(R.string.trigger_modifier_alt_entry_value));
        if (string.equals(this.context.getString(R.string.trigger_modifier_alt_entry_value))) {
            str = this.context.getString(R.string.keycombo_key_modifier_alt);
        } else if (string.equals(this.context.getString(R.string.trigger_modifier_meta_entry_value))) {
            str = this.context.getString(R.string.keycombo_key_modifier_meta);
        } else {
            str = "";
        }
        return this.context.getString(R.string.keycombo_assign_dialog_default_keymap_instruction, str);
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
        for (Map.Entry entry : this.keyComboCodeMap.entrySet()) {
            if (((Long) entry.getValue()).longValue() == j) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final String getPreferenceKeyForTriggerModifier() {
        return this.context.getString(R.string.pref_default_keymap_trigger_modifier_key);
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final int getTriggerModifier() {
        return this.triggerModifier;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final boolean isEligibleKeyComboCode(long j) {
        if (j == 0) {
            return true;
        }
        int i = (int) j;
        if (!KeyEvent.isModifierKey(i) && i != 0 && (KeyComboManager.getModifier(j) & this.triggerModifier) == 0) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final void notifyTriggerModifierChanged() {
        loadTriggerModifierFromPreferences();
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final void saveKeyComboCode(String str, long j) {
        this.persister$ar$class_merging$ar$class_merging$ar$class_merging.saveKeyCombo(str, j);
        this.keyComboCodeMap.put(str, Long.valueOf(j));
    }

    @Override // com.google.android.accessibility.talkback.keyboard.KeyComboModel
    public final void updateVersion(int i) {
        if (i < 50200001) {
            Context context = this.context;
            moveKeyComboPreferenceValue(context.getString(R.string.keycombo_shortcut_navigate_next), context.getString(R.string.keycombo_shortcut_navigate_next_default));
            Context context2 = this.context;
            moveKeyComboPreferenceValue(context2.getString(R.string.keycombo_shortcut_navigate_previous), context2.getString(R.string.keycombo_shortcut_navigate_previous_default));
        }
    }
}

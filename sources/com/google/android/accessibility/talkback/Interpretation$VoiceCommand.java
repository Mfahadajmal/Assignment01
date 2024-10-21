package com.google.android.accessibility.talkback;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$VoiceCommand extends SpannableUtils$NonCopyableTextSpan {
    private final Action command;
    private final CursorGranularity granularity;
    private final AccessibilityNodeInfoCompat targetNode;
    private final CharSequence text;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Action {
        VOICE_COMMAND_UNKNOWN,
        VOICE_COMMAND_NEXT_GRANULARITY,
        VOICE_COMMAND_SELECT_ALL,
        VOICE_COMMAND_START_SELECT,
        VOICE_COMMAND_END_SELECT,
        VOICE_COMMAND_COPY,
        VOICE_COMMAND_INSERT,
        VOICE_COMMAND_CUT,
        VOICE_COMMAND_PASTE,
        VOICE_COMMAND_DELETE,
        VOICE_COMMAND_LABEL,
        VOICE_COMMAND_REPEAT_SEARCH,
        VOICE_COMMAND_FIND,
        VOICE_COMMAND_START_AT_TOP,
        VOICE_COMMAND_START_AT_CURSOR,
        VOICE_COMMAND_COPY_LAST_SPOKEN_UTTERANCE,
        VOICE_COMMAND_FIRST,
        VOICE_COMMAND_LAST,
        VOICE_COMMAND_HOME,
        VOICE_COMMAND_BACK,
        VOICE_COMMAND_RECENT,
        VOICE_COMMAND_ALL_APPS,
        VOICE_COMMAND_NOTIFICATIONS,
        VOICE_COMMAND_QUICK_SETTINGS,
        VOICE_COMMAND_BRIGHTEN_SCREEN,
        VOICE_COMMAND_DIM_SCREEN,
        VOICE_COMMAND_SHOW_COMMAND_LIST
    }

    public Interpretation$VoiceCommand() {
        super((byte[]) null);
    }

    public final Action command() {
        return this.command;
    }

    public final boolean equals(Object obj) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        CursorGranularity cursorGranularity;
        CharSequence charSequence;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Interpretation$VoiceCommand) {
            Interpretation$VoiceCommand interpretation$VoiceCommand = (Interpretation$VoiceCommand) obj;
            if (this.command.equals(interpretation$VoiceCommand.command()) && ((accessibilityNodeInfoCompat = this.targetNode) != null ? accessibilityNodeInfoCompat.equals(interpretation$VoiceCommand.targetNode()) : interpretation$VoiceCommand.targetNode() == null) && ((cursorGranularity = this.granularity) != null ? cursorGranularity.equals(interpretation$VoiceCommand.granularity()) : interpretation$VoiceCommand.granularity() == null) && ((charSequence = this.text) != null ? charSequence.equals(interpretation$VoiceCommand.text()) : interpretation$VoiceCommand.text() == null)) {
                return true;
            }
        }
        return false;
    }

    public final CursorGranularity granularity() {
        return this.granularity;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        int hashCode3 = this.command.hashCode() ^ 1000003;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.targetNode;
        int i = 0;
        if (accessibilityNodeInfoCompat == null) {
            hashCode = 0;
        } else {
            hashCode = accessibilityNodeInfoCompat.hashCode();
        }
        int i2 = ((hashCode3 * 1000003) ^ hashCode) * 1000003;
        CursorGranularity cursorGranularity = this.granularity;
        if (cursorGranularity == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = cursorGranularity.hashCode();
        }
        int i3 = (i2 ^ hashCode2) * 1000003;
        CharSequence charSequence = this.text;
        if (charSequence != null) {
            i = charSequence.hashCode();
        }
        return i3 ^ i;
    }

    public final AccessibilityNodeInfoCompat targetNode() {
        return this.targetNode;
    }

    public final CharSequence text() {
        return this.text;
    }

    public final String toString() {
        String concat;
        String optionalField = StringBuilderUtils.optionalField("command", command());
        if (targetNode() == null) {
            concat = null;
        } else {
            concat = "targetNode=".concat(String.valueOf(AccessibilityNodeInfoUtils.toStringShort(targetNode())));
        }
        return StringBuilderUtils.joinFields("VoiceCommand{", optionalField, concat, StringBuilderUtils.optionalField("granularity", granularity()), StringBuilderUtils.optionalText("text", text()), "}");
    }

    public Interpretation$VoiceCommand(Action action, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, CursorGranularity cursorGranularity, CharSequence charSequence) {
        this();
        if (action == null) {
            throw new NullPointerException("Null command");
        }
        this.command = action;
        this.targetNode = accessibilityNodeInfoCompat;
        this.granularity = cursorGranularity;
        this.text = charSequence;
    }
}

package com.google.android.accessibility.talkback;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityGestureEvent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Interpretation$ManualScroll;
import com.google.android.accessibility.talkback.actor.TalkBackUIActor$Type;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.shared.logger.MLKitLoggingOptions;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Feedback {
    public final Performance.EventId eventId;
    public final ImmutableList failovers;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AdjustValue {
        public final int action$ar$edu;

        public AdjustValue() {
        }

        public final int action$ar$edu$59d8d043_0() {
            return this.action$ar$edu;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof AdjustValue) && this.action$ar$edu == ((AdjustValue) obj).action$ar$edu$59d8d043_0()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu) ^ 1000003;
        }

        public final String toString() {
            String str;
            if (this.action$ar$edu != 1) {
                str = "DECREASE_VALUE";
            } else {
                str = "INCREASE_VALUE";
            }
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "AdjustValue{action=", "}");
        }

        public AdjustValue(int i) {
            this();
            this.action$ar$edu = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AdjustVolume {
        public final int action$ar$edu$9c965da5_0;
        private final int streamType$ar$edu;

        public AdjustVolume() {
        }

        public final int action$ar$edu$3af50ce9_0() {
            return this.action$ar$edu$9c965da5_0;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof AdjustVolume) && this.action$ar$edu$9c965da5_0 == ((AdjustVolume) obj).action$ar$edu$3af50ce9_0()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return ((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$9c965da5_0) ^ 1000003) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(1);
        }

        public final String toString() {
            String str;
            if (this.action$ar$edu$9c965da5_0 != 1) {
                str = "DECREASE_VOLUME";
            } else {
                str = "INCREASE_VOLUME";
            }
            return "AdjustVolume{action=" + str + ", streamType=STREAM_TYPE_ACCESSIBILITY}";
        }

        public AdjustVolume(int i, int i2) {
            this();
            this.action$ar$edu$9c965da5_0 = i;
            this.streamType$ar$edu = 1;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BrailleDisplay {
        private final int action$ar$edu$54249cf3_0;

        public BrailleDisplay() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof BrailleDisplay) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(1) ^ 1000003;
        }

        public final String toString() {
            return "BrailleDisplay{action=TOGGLE_BRAILLE_DISPLAY_ON_OR_OFF}";
        }

        public BrailleDisplay(int i) {
            this();
            this.action$ar$edu$54249cf3_0 = 1;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ContinuousRead {
        public final int action$ar$edu$e8a97c7_0;

        public ContinuousRead() {
        }

        public final int action$ar$edu$7de60f37_0() {
            return this.action$ar$edu$e8a97c7_0;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof ContinuousRead) && this.action$ar$edu$e8a97c7_0 == ((ContinuousRead) obj).action$ar$edu$7de60f37_0()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$e8a97c7_0) ^ 1000003;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$e8a97c7_0;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            str = "IGNORE";
                        } else {
                            str = "INTERRUPT";
                        }
                    } else {
                        str = "READ_FOCUSED_CONTENT";
                    }
                } else {
                    str = "START_AT_CURSOR";
                }
            } else {
                str = "START_AT_TOP";
            }
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "ContinuousRead{action=", "}");
        }

        public ContinuousRead(int i) {
            this();
            this.action$ar$edu$e8a97c7_0 = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DeviceInfo {
        private final int action$ar$edu$f2621738_0;
        public final Configuration configuration;

        public DeviceInfo() {
        }

        public final Configuration configuration() {
            return this.configuration;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof DeviceInfo) {
                DeviceInfo deviceInfo = (DeviceInfo) obj;
                Configuration configuration = this.configuration;
                if (configuration != null ? configuration.equals(deviceInfo.configuration()) : deviceInfo.configuration() == null) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(1) ^ 1000003;
            Configuration configuration = this.configuration;
            if (configuration == null) {
                hashCode = 0;
            } else {
                hashCode = configuration.hashCode();
            }
            return (ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode;
        }

        public final String toString() {
            return "DeviceInfo{action=CONFIG_CHANGED, configuration=" + String.valueOf(this.configuration) + "}";
        }

        public DeviceInfo(int i, Configuration configuration) {
            this();
            this.action$ar$edu$f2621738_0 = 1;
            this.configuration = configuration;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DimScreen {
        public final int action$ar$edu$e9e2fd4b_0;

        public DimScreen() {
        }

        public final int action$ar$edu$d6bbf131_0() {
            return this.action$ar$edu$e9e2fd4b_0;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof DimScreen) && this.action$ar$edu$e9e2fd4b_0 == ((DimScreen) obj).action$ar$edu$d6bbf131_0()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$e9e2fd4b_0) ^ 1000003;
        }

        public final String toString() {
            String str;
            if (this.action$ar$edu$e9e2fd4b_0 != 1) {
                str = "BRIGHTEN";
            } else {
                str = "DIM";
            }
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "DimScreen{action=", "}");
        }

        public DimScreen(int i) {
            this();
            this.action$ar$edu$e9e2fd4b_0 = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EditText {
        public final int action$ar$edu$24097d5e_0;
        public final int cursorIndex;
        public final AccessibilityNodeInfoCompat node;
        public final AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion;
        public final CharSequence text;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public int action$ar$edu$24097d5e_0;
            private int cursorIndex;
            public AccessibilityNodeInfoCompat node;
            public byte set$0;
            public AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion;
            public CharSequence text;

            public Builder() {
            }

            public final EditText build() {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
                int i;
                if (this.set$0 == 3 && (accessibilityNodeInfoCompat = this.node) != null && (i = this.action$ar$edu$24097d5e_0) != 0) {
                    return new EditText(accessibilityNodeInfoCompat, i, this.text, this.spellingSuggestion, this.cursorIndex);
                }
                StringBuilder sb = new StringBuilder();
                if (this.node == null) {
                    sb.append(" node");
                }
                if (this.action$ar$edu$24097d5e_0 == 0) {
                    sb.append(" action");
                }
                if ((this.set$0 & 1) == 0) {
                    sb.append(" stopSelecting");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" cursorIndex");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }

            public final void setCursorIndex$ar$ds(int i) {
                this.cursorIndex = i;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public EditText() {
        }

        public final int action$ar$edu$edd0b73c_0() {
            return this.action$ar$edu$24097d5e_0;
        }

        public final int cursorIndex() {
            return this.cursorIndex;
        }

        public final boolean equals(Object obj) {
            CharSequence charSequence;
            AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion;
            if (obj == this) {
                return true;
            }
            if (obj instanceof EditText) {
                EditText editText = (EditText) obj;
                if (this.node.equals(editText.node())) {
                    int i = this.action$ar$edu$24097d5e_0;
                    int action$ar$edu$edd0b73c_0 = editText.action$ar$edu$edd0b73c_0();
                    if (i != 0) {
                        if (i == action$ar$edu$edd0b73c_0 && ((charSequence = this.text) != null ? charSequence.equals(editText.text()) : editText.text() == null) && ((spellingSuggestion = this.spellingSuggestion) != null ? spellingSuggestion.equals(editText.spellingSuggestion()) : editText.spellingSuggestion() == null) && this.cursorIndex == editText.cursorIndex()) {
                            return true;
                        }
                    } else {
                        throw null;
                    }
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int hashCode2 = ((this.node.hashCode() ^ 1000003) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$24097d5e_0);
            CharSequence charSequence = this.text;
            int i = 0;
            if (charSequence == null) {
                hashCode = 0;
            } else {
                hashCode = charSequence.hashCode();
            }
            int i2 = ((((hashCode2 * 1000003) ^ 1237) * 1000003) ^ hashCode) * 1000003;
            AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion = this.spellingSuggestion;
            if (spellingSuggestion != null) {
                i = spellingSuggestion.hashCode();
            }
            return ((i2 ^ i) * 1000003) ^ this.cursorIndex;
        }

        public final AccessibilityNodeInfoCompat node() {
            return this.node;
        }

        public final AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion() {
            return this.spellingSuggestion;
        }

        public final CharSequence text() {
            return this.text;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$24097d5e_0;
            String valueOf = String.valueOf(this.node);
            switch (i) {
                case 1:
                    str = "SELECT_ALL";
                    break;
                case 2:
                    str = "START_SELECT";
                    break;
                case 3:
                    str = "END_SELECT";
                    break;
                case 4:
                    str = "COPY";
                    break;
                case 5:
                    str = "CUT";
                    break;
                case 6:
                    str = "PASTE";
                    break;
                case 7:
                    str = "DELETE";
                    break;
                case 8:
                    str = "CURSOR_TO_BEGINNING";
                    break;
                case 9:
                    str = "CURSOR_TO_END";
                    break;
                case 10:
                    str = "INSERT";
                    break;
                case 11:
                    str = "TYPO_CORRECTION";
                    break;
                case 12:
                    str = "MOVE_CURSOR";
                    break;
                default:
                    str = "null";
                    break;
            }
            CharSequence charSequence = this.text;
            AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion = this.spellingSuggestion;
            int i2 = this.cursorIndex;
            return "EditText{node=" + valueOf + ", action=" + str + ", stopSelecting=false, text=" + String.valueOf(charSequence) + ", spellingSuggestion=" + String.valueOf(spellingSuggestion) + ", cursorIndex=" + i2 + "}";
        }

        public EditText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, CharSequence charSequence, AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion, int i2) {
            this();
            this.node = accessibilityNodeInfoCompat;
            this.action$ar$edu$24097d5e_0 = i;
            this.text = charSequence;
            this.spellingSuggestion = spellingSuggestion;
            this.cursorIndex = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Focus {
        public final Action action;
        public final FocusActionInfo focusActionInfo;
        public final boolean forceRefocus;
        public final ScreenState screenState;
        public final CharSequence searchKeyword;
        public final AccessibilityNodeInfoCompat stealNextWindowTarget;
        public final int stealNextWindowTargetDirection;
        public final AccessibilityNodeInfoCompat target;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public enum Action {
            FOCUS,
            CLEAR,
            CACHE,
            MUTE_NEXT_FOCUS,
            RESTORE_ON_NEXT_WINDOW,
            RESTORE_TO_CACHE,
            CLEAR_CACHED,
            INITIAL_FOCUS_RESTORE,
            INITIAL_FOCUS_FOLLOW_INPUT,
            INITIAL_FOCUS_FIRST_CONTENT,
            FOCUS_FOR_TOUCH,
            CLICK_NODE,
            LONG_CLICK_NODE,
            CLICK_CURRENT,
            LONG_CLICK_CURRENT,
            CLICK_ANCESTOR,
            SEARCH_FROM_TOP,
            SEARCH_AGAIN,
            ENSURE_ACCESSIBILITY_FOCUS_ON_SCREEN,
            RENEW_ENSURE_FOCUS,
            STEAL_NEXT_WINDOW_NAVIGATION
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            private Action action;
            public FocusActionInfo focusActionInfo;
            private boolean forceRefocus;
            public ScreenState screenState;
            public CharSequence searchKeyword;
            private byte set$0;
            public AccessibilityNodeInfoCompat stealNextWindowTarget;
            private int stealNextWindowTargetDirection;
            public AccessibilityNodeInfoCompat target;

            public Builder() {
            }

            public final Focus build() {
                Action action;
                if (this.set$0 == 3 && (action = this.action) != null) {
                    return new Focus(this.target, this.focusActionInfo, this.searchKeyword, this.forceRefocus, action, this.screenState, this.stealNextWindowTarget, this.stealNextWindowTargetDirection);
                }
                StringBuilder sb = new StringBuilder();
                if ((this.set$0 & 1) == 0) {
                    sb.append(" forceRefocus");
                }
                if (this.action == null) {
                    sb.append(" action");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" stealNextWindowTargetDirection");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }

            public final void setAction$ar$ds$84b60801_0(Action action) {
                if (action != null) {
                    this.action = action;
                    return;
                }
                throw new NullPointerException("Null action");
            }

            public final void setForceRefocus$ar$ds(boolean z) {
                this.forceRefocus = z;
                this.set$0 = (byte) (this.set$0 | 1);
            }

            public final void setStealNextWindowTargetDirection$ar$ds(int i) {
                this.stealNextWindowTargetDirection = i;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public Focus() {
        }

        public static Builder builder() {
            Builder builder = new Builder(null);
            builder.setForceRefocus$ar$ds(false);
            builder.setStealNextWindowTargetDirection$ar$ds(0);
            return builder;
        }

        public final Action action() {
            return this.action;
        }

        public final boolean equals(Object obj) {
            ScreenState screenState;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Focus) {
                Focus focus = (Focus) obj;
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = this.target;
                if (accessibilityNodeInfoCompat2 != null ? accessibilityNodeInfoCompat2.equals(focus.target()) : focus.target() == null) {
                    FocusActionInfo focusActionInfo = this.focusActionInfo;
                    if (focusActionInfo != null ? focusActionInfo.equals(focus.focusActionInfo()) : focus.focusActionInfo() == null) {
                        CharSequence charSequence = this.searchKeyword;
                        if (charSequence != null ? charSequence.equals(focus.searchKeyword()) : focus.searchKeyword() == null) {
                            if (this.forceRefocus == focus.forceRefocus() && this.action.equals(focus.action()) && ((screenState = this.screenState) != null ? screenState.equals(focus.screenState()) : focus.screenState() == null) && ((accessibilityNodeInfoCompat = this.stealNextWindowTarget) != null ? accessibilityNodeInfoCompat.equals(focus.stealNextWindowTarget()) : focus.stealNextWindowTarget() == null) && this.stealNextWindowTargetDirection == focus.stealNextWindowTargetDirection()) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public final FocusActionInfo focusActionInfo() {
            return this.focusActionInfo;
        }

        public final boolean forceRefocus() {
            return this.forceRefocus;
        }

        public final int hashCode() {
            int hashCode;
            int hashCode2;
            int hashCode3;
            int i;
            int hashCode4;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.target;
            int i2 = 0;
            if (accessibilityNodeInfoCompat == null) {
                hashCode = 0;
            } else {
                hashCode = accessibilityNodeInfoCompat.hashCode();
            }
            FocusActionInfo focusActionInfo = this.focusActionInfo;
            if (focusActionInfo == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = focusActionInfo.hashCode();
            }
            int i3 = hashCode ^ (-721379959);
            CharSequence charSequence = this.searchKeyword;
            if (charSequence == null) {
                hashCode3 = 0;
            } else {
                hashCode3 = charSequence.hashCode();
            }
            int i4 = ((i3 * 1000003) ^ hashCode2) * (-721379959);
            if (true != this.forceRefocus) {
                i = 1237;
            } else {
                i = 1231;
            }
            int hashCode5 = (((((i4 ^ hashCode3) * 1000003) ^ i) * 1000003) ^ this.action.hashCode()) * 1000003;
            ScreenState screenState = this.screenState;
            if (screenState == null) {
                hashCode4 = 0;
            } else {
                hashCode4 = screenState.hashCode();
            }
            int i5 = (hashCode5 ^ hashCode4) * 1000003;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = this.stealNextWindowTarget;
            if (accessibilityNodeInfoCompat2 != null) {
                i2 = accessibilityNodeInfoCompat2.hashCode();
            }
            return ((i5 ^ i2) * 1000003) ^ this.stealNextWindowTargetDirection;
        }

        public final ScreenState screenState() {
            return this.screenState;
        }

        public final CharSequence searchKeyword() {
            return this.searchKeyword;
        }

        public final AccessibilityNodeInfoCompat stealNextWindowTarget() {
            return this.stealNextWindowTarget;
        }

        public final int stealNextWindowTargetDirection() {
            return this.stealNextWindowTargetDirection;
        }

        public final AccessibilityNodeInfoCompat target() {
            return this.target;
        }

        public final String toString() {
            return StringBuilderUtils.joinFields(StringBuilderUtils.optionalField("action", action()), StringBuilderUtils.optionalSubObj("start", AccessibilityNodeInfoUtils.toStringShort(null)), StringBuilderUtils.optionalSubObj("target", AccessibilityNodeInfoUtils.toStringShort(target())), StringBuilderUtils.optionalSubObj("focusActionInfo", focusActionInfo()), StringBuilderUtils.optionalSubObj("navigationAction", null), StringBuilderUtils.optionalText("searchKeyword", searchKeyword()), StringBuilderUtils.optionalTag("forceRefocus", forceRefocus()), StringBuilderUtils.optionalSubObj("screenState", screenState()), StringBuilderUtils.optionalSubObj("stealNextWindowTarget", AccessibilityNodeInfoUtils.toStringShort(stealNextWindowTarget())), StringBuilderUtils.optionalInt("stealNextWindowTargetDirection", stealNextWindowTargetDirection(), 0));
        }

        public Focus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, FocusActionInfo focusActionInfo, CharSequence charSequence, boolean z, Action action, ScreenState screenState, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2, int i) {
            this();
            this.target = accessibilityNodeInfoCompat;
            this.focusActionInfo = focusActionInfo;
            this.searchKeyword = charSequence;
            this.forceRefocus = z;
            this.action = action;
            this.screenState = screenState;
            this.stealNextWindowTarget = accessibilityNodeInfoCompat2;
            this.stealNextWindowTargetDirection = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FocusDirection {
        public final Action action;
        public final boolean defaultToInputFocus;
        public final int direction;
        public final boolean fromUser;
        public final CursorGranularity granularity;
        public final int htmlTargetType;
        public final int inputMode;
        public final boolean scroll;
        public final AccessibilityNodeInfoCompat targetNode;
        public final boolean toContainer;
        public final boolean toWindow;
        public final boolean wrap;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public enum Action {
            FOLLOW,
            NEXT,
            NEXT_PAGE,
            PREVIOUS_PAGE,
            TOP,
            BOTTOM,
            SCROLL_UP,
            SCROLL_DOWN,
            SCROLL_LEFT,
            SCROLL_RIGHT,
            SET_GRANULARITY,
            NEXT_GRANULARITY,
            PREVIOUS_GRANULARITY,
            SELECTION_MODE_ON,
            SELECTION_MODE_OFF,
            NAVIGATE
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            private Action action;
            private boolean defaultToInputFocus;
            private int direction;
            private boolean fromUser;
            public CursorGranularity granularity;
            private int htmlTargetType;
            private int inputMode;
            private boolean scroll;
            private short set$0;
            public AccessibilityNodeInfoCompat targetNode;
            private boolean toContainer;
            private boolean toWindow;
            private boolean wrap;

            public Builder() {
            }

            public final FocusDirection build() {
                Action action;
                if (this.set$0 == 511 && (action = this.action) != null) {
                    return new FocusDirection(this.direction, this.htmlTargetType, this.targetNode, this.defaultToInputFocus, this.scroll, this.wrap, this.toContainer, this.toWindow, this.inputMode, this.granularity, this.fromUser, action);
                }
                StringBuilder sb = new StringBuilder();
                if ((this.set$0 & 1) == 0) {
                    sb.append(" direction");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" htmlTargetType");
                }
                if ((this.set$0 & 4) == 0) {
                    sb.append(" defaultToInputFocus");
                }
                if ((this.set$0 & 8) == 0) {
                    sb.append(" scroll");
                }
                if ((this.set$0 & 16) == 0) {
                    sb.append(" wrap");
                }
                if ((this.set$0 & 32) == 0) {
                    sb.append(" toContainer");
                }
                if ((this.set$0 & 64) == 0) {
                    sb.append(" toWindow");
                }
                if ((this.set$0 & 128) == 0) {
                    sb.append(" inputMode");
                }
                if ((this.set$0 & 256) == 0) {
                    sb.append(" fromUser");
                }
                if (this.action == null) {
                    sb.append(" action");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }

            public final void setAction$ar$ds$940a2012_0(Action action) {
                if (action != null) {
                    this.action = action;
                    return;
                }
                throw new NullPointerException("Null action");
            }

            public final void setDefaultToInputFocus$ar$ds(boolean z) {
                this.defaultToInputFocus = z;
                this.set$0 = (short) (this.set$0 | 4);
            }

            public final void setDirection$ar$ds(int i) {
                this.direction = i;
                this.set$0 = (short) (this.set$0 | 1);
            }

            public final void setFromUser$ar$ds(boolean z) {
                this.fromUser = z;
                this.set$0 = (short) (this.set$0 | 256);
            }

            public final void setHtmlTargetType$ar$ds(int i) {
                this.htmlTargetType = i;
                this.set$0 = (short) (this.set$0 | 2);
            }

            public final void setInputMode$ar$ds(int i) {
                this.inputMode = i;
                this.set$0 = (short) (this.set$0 | 128);
            }

            public final void setScroll$ar$ds(boolean z) {
                this.scroll = z;
                this.set$0 = (short) (this.set$0 | 8);
            }

            public final void setToContainer$ar$ds(boolean z) {
                this.toContainer = z;
                this.set$0 = (short) (this.set$0 | 32);
            }

            public final void setToWindow$ar$ds(boolean z) {
                this.toWindow = z;
                this.set$0 = (short) (this.set$0 | 64);
            }

            public final void setWrap$ar$ds(boolean z) {
                this.wrap = z;
                this.set$0 = (short) (this.set$0 | 16);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public FocusDirection() {
        }

        public static Builder builder() {
            Builder builder = new Builder(null);
            builder.setDirection$ar$ds(0);
            builder.setHtmlTargetType$ar$ds(0);
            builder.setDefaultToInputFocus$ar$ds(false);
            builder.setScroll$ar$ds(false);
            builder.setWrap$ar$ds(false);
            builder.setToContainer$ar$ds(false);
            builder.setToWindow$ar$ds(false);
            builder.setInputMode$ar$ds(-1);
            builder.setFromUser$ar$ds(false);
            return builder;
        }

        public final Action action() {
            return this.action;
        }

        public final boolean defaultToInputFocus() {
            return this.defaultToInputFocus;
        }

        public final int direction() {
            return this.direction;
        }

        public final boolean equals(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
            CursorGranularity cursorGranularity;
            if (obj == this) {
                return true;
            }
            if (obj instanceof FocusDirection) {
                FocusDirection focusDirection = (FocusDirection) obj;
                if (this.direction == focusDirection.direction() && this.htmlTargetType == focusDirection.htmlTargetType() && ((accessibilityNodeInfoCompat = this.targetNode) != null ? accessibilityNodeInfoCompat.equals(focusDirection.targetNode()) : focusDirection.targetNode() == null) && this.defaultToInputFocus == focusDirection.defaultToInputFocus() && this.scroll == focusDirection.scroll() && this.wrap == focusDirection.wrap() && this.toContainer == focusDirection.toContainer() && this.toWindow == focusDirection.toWindow() && this.inputMode == focusDirection.inputMode() && ((cursorGranularity = this.granularity) != null ? cursorGranularity.equals(focusDirection.granularity()) : focusDirection.granularity() == null) && this.fromUser == focusDirection.fromUser() && this.action.equals(focusDirection.action())) {
                    return true;
                }
            }
            return false;
        }

        public final boolean fromUser() {
            return this.fromUser;
        }

        public final CursorGranularity granularity() {
            return this.granularity;
        }

        public final boolean hasHtmlTargetType() {
            if (htmlTargetType() != 0) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.targetNode;
            int i6 = 0;
            if (accessibilityNodeInfoCompat == null) {
                hashCode = 0;
            } else {
                hashCode = accessibilityNodeInfoCompat.hashCode();
            }
            int i7 = this.direction;
            int i8 = this.htmlTargetType;
            int i9 = 1237;
            if (true != this.defaultToInputFocus) {
                i = 1237;
            } else {
                i = 1231;
            }
            int i10 = hashCode ^ ((((i7 ^ 1000003) * 1000003) ^ i8) * 1000003);
            if (true != this.scroll) {
                i2 = 1237;
            } else {
                i2 = 1231;
            }
            int i11 = ((((i10 * 1000003) ^ i) * 1000003) ^ i2) * 1000003;
            if (true != this.wrap) {
                i3 = 1237;
            } else {
                i3 = 1231;
            }
            int i12 = (i11 ^ i3) * 1000003;
            if (true != this.toContainer) {
                i4 = 1237;
            } else {
                i4 = 1231;
            }
            int i13 = (i12 ^ i4) * 1000003;
            if (true != this.toWindow) {
                i5 = 1237;
            } else {
                i5 = 1231;
            }
            int i14 = (((i13 ^ i5) * 1000003) ^ this.inputMode) * 1000003;
            CursorGranularity cursorGranularity = this.granularity;
            if (cursorGranularity != null) {
                i6 = cursorGranularity.hashCode();
            }
            int i15 = (i14 ^ i6) * 1000003;
            if (true == this.fromUser) {
                i9 = 1231;
            }
            return ((i15 ^ i9) * 1000003) ^ this.action.hashCode();
        }

        public final int htmlTargetType() {
            return this.htmlTargetType;
        }

        public final int inputMode() {
            return this.inputMode;
        }

        public final boolean scroll() {
            return this.scroll;
        }

        public final AccessibilityNodeInfoCompat targetNode() {
            return this.targetNode;
        }

        public final boolean toContainer() {
            return this.toContainer;
        }

        public final String toString() {
            return StringBuilderUtils.joinFields(StringBuilderUtils.optionalField("action", action()), StringBuilderUtils.optionalInt("direction", direction(), 0), StringBuilderUtils.optionalInt("htmlTargetType", htmlTargetType(), 0), StringBuilderUtils.optionalSubObj("targetNode", AccessibilityNodeInfoUtils.toStringShort(targetNode())), StringBuilderUtils.optionalTag("defaultToInputFocus", defaultToInputFocus()), StringBuilderUtils.optionalTag("scroll", scroll()), StringBuilderUtils.optionalTag("wrap", wrap()), StringBuilderUtils.optionalTag("toContainer", toContainer()), StringBuilderUtils.optionalTag("toWindow", toWindow()), StringBuilderUtils.optionalInt("inputMode", inputMode(), -1), StringBuilderUtils.optionalField("granularity", granularity()), StringBuilderUtils.optionalTag("fromUser", fromUser()));
        }

        public final boolean toWindow() {
            return this.toWindow;
        }

        public final boolean wrap() {
            return this.wrap;
        }

        public FocusDirection(int i, int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i3, CursorGranularity cursorGranularity, boolean z6, Action action) {
            this();
            this.direction = i;
            this.htmlTargetType = i2;
            this.targetNode = accessibilityNodeInfoCompat;
            this.defaultToInputFocus = z;
            this.scroll = z2;
            this.wrap = z3;
            this.toContainer = z4;
            this.toWindow = z5;
            this.inputMode = i3;
            this.granularity = cursorGranularity;
            this.fromUser = z6;
            this.action = action;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GeminiRequest {
        public final int action$ar$edu$59689e14_0;
        public final Bitmap image;
        public final boolean manualTrigger;
        public final int requestId;
        public final String text;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            private int action$ar$edu$59689e14_0;
            public Bitmap image;
            private boolean manualTrigger;
            private int requestId;
            private byte set$0;
            public String text;

            public Builder() {
            }

            public final GeminiRequest build() {
                int i;
                if (this.set$0 == 3 && (i = this.action$ar$edu$59689e14_0) != 0) {
                    return new GeminiRequest(i, this.requestId, this.text, this.image, this.manualTrigger);
                }
                StringBuilder sb = new StringBuilder();
                if (this.action$ar$edu$59689e14_0 == 0) {
                    sb.append(" action");
                }
                if ((this.set$0 & 1) == 0) {
                    sb.append(" requestId");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" manualTrigger");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }

            public final void setAction$ar$edu$e15164a1_0$ar$ds(int i) {
                this.action$ar$edu$59689e14_0 = i;
            }

            public final void setManualTrigger$ar$ds(boolean z) {
                this.manualTrigger = z;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public final void setRequestId$ar$ds(int i) {
                this.requestId = i;
                this.set$0 = (byte) (this.set$0 | 1);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public GeminiRequest() {
        }

        public static Builder builder() {
            Builder builder = new Builder(null);
            builder.setRequestId$ar$ds(-1);
            builder.setManualTrigger$ar$ds(true);
            return builder;
        }

        public final int action$ar$edu$b19004e9_0() {
            return this.action$ar$edu$59689e14_0;
        }

        public final boolean equals(Object obj) {
            String str;
            Bitmap bitmap;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GeminiRequest)) {
                return false;
            }
            GeminiRequest geminiRequest = (GeminiRequest) obj;
            int i = this.action$ar$edu$59689e14_0;
            int action$ar$edu$b19004e9_0 = geminiRequest.action$ar$edu$b19004e9_0();
            if (i != 0) {
                if (i == action$ar$edu$b19004e9_0 && this.requestId == geminiRequest.requestId() && ((str = this.text) != null ? str.equals(geminiRequest.text()) : geminiRequest.text() == null) && ((bitmap = this.image) != null ? bitmap.equals(geminiRequest.image()) : geminiRequest.image() == null) && this.manualTrigger == geminiRequest.manualTrigger()) {
                    return true;
                }
                return false;
            }
            throw null;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$59689e14_0) ^ 1000003;
            String str = this.text;
            int i2 = 0;
            if (str == null) {
                hashCode = 0;
            } else {
                hashCode = str.hashCode();
            }
            int i3 = ((((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ this.requestId) * 1000003) ^ hashCode) * 1000003;
            Bitmap bitmap = this.image;
            if (bitmap != null) {
                i2 = bitmap.hashCode();
            }
            int i4 = (i3 ^ i2) * 1000003;
            if (true != this.manualTrigger) {
                i = 1237;
            } else {
                i = 1231;
            }
            return i4 ^ i;
        }

        public final Bitmap image() {
            return this.image;
        }

        public final boolean manualTrigger() {
            return this.manualTrigger;
        }

        public final int requestId() {
            return this.requestId;
        }

        public final String text() {
            return this.text;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$59689e14_0;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        str = "null";
                    } else {
                        str = "MUTE_GEMINI_SOUND";
                    }
                } else {
                    str = "REQUEST_ON_DEVICE_IMAGE_CAPTIONING";
                }
            } else {
                str = "REQUEST";
            }
            int i2 = this.requestId;
            String str2 = this.text;
            Bitmap bitmap = this.image;
            boolean z = this.manualTrigger;
            return "GeminiRequest{action=" + str + ", requestId=" + i2 + ", text=" + str2 + ", image=" + String.valueOf(bitmap) + ", manualTrigger=" + z + "}";
        }

        public GeminiRequest(int i, int i2, String str, Bitmap bitmap, boolean z) {
            this();
            this.action$ar$edu$59689e14_0 = i;
            this.requestId = i2;
            this.text = str;
            this.image = bitmap;
            this.manualTrigger = z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Gesture {
        public final int action$ar$edu$a4cdb88d_0;
        private final AccessibilityGestureEvent currentGesture;

        public Gesture() {
        }

        public final int action$ar$edu$a8b4d88a_0() {
            return this.action$ar$edu$a4cdb88d_0;
        }

        public final AccessibilityGestureEvent currentGesture() {
            return this.currentGesture;
        }

        public final boolean equals(Object obj) {
            AccessibilityGestureEvent accessibilityGestureEvent;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Gesture) {
                Gesture gesture = (Gesture) obj;
                if (this.action$ar$edu$a4cdb88d_0 == gesture.action$ar$edu$a8b4d88a_0() && ((accessibilityGestureEvent = this.currentGesture) != null ? accessibilityGestureEvent.equals(gesture.currentGesture()) : gesture.currentGesture() == null)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$a4cdb88d_0) ^ 1000003;
            AccessibilityGestureEvent accessibilityGestureEvent = this.currentGesture;
            if (accessibilityGestureEvent == null) {
                hashCode = 0;
            } else {
                hashCode = accessibilityGestureEvent.hashCode();
            }
            return (ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode;
        }

        public final String toString() {
            String str;
            if (this.action$ar$edu$a4cdb88d_0 != 1) {
                str = "REPORT";
            } else {
                str = "SAVE";
            }
            return "Gesture{action=" + str + ", currentGesture=" + String.valueOf(this.currentGesture) + "}";
        }

        public Gesture(int i, AccessibilityGestureEvent accessibilityGestureEvent) {
            this();
            this.action$ar$edu$a4cdb88d_0 = i;
            this.currentGesture = accessibilityGestureEvent;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImageCaption {
        public final int action$ar$edu$540c2089_0;
        public final AccessibilityNodeInfoCompat target;
        public final boolean userRequested;

        public ImageCaption() {
        }

        public static MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0() {
            MLKitLoggingOptions.Builder builder = new MLKitLoggingOptions.Builder(null, null);
            builder.setUserRequested$ar$ds(false);
            return builder;
        }

        public final int action$ar$edu$8a4a7d8_0() {
            return this.action$ar$edu$540c2089_0;
        }

        public final boolean equals(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ImageCaption)) {
                return false;
            }
            ImageCaption imageCaption = (ImageCaption) obj;
            int i = this.action$ar$edu$540c2089_0;
            int action$ar$edu$8a4a7d8_0 = imageCaption.action$ar$edu$8a4a7d8_0();
            if (i != 0) {
                if (i == action$ar$edu$8a4a7d8_0 && ((accessibilityNodeInfoCompat = this.target) != null ? accessibilityNodeInfoCompat.equals(imageCaption.target()) : imageCaption.target() == null) && this.userRequested == imageCaption.userRequested()) {
                    return true;
                }
                return false;
            }
            throw null;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$540c2089_0) ^ 1000003;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.target;
            if (accessibilityNodeInfoCompat == null) {
                hashCode = 0;
            } else {
                hashCode = accessibilityNodeInfoCompat.hashCode();
            }
            int i2 = ((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode) * 1000003;
            if (true != this.userRequested) {
                i = 1237;
            } else {
                i = 1231;
            }
            return i2 ^ i;
        }

        public final AccessibilityNodeInfoCompat target() {
            return this.target;
        }

        public final String toString() {
            String str;
            switch (this.action$ar$edu$540c2089_0) {
                case 1:
                    str = "PERFORM_CAPTIONS";
                    break;
                case 2:
                    str = "CONFIRM_DOWNLOAD_AND_PERFORM_CAPTIONS";
                    break;
                case 3:
                    str = "INITIALIZE_ICON_DETECTION";
                    break;
                case 4:
                    str = "INITIALIZE_IMAGE_DESCRIPTION";
                    break;
                case 5:
                    str = "PERFORM_CAPTION_WITH_GEMINI";
                    break;
                case 6:
                    str = "DETAILED_DESCRIPTION_OPT_IN";
                    break;
                case 7:
                    str = "PERFORM_CAPTION_WITH_ON_DEVICE_GEMINI";
                    break;
                case 8:
                    str = "ON_DEVICE_DETAILED_DESCRIPTION_OPT_IN";
                    break;
                case 9:
                    str = "CONFIG_DETAILED_IMAGE_DESCRIPTIONS_SETTINGS";
                    break;
                default:
                    str = "null";
                    break;
            }
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.target;
            boolean z = this.userRequested;
            return "ImageCaption{action=" + str + ", target=" + String.valueOf(accessibilityNodeInfoCompat) + ", userRequested=" + z + "}";
        }

        public final boolean userRequested() {
            return this.userRequested;
        }

        public ImageCaption(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
            this();
            this.action$ar$edu$540c2089_0 = i;
            this.target = accessibilityNodeInfoCompat;
            this.userRequested = z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImageCaptionResult {
        public final int finishReason$ar$edu;
        public final boolean isSuccess;
        public final int requestId;
        public final String text;
        public final boolean userRequested;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public int finishReason$ar$edu;
            public boolean isSuccess;
            public int requestId;
            public byte set$0;
            public String text;
            public boolean userRequested;

            public Builder() {
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public ImageCaptionResult() {
        }

        public final boolean equals(Object obj) {
            int i;
            if (obj == this) {
                return true;
            }
            if (obj instanceof ImageCaptionResult) {
                ImageCaptionResult imageCaptionResult = (ImageCaptionResult) obj;
                if (this.requestId == imageCaptionResult.requestId() && this.text.equals(imageCaptionResult.text()) && this.isSuccess == imageCaptionResult.isSuccess() && this.userRequested == imageCaptionResult.userRequested() && ((i = this.finishReason$ar$edu) != 0 ? i == imageCaptionResult.finishReason$ar$edu$6be4b9b_0() : imageCaptionResult.finishReason$ar$edu$6be4b9b_0() == 0)) {
                    return true;
                }
            }
            return false;
        }

        public final int finishReason$ar$edu$6be4b9b_0() {
            return this.finishReason$ar$edu;
        }

        public final int hashCode() {
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0;
            int i;
            int hashCode = ((this.requestId ^ 1000003) * 1000003) ^ this.text.hashCode();
            int i2 = this.finishReason$ar$edu;
            if (i2 == 0) {
                ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = 0;
            } else {
                ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(i2);
            }
            boolean z = this.userRequested;
            boolean z2 = this.isSuccess;
            int i3 = 1237;
            if (true != z) {
                i = 1237;
            } else {
                i = 1231;
            }
            if (true == z2) {
                i3 = 1231;
            }
            return (((((hashCode * 1000003) ^ i3) * 1000003) ^ i) * 1000003) ^ ArtificialStackFrames$ar$MethodMerging$dc56d17a_0;
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }

        public final int requestId() {
            return this.requestId;
        }

        public final String text() {
            return this.text;
        }

        public final String toString() {
            String str;
            int i = this.finishReason$ar$edu;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                str = "null";
                            } else {
                                str = "ERROR_NOT_FINISHED";
                            }
                        } else {
                            str = "ERROR_BLOCKED";
                        }
                    } else {
                        str = "ERROR_RESPONSE";
                    }
                } else {
                    str = "ERROR_PARSING_RESULT";
                }
            } else {
                str = "STOP";
            }
            boolean z = this.userRequested;
            boolean z2 = this.isSuccess;
            String str2 = this.text;
            return "ImageCaptionResult{requestId=" + this.requestId + ", text=" + str2 + ", isSuccess=" + z2 + ", userRequested=" + z + ", finishReason=" + str + "}";
        }

        public final boolean userRequested() {
            return this.userRequested;
        }

        public ImageCaptionResult(int i, String str, boolean z, boolean z2, int i2) {
            this();
            this.requestId = i;
            this.text = str;
            this.isSuccess = z;
            this.userRequested = z2;
            this.finishReason$ar$edu = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Label {
        public final int action$ar$edu$15b0ed88_0;
        public final AccessibilityNodeInfoCompat node;
        public final String text;

        public Label() {
        }

        public final int action$ar$edu$2791181e_0() {
            return this.action$ar$edu$15b0ed88_0;
        }

        public final boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Label)) {
                return false;
            }
            Label label = (Label) obj;
            int i = this.action$ar$edu$15b0ed88_0;
            int action$ar$edu$2791181e_0 = label.action$ar$edu$2791181e_0();
            if (i != 0) {
                if (action$ar$edu$2791181e_0 == 1 && ((str = this.text) != null ? str.equals(label.text()) : label.text() == null) && this.node.equals(label.node())) {
                    return true;
                }
                return false;
            }
            throw null;
        }

        public final int hashCode() {
            int hashCode;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$15b0ed88_0) ^ 1000003;
            String str = this.text;
            if (str == null) {
                hashCode = 0;
            } else {
                hashCode = str.hashCode();
            }
            return (((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode) * 1000003) ^ this.node.hashCode();
        }

        public final AccessibilityNodeInfoCompat node() {
            return this.node;
        }

        public final String text() {
            return this.text;
        }

        public final String toString() {
            String str;
            if (this.action$ar$edu$15b0ed88_0 != 1) {
                str = "null";
            } else {
                str = "SET";
            }
            return "Label{action=" + str + ", text=" + this.text + ", node=" + String.valueOf(this.node) + "}";
        }

        public Label(int i, String str, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this();
            this.action$ar$edu$15b0ed88_0 = 1;
            this.text = str;
            this.node = accessibilityNodeInfoCompat;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Language {
        public final int action$ar$edu$e7b70ea2_0;
        public final Locale currentLanguage;

        public Language() {
        }

        public final int action$ar$edu$639c1807_0() {
            return this.action$ar$edu$e7b70ea2_0;
        }

        public final Locale currentLanguage() {
            return this.currentLanguage;
        }

        public final boolean equals(Object obj) {
            Locale locale;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Language) {
                Language language = (Language) obj;
                if (this.action$ar$edu$e7b70ea2_0 == language.action$ar$edu$639c1807_0() && ((locale = this.currentLanguage) != null ? locale.equals(language.currentLanguage()) : language.currentLanguage() == null)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$e7b70ea2_0) ^ 1000003;
            Locale locale = this.currentLanguage;
            if (locale == null) {
                hashCode = 0;
            } else {
                hashCode = locale.hashCode();
            }
            return (ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$e7b70ea2_0;
            if (i != 1) {
                if (i != 2) {
                    str = "SET_LANGUAGE";
                } else {
                    str = "NEXT_LANGUAGE";
                }
            } else {
                str = "PREVIOUS_LANGUAGE";
            }
            return "Language{action=" + str + ", currentLanguage=" + String.valueOf(this.currentLanguage) + "}";
        }

        public Language(int i, Locale locale) {
            this();
            this.action$ar$edu$e7b70ea2_0 = i;
            this.currentLanguage = locale;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NavigateTypo {
        public final boolean isNext;

        public NavigateTypo() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof NavigateTypo) && this.isNext == ((NavigateTypo) obj).isNext()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int i;
            if (true != this.isNext) {
                i = 1237;
            } else {
                i = 1231;
            }
            return ((i ^ 1000003) * 1000003) ^ 1231;
        }

        public final boolean isNext() {
            return this.isNext;
        }

        public final String toString() {
            return "NavigateTypo{isNext=" + this.isNext + ", useInputFocusIfEmpty=true}";
        }

        public NavigateTypo(boolean z) {
            this();
            this.isNext = z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NodeAction {
        public final int actionId;
        public final Bundle args;
        public final AccessibilityNode target;

        public NodeAction() {
        }

        public final int actionId() {
            return this.actionId;
        }

        public final Bundle args() {
            return this.args;
        }

        public final boolean equals(Object obj) {
            Bundle bundle;
            if (obj == this) {
                return true;
            }
            if (obj instanceof NodeAction) {
                NodeAction nodeAction = (NodeAction) obj;
                if (this.target.equals(nodeAction.target()) && this.actionId == nodeAction.actionId() && ((bundle = this.args) != null ? bundle.equals(nodeAction.args()) : nodeAction.args() == null)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int hashCode2 = this.target.hashCode() ^ 1000003;
            Bundle bundle = this.args;
            if (bundle == null) {
                hashCode = 0;
            } else {
                hashCode = bundle.hashCode();
            }
            return (((hashCode2 * 1000003) ^ this.actionId) * 1000003) ^ hashCode;
        }

        public final AccessibilityNode target() {
            return this.target;
        }

        public final String toString() {
            return StringBuilderUtils.joinFields(StringBuilderUtils.optionalSubObj("target", target()), StringBuilderUtils.optionalField("actionId", AccessibilityNodeInfoUtils.actionToString(actionId())), StringBuilderUtils.optionalSubObj("args", args()));
        }

        public NodeAction(AccessibilityNode accessibilityNode, int i, Bundle bundle) {
            this();
            this.target = accessibilityNode;
            this.actionId = i;
            this.args = bundle;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Part {
        private final AdjustValue adjustValue;
        private final AdjustVolume adjustVolume;
        private final BrailleDisplay brailleDisplay;
        public final ContinuousRead continuousRead;
        private final int delayMs;
        private final DeviceInfo deviceInfo;
        public final DimScreen dimScreen;
        private final EditText edit;
        private final Focus focus;
        public final FocusDirection focusDirection;
        private final GeminiRequest geminiRequest;
        private final Gesture gesture;
        private final ImageCaption imageCaption;
        private final ImageCaptionResult imageCaptionResult;
        private final boolean interruptGentle;
        private final int interruptGroup;
        private final int interruptLevel;
        private final boolean interruptSoundAndVibration;
        public final Label label;
        private final Language language;
        private final NavigateTypo navigateTypo;
        private final NodeAction nodeAction;
        private final PassThroughMode passThroughMode;
        private final Scroll scroll;
        private final String senderName;
        private final ServiceFlag serviceFlag;
        private final ShowToast showToast;
        private final Sound sound;
        private final Speech speech;
        private final SpeechRate speechRate;
        private final SystemAction systemAction;
        private final TalkBackUI talkBackUI;
        private final TriggerIntent triggerIntent;
        private final UiChange uiChange;
        private final UniversalSearch universalSearch;
        private final Vibration vibration;
        public final VoiceRecognition voiceRecognition;
        private final WebAction webAction;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public AdjustValue adjustValue;
            public AdjustVolume adjustVolume;
            public BrailleDisplay brailleDisplay;
            public ContinuousRead continuousRead;
            private int delayMs;
            public DeviceInfo deviceInfo;
            public DimScreen dimScreen;
            public EditText edit;
            public Focus focus;
            public FocusDirection focusDirection;
            public GeminiRequest geminiRequest;
            public Gesture gesture;
            public ImageCaption imageCaption;
            public ImageCaptionResult imageCaptionResult;
            private boolean interruptGentle;
            private int interruptGroup;
            private int interruptLevel;
            private boolean interruptSoundAndVibration;
            public Label label;
            public Language language;
            public NavigateTypo navigateTypo;
            public NodeAction nodeAction;
            public PassThroughMode passThroughMode;
            public Scroll scroll;
            public String senderName;
            public ServiceFlag serviceFlag;
            public byte set$0;
            public ShowToast showToast;
            public Sound sound;
            public Speech speech;
            public SpeechRate speechRate;
            public SystemAction systemAction;
            public TalkBackUI talkBackUI;
            public TriggerIntent triggerIntent;
            public UiChange uiChange;
            public UniversalSearch universalSearch;
            private Vibration vibration;
            public VoiceRecognition voiceRecognition;
            public WebAction webAction;

            public Builder() {
            }

            public final Part build() {
                if (this.set$0 != Byte.MAX_VALUE) {
                    StringBuilder sb = new StringBuilder();
                    if ((this.set$0 & 1) == 0) {
                        sb.append(" delayMs");
                    }
                    if ((this.set$0 & 2) == 0) {
                        sb.append(" interruptGroup");
                    }
                    if ((this.set$0 & 4) == 0) {
                        sb.append(" interruptLevel");
                    }
                    if ((this.set$0 & 8) == 0) {
                        sb.append(" interruptSoundAndVibration");
                    }
                    if ((this.set$0 & 16) == 0) {
                        sb.append(" interruptAllFeedback");
                    }
                    if ((this.set$0 & 32) == 0) {
                        sb.append(" interruptGentle");
                    }
                    if ((this.set$0 & 64) == 0) {
                        sb.append(" stopTts");
                    }
                    throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
                }
                return new Part(this.delayMs, this.interruptGroup, this.interruptLevel, this.senderName, this.interruptSoundAndVibration, this.interruptGentle, this.label, this.dimScreen, this.speech, this.voiceRecognition, this.continuousRead, this.sound, this.vibration, this.triggerIntent, this.language, this.edit, this.systemAction, this.nodeAction, this.webAction, this.scroll, this.focus, this.focusDirection, this.passThroughMode, this.speechRate, this.adjustValue, this.navigateTypo, this.adjustVolume, this.talkBackUI, this.showToast, this.gesture, this.imageCaption, this.imageCaptionResult, this.deviceInfo, this.uiChange, this.universalSearch, this.geminiRequest, this.serviceFlag, this.brailleDisplay);
            }

            public final void setDelayMs$ar$ds(int i) {
                this.delayMs = i;
                this.set$0 = (byte) (this.set$0 | 1);
            }

            public final void setInterruptGentle$ar$ds(boolean z) {
                this.interruptGentle = z;
                this.set$0 = (byte) (this.set$0 | 32);
            }

            public final void setInterruptGroup$ar$ds(int i) {
                this.interruptGroup = i;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public final void setInterruptLevel$ar$ds(int i) {
                this.interruptLevel = i;
                this.set$0 = (byte) (this.set$0 | 4);
            }

            public final void setInterruptSoundAndVibration$ar$ds(boolean z) {
                this.interruptSoundAndVibration = z;
                this.set$0 = (byte) (this.set$0 | 8);
            }

            public final Builder setSound(Sound sound) {
                this.sound = sound;
                return this;
            }

            public final Builder setSpeech(Speech speech) {
                this.speech = speech;
                return this;
            }

            public final Builder setVibration(Vibration vibration) {
                this.vibration = vibration;
                return this;
            }

            public final Builder sound(int i) {
                return setSound(new Sound(i, 1.0f, 1.0f, 0L));
            }

            public final Builder speech(CharSequence charSequence, SpeechController.SpeakOptions speakOptions) {
                Speech.Builder builder = Speech.builder();
                builder.setAction$ar$ds$c7b78277_0(Speech.Action.SPEAK);
                builder.text = charSequence;
                builder.options = speakOptions;
                return setSpeech(builder.build());
            }

            public final Builder vibration(int i) {
                return setVibration(new Vibration(i));
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public Part() {
        }

        public static Builder builder() {
            Builder builder = new Builder(null);
            builder.setDelayMs$ar$ds(0);
            builder.setInterruptGroup$ar$ds(-1);
            builder.setInterruptLevel$ar$ds(-1);
            builder.setInterruptSoundAndVibration$ar$ds(false);
            builder.set$0 = (byte) (builder.set$0 | 16);
            builder.setInterruptGentle$ar$ds(false);
            builder.set$0 = (byte) (builder.set$0 | 64);
            return builder;
        }

        public final AdjustValue adjustValue() {
            return this.adjustValue;
        }

        public final AdjustVolume adjustVolume() {
            return this.adjustVolume;
        }

        public final BrailleDisplay brailleDisplay() {
            return this.brailleDisplay;
        }

        public final ContinuousRead continuousRead() {
            return this.continuousRead;
        }

        public final int delayMs() {
            return this.delayMs;
        }

        public final DeviceInfo deviceInfo() {
            return this.deviceInfo;
        }

        public final DimScreen dimScreen() {
            return this.dimScreen;
        }

        public final EditText edit() {
            return this.edit;
        }

        public final boolean equals(Object obj) {
            String str;
            Label label;
            DimScreen dimScreen;
            Speech speech;
            VoiceRecognition voiceRecognition;
            ContinuousRead continuousRead;
            Sound sound;
            Vibration vibration;
            TriggerIntent triggerIntent;
            Language language;
            EditText editText;
            SystemAction systemAction;
            NodeAction nodeAction;
            WebAction webAction;
            Scroll scroll;
            Focus focus;
            FocusDirection focusDirection;
            PassThroughMode passThroughMode;
            SpeechRate speechRate;
            AdjustValue adjustValue;
            NavigateTypo navigateTypo;
            AdjustVolume adjustVolume;
            TalkBackUI talkBackUI;
            ShowToast showToast;
            Gesture gesture;
            ImageCaption imageCaption;
            ImageCaptionResult imageCaptionResult;
            DeviceInfo deviceInfo;
            UiChange uiChange;
            UniversalSearch universalSearch;
            GeminiRequest geminiRequest;
            ServiceFlag serviceFlag;
            BrailleDisplay brailleDisplay;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Part) {
                Part part = (Part) obj;
                if (this.delayMs == part.delayMs() && this.interruptGroup == part.interruptGroup() && this.interruptLevel == part.interruptLevel() && ((str = this.senderName) != null ? str.equals(part.senderName()) : part.senderName() == null) && this.interruptSoundAndVibration == part.interruptSoundAndVibration() && this.interruptGentle == part.interruptGentle() && ((label = this.label) != null ? label.equals(part.label()) : part.label() == null) && ((dimScreen = this.dimScreen) != null ? dimScreen.equals(part.dimScreen()) : part.dimScreen() == null) && ((speech = this.speech) != null ? speech.equals(part.speech()) : part.speech() == null) && ((voiceRecognition = this.voiceRecognition) != null ? voiceRecognition.equals(part.voiceRecognition()) : part.voiceRecognition() == null) && ((continuousRead = this.continuousRead) != null ? continuousRead.equals(part.continuousRead()) : part.continuousRead() == null) && ((sound = this.sound) != null ? sound.equals(part.sound()) : part.sound() == null) && ((vibration = this.vibration) != null ? vibration.equals(part.vibration()) : part.vibration() == null) && ((triggerIntent = this.triggerIntent) != null ? triggerIntent.equals(part.triggerIntent()) : part.triggerIntent() == null) && ((language = this.language) != null ? language.equals(part.language()) : part.language() == null) && ((editText = this.edit) != null ? editText.equals(part.edit()) : part.edit() == null) && ((systemAction = this.systemAction) != null ? systemAction.equals(part.systemAction()) : part.systemAction() == null) && ((nodeAction = this.nodeAction) != null ? nodeAction.equals(part.nodeAction()) : part.nodeAction() == null) && ((webAction = this.webAction) != null ? webAction.equals(part.webAction()) : part.webAction() == null) && ((scroll = this.scroll) != null ? scroll.equals(part.scroll()) : part.scroll() == null) && ((focus = this.focus) != null ? focus.equals(part.focus()) : part.focus() == null) && ((focusDirection = this.focusDirection) != null ? focusDirection.equals(part.focusDirection()) : part.focusDirection() == null) && ((passThroughMode = this.passThroughMode) != null ? passThroughMode.equals(part.passThroughMode()) : part.passThroughMode() == null) && ((speechRate = this.speechRate) != null ? speechRate.equals(part.speechRate()) : part.speechRate() == null) && ((adjustValue = this.adjustValue) != null ? adjustValue.equals(part.adjustValue()) : part.adjustValue() == null) && ((navigateTypo = this.navigateTypo) != null ? navigateTypo.equals(part.navigateTypo()) : part.navigateTypo() == null) && ((adjustVolume = this.adjustVolume) != null ? adjustVolume.equals(part.adjustVolume()) : part.adjustVolume() == null) && ((talkBackUI = this.talkBackUI) != null ? talkBackUI.equals(part.talkBackUI()) : part.talkBackUI() == null) && ((showToast = this.showToast) != null ? showToast.equals(part.showToast()) : part.showToast() == null) && ((gesture = this.gesture) != null ? gesture.equals(part.gesture()) : part.gesture() == null) && ((imageCaption = this.imageCaption) != null ? imageCaption.equals(part.imageCaption()) : part.imageCaption() == null) && ((imageCaptionResult = this.imageCaptionResult) != null ? imageCaptionResult.equals(part.imageCaptionResult()) : part.imageCaptionResult() == null) && ((deviceInfo = this.deviceInfo) != null ? deviceInfo.equals(part.deviceInfo()) : part.deviceInfo() == null) && ((uiChange = this.uiChange) != null ? uiChange.equals(part.uiChange()) : part.uiChange() == null) && ((universalSearch = this.universalSearch) != null ? universalSearch.equals(part.universalSearch()) : part.universalSearch() == null) && ((geminiRequest = this.geminiRequest) != null ? geminiRequest.equals(part.geminiRequest()) : part.geminiRequest() == null) && ((serviceFlag = this.serviceFlag) != null ? serviceFlag.equals(part.serviceFlag()) : part.serviceFlag() == null) && ((brailleDisplay = this.brailleDisplay) != null ? brailleDisplay.equals(part.brailleDisplay()) : part.brailleDisplay() == null)) {
                    return true;
                }
            }
            return false;
        }

        public final Focus focus() {
            return this.focus;
        }

        public final FocusDirection focusDirection() {
            return this.focusDirection;
        }

        public final GeminiRequest geminiRequest() {
            return this.geminiRequest;
        }

        public final Gesture gesture() {
            return this.gesture;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int hashCode2;
            int hashCode3;
            int hashCode4;
            int hashCode5;
            int hashCode6;
            int hashCode7;
            int hashCode8;
            int hashCode9;
            int hashCode10;
            int hashCode11;
            int hashCode12;
            int hashCode13;
            int hashCode14;
            int hashCode15;
            int hashCode16;
            int hashCode17;
            int hashCode18;
            int hashCode19;
            int hashCode20;
            int hashCode21;
            int hashCode22;
            int hashCode23;
            int hashCode24;
            int hashCode25;
            int hashCode26;
            int hashCode27;
            int hashCode28;
            int hashCode29;
            int hashCode30;
            int hashCode31;
            int hashCode32;
            String str = this.senderName;
            int i2 = 0;
            if (str == null) {
                hashCode = 0;
            } else {
                hashCode = str.hashCode();
            }
            int i3 = this.delayMs;
            int i4 = this.interruptGroup;
            int i5 = this.interruptLevel;
            int i6 = 1231;
            if (true != this.interruptSoundAndVibration) {
                i = 1237;
            } else {
                i = 1231;
            }
            int i7 = ((((hashCode ^ ((((((i3 ^ 1000003) * 1000003) ^ i4) * 1000003) ^ i5) * 1000003)) * 1000003) ^ i) * 1000003) ^ 1237;
            if (true != this.interruptGentle) {
                i6 = 1237;
            }
            int i8 = ((((i7 * 1000003) ^ i6) * 1000003) ^ 1237) * 1000003;
            Label label = this.label;
            if (label == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = label.hashCode();
            }
            int i9 = (i8 ^ hashCode2) * 1000003;
            DimScreen dimScreen = this.dimScreen;
            if (dimScreen == null) {
                hashCode3 = 0;
            } else {
                hashCode3 = dimScreen.hashCode();
            }
            int i10 = (i9 ^ hashCode3) * 1000003;
            Speech speech = this.speech;
            if (speech == null) {
                hashCode4 = 0;
            } else {
                hashCode4 = speech.hashCode();
            }
            int i11 = (i10 ^ hashCode4) * 1000003;
            VoiceRecognition voiceRecognition = this.voiceRecognition;
            if (voiceRecognition == null) {
                hashCode5 = 0;
            } else {
                hashCode5 = voiceRecognition.hashCode();
            }
            int i12 = (i11 ^ hashCode5) * 1000003;
            ContinuousRead continuousRead = this.continuousRead;
            if (continuousRead == null) {
                hashCode6 = 0;
            } else {
                hashCode6 = continuousRead.hashCode();
            }
            int i13 = (i12 ^ hashCode6) * 1000003;
            Sound sound = this.sound;
            if (sound == null) {
                hashCode7 = 0;
            } else {
                hashCode7 = sound.hashCode();
            }
            int i14 = (i13 ^ hashCode7) * 1000003;
            Vibration vibration = this.vibration;
            if (vibration == null) {
                hashCode8 = 0;
            } else {
                hashCode8 = vibration.hashCode();
            }
            int i15 = (i14 ^ hashCode8) * 1000003;
            TriggerIntent triggerIntent = this.triggerIntent;
            if (triggerIntent == null) {
                hashCode9 = 0;
            } else {
                hashCode9 = triggerIntent.hashCode();
            }
            int i16 = (i15 ^ hashCode9) * 1000003;
            Language language = this.language;
            if (language == null) {
                hashCode10 = 0;
            } else {
                hashCode10 = language.hashCode();
            }
            int i17 = (i16 ^ hashCode10) * 1000003;
            EditText editText = this.edit;
            if (editText == null) {
                hashCode11 = 0;
            } else {
                hashCode11 = editText.hashCode();
            }
            int i18 = (i17 ^ hashCode11) * 1000003;
            SystemAction systemAction = this.systemAction;
            if (systemAction == null) {
                hashCode12 = 0;
            } else {
                hashCode12 = systemAction.hashCode();
            }
            int i19 = (i18 ^ hashCode12) * 1000003;
            NodeAction nodeAction = this.nodeAction;
            if (nodeAction == null) {
                hashCode13 = 0;
            } else {
                hashCode13 = nodeAction.hashCode();
            }
            int i20 = (i19 ^ hashCode13) * 1000003;
            WebAction webAction = this.webAction;
            if (webAction == null) {
                hashCode14 = 0;
            } else {
                hashCode14 = webAction.hashCode();
            }
            int i21 = (i20 ^ hashCode14) * 1000003;
            Scroll scroll = this.scroll;
            if (scroll == null) {
                hashCode15 = 0;
            } else {
                hashCode15 = scroll.hashCode();
            }
            int i22 = (i21 ^ hashCode15) * 1000003;
            Focus focus = this.focus;
            if (focus == null) {
                hashCode16 = 0;
            } else {
                hashCode16 = focus.hashCode();
            }
            int i23 = (i22 ^ hashCode16) * 1000003;
            FocusDirection focusDirection = this.focusDirection;
            if (focusDirection == null) {
                hashCode17 = 0;
            } else {
                hashCode17 = focusDirection.hashCode();
            }
            int i24 = (i23 ^ hashCode17) * 1000003;
            PassThroughMode passThroughMode = this.passThroughMode;
            if (passThroughMode == null) {
                hashCode18 = 0;
            } else {
                hashCode18 = passThroughMode.hashCode();
            }
            int i25 = (i24 ^ hashCode18) * 1000003;
            SpeechRate speechRate = this.speechRate;
            if (speechRate == null) {
                hashCode19 = 0;
            } else {
                hashCode19 = speechRate.hashCode();
            }
            int i26 = (i25 ^ hashCode19) * 1000003;
            AdjustValue adjustValue = this.adjustValue;
            if (adjustValue == null) {
                hashCode20 = 0;
            } else {
                hashCode20 = adjustValue.hashCode();
            }
            int i27 = (i26 ^ hashCode20) * 1000003;
            NavigateTypo navigateTypo = this.navigateTypo;
            if (navigateTypo == null) {
                hashCode21 = 0;
            } else {
                hashCode21 = navigateTypo.hashCode();
            }
            int i28 = (i27 ^ hashCode21) * 1000003;
            AdjustVolume adjustVolume = this.adjustVolume;
            if (adjustVolume == null) {
                hashCode22 = 0;
            } else {
                hashCode22 = adjustVolume.hashCode();
            }
            int i29 = (i28 ^ hashCode22) * 1000003;
            TalkBackUI talkBackUI = this.talkBackUI;
            if (talkBackUI == null) {
                hashCode23 = 0;
            } else {
                hashCode23 = talkBackUI.hashCode();
            }
            int i30 = (i29 ^ hashCode23) * 1000003;
            ShowToast showToast = this.showToast;
            if (showToast == null) {
                hashCode24 = 0;
            } else {
                hashCode24 = showToast.hashCode();
            }
            int i31 = (i30 ^ hashCode24) * 1000003;
            Gesture gesture = this.gesture;
            if (gesture == null) {
                hashCode25 = 0;
            } else {
                hashCode25 = gesture.hashCode();
            }
            int i32 = (i31 ^ hashCode25) * 1000003;
            ImageCaption imageCaption = this.imageCaption;
            if (imageCaption == null) {
                hashCode26 = 0;
            } else {
                hashCode26 = imageCaption.hashCode();
            }
            int i33 = (i32 ^ hashCode26) * 1000003;
            ImageCaptionResult imageCaptionResult = this.imageCaptionResult;
            if (imageCaptionResult == null) {
                hashCode27 = 0;
            } else {
                hashCode27 = imageCaptionResult.hashCode();
            }
            int i34 = (i33 ^ hashCode27) * 1000003;
            DeviceInfo deviceInfo = this.deviceInfo;
            if (deviceInfo == null) {
                hashCode28 = 0;
            } else {
                hashCode28 = deviceInfo.hashCode();
            }
            int i35 = (i34 ^ hashCode28) * 1000003;
            UiChange uiChange = this.uiChange;
            if (uiChange == null) {
                hashCode29 = 0;
            } else {
                hashCode29 = uiChange.hashCode();
            }
            int i36 = (i35 ^ hashCode29) * 1000003;
            UniversalSearch universalSearch = this.universalSearch;
            if (universalSearch == null) {
                hashCode30 = 0;
            } else {
                hashCode30 = universalSearch.hashCode();
            }
            int i37 = (i36 ^ hashCode30) * 1000003;
            GeminiRequest geminiRequest = this.geminiRequest;
            if (geminiRequest == null) {
                hashCode31 = 0;
            } else {
                hashCode31 = geminiRequest.hashCode();
            }
            int i38 = (i37 ^ hashCode31) * 1000003;
            ServiceFlag serviceFlag = this.serviceFlag;
            if (serviceFlag == null) {
                hashCode32 = 0;
            } else {
                hashCode32 = serviceFlag.hashCode();
            }
            int i39 = (i38 ^ hashCode32) * 1000003;
            BrailleDisplay brailleDisplay = this.brailleDisplay;
            if (brailleDisplay != null) {
                i2 = brailleDisplay.hashCode();
            }
            return i39 ^ i2;
        }

        public final ImageCaption imageCaption() {
            return this.imageCaption;
        }

        public final ImageCaptionResult imageCaptionResult() {
            return this.imageCaptionResult;
        }

        public final boolean interruptGentle() {
            return this.interruptGentle;
        }

        public final int interruptGroup() {
            return this.interruptGroup;
        }

        public final int interruptLevel() {
            return this.interruptLevel;
        }

        public final boolean interruptSoundAndVibration() {
            return this.interruptSoundAndVibration;
        }

        public final Label label() {
            return this.label;
        }

        public final Language language() {
            return this.language;
        }

        public final NavigateTypo navigateTypo() {
            return this.navigateTypo;
        }

        public final NodeAction nodeAction() {
            return this.nodeAction;
        }

        public final PassThroughMode passThroughMode() {
            return this.passThroughMode;
        }

        public final Scroll scroll() {
            return this.scroll;
        }

        public final String senderName() {
            return this.senderName;
        }

        public final ServiceFlag serviceFlag() {
            return this.serviceFlag;
        }

        public final ShowToast showToast() {
            return this.showToast;
        }

        public final Sound sound() {
            return this.sound;
        }

        public final Speech speech() {
            return this.speech;
        }

        public final SpeechRate speechRate() {
            return this.speechRate;
        }

        public final SystemAction systemAction() {
            return this.systemAction;
        }

        public final TalkBackUI talkBackUI() {
            return this.talkBackUI;
        }

        public final String toString() {
            return "Part= ".concat(String.valueOf(StringBuilderUtils.joinFields(StringBuilderUtils.optionalInt("delayMs", delayMs(), 0), StringBuilderUtils.optionalInt("interruptGroup", interruptGroup(), -1), StringBuilderUtils.optionalInt("interruptLevel", interruptLevel(), -1), StringBuilderUtils.optionalText("senderName", senderName()), StringBuilderUtils.optionalTag("interruptSoundAndVibration", interruptSoundAndVibration()), StringBuilderUtils.optionalTag("interruptAllFeedback", false), StringBuilderUtils.optionalTag("interruptGentle", interruptGentle()), StringBuilderUtils.optionalTag("stopTts", false), StringBuilderUtils.optionalSubObj("label", label()), StringBuilderUtils.optionalSubObj("dimScreen", dimScreen()), StringBuilderUtils.optionalSubObj("speech", speech()), StringBuilderUtils.optionalSubObj("voiceRecognition", voiceRecognition()), StringBuilderUtils.optionalSubObj("continuousRead", continuousRead()), StringBuilderUtils.optionalSubObj("sound", sound()), StringBuilderUtils.optionalSubObj("vibration", vibration()), StringBuilderUtils.optionalSubObj("triggerIntent", triggerIntent()), StringBuilderUtils.optionalSubObj("language", language()), StringBuilderUtils.optionalSubObj("edit", edit()), StringBuilderUtils.optionalSubObj("systemAction", systemAction()), StringBuilderUtils.optionalSubObj("nodeAction", nodeAction()), StringBuilderUtils.optionalSubObj("webAction", webAction()), StringBuilderUtils.optionalSubObj("scroll", scroll()), StringBuilderUtils.optionalSubObj("focus", focus()), StringBuilderUtils.optionalSubObj("focusDirection", focusDirection()), StringBuilderUtils.optionalSubObj("passThroughMode", passThroughMode()), StringBuilderUtils.optionalSubObj("talkBackUI", talkBackUI()), StringBuilderUtils.optionalSubObj("showToast", showToast()), StringBuilderUtils.optionalSubObj("gesture", gesture()), StringBuilderUtils.optionalSubObj("imageCaption", imageCaption()), StringBuilderUtils.optionalSubObj("imageCaptionResult", imageCaptionResult()), StringBuilderUtils.optionalSubObj("deviceInfo", deviceInfo()), StringBuilderUtils.optionalSubObj("uiChange", uiChange()), StringBuilderUtils.optionalSubObj("speechRate", speechRate()), StringBuilderUtils.optionalSubObj("adjustValue", adjustValue()), StringBuilderUtils.optionalSubObj("navigateTypo", navigateTypo()), StringBuilderUtils.optionalSubObj("adjustVolume", adjustVolume()), StringBuilderUtils.optionalSubObj("universalSearch", universalSearch()), StringBuilderUtils.optionalSubObj("geminiRequest", geminiRequest()), StringBuilderUtils.optionalSubObj("serviceFlag", serviceFlag()), StringBuilderUtils.optionalSubObj("brailleDisplay", brailleDisplay()))));
        }

        public final TriggerIntent triggerIntent() {
            return this.triggerIntent;
        }

        public final UiChange uiChange() {
            return this.uiChange;
        }

        public final UniversalSearch universalSearch() {
            return this.universalSearch;
        }

        public final Vibration vibration() {
            return this.vibration;
        }

        public final VoiceRecognition voiceRecognition() {
            return this.voiceRecognition;
        }

        public final WebAction webAction() {
            return this.webAction;
        }

        public Part(int i, int i2, int i3, String str, boolean z, boolean z2, Label label, DimScreen dimScreen, Speech speech, VoiceRecognition voiceRecognition, ContinuousRead continuousRead, Sound sound, Vibration vibration, TriggerIntent triggerIntent, Language language, EditText editText, SystemAction systemAction, NodeAction nodeAction, WebAction webAction, Scroll scroll, Focus focus, FocusDirection focusDirection, PassThroughMode passThroughMode, SpeechRate speechRate, AdjustValue adjustValue, NavigateTypo navigateTypo, AdjustVolume adjustVolume, TalkBackUI talkBackUI, ShowToast showToast, Gesture gesture, ImageCaption imageCaption, ImageCaptionResult imageCaptionResult, DeviceInfo deviceInfo, UiChange uiChange, UniversalSearch universalSearch, GeminiRequest geminiRequest, ServiceFlag serviceFlag, BrailleDisplay brailleDisplay) {
            this();
            this.delayMs = i;
            this.interruptGroup = i2;
            this.interruptLevel = i3;
            this.senderName = str;
            this.interruptSoundAndVibration = z;
            this.interruptGentle = z2;
            this.label = label;
            this.dimScreen = dimScreen;
            this.speech = speech;
            this.voiceRecognition = voiceRecognition;
            this.continuousRead = continuousRead;
            this.sound = sound;
            this.vibration = vibration;
            this.triggerIntent = triggerIntent;
            this.language = language;
            this.edit = editText;
            this.systemAction = systemAction;
            this.nodeAction = nodeAction;
            this.webAction = webAction;
            this.scroll = scroll;
            this.focus = focus;
            this.focusDirection = focusDirection;
            this.passThroughMode = passThroughMode;
            this.speechRate = speechRate;
            this.adjustValue = adjustValue;
            this.navigateTypo = navigateTypo;
            this.adjustVolume = adjustVolume;
            this.talkBackUI = talkBackUI;
            this.showToast = showToast;
            this.gesture = gesture;
            this.imageCaption = imageCaption;
            this.imageCaptionResult = imageCaptionResult;
            this.deviceInfo = deviceInfo;
            this.uiChange = uiChange;
            this.universalSearch = universalSearch;
            this.geminiRequest = geminiRequest;
            this.serviceFlag = serviceFlag;
            this.brailleDisplay = brailleDisplay;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PassThroughMode {
        public final int action$ar$edu$5c81fd87_0;
        public final Region region;

        public PassThroughMode() {
        }

        public final int action$ar$edu$fc9f48bb_0() {
            return this.action$ar$edu$5c81fd87_0;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof PassThroughMode) {
                PassThroughMode passThroughMode = (PassThroughMode) obj;
                if (this.action$ar$edu$5c81fd87_0 == passThroughMode.action$ar$edu$fc9f48bb_0() && this.region.equals(passThroughMode.region())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$5c81fd87_0) ^ 1000003) * 1000003) ^ this.region.hashCode();
        }

        public final Region region() {
            return this.region;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$5c81fd87_0;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        str = "STOP_TIMER";
                    } else {
                        str = "PASSTHROUGH_CONFIRM_DIALOG";
                    }
                } else {
                    str = "ENABLE_PASSTHROUGH";
                }
            } else {
                str = "DISABLE_PASSTHROUGH";
            }
            return "PassThroughMode{action=" + str + ", region=" + this.region.toString() + "}";
        }

        public PassThroughMode(int i, Region region) {
            this();
            this.action$ar$edu$5c81fd87_0 = i;
            this.region = region;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Scroll {
        public final int action$ar$edu$f8733675_0;
        public final Integer autoScrollAttempt;
        public final AccessibilityNode node;
        public final int nodeAction;
        public final AccessibilityNodeInfoCompat nodeCompat;
        public final AccessibilityNodeInfoCompat nodeToMoveOnScreen;
        public final String source;
        public final int timeout$ar$edu;
        public final int userAction;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            private int action$ar$edu$f8733675_0;
            public Integer autoScrollAttempt;
            public AccessibilityNode node;
            private int nodeAction;
            public AccessibilityNodeInfoCompat nodeCompat;
            public AccessibilityNodeInfoCompat nodeToMoveOnScreen;
            private byte set$0;
            public String source;
            private int timeout$ar$edu;
            private int userAction;

            public Builder() {
            }

            public final Scroll build() {
                int i;
                int i2;
                if (this.set$0 == 3 && (i = this.action$ar$edu$f8733675_0) != 0 && (i2 = this.timeout$ar$edu) != 0) {
                    return new Scroll(i, this.node, this.nodeCompat, this.nodeToMoveOnScreen, this.userAction, this.nodeAction, this.source, i2, this.autoScrollAttempt);
                }
                StringBuilder sb = new StringBuilder();
                if (this.action$ar$edu$f8733675_0 == 0) {
                    sb.append(" action");
                }
                if ((this.set$0 & 1) == 0) {
                    sb.append(" userAction");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" nodeAction");
                }
                if (this.timeout$ar$edu == 0) {
                    sb.append(" timeout");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }

            public final void setAction$ar$edu$e02d76b2_0$ar$ds(int i) {
                this.action$ar$edu$f8733675_0 = i;
            }

            public final void setNodeAction$ar$ds(int i) {
                this.nodeAction = i;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public final void setTimeout$ar$edu$ar$ds(int i) {
                this.timeout$ar$edu = i;
            }

            public final void setUserAction$ar$ds(int i) {
                this.userAction = i;
                this.set$0 = (byte) (this.set$0 | 1);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public Scroll() {
        }

        public static Builder builder() {
            Builder builder = new Builder(null);
            builder.setTimeout$ar$edu$ar$ds(500);
            return builder;
        }

        public final int action$ar$edu$574f6da0_0() {
            return this.action$ar$edu$f8733675_0;
        }

        public final Integer autoScrollAttempt() {
            return this.autoScrollAttempt;
        }

        public final boolean equals(Object obj) {
            AccessibilityNode accessibilityNode;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2;
            String str;
            Integer num;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Scroll)) {
                return false;
            }
            Scroll scroll = (Scroll) obj;
            int i = this.action$ar$edu$f8733675_0;
            int action$ar$edu$574f6da0_0 = scroll.action$ar$edu$574f6da0_0();
            if (i != 0) {
                if (i == action$ar$edu$574f6da0_0 && ((accessibilityNode = this.node) != null ? accessibilityNode.equals(scroll.node()) : scroll.node() == null) && ((accessibilityNodeInfoCompat = this.nodeCompat) != null ? accessibilityNodeInfoCompat.equals(scroll.nodeCompat()) : scroll.nodeCompat() == null) && ((accessibilityNodeInfoCompat2 = this.nodeToMoveOnScreen) != null ? accessibilityNodeInfoCompat2.equals(scroll.nodeToMoveOnScreen()) : scroll.nodeToMoveOnScreen() == null) && this.userAction == scroll.userAction() && this.nodeAction == scroll.nodeAction() && ((str = this.source) != null ? str.equals(scroll.source()) : scroll.source() == null)) {
                    int i2 = this.timeout$ar$edu;
                    int timeout$ar$edu$d53465a2_0 = scroll.timeout$ar$edu$d53465a2_0();
                    if (i2 != 0) {
                        if (i2 == timeout$ar$edu$d53465a2_0 && ((num = this.autoScrollAttempt) != null ? num.equals(scroll.autoScrollAttempt()) : scroll.autoScrollAttempt() == null)) {
                            return true;
                        }
                    } else {
                        throw null;
                    }
                }
                return false;
            }
            throw null;
        }

        public final int hashCode() {
            int hashCode;
            int hashCode2;
            int hashCode3;
            int hashCode4;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$f8733675_0) ^ 1000003;
            AccessibilityNode accessibilityNode = this.node;
            int i = 0;
            if (accessibilityNode == null) {
                hashCode = 0;
            } else {
                hashCode = accessibilityNode.hashCode();
            }
            int i2 = ((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode) * 1000003;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.nodeCompat;
            if (accessibilityNodeInfoCompat == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = accessibilityNodeInfoCompat.hashCode();
            }
            int i3 = (i2 ^ hashCode2) * 1000003;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = this.nodeToMoveOnScreen;
            if (accessibilityNodeInfoCompat2 == null) {
                hashCode3 = 0;
            } else {
                hashCode3 = accessibilityNodeInfoCompat2.hashCode();
            }
            int i4 = (((((i3 ^ hashCode3) * 1000003) ^ this.userAction) * 1000003) ^ this.nodeAction) * 1000003;
            String str = this.source;
            if (str == null) {
                hashCode4 = 0;
            } else {
                hashCode4 = str.hashCode();
            }
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_02 = (((i4 ^ hashCode4) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.timeout$ar$edu)) * 1000003;
            Integer num = this.autoScrollAttempt;
            if (num != null) {
                i = num.hashCode();
            }
            return ArtificialStackFrames$ar$MethodMerging$dc56d17a_02 ^ i;
        }

        public final AccessibilityNode node() {
            return this.node;
        }

        public final int nodeAction() {
            return this.nodeAction;
        }

        public final AccessibilityNodeInfoCompat nodeCompat() {
            return this.nodeCompat;
        }

        public final AccessibilityNodeInfoCompat nodeToMoveOnScreen() {
            return this.nodeToMoveOnScreen;
        }

        public final String source() {
            return this.source;
        }

        public final int timeout$ar$edu$d53465a2_0() {
            return this.timeout$ar$edu;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$f8733675_0;
            String str2 = "null";
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        str = "null";
                    } else {
                        str = "ENSURE_ON_SCREEN";
                    }
                } else {
                    str = "CANCEL_TIMEOUT";
                }
            } else {
                str = "SCROLL";
            }
            AccessibilityNode accessibilityNode = this.node;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.nodeCompat;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = this.nodeToMoveOnScreen;
            int i2 = this.userAction;
            int i3 = this.nodeAction;
            String str3 = this.source;
            int i4 = this.timeout$ar$edu;
            String valueOf = String.valueOf(accessibilityNode);
            String valueOf2 = String.valueOf(accessibilityNodeInfoCompat);
            String valueOf3 = String.valueOf(accessibilityNodeInfoCompat2);
            if (i4 != 500) {
                if (i4 == 1000) {
                    str2 = "SCROLL_TIMEOUT_LONG";
                }
            } else {
                str2 = "SCROLL_TIMEOUT_SHORT";
            }
            return "Scroll{action=" + str + ", node=" + valueOf + ", nodeCompat=" + valueOf2 + ", nodeToMoveOnScreen=" + valueOf3 + ", userAction=" + i2 + ", nodeAction=" + i3 + ", source=" + str3 + ", timeout=" + str2 + ", autoScrollAttempt=" + this.autoScrollAttempt + "}";
        }

        public final int userAction() {
            return this.userAction;
        }

        public Scroll(int i, AccessibilityNode accessibilityNode, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2, int i2, int i3, String str, int i4, Integer num) {
            this();
            this.action$ar$edu$f8733675_0 = i;
            this.node = accessibilityNode;
            this.nodeCompat = accessibilityNodeInfoCompat;
            this.nodeToMoveOnScreen = accessibilityNodeInfoCompat2;
            this.userAction = i2;
            this.nodeAction = i3;
            this.source = str;
            this.timeout$ar$edu = i4;
            this.autoScrollAttempt = num;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ServiceFlag {
        public final int action$ar$edu$65c49571_0;

        public ServiceFlag() {
        }

        public final int action$ar$edu$fa0479e2_0() {
            return this.action$ar$edu$65c49571_0;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof ServiceFlag) && this.action$ar$edu$65c49571_0 == ((ServiceFlag) obj).action$ar$edu$fa0479e2_0()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return ((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$65c49571_0) ^ 1000003) * 1000003) ^ 2048;
        }

        public final String toString() {
            String str;
            if (this.action$ar$edu$65c49571_0 != 1) {
                str = "DISABLE_FLAG";
            } else {
                str = "ENABLE_FLAG";
            }
            return "ServiceFlag{action=" + str + ", flag=2048}";
        }

        public ServiceFlag(int i) {
            this();
            this.action$ar$edu$65c49571_0 = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ShowToast {
        private final int action$ar$edu$b3683e21_0;
        public final boolean durationIsLong;
        public final CharSequence message;

        public ShowToast() {
        }

        public final boolean durationIsLong() {
            return this.durationIsLong;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ShowToast) {
                ShowToast showToast = (ShowToast) obj;
                CharSequence charSequence = this.message;
                if (charSequence != null ? charSequence.equals(showToast.message()) : showToast.message() == null) {
                    if (this.durationIsLong == showToast.durationIsLong()) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(1) ^ 1000003;
            CharSequence charSequence = this.message;
            if (charSequence == null) {
                hashCode = 0;
            } else {
                hashCode = charSequence.hashCode();
            }
            int i2 = ((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode) * 1000003;
            if (true != this.durationIsLong) {
                i = 1237;
            } else {
                i = 1231;
            }
            return i ^ i2;
        }

        public final CharSequence message() {
            return this.message;
        }

        public final String toString() {
            return "ShowToast{action=SHOW, message=" + String.valueOf(this.message) + ", durationIsLong=" + this.durationIsLong + "}";
        }

        public ShowToast(int i, CharSequence charSequence, boolean z) {
            this();
            this.action$ar$edu$b3683e21_0 = 1;
            this.message = charSequence;
            this.durationIsLong = z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Sound {
        public final float rate;
        public final int resourceId;
        public final long separationMillisec;
        public final float volume;

        public Sound() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Sound) {
                Sound sound = (Sound) obj;
                if (this.resourceId == sound.resourceId() && Float.floatToIntBits(this.rate) == Float.floatToIntBits(sound.rate()) && Float.floatToIntBits(this.volume) == Float.floatToIntBits(sound.volume()) && this.separationMillisec == sound.separationMillisec()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((((((((this.resourceId ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.rate)) * 1000003) ^ Float.floatToIntBits(this.volume)) * 1000003) ^ 1237) * 1000003) ^ ((int) this.separationMillisec);
        }

        public final float rate() {
            return this.rate;
        }

        public final int resourceId() {
            return this.resourceId;
        }

        public final long separationMillisec() {
            return this.separationMillisec;
        }

        public final String toString() {
            return "Sound{resourceId=" + this.resourceId + ", rate=" + this.rate + ", volume=" + this.volume + ", ignoreVolumeAdjustment=false, separationMillisec=" + this.separationMillisec + "}";
        }

        public final float volume() {
            return this.volume;
        }

        public Sound(int i, float f, float f2, long j) {
            this();
            this.resourceId = i;
            this.rate = f;
            this.volume = f2;
            this.separationMillisec = j;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Speech {
        public final Action action;
        public final CharSequence hint;
        public final int hintInterruptLevel;
        public final SpeechController.SpeakOptions hintSpeakOptions;
        public final SpeechController.SpeakOptions options;
        public final CharSequence text;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public enum Action {
            SPEAK,
            SAVE_LAST,
            COPY_SAVED,
            COPY_LAST,
            REPEAT_SAVED,
            SPELL_SAVED,
            PAUSE_OR_RESUME,
            TOGGLE_VOICE_FEEDBACK,
            SILENCE,
            UNSILENCE,
            INVALIDATE_FREQUENT_CONTENT_CHANGE_CACHE
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            private Action action;
            public CharSequence hint;
            private int hintInterruptLevel;
            public SpeechController.SpeakOptions hintSpeakOptions;
            public SpeechController.SpeakOptions options;
            public byte set$0;
            public CharSequence text;

            public Builder() {
            }

            public final Speech build() {
                Action action;
                if (this.set$0 == 3 && (action = this.action) != null) {
                    return new Speech(action, this.text, this.options, this.hint, this.hintSpeakOptions, this.hintInterruptLevel);
                }
                StringBuilder sb = new StringBuilder();
                if (this.action == null) {
                    sb.append(" action");
                }
                if ((this.set$0 & 1) == 0) {
                    sb.append(" hintInterruptGroup");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" hintInterruptLevel");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }

            public final void setAction$ar$ds$c7b78277_0(Action action) {
                if (action != null) {
                    this.action = action;
                    return;
                }
                throw new NullPointerException("Null action");
            }

            public final void setHintInterruptLevel$ar$ds(int i) {
                this.hintInterruptLevel = i;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public Speech() {
        }

        public static Builder builder() {
            Builder builder = new Builder(null);
            builder.set$0 = (byte) (builder.set$0 | 1);
            builder.setHintInterruptLevel$ar$ds(1);
            return builder;
        }

        public static Speech create(Action action) {
            Builder builder = builder();
            builder.setAction$ar$ds$c7b78277_0(action);
            return builder.build();
        }

        public final Action action() {
            return this.action;
        }

        public final boolean equals(Object obj) {
            CharSequence charSequence;
            SpeechController.SpeakOptions speakOptions;
            CharSequence charSequence2;
            SpeechController.SpeakOptions speakOptions2;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Speech) {
                Speech speech = (Speech) obj;
                if (this.action.equals(speech.action()) && ((charSequence = this.text) != null ? charSequence.equals(speech.text()) : speech.text() == null) && ((speakOptions = this.options) != null ? speakOptions.equals(speech.options()) : speech.options() == null) && ((charSequence2 = this.hint) != null ? charSequence2.equals(speech.hint()) : speech.hint() == null) && ((speakOptions2 = this.hintSpeakOptions) != null ? speakOptions2.equals(speech.hintSpeakOptions()) : speech.hintSpeakOptions() == null) && this.hintInterruptLevel == speech.hintInterruptLevel()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int hashCode2;
            int hashCode3;
            int hashCode4 = this.action.hashCode() ^ 1000003;
            CharSequence charSequence = this.text;
            int i = 0;
            if (charSequence == null) {
                hashCode = 0;
            } else {
                hashCode = charSequence.hashCode();
            }
            int i2 = ((hashCode4 * 1000003) ^ hashCode) * 1000003;
            SpeechController.SpeakOptions speakOptions = this.options;
            if (speakOptions == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = speakOptions.hashCode();
            }
            int i3 = (i2 ^ hashCode2) * 1000003;
            CharSequence charSequence2 = this.hint;
            if (charSequence2 == null) {
                hashCode3 = 0;
            } else {
                hashCode3 = charSequence2.hashCode();
            }
            int i4 = (i3 ^ hashCode3) * 1000003;
            SpeechController.SpeakOptions speakOptions2 = this.hintSpeakOptions;
            if (speakOptions2 != null) {
                i = speakOptions2.hashCode();
            }
            return ((i4 ^ i) * (-721379959)) ^ this.hintInterruptLevel;
        }

        public final CharSequence hint() {
            return this.hint;
        }

        public final int hintInterruptLevel() {
            return this.hintInterruptLevel;
        }

        public final SpeechController.SpeakOptions hintSpeakOptions() {
            return this.hintSpeakOptions;
        }

        public final SpeechController.SpeakOptions options() {
            return this.options;
        }

        public final CharSequence text() {
            return this.text;
        }

        public final String toString() {
            CharSequence charSequence;
            String optionalField = StringBuilderUtils.optionalField("action", action());
            if (FeatureSupport.logcatIncludePsi()) {
                charSequence = text();
            } else if (TextUtils.isEmpty(text())) {
                charSequence = null;
            } else {
                charSequence = "***";
            }
            return StringBuilderUtils.joinFields(optionalField, StringBuilderUtils.optionalText("text", charSequence), StringBuilderUtils.optionalSubObj("options", options()), String.format("%s= %s", "hint", hint()), String.format("%s= %s", "hintSpeakOptions", hintSpeakOptions()));
        }

        public Speech(Action action, CharSequence charSequence, SpeechController.SpeakOptions speakOptions, CharSequence charSequence2, SpeechController.SpeakOptions speakOptions2, int i) {
            this();
            this.action = action;
            this.text = charSequence;
            this.options = speakOptions;
            this.hint = charSequence2;
            this.hintSpeakOptions = speakOptions2;
            this.hintInterruptLevel = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SpeechRate {
        public final int action$ar$edu$3e00bb81_0;

        public SpeechRate() {
        }

        public final int action$ar$edu$94d9911f_0() {
            return this.action$ar$edu$3e00bb81_0;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof SpeechRate) && this.action$ar$edu$3e00bb81_0 == ((SpeechRate) obj).action$ar$edu$94d9911f_0()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$3e00bb81_0) ^ 1000003;
        }

        public final String toString() {
            String str;
            if (this.action$ar$edu$3e00bb81_0 != 1) {
                str = "DECREASE_RATE";
            } else {
                str = "INCREASE_RATE";
            }
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "SpeechRate{action=", "}");
        }

        public SpeechRate(int i) {
            this();
            this.action$ar$edu$3e00bb81_0 = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SystemAction {
        public final int systemActionId;

        public SystemAction() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof SystemAction) && this.systemActionId == ((SystemAction) obj).systemActionId()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return this.systemActionId ^ 1000003;
        }

        public final int systemActionId() {
            return this.systemActionId;
        }

        public final String toString() {
            return "SystemAction{systemActionId=" + this.systemActionId + "}";
        }

        public SystemAction(int i) {
            this();
            this.systemActionId = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TalkBackUI {
        public final int action$ar$edu$5c2d8aaf_0;
        public final CharSequence message;
        public final boolean showIcon;
        public final TalkBackUIActor$Type type;

        public TalkBackUI() {
        }

        public final int action$ar$edu$21d9560a_0() {
            return this.action$ar$edu$5c2d8aaf_0;
        }

        public final boolean equals(Object obj) {
            CharSequence charSequence;
            if (obj == this) {
                return true;
            }
            if (obj instanceof TalkBackUI) {
                TalkBackUI talkBackUI = (TalkBackUI) obj;
                if (this.action$ar$edu$5c2d8aaf_0 == talkBackUI.action$ar$edu$21d9560a_0() && this.type.equals(talkBackUI.type()) && ((charSequence = this.message) != null ? charSequence.equals(talkBackUI.message()) : talkBackUI.message() == null) && this.showIcon == talkBackUI.showIcon()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = ((_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$5c2d8aaf_0) ^ 1000003) * 1000003) ^ this.type.hashCode();
            CharSequence charSequence = this.message;
            if (charSequence == null) {
                hashCode = 0;
            } else {
                hashCode = charSequence.hashCode();
            }
            int i2 = ((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode) * 1000003;
            if (true != this.showIcon) {
                i = 1237;
            } else {
                i = 1231;
            }
            return i2 ^ i;
        }

        public final CharSequence message() {
            return this.message;
        }

        public final boolean showIcon() {
            return this.showIcon;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$5c2d8aaf_0;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            str = "NOT_SUPPORT";
                        } else {
                            str = "SUPPORT";
                        }
                    } else {
                        str = "HIDE";
                    }
                } else {
                    str = "SHOW_GESTURE_ACTION_UI";
                }
            } else {
                str = "SHOW_SELECTOR_UI";
            }
            TalkBackUIActor$Type talkBackUIActor$Type = this.type;
            CharSequence charSequence = this.message;
            boolean z = this.showIcon;
            return "TalkBackUI{action=" + str + ", type=" + talkBackUIActor$Type.toString() + ", message=" + String.valueOf(charSequence) + ", showIcon=" + z + "}";
        }

        public final TalkBackUIActor$Type type() {
            return this.type;
        }

        public TalkBackUI(int i, TalkBackUIActor$Type talkBackUIActor$Type, CharSequence charSequence, boolean z) {
            this();
            this.action$ar$edu$5c2d8aaf_0 = i;
            if (talkBackUIActor$Type == null) {
                throw new NullPointerException("Null type");
            }
            this.type = talkBackUIActor$Type;
            this.message = charSequence;
            this.showIcon = z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TriggerIntent {
        public final int action$ar$edu$818c351a_0;

        public TriggerIntent() {
        }

        public final int action$ar$edu$3e5d56cf_0() {
            return this.action$ar$edu$818c351a_0;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof TriggerIntent) && this.action$ar$edu$818c351a_0 == ((TriggerIntent) obj).action$ar$edu$3e5d56cf_0()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$818c351a_0) ^ 1000003;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$818c351a_0;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            str = "TRIGGER_IMAGE_DESCRIPTIONS_SETTINGS";
                        } else {
                            str = "TRIGGER_BRAILLE_DISPLAY_SETTINGS";
                        }
                    } else {
                        str = "TRIGGER_ASSISTANT";
                    }
                } else {
                    str = "TRIGGER_PRACTICE_GESTURE";
                }
            } else {
                str = "TRIGGER_TUTORIAL";
            }
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "TriggerIntent{action=", "}");
        }

        public TriggerIntent(int i) {
            this();
            this.action$ar$edu$818c351a_0 = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UiChange {
        public final int action$ar$edu$483e6edc_0;
        public final Rect sourceBoundsInScreen;

        public UiChange() {
        }

        public final int action$ar$edu$ebf9cfe8_0() {
            return this.action$ar$edu$483e6edc_0;
        }

        public final boolean equals(Object obj) {
            Rect rect;
            if (obj == this) {
                return true;
            }
            if (obj instanceof UiChange) {
                UiChange uiChange = (UiChange) obj;
                if (this.action$ar$edu$483e6edc_0 == uiChange.action$ar$edu$ebf9cfe8_0() && ((rect = this.sourceBoundsInScreen) != null ? rect.equals(uiChange.sourceBoundsInScreen()) : uiChange.sourceBoundsInScreen() == null)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$483e6edc_0) ^ 1000003;
            Rect rect = this.sourceBoundsInScreen;
            if (rect == null) {
                hashCode = 0;
            } else {
                hashCode = rect.hashCode();
            }
            return (ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode;
        }

        public final Rect sourceBoundsInScreen() {
            return this.sourceBoundsInScreen;
        }

        public final String toString() {
            String str;
            if (this.action$ar$edu$483e6edc_0 != 1) {
                str = "CLEAR_CACHE_FOR_VIEW";
            } else {
                str = "CLEAR_SCREEN_CACHE";
            }
            return "UiChange{action=" + str + ", sourceBoundsInScreen=" + String.valueOf(this.sourceBoundsInScreen) + "}";
        }

        public UiChange(int i, Rect rect) {
            this();
            this.action$ar$edu$483e6edc_0 = i;
            this.sourceBoundsInScreen = rect;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UniversalSearch {
        public final int action$ar$edu$77b42997_0;
        public final Configuration config;

        public UniversalSearch() {
        }

        public final int action$ar$edu$43b52495_0() {
            return this.action$ar$edu$77b42997_0;
        }

        public final Configuration config() {
            return this.config;
        }

        public final boolean equals(Object obj) {
            Configuration configuration;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UniversalSearch)) {
                return false;
            }
            UniversalSearch universalSearch = (UniversalSearch) obj;
            int i = this.action$ar$edu$77b42997_0;
            int action$ar$edu$43b52495_0 = universalSearch.action$ar$edu$43b52495_0();
            if (i != 0) {
                if (i == action$ar$edu$43b52495_0 && ((configuration = this.config) != null ? configuration.equals(universalSearch.config()) : universalSearch.config() == null)) {
                    return true;
                }
                return false;
            }
            throw null;
        }

        public final int hashCode() {
            int hashCode;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$77b42997_0) ^ 1000003;
            Configuration configuration = this.config;
            if (configuration == null) {
                hashCode = 0;
            } else {
                hashCode = configuration.hashCode();
            }
            return (ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ hashCode;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$77b42997_0;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            str = "null";
                        } else {
                            str = "RENEW_OVERLAY";
                        }
                    } else {
                        str = "HANDLE_SCREEN_STATE";
                    }
                } else {
                    str = "CANCEL_SEARCH";
                }
            } else {
                str = "TOGGLE_SEARCH";
            }
            return "UniversalSearch{action=" + str + ", config=" + String.valueOf(this.config) + "}";
        }

        public UniversalSearch(int i, Configuration configuration) {
            this();
            this.action$ar$edu$77b42997_0 = i;
            this.config = configuration;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Vibration {
        public final int resourceId;

        public Vibration() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof Vibration) && this.resourceId == ((Vibration) obj).resourceId()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return this.resourceId ^ 1000003;
        }

        public final int resourceId() {
            return this.resourceId;
        }

        public final String toString() {
            return "Vibration{resourceId=" + this.resourceId + "}";
        }

        public Vibration(int i) {
            this();
            this.resourceId = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VoiceRecognition {
        public final int action$ar$edu$1e6c4160_0;
        public final boolean checkDialog;

        public VoiceRecognition() {
        }

        public final int action$ar$edu$d40d7bd8_0() {
            return this.action$ar$edu$1e6c4160_0;
        }

        public final boolean checkDialog() {
            return this.checkDialog;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof VoiceRecognition) {
                VoiceRecognition voiceRecognition = (VoiceRecognition) obj;
                if (this.action$ar$edu$1e6c4160_0 == voiceRecognition.action$ar$edu$d40d7bd8_0() && this.checkDialog == voiceRecognition.checkDialog()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int i;
            int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.action$ar$edu$1e6c4160_0) ^ 1000003;
            if (true != this.checkDialog) {
                i = 1237;
            } else {
                i = 1231;
            }
            return (ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 * 1000003) ^ i;
        }

        public final String toString() {
            String str;
            int i = this.action$ar$edu$1e6c4160_0;
            if (i != 1) {
                if (i != 2) {
                    str = "SHOW_COMMAND_LIST";
                } else {
                    str = "STOP_LISTENING";
                }
            } else {
                str = "START_LISTENING";
            }
            return "VoiceRecognition{action=" + str + ", checkDialog=" + this.checkDialog + "}";
        }

        public VoiceRecognition(int i, boolean z) {
            this();
            this.action$ar$edu$1e6c4160_0 = i;
            this.checkDialog = z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WebAction {
        public final Action action;
        public final NavigationAction navigationAction;
        public final int nodeAction;
        public final Bundle nodeActionArgs;
        public final AccessibilityNodeInfoCompat target;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public enum Action {
            PERFORM_ACTION,
            HTML_DIRECTION
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            private Action action;
            public NavigationAction navigationAction;
            private int nodeAction;
            public Bundle nodeActionArgs;
            private byte set$0;
            private AccessibilityNodeInfoCompat target;

            public Builder() {
            }

            public final WebAction build() {
                Action action;
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
                if (this.set$0 == 3 && (action = this.action) != null && (accessibilityNodeInfoCompat = this.target) != null) {
                    return new WebAction(action, accessibilityNodeInfoCompat, this.nodeAction, this.nodeActionArgs, this.navigationAction);
                }
                StringBuilder sb = new StringBuilder();
                if (this.action == null) {
                    sb.append(" action");
                }
                if (this.target == null) {
                    sb.append(" target");
                }
                if ((this.set$0 & 1) == 0) {
                    sb.append(" nodeAction");
                }
                if ((this.set$0 & 2) == 0) {
                    sb.append(" updateFocusHistory");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }

            public final void setAction$ar$ds$6ba21b75_0(Action action) {
                if (action != null) {
                    this.action = action;
                    return;
                }
                throw new NullPointerException("Null action");
            }

            public final void setNodeAction$ar$ds$7670328a_0(int i) {
                this.nodeAction = i;
                this.set$0 = (byte) (this.set$0 | 1);
            }

            public final void setTarget$ar$ds$cb4df357_0(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                if (accessibilityNodeInfoCompat != null) {
                    this.target = accessibilityNodeInfoCompat;
                    return;
                }
                throw new NullPointerException("Null target");
            }

            public final void setUpdateFocusHistory$ar$ds() {
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public WebAction() {
        }

        public static Builder builder() {
            Builder builder = new Builder(null);
            builder.setUpdateFocusHistory$ar$ds();
            builder.setNodeAction$ar$ds$7670328a_0(1024);
            return builder;
        }

        public final Action action() {
            return this.action;
        }

        public final boolean equals(Object obj) {
            Bundle bundle;
            NavigationAction navigationAction;
            if (obj == this) {
                return true;
            }
            if (obj instanceof WebAction) {
                WebAction webAction = (WebAction) obj;
                if (this.action.equals(webAction.action()) && this.target.equals(webAction.target()) && this.nodeAction == webAction.nodeAction() && ((bundle = this.nodeActionArgs) != null ? bundle.equals(webAction.nodeActionArgs()) : webAction.nodeActionArgs() == null) && ((navigationAction = this.navigationAction) != null ? navigationAction.equals(webAction.navigationAction()) : webAction.navigationAction() == null)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int hashCode2 = ((this.action.hashCode() ^ 1000003) * 1000003) ^ this.target.hashCode();
            Bundle bundle = this.nodeActionArgs;
            int i = 0;
            if (bundle == null) {
                hashCode = 0;
            } else {
                hashCode = bundle.hashCode();
            }
            int i2 = ((((((hashCode2 * 1000003) ^ this.nodeAction) * 1000003) ^ hashCode) * 1000003) ^ 1237) * 1000003;
            NavigationAction navigationAction = this.navigationAction;
            if (navigationAction != null) {
                i = navigationAction.hashCode();
            }
            return i2 ^ i;
        }

        public final NavigationAction navigationAction() {
            return this.navigationAction;
        }

        public final int nodeAction() {
            return this.nodeAction;
        }

        public final Bundle nodeActionArgs() {
            return this.nodeActionArgs;
        }

        public final AccessibilityNodeInfoCompat target() {
            return this.target;
        }

        public final String toString() {
            return StringBuilderUtils.joinFields(StringBuilderUtils.optionalField("action", action()), StringBuilderUtils.optionalField("performNodeAction", Integer.valueOf(nodeAction())), StringBuilderUtils.optionalSubObj("nodeActionArgs", nodeActionArgs()), StringBuilderUtils.optionalTag("updateFocusHistory", false), StringBuilderUtils.optionalSubObj("navigationAction", navigationAction()), StringBuilderUtils.optionalSubObj("target", AccessibilityNodeInfoUtils.toStringShort(target())));
        }

        public WebAction(Action action, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Bundle bundle, NavigationAction navigationAction) {
            this();
            this.action = action;
            this.target = accessibilityNodeInfoCompat;
            this.nodeAction = i;
            this.nodeActionArgs = bundle;
            this.navigationAction = navigationAction;
        }
    }

    public Feedback() {
    }

    public static Part.Builder adjustVolume$ar$edu(int i, int i2) {
        Part.Builder builder = Part.builder();
        builder.adjustVolume = new AdjustVolume(i, 1);
        return builder;
    }

    public static Part.Builder continuousRead$ar$edu(int i) {
        Part.Builder builder = Part.builder();
        builder.continuousRead = new ContinuousRead(i);
        return builder;
    }

    public static Feedback create(Performance.EventId eventId, Part part) {
        return new Feedback(eventId, ImmutableList.of((Object) part));
    }

    public static Part.Builder dimScreen$ar$edu(int i) {
        Part.Builder builder = Part.builder();
        builder.dimScreen = new DimScreen(i);
        return builder;
    }

    public static EditText.Builder edit$ar$edu(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        EditText.Builder builder = new EditText.Builder(null);
        builder.set$0 = (byte) (builder.set$0 | 1);
        builder.setCursorIndex$ar$ds(-1);
        if (accessibilityNodeInfoCompat != null) {
            builder.node = accessibilityNodeInfoCompat;
            builder.action$ar$edu$24097d5e_0 = i;
            return builder;
        }
        throw new NullPointerException("Null node");
    }

    public static Focus.Builder focus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, FocusActionInfo focusActionInfo) {
        Focus.Builder builder = Focus.builder();
        builder.setAction$ar$ds$84b60801_0(Focus.Action.FOCUS);
        builder.focusActionInfo = focusActionInfo;
        builder.target = accessibilityNodeInfoCompat;
        return builder;
    }

    public static Part.Builder focusBottom(int i) {
        Part.Builder builder = Part.builder();
        FocusDirection.Builder builder2 = FocusDirection.builder();
        builder2.setAction$ar$ds$940a2012_0(FocusDirection.Action.BOTTOM);
        builder2.setInputMode$ar$ds(i);
        builder.focusDirection = builder2.build();
        return builder;
    }

    public static FocusDirection.Builder focusDirection(int i) {
        FocusDirection.Builder builder = FocusDirection.builder();
        builder.setAction$ar$ds$940a2012_0(FocusDirection.Action.NAVIGATE);
        builder.setDirection$ar$ds(i);
        return builder;
    }

    public static Part.Builder focusTop(int i) {
        Part.Builder builder = Part.builder();
        FocusDirection.Builder builder2 = FocusDirection.builder();
        builder2.setAction$ar$ds$940a2012_0(FocusDirection.Action.TOP);
        builder2.setInputMode$ar$ds(i);
        builder.focusDirection = builder2.build();
        return builder;
    }

    public static Part.Builder geminiRequest(int i, String str, Bitmap bitmap) {
        Part.Builder builder = Part.builder();
        GeminiRequest.Builder builder2 = GeminiRequest.builder();
        builder2.setAction$ar$edu$e15164a1_0$ar$ds(1);
        builder2.setRequestId$ar$ds(i);
        builder2.text = str;
        builder2.image = bitmap;
        builder.geminiRequest = builder2.build();
        return builder;
    }

    public static FocusDirection.Builder granularity(CursorGranularity cursorGranularity) {
        FocusDirection.Builder builder = FocusDirection.builder();
        builder.setAction$ar$ds$940a2012_0(FocusDirection.Action.SET_GRANULARITY);
        builder.granularity = cursorGranularity;
        return builder;
    }

    public static Part.Builder interrupt(int i, int i2) {
        Part.Builder builder = Part.builder();
        builder.setInterruptGroup$ar$ds(i);
        builder.setInterruptLevel$ar$ds(i2);
        return builder;
    }

    public static FocusDirection.Builder nextGranularity$ar$ds(CursorGranularity cursorGranularity) {
        FocusDirection.Builder builder = FocusDirection.builder();
        builder.setAction$ar$ds$940a2012_0(FocusDirection.Action.NEXT);
        builder.granularity = cursorGranularity;
        builder.setInputMode$ar$ds(0);
        return builder;
    }

    public static FocusDirection.Builder nextWindow(int i) {
        FocusDirection.Builder builder = FocusDirection.builder();
        builder.setAction$ar$ds$940a2012_0(FocusDirection.Action.NAVIGATE);
        builder.setDirection$ar$ds(1);
        builder.setToWindow$ar$ds(true);
        builder.setInputMode$ar$ds(i);
        return builder;
    }

    public static Part.Builder nodeAction(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        return nodeAction(accessibilityNodeInfoCompat, i, null);
    }

    public static Part.Builder passThroughMode$ar$edu(int i) {
        Part.Builder builder = Part.builder();
        builder.passThroughMode = new PassThroughMode(i, new Region());
        return builder;
    }

    public static Part.Builder performDetailedImageCaption(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Part.Builder builder = Part.builder();
        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = ImageCaption.builder$ar$class_merging$19c02b89_0();
        builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(5);
        builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
        builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
        return builder;
    }

    public static Part.Builder performDetailedOnDeviceImageCaption(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Part.Builder builder = Part.builder();
        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = ImageCaption.builder$ar$class_merging$19c02b89_0();
        builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(7);
        builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
        builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
        return builder;
    }

    public static Part.Builder performImageCaptions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        Part.Builder builder = Part.builder();
        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = ImageCaption.builder$ar$class_merging$19c02b89_0();
        builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(1);
        builder$ar$class_merging$19c02b89_0.MLKitLoggingOptions$Builder$ar$libraryName = accessibilityNodeInfoCompat;
        builder$ar$class_merging$19c02b89_0.setUserRequested$ar$ds(z);
        builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
        return builder;
    }

    public static FocusDirection.Builder previousWindow(int i) {
        FocusDirection.Builder builder = FocusDirection.builder();
        builder.setAction$ar$ds$940a2012_0(FocusDirection.Action.NAVIGATE);
        builder.setDirection$ar$ds(2);
        builder.setToWindow$ar$ds(true);
        builder.setInputMode$ar$ds(i);
        return builder;
    }

    public static Part.Builder requestServiceFlag$ar$edu$ar$ds(int i) {
        Part.Builder builder = Part.builder();
        builder.serviceFlag = new ServiceFlag(i);
        return builder;
    }

    public static Part.Builder selectionModeOn(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Part.Builder builder = Part.builder();
        FocusDirection.Builder builder2 = FocusDirection.builder();
        builder2.setAction$ar$ds$940a2012_0(FocusDirection.Action.SELECTION_MODE_ON);
        builder2.targetNode = accessibilityNodeInfoCompat;
        builder.focusDirection = builder2.build();
        return builder;
    }

    public static Part.Builder showSelectorUI(TalkBackUIActor$Type talkBackUIActor$Type, CharSequence charSequence, boolean z) {
        Part.Builder builder = Part.builder();
        builder.talkBackUI = new TalkBackUI(1, talkBackUIActor$Type, charSequence, z);
        return builder;
    }

    public static Part.Builder showToast$ar$edu(int i, CharSequence charSequence, boolean z) {
        Part.Builder builder = Part.builder();
        builder.showToast = new ShowToast(1, charSequence, z);
        return builder;
    }

    public static Part.Builder sound(int i) {
        return Part.builder().sound(i);
    }

    public static Part.Builder speech(Speech.Action action) {
        Part.Builder builder = Part.builder();
        builder.speech = Speech.create(action);
        return builder;
    }

    public static Part.Builder systemAction(int i) {
        Part.Builder builder = Part.builder();
        builder.systemAction = new SystemAction(i);
        return builder;
    }

    public static Part.Builder talkBackUI$ar$edu(int i, TalkBackUIActor$Type talkBackUIActor$Type) {
        Part.Builder builder = Part.builder();
        builder.talkBackUI = new TalkBackUI(i, talkBackUIActor$Type, null, true);
        return builder;
    }

    public static Part.Builder triggerIntent$ar$edu(int i) {
        Part.Builder builder = Part.builder();
        builder.triggerIntent = new TriggerIntent(i);
        return builder;
    }

    public static Part.Builder universalSearch$ar$edu(int i) {
        Part.Builder builder = Part.builder();
        ApplicationExitConfigurations.Builder builder2 = new ApplicationExitConfigurations.Builder(null, null, null);
        builder2.setAction$ar$edu$aa732c31_0$ar$ds(i);
        builder.universalSearch = builder2.build();
        return builder;
    }

    public static Part.Builder voiceRecognition$ar$edu$6decc7d7_0(int i, boolean z) {
        Part.Builder builder = Part.builder();
        builder.voiceRecognition = new VoiceRecognition(1, z);
        return builder;
    }

    public static Part.Builder webDirectionHtml(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction) {
        Part.Builder builder = Part.builder();
        WebAction.Builder builder2 = WebAction.builder();
        builder2.setAction$ar$ds$6ba21b75_0(WebAction.Action.HTML_DIRECTION);
        builder2.setTarget$ar$ds$cb4df357_0(accessibilityNodeInfoCompat);
        builder2.navigationAction = navigationAction;
        builder.webAction = builder2.build();
        return builder;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Feedback) {
            Feedback feedback = (Feedback) obj;
            Performance.EventId eventId = this.eventId;
            if (eventId != null ? eventId.equals(feedback.eventId()) : feedback.eventId() == null) {
                if (ContextDataProvider.equalsImpl(this.failovers, feedback.failovers())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final Performance.EventId eventId() {
        return this.eventId;
    }

    public final ImmutableList failovers() {
        return this.failovers;
    }

    public final int hashCode() {
        int hashCode;
        Performance.EventId eventId = this.eventId;
        if (eventId == null) {
            hashCode = 0;
        } else {
            hashCode = eventId.hashCode();
        }
        return ((hashCode ^ 1000003) * 1000003) ^ this.failovers.hashCode();
    }

    public final String toString() {
        ImmutableList immutableList = this.failovers;
        return "Feedback{eventId=" + String.valueOf(this.eventId) + ", failovers=" + immutableList.toString() + "}";
    }

    public Feedback(Performance.EventId eventId, ImmutableList immutableList) {
        this();
        this.eventId = eventId;
        if (immutableList == null) {
            throw new NullPointerException("Null failovers");
        }
        this.failovers = immutableList;
    }

    public static Feedback create(Performance.EventId eventId, List list) {
        return new Feedback(eventId, ImmutableList.copyOf((Collection) list));
    }

    public static Part.Builder nodeAction(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Bundle bundle) {
        Part.Builder builder = Part.builder();
        if (accessibilityNodeInfoCompat == null) {
            return builder;
        }
        AccessibilityNode takeOwnership = AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat);
        Interpretation$ManualScroll.Builder builder2 = new Interpretation$ManualScroll.Builder(null, null);
        builder2.setTarget$ar$ds(takeOwnership);
        builder2.setActionId$ar$ds(i);
        builder2.Interpretation$ManualScroll$Builder$ar$currentFocusedNode = bundle;
        builder.nodeAction = builder2.build();
        return builder;
    }

    public static Part.Builder speech(CharSequence charSequence) {
        return Part.builder().speech(charSequence, null);
    }

    public static Focus.Builder focus(Focus.Action action) {
        Focus.Builder builder = Focus.builder();
        builder.setAction$ar$ds$84b60801_0(action);
        return builder;
    }

    public static Part.Builder speech(CharSequence charSequence, SpeechController.SpeakOptions speakOptions) {
        return Part.builder().speech(charSequence, speakOptions);
    }

    public static Part.Builder focusDirection(FocusDirection.Action action) {
        Part.Builder builder = Part.builder();
        FocusDirection.Builder builder2 = FocusDirection.builder();
        builder2.setAction$ar$ds$940a2012_0(action);
        builder.focusDirection = builder2.build();
        return builder;
    }
}

package com.google.android.accessibility.talkback.analytics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.RegularImmutableSet;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackAnalyticsDBHelper extends SQLiteOpenHelper {
    private static final ImmutableList DB_TABLE_COLLECTION = ImmutableList.of("granularityMovements", "settingChanges", "globalContextMenuEntry", "localContextMenuEntry", "ContextMenuOpenedEntry", "gestureUsedEntry", "voiceCommandEvent", "imageCaptionEvent", "selectorEvent", "selectorActionEvent", "magnificationEntry", "shortcutActionsEntry", "TrainingSectionEntry", "TrainingEntry", "keyboardShortcutUsedEntry", "keyboardKeymapUsedEntry", "keyboardModifierKeyUsedEntry", "keyboardShortcutChangedEntry", "MiscellaneousEntry");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ContextMenuOpenedEntry {
        private final int count;
        private final int menuStyle;
        private final int menuType;

        public ContextMenuOpenedEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ContextMenuOpenedEntry) {
                ContextMenuOpenedEntry contextMenuOpenedEntry = (ContextMenuOpenedEntry) obj;
                if (this.menuType == contextMenuOpenedEntry.menuType() && this.menuStyle == contextMenuOpenedEntry.menuStyle() && this.count == contextMenuOpenedEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((((this.menuType ^ 1000003) * 1000003) ^ this.menuStyle) * 1000003) ^ this.count;
        }

        public final int menuStyle() {
            return this.menuStyle;
        }

        public final int menuType() {
            return this.menuType;
        }

        public final String toString() {
            return "ContextMenuOpenedEntry{menuType=" + this.menuType + ", menuStyle=" + this.menuStyle + ", count=" + this.count + "}";
        }

        public ContextMenuOpenedEntry(int i, int i2, int i3) {
            this();
            this.menuType = i;
            this.menuStyle = i2;
            this.count = i3;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GestureUsedEntry {
        public final int count;
        public final int gestureId;

        public GestureUsedEntry(int i, int i2) {
            this.gestureId = i;
            this.count = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof GestureUsedEntry)) {
                return false;
            }
            GestureUsedEntry gestureUsedEntry = (GestureUsedEntry) obj;
            if (this.gestureId == gestureUsedEntry.gestureId && this.count == gestureUsedEntry.count) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.gestureId), Integer.valueOf(this.count));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GlobalContextMenuEntry {
        private final int count;
        private final int menuAction;

        public GlobalContextMenuEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof GlobalContextMenuEntry) {
                GlobalContextMenuEntry globalContextMenuEntry = (GlobalContextMenuEntry) obj;
                if (this.menuAction == globalContextMenuEntry.menuAction() && this.count == globalContextMenuEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.menuAction ^ 1000003) * 1000003) ^ this.count;
        }

        public final int menuAction() {
            return this.menuAction;
        }

        public final String toString() {
            return "GlobalContextMenuEntry{menuAction=" + this.menuAction + ", count=" + this.count + "}";
        }

        public GlobalContextMenuEntry(int i, int i2) {
            this();
            this.menuAction = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GranularityMovementEntry {
        public final int count;
        public final CursorGranularity granularity;

        public GranularityMovementEntry(CursorGranularity cursorGranularity, int i) {
            this.granularity = cursorGranularity;
            this.count = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof GranularityMovementEntry)) {
                return false;
            }
            GranularityMovementEntry granularityMovementEntry = (GranularityMovementEntry) obj;
            if (this.granularity == granularityMovementEntry.granularity && this.count == granularityMovementEntry.count) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(this.granularity, Integer.valueOf(this.count));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImageCaptionEvent {
        private final int count;
        private final int event;

        public ImageCaptionEvent() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ImageCaptionEvent) {
                ImageCaptionEvent imageCaptionEvent = (ImageCaptionEvent) obj;
                if (this.event == imageCaptionEvent.event() && this.count == imageCaptionEvent.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int event() {
            return this.event;
        }

        public final int hashCode() {
            return ((this.event ^ 1000003) * 1000003) ^ this.count;
        }

        public final String toString() {
            return "ImageCaptionEvent{event=" + this.event + ", count=" + this.count + "}";
        }

        public ImageCaptionEvent(int i, int i2) {
            this();
            this.event = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class KeyboardKeymapUsedEntry {
        private final int count;
        private final int keymapType;

        public KeyboardKeymapUsedEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof KeyboardKeymapUsedEntry) {
                KeyboardKeymapUsedEntry keyboardKeymapUsedEntry = (KeyboardKeymapUsedEntry) obj;
                if (this.keymapType == keyboardKeymapUsedEntry.keymapType() && this.count == keyboardKeymapUsedEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.keymapType ^ 1000003) * 1000003) ^ this.count;
        }

        public final int keymapType() {
            return this.keymapType;
        }

        public final String toString() {
            return "KeyboardKeymapUsedEntry{keymapType=" + this.keymapType + ", count=" + this.count + "}";
        }

        public KeyboardKeymapUsedEntry(int i, int i2) {
            this();
            this.keymapType = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class KeyboardModifierKeyUsedEntry {
        private final int count;
        private final int modifierKey;

        public KeyboardModifierKeyUsedEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof KeyboardModifierKeyUsedEntry) {
                KeyboardModifierKeyUsedEntry keyboardModifierKeyUsedEntry = (KeyboardModifierKeyUsedEntry) obj;
                if (this.modifierKey == keyboardModifierKeyUsedEntry.modifierKey() && this.count == keyboardModifierKeyUsedEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.modifierKey ^ 1000003) * 1000003) ^ this.count;
        }

        public final int modifierKey() {
            return this.modifierKey;
        }

        public final String toString() {
            return "KeyboardModifierKeyUsedEntry{modifierKey=" + this.modifierKey + ", count=" + this.count + "}";
        }

        public KeyboardModifierKeyUsedEntry(int i, int i2) {
            this();
            this.modifierKey = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class KeyboardShortcutChangedEntry {
        private final int actionId;
        private final int count;
        private final long keyComboCode;
        private final int triggerModifier;

        public KeyboardShortcutChangedEntry() {
        }

        public final int actionId() {
            return this.actionId;
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof KeyboardShortcutChangedEntry) {
                KeyboardShortcutChangedEntry keyboardShortcutChangedEntry = (KeyboardShortcutChangedEntry) obj;
                if (this.actionId == keyboardShortcutChangedEntry.actionId() && this.triggerModifier == keyboardShortcutChangedEntry.triggerModifier() && this.keyComboCode == keyboardShortcutChangedEntry.keyComboCode() && this.count == keyboardShortcutChangedEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            long j = this.keyComboCode;
            return ((((int) (j ^ (j >>> 32))) ^ ((((this.actionId ^ 1000003) * 1000003) ^ this.triggerModifier) * 1000003)) * 1000003) ^ this.count;
        }

        public final long keyComboCode() {
            return this.keyComboCode;
        }

        public final String toString() {
            return "KeyboardShortcutChangedEntry{actionId=" + this.actionId + ", triggerModifier=" + this.triggerModifier + ", keyComboCode=" + this.keyComboCode + ", count=" + this.count + "}";
        }

        public final int triggerModifier() {
            return this.triggerModifier;
        }

        public KeyboardShortcutChangedEntry(int i, int i2, long j, int i3) {
            this();
            this.actionId = i;
            this.triggerModifier = i2;
            this.keyComboCode = j;
            this.count = i3;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class KeyboardShortcutUsedEntry {
        private final int actionId;
        private final int count;
        private final long keyComboCode;
        private final int triggerModifier;

        public KeyboardShortcutUsedEntry() {
        }

        public final int actionId() {
            return this.actionId;
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof KeyboardShortcutUsedEntry) {
                KeyboardShortcutUsedEntry keyboardShortcutUsedEntry = (KeyboardShortcutUsedEntry) obj;
                if (this.actionId == keyboardShortcutUsedEntry.actionId() && this.triggerModifier == keyboardShortcutUsedEntry.triggerModifier() && this.keyComboCode == keyboardShortcutUsedEntry.keyComboCode() && this.count == keyboardShortcutUsedEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            long j = this.keyComboCode;
            return ((((int) (j ^ (j >>> 32))) ^ ((((this.actionId ^ 1000003) * 1000003) ^ this.triggerModifier) * 1000003)) * 1000003) ^ this.count;
        }

        public final long keyComboCode() {
            return this.keyComboCode;
        }

        public final String toString() {
            return "KeyboardShortcutUsedEntry{actionId=" + this.actionId + ", triggerModifier=" + this.triggerModifier + ", keyComboCode=" + this.keyComboCode + ", count=" + this.count + "}";
        }

        public final int triggerModifier() {
            return this.triggerModifier;
        }

        public KeyboardShortcutUsedEntry(int i, int i2, long j, int i3) {
            this();
            this.actionId = i;
            this.triggerModifier = i2;
            this.keyComboCode = j;
            this.count = i3;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LocalContextMenuEntry {
        private final int count;
        private final int menuAction;

        public LocalContextMenuEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof LocalContextMenuEntry) {
                LocalContextMenuEntry localContextMenuEntry = (LocalContextMenuEntry) obj;
                if (this.menuAction == localContextMenuEntry.menuAction() && this.count == localContextMenuEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.menuAction ^ 1000003) * 1000003) ^ this.count;
        }

        public final int menuAction() {
            return this.menuAction;
        }

        public final String toString() {
            return "LocalContextMenuEntry{menuAction=" + this.menuAction + ", count=" + this.count + "}";
        }

        public LocalContextMenuEntry(int i, int i2) {
            this();
            this.menuAction = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MagnificationEntry {
        private final int count;
        private final int magnificationUsed;

        public MagnificationEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MagnificationEntry) {
                MagnificationEntry magnificationEntry = (MagnificationEntry) obj;
                if (this.magnificationUsed == magnificationEntry.magnificationUsed() && this.count == magnificationEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.magnificationUsed ^ 1000003) * 1000003) ^ this.count;
        }

        public final int magnificationUsed() {
            return this.magnificationUsed;
        }

        public final String toString() {
            return "MagnificationEntry{magnificationUsed=" + this.magnificationUsed + ", count=" + this.count + "}";
        }

        public MagnificationEntry(int i, int i2) {
            this();
            this.magnificationUsed = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MiscellaneousEntry {
        private final int count;
        private final int item;

        public MiscellaneousEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MiscellaneousEntry) {
                MiscellaneousEntry miscellaneousEntry = (MiscellaneousEntry) obj;
                if (this.item == miscellaneousEntry.item() && this.count == miscellaneousEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.item ^ 1000003) * 1000003) ^ this.count;
        }

        public final int item() {
            return this.item;
        }

        public final String toString() {
            return "MiscellaneousEntry{item=" + this.item + ", count=" + this.count + "}";
        }

        public MiscellaneousEntry(int i, int i2) {
            this();
            this.item = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SelectorActionEvent {
        private final int count;
        private final int selectedItem;

        public SelectorActionEvent() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof SelectorActionEvent) {
                SelectorActionEvent selectorActionEvent = (SelectorActionEvent) obj;
                if (this.selectedItem == selectorActionEvent.selectedItem() && this.count == selectorActionEvent.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.selectedItem ^ 1000003) * 1000003) ^ this.count;
        }

        public final int selectedItem() {
            return this.selectedItem;
        }

        public final String toString() {
            return "SelectorActionEvent{selectedItem=" + this.selectedItem + ", count=" + this.count + "}";
        }

        public SelectorActionEvent(int i, int i2) {
            this();
            this.selectedItem = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SelectorEvent {
        private final int count;
        private final boolean isDestination;

        public SelectorEvent() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof SelectorEvent) {
                SelectorEvent selectorEvent = (SelectorEvent) obj;
                if (this.isDestination == selectorEvent.isDestination() && this.count == selectorEvent.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int i;
            if (true != this.isDestination) {
                i = 1237;
            } else {
                i = 1231;
            }
            return ((i ^ 1000003) * 1000003) ^ this.count;
        }

        public final boolean isDestination() {
            return this.isDestination;
        }

        public final String toString() {
            return "SelectorEvent{isDestination=" + this.isDestination + ", count=" + this.count + "}";
        }

        public SelectorEvent(boolean z, int i) {
            this();
            this.isDestination = z;
            this.count = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SettingChangeEntry {
        public final int count;
        public final String prefKey;
        public final String prefValue;
        public final int userActionType;

        public SettingChangeEntry(String str, String str2, int i, int i2) {
            this.prefKey = str;
            this.prefValue = str2;
            this.userActionType = i;
            this.count = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SettingChangeEntry)) {
                return false;
            }
            SettingChangeEntry settingChangeEntry = (SettingChangeEntry) obj;
            if (this.prefKey.equals(settingChangeEntry.prefKey) && this.prefValue.equals(settingChangeEntry.prefValue) && this.userActionType == settingChangeEntry.userActionType && this.count == settingChangeEntry.count) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(this.prefKey, this.prefValue, Integer.valueOf(this.userActionType), Integer.valueOf(this.count));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ShortcutActionsEntry {
        private final int count;
        private final int shortcutAction;

        public ShortcutActionsEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ShortcutActionsEntry) {
                ShortcutActionsEntry shortcutActionsEntry = (ShortcutActionsEntry) obj;
                if (this.shortcutAction == shortcutActionsEntry.shortcutAction() && this.count == shortcutActionsEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.shortcutAction ^ 1000003) * 1000003) ^ this.count;
        }

        public final int shortcutAction() {
            return this.shortcutAction;
        }

        public final String toString() {
            return "ShortcutActionsEntry{shortcutAction=" + this.shortcutAction + ", count=" + this.count + "}";
        }

        public ShortcutActionsEntry(int i, int i2) {
            this();
            this.shortcutAction = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TrainingEntry {
        private final byte[] trainingMetricInBytes;

        public TrainingEntry() {
        }

        public final boolean equals(Object obj) {
            byte[] trainingMetricInBytes;
            if (obj == this) {
                return true;
            }
            if (obj instanceof TrainingEntry) {
                TrainingEntry trainingEntry = (TrainingEntry) obj;
                byte[] bArr = this.trainingMetricInBytes;
                if (trainingEntry instanceof TrainingEntry) {
                    trainingMetricInBytes = trainingEntry.trainingMetricInBytes;
                } else {
                    trainingMetricInBytes = trainingEntry.trainingMetricInBytes();
                }
                return Arrays.equals(bArr, trainingMetricInBytes);
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(this.trainingMetricInBytes) ^ 1000003;
        }

        public final String toString() {
            return "TrainingEntry{trainingMetricInBytes=" + Arrays.toString(this.trainingMetricInBytes) + "}";
        }

        public final byte[] trainingMetricInBytes() {
            return this.trainingMetricInBytes;
        }

        public TrainingEntry(byte[] bArr) {
            this();
            if (bArr == null) {
                throw new NullPointerException("Null trainingMetricInBytes");
            }
            this.trainingMetricInBytes = bArr;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TrainingSectionEntry {
        private final int count;
        private final int trainingSection;

        public TrainingSectionEntry() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof TrainingSectionEntry) {
                TrainingSectionEntry trainingSectionEntry = (TrainingSectionEntry) obj;
                if (this.trainingSection == trainingSectionEntry.trainingSection() && this.count == trainingSectionEntry.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.trainingSection ^ 1000003) * 1000003) ^ this.count;
        }

        public final String toString() {
            return "TrainingSectionEntry{trainingSection=" + this.trainingSection + ", count=" + this.count + "}";
        }

        public final int trainingSection() {
            return this.trainingSection;
        }

        public TrainingSectionEntry(int i, int i2) {
            this();
            this.trainingSection = i;
            this.count = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VoiceCommandEvent {
        private final int count;
        private final int event;

        public VoiceCommandEvent() {
        }

        public final int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof VoiceCommandEvent) {
                VoiceCommandEvent voiceCommandEvent = (VoiceCommandEvent) obj;
                if (this.event == voiceCommandEvent.event() && this.count == voiceCommandEvent.count()) {
                    return true;
                }
            }
            return false;
        }

        public final int event() {
            return this.event;
        }

        public final int hashCode() {
            return ((this.event ^ 1000003) * 1000003) ^ this.count;
        }

        public final String toString() {
            return "VoiceCommandEvent{event=" + this.event + ", count=" + this.count + "}";
        }

        public VoiceCommandEvent(int i, int i2) {
            this();
            this.event = i;
            this.count = i2;
        }
    }

    public TalkBackAnalyticsDBHelper(Context context) {
        super(context, "TalkBackAnalytics.db", (SQLiteDatabase.CursorFactory) null, 11);
    }

    private static String createSQLTable$ar$ds(String str, String... strArr) {
        if (!DB_TABLE_COLLECTION.contains(str)) {
            LogUtils.e("TalkBackAnalyticsDBHelper", "DB table is undefined", new Object[0]);
            return "";
        }
        return "CREATE TABLE " + str + " (compound TEXT PRIMARY KEY," + TextUtils.join(",", strArr) + ")";
    }

    private static void createTables(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("granularityMovements", "granularity INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("settingChanges", "prefKey TEXT", "prefValue TEXT", "userActionType INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("globalContextMenuEntry", "menuAction INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("localContextMenuEntry", "menuAction INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("ContextMenuOpenedEntry", "menuType INTEGER", "menuStyle INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("gestureUsedEntry", "gestureId INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("voiceCommandEvent", "event INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("imageCaptionEvent", "event INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("selectorEvent", "destination INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("selectorActionEvent", "selectedItem INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("magnificationEntry", "magnificationUsed INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("shortcutActionsEntry", "shortcutActions INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("TrainingSectionEntry", "trainingSection INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("TrainingEntry", "trainingMetric BLOB", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("keyboardShortcutUsedEntry", "keyboardShortcutUsed INTEGER", "triggerModifier INTEGER", "keyComboCode INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("keyboardKeymapUsedEntry", "keyboardKeymapUsed INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("keyboardModifierKeyUsedEntry", "keyboardModifierKeyUsed INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("keyboardShortcutChangedEntry", "keyboardShortcutChanged INTEGER", "triggerModifier INTEGER", "keyComboCode INTEGER", "count INTEGER"));
        safeExecSQL(sQLiteDatabase, createSQLTable$ar$ds("MiscellaneousEntry", "item INTEGER", "count INTEGER"));
    }

    private static void safeExecSQL(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (RuntimeException e) {
            LogUtils.d("TalkBackAnalyticsDBHelper", "safeExecSQL exception:".concat(e.toString()), new Object[0]);
        }
    }

    private static Cursor safeQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        try {
            return sQLiteDatabase.query(true, str, strArr, null, null, null, null, null, null);
        } catch (RuntimeException e) {
            LogUtils.d("TalkBackAnalyticsDBHelper", "safeQuery exception:".concat(e.toString()), new Object[0]);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r4v3 */
    public static void safeUpdate(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) {
        int i;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase2;
        String str3;
        ContentValues contentValues2;
        try {
            i = new String[]{"count"};
        } catch (RuntimeException e) {
            e = e;
            i = 0;
        }
        try {
            try {
                cursor = sQLiteDatabase.query(true, str, i, "compound=?", new String[]{str2}, null, null, null, null);
            } catch (RuntimeException e2) {
                LogUtils.d("TalkBackAnalyticsDBHelper", "safeQueryWithWhere exception:".concat(e2.toString()), new Object[0]);
                cursor = null;
            }
        } catch (RuntimeException e3) {
            e = e3;
            i = 0;
            LogUtils.d("TalkBackAnalyticsDBHelper", "safeUpdate exception:".concat(e.toString()), new Object[i]);
        }
        try {
            if (cursor != null) {
                if (!cursor.moveToNext()) {
                    sQLiteDatabase2 = sQLiteDatabase;
                    contentValues2 = contentValues;
                    str3 = null;
                } else {
                    int i2 = cursor.getInt(0) + 1;
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put("count", Integer.valueOf(i2));
                    sQLiteDatabase.update(str, contentValues3, "compound= \"" + str2 + "\"", null);
                    cursor.close();
                    return;
                }
            } else {
                sQLiteDatabase2 = sQLiteDatabase;
                str3 = null;
                contentValues2 = contentValues;
            }
            sQLiteDatabase2.insert(str, str3, contentValues2);
        } catch (RuntimeException e4) {
            e = e4;
            LogUtils.d("TalkBackAnalyticsDBHelper", "safeUpdate exception:".concat(e.toString()), new Object[i]);
        }
    }

    public final void cacheMiscellaneousEvent(int i) {
        SQLiteDatabase safeGetWritableDatabase = safeGetWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String valueOf = String.valueOf(i);
        contentValues.put("item", Integer.valueOf(i));
        contentValues.put("compound", valueOf);
        contentValues.put("count", (Integer) 1);
        safeUpdate(safeGetWritableDatabase, "MiscellaneousEntry", valueOf, contentValues);
    }

    public final void cacheSelectorEvent(boolean z) {
        SQLiteDatabase safeGetWritableDatabase = safeGetWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String valueOf = String.valueOf(z);
        contentValues.put("destination", Boolean.valueOf(z));
        contentValues.put("compound", valueOf);
        contentValues.put("count", (Integer) 1);
        safeUpdate(safeGetWritableDatabase, "selectorEvent", valueOf, contentValues);
    }

    public final void cacheSettingChange(String str, String str2, int i) {
        SQLiteDatabase safeGetWritableDatabase = safeGetWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String join = TextUtils.join("-", new String[]{str, str2, String.valueOf(i)});
        contentValues.put("prefKey", str);
        contentValues.put("prefValue", str2);
        contentValues.put("userActionType", Integer.valueOf(i));
        contentValues.put("compound", join);
        contentValues.put("count", (Integer) 1);
        safeUpdate(safeGetWritableDatabase, "settingChanges", join, contentValues);
    }

    public final Set getContextMenuOpened() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "ContextMenuOpenedEntry", new String[]{"menuType", "menuStyle", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new ContextMenuOpenedEntry(safeQuery.getInt(0), safeQuery.getInt(1), safeQuery.getInt(2)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getGestureUsed() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "gestureUsedEntry", new String[]{"gestureId", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new GestureUsedEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getGlobalContextMenu() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "globalContextMenuEntry", new String[]{"menuAction", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new GlobalContextMenuEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getGranularityMovements() {
        CursorGranularity cursorGranularity;
        Cursor safeQuery = safeQuery(getReadableDatabase(), "granularityMovements", new String[]{"granularity", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            int i = 0;
            int i2 = safeQuery.getInt(0);
            CursorGranularity[] values = CursorGranularity.values();
            int length = values.length;
            while (true) {
                if (i < length) {
                    cursorGranularity = values[i];
                    if (cursorGranularity.id == i2) {
                        break;
                    }
                    i++;
                } else {
                    cursorGranularity = null;
                    break;
                }
            }
            hashSet.add(new GranularityMovementEntry(cursorGranularity, safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getImageCaptionEvent() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "imageCaptionEvent", new String[]{"event", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new ImageCaptionEvent(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getKeyboardKeymapUsedEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "keyboardKeymapUsedEntry", new String[]{"keyboardKeymapUsed", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new KeyboardKeymapUsedEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getKeyboardModifierKeyUsedEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "keyboardModifierKeyUsedEntry", new String[]{"keyboardModifierKeyUsed", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new KeyboardModifierKeyUsedEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getKeyboardShortcutChangedEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "keyboardShortcutChangedEntry", new String[]{"keyboardShortcutChanged", "triggerModifier", "keyComboCode", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new KeyboardShortcutChangedEntry(safeQuery.getInt(0), safeQuery.getInt(1), safeQuery.getLong(2), safeQuery.getInt(3)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getKeyboardShortcutUsedEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "keyboardShortcutUsedEntry", new String[]{"keyboardShortcutUsed", "triggerModifier", "keyComboCode", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new KeyboardShortcutUsedEntry(safeQuery.getInt(0), safeQuery.getInt(1), safeQuery.getLong(2), safeQuery.getInt(3)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getLocalContextMenu() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "localContextMenuEntry", new String[]{"menuAction", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new LocalContextMenuEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getMagnificationEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "magnificationEntry", new String[]{"magnificationUsed", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new MagnificationEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getMiscellaneousEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "MiscellaneousEntry", new String[]{"item", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new MiscellaneousEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getSelectorActionEvent() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "selectorActionEvent", new String[]{"selectedItem", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new SelectorActionEvent(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getSelectorEvent() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "selectorEvent", new String[]{"destination", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            boolean z = false;
            int i = safeQuery.getInt(0);
            int i2 = safeQuery.getInt(1);
            if (i != 0) {
                z = true;
            }
            hashSet.add(new SelectorEvent(z, i2));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getSettingChanges() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "settingChanges", new String[]{"prefKey", "prefValue", "userActionType", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new SettingChangeEntry(safeQuery.getString(0), safeQuery.getString(1), safeQuery.getInt(2), safeQuery.getInt(3)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getShortcutActionsEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "shortcutActionsEntry", new String[]{"shortcutActions", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new ShortcutActionsEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final List getTrainingEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "TrainingEntry", new String[]{"trainingMetric"});
        if (safeQuery == null) {
            int i = ImmutableList.ImmutableList$ar$NoOp;
            return RegularImmutableList.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            int i2 = ImmutableList.ImmutableList$ar$NoOp;
            return RegularImmutableList.EMPTY;
        }
        ArrayList arrayList = new ArrayList();
        while (safeQuery.moveToNext()) {
            arrayList.add(new TrainingEntry(safeQuery.getBlob(0)));
        }
        safeQuery.close();
        return ImmutableList.copyOf((Collection) arrayList);
    }

    public final Set getTutorialSectionEntry() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "TrainingSectionEntry", new String[]{"trainingSection", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new TrainingSectionEntry(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    public final Set getVoiceCommandEvent() {
        Cursor safeQuery = safeQuery(getReadableDatabase(), "voiceCommandEvent", new String[]{"event", "count"});
        if (safeQuery == null) {
            return RegularImmutableSet.EMPTY;
        }
        if (safeQuery.getCount() == 0) {
            safeQuery.close();
            return RegularImmutableSet.EMPTY;
        }
        HashSet hashSet = new HashSet();
        while (safeQuery.moveToNext()) {
            hashSet.add(new VoiceCommandEvent(safeQuery.getInt(0), safeQuery.getInt(1)));
        }
        safeQuery.close();
        return hashSet;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        createTables(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        reset(sQLiteDatabase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        boolean z;
        ImmutableList immutableList = DB_TABLE_COLLECTION;
        int i = ((RegularImmutableList) immutableList).size;
        int i2 = 0;
        while (i2 < i) {
            String str = (String) immutableList.get(i2);
            try {
                Cursor query = sQLiteDatabase.query(true, str, new String[]{"count"}, null, null, null, null, null, null);
                if (query != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (query != null) {
                    query.close();
                }
            } catch (RuntimeException unused) {
                LogUtils.e("TalkBackAnalyticsDBHelper", "database open fail for table:%s", str);
                z = false;
            }
            i2++;
            if (!z) {
                reset(sQLiteDatabase);
                return;
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        reset(sQLiteDatabase);
    }

    public final void reset() {
        SQLiteDatabase safeGetWritableDatabase = safeGetWritableDatabase();
        if (safeGetWritableDatabase != null) {
            reset(safeGetWritableDatabase);
        }
    }

    public final SQLiteDatabase safeGetWritableDatabase() {
        try {
            return getWritableDatabase();
        } catch (RuntimeException e) {
            LogUtils.d("TalkBackAnalyticsDBHelper", "safeGetWritableDatabase exception:".concat(e.toString()), new Object[0]);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void reset(SQLiteDatabase sQLiteDatabase) {
        ImmutableList immutableList = DB_TABLE_COLLECTION;
        int i = ((RegularImmutableList) immutableList).size;
        for (int i2 = 0; i2 < i; i2++) {
            safeExecSQL(sQLiteDatabase, "DROP TABLE IF EXISTS ".concat(String.valueOf((String) immutableList.get(i2))));
        }
        createTables(sQLiteDatabase);
    }
}

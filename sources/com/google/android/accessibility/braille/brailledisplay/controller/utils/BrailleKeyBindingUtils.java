package com.google.android.accessibility.braille.brailledisplay.controller.utils;

import android.content.Context;
import android.content.res.Resources;
import android.icu.text.NumberFormat;
import android.support.v7.widget.GapWorker;
import android.text.TextUtils;
import com.google.android.accessibility.braille.brltty.BrailleDisplayProperties;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.brltty.BrailleKeyBinding;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.shared.logger.MLKitLoggingOptions;
import googledata.experiments.mobile.accessibility_suite.features.BrailleDisplayConfig;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleKeyBindingUtils {
    public static final Comparator COMPARE_BINDINGS = new GapWorker.AnonymousClass1(5);
    public static final Comparator COMPARE_BINDINGS_BY_COMMAND = new GapWorker.AnonymousClass1(6);
    private static List supportedCommands;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SupportedCommand {
        public final Category category;
        public final int command;
        public final int commandDescriptionRes;
        private final KeyDescriptor keyDescriptor;
        public final Subcategory subcategory;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public enum Category {
            BASIC,
            NAVIGATION,
            SYSTEM_ACTIONS,
            TALKBACK_FEATURES,
            BRAILLE_SETTINGS,
            EDITING(R.string.bd_cmd_subcategory_editing_description);

            public final int descriptionRes;

            Category() {
                this(0);
            }

            Category(int i) {
                this.descriptionRes = i;
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class KeyDescriptor {
            public final BrailleCharacter dots;
            private final int keyNameRes;
            public final boolean space;

            public KeyDescriptor() {
            }

            static MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging() {
                MLKitLoggingOptions.Builder builder = new MLKitLoggingOptions.Builder(null, null, null);
                builder.setSpace$ar$ds(false);
                builder.setDots$ar$ds(BrailleCharacter.EMPTY_CELL);
                builder.set$0 = (byte) (builder.set$0 | 2);
                builder.setKeyNameRes$ar$ds(0);
                return builder;
            }

            public final BrailleCharacter dots() {
                return this.dots;
            }

            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof KeyDescriptor) {
                    KeyDescriptor keyDescriptor = (KeyDescriptor) obj;
                    if (this.space == keyDescriptor.space() && this.dots.equals(keyDescriptor.dots()) && this.keyNameRes == keyDescriptor.keyNameRes()) {
                        return true;
                    }
                }
                return false;
            }

            public final int hashCode() {
                int i;
                if (true != this.space) {
                    i = 1237;
                } else {
                    i = 1231;
                }
                return ((((((i ^ 1000003) * 1000003) ^ this.dots.hashCode()) * 1000003) ^ 1237) * 1000003) ^ this.keyNameRes;
            }

            public final int keyNameRes() {
                return this.keyNameRes;
            }

            public final boolean space() {
                return this.space;
            }

            public final String toString() {
                return "KeyDescriptor{space=" + this.space + ", dots=" + String.valueOf(this.dots) + ", longPress=false, keyNameRes=" + this.keyNameRes + "}";
            }

            public KeyDescriptor(boolean z, BrailleCharacter brailleCharacter, int i) {
                this();
                this.space = z;
                this.dots = brailleCharacter;
                this.keyNameRes = i;
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public enum Subcategory {
            UNDEFINED(0),
            BASIC(R.string.bd_cmd_subcategory_title_basic),
            WINDOW(R.string.bd_cmd_subcategory_title_window),
            PLACE_ON_PAGE(R.string.bd_cmd_subcategory_title_place_on_page),
            WEB_CONTENT(R.string.bd_cmd_subcategory_title_web_content),
            READING_CONTROLS(R.string.bd_cmd_subcategory_title_reading_controls),
            AUTO_SCROLL(R.string.bd_cmd_subcategory_title_auto_scroll),
            MOVE_CURSOR(R.string.bd_cmd_subcategory_title_move_cursor),
            SELECT(R.string.bd_cmd_subcategory_title_select),
            EDIT(R.string.bd_cmd_subcategory_title_edit),
            SWITCH_KEYBOARD(R.string.bd_cmd_subcategory_title_switch_keyboard);

            public final int titleRes;

            Subcategory(int i) {
                this.titleRes = i;
            }
        }

        public SupportedCommand(int i, int i2, Category category, Subcategory subcategory, KeyDescriptor keyDescriptor) {
            this.command = i;
            this.commandDescriptionRes = i2;
            this.category = category;
            this.subcategory = subcategory;
            this.keyDescriptor = keyDescriptor;
        }

        public final String getKeyDescription(Resources resources) {
            String str;
            String str2;
            KeyDescriptor keyDescriptor = this.keyDescriptor;
            if (keyDescriptor.keyNameRes() != 0) {
                str2 = resources.getString(keyDescriptor.keyNameRes());
            } else {
                if (keyDescriptor.space()) {
                    str = resources.getString(R.string.bd_key_space);
                } else {
                    str = "";
                }
                if (!keyDescriptor.dots().equals(BrailleCharacter.EMPTY_CELL)) {
                    str2 = BrailleKeyBindingUtils.getDotsDescription(resources, keyDescriptor.dots());
                    if (!TextUtils.isEmpty(str)) {
                        str2 = resources.getString(R.string.bd_commands_delimiter, str, str2);
                    }
                } else {
                    str2 = str;
                }
            }
            return resources.getString(R.string.bd_commands_press_template, str2);
        }

        public final BrailleCharacter getPressDot() {
            return this.keyDescriptor.dots;
        }

        public final boolean hasSpace() {
            return this.keyDescriptor.space;
        }

        public SupportedCommand(int i, int i2, Category category, KeyDescriptor keyDescriptor) {
            this(i, i2, category, Subcategory.UNDEFINED, keyDescriptor);
        }
    }

    public static String changeToSentence(Resources resources, String[] strArr) {
        if (strArr.length > 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(strArr[0]);
            for (int i = 1; i < strArr.length; i++) {
                sb.append(resources.getString(R.string.split_comma, strArr[i]));
            }
            return sb.toString();
        }
        return TextUtils.join("", strArr);
    }

    public static BrailleKeyBinding getBrailleKeyBindingForCommand(int i, ArrayList arrayList) {
        BrailleKeyBinding brailleKeyBinding = new BrailleKeyBinding();
        brailleKeyBinding.command = i;
        int binarySearch = Collections.binarySearch(arrayList, brailleKeyBinding, COMPARE_BINDINGS_BY_COMMAND);
        if (binarySearch >= 0) {
            while (binarySearch > 0) {
                int i2 = binarySearch - 1;
                if (((BrailleKeyBinding) arrayList.get(i2)).command != i) {
                    break;
                }
                binarySearch = i2;
            }
            return (BrailleKeyBinding) arrayList.get(binarySearch);
        }
        return null;
    }

    public static String getDotsDescription(Resources resources, BrailleCharacter brailleCharacter) {
        NumberFormat numberInstance;
        String format;
        StringBuilder sb = new StringBuilder();
        numberInstance = NumberFormat.getNumberInstance(Locale.getDefault());
        int i = 0;
        while (i < brailleCharacter.dotNumbers.length()) {
            int i2 = i + 1;
            if (brailleCharacter.dotNumbers.get(i)) {
                format = numberInstance.format(i2);
                sb.append(format);
            }
            i = i2;
        }
        return resources.getQuantityString(R.plurals.braille_dots, brailleCharacter.getOnCount(), changeToSentence(resources, sb.toString().split("")));
    }

    public static ArrayList getSortedBindingsForDisplay(BrailleDisplayProperties brailleDisplayProperties) {
        ArrayList arrayList = new ArrayList(Arrays.asList(brailleDisplayProperties.keyBindings));
        Collections.sort(arrayList, COMPARE_BINDINGS);
        return arrayList;
    }

    public static List getSupportedCommands(Context context) {
        if (supportedCommands == null) {
            ArrayList arrayList = new ArrayList();
            SupportedCommand.Category category = SupportedCommand.Category.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging.setKeyNameRes$ar$ds(R.string.bd_key_pan_down);
            arrayList.add(new SupportedCommand(6, R.string.bd_cmd_nav_pan_down, category, builder$ar$class_merging$ar$class_merging.build()));
            SupportedCommand.Category category2 = SupportedCommand.Category.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging2 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging2.setKeyNameRes$ar$ds(R.string.bd_key_pan_up);
            arrayList.add(new SupportedCommand(5, R.string.bd_cmd_nav_pan_up, category2, builder$ar$class_merging$ar$class_merging2.build()));
            SupportedCommand.Category category3 = SupportedCommand.Category.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging3 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging3.setKeyNameRes$ar$ds(R.string.bd_key_route);
            arrayList.add(new SupportedCommand(50, R.string.bd_cmd_route, category3, builder$ar$class_merging$ar$class_merging3.build()));
            SupportedCommand.Category category4 = SupportedCommand.Category.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging4 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging4.setKeyNameRes$ar$ds(R.string.bd_key_route);
            arrayList.add(new SupportedCommand(20, R.string.bd_cmd_activate_current, category4, builder$ar$class_merging$ar$class_merging4.build()));
            SupportedCommand.Category category5 = SupportedCommand.Category.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging5 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging5.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging5.setDots$ar$ds(new BrailleCharacter(8));
            arrayList.add(new SupportedCommand(21, R.string.bd_cmd_touch_and_hold_current, category5, builder$ar$class_merging$ar$class_merging5.build()));
            SupportedCommand.Category category6 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory = SupportedCommand.Subcategory.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging6 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging6.setDots$ar$ds(new BrailleCharacter(1, 2, 7));
            arrayList.add(new SupportedCommand(3, R.string.bd_cmd_nav_item_previous, category6, subcategory, builder$ar$class_merging$ar$class_merging6.build()));
            SupportedCommand.Category category7 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory2 = SupportedCommand.Subcategory.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging7 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging7.setDots$ar$ds(new BrailleCharacter(4, 5, 8));
            arrayList.add(new SupportedCommand(4, R.string.bd_cmd_nav_item_next, category7, subcategory2, builder$ar$class_merging$ar$class_merging7.build()));
            SupportedCommand.Category category8 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory3 = SupportedCommand.Subcategory.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging8 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging8.setDots$ar$ds(new BrailleCharacter(2, 4, 6, 7));
            arrayList.add(new SupportedCommand(30, R.string.bd_cmd_scroll_backward, category8, subcategory3, builder$ar$class_merging$ar$class_merging8.build()));
            SupportedCommand.Category category9 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory4 = SupportedCommand.Subcategory.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging9 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging9.setDots$ar$ds(new BrailleCharacter(1, 3, 5, 8));
            arrayList.add(new SupportedCommand(31, R.string.bd_cmd_scroll_forward, category9, subcategory4, builder$ar$class_merging$ar$class_merging9.build()));
            SupportedCommand.Category category10 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory5 = SupportedCommand.Subcategory.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging10 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging10.setDots$ar$ds(new BrailleCharacter(3, 7));
            arrayList.add(new SupportedCommand(15, R.string.bd_cmd_move_by_reading_granularity_or_adjust_reading_control_backward, category10, subcategory5, builder$ar$class_merging$ar$class_merging10.build()));
            SupportedCommand.Category category11 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory6 = SupportedCommand.Subcategory.BASIC;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging11 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging11.setDots$ar$ds(new BrailleCharacter(6, 8));
            arrayList.add(new SupportedCommand(16, R.string.bd_cmd_move_by_reading_granularity_or_adjust_reading_control_forward, category11, subcategory6, builder$ar$class_merging$ar$class_merging11.build()));
            SupportedCommand.Category category12 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory7 = SupportedCommand.Subcategory.AUTO_SCROLL;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging12 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging12.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging12.setDots$ar$ds(new BrailleCharacter(1, 2, 4, 5, 6));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_TOGGLE_AUTO_SCROLL, R.string.bd_cmd_toggle_auto_scroll, category12, subcategory7, builder$ar$class_merging$ar$class_merging12.build()));
            SupportedCommand.Category category13 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory8 = SupportedCommand.Subcategory.AUTO_SCROLL;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging13 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging13.setDots$ar$ds(new BrailleCharacter(4));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_INCREASE_AUTO_SCROLL_DURATION, R.string.bd_cmd_increase_auto_scroll_duration, category13, subcategory8, builder$ar$class_merging$ar$class_merging13.build()));
            SupportedCommand.Category category14 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory9 = SupportedCommand.Subcategory.AUTO_SCROLL;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging14 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging14.setDots$ar$ds(new BrailleCharacter(1));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_DECREASE_AUTO_SCROLL_DURATION, R.string.bd_cmd_decrease_auto_scroll_duration, category14, subcategory9, builder$ar$class_merging$ar$class_merging14.build()));
            SupportedCommand.Category category15 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory10 = SupportedCommand.Subcategory.WINDOW;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging15 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging15.setDots$ar$ds(new BrailleCharacter(2, 4, 5, 6, 7));
            arrayList.add(new SupportedCommand(13, R.string.bd_cmd_nav_window_previous, category15, subcategory10, builder$ar$class_merging$ar$class_merging15.build()));
            SupportedCommand.Category category16 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory11 = SupportedCommand.Subcategory.WINDOW;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging16 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging16.setDots$ar$ds(new BrailleCharacter(2, 4, 5, 6, 8));
            arrayList.add(new SupportedCommand(14, R.string.bd_cmd_nav_window_next, category16, subcategory11, builder$ar$class_merging$ar$class_merging16.build()));
            SupportedCommand.Category category17 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory12 = SupportedCommand.Subcategory.PLACE_ON_PAGE;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging17 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging17.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging17.setDots$ar$ds(new BrailleCharacter(1, 2, 3));
            arrayList.add(new SupportedCommand(7, R.string.bd_cmd_nav_top, category17, subcategory12, builder$ar$class_merging$ar$class_merging17.build()));
            SupportedCommand.Category category18 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory13 = SupportedCommand.Subcategory.PLACE_ON_PAGE;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging18 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging18.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging18.setDots$ar$ds(new BrailleCharacter(4, 5, 6));
            arrayList.add(new SupportedCommand(8, R.string.bd_cmd_nav_bottom, category18, subcategory13, builder$ar$class_merging$ar$class_merging18.build()));
            SupportedCommand.Category category19 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory14 = SupportedCommand.Subcategory.READING_CONTROLS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging19 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging19.setDots$ar$ds(new BrailleCharacter(2, 3, 7));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_PREVIOUS_READING_CONTROL, R.string.bd_cmd_previous_reading_control, category19, subcategory14, builder$ar$class_merging$ar$class_merging19.build()));
            SupportedCommand.Category category20 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory15 = SupportedCommand.Subcategory.READING_CONTROLS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging20 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging20.setDots$ar$ds(new BrailleCharacter(5, 6, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_NEXT_READING_CONTROL, R.string.bd_cmd_next_reading_control, category20, subcategory15, builder$ar$class_merging$ar$class_merging20.build()));
            SupportedCommand.Category category21 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory16 = SupportedCommand.Subcategory.READING_CONTROLS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging21 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging21.setDots$ar$ds(new BrailleCharacter(1, 2, 5, 7));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_HEADING_PREVIOUS, R.string.bd_cmd_heading_previous, category21, subcategory16, builder$ar$class_merging$ar$class_merging21.build()));
            SupportedCommand.Category category22 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory17 = SupportedCommand.Subcategory.READING_CONTROLS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging22 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging22.setDots$ar$ds(new BrailleCharacter(1, 2, 5, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_HEADING_NEXT, R.string.bd_cmd_heading_next, category22, subcategory17, builder$ar$class_merging$ar$class_merging22.build()));
            SupportedCommand.Category category23 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory18 = SupportedCommand.Subcategory.READING_CONTROLS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging23 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging23.setDots$ar$ds(new BrailleCharacter(1, 4, 7));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_CONTROL_PREVIOUS, R.string.bd_cmd_control_previous, category23, subcategory18, builder$ar$class_merging$ar$class_merging23.build()));
            SupportedCommand.Category category24 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory19 = SupportedCommand.Subcategory.READING_CONTROLS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging24 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging24.setDots$ar$ds(new BrailleCharacter(1, 4, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_CONTROL_NEXT, R.string.bd_cmd_control_next, category24, subcategory19, builder$ar$class_merging$ar$class_merging24.build()));
            SupportedCommand.Category category25 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory20 = SupportedCommand.Subcategory.READING_CONTROLS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging25 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging25.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 7));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_LINK_PREVIOUS, R.string.bd_cmd_list_previous, category25, subcategory20, builder$ar$class_merging$ar$class_merging25.build()));
            SupportedCommand.Category category26 = SupportedCommand.Category.NAVIGATION;
            SupportedCommand.Subcategory subcategory21 = SupportedCommand.Subcategory.READING_CONTROLS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging26 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging26.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_LINK_NEXT, R.string.bd_cmd_list_next, category26, subcategory21, builder$ar$class_merging$ar$class_merging26.build()));
            SupportedCommand.Category category27 = SupportedCommand.Category.SYSTEM_ACTIONS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging27 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging27.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging27.setDots$ar$ds(new BrailleCharacter(1, 2));
            arrayList.add(new SupportedCommand(90, R.string.bd_cmd_global_back, category27, builder$ar$class_merging$ar$class_merging27.build()));
            SupportedCommand.Category category28 = SupportedCommand.Category.SYSTEM_ACTIONS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging28 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging28.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging28.setDots$ar$ds(new BrailleCharacter(1, 2, 5));
            arrayList.add(new SupportedCommand(91, R.string.bd_cmd_global_home, category28, builder$ar$class_merging$ar$class_merging28.build()));
            SupportedCommand.Category category29 = SupportedCommand.Category.SYSTEM_ACTIONS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging29 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging29.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging29.setDots$ar$ds(new BrailleCharacter(1, 3, 4, 5));
            arrayList.add(new SupportedCommand(93, R.string.bd_cmd_global_notifications, category29, builder$ar$class_merging$ar$class_merging29.build()));
            SupportedCommand.Category category30 = SupportedCommand.Category.SYSTEM_ACTIONS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging30 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging30.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging30.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 5));
            arrayList.add(new SupportedCommand(92, R.string.bd_cmd_global_recents, category30, builder$ar$class_merging$ar$class_merging30.build()));
            SupportedCommand.Category category31 = SupportedCommand.Category.SYSTEM_ACTIONS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging31 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging31.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging31.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 4, 5));
            arrayList.add(new SupportedCommand(94, R.string.bd_cmd_quick_settings, category31, builder$ar$class_merging$ar$class_merging31.build()));
            if (FeatureSupport.supportGetSystemActions(context)) {
                SupportedCommand.Category category32 = SupportedCommand.Category.SYSTEM_ACTIONS;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging32 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging32.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging32.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 4));
                arrayList.add(new SupportedCommand(95, R.string.bd_cmd_global_all_apps, category32, builder$ar$class_merging$ar$class_merging32.build()));
            }
            SupportedCommand.Category category33 = SupportedCommand.Category.TALKBACK_FEATURES;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging33 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging33.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging33.setDots$ar$ds(new BrailleCharacter(3, 4));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_TOGGLE_SCREEN_SEARCH, R.string.bd_cmd_toggle_screen_search, category33, builder$ar$class_merging$ar$class_merging33.build()));
            SupportedCommand.Category category34 = SupportedCommand.Category.TALKBACK_FEATURES;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging34 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging34.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging34.setDots$ar$ds(new BrailleCharacter(1, 3, 4, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL, R.string.bd_cmd_edit_custom_label, category34, builder$ar$class_merging$ar$class_merging34.build()));
            SupportedCommand.Category category35 = SupportedCommand.Category.TALKBACK_FEATURES;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging35 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging35.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging35.setDots$ar$ds(new BrailleCharacter(1, 3, 4));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_OPEN_TALKBACK_MENU, R.string.bd_cmd_open_talkback_menu, category35, builder$ar$class_merging$ar$class_merging35.build()));
            SupportedCommand.Category category36 = SupportedCommand.Category.TALKBACK_FEATURES;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging36 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging36.setDots$ar$ds(new BrailleCharacter(1, 3, 4, 7, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_TOGGLE_VOICE_FEEDBACK, R.string.bd_cmd_toggle_voice_feedback, category36, builder$ar$class_merging$ar$class_merging36.build()));
            SupportedCommand.Category category37 = SupportedCommand.Category.TALKBACK_FEATURES;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging37 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging37.setDots$ar$ds(new BrailleCharacter(7, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_STOP_READING, R.string.bd_cmd_read_stop, category37, builder$ar$class_merging$ar$class_merging37.build()));
            SupportedCommand.Category category38 = SupportedCommand.Category.TALKBACK_FEATURES;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging38 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging38.setDots$ar$ds(new BrailleCharacter(2, 3, 4, 5, 7, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_TALKBACK_SETTINGS, R.string.bd_cmd_talkback_settings, category38, builder$ar$class_merging$ar$class_merging38.build()));
            if (FeatureSupport.supportGetSystemActions(context) && BrailleDisplayConfig.playPauseMedia(context)) {
                SupportedCommand.Category category39 = SupportedCommand.Category.TALKBACK_FEATURES;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging39 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging39.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging39.setDots$ar$ds(new BrailleCharacter(7, 8));
                arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_PLAY_PAUSE_MEDIA, R.string.bd_cmd_play_pause_media, category39, builder$ar$class_merging$ar$class_merging39.build()));
            }
            SupportedCommand.Category category40 = SupportedCommand.Category.BRAILLE_SETTINGS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging40 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging40.setDots$ar$ds(new BrailleCharacter(2, 4, 7, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_SWITCH_TO_NEXT_INPUT_LANGUAGE, R.string.bd_cmd_switch_to_next_input_language, category40, builder$ar$class_merging$ar$class_merging40.build()));
            SupportedCommand.Category category41 = SupportedCommand.Category.BRAILLE_SETTINGS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging41 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging41.setDots$ar$ds(new BrailleCharacter(1, 3, 5, 7, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE, R.string.bd_cmd_switch_to_next_output_language, category41, builder$ar$class_merging$ar$class_merging41.build()));
            SupportedCommand.Category category42 = SupportedCommand.Category.BRAILLE_SETTINGS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging42 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging42.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging42.setDots$ar$ds(new BrailleCharacter(1, 2, 4, 5));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE, R.string.bd_cmd_toggle_contracted_mode, category42, builder$ar$class_merging$ar$class_merging42.build()));
            SupportedCommand.Category category43 = SupportedCommand.Category.BRAILLE_SETTINGS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging43 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging43.setDots$ar$ds(new BrailleCharacter(1, 3, 7, 8));
            arrayList.add(new SupportedCommand(100, R.string.bd_cmd_help, category43, builder$ar$class_merging$ar$class_merging43.build()));
            SupportedCommand.Category category44 = SupportedCommand.Category.BRAILLE_SETTINGS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging44 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging44.setDots$ar$ds(new BrailleCharacter(1, 2, 7, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_BRAILLE_DISPLAY_SETTINGS, R.string.bd_cmd_braille_display_settings, category44, builder$ar$class_merging$ar$class_merging44.build()));
            SupportedCommand.Category category45 = SupportedCommand.Category.BRAILLE_SETTINGS;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging45 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging45.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 4, 5, 6, 7, 8));
            arrayList.add(new SupportedCommand(BrailleInputEvent.CMD_TURN_OFF_BRAILLE_DISPLAY, R.string.bd_cmd_turn_off_braille_display, category45, builder$ar$class_merging$ar$class_merging45.build()));
            SupportedCommand.Category category46 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory22 = SupportedCommand.Subcategory.SWITCH_KEYBOARD;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging46 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging46.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging46.setDots$ar$ds(new BrailleCharacter(1, 3, 8));
            arrayList.add(new SupportedCommand(83, R.string.bd_cmd_switch_to_next_input_method, category46, subcategory22, builder$ar$class_merging$ar$class_merging46.build()));
            SupportedCommand.Category category47 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory23 = SupportedCommand.Subcategory.MOVE_CURSOR;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging47 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging47.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging47.setDots$ar$ds(new BrailleCharacter(3));
            arrayList.add(new SupportedCommand(9, R.string.bd_cmd_nav_character_previous, category47, subcategory23, builder$ar$class_merging$ar$class_merging47.build()));
            SupportedCommand.Category category48 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory24 = SupportedCommand.Subcategory.MOVE_CURSOR;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging48 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging48.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging48.setDots$ar$ds(new BrailleCharacter(6));
            arrayList.add(new SupportedCommand(10, R.string.bd_cmd_nav_character_next, category48, subcategory24, builder$ar$class_merging$ar$class_merging48.build()));
            SupportedCommand.Category category49 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory25 = SupportedCommand.Subcategory.MOVE_CURSOR;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging49 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging49.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging49.setDots$ar$ds(new BrailleCharacter(2));
            arrayList.add(new SupportedCommand(11, R.string.bd_cmd_nav_word_previous, category49, subcategory25, builder$ar$class_merging$ar$class_merging49.build()));
            SupportedCommand.Category category50 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory26 = SupportedCommand.Subcategory.MOVE_CURSOR;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging50 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging50.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging50.setDots$ar$ds(new BrailleCharacter(5));
            arrayList.add(new SupportedCommand(12, R.string.bd_cmd_nav_word_next, category50, subcategory26, builder$ar$class_merging$ar$class_merging50.build()));
            SupportedCommand.Category category51 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory27 = SupportedCommand.Subcategory.MOVE_CURSOR;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging51 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging51.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging51.setDots$ar$ds(new BrailleCharacter(1));
            arrayList.add(new SupportedCommand(1, R.string.bd_cmd_nav_line_previous, category51, subcategory27, builder$ar$class_merging$ar$class_merging51.build()));
            SupportedCommand.Category category52 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory28 = SupportedCommand.Subcategory.MOVE_CURSOR;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging52 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging52.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging52.setDots$ar$ds(new BrailleCharacter(4));
            arrayList.add(new SupportedCommand(2, R.string.bd_cmd_nav_line_next, category52, subcategory28, builder$ar$class_merging$ar$class_merging52.build()));
            if (BrailleDisplayConfig.selectAll(context)) {
                SupportedCommand.Category category53 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory29 = SupportedCommand.Subcategory.SELECT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging53 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging53.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging53.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 4, 5, 6, 8));
                arrayList.add(new SupportedCommand(42, R.string.bd_cmd_select_all, category53, subcategory29, builder$ar$class_merging$ar$class_merging53.build()));
            }
            if (BrailleDisplayConfig.selectCurrentToStartOrEnd(context)) {
                SupportedCommand.Category category54 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory30 = SupportedCommand.Subcategory.SELECT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging54 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging54.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging54.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 7, 8));
                arrayList.add(new SupportedCommand(46, R.string.bd_cmd_select_cursor_to_start, category54, subcategory30, builder$ar$class_merging$ar$class_merging54.build()));
                SupportedCommand.Category category55 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory31 = SupportedCommand.Subcategory.SELECT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging55 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging55.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging55.setDots$ar$ds(new BrailleCharacter(4, 5, 6, 7, 8));
                arrayList.add(new SupportedCommand(47, R.string.bd_cmd_select_cursor_to_end, category55, subcategory31, builder$ar$class_merging$ar$class_merging55.build()));
            }
            if (BrailleDisplayConfig.selectPreviousNextCharacterWordLine(context)) {
                SupportedCommand.Category category56 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory32 = SupportedCommand.Subcategory.SELECT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging56 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging56.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging56.setDots$ar$ds(new BrailleCharacter(3, 8));
                arrayList.add(new SupportedCommand(77, R.string.bd_cmd_select_previous_character, category56, subcategory32, builder$ar$class_merging$ar$class_merging56.build()));
                SupportedCommand.Category category57 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory33 = SupportedCommand.Subcategory.SELECT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging57 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging57.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging57.setDots$ar$ds(new BrailleCharacter(6, 8));
                arrayList.add(new SupportedCommand(78, R.string.bd_cmd_select_next_character, category57, subcategory33, builder$ar$class_merging$ar$class_merging57.build()));
                SupportedCommand.Category category58 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory34 = SupportedCommand.Subcategory.SELECT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging58 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging58.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging58.setDots$ar$ds(new BrailleCharacter(2, 8));
                arrayList.add(new SupportedCommand(79, R.string.bd_cmd_select_previous_word, category58, subcategory34, builder$ar$class_merging$ar$class_merging58.build()));
                SupportedCommand.Category category59 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory35 = SupportedCommand.Subcategory.SELECT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging59 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging59.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging59.setDots$ar$ds(new BrailleCharacter(5, 8));
                arrayList.add(new SupportedCommand(80, R.string.bd_cmd_select_next_word, category59, subcategory35, builder$ar$class_merging$ar$class_merging59.build()));
            }
            if (BrailleDisplayConfig.cutCopyPaste(context)) {
                SupportedCommand.Category category60 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory36 = SupportedCommand.Subcategory.EDIT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging60 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging60.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging60.setDots$ar$ds(new BrailleCharacter(1, 4, 8));
                arrayList.add(new SupportedCommand(44, R.string.bd_cmd_copy, category60, subcategory36, builder$ar$class_merging$ar$class_merging60.build()));
                SupportedCommand.Category category61 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory37 = SupportedCommand.Subcategory.EDIT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging61 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging61.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging61.setDots$ar$ds(new BrailleCharacter(1, 3, 4, 6, 8));
                arrayList.add(new SupportedCommand(43, R.string.bd_cmd_cut, category61, subcategory37, builder$ar$class_merging$ar$class_merging61.build()));
                SupportedCommand.Category category62 = SupportedCommand.Category.EDITING;
                SupportedCommand.Subcategory subcategory38 = SupportedCommand.Subcategory.EDIT;
                MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging62 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
                builder$ar$class_merging$ar$class_merging62.setSpace$ar$ds(true);
                builder$ar$class_merging$ar$class_merging62.setDots$ar$ds(new BrailleCharacter(1, 2, 3, 6, 8));
                arrayList.add(new SupportedCommand(45, R.string.bd_cmd_paste, category62, subcategory38, builder$ar$class_merging$ar$class_merging62.build()));
            }
            SupportedCommand.Category category63 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory39 = SupportedCommand.Subcategory.EDIT;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging63 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging63.setDots$ar$ds(new BrailleCharacter(7));
            arrayList.add(new SupportedCommand(71, R.string.bd_cmd_key_del, category63, subcategory39, builder$ar$class_merging$ar$class_merging63.build()));
            SupportedCommand.Category category64 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory40 = SupportedCommand.Subcategory.EDIT;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging64 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging64.setSpace$ar$ds(true);
            builder$ar$class_merging$ar$class_merging64.setDots$ar$ds(new BrailleCharacter(2, 7));
            arrayList.add(new SupportedCommand(72, R.string.bd_cmd_del_word, category64, subcategory40, builder$ar$class_merging$ar$class_merging64.build()));
            SupportedCommand.Category category65 = SupportedCommand.Category.EDITING;
            SupportedCommand.Subcategory subcategory41 = SupportedCommand.Subcategory.EDIT;
            MLKitLoggingOptions.Builder builder$ar$class_merging$ar$class_merging65 = SupportedCommand.KeyDescriptor.builder$ar$class_merging$ar$class_merging();
            builder$ar$class_merging$ar$class_merging65.setDots$ar$ds(new BrailleCharacter(8));
            arrayList.add(new SupportedCommand(70, R.string.bd_cmd_key_enter, category65, subcategory41, builder$ar$class_merging$ar$class_merging65.build()));
            supportedCommands = DesugarCollections.unmodifiableList(arrayList);
        }
        return supportedCommands;
    }
}

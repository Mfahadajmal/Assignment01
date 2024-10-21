package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.preference.base.GestureListPreference;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.AccessibilitySuiteDialogPreference;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import googledata.experiments.mobile.accessibility_suite.features.GestureConfig;
import j$.util.Collection;
import j$.util.Optional;
import j$.util.function.Predicate$CC;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GestureListPreference extends AccessibilitySuiteDialogPreference {
    private static final String TAG = "GestureListPreference";
    static final int TYPE_ACTION_ITEM = 1;
    static final int TYPE_TITLE = 0;
    private final FormFactorUtils formFactorUtils;
    private String initialValue;
    private ImmutableList<ActionItem> items;
    private String summaryWhenDisabled;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ActionItem implements Parcelable {
        private static final String ACTION_NO_VALUE = "";
        public static final Parcelable.Creator<ActionItem> CREATOR = new Parcelable.Creator<ActionItem>() { // from class: com.google.android.accessibility.talkback.preference.base.GestureListPreference.ActionItem.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ActionItem createFromParcel(Parcel parcel) {
                return new ActionItem(parcel.readString(), parcel.readString(), parcel.readInt());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ActionItem[] newArray(int i) {
                return new ActionItem[i];
            }
        };
        final String text;
        final String value;
        final int viewType;

        public ActionItem(String str, String str2, int i) {
            this.text = str;
            this.value = str2;
            this.viewType = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ImmutableList<ActionItem> createItemsFromArray(String[] strArr, String[] strArr2) {
            int length = strArr.length;
            if (length != strArr2.length) {
                LogUtils.e(GestureListPreference.TAG, "createItemsFromArray : Length doesn't match.", new Object[0]);
                int i = ImmutableList.ImmutableList$ar$NoOp;
                return RegularImmutableList.EMPTY;
            }
            if (length == 0) {
                LogUtils.e(GestureListPreference.TAG, "createItemsFromArray : Empty array", new Object[0]);
                int i2 = ImmutableList.ImmutableList$ar$NoOp;
                return RegularImmutableList.EMPTY;
            }
            ImmutableList.Builder builder = new ImmutableList.Builder();
            for (int i3 = 0; i3 < strArr.length; i3++) {
                builder.add$ar$ds$4f674a09_0(new ActionItem(strArr[i3], strArr2[i3], 1));
            }
            return builder.build();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.text);
            parcel.writeString(this.value);
            parcel.writeInt(this.viewType);
        }

        public ActionItem(String str, int i) {
            this(str, ACTION_NO_VALUE, i);
        }
    }

    /* compiled from: PG */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface ActionViewType {
    }

    public GestureListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.formFactorUtils = FormFactorUtils.getInstance();
        setSingleLineTitle(false);
        populateActionItems();
    }

    private static void addActionItemsToList(ImmutableList.Builder<ActionItem> builder, ImmutableList<ActionItem> immutableList) {
        if (immutableList.size() == 1 && immutableList.get(0).viewType == 0) {
            return;
        }
        builder.addAll$ar$ds$2104aa48_0(immutableList);
    }

    private ImmutableList.Builder<ActionItem> createActionListBuilder(int i, int i2, int i3) {
        ImmutableList.Builder<ActionItem> builder = new ImmutableList.Builder<>();
        Resources resources = getContext().getResources();
        builder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(i), 0));
        if (i2 != 0) {
            builder.addAll$ar$ds$2104aa48_0(ActionItem.createItemsFromArray(resources.getStringArray(i2), resources.getStringArray(i3)));
        }
        return builder;
    }

    private ImmutableList<ActionItem> createBasicNavigation() {
        ImmutableList.Builder<ActionItem> createActionListBuilder = createActionListBuilder(R.string.shortcut_title_basic_navigation, R.array.shortcut_basic_navigation, R.array.shortcut_value_basic_navigation);
        Resources resources = getContext().getResources();
        if (FeatureSupport.isMultiFingerGestureSupported() && !this.formFactorUtils.isAndroidWear) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_scroll_up), resources.getString(R.string.shortcut_value_scroll_up), 1));
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_scroll_down), resources.getString(R.string.shortcut_value_scroll_down), 1));
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_scroll_left), resources.getString(R.string.shortcut_value_scroll_left), 1));
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_scroll_right), resources.getString(R.string.shortcut_value_scroll_right), 1));
        }
        return createActionListBuilder.build();
    }

    private ImmutableList<ActionItem> createMenuControl() {
        return createActionListBuilder(R.string.shortcut_title_menu_control, R.array.shortcut_menu_control, R.array.shortcut_value_menu_control).build();
    }

    private ImmutableList<ActionItem> createReadingControl() {
        return createActionListBuilder(R.string.shortcut_title_reading_control, R.array.shortcut_reading_control, R.array.shortcut_value_reading_control).build();
    }

    private ImmutableList<ActionItem> createSpecialFeatures() {
        ImmutableList.Builder<ActionItem> createActionListBuilder = createActionListBuilder(R.string.shortcut_title_special_features, 0, 0);
        Resources resources = getContext().getResources();
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_media_control), resources.getString(R.string.shortcut_value_media_control), 1));
        }
        createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_increase_volume), resources.getString(R.string.shortcut_value_increase_volume), 1));
        createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_decrease_volume), resources.getString(R.string.shortcut_value_decrease_volume), 1));
        if (!FormFactorUtils.getInstance().isAndroidWear) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_voice_commands), resources.getString(R.string.shortcut_value_voice_commands), 1));
        }
        if (!this.formFactorUtils.isAndroidWear) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.title_show_screen_search), resources.getString(R.string.shortcut_value_screen_search), 1));
        }
        createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.title_show_hide_screen), resources.getString(R.string.shortcut_value_show_hide_screen), 1));
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_pass_through_next), resources.getString(R.string.shortcut_value_pass_through_next_gesture), 1));
        }
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(getContext());
        if (sharedPreferences.getBoolean(resources.getString(R.string.pref_tree_debug_key), false)) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_print_node_tree), resources.getString(R.string.shortcut_value_print_node_tree), 1));
        }
        if (sharedPreferences.getBoolean(resources.getString(R.string.pref_performance_stats_key), false)) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_print_performance_stats), resources.getString(R.string.shortcut_value_print_performance_stats), 1));
        }
        createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_show_custom_actions), resources.getString(R.string.shortcut_value_show_custom_actions), 1));
        if (FeatureSupport.supportBrailleDisplay(getContext())) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_braille_display_settings), resources.getString(R.string.shortcut_value_braille_display_settings), 1));
        }
        createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_tutorial), resources.getString(R.string.shortcut_value_tutorial), 1));
        if (!this.formFactorUtils.isAndroidWear) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_practice_gestures), resources.getString(R.string.shortcut_value_practice_gestures), 1));
        }
        if (FeatureSupport.supportBrailleDisplay(getContext())) {
            if (GestureConfig.INSTANCE.get().handleGestureBrailleDisplayOnOff(getContext())) {
                createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_toggle_braille_display), resources.getString(R.string.shortcut_value_toggle_braille_display), 1));
            }
        }
        if (ImageCaptioner.supportsImageCaption$ar$ds()) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.title_image_caption), resources.getString(R.string.shortcut_value_describe_image), 1));
        }
        return createActionListBuilder.build();
    }

    private ImmutableList<ActionItem> createSystemActions() {
        ImmutableList.Builder<ActionItem> createActionListBuilder = createActionListBuilder(R.string.shortcut_title_system_actions, R.array.shortcut_system_actions, R.array.shortcut_value_system_actions);
        Resources resources = getContext().getResources();
        if (FeatureSupport.supportGetSystemActions(getContext())) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_all_apps), resources.getString(R.string.shortcut_value_all_apps), 1));
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_a11y_button), resources.getString(R.string.shortcut_value_a11y_button), 1));
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_a11y_button_long_press), resources.getString(R.string.shortcut_value_a11y_button_long_press), 1));
        }
        return createActionListBuilder.build();
    }

    private ImmutableList<ActionItem> createTextEditing() {
        ImmutableList.Builder<ActionItem> createActionListBuilder = createActionListBuilder(R.string.shortcut_title_text_editing, R.array.shortcut_text_editing, R.array.shortcut_value_text_editing);
        Resources resources = getContext().getResources();
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && !this.formFactorUtils.isAndroidWear) {
            createActionListBuilder.add$ar$ds$4f674a09_0(new ActionItem(resources.getString(R.string.shortcut_braille_keyboard), resources.getString(R.string.shortcut_value_braille_keyboard), 1));
        }
        return createActionListBuilder.build();
    }

    private String getCurrentActionText() {
        String str;
        if (!isEnabled() && (str = this.summaryWhenDisabled) != null) {
            return str;
        }
        final String currentValue = getCurrentValue();
        Optional findFirst = Collection.EL.stream(this.items).filter(new Predicate() { // from class: com.google.android.accessibility.talkback.preference.base.GestureListPreference$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = TextUtils.equals(((GestureListPreference.ActionItem) obj).value, currentValue);
                return equals;
            }
        }).findFirst();
        if (findFirst.isPresent()) {
            return ((ActionItem) findFirst.get()).text;
        }
        LogUtils.w(TAG, "%s - Can't find the value from supported action list.", getTitle());
        return getContext().getResources().getString(R.string.shortcut_unassigned);
    }

    private void populateActionItems() {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.add$ar$ds$4f674a09_0(new ActionItem(getContext().getResources().getString(R.string.shortcut_unassigned), getContext().getResources().getString(R.string.shortcut_value_unassigned), 1));
        addActionItemsToList(builder, createBasicNavigation());
        addActionItemsToList(builder, createSystemActions());
        addActionItemsToList(builder, createReadingControl());
        addActionItemsToList(builder, createMenuControl());
        addActionItemsToList(builder, createTextEditing());
        addActionItemsToList(builder, createSpecialFeatures());
        this.items = builder.build();
    }

    public GesturePreferenceFragmentCompat createDialogFragment() {
        return GesturePreferenceFragmentCompat.create(this);
    }

    public ActionItem[] getActionItems() {
        ImmutableList<ActionItem> immutableList = this.items;
        return (ActionItem[]) immutableList.toArray(new ActionItem[immutableList.size()]);
    }

    public String getCurrentValue() {
        String string = getPreferenceManager().getSharedPreferences().getString(getKey(), this.initialValue);
        if (TextUtils.equals(string, getContext().getString(R.string.shortcut_value_local_breakout))) {
            return getContext().getString(R.string.shortcut_value_talkback_breakout);
        }
        if (TextUtils.equals(string, getContext().getString(R.string.shortcut_value_editing))) {
            return getContext().getString(R.string.shortcut_value_show_custom_actions);
        }
        return string;
    }

    public String getDefaultValue() {
        return this.initialValue;
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        return getCurrentActionText();
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        String string = typedArray.getString(i);
        this.initialValue = string;
        return string;
    }

    public void setSummaryWhenDisabled(String str) {
        this.summaryWhenDisabled = str;
    }

    public void setValue(String str) {
        getPreferenceManager().getSharedPreferences().edit().putString(getKey(), str).apply();
    }

    public void updateSummaryToDefaultValue() {
        setSummary(GestureShortcutMapping.getActionString(getContext(), this.initialValue));
    }
}

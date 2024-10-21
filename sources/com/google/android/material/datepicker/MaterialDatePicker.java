package com.google.android.material.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.BackStackRecord;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.BlendModeUtils$Api29Impl;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat$Api30Impl;
import androidx.core.view.WindowCompat$Api35Impl;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialDatePicker extends DialogFragment {
    private MaterialShapeDrawable background;
    private MaterialCalendar calendar;
    private CalendarConstraints calendarConstraints;
    public Button confirmButton;
    private DateSelector dateSelector;
    private DayViewDecorator dayViewDecorator;
    private boolean edgeToEdgeEnabled;
    private CharSequence fullTitleText;
    private boolean fullscreen;
    private TextView headerSelectionText;
    private TextView headerTitleTextView;
    public CheckableImageButton headerToggleButton;
    public int inputMode;
    private CharSequence negativeButtonContentDescription;
    private int negativeButtonContentDescriptionResId;
    private CharSequence negativeButtonText;
    private int negativeButtonTextResId;
    private int overrideThemeResId;
    private PickerFragment pickerFragment;
    private CharSequence positiveButtonContentDescription;
    private int positiveButtonContentDescriptionResId;
    private CharSequence positiveButtonText;
    private int positiveButtonTextResId;
    private CharSequence singleLineTitleText;
    private CharSequence titleText;
    private int titleTextResId;
    public final LinkedHashSet onPositiveButtonClickListeners = new LinkedHashSet();
    public final LinkedHashSet onNegativeButtonClickListeners = new LinkedHashSet();
    private final LinkedHashSet onCancelListeners = new LinkedHashSet();
    private final LinkedHashSet onDismissListeners = new LinkedHashSet();

    private static int getPaddedPickerWidth(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_content_padding);
        int i = new Month(UtcDates.getTodayCalendar()).daysInWeek;
        return dimensionPixelOffset + dimensionPixelOffset + (resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_width) * i) + ((i - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_horizontal_padding));
    }

    private final int getThemeResId(Context context) {
        int i = this.overrideThemeResId;
        if (i != 0) {
            return i;
        }
        return getDateSelector().getDefaultThemeResId$ar$ds();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFullscreen(Context context) {
        return readMaterialCalendarStyleBoolean(context, android.R.attr.windowFullscreen);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean readMaterialCalendarStyleBoolean(Context context, int i) {
        int i2;
        i2 = DrawableUtils$OutlineCompatR.resolveTypedValueOrThrow(context, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()).data;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, new int[]{i});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    public final DateSelector getDateSelector() {
        if (this.dateSelector == null) {
            this.dateSelector = (DateSelector) getArguments().getParcelable("DATE_SELECTOR_KEY");
        }
        return this.dateSelector;
    }

    public final String getHeaderText() {
        return getDateSelector().getSelectionDisplayString$ar$ds();
    }

    @Override // android.support.v4.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.onCancelListeners.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.overrideThemeResId = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.dateSelector = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.calendarConstraints = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.dayViewDecorator = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.titleTextResId = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.titleText = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.inputMode = bundle.getInt("INPUT_MODE_KEY");
        this.positiveButtonTextResId = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.positiveButtonText = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.positiveButtonContentDescriptionResId = bundle.getInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.positiveButtonContentDescription = bundle.getCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        this.negativeButtonTextResId = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.negativeButtonText = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
        this.negativeButtonContentDescriptionResId = bundle.getInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.negativeButtonContentDescription = bundle.getCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        CharSequence charSequence = this.titleText;
        if (charSequence == null) {
            charSequence = requireContext().getResources().getText(this.titleTextResId);
        }
        this.fullTitleText = charSequence;
        if (charSequence != null) {
            CharSequence[] split = TextUtils.split(charSequence.toString(), "\n");
            if (split.length > 1) {
                charSequence = split[0];
            }
        } else {
            charSequence = null;
        }
        this.singleLineTitleText = charSequence;
    }

    @Override // android.support.v4.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(requireContext(), getThemeResId(requireContext()));
        Context context = dialog.getContext();
        this.fullscreen = isFullscreen(context);
        this.background = new MaterialShapeDrawable(context, null, R.attr.materialCalendarStyle, R.style.Widget_MaterialComponents_MaterialCalendar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.MaterialCalendar, R.attr.materialCalendarStyle, R.style.Widget_MaterialComponents_MaterialCalendar);
        int color = obtainStyledAttributes.getColor(1, 0);
        obtainStyledAttributes.recycle();
        this.background.initializeElevationOverlay(context);
        this.background.setFillColor(ColorStateList.valueOf(color));
        this.background.setElevation(ViewCompat.Api21Impl.getElevation(dialog.getWindow().getDecorView()));
        return dialog;
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        boolean z;
        if (true != this.fullscreen) {
            i = R.layout.mtrl_picker_dialog;
        } else {
            i = R.layout.mtrl_picker_fullscreen;
        }
        View inflate = layoutInflater.inflate(i, viewGroup);
        Context context = inflate.getContext();
        if (this.dayViewDecorator == null) {
            if (this.fullscreen) {
                inflate.findViewById(R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(getPaddedPickerWidth(context), -2));
            } else {
                inflate.findViewById(R.id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(getPaddedPickerWidth(context), -1));
            }
            TextView textView = (TextView) inflate.findViewById(R.id.mtrl_picker_header_selection_text);
            this.headerSelectionText = textView;
            textView.setAccessibilityLiveRegion(1);
            this.headerToggleButton = (CheckableImageButton) inflate.findViewById(R.id.mtrl_picker_header_toggle);
            this.headerTitleTextView = (TextView) inflate.findViewById(R.id.mtrl_picker_title_text);
            this.headerToggleButton.setTag("TOGGLE_BUTTON_TAG");
            CheckableImageButton checkableImageButton = this.headerToggleButton;
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, AppCompatDelegate.Api33Impl.getDrawable(context, R.drawable.material_ic_calendar_black_24dp));
            stateListDrawable.addState(new int[0], AppCompatDelegate.Api33Impl.getDrawable(context, R.drawable.material_ic_edit_black_24dp));
            checkableImageButton.setImageDrawable(stateListDrawable);
            CheckableImageButton checkableImageButton2 = this.headerToggleButton;
            if (this.inputMode != 0) {
                z = true;
            } else {
                z = false;
            }
            checkableImageButton2.setChecked(z);
            ViewCompat.setAccessibilityDelegate(this.headerToggleButton, null);
            updateToggleContentDescription(this.headerToggleButton);
            this.headerToggleButton.setOnClickListener(new TrainingActivity$$ExternalSyntheticLambda0(this, 11));
            this.confirmButton = (Button) inflate.findViewById(R.id.confirm_button);
            if (getDateSelector().isSelectionComplete()) {
                this.confirmButton.setEnabled(true);
            } else {
                this.confirmButton.setEnabled(false);
            }
            this.confirmButton.setTag("CONFIRM_BUTTON_TAG");
            CharSequence charSequence = this.positiveButtonText;
            if (charSequence != null) {
                this.confirmButton.setText(charSequence);
            } else {
                int i2 = this.positiveButtonTextResId;
                if (i2 != 0) {
                    this.confirmButton.setText(i2);
                }
            }
            CharSequence charSequence2 = this.positiveButtonContentDescription;
            if (charSequence2 != null) {
                this.confirmButton.setContentDescription(charSequence2);
            } else if (this.positiveButtonContentDescriptionResId != 0) {
                this.confirmButton.setContentDescription(getContext().getResources().getText(this.positiveButtonContentDescriptionResId));
            }
            this.confirmButton.setOnClickListener(new TrainingActivity$$ExternalSyntheticLambda0(this, 12, null));
            Button button = (Button) inflate.findViewById(R.id.cancel_button);
            button.setTag("CANCEL_BUTTON_TAG");
            CharSequence charSequence3 = this.negativeButtonText;
            if (charSequence3 != null) {
                button.setText(charSequence3);
            } else {
                int i3 = this.negativeButtonTextResId;
                if (i3 != 0) {
                    button.setText(i3);
                }
            }
            CharSequence charSequence4 = this.negativeButtonContentDescription;
            if (charSequence4 != null) {
                button.setContentDescription(charSequence4);
            } else if (this.negativeButtonContentDescriptionResId != 0) {
                button.setContentDescription(getContext().getResources().getText(this.negativeButtonContentDescriptionResId));
            }
            button.setOnClickListener(new TrainingActivity$$ExternalSyntheticLambda0(this, 13, null));
            return inflate;
        }
        throw null;
    }

    @Override // android.support.v4.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.onDismissListeners.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        Month month;
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.overrideThemeResId);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.dateSelector);
        CalendarConstraints.Builder builder = new CalendarConstraints.Builder(this.calendarConstraints);
        MaterialCalendar materialCalendar = this.calendar;
        Month month2 = null;
        if (materialCalendar == null) {
            month = null;
        } else {
            month = materialCalendar.current;
        }
        if (month != null) {
            builder.openAt = Long.valueOf(month.timeInMillis);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", builder.validator);
        Month create = Month.create(builder.start);
        Month create2 = Month.create(builder.end);
        CalendarConstraints.DateValidator dateValidator = (CalendarConstraints.DateValidator) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l = builder.openAt;
        if (l != null) {
            month2 = Month.create(l.longValue());
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new CalendarConstraints(create, create2, dateValidator, month2, builder.firstDayOfWeek));
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.dayViewDecorator);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.titleTextResId);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.titleText);
        bundle.putInt("INPUT_MODE_KEY", this.inputMode);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.positiveButtonTextResId);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.positiveButtonText);
        bundle.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.positiveButtonContentDescriptionResId);
        bundle.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.positiveButtonContentDescription);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.negativeButtonTextResId);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.negativeButtonText);
        bundle.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.negativeButtonContentDescriptionResId);
        bundle.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.negativeButtonContentDescription);
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onStart() {
        Integer num;
        boolean z;
        int i;
        super.onStart();
        Window window = requireDialog().getWindow();
        if (this.fullscreen) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.background);
            if (!this.edgeToEdgeEnabled) {
                final View findViewById = requireView().findViewById(R.id.fullscreen_header);
                ColorStateList colorStateListOrNull = DrawableUtils$OutlineCompatR.getColorStateListOrNull(findViewById.getBackground());
                if (colorStateListOrNull != null) {
                    num = Integer.valueOf(colorStateListOrNull.getDefaultColor());
                } else {
                    num = null;
                }
                if (num != null && num.intValue() != 0) {
                    z = false;
                } else {
                    z = true;
                }
                int color = FileUtils.getColor(window.getContext(), android.R.attr.colorBackground, -16777216);
                if (z) {
                    num = Integer.valueOf(color);
                }
                Integer valueOf = Integer.valueOf(color);
                if (Build.VERSION.SDK_INT >= 35) {
                    WindowCompat$Api35Impl.setDecorFitsSystemWindows(window, false);
                } else if (Build.VERSION.SDK_INT >= 30) {
                    WindowCompat$Api30Impl.setDecorFitsSystemWindows(window, false);
                } else {
                    View decorView = window.getDecorView();
                    decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 1792);
                }
                window.getContext();
                Context context = window.getContext();
                if (Build.VERSION.SDK_INT < 27) {
                    i = ColorUtils.setAlphaComponent(FileUtils.getColor(context, android.R.attr.navigationBarColor, -16777216), BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                } else {
                    i = 0;
                }
                window.setStatusBarColor(0);
                window.setNavigationBarColor(i);
                ((BlendModeUtils$Api29Impl) new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(window, window.getDecorView()).mInfo).setAppearanceLightStatusBars(DrawableUtils$OutlineCompatR.isUsingLightSystemBar(0, FileUtils.isColorLight(num.intValue())));
                valueOf.getClass();
                ((BlendModeUtils$Api29Impl) new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(window, window.getDecorView()).mInfo).setAppearanceLightNavigationBars(DrawableUtils$OutlineCompatR.isUsingLightSystemBar(i, FileUtils.isColorLight(color)));
                final int paddingTop = findViewById.getPaddingTop();
                final int i2 = findViewById.getLayoutParams().height;
                ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(findViewById, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.datepicker.MaterialDatePicker.3
                    @Override // androidx.core.view.OnApplyWindowInsetsListener
                    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                        int i3 = windowInsetsCompat.getInsets(7).top;
                        int i4 = i2;
                        if (i4 >= 0) {
                            findViewById.getLayoutParams().height = i4 + i3;
                            View view2 = findViewById;
                            view2.setLayoutParams(view2.getLayoutParams());
                        }
                        View view3 = findViewById;
                        view3.setPadding(view3.getPaddingLeft(), paddingTop + i3, view3.getPaddingRight(), view3.getPaddingBottom());
                        return windowInsetsCompat;
                    }
                });
                this.edgeToEdgeEnabled = true;
            }
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.background, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(requireDialog(), rect));
        }
        startPickerFragment();
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onStop() {
        this.pickerFragment.onSelectionChangedListeners.clear();
        super.onStop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.google.android.material.datepicker.MaterialTextInputPicker] */
    public final void startPickerFragment() {
        CharSequence charSequence;
        int themeResId = getThemeResId(requireContext());
        DateSelector dateSelector = getDateSelector();
        CalendarConstraints calendarConstraints = this.calendarConstraints;
        DayViewDecorator dayViewDecorator = this.dayViewDecorator;
        MaterialCalendar materialCalendar = new MaterialCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", themeResId);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", dayViewDecorator);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.openAt);
        materialCalendar.setArguments(bundle);
        this.calendar = materialCalendar;
        if (this.inputMode == 1) {
            DateSelector dateSelector2 = getDateSelector();
            CalendarConstraints calendarConstraints2 = this.calendarConstraints;
            ?? materialTextInputPicker = new MaterialTextInputPicker();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("THEME_RES_ID_KEY", themeResId);
            bundle2.putParcelable("DATE_SELECTOR_KEY", dateSelector2);
            bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
            materialTextInputPicker.setArguments(bundle2);
            materialCalendar = materialTextInputPicker;
        }
        this.pickerFragment = materialCalendar;
        TextView textView = this.headerTitleTextView;
        if (this.inputMode == 1 && getResources().getConfiguration().orientation == 2) {
            charSequence = this.singleLineTitleText;
        } else {
            charSequence = this.fullTitleText;
        }
        textView.setText(charSequence);
        updateHeader(getHeaderText());
        BackStackRecord backStackRecord = new BackStackRecord(getChildFragmentManager());
        backStackRecord.replace(R.id.mtrl_calendar_frame, this.pickerFragment);
        backStackRecord.commitNow();
        this.pickerFragment.addOnSelectionChangedListener(new OnSelectionChangedListener() { // from class: com.google.android.material.datepicker.MaterialDatePicker.4
            @Override // com.google.android.material.datepicker.OnSelectionChangedListener
            public final void onSelectionChanged(Object obj) {
                MaterialDatePicker materialDatePicker = MaterialDatePicker.this;
                materialDatePicker.updateHeader(materialDatePicker.getHeaderText());
                MaterialDatePicker materialDatePicker2 = MaterialDatePicker.this;
                materialDatePicker2.confirmButton.setEnabled(materialDatePicker2.getDateSelector().isSelectionComplete());
            }
        });
    }

    final void updateHeader(String str) {
        TextView textView = this.headerSelectionText;
        DateSelector dateSelector = getDateSelector();
        requireContext();
        textView.setContentDescription(dateSelector.getSelectionContentDescription$ar$ds());
        this.headerSelectionText.setText(str);
    }

    public final void updateToggleContentDescription(CheckableImageButton checkableImageButton) {
        String string;
        if (this.inputMode == 1) {
            string = checkableImageButton.getContext().getString(R.string.mtrl_picker_toggle_to_calendar_input_mode);
        } else {
            string = checkableImageButton.getContext().getString(R.string.mtrl_picker_toggle_to_text_input_mode);
        }
        this.headerToggleButton.setContentDescription(string);
    }
}

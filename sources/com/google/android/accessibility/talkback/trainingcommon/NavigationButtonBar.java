package com.google.android.accessibility.talkback.trainingcommon;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda2;
import com.google.android.accessibility.talkback.trainingcommon.tv.TvNavigationButton;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavigationButtonBar extends LinearLayout {
    public static final ImmutableList DEFAULT_BUTTONS = ImmutableList.of((Object) 0, (Object) 1, (Object) 2);
    private final int currentPageNumber;
    private final boolean isExitButtonOnlyShowOnLastPage;
    private final boolean isFirstPage;
    private final boolean isLastPage;
    private final boolean isPrevButtonShownOnFirstPage;
    private final LinearLayout navigationBarLayout;
    private final List navigationButtons;
    public final WindowTrackerFactory navigationListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public NavigationButtonBar(Context context, List list, WindowTrackerFactory windowTrackerFactory, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        super(context);
        this.navigationBarLayout = (LinearLayout) inflate(context, R.layout.training_navigation_button_bar, this).findViewById(R.id.training_navigation);
        this.navigationButtons = list;
        this.navigationListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = windowTrackerFactory;
        this.currentPageNumber = i;
        this.isFirstPage = z;
        this.isLastPage = z2;
        this.isExitButtonOnlyShowOnLastPage = z3;
        this.isPrevButtonShownOnFirstPage = z4;
        if (FormFactorUtils.getInstance().isAndroidTv) {
            setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            setClipChildren(false);
            setClipToPadding(false);
        }
        if (FormFactorUtils.getInstance().isAndroidWear) {
            int buttonsCount = getButtonsCount();
            if (z2 && hasButton(2)) {
                addButton(3, buttonsCount);
            }
            if (!z2 && !z3 && hasButton(2)) {
                addButton(2, buttonsCount);
            }
            if (!z2 && hasButton(1)) {
                addButton(1, buttonsCount);
                return;
            }
            return;
        }
        int buttonsCount2 = getButtonsCount();
        if (z2 && hasButton(2)) {
            addButton(3, buttonsCount2);
        }
        if (!z2 && hasButton(1)) {
            addButton(1, buttonsCount2);
        }
        if ((!z || z4) && hasButton(0)) {
            addButton(0, buttonsCount2);
        }
        if (!z2 && !z3 && hasButton(2)) {
            addButton(2, buttonsCount2);
        }
    }

    private final void addButton(int i, int i2) {
        if (i != 0) {
            int i3 = 1;
            if (i != 1) {
                if (i != 2) {
                    this.navigationBarLayout.addView(createButton(getContext(), i, R.string.training_finish_button, new TrainingActivity$$ExternalSyntheticLambda0(this, i3), i2));
                    return;
                } else {
                    Button createButton = createButton(getContext(), i, R.string.training_finish_button, new UIManager$$ExternalSyntheticLambda2(this, 20), i2);
                    createButton.setContentDescription(getContext().getString(R.string.training_finish_tutorial));
                    this.navigationBarLayout.addView(createButton);
                    return;
                }
            }
            this.navigationBarLayout.addView(createButton(getContext(), i, R.string.training_next_button, new UIManager$$ExternalSyntheticLambda2(this, 19), i2));
            return;
        }
        this.navigationBarLayout.addView(createButton(getContext(), i, R.string.training_back_button, new UIManager$$ExternalSyntheticLambda2(this, 18), i2));
    }

    private final Button createButton(Context context, int i, int i2, View.OnClickListener onClickListener, int i3) {
        Button button;
        int i4;
        int i5;
        if (FormFactorUtils.getInstance().isAndroidTv) {
            TvNavigationButton tvNavigationButton = new TvNavigationButton(context);
            tvNavigationButton.setText(context.getString(i2));
            tvNavigationButton.setOnClickListener(onClickListener);
            return tvNavigationButton;
        }
        if (SpannableUtils$NonCopyableTextSpan.supportMaterialComponent(context)) {
            if (i != 1 && i != 3) {
                if (i != 2 && i != 0) {
                    button = SpannableUtils$NonCopyableTextSpan.createButton$ar$edu(context, 3);
                } else {
                    button = SpannableUtils$NonCopyableTextSpan.createButton$ar$edu(context, 2);
                }
            } else {
                button = SpannableUtils$NonCopyableTextSpan.createButton$ar$edu(context, 1);
            }
        } else {
            button = new Button(context);
            button.setBackgroundColor(context.getResources().getColor(R.color.training_navigation_button_bar_background_color, null));
            button.setTextColor(context.getResources().getColor(R.color.training_button_text_color, null));
        }
        button.setText(i2);
        button.setTypeface(Typeface.create(context.getString(R.string.accessibility_font), 0));
        button.setTextSize(0, context.getResources().getDimensionPixelSize(R.dimen.training_button_text_size));
        button.setPaddingRelative(0, 0, 0, context.getResources().getDimensionPixelSize(R.dimen.training_button_text_padding_bottom));
        button.setAllCaps(false);
        button.setEllipsize(TextUtils.TruncateAt.END);
        button.setLines(1);
        int i6 = this.currentPageNumber;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    i4 = R.id.training_finish_button_1;
                    i5 = R.id.training_finish_button_0;
                } else {
                    i4 = R.id.training_exit_button_1;
                    i5 = R.id.training_exit_button_0;
                }
            } else {
                i4 = R.id.training_next_button_1;
                i5 = R.id.training_next_button_0;
            }
        } else {
            i4 = R.id.training_back_button_1;
            i5 = R.id.training_back_button_0;
        }
        if (i6 % 2 == 0) {
            i4 = i5;
        }
        button.setId(i4);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, (int) context.getResources().getDimension(R.dimen.training_button_height), 1.0f);
        if (SpannableUtils$NonCopyableTextSpan.supportMaterialComponent(context)) {
            int i7 = R.dimen.training_button_margin_2dp;
            int i8 = R.dimen.training_button_margin_24dp;
            if (i3 == 2) {
                if (i != 1 && i != 3) {
                    if (i == 2 || i == 0) {
                        i7 = R.dimen.training_button_margin_8dp;
                    }
                    i8 = R.dimen.training_button_margin_2dp;
                } else {
                    i7 = R.dimen.training_button_margin_24dp;
                    i8 = R.dimen.training_button_margin_8dp;
                }
                layoutParams.leftMargin = (int) context.getResources().getDimension(i7);
                layoutParams.rightMargin = (int) context.getResources().getDimension(i8);
            } else {
                if (i3 == 1) {
                    i7 = R.dimen.training_button_margin_24dp;
                    layoutParams.leftMargin = (int) context.getResources().getDimension(i7);
                    layoutParams.rightMargin = (int) context.getResources().getDimension(i8);
                }
                i8 = R.dimen.training_button_margin_2dp;
                layoutParams.leftMargin = (int) context.getResources().getDimension(i7);
                layoutParams.rightMargin = (int) context.getResources().getDimension(i8);
            }
        }
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(onClickListener);
        return button;
    }

    private final int getButtonsCount() {
        int i;
        if (this.isLastPage && hasButton(2)) {
            i = 1;
        } else {
            i = 0;
        }
        if (!this.isLastPage && hasButton(1)) {
            i++;
        }
        if (!this.isFirstPage && hasButton(0)) {
            i++;
        }
        if (!this.isLastPage && !this.isExitButtonOnlyShowOnLastPage && hasButton(2)) {
            return i + 1;
        }
        return i;
    }

    private final boolean hasButton(int i) {
        return this.navigationButtons.contains(Integer.valueOf(i));
    }
}

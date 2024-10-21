package com.google.android.libraries.surveys.internal.resourceutils;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.surveys_android.features.HatsV1M5Bugfixes;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LayoutUtils {
    private static final Rect visibleDisplayFrame = new Rect();

    private static int getPixelOffset(Context context, int i) {
        return context.getResources().getDimensionPixelOffset(i);
    }

    public static int getSurveyContainerMaxWidth(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration.smallestScreenWidthDp >= 411) {
            return getPixelOffset(context, R.dimen.survey_prompt_max_width_sw_411);
        }
        if (configuration.smallestScreenWidthDp >= 380) {
            return getPixelOffset(context, R.dimen.survey_prompt_max_width_sw_380);
        }
        if (configuration.smallestScreenWidthDp >= 320) {
            return getPixelOffset(context, R.dimen.survey_prompt_max_width_sw_320);
        }
        if (configuration.smallestScreenWidthDp >= 240) {
            return getPixelOffset(context, R.dimen.survey_prompt_max_width_sw_240);
        }
        return getPixelOffset(context, R.dimen.survey_prompt_max_width);
    }

    public static boolean isInCarMode(Context context) {
        if (((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 3) {
            return true;
        }
        return false;
    }

    public static int measureCardHeightMeasureSpec(ViewGroup viewGroup, View view, int i, int i2, View view2, View view3, boolean z) {
        if (viewGroup != view) {
            if (viewGroup.getChildCount() == 0) {
                return i2;
            }
            int i3 = 0;
            view.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = view.getMeasuredHeight();
            if (z) {
                return View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
            }
            Resources resources = view.getContext().getResources();
            Rect rect = visibleDisplayFrame;
            view.getWindowVisibleDisplayFrame(rect);
            int height = rect.height();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.survey_answer_min_height_for_scrolling);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.survey_card_vertical_margin);
            if (view3 != null) {
                CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                if (FlagsUtil.isBugfixEnabled(HatsV1M5Bugfixes.INSTANCE.get().fixSuccessionModeControlMeasurement(FlagsUtil.phenotypeContext))) {
                    i3 = view3.getMeasuredHeight();
                } else {
                    i3 = view3.getHeight();
                }
            }
            int height2 = height - view2.getHeight();
            int i4 = dimensionPixelSize2 + dimensionPixelSize2 + i3;
            if (measuredHeight + i4 > height && height2 - i4 > dimensionPixelSize) {
                measuredHeight = height - i4;
            }
            return View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        }
        throw new IllegalStateException("contentView should be the single child of containerViewGroup");
    }

    public static void updateImageViewWithLogo(ImageView imageView, Integer num) {
        if (num != null && num.intValue() != 0) {
            imageView.setImageResource(num.intValue());
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }
}

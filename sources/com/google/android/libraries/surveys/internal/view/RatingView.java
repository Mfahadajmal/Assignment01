package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.LocaleList;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat$Api21Impl;
import androidx.core.content.ContextCompat$Api23Impl;
import androidx.core.provider.CallbackWithHandler$2;
import com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.marvin.talkback.R;
import com.google.android.material.card.MaterialCardView;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.scone.proto.Survey$Rating;
import java.text.NumberFormat;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RatingView extends LinearLayout {
    private static final ImmutableList RATING_SMILEY_ICON_RESOURCE_LIST;
    public static final /* synthetic */ int RatingView$ar$NoOp = 0;
    public OnRatingClickListener onRatingClickListener;
    private Survey$Rating question;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnRatingClickListener {
        void onClickRating(int i);
    }

    static {
        Integer valueOf = Integer.valueOf(R.drawable.very_dissatisfied);
        Integer valueOf2 = Integer.valueOf(R.drawable.dissatisfied);
        Integer valueOf3 = Integer.valueOf(R.drawable.neutral);
        Integer valueOf4 = Integer.valueOf(R.drawable.satisfied);
        Integer valueOf5 = Integer.valueOf(R.drawable.very_satisfied);
        int i = ImmutableList.ImmutableList$ar$NoOp;
        RATING_SMILEY_ICON_RESOURCE_LIST = ImmutableList.construct(valueOf, valueOf2, valueOf3, valueOf4, valueOf5);
    }

    public RatingView(Context context) {
        super(context);
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.survey_question_rating_container, (ViewGroup) this, true);
    }

    private final Drawable getRecoloredDrawable(int i, int i2) {
        return DefaultExperimentTokenDecorator.getRecoloredDrawable(ContextCompat$Api21Impl.getDrawable(getContext(), i), getContext(), i2);
    }

    public static boolean isTouchWithinViewBounds(MotionEvent motionEvent, View view) {
        if (motionEvent.getX() >= 0.0f && motionEvent.getX() < view.getWidth() && motionEvent.getY() >= 0.0f && motionEvent.getY() < view.getHeight()) {
            return true;
        }
        return false;
    }

    public static void removeOnClickListenersAndDisableClickEvents(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnClickListener(null);
            viewGroup.getChildAt(i).setClickable(false);
        }
    }

    private final void setDescriptionForTalkBack$ar$edu(View view, int i, int i2, int i3) {
        LocaleList locales;
        Locale locale;
        String string;
        locales = getContext().getResources().getConfiguration().getLocales();
        locale = locales.get(0);
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        String format = numberFormat.format(i);
        String format2 = numberFormat.format(i2);
        String string2 = getContext().getString(R.string.survey_num_rating, format, format2);
        if (i3 == Survey$Rating.RatingType.RATING_TYPE_STARS$ar$edu) {
            string2 = getResources().getQuantityString(R.plurals.survey_star_rating, i, Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (i == 1) {
            string2 = string2 + " " + this.question.minOrdinalLabel_;
        } else if (i == i2) {
            string2 = string2 + " " + this.question.maxOrdinalLabel_;
        }
        if (i3 == Survey$Rating.RatingType.RATING_TYPE_SMILEYS$ar$edu) {
            Context context = getContext();
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                string = "";
                            } else {
                                string = getContext().getString(R.string.survey_very_satisfied);
                            }
                        } else {
                            string = getContext().getString(R.string.survey_somewhat_satisfied);
                        }
                    } else {
                        string = getContext().getString(R.string.survey_neutral);
                    }
                } else {
                    string = getContext().getString(R.string.survey_somewhat_dissatisfied);
                }
            } else {
                string = getContext().getString(R.string.survey_very_dissatisfied);
            }
            string2 = context.getString(R.string.survey_smiley_rating, format, format2, string);
        }
        view.setContentDescription(string2);
    }

    public static void setStarDrawablesBasedOnSelectedRating(ViewGroup viewGroup, int i, Drawable drawable, Drawable drawable2) {
        Drawable drawable3;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            ImageView imageView = (ImageView) viewGroup.getChildAt(i2).findViewById(R.id.survey_rating_image_icon);
            if (i2 >= i) {
                drawable3 = drawable;
            } else {
                drawable3 = drawable2;
            }
            imageView.setImageDrawable(drawable3);
        }
    }

    private static void setTextAndContentDescription(TextView textView, String str) {
        textView.setText(str);
        textView.setContentDescription(str);
    }

    public final int getColor(int i) {
        return ContextCompat$Api23Impl.getColor(getContext(), i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setUpRatingView(Survey$Rating survey$Rating) {
        this.question = survey$Rating;
        int i = survey$Rating.numRatingChoices_;
        final int i2 = 2;
        if (i >= 2 && i <= 7) {
            int forNumber$ar$edu$e870e65f_0 = Survey$Rating.RatingType.forNumber$ar$edu$e870e65f_0(survey$Rating.ratingType_);
            if (forNumber$ar$edu$e870e65f_0 == 0) {
                forNumber$ar$edu$e870e65f_0 = Survey$Rating.RatingType.UNRECOGNIZED$ar$edu$f6e0fe3d_0;
            }
            if (forNumber$ar$edu$e870e65f_0 != Survey$Rating.RatingType.RATING_TYPE_THUMBS_UP_THUMBS_DOWN$ar$edu) {
                setTextAndContentDescription((TextView) findViewById(R.id.survey_rating_low_value_text), survey$Rating.minOrdinalLabel_);
                setTextAndContentDescription((TextView) findViewById(R.id.survey_rating_high_value_text), survey$Rating.maxOrdinalLabel_);
            }
            int forNumber$ar$edu$e870e65f_02 = Survey$Rating.RatingType.forNumber$ar$edu$e870e65f_0(survey$Rating.ratingType_);
            if (forNumber$ar$edu$e870e65f_02 == 0) {
                forNumber$ar$edu$e870e65f_02 = Survey$Rating.RatingType.UNRECOGNIZED$ar$edu$f6e0fe3d_0;
            }
            int i3 = Survey$Rating.RatingType.RATING_TYPE_SMILEYS$ar$edu;
            int i4 = R.id.survey_rating_image_icon;
            int i5 = R.id.survey_rating_image_layout;
            int i6 = R.layout.survey_question_rating_image_item;
            final int i7 = 1;
            final int i8 = 0;
            if (forNumber$ar$edu$e870e65f_02 == i3) {
                final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.survey_rating_images_container);
                LayoutInflater from = LayoutInflater.from(getContext());
                int i9 = ((RegularImmutableList) RATING_SMILEY_ICON_RESOURCE_LIST).size;
                final int i10 = 0;
                while (i10 < i9) {
                    View inflate = from.inflate(R.layout.survey_question_rating_image_item, viewGroup, false);
                    if (i10 == 0) {
                        inflate.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        i10 = 0;
                    }
                    FrameLayout frameLayout = (FrameLayout) inflate.findViewById(i5);
                    ImageView imageView = (ImageView) frameLayout.findViewById(i4);
                    imageView.setImageDrawable(DefaultExperimentTokenDecorator.getRecoloredDrawable(ContextCompat$Api21Impl.getDrawable(getContext(), ((Integer) RATING_SMILEY_ICON_RESOURCE_LIST.get(i10)).intValue()), getContext(), getColor(R.color.survey_grey_icon_color)));
                    i10++;
                    imageView.setTag(Integer.valueOf(i10));
                    setDescriptionForTalkBack$ar$edu(imageView, i10, i9, Survey$Rating.RatingType.RATING_TYPE_SMILEYS$ar$edu);
                    frameLayout.setOnTouchListener(new RatingView$$ExternalSyntheticLambda6(this, frameLayout, imageView, i8));
                    frameLayout.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda7
                        public final /* synthetic */ RatingView f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i11 = i8;
                            int i12 = 1;
                            if (i11 != 0) {
                                if (i11 != 1) {
                                    RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup);
                                    new Handler().postDelayed(new CallbackWithHandler$2(this.f$0, i10, 20), 250L);
                                    return;
                                }
                                RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup);
                                new Handler().postDelayed(new RatingView$$ExternalSyntheticLambda5(this.f$0, i10, 0), 250L);
                                return;
                            }
                            RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup);
                            new Handler().postDelayed(new RatingView$$ExternalSyntheticLambda5(this.f$0, i10, i12), 250L);
                        }
                    });
                    viewGroup.addView(inflate);
                    i4 = R.id.survey_rating_image_icon;
                    i5 = R.id.survey_rating_image_layout;
                }
                return;
            }
            int forNumber$ar$edu$e870e65f_03 = Survey$Rating.RatingType.forNumber$ar$edu$e870e65f_0(survey$Rating.ratingType_);
            if (forNumber$ar$edu$e870e65f_03 == 0) {
                forNumber$ar$edu$e870e65f_03 = Survey$Rating.RatingType.UNRECOGNIZED$ar$edu$f6e0fe3d_0;
            }
            if (forNumber$ar$edu$e870e65f_03 == Survey$Rating.RatingType.RATING_TYPE_NUMBERS$ar$edu) {
                final ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.survey_rating_images_container);
                LayoutInflater from2 = LayoutInflater.from(getContext());
                int i11 = this.question.numRatingChoices_;
                final int i12 = 0;
                while (i12 < i11) {
                    View inflate2 = from2.inflate(R.layout.survey_question_rating_number_item, viewGroup2, false);
                    if (i12 == 0) {
                        inflate2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        i12 = 0;
                    }
                    final FrameLayout frameLayout2 = (FrameLayout) inflate2.findViewById(R.id.survey_rating_number_layout);
                    int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.survey_rating_number_large_button_horizontal_padding);
                    viewGroup2.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
                    final MaterialCardView materialCardView = (MaterialCardView) frameLayout2.findViewById(R.id.survey_rating_number_card);
                    int dimension = (int) getResources().getDimension(R.dimen.survey_rating_number_large_side_length);
                    ViewGroup.LayoutParams layoutParams = materialCardView.getLayoutParams();
                    layoutParams.width = dimension;
                    layoutParams.height = dimension;
                    materialCardView.setLayoutParams(layoutParams);
                    final TextView textView = (TextView) materialCardView.findViewById(R.id.survey_rating_number_text);
                    i12++;
                    textView.setTag(Integer.valueOf(i12));
                    textView.setText(String.valueOf(i12));
                    setDescriptionForTalkBack$ar$edu(textView, i12, i11, Survey$Rating.RatingType.RATING_TYPE_NUMBERS$ar$edu);
                    frameLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda11
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            FrameLayout frameLayout3 = frameLayout2;
                            RatingView ratingView = RatingView.this;
                            MaterialCardView materialCardView2 = materialCardView;
                            TextView textView2 = textView;
                            int action = motionEvent.getAction();
                            if (action != 0) {
                                if (action != 1) {
                                    if (action != 2) {
                                        return false;
                                    }
                                    if (RatingView.isTouchWithinViewBounds(motionEvent, view)) {
                                        return true;
                                    }
                                    frameLayout3.setPressed(false);
                                    materialCardView2.setCardBackgroundColor(ratingView.getColor(R.color.google_transparent));
                                    textView2.setTextColor(ratingView.getColor(R.color.survey_primary_text_color));
                                    return true;
                                }
                                if (!frameLayout3.isPressed()) {
                                    return true;
                                }
                                frameLayout3.performClick();
                                return true;
                            }
                            if (!RatingView.isTouchWithinViewBounds(motionEvent, view)) {
                                return true;
                            }
                            frameLayout3.setPressed(true);
                            materialCardView2.setCardBackgroundColor(ratingView.getColor(R.color.survey_accent_color));
                            textView2.setTextColor(ratingView.getColor(R.color.survey_surface_color_elevation_2));
                            return true;
                        }
                    });
                    frameLayout2.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda7
                        public final /* synthetic */ RatingView f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i112 = i7;
                            int i122 = 1;
                            if (i112 != 0) {
                                if (i112 != 1) {
                                    RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup2);
                                    new Handler().postDelayed(new CallbackWithHandler$2(this.f$0, i12, 20), 250L);
                                    return;
                                }
                                RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup2);
                                new Handler().postDelayed(new RatingView$$ExternalSyntheticLambda5(this.f$0, i12, 0), 250L);
                                return;
                            }
                            RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup2);
                            new Handler().postDelayed(new RatingView$$ExternalSyntheticLambda5(this.f$0, i12, i122), 250L);
                        }
                    });
                    viewGroup2.addView(inflate2);
                }
                return;
            }
            int forNumber$ar$edu$e870e65f_04 = Survey$Rating.RatingType.forNumber$ar$edu$e870e65f_0(survey$Rating.ratingType_);
            if (forNumber$ar$edu$e870e65f_04 == 0) {
                forNumber$ar$edu$e870e65f_04 = Survey$Rating.RatingType.UNRECOGNIZED$ar$edu$f6e0fe3d_0;
            }
            if (forNumber$ar$edu$e870e65f_04 == Survey$Rating.RatingType.RATING_TYPE_STARS$ar$edu) {
                int color = getColor(R.color.survey_accent_color);
                final Drawable recoloredDrawable = getRecoloredDrawable(R.drawable.quantum_ic_star_border_grey600_36, getColor(R.color.survey_grey_icon_color));
                final Drawable recoloredDrawable2 = getRecoloredDrawable(R.drawable.quantum_ic_star_grey600_36, color);
                final ViewGroup viewGroup3 = (ViewGroup) findViewById(R.id.survey_rating_images_container);
                LayoutInflater from3 = LayoutInflater.from(getContext());
                int i13 = this.question.numRatingChoices_;
                final int i14 = 0;
                while (i14 < i13) {
                    View inflate3 = from3.inflate(i6, viewGroup3, false);
                    if (i14 == 0) {
                        inflate3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        i14 = 0;
                    }
                    final FrameLayout frameLayout3 = (FrameLayout) inflate3.findViewById(R.id.survey_rating_image_layout);
                    int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.survey_rating_image_large_button_horizontal_padding);
                    viewGroup3.setPadding(dimensionPixelOffset2, 0, dimensionPixelOffset2, 0);
                    ImageView imageView2 = (ImageView) frameLayout3.findViewById(R.id.survey_rating_image_icon);
                    imageView2.setImageDrawable(recoloredDrawable);
                    i14++;
                    imageView2.setTag(Integer.valueOf(i14));
                    setDescriptionForTalkBack$ar$edu(imageView2, i14, i13, Survey$Rating.RatingType.RATING_TYPE_STARS$ar$edu);
                    frameLayout3.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda8
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            int i15 = RatingView.RatingView$ar$NoOp;
                            ViewGroup viewGroup4 = viewGroup3;
                            FrameLayout frameLayout4 = frameLayout3;
                            int i16 = i14;
                            Drawable drawable = recoloredDrawable;
                            int action = motionEvent.getAction();
                            Drawable drawable2 = recoloredDrawable2;
                            if (action != 0) {
                                if (action != 1) {
                                    if (action != 2) {
                                        return false;
                                    }
                                    if (RatingView.isTouchWithinViewBounds(motionEvent, view)) {
                                        return true;
                                    }
                                    frameLayout4.setPressed(false);
                                    RatingView.setStarDrawablesBasedOnSelectedRating(viewGroup4, 0, drawable, drawable2);
                                    return true;
                                }
                                if (!frameLayout4.isPressed()) {
                                    return true;
                                }
                                frameLayout4.performClick();
                                RatingView.setStarDrawablesBasedOnSelectedRating(viewGroup4, i16, drawable, drawable2);
                                return true;
                            }
                            if (!RatingView.isTouchWithinViewBounds(motionEvent, view)) {
                                return true;
                            }
                            frameLayout4.setPressed(true);
                            RatingView.setStarDrawablesBasedOnSelectedRating(viewGroup4, i16, drawable, drawable2);
                            return true;
                        }
                    });
                    frameLayout3.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda7
                        public final /* synthetic */ RatingView f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i112 = i2;
                            int i122 = 1;
                            if (i112 != 0) {
                                if (i112 != 1) {
                                    RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup3);
                                    new Handler().postDelayed(new CallbackWithHandler$2(this.f$0, i14, 20), 250L);
                                    return;
                                }
                                RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup3);
                                new Handler().postDelayed(new RatingView$$ExternalSyntheticLambda5(this.f$0, i14, 0), 250L);
                                return;
                            }
                            RatingView.removeOnClickListenersAndDisableClickEvents(viewGroup3);
                            new Handler().postDelayed(new RatingView$$ExternalSyntheticLambda5(this.f$0, i14, i122), 250L);
                        }
                    });
                    viewGroup3.addView(inflate3);
                    i6 = R.layout.survey_question_rating_image_item;
                }
                return;
            }
            int forNumber$ar$edu$e870e65f_05 = Survey$Rating.RatingType.forNumber$ar$edu$e870e65f_0(survey$Rating.ratingType_);
            if (forNumber$ar$edu$e870e65f_05 == 0) {
                forNumber$ar$edu$e870e65f_05 = Survey$Rating.RatingType.UNRECOGNIZED$ar$edu$f6e0fe3d_0;
            }
            if (forNumber$ar$edu$e870e65f_05 == Survey$Rating.RatingType.RATING_TYPE_THUMBS_UP_THUMBS_DOWN$ar$edu) {
                int color2 = getColor(R.color.survey_grey_icon_color);
                Drawable recoloredDrawable3 = getRecoloredDrawable(R.drawable.quantum_ic_thumb_up_grey600_36, color2);
                Drawable recoloredDrawable4 = getRecoloredDrawable(R.drawable.quantum_ic_thumb_down_grey600_36, color2);
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.survey_rating_images_container);
                linearLayout.setGravity(17);
                View inflate4 = LayoutInflater.from(getContext()).inflate(R.layout.survey_question_rating_thumb_item, (ViewGroup) linearLayout, false);
                ImageView imageView3 = (ImageView) inflate4.findViewById(R.id.survey_rating_thumb_up_icon);
                imageView3.setImageDrawable(recoloredDrawable3);
                imageView3.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(this, linearLayout, 11));
                ImageView imageView4 = (ImageView) inflate4.findViewById(R.id.survey_rating_thumb_down_icon);
                imageView4.setImageDrawable(recoloredDrawable4);
                imageView4.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(this, linearLayout, 12));
                linearLayout.addView(inflate4);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Number of ratings must be between 2 and 7.");
    }
}

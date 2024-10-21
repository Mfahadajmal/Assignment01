package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.versionedparcelable.ParcelImpl;
import com.google.android.marvin.talkback.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.Month;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.navigation.NavigationBarPresenter;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.slider.BaseSlider;
import java.util.Locale;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BadgeState {
    public int badgeFixedEdge;
    final float badgeHeight;
    final float badgeRadius;
    final float badgeWidth;
    final float badgeWithTextHeight;
    final float badgeWithTextRadius;
    final float badgeWithTextWidth;
    public final State currentState;
    final int horizontalInset;
    final int horizontalInsetWithText;
    final int offsetAlignmentMode;
    public final State overridingState;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class State implements Parcelable {
        public static final Parcelable.Creator<State> CREATOR = new AnonymousClass1(0);
        public Integer additionalHorizontalOffset;
        public Integer additionalVerticalOffset;
        public int alpha;
        public Boolean autoAdjustToWithinGrandparentBounds;
        public Integer backgroundColor;
        private Integer badgeFixedEdge;
        public Integer badgeGravity;
        public Integer badgeHorizontalPadding;
        public int badgeResId;
        public Integer badgeShapeAppearanceOverlayResId;
        public Integer badgeShapeAppearanceResId;
        public Integer badgeTextAppearanceResId;
        public Integer badgeTextColor;
        public Integer badgeVerticalPadding;
        public Integer badgeWithTextShapeAppearanceOverlayResId;
        public Integer badgeWithTextShapeAppearanceResId;
        public int contentDescriptionExceedsMaxBadgeNumberRes;
        public CharSequence contentDescriptionForText;
        public CharSequence contentDescriptionNumberless;
        public int contentDescriptionQuantityStrings;
        public Integer horizontalOffsetWithText;
        public Integer horizontalOffsetWithoutText;
        public Boolean isVisible;
        public Integer largeFontVerticalOffsetAdjustment;
        public int maxCharacterCount;
        public int maxNumber;
        public int number;
        public Locale numberLocale;
        public String text;
        public Integer verticalOffsetWithText;
        public Integer verticalOffsetWithoutText;

        /* compiled from: PG */
        /* renamed from: com.google.android.material.badge.BadgeState$State$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements Parcelable.Creator {
            private final /* synthetic */ int switching_field;

            public AnonymousClass1(int i) {
                this.switching_field = i;
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                int i = this.switching_field;
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i != 5) {
                                        return new BaseSlider.SliderState(parcel);
                                    }
                                    return new NavigationBarPresenter.SavedState(parcel);
                                }
                                return Month.create(parcel.readInt(), parcel.readInt());
                            }
                            return new DateValidatorPointForward(parcel.readLong());
                        }
                        return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (CalendarConstraints.DateValidator) parcel.readParcelable(CalendarConstraints.DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), parcel.readInt());
                    }
                    return new ParcelImpl(parcel);
                }
                return new State(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Object[] newArray(int i) {
                int i2 = this.switching_field;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 != 4) {
                                    if (i2 != 5) {
                                        return new BaseSlider.SliderState[i];
                                    }
                                    return new NavigationBarPresenter.SavedState[i];
                                }
                                return new Month[i];
                            }
                            return new DateValidatorPointForward[i];
                        }
                        return new CalendarConstraints[i];
                    }
                    return new ParcelImpl[i];
                }
                return new State[i];
            }
        }

        public State() {
            this.alpha = PrivateKeyType.INVALID;
            this.number = -2;
            this.maxCharacterCount = -2;
            this.maxNumber = -2;
            this.isVisible = true;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            String str;
            parcel.writeInt(this.badgeResId);
            parcel.writeSerializable(this.backgroundColor);
            parcel.writeSerializable(this.badgeTextColor);
            parcel.writeSerializable(this.badgeTextAppearanceResId);
            parcel.writeSerializable(this.badgeShapeAppearanceResId);
            parcel.writeSerializable(this.badgeShapeAppearanceOverlayResId);
            parcel.writeSerializable(this.badgeWithTextShapeAppearanceResId);
            parcel.writeSerializable(this.badgeWithTextShapeAppearanceOverlayResId);
            parcel.writeInt(this.alpha);
            parcel.writeString(this.text);
            parcel.writeInt(this.number);
            parcel.writeInt(this.maxCharacterCount);
            parcel.writeInt(this.maxNumber);
            CharSequence charSequence = this.contentDescriptionForText;
            String str2 = null;
            if (charSequence != null) {
                str = charSequence.toString();
            } else {
                str = null;
            }
            parcel.writeString(str);
            CharSequence charSequence2 = this.contentDescriptionNumberless;
            if (charSequence2 != null) {
                str2 = charSequence2.toString();
            }
            parcel.writeString(str2);
            parcel.writeInt(this.contentDescriptionQuantityStrings);
            parcel.writeSerializable(this.badgeGravity);
            parcel.writeSerializable(this.badgeHorizontalPadding);
            parcel.writeSerializable(this.badgeVerticalPadding);
            parcel.writeSerializable(this.horizontalOffsetWithoutText);
            parcel.writeSerializable(this.verticalOffsetWithoutText);
            parcel.writeSerializable(this.horizontalOffsetWithText);
            parcel.writeSerializable(this.verticalOffsetWithText);
            parcel.writeSerializable(this.largeFontVerticalOffsetAdjustment);
            parcel.writeSerializable(this.additionalHorizontalOffset);
            parcel.writeSerializable(this.additionalVerticalOffset);
            parcel.writeSerializable(this.isVisible);
            parcel.writeSerializable(this.numberLocale);
            parcel.writeSerializable(this.autoAdjustToWithinGrandparentBounds);
            parcel.writeSerializable(this.badgeFixedEdge);
        }

        public State(Parcel parcel) {
            this.alpha = PrivateKeyType.INVALID;
            this.number = -2;
            this.maxCharacterCount = -2;
            this.maxNumber = -2;
            this.isVisible = true;
            this.badgeResId = parcel.readInt();
            this.backgroundColor = (Integer) parcel.readSerializable();
            this.badgeTextColor = (Integer) parcel.readSerializable();
            this.badgeTextAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeShapeAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeShapeAppearanceOverlayResId = (Integer) parcel.readSerializable();
            this.badgeWithTextShapeAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeWithTextShapeAppearanceOverlayResId = (Integer) parcel.readSerializable();
            this.alpha = parcel.readInt();
            this.text = parcel.readString();
            this.number = parcel.readInt();
            this.maxCharacterCount = parcel.readInt();
            this.maxNumber = parcel.readInt();
            this.contentDescriptionForText = parcel.readString();
            this.contentDescriptionNumberless = parcel.readString();
            this.contentDescriptionQuantityStrings = parcel.readInt();
            this.badgeGravity = (Integer) parcel.readSerializable();
            this.badgeHorizontalPadding = (Integer) parcel.readSerializable();
            this.badgeVerticalPadding = (Integer) parcel.readSerializable();
            this.horizontalOffsetWithoutText = (Integer) parcel.readSerializable();
            this.verticalOffsetWithoutText = (Integer) parcel.readSerializable();
            this.horizontalOffsetWithText = (Integer) parcel.readSerializable();
            this.verticalOffsetWithText = (Integer) parcel.readSerializable();
            this.largeFontVerticalOffsetAdjustment = (Integer) parcel.readSerializable();
            this.additionalHorizontalOffset = (Integer) parcel.readSerializable();
            this.additionalVerticalOffset = (Integer) parcel.readSerializable();
            this.isVisible = (Boolean) parcel.readSerializable();
            this.numberLocale = (Locale) parcel.readSerializable();
            this.autoAdjustToWithinGrandparentBounds = (Boolean) parcel.readSerializable();
            this.badgeFixedEdge = (Integer) parcel.readSerializable();
        }
    }

    public BadgeState(Context context, State state) {
        AttributeSet attributeSet;
        int i;
        int i2;
        boolean z;
        int intValue;
        int intValue2;
        int intValue3;
        int intValue4;
        int intValue5;
        int intValue6;
        int intValue7;
        int intValue8;
        int intValue9;
        int intValue10;
        int intValue11;
        int intValue12;
        int intValue13;
        int intValue14;
        int intValue15;
        int intValue16;
        boolean booleanValue;
        Locale.Category category;
        State state2 = new State();
        this.currentState = state2;
        int i3 = state.badgeResId;
        if (i3 != 0) {
            AttributeSet parseDrawableXml$ar$ds = DrawableUtils$OutlineCompatR.parseDrawableXml$ar$ds(context, i3);
            i = parseDrawableXml$ar$ds.getStyleAttribute();
            attributeSet = parseDrawableXml$ar$ds;
        } else {
            attributeSet = null;
            i = 0;
        }
        if (i == 0) {
            i2 = 2132150238;
        } else {
            i2 = i;
        }
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R$styleable.Badge, R.attr.badgeStyle, i2, new int[0]);
        Resources resources = context.getResources();
        this.badgeRadius = obtainStyledAttributes.getDimensionPixelSize(5, -1);
        this.horizontalInset = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_horizontal_edge_offset);
        this.horizontalInsetWithText = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_text_horizontal_edge_offset);
        this.badgeWithTextRadius = obtainStyledAttributes.getDimensionPixelSize(15, -1);
        this.badgeWidth = obtainStyledAttributes.getDimension(13, resources.getDimension(R.dimen.m3_badge_size));
        this.badgeWithTextWidth = obtainStyledAttributes.getDimension(18, resources.getDimension(R.dimen.m3_badge_with_text_size));
        this.badgeHeight = obtainStyledAttributes.getDimension(4, resources.getDimension(R.dimen.m3_badge_size));
        this.badgeWithTextHeight = obtainStyledAttributes.getDimension(14, resources.getDimension(R.dimen.m3_badge_with_text_size));
        this.offsetAlignmentMode = obtainStyledAttributes.getInt(25, 1);
        this.badgeFixedEdge = obtainStyledAttributes.getInt(2, 0);
        int i4 = state.alpha;
        state2.alpha = i4 == -2 ? PrivateKeyType.INVALID : i4;
        int i5 = state.number;
        if (i5 != -2) {
            state2.number = i5;
        } else if (obtainStyledAttributes.hasValue(24)) {
            state2.number = obtainStyledAttributes.getInt(24, 0);
        } else {
            state2.number = -1;
        }
        String str = state.text;
        if (str != null) {
            state2.text = str;
        } else if (obtainStyledAttributes.hasValue(8)) {
            state2.text = obtainStyledAttributes.getString(8);
        }
        state2.contentDescriptionForText = state.contentDescriptionForText;
        CharSequence charSequence = state.contentDescriptionNumberless;
        state2.contentDescriptionNumberless = charSequence == null ? context.getString(R.string.mtrl_badge_numberless_content_description) : charSequence;
        int i6 = state.contentDescriptionQuantityStrings;
        state2.contentDescriptionQuantityStrings = i6 == 0 ? R.plurals.mtrl_badge_content_description : i6;
        int i7 = state.contentDescriptionExceedsMaxBadgeNumberRes;
        state2.contentDescriptionExceedsMaxBadgeNumberRes = i7 == 0 ? R.string.mtrl_exceed_max_badge_number_content_description : i7;
        Boolean bool = state.isVisible;
        if (bool != null && !bool.booleanValue()) {
            z = false;
        } else {
            z = true;
        }
        state2.isVisible = Boolean.valueOf(z);
        int i8 = state.maxCharacterCount;
        state2.maxCharacterCount = i8 == -2 ? obtainStyledAttributes.getInt(22, -2) : i8;
        int i9 = state.maxNumber;
        state2.maxNumber = i9 == -2 ? obtainStyledAttributes.getInt(23, -2) : i9;
        Integer num = state.badgeShapeAppearanceResId;
        if (num == null) {
            intValue = obtainStyledAttributes.getResourceId(6, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full);
        } else {
            intValue = num.intValue();
        }
        state2.badgeShapeAppearanceResId = Integer.valueOf(intValue);
        Integer num2 = state.badgeShapeAppearanceOverlayResId;
        if (num2 == null) {
            intValue2 = obtainStyledAttributes.getResourceId(7, 0);
        } else {
            intValue2 = num2.intValue();
        }
        state2.badgeShapeAppearanceOverlayResId = Integer.valueOf(intValue2);
        Integer num3 = state.badgeWithTextShapeAppearanceResId;
        if (num3 == null) {
            intValue3 = obtainStyledAttributes.getResourceId(16, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full);
        } else {
            intValue3 = num3.intValue();
        }
        state2.badgeWithTextShapeAppearanceResId = Integer.valueOf(intValue3);
        Integer num4 = state.badgeWithTextShapeAppearanceOverlayResId;
        if (num4 == null) {
            intValue4 = obtainStyledAttributes.getResourceId(17, 0);
        } else {
            intValue4 = num4.intValue();
        }
        state2.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(intValue4);
        Integer num5 = state.backgroundColor;
        if (num5 == null) {
            intValue5 = readColorFromAttributes(context, obtainStyledAttributes, 1);
        } else {
            intValue5 = num5.intValue();
        }
        state2.backgroundColor = Integer.valueOf(intValue5);
        Integer num6 = state.badgeTextAppearanceResId;
        if (num6 == null) {
            intValue6 = obtainStyledAttributes.getResourceId(9, R.style.TextAppearance_MaterialComponents_Badge);
        } else {
            intValue6 = num6.intValue();
        }
        state2.badgeTextAppearanceResId = Integer.valueOf(intValue6);
        Integer num7 = state.badgeTextColor;
        if (num7 != null) {
            state2.badgeTextColor = num7;
        } else if (obtainStyledAttributes.hasValue(10)) {
            state2.badgeTextColor = Integer.valueOf(readColorFromAttributes(context, obtainStyledAttributes, 10));
        } else {
            state2.badgeTextColor = Integer.valueOf(new TextAppearance(context, state2.badgeTextAppearanceResId.intValue()).textColor.getDefaultColor());
        }
        Integer num8 = state.badgeGravity;
        if (num8 == null) {
            intValue7 = obtainStyledAttributes.getInt(3, 8388661);
        } else {
            intValue7 = num8.intValue();
        }
        state2.badgeGravity = Integer.valueOf(intValue7);
        Integer num9 = state.badgeHorizontalPadding;
        if (num9 == null) {
            intValue8 = obtainStyledAttributes.getDimensionPixelSize(12, resources.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding));
        } else {
            intValue8 = num9.intValue();
        }
        state2.badgeHorizontalPadding = Integer.valueOf(intValue8);
        Integer num10 = state.badgeVerticalPadding;
        if (num10 == null) {
            intValue9 = obtainStyledAttributes.getDimensionPixelSize(11, resources.getDimensionPixelSize(R.dimen.m3_badge_with_text_vertical_padding));
        } else {
            intValue9 = num10.intValue();
        }
        state2.badgeVerticalPadding = Integer.valueOf(intValue9);
        Integer num11 = state.horizontalOffsetWithoutText;
        if (num11 == null) {
            intValue10 = obtainStyledAttributes.getDimensionPixelOffset(19, 0);
        } else {
            intValue10 = num11.intValue();
        }
        state2.horizontalOffsetWithoutText = Integer.valueOf(intValue10);
        Integer num12 = state.verticalOffsetWithoutText;
        if (num12 == null) {
            intValue11 = obtainStyledAttributes.getDimensionPixelOffset(26, 0);
        } else {
            intValue11 = num12.intValue();
        }
        state2.verticalOffsetWithoutText = Integer.valueOf(intValue11);
        Integer num13 = state.horizontalOffsetWithText;
        if (num13 == null) {
            intValue12 = obtainStyledAttributes.getDimensionPixelOffset(20, state2.horizontalOffsetWithoutText.intValue());
        } else {
            intValue12 = num13.intValue();
        }
        state2.horizontalOffsetWithText = Integer.valueOf(intValue12);
        Integer num14 = state.verticalOffsetWithText;
        if (num14 == null) {
            intValue13 = obtainStyledAttributes.getDimensionPixelOffset(27, state2.verticalOffsetWithoutText.intValue());
        } else {
            intValue13 = num14.intValue();
        }
        state2.verticalOffsetWithText = Integer.valueOf(intValue13);
        Integer num15 = state.largeFontVerticalOffsetAdjustment;
        if (num15 == null) {
            intValue14 = obtainStyledAttributes.getDimensionPixelOffset(21, 0);
        } else {
            intValue14 = num15.intValue();
        }
        state2.largeFontVerticalOffsetAdjustment = Integer.valueOf(intValue14);
        Integer num16 = state.additionalHorizontalOffset;
        if (num16 == null) {
            intValue15 = 0;
        } else {
            intValue15 = num16.intValue();
        }
        state2.additionalHorizontalOffset = Integer.valueOf(intValue15);
        Integer num17 = state.additionalVerticalOffset;
        if (num17 == null) {
            intValue16 = 0;
        } else {
            intValue16 = num17.intValue();
        }
        state2.additionalVerticalOffset = Integer.valueOf(intValue16);
        Boolean bool2 = state.autoAdjustToWithinGrandparentBounds;
        if (bool2 == null) {
            booleanValue = obtainStyledAttributes.getBoolean(0, false);
        } else {
            booleanValue = bool2.booleanValue();
        }
        state2.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(booleanValue);
        obtainStyledAttributes.recycle();
        Locale locale = state.numberLocale;
        if (locale == null) {
            category = Locale.Category.FORMAT;
            locale = Locale.getDefault(category);
        }
        state2.numberLocale = locale;
        this.overridingState = state;
    }

    private static int readColorFromAttributes(Context context, TypedArray typedArray, int i) {
        return DrawableUtils$OutlineCompatR.getColorStateList(context, typedArray, i).getDefaultColor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getBadgeGravity() {
        return this.currentState.badgeGravity.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getBadgeShapeAppearanceOverlayResId() {
        return this.currentState.badgeShapeAppearanceOverlayResId.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getBadgeShapeAppearanceResId() {
        return this.currentState.badgeShapeAppearanceResId.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getBadgeWithTextShapeAppearanceOverlayResId() {
        return this.currentState.badgeWithTextShapeAppearanceOverlayResId.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getBadgeWithTextShapeAppearanceResId() {
        return this.currentState.badgeWithTextShapeAppearanceResId.intValue();
    }

    public final int getContentDescriptionQuantityStrings() {
        return this.currentState.contentDescriptionQuantityStrings;
    }

    public final int getHorizontalOffsetWithoutText() {
        return this.currentState.horizontalOffsetWithoutText.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Locale getNumberLocale() {
        return this.currentState.numberLocale;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasNumber() {
        if (this.currentState.number != -1) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasText() {
        if (this.currentState.text != null) {
            return true;
        }
        return false;
    }
}

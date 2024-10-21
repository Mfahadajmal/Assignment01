package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.marvin.talkback.R;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CalendarStyle {
    final CalendarItemStyle day;
    final CalendarItemStyle invalidDay;
    final Paint rangeFill;
    final CalendarItemStyle selectedDay;
    final CalendarItemStyle selectedYear;
    final CalendarItemStyle todayDay;
    final CalendarItemStyle todayYear;
    final CalendarItemStyle year;

    public CalendarStyle(Context context) {
        int i;
        i = DrawableUtils$OutlineCompatR.resolveTypedValueOrThrow(context, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()).data;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.MaterialCalendar);
        this.day = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(4, 0));
        this.invalidDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(2, 0));
        this.selectedDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(3, 0));
        this.todayDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(5, 0));
        ColorStateList colorStateList = DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 7);
        this.year = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(9, 0));
        this.selectedYear = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(8, 0));
        this.todayYear = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(10, 0));
        Paint paint = new Paint();
        this.rangeFill = paint;
        paint.setColor(colorStateList.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}

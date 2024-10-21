package com.google.android.material.datepicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.libraries.mdi.download.debug.MddDebugListAdapter$$ExternalSyntheticLambda0;
import com.google.android.marvin.talkback.R;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class YearGridAdapter extends RecyclerView.Adapter {
    public final MaterialCalendar materialCalendar;

    public YearGridAdapter(MaterialCalendar materialCalendar) {
        this.materialCalendar = materialCalendar;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.materialCalendar.calendarConstraints.yearSpan;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getPositionForYear(int i) {
        return i - this.materialCalendar.calendarConstraints.start.year;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        String format;
        CalendarItemStyle calendarItemStyle;
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        int i2 = this.materialCalendar.calendarConstraints.start.year;
        View view = viewHolder2.YearGridAdapter$ViewHolder$ar$textView;
        Locale locale = Locale.getDefault();
        int i3 = i2 + i;
        Integer valueOf = Integer.valueOf(i3);
        ((TextView) view).setText(String.format(locale, "%d", valueOf));
        TextView textView = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView;
        Context context = textView.getContext();
        if (UtcDates.getTodayCalendar().get(1) == i3) {
            format = String.format(context.getString(R.string.mtrl_picker_navigate_to_current_year_description), valueOf);
        } else {
            format = String.format(context.getString(R.string.mtrl_picker_navigate_to_year_description), valueOf);
        }
        textView.setContentDescription(format);
        CalendarStyle calendarStyle = this.materialCalendar.calendarStyle;
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        if (todayCalendar.get(1) == i3) {
            calendarItemStyle = calendarStyle.todayYear;
        } else {
            calendarItemStyle = calendarStyle.year;
        }
        Iterator it = this.materialCalendar.dateSelector.getSelectedDays().iterator();
        while (it.hasNext()) {
            todayCalendar.setTimeInMillis(((Long) it.next()).longValue());
            if (todayCalendar.get(1) == i3) {
                calendarItemStyle = calendarStyle.selectedYear;
            }
        }
        calendarItemStyle.styleItem((TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView);
        ((TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView).setOnClickListener(new MddDebugListAdapter$$ExternalSyntheticLambda0(this, i3, 2));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_year, viewGroup, false));
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        public static final /* synthetic */ int YearGridAdapter$ViewHolder$ar$NoOp = 0;
        public final View YearGridAdapter$ViewHolder$ar$textView;

        public ViewHolder(View view, byte[] bArr) {
            super(view);
            this.YearGridAdapter$ViewHolder$ar$textView = (TextView) view.findViewById(android.R.id.text1);
        }

        public ViewHolder(TextView textView, byte[] bArr) {
            super(textView);
            this.YearGridAdapter$ViewHolder$ar$textView = textView;
        }

        public ViewHolder(ViewGroup viewGroup) {
            super(viewGroup);
            this.YearGridAdapter$ViewHolder$ar$textView = (TextView) viewGroup.findViewById(android.R.id.text1);
        }

        public ViewHolder(View view) {
            super(view);
            this.YearGridAdapter$ViewHolder$ar$textView = view;
        }

        public ViewHolder(TextView textView) {
            super(textView);
            this.YearGridAdapter$ViewHolder$ar$textView = textView;
        }
    }
}

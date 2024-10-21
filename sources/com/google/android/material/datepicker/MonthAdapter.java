package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.core.util.Pair;
import com.google.android.marvin.talkback.R;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MonthAdapter extends BaseAdapter {
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector dateSelector;
    final DayViewDecorator dayViewDecorator;
    final Month month;
    public Collection previouslySelectedDates;
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    private static final int MAXIMUM_GRID_CELLS = (UtcDates.getUtcCalendar().getMaximum(5) + UtcDates.getUtcCalendar().getMaximum(7)) - 1;

    public MonthAdapter(Month month, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        this.month = month;
        this.dateSelector = dateSelector;
        this.calendarConstraints = calendarConstraints;
        this.dayViewDecorator = dayViewDecorator;
        this.previouslySelectedDates = dateSelector.getSelectedDays();
    }

    private static final boolean isToday$ar$ds(long j) {
        if (UtcDates.getTodayCalendar().getTimeInMillis() == j) {
            return true;
        }
        return false;
    }

    private final void updateSelectedState(TextView textView, long j, int i) {
        boolean z;
        boolean z2;
        boolean z3;
        CalendarItemStyle calendarItemStyle;
        if (textView == null) {
            return;
        }
        Context context = textView.getContext();
        boolean isToday$ar$ds = isToday$ar$ds(j);
        Iterator it = this.dateSelector.getSelectedRanges().iterator();
        while (true) {
            z = false;
            if (it.hasNext()) {
                Object obj = ((Pair) it.next()).first;
                if (obj != null && ((Long) obj).longValue() == j) {
                    z2 = true;
                    break;
                }
            } else {
                z2 = false;
                break;
            }
        }
        Iterator it2 = this.dateSelector.getSelectedRanges().iterator();
        while (true) {
            if (it2.hasNext()) {
                Object obj2 = ((Pair) it2.next()).second;
                if (obj2 != null && ((Long) obj2).longValue() == j) {
                    z3 = true;
                    break;
                }
            } else {
                z3 = false;
                break;
            }
        }
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(j);
        String format = todayCalendar.get(1) == utcCalendar.get(1) ? UtcDates.getAndroidFormat("MMMMEEEEd", Locale.getDefault()).format(new Date(j)) : UtcDates.getAndroidFormat("yMMMMEEEEd", Locale.getDefault()).format(new Date(j));
        if (isToday$ar$ds) {
            format = String.format(context.getString(R.string.mtrl_picker_today_description), format);
        }
        if (z2) {
            format = String.format(context.getString(R.string.mtrl_picker_start_date_description), format);
        } else if (z3) {
            format = String.format(context.getString(R.string.mtrl_picker_end_date_description), format);
        }
        textView.setContentDescription(format);
        if (this.calendarConstraints.validator.isValid(j)) {
            textView.setEnabled(true);
            Iterator it3 = this.dateSelector.getSelectedDays().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                } else if (UtcDates.canonicalYearMonthDay(j) == UtcDates.canonicalYearMonthDay(((Long) it3.next()).longValue())) {
                    z = true;
                    break;
                }
            }
            textView.setSelected(z);
            if (z) {
                calendarItemStyle = this.calendarStyle.selectedDay;
            } else if (isToday$ar$ds(j)) {
                calendarItemStyle = this.calendarStyle.todayDay;
            } else {
                calendarItemStyle = this.calendarStyle.day;
            }
        } else {
            textView.setEnabled(false);
            calendarItemStyle = this.calendarStyle.invalidDay;
        }
        if (this.dayViewDecorator != null && i != -1) {
            Month month = this.month;
            int i2 = month.year;
            int i3 = month.month;
            throw null;
        }
        calendarItemStyle.styleItem(textView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int dayToPosition(int i) {
        return firstPositionInMonth() + (i - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int firstPositionInMonth() {
        Month month = this.month;
        int i = month.firstOfMonth.get(7);
        int i2 = this.calendarConstraints.firstDayOfWeek;
        if (i2 <= 0) {
            i2 = month.firstOfMonth.getFirstDayOfWeek();
        }
        int i3 = i - i2;
        if (i3 < 0) {
            return i3 + month.daysInWeek;
        }
        return i3;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return MAXIMUM_GRID_CELLS;
    }

    @Override // android.widget.Adapter
    public final Long getItem(int i) {
        if (i < firstPositionInMonth() || i > lastPositionInMonth()) {
            return null;
        }
        Month month = this.month;
        int firstPositionInMonth = i - firstPositionInMonth();
        Calendar dayCopy = UtcDates.getDayCopy(month.firstOfMonth);
        dayCopy.set(5, firstPositionInMonth + 1);
        return Long.valueOf(dayCopy.getTimeInMillis());
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i / this.month.daysInWeek;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006c  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* bridge */ /* synthetic */ android.view.View getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            com.google.android.material.datepicker.CalendarStyle r1 = r5.calendarStyle
            if (r1 != 0) goto Lf
            com.google.android.material.datepicker.CalendarStyle r1 = new com.google.android.material.datepicker.CalendarStyle
            r1.<init>(r0)
            r5.calendarStyle = r1
        Lf:
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L27
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            r0 = 2131624077(0x7f0e008d, float:1.8875324E38)
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L27:
            int r7 = r5.firstPositionInMonth()
            int r7 = r6 - r7
            if (r7 < 0) goto L5d
            com.google.android.material.datepicker.Month r8 = r5.month
            int r2 = r8.daysInMonth
            if (r7 < r2) goto L36
            goto L5d
        L36:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r4[r1] = r3
            java.lang.String r3 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r3, r4)
            r0.setText(r8)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L66
        L5d:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
            r7 = -1
        L66:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 == 0) goto L73
            long r1 = r6.longValue()
            r5.updateSelectedState(r0, r1, r7)
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.MonthAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int lastPositionInMonth() {
        return (firstPositionInMonth() + this.month.daysInMonth) - 1;
    }

    public final void updateSelectedStateForDate(MaterialCalendarGridView materialCalendarGridView, long j) {
        if (Month.create(j).equals(this.month)) {
            Calendar dayCopy = UtcDates.getDayCopy(this.month.firstOfMonth);
            dayCopy.setTimeInMillis(j);
            int i = dayCopy.get(5);
            updateSelectedState((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().dayToPosition(i) - materialCalendarGridView.getFirstVisiblePosition()), j, i);
        }
    }
}

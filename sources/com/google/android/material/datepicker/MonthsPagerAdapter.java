package com.google.android.material.datepicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MonthsPagerAdapter extends RecyclerView.Adapter {
    private final CalendarConstraints calendarConstraints;
    private final DateSelector dateSelector;
    private final DayViewDecorator dayViewDecorator;
    private final int itemHeight;
    public final FloatingActionButton.ShadowDelegateImpl onDayClickListener$ar$class_merging$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        final MaterialCalendarGridView monthGrid;
        final TextView monthTitle;

        public ViewHolder(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
            this.monthTitle = textView;
            ViewCompat.setAccessibilityHeading(textView, true);
            this.monthGrid = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
            if (!z) {
                textView.setVisibility(8);
            }
        }
    }

    public MonthsPagerAdapter(Context context, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        int i;
        Month month = calendarConstraints.start;
        Month month2 = calendarConstraints.end;
        Month month3 = calendarConstraints.openAt;
        if (month.compareTo(month3) <= 0) {
            if (month3.compareTo(month2) <= 0) {
                int dayHeight = MonthAdapter.MAXIMUM_WEEKS * MaterialCalendar.getDayHeight(context);
                if (MaterialDatePicker.isFullscreen(context)) {
                    i = MaterialCalendar.getDayHeight(context);
                } else {
                    i = 0;
                }
                this.itemHeight = dayHeight + i;
                this.calendarConstraints = calendarConstraints;
                this.dateSelector = dateSelector;
                this.dayViewDecorator = dayViewDecorator;
                this.onDayClickListener$ar$class_merging$ar$class_merging = shadowDelegateImpl;
                setHasStableIds(true);
                return;
            }
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        throw new IllegalArgumentException("firstPage cannot be after currentPage");
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.calendarConstraints.monthSpan;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        return this.calendarConstraints.start.monthsLater(i).firstOfMonth.getTimeInMillis();
    }

    public final Month getPageMonth(int i) {
        return this.calendarConstraints.start.monthsLater(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getPosition(Month month) {
        return this.calendarConstraints.start.monthsUntil(month);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        Month monthsLater = this.calendarConstraints.start.monthsLater(i);
        viewHolder2.monthTitle.setText(monthsLater.getLongName());
        final MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) viewHolder2.monthGrid.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter() != null && monthsLater.equals(materialCalendarGridView.getAdapter().month)) {
            materialCalendarGridView.invalidate();
            MonthAdapter adapter = materialCalendarGridView.getAdapter();
            Iterator it = adapter.previouslySelectedDates.iterator();
            while (it.hasNext()) {
                adapter.updateSelectedStateForDate(materialCalendarGridView, ((Long) it.next()).longValue());
            }
            DateSelector dateSelector = adapter.dateSelector;
            if (dateSelector != null) {
                Iterator it2 = dateSelector.getSelectedDays().iterator();
                while (it2.hasNext()) {
                    adapter.updateSelectedStateForDate(materialCalendarGridView, ((Long) it2.next()).longValue());
                }
                adapter.previouslySelectedDates = adapter.dateSelector.getSelectedDays();
            }
        } else {
            MonthAdapter monthAdapter = new MonthAdapter(monthsLater, this.dateSelector, this.calendarConstraints, this.dayViewDecorator);
            materialCalendarGridView.setNumColumns(monthsLater.daysInWeek);
            materialCalendarGridView.setAdapter((ListAdapter) monthAdapter);
        }
        materialCalendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(this) { // from class: com.google.android.material.datepicker.MonthsPagerAdapter.1
            final /* synthetic */ MonthsPagerAdapter this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                MonthAdapter adapter2 = materialCalendarGridView.getAdapter();
                if (i2 >= adapter2.firstPositionInMonth() && i2 <= adapter2.lastPositionInMonth()) {
                    MonthsPagerAdapter monthsPagerAdapter = this.this$0;
                    long longValue = materialCalendarGridView.getAdapter().getItem(i2).longValue();
                    FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = monthsPagerAdapter.onDayClickListener$ar$class_merging$ar$class_merging;
                    MaterialCalendar materialCalendar = (MaterialCalendar) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                    if (materialCalendar.calendarConstraints.validator.isValid(longValue)) {
                        materialCalendar.dateSelector.select$ar$ds();
                        Iterator it3 = ((MaterialCalendar) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).onSelectionChangedListeners.iterator();
                        while (it3.hasNext()) {
                            ((OnSelectionChangedListener) it3.next()).onSelectionChanged(((MaterialCalendar) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).dateSelector.getSelection());
                        }
                        ((MaterialCalendar) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).recyclerView.mAdapter.notifyDataSetChanged();
                        RecyclerView recyclerView = ((MaterialCalendar) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).yearSelector;
                        if (recyclerView != null) {
                            recyclerView.mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (MaterialDatePicker.isFullscreen(viewGroup.getContext())) {
            linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.itemHeight));
            return new ViewHolder(linearLayout, true);
        }
        return new ViewHolder(linearLayout, false);
    }
}

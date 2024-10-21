package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Scroller;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.android.marvin.talkback.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Calendar;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialCalendar extends PickerFragment {
    public CalendarConstraints calendarConstraints;
    public int calendarSelector$ar$edu;
    public CalendarStyle calendarStyle;
    public Month current;
    public DateSelector dateSelector;
    public View dayFrame;
    private DayViewDecorator dayViewDecorator;
    private View monthNext;
    private View monthPrev;
    public RecyclerView recyclerView;
    private int themeResId;
    private View yearFrame;
    public RecyclerView yearSelector;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDayHeight(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
    }

    private final void postSmoothRecyclerViewScroll(int i) {
        this.recyclerView.post(new RatingView$$ExternalSyntheticLambda5(this, i, 2, (byte[]) null));
    }

    @Override // com.google.android.material.datepicker.PickerFragment
    public final boolean addOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        return super.addOnSelectionChangedListener(onSelectionChangedListener);
    }

    public final LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) this.recyclerView.mLayout;
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.themeResId = bundle.getInt("THEME_RES_ID_KEY");
        this.dateSelector = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.calendarConstraints = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.dayViewDecorator = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.current = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        DaysOfWeekAdapter daysOfWeekAdapter;
        PagerSnapHelper pagerSnapHelper;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.themeResId);
        this.calendarStyle = new CalendarStyle(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month month = this.calendarConstraints.start;
        boolean isFullscreen = MaterialDatePicker.isFullscreen(contextThemeWrapper);
        if (true != isFullscreen) {
            i = R.layout.mtrl_calendar_horizontal;
        } else {
            i = R.layout.mtrl_calendar_vertical;
        }
        View inflate = cloneInContext.inflate(i, viewGroup, false);
        Resources resources = requireContext().getResources();
        inflate.setMinimumHeight(resources.getDimensionPixelSize(R.dimen.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_bottom_padding) + resources.getDimensionPixelSize(R.dimen.mtrl_calendar_days_of_week_height) + (MonthAdapter.MAXIMUM_WEEKS * resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_height)) + ((MonthAdapter.MAXIMUM_WEEKS - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_vertical_padding)) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_bottom_padding));
        GridView gridView = (GridView) inflate.findViewById(R.id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(gridView, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCollectionInfo(null);
            }
        });
        int i2 = this.calendarConstraints.firstDayOfWeek;
        if (i2 > 0) {
            daysOfWeekAdapter = new DaysOfWeekAdapter(i2);
        } else {
            daysOfWeekAdapter = new DaysOfWeekAdapter();
        }
        gridView.setAdapter((ListAdapter) daysOfWeekAdapter);
        gridView.setNumColumns(month.daysInWeek);
        gridView.setEnabled(false);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_months);
        Context context = getContext();
        int i3 = isFullscreen ? 1 : 0;
        final int i4 = isFullscreen ? 1 : 0;
        this.recyclerView.setLayoutManager(new SmoothCalendarLayoutManager(this, context, i3) { // from class: com.google.android.material.datepicker.MaterialCalendar.2
            final /* synthetic */ MaterialCalendar this$0;

            {
                this.this$0 = this;
            }

            @Override // android.support.v7.widget.LinearLayoutManager
            protected final void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
                if (i4 == 0) {
                    iArr[0] = this.this$0.recyclerView.getWidth();
                    iArr[1] = this.this$0.recyclerView.getWidth();
                } else {
                    iArr[0] = this.this$0.recyclerView.getHeight();
                    iArr[1] = this.this$0.recyclerView.getHeight();
                }
            }
        });
        this.recyclerView.setTag("MONTHS_VIEW_GROUP_TAG");
        final MonthsPagerAdapter monthsPagerAdapter = new MonthsPagerAdapter(contextThemeWrapper, this.dateSelector, this.calendarConstraints, this.dayViewDecorator, new FloatingActionButton.ShadowDelegateImpl(this));
        this.recyclerView.setAdapter(monthsPagerAdapter);
        int integer = contextThemeWrapper.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView3 = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.yearSelector = recyclerView3;
        if (recyclerView3 != null) {
            recyclerView3.setHasFixedSize$ar$ds();
            this.yearSelector.setLayoutManager(new GridLayoutManager(contextThemeWrapper, integer));
            this.yearSelector.setAdapter(new YearGridAdapter(this));
            this.yearSelector.addItemDecoration$ar$class_merging(new AppCompatReceiveContentHelper$OnDropApi24Impl() { // from class: com.google.android.material.datepicker.MaterialCalendar.5
                private final Calendar startItem = UtcDates.getUtcCalendar();
                private final Calendar endItem = UtcDates.getUtcCalendar();

                @Override // android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl
                public final void onDraw$ar$ds(Canvas canvas, RecyclerView recyclerView4) {
                    int width;
                    RecyclerView.Adapter adapter = recyclerView4.mAdapter;
                    if (adapter instanceof YearGridAdapter) {
                        RecyclerView.LayoutManager layoutManager = recyclerView4.mLayout;
                        if (layoutManager instanceof GridLayoutManager) {
                            YearGridAdapter yearGridAdapter = (YearGridAdapter) adapter;
                            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                            for (Pair pair : MaterialCalendar.this.dateSelector.getSelectedRanges()) {
                                Object obj = pair.first;
                                if (obj != null && pair.second != null) {
                                    this.startItem.setTimeInMillis(((Long) obj).longValue());
                                    this.endItem.setTimeInMillis(((Long) pair.second).longValue());
                                    int positionForYear = yearGridAdapter.getPositionForYear(this.startItem.get(1));
                                    int positionForYear2 = yearGridAdapter.getPositionForYear(this.endItem.get(1));
                                    View findViewByPosition = gridLayoutManager.findViewByPosition(positionForYear);
                                    View findViewByPosition2 = gridLayoutManager.findViewByPosition(positionForYear2);
                                    int i5 = gridLayoutManager.mSpanCount;
                                    int i6 = positionForYear / i5;
                                    int i7 = positionForYear2 / i5;
                                    for (int i8 = i6; i8 <= i7; i8++) {
                                        View findViewByPosition3 = gridLayoutManager.findViewByPosition(gridLayoutManager.mSpanCount * i8);
                                        if (findViewByPosition3 != null) {
                                            int top = findViewByPosition3.getTop() + MaterialCalendar.this.calendarStyle.year.getTopInset();
                                            int bottom = findViewByPosition3.getBottom() - MaterialCalendar.this.calendarStyle.year.getBottomInset();
                                            int i9 = 0;
                                            if (i8 == i6 && findViewByPosition != null) {
                                                i9 = findViewByPosition.getLeft() + (findViewByPosition.getWidth() / 2);
                                            }
                                            if (i8 == i7 && findViewByPosition2 != null) {
                                                width = findViewByPosition2.getLeft() + (findViewByPosition2.getWidth() / 2);
                                            } else {
                                                width = recyclerView4.getWidth();
                                            }
                                            canvas.drawRect(i9, top, width, bottom, MaterialCalendar.this.calendarStyle.rangeFill);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (inflate.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            final MaterialButton materialButton = (MaterialButton) inflate.findViewById(R.id.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            ViewCompat.setAccessibilityDelegate(materialButton, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.6
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    String string;
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    if (MaterialCalendar.this.dayFrame.getVisibility() == 0) {
                        string = MaterialCalendar.this.getString(R.string.mtrl_picker_toggle_to_year_selection);
                    } else {
                        string = MaterialCalendar.this.getString(R.string.mtrl_picker_toggle_to_day_selection);
                    }
                    accessibilityNodeInfoCompat.setHintText(string);
                }
            });
            View findViewById = inflate.findViewById(R.id.month_navigation_previous);
            this.monthPrev = findViewById;
            findViewById.setTag("NAVIGATION_PREV_TAG");
            View findViewById2 = inflate.findViewById(R.id.month_navigation_next);
            this.monthNext = findViewById2;
            findViewById2.setTag("NAVIGATION_NEXT_TAG");
            this.yearFrame = inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
            this.dayFrame = inflate.findViewById(R.id.mtrl_calendar_day_selector_frame);
            setSelector$ar$edu(1);
            materialButton.setText(this.current.getLongName());
            this.recyclerView.addOnScrollListener$ar$class_merging(new AppCompatSpinner.Api23Impl(this) { // from class: com.google.android.material.datepicker.MaterialCalendar.7
                final /* synthetic */ MaterialCalendar this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.support.v7.widget.AppCompatSpinner.Api23Impl
                public final void onScrollStateChanged(RecyclerView recyclerView4, int i5) {
                    if (i5 == 0) {
                        recyclerView4.announceForAccessibility(materialButton.getText());
                    }
                }

                @Override // android.support.v7.widget.AppCompatSpinner.Api23Impl
                public final void onScrolled(RecyclerView recyclerView4, int i5, int i6) {
                    int findLastVisibleItemPosition;
                    if (i5 < 0) {
                        findLastVisibleItemPosition = this.this$0.getLayoutManager().findFirstVisibleItemPosition();
                    } else {
                        findLastVisibleItemPosition = this.this$0.getLayoutManager().findLastVisibleItemPosition();
                    }
                    this.this$0.current = monthsPagerAdapter.getPageMonth(findLastVisibleItemPosition);
                    materialButton.setText(monthsPagerAdapter.getPageMonth(findLastVisibleItemPosition).getLongName());
                }
            });
            materialButton.setOnClickListener(new TrainingActivity$$ExternalSyntheticLambda0(this, 10, null));
            this.monthNext.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(this, monthsPagerAdapter, 18));
            this.monthPrev.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(this, monthsPagerAdapter, 17));
        }
        if (!MaterialDatePicker.isFullscreen(contextThemeWrapper) && (recyclerView2 = (pagerSnapHelper = new PagerSnapHelper()).mRecyclerView) != (recyclerView = this.recyclerView)) {
            if (recyclerView2 != null) {
                recyclerView2.removeOnScrollListener$ar$class_merging(pagerSnapHelper.mScrollListener$ar$class_merging);
                pagerSnapHelper.mRecyclerView.mOnFlingListener = null;
            }
            pagerSnapHelper.mRecyclerView = recyclerView;
            RecyclerView recyclerView4 = pagerSnapHelper.mRecyclerView;
            if (recyclerView4 != null) {
                if (recyclerView4.mOnFlingListener == null) {
                    recyclerView4.addOnScrollListener$ar$class_merging(pagerSnapHelper.mScrollListener$ar$class_merging);
                    RecyclerView recyclerView5 = pagerSnapHelper.mRecyclerView;
                    recyclerView5.mOnFlingListener = pagerSnapHelper;
                    new Scroller(recyclerView5.getContext(), new DecelerateInterpolator());
                    pagerSnapHelper.snapToTargetExistingView();
                } else {
                    throw new IllegalStateException("An instance of OnFlingListener already set.");
                }
            }
        }
        this.recyclerView.scrollToPosition(monthsPagerAdapter.getPosition(this.current));
        ViewCompat.setAccessibilityDelegate(this.recyclerView, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.4
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setScrollable(false);
            }
        });
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.themeResId);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.calendarConstraints);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.dayViewDecorator);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.current);
    }

    public final void setCurrentMonth(Month month) {
        MonthsPagerAdapter monthsPagerAdapter = (MonthsPagerAdapter) this.recyclerView.mAdapter;
        int position = monthsPagerAdapter.getPosition(month);
        int position2 = position - monthsPagerAdapter.getPosition(this.current);
        int abs = Math.abs(position2);
        this.current = month;
        if (abs > 3) {
            if (position2 > 0) {
                this.recyclerView.scrollToPosition(position - 3);
                postSmoothRecyclerViewScroll(position);
                return;
            } else {
                this.recyclerView.scrollToPosition(position + 3);
                postSmoothRecyclerViewScroll(position);
                return;
            }
        }
        postSmoothRecyclerViewScroll(position);
    }

    public final void setSelector$ar$edu(int i) {
        this.calendarSelector$ar$edu = i;
        if (i == 2) {
            RecyclerView recyclerView = this.yearSelector;
            recyclerView.mLayout.scrollToPosition(((YearGridAdapter) recyclerView.mAdapter).getPositionForYear(this.current.year));
            this.yearFrame.setVisibility(0);
            this.dayFrame.setVisibility(8);
            this.monthPrev.setVisibility(8);
            this.monthNext.setVisibility(8);
            return;
        }
        this.yearFrame.setVisibility(8);
        this.dayFrame.setVisibility(0);
        this.monthPrev.setVisibility(0);
        this.monthNext.setVisibility(0);
        setCurrentMonth(this.current);
    }
}

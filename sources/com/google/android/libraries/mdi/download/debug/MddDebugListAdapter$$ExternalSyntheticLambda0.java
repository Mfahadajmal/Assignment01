package com.google.android.libraries.mdi.download.debug;

import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.libraries.mdi.Download$ClientFileGroup;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProviderImpl;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.Month;
import com.google.android.material.datepicker.YearGridAdapter;
import com.google.common.flogger.GoogleLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class MddDebugListAdapter$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ RecyclerView.Adapter MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ int f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ MddDebugListAdapter$$ExternalSyntheticLambda0(RecyclerView.Adapter adapter, int i, int i2) {
        this.switching_field = i2;
        this.MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0 = adapter;
        this.f$1 = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                Month create = Month.create(this.f$1, ((YearGridAdapter) this.MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0).materialCalendar.current.month);
                CalendarConstraints calendarConstraints = ((YearGridAdapter) this.MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0).materialCalendar.calendarConstraints;
                if (create.compareTo(calendarConstraints.start) < 0) {
                    create = calendarConstraints.start;
                } else if (create.compareTo(calendarConstraints.end) > 0) {
                    create = calendarConstraints.end;
                }
                ((YearGridAdapter) this.MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0).materialCalendar.setCurrentMonth(create);
                ((YearGridAdapter) this.MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0).materialCalendar.setSelector$ar$edu(1);
                return;
            }
            ((ListMenuManager.RecyclerViewAdapter) this.MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0).listener$ar$class_merging$19028214_0$ar$class_merging$ar$class_merging.onItemClick(this.f$1);
            return;
        }
        int i2 = this.f$1;
        RecyclerView.Adapter adapter = this.MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0;
        Download$ClientFileGroup download$ClientFileGroup = (Download$ClientFileGroup) ((ListAdapter) adapter).getItem(i2);
        if (((MddDebugListAdapter) adapter).selectListener$ar$class_merging$ar$class_merging == null) {
            return;
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugListFragmentActionProviderImpl.logger.atFine()).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugListFragmentActionProviderImpl", "downloadFileGroup", BrailleInputEvent.CMD_TOGGLE_SCREEN_SEARCH, "MddDebugListFragmentActionProviderImpl.java")).log("Downloading File Group %s...", download$ClientFileGroup.groupName_);
        throw null;
    }

    public MddDebugListAdapter$$ExternalSyntheticLambda0(YearGridAdapter yearGridAdapter, int i, int i2) {
        this.switching_field = i2;
        this.f$1 = i;
        this.MddDebugListAdapter$$ExternalSyntheticLambda0$ar$f$0 = yearGridAdapter;
    }
}

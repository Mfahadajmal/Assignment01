package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.badge.BadgeState;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new BadgeState.State.AnonymousClass1(4);
    final int daysInMonth;
    final int daysInWeek;
    public final Calendar firstOfMonth;
    private String longName;
    public final int month;
    final long timeInMillis;
    final int year;

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar dayCopy = UtcDates.getDayCopy(calendar);
        this.firstOfMonth = dayCopy;
        this.month = dayCopy.get(2);
        this.year = dayCopy.get(1);
        this.daysInWeek = dayCopy.getMaximum(7);
        this.daysInMonth = dayCopy.getActualMaximum(5);
        this.timeInMillis = dayCopy.getTimeInMillis();
    }

    public static Month create(int i, int i2) {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.set(1, i);
        utcCalendar.set(2, i2);
        return new Month(utcCalendar);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Month month) {
        return this.firstOfMonth.compareTo(month.firstOfMonth);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        if (this.month == month.month && this.year == month.year) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getLongName() {
        String format;
        if (this.longName == null) {
            format = UtcDates.getAndroidFormat("yMMMM", Locale.getDefault()).format(new Date(this.firstOfMonth.getTimeInMillis()));
            this.longName = format;
        }
        return this.longName;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Month monthsLater(int i) {
        Calendar dayCopy = UtcDates.getDayCopy(this.firstOfMonth);
        dayCopy.add(2, i);
        return new Month(dayCopy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int monthsUntil(Month month) {
        if (this.firstOfMonth instanceof GregorianCalendar) {
            return ((month.year - this.year) * 12) + (month.month - this.month);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.year);
        parcel.writeInt(this.month);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Month create(long j) {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(j);
        return new Month(utcCalendar);
    }
}

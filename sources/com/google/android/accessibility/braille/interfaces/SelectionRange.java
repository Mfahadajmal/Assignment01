package com.google.android.accessibility.braille.interfaces;

import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectionRange {
    public final int end;
    public final int start;

    public SelectionRange(int i, int i2) {
        this.start = i;
        this.end = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SelectionRange)) {
            return false;
        }
        SelectionRange selectionRange = (SelectionRange) obj;
        if (this.start == selectionRange.start && this.end == selectionRange.end) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.start), Integer.valueOf(this.end));
    }

    public final String toString() {
        return String.format("SelectionRange {start=%s, end=%s}", Integer.valueOf(this.start), Integer.valueOf(this.end));
    }
}

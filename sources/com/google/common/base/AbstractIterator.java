package com.google.common.base;

import androidx.preference.Preference;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AbstractIterator implements Iterator {
    int limit;
    private Object next;
    int offset;
    final boolean omitEmptyStrings;
    public int state$ar$edu$38d3c429_0;
    final CharSequence toSplit;
    final CharMatcher trimmer;

    protected AbstractIterator() {
        this.state$ar$edu$38d3c429_0 = 2;
    }

    protected final /* bridge */ /* synthetic */ Object computeNext() {
        int separatorStart;
        int separatorEnd;
        int i = this.offset;
        while (true) {
            int i2 = this.offset;
            if (i2 != -1) {
                separatorStart = separatorStart(i2);
                if (separatorStart == -1) {
                    separatorStart = this.toSplit.length();
                    this.offset = -1;
                    separatorEnd = -1;
                } else {
                    separatorEnd = separatorEnd(separatorStart);
                    this.offset = separatorEnd;
                }
                if (separatorEnd == i) {
                    int i3 = separatorEnd + 1;
                    this.offset = i3;
                    if (i3 > this.toSplit.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (i < separatorStart && this.trimmer.matches(this.toSplit.charAt(i))) {
                        i++;
                    }
                    while (separatorStart > i) {
                        int i4 = separatorStart - 1;
                        if (!this.trimmer.matches(this.toSplit.charAt(i4))) {
                            break;
                        }
                        separatorStart = i4;
                    }
                    if (!this.omitEmptyStrings || i != separatorStart) {
                        break;
                    }
                    i = this.offset;
                }
            } else {
                this.state$ar$edu$38d3c429_0 = 3;
                return null;
            }
        }
        int i5 = this.limit;
        if (i5 == 1) {
            separatorStart = this.toSplit.length();
            this.offset = -1;
            while (separatorStart > i) {
                int i6 = separatorStart - 1;
                if (!this.trimmer.matches(this.toSplit.charAt(i6))) {
                    break;
                }
                separatorStart = i6;
            }
        } else {
            this.limit = i5 - 1;
        }
        return this.toSplit.subSequence(i, separatorStart).toString();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        boolean z;
        if (this.state$ar$edu$38d3c429_0 != 4) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z);
        int i = this.state$ar$edu$38d3c429_0;
        int i2 = i - 1;
        if (i != 0) {
            if (i2 == 0) {
                return true;
            }
            if (i2 != 2) {
                this.state$ar$edu$38d3c429_0 = 4;
                this.next = computeNext();
                if (this.state$ar$edu$38d3c429_0 != 3) {
                    this.state$ar$edu$38d3c429_0 = 1;
                    return true;
                }
            }
            return false;
        }
        throw null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            this.state$ar$edu$38d3c429_0 = 2;
            Object obj = this.next;
            this.next = null;
            return obj;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public abstract int separatorEnd(int i);

    public abstract int separatorStart(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractIterator(Splitter splitter, CharSequence charSequence) {
        this();
        this.offset = 0;
        this.trimmer = splitter.trimmer;
        this.omitEmptyStrings = splitter.omitEmptyStrings;
        this.limit = Preference.DEFAULT_ORDER;
        this.toSplit = charSequence;
    }
}

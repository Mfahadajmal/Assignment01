package com.google.android.accessibility.selecttospeak.iterator;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SentenceIterator implements ListIterator, KMappedMarker {
    public final int characterCount;
    public final List list;
    public int currentIndex = -1;
    public final InSentenceOffset offsetTracker = new InSentenceOffset(null);

    public SentenceIterator(List list, int i) {
        this.list = list;
        this.characterCount = i;
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ void add(Object obj) {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_7();
    }

    public final Sentence getPeek() {
        return (Sentence) OnDeviceLanguageIdentificationLogEvent.getOrNull(this.list, this.currentIndex);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        if (OnDeviceLanguageIdentificationLogEvent.getOrNull(this.list, this.currentIndex + 1) != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (OnDeviceLanguageIdentificationLogEvent.getOrNull(this.list, this.currentIndex - 1) != null) {
            return true;
        }
        return false;
    }

    public final Sentence moveAndResetIndex(Function0 function0) {
        this.offsetTracker.resetOffset();
        Sentence sentence = (Sentence) function0.invoke();
        this.currentIndex = this.list.indexOf(sentence);
        return sentence;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Sentence next() {
        this.offsetTracker.resetOffset();
        int i = this.currentIndex + 1;
        this.currentIndex = i;
        return (Sentence) this.list.get(i);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.currentIndex + 1;
    }

    @Override // java.util.ListIterator
    public final Sentence previous() {
        this.offsetTracker.resetOffset();
        int i = this.currentIndex - 1;
        this.currentIndex = i;
        return (Sentence) this.list.get(i);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.currentIndex - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }

    public final void reset() {
        this.offsetTracker.resetOffset();
        this.currentIndex = -1;
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ void set(Object obj) {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_7();
    }
}

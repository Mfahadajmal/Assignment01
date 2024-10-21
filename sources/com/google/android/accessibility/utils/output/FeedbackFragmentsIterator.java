package com.google.android.accessibility.utils.output;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeedbackFragmentsIterator {
    public final AtomicReference currentFeedbackFragment = new AtomicReference();
    public Iterator currentFragmentIterator;
    public String feedBackItemUtteranceId;

    public FeedbackFragmentsIterator(Iterator it) {
        this.currentFragmentIterator = it;
    }
}

package com.google.android.accessibility.selecttospeak.iterator;

import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TextLocationFinder {
    boolean getSupportsTextLocation();

    List getTextLocation(boolean z, int i, int i2, int i3);
}

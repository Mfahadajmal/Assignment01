package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PageContentConfig {
    public PageConfig.PageAndContentPredicate predicate;

    public abstract View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData);

    public final void setShowingPredicate(PageConfig.PageAndContentPredicate pageAndContentPredicate) {
        this.predicate = pageAndContentPredicate;
    }
}

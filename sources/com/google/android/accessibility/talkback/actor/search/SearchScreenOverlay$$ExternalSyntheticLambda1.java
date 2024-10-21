package com.google.android.accessibility.talkback.actor.search;

import android.os.Handler;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SearchScreenOverlay$$ExternalSyntheticLambda1 implements SearchScreenOverlay.Action {
    public final /* synthetic */ SearchScreenOverlay f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SearchScreenOverlay$$ExternalSyntheticLambda1(SearchScreenOverlay searchScreenOverlay, int i) {
        this.switching_field = i;
        this.f$0 = searchScreenOverlay;
    }

    @Override // com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay.Action
    public final void act() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                this.f$0.scrollScreen$ar$ds(4096);
                return;
            } else {
                new Handler().postDelayed(new VoiceActionMonitor$$ExternalSyntheticLambda0(this.f$0, 11), 500L);
                return;
            }
        }
        this.f$0.scrollScreen$ar$ds(8192);
    }
}

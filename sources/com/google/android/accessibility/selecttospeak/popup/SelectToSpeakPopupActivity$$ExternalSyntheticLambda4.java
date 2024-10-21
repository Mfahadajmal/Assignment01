package com.google.android.accessibility.selecttospeak.popup;

import android.view.View;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SelectToSpeakPopupActivity$$ExternalSyntheticLambda4 implements View.OnClickListener {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SelectToSpeakPopupActivity$$ExternalSyntheticLambda4(int i) {
        this.switching_field = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.switching_field != 0) {
            LogUtils.d("SelectToSpeakService", "Trigger button clicked.", new Object[0]);
            SelectToSpeakService.trigger$ar$ds();
        }
    }
}

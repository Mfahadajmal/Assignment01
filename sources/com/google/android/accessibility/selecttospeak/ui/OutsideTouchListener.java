package com.google.android.accessibility.selecttospeak.ui;

import android.view.MotionEvent;
import android.view.View;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OutsideTouchListener implements View.OnTouchListener {
    final /* synthetic */ SelectToSpeakService this$0;

    public OutsideTouchListener() {
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 4) {
            onTouchOutside();
            return false;
        }
        return false;
    }

    public final void onTouchOutside() {
        this.this$0.activateMultitaskingIfNecessary();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OutsideTouchListener(SelectToSpeakService selectToSpeakService) {
        this();
        this.this$0 = selectToSpeakService;
    }
}

package com.google.android.accessibility.utils.material;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import com.google.android.accessibility.talkback.trainingcommon.tv.TvNavigationButton$$ExternalSyntheticLambda0;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AlertDialogAdaptiveContrastUtil$V7AlertDialogAdaptiveContrastBuilder extends AlertDialog.Builder {
    private final Context context;

    public AlertDialogAdaptiveContrastUtil$V7AlertDialogAdaptiveContrastBuilder(Context context) {
        super(context, R.style.A11yAlertDialogTheme);
        this.context = context;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final AlertDialog create() {
        AlertDialog create = super.create();
        create.create();
        Button button = create.getButton(-1);
        Button button2 = create.getButton(-2);
        TvNavigationButton$$ExternalSyntheticLambda0 tvNavigationButton$$ExternalSyntheticLambda0 = new TvNavigationButton$$ExternalSyntheticLambda0(this.context, 2);
        if (button != null) {
            button.setOnFocusChangeListener(tvNavigationButton$$ExternalSyntheticLambda0);
        }
        if (button2 != null) {
            button2.setOnFocusChangeListener(tvNavigationButton$$ExternalSyntheticLambda0);
        }
        return create;
    }
}

package com.google.android.accessibility.talkback.contextmenu;

import android.content.DialogInterface;
import android.os.SystemClock;
import com.google.android.accessibility.talkback.NotificationActivity;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.accessibility.talkback.labeling.LabelImportActivity;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ListMenuManager$$ExternalSyntheticLambda4 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ Object ListMenuManager$$ExternalSyntheticLambda4$ar$f$0;
    private final /* synthetic */ int switching_field;

    public ListMenuManager$$ExternalSyntheticLambda4(LabelImportActivity labelImportActivity, int i) {
        this.switching_field = i;
        this.ListMenuManager$$ExternalSyntheticLambda4$ar$f$0 = labelImportActivity;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    ((LabelImportActivity) this.ListMenuManager$$ExternalSyntheticLambda4$ar$f$0).finish();
                    return;
                } else {
                    ((BaseDialog) this.ListMenuManager$$ExternalSyntheticLambda4$ar$f$0).m99x18676454(dialogInterface);
                    return;
                }
            }
            ((NotificationActivity) this.ListMenuManager$$ExternalSyntheticLambda4$ar$f$0).finish();
            return;
        }
        ListMenuManager listMenuManager = (ListMenuManager) this.ListMenuManager$$ExternalSyntheticLambda4$ar$f$0;
        listMenuManager.lastMenuDismissUptimeMs = SystemClock.uptimeMillis();
        int i2 = listMenuManager.menuShown - 1;
        listMenuManager.menuShown = i2;
        if (i2 == 0) {
            listMenuManager.currentDialog = null;
        }
    }

    public /* synthetic */ ListMenuManager$$ExternalSyntheticLambda4(Object obj, int i) {
        this.switching_field = i;
        this.ListMenuManager$$ExternalSyntheticLambda4$ar$f$0 = obj;
    }
}

package com.google.android.accessibility.talkback.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.core.app.ActivityCompat;
import com.android.talkback.TalkBackPreferencesActivity;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PermissionRequestActivity$$ExternalSyntheticLambda0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Object PermissionRequestActivity$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PermissionRequestActivity$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.PermissionRequestActivity$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.switching_field;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            ((TrainingActivity) this.PermissionRequestActivity$$ExternalSyntheticLambda0$ar$f$0).finishOnAbort(true);
                            return;
                        }
                        ((TrainingActivity) this.PermissionRequestActivity$$ExternalSyntheticLambda0$ar$f$0).startActivity(SpannableUtils$IdentifierSpan.getAccessibilitySettingsAndHighLightTalkBackIntent());
                        return;
                    }
                    ((Context) this.PermissionRequestActivity$$ExternalSyntheticLambda0$ar$f$0).startActivity(SpannableUtils$IdentifierSpan.getAccessibilitySettingsAndHighLightTalkBackIntent());
                    return;
                }
                ((PermissionRequestActivity) this.PermissionRequestActivity$$ExternalSyntheticLambda0$ar$f$0).finish();
                return;
            }
            KeyComboManager keyComboManager = (KeyComboManager) this.PermissionRequestActivity$$ExternalSyntheticLambda0$ar$f$0;
            Intent intent = new Intent(keyComboManager.context, (Class<?>) TalkBackPreferencesActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("FragmentName", TalkBackKeyboardShortcutPreferenceFragment.class.getName());
            keyComboManager.context.startActivity(intent);
            return;
        }
        ActivityCompat.requestPermissions((Activity) this.PermissionRequestActivity$$ExternalSyntheticLambda0$ar$f$0, new String[]{"android.permission.READ_PHONE_STATE"}, 1);
    }
}

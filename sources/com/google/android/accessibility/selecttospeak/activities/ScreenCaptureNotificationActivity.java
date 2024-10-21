package com.google.android.accessibility.selecttospeak.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ScreenCaptureNotificationActivity extends Activity {
    public LocalBroadcastManager broadcastManager;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NotificationDialogFragment extends DialogFragment {
        @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public final void onCancel(DialogInterface dialogInterface) {
            ((ScreenCaptureNotificationActivity) getActivity()).onCancel();
        }

        @Override // android.app.DialogFragment
        public final Dialog onCreateDialog(Bundle bundle) {
            String string;
            int i = getActivity().getApplicationInfo().labelRes;
            if (i != 0) {
                string = getString(i);
            } else {
                string = getString(R.string.s2s_service_name);
            }
            return SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(getActivity()).setTitle(R.string.permission_required_notice_dialog_title).setMessage(getString(R.string.permission_required_notice_dialog_message, new Object[]{string})).setNegativeButton(android.R.string.cancel, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 16)).setPositiveButton(R.string.permission_required_notice_dialog_positive_button, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 17)).setCancelable(true).create();
        }
    }

    public final void onCancel() {
        this.broadcastManager.sendBroadcast$ar$ds(new Intent("com.google.android.accessibility.selecttospeak.screencapturenotification.ACTION_CANCEL"));
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.broadcastManager = LocalBroadcastManager.getInstance(this);
        new NotificationDialogFragment().show(getFragmentManager(), "dialog");
    }
}

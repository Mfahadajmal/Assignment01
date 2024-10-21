package com.google.android.accessibility.talkback.preference;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import com.android.talkback.TalkBackPreferencesActivity;
import com.google.android.accessibility.talkback.preference.base.TalkBackGestureShortcutPreferenceFragment;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GestureChangeNotificationActivity extends Activity {
    private SharedPreferences prefs;
    private final int[] gestureNameRes = {R.string.title_pref_shortcut_down_and_left, R.string.title_pref_shortcut_up_and_left, R.string.title_pref_shortcut_down_and_right, R.string.title_pref_shortcut_up_and_right, R.string.title_pref_shortcut_right_and_down, R.string.title_pref_shortcut_left_and_down, R.string.title_pref_shortcut_right_and_up, R.string.title_pref_shortcut_left_and_up};
    private final int[] actionNameRes = {R.string.shortcut_back, R.string.shortcut_home, R.string.shortcut_talkback_breakout, R.string.shortcut_talkback_breakout, R.string.shortcut_notifications, R.string.shortcut_unassigned, R.string.shortcut_unassigned, R.string.shortcut_overview};

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPreviouslyConfiguredMappings() {
        SharedPreferences.Editor edit = this.prefs.edit();
        edit.remove(getString(R.string.pref_shortcut_down_and_left_key));
        edit.remove(getString(R.string.pref_shortcut_down_and_right_key));
        edit.remove(getString(R.string.pref_shortcut_up_and_left_key));
        edit.remove(getString(R.string.pref_shortcut_up_and_right_key));
        edit.remove(getString(R.string.pref_shortcut_right_and_down_key));
        edit.remove(getString(R.string.pref_shortcut_right_and_up_key));
        edit.remove(getString(R.string.pref_shortcut_left_and_down_key));
        edit.remove(getString(R.string.pref_shortcut_left_and_up_key));
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissNotification() {
        ((NotificationManager) getSystemService("notification")).cancel(2);
        SharedPreferences.Editor edit = this.prefs.edit();
        edit.remove(getString(R.string.pref_must_accept_gesture_change_notification));
        edit.apply();
    }

    private CharSequence getMappingDescription(int[] iArr, int[] iArr2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i = 0; i < iArr.length; i++) {
            spannableStringBuilder.append((CharSequence) getString(iArr[i])).append((CharSequence) ": ").append((CharSequence) getString(iArr2[i])).append((CharSequence) "\n");
        }
        return spannableStringBuilder;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(this);
        String string = getString(R.string.notification_title_talkback_gestures_changed);
        String string2 = getString(R.string.talkback_gesture_change_details, new Object[]{getMappingDescription(this.gestureNameRes, this.actionNameRes)});
        SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(this).setTitle(string).setMessage(string2).setCancelable(false).setPositiveButton(getString(R.string.button_accept_changed_gesture_mappings), new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.GestureChangeNotificationActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                GestureChangeNotificationActivity.this.clearPreviouslyConfiguredMappings();
                GestureChangeNotificationActivity.this.dismissNotification();
                dialogInterface.dismiss();
                GestureChangeNotificationActivity.this.finish();
            }
        }).setNeutralButton(getString(R.string.button_customize_gesture_mappings), new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.GestureChangeNotificationActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                GestureChangeNotificationActivity.this.clearPreviouslyConfiguredMappings();
                GestureChangeNotificationActivity.this.dismissNotification();
                dialogInterface.dismiss();
                Intent intent = new Intent(GestureChangeNotificationActivity.this.getApplicationContext(), (Class<?>) TalkBackPreferencesActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("FragmentName", TalkBackGestureShortcutPreferenceFragment.class.getName());
                GestureChangeNotificationActivity.this.startActivity(intent);
                GestureChangeNotificationActivity.this.finish();
            }
        }).create().show();
    }
}

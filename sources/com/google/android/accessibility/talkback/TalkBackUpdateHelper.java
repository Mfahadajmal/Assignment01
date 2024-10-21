package com.google.android.accessibility.talkback;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.common.BrailleUserPreferences$$ExternalSyntheticLambda2;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import j$.util.Collection;
import j$.util.DesugarArrays;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackUpdateHelper {
    public static final String TAG = "TalkBackUpdateHelper";
    public final NotificationManager notificationManager;
    public final TalkBackService service;
    public final SharedPreferences sharedPreferences;
    public final Handler handler = new Handler();
    private final List notificationRunnablePendingList = new ArrayList();
    public final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NotificationPosterRunnable implements Runnable {
        public final int id;
        public final Notification notification;

        public NotificationPosterRunnable(Notification notification, int i) {
            this.notification = notification;
            this.id = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (SpannableUtils$IdentifierSpan.hasPostNotificationPermission(TalkBackUpdateHelper.this.service)) {
                TalkBackUpdateHelper talkBackUpdateHelper = TalkBackUpdateHelper.this;
                talkBackUpdateHelper.notificationManager.notify(this.id, this.notification);
                return;
            }
            TalkBackUpdateHelper talkBackUpdateHelper2 = TalkBackUpdateHelper.this;
            SpannableUtils$IdentifierSpan.requestPostNotificationPermissionIfNeeded(talkBackUpdateHelper2.service, new BroadcastReceiver() { // from class: com.google.android.accessibility.talkback.TalkBackUpdateHelper.NotificationPosterRunnable.1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    if (DesugarArrays.stream(intent.getStringArrayExtra("permissions")).anyMatch(new BrailleUserPreferences$$ExternalSyntheticLambda2(4))) {
                        context.unregisterReceiver(this);
                        if (SpannableUtils$IdentifierSpan.hasPostNotificationPermission(context)) {
                            NotificationPosterRunnable notificationPosterRunnable = NotificationPosterRunnable.this;
                            TalkBackUpdateHelper.this.notificationManager.notify(notificationPosterRunnable.id, notificationPosterRunnable.notification);
                        }
                    }
                }
            });
        }
    }

    public TalkBackUpdateHelper(TalkBackService talkBackService) {
        this.service = talkBackService;
        this.notificationManager = (NotificationManager) talkBackService.getSystemService("notification");
        this.sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(talkBackService);
    }

    public final void addNotificationToPendingList(Notification notification, int i) {
        this.notificationRunnablePendingList.add(new NotificationPosterRunnable(notification, i));
    }

    public final Notification buildGestureChangeNotification(Intent intent) {
        TalkBackService talkBackService = this.service;
        return SpannableUtils$IdentifierSpan.createNotification(talkBackService, talkBackService.getString(R.string.notification_title_talkback_gestures_changed), talkBackService.getString(R.string.notification_title_talkback_gestures_changed), talkBackService.getString(R.string.notification_message_talkback_gestures_changed), PendingIntent.getActivity(talkBackService, 0, intent, 201326592), false);
    }

    public final void copyPreferenceBoolean(SharedPreferences.Editor editor, String str, int i, int i2) {
        Resources resources = this.service.getResources();
        String string = resources.getString(i);
        editor.putBoolean(SpannableUtils$IdentifierSpan.toVerbosityPrefKey(str, string), this.sharedPreferences.getBoolean(string, resources.getBoolean(i2)));
    }

    public final void deprecateStringPreference(SharedPreferences.Editor editor, int i, int i2) {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        TalkBackService talkBackService = this.service;
        String string = talkBackService.getString(i);
        editor.putString(string, sharedPreferences.getString(string, talkBackService.getString(i2)));
    }

    public final void flushPendingNotification() {
        Collection.EL.forEach(this.notificationRunnablePendingList, new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(this, 9));
        this.notificationRunnablePendingList.clear();
    }

    public final void notifyGestureChange(int i, int i2) {
        notifyGestureChange(i, i2, android.R.string.ok, null);
    }

    public final void notifyGestureChange(int i, int i2, int i3, String str) {
        addNotificationToPendingList(buildGestureChangeNotification(NotificationActivity.createStartIntent(this.service, R.string.notification_title_talkback_gestures_changed, i, i2, i3, str)), i2);
    }
}

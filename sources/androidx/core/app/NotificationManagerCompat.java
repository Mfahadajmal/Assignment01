package androidx.core.app;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationManagerCompat {
    private final Context mContext;
    public final NotificationManager mNotificationManager;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Api24Impl {
        public static boolean areNotificationsEnabled(NotificationManager notificationManager) {
            return notificationManager.areNotificationsEnabled();
        }

        static int getImportance(NotificationManager notificationManager) {
            return notificationManager.getImportance();
        }
    }

    static {
        new HashSet();
    }

    public NotificationManagerCompat(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
    }
}

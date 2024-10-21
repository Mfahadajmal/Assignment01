package androidx.core.app;

import android.app.Notification;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationCompat$CallStyle$Api21Impl {
    static Notification.Builder addPerson(Notification.Builder builder, String str) {
        return builder.addPerson(str);
    }

    public static int growSize(int i) {
        if (i <= 4) {
            return 8;
        }
        return i + i;
    }

    static Notification.Builder setCategory(Notification.Builder builder, String str) {
        return builder.setCategory(str);
    }
}

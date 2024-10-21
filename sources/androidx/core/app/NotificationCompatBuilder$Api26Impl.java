package androidx.core.app;

import android.app.Notification;
import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationCompatBuilder$Api26Impl {
    public static float clamp(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public static Notification.Builder createBuilder(Context context, String str) {
        return new Notification.Builder(context, str);
    }

    public static Notification.Builder setBadgeIconType(Notification.Builder builder, int i) {
        return builder.setBadgeIconType(i);
    }

    static Notification.Builder setColorized(Notification.Builder builder, boolean z) {
        return builder.setColorized(z);
    }

    public static Notification.Builder setGroupAlertBehavior(Notification.Builder builder, int i) {
        return builder.setGroupAlertBehavior(i);
    }

    public static Notification.Builder setSettingsText(Notification.Builder builder, CharSequence charSequence) {
        return builder.setSettingsText(charSequence);
    }

    public static Notification.Builder setShortcutId(Notification.Builder builder, String str) {
        return builder.setShortcutId(str);
    }

    public static Notification.Builder setTimeoutAfter(Notification.Builder builder, long j) {
        return builder.setTimeoutAfter(j);
    }

    public static int clamp(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }
}

package androidx.core.app;

import android.app.Notification;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.WrappedDrawable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationCompatBuilder$Api24Impl {
    public static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder builder, boolean z) {
        return builder.setAllowGeneratedReplies(z);
    }

    static Notification.Builder setCustomBigContentView(Notification.Builder builder, RemoteViews remoteViews) {
        return builder.setCustomBigContentView(remoteViews);
    }

    static Notification.Builder setCustomContentView(Notification.Builder builder, RemoteViews remoteViews) {
        return builder.setCustomContentView(remoteViews);
    }

    static Notification.Builder setCustomHeadsUpContentView(Notification.Builder builder, RemoteViews remoteViews) {
        return builder.setCustomHeadsUpContentView(remoteViews);
    }

    public static Notification.Builder setRemoteInputHistory(Notification.Builder builder, CharSequence[] charSequenceArr) {
        return builder.setRemoteInputHistory(charSequenceArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Drawable unwrap(Drawable drawable) {
        if (drawable instanceof WrappedDrawable) {
            return ((WrappedDrawable) drawable).getWrappedDrawable();
        }
        return drawable;
    }
}

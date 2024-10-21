package androidx.core.app;

import android.app.Notification;
import android.content.LocusId;
import android.content.res.Configuration;
import androidx.core.os.ConfigurationCompat$Api24Impl;
import androidx.core.os.LocaleListCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationCompatBuilder$Api29Impl {
    public static LocaleListCompat getLocales(Configuration configuration) {
        return LocaleListCompat.wrap(ConfigurationCompat$Api24Impl.getLocales(configuration));
    }

    public static Notification.Builder setAllowSystemGeneratedContextualActions(Notification.Builder builder, boolean z) {
        return builder.setAllowSystemGeneratedContextualActions(z);
    }

    public static Notification.Builder setBubbleMetadata(Notification.Builder builder, Notification.BubbleMetadata bubbleMetadata) {
        return builder.setBubbleMetadata(bubbleMetadata);
    }

    public static Notification.Action.Builder setContextual(Notification.Action.Builder builder, boolean z) {
        return builder.setContextual(z);
    }

    static Notification.Builder setLocusId(Notification.Builder builder, Object obj) {
        return builder.setLocusId((LocusId) obj);
    }
}

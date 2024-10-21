package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationCompat$Builder {
    public boolean mAllowSystemGeneratedContextualActions;
    public String mChannelId;
    public PendingIntent mContentIntent;
    public CharSequence mContentText;
    public CharSequence mContentTitle;
    public Context mContext;
    public Bundle mExtras;
    public Notification mNotification;

    @Deprecated
    public ArrayList mPeople;
    public int mPriority;
    NotificationCompat$Style mStyle;
    public ArrayList mActions = new ArrayList();
    public ArrayList mPersonList = new ArrayList();
    public ArrayList mInvisibleActions = new ArrayList();
    public boolean mShowWhen = true;
    public boolean mLocalOnly = false;

    public NotificationCompat$Builder(Context context, String str) {
        Notification notification = new Notification();
        this.mNotification = notification;
        this.mContext = context;
        this.mChannelId = str;
        notification.when = System.currentTimeMillis();
        this.mNotification.audioStreamType = -1;
        this.mPriority = 0;
        this.mPeople = new ArrayList();
        this.mAllowSystemGeneratedContextualActions = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static CharSequence limitCharSequenceLength(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        if (charSequence.length() > 5120) {
            return charSequence.subSequence(0, 5120);
        }
        return charSequence;
    }

    private final void setFlag(int i, boolean z) {
        if (z) {
            Notification notification = this.mNotification;
            notification.flags = i | notification.flags;
        } else {
            Notification notification2 = this.mNotification;
            notification2.flags = (~i) & notification2.flags;
        }
    }

    public final void addAction$ar$ds(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        IconCompat createWithResource;
        ArrayList arrayList = this.mActions;
        if (i == 0) {
            createWithResource = null;
        } else {
            createWithResource = IconCompat.createWithResource(null, "", i);
        }
        arrayList.add(new NotificationCompat$Action(createWithResource, charSequence, pendingIntent, new Bundle(), null, true, true));
    }

    public final Notification build() {
        Bundle bundle;
        OptionalMethod optionalMethod = new OptionalMethod(this);
        NotificationCompat$Style notificationCompat$Style = ((NotificationCompat$Builder) optionalMethod.OptionalMethod$ar$methodName).mStyle;
        if (notificationCompat$Style != null) {
            notificationCompat$Style.apply$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(optionalMethod);
        }
        Notification build = ((Notification.Builder) optionalMethod.OptionalMethod$ar$methodParams).build();
        if (notificationCompat$Style != null && (bundle = build.extras) != null) {
            notificationCompat$Style.addCompatExtras(bundle);
        }
        return build;
    }

    public final Bundle getExtras() {
        if (this.mExtras == null) {
            this.mExtras = new Bundle();
        }
        return this.mExtras;
    }

    public final void setAutoCancel$ar$ds(boolean z) {
        setFlag(16, z);
    }

    public final void setContentText$ar$ds(CharSequence charSequence) {
        this.mContentText = limitCharSequenceLength(charSequence);
    }

    public final void setContentTitle$ar$ds(CharSequence charSequence) {
        this.mContentTitle = limitCharSequenceLength(charSequence);
    }

    public final void setOngoing$ar$ds(boolean z) {
        setFlag(2, z);
    }

    public final void setSmallIcon$ar$ds(int i) {
        this.mNotification.icon = i;
    }

    public final void setStyle$ar$ds(NotificationCompat$Style notificationCompat$Style) {
        if (this.mStyle != notificationCompat$Style) {
            this.mStyle = notificationCompat$Style;
            if (notificationCompat$Style != null && notificationCompat$Style.mBuilder != this) {
                notificationCompat$Style.mBuilder = this;
                NotificationCompat$Builder notificationCompat$Builder = notificationCompat$Style.mBuilder;
                if (notificationCompat$Builder != null) {
                    notificationCompat$Builder.setStyle$ar$ds(notificationCompat$Style);
                }
            }
        }
    }

    public final void setTicker$ar$ds(CharSequence charSequence) {
        this.mNotification.tickerText = limitCharSequenceLength(charSequence);
    }

    public final void setWhen$ar$ds(long j) {
        this.mNotification.when = j;
    }
}

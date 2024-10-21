package androidx.core.app;

import android.app.Notification;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationCompat$BigTextStyle extends NotificationCompat$Style {
    private CharSequence mBigText;

    @Override // androidx.core.app.NotificationCompat$Style
    public final void apply$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(OptionalMethod optionalMethod) {
        new Notification.BigTextStyle((Notification.Builder) optionalMethod.OptionalMethod$ar$methodParams).setBigContentTitle(null).bigText(this.mBigText);
    }

    public final void bigText$ar$ds(CharSequence charSequence) {
        this.mBigText = NotificationCompat$Builder.limitCharSequenceLength(charSequence);
    }

    @Override // androidx.core.app.NotificationCompat$Style
    protected final String getClassName() {
        return "androidx.core.app.NotificationCompat$BigTextStyle";
    }
}

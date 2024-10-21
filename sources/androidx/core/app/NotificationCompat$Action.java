package androidx.core.app;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.drawable.IconCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationCompat$Action {
    public PendingIntent actionIntent;

    @Deprecated
    public int icon;
    public boolean mAllowGeneratedReplies;
    public final Bundle mExtras;
    private IconCompat mIcon;
    public final ActivityCompat.Api31Impl[] mRemoteInputs$ar$class_merging;
    public boolean mShowsUserInterface;
    public CharSequence title;

    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public NotificationCompat$Action(androidx.core.graphics.drawable.IconCompat r7, java.lang.CharSequence r8, android.app.PendingIntent r9, android.os.Bundle r10, androidx.core.app.ActivityCompat.Api31Impl[] r11, boolean r12, boolean r13) {
        /*
            r6 = this;
            java.lang.String r12 = "IconCompat"
            java.lang.String r13 = "Unable to get icon type "
            r6.<init>()
            r0 = 1
            r6.mShowsUserInterface = r0
            r6.mIcon = r7
            if (r7 == 0) goto L6f
            int r1 = r7.mType
            r2 = -1
            if (r1 != r2) goto L66
            java.lang.Object r1 = r7.mObj1
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r3 < r4) goto L20
            int r1 = androidx.core.graphics.drawable.IconCompat.Api28Impl.getType(r1)
            goto L66
        L20:
            java.lang.Class r3 = r1.getClass()     // Catch: java.lang.NoSuchMethodException -> L36 java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalAccessException -> L56
            java.lang.String r4 = "getType"
            r5 = 0
            java.lang.reflect.Method r3 = r3.getMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L36 java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalAccessException -> L56
            java.lang.Object r3 = r3.invoke(r1, r5)     // Catch: java.lang.NoSuchMethodException -> L36 java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalAccessException -> L56
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.NoSuchMethodException -> L36 java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalAccessException -> L56
            int r1 = r3.intValue()     // Catch: java.lang.NoSuchMethodException -> L36 java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalAccessException -> L56
            goto L66
        L36:
            r3 = move-exception
            java.util.Objects.toString(r1)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r13 = r13.concat(r1)
            android.util.Log.e(r12, r13, r3)
            goto L65
        L46:
            r3 = move-exception
            java.util.Objects.toString(r1)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r13 = r13.concat(r1)
            android.util.Log.e(r12, r13, r3)
            goto L65
        L56:
            r3 = move-exception
            java.util.Objects.toString(r1)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r13 = r13.concat(r1)
            android.util.Log.e(r12, r13, r3)
        L65:
            r1 = r2
        L66:
            r12 = 2
            if (r1 != r12) goto L6f
            int r7 = r7.getResId()
            r6.icon = r7
        L6f:
            java.lang.CharSequence r7 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r8)
            r6.title = r7
            r6.actionIntent = r9
            r6.mExtras = r10
            r6.mRemoteInputs$ar$class_merging = r11
            r6.mAllowGeneratedReplies = r0
            r6.mShowsUserInterface = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat$Action.<init>(androidx.core.graphics.drawable.IconCompat, java.lang.CharSequence, android.app.PendingIntent, android.os.Bundle, androidx.core.app.ActivityCompat$Api31Impl[], boolean, boolean):void");
    }

    public final IconCompat getIconCompat() {
        int i;
        if (this.mIcon == null && (i = this.icon) != 0) {
            this.mIcon = IconCompat.createWithResource(null, "", i);
        }
        return this.mIcon;
    }
}

package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NotificationCompatBuilder$Api20Impl {
    public static Notification.Builder addAction(Notification.Builder builder, Notification.Action action) {
        return builder.addAction(action);
    }

    public static Notification.Action.Builder addExtras(Notification.Action.Builder builder, Bundle bundle) {
        return builder.addExtras(bundle);
    }

    public static Notification.Action.Builder addRemoteInput(Notification.Action.Builder builder, RemoteInput remoteInput) {
        return builder.addRemoteInput(remoteInput);
    }

    public static Notification.Action build(Notification.Action.Builder builder) {
        return builder.build();
    }

    static Notification.Action.Builder createBuilder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        return new Notification.Action.Builder(i, charSequence, pendingIntent);
    }

    public static int getAttr(Context context, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        if (typedValue.resourceId != 0) {
            return i;
        }
        return i2;
    }

    public static boolean getBoolean(TypedArray typedArray, int i, int i2, boolean z) {
        return typedArray.getBoolean(i, typedArray.getBoolean(i2, z));
    }

    static String getGroup(Notification notification) {
        return notification.getGroup();
    }

    public static int getNamedColor$ar$ds(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (!hasAttribute(xmlPullParser, str)) {
            return 0;
        }
        return typedArray.getColor(i, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0174, code lost:
    
        if (r12.size() <= 0) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0176, code lost:
    
        r0 = new androidx.work.impl.model.WorkName((java.util.List) r12, (java.util.List) r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x017d, code lost:
    
        if (r0 == null) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x018f, code lost:
    
        if (r13 == 1) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0192, code lost:
    
        if (r13 == 2) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0194, code lost:
    
        r15 = r23;
        r1 = new android.graphics.LinearGradient(r14, r15, r16, r17, (int[]) r0.WorkName$ar$name, (float[]) r0.WorkName$ar$workSpecId, androidx.core.app.AppOpsManagerCompat$Api29Impl.parseTileMode(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01e3, code lost:
    
        r0 = new com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener.PendingMessage(r1, null, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01ad, code lost:
    
        r1 = new android.graphics.SweepGradient(r22, r5, (int[]) r0.WorkName$ar$name, (float[]) r0.WorkName$ar$workSpecId);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01c6, code lost:
    
        if (r21 <= 0.0f) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01c8, code lost:
    
        r1 = new android.graphics.RadialGradient(r22, r5, r21, (int[]) r0.WorkName$ar$name, (float[]) r0.WorkName$ar$workSpecId, androidx.core.app.AppOpsManagerCompat$Api29Impl.parseTileMode(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f2, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0181, code lost:
    
        if (r18 == false) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0183, code lost:
    
        r0 = new androidx.work.impl.model.WorkName(r8, r3, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0189, code lost:
    
        r0 = new androidx.work.impl.model.WorkName(r8, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x017c, code lost:
    
        r0 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener.PendingMessage getNamedComplexColor$ar$ds$ar$class_merging$ar$class_merging(android.content.res.TypedArray r26, org.xmlpull.v1.XmlPullParser r27, android.content.res.Resources.Theme r28, java.lang.String r29, int r30) {
        /*
            Method dump skipped, instructions count: 595
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompatBuilder$Api20Impl.getNamedComplexColor$ar$ds$ar$class_merging$ar$class_merging(android.content.res.TypedArray, org.xmlpull.v1.XmlPullParser, android.content.res.Resources$Theme, java.lang.String, int):com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener$PendingMessage");
    }

    public static float getNamedFloat(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, float f) {
        if (!hasAttribute(xmlPullParser, str)) {
            return f;
        }
        return typedArray.getFloat(i, f);
    }

    public static int getNamedInt(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        if (!hasAttribute(xmlPullParser, str)) {
            return i2;
        }
        return typedArray.getInt(i, i2);
    }

    public static int getResourceId(TypedArray typedArray, int i, int i2, int i3) {
        return typedArray.getResourceId(i, typedArray.getResourceId(i2, i3));
    }

    public static String getString(TypedArray typedArray, int i, int i2) {
        String string = typedArray.getString(i);
        if (string == null) {
            return typedArray.getString(i2);
        }
        return string;
    }

    public static CharSequence getText(TypedArray typedArray, int i, int i2) {
        CharSequence text = typedArray.getText(i);
        if (text == null) {
            return typedArray.getText(i2);
        }
        return text;
    }

    public static CharSequence[] getTextArray(TypedArray typedArray, int i, int i2) {
        CharSequence[] textArray = typedArray.getTextArray(i);
        if (textArray == null) {
            return typedArray.getTextArray(i2);
        }
        return textArray;
    }

    public static boolean hasAttribute(XmlPullParser xmlPullParser, String str) {
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null) {
            return true;
        }
        return false;
    }

    public static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static Notification.Builder setGroup(Notification.Builder builder, String str) {
        return builder.setGroup(str);
    }

    public static Notification.Builder setGroupSummary(Notification.Builder builder, boolean z) {
        return builder.setGroupSummary(z);
    }

    public static Notification.Builder setLocalOnly(Notification.Builder builder, boolean z) {
        return builder.setLocalOnly(z);
    }

    public static Notification.Builder setSortKey(Notification.Builder builder, String str) {
        return builder.setSortKey(str);
    }
}

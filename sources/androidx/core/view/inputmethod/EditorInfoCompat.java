package androidx.core.view.inputmethod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat$Api21Impl;
import androidx.core.content.ContextCompat$Api26Impl;
import androidx.core.content.ContextCompat$Api33Impl;
import androidx.core.content.res.ColorStateListInflaterCompat;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EditorInfoCompat {
    public static int checkSelfPermission(Context context, String str) {
        ContextCompat$Api21Impl.requireNonNull$ar$ds$6106c18d_0(str, "permission must be non-null");
        if (Build.VERSION.SDK_INT < 33 && TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
            if (NotificationManagerCompat.Api24Impl.areNotificationsEnabled(new NotificationManagerCompat(context).mNotificationManager)) {
                return 0;
            }
            return -1;
        }
        return context.checkPermission(str, Process.myPid(), Process.myUid());
    }

    public static ColorStateList getColorStateList(Context context, int i) {
        ColorStateList colorStateList;
        Object obj;
        PhenotypeProcessReaper phenotypeProcessReaper;
        Resources.Theme theme;
        Resources resources = context.getResources();
        Resources.Theme theme2 = context.getTheme();
        ResourcesCompat.ColorStateListCacheKey colorStateListCacheKey = new ResourcesCompat.ColorStateListCacheKey(resources, theme2);
        synchronized (ResourcesCompat.sColorStateCacheLock) {
            SparseArray sparseArray = (SparseArray) ResourcesCompat.sColorStateCaches.get(colorStateListCacheKey);
            colorStateList = null;
            if (sparseArray != null && sparseArray.size() > 0 && (phenotypeProcessReaper = (PhenotypeProcessReaper) sparseArray.get(i)) != null) {
                if (((Configuration) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).equals(colorStateListCacheKey.mResources.getConfiguration()) && (((theme = colorStateListCacheKey.mTheme) == null && phenotypeProcessReaper.pollingMinutes == 0) || (theme != null && phenotypeProcessReaper.pollingMinutes == theme.hashCode()))) {
                    obj = phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable;
                } else {
                    sparseArray.remove(i);
                }
            }
            obj = null;
        }
        if (obj == null) {
            TypedValue typedValue = (TypedValue) ResourcesCompat.sTempTypedValue.get();
            if (typedValue == null) {
                typedValue = new TypedValue();
                ResourcesCompat.sTempTypedValue.set(typedValue);
            }
            resources.getValue(i, typedValue, true);
            if (typedValue.type < 28 || typedValue.type > 31) {
                try {
                    colorStateList = ColorStateListInflaterCompat.createFromXml(resources, resources.getXml(i), theme2);
                } catch (Exception e) {
                    Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e);
                }
            }
            if (colorStateList != null) {
                synchronized (ResourcesCompat.sColorStateCacheLock) {
                    SparseArray sparseArray2 = (SparseArray) ResourcesCompat.sColorStateCaches.get(colorStateListCacheKey);
                    if (sparseArray2 == null) {
                        sparseArray2 = new SparseArray();
                        ResourcesCompat.sColorStateCaches.put(colorStateListCacheKey, sparseArray2);
                    }
                    sparseArray2.append(i, new PhenotypeProcessReaper(colorStateList, colorStateListCacheKey.mResources.getConfiguration(), theme2));
                }
                obj = colorStateList;
            } else {
                obj = ResourcesCompat.Api23Impl.getColorStateList(resources, i, theme2);
            }
        }
        return (ColorStateList) obj;
    }

    public static boolean isCutOnSurrogate(CharSequence charSequence, int i, int i2) {
        if (i2 != 0) {
            return Character.isHighSurrogate(charSequence.charAt(i));
        }
        return Character.isLowSurrogate(charSequence.charAt(i));
    }

    public static void registerReceiver$ar$ds(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i) {
        if ((i & 1) != 0) {
            if ((i & 4) == 0) {
                i |= 2;
            } else {
                throw new IllegalArgumentException("Cannot specify both RECEIVER_VISIBLE_TO_INSTANT_APPS and RECEIVER_NOT_EXPORTED");
            }
        }
        int i2 = i;
        if ((i2 & 2) == 0) {
            if ((i2 & 4) == 0) {
                throw new IllegalArgumentException("One of either RECEIVER_EXPORTED or RECEIVER_NOT_EXPORTED is required");
            }
        } else if ((i2 & 4) != 0) {
            throw new IllegalArgumentException("Cannot specify both RECEIVER_EXPORTED and RECEIVER_NOT_EXPORTED");
        }
        if (Build.VERSION.SDK_INT >= 33) {
            ContextCompat$Api33Impl.registerReceiver(context, broadcastReceiver, intentFilter, null, null, i2);
        } else {
            ContextCompat$Api26Impl.registerReceiver(context, broadcastReceiver, intentFilter, null, null, i2);
        }
    }

    public static void setSurroundingText(EditorInfo editorInfo, CharSequence charSequence, int i, int i2) {
        SpannableStringBuilder spannableStringBuilder;
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        if (charSequence != null) {
            spannableStringBuilder = new SpannableStringBuilder(charSequence);
        } else {
            spannableStringBuilder = null;
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", spannableStringBuilder);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i2);
    }
}

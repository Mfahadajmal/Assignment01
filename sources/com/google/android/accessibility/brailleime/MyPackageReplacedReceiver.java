package com.google.android.accessibility.brailleime;

import _COROUTINE._BOUNDARY;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.utils.AccessibilityServiceCompatUtils$Constants;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MyPackageReplacedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        _BOUNDARY.d("MyPackageReplacedReceiver", "MY_PACKAGE_REPLACED received.");
        if (!SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(context, context.getPackageName()) && BrailleUserPreferences.shouldLaunchTutorial(context)) {
            _BOUNDARY.setComponentEnabled$ar$ds(context, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD, false);
        } else {
            _BOUNDARY.setComponentEnabled$ar$ds(context, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD, true);
        }
        BrailleUserPreferences.migrateIfNecessary(context);
    }
}

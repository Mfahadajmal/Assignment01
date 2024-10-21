package com.google.android.play.core.missingsplits;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.android.play.core.util.PhoneskyVerificationUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PlayCoreMissingSplitsActivity extends Activity implements DialogInterface.OnClickListener {
    private final String getAppName() {
        return getApplicationInfo().loadLabel(getPackageManager()).toString();
    }

    private final void openPlayStoreDetailsPageForApp(String str) {
        try {
            startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "market://details?id=", "&referrer=utm_source%3Dplay.core.missingsplits"))).setPackage("com.android.vending"));
        } catch (ActivityNotFoundException e) {
            new SplitInstallModule(getClass().getName()).e$ar$ds$fb17e3b8_0(e, "Couldn't start missing splits activity for %s", str);
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            openPlayStoreDetailsPageForApp(getPackageName());
        }
        finish();
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder neutralButton = new AlertDialog.Builder(this).setTitle("Installation failed").setCancelable(false).setNeutralButton("Close", this);
        if (PhoneskyVerificationUtils.isPhoneskyInstalled(this)) {
            neutralButton.setMessage("The app " + getAppName() + " is missing required components and must be reinstalled from the Google Play Store.").setPositiveButton("Reinstall", this);
        } else {
            neutralButton.setMessage("The app " + getAppName() + " is missing required components and must be reinstalled from an official store.");
        }
        neutralButton.create().show();
    }
}

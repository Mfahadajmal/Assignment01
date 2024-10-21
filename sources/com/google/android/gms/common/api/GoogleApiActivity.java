package com.google.android.gms.common.api;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    protected int resolutionStatus = 0;

    public static Intent getIntentForResolution(Context context, PendingIntent pendingIntent, int i, boolean z) {
        Intent intent = new Intent(context, (Class<?>) GoogleApiActivity.class);
        intent.putExtra("pending_intent", pendingIntent);
        intent.putExtra("failing_client_id", i);
        intent.putExtra("notify_manager", z);
        return intent;
    }

    private final void startResolution() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e("GoogleApiActivity", "Activity started without extras");
            finish();
            return;
        }
        PendingIntent pendingIntent = (PendingIntent) extras.get("pending_intent");
        Integer num = (Integer) extras.get("error_code");
        if (pendingIntent == null && num == null) {
            Log.e("GoogleApiActivity", "Activity started without resolution");
            finish();
            return;
        }
        if (pendingIntent != null) {
            try {
                startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0);
                this.resolutionStatus = 1;
                return;
            } catch (ActivityNotFoundException e) {
                if (extras.getBoolean("notify_manager", true)) {
                    GoogleApiManager.getInstance(this).onErrorResolutionFailed(new ConnectionResult(22, null), getIntent().getIntExtra("failing_client_id", -1));
                } else {
                    String _BOUNDARY$ar$MethodOutlining$dc56d17a_10 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(pendingIntent, "Activity not found while launching ", ".");
                    if (Build.FINGERPRINT.contains("generic")) {
                        _BOUNDARY$ar$MethodOutlining$dc56d17a_10 = _BOUNDARY$ar$MethodOutlining$dc56d17a_10.concat(" This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.");
                    }
                    Log.e("GoogleApiActivity", _BOUNDARY$ar$MethodOutlining$dc56d17a_10, e);
                }
                this.resolutionStatus = 1;
                finish();
                return;
            } catch (IntentSender.SendIntentException e2) {
                Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e2);
                finish();
                return;
            }
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(num);
        GoogleApiAvailability.INSTANCE.showErrorDialogFragment$ar$ds(this, num.intValue(), 2, this);
        this.resolutionStatus = 1;
    }

    @Override // android.app.Activity
    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.resolutionStatus = 0;
            setResult(i2, intent);
            if (booleanExtra) {
                GoogleApiManager googleApiManager = GoogleApiManager.getInstance(this);
                if (i2 != -1) {
                    if (i2 == 0) {
                        googleApiManager.onErrorResolutionFailed(new ConnectionResult(13, null), getIntent().getIntExtra("failing_client_id", -1));
                    }
                } else {
                    googleApiManager.onErrorsResolved();
                }
            }
        } else if (i == 2) {
            this.resolutionStatus = 0;
            setResult(i2, intent);
        }
        finish();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        this.resolutionStatus = 0;
        setResult(0);
        finish();
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.resolutionStatus = bundle.getInt("resolution");
        }
        if (this.resolutionStatus != 1) {
            startResolution();
        }
    }

    @Override // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("resolution", this.resolutionStatus);
        super.onSaveInstanceState(bundle);
    }
}

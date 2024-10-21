package com.google.android.libraries.social.licenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BackStackRecord;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LicenseMenuActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.libraries_social_licenses_license_menu_activity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (!(supportFragmentManager.findFragmentById(R.id.license_menu_fragment_container) instanceof LicenseMenuFragment)) {
            LicenseMenuFragment licenseMenuFragment = new LicenseMenuFragment();
            BackStackRecord backStackRecord = new BackStackRecord(supportFragmentManager);
            backStackRecord.add$ar$ds(R.id.license_menu_fragment_container, licenseMenuFragment);
            backStackRecord.commitNow();
        }
    }

    public final void onLicenseSelected(License license) {
        Intent intent = new Intent(this, (Class<?>) LicenseActivity.class);
        intent.putExtra("license", license);
        startActivity(intent);
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

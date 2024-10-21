package com.google.android.libraries.social.licenses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.WindowDecorActionBar;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.marvin.talkback.R;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LicenseActivity extends AppCompatActivity {
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.libraries_social_licenses_license_activity);
        License license = (License) getIntent().getParcelableExtra("license");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(license.libraryName);
            ((WindowDecorActionBar) getSupportActionBar()).setDisplayOptions(2, 2);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((WindowDecorActionBar) getSupportActionBar()).mDecorToolbar$ar$class_merging.setLogo(null);
        }
        TextView textView = (TextView) findViewById(R.id.license_activity_textview);
        long j = license.licenseOffset;
        int i = license.licenseLength;
        String str2 = license.path;
        if (str2.isEmpty()) {
            str = CurrentProcess.getTextFromResource$ar$ds(this, "third_party_licenses", j, i);
        } else {
            try {
                String textFromInputStream = CurrentProcess.getTextFromInputStream(new BufferedInputStream(new FileInputStream(str2)), j, i);
                if (textFromInputStream != null) {
                    if (!textFromInputStream.isEmpty()) {
                        str = textFromInputStream;
                    }
                }
            } catch (FileNotFoundException unused) {
            }
            throw new RuntimeException(String.valueOf(str2).concat(" does not contain res/raw/third_party_licenses"));
        }
        if (str == null) {
            finish();
        } else {
            textView.setText(str);
        }
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public final void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        ScrollView scrollView = (ScrollView) findViewById(R.id.license_activity_scrollview);
        int i = bundle.getInt("scroll_pos");
        if (i != 0) {
            scrollView.post(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(this, i, scrollView, 5));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ScrollView scrollView = (ScrollView) findViewById(R.id.license_activity_scrollview);
        Layout layout = ((TextView) findViewById(R.id.license_activity_textview)).getLayout();
        if (layout != null) {
            bundle.putInt("scroll_pos", layout.getLineStart(layout.getLineForVertical(scrollView.getScrollY())));
        }
    }
}

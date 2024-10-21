package androidx.browser.customtabs;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.work.impl.model.WorkName;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CustomTabsIntent$Builder {
    private ActivityOptions mActivityOptions;
    public Bundle mDefaultColorSchemeBundle;
    private final Intent mIntent = new Intent("android.intent.action.VIEW");
    public final OnDeviceTextDetectionLoadLogEvent mDefaultColorSchemeBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
    private final boolean mInstantAppsEnabled = true;

    public final WorkName build$ar$class_merging$d5984029_0$ar$class_merging() {
        Bundle bundle;
        Bundle bundle2 = null;
        if (!this.mIntent.hasExtra("android.support.customtabs.extra.SESSION")) {
            Bundle bundle3 = new Bundle();
            bundle3.putBinder("android.support.customtabs.extra.SESSION", null);
            this.mIntent.putExtras(bundle3);
        }
        this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
        this.mIntent.putExtras(this.mDefaultColorSchemeBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.build$ar$class_merging$ar$class_merging$ar$class_merging().toBundle());
        Bundle bundle4 = this.mDefaultColorSchemeBundle;
        if (bundle4 != null) {
            this.mIntent.putExtras(bundle4);
        }
        this.mIntent.putExtra("androidx.browser.customtabs.extra.SHARE_STATE", 0);
        String defaultLocale = CustomTabsIntent$Api24Impl.getDefaultLocale();
        if (!TextUtils.isEmpty(defaultLocale)) {
            if (this.mIntent.hasExtra("com.android.browser.headers")) {
                bundle = this.mIntent.getBundleExtra("com.android.browser.headers");
            } else {
                bundle = new Bundle();
            }
            if (!bundle.containsKey("Accept-Language")) {
                bundle.putString("Accept-Language", defaultLocale);
                this.mIntent.putExtra("com.android.browser.headers", bundle);
            }
        }
        if (Build.VERSION.SDK_INT >= 34) {
            if (this.mActivityOptions == null) {
                this.mActivityOptions = CustomTabsIntent$Api23Impl.makeBasicActivityOptions();
            }
            CustomTabsIntent$Api34Impl.setShareIdentityEnabled(this.mActivityOptions, false);
        }
        ActivityOptions activityOptions = this.mActivityOptions;
        if (activityOptions != null) {
            bundle2 = activityOptions.toBundle();
        }
        return new WorkName(this.mIntent, bundle2);
    }
}

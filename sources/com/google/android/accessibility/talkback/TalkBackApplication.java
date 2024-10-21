package com.google.android.accessibility.talkback;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.play.core.splitcompat.SplitCompatApplication;
import com.google.common.base.Optional;
import dagger.android.HasAndroidInjector;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackApplication extends SplitCompatApplication implements PhenotypeContext.PhenotypeApplication, HasAndroidInjector {
    @Override // com.google.android.libraries.phenotype.client.PhenotypeContext.PhenotypeApplication
    public final Optional getPhenotypeContext() {
        return Optional.of(new PhenotypeContext(this));
    }

    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        PhenotypeContext.setContext(this);
        FormFactorUtils.instance = new FormFactorUtils(this);
        PackageManager packageManager = getPackageManager();
        ResolveInfo resolveActivity = packageManager.resolveActivity(new Intent("android.provider.Telephony.SMS_CB_RECEIVED"), 1048576);
        String str = null;
        if (resolveActivity == null) {
            LogUtils.d("PackageNameProvider", "getDefaultCellBroadcastReceiverPackageName: no package found", new Object[0]);
        } else {
            String str2 = resolveActivity.activityInfo.applicationInfo.packageName;
            LogUtils.d("PackageNameProvider", "getDefaultCellBroadcastReceiverPackageName: found package: %s", str2);
            if (!TextUtils.isEmpty(str2) && packageManager.checkPermission("android.permission.RECEIVE_EMERGENCY_BROADCAST", str2) != -1) {
                str = str2;
            } else {
                LogUtils.d("PackageNameProvider", "getDefaultCellBroadcastReceiverPackageName: returning null; permission check failed for : %s", str2);
            }
        }
        PackageNameProvider.CELL_BROADCAST_RECEIVER_PACKAGE_NAME = str;
        LogUtils.d("PackageNameProvider", "CELL_BROADCAST_RECEIVER_PACKAGE_NAME=".concat(String.valueOf(PackageNameProvider.CELL_BROADCAST_RECEIVER_PACKAGE_NAME)), new Object[0]);
        PackageNameProvider.HOME_ACTIVITY_RESOLVED_INFO = getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME"), 65536);
        LogUtils.d("PackageNameProvider", "HOME_ACTIVITY_RESOLVED_INFO=".concat(String.valueOf(String.valueOf(PackageNameProvider.HOME_ACTIVITY_RESOLVED_INFO))), new Object[0]);
        PackageNameProvider.isInitialized = true;
        WindowTrackerFactory.trainingActivityInterfaceInjector$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory(new SpannableUtils$IdentifierSpan(), new SpannableUtils$IdentifierSpan());
    }

    @Override // dagger.android.HasAndroidInjector
    public final void androidInjector$ar$class_merging$ar$ds() {
    }
}

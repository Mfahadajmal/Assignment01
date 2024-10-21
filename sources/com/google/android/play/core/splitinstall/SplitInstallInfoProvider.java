package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import io.grpc.internal.RetryingNameResolver;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallInfoProvider {
    private static final SplitInstallModule logger$ar$class_merging$ceb098d3_0$ar$class_merging = new SplitInstallModule("SplitInstallInfoProvider");
    private final Context context;
    private final String packageName;

    public SplitInstallInfoProvider(Context context) {
        this.context = context;
        this.packageName = context.getPackageName();
    }

    public static String getModuleName(String str) {
        if (isBaseConfigSplit(str)) {
            return "";
        }
        return str.split("\\.config\\.", 2)[0];
    }

    public static boolean isBaseConfigSplit(String str) {
        return str.startsWith("config.");
    }

    public static boolean isConfigSplit(String str) {
        if (!isBaseConfigSplit(str) && !str.contains(".config.")) {
            return false;
        }
        return true;
    }

    public final Set getInstalledAndEmulatedModules() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null && packageInfo.applicationInfo != null) {
            return getInstalledAndEmulatedModules(packageInfo);
        }
        return new HashSet();
    }

    public final Set getInstalledAndEmulatedSplits(PackageInfo packageInfo) {
        String string;
        Bundle bundle = packageInfo.applicationInfo.metaData;
        HashSet hashSet = new HashSet();
        if (bundle != null && (string = bundle.getString("com.android.dynamic.apk.fused.modules")) != null && !string.isEmpty()) {
            Collections.addAll(hashSet, string.split(",", -1));
            hashSet.remove("");
            hashSet.remove("base");
        }
        String[] strArr = packageInfo.splitNames;
        if (strArr != null) {
            Arrays.toString(strArr);
            Collections.addAll(hashSet, strArr);
        }
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) SplitInstallEmulatedSplitsProvider.provider.get();
        if (resolutionResultListener != null) {
            hashSet.addAll(resolutionResultListener.getEmulatedSplits());
        }
        return hashSet;
    }

    public final SplitInstallModule getLanguageSplitMapping$ar$class_merging$bfee7109_0(Bundle bundle) {
        if (bundle == null) {
            logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("No metadata found in Context.", new Object[0]);
            return null;
        }
        int i = bundle.getInt("com.android.vending.splits");
        if (i == 0) {
            logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("No metadata found in AndroidManifest.", new Object[0]);
            return null;
        }
        try {
            SplitInstallModule parse$ar$objectUnboxing$ar$class_merging$ar$class_merging = NativeLibraryPathListMutex.parse$ar$objectUnboxing$ar$class_merging$ar$class_merging(this.context.getResources().getXml(i), new SplitInstallModule());
            if (parse$ar$objectUnboxing$ar$class_merging$ar$class_merging == null) {
                logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("Can't parse languages metadata.", new Object[0]);
            }
            return parse$ar$objectUnboxing$ar$class_merging$ar$class_merging;
        } catch (Resources.NotFoundException unused) {
            logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("Resource with languages metadata doesn't exist.", new Object[0]);
            return null;
        }
    }

    public final PackageInfo getPackageInfo() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.packageName, BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
        } catch (PackageManager.NameNotFoundException unused) {
            logger$ar$class_merging$ceb098d3_0$ar$class_merging.e$ar$ds$cdf76eb7_0("App is not found in PackageManager", new Object[0]);
            return null;
        }
    }

    public final Set getInstalledAndEmulatedModules(PackageInfo packageInfo) {
        HashSet hashSet = new HashSet();
        for (String str : getInstalledAndEmulatedSplits(packageInfo)) {
            if (!isConfigSplit(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }
}

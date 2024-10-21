package org.chromium.base;

import _COROUTINE._BOUNDARY;
import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BuildInfo {
    public final String abiString;
    public final String androidBuildFingerprint;
    public final String customThemes;
    public final String gmsVersionCode;
    public final String hostPackageLabel;
    public final String hostPackageName;
    public final long hostVersionCode;
    public final String installerPackageName;
    public final boolean isAutomotive;
    public final boolean isFoldable;
    public final boolean isTV;
    public final String packageName;
    public final String resourcesVersion;
    public final long versionCode = 1;
    public final String versionName;
    public final int vulkanDeqpLevel;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Holder {
        public static final BuildInfo INSTANCE = new BuildInfo();
    }

    public BuildInfo() {
        Long l;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        boolean z;
        boolean z2;
        boolean z3;
        FeatureInfo[] systemAvailableFeatures;
        Context context = ContextUtils.sApplicationContext;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        String str7 = null;
        if (CommandLine.sCommandLine.get() != null) {
            CommandLine commandLine = CommandLine.getInstance();
            String switchValue$ar$ds = commandLine.getSwitchValue$ar$ds();
            str = commandLine.getSwitchValue$ar$ds();
            str2 = commandLine.getSwitchValue$ar$ds();
            str3 = commandLine.getSwitchValue$ar$ds();
            l = commandLine.hasSwitch$ar$ds() ? Long.valueOf(Long.parseLong(commandLine.getSwitchValue$ar$ds())) : null;
            str7 = switchValue$ar$ds;
        } else {
            l = null;
            str = null;
            str2 = null;
            str3 = null;
        }
        int i = 0;
        if (str7 != null && str != null && l != null && str2 != null && str3 != null) {
            this.hostPackageName = str7;
            this.hostPackageLabel = str;
            this.hostVersionCode = l.longValue();
            this.versionName = str3;
            this.packageName = str2;
            context.getApplicationInfo();
        } else {
            if (ContextUtils.isSdkSandboxProcess()) {
                String[] packagesForUid = packageManager.getPackagesForUid(Process.myUid() - 10000);
                if (packagesForUid.length > 0) {
                    str4 = packagesForUid[0];
                    str5 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str4, packageName, ":");
                    PackageInfo packageInfo$ar$ds = PackageUtils.getPackageInfo$ar$ds(str4);
                    this.hostPackageName = str5;
                    this.hostPackageLabel = nullToEmpty(packageManager.getApplicationLabel(packageInfo$ar$ds.applicationInfo));
                    this.hostVersionCode = packageVersionCode(packageInfo$ar$ds);
                    this.packageName = packageName;
                    this.versionName = nullToEmpty(packageInfo$ar$ds.versionName);
                    context.getApplicationInfo();
                    packageName = str4;
                }
            }
            str4 = packageName;
            str5 = str4;
            PackageInfo packageInfo$ar$ds2 = PackageUtils.getPackageInfo$ar$ds(str4);
            this.hostPackageName = str5;
            this.hostPackageLabel = nullToEmpty(packageManager.getApplicationLabel(packageInfo$ar$ds2.applicationInfo));
            this.hostVersionCode = packageVersionCode(packageInfo$ar$ds2);
            this.packageName = packageName;
            this.versionName = nullToEmpty(packageInfo$ar$ds2.versionName);
            context.getApplicationInfo();
            packageName = str4;
        }
        this.installerPackageName = nullToEmpty(packageManager.getInstallerPackageName(packageName));
        PackageInfo packageInfo$ar$ds3 = PackageUtils.getPackageInfo$ar$ds("com.google.android.gms");
        if (packageInfo$ar$ds3 != null) {
            str6 = String.valueOf(packageVersionCode(packageInfo$ar$ds3));
        } else {
            str6 = "gms versionCode not available.";
        }
        this.gmsVersionCode = str6;
        if (PackageUtils.getPackageInfo$ar$ds("projekt.substratum") != null) {
            z = true;
        } else {
            z = false;
        }
        this.customThemes = String.valueOf(z);
        this.resourcesVersion = "Not Enabled";
        this.abiString = TextUtils.join(", ", Build.SUPPORTED_ABIS);
        this.androidBuildFingerprint = Build.FINGERPRINT.substring(0, Math.min(Build.FINGERPRINT.length(), BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE));
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.isTV = z2;
        try {
            z3 = packageManager.hasSystemFeature("android.hardware.type.automotive");
        } catch (SecurityException e) {
            Log.e("cr_".concat("BuildInfo"), "Unable to query for Automotive system feature", e);
            z3 = false;
        }
        this.isAutomotive = z3;
        this.isFoldable = Build.VERSION.SDK_INT >= 30 && packageManager.hasSystemFeature("android.hardware.sensor.hinge_angle");
        if (Build.VERSION.SDK_INT >= 33 && (systemAvailableFeatures = packageManager.getSystemAvailableFeatures()) != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= systemAvailableFeatures.length) {
                    break;
                }
                FeatureInfo featureInfo = systemAvailableFeatures[i2];
                if ("android.software.vulkan.deqp.level".equals(featureInfo.name)) {
                    i = featureInfo.version;
                    break;
                }
                i2++;
            }
        }
        this.vulkanDeqpLevel = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String[] getAll() {
        /*
            Method dump skipped, instructions count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.BuildInfo.getAll():java.lang.String[]");
    }

    private static String nullToEmpty(CharSequence charSequence) {
        if (charSequence == null) {
            return "";
        }
        return charSequence.toString();
    }

    public static long packageVersionCode(PackageInfo packageInfo) {
        long longVersionCode;
        if (Build.VERSION.SDK_INT >= 28) {
            longVersionCode = packageInfo.getLongVersionCode();
            return longVersionCode;
        }
        return packageInfo.versionCode;
    }
}

package com.google.android.play.core.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhoneskyVerificationUtils {
    private static final SplitInstallModule logger$ar$class_merging$ceb098d3_0$ar$class_merging = new SplitInstallModule("PhoneskyVerificationUtils");

    public static boolean isPhoneskyInstalled(Context context) {
        try {
            if (!context.getPackageManager().getApplicationInfo("com.android.vending", 0).enabled) {
                logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("Play Store package is disabled.", new Object[0]);
            } else {
                try {
                    Signature[] signatureArr = context.getPackageManager().getPackageInfo("com.android.vending", 64).signatures;
                    if (signatureArr != null && (signatureArr.length) != 0) {
                        ArrayList arrayList = new ArrayList();
                        for (Signature signature : signatureArr) {
                            String sha256 = NativeLibraryPathListMutex.sha256(signature.toByteArray());
                            arrayList.add(sha256);
                            if ("8P1sW0EPJcslw7UzRsiXL64w-O50Ed-RBICtay1g24M".equals(sha256) || ((Build.TAGS.contains("dev-keys") || Build.TAGS.contains("test-keys")) && "GXWy8XF3vIml3_MfnmSmyuKBpT3B0dWbHRR_4cgq-gA".equals(sha256))) {
                                return true;
                            }
                        }
                        SplitInstallModule splitInstallModule = logger$ar$class_merging$ceb098d3_0$ar$class_merging;
                        StringBuilder sb = new StringBuilder();
                        Iterator it = arrayList.iterator();
                        if (it.hasNext()) {
                            while (true) {
                                sb.append((CharSequence) it.next());
                                if (!it.hasNext()) {
                                    break;
                                }
                                sb.append((CharSequence) ", ");
                            }
                        }
                        splitInstallModule.w$ar$ds(String.format("Play Store package certs are not valid. Found these sha256 certs: [%s].", sb.toString()), new Object[0]);
                    } else {
                        logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("Play Store package is not signed -- possibly self-built package. Could not verify.", new Object[0]);
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("Play Store package is not found.", new Object[0]);
                }
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            logger$ar$class_merging$ceb098d3_0$ar$class_merging.w$ar$ds("Play Store package is not found.", new Object[0]);
        }
        return false;
    }
}

package com.google.android.accessibility.talkback.labeling;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.util.concurrent.ExecutionList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DataConsistencyCheckRequest extends LabelClientRequest {
    private final HapticPatternParser$$ExternalSyntheticLambda1 callback$ar$class_merging$9a6e996c_0$ar$class_merging$ar$class_merging;
    private final Context context;

    public DataConsistencyCheckRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, Context context, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        super(runnableExecutorPair);
        this.context = context;
        this.callback$ar$class_merging$9a6e996c_0$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    private static String computePackageSignatureHash(PackageInfo packageInfo) {
        Signature[] signatureArr = packageInfo.signatures;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            for (Signature signature : signatureArr) {
                messageDigest.update(signature.toByteArray());
            }
            return StringBuilderUtils.bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            LogUtils.w("DataConsistencyCheckReq", "Unable to create SHA-1 MessageDigest", new Object[0]);
            return "";
        }
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ Object doInBackground() {
        List currentLabels = this.mClient$ar$class_merging.getCurrentLabels();
        if (currentLabels == null || currentLabels.isEmpty()) {
            return null;
        }
        PackageManager packageManager = this.context.getPackageManager();
        ArrayList arrayList = new ArrayList(currentLabels);
        ListIterator listIterator = arrayList.listIterator();
        while (listIterator.hasNext()) {
            Label label = (Label) listIterator.next();
            String str = label.mPackageName;
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
                String str2 = label.mPackageSignature;
                String computePackageSignatureHash = computePackageSignatureHash(packageInfo);
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(computePackageSignatureHash) && str2.equals(computePackageSignatureHash)) {
                    listIterator.remove();
                } else {
                    LogUtils.w("DataConsistencyCheckReq", "Consistency check removing label due to signature mismatch for package %s.", str);
                }
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtils.v("DataConsistencyCheckReq", "Consistency check removing label for unknown package %s.", str);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        List list = (List) obj;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.callback$ar$class_merging$9a6e996c_0$ar$class_merging$ar$class_merging;
        if (hapticPatternParser$$ExternalSyntheticLambda1 != null && list != null && !list.isEmpty()) {
            LogUtils.v("CustomLabelManager", "Found %d labels to remove during consistency check", Integer.valueOf(list.size()));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((CustomLabelManager) hapticPatternParser$$ExternalSyntheticLambda1.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).removeLabel((Label) it.next());
            }
        }
    }
}

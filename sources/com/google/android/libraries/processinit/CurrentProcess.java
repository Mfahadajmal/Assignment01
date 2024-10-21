package com.google.android.libraries.processinit;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.preference.Preference;
import com.google.android.libraries.surveys.internal.model.Answer;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.marvin.talkback.R;
import com.google.scone.proto.Survey$Payload;
import googledata.experiments.mobile.surveys_android.features.HatsV1M9Bugfixes;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CurrentProcess {
    public static String processName;

    public static File getFilesDirWithPreNWorkaround(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            SystemClock.sleep(100L);
            filesDir = context.getFilesDir();
            if (filesDir == null) {
                throw new IllegalStateException("getFilesDir returned null twice.");
            }
        }
        return filesDir;
    }

    public static String getTextFromInputStream(InputStream inputStream, long j, int i) {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream.skip(j);
            if (i <= 0) {
                i = Preference.DEFAULT_ORDER;
            }
            while (i > 0) {
                int read = inputStream.read(bArr, 0, Math.min(i, 1024));
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                i -= read;
            }
            inputStream.close();
            try {
                return byteArrayOutputStream.toString("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unsupported encoding UTF8. This should always be supported.", e);
            }
        } catch (IOException e2) {
            throw new RuntimeException("Failed to read license or metadata text.", e2);
        }
    }

    public static String getTextFromResource$ar$ds(Context context, String str, long j, int i) {
        Resources resources = context.getApplicationContext().getResources();
        return getTextFromInputStream(resources.openRawResource(resources.getIdentifier(str, "raw", resources.getResourcePackageName(R.id.dummy_placeholder))), j, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0139 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean shouldContinueSurveyDueToScreenIn(int r8, com.google.scone.proto.Survey$Payload r9, com.google.android.libraries.surveys.internal.model.Answer r10) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.processinit.CurrentProcess.shouldContinueSurveyDueToScreenIn(int, com.google.scone.proto.Survey$Payload, com.google.android.libraries.surveys.internal.model.Answer):boolean");
    }

    public static boolean shouldIgnoreScreenInFollowUpQuestions$ar$ds(boolean z, Survey$Payload survey$Payload, Answer answer) {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!FlagsUtil.isBugfixEnabled(HatsV1M9Bugfixes.INSTANCE.get().fixScreenInFlow(FlagsUtil.phenotypeContext)) || !z || shouldContinueSurveyDueToScreenIn(0, survey$Payload, answer)) {
            return false;
        }
        return true;
    }
}

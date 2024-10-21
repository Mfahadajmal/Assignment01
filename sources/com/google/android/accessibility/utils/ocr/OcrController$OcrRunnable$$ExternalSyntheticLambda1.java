package com.google.android.accessibility.utils.ocr;

import com.google.android.accessibility.talkback.dynamicfeature.Downloader;
import com.google.android.accessibility.talkback.dynamicfeature.SplitApkDownloader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class OcrController$OcrRunnable$$ExternalSyntheticLambda1 implements OnFailureListener {
    public final /* synthetic */ Object OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$0;
    public final /* synthetic */ Object OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ OcrController$OcrRunnable$$ExternalSyntheticLambda1(SplitApkDownloader splitApkDownloader, String[] strArr, int i) {
        this.switching_field = i;
        this.OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$1 = splitApkDownloader;
        this.OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$0 = strArr;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        int i;
        if (this.switching_field != 0) {
            LogUtils.v("FeatureDownloader", "Failed to download. error=%s.", exc);
            ImmutableList of = ImmutableList.of((Object) ((String[]) this.OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$0)[0]);
            boolean z = exc instanceof SplitInstallException;
            Downloader.DownloadStatus downloadStatus = Downloader.DownloadStatus.FAILED;
            if (z) {
                i = SplitApkDownloader.getErrorCode$ar$edu(((SplitInstallException) exc).getErrorCode());
            } else {
                i = 2;
            }
            int i2 = i;
            Object obj = this.OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$1;
            Downloader.DownloadState downloadState = new Downloader.DownloadState(of, downloadStatus, i2, 0L, 0L);
            Iterator it = ((SplitApkDownloader) obj).registeredListeners.keySet().iterator();
            while (it.hasNext()) {
                ((Downloader.DownloadStateUpdateListener) it.next()).onStateUpdate(downloadState);
            }
            return;
        }
        LogUtils.w("OcrController", "Fail to recognize text. errMsg=".concat(String.valueOf(exc.getMessage())), new Object[0]);
        int i3 = ImmutableList.ImmutableList$ar$NoOp;
        ((ConcurrentHashMap) this.OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$0).put(this.OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$1, RegularImmutableList.EMPTY);
    }

    public /* synthetic */ OcrController$OcrRunnable$$ExternalSyntheticLambda1(ConcurrentHashMap concurrentHashMap, OcrInfo ocrInfo, int i) {
        this.switching_field = i;
        this.OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$0 = concurrentHashMap;
        this.OcrController$OcrRunnable$$ExternalSyntheticLambda1$ar$f$1 = ocrInfo;
    }
}

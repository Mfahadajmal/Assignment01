package com.google.android.accessibility.talkback.dynamicfeature;

import android.content.Context;
import com.google.android.accessibility.talkback.dynamicfeature.Downloader;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.accessibility.utils.ocr.OcrController$OcrRunnable$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils$$ExternalSyntheticLambda1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitApkDownloader implements Downloader {
    private static SplitApkDownloader instance;
    private final SplitInstallManager splitInstallManager;
    public final Map registeredListeners = new HashMap();
    public final Map installStatus = new HashMap();
    private boolean isInstallStatusUpdated = false;

    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, dagger.internal.Provider] */
    private SplitApkDownloader(Context context) {
        this.splitInstallManager = (SplitInstallManager) NativeLibraryPathListMutex.getComponent$ar$class_merging$ar$class_merging(context).SystemInfo$ar$appId.get();
    }

    public static int getErrorCode$ar$edu(int i) {
        if (i != -10) {
            if (i != -6) {
                if (i != 0) {
                    return 2;
                }
                return 1;
            }
            return 4;
        }
        return 3;
    }

    public static SplitApkDownloader getInstance(Context context) {
        if (instance == null) {
            instance = new SplitApkDownloader(context);
        }
        return instance;
    }

    public static Downloader.DownloadStatus getStatus(int i) {
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i != 6) {
                            return Downloader.DownloadStatus.UNKNOWN;
                        }
                        return Downloader.DownloadStatus.FAILED;
                    }
                    return Downloader.DownloadStatus.INSTALLED;
                }
                return Downloader.DownloadStatus.INSTALLING;
            }
            return Downloader.DownloadStatus.DOWNLOADED;
        }
        return Downloader.DownloadStatus.DOWNLOADING;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final void download(String... strArr) {
        RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = new RemoteModelManager.RemoteModelManagerRegistration();
        remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass.add(strArr[0]);
        this.splitInstallManager.startInstall(new SplitInstallRequest(remoteModelManagerRegistration)).addOnSuccessListener(new SplitApkDownloader$$ExternalSyntheticLambda3(strArr, 0)).addOnFailureListener$ar$ds(new OcrController$OcrRunnable$$ExternalSyntheticLambda1(this, strArr, 1));
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final String getModuleName(ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType) {
        int ordinal = imageCaptionUtils$CaptionType.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                return "";
            }
            return "garcon_assets_module";
        }
        return "screenunderstanding_feature_module";
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final void install(Context context) {
        SplitCompat.installActivity(context);
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final boolean isDownloading(String str) {
        Downloader.DownloadStatus downloadStatus = (Downloader.DownloadStatus) this.installStatus.get(str);
        if (downloadStatus != Downloader.DownloadStatus.DOWNLOADING && downloadStatus != Downloader.DownloadStatus.DOWNLOADED && downloadStatus != Downloader.DownloadStatus.INSTALLING) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final boolean isInstalled(String str) {
        return this.splitInstallManager.getInstalledModules().contains(str);
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final void registerListener(Downloader.DownloadStateUpdateListener downloadStateUpdateListener) {
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = new HapticPatternParser$$ExternalSyntheticLambda1(downloadStateUpdateListener);
        this.registeredListeners.put(downloadStateUpdateListener, hapticPatternParser$$ExternalSyntheticLambda1);
        this.splitInstallManager.registerListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(hapticPatternParser$$ExternalSyntheticLambda1);
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final void uninstall(String... strArr) {
        this.splitInstallManager.deferredUninstall(Arrays.asList(strArr));
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final void unregisterListener(Downloader.DownloadStateUpdateListener downloadStateUpdateListener) {
        if (this.registeredListeners.containsKey(downloadStateUpdateListener)) {
            HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = (HapticPatternParser$$ExternalSyntheticLambda1) this.registeredListeners.remove(downloadStateUpdateListener);
            if (hapticPatternParser$$ExternalSyntheticLambda1 != null) {
                this.splitInstallManager.unregisterListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(hapticPatternParser$$ExternalSyntheticLambda1);
            }
            if (this.registeredListeners.isEmpty()) {
                this.isInstallStatusUpdated = false;
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final synchronized void updateAllDownloadStatus() {
        if (this.isInstallStatusUpdated) {
            return;
        }
        this.isInstallStatusUpdated = true;
        this.splitInstallManager.getSessionStates().addOnSuccessListener(new SplitApkDownloader$$ExternalSyntheticLambda3(this, 1)).addOnFailureListener$ar$ds(new OptionalModuleUtils$$ExternalSyntheticLambda1(1));
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader
    public final void updateDownloadStatus(String str, Downloader.DownloadStatus downloadStatus) {
        this.installStatus.put(str, downloadStatus);
    }
}

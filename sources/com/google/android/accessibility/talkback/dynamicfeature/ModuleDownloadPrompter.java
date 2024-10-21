package com.google.android.accessibility.talkback.dynamicfeature;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.dynamicfeature.Downloader;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$DownloadDialogResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$ImageCaptionPreferenceKeys;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$UninstallDialogResources;
import com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ModuleDownloadPrompter implements Downloader.DownloadStateUpdateListener {
    public final Context context;
    public final DownloadDialog downloadDialog;
    public DownloadStateListener downloadStateListener;
    public final Downloader downloader;
    public final String moduleName;
    public final ImageCaptionConstants$ImageCaptionPreferenceKeys preferenceKeys;
    public final SharedPreferences prefs;
    public AccessibilityNodeInfoCompat queuedNode;
    public final UninstallDialog uninstallDialog;
    public AutomaticDescriptionsFragment.AutomaticDescriptionUninstallStateListener uninstallStateListener$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DownloadStateListener {
        void onAccepted();

        void onDialogDismissed(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

        void onFailed(int i);

        void onInstalled();

        void onRejected();
    }

    public ModuleDownloadPrompter(Context context, Downloader downloader, ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType, ImageCaptionConstants$ImageCaptionPreferenceKeys imageCaptionConstants$ImageCaptionPreferenceKeys, ImageCaptionConstants$DownloadDialogResources imageCaptionConstants$DownloadDialogResources, ImageCaptionConstants$UninstallDialogResources imageCaptionConstants$UninstallDialogResources) {
        boolean z;
        this.context = context;
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.prefs = sharedPreferences;
        this.downloader = downloader;
        this.moduleName = downloader.getModuleName(imageCaptionUtils$CaptionType);
        this.preferenceKeys = imageCaptionConstants$ImageCaptionPreferenceKeys;
        this.downloadDialog = new DownloadDialog(this, context, imageCaptionConstants$DownloadDialogResources);
        this.uninstallDialog = new UninstallDialog(this, context, imageCaptionConstants$UninstallDialogResources.uninstallTitleRes);
        downloader.registerListener(this);
        if (isModuleAvailable() && !isUninstalled()) {
            z = sharedPreferences.getBoolean(context.getResources().getString(imageCaptionConstants$ImageCaptionPreferenceKeys.installedKey), false);
            if (!z) {
                sharedPreferences.edit().putBoolean(context.getString(imageCaptionConstants$ImageCaptionPreferenceKeys.installedKey), true).putBoolean(context.getString(imageCaptionConstants$ImageCaptionPreferenceKeys.switchKey), true).apply();
            }
        }
    }

    protected abstract boolean initModule();

    public final boolean isModuleAvailable() {
        return this.downloader.isInstalled(this.moduleName);
    }

    public final boolean isModuleDownloading() {
        return this.downloader.isDownloading(this.moduleName);
    }

    public final boolean isUninstalled() {
        return this.prefs.getBoolean(this.context.getString(this.preferenceKeys.uninstalledKey), false);
    }

    public final boolean needDownloadDialog$ar$edu(int i) {
        if ((isModuleAvailable() || isModuleDownloading()) && !isUninstalled()) {
            return false;
        }
        if (i != 1) {
            return true;
        }
        if (this.prefs.getBoolean(this.context.getString(this.preferenceKeys.doNotShowKey), false) || this.prefs.getInt(this.context.getString(this.preferenceKeys.downloadShownTimesKey), 0) >= 3) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.Downloader.DownloadStateUpdateListener
    public final void onStateUpdate(Downloader.DownloadState downloadState) {
        int i;
        LogUtils.v("ModuleDownloadPrompter", "%s.onStateUpdate=%s", downloadState.moduleNames, downloadState);
        if (downloadState.moduleNames.isEmpty()) {
            LogUtils.e("ModuleDownloadPrompter", "No modules!", new Object[0]);
        } else if (!this.moduleName.equals(downloadState.moduleNames.get(0))) {
            return;
        }
        LogUtils.v("ModuleDownloadPrompter", "%s.onStateUpdate", this.moduleName);
        this.downloader.updateDownloadStatus(this.moduleName, downloadState.downloadStatus);
        int ordinal = downloadState.downloadStatus.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            LogUtils.v("ModuleDownloadPrompter", "Installed", new Object[0]);
                            if (!initModule()) {
                                LogUtils.w("ModuleDownloadPrompter", "Fail to initialize module, ".concat(this.moduleName), new Object[0]);
                            }
                            this.prefs.edit().putBoolean(this.context.getString(this.preferenceKeys.installedKey), true).putBoolean(this.context.getString(this.preferenceKeys.switchKey), true).apply();
                            DownloadStateListener downloadStateListener = this.downloadStateListener;
                            if (downloadStateListener != null) {
                                downloadStateListener.onInstalled();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    LogUtils.v("ModuleDownloadPrompter", "Installing...", new Object[0]);
                    return;
                }
                LogUtils.v("ModuleDownloadPrompter", "Downloaded", new Object[0]);
                return;
            }
            long j = downloadState.totalBytesToDownload;
            long j2 = 0;
            if (j != 0) {
                j2 = (downloadState.bytesDownloaded * 100) / j;
            }
            LogUtils.v("ModuleDownloadPrompter", "Downloading... %d", Long.valueOf(j2));
            return;
        }
        DownloadStateListener downloadStateListener2 = this.downloadStateListener;
        if (downloadStateListener2 != null) {
            int i2 = downloadState.downloadErrorCode$ar$edu - 1;
            if (i2 != 2) {
                if (i2 != 3) {
                    i = 100;
                } else {
                    i = 102;
                }
            } else {
                i = ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu;
            }
            downloadStateListener2.onFailed(i);
        }
    }

    public final void showDownloadDialog$ar$edu(int i) {
        this.downloader.install(this.context);
        this.downloadDialog.showDialog$ar$edu(i);
    }

    public final void shutdown() {
        this.downloader.unregisterListener(this);
    }
}

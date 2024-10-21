package com.google.android.accessibility.talkback.dynamicfeature;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Downloader {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DownloadState {
        public final long bytesDownloaded;
        public final int downloadErrorCode$ar$edu;
        public final DownloadStatus downloadStatus;
        public final ImmutableList moduleNames;
        public final long totalBytesToDownload;

        public DownloadState() {
        }

        public final long bytesDownloaded() {
            return this.bytesDownloaded;
        }

        public final int downloadErrorCode$ar$edu$760fc0a4_0() {
            return this.downloadErrorCode$ar$edu;
        }

        public final DownloadStatus downloadStatus() {
            return this.downloadStatus;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof DownloadState) {
                DownloadState downloadState = (DownloadState) obj;
                if (ContextDataProvider.equalsImpl(this.moduleNames, downloadState.moduleNames()) && this.downloadStatus.equals(downloadState.downloadStatus()) && this.downloadErrorCode$ar$edu == downloadState.downloadErrorCode$ar$edu$760fc0a4_0() && this.bytesDownloaded == downloadState.bytesDownloaded() && this.totalBytesToDownload == downloadState.totalBytesToDownload()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode = ((((this.moduleNames.hashCode() ^ 1000003) * 1000003) ^ this.downloadStatus.hashCode()) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.downloadErrorCode$ar$edu);
            long j = this.totalBytesToDownload;
            long j2 = j ^ (j >>> 32);
            long j3 = this.bytesDownloaded;
            return (((hashCode * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003) ^ ((int) j2);
        }

        public final ImmutableList moduleNames() {
            return this.moduleNames;
        }

        public final String toString() {
            String str;
            int i = this.downloadErrorCode$ar$edu;
            DownloadStatus downloadStatus = this.downloadStatus;
            String obj = this.moduleNames.toString();
            String obj2 = downloadStatus.toString();
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        str = "NETWORK_ERROR";
                    } else {
                        str = "INSUFFICIENT_STORAGE";
                    }
                } else {
                    str = "UNKNOWN_ERROR";
                }
            } else {
                str = "NO_ERROR";
            }
            return "DownloadState{moduleNames=" + obj + ", downloadStatus=" + obj2 + ", downloadErrorCode=" + str + ", bytesDownloaded=" + this.bytesDownloaded + ", totalBytesToDownload=" + this.totalBytesToDownload + "}";
        }

        public final long totalBytesToDownload() {
            return this.totalBytesToDownload;
        }

        public DownloadState(ImmutableList immutableList, DownloadStatus downloadStatus, int i, long j, long j2) {
            this();
            if (immutableList == null) {
                throw new NullPointerException("Null moduleNames");
            }
            this.moduleNames = immutableList;
            if (downloadStatus != null) {
                this.downloadStatus = downloadStatus;
                this.downloadErrorCode$ar$edu = i;
                this.bytesDownloaded = j;
                this.totalBytesToDownload = j2;
                return;
            }
            throw new NullPointerException("Null downloadStatus");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DownloadStateUpdateListener {
        void onStateUpdate(DownloadState downloadState);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum DownloadStatus {
        UNKNOWN,
        FAILED,
        DOWNLOADING,
        DOWNLOADED,
        INSTALLING,
        INSTALLED
    }

    void download(String... strArr);

    String getModuleName(ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType);

    void install(Context context);

    boolean isDownloading(String str);

    boolean isInstalled(String str);

    void registerListener(DownloadStateUpdateListener downloadStateUpdateListener);

    void uninstall(String... strArr);

    void unregisterListener(DownloadStateUpdateListener downloadStateUpdateListener);

    void updateAllDownloadStatus();

    void updateDownloadStatus(String str, DownloadStatus downloadStatus);
}

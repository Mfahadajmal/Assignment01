package com.google.android.play.core.splitinstall;

import android.app.PendingIntent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallSessionState {
    private final long bytesDownloaded;
    private final int errorCode;
    private final List languagesNullable;
    private final List moduleNamesNullable;
    private final PendingIntent resolutionIntent;
    private final int sessionId;
    public final List splitFileIntents;
    public final int status;
    private final long totalBytesToDownload;

    public SplitInstallSessionState() {
    }

    public static SplitInstallSessionState fromBundle(Bundle bundle) {
        return new SplitInstallSessionState(bundle.getInt("session_id"), bundle.getInt("status"), bundle.getInt("error_code"), bundle.getLong("bytes_downloaded"), bundle.getLong("total_bytes_to_download"), bundle.getStringArrayList("module_names"), bundle.getStringArrayList("languages"), (PendingIntent) bundle.getParcelable("user_confirmation_intent"), bundle.getParcelableArrayList("split_file_intents"));
    }

    public final long bytesDownloaded() {
        return this.bytesDownloaded;
    }

    public final boolean equals(Object obj) {
        List list;
        List list2;
        PendingIntent pendingIntent;
        List list3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof SplitInstallSessionState) {
            SplitInstallSessionState splitInstallSessionState = (SplitInstallSessionState) obj;
            if (this.sessionId == splitInstallSessionState.sessionId() && this.status == splitInstallSessionState.status() && this.errorCode == splitInstallSessionState.errorCode() && this.bytesDownloaded == splitInstallSessionState.bytesDownloaded() && this.totalBytesToDownload == splitInstallSessionState.totalBytesToDownload() && ((list = this.moduleNamesNullable) != null ? list.equals(splitInstallSessionState.moduleNamesNullable()) : splitInstallSessionState.moduleNamesNullable() == null) && ((list2 = this.languagesNullable) != null ? list2.equals(splitInstallSessionState.languagesNullable()) : splitInstallSessionState.languagesNullable() == null) && ((pendingIntent = this.resolutionIntent) != null ? pendingIntent.equals(splitInstallSessionState.resolutionIntent()) : splitInstallSessionState.resolutionIntent() == null) && ((list3 = this.splitFileIntents) != null ? list3.equals(splitInstallSessionState.splitFileIntents()) : splitInstallSessionState.splitFileIntents() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int errorCode() {
        return this.errorCode;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        int hashCode3;
        List list = this.moduleNamesNullable;
        int i = 0;
        if (list == null) {
            hashCode = 0;
        } else {
            hashCode = list.hashCode();
        }
        int i2 = this.sessionId;
        int i3 = this.status;
        long j = this.bytesDownloaded;
        int i4 = this.errorCode;
        long j2 = this.totalBytesToDownload;
        List list2 = this.languagesNullable;
        if (list2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = list2.hashCode();
        }
        int i5 = hashCode ^ ((((((((((i2 ^ 1000003) * 1000003) ^ i3) * 1000003) ^ i4) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003);
        PendingIntent pendingIntent = this.resolutionIntent;
        if (pendingIntent == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = pendingIntent.hashCode();
        }
        int i6 = ((((i5 * 1000003) ^ hashCode2) * 1000003) ^ hashCode3) * 1000003;
        List list3 = this.splitFileIntents;
        if (list3 != null) {
            i = list3.hashCode();
        }
        return i6 ^ i;
    }

    public final List languages() {
        if (languagesNullable() != null) {
            return new ArrayList(languagesNullable());
        }
        return new ArrayList();
    }

    public final List languagesNullable() {
        return this.languagesNullable;
    }

    public final List moduleNames() {
        if (moduleNamesNullable() != null) {
            return new ArrayList(moduleNamesNullable());
        }
        return new ArrayList();
    }

    public final List moduleNamesNullable() {
        return this.moduleNamesNullable;
    }

    @Deprecated
    public final PendingIntent resolutionIntent() {
        return this.resolutionIntent;
    }

    public final int sessionId() {
        return this.sessionId;
    }

    public final List splitFileIntents() {
        return this.splitFileIntents;
    }

    public final int status() {
        return this.status;
    }

    public final String toString() {
        List list = this.splitFileIntents;
        PendingIntent pendingIntent = this.resolutionIntent;
        List list2 = this.languagesNullable;
        return "SplitInstallSessionState{sessionId=" + this.sessionId + ", status=" + this.status + ", errorCode=" + this.errorCode + ", bytesDownloaded=" + this.bytesDownloaded + ", totalBytesToDownload=" + this.totalBytesToDownload + ", moduleNamesNullable=" + String.valueOf(this.moduleNamesNullable) + ", languagesNullable=" + String.valueOf(list2) + ", resolutionIntent=" + String.valueOf(pendingIntent) + ", splitFileIntents=" + String.valueOf(list) + "}";
    }

    public final long totalBytesToDownload() {
        return this.totalBytesToDownload;
    }

    public SplitInstallSessionState(int i, int i2, int i3, long j, long j2, List list, List list2, PendingIntent pendingIntent, List list3) {
        this();
        this.sessionId = i;
        this.status = i2;
        this.errorCode = i3;
        this.bytesDownloaded = j;
        this.totalBytesToDownload = j2;
        this.moduleNamesNullable = list;
        this.languagesNullable = list2;
        this.resolutionIntent = pendingIntent;
        this.splitFileIntents = list3;
    }
}

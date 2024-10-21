package com.google.android.accessibility.talkback.dynamicfeature;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$DownloadDialogResources;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DownloadDialog extends BaseDialog {
    public CheckBox checkBox;
    private int requester$ar$edu;
    private final ImageCaptionConstants$DownloadDialogResources resources;
    final /* synthetic */ ModuleDownloadPrompter this$0;

    public DownloadDialog(Context context, ImageCaptionConstants$DownloadDialogResources imageCaptionConstants$DownloadDialogResources) {
        super(context, imageCaptionConstants$DownloadDialogResources.downloadTitleRes, null);
        this.resources = imageCaptionConstants$DownloadDialogResources;
        setPositiveButtonStringRes(R.string.confirm_download_positive_button_text);
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final View getCustomizedView() {
        ScrollView scrollView = (ScrollView) LayoutInflater.from(this.context).inflate(R.layout.confirm_download_dialog, (ViewGroup) null);
        TextView textView = (TextView) scrollView.findViewById(R.id.confirm_download_dialog_subtitle);
        textView.setVisibility(0);
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        Network activeNetwork = connectivityManager.getActiveNetwork();
        int i = R.string.confirm_download_subtitle_via_wifi;
        if (activeNetwork == null) {
            LogUtils.v("DownloadDialog", "No active network.", new Object[0]);
        } else {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                LogUtils.v("DownloadDialog", "Can't get the capability of the active network.", new Object[0]);
            } else if (networkCapabilities.hasTransport(0)) {
                i = R.string.confirm_download_subtitle_via_mobile_data;
            }
        }
        textView.setText(this.context.getString(i, Integer.valueOf(this.resources.moduleSizeInMb)));
        TextView textView2 = (TextView) scrollView.findViewById(R.id.confirm_download_dialog_message);
        this.checkBox = (CheckBox) scrollView.findViewById(R.id.confirm_download_dialog_checkbox);
        if (this.requester$ar$edu == 0) {
            this.requester$ar$edu = 2;
        }
        int i2 = this.requester$ar$edu;
        int i3 = i2 - 1;
        if (i2 != 0) {
            if (i3 != 0) {
                if (i3 != 2) {
                    textView2.setText(this.resources.downloadMessageRes);
                } else {
                    textView2.setVisibility(8);
                }
            } else {
                textView2.setText(this.resources.downloadMessageRes);
                this.checkBox.setVisibility(0);
                this.checkBox.setText(R.string.confirm_download_checkbox_text);
            }
            return scrollView;
        }
        throw null;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final String getMessageString() {
        return null;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogClick(int i) {
        ModuleDownloadPrompter moduleDownloadPrompter = this.this$0;
        if (i != -2) {
            if (i == -1) {
                LogUtils.v("ModuleDownloadPrompter", "Starts the download.", new Object[0]);
                moduleDownloadPrompter.queuedNode = null;
                ModuleDownloadPrompter.DownloadStateListener downloadStateListener = moduleDownloadPrompter.downloadStateListener;
                if (downloadStateListener != null) {
                    downloadStateListener.onAccepted();
                }
                moduleDownloadPrompter.downloader.download(moduleDownloadPrompter.moduleName);
                return;
            }
            return;
        }
        LogUtils.v("ModuleDownloadPrompter", "Rejects the download.", new Object[0]);
        ModuleDownloadPrompter.DownloadStateListener downloadStateListener2 = moduleDownloadPrompter.downloadStateListener;
        if (downloadStateListener2 != null) {
            downloadStateListener2.onRejected();
        }
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogDismiss() {
        ModuleDownloadPrompter moduleDownloadPrompter = this.this$0;
        ModuleDownloadPrompter.DownloadStateListener downloadStateListener = moduleDownloadPrompter.downloadStateListener;
        if (downloadStateListener != null) {
            downloadStateListener.onDialogDismissed(moduleDownloadPrompter.queuedNode);
        }
        CheckBox checkBox = moduleDownloadPrompter.downloadDialog.checkBox;
        if (checkBox != null && checkBox.isChecked()) {
            SpannableUtils$IdentifierSpan.putBooleanPref(moduleDownloadPrompter.prefs, moduleDownloadPrompter.context.getResources(), moduleDownloadPrompter.preferenceKeys.doNotShowKey, true);
        }
    }

    public final void showDialog$ar$edu(int i) {
        this.requester$ar$edu = i;
        super.showDialog();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DownloadDialog(ModuleDownloadPrompter moduleDownloadPrompter, Context context, ImageCaptionConstants$DownloadDialogResources imageCaptionConstants$DownloadDialogResources) {
        this(context, imageCaptionConstants$DownloadDialogResources);
        this.this$0 = moduleDownloadPrompter;
    }
}

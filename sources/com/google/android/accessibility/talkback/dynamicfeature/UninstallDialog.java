package com.google.android.accessibility.talkback.dynamicfeature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.accessibility.talkback.preference.base.AutomaticDescriptionsFragment;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UninstallDialog extends BaseDialog {
    final /* synthetic */ ModuleDownloadPrompter this$0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UninstallDialog(ModuleDownloadPrompter moduleDownloadPrompter, Context context, int i) {
        this(context, i);
        this.this$0 = moduleDownloadPrompter;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final View getCustomizedView() {
        ScrollView scrollView = (ScrollView) LayoutInflater.from(this.context).inflate(R.layout.confirm_download_dialog, (ViewGroup) null);
        ((TextView) scrollView.findViewById(R.id.confirm_download_dialog_subtitle)).setVisibility(8);
        ((TextView) scrollView.findViewById(R.id.confirm_download_dialog_message)).setText(R.string.delete_dialog_message);
        return scrollView;
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
                LogUtils.v("ModuleDownloadPrompter", "Starts to uninstall.", new Object[0]);
                AutomaticDescriptionsFragment.AutomaticDescriptionUninstallStateListener automaticDescriptionUninstallStateListener = moduleDownloadPrompter.uninstallStateListener$ar$class_merging;
                if (automaticDescriptionUninstallStateListener != null) {
                    automaticDescriptionUninstallStateListener.onAccepted();
                }
                moduleDownloadPrompter.downloader.uninstall(moduleDownloadPrompter.moduleName);
                return;
            }
            return;
        }
        LogUtils.v("ModuleDownloadPrompter", "Rejects the uninstallation.", new Object[0]);
        AutomaticDescriptionsFragment.AutomaticDescriptionUninstallStateListener automaticDescriptionUninstallStateListener2 = moduleDownloadPrompter.uninstallStateListener$ar$class_merging;
        if (automaticDescriptionUninstallStateListener2 != null) {
            automaticDescriptionUninstallStateListener2.onRejected();
        }
    }

    public UninstallDialog(Context context, int i) {
        super(context, i, null);
        setPositiveButtonStringRes(R.string.delete_dialog_positive_button_text);
        setNegativeButtonStringRes(R.string.delete_dialog_negative_button_text);
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public final void handleDialogDismiss() {
    }
}

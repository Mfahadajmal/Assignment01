package com.google.android.accessibility.talkback.dialog;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.DeviceConfigurationMonitor;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.RingerModeAndScreenMonitor;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda4;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseDialog {
    private static final int RESOURCE_ID_UNKNOWN = -1;
    private static final String TAG = "BaseDialog";
    public final Context context;
    private A11yAlertDialogWrapper dialog;
    private String dialogTitle;
    private final int dialogTitleResId;
    private Pipeline.FeedbackReturner pipeline;
    private boolean isSoftInputMode = false;
    private boolean needToRestoreFocus = false;
    private boolean includeNegativeButton = true;
    private int positiveButtonStringRes = R.string.ok;
    private int negativeButtonStringRes = R.string.cancel;
    private int neutralButtonStringRes = -1;

    public BaseDialog(Context context, int i, Pipeline.FeedbackReturner feedbackReturner) {
        this.context = context;
        this.dialogTitleResId = i;
        this.pipeline = feedbackReturner;
    }

    private void clickDialogInternal(int i) {
        Pipeline.FeedbackReturner feedbackReturner;
        handleDialogClick(i);
        if ((i == -1 || i == -3 || i == -2) && (this.context instanceof TalkBackService) && this.needToRestoreFocus && (feedbackReturner = this.pipeline) != null) {
            Logger logger = Performance.DEFAULT_LOGGER;
            feedbackReturner.returnFeedback$ar$ds(null, Feedback.focus(Feedback.Focus.Action.RESTORE_ON_NEXT_WINDOW));
        }
    }

    private void dismissDialogInternal() {
        handleDialogDismiss();
        unregisterServiceDialog();
        this.dialog = null;
    }

    private void registerServiceDialog(boolean z) {
        Context context = this.context;
        if (context instanceof TalkBackService) {
            TalkBackService talkBackService = (TalkBackService) context;
            A11yAlertDialogWrapper a11yAlertDialogWrapper = this.dialog;
            RingerModeAndScreenMonitor ringerModeAndScreenMonitor = talkBackService.ringerModeAndScreenMonitor;
            if (ringerModeAndScreenMonitor != null) {
                ringerModeAndScreenMonitor.openDialogs.add(a11yAlertDialogWrapper);
            }
            DeviceConfigurationMonitor deviceConfigurationMonitor = talkBackService.deviceConfigurationMonitor;
            if (deviceConfigurationMonitor != null && z && (a11yAlertDialogWrapper instanceof A11yAlertDialogWrapper)) {
                deviceConfigurationMonitor.dialog = a11yAlertDialogWrapper;
            }
        }
    }

    private void unregisterServiceDialog() {
        Context context = this.context;
        if (context instanceof TalkBackService) {
            TalkBackService talkBackService = (TalkBackService) context;
            A11yAlertDialogWrapper a11yAlertDialogWrapper = this.dialog;
            RingerModeAndScreenMonitor ringerModeAndScreenMonitor = talkBackService.ringerModeAndScreenMonitor;
            if (ringerModeAndScreenMonitor != null) {
                ringerModeAndScreenMonitor.openDialogs.remove(a11yAlertDialogWrapper);
            }
            DeviceConfigurationMonitor deviceConfigurationMonitor = talkBackService.deviceConfigurationMonitor;
            if (deviceConfigurationMonitor != null) {
                deviceConfigurationMonitor.dialog = null;
            }
        }
    }

    public void cancelDialog() {
        A11yAlertDialogWrapper a11yAlertDialogWrapper = this.dialog;
        if (a11yAlertDialogWrapper != null && a11yAlertDialogWrapper.isShowing()) {
            this.dialog.cancel();
        }
    }

    public void dismissDialog() {
        A11yAlertDialogWrapper a11yAlertDialogWrapper = this.dialog;
        if (a11yAlertDialogWrapper != null) {
            a11yAlertDialogWrapper.dismiss();
        }
    }

    public abstract View getCustomizedView();

    public abstract String getMessageString();

    public abstract void handleDialogClick(int i);

    public abstract void handleDialogDismiss();

    /* renamed from: lambda$showDialog$0$com-google-android-accessibility-talkback-dialog-BaseDialog, reason: not valid java name */
    public /* synthetic */ void m98x5df1c3d3(DialogInterface dialogInterface, int i) {
        clickDialogInternal(i);
    }

    /* renamed from: lambda$showDialog$1$com-google-android-accessibility-talkback-dialog-BaseDialog, reason: not valid java name */
    public /* synthetic */ void m99x18676454(DialogInterface dialogInterface) {
        dismissDialogInternal();
    }

    public void setButtonEnabled(int i, boolean z) {
        A11yAlertDialogWrapper a11yAlertDialogWrapper = this.dialog;
        if (a11yAlertDialogWrapper != null) {
            if (i != -1 && i != -2) {
                if (i == -3) {
                    i = -3;
                } else {
                    return;
                }
            }
            a11yAlertDialogWrapper.getButton(i).setEnabled(z);
        }
    }

    public BaseDialog setIncludeNegativeButton(boolean z) {
        this.includeNegativeButton = z;
        return this;
    }

    public void setNegativeButtonStringRes(int i) {
        this.negativeButtonStringRes = i;
    }

    public void setNeutralButtonStringRes(int i) {
        this.neutralButtonStringRes = i;
    }

    public void setPipeline(Pipeline.FeedbackReturner feedbackReturner) {
        this.pipeline = feedbackReturner;
    }

    public void setPositiveButtonStringRes(int i) {
        this.positiveButtonStringRes = i;
    }

    public void setRestoreFocus(boolean z) {
        this.needToRestoreFocus = z;
    }

    public void setSoftInputMode(boolean z) {
        this.isSoftInputMode = z;
    }

    public void setTitle(String str) {
        this.dialogTitle = str;
    }

    public A11yAlertDialogWrapper showDialog() {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        A11yAlertDialogWrapper a11yAlertDialogWrapper = this.dialog;
        if (a11yAlertDialogWrapper != null && a11yAlertDialogWrapper.isShowing()) {
            return this.dialog;
        }
        BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0 brailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0 = new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 20);
        ListMenuManager$$ExternalSyntheticLambda4 listMenuManager$$ExternalSyntheticLambda4 = new ListMenuManager$$ExternalSyntheticLambda4(this, 2);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(new ContextThemeWrapper(this.context, com.google.android.marvin.talkback.R.style.A11yAlertDialogCustomViewTheme), 2);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(this.positiveButtonStringRes, brailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setOnDismissListener$ar$class_merging$ar$ds(listMenuManager$$ExternalSyntheticLambda4);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setCancelable$ar$class_merging$ar$ds();
        if (!TextUtils.isEmpty(this.dialogTitle)) {
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$51f49cd0_0$ar$ds(this.dialogTitle);
        } else {
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(this.dialogTitleResId);
        }
        if (this.includeNegativeButton) {
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(this.negativeButtonStringRes, brailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0);
        }
        int i = this.neutralButtonStringRes;
        if (i != -1) {
            ((AlertDialog.Builder) createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).setNeutralButton$ar$ds(i, brailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0);
        }
        String messageString = getMessageString();
        if (!TextUtils.isEmpty(messageString)) {
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$51f49cd0_0$ar$ds(messageString);
        }
        View customizedView = getCustomizedView();
        if (customizedView != null) {
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setView$ar$class_merging$ar$ds(customizedView);
            if (FormFactorUtils.getInstance().isAndroidWear) {
                customizedView.requestFocus();
            }
        }
        A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
        this.dialog = create;
        if (this.isSoftInputMode) {
            create.getWindow().setSoftInputMode(4);
        }
        Context context = this.context;
        if (context instanceof TalkBackService) {
            SpannableUtils$IdentifierSpan.setWindowTypeToDialog(this.dialog.getWindow());
        } else {
            LogUtils.v(TAG, "Create BaseDialog from context not instance of TalkBackService, class:".concat(String.valueOf(String.valueOf(context.getClass()))), new Object[0]);
        }
        this.dialog.show();
        registerServiceDialog(this.isSoftInputMode);
        return this.dialog;
    }
}

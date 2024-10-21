package com.google.android.accessibility.talkback.labeling;

import android.content.Context;
import android.widget.EditText;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirectLabelFetchRequest extends LabelClientRequest {
    private final long labelId;
    private final LabelDialogManager$$ExternalSyntheticLambda0 onLabelFetchedListener$ar$class_merging;

    public DirectLabelFetchRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, long j, LabelDialogManager$$ExternalSyntheticLambda0 labelDialogManager$$ExternalSyntheticLambda0) {
        super(runnableExecutorPair);
        this.labelId = j;
        this.onLabelFetchedListener$ar$class_merging = labelDialogManager$$ExternalSyntheticLambda0;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006f  */
    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* bridge */ /* synthetic */ java.lang.Object doInBackground() {
        /*
            r14 = this;
            long r0 = r14.labelId
            java.lang.Long r2 = java.lang.Long.valueOf(r0)
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r5 = 0
            r4[r5] = r2
            java.lang.String r2 = "Querying single label: id=%d."
            java.lang.String r6 = "LabelProviderClient"
            com.google.android.libraries.accessibility.utils.log.LogUtils.d(r6, r2, r4)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r2 = r14.mClient$ar$class_merging
            boolean r4 = r2.checkClient()
            r7 = 0
            if (r4 != 0) goto L1d
            goto L6a
        L1d:
            java.lang.Object r4 = r2.ExecutionList$RunnableExecutorPair$ar$runnable
            android.net.Uri r4 = (android.net.Uri) r4
            android.net.Uri r9 = android.content.ContentUris.withAppendedId(r4, r0)
            java.lang.Object r0 = r2.ExecutionList$RunnableExecutorPair$ar$next     // Catch: java.lang.Throwable -> L4f android.os.RemoteException -> L52
            java.lang.String[] r10 = com.google.android.accessibility.utils.labeling.LabelsTable.ALL_COLUMNS     // Catch: java.lang.Throwable -> L4f android.os.RemoteException -> L52
            r8 = r0
            android.content.ContentProviderClient r8 = (android.content.ContentProviderClient) r8     // Catch: java.lang.Throwable -> L4f android.os.RemoteException -> L52
            r12 = 0
            r13 = 0
            r11 = 0
            android.database.Cursor r0 = r8.query(r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L4f android.os.RemoteException -> L52
            if (r0 != 0) goto L37
            r1 = r7
            goto L47
        L37:
            r0.moveToFirst()     // Catch: android.os.RemoteException -> L4d java.lang.Throwable -> L6b
            com.google.android.accessibility.utils.labeling.Label r1 = com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair.getLabelFromCursorAtCurrentPosition$ar$ds(r0)     // Catch: android.os.RemoteException -> L4d java.lang.Throwable -> L6b
            java.lang.String r2 = "Query result: %s."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: android.os.RemoteException -> L4d java.lang.Throwable -> L6b
            r3[r5] = r1     // Catch: android.os.RemoteException -> L4d java.lang.Throwable -> L6b
            com.google.android.libraries.accessibility.utils.log.LogUtils.v(r6, r2, r3)     // Catch: android.os.RemoteException -> L4d java.lang.Throwable -> L6b
        L47:
            if (r0 == 0) goto L6a
            r0.close()
            return r1
        L4d:
            r1 = move-exception
            goto L55
        L4f:
            r0 = move-exception
            r1 = r0
            goto L6d
        L52:
            r0 = move-exception
            r1 = r0
            r0 = r7
        L55:
            java.lang.String r2 = "RemoteException caught!"
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L6b
            com.google.android.libraries.accessibility.utils.log.LogUtils.e(r6, r2, r3)     // Catch: java.lang.Throwable -> L6b
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L6b
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L6b
            com.google.android.libraries.accessibility.utils.log.LogUtils.d(r6, r1, r2)     // Catch: java.lang.Throwable -> L6b
            if (r0 == 0) goto L6a
            r0.close()
        L6a:
            return r7
        L6b:
            r1 = move-exception
            r7 = r0
        L6d:
            if (r7 == 0) goto L72
            r7.close()
        L72:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.labeling.DirectLabelFetchRequest.doInBackground():java.lang.Object");
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        final Label label = (Label) obj;
        if (label != null) {
            LabelDialogManager$$ExternalSyntheticLambda0 labelDialogManager$$ExternalSyntheticLambda0 = this.onLabelFetchedListener$ar$class_merging;
            WindowTrackerFactory windowTrackerFactory = labelDialogManager$$ExternalSyntheticLambda0.f$0$ar$class_merging$aae6da70_0$ar$class_merging;
            final Context context = (Context) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider;
            final CustomLabelManager customLabelManager = (CustomLabelManager) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
            final Pipeline.FeedbackReturner feedbackReturner = labelDialogManager$$ExternalSyntheticLambda0.f$1;
            LabelDialogManager$BaseEditLabelDialog labelDialogManager$BaseEditLabelDialog = new LabelDialogManager$BaseEditLabelDialog(context, label, customLabelManager, feedbackReturner) { // from class: com.google.android.accessibility.talkback.labeling.LabelDialogManager$EditLabelDialog
                private final Context context;
                private final Label existing;
                private final Pipeline.FeedbackReturner pipeline;

                {
                    super(context, R.string.label_dialog_title_edit, customLabelManager, feedbackReturner);
                    this.context = context;
                    this.existing = label;
                    this.pipeline = feedbackReturner;
                }

                @Override // com.google.android.accessibility.talkback.labeling.LabelDialogManager$BaseEditLabelDialog
                protected final String getCustomizedMessage() {
                    return this.context.getString(R.string.label_dialog_text, this.existing.mViewName);
                }

                @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
                public final String getMessageString() {
                    return null;
                }

                @Override // com.google.android.accessibility.talkback.labeling.LabelDialogManager$BaseEditLabelDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
                public final void handleDialogClick(int i) {
                    if (i == -3) {
                        this.labelManager.removeLabel(this.existing);
                        Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
                        if (feedbackReturner2 != null) {
                            Logger logger = Performance.DEFAULT_LOGGER;
                            String string = this.context.getString(R.string.label_dialog_confirm_label_remove);
                            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                            speakOptions.mFlags = 286;
                            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, Feedback.speech(string, speakOptions));
                            return;
                        }
                        return;
                    }
                    super.handleDialogClick(i);
                }

                @Override // com.google.android.accessibility.talkback.labeling.LabelDialogManager$BaseEditLabelDialog
                public final void onPositiveAction() {
                    EditText editText = this.editField;
                    if (editText != null) {
                        this.existing.mText = editText.getText().toString();
                        this.existing.mTimestampMillis = System.currentTimeMillis();
                        this.labelManager.updateLabel(this.existing);
                    }
                }

                @Override // com.google.android.accessibility.talkback.labeling.LabelDialogManager$BaseEditLabelDialog
                public final void setupCustomizedView() {
                    EditText editText = this.editField;
                    if (editText != null) {
                        editText.setText(this.existing.mText);
                    }
                }
            };
            labelDialogManager$BaseEditLabelDialog.setSoftInputMode(true);
            labelDialogManager$BaseEditLabelDialog.setRestoreFocus(labelDialogManager$$ExternalSyntheticLambda0.f$2);
            labelDialogManager$BaseEditLabelDialog.setNeutralButtonStringRes(R.string.label_dialog_title_remove);
            labelDialogManager$BaseEditLabelDialog.showDialog();
        }
    }
}

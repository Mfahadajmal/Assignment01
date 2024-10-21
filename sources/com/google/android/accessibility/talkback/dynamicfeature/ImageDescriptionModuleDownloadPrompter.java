package com.google.android.accessibility.talkback.dynamicfeature;

import android.content.Context;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$DownloadDialogResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$ImageCaptionPreferenceKeys;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$UninstallDialogResources;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.mlkit.shared.logger.MLKitLoggingOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionModuleDownloadPrompter extends ModuleDownloadPrompter {
    public Pipeline.FeedbackReturner pipeline;

    public ImageDescriptionModuleDownloadPrompter(Context context, Downloader downloader) {
        super(context, downloader, ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION, ImageCaptionConstants$ImageCaptionPreferenceKeys.IMAGE_DESCRIPTION, ImageCaptionConstants$DownloadDialogResources.IMAGE_DESCRIPTION, ImageCaptionConstants$UninstallDialogResources.IMAGE_DESCRIPTION);
    }

    @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter
    protected final boolean initModule() {
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        if (feedbackReturner == null) {
            return false;
        }
        Logger logger = Performance.DEFAULT_LOGGER;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        MLKitLoggingOptions.Builder builder$ar$class_merging$19c02b89_0 = Feedback.ImageCaption.builder$ar$class_merging$19c02b89_0();
        builder$ar$class_merging$19c02b89_0.setAction$ar$edu$a06fe909_0$ar$ds(4);
        builder.imageCaption = builder$ar$class_merging$19c02b89_0.m225build();
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder);
    }
}

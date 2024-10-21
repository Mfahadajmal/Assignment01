package com.google.android.accessibility.selecttospeak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.navigation.ActivityNavigator$hostActivity$1;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.selecttospeak.iterator.ScreenshotNotAvailableException;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SelectToSpeakService$$ExternalSyntheticLambda3 implements ScreenCaptureController.CaptureListener {
    public final /* synthetic */ Object SelectToSpeakService$$ExternalSyntheticLambda3$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SelectToSpeakService$$ExternalSyntheticLambda3(SelectToSpeakService selectToSpeakService, int i) {
        this.switching_field = i;
        this.SelectToSpeakService$$ExternalSyntheticLambda3$ar$f$0 = selectToSpeakService;
    }

    @Override // com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.CaptureListener
    public final void onScreenCaptureFinished(Bitmap bitmap, boolean z) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (bitmap != null && z) {
                    ((CancellableContinuationImpl) this.SelectToSpeakService$$ExternalSyntheticLambda3$ar$f$0).resume(bitmap, ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$86d7b783_0);
                    return;
                }
                throw new ScreenshotNotAvailableException();
            }
            Object obj = this.SelectToSpeakService$$ExternalSyntheticLambda3$ar$f$0;
            if (bitmap != null && z) {
                SelectToSpeakService selectToSpeakService = (SelectToSpeakService) obj;
                selectToSpeakService.screenshot = bitmap;
                selectToSpeakService.uIManager.setBoardBackground(bitmap);
                selectToSpeakService.selectionRequestHandler.post(new ContextMenuDialog$$ExternalSyntheticLambda5(obj, 17, null));
                Rect rect = selectToSpeakService.mlastSelection;
                if (rect != null) {
                    selectToSpeakService.handleSelection(rect, false, new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(obj, 5));
                    return;
                }
                return;
            }
            LogUtils.d("SelectToSpeakService", "Screen Capture failed!", new Object[0]);
            ((SelectToSpeakService) obj).abortHandlingSelection();
            return;
        }
        Object obj2 = this.SelectToSpeakService$$ExternalSyntheticLambda3$ar$f$0;
        if (bitmap != null && z) {
            SelectToSpeakService selectToSpeakService2 = (SelectToSpeakService) obj2;
            selectToSpeakService2.screenshot = bitmap;
            selectToSpeakService2.uIManager.setBoardBackground(bitmap);
            selectToSpeakService2.selectionRequestHandler.post(new ContextMenuDialog$$ExternalSyntheticLambda5(obj2, 15, null));
            selectToSpeakService2.handleSelection(SpannableUtils$NonCopyableTextSpan.getDisplaySizeRect((Context) obj2), true, new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(obj2, 6));
            return;
        }
        LogUtils.d("SelectToSpeakService", "Screen Capture failed!", new Object[0]);
        ((SelectToSpeakService) obj2).abortHandlingSelection();
    }

    public SelectToSpeakService$$ExternalSyntheticLambda3(CancellableContinuationImpl cancellableContinuationImpl, int i) {
        this.switching_field = i;
        this.SelectToSpeakService$$ExternalSyntheticLambda3$ar$f$0 = cancellableContinuationImpl;
    }
}

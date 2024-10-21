package com.google.android.accessibility.talkback.imagecaption;

import android.graphics.Bitmap;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.imagecaption.CaptionRequest;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.accessibility.utils.caption.Result;
import com.google.android.accessibility.utils.ocr.OcrController;
import com.google.android.accessibility.utils.ocr.OcrInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CharacterCaptionRequest extends CaptionRequest implements OcrController.OcrListener {
    public static final /* synthetic */ int CharacterCaptionRequest$ar$NoOp = 0;
    private final OcrController ocrController;
    private final Bitmap screenCapture;

    public CharacterCaptionRequest(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, CaptionRequest.OnFinishListener onFinishListener, CaptionRequest.OnErrorListener onErrorListener, boolean z) {
        super(i, accessibilityNodeInfoCompat, onFinishListener, onErrorListener, z);
        this.ocrController = new OcrController(this, false);
        this.screenCapture = bitmap;
    }

    @Override // com.google.android.accessibility.utils.ocr.OcrController.OcrListener
    public final void onOcrFinished(List list) {
        stopTimeoutRunnable();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String textFromBlocks = OcrController.getTextFromBlocks(((OcrInfo) it.next()).textBlocks);
            if (!TextUtils.isEmpty(textFromBlocks)) {
                arrayList.add(textFromBlocks);
            }
        }
        onCaptionFinish(new Result(ImageCaptionUtils$CaptionType.OCR, StringBuilderUtils.getAggregateText(arrayList), 1.0f));
    }

    @Override // com.google.android.accessibility.talkback.imagecaption.Request
    public final void perform() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OcrInfo(this.node));
        onCaptionStart();
        this.ocrController.recognizeTextForNodes(this.screenCapture, arrayList, null, new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(10)));
        runTimeoutRunnable();
    }

    @Override // com.google.android.accessibility.utils.ocr.OcrController.OcrListener
    public final void onOcrStarted() {
    }
}

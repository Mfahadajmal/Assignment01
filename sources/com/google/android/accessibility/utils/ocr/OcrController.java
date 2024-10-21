package com.google.android.accessibility.utils.ocr;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.RectUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.mlkit.logging.schema.AccelerationAllowlistLogEvent;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.text.Text$TextBase;
import com.google.mlkit.vision.text.Text$TextBlock;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import j$.util.Comparator$CC;
import j$.util.List;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OcrController {
    public static final Comparator TEXT_BLOCK_POSITION_COMPARATOR = Comparator$CC.comparing(new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(9), RectUtils.RECT_POSITION_COMPARATOR);
    private final Handler handler;
    private final boolean needCropScreenshot;
    private final OcrListener ocrListener;
    public TextRecognizer recognizer;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OcrListener {
        void onOcrFinished(List list);

        void onOcrStarted();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OcrRunnable implements Runnable {
        private final Filter filter;
        private final Handler handler;
        private final boolean needCropScreenshot;
        private final List ocrInfos;
        private final OcrListener ocrListener;
        private final TextRecognizer recognizer;
        private final Bitmap screenshot;
        private final Rect selectionBounds;

        public OcrRunnable(Handler handler, OcrListener ocrListener, TextRecognizer textRecognizer, Bitmap bitmap, boolean z, List list, Rect rect, Filter filter) {
            this.handler = handler;
            this.ocrListener = ocrListener;
            this.recognizer = textRecognizer;
            this.screenshot = bitmap;
            this.needCropScreenshot = z;
            this.ocrInfos = list;
            this.selectionBounds = rect;
            this.filter = filter;
        }

        @Override // java.lang.Runnable
        public final void run() {
            String message;
            Bitmap bitmap;
            final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            Handler handler = this.handler;
            Rect rect = this.selectionBounds;
            List list = this.ocrInfos;
            ParserResultRunnable parserResultRunnable = new ParserResultRunnable(handler, list, concurrentHashMap, rect, this.ocrListener);
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                final OcrInfo ocrInfo = (OcrInfo) it.next();
                if (this.filter.accept(ocrInfo.node)) {
                    Rect rect2 = new Rect();
                    ocrInfo.getBoundsInScreenForOcr(rect2);
                    int i = 0;
                    if (this.screenshot.isRecycled()) {
                        LogUtils.w("OcrController", "Screenshot has been recycled.", new Object[0]);
                        break;
                    }
                    try {
                        if (this.needCropScreenshot) {
                            bitmap = SpannableUtils$NonCopyableTextSpan.cropBitmap(this.screenshot, rect2);
                        } else {
                            bitmap = this.screenshot;
                        }
                        if (bitmap != null) {
                            parserResultRunnable.addRecognitionCount();
                            this.recognizer.process(InputImage.fromBitmap$ar$ds(bitmap)).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.accessibility.utils.ocr.OcrController$OcrRunnable$$ExternalSyntheticLambda0
                                @Override // com.google.android.gms.tasks.OnSuccessListener
                                public final void onSuccess(Object obj) {
                                    ConcurrentHashMap.this.put(ocrInfo, ((MultiFlavorDetectorCreator) obj).getTextBlocks());
                                }
                            }).addOnFailureListener$ar$ds(new OcrController$OcrRunnable$$ExternalSyntheticLambda1(concurrentHashMap, ocrInfo, i));
                        }
                    } catch (IllegalArgumentException e) {
                        if (e.getMessage() == null) {
                            message = "Fail to crop screenshot.";
                        } else {
                            message = e.getMessage();
                        }
                        LogUtils.w("OcrController", message, new Object[0]);
                    }
                }
            }
            this.handler.postDelayed(parserResultRunnable, 50L);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ParserResultRunnable implements Runnable {
        private final Handler handler;
        private final List ocrInfos;
        private final OcrListener ocrListener;
        private final Rect selectionBounds;
        private final ConcurrentHashMap textBlocksMap;
        private int recognitionNumber = 0;
        private long waitingTimeMs = 0;

        public ParserResultRunnable(Handler handler, List list, ConcurrentHashMap concurrentHashMap, Rect rect, OcrListener ocrListener) {
            this.handler = handler;
            this.ocrInfos = list;
            this.textBlocksMap = concurrentHashMap;
            this.selectionBounds = rect;
            this.ocrListener = ocrListener;
        }

        public final synchronized void addRecognitionCount() {
            this.recognitionNumber++;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.textBlocksMap.size() != this.recognitionNumber) {
                long j = this.waitingTimeMs;
                if (j < 5000) {
                    long j2 = j + 50;
                    this.waitingTimeMs = j2;
                    LogUtils.v("OcrController", "waiting for OCR result... timeout=" + j2, new Object[0]);
                    this.handler.postDelayed(this, 50L);
                    return;
                }
            }
            boolean z = false;
            for (OcrInfo ocrInfo : this.ocrInfos) {
                List<Text$TextBlock> list = (List) this.textBlocksMap.get(ocrInfo);
                Comparator comparator = OcrController.TEXT_BLOCK_POSITION_COMPARATOR;
                ArrayList arrayList = null;
                if (list != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (Text$TextBlock text$TextBlock : list) {
                        if (text$TextBlock != null) {
                            arrayList2.add(text$TextBlock);
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        List.EL.sort(arrayList2, OcrController.TEXT_BLOCK_POSITION_COMPARATOR);
                        arrayList = arrayList2;
                    }
                }
                ocrInfo.textBlocks = arrayList;
                if (!z && !TextUtils.isEmpty(OcrController.getTextFromBlocks(arrayList))) {
                    z = true;
                }
            }
            if (z) {
                this.ocrListener.onOcrStarted();
            }
            if (this.selectionBounds != null && this.ocrInfos.size() == 1 && ((OcrInfo) this.ocrInfos.get(0)).textBlocks != null) {
                OcrInfo ocrInfo2 = (OcrInfo) this.ocrInfos.get(0);
                Rect rect = this.selectionBounds;
                Rect rect2 = new Rect();
                ocrInfo2.getBoundsInScreenForOcr(rect2);
                java.util.List list2 = ocrInfo2.textBlocks;
                if (list2 != null) {
                    HashSet hashSet = new HashSet();
                    int size = list2.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        Text$TextBlock text$TextBlock2 = (Text$TextBlock) list2.get(size);
                        Rect rect3 = text$TextBlock2.boundingBox;
                        rect3.offset(rect2.left, rect2.top);
                        if (!Rect.intersects(rect3, rect)) {
                            hashSet.add(text$TextBlock2);
                        }
                    }
                    list2.removeAll(hashSet);
                }
            }
            this.ocrListener.onOcrFinished(this.ocrInfos);
        }
    }

    public OcrController(OcrListener ocrListener, boolean z) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.ocrListener = ocrListener;
        this.handler = handler;
        this.recognizer = null;
        this.needCropScreenshot = z;
    }

    public static String getTextFromBlocks(java.util.List list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                Text$TextBlock text$TextBlock = (Text$TextBlock) list.get(i);
                Iterator it = text$TextBlock.getLines().iterator();
                while (it.hasNext()) {
                    Iterator it2 = ((Text$TextBlock) it.next()).getElements().iterator();
                    while (it2.hasNext()) {
                        sb.append(((Text$TextBase) it2.next()).getTextInternal().trim());
                        sb.append(" ");
                    }
                }
                if (!text$TextBlock.getLines().isEmpty() && !TextUtils.isEmpty(sb)) {
                    if (i < list.size() - 1) {
                        sb.replace(sb.length() - 1, sb.length(), "\n");
                    } else {
                        sb.replace(sb.length() - 1, sb.length(), "");
                    }
                }
            }
            return sb.toString();
        }
        return null;
    }

    public final void recognizeTextForNodes(Bitmap bitmap, java.util.List list, Rect rect, Filter filter) {
        if (this.recognizer == null) {
            try {
                this.recognizer = AccelerationAllowlistLogEvent.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
            } catch (IllegalStateException unused) {
                LogUtils.w("OcrController", "Fail to get TextRecognizer.", new Object[0]);
                this.ocrListener.onOcrFinished(list);
                return;
            }
        }
        new Thread(new OcrRunnable(this.handler, this.ocrListener, this.recognizer, bitmap, this.needCropScreenshot, list, rect, filter)).start();
    }
}

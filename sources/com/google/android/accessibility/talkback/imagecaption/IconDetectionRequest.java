package com.google.android.accessibility.talkback.imagecaption;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.imagecaption.CaptionRequest;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.accessibility.utils.caption.Result;
import com.google.android.accessibility.utils.screenunderstanding.IconAnnotation;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.utils.screenunderstanding.Annotation;
import com.google.android.libraries.accessibility.utils.screenunderstanding.AnnotationComparator;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.vision.visionkit.ColorSpace;
import com.google.android.libraries.vision.visionkit.Rotation;
import com.google.android.libraries.vision.visionkit.camera.manager.Size;
import com.google.android.libraries.vision.visionkit.pipeline.Frame;
import com.google.android.libraries.vision.visionkit.pipeline.NativePipeline;
import com.google.android.libraries.vision.visionkit.pipeline.Pipeline;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.util.concurrent.ExecutionList;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IconDetectionRequest extends CaptionRequest {
    private final WorkQueue iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Locale locale;
    private final Bitmap screenCapture;

    public IconDetectionRequest(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, WorkQueue workQueue, Locale locale, CaptionRequest.OnFinishListener onFinishListener, CaptionRequest.OnErrorListener onErrorListener, boolean z) {
        super(i, accessibilityNodeInfoCompat, onFinishListener, onErrorListener, z);
        this.screenCapture = bitmap;
        this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = workQueue;
        this.locale = locale;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetectionFinished$ar$ds() {
        Integer num;
        stopTimeoutRunnable();
        ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType = ImageCaptionUtils$CaptionType.ICON_LABEL;
        WorkQueue workQueue = this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        Context context = (Context) workQueue.WorkQueue$ar$blockingTasksInBuffer;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.node;
        String str = null;
        if (SpannableUtils$IdentifierSpan.isCaptionable(context, accessibilityNodeInfoCompat)) {
            Rect rect = new Rect();
            accessibilityNodeInfoCompat.getBoundsInScreen(rect);
            Object obj = workQueue.WorkQueue$ar$buffer;
            ArrayList arrayList = new ArrayList();
            synchronized (obj) {
                UnmodifiableListIterator it = ((ImmutableList) ((MetricsLogger) obj).MetricsLogger$ar$loggerProvider$ar$class_merging).iterator();
                while (it.hasNext()) {
                    Annotation annotation = (Annotation) it.next();
                    if (Rect.intersects(annotation.getBounds(), rect)) {
                        arrayList.add(annotation);
                    }
                }
            }
            ImmutableList sortedCopyOf = ImmutableList.sortedCopyOf(new AnnotationComparator(rect), arrayList);
            if (!sortedCopyOf.isEmpty()) {
                ImmutableList.Builder builder = new ImmutableList.Builder();
                int i = ((RegularImmutableList) sortedCopyOf).size;
                for (int i2 = 0; i2 < i; i2++) {
                    Annotation annotation2 = (Annotation) sortedCopyOf.get(i2);
                    if (IconAnnotation.ACTIONABLE_ICON_TO_LABEL_RES.containsKey(annotation2.type)) {
                        builder.add$ar$ds$4f674a09_0(new IconAnnotation(annotation2));
                    }
                }
                ImmutableList build = builder.build();
                if (!build.isEmpty()) {
                    if (((RegularImmutableList) build).size == 1) {
                        IconAnnotation iconAnnotation = (IconAnnotation) build.get(0);
                        Object obj2 = workQueue.WorkQueue$ar$blockingTasksInBuffer;
                        if (!AccessibilityNodeInfoUtils.isClickable(accessibilityNodeInfoCompat) && !AccessibilityNodeInfoUtils.isLongClickable(accessibilityNodeInfoCompat)) {
                            num = (Integer) IconAnnotation.ICON_TO_LABEL_RES.get(iconAnnotation.type);
                        } else {
                            num = (Integer) IconAnnotation.ACTIONABLE_ICON_TO_LABEL_RES.get(iconAnnotation.type);
                        }
                        if (num != null) {
                            str = ((Context) obj2).getResources().getString(num.intValue());
                        }
                    } else {
                        LogUtils.v("IconAnnotationsDetectorImpl", "Multiple icons in a node.", new Object[0]);
                    }
                }
            }
        }
        onCaptionFinish(new Result(imageCaptionUtils$CaptionType, str, 1.0f));
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.util.List, java.lang.Object] */
    @Override // com.google.android.accessibility.talkback.imagecaption.Request
    public final void perform() {
        onCaptionStart();
        WorkQueue workQueue = this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        synchronized (workQueue) {
            if (workQueue.isProcessingScreenshot()) {
                workQueue.WorkQueue$ar$consumerIndex.add(this);
            } else {
                Bitmap bitmap = this.screenCapture;
                ((UiChangesTracker) workQueue.WorkQueue$ar$lastScheduledTask).reset();
                Object obj = workQueue.WorkQueue$ar$producerIndex;
                long micros = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
                int i = Rotation.ROTATION_0$ar$edu;
                if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
                    Object obj2 = ((ExecutionList.RunnableExecutorPair) obj).ExecutionList$RunnableExecutorPair$ar$runnable;
                    ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
                    bitmap.copyPixelsToBuffer(allocate);
                    byte[] array = allocate.array();
                    Frame.Builder builder = new Frame.Builder();
                    builder.rawData = array;
                    builder.timestampUs = micros;
                    builder.rotation$ar$edu = i;
                    builder.size = new Size(bitmap.getWidth(), bitmap.getHeight());
                    builder.colorSpace$ar$edu = ColorSpace.RGBA$ar$edu;
                    Frame frame = new Frame(builder.rawData, builder.timestampUs, builder.size, builder.colorSpace$ar$edu, builder.rotation$ar$edu);
                    Pipeline pipeline = (Pipeline) obj2;
                    if (pipeline.nativeContext != 0) {
                        if (pipeline.frameBuffer$ar$class_merging.addFrame(frame, frame.timestampUs)) {
                            NativePipeline nativePipeline = pipeline.nativePipeline;
                            long j = pipeline.nativeContext;
                            long j2 = pipeline.nativeFrameManager;
                            long j3 = frame.timestampUs;
                            byte[] bArr = frame.rawData;
                            Size size = frame.size;
                            int i2 = frame.colorSpace$ar$edu;
                            int i3 = frame.rotation$ar$edu;
                            if (i2 != 0) {
                                int i4 = i3 - 1;
                                if (i3 != 0) {
                                    nativePipeline.receivePreviewFrameWithStreamName(j, j2, j3, bArr, size.width, size.height, i2 - 1, i4, "image_frame");
                                } else {
                                    throw null;
                                }
                            } else {
                                throw null;
                            }
                        }
                        synchronized (workQueue) {
                            workQueue.WorkQueue$ar$consumerIndex.add(this);
                        }
                    } else {
                        throw new IllegalStateException("Pipeline has been closed or was not initialized");
                    }
                } else {
                    throw new IllegalArgumentException("Unsupported bitmap config ".concat(String.valueOf(String.valueOf(bitmap.getConfig()))));
                }
            }
        }
        runTimeoutRunnable();
    }
}

package com.google.android.accessibility.talkback.imagedescription;

import android.graphics.Bitmap;
import android.os.SystemClock;
import androidx.work.CoroutineWorker$getForegroundInfoAsync$1;
import androidx.work.impl.WorkManagerImplExtKt;
import androidx.work.impl.WorkerWrapper;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.phenotype.client.stable.SnapshotHandler;
import com.google.android.libraries.phenotype.client.stable.SnapshotProto$Snapshot;
import com.google.android.libraries.vision.visionkit.ColorSpace;
import com.google.android.libraries.vision.visionkit.Rotation;
import com.google.android.libraries.vision.visionkit.pipeline.Results;
import com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline;
import com.google.android.libraries.vision.visionkit.pipeline.alt.Pipeline;
import com.google.android.libraries.vision.visionkit.pipeline.alt.PipelineException;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.mlkit.common.sdkinternal.CloseGuard$Factory;
import com.google.mlkit.shared.devtools.DevTimer;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.text.internal.TextRecognizerTaskWithResource;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ImageDescriptionProcessor$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ Object ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ImageDescriptionProcessor$$ExternalSyntheticLambda0(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$0 = obj;
        this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$1 = obj2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        Optional of;
        Pipeline pipeline;
        long j;
        int status;
        DevTimer devTimer;
        int i = this.switching_field;
        if (i != 0) {
            int i2 = 1;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        CloseGuard$Factory.getInstance$ar$ds$cb56d710_0();
                        CloseGuard$Factory.getInstance$ar$ds$cb56d710_0();
                        Object obj = this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$1;
                        Object obj2 = this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$0;
                        if (!Boolean.parseBoolean("")) {
                            devTimer = DevTimer.NoopTimer.SINGLETON;
                        } else {
                            if (DevTimer.timers.get("detectorTaskWithResource#run") == null) {
                                DevTimer.timers.put("detectorTaskWithResource#run", new DevTimer("detectorTaskWithResource#run"));
                            }
                            devTimer = (DevTimer) DevTimer.timers.get("detectorTaskWithResource#run");
                        }
                        DevTimer start = devTimer.start();
                        try {
                            MultiFlavorDetectorCreator run$ar$class_merging = ((TextRecognizerTaskWithResource) ((MobileVisionBase) obj2).detectorTaskWithResource).run$ar$class_merging((InputImage) obj);
                            start.close();
                            return run$ar$class_merging;
                        } finally {
                        }
                    } else {
                        long j2 = FakeSplitInstallManager.DOWNLOAD_SEGMENT_DURATION_MS;
                        Object obj3 = this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$0;
                        if (obj3 != null && (status = ((SplitInstallSessionState) obj3).status()) != 0 && status != 5 && status != 6 && status != 7) {
                            throw new SplitInstallException(-1);
                        }
                        if (obj3 != null) {
                            i2 = 1 + ((SplitInstallSessionState) obj3).sessionId();
                        }
                        return new SplitInstallSessionState(i2, 1, 0, 0L, 0L, ((SplitInstallRequest) this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$1).moduleNames, new ArrayList(), null, null);
                    }
                } else {
                    ((SnapshotHandler) this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$1).m205x39098f80((SnapshotProto$Snapshot) this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$0);
                    return null;
                }
            } else {
                return CoroutineWorker$getForegroundInfoAsync$1.invokeSuspend$lambda$1$ar$class_merging((WorkManagerImplExtKt) this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$1, (WorkerWrapper) this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$0);
            }
        } else {
            Object obj4 = this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$0;
            try {
                pipeline = ((ImageDescriptionProcessor) obj4).pipeline;
                j = pipeline.nativeContext;
            } catch (PipelineException unused) {
                LogUtils.e("ImageDescriptionProcessor", "Fail to initialize the AmbientKit Pipeline", new Object[0]);
            }
            if (j != 0) {
                try {
                    pipeline.nativePipeline.start(j);
                    pipeline.nativePipeline.waitUntilIdle(pipeline.nativeContext);
                    Object obj5 = this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$1;
                    Pipeline pipeline2 = ((ImageDescriptionProcessor) obj4).pipeline;
                    long micros = TimeUnit.MILLISECONDS.toMicros(SystemClock.elapsedRealtime());
                    Bitmap bitmap = (Bitmap) obj5;
                    if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
                        if (bitmap.isMutable()) {
                            bitmap.setConfig(Bitmap.Config.ARGB_8888);
                        } else {
                            obj5 = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                        }
                    }
                    int i3 = Rotation.ROTATION_0$ar$edu;
                    if (pipeline2.nativeContext != 0) {
                        Bitmap bitmap2 = (Bitmap) obj5;
                        if (bitmap2.getConfig() == Bitmap.Config.ARGB_8888) {
                            NativePipeline nativePipeline = pipeline2.nativePipeline;
                            long j3 = pipeline2.nativeContext;
                            int width = bitmap2.getWidth();
                            int height = bitmap2.getHeight();
                            int i4 = ColorSpace.RGBA$ar$edu;
                            int i5 = i4 - 1;
                            if (i4 != 0) {
                                int i6 = i3 - 1;
                                if (i3 != 0) {
                                    byte[] processBitmap = nativePipeline.processBitmap(j3, micros, bitmap2, width, height, i5, i6);
                                    if (processBitmap == null) {
                                        of = Absent.INSTANCE;
                                    } else {
                                        try {
                                            of = Optional.of((Results) GeneratedMessageLite.parseFrom(Results.DEFAULT_INSTANCE, processBitmap, pipeline2.extensionRegistry));
                                        } catch (InvalidProtocolBufferException e) {
                                            throw new IllegalStateException("Could not parse results", e);
                                        }
                                    }
                                    return (Results) of.or(Results.DEFAULT_INSTANCE);
                                }
                                throw null;
                            }
                            throw null;
                        }
                        throw new IllegalArgumentException("Unsupported bitmap config ".concat(String.valueOf(String.valueOf(bitmap2.getConfig()))));
                    }
                    throw new IllegalStateException("Pipeline has been closed or was not initialized");
                } catch (PipelineException e2) {
                    pipeline.nativePipeline.stop(pipeline.nativeContext);
                    throw e2;
                }
            }
            throw new PipelineException(PipelineException.StatusCode.FAILED_PRECONDITION.ordinal(), "Pipeline has been closed or was not initialized");
        }
    }

    public /* synthetic */ ImageDescriptionProcessor$$ExternalSyntheticLambda0(Object obj, Object obj2, int i, byte[] bArr) {
        this.switching_field = i;
        this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$1 = obj;
        this.ImageDescriptionProcessor$$ExternalSyntheticLambda0$ar$f$0 = obj2;
    }
}

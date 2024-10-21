package com.google.mlkit.logging.schema;

import _COROUTINE._BOUNDARY;
import com.google.android.libraries.mdi.download.debug.common.filegroups.AutoValue_MddDebugMainFragmentHelper_ActionInfo;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentHelper;
import com.google.android.libraries.storage.file.common.UnsupportedFileStorageOperation;
import com.google.android.libraries.storage.file.common.internal.BackendOutputStream;
import com.google.android.play.core.splitinstall.testing.LocalTestingConfig;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.firebase.encoders.proto.ProtoEnum;
import com.google.mlkit.logging.schema.ImageInfo;
import j$.util.DesugarCollections;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageInfo {
    public ImageFormat imageFormat;
    public Integer originalImageSize;
    private Integer compressedImageSize = null;
    private Boolean isOdmlImage = null;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object ImageInfo$Builder$ar$imageFormat;
        public Object ImageInfo$Builder$ar$originalImageSize;

        public Builder() {
        }

        public static ThreadFactory doBuild$ar$class_merging(Builder builder) {
            final AtomicLong atomicLong;
            Object obj = builder.ImageInfo$Builder$ar$originalImageSize;
            Object obj2 = builder.ImageInfo$Builder$ar$imageFormat;
            final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
            if (obj != null) {
                atomicLong = new AtomicLong(0L);
            } else {
                atomicLong = null;
            }
            final Boolean bool = (Boolean) obj2;
            final String str = (String) obj;
            return new ThreadFactory() { // from class: com.google.common.util.concurrent.ThreadFactoryBuilder$1
                @Override // java.util.concurrent.ThreadFactory
                public final Thread newThread(Runnable runnable) {
                    Thread newThread = defaultThreadFactory.newThread(runnable);
                    newThread.getClass();
                    String str2 = str;
                    if (str2 != null) {
                        AtomicLong atomicLong2 = atomicLong;
                        atomicLong2.getClass();
                        newThread.setName(ImageInfo.Builder.format(str2, Long.valueOf(atomicLong2.getAndIncrement())));
                    }
                    Boolean bool2 = bool;
                    if (bool2 != null) {
                        bool2.booleanValue();
                        newThread.setDaemon(true);
                    }
                    return newThread;
                }
            };
        }

        public static String format(String str, Object... objArr) {
            return String.format(Locale.ROOT, str, objArr);
        }

        /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Map, java.lang.Object] */
        public final LocalTestingConfig autoBuild() {
            if (this.ImageInfo$Builder$ar$originalImageSize != null) {
                return new LocalTestingConfig((Integer) this.ImageInfo$Builder$ar$imageFormat, this.ImageInfo$Builder$ar$originalImageSize);
            }
            throw new IllegalStateException("Missing required properties: splitInstallErrorCodeByModule");
        }

        public final MddDebugMainFragmentHelper.ActionInfo build() {
            Object obj = this.ImageInfo$Builder$ar$originalImageSize;
            if (obj != null) {
                return new AutoValue_MddDebugMainFragmentHelper_ActionInfo((String) obj, (String) this.ImageInfo$Builder$ar$imageFormat);
            }
            throw new IllegalStateException("Missing required properties: action");
        }

        public final void commit() {
            sync();
        }

        public final void forOutputChain(List list) {
            OutputStream outputStream = (OutputStream) ContextDataProvider.getLast(list);
            if (outputStream instanceof BackendOutputStream) {
                this.ImageInfo$Builder$ar$originalImageSize = (BackendOutputStream) outputStream;
                this.ImageInfo$Builder$ar$imageFormat = (OutputStream) list.get(0);
            }
        }

        public final void setNameFormat$ar$ds(String str) {
            format(str, 0);
            this.ImageInfo$Builder$ar$originalImageSize = str;
        }

        public final void setSplitInstallErrorCodeByModule$ar$ds(Map map) {
            if (map != null) {
                this.ImageInfo$Builder$ar$originalImageSize = map;
                return;
            }
            throw new NullPointerException("Null splitInstallErrorCodeByModule");
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        public final Map splitInstallErrorCodeByModule() {
            ?? r0 = this.ImageInfo$Builder$ar$originalImageSize;
            if (r0 != 0) {
                return r0;
            }
            throw new IllegalStateException("Property \"splitInstallErrorCodeByModule\" has not been set");
        }

        public final void sync() {
            if (this.ImageInfo$Builder$ar$originalImageSize != null) {
                ((OutputStream) this.ImageInfo$Builder$ar$imageFormat).flush();
                ((BackendOutputStream) this.ImageInfo$Builder$ar$originalImageSize).sync();
                return;
            }
            throw new UnsupportedFileStorageOperation("Cannot sync underlying stream");
        }

        public Builder(byte[] bArr) {
            this.ImageInfo$Builder$ar$originalImageSize = null;
            this.ImageInfo$Builder$ar$imageFormat = null;
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this();
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            this();
        }

        /* renamed from: build */
        public final LocalTestingConfig m222build() {
            setSplitInstallErrorCodeByModule$ar$ds(DesugarCollections.unmodifiableMap(splitInstallErrorCodeByModule()));
            return autoBuild();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ImageFormat implements ProtoEnum {
        UNKNOWN_FORMAT(0),
        NV16(1),
        NV21(2),
        YV12(3),
        YUV_420_888(7),
        JPEG(8),
        BITMAP(4),
        CM_SAMPLE_BUFFER_REF(5),
        UI_IMAGE(6),
        CV_PIXEL_BUFFER_REF(9);

        private final int value;

        ImageFormat(int i) {
            this.value = i;
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public final int getNumber() {
            return this.value;
        }
    }

    public ImageInfo(Builder builder) {
        this.imageFormat = (ImageFormat) builder.ImageInfo$Builder$ar$imageFormat;
        this.originalImageSize = (Integer) builder.ImageInfo$Builder$ar$originalImageSize;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageInfo)) {
            return false;
        }
        ImageInfo imageInfo = (ImageInfo) obj;
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.imageFormat, imageInfo.imageFormat) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.originalImageSize, imageInfo.originalImageSize)) {
            Integer num = imageInfo.compressedImageSize;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
                Boolean bool = imageInfo.isOdmlImage;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.imageFormat, this.originalImageSize, null, null});
    }
}

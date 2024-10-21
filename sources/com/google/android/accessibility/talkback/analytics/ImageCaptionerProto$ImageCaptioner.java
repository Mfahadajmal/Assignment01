package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageCaptionerProto$ImageCaptioner extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final ImageCaptionerProto$ImageCaptioner DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int bitField1_;
    private int captionRequestCount_;
    private int captionRequestManualCount_;
    private int iconDetectAbortCount_;
    private int iconDetectFailCount_;
    private int iconDetectLibInstallDenyBySettings_;
    public int iconDetectLibInstallDeny_;
    public int iconDetectLibInstallFail_;
    private int iconDetectLibInstallRequestBySettings_;
    public int iconDetectLibInstallRequest_;
    public int iconDetectLibInstallSuccess_;
    public int iconDetectLibUninstallDeny_;
    public int iconDetectLibUninstallRequest_;
    private int iconDetectNoResultCount_;
    private int iconDetectPerformCount_;
    private int iconDetectSucceedCount_;
    private int imageCaptionCacheHitCount_;
    private int imageCaptionFailAsScreenHiddenCount_;
    private int imageDescribeAbortCount_;
    private int imageDescribeFailCount_;
    private int imageDescribeLibInstallDenyBySettings_;
    public int imageDescribeLibInstallDeny_;
    public int imageDescribeLibInstallFail_;
    private int imageDescribeLibInstallRequestBySettings_;
    public int imageDescribeLibInstallRequest_;
    public int imageDescribeLibInstallSuccess_;
    public int imageDescribeLibUninstallDeny_;
    public int imageDescribeLibUninstallRequest_;
    private int imageDescribeNoResultCount_;
    private int imageDescribePerformCount_;
    private int imageDescribeSucceedCount_;
    private int ocrAbortCount_;
    private int ocrPerformCount_;
    private int ocrPerformFailCount_;
    private int ocrPerformSucceedCount_;
    private int ocrPerformSucceedEmptyCount_;
    private int scheduleScreenshotCaptureFailedCount_;
    private int screenshotFailedCount_;

    static {
        ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner = new ImageCaptionerProto$ImageCaptioner();
        DEFAULT_INSTANCE = imageCaptionerProto$ImageCaptioner;
        GeneratedMessageLite.registerDefaultInstance(ImageCaptionerProto$ImageCaptioner.class, imageCaptionerProto$ImageCaptioner);
    }

    private ImageCaptionerProto$ImageCaptioner() {
    }

    public static /* synthetic */ void access$100(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 1;
        imageCaptionerProto$ImageCaptioner.captionRequestCount_ = i;
    }

    public static /* synthetic */ void access$1100(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 32;
        imageCaptionerProto$ImageCaptioner.iconDetectSucceedCount_ = i;
    }

    public static /* synthetic */ void access$1300(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 64;
        imageCaptionerProto$ImageCaptioner.iconDetectNoResultCount_ = i;
    }

    public static /* synthetic */ void access$1500(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        imageCaptionerProto$ImageCaptioner.iconDetectFailCount_ = i;
    }

    public static /* synthetic */ void access$1700(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 256;
        imageCaptionerProto$ImageCaptioner.ocrPerformCount_ = i;
    }

    public static /* synthetic */ void access$1900(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 512;
        imageCaptionerProto$ImageCaptioner.ocrPerformSucceedCount_ = i;
    }

    public static /* synthetic */ void access$2100(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 1024;
        imageCaptionerProto$ImageCaptioner.ocrPerformSucceedEmptyCount_ = i;
    }

    public static /* synthetic */ void access$2300(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 2048;
        imageCaptionerProto$ImageCaptioner.ocrPerformFailCount_ = i;
    }

    public static /* synthetic */ void access$2500(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 4096;
        imageCaptionerProto$ImageCaptioner.iconDetectAbortCount_ = i;
    }

    public static /* synthetic */ void access$2700(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 8192;
        imageCaptionerProto$ImageCaptioner.ocrAbortCount_ = i;
    }

    public static /* synthetic */ void access$2900(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 16384;
        imageCaptionerProto$ImageCaptioner.iconDetectLibInstallRequest_ = i;
    }

    public static /* synthetic */ void access$300(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 2;
        imageCaptionerProto$ImageCaptioner.captionRequestManualCount_ = i;
    }

    public static /* synthetic */ void access$3100(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 32768;
        imageCaptionerProto$ImageCaptioner.iconDetectLibInstallRequestBySettings_ = i;
    }

    public static /* synthetic */ void access$3300(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 65536;
        imageCaptionerProto$ImageCaptioner.iconDetectLibInstallDeny_ = i;
    }

    public static /* synthetic */ void access$3500(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 131072;
        imageCaptionerProto$ImageCaptioner.iconDetectLibInstallDenyBySettings_ = i;
    }

    public static /* synthetic */ void access$3700(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 262144;
        imageCaptionerProto$ImageCaptioner.iconDetectLibInstallSuccess_ = i;
    }

    public static /* synthetic */ void access$3900(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 524288;
        imageCaptionerProto$ImageCaptioner.iconDetectLibInstallFail_ = i;
    }

    public static /* synthetic */ void access$4100(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 1048576;
        imageCaptionerProto$ImageCaptioner.iconDetectLibUninstallRequest_ = i;
    }

    public static /* synthetic */ void access$4300(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 2097152;
        imageCaptionerProto$ImageCaptioner.iconDetectLibUninstallDeny_ = i;
    }

    public static /* synthetic */ void access$4500(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 4194304;
        imageCaptionerProto$ImageCaptioner.imageDescribePerformCount_ = i;
    }

    public static /* synthetic */ void access$4700(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 8388608;
        imageCaptionerProto$ImageCaptioner.imageDescribeSucceedCount_ = i;
    }

    public static /* synthetic */ void access$4900(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 16777216;
        imageCaptionerProto$ImageCaptioner.imageDescribeNoResultCount_ = i;
    }

    public static /* synthetic */ void access$500(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 4;
        imageCaptionerProto$ImageCaptioner.screenshotFailedCount_ = i;
    }

    public static /* synthetic */ void access$5100(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 33554432;
        imageCaptionerProto$ImageCaptioner.imageDescribeFailCount_ = i;
    }

    public static /* synthetic */ void access$5300(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 67108864;
        imageCaptionerProto$ImageCaptioner.imageDescribeAbortCount_ = i;
    }

    public static /* synthetic */ void access$5500(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 134217728;
        imageCaptionerProto$ImageCaptioner.imageCaptionFailAsScreenHiddenCount_ = i;
    }

    public static /* synthetic */ void access$5700(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 268435456;
        imageCaptionerProto$ImageCaptioner.scheduleScreenshotCaptureFailedCount_ = i;
    }

    public static /* synthetic */ void access$5900(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 536870912;
        imageCaptionerProto$ImageCaptioner.imageDescribeLibInstallRequest_ = i;
    }

    public static /* synthetic */ void access$6100(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 1073741824;
        imageCaptionerProto$ImageCaptioner.imageDescribeLibInstallRequestBySettings_ = i;
    }

    public static /* synthetic */ void access$6300(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= Integer.MIN_VALUE;
        imageCaptionerProto$ImageCaptioner.imageDescribeLibInstallDeny_ = i;
    }

    public static /* synthetic */ void access$6500(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField1_ |= 1;
        imageCaptionerProto$ImageCaptioner.imageDescribeLibInstallDenyBySettings_ = i;
    }

    public static /* synthetic */ void access$6700(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField1_ |= 2;
        imageCaptionerProto$ImageCaptioner.imageDescribeLibInstallSuccess_ = i;
    }

    public static /* synthetic */ void access$6900(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField1_ |= 4;
        imageCaptionerProto$ImageCaptioner.imageDescribeLibInstallFail_ = i;
    }

    public static /* synthetic */ void access$700(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 8;
        imageCaptionerProto$ImageCaptioner.imageCaptionCacheHitCount_ = i;
    }

    public static /* synthetic */ void access$7100(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField1_ |= 8;
        imageCaptionerProto$ImageCaptioner.imageDescribeLibUninstallRequest_ = i;
    }

    public static /* synthetic */ void access$7300(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField1_ |= 16;
        imageCaptionerProto$ImageCaptioner.imageDescribeLibUninstallDeny_ = i;
    }

    public static /* synthetic */ void access$900(ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner, int i) {
        imageCaptionerProto$ImageCaptioner.bitField0_ |= 16;
        imageCaptionerProto$ImageCaptioner.iconDetectPerformCount_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001%\u0000\u0002\u00010%\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006\bင\u0007\tင\b\nင\t\u000bင\n\fင\u000b\rင\f\u000eင\r\u000fင\u000e\u0010င\u000f\u0011င\u0010\u0012င\u0011\u0013င\u0012\u0014င\u0013\u0015င\u0014\u0016င\u0015\u001fင\u0016 င\u0017!င\u0018\"င\u0019#င\u001a$င\u001b%င\u001c)င\u001d*င\u001e+င\u001f,င -င!.င\"/င#0င$", new Object[]{"bitField0_", "bitField1_", "captionRequestCount_", "captionRequestManualCount_", "screenshotFailedCount_", "imageCaptionCacheHitCount_", "iconDetectPerformCount_", "iconDetectSucceedCount_", "iconDetectNoResultCount_", "iconDetectFailCount_", "ocrPerformCount_", "ocrPerformSucceedCount_", "ocrPerformSucceedEmptyCount_", "ocrPerformFailCount_", "iconDetectAbortCount_", "ocrAbortCount_", "iconDetectLibInstallRequest_", "iconDetectLibInstallRequestBySettings_", "iconDetectLibInstallDeny_", "iconDetectLibInstallDenyBySettings_", "iconDetectLibInstallSuccess_", "iconDetectLibInstallFail_", "iconDetectLibUninstallRequest_", "iconDetectLibUninstallDeny_", "imageDescribePerformCount_", "imageDescribeSucceedCount_", "imageDescribeNoResultCount_", "imageDescribeFailCount_", "imageDescribeAbortCount_", "imageCaptionFailAsScreenHiddenCount_", "scheduleScreenshotCaptureFailedCount_", "imageDescribeLibInstallRequest_", "imageDescribeLibInstallRequestBySettings_", "imageDescribeLibInstallDeny_", "imageDescribeLibInstallDenyBySettings_", "imageDescribeLibInstallSuccess_", "imageDescribeLibInstallFail_", "imageDescribeLibUninstallRequest_", "imageDescribeLibUninstallDeny_"});
            case NEW_MUTABLE_INSTANCE:
                return new ImageCaptionerProto$ImageCaptioner();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (char[]) null, (char[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ImageCaptionerProto$ImageCaptioner.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            default:
                throw new UnsupportedOperationException();
        }
    }
}

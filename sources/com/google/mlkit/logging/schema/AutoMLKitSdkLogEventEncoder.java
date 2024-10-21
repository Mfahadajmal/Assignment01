package com.google.mlkit.logging.schema;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.AtProtobuf;
import com.google.mlkit.logging.schema.AggregatedAutoMLImageLabelingInferenceLogEvent;
import com.google.mlkit.logging.schema.AggregatedCustomModelInferenceLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceBarcodeDetectionLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceDocumentCroppingLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceDocumentDetectionLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceDocumentEnhancementLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceExplicitContentLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceFaceDetectionLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceFaceMeshLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceImageCaptioningInferenceLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceImageLabelDetectionLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceImageQualityAnalysisLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceObjectInferenceLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDevicePoseDetectionLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceSegmentationLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceShadowRemovalLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceStainRemovalLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceSubjectSegmentationLogEvent;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.CustomModelInferenceLogEvent;
import com.google.mlkit.logging.schema.ModelOptions;
import com.google.mlkit.logging.schema.OnDeviceDigitalInkSegmentationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import com.google.mlkit.logging.schema.acceleration.DeviceInfo;
import com.google.mlkit.logging.schema.acceleration.GPUInfo;
import com.google.mlkit.logging.schema.acceleration.InferenceError;
import com.google.mlkit.logging.schema.acceleration.NNAPIInfo;
import com.google.mlkit.logging.schema.acceleration.PipelineAcceleration;
import com.google.mlkit.logging.schema.acceleration.PipelineIdentifier;
import com.google.mlkit.logging.schema.acceleration.PipelineInferenceEvent;
import com.google.mlkit.logging.schema.acceleration.PipelineStageAcceleration;
import com.google.mlkit.logging.schema.acceleration.ValidationMetricResult;
import com.google.mlkit.logging.schema.acceleration.ValidationTestResult;
import com.google.mlkit.logging.schema.visionextension.BarcodeInitializationOptions;
import com.google.mlkit.logging.schema.visionextension.FaceInitializationOptions;
import com.google.search.mdi.aratea.proto.FeatureName;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoMLKitSdkLogEventEncoder {
    public static final AutoMLKitSdkLogEventEncoder CONFIG$ar$class_merging = new AutoMLKitSdkLogEventEncoder();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AccelerationAllowlistLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DELEGATEFILTER_DESCRIPTOR;
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor HANDLEDERRORS_DESCRIPTOR;
        private static final FieldDescriptor HTTPRESPONSECODE_DESCRIPTOR;
        static final AccelerationAllowlistLogEventEncoder INSTANCE = new AccelerationAllowlistLogEventEncoder();
        private static final FieldDescriptor MODELNAMESPACE_DESCRIPTOR;
        private static final FieldDescriptor PARTIALLYHANDLEDERRORS_DESCRIPTOR;
        private static final FieldDescriptor UNHANDLEDERRORS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DURATIONMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("handledErrors");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            HANDLEDERRORS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("partiallyHandledErrors");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            PARTIALLYHANDLEDERRORS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("unhandledErrors");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            UNHANDLEDERRORS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("modelNamespace");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            MODELNAMESPACE_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("delegateFilter");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            DELEGATEFILTER_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("httpResponseCode");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            HTTPRESPONSECODE_DESCRIPTOR = builder7.build();
        }

        private AccelerationAllowlistLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedAutoMLImageLabelingInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedAutoMLImageLabelingInferenceLogEventEncoder INSTANCE = new AggregatedAutoMLImageLabelingInferenceLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedAutoMLImageLabelingInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedAutoMLImageLabelingInferenceLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedAutoMLImageLabelingInferenceLogEventLogEventKeyEncoder INSTANCE = new AggregatedAutoMLImageLabelingInferenceLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISCOLDCALL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            OPTIONS_DESCRIPTOR = builder4.build();
        }

        private AggregatedAutoMLImageLabelingInferenceLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedCustomModelInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedCustomModelInferenceLogEventEncoder INSTANCE = new AggregatedCustomModelInferenceLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedCustomModelInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedCustomModelInferenceLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor INPUTSFORMATS_DESCRIPTOR;
        static final AggregatedCustomModelInferenceLogEventLogEventKeyEncoder INSTANCE = new AggregatedCustomModelInferenceLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor OUTPUTFORMATS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISCOLDCALL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inputsFormats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INPUTSFORMATS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("outputFormats");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            OUTPUTFORMATS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            OPTIONS_DESCRIPTOR = builder5.build();
        }

        private AggregatedCustomModelInferenceLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceBarcodeDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceBarcodeDetectionLogEventEncoder INSTANCE = new AggregatedOnDeviceBarcodeDetectionLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceBarcodeDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceBarcodeDetectionLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTEDBARCODEFORMATS_DESCRIPTOR;
        private static final FieldDescriptor DETECTEDBARCODEVALUETYPES_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor HASRESULT_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceBarcodeDetectionLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceBarcodeDetectionLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("hasResult");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            HASRESULT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            IMAGEINFO_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            OPTIONS_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("detectedBarcodeFormats");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            DETECTEDBARCODEFORMATS_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("detectedBarcodeValueTypes");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            DETECTEDBARCODEVALUETYPES_DESCRIPTOR = builder7.build();
        }

        private AggregatedOnDeviceBarcodeDetectionLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceDocumentCroppingLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceDocumentCroppingLogEventEncoder INSTANCE = new AggregatedOnDeviceDocumentCroppingLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceDocumentCroppingLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceDocumentCroppingLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceDocumentCroppingLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceDocumentCroppingLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceDocumentCroppingLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceDocumentDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceDocumentDetectionLogEventEncoder INSTANCE = new AggregatedOnDeviceDocumentDetectionLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceDocumentDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceDocumentDetectionLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor DOCUMENTCORNERCONFIDENCE_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTPRESENCECONFIDENCE_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTROTATIONSUGGESTIONCONFIDENCE_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTROTATIONSUGGESTIONDEGREES_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceDocumentDetectionLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceDocumentDetectionLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            OPTIONS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("documentPresenceConfidence");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            DOCUMENTPRESENCECONFIDENCE_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("documentCornerConfidence");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            DOCUMENTCORNERCONFIDENCE_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("documentRotationSuggestionDegrees");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            DOCUMENTROTATIONSUGGESTIONDEGREES_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("documentRotationSuggestionConfidence");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            DOCUMENTROTATIONSUGGESTIONCONFIDENCE_DESCRIPTOR = builder8.build();
        }

        private AggregatedOnDeviceDocumentDetectionLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceDocumentEnhancementLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceDocumentEnhancementLogEventEncoder INSTANCE = new AggregatedOnDeviceDocumentEnhancementLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceDocumentEnhancementLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceDocumentEnhancementLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceDocumentEnhancementLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceDocumentEnhancementLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor PARAMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("params");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PARAMS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDeviceDocumentEnhancementLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceExplicitContentLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceExplicitContentLogEventEncoder INSTANCE = new AggregatedOnDeviceExplicitContentLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceExplicitContentLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceExplicitContentLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceExplicitContentLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceExplicitContentLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceExplicitContentLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceFaceDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceFaceDetectionLogEventEncoder INSTANCE = new AggregatedOnDeviceFaceDetectionLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceFaceDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceFaceDetectionLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor CONTOURDETECTEDFACES_DESCRIPTOR;
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceFaceDetectionLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceFaceDetectionLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor NONCONTOURDETECTEDFACES_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISCOLDCALL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DETECTOROPTIONS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("contourDetectedFaces");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            CONTOURDETECTEDFACES_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("nonContourDetectedFaces");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            NONCONTOURDETECTEDFACES_DESCRIPTOR = builder6.build();
        }

        private AggregatedOnDeviceFaceDetectionLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceFaceMeshLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceFaceMeshLogEventEncoder INSTANCE = new AggregatedOnDeviceFaceMeshLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceFaceMeshLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceFaceMeshLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceFaceMeshLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceFaceMeshLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DETECTOROPTIONS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDeviceFaceMeshLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceImageCaptioningInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceImageCaptioningInferenceLogEventEncoder INSTANCE = new AggregatedOnDeviceImageCaptioningInferenceLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceImageCaptioningInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceImageCaptioningInferenceLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceImageCaptioningInferenceLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceImageCaptioningInferenceLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISCOLDCALL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceImageCaptioningInferenceLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceImageLabelDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceImageLabelDetectionLogEventEncoder INSTANCE = new AggregatedOnDeviceImageLabelDetectionLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceImageLabelDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceImageLabelDetectionLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceImageLabelDetectionLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceImageLabelDetectionLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISCOLDCALL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DETECTOROPTIONS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDeviceImageLabelDetectionLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceImageQualityAnalysisLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceImageQualityAnalysisLogEventEncoder INSTANCE = new AggregatedOnDeviceImageQualityAnalysisLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceImageQualityAnalysisLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceImageQualityAnalysisLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceImageQualityAnalysisLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceImageQualityAnalysisLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            OPTIONS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDeviceImageQualityAnalysisLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceObjectInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceObjectInferenceLogEventEncoder INSTANCE = new AggregatedOnDeviceObjectInferenceLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceObjectInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceObjectInferenceLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor HASRESULT_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceObjectInferenceLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceObjectInferenceLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("hasResult");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            HASRESULT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            IMAGEINFO_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            DETECTOROPTIONS_DESCRIPTOR = builder5.build();
        }

        private AggregatedOnDeviceObjectInferenceLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDevicePoseDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDevicePoseDetectionLogEventEncoder INSTANCE = new AggregatedOnDevicePoseDetectionLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDevicePoseDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDevicePoseDetectionLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDevicePoseDetectionLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDevicePoseDetectionLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISCOLDCALL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DETECTOROPTIONS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDevicePoseDetectionLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceSegmentationLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceSegmentationLogEventEncoder INSTANCE = new AggregatedOnDeviceSegmentationLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceSegmentationLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceSegmentationLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceSegmentationLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceSegmentationLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISCOLDCALL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DETECTOROPTIONS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDeviceSegmentationLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceShadowRemovalLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceShadowRemovalLogEventEncoder INSTANCE = new AggregatedOnDeviceShadowRemovalLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceShadowRemovalLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceShadowRemovalLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceShadowRemovalLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceShadowRemovalLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor PARAMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("params");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PARAMS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDeviceShadowRemovalLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceStainRemovalLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceStainRemovalLogEventEncoder INSTANCE = new AggregatedOnDeviceStainRemovalLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceStainRemovalLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceStainRemovalLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceStainRemovalLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceStainRemovalLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor PARAMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("params");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PARAMS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDeviceStainRemovalLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceSubjectSegmentationLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceSubjectSegmentationLogEventEncoder INSTANCE = new AggregatedOnDeviceSubjectSegmentationLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceSubjectSegmentationLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceSubjectSegmentationLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceSubjectSegmentationLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceSubjectSegmentationLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor SUBJECTSEGMENTEROPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISCOLDCALL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("subjectSegmenterOptions");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            SUBJECTSEGMENTEROPTIONS_DESCRIPTOR = builder4.build();
        }

        private AggregatedOnDeviceSubjectSegmentationLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceTextDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCEDURATIONSTATS_DESCRIPTOR;
        static final AggregatedOnDeviceTextDetectionLogEventEncoder INSTANCE = new AggregatedOnDeviceTextDetectionLogEventEncoder();
        private static final FieldDescriptor LOGEVENTKEY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logEventKey");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LOGEVENTKEY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inferenceDurationStats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INFERENCEDURATIONSTATS_DESCRIPTOR = builder3.build();
        }

        private AggregatedOnDeviceTextDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = (AggregatedOnDeviceTextDetectionLogEvent) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(LOGEVENTKEY_DESCRIPTOR, aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey);
            objectEncoderContext.add$ar$ds$6bd70da1_0(EVENTCOUNT_DESCRIPTOR, aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount);
            objectEncoderContext.add$ar$ds$6bd70da1_0(INFERENCEDURATIONSTATS_DESCRIPTOR, aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AggregatedOnDeviceTextDetectionLogEventLogEventKeyEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor HASRESULT_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        static final AggregatedOnDeviceTextDetectionLogEventLogEventKeyEncoder INSTANCE = new AggregatedOnDeviceTextDetectionLogEventLogEventKeyEncoder();
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor RECOGNIZEROPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("hasResult");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            HASRESULT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            IMAGEINFO_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("recognizerOptions");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            RECOGNIZEROPTIONS_DESCRIPTOR = builder5.build();
        }

        private AggregatedOnDeviceTextDetectionLogEventLogEventKeyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            AggregatedOnDeviceTextDetectionLogEvent.LogEventKey logEventKey = (AggregatedOnDeviceTextDetectionLogEvent.LogEventKey) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(ERRORCODE_DESCRIPTOR, logEventKey.errorCode);
            objectEncoderContext.add$ar$ds$6bd70da1_0(HASRESULT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISCOLDCALL_DESCRIPTOR, logEventKey.isColdCall);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGEINFO_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(RECOGNIZEROPTIONS_DESCRIPTOR, logEventKey.recognizerOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AutoMLImageLabelingCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final AutoMLImageLabelingCreateLogEventEncoder INSTANCE = new AutoMLImageLabelingCreateLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private AutoMLImageLabelingCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AutoMLImageLabelingInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor CONFIDENCETHRESHOLD_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final AutoMLImageLabelingInferenceLogEventEncoder INSTANCE = new AutoMLImageLabelingInferenceLogEventEncoder();
        private static final FieldDescriptor MODELINITIALIZATIONMS_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("modelInitializationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            MODELINITIALIZATIONMS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("confidenceThreshold");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            CONFIDENCETHRESHOLD_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            IMAGEINFO_DESCRIPTOR = builder5.build();
        }

        private AutoMLImageLabelingInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AutoMLImageLabelingLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODES_DESCRIPTOR;
        static final AutoMLImageLabelingLoadLogEventEncoder INSTANCE = new AutoMLImageLabelingLoadLogEventEncoder();
        private static final FieldDescriptor LOCALMODELOPTIONS_DESCRIPTOR;
        private static final FieldDescriptor MODELINITIALIZATIONMS_DESCRIPTOR;
        private static final FieldDescriptor REMOTEMODELOPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("remoteModelOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            REMOTEMODELOPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("localModelOptions");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            LOCALMODELOPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCodes");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODES_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("modelInitializationMs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            MODELINITIALIZATIONMS_DESCRIPTOR = builder4.build();
        }

        private AutoMLImageLabelingLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BarcodeDetectionOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR;
        static final BarcodeDetectionOptionalModuleLogEventEncoder INSTANCE = new BarcodeDetectionOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DURATIONMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DETECTOROPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("eventType");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            EVENTTYPE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ERRORCODE_DESCRIPTOR = builder4.build();
        }

        private BarcodeDetectionOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BarcodeInitializationOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor FORMAT_DESCRIPTOR;
        static final BarcodeInitializationOptionsEncoder INSTANCE = new BarcodeInitializationOptionsEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("format");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            FORMAT_DESCRIPTOR = builder.build();
        }

        private BarcodeInitializationOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CameraSourceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ACTUALPREVIEWHEIGHT_DESCRIPTOR;
        private static final FieldDescriptor ACTUALPREVIEWWIDTH_DESCRIPTOR;
        private static final FieldDescriptor CAMERASOURCE_DESCRIPTOR;
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR;
        static final CameraSourceLogEventEncoder INSTANCE = new CameraSourceLogEventEncoder();
        private static final FieldDescriptor REQUESTEDPREVIEWHEIGHT_DESCRIPTOR;
        private static final FieldDescriptor REQUESTEDPREVIEWWIDTH_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("cameraSource");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            CAMERASOURCE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventType");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTTYPE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("requestedPreviewHeight");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            REQUESTEDPREVIEWHEIGHT_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("requestedPreviewWidth");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            REQUESTEDPREVIEWWIDTH_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("actualPreviewHeight");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            ACTUALPREVIEWHEIGHT_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("actualPreviewWidth");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            ACTUALPREVIEWWIDTH_DESCRIPTOR = builder6.build();
        }

        private CameraSourceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudCropHintsDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudCropHintsDetectionLogEventEncoder INSTANCE = new CloudCropHintsDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudCropHintsDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudDetectorCommonOptionsEncoder implements ObjectEncoder {
        static final CloudDetectorCommonOptionsEncoder INSTANCE = new CloudDetectorCommonOptionsEncoder();
        private static final FieldDescriptor MAXRESULTS_DESCRIPTOR;
        private static final FieldDescriptor MODELTYPE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("maxResults");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            MAXRESULTS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("modelType");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            MODELTYPE_DESCRIPTOR = builder2.build();
        }

        private CloudDetectorCommonOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudDocumentTextDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudDocumentTextDetectionLogEventEncoder INSTANCE = new CloudDocumentTextDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudDocumentTextDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudFaceDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudFaceDetectionLogEventEncoder INSTANCE = new CloudFaceDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudFaceDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudImageLabelDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudImageLabelDetectionLogEventEncoder INSTANCE = new CloudImageLabelDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudImageLabelDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudImagePropertiesDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudImagePropertiesDetectionLogEventEncoder INSTANCE = new CloudImagePropertiesDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudImagePropertiesDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudLandmarkDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudLandmarkDetectionLogEventEncoder INSTANCE = new CloudLandmarkDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudLandmarkDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudLogoDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudLogoDetectionLogEventEncoder INSTANCE = new CloudLogoDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudLogoDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudSafeSearchDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudSafeSearchDetectionLogEventEncoder INSTANCE = new CloudSafeSearchDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudSafeSearchDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudTextDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudTextDetectionLogEventEncoder INSTANCE = new CloudTextDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudTextDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloudWebSearchDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final CloudWebSearchDetectionLogEventEncoder INSTANCE = new CloudWebSearchDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private CloudWebSearchDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CodeScannerOptionalModuleEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor CALLINGSOURCE_DESCRIPTOR;
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final CodeScannerOptionalModuleEventEncoder INSTANCE = new CodeScannerOptionalModuleEventEncoder();
        private static final FieldDescriptor SUPPORTEDFORMATS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("callingSource");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            CALLINGSOURCE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("supportedFormats");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            SUPPORTEDFORMATS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DURATIONMS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ERRORCODE_DESCRIPTOR = builder4.build();
        }

        private CodeScannerOptionalModuleEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CodeScannerScanApiEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ALLOWMANUALINPUT_DESCRIPTOR;
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final CodeScannerScanApiEventEncoder INSTANCE = new CodeScannerScanApiEventEncoder();
        private static final FieldDescriptor SUPPORTEDFORMATS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("supportedFormats");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SUPPORTEDFORMATS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DURATIONMS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("allowManualInput");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ALLOWMANUALINPUT_DESCRIPTOR = builder4.build();
        }

        private CodeScannerScanApiEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CustomImageLabelOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final CustomImageLabelOptionalModuleLogEventEncoder INSTANCE = new CustomImageLabelOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTOROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private CustomImageLabelOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CustomModelCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final CustomModelCreateLogEventEncoder INSTANCE = new CustomModelCreateLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private CustomModelCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CustomModelInferenceLogEventDataSpecEncoder implements ObjectEncoder {
        private static final FieldDescriptor DIMS_DESCRIPTOR;
        static final CustomModelInferenceLogEventDataSpecEncoder INSTANCE = new CustomModelInferenceLogEventDataSpecEncoder();
        private static final FieldDescriptor TYPE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("type");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            TYPE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("dims");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DIMS_DESCRIPTOR = builder2.build();
        }

        private CustomModelInferenceLogEventDataSpecEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CustomModelInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor INPUTSFORMATS_DESCRIPTOR;
        static final CustomModelInferenceLogEventEncoder INSTANCE = new CustomModelInferenceLogEventEncoder();
        private static final FieldDescriptor MODELINITIALIZATIONMS_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor OUTPUTFORMATS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inputsFormats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INPUTSFORMATS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("outputFormats");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            OUTPUTFORMATS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("modelInitializationMs");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            MODELINITIALIZATIONMS_DESCRIPTOR = builder5.build();
        }

        private CustomModelInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CustomModelLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODES_DESCRIPTOR;
        static final CustomModelLoadLogEventEncoder INSTANCE = new CustomModelLoadLogEventEncoder();
        private static final FieldDescriptor ISNNAPIENABLED_DESCRIPTOR;
        private static final FieldDescriptor LOCALMODELOPTIONS_DESCRIPTOR;
        private static final FieldDescriptor MODELINITIALIZATIONMS_DESCRIPTOR;
        private static final FieldDescriptor REMOTEMODELOPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("remoteModelOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            REMOTEMODELOPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("localModelOptions");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            LOCALMODELOPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCodes");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODES_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("modelInitializationMs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            MODELINITIALIZATIONMS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("isNnApiEnabled");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            ISNNAPIENABLED_DESCRIPTOR = builder5.build();
        }

        private CustomModelLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DeleteModelLogEventEncoder implements ObjectEncoder {
        static final DeleteModelLogEventEncoder INSTANCE = new DeleteModelLogEventEncoder();
        private static final FieldDescriptor ISSUCCESSFUL_DESCRIPTOR;
        private static final FieldDescriptor MODELNAME_DESCRIPTOR;
        private static final FieldDescriptor MODELTYPE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("modelType");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            MODELTYPE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isSuccessful");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISSUCCESSFUL_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("modelName");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            MODELNAME_DESCRIPTOR = builder3.build();
        }

        private DeleteModelLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DeviceInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor BRAND_DESCRIPTOR;
        private static final FieldDescriptor DEVICE_DESCRIPTOR;
        private static final FieldDescriptor FINGERPRINT_DESCRIPTOR;
        private static final FieldDescriptor HARDWARE_DESCRIPTOR;
        static final DeviceInfoEncoder INSTANCE = new DeviceInfoEncoder();
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR;
        private static final FieldDescriptor MODEL_DESCRIPTOR;
        private static final FieldDescriptor OSBUILD_DESCRIPTOR;
        private static final FieldDescriptor PRODUCT_DESCRIPTOR;
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR;
        private static final FieldDescriptor SOCMETABUILDID_DESCRIPTOR;
        private static final FieldDescriptor SOC_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("sdkVersion");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SDKVERSION_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("osBuild");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OSBUILD_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("brand");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            BRAND_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("device");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DEVICE_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("hardware");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            HARDWARE_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("manufacturer");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            MANUFACTURER_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("model");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            MODEL_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("product");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            PRODUCT_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("soc");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            SOC_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("socMetaBuildId");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 10;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            SOCMETABUILDID_DESCRIPTOR = builder10.build();
            FieldDescriptor.Builder builder11 = new FieldDescriptor.Builder("fingerprint");
            AtProtobuf atProtobuf11 = new AtProtobuf();
            atProtobuf11.tag = 11;
            builder11.withProperty$ar$ds(atProtobuf11.build());
            FINGERPRINT_DESCRIPTOR = builder11.build();
        }

        private DeviceInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DocumentCroppingOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final DocumentCroppingOptionalModuleLogEventEncoder INSTANCE = new DocumentCroppingOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private DocumentCroppingOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DocumentDetectionOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final DocumentDetectionOptionalModuleLogEventEncoder INSTANCE = new DocumentDetectionOptionalModuleLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
        }

        private DocumentDetectionOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DocumentDetectorOptionsEncoder implements ObjectEncoder {
        static final DocumentDetectorOptionsEncoder INSTANCE = new DocumentDetectorOptionsEncoder();
        private static final FieldDescriptor USECASES_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("useCases");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            USECASES_DESCRIPTOR = builder.build();
        }

        private DocumentDetectorOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DocumentEnhancementOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final DocumentEnhancementOptionalModuleLogEventEncoder INSTANCE = new DocumentEnhancementOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private DocumentEnhancementOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DocumentScannerUIModuleSreenEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor APPLIEDTOALLPAGES_DESCRIPTOR;
        private static final FieldDescriptor CALLERAPPID_DESCRIPTOR;
        private static final FieldDescriptor CLEANUPSTROKESIZE_DESCRIPTOR;
        private static final FieldDescriptor ELEMENTNAME_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final DocumentScannerUIModuleSreenEventEncoder INSTANCE = new DocumentScannerUIModuleSreenEventEncoder();
        private static final FieldDescriptor ISAUTOCAPTUREMODE_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor PAGEINDEX_DESCRIPTOR;
        private static final FieldDescriptor SCREENNAME_DESCRIPTOR;
        private static final FieldDescriptor SESSIONID_DESCRIPTOR;
        private static final FieldDescriptor TIMESTAMPMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("screenName");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SCREENNAME_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("sessionId");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            SESSIONID_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("timestampMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            TIMESTAMPMS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            OPTIONS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("elementName");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            ELEMENTNAME_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("isAutoCaptureMode");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            ISAUTOCAPTUREMODE_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("pageIndex");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            PAGEINDEX_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("appliedToAllPages");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            APPLIEDTOALLPAGES_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            ERRORCODE_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("callerAppId");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 10;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            CALLERAPPID_DESCRIPTOR = builder10.build();
            FieldDescriptor.Builder builder11 = new FieldDescriptor.Builder("cleanUpStrokeSize");
            AtProtobuf atProtobuf11 = new AtProtobuf();
            atProtobuf11.tag = 11;
            builder11.withProperty$ar$ds(atProtobuf11.build());
            CLEANUPSTROKESIZE_DESCRIPTOR = builder11.build();
        }

        private DocumentScannerUIModuleSreenEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DocumentScannerUIOptionalModuleSessionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor CALLERAPPID_DESCRIPTOR;
        static final DocumentScannerUIOptionalModuleSessionLogEventEncoder INSTANCE = new DocumentScannerUIOptionalModuleSessionLogEventEncoder();
        private static final FieldDescriptor ISSESSIONCANCELED_DESCRIPTOR;
        private static final FieldDescriptor NUMRETAKES_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor PAGES_DESCRIPTOR;
        private static final FieldDescriptor SESSIONDURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor SESSIONID_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("sessionId");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            SESSIONID_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("sessionDurationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            SESSIONDURATIONMS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("callerAppId");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            CALLERAPPID_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("numRetakes");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            NUMRETAKES_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("pages");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            PAGES_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("isSessionCanceled");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            ISSESSIONCANCELED_DESCRIPTOR = builder7.build();
        }

        private DocumentScannerUIOptionalModuleSessionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DocumentScannerUIOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor DEFAULTCAPTUREMODE_DESCRIPTOR;
        private static final FieldDescriptor ENABLEALLNEWFEATURESBYDEFAULT_DESCRIPTOR;
        private static final FieldDescriptor ENABLEGALLERYIMPORTAUTOTRANSFORM_DESCRIPTOR;
        private static final FieldDescriptor FILTERALLOWED_DESCRIPTOR;
        private static final FieldDescriptor FLASHMODECHANGEALLOWED_DESCRIPTOR;
        private static final FieldDescriptor GALLERYIMPORTALLOWED_DESCRIPTOR;
        private static final FieldDescriptor INITIALIMAGEURICOUNT_DESCRIPTOR;
        static final DocumentScannerUIOptionsEncoder INSTANCE = new DocumentScannerUIOptionsEncoder();
        private static final FieldDescriptor MULTIPAGEALLOWED_DESCRIPTOR;
        private static final FieldDescriptor PAGEEDITLISTENERSET_DESCRIPTOR;
        private static final FieldDescriptor PAGELIMITMAX_DESCRIPTOR;
        private static final FieldDescriptor RESULTFORMATS_DESCRIPTOR;
        private static final FieldDescriptor SHADOWREMOVALALLOWED_DESCRIPTOR;
        private static final FieldDescriptor STAINREMOVALALLOWED_DESCRIPTOR;
        private static final FieldDescriptor TARGETRESOLUTIONHEIGHT_DESCRIPTOR;
        private static final FieldDescriptor TARGETRESOLUTIONWIDTH_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("initialImageUriCount");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INITIALIMAGEURICOUNT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("defaultCaptureMode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DEFAULTCAPTUREMODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("flashModeChangeAllowed");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            FLASHMODECHANGEALLOWED_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("galleryImportAllowed");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            GALLERYIMPORTALLOWED_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("multiPageAllowed");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            MULTIPAGEALLOWED_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("filterAllowed");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            FILTERALLOWED_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("targetResolutionWidth");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            TARGETRESOLUTIONWIDTH_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("targetResolutionHeight");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            TARGETRESOLUTIONHEIGHT_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("resultFormats");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            RESULTFORMATS_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("pageEditListenerSet");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 10;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            PAGEEDITLISTENERSET_DESCRIPTOR = builder10.build();
            FieldDescriptor.Builder builder11 = new FieldDescriptor.Builder("shadowRemovalAllowed");
            AtProtobuf atProtobuf11 = new AtProtobuf();
            atProtobuf11.tag = 11;
            builder11.withProperty$ar$ds(atProtobuf11.build());
            SHADOWREMOVALALLOWED_DESCRIPTOR = builder11.build();
            FieldDescriptor.Builder builder12 = new FieldDescriptor.Builder("stainRemovalAllowed");
            AtProtobuf atProtobuf12 = new AtProtobuf();
            atProtobuf12.tag = 12;
            builder12.withProperty$ar$ds(atProtobuf12.build());
            STAINREMOVALALLOWED_DESCRIPTOR = builder12.build();
            FieldDescriptor.Builder builder13 = new FieldDescriptor.Builder("enableAllNewFeaturesByDefault");
            AtProtobuf atProtobuf13 = new AtProtobuf();
            atProtobuf13.tag = 13;
            builder13.withProperty$ar$ds(atProtobuf13.build());
            ENABLEALLNEWFEATURESBYDEFAULT_DESCRIPTOR = builder13.build();
            FieldDescriptor.Builder builder14 = new FieldDescriptor.Builder("pageLimitMax");
            AtProtobuf atProtobuf14 = new AtProtobuf();
            atProtobuf14.tag = 14;
            builder14.withProperty$ar$ds(atProtobuf14.build());
            PAGELIMITMAX_DESCRIPTOR = builder14.build();
            FieldDescriptor.Builder builder15 = new FieldDescriptor.Builder("enableGalleryImportAutoTransform");
            AtProtobuf atProtobuf15 = new AtProtobuf();
            atProtobuf15.tag = 15;
            builder15.withProperty$ar$ds(atProtobuf15.build());
            ENABLEGALLERYIMPORTAUTOTRANSFORM_DESCRIPTOR = builder15.build();
        }

        private DocumentScannerUIOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DocumentScannerUIPageEncoder implements ObjectEncoder {
        private static final FieldDescriptor ADJUSTEDCORNERS_DESCRIPTOR;
        private static final FieldDescriptor APPLIEDFILTER_DESCRIPTOR;
        private static final FieldDescriptor AUTOCAPTURETRIGGERLATENCYMS_DESCRIPTOR;
        private static final FieldDescriptor GALLERYIMPORTPROCESSINGMS_DESCRIPTOR;
        private static final FieldDescriptor HASLOWCONFIDENCEPROPOSEDCORNERS_DESCRIPTOR;
        private static final FieldDescriptor IMAGEHEIGHT_DESCRIPTOR;
        private static final FieldDescriptor IMAGEWIDTH_DESCRIPTOR;
        static final DocumentScannerUIPageEncoder INSTANCE = new DocumentScannerUIPageEncoder();
        private static final FieldDescriptor ISAUTOCAPTUREMANUALLYTRIGGERED_DESCRIPTOR;
        private static final FieldDescriptor ISROTATED_DESCRIPTOR;
        private static final FieldDescriptor ISSHADOWREMOVED_DESCRIPTOR;
        private static final FieldDescriptor NUMOFAPPLIEDCLEANUPSTROKES_DESCRIPTOR;
        private static final FieldDescriptor NUMOFATTEMPTEDCLEANUPSTROKES_DESCRIPTOR;
        private static final FieldDescriptor PROPOSEDCORNERS_DESCRIPTOR;
        private static final FieldDescriptor SOURCE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("source");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SOURCE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("appliedFilter");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            APPLIEDFILTER_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isAutoCaptureManuallyTriggered");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISAUTOCAPTUREMANUALLYTRIGGERED_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("isRotated");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ISROTATED_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("hasLowConfidenceProposedCorners");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            HASLOWCONFIDENCEPROPOSEDCORNERS_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("autoCaptureTriggerLatencyMs");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            AUTOCAPTURETRIGGERLATENCYMS_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("galleryImportProcessingMs");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            GALLERYIMPORTPROCESSINGMS_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("imageWidth");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            IMAGEWIDTH_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("imageHeight");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            IMAGEHEIGHT_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("proposedCorners");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 10;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            PROPOSEDCORNERS_DESCRIPTOR = builder10.build();
            FieldDescriptor.Builder builder11 = new FieldDescriptor.Builder("adjustedCorners");
            AtProtobuf atProtobuf11 = new AtProtobuf();
            atProtobuf11.tag = 11;
            builder11.withProperty$ar$ds(atProtobuf11.build());
            ADJUSTEDCORNERS_DESCRIPTOR = builder11.build();
            FieldDescriptor.Builder builder12 = new FieldDescriptor.Builder("isShadowRemoved");
            AtProtobuf atProtobuf12 = new AtProtobuf();
            atProtobuf12.tag = 12;
            builder12.withProperty$ar$ds(atProtobuf12.build());
            ISSHADOWREMOVED_DESCRIPTOR = builder12.build();
            FieldDescriptor.Builder builder13 = new FieldDescriptor.Builder("numOfAppliedCleanUpStrokes");
            AtProtobuf atProtobuf13 = new AtProtobuf();
            atProtobuf13.tag = 13;
            builder13.withProperty$ar$ds(atProtobuf13.build());
            NUMOFAPPLIEDCLEANUPSTROKES_DESCRIPTOR = builder13.build();
            FieldDescriptor.Builder builder14 = new FieldDescriptor.Builder("numOfAttemptedCleanUpStrokes");
            AtProtobuf atProtobuf14 = new AtProtobuf();
            atProtobuf14.tag = 14;
            builder14.withProperty$ar$ds(atProtobuf14.build());
            NUMOFATTEMPTEDCLEANUPSTROKES_DESCRIPTOR = builder14.build();
        }

        private DocumentScannerUIPageEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DurationStatsEncoder implements ObjectEncoder {
        private static final FieldDescriptor AVGMS_DESCRIPTOR;
        private static final FieldDescriptor FIRSTQUARTILEMS_DESCRIPTOR;
        static final DurationStatsEncoder INSTANCE = new DurationStatsEncoder();
        private static final FieldDescriptor MAXMS_DESCRIPTOR;
        private static final FieldDescriptor MEDIANMS_DESCRIPTOR;
        private static final FieldDescriptor MINMS_DESCRIPTOR;
        private static final FieldDescriptor THIRDQUARTILEMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("maxMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            MAXMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("minMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            MINMS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("avgMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            AVGMS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("firstQuartileMs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            FIRSTQUARTILEMS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("medianMs");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            MEDIANMS_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("thirdQuartileMs");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            THIRDQUARTILEMS_DESCRIPTOR = builder6.build();
        }

        private DurationStatsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            DurationStats durationStats = (DurationStats) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(MAXMS_DESCRIPTOR, durationStats.DurationStats$ar$maxMs);
            objectEncoderContext.add$ar$ds$6bd70da1_0(MINMS_DESCRIPTOR, durationStats.DurationStats$ar$minMs);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AVGMS_DESCRIPTOR, durationStats.DurationStats$ar$avgMs);
            objectEncoderContext.add$ar$ds$6bd70da1_0(FIRSTQUARTILEMS_DESCRIPTOR, durationStats.DurationStats$ar$firstQuartileMs);
            objectEncoderContext.add$ar$ds$6bd70da1_0(MEDIANMS_DESCRIPTOR, durationStats.DurationStats$ar$medianMs);
            objectEncoderContext.add$ar$ds$6bd70da1_0(THIRDQUARTILEMS_DESCRIPTOR, durationStats.DurationStats$ar$thirdQuartileMs);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FaceDetectionOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR;
        static final FaceDetectionOptionalModuleLogEventEncoder INSTANCE = new FaceDetectionOptionalModuleLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventType");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTTYPE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODE_DESCRIPTOR = builder3.build();
        }

        private FaceDetectionOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FaceDetectorOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CLASSIFICATIONMODE_DESCRIPTOR;
        private static final FieldDescriptor CONTOURMODE_DESCRIPTOR;
        static final FaceDetectorOptionsEncoder INSTANCE = new FaceDetectorOptionsEncoder();
        private static final FieldDescriptor ISTRACKINGENABLED_DESCRIPTOR;
        private static final FieldDescriptor LANDMARKMODE_DESCRIPTOR;
        private static final FieldDescriptor MINFACESIZE_DESCRIPTOR;
        private static final FieldDescriptor PERFORMANCEMODE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("landmarkMode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LANDMARKMODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("classificationMode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            CLASSIFICATIONMODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("performanceMode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            PERFORMANCEMODE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("contourMode");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            CONTOURMODE_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("isTrackingEnabled");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            ISTRACKINGENABLED_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("minFaceSize");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            MINFACESIZE_DESCRIPTOR = builder6.build();
        }

        private FaceDetectorOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FaceInitializationOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CLASSIFICATION_DESCRIPTOR;
        static final FaceInitializationOptionsEncoder INSTANCE = new FaceInitializationOptionsEncoder();
        private static final FieldDescriptor LANDMARK_DESCRIPTOR;
        private static final FieldDescriptor MINFACESIZE_DESCRIPTOR;
        private static final FieldDescriptor MODE_DESCRIPTOR;
        private static final FieldDescriptor PROMINENTFACEONLY_DESCRIPTOR;
        private static final FieldDescriptor TRACKING_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("mode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            MODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("landmark");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            LANDMARK_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("classification");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            CLASSIFICATION_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("prominentFaceOnly");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PROMINENTFACEONLY_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("tracking");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            TRACKING_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("minFaceSize");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            MINFACESIZE_DESCRIPTOR = builder6.build();
        }

        private FaceInitializationOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GPUInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final GPUInfoEncoder INSTANCE = new GPUInfoEncoder();
        private static final FieldDescriptor MAXIMAGES_DESCRIPTOR;
        private static final FieldDescriptor MAXSSBO_DESCRIPTOR;
        private static final FieldDescriptor RENDERER_DESCRIPTOR;
        private static final FieldDescriptor VENDOR_DESCRIPTOR;
        private static final FieldDescriptor VERSION_DESCRIPTOR;
        private static final FieldDescriptor WORKGROUPSIZES_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("renderer");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            RENDERER_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("vendor");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            VENDOR_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("version");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            VERSION_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("maxImages");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            MAXIMAGES_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("maxSsbo");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            MAXSSBO_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("workGroupSizes");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            WORKGROUPSIZES_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            ERRORCODE_DESCRIPTOR = builder7.build();
        }

        private GPUInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImageCaptioningOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final ImageCaptioningOptionalModuleLogEventEncoder INSTANCE = new ImageCaptioningOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private ImageCaptioningOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImageInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor COMPRESSEDIMAGESIZE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEFORMAT_DESCRIPTOR;
        static final ImageInfoEncoder INSTANCE = new ImageInfoEncoder();
        private static final FieldDescriptor ISODMLIMAGE_DESCRIPTOR;
        private static final FieldDescriptor ORIGINALIMAGESIZE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("imageFormat");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            IMAGEFORMAT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("originalImageSize");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ORIGINALIMAGESIZE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("compressedImageSize");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            COMPRESSEDIMAGESIZE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("isOdmlImage");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ISODMLIMAGE_DESCRIPTOR = builder4.build();
        }

        private ImageInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            ImageInfo imageInfo = (ImageInfo) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGEFORMAT_DESCRIPTOR, imageInfo.imageFormat);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ORIGINALIMAGESIZE_DESCRIPTOR, imageInfo.originalImageSize);
            objectEncoderContext.add$ar$ds$6bd70da1_0(COMPRESSEDIMAGESIZE_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISODMLIMAGE_DESCRIPTOR, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImageLabelOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR;
        static final ImageLabelOptionalModuleLogEventEncoder INSTANCE = new ImageLabelOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTOROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventType");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTTYPE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODE_DESCRIPTOR = builder3.build();
        }

        private ImageLabelOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImageQualityAnalysisOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final ImageQualityAnalysisOptionalModuleLogEventEncoder INSTANCE = new ImageQualityAnalysisOptionalModuleLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private ImageQualityAnalysisOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ImageQualityScoreEncoder implements ObjectEncoder {
        static final ImageQualityScoreEncoder INSTANCE = new ImageQualityScoreEncoder();
        private static final FieldDescriptor SCORETYPE_DESCRIPTOR;
        private static final FieldDescriptor SCORE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("scoreType");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SCORETYPE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("score");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            SCORE_DESCRIPTOR = builder2.build();
        }

        private ImageQualityScoreEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InferenceCommonLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor AUTOMANAGEMODELONBACKGROUND_DESCRIPTOR;
        private static final FieldDescriptor AUTOMANAGEMODELONLOWMEMORY_DESCRIPTOR;
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor EVENTSCOUNT_DESCRIPTOR;
        static final InferenceCommonLogEventEncoder INSTANCE = new InferenceCommonLogEventEncoder();
        private static final FieldDescriptor ISACCELERATED_DESCRIPTOR;
        private static final FieldDescriptor ISCOLDCALL_DESCRIPTOR;
        private static final FieldDescriptor ISNNAPIENABLED_DESCRIPTOR;
        private static final FieldDescriptor OTHERERRORS_DESCRIPTOR;
        private static final FieldDescriptor REMOTECONFIGVALUEFORACCELERATION_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DURATIONMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isColdCall");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISCOLDCALL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("autoManageModelOnBackground");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            AUTOMANAGEMODELONBACKGROUND_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("autoManageModelOnLowMemory");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            AUTOMANAGEMODELONLOWMEMORY_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("isNnApiEnabled");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            ISNNAPIENABLED_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("eventsCount");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            EVENTSCOUNT_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("otherErrors");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            OTHERERRORS_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("remoteConfigValueForAcceleration");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            REMOTECONFIGVALUEFORACCELERATION_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("isAccelerated");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 10;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            ISACCELERATED_DESCRIPTOR = builder10.build();
        }

        private InferenceCommonLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            InferenceCommonLogEvent inferenceCommonLogEvent = (InferenceCommonLogEvent) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(DURATIONMS_DESCRIPTOR, inferenceCommonLogEvent.durationMs);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ERRORCODE_DESCRIPTOR, inferenceCommonLogEvent.errorCode);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISCOLDCALL_DESCRIPTOR, inferenceCommonLogEvent.isColdCall);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AUTOMANAGEMODELONBACKGROUND_DESCRIPTOR, inferenceCommonLogEvent.autoManageModelOnBackground);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AUTOMANAGEMODELONLOWMEMORY_DESCRIPTOR, inferenceCommonLogEvent.autoManageModelOnLowMemory);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISNNAPIENABLED_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(EVENTSCOUNT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(OTHERERRORS_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(REMOTECONFIGVALUEFORACCELERATION_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISACCELERATED_DESCRIPTOR, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InferenceErrorEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final InferenceErrorEncoder INSTANCE = new InferenceErrorEncoder();
        private static final FieldDescriptor SOURCE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("source");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SOURCE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private InferenceErrorEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InputImageConstructionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor IMAGEBYTESIZE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEFORMAT_DESCRIPTOR;
        private static final FieldDescriptor IMAGEHEIGHT_DESCRIPTOR;
        private static final FieldDescriptor IMAGESOURCE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEWIDTH_DESCRIPTOR;
        static final InputImageConstructionLogEventEncoder INSTANCE = new InputImageConstructionLogEventEncoder();
        private static final FieldDescriptor ROTATIONDEGREES_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DURATIONMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageSource");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGESOURCE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageFormat");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEFORMAT_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("imageByteSize");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            IMAGEBYTESIZE_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("imageWidth");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            IMAGEWIDTH_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("imageHeight");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            IMAGEHEIGHT_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("rotationDegrees");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            ROTATIONDEGREES_DESCRIPTOR = builder7.build();
        }

        private InputImageConstructionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            InputImageConstructionLogEvent inputImageConstructionLogEvent = (InputImageConstructionLogEvent) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(DURATIONMS_DESCRIPTOR, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$durationMs);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGESOURCE_DESCRIPTOR, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageSource);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGEFORMAT_DESCRIPTOR, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageFormat);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGEBYTESIZE_DESCRIPTOR, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageByteSize);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGEWIDTH_DESCRIPTOR, inputImageConstructionLogEvent.imageWidth);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGEHEIGHT_DESCRIPTOR, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageHeight);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ROTATIONDEGREES_DESCRIPTOR, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$rotationDegrees);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IsModelDownloadedLogEventEncoder implements ObjectEncoder {
        static final IsModelDownloadedLogEventEncoder INSTANCE = new IsModelDownloadedLogEventEncoder();
        private static final FieldDescriptor ISDOWNLOADED_DESCRIPTOR;
        private static final FieldDescriptor MODELNAME_DESCRIPTOR;
        private static final FieldDescriptor MODELTYPE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("modelType");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            MODELTYPE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isDownloaded");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISDOWNLOADED_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("modelName");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            MODELNAME_DESCRIPTOR = builder3.build();
        }

        private IsModelDownloadedLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LanguageIdentificationOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR;
        static final LanguageIdentificationOptionalModuleLogEventEncoder INSTANCE = new LanguageIdentificationOptionalModuleLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventType");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTTYPE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODE_DESCRIPTOR = builder3.build();
        }

        private LanguageIdentificationOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LanguageIdentificationOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CONFIDENCETHRESHOLD_DESCRIPTOR;
        private static final FieldDescriptor IDENTIFYALLLANGUAGESCONFIDENCETHRESHOLD_DESCRIPTOR;
        private static final FieldDescriptor IDENTIFYLANGUAGECONFIDENCETHRESHOLD_DESCRIPTOR;
        static final LanguageIdentificationOptionsEncoder INSTANCE = new LanguageIdentificationOptionsEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("identifyLanguageConfidenceThreshold");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            IDENTIFYLANGUAGECONFIDENCETHRESHOLD_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("identifyAllLanguagesConfidenceThreshold");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IDENTIFYALLLANGUAGESCONFIDENCETHRESHOLD_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("confidenceThreshold");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            CONFIDENCETHRESHOLD_DESCRIPTOR = builder3.build();
        }

        private LanguageIdentificationOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LeakedHandleEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor API_DESCRIPTOR;
        static final LeakedHandleEventEncoder INSTANCE = new LeakedHandleEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("api");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            API_DESCRIPTOR = builder.build();
        }

        private LeakedHandleEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LowLightAutoExposureComputationEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor BURSTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor ESTIMATEDCAPTURELATENCYMS_DESCRIPTOR;
        static final LowLightAutoExposureComputationEventEncoder INSTANCE = new LowLightAutoExposureComputationEventEncoder();
        private static final FieldDescriptor LATENCYMS_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("latencyMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            LATENCYMS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("burstCount");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            BURSTCOUNT_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("estimatedCaptureLatencyMs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ESTIMATEDCAPTURELATENCYMS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            ERRORCODE_DESCRIPTOR = builder5.build();
        }

        private LowLightAutoExposureComputationEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LowLightFrameProcessEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor FRAMECOUNT_DESCRIPTOR;
        static final LowLightFrameProcessEventEncoder INSTANCE = new LowLightFrameProcessEventEncoder();
        private static final FieldDescriptor LATENCYMS_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("latencyMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            LATENCYMS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("frameCount");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            FRAMECOUNT_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ERRORCODE_DESCRIPTOR = builder4.build();
        }

        private LowLightFrameProcessEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LowLightImageCaptureOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CAMERAID_DESCRIPTOR;
        private static final FieldDescriptor DEVICEMODEL_DESCRIPTOR;
        private static final FieldDescriptor ENABLEMLDENOISER_DESCRIPTOR;
        static final LowLightImageCaptureOptionsEncoder INSTANCE = new LowLightImageCaptureOptionsEncoder();
        private static final FieldDescriptor PHYSICALCAMERAIDS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("cameraId");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            CAMERAID_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("physicalCameraIds");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            PHYSICALCAMERAIDS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("deviceModel");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DEVICEMODEL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("enableMlDenoiser");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ENABLEMLDENOISER_DESCRIPTOR = builder4.build();
        }

        private LowLightImageCaptureOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LowLightSceneDetectionEventEncoder implements ObjectEncoder {
        static final LowLightSceneDetectionEventEncoder INSTANCE = new LowLightSceneDetectionEventEncoder();
        private static final FieldDescriptor LATENCYMS_DESCRIPTOR;
        private static final FieldDescriptor LOGSCENEBRIGHTNESS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("latencyMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LATENCYMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("logSceneBrightness");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            LOGSCENEBRIGHTNESS_DESCRIPTOR = builder2.build();
        }

        private LowLightSceneDetectionEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MLKitSdkLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ACCELERATIONALLOWLISTLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDAUTOMLIMAGELABELINGINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDCUSTOMMODELINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEBARCODEDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEDOCUMENTCROPPINGLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEDOCUMENTDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEDOCUMENTENHANCEMENTLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEEXPLICITCONTENTLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEFACEDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEFACEMESHLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEIMAGECAPTIONINGINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEIMAGEQUALITYANALYSISLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEOBJECTINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICEPOSEDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICESEGMENTATIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICESHADOWREMOVALLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICESTAINREMOVALLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICESUBJECTSEGMENTATIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AGGREGATEDONDEVICETEXTDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AUTOMLIMAGELABELINGCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AUTOMLIMAGELABELINGINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor AUTOMLIMAGELABELINGLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor BARCODEDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CAMERASOURCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLIENTTYPE_DESCRIPTOR;
        private static final FieldDescriptor CLOUDCROPHINTDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDDOCUMENTTEXTDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDFACEDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDIMAGEPROPERTIESDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDLANDMARKDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDLOGODETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDSAFESEARCHDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDTEXTDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CLOUDWEBSEARCHDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CODESCANNEROPTIONALMODULEEVENT_DESCRIPTOR;
        private static final FieldDescriptor CODESCANNERSCANAPIEVENT_DESCRIPTOR;
        private static final FieldDescriptor CUSTOMIMAGELABELOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CUSTOMMODELCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CUSTOMMODELINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor CUSTOMMODELLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor DELETEMODELLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTCROPPINGOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTENHANCEMENTOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTSCANNERUIMODULESCREENCLICKEVENT_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTSCANNERUIMODULESCREENERROREVENT_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTSCANNERUIMODULESCREENVIEWEVENT_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTSCANNERUIOPTIONALMODULESESSIONFINISHLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTSCANNERUIOPTIONALMODULESESSIONSTARTLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor EVENTNAME_DESCRIPTOR;
        private static final FieldDescriptor FACEDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor IMAGECAPTIONINGOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor IMAGELABELOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor IMAGEQUALITYANALYSISOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor INPUTIMAGECONSTRUCTIONLOGEVENT_DESCRIPTOR;
        static final MLKitSdkLogEventEncoder INSTANCE = new MLKitSdkLogEventEncoder();
        private static final FieldDescriptor ISMODELDOWNLOADEDLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ISTHICKCLIENT_DESCRIPTOR;
        private static final FieldDescriptor LANGUAGEIDENTIFICATIONOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor LEAKEDHANDLEEVENT_DESCRIPTOR;
        private static final FieldDescriptor LOWLIGHTAUTOEXPOSURECOMPUTATIONEVENT_DESCRIPTOR;
        private static final FieldDescriptor LOWLIGHTFRAMEPROCESSEVENT_DESCRIPTOR;
        private static final FieldDescriptor LOWLIGHTSCENEDETECTIONEVENT_DESCRIPTOR;
        private static final FieldDescriptor MODELDOWNLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor NLCLASSIFIERCLIENTLIBRARYLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor NLCLASSIFIEROPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEBARCODEDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEBARCODELOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDIGITALINKSEGMENTATIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTCROPPINGCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTCROPPINGLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTCROPPINGLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTDETECTIONCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTDETECTIONLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTENHANCEMENTCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTENHANCEMENTLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTENHANCEMENTLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTSCANNERFINISHLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTSCANNERSTARTLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTSCANNERUICREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTSCANNERUIFINISHLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEDOCUMENTSCANNERUISTARTLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEEXPLICITCONTENTCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEEXPLICITCONTENTINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEEXPLICITCONTENTLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEFACEDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEFACELOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEFACEMESHCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEFACEMESHLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEFACEMESHLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGECAPTIONINGCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGECAPTIONINGINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGECAPTIONINGLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGELABELCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGELABELLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGEQUALITYANALYSISCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGEQUALITYANALYSISLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEIMAGEQUALITYANALYSISLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICELANGUAGEIDENTIFICATIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEOBJECTCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEOBJECTINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEOBJECTLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICEPOSEDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICESEGMENTATIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICESHADOWREMOVALLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICESMARTREPLYLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICESTAINREMOVALLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICESUBJECTSEGMENTATIONCREATELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICESUBJECTSEGMENTATIONINFERENCELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICESUBJECTSEGMENTATIONLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICETEXTDETECTIONLOADLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICETEXTDETECTIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor ONDEVICETRANSLATIONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor PIPELINEACCELERATIONINFERENCEEVENTS_DESCRIPTOR;
        private static final FieldDescriptor REMOTECONFIGLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor SCANNERAUTOZOOMEVENT_DESCRIPTOR;
        private static final FieldDescriptor SHADOWREMOVALOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor SMARTREPLYOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor STAINREMOVALOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor SUBJECTSEGMENTATIONOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor SYSTEMINFO_DESCRIPTOR;
        private static final FieldDescriptor TEXTDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor TOXICITYDETECTIONCREATEEVENT_DESCRIPTOR;
        private static final FieldDescriptor TOXICITYDETECTIONINFERENCEEVENT_DESCRIPTOR;
        private static final FieldDescriptor TOXICITYDETECTIONLOADEVENT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("systemInfo");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SYSTEMINFO_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("eventName");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            EVENTNAME_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isThickClient");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 37;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISTHICKCLIENT_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("clientType");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 61;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            CLIENTTYPE_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("modelDownloadLogEvent");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 3;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            MODELDOWNLOADLOGEVENT_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("customModelLoadLogEvent");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 20;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            CUSTOMMODELLOADLOGEVENT_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("customModelInferenceLogEvent");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 4;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            CUSTOMMODELINFERENCELOGEVENT_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("customModelCreateLogEvent");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 29;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            CUSTOMMODELCREATELOGEVENT_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("onDeviceFaceDetectionLogEvent");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 5;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            ONDEVICEFACEDETECTIONLOGEVENT_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("onDeviceFaceLoadLogEvent");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 59;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            ONDEVICEFACELOADLOGEVENT_DESCRIPTOR = builder10.build();
            FieldDescriptor.Builder builder11 = new FieldDescriptor.Builder("onDeviceTextDetectionLogEvent");
            AtProtobuf atProtobuf11 = new AtProtobuf();
            atProtobuf11.tag = 6;
            builder11.withProperty$ar$ds(atProtobuf11.build());
            ONDEVICETEXTDETECTIONLOGEVENT_DESCRIPTOR = builder11.build();
            FieldDescriptor.Builder builder12 = new FieldDescriptor.Builder("onDeviceTextDetectionLoadLogEvent");
            AtProtobuf atProtobuf12 = new AtProtobuf();
            atProtobuf12.tag = 79;
            builder12.withProperty$ar$ds(atProtobuf12.build());
            ONDEVICETEXTDETECTIONLOADLOGEVENT_DESCRIPTOR = builder12.build();
            FieldDescriptor.Builder builder13 = new FieldDescriptor.Builder("onDeviceBarcodeDetectionLogEvent");
            AtProtobuf atProtobuf13 = new AtProtobuf();
            atProtobuf13.tag = 7;
            builder13.withProperty$ar$ds(atProtobuf13.build());
            ONDEVICEBARCODEDETECTIONLOGEVENT_DESCRIPTOR = builder13.build();
            FieldDescriptor.Builder builder14 = new FieldDescriptor.Builder("onDeviceBarcodeLoadLogEvent");
            AtProtobuf atProtobuf14 = new AtProtobuf();
            atProtobuf14.tag = 58;
            builder14.withProperty$ar$ds(atProtobuf14.build());
            ONDEVICEBARCODELOADLOGEVENT_DESCRIPTOR = builder14.build();
            FieldDescriptor.Builder builder15 = new FieldDescriptor.Builder("onDeviceImageLabelCreateLogEvent");
            AtProtobuf atProtobuf15 = new AtProtobuf();
            atProtobuf15.tag = 48;
            builder15.withProperty$ar$ds(atProtobuf15.build());
            ONDEVICEIMAGELABELCREATELOGEVENT_DESCRIPTOR = builder15.build();
            FieldDescriptor.Builder builder16 = new FieldDescriptor.Builder("onDeviceImageLabelLoadLogEvent");
            AtProtobuf atProtobuf16 = new AtProtobuf();
            atProtobuf16.tag = 49;
            builder16.withProperty$ar$ds(atProtobuf16.build());
            ONDEVICEIMAGELABELLOADLOGEVENT_DESCRIPTOR = builder16.build();
            FieldDescriptor.Builder builder17 = new FieldDescriptor.Builder("onDeviceImageLabelDetectionLogEvent");
            AtProtobuf atProtobuf17 = new AtProtobuf();
            atProtobuf17.tag = 18;
            builder17.withProperty$ar$ds(atProtobuf17.build());
            ONDEVICEIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR = builder17.build();
            FieldDescriptor.Builder builder18 = new FieldDescriptor.Builder("onDeviceObjectCreateLogEvent");
            AtProtobuf atProtobuf18 = new AtProtobuf();
            atProtobuf18.tag = 26;
            builder18.withProperty$ar$ds(atProtobuf18.build());
            ONDEVICEOBJECTCREATELOGEVENT_DESCRIPTOR = builder18.build();
            FieldDescriptor.Builder builder19 = new FieldDescriptor.Builder("onDeviceObjectLoadLogEvent");
            AtProtobuf atProtobuf19 = new AtProtobuf();
            atProtobuf19.tag = 27;
            builder19.withProperty$ar$ds(atProtobuf19.build());
            ONDEVICEOBJECTLOADLOGEVENT_DESCRIPTOR = builder19.build();
            FieldDescriptor.Builder builder20 = new FieldDescriptor.Builder("onDeviceObjectInferenceLogEvent");
            AtProtobuf atProtobuf20 = new AtProtobuf();
            atProtobuf20.tag = 28;
            builder20.withProperty$ar$ds(atProtobuf20.build());
            ONDEVICEOBJECTINFERENCELOGEVENT_DESCRIPTOR = builder20.build();
            FieldDescriptor.Builder builder21 = new FieldDescriptor.Builder("onDevicePoseDetectionLogEvent");
            AtProtobuf atProtobuf21 = new AtProtobuf();
            atProtobuf21.tag = 44;
            builder21.withProperty$ar$ds(atProtobuf21.build());
            ONDEVICEPOSEDETECTIONLOGEVENT_DESCRIPTOR = builder21.build();
            FieldDescriptor.Builder builder22 = new FieldDescriptor.Builder("onDeviceSegmentationLogEvent");
            AtProtobuf atProtobuf22 = new AtProtobuf();
            atProtobuf22.tag = 45;
            builder22.withProperty$ar$ds(atProtobuf22.build());
            ONDEVICESEGMENTATIONLOGEVENT_DESCRIPTOR = builder22.build();
            FieldDescriptor.Builder builder23 = new FieldDescriptor.Builder("onDeviceSmartReplyLogEvent");
            AtProtobuf atProtobuf23 = new AtProtobuf();
            atProtobuf23.tag = 19;
            builder23.withProperty$ar$ds(atProtobuf23.build());
            ONDEVICESMARTREPLYLOGEVENT_DESCRIPTOR = builder23.build();
            FieldDescriptor.Builder builder24 = new FieldDescriptor.Builder("onDeviceLanguageIdentificationLogEvent");
            AtProtobuf atProtobuf24 = new AtProtobuf();
            atProtobuf24.tag = 21;
            builder24.withProperty$ar$ds(atProtobuf24.build());
            ONDEVICELANGUAGEIDENTIFICATIONLOGEVENT_DESCRIPTOR = builder24.build();
            FieldDescriptor.Builder builder25 = new FieldDescriptor.Builder("onDeviceTranslationLogEvent");
            AtProtobuf atProtobuf25 = new AtProtobuf();
            atProtobuf25.tag = 22;
            builder25.withProperty$ar$ds(atProtobuf25.build());
            ONDEVICETRANSLATIONLOGEVENT_DESCRIPTOR = builder25.build();
            FieldDescriptor.Builder builder26 = new FieldDescriptor.Builder("cloudFaceDetectionLogEvent");
            AtProtobuf atProtobuf26 = new AtProtobuf();
            atProtobuf26.tag = 8;
            builder26.withProperty$ar$ds(atProtobuf26.build());
            CLOUDFACEDETECTIONLOGEVENT_DESCRIPTOR = builder26.build();
            FieldDescriptor.Builder builder27 = new FieldDescriptor.Builder("cloudCropHintDetectionLogEvent");
            AtProtobuf atProtobuf27 = new AtProtobuf();
            atProtobuf27.tag = 9;
            builder27.withProperty$ar$ds(atProtobuf27.build());
            CLOUDCROPHINTDETECTIONLOGEVENT_DESCRIPTOR = builder27.build();
            FieldDescriptor.Builder builder28 = new FieldDescriptor.Builder("cloudDocumentTextDetectionLogEvent");
            AtProtobuf atProtobuf28 = new AtProtobuf();
            atProtobuf28.tag = 10;
            builder28.withProperty$ar$ds(atProtobuf28.build());
            CLOUDDOCUMENTTEXTDETECTIONLOGEVENT_DESCRIPTOR = builder28.build();
            FieldDescriptor.Builder builder29 = new FieldDescriptor.Builder("cloudImagePropertiesDetectionLogEvent");
            AtProtobuf atProtobuf29 = new AtProtobuf();
            atProtobuf29.tag = 11;
            builder29.withProperty$ar$ds(atProtobuf29.build());
            CLOUDIMAGEPROPERTIESDETECTIONLOGEVENT_DESCRIPTOR = builder29.build();
            FieldDescriptor.Builder builder30 = new FieldDescriptor.Builder("cloudImageLabelDetectionLogEvent");
            AtProtobuf atProtobuf30 = new AtProtobuf();
            atProtobuf30.tag = 12;
            builder30.withProperty$ar$ds(atProtobuf30.build());
            CLOUDIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR = builder30.build();
            FieldDescriptor.Builder builder31 = new FieldDescriptor.Builder("cloudLandmarkDetectionLogEvent");
            AtProtobuf atProtobuf31 = new AtProtobuf();
            atProtobuf31.tag = 13;
            builder31.withProperty$ar$ds(atProtobuf31.build());
            CLOUDLANDMARKDETECTIONLOGEVENT_DESCRIPTOR = builder31.build();
            FieldDescriptor.Builder builder32 = new FieldDescriptor.Builder("cloudLogoDetectionLogEvent");
            AtProtobuf atProtobuf32 = new AtProtobuf();
            atProtobuf32.tag = 14;
            builder32.withProperty$ar$ds(atProtobuf32.build());
            CLOUDLOGODETECTIONLOGEVENT_DESCRIPTOR = builder32.build();
            FieldDescriptor.Builder builder33 = new FieldDescriptor.Builder("cloudSafeSearchDetectionLogEvent");
            AtProtobuf atProtobuf33 = new AtProtobuf();
            atProtobuf33.tag = 15;
            builder33.withProperty$ar$ds(atProtobuf33.build());
            CLOUDSAFESEARCHDETECTIONLOGEVENT_DESCRIPTOR = builder33.build();
            FieldDescriptor.Builder builder34 = new FieldDescriptor.Builder("cloudTextDetectionLogEvent");
            AtProtobuf atProtobuf34 = new AtProtobuf();
            atProtobuf34.tag = 16;
            builder34.withProperty$ar$ds(atProtobuf34.build());
            CLOUDTEXTDETECTIONLOGEVENT_DESCRIPTOR = builder34.build();
            FieldDescriptor.Builder builder35 = new FieldDescriptor.Builder("cloudWebSearchDetectionLogEvent");
            AtProtobuf atProtobuf35 = new AtProtobuf();
            atProtobuf35.tag = 17;
            builder35.withProperty$ar$ds(atProtobuf35.build());
            CLOUDWEBSEARCHDETECTIONLOGEVENT_DESCRIPTOR = builder35.build();
            FieldDescriptor.Builder builder36 = new FieldDescriptor.Builder("automlImageLabelingCreateLogEvent");
            AtProtobuf atProtobuf36 = new AtProtobuf();
            atProtobuf36.tag = 23;
            builder36.withProperty$ar$ds(atProtobuf36.build());
            AUTOMLIMAGELABELINGCREATELOGEVENT_DESCRIPTOR = builder36.build();
            FieldDescriptor.Builder builder37 = new FieldDescriptor.Builder("automlImageLabelingLoadLogEvent");
            AtProtobuf atProtobuf37 = new AtProtobuf();
            atProtobuf37.tag = 24;
            builder37.withProperty$ar$ds(atProtobuf37.build());
            AUTOMLIMAGELABELINGLOADLOGEVENT_DESCRIPTOR = builder37.build();
            FieldDescriptor.Builder builder38 = new FieldDescriptor.Builder("automlImageLabelingInferenceLogEvent");
            AtProtobuf atProtobuf38 = new AtProtobuf();
            atProtobuf38.tag = 25;
            builder38.withProperty$ar$ds(atProtobuf38.build());
            AUTOMLIMAGELABELINGINFERENCELOGEVENT_DESCRIPTOR = builder38.build();
            FieldDescriptor.Builder builder39 = new FieldDescriptor.Builder("isModelDownloadedLogEvent");
            AtProtobuf atProtobuf39 = new AtProtobuf();
            atProtobuf39.tag = 39;
            builder39.withProperty$ar$ds(atProtobuf39.build());
            ISMODELDOWNLOADEDLOGEVENT_DESCRIPTOR = builder39.build();
            FieldDescriptor.Builder builder40 = new FieldDescriptor.Builder("deleteModelLogEvent");
            AtProtobuf atProtobuf40 = new AtProtobuf();
            atProtobuf40.tag = 40;
            builder40.withProperty$ar$ds(atProtobuf40.build());
            DELETEMODELLOGEVENT_DESCRIPTOR = builder40.build();
            FieldDescriptor.Builder builder41 = new FieldDescriptor.Builder("aggregatedAutomlImageLabelingInferenceLogEvent");
            AtProtobuf atProtobuf41 = new AtProtobuf();
            atProtobuf41.tag = 30;
            builder41.withProperty$ar$ds(atProtobuf41.build());
            AGGREGATEDAUTOMLIMAGELABELINGINFERENCELOGEVENT_DESCRIPTOR = builder41.build();
            FieldDescriptor.Builder builder42 = new FieldDescriptor.Builder("aggregatedCustomModelInferenceLogEvent");
            AtProtobuf atProtobuf42 = new AtProtobuf();
            atProtobuf42.tag = 31;
            builder42.withProperty$ar$ds(atProtobuf42.build());
            AGGREGATEDCUSTOMMODELINFERENCELOGEVENT_DESCRIPTOR = builder42.build();
            FieldDescriptor.Builder builder43 = new FieldDescriptor.Builder("aggregatedOnDeviceFaceDetectionLogEvent");
            AtProtobuf atProtobuf43 = new AtProtobuf();
            atProtobuf43.tag = 32;
            builder43.withProperty$ar$ds(atProtobuf43.build());
            AGGREGATEDONDEVICEFACEDETECTIONLOGEVENT_DESCRIPTOR = builder43.build();
            FieldDescriptor.Builder builder44 = new FieldDescriptor.Builder("aggregatedOnDeviceBarcodeDetectionLogEvent");
            AtProtobuf atProtobuf44 = new AtProtobuf();
            atProtobuf44.tag = 33;
            builder44.withProperty$ar$ds(atProtobuf44.build());
            AGGREGATEDONDEVICEBARCODEDETECTIONLOGEVENT_DESCRIPTOR = builder44.build();
            FieldDescriptor.Builder builder45 = new FieldDescriptor.Builder("aggregatedOnDeviceImageLabelDetectionLogEvent");
            AtProtobuf atProtobuf45 = new AtProtobuf();
            atProtobuf45.tag = 34;
            builder45.withProperty$ar$ds(atProtobuf45.build());
            AGGREGATEDONDEVICEIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR = builder45.build();
            FieldDescriptor.Builder builder46 = new FieldDescriptor.Builder("aggregatedOnDeviceObjectInferenceLogEvent");
            AtProtobuf atProtobuf46 = new AtProtobuf();
            atProtobuf46.tag = 35;
            builder46.withProperty$ar$ds(atProtobuf46.build());
            AGGREGATEDONDEVICEOBJECTINFERENCELOGEVENT_DESCRIPTOR = builder46.build();
            FieldDescriptor.Builder builder47 = new FieldDescriptor.Builder("aggregatedOnDeviceTextDetectionLogEvent");
            AtProtobuf atProtobuf47 = new AtProtobuf();
            atProtobuf47.tag = 36;
            builder47.withProperty$ar$ds(atProtobuf47.build());
            AGGREGATEDONDEVICETEXTDETECTIONLOGEVENT_DESCRIPTOR = builder47.build();
            FieldDescriptor.Builder builder48 = new FieldDescriptor.Builder("aggregatedOnDevicePoseDetectionLogEvent");
            AtProtobuf atProtobuf48 = new AtProtobuf();
            atProtobuf48.tag = 46;
            builder48.withProperty$ar$ds(atProtobuf48.build());
            AGGREGATEDONDEVICEPOSEDETECTIONLOGEVENT_DESCRIPTOR = builder48.build();
            FieldDescriptor.Builder builder49 = new FieldDescriptor.Builder("aggregatedOnDeviceSegmentationLogEvent");
            AtProtobuf atProtobuf49 = new AtProtobuf();
            atProtobuf49.tag = 47;
            builder49.withProperty$ar$ds(atProtobuf49.build());
            AGGREGATEDONDEVICESEGMENTATIONLOGEVENT_DESCRIPTOR = builder49.build();
            FieldDescriptor.Builder builder50 = new FieldDescriptor.Builder("pipelineAccelerationInferenceEvents");
            AtProtobuf atProtobuf50 = new AtProtobuf();
            atProtobuf50.tag = 69;
            builder50.withProperty$ar$ds(atProtobuf50.build());
            PIPELINEACCELERATIONINFERENCEEVENTS_DESCRIPTOR = builder50.build();
            FieldDescriptor.Builder builder51 = new FieldDescriptor.Builder("remoteConfigLogEvent");
            AtProtobuf atProtobuf51 = new AtProtobuf();
            atProtobuf51.tag = 42;
            builder51.withProperty$ar$ds(atProtobuf51.build());
            REMOTECONFIGLOGEVENT_DESCRIPTOR = builder51.build();
            FieldDescriptor.Builder builder52 = new FieldDescriptor.Builder("inputImageConstructionLogEvent");
            AtProtobuf atProtobuf52 = new AtProtobuf();
            atProtobuf52.tag = 50;
            builder52.withProperty$ar$ds(atProtobuf52.build());
            INPUTIMAGECONSTRUCTIONLOGEVENT_DESCRIPTOR = builder52.build();
            FieldDescriptor.Builder builder53 = new FieldDescriptor.Builder("leakedHandleEvent");
            AtProtobuf atProtobuf53 = new AtProtobuf();
            atProtobuf53.tag = 51;
            builder53.withProperty$ar$ds(atProtobuf53.build());
            LEAKEDHANDLEEVENT_DESCRIPTOR = builder53.build();
            FieldDescriptor.Builder builder54 = new FieldDescriptor.Builder("cameraSourceLogEvent");
            AtProtobuf atProtobuf54 = new AtProtobuf();
            atProtobuf54.tag = 52;
            builder54.withProperty$ar$ds(atProtobuf54.build());
            CAMERASOURCELOGEVENT_DESCRIPTOR = builder54.build();
            FieldDescriptor.Builder builder55 = new FieldDescriptor.Builder("imageLabelOptionalModuleLogEvent");
            AtProtobuf atProtobuf55 = new AtProtobuf();
            atProtobuf55.tag = 53;
            builder55.withProperty$ar$ds(atProtobuf55.build());
            IMAGELABELOPTIONALMODULELOGEVENT_DESCRIPTOR = builder55.build();
            FieldDescriptor.Builder builder56 = new FieldDescriptor.Builder("languageIdentificationOptionalModuleLogEvent");
            AtProtobuf atProtobuf56 = new AtProtobuf();
            atProtobuf56.tag = 54;
            builder56.withProperty$ar$ds(atProtobuf56.build());
            LANGUAGEIDENTIFICATIONOPTIONALMODULELOGEVENT_DESCRIPTOR = builder56.build();
            FieldDescriptor.Builder builder57 = new FieldDescriptor.Builder("faceDetectionOptionalModuleLogEvent");
            AtProtobuf atProtobuf57 = new AtProtobuf();
            atProtobuf57.tag = 60;
            builder57.withProperty$ar$ds(atProtobuf57.build());
            FACEDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR = builder57.build();
            FieldDescriptor.Builder builder58 = new FieldDescriptor.Builder("documentDetectionOptionalModuleLogEvent");
            AtProtobuf atProtobuf58 = new AtProtobuf();
            atProtobuf58.tag = 85;
            builder58.withProperty$ar$ds(atProtobuf58.build());
            DOCUMENTDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR = builder58.build();
            FieldDescriptor.Builder builder59 = new FieldDescriptor.Builder("documentCroppingOptionalModuleLogEvent");
            AtProtobuf atProtobuf59 = new AtProtobuf();
            atProtobuf59.tag = 86;
            builder59.withProperty$ar$ds(atProtobuf59.build());
            DOCUMENTCROPPINGOPTIONALMODULELOGEVENT_DESCRIPTOR = builder59.build();
            FieldDescriptor.Builder builder60 = new FieldDescriptor.Builder("documentEnhancementOptionalModuleLogEvent");
            AtProtobuf atProtobuf60 = new AtProtobuf();
            atProtobuf60.tag = 87;
            builder60.withProperty$ar$ds(atProtobuf60.build());
            DOCUMENTENHANCEMENTOPTIONALMODULELOGEVENT_DESCRIPTOR = builder60.build();
            FieldDescriptor.Builder builder61 = new FieldDescriptor.Builder("nlClassifierOptionalModuleLogEvent");
            AtProtobuf atProtobuf61 = new AtProtobuf();
            atProtobuf61.tag = 55;
            builder61.withProperty$ar$ds(atProtobuf61.build());
            NLCLASSIFIEROPTIONALMODULELOGEVENT_DESCRIPTOR = builder61.build();
            FieldDescriptor.Builder builder62 = new FieldDescriptor.Builder("nlClassifierClientLibraryLogEvent");
            AtProtobuf atProtobuf62 = new AtProtobuf();
            atProtobuf62.tag = 56;
            builder62.withProperty$ar$ds(atProtobuf62.build());
            NLCLASSIFIERCLIENTLIBRARYLOGEVENT_DESCRIPTOR = builder62.build();
            FieldDescriptor.Builder builder63 = new FieldDescriptor.Builder("accelerationAllowlistLogEvent");
            AtProtobuf atProtobuf63 = new AtProtobuf();
            atProtobuf63.tag = 57;
            builder63.withProperty$ar$ds(atProtobuf63.build());
            ACCELERATIONALLOWLISTLOGEVENT_DESCRIPTOR = builder63.build();
            FieldDescriptor.Builder builder64 = new FieldDescriptor.Builder("toxicityDetectionCreateEvent");
            AtProtobuf atProtobuf64 = new AtProtobuf();
            atProtobuf64.tag = 62;
            builder64.withProperty$ar$ds(atProtobuf64.build());
            TOXICITYDETECTIONCREATEEVENT_DESCRIPTOR = builder64.build();
            FieldDescriptor.Builder builder65 = new FieldDescriptor.Builder("toxicityDetectionLoadEvent");
            AtProtobuf atProtobuf65 = new AtProtobuf();
            atProtobuf65.tag = 63;
            builder65.withProperty$ar$ds(atProtobuf65.build());
            TOXICITYDETECTIONLOADEVENT_DESCRIPTOR = builder65.build();
            FieldDescriptor.Builder builder66 = new FieldDescriptor.Builder("toxicityDetectionInferenceEvent");
            AtProtobuf atProtobuf66 = new AtProtobuf();
            atProtobuf66.tag = 64;
            builder66.withProperty$ar$ds(atProtobuf66.build());
            TOXICITYDETECTIONINFERENCEEVENT_DESCRIPTOR = builder66.build();
            FieldDescriptor.Builder builder67 = new FieldDescriptor.Builder("barcodeDetectionOptionalModuleLogEvent");
            AtProtobuf atProtobuf67 = new AtProtobuf();
            atProtobuf67.tag = 65;
            builder67.withProperty$ar$ds(atProtobuf67.build());
            BARCODEDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR = builder67.build();
            FieldDescriptor.Builder builder68 = new FieldDescriptor.Builder("customImageLabelOptionalModuleLogEvent");
            AtProtobuf atProtobuf68 = new AtProtobuf();
            atProtobuf68.tag = 66;
            builder68.withProperty$ar$ds(atProtobuf68.build());
            CUSTOMIMAGELABELOPTIONALMODULELOGEVENT_DESCRIPTOR = builder68.build();
            FieldDescriptor.Builder builder69 = new FieldDescriptor.Builder("codeScannerScanApiEvent");
            AtProtobuf atProtobuf69 = new AtProtobuf();
            atProtobuf69.tag = 67;
            builder69.withProperty$ar$ds(atProtobuf69.build());
            CODESCANNERSCANAPIEVENT_DESCRIPTOR = builder69.build();
            FieldDescriptor.Builder builder70 = new FieldDescriptor.Builder("codeScannerOptionalModuleEvent");
            AtProtobuf atProtobuf70 = new AtProtobuf();
            atProtobuf70.tag = 68;
            builder70.withProperty$ar$ds(atProtobuf70.build());
            CODESCANNEROPTIONALMODULEEVENT_DESCRIPTOR = builder70.build();
            FieldDescriptor.Builder builder71 = new FieldDescriptor.Builder("onDeviceExplicitContentCreateLogEvent");
            AtProtobuf atProtobuf71 = new AtProtobuf();
            atProtobuf71.tag = 70;
            builder71.withProperty$ar$ds(atProtobuf71.build());
            ONDEVICEEXPLICITCONTENTCREATELOGEVENT_DESCRIPTOR = builder71.build();
            FieldDescriptor.Builder builder72 = new FieldDescriptor.Builder("onDeviceExplicitContentLoadLogEvent");
            AtProtobuf atProtobuf72 = new AtProtobuf();
            atProtobuf72.tag = 71;
            builder72.withProperty$ar$ds(atProtobuf72.build());
            ONDEVICEEXPLICITCONTENTLOADLOGEVENT_DESCRIPTOR = builder72.build();
            FieldDescriptor.Builder builder73 = new FieldDescriptor.Builder("onDeviceExplicitContentInferenceLogEvent");
            AtProtobuf atProtobuf73 = new AtProtobuf();
            atProtobuf73.tag = 72;
            builder73.withProperty$ar$ds(atProtobuf73.build());
            ONDEVICEEXPLICITCONTENTINFERENCELOGEVENT_DESCRIPTOR = builder73.build();
            FieldDescriptor.Builder builder74 = new FieldDescriptor.Builder("aggregatedOnDeviceExplicitContentLogEvent");
            AtProtobuf atProtobuf74 = new AtProtobuf();
            atProtobuf74.tag = 73;
            builder74.withProperty$ar$ds(atProtobuf74.build());
            AGGREGATEDONDEVICEEXPLICITCONTENTLOGEVENT_DESCRIPTOR = builder74.build();
            FieldDescriptor.Builder builder75 = new FieldDescriptor.Builder("onDeviceFaceMeshCreateLogEvent");
            AtProtobuf atProtobuf75 = new AtProtobuf();
            atProtobuf75.tag = 74;
            builder75.withProperty$ar$ds(atProtobuf75.build());
            ONDEVICEFACEMESHCREATELOGEVENT_DESCRIPTOR = builder75.build();
            FieldDescriptor.Builder builder76 = new FieldDescriptor.Builder("onDeviceFaceMeshLoadLogEvent");
            AtProtobuf atProtobuf76 = new AtProtobuf();
            atProtobuf76.tag = 75;
            builder76.withProperty$ar$ds(atProtobuf76.build());
            ONDEVICEFACEMESHLOADLOGEVENT_DESCRIPTOR = builder76.build();
            FieldDescriptor.Builder builder77 = new FieldDescriptor.Builder("onDeviceFaceMeshLogEvent");
            AtProtobuf atProtobuf77 = new AtProtobuf();
            atProtobuf77.tag = 76;
            builder77.withProperty$ar$ds(atProtobuf77.build());
            ONDEVICEFACEMESHLOGEVENT_DESCRIPTOR = builder77.build();
            FieldDescriptor.Builder builder78 = new FieldDescriptor.Builder("aggregatedOnDeviceFaceMeshLogEvent");
            AtProtobuf atProtobuf78 = new AtProtobuf();
            atProtobuf78.tag = 77;
            builder78.withProperty$ar$ds(atProtobuf78.build());
            AGGREGATEDONDEVICEFACEMESHLOGEVENT_DESCRIPTOR = builder78.build();
            FieldDescriptor.Builder builder79 = new FieldDescriptor.Builder("smartReplyOptionalModuleLogEvent");
            AtProtobuf atProtobuf79 = new AtProtobuf();
            atProtobuf79.tag = 78;
            builder79.withProperty$ar$ds(atProtobuf79.build());
            SMARTREPLYOPTIONALMODULELOGEVENT_DESCRIPTOR = builder79.build();
            FieldDescriptor.Builder builder80 = new FieldDescriptor.Builder("textDetectionOptionalModuleLogEvent");
            AtProtobuf atProtobuf80 = new AtProtobuf();
            atProtobuf80.tag = 80;
            builder80.withProperty$ar$ds(atProtobuf80.build());
            TEXTDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR = builder80.build();
            FieldDescriptor.Builder builder81 = new FieldDescriptor.Builder("onDeviceImageQualityAnalysisCreateLogEvent");
            AtProtobuf atProtobuf81 = new AtProtobuf();
            atProtobuf81.tag = 81;
            builder81.withProperty$ar$ds(atProtobuf81.build());
            ONDEVICEIMAGEQUALITYANALYSISCREATELOGEVENT_DESCRIPTOR = builder81.build();
            FieldDescriptor.Builder builder82 = new FieldDescriptor.Builder("onDeviceImageQualityAnalysisLoadLogEvent");
            AtProtobuf atProtobuf82 = new AtProtobuf();
            atProtobuf82.tag = 82;
            builder82.withProperty$ar$ds(atProtobuf82.build());
            ONDEVICEIMAGEQUALITYANALYSISLOADLOGEVENT_DESCRIPTOR = builder82.build();
            FieldDescriptor.Builder builder83 = new FieldDescriptor.Builder("onDeviceImageQualityAnalysisLogEvent");
            AtProtobuf atProtobuf83 = new AtProtobuf();
            atProtobuf83.tag = 83;
            builder83.withProperty$ar$ds(atProtobuf83.build());
            ONDEVICEIMAGEQUALITYANALYSISLOGEVENT_DESCRIPTOR = builder83.build();
            FieldDescriptor.Builder builder84 = new FieldDescriptor.Builder("aggregatedOnDeviceImageQualityAnalysisLogEvent");
            AtProtobuf atProtobuf84 = new AtProtobuf();
            atProtobuf84.tag = 84;
            builder84.withProperty$ar$ds(atProtobuf84.build());
            AGGREGATEDONDEVICEIMAGEQUALITYANALYSISLOGEVENT_DESCRIPTOR = builder84.build();
            FieldDescriptor.Builder builder85 = new FieldDescriptor.Builder("imageQualityAnalysisOptionalModuleLogEvent");
            AtProtobuf atProtobuf85 = new AtProtobuf();
            atProtobuf85.tag = 88;
            builder85.withProperty$ar$ds(atProtobuf85.build());
            IMAGEQUALITYANALYSISOPTIONALMODULELOGEVENT_DESCRIPTOR = builder85.build();
            FieldDescriptor.Builder builder86 = new FieldDescriptor.Builder("imageCaptioningOptionalModuleLogEvent");
            AtProtobuf atProtobuf86 = new AtProtobuf();
            atProtobuf86.tag = 89;
            builder86.withProperty$ar$ds(atProtobuf86.build());
            IMAGECAPTIONINGOPTIONALMODULELOGEVENT_DESCRIPTOR = builder86.build();
            FieldDescriptor.Builder builder87 = new FieldDescriptor.Builder("onDeviceImageCaptioningCreateLogEvent");
            AtProtobuf atProtobuf87 = new AtProtobuf();
            atProtobuf87.tag = 90;
            builder87.withProperty$ar$ds(atProtobuf87.build());
            ONDEVICEIMAGECAPTIONINGCREATELOGEVENT_DESCRIPTOR = builder87.build();
            FieldDescriptor.Builder builder88 = new FieldDescriptor.Builder("onDeviceImageCaptioningLoadLogEvent");
            AtProtobuf atProtobuf88 = new AtProtobuf();
            atProtobuf88.tag = 91;
            builder88.withProperty$ar$ds(atProtobuf88.build());
            ONDEVICEIMAGECAPTIONINGLOADLOGEVENT_DESCRIPTOR = builder88.build();
            FieldDescriptor.Builder builder89 = new FieldDescriptor.Builder("onDeviceImageCaptioningInferenceLogEvent");
            AtProtobuf atProtobuf89 = new AtProtobuf();
            atProtobuf89.tag = 92;
            builder89.withProperty$ar$ds(atProtobuf89.build());
            ONDEVICEIMAGECAPTIONINGINFERENCELOGEVENT_DESCRIPTOR = builder89.build();
            FieldDescriptor.Builder builder90 = new FieldDescriptor.Builder("aggregatedOnDeviceImageCaptioningInferenceLogEvent");
            AtProtobuf atProtobuf90 = new AtProtobuf();
            atProtobuf90.tag = 93;
            builder90.withProperty$ar$ds(atProtobuf90.build());
            AGGREGATEDONDEVICEIMAGECAPTIONINGINFERENCELOGEVENT_DESCRIPTOR = builder90.build();
            FieldDescriptor.Builder builder91 = new FieldDescriptor.Builder("onDeviceDocumentDetectionCreateLogEvent");
            AtProtobuf atProtobuf91 = new AtProtobuf();
            atProtobuf91.tag = 94;
            builder91.withProperty$ar$ds(atProtobuf91.build());
            ONDEVICEDOCUMENTDETECTIONCREATELOGEVENT_DESCRIPTOR = builder91.build();
            FieldDescriptor.Builder builder92 = new FieldDescriptor.Builder("onDeviceDocumentDetectionLoadLogEvent");
            AtProtobuf atProtobuf92 = new AtProtobuf();
            atProtobuf92.tag = 95;
            builder92.withProperty$ar$ds(atProtobuf92.build());
            ONDEVICEDOCUMENTDETECTIONLOADLOGEVENT_DESCRIPTOR = builder92.build();
            FieldDescriptor.Builder builder93 = new FieldDescriptor.Builder("onDeviceDocumentDetectionLogEvent");
            AtProtobuf atProtobuf93 = new AtProtobuf();
            atProtobuf93.tag = 96;
            builder93.withProperty$ar$ds(atProtobuf93.build());
            ONDEVICEDOCUMENTDETECTIONLOGEVENT_DESCRIPTOR = builder93.build();
            FieldDescriptor.Builder builder94 = new FieldDescriptor.Builder("aggregatedOnDeviceDocumentDetectionLogEvent");
            AtProtobuf atProtobuf94 = new AtProtobuf();
            atProtobuf94.tag = 97;
            builder94.withProperty$ar$ds(atProtobuf94.build());
            AGGREGATEDONDEVICEDOCUMENTDETECTIONLOGEVENT_DESCRIPTOR = builder94.build();
            FieldDescriptor.Builder builder95 = new FieldDescriptor.Builder("onDeviceDocumentCroppingCreateLogEvent");
            AtProtobuf atProtobuf95 = new AtProtobuf();
            atProtobuf95.tag = 98;
            builder95.withProperty$ar$ds(atProtobuf95.build());
            ONDEVICEDOCUMENTCROPPINGCREATELOGEVENT_DESCRIPTOR = builder95.build();
            FieldDescriptor.Builder builder96 = new FieldDescriptor.Builder("onDeviceDocumentCroppingLoadLogEvent");
            AtProtobuf atProtobuf96 = new AtProtobuf();
            atProtobuf96.tag = 99;
            builder96.withProperty$ar$ds(atProtobuf96.build());
            ONDEVICEDOCUMENTCROPPINGLOADLOGEVENT_DESCRIPTOR = builder96.build();
            FieldDescriptor.Builder builder97 = new FieldDescriptor.Builder("onDeviceDocumentCroppingLogEvent");
            AtProtobuf atProtobuf97 = new AtProtobuf();
            atProtobuf97.tag = 100;
            builder97.withProperty$ar$ds(atProtobuf97.build());
            ONDEVICEDOCUMENTCROPPINGLOGEVENT_DESCRIPTOR = builder97.build();
            FieldDescriptor.Builder builder98 = new FieldDescriptor.Builder("aggregatedOnDeviceDocumentCroppingLogEvent");
            AtProtobuf atProtobuf98 = new AtProtobuf();
            atProtobuf98.tag = FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu;
            builder98.withProperty$ar$ds(atProtobuf98.build());
            AGGREGATEDONDEVICEDOCUMENTCROPPINGLOGEVENT_DESCRIPTOR = builder98.build();
            FieldDescriptor.Builder builder99 = new FieldDescriptor.Builder("onDeviceDocumentEnhancementCreateLogEvent");
            AtProtobuf atProtobuf99 = new AtProtobuf();
            atProtobuf99.tag = 102;
            builder99.withProperty$ar$ds(atProtobuf99.build());
            ONDEVICEDOCUMENTENHANCEMENTCREATELOGEVENT_DESCRIPTOR = builder99.build();
            FieldDescriptor.Builder builder100 = new FieldDescriptor.Builder("onDeviceDocumentEnhancementLoadLogEvent");
            AtProtobuf atProtobuf100 = new AtProtobuf();
            atProtobuf100.tag = ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu;
            builder100.withProperty$ar$ds(atProtobuf100.build());
            ONDEVICEDOCUMENTENHANCEMENTLOADLOGEVENT_DESCRIPTOR = builder100.build();
            FieldDescriptor.Builder builder101 = new FieldDescriptor.Builder("onDeviceDocumentEnhancementLogEvent");
            AtProtobuf atProtobuf101 = new AtProtobuf();
            atProtobuf101.tag = ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_KEY_EVENT$ar$edu;
            builder101.withProperty$ar$ds(atProtobuf101.build());
            ONDEVICEDOCUMENTENHANCEMENTLOGEVENT_DESCRIPTOR = builder101.build();
            FieldDescriptor.Builder builder102 = new FieldDescriptor.Builder("aggregatedOnDeviceDocumentEnhancementLogEvent");
            AtProtobuf atProtobuf102 = new AtProtobuf();
            atProtobuf102.tag = ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_DEVICE_ROTATE$ar$edu;
            builder102.withProperty$ar$ds(atProtobuf102.build());
            AGGREGATEDONDEVICEDOCUMENTENHANCEMENTLOGEVENT_DESCRIPTOR = builder102.build();
            FieldDescriptor.Builder builder103 = new FieldDescriptor.Builder("scannerAutoZoomEvent");
            AtProtobuf atProtobuf103 = new AtProtobuf();
            atProtobuf103.tag = ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_MOTION_EVENT_SOURCE$ar$edu;
            builder103.withProperty$ar$ds(atProtobuf103.build());
            SCANNERAUTOZOOMEVENT_DESCRIPTOR = builder103.build();
            FieldDescriptor.Builder builder104 = new FieldDescriptor.Builder("lowLightAutoExposureComputationEvent");
            AtProtobuf atProtobuf104 = new AtProtobuf();
            atProtobuf104.tag = 107;
            builder104.withProperty$ar$ds(atProtobuf104.build());
            LOWLIGHTAUTOEXPOSURECOMPUTATIONEVENT_DESCRIPTOR = builder104.build();
            FieldDescriptor.Builder builder105 = new FieldDescriptor.Builder("lowLightFrameProcessEvent");
            AtProtobuf atProtobuf105 = new AtProtobuf();
            atProtobuf105.tag$ar$ds(108);
            builder105.withProperty$ar$ds(atProtobuf105.build());
            LOWLIGHTFRAMEPROCESSEVENT_DESCRIPTOR = builder105.build();
            FieldDescriptor.Builder builder106 = new FieldDescriptor.Builder("lowLightSceneDetectionEvent");
            AtProtobuf atProtobuf106 = new AtProtobuf();
            atProtobuf106.tag$ar$ds(109);
            builder106.withProperty$ar$ds(atProtobuf106.build());
            LOWLIGHTSCENEDETECTIONEVENT_DESCRIPTOR = builder106.build();
            FieldDescriptor.Builder builder107 = new FieldDescriptor.Builder("onDeviceStainRemovalLogEvent");
            AtProtobuf atProtobuf107 = new AtProtobuf();
            atProtobuf107.tag$ar$ds(BrailleInputEvent.CMD_HEADING_NEXT);
            builder107.withProperty$ar$ds(atProtobuf107.build());
            ONDEVICESTAINREMOVALLOGEVENT_DESCRIPTOR = builder107.build();
            FieldDescriptor.Builder builder108 = new FieldDescriptor.Builder("aggregatedOnDeviceStainRemovalLogEvent");
            AtProtobuf atProtobuf108 = new AtProtobuf();
            atProtobuf108.tag$ar$ds(BrailleInputEvent.CMD_HEADING_PREVIOUS);
            builder108.withProperty$ar$ds(atProtobuf108.build());
            AGGREGATEDONDEVICESTAINREMOVALLOGEVENT_DESCRIPTOR = builder108.build();
            FieldDescriptor.Builder builder109 = new FieldDescriptor.Builder("stainRemovalOptionalModuleLogEvent");
            AtProtobuf atProtobuf109 = new AtProtobuf();
            atProtobuf109.tag$ar$ds(BrailleInputEvent.CMD_CONTROL_NEXT);
            builder109.withProperty$ar$ds(atProtobuf109.build());
            STAINREMOVALOPTIONALMODULELOGEVENT_DESCRIPTOR = builder109.build();
            FieldDescriptor.Builder builder110 = new FieldDescriptor.Builder("onDeviceShadowRemovalLogEvent");
            AtProtobuf atProtobuf110 = new AtProtobuf();
            atProtobuf110.tag$ar$ds(BrailleInputEvent.CMD_CONTROL_PREVIOUS);
            builder110.withProperty$ar$ds(atProtobuf110.build());
            ONDEVICESHADOWREMOVALLOGEVENT_DESCRIPTOR = builder110.build();
            FieldDescriptor.Builder builder111 = new FieldDescriptor.Builder("aggregatedOnDeviceShadowRemovalLogEvent");
            AtProtobuf atProtobuf111 = new AtProtobuf();
            atProtobuf111.tag$ar$ds(BrailleInputEvent.CMD_LINK_NEXT);
            builder111.withProperty$ar$ds(atProtobuf111.build());
            AGGREGATEDONDEVICESHADOWREMOVALLOGEVENT_DESCRIPTOR = builder111.build();
            FieldDescriptor.Builder builder112 = new FieldDescriptor.Builder("shadowRemovalOptionalModuleLogEvent");
            AtProtobuf atProtobuf112 = new AtProtobuf();
            atProtobuf112.tag$ar$ds(BrailleInputEvent.CMD_LINK_PREVIOUS);
            builder112.withProperty$ar$ds(atProtobuf112.build());
            SHADOWREMOVALOPTIONALMODULELOGEVENT_DESCRIPTOR = builder112.build();
            FieldDescriptor.Builder builder113 = new FieldDescriptor.Builder("onDeviceDigitalInkSegmentationLogEvent");
            AtProtobuf atProtobuf113 = new AtProtobuf();
            atProtobuf113.tag$ar$ds(BrailleInputEvent.CMD_TOGGLE_SCREEN_SEARCH);
            builder113.withProperty$ar$ds(atProtobuf113.build());
            ONDEVICEDIGITALINKSEGMENTATIONLOGEVENT_DESCRIPTOR = builder113.build();
            FieldDescriptor.Builder builder114 = new FieldDescriptor.Builder("onDeviceDocumentScannerStartLogEvent");
            AtProtobuf atProtobuf114 = new AtProtobuf();
            atProtobuf114.tag$ar$ds(BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL);
            builder114.withProperty$ar$ds(atProtobuf114.build());
            ONDEVICEDOCUMENTSCANNERSTARTLOGEVENT_DESCRIPTOR = builder114.build();
            FieldDescriptor.Builder builder115 = new FieldDescriptor.Builder("onDeviceDocumentScannerFinishLogEvent");
            AtProtobuf atProtobuf115 = new AtProtobuf();
            atProtobuf115.tag$ar$ds(BrailleInputEvent.CMD_OPEN_TALKBACK_MENU);
            builder115.withProperty$ar$ds(atProtobuf115.build());
            ONDEVICEDOCUMENTSCANNERFINISHLOGEVENT_DESCRIPTOR = builder115.build();
            FieldDescriptor.Builder builder116 = new FieldDescriptor.Builder("onDeviceDocumentScannerUiStartLogEvent");
            AtProtobuf atProtobuf116 = new AtProtobuf();
            atProtobuf116.tag$ar$ds(BrailleInputEvent.CMD_SWITCH_TO_NEXT_INPUT_LANGUAGE);
            builder116.withProperty$ar$ds(atProtobuf116.build());
            ONDEVICEDOCUMENTSCANNERUISTARTLOGEVENT_DESCRIPTOR = builder116.build();
            FieldDescriptor.Builder builder117 = new FieldDescriptor.Builder("onDeviceDocumentScannerUiFinishLogEvent");
            AtProtobuf atProtobuf117 = new AtProtobuf();
            atProtobuf117.tag$ar$ds(BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE);
            builder117.withProperty$ar$ds(atProtobuf117.build());
            ONDEVICEDOCUMENTSCANNERUIFINISHLOGEVENT_DESCRIPTOR = builder117.build();
            FieldDescriptor.Builder builder118 = new FieldDescriptor.Builder("documentScannerUiOptionalModuleSessionStartLogEvent");
            AtProtobuf atProtobuf118 = new AtProtobuf();
            atProtobuf118.tag$ar$ds(BrailleInputEvent.CMD_BRAILLE_DISPLAY_SETTINGS);
            builder118.withProperty$ar$ds(atProtobuf118.build());
            DOCUMENTSCANNERUIOPTIONALMODULESESSIONSTARTLOGEVENT_DESCRIPTOR = builder118.build();
            FieldDescriptor.Builder builder119 = new FieldDescriptor.Builder("documentScannerUiOptionalModuleSessionFinishLogEvent");
            AtProtobuf atProtobuf119 = new AtProtobuf();
            atProtobuf119.tag$ar$ds(BrailleInputEvent.CMD_TALKBACK_SETTINGS);
            builder119.withProperty$ar$ds(atProtobuf119.build());
            DOCUMENTSCANNERUIOPTIONALMODULESESSIONFINISHLOGEVENT_DESCRIPTOR = builder119.build();
            FieldDescriptor.Builder builder120 = new FieldDescriptor.Builder("onDeviceDocumentScannerUiCreateLogEvent");
            AtProtobuf atProtobuf120 = new AtProtobuf();
            atProtobuf120.tag$ar$ds(BrailleInputEvent.CMD_TURN_OFF_BRAILLE_DISPLAY);
            builder120.withProperty$ar$ds(atProtobuf120.build());
            ONDEVICEDOCUMENTSCANNERUICREATELOGEVENT_DESCRIPTOR = builder120.build();
            FieldDescriptor.Builder builder121 = new FieldDescriptor.Builder("onDeviceSubjectSegmentationCreateLogEvent");
            AtProtobuf atProtobuf121 = new AtProtobuf();
            atProtobuf121.tag$ar$ds(BrailleInputEvent.CMD_TOGGLE_VOICE_FEEDBACK);
            builder121.withProperty$ar$ds(atProtobuf121.build());
            ONDEVICESUBJECTSEGMENTATIONCREATELOGEVENT_DESCRIPTOR = builder121.build();
            FieldDescriptor.Builder builder122 = new FieldDescriptor.Builder("onDeviceSubjectSegmentationLoadLogEvent");
            AtProtobuf atProtobuf122 = new AtProtobuf();
            atProtobuf122.tag$ar$ds(BrailleInputEvent.CMD_PREVIOUS_READING_CONTROL);
            builder122.withProperty$ar$ds(atProtobuf122.build());
            ONDEVICESUBJECTSEGMENTATIONLOADLOGEVENT_DESCRIPTOR = builder122.build();
            FieldDescriptor.Builder builder123 = new FieldDescriptor.Builder("onDeviceSubjectSegmentationInferenceLogEvent");
            AtProtobuf atProtobuf123 = new AtProtobuf();
            atProtobuf123.tag$ar$ds(BrailleInputEvent.CMD_NEXT_READING_CONTROL);
            builder123.withProperty$ar$ds(atProtobuf123.build());
            ONDEVICESUBJECTSEGMENTATIONINFERENCELOGEVENT_DESCRIPTOR = builder123.build();
            FieldDescriptor.Builder builder124 = new FieldDescriptor.Builder("aggregatedOnDeviceSubjectSegmentationLogEvent");
            AtProtobuf atProtobuf124 = new AtProtobuf();
            atProtobuf124.tag$ar$ds(BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE);
            builder124.withProperty$ar$ds(atProtobuf124.build());
            AGGREGATEDONDEVICESUBJECTSEGMENTATIONLOGEVENT_DESCRIPTOR = builder124.build();
            FieldDescriptor.Builder builder125 = new FieldDescriptor.Builder("subjectSegmentationOptionalModuleLogEvent");
            AtProtobuf atProtobuf125 = new AtProtobuf();
            atProtobuf125.tag$ar$ds(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
            builder125.withProperty$ar$ds(atProtobuf125.build());
            SUBJECTSEGMENTATIONOPTIONALMODULELOGEVENT_DESCRIPTOR = builder125.build();
            FieldDescriptor.Builder builder126 = new FieldDescriptor.Builder("documentScannerUiModuleScreenViewEvent");
            AtProtobuf atProtobuf126 = new AtProtobuf();
            atProtobuf126.tag$ar$ds(BrailleInputEvent.CMD_NAV_BOTTOM_OR_KEY_ACTIVATE);
            builder126.withProperty$ar$ds(atProtobuf126.build());
            DOCUMENTSCANNERUIMODULESCREENVIEWEVENT_DESCRIPTOR = builder126.build();
            FieldDescriptor.Builder builder127 = new FieldDescriptor.Builder("documentScannerUiModuleScreenClickEvent");
            AtProtobuf atProtobuf127 = new AtProtobuf();
            atProtobuf127.tag$ar$ds(BrailleInputEvent.CMD_STOP_READING);
            builder127.withProperty$ar$ds(atProtobuf127.build());
            DOCUMENTSCANNERUIMODULESCREENCLICKEVENT_DESCRIPTOR = builder127.build();
            FieldDescriptor.Builder builder128 = new FieldDescriptor.Builder("documentScannerUiModuleScreenErrorEvent");
            AtProtobuf atProtobuf128 = new AtProtobuf();
            atProtobuf128.tag$ar$ds(BrailleInputEvent.CMD_TOGGLE_AUTO_SCROLL);
            builder128.withProperty$ar$ds(atProtobuf128.build());
            DOCUMENTSCANNERUIMODULESCREENERROREVENT_DESCRIPTOR = builder128.build();
        }

        private MLKitSdkLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            MLKitSdkLogEvent mLKitSdkLogEvent = (MLKitSdkLogEvent) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(SYSTEMINFO_DESCRIPTOR, mLKitSdkLogEvent.systemInfo);
            objectEncoderContext.add$ar$ds$6bd70da1_0(EVENTNAME_DESCRIPTOR, mLKitSdkLogEvent.eventName);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISTHICKCLIENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLIENTTYPE_DESCRIPTOR, mLKitSdkLogEvent.clientType);
            objectEncoderContext.add$ar$ds$6bd70da1_0(MODELDOWNLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CUSTOMMODELLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CUSTOMMODELINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CUSTOMMODELCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEFACEDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEFACELOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICETEXTDETECTIONLOGEVENT_DESCRIPTOR, mLKitSdkLogEvent.onDeviceTextDetectionLogEvent);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICETEXTDETECTIONLOADLOGEVENT_DESCRIPTOR, mLKitSdkLogEvent.onDeviceTextDetectionLoadLogEvent);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEBARCODEDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEBARCODELOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGELABELCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGELABELLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEOBJECTCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEOBJECTLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEOBJECTINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEPOSEDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICESEGMENTATIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICESMARTREPLYLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICELANGUAGEIDENTIFICATIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICETRANSLATIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDFACEDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDCROPHINTDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDDOCUMENTTEXTDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDIMAGEPROPERTIESDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDLANDMARKDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDLOGODETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDSAFESEARCHDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDTEXTDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CLOUDWEBSEARCHDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AUTOMLIMAGELABELINGCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AUTOMLIMAGELABELINGLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AUTOMLIMAGELABELINGINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISMODELDOWNLOADEDLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DELETEMODELLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDAUTOMLIMAGELABELINGINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDCUSTOMMODELINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEFACEDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEBARCODEDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEIMAGELABELDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEOBJECTINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICETEXTDETECTIONLOGEVENT_DESCRIPTOR, mLKitSdkLogEvent.aggregatedOnDeviceTextDetectionLogEvent);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEPOSEDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICESEGMENTATIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(PIPELINEACCELERATIONINFERENCEEVENTS_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(REMOTECONFIGLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(INPUTIMAGECONSTRUCTIONLOGEVENT_DESCRIPTOR, mLKitSdkLogEvent.inputImageConstructionLogEvent);
            objectEncoderContext.add$ar$ds$6bd70da1_0(LEAKEDHANDLEEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CAMERASOURCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGELABELOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(LANGUAGEIDENTIFICATIONOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(FACEDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DOCUMENTDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DOCUMENTCROPPINGOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DOCUMENTENHANCEMENTOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(NLCLASSIFIEROPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(NLCLASSIFIERCLIENTLIBRARYLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ACCELERATIONALLOWLISTLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(TOXICITYDETECTIONCREATEEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(TOXICITYDETECTIONLOADEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(TOXICITYDETECTIONINFERENCEEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(BARCODEDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CUSTOMIMAGELABELOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CODESCANNERSCANAPIEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(CODESCANNEROPTIONALMODULEEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEEXPLICITCONTENTCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEEXPLICITCONTENTLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEEXPLICITCONTENTINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEEXPLICITCONTENTLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEFACEMESHCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEFACEMESHLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEFACEMESHLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEFACEMESHLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(SMARTREPLYOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(TEXTDETECTIONOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGEQUALITYANALYSISCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGEQUALITYANALYSISLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGEQUALITYANALYSISLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEIMAGEQUALITYANALYSISLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGEQUALITYANALYSISOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGECAPTIONINGOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGECAPTIONINGCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGECAPTIONINGLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEIMAGECAPTIONINGINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEIMAGECAPTIONINGINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTDETECTIONCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTDETECTIONLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEDOCUMENTDETECTIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTCROPPINGCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTCROPPINGLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTCROPPINGLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEDOCUMENTCROPPINGLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTENHANCEMENTCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTENHANCEMENTLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTENHANCEMENTLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICEDOCUMENTENHANCEMENTLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(SCANNERAUTOZOOMEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(LOWLIGHTAUTOEXPOSURECOMPUTATIONEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(LOWLIGHTFRAMEPROCESSEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(LOWLIGHTSCENEDETECTIONEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICESTAINREMOVALLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICESTAINREMOVALLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(STAINREMOVALOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICESHADOWREMOVALLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICESHADOWREMOVALLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(SHADOWREMOVALOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDIGITALINKSEGMENTATIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTSCANNERSTARTLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTSCANNERFINISHLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTSCANNERUISTARTLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTSCANNERUIFINISHLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DOCUMENTSCANNERUIOPTIONALMODULESESSIONSTARTLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DOCUMENTSCANNERUIOPTIONALMODULESESSIONFINISHLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICEDOCUMENTSCANNERUICREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICESUBJECTSEGMENTATIONCREATELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICESUBJECTSEGMENTATIONLOADLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ONDEVICESUBJECTSEGMENTATIONINFERENCELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(AGGREGATEDONDEVICESUBJECTSEGMENTATIONLOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(SUBJECTSEGMENTATIONOPTIONALMODULELOGEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DOCUMENTSCANNERUIMODULESCREENVIEWEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DOCUMENTSCANNERUIMODULESCREENCLICKEVENT_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(DOCUMENTSCANNERUIMODULESCREENERROREVENT_DESCRIPTOR, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ModelDownloadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DOWNLOADFAILURESTATUS_DESCRIPTOR;
        private static final FieldDescriptor DOWNLOADSTATUS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor EXACTDOWNLOADDURATIONMS_DESCRIPTOR;
        static final ModelDownloadLogEventEncoder INSTANCE = new ModelDownloadLogEventEncoder();
        private static final FieldDescriptor MDDDOWNLOADERRORCODES_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ROUGHDOWNLOADDURATIONMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("roughDownloadDurationMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ROUGHDOWNLOADDURATIONMS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("exactDownloadDurationMs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            EXACTDOWNLOADDURATIONMS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("downloadStatus");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            DOWNLOADSTATUS_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("downloadFailureStatus");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            DOWNLOADFAILURESTATUS_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("mddDownloadErrorCodes");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            MDDDOWNLOADERRORCODES_DESCRIPTOR = builder7.build();
        }

        private ModelDownloadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ModelInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor HASH_DESCRIPTOR;
        private static final FieldDescriptor HASLABELMAP_DESCRIPTOR;
        static final ModelInfoEncoder INSTANCE = new ModelInfoEncoder();
        private static final FieldDescriptor ISMANIFESTMODEL_DESCRIPTOR;
        private static final FieldDescriptor MODELTYPE_DESCRIPTOR;
        private static final FieldDescriptor NAME_DESCRIPTOR;
        private static final FieldDescriptor SIZE_DESCRIPTOR;
        private static final FieldDescriptor SOURCE_DESCRIPTOR;
        private static final FieldDescriptor URI_DESCRIPTOR;
        private static final FieldDescriptor VERSION_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("name");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            NAME_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("version");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            VERSION_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("source");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            SOURCE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("uri");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            URI_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("hash");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            HASH_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("modelType");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            MODELTYPE_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("size");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            SIZE_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("hasLabelMap");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            HASLABELMAP_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("isManifestModel");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            ISMANIFESTMODEL_DESCRIPTOR = builder9.build();
        }

        private ModelInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ModelOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor INITIALDOWNLOADCONDITIONS_DESCRIPTOR;
        static final ModelOptionsEncoder INSTANCE = new ModelOptionsEncoder();
        private static final FieldDescriptor ISMODELUPDATEENABLED_DESCRIPTOR;
        private static final FieldDescriptor MODELINFO_DESCRIPTOR;
        private static final FieldDescriptor UPDATEDOWNLOADCONDITIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("modelInfo");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            MODELINFO_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("initialDownloadConditions");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            INITIALDOWNLOADCONDITIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("updateDownloadConditions");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            UPDATEDOWNLOADCONDITIONS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("isModelUpdateEnabled");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ISMODELUPDATEENABLED_DESCRIPTOR = builder4.build();
        }

        private ModelOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ModelOptionsModelDownloadConditionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CANDOWNLOADINBACKGROUND_DESCRIPTOR;
        static final ModelOptionsModelDownloadConditionsEncoder INSTANCE = new ModelOptionsModelDownloadConditionsEncoder();
        private static final FieldDescriptor ISCHARGINGREQUIRED_DESCRIPTOR;
        private static final FieldDescriptor ISDEVICEIDLEREQUIRED_DESCRIPTOR;
        private static final FieldDescriptor ISWIFIREQUIRED_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("isChargingRequired");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ISCHARGINGREQUIRED_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isWifiRequired");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISWIFIREQUIRED_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isDeviceIdleRequired");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISDEVICEIDLEREQUIRED_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("canDownloadInBackground");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            CANDOWNLOADINBACKGROUND_DESCRIPTOR = builder4.build();
        }

        private ModelOptionsModelDownloadConditionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NLClassifierClientLibraryLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR;
        static final NLClassifierClientLibraryLogEventEncoder INSTANCE = new NLClassifierClientLibraryLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("eventType");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            EVENTTYPE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private NLClassifierClientLibraryLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NLClassifierOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR;
        static final NLClassifierOptionalModuleLogEventEncoder INSTANCE = new NLClassifierOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("eventType");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            EVENTTYPE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private NLClassifierOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NNAPIInfoDeviceInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor FEATURELEVEL_DESCRIPTOR;
        static final NNAPIInfoDeviceInfoEncoder INSTANCE = new NNAPIInfoDeviceInfoEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR;
        private static final FieldDescriptor TYPE_DESCRIPTOR;
        private static final FieldDescriptor VERSION_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("name");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            NAME_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("type");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            TYPE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("version");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            VERSION_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("featureLevel");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            FEATURELEVEL_DESCRIPTOR = builder4.build();
        }

        private NNAPIInfoDeviceInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NNAPIInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor DEVICEINFOS_DESCRIPTOR;
        private static final FieldDescriptor ERRORINFO_DESCRIPTOR;
        static final NNAPIInfoEncoder INSTANCE = new NNAPIInfoEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("deviceInfos");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DEVICEINFOS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORINFO_DESCRIPTOR = builder2.build();
        }

        private NNAPIInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ObjectInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor CATEGORY_DESCRIPTOR;
        private static final FieldDescriptor CLASSIFICATIONCONFIDENCE_DESCRIPTOR;
        static final ObjectInfoEncoder INSTANCE = new ObjectInfoEncoder();
        private static final FieldDescriptor LABELCOUNT_DESCRIPTOR;
        private static final FieldDescriptor TRACKINGID_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("category");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            CATEGORY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("classificationConfidence");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            CLASSIFICATIONCONFIDENCE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("trackingId");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            TRACKINGID_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("labelCount");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            LABELCOUNT_DESCRIPTOR = builder4.build();
        }

        private ObjectInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceBarcodeDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTEDBARCODEFORMATS_DESCRIPTOR;
        private static final FieldDescriptor DETECTEDBARCODEVALUETYPES_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceBarcodeDetectionLogEventEncoder INSTANCE = new OnDeviceBarcodeDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("detectedBarcodeFormats");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DETECTEDBARCODEFORMATS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("detectedBarcodeValueTypes");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DETECTEDBARCODEVALUETYPES_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            IMAGEINFO_DESCRIPTOR = builder5.build();
        }

        private OnDeviceBarcodeDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceBarcodeLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceBarcodeLoadLogEventEncoder INSTANCE = new OnDeviceBarcodeLoadLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private OnDeviceBarcodeLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventAggregatedSegmentationsEncoder implements ObjectEncoder {
        private static final FieldDescriptor ACTUALCOUNT_DESCRIPTOR;
        static final OnDeviceDigitalInkSegmentationLogEventAggregatedSegmentationsEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventAggregatedSegmentationsEncoder();
        private static final FieldDescriptor SAMPLEDSEGMENTATIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("sampledSegmentations");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SAMPLEDSEGMENTATIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("actualCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ACTUALCOUNT_DESCRIPTOR = builder2.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventAggregatedSegmentationsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor AGGREGATEDSEGMENTATIONS_DESCRIPTOR;
        private static final FieldDescriptor DOMAIN_DESCRIPTOR;
        private static final FieldDescriptor DOWNLOADERRORCODES_DESCRIPTOR;
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        static final OnDeviceDigitalInkSegmentationLogEventEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventEncoder();
        private static final FieldDescriptor LANGUAGE_DESCRIPTOR;
        private static final FieldDescriptor MODEL_DESCRIPTOR;
        private static final FieldDescriptor NATIVESEGMENTATIONEXCEPTION_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor SEGMENTATIONREQUEST_DESCRIPTOR;
        private static final FieldDescriptor SEGMENTATIONRESULT_DESCRIPTOR;
        private static final FieldDescriptor STATUS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("status");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            STATUS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("model");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            MODEL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("language");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            LANGUAGE_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("segmentationRequest");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            SEGMENTATIONREQUEST_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("segmentationResult");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            SEGMENTATIONRESULT_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("aggregatedSegmentations");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            AGGREGATEDSEGMENTATIONS_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            DURATIONMS_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("nativeSegmentationException");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            NATIVESEGMENTATIONEXCEPTION_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("downloadErrorCodes");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 10;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            DOWNLOADERRORCODES_DESCRIPTOR = builder10.build();
            FieldDescriptor.Builder builder11 = new FieldDescriptor.Builder("domain");
            AtProtobuf atProtobuf11 = new AtProtobuf();
            atProtobuf11.tag = 11;
            builder11.withProperty$ar$ds(atProtobuf11.build());
            DOMAIN_DESCRIPTOR = builder11.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventNativeSegmentationExceptionEncoder implements ObjectEncoder {
        private static final FieldDescriptor FILENAME_DESCRIPTOR;
        static final OnDeviceDigitalInkSegmentationLogEventNativeSegmentationExceptionEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventNativeSegmentationExceptionEncoder();
        private static final FieldDescriptor LINENUMBER_DESCRIPTOR;
        private static final FieldDescriptor STATUS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("status");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            STATUS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("filename");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            FILENAME_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("lineNumber");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            LINENUMBER_DESCRIPTOR = builder3.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventNativeSegmentationExceptionEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventSegmentationEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        static final OnDeviceDigitalInkSegmentationLogEventSegmentationEventEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventSegmentationEventEncoder();
        private static final FieldDescriptor REQUEST_DESCRIPTOR;
        private static final FieldDescriptor RESULT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("request");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            REQUEST_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("result");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            RESULT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DURATIONMS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventSegmentationEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventSegmentationGroupCounterEncoder implements ObjectEncoder {
        private static final FieldDescriptor CLASSIFICATIONNAME_DESCRIPTOR;
        private static final FieldDescriptor COUNT_DESCRIPTOR;
        static final OnDeviceDigitalInkSegmentationLogEventSegmentationGroupCounterEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventSegmentationGroupCounterEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("classificationName");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            CLASSIFICATIONNAME_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("count");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            COUNT_DESCRIPTOR = builder2.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventSegmentationGroupCounterEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventSegmentationLanguageEncoder implements ObjectEncoder {
        static final OnDeviceDigitalInkSegmentationLogEventSegmentationLanguageEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventSegmentationLanguageEncoder();
        private static final FieldDescriptor LANGUAGECODE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("languageCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LANGUAGECODE_DESCRIPTOR = builder.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventSegmentationLanguageEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventSegmentationModelEncoder implements ObjectEncoder {
        static final OnDeviceDigitalInkSegmentationLogEventSegmentationModelEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventSegmentationModelEncoder();
        private static final FieldDescriptor USERECOGNITION_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("useRecognition");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            USERECOGNITION_DESCRIPTOR = builder.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventSegmentationModelEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventSegmentationRequestEncoder implements ObjectEncoder {
        private static final FieldDescriptor ADDEDPOINTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor ADDEDSTROKECOUNT_DESCRIPTOR;
        static final OnDeviceDigitalInkSegmentationLogEventSegmentationRequestEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventSegmentationRequestEncoder();
        private static final FieldDescriptor REMOVEDPOINTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor REMOVEDSTROKECOUNT_DESCRIPTOR;
        private static final FieldDescriptor TOTALPOINTCOUNT_DESCRIPTOR;
        private static final FieldDescriptor TOTALSTROKECOUNT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("totalStrokeCount");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            TOTALSTROKECOUNT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("totalPointCount");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            TOTALPOINTCOUNT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("addedStrokeCount");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ADDEDSTROKECOUNT_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("addedPointCount");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ADDEDPOINTCOUNT_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("removedStrokeCount");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            REMOVEDSTROKECOUNT_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("removedPointCount");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            REMOVEDPOINTCOUNT_DESCRIPTOR = builder6.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventSegmentationRequestEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventSegmentationResultEncoder implements ObjectEncoder {
        private static final FieldDescriptor ADDEDGROUPS_DESCRIPTOR;
        static final OnDeviceDigitalInkSegmentationLogEventSegmentationResultEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventSegmentationResultEncoder();
        private static final FieldDescriptor REMOVEDGROUPS_DESCRIPTOR;
        private static final FieldDescriptor TOTALGROUPS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("totalGroups");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            TOTALGROUPS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("addedGroups");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ADDEDGROUPS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("removedGroups");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            REMOVEDGROUPS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventSegmentationResultEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDigitalInkSegmentationLogEventSegmenterOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor HANDWRITINGRECOGNITIONSTRATEGY_DESCRIPTOR;
        static final OnDeviceDigitalInkSegmentationLogEventSegmenterOptionsEncoder INSTANCE = new OnDeviceDigitalInkSegmentationLogEventSegmenterOptionsEncoder();
        private static final FieldDescriptor RECOGNITIONSCHEDULINGSTRATEGY_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("handwritingRecognitionStrategy");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            HANDWRITINGRECOGNITIONSTRATEGY_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("recognitionSchedulingStrategy");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            RECOGNITIONSCHEDULINGSTRATEGY_DESCRIPTOR = builder2.build();
        }

        private OnDeviceDigitalInkSegmentationLogEventSegmenterOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentCroppingCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceDocumentCroppingCreateLogEventEncoder INSTANCE = new OnDeviceDocumentCroppingCreateLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private OnDeviceDocumentCroppingCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentCroppingLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceDocumentCroppingLoadLogEventEncoder INSTANCE = new OnDeviceDocumentCroppingLoadLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DURATIONMS_DESCRIPTOR = builder2.build();
        }

        private OnDeviceDocumentCroppingLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentCroppingLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceDocumentCroppingLogEventEncoder INSTANCE = new OnDeviceDocumentCroppingLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
        }

        private OnDeviceDocumentCroppingLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentDetectionCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceDocumentDetectionCreateLogEventEncoder INSTANCE = new OnDeviceDocumentDetectionCreateLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private OnDeviceDocumentDetectionCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentDetectionLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceDocumentDetectionLoadLogEventEncoder INSTANCE = new OnDeviceDocumentDetectionLoadLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DURATIONMS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceDocumentDetectionLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DOCUMENTCORNERCONFIDENCE_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTPRESENCECONFIDENCE_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTROTATIONSUGGESTIONCONFIDENCE_DESCRIPTOR;
        private static final FieldDescriptor DOCUMENTROTATIONSUGGESTIONDEGREES_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceDocumentDetectionLogEventEncoder INSTANCE = new OnDeviceDocumentDetectionLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("documentPresenceConfidence");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DOCUMENTPRESENCECONFIDENCE_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("documentCornerConfidence");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            DOCUMENTCORNERCONFIDENCE_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("documentRotationSuggestionDegrees");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            DOCUMENTROTATIONSUGGESTIONDEGREES_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("documentRotationSuggestionConfidence");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            DOCUMENTROTATIONSUGGESTIONCONFIDENCE_DESCRIPTOR = builder7.build();
        }

        private OnDeviceDocumentDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentEnhancementCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceDocumentEnhancementCreateLogEventEncoder INSTANCE = new OnDeviceDocumentEnhancementCreateLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private OnDeviceDocumentEnhancementCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentEnhancementLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceDocumentEnhancementLoadLogEventEncoder INSTANCE = new OnDeviceDocumentEnhancementLoadLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DURATIONMS_DESCRIPTOR = builder2.build();
        }

        private OnDeviceDocumentEnhancementLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentEnhancementLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceDocumentEnhancementLogEventEncoder INSTANCE = new OnDeviceDocumentEnhancementLogEventEncoder();
        private static final FieldDescriptor PARAMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("params");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            PARAMS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
        }

        private OnDeviceDocumentEnhancementLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentEnhancementParamsEncoder implements ObjectEncoder {
        private static final FieldDescriptor AUTOWHITEBALANCEENABLED_DESCRIPTOR;
        static final OnDeviceDocumentEnhancementParamsEncoder INSTANCE = new OnDeviceDocumentEnhancementParamsEncoder();
        private static final FieldDescriptor LIGHTNESSENHANCEMENTPARAM_DESCRIPTOR;
        private static final FieldDescriptor TEXTENHANCEMENTPARAM_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("textEnhancementParam");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            TEXTENHANCEMENTPARAM_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("autoWhiteBalanceEnabled");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            AUTOWHITEBALANCEENABLED_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("lightnessEnhancementParam");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            LIGHTNESSENHANCEMENTPARAM_DESCRIPTOR = builder3.build();
        }

        private OnDeviceDocumentEnhancementParamsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentScannerFinishLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceDocumentScannerFinishLogEventEncoder INSTANCE = new OnDeviceDocumentScannerFinishLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor PAGECOUNT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DURATIONMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            OPTIONS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("pageCount");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PAGECOUNT_DESCRIPTOR = builder4.build();
        }

        private OnDeviceDocumentScannerFinishLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentScannerStartLogEventEncoder implements ObjectEncoder {
        static final OnDeviceDocumentScannerStartLogEventEncoder INSTANCE = new OnDeviceDocumentScannerStartLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
        }

        private OnDeviceDocumentScannerStartLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceDocumentScannerUILogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceDocumentScannerUILogEventEncoder INSTANCE = new OnDeviceDocumentScannerUILogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor PAGECOUNT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DURATIONMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            OPTIONS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("pageCount");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PAGECOUNT_DESCRIPTOR = builder4.build();
        }

        private OnDeviceDocumentScannerUILogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceExplicitContentCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceExplicitContentCreateLogEventEncoder INSTANCE = new OnDeviceExplicitContentCreateLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private OnDeviceExplicitContentCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceExplicitContentInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceExplicitContentInferenceLogEventEncoder INSTANCE = new OnDeviceExplicitContentInferenceLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
        }

        private OnDeviceExplicitContentInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceExplicitContentLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceExplicitContentLoadLogEventEncoder INSTANCE = new OnDeviceExplicitContentLoadLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DURATIONMS_DESCRIPTOR = builder2.build();
        }

        private OnDeviceExplicitContentLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceFaceDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor CONTOURDETECTEDFACES_DESCRIPTOR;
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceFaceDetectionLogEventEncoder INSTANCE = new OnDeviceFaceDetectionLogEventEncoder();
        private static final FieldDescriptor NONCONTOURDETECTEDFACES_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DETECTOROPTIONS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("contourDetectedFaces");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            CONTOURDETECTEDFACES_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("nonContourDetectedFaces");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            NONCONTOURDETECTEDFACES_DESCRIPTOR = builder6.build();
        }

        private OnDeviceFaceDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceFaceLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceFaceLoadLogEventEncoder INSTANCE = new OnDeviceFaceLoadLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private OnDeviceFaceLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceFaceMeshCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceFaceMeshCreateLogEventEncoder INSTANCE = new OnDeviceFaceMeshCreateLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTOROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private OnDeviceFaceMeshCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceFaceMeshLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceFaceMeshLoadLogEventEncoder INSTANCE = new OnDeviceFaceMeshLoadLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTOROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DURATIONMS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceFaceMeshLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceFaceMeshLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTEDFACES_DESCRIPTOR;
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceFaceMeshLogEventEncoder INSTANCE = new OnDeviceFaceMeshLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DETECTOROPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("detectedFaces");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            DETECTEDFACES_DESCRIPTOR = builder4.build();
        }

        private OnDeviceFaceMeshLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceFaceMeshOptionsEncoder implements ObjectEncoder {
        static final OnDeviceFaceMeshOptionsEncoder INSTANCE = new OnDeviceFaceMeshOptionsEncoder();
        private static final FieldDescriptor ISFACEMESHENABLED_DESCRIPTOR;
        private static final FieldDescriptor USECASE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("isFaceMeshEnabled");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ISFACEMESHENABLED_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("useCase");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            USECASE_DESCRIPTOR = builder2.build();
        }

        private OnDeviceFaceMeshOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageCaptioningCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceImageCaptioningCreateLogEventEncoder INSTANCE = new OnDeviceImageCaptioningCreateLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private OnDeviceImageCaptioningCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageCaptioningInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor CAPTIONCOUNT_DESCRIPTOR;
        private static final FieldDescriptor HIGHESTSCORE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor IMAGETYPE_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceImageCaptioningInferenceLogEventEncoder INSTANCE = new OnDeviceImageCaptioningInferenceLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("captionCount");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 4;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            CAPTIONCOUNT_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("highestScore");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 5;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            HIGHESTSCORE_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("imageType");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 6;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            IMAGETYPE_DESCRIPTOR = builder5.build();
        }

        private OnDeviceImageCaptioningInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageCaptioningLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceImageCaptioningLoadLogEventEncoder INSTANCE = new OnDeviceImageCaptioningLoadLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DURATIONMS_DESCRIPTOR = builder2.build();
        }

        private OnDeviceImageCaptioningLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageLabelCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceImageLabelCreateLogEventEncoder INSTANCE = new OnDeviceImageLabelCreateLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTOROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private OnDeviceImageLabelCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageLabelDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor HIGHESTCONFIDENCE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceImageLabelDetectionLogEventEncoder INSTANCE = new OnDeviceImageLabelDetectionLogEventEncoder();
        private static final FieldDescriptor LABELCOUNT_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("labelCount");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            LABELCOUNT_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("highestConfidence");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            HIGHESTCONFIDENCE_DESCRIPTOR = builder5.build();
        }

        private OnDeviceImageLabelDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageLabelLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODES_DESCRIPTOR;
        static final OnDeviceImageLabelLoadLogEventEncoder INSTANCE = new OnDeviceImageLabelLoadLogEventEncoder();
        private static final FieldDescriptor LOGGINGINITIALIZATIONMS_DESCRIPTOR;
        private static final FieldDescriptor OTHERERRORS_DESCRIPTOR;
        private static final FieldDescriptor TOTALINITIALIZATIONMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTOROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCodes");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODES_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("totalInitializationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            TOTALINITIALIZATIONMS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("loggingInitializationMs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            LOGGINGINITIALIZATIONMS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("otherErrors");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            OTHERERRORS_DESCRIPTOR = builder5.build();
        }

        private OnDeviceImageLabelLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageLabelOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CONFIDENCETHRESHOLD_DESCRIPTOR;
        private static final FieldDescriptor CUSTOMLOCALMODELOPTIONS_DESCRIPTOR;
        static final OnDeviceImageLabelOptionsEncoder INSTANCE = new OnDeviceImageLabelOptionsEncoder();
        private static final FieldDescriptor MAXLABELS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("maxLabels");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            MAXLABELS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("confidenceThreshold");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            CONFIDENCETHRESHOLD_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("customLocalModelOptions");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            CUSTOMLOCALMODELOPTIONS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceImageLabelOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageQualityAnalysisCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceImageQualityAnalysisCreateLogEventEncoder INSTANCE = new OnDeviceImageQualityAnalysisCreateLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private OnDeviceImageQualityAnalysisCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageQualityAnalysisLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceImageQualityAnalysisLoadLogEventEncoder INSTANCE = new OnDeviceImageQualityAnalysisLoadLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DURATIONMS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceImageQualityAnalysisLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageQualityAnalysisLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor IMAGEQUALITYSCORES_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceImageQualityAnalysisLogEventEncoder INSTANCE = new OnDeviceImageQualityAnalysisLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IMAGEINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("imageQualityScores");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            IMAGEQUALITYSCORES_DESCRIPTOR = builder4.build();
        }

        private OnDeviceImageQualityAnalysisLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceImageQualityAnalysisOptionsEncoder implements ObjectEncoder {
        static final OnDeviceImageQualityAnalysisOptionsEncoder INSTANCE = new OnDeviceImageQualityAnalysisOptionsEncoder();
        private static final FieldDescriptor OPTIONSTYPE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("optionsType");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONSTYPE_DESCRIPTOR = builder.build();
        }

        private OnDeviceImageQualityAnalysisOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceLanguageIdentificationLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IDENTIFYLANGUAGERESULT_DESCRIPTOR;
        private static final FieldDescriptor IDENTIFYPOSSIBLELANGUAGESRESULT_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceLanguageIdentificationLogEventEncoder INSTANCE = new OnDeviceLanguageIdentificationLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("identifyLanguageResult");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            IDENTIFYLANGUAGERESULT_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("identifyPossibleLanguagesResult");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            IDENTIFYPOSSIBLELANGUAGESRESULT_DESCRIPTOR = builder4.build();
        }

        private OnDeviceLanguageIdentificationLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceLanguageIdentificationLogEventIdentifiedLanguageEncoder implements ObjectEncoder {
        private static final FieldDescriptor CONFIDENCE_DESCRIPTOR;
        static final OnDeviceLanguageIdentificationLogEventIdentifiedLanguageEncoder INSTANCE = new OnDeviceLanguageIdentificationLogEventIdentifiedLanguageEncoder();
        private static final FieldDescriptor LANGUAGECODE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("confidence");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            CONFIDENCE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("languageCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            LANGUAGECODE_DESCRIPTOR = builder2.build();
        }

        private OnDeviceLanguageIdentificationLogEventIdentifiedLanguageEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceLanguageIdentificationLogEventIdentifyLanguageResultEncoder implements ObjectEncoder {
        private static final FieldDescriptor IDENTIFIEDLANGUAGE_DESCRIPTOR;
        static final OnDeviceLanguageIdentificationLogEventIdentifyLanguageResultEncoder INSTANCE = new OnDeviceLanguageIdentificationLogEventIdentifyLanguageResultEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("identifiedLanguage");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            IDENTIFIEDLANGUAGE_DESCRIPTOR = builder.build();
        }

        private OnDeviceLanguageIdentificationLogEventIdentifyLanguageResultEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceLanguageIdentificationLogEventIdentifyPossibleLanguagesResultEncoder implements ObjectEncoder {
        private static final FieldDescriptor IDENTIFIEDLANGUAGES_DESCRIPTOR;
        static final OnDeviceLanguageIdentificationLogEventIdentifyPossibleLanguagesResultEncoder INSTANCE = new OnDeviceLanguageIdentificationLogEventIdentifyPossibleLanguagesResultEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("identifiedLanguages");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            IDENTIFIEDLANGUAGES_DESCRIPTOR = builder.build();
        }

        private OnDeviceLanguageIdentificationLogEventIdentifyPossibleLanguagesResultEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceObjectCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceObjectCreateLogEventEncoder INSTANCE = new OnDeviceObjectCreateLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTOROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private OnDeviceObjectCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceObjectDetectorOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CLASSIFICATIONCONFIDENCETHRESHOLD_DESCRIPTOR;
        private static final FieldDescriptor CLASSIFICATIONENABLED_DESCRIPTOR;
        private static final FieldDescriptor CUSTOMLOCALMODELOPTIONS_DESCRIPTOR;
        private static final FieldDescriptor DETECTORMODE_DESCRIPTOR;
        static final OnDeviceObjectDetectorOptionsEncoder INSTANCE = new OnDeviceObjectDetectorOptionsEncoder();
        private static final FieldDescriptor MAXPEROBJECTLABELCOUNT_DESCRIPTOR;
        private static final FieldDescriptor MULTIPLEOBJECTSENABLED_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorMode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTORMODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("multipleObjectsEnabled");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            MULTIPLEOBJECTSENABLED_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("classificationEnabled");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            CLASSIFICATIONENABLED_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("maxPerObjectLabelCount");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            MAXPEROBJECTLABELCOUNT_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("classificationConfidenceThreshold");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            CLASSIFICATIONCONFIDENCETHRESHOLD_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("customLocalModelOptions");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            CUSTOMLOCALMODELOPTIONS_DESCRIPTOR = builder6.build();
        }

        private OnDeviceObjectDetectorOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceObjectInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceObjectInferenceLogEventEncoder INSTANCE = new OnDeviceObjectInferenceLogEventEncoder();
        private static final FieldDescriptor OBJECTINFOS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DETECTOROPTIONS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("objectInfos");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            OBJECTINFOS_DESCRIPTOR = builder4.build();
        }

        private OnDeviceObjectInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceObjectLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceObjectLoadLogEventEncoder INSTANCE = new OnDeviceObjectLoadLogEventEncoder();
        private static final FieldDescriptor LOGGINGINITIALIZATIONMS_DESCRIPTOR;
        private static final FieldDescriptor OTHERERRORS_DESCRIPTOR;
        private static final FieldDescriptor TOTALINITIALIZATIONMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTOROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("totalInitializationMs");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            TOTALINITIALIZATIONMS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("loggingInitializationMs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            LOGGINGINITIALIZATIONMS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("otherErrors");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            OTHERERRORS_DESCRIPTOR = builder5.build();
        }

        private OnDeviceObjectLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDevicePoseDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDevicePoseDetectionLogEventEncoder INSTANCE = new OnDevicePoseDetectionLogEventEncoder();
        private static final FieldDescriptor LOADDURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor SESSIONDURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor SESSIONTOTALINFERENCEDURATIONMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DETECTOROPTIONS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("loadDurationMs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            LOADDURATIONMS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("sessionDurationMs");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            SESSIONDURATIONMS_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("sessionTotalInferenceDurationMs");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            SESSIONTOTALINFERENCEDURATIONMS_DESCRIPTOR = builder6.build();
        }

        private OnDevicePoseDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDevicePoseDetectorOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTORMODE_DESCRIPTOR;
        static final OnDevicePoseDetectorOptionsEncoder INSTANCE = new OnDevicePoseDetectorOptionsEncoder();
        private static final FieldDescriptor LANDMARKDETECTIONMODE_DESCRIPTOR;
        private static final FieldDescriptor PERSONDETECTIONMODE_DESCRIPTOR;
        private static final FieldDescriptor PREFERREDHARDWARECONFIGS_DESCRIPTOR;
        private static final FieldDescriptor RUNCONFIG_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorMode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTORMODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("personDetectionMode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            PERSONDETECTIONMODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("landmarkDetectionMode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            LANDMARKDETECTIONMODE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("preferredHardwareConfigs");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PREFERREDHARDWARECONFIGS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("runConfig");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            RUNCONFIG_DESCRIPTOR = builder5.build();
        }

        private OnDevicePoseDetectorOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceSegmentationLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTOROPTIONS_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceSegmentationLogEventEncoder INSTANCE = new OnDeviceSegmentationLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("detectorOptions");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            DETECTOROPTIONS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceSegmentationLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceSegmenterOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTORMODE_DESCRIPTOR;
        static final OnDeviceSegmenterOptionsEncoder INSTANCE = new OnDeviceSegmenterOptionsEncoder();
        private static final FieldDescriptor RAWSIZEMASKENABLED_DESCRIPTOR;
        private static final FieldDescriptor STREAMMODESMOOTHINGRATIO_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectorMode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTORMODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("streamModeSmoothingRatio");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            STREAMMODESMOOTHINGRATIO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("rawSizeMaskEnabled");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            RAWSIZEMASKENABLED_DESCRIPTOR = builder3.build();
        }

        private OnDeviceSegmenterOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceShadowRemovalLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceShadowRemovalLogEventEncoder INSTANCE = new OnDeviceShadowRemovalLogEventEncoder();
        private static final FieldDescriptor PARAMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("params");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PARAMS_DESCRIPTOR = builder4.build();
        }

        private OnDeviceShadowRemovalLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceShadowRemovalParamsEncoder implements ObjectEncoder {
        static final OnDeviceShadowRemovalParamsEncoder INSTANCE = new OnDeviceShadowRemovalParamsEncoder();
        private static final FieldDescriptor SHADOWREMOVALTYPE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("shadowRemovalType");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SHADOWREMOVALTYPE_DESCRIPTOR = builder.build();
        }

        private OnDeviceShadowRemovalParamsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceSmartReplyLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor BLACKLISTERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceSmartReplyLogEventEncoder INSTANCE = new OnDeviceSmartReplyLogEventEncoder();
        private static final FieldDescriptor RESULTSTATUS_DESCRIPTOR;
        private static final FieldDescriptor SMARTREPLIES_DESCRIPTOR;
        private static final FieldDescriptor SUGGESTIONSCOUNT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("smartReplies");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            SMARTREPLIES_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("resultStatus");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            RESULTSTATUS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("suggestionsCount");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            SUGGESTIONSCOUNT_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("blacklistErrorCode");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            BLACKLISTERRORCODE_DESCRIPTOR = builder5.build();
        }

        private OnDeviceSmartReplyLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceSmartReplyLogEventSmartReplyEncoder implements ObjectEncoder {
        private static final FieldDescriptor CONFIDENCE_DESCRIPTOR;
        static final OnDeviceSmartReplyLogEventSmartReplyEncoder INSTANCE = new OnDeviceSmartReplyLogEventSmartReplyEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("confidence");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            CONFIDENCE_DESCRIPTOR = builder.build();
        }

        private OnDeviceSmartReplyLogEventSmartReplyEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceStainRemovalLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceStainRemovalLogEventEncoder INSTANCE = new OnDeviceStainRemovalLogEventEncoder();
        private static final FieldDescriptor PARAMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODE_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("params");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PARAMS_DESCRIPTOR = builder4.build();
        }

        private OnDeviceStainRemovalLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceStainRemovalParamsEncoder implements ObjectEncoder {
        static final OnDeviceStainRemovalParamsEncoder INSTANCE = new OnDeviceStainRemovalParamsEncoder();
        private static final FieldDescriptor USERSELECTEDAREA_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("userSelectedArea");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            USERSELECTEDAREA_DESCRIPTOR = builder.build();
        }

        private OnDeviceStainRemovalParamsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceSubjectSegmentationCreateLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceSubjectSegmentationCreateLogEventEncoder INSTANCE = new OnDeviceSubjectSegmentationCreateLogEventEncoder();
        private static final FieldDescriptor SUBJECTSEGMENTEROPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("subjectSegmenterOptions");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            SUBJECTSEGMENTEROPTIONS_DESCRIPTOR = builder2.build();
        }

        private OnDeviceSubjectSegmentationCreateLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceSubjectSegmentationInferenceLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceSubjectSegmentationInferenceLogEventEncoder INSTANCE = new OnDeviceSubjectSegmentationInferenceLogEventEncoder();
        private static final FieldDescriptor QUALITYSCORES_DESCRIPTOR;
        private static final FieldDescriptor SUBJECTINFOS_DESCRIPTOR;
        private static final FieldDescriptor SUBJECTSEGMENTEROPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("subjectSegmenterOptions");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            SUBJECTSEGMENTEROPTIONS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("subjectInfos");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            SUBJECTINFOS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("qualityScores");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            QUALITYSCORES_DESCRIPTOR = builder5.build();
        }

        private OnDeviceSubjectSegmentationInferenceLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceSubjectSegmentationLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceSubjectSegmentationLoadLogEventEncoder INSTANCE = new OnDeviceSubjectSegmentationLoadLogEventEncoder();
        private static final FieldDescriptor SUBJECTSEGMENTEROPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DURATIONMS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("subjectSegmenterOptions");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            SUBJECTSEGMENTEROPTIONS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceSubjectSegmentationLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceTextDetectionLoadLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final OnDeviceTextDetectionLoadLogEventEncoder INSTANCE = new OnDeviceTextDetectionLoadLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private OnDeviceTextDetectionLoadLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            ((ObjectEncoderContext) obj2).add$ar$ds$6bd70da1_0(ERRORCODE_DESCRIPTOR, ((OnDeviceTextDetectionLoadLogEvent) obj).OnDeviceTextDetectionLoadLogEvent$ar$errorCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceTextDetectionLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor IMAGEINFO_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final OnDeviceTextDetectionLogEventEncoder INSTANCE = new OnDeviceTextDetectionLogEventEncoder();
        private static final FieldDescriptor RECOGNIZEROPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("imageInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            IMAGEINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("recognizerOptions");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            RECOGNIZEROPTIONS_DESCRIPTOR = builder3.build();
        }

        private OnDeviceTextDetectionLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            OnDeviceTextDetectionLogEvent onDeviceTextDetectionLogEvent = (OnDeviceTextDetectionLogEvent) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(INFERENCECOMMONLOGEVENT_DESCRIPTOR, onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$inferenceCommonLogEvent);
            objectEncoderContext.add$ar$ds$6bd70da1_0(IMAGEINFO_DESCRIPTOR, onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$imageInfo);
            objectEncoderContext.add$ar$ds$6bd70da1_0(RECOGNIZEROPTIONS_DESCRIPTOR, onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$recognizerOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceTextRecognizerOptionsEncoder implements ObjectEncoder {
        static final OnDeviceTextRecognizerOptionsEncoder INSTANCE = new OnDeviceTextRecognizerOptionsEncoder();
        private static final FieldDescriptor ISUSINGLEGACYAPI_DESCRIPTOR;
        private static final FieldDescriptor LANGUAGEOPTION_DESCRIPTOR;
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("languageOption");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 3;
            builder.withProperty$ar$ds(atProtobuf.build());
            LANGUAGEOPTION_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isUsingLegacyApi");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 4;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISUSINGLEGACYAPI_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("sdkVersion");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 5;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            SDKVERSION_DESCRIPTOR = builder3.build();
        }

        private OnDeviceTextRecognizerOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(LANGUAGEOPTION_DESCRIPTOR, ((OnDeviceTextRecognizerOptions) obj).languageOption);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISUSINGLEGACYAPI_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(SDKVERSION_DESCRIPTOR, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnDeviceTranslationLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DOWNLOADHTTPRESPONSECODE_DESCRIPTOR;
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor INPUTLENGTH_DESCRIPTOR;
        static final OnDeviceTranslationLogEventEncoder INSTANCE = new OnDeviceTranslationLogEventEncoder();
        private static final FieldDescriptor LOADDICTIONARYERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;
        private static final FieldDescriptor OUTPUTLENGTH_DESCRIPTOR;
        private static final FieldDescriptor STATUS_DESCRIPTOR;
        private static final FieldDescriptor TRANSLATERESULTSTATUSCODE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OPTIONS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("inputLength");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            INPUTLENGTH_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("outputLength");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            OUTPUTLENGTH_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("loadDictionaryErrorCode");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            LOADDICTIONARYERRORCODE_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("translateResultStatusCode");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            TRANSLATERESULTSTATUSCODE_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("status");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            STATUS_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("downloadHttpResponseCode");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            DOWNLOADHTTPRESPONSECODE_DESCRIPTOR = builder8.build();
        }

        private OnDeviceTranslationLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OtherErrorEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        private static final FieldDescriptor ERRORSPACE_DESCRIPTOR;
        static final OtherErrorEncoder INSTANCE = new OtherErrorEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorSpace");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORSPACE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private OtherErrorEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PipelineAccelerationEncoder implements ObjectEncoder {
        static final PipelineAccelerationEncoder INSTANCE = new PipelineAccelerationEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR;
        private static final FieldDescriptor RUNMINIBENCHMARK_DESCRIPTOR;
        private static final FieldDescriptor STAGES_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("name");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            NAME_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("stages");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            STAGES_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("runMiniBenchmark");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            RUNMINIBENCHMARK_DESCRIPTOR = builder3.build();
        }

        private PipelineAccelerationEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PipelineIdentifierEncoder implements ObjectEncoder {
        private static final FieldDescriptor CLIENTLIBRARYNAME_DESCRIPTOR;
        private static final FieldDescriptor CLIENTLIBRARYVERSION_DESCRIPTOR;
        static final PipelineIdentifierEncoder INSTANCE = new PipelineIdentifierEncoder();
        private static final FieldDescriptor MAXCLIENTLIBRARYVERSION_DESCRIPTOR;
        private static final FieldDescriptor MINCLIENTLIBRARYVERSION_DESCRIPTOR;
        private static final FieldDescriptor NAME_DESCRIPTOR;
        private static final FieldDescriptor PIPELINENAMESPACE_DESCRIPTOR;
        private static final FieldDescriptor SOURCEPRODUCT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("pipelineNamespace");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            PIPELINENAMESPACE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("name");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            NAME_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("clientLibraryName");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            CLIENTLIBRARYNAME_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("clientLibraryVersion");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            CLIENTLIBRARYVERSION_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("minClientLibraryVersion");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            MINCLIENTLIBRARYVERSION_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("maxClientLibraryVersion");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            MAXCLIENTLIBRARYVERSION_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("sourceProduct");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            SOURCEPRODUCT_DESCRIPTOR = builder7.build();
        }

        private PipelineIdentifierEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PipelineInferenceEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ACCEPTEDCONFIGURATIONS_DESCRIPTOR;
        private static final FieldDescriptor ACTION_DESCRIPTOR;
        private static final FieldDescriptor BENCHMARKSTATUS_DESCRIPTOR;
        private static final FieldDescriptor CUSTOMERRORS_DESCRIPTOR;
        private static final FieldDescriptor DEVICEINFO_DESCRIPTOR;
        private static final FieldDescriptor ELAPSEDUS_DESCRIPTOR;
        private static final FieldDescriptor GPUINFO_DESCRIPTOR;
        static final PipelineInferenceEventEncoder INSTANCE = new PipelineInferenceEventEncoder();
        private static final FieldDescriptor NNAPIINFO_DESCRIPTOR;
        private static final FieldDescriptor PIPELINEIDENTIFIER_DESCRIPTOR;
        private static final FieldDescriptor STATUS_DESCRIPTOR;
        private static final FieldDescriptor TIMESTAMPUS_DESCRIPTOR;
        private static final FieldDescriptor VALIDATIONTESTRESULT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("deviceInfo");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DEVICEINFO_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("nnapiInfo");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            NNAPIINFO_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("gpuInfo");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            GPUINFO_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("pipelineIdentifier");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            PIPELINEIDENTIFIER_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("acceptedConfigurations");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            ACCEPTEDCONFIGURATIONS_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("action");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            ACTION_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("status");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            STATUS_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("customErrors");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            CUSTOMERRORS_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("benchmarkStatus");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            BENCHMARKSTATUS_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("validationTestResult");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 10;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            VALIDATIONTESTRESULT_DESCRIPTOR = builder10.build();
            FieldDescriptor.Builder builder11 = new FieldDescriptor.Builder("timestampUs");
            AtProtobuf atProtobuf11 = new AtProtobuf();
            atProtobuf11.tag = 11;
            builder11.withProperty$ar$ds(atProtobuf11.build());
            TIMESTAMPUS_DESCRIPTOR = builder11.build();
            FieldDescriptor.Builder builder12 = new FieldDescriptor.Builder("elapsedUs");
            AtProtobuf atProtobuf12 = new AtProtobuf();
            atProtobuf12.tag = 12;
            builder12.withProperty$ar$ds(atProtobuf12.build());
            ELAPSEDUS_DESCRIPTOR = builder12.build();
        }

        private PipelineInferenceEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PipelineStageAccelerationEncoder implements ObjectEncoder {
        private static final FieldDescriptor DEVICE_DESCRIPTOR;
        static final PipelineStageAccelerationEncoder INSTANCE = new PipelineStageAccelerationEncoder();
        private static final FieldDescriptor STAGEID_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("stageId");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            STAGEID_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("device");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DEVICE_DESCRIPTOR = builder2.build();
        }

        private PipelineStageAccelerationEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PointF2DEncoder implements ObjectEncoder {
        static final PointF2DEncoder INSTANCE = new PointF2DEncoder();
        private static final FieldDescriptor X_DESCRIPTOR;
        private static final FieldDescriptor Y_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("x");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            X_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("y");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            Y_DESCRIPTOR = builder2.build();
        }

        private PointF2DEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RemoteConfigLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor HANDLEDERRORS_DESCRIPTOR;
        private static final FieldDescriptor HTTPRESPONSECODE_DESCRIPTOR;
        static final RemoteConfigLogEventEncoder INSTANCE = new RemoteConfigLogEventEncoder();
        private static final FieldDescriptor PARTIALLYHANDLEDERRORS_DESCRIPTOR;
        private static final FieldDescriptor UNHANDLEDERRORS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DURATIONMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("handledErrors");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            HANDLEDERRORS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("partiallyHandledErrors");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            PARTIALLYHANDLEDERRORS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("unhandledErrors");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            UNHANDLEDERRORS_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("httpResponseCode");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            HTTPRESPONSECODE_DESCRIPTOR = builder5.build();
        }

        private RemoteConfigLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ScannerAutoZoomEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor APPNAME_DESCRIPTOR;
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ENDZOOMLEVEL_DESCRIPTOR;
        static final ScannerAutoZoomEventEncoder INSTANCE = new ScannerAutoZoomEventEncoder();
        private static final FieldDescriptor PREDICTEDAREA_DESCRIPTOR;
        private static final FieldDescriptor SESSIONID_DESCRIPTOR;
        private static final FieldDescriptor STARTZOOMLEVEL_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("appName");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            APPNAME_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("sessionId");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            SESSIONID_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("startZoomLevel");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            STARTZOOMLEVEL_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("endZoomLevel");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ENDZOOMLEVEL_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            DURATIONMS_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("predictedArea");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            PREDICTEDAREA_DESCRIPTOR = builder6.build();
        }

        private ScannerAutoZoomEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ScannerAutoZoomEventPredictedAreaEncoder implements ObjectEncoder {
        private static final FieldDescriptor CONFIDENCESCORE_DESCRIPTOR;
        static final ScannerAutoZoomEventPredictedAreaEncoder INSTANCE = new ScannerAutoZoomEventPredictedAreaEncoder();
        private static final FieldDescriptor XMAX_DESCRIPTOR;
        private static final FieldDescriptor XMIN_DESCRIPTOR;
        private static final FieldDescriptor YMAX_DESCRIPTOR;
        private static final FieldDescriptor YMIN_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("xMin");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            XMIN_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("yMin");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            YMIN_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("xMax");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            XMAX_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("yMax");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            YMAX_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("confidenceScore");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            CONFIDENCESCORE_DESCRIPTOR = builder5.build();
        }

        private ScannerAutoZoomEventPredictedAreaEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ShadowRemovalOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final ShadowRemovalOptionalModuleLogEventEncoder INSTANCE = new ShadowRemovalOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private ShadowRemovalOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SmartReplyGeneratorOptionsEncoder implements ObjectEncoder {
        static final SmartReplyGeneratorOptionsEncoder INSTANCE = new SmartReplyGeneratorOptionsEncoder();
        private static final FieldDescriptor MODELLANGUAGE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("modelLanguage");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            MODELLANGUAGE_DESCRIPTOR = builder.build();
        }

        private SmartReplyGeneratorOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SmartReplyOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final SmartReplyOptionalModuleLogEventEncoder INSTANCE = new SmartReplyOptionalModuleLogEventEncoder();
        private static final FieldDescriptor OPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("options");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            OPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private SmartReplyOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StainRemovalOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final StainRemovalOptionalModuleLogEventEncoder INSTANCE = new StainRemovalOptionalModuleLogEventEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
        }

        private StainRemovalOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SubjectInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor HEIGHT_DESCRIPTOR;
        static final SubjectInfoEncoder INSTANCE = new SubjectInfoEncoder();
        private static final FieldDescriptor STARTX_DESCRIPTOR;
        private static final FieldDescriptor STARTY_DESCRIPTOR;
        private static final FieldDescriptor WIDTH_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("width");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            WIDTH_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("height");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            HEIGHT_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("startX");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            STARTX_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("startY");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            STARTY_DESCRIPTOR = builder4.build();
        }

        private SubjectInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SubjectSegmentationOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final SubjectSegmentationOptionalModuleLogEventEncoder INSTANCE = new SubjectSegmentationOptionalModuleLogEventEncoder();
        private static final FieldDescriptor SUBJECTSEGMENTEROPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("subjectSegmenterOptions");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SUBJECTSEGMENTEROPTIONS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private SubjectSegmentationOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SubjectSegmenterOptionsEncoder implements ObjectEncoder {
        static final SubjectSegmenterOptionsEncoder INSTANCE = new SubjectSegmenterOptionsEncoder();
        private static final FieldDescriptor ISFOREGROUNDBITMAPENABLED_DESCRIPTOR;
        private static final FieldDescriptor ISFOREGROUNDCONFIDENCEMASKENABLED_DESCRIPTOR;
        private static final FieldDescriptor ISMULTIPLESUBJECTSENABLED_DESCRIPTOR;
        private static final FieldDescriptor ISSUBJECTBITMAPENABLED_DESCRIPTOR;
        private static final FieldDescriptor ISSUBJECTCONFIDENCEMASKENABLED_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("isForegroundConfidenceMaskEnabled");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ISFOREGROUNDCONFIDENCEMASKENABLED_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("isForegroundBitmapEnabled");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ISFOREGROUNDBITMAPENABLED_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("isMultipleSubjectsEnabled");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ISMULTIPLESUBJECTSENABLED_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("isSubjectConfidenceMaskEnabled");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            ISSUBJECTCONFIDENCEMASKENABLED_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("isSubjectBitmapEnabled");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            ISSUBJECTBITMAPENABLED_DESCRIPTOR = builder5.build();
        }

        private SubjectSegmenterOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SystemInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor APIKEY_DESCRIPTOR;
        private static final FieldDescriptor APPID_DESCRIPTOR;
        private static final FieldDescriptor APPVERSION_DESCRIPTOR;
        private static final FieldDescriptor BUILDLEVEL_DESCRIPTOR;
        private static final FieldDescriptor FIREBASEPROJECTID_DESCRIPTOR;
        private static final FieldDescriptor GCMSENDERID_DESCRIPTOR;
        static final SystemInfoEncoder INSTANCE = new SystemInfoEncoder();
        private static final FieldDescriptor ISCLEARCUTCLIENT_DESCRIPTOR;
        private static final FieldDescriptor ISJSONLOGGING_DESCRIPTOR;
        private static final FieldDescriptor ISSTANDALONEMLKIT_DESCRIPTOR;
        private static final FieldDescriptor LANGUAGES_DESCRIPTOR;
        private static final FieldDescriptor MLSDKINSTANCEID_DESCRIPTOR;
        private static final FieldDescriptor MLSDKVERSION_DESCRIPTOR;
        private static final FieldDescriptor OPTIONALMODULEVERSION_DESCRIPTOR;
        private static final FieldDescriptor TFLITESCHEMAVERSION_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("appId");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            APPID_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("appVersion");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            APPVERSION_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("firebaseProjectId");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            FIREBASEPROJECTID_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("mlSdkVersion");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty$ar$ds(atProtobuf4.build());
            MLSDKVERSION_DESCRIPTOR = builder4.build();
            FieldDescriptor.Builder builder5 = new FieldDescriptor.Builder("tfliteSchemaVersion");
            AtProtobuf atProtobuf5 = new AtProtobuf();
            atProtobuf5.tag = 5;
            builder5.withProperty$ar$ds(atProtobuf5.build());
            TFLITESCHEMAVERSION_DESCRIPTOR = builder5.build();
            FieldDescriptor.Builder builder6 = new FieldDescriptor.Builder("gcmSenderId");
            AtProtobuf atProtobuf6 = new AtProtobuf();
            atProtobuf6.tag = 6;
            builder6.withProperty$ar$ds(atProtobuf6.build());
            GCMSENDERID_DESCRIPTOR = builder6.build();
            FieldDescriptor.Builder builder7 = new FieldDescriptor.Builder("apiKey");
            AtProtobuf atProtobuf7 = new AtProtobuf();
            atProtobuf7.tag = 7;
            builder7.withProperty$ar$ds(atProtobuf7.build());
            APIKEY_DESCRIPTOR = builder7.build();
            FieldDescriptor.Builder builder8 = new FieldDescriptor.Builder("languages");
            AtProtobuf atProtobuf8 = new AtProtobuf();
            atProtobuf8.tag = 8;
            builder8.withProperty$ar$ds(atProtobuf8.build());
            LANGUAGES_DESCRIPTOR = builder8.build();
            FieldDescriptor.Builder builder9 = new FieldDescriptor.Builder("mlSdkInstanceId");
            AtProtobuf atProtobuf9 = new AtProtobuf();
            atProtobuf9.tag = 9;
            builder9.withProperty$ar$ds(atProtobuf9.build());
            MLSDKINSTANCEID_DESCRIPTOR = builder9.build();
            FieldDescriptor.Builder builder10 = new FieldDescriptor.Builder("isClearcutClient");
            AtProtobuf atProtobuf10 = new AtProtobuf();
            atProtobuf10.tag = 10;
            builder10.withProperty$ar$ds(atProtobuf10.build());
            ISCLEARCUTCLIENT_DESCRIPTOR = builder10.build();
            FieldDescriptor.Builder builder11 = new FieldDescriptor.Builder("isStandaloneMlkit");
            AtProtobuf atProtobuf11 = new AtProtobuf();
            atProtobuf11.tag = 11;
            builder11.withProperty$ar$ds(atProtobuf11.build());
            ISSTANDALONEMLKIT_DESCRIPTOR = builder11.build();
            FieldDescriptor.Builder builder12 = new FieldDescriptor.Builder("isJsonLogging");
            AtProtobuf atProtobuf12 = new AtProtobuf();
            atProtobuf12.tag = 12;
            builder12.withProperty$ar$ds(atProtobuf12.build());
            ISJSONLOGGING_DESCRIPTOR = builder12.build();
            FieldDescriptor.Builder builder13 = new FieldDescriptor.Builder("buildLevel");
            AtProtobuf atProtobuf13 = new AtProtobuf();
            atProtobuf13.tag = 13;
            builder13.withProperty$ar$ds(atProtobuf13.build());
            BUILDLEVEL_DESCRIPTOR = builder13.build();
            FieldDescriptor.Builder builder14 = new FieldDescriptor.Builder("optionalModuleVersion");
            AtProtobuf atProtobuf14 = new AtProtobuf();
            atProtobuf14.tag = 14;
            builder14.withProperty$ar$ds(atProtobuf14.build());
            OPTIONALMODULEVERSION_DESCRIPTOR = builder14.build();
        }

        private SystemInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            SystemInfo systemInfo = (SystemInfo) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            objectEncoderContext.add$ar$ds$6bd70da1_0(APPID_DESCRIPTOR, systemInfo.SystemInfo$ar$appId);
            objectEncoderContext.add$ar$ds$6bd70da1_0(APPVERSION_DESCRIPTOR, systemInfo.SystemInfo$ar$appVersion);
            objectEncoderContext.add$ar$ds$6bd70da1_0(FIREBASEPROJECTID_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(MLSDKVERSION_DESCRIPTOR, systemInfo.SystemInfo$ar$mlSdkVersion);
            objectEncoderContext.add$ar$ds$6bd70da1_0(TFLITESCHEMAVERSION_DESCRIPTOR, systemInfo.SystemInfo$ar$tfliteSchemaVersion);
            objectEncoderContext.add$ar$ds$6bd70da1_0(GCMSENDERID_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(APIKEY_DESCRIPTOR, null);
            objectEncoderContext.add$ar$ds$6bd70da1_0(LANGUAGES_DESCRIPTOR, systemInfo.SystemInfo$ar$languages);
            objectEncoderContext.add$ar$ds$6bd70da1_0(MLSDKINSTANCEID_DESCRIPTOR, systemInfo.SystemInfo$ar$mlSdkInstanceId);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISCLEARCUTCLIENT_DESCRIPTOR, systemInfo.SystemInfo$ar$isClearcutClient);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISSTANDALONEMLKIT_DESCRIPTOR, systemInfo.SystemInfo$ar$isStandaloneMlkit);
            objectEncoderContext.add$ar$ds$6bd70da1_0(ISJSONLOGGING_DESCRIPTOR, systemInfo.SystemInfo$ar$isJsonLogging);
            objectEncoderContext.add$ar$ds$6bd70da1_0(BUILDLEVEL_DESCRIPTOR, systemInfo.SystemInfo$ar$buildLevel);
            objectEncoderContext.add$ar$ds$6bd70da1_0(OPTIONALMODULEVERSION_DESCRIPTOR, systemInfo.SystemInfo$ar$optionalModuleVersion);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TextDetectionOptionalModuleLogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final TextDetectionOptionalModuleLogEventEncoder INSTANCE = new TextDetectionOptionalModuleLogEventEncoder();
        private static final FieldDescriptor TEXTDETECTIONOPTIONS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            ERRORCODE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("textDetectionOptions");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            TEXTDETECTIONOPTIONS_DESCRIPTOR = builder2.build();
        }

        private TextDetectionOptionalModuleLogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TextDetectionOptionalModuleOptionsEncoder implements ObjectEncoder {
        private static final FieldDescriptor DETECTIONTYPE_DESCRIPTOR;
        static final TextDetectionOptionalModuleOptionsEncoder INSTANCE = new TextDetectionOptionalModuleOptionsEncoder();

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("detectionType");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            DETECTIONTYPE_DESCRIPTOR = builder.build();
        }

        private TextDetectionOptionalModuleOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ToxicityDetectionCreateEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final ToxicityDetectionCreateEventEncoder INSTANCE = new ToxicityDetectionCreateEventEncoder();
        private static final FieldDescriptor LANGUAGE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("language");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LANGUAGE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
        }

        private ToxicityDetectionCreateEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ToxicityDetectionInferenceEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor INFERENCECOMMONLOGEVENT_DESCRIPTOR;
        static final ToxicityDetectionInferenceEventEncoder INSTANCE = new ToxicityDetectionInferenceEventEncoder();
        private static final FieldDescriptor LANGUAGE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("language");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LANGUAGE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("inferenceCommonLogEvent");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            INFERENCECOMMONLOGEVENT_DESCRIPTOR = builder2.build();
        }

        private ToxicityDetectionInferenceEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ToxicityDetectionLoadEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor DURATIONMS_DESCRIPTOR;
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final ToxicityDetectionLoadEventEncoder INSTANCE = new ToxicityDetectionLoadEventEncoder();
        private static final FieldDescriptor LANGUAGE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("language");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            LANGUAGE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("durationMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            DURATIONMS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            ERRORCODE_DESCRIPTOR = builder3.build();
        }

        private ToxicityDetectionLoadEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TranslatorOptionsEncoder implements ObjectEncoder {
        static final TranslatorOptionsEncoder INSTANCE = new TranslatorOptionsEncoder();
        private static final FieldDescriptor SOURCELANGUAGE_DESCRIPTOR;
        private static final FieldDescriptor TARGETLANGUAGE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("sourceLanguage");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            SOURCELANGUAGE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("targetLanguage");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            TARGETLANGUAGE_DESCRIPTOR = builder2.build();
        }

        private TranslatorOptionsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ValidationMetricResultEncoder implements ObjectEncoder {
        static final ValidationMetricResultEncoder INSTANCE = new ValidationMetricResultEncoder();
        private static final FieldDescriptor METRIC_DESCRIPTOR;
        private static final FieldDescriptor RESULT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("metric");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            METRIC_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("result");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            RESULT_DESCRIPTOR = builder2.build();
        }

        private ValidationMetricResultEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ValidationTestResultEncoder implements ObjectEncoder {
        static final ValidationTestResultEncoder INSTANCE = new ValidationTestResultEncoder();
        private static final FieldDescriptor OK_DESCRIPTOR;
        private static final FieldDescriptor RESULT_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("result");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            RESULT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("ok");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            OK_DESCRIPTOR = builder2.build();
        }

        private ValidationTestResultEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ValidationTestResultResultEncoder implements ObjectEncoder {
        private static final FieldDescriptor ERRORCODE_DESCRIPTOR;
        static final ValidationTestResultResultEncoder INSTANCE = new ValidationTestResultResultEncoder();
        private static final FieldDescriptor METRICRESULTS_DESCRIPTOR;
        private static final FieldDescriptor NUMSAMPLES_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("numSamples");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty$ar$ds(atProtobuf.build());
            NUMSAMPLES_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("errorCode");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty$ar$ds(atProtobuf2.build());
            ERRORCODE_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("metricResults");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty$ar$ds(atProtobuf3.build());
            METRICRESULTS_DESCRIPTOR = builder3.build();
        }

        private ValidationTestResultResultEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            throw null;
        }
    }

    private AutoMLKitSdkLogEventEncoder() {
    }

    public static final void configure$ar$ds(EncoderConfig encoderConfig) {
        encoderConfig.registerEncoder$ar$ds(MLKitSdkLogEvent.class, MLKitSdkLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(SystemInfo.class, SystemInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ModelDownloadLogEvent.class, ModelDownloadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ModelOptions.class, ModelOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ModelInfo.class, ModelInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ModelOptions.ModelDownloadConditions.class, ModelOptionsModelDownloadConditionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CustomModelLoadLogEvent.class, CustomModelLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CustomModelInferenceLogEvent.class, CustomModelInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(InferenceCommonLogEvent.class, InferenceCommonLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OtherError.class, OtherErrorEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CustomModelInferenceLogEvent.DataSpec.class, CustomModelInferenceLogEventDataSpecEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CustomModelCreateLogEvent.class, CustomModelCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceFaceDetectionLogEvent.class, OnDeviceFaceDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(FaceInitializationOptions.class, FaceInitializationOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ImageInfo.class, ImageInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(FaceDetectorOptions.class, FaceDetectorOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceFaceLoadLogEvent.class, OnDeviceFaceLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceTextDetectionLogEvent.class, OnDeviceTextDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceTextRecognizerOptions.class, OnDeviceTextRecognizerOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceTextDetectionLoadLogEvent.class, OnDeviceTextDetectionLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceBarcodeDetectionLogEvent.class, OnDeviceBarcodeDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(BarcodeInitializationOptions.class, BarcodeInitializationOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceBarcodeLoadLogEvent.class, OnDeviceBarcodeLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageLabelCreateLogEvent.class, OnDeviceImageLabelCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageLabelOptions.class, OnDeviceImageLabelOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageLabelLoadLogEvent.class, OnDeviceImageLabelLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageLabelDetectionLogEvent.class, OnDeviceImageLabelDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceObjectCreateLogEvent.class, OnDeviceObjectCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceObjectDetectorOptions.class, OnDeviceObjectDetectorOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceObjectLoadLogEvent.class, OnDeviceObjectLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceObjectInferenceLogEvent.class, OnDeviceObjectInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ObjectInfo.class, ObjectInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDevicePoseDetectionLogEvent.class, OnDevicePoseDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDevicePoseDetectorOptions.class, OnDevicePoseDetectorOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceSegmentationLogEvent.class, OnDeviceSegmentationLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceSegmenterOptions.class, OnDeviceSegmenterOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceSmartReplyLogEvent.class, OnDeviceSmartReplyLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceSmartReplyLogEvent.SmartReply.class, OnDeviceSmartReplyLogEventSmartReplyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceLanguageIdentificationLogEvent.class, OnDeviceLanguageIdentificationLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(LanguageIdentificationOptions.class, LanguageIdentificationOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.class, OnDeviceLanguageIdentificationLogEventIdentifyLanguageResultEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage.class, OnDeviceLanguageIdentificationLogEventIdentifiedLanguageEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.class, OnDeviceLanguageIdentificationLogEventIdentifyPossibleLanguagesResultEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceTranslationLogEvent.class, OnDeviceTranslationLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(TranslatorOptions.class, TranslatorOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudFaceDetectionLogEvent.class, CloudFaceDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudDetectorCommonOptions.class, CloudDetectorCommonOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudCropHintsDetectionLogEvent.class, CloudCropHintsDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudDocumentTextDetectionLogEvent.class, CloudDocumentTextDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudImagePropertiesDetectionLogEvent.class, CloudImagePropertiesDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudImageLabelDetectionLogEvent.class, CloudImageLabelDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudLandmarkDetectionLogEvent.class, CloudLandmarkDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudLogoDetectionLogEvent.class, CloudLogoDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudSafeSearchDetectionLogEvent.class, CloudSafeSearchDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudTextDetectionLogEvent.class, CloudTextDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CloudWebSearchDetectionLogEvent.class, CloudWebSearchDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AutoMLImageLabelingCreateLogEvent.class, AutoMLImageLabelingCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AutoMLImageLabelingLoadLogEvent.class, AutoMLImageLabelingLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AutoMLImageLabelingInferenceLogEvent.class, AutoMLImageLabelingInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(IsModelDownloadedLogEvent.class, IsModelDownloadedLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DeleteModelLogEvent.class, DeleteModelLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedAutoMLImageLabelingInferenceLogEvent.class, AggregatedAutoMLImageLabelingInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedAutoMLImageLabelingInferenceLogEvent.LogEventKey.class, AggregatedAutoMLImageLabelingInferenceLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DurationStats.class, DurationStatsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedCustomModelInferenceLogEvent.class, AggregatedCustomModelInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedCustomModelInferenceLogEvent.LogEventKey.class, AggregatedCustomModelInferenceLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceFaceDetectionLogEvent.class, AggregatedOnDeviceFaceDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceFaceDetectionLogEvent.LogEventKey.class, AggregatedOnDeviceFaceDetectionLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceBarcodeDetectionLogEvent.class, AggregatedOnDeviceBarcodeDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceBarcodeDetectionLogEvent.LogEventKey.class, AggregatedOnDeviceBarcodeDetectionLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceImageLabelDetectionLogEvent.class, AggregatedOnDeviceImageLabelDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceImageLabelDetectionLogEvent.LogEventKey.class, AggregatedOnDeviceImageLabelDetectionLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceObjectInferenceLogEvent.class, AggregatedOnDeviceObjectInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceObjectInferenceLogEvent.LogEventKey.class, AggregatedOnDeviceObjectInferenceLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceTextDetectionLogEvent.class, AggregatedOnDeviceTextDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceTextDetectionLogEvent.LogEventKey.class, AggregatedOnDeviceTextDetectionLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDevicePoseDetectionLogEvent.class, AggregatedOnDevicePoseDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDevicePoseDetectionLogEvent.LogEventKey.class, AggregatedOnDevicePoseDetectionLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceSegmentationLogEvent.class, AggregatedOnDeviceSegmentationLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceSegmentationLogEvent.LogEventKey.class, AggregatedOnDeviceSegmentationLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(PipelineInferenceEvent.class, PipelineInferenceEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DeviceInfo.class, DeviceInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(NNAPIInfo.class, NNAPIInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(NNAPIInfo.DeviceInfo.class, NNAPIInfoDeviceInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(GPUInfo.class, GPUInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(PipelineIdentifier.class, PipelineIdentifierEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(PipelineAcceleration.class, PipelineAccelerationEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(PipelineStageAcceleration.class, PipelineStageAccelerationEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(InferenceError.class, InferenceErrorEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ValidationTestResult.class, ValidationTestResultEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ValidationTestResult.Result.class, ValidationTestResultResultEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ValidationMetricResult.class, ValidationMetricResultEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(RemoteConfigLogEvent.class, RemoteConfigLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(InputImageConstructionLogEvent.class, InputImageConstructionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(LeakedHandleEvent.class, LeakedHandleEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CameraSourceLogEvent.class, CameraSourceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ImageLabelOptionalModuleLogEvent.class, ImageLabelOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(LanguageIdentificationOptionalModuleLogEvent.class, LanguageIdentificationOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(FaceDetectionOptionalModuleLogEvent.class, FaceDetectionOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DocumentDetectionOptionalModuleLogEvent.class, DocumentDetectionOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DocumentDetectorOptions.class, DocumentDetectorOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DocumentCroppingOptionalModuleLogEvent.class, DocumentCroppingOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DocumentEnhancementOptionalModuleLogEvent.class, DocumentEnhancementOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(NLClassifierOptionalModuleLogEvent.class, NLClassifierOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(NLClassifierClientLibraryLogEvent.class, NLClassifierClientLibraryLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AccelerationAllowlistLogEvent.class, AccelerationAllowlistLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ToxicityDetectionCreateEvent.class, ToxicityDetectionCreateEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ToxicityDetectionLoadEvent.class, ToxicityDetectionLoadEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ToxicityDetectionInferenceEvent.class, ToxicityDetectionInferenceEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(BarcodeDetectionOptionalModuleLogEvent.class, BarcodeDetectionOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CustomImageLabelOptionalModuleLogEvent.class, CustomImageLabelOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CodeScannerScanApiEvent.class, CodeScannerScanApiEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(CodeScannerOptionalModuleEvent.class, CodeScannerOptionalModuleEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceExplicitContentCreateLogEvent.class, OnDeviceExplicitContentCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceExplicitContentLoadLogEvent.class, OnDeviceExplicitContentLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceExplicitContentInferenceLogEvent.class, OnDeviceExplicitContentInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceExplicitContentLogEvent.class, AggregatedOnDeviceExplicitContentLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceExplicitContentLogEvent.LogEventKey.class, AggregatedOnDeviceExplicitContentLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceFaceMeshCreateLogEvent.class, OnDeviceFaceMeshCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceFaceMeshOptions.class, OnDeviceFaceMeshOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceFaceMeshLoadLogEvent.class, OnDeviceFaceMeshLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceFaceMeshLogEvent.class, OnDeviceFaceMeshLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceFaceMeshLogEvent.class, AggregatedOnDeviceFaceMeshLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceFaceMeshLogEvent.LogEventKey.class, AggregatedOnDeviceFaceMeshLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(SmartReplyOptionalModuleLogEvent.class, SmartReplyOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(SmartReplyGeneratorOptions.class, SmartReplyGeneratorOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(TextDetectionOptionalModuleLogEvent.class, TextDetectionOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(TextDetectionOptionalModuleOptions.class, TextDetectionOptionalModuleOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageQualityAnalysisCreateLogEvent.class, OnDeviceImageQualityAnalysisCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageQualityAnalysisOptions.class, OnDeviceImageQualityAnalysisOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageQualityAnalysisLoadLogEvent.class, OnDeviceImageQualityAnalysisLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageQualityAnalysisLogEvent.class, OnDeviceImageQualityAnalysisLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ImageQualityScore.class, ImageQualityScoreEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceImageQualityAnalysisLogEvent.class, AggregatedOnDeviceImageQualityAnalysisLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceImageQualityAnalysisLogEvent.LogEventKey.class, AggregatedOnDeviceImageQualityAnalysisLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ImageQualityAnalysisOptionalModuleLogEvent.class, ImageQualityAnalysisOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ImageCaptioningOptionalModuleLogEvent.class, ImageCaptioningOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageCaptioningCreateLogEvent.class, OnDeviceImageCaptioningCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageCaptioningLoadLogEvent.class, OnDeviceImageCaptioningLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceImageCaptioningInferenceLogEvent.class, OnDeviceImageCaptioningInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceImageCaptioningInferenceLogEvent.class, AggregatedOnDeviceImageCaptioningInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceImageCaptioningInferenceLogEvent.LogEventKey.class, AggregatedOnDeviceImageCaptioningInferenceLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentDetectionCreateLogEvent.class, OnDeviceDocumentDetectionCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentDetectionLoadLogEvent.class, OnDeviceDocumentDetectionLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentDetectionLogEvent.class, OnDeviceDocumentDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceDocumentDetectionLogEvent.class, AggregatedOnDeviceDocumentDetectionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceDocumentDetectionLogEvent.LogEventKey.class, AggregatedOnDeviceDocumentDetectionLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentCroppingCreateLogEvent.class, OnDeviceDocumentCroppingCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentCroppingLoadLogEvent.class, OnDeviceDocumentCroppingLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentCroppingLogEvent.class, OnDeviceDocumentCroppingLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceDocumentCroppingLogEvent.class, AggregatedOnDeviceDocumentCroppingLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceDocumentCroppingLogEvent.LogEventKey.class, AggregatedOnDeviceDocumentCroppingLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentEnhancementCreateLogEvent.class, OnDeviceDocumentEnhancementCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentEnhancementLoadLogEvent.class, OnDeviceDocumentEnhancementLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentEnhancementLogEvent.class, OnDeviceDocumentEnhancementLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentEnhancementParams.class, OnDeviceDocumentEnhancementParamsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceDocumentEnhancementLogEvent.class, AggregatedOnDeviceDocumentEnhancementLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceDocumentEnhancementLogEvent.LogEventKey.class, AggregatedOnDeviceDocumentEnhancementLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ScannerAutoZoomEvent.class, ScannerAutoZoomEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ScannerAutoZoomEvent.PredictedArea.class, ScannerAutoZoomEventPredictedAreaEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(LowLightAutoExposureComputationEvent.class, LowLightAutoExposureComputationEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(LowLightImageCaptureOptions.class, LowLightImageCaptureOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(LowLightFrameProcessEvent.class, LowLightFrameProcessEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(LowLightSceneDetectionEvent.class, LowLightSceneDetectionEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceStainRemovalLogEvent.class, OnDeviceStainRemovalLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceStainRemovalParams.class, OnDeviceStainRemovalParamsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceStainRemovalLogEvent.class, AggregatedOnDeviceStainRemovalLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceStainRemovalLogEvent.LogEventKey.class, AggregatedOnDeviceStainRemovalLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(StainRemovalOptionalModuleLogEvent.class, StainRemovalOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceShadowRemovalLogEvent.class, OnDeviceShadowRemovalLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceShadowRemovalParams.class, OnDeviceShadowRemovalParamsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceShadowRemovalLogEvent.class, AggregatedOnDeviceShadowRemovalLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceShadowRemovalLogEvent.LogEventKey.class, AggregatedOnDeviceShadowRemovalLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(ShadowRemovalOptionalModuleLogEvent.class, ShadowRemovalOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.class, OnDeviceDigitalInkSegmentationLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.SegmenterOptions.class, OnDeviceDigitalInkSegmentationLogEventSegmenterOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.SegmentationModel.class, OnDeviceDigitalInkSegmentationLogEventSegmentationModelEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.SegmentationLanguage.class, OnDeviceDigitalInkSegmentationLogEventSegmentationLanguageEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.SegmentationRequest.class, OnDeviceDigitalInkSegmentationLogEventSegmentationRequestEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.SegmentationResult.class, OnDeviceDigitalInkSegmentationLogEventSegmentationResultEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.SegmentationGroupCounter.class, OnDeviceDigitalInkSegmentationLogEventSegmentationGroupCounterEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.AggregatedSegmentations.class, OnDeviceDigitalInkSegmentationLogEventAggregatedSegmentationsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.SegmentationEvent.class, OnDeviceDigitalInkSegmentationLogEventSegmentationEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDigitalInkSegmentationLogEvent.NativeSegmentationException.class, OnDeviceDigitalInkSegmentationLogEventNativeSegmentationExceptionEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentScannerStartLogEvent.class, OnDeviceDocumentScannerStartLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DocumentScannerUIOptions.class, DocumentScannerUIOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentScannerFinishLogEvent.class, OnDeviceDocumentScannerFinishLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceDocumentScannerUILogEvent.class, OnDeviceDocumentScannerUILogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DocumentScannerUIOptionalModuleSessionLogEvent.class, DocumentScannerUIOptionalModuleSessionLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DocumentScannerUIPage.class, DocumentScannerUIPageEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(PointF2D.class, PointF2DEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceSubjectSegmentationCreateLogEvent.class, OnDeviceSubjectSegmentationCreateLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(SubjectSegmenterOptions.class, SubjectSegmenterOptionsEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceSubjectSegmentationLoadLogEvent.class, OnDeviceSubjectSegmentationLoadLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(OnDeviceSubjectSegmentationInferenceLogEvent.class, OnDeviceSubjectSegmentationInferenceLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(SubjectInfo.class, SubjectInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceSubjectSegmentationLogEvent.class, AggregatedOnDeviceSubjectSegmentationLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(AggregatedOnDeviceSubjectSegmentationLogEvent.LogEventKey.class, AggregatedOnDeviceSubjectSegmentationLogEventLogEventKeyEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(SubjectSegmentationOptionalModuleLogEvent.class, SubjectSegmentationOptionalModuleLogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder$ar$ds(DocumentScannerUIModuleSreenEvent.class, DocumentScannerUIModuleSreenEventEncoder.INSTANCE);
    }
}

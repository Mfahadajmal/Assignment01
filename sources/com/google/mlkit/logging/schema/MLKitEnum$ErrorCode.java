package com.google.mlkit.logging.schema;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId;
import com.google.firebase.encoders.proto.ProtoEnum;
import com.google.search.mdi.aratea.proto.FeatureName;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum MLKitEnum$ErrorCode implements ProtoEnum {
    NO_ERROR(0),
    INCOMPATIBLE_INPUT(1),
    INCOMPATIBLE_OUTPUT(2),
    INCOMPATIBLE_TFLITE_VERSION(3),
    MISSING_OP(4),
    DATA_TYPE_ERROR(6),
    TFLITE_INTERNAL_ERROR(7),
    TFLITE_UNKNOWN_ERROR(8),
    MEDIAPIPE_ERROR(9),
    TIME_OUT_FETCHING_MODEL_METADATA(5),
    MODEL_NOT_DOWNLOADED(100),
    URI_EXPIRED(FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu),
    NO_NETWORK_CONNECTION(102),
    METERED_NETWORK(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu),
    DOWNLOAD_FAILED(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_KEY_EVENT$ar$edu),
    MODEL_INFO_DOWNLOAD_UNSUCCESSFUL_HTTP_STATUS(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_DEVICE_ROTATE$ar$edu),
    MODEL_INFO_DOWNLOAD_NO_HASH(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_MOTION_EVENT_SOURCE$ar$edu),
    MODEL_INFO_DOWNLOAD_CONNECTION_FAILED(107),
    NO_VALID_MODEL(108),
    LOCAL_MODEL_INVALID(109),
    REMOTE_MODEL_INVALID(BrailleInputEvent.CMD_HEADING_NEXT),
    REMOTE_MODEL_LOADER_ERROR(BrailleInputEvent.CMD_HEADING_PREVIOUS),
    REMOTE_MODEL_LOADER_LOADS_NO_MODEL(BrailleInputEvent.CMD_CONTROL_NEXT),
    SMART_REPLY_LANG_ID_DETECTAION_FAILURE(BrailleInputEvent.CMD_CONTROL_PREVIOUS),
    MODEL_NOT_REGISTERED(BrailleInputEvent.CMD_LINK_NEXT),
    MODEL_TYPE_MISUSE(BrailleInputEvent.CMD_LINK_PREVIOUS),
    MODEL_HASH_MISMATCH(BrailleInputEvent.CMD_TOGGLE_SCREEN_SEARCH),
    OPTIONAL_MODULE_NOT_AVAILABLE(201),
    OPTIONAL_MODULE_INIT_ERROR(202),
    OPTIONAL_MODULE_INFERENCE_ERROR(TrainingProto$TrainingPageId.PAGE_ID_WATCH_GO_BACK$ar$edu),
    OPTIONAL_MODULE_RELEASE_ERROR(TrainingProto$TrainingPageId.PAGE_ID_WATCH_VOLUME_UP$ar$edu),
    OPTIONAL_TFLITE_MODULE_INIT_ERROR(TrainingProto$TrainingPageId.PAGE_ID_WATCH_VOLUME_DOWN$ar$edu),
    NATIVE_LIBRARY_LOAD_ERROR(TrainingProto$TrainingPageId.PAGE_ID_WATCH_OPEN_TALKBACK_MENU$ar$edu),
    OPTIONAL_MODULE_CREATE_ERROR(TrainingProto$TrainingPageId.PAGE_ID_WATCH_END_TUTORIAL$ar$edu),
    CAMERAX_SOURCE_ERROR(301),
    CAMERA1_SOURCE_CANT_START_ERROR(302),
    CAMERA1_SOURCE_NO_SUITABLE_SIZE_ERROR(303),
    CAMERA1_SOURCE_NO_SUITABLE_FPS_ERROR(304),
    CAMERA1_SOURCE_NO_BYTE_SOURCE_FOUND_ERROR(305),
    CODE_SCANNER_UNAVAILABLE(400),
    CODE_SCANNER_CANCELLED(FeatureName.STYLUS_IMAGE_CAPTION$ar$edu),
    CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED(FeatureName.STYLUS_SKETCH_TO_IMAGE$ar$edu),
    CODE_SCANNER_APP_NAME_UNAVAILABLE(403),
    CODE_SCANNER_TASK_IN_PROGRESS(404),
    CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR(405),
    CODE_SCANNER_PIPELINE_INFERENCE_ERROR(406),
    CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD(407),
    LOW_LIGHT_AUTO_EXPOSURE_COMPUTATION_FAILURE(500),
    LOW_LIGHT_IMAGE_CAPTURE_PROCESSING_FAILURE(FeatureName.GENC_COMPUTE$ar$edu),
    PERMISSION_DENIED(600),
    CANCELLED(FeatureName.AM_GENERATIVE_STICKERS$ar$edu),
    GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD(602),
    LOW_MEMORY(603),
    UNKNOWN_ERROR(9999);

    public final int value;

    MLKitEnum$ErrorCode(int i) {
        this.value = i;
    }

    @Override // com.google.firebase.encoders.proto.ProtoEnum
    public final int getNumber() {
        return this.value;
    }
}

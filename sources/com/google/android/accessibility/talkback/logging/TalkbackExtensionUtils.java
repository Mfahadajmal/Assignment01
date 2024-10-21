package com.google.android.accessibility.talkback.logging;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.utils.Performance;
import java.util.regex.Pattern;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkbackExtensionUtils {
    public static final Pattern PARSE_DELAYED_TIME = Pattern.compile("when=-(\\d+s)?(\\d+ms)?");
    public static final Pattern PARSE_TOTAL_MASSAGE = Pattern.compile("Total messages: (\\d+)");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int eventTypeToEventLatencyInfoType$ar$edu(Performance.EventId eventId) {
        int i = eventId.eventType;
        if (i != 0) {
            if (i != 1 && i != 2) {
                if (i != 4) {
                    if (i != 5) {
                        if (i != 6) {
                            if (i != 7) {
                                return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_UNDEFINED$ar$edu$46c2921f_0;
                            }
                            return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_MOTION_EVENT_SOURCE$ar$edu;
                        }
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu;
                    }
                    return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_DEVICE_ROTATE$ar$edu;
                }
                return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_GESTURE$ar$edu;
            }
            return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_KEY_EVENT$ar$edu;
        }
        int i2 = eventId.eventSubtype;
        if (i2 != 1) {
            if (i2 != 2) {
                switch (i2) {
                    case 4:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_SELECTED$ar$edu;
                    case 8:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_FOCUSED$ar$edu;
                    case 16:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_TEXT_CHANGED$ar$edu;
                    case 32:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_WINDOW_STATE_CHANGED$ar$edu;
                    case 64:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_NOTIFICATION_STATE_CHANGED$ar$edu;
                    case BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE /* 128 */:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_HOVER_ENTER$ar$edu;
                    case 256:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_HOVER_EXIT$ar$edu;
                    case 512:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_TOUCH_EXPLORATION_GESTURE_START$ar$edu;
                    case 1024:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_TOUCH_EXPLORATION_GESTURE_END$ar$edu;
                    case 2048:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_WINDOW_CONTENT_CHANGED$ar$edu;
                    case 4096:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_SCROLLED$ar$edu;
                    case 8192:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_TEXT_SELECTION_CHANGED$ar$edu;
                    case 16384:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_ANNOUNCEMENT$ar$edu;
                    case 32768:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_ACCESSIBILITY_FOCUSED$ar$edu;
                    case 65536:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED$ar$edu;
                    case 131072:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY$ar$edu;
                    case 262144:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_GESTURE_DETECTION_START$ar$edu;
                    case 524288:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_GESTURE_DETECTION_END$ar$edu;
                    case 1048576:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_TOUCH_INTERACTION_START$ar$edu;
                    case 2097152:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_TOUCH_INTERACTION_END$ar$edu;
                    case 4194304:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_WINDOWS_CHANGED$ar$edu;
                    case 8388608:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_CONTEXT_CLICKED$ar$edu;
                    case 16777216:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_ASSIST_READING_CONTEXT$ar$edu;
                    case 33554432:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_SPEECH_STATE_CHANGE$ar$edu;
                    case 67108864:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_TARGETED_BY_SCROLL$ar$edu;
                    default:
                        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_ACCESSIBILITY_UNDEFINED$ar$edu;
                }
            }
            return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_LONG_CLICKED$ar$edu;
        }
        return ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_VIEW_CLICKED$ar$edu;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int ttsEnginePackageNameToEnum$ar$edu(String str) {
        char c;
        if (str == null) {
            return ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_UNDEFINED$ar$edu;
        }
        switch (str.hashCode()) {
            case -1409468099:
                if (str.equals("com.redzoc.ramees.tts.espeak")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1400990575:
                if (str.equals("es.codefactory.vocalizertts")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -884712359:
                if (str.equals("com.samsung.SMT")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -613657399:
                if (str.equals("com.acapelagroup.android.tts")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 325979980:
                if (str.equals("com.google.android.tts")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1407890373:
                if (str.equals("jp.co.createsystem")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        if (c != 4) {
                            if (c != 5) {
                                return ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_UNDEFINED$ar$edu;
                            }
                            return ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_DTALKER$ar$edu;
                        }
                        return ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_VOCALIZER$ar$edu;
                    }
                    return ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_SAMSUNG$ar$edu;
                }
                return ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_GOOGLE$ar$edu;
            }
            return ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_ESPEAK$ar$edu;
        }
        return ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_ACAPELA$ar$edu;
    }
}

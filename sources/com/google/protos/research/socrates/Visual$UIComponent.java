package com.google.protos.research.socrates;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.search.mdi.aratea.proto.FeatureName;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$LanguageCode;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Visual$UIComponent extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final Visual$UIComponent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    public Visual$Rectangular boundingBox_;
    public Internal.ProtobufList predictedType_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PredictedType extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final PredictedType DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public float score_;
        public int typeOfCase_ = 0;
        public Object typeOf_;

        static {
            PredictedType predictedType = new PredictedType();
            DEFAULT_INSTANCE = predictedType;
            GeneratedMessageLite.registerDefaultInstance(PredictedType.class, predictedType);
        }

        private PredictedType() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0002\u0004\u0003\u0000\u0000\u0000\u0002\u0001\u0003?\u0000\u0004Ȼ\u0000", new Object[]{"typeOf_", "typeOfCase_", "score_"});
                case NEW_MUTABLE_INSTANCE:
                    return new PredictedType();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((char[]) null, (byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (PredictedType.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Type implements Internal.EnumLite {
        UNKNOWN_COMPONENT_TYPE(0),
        BUTTON(1),
        ICON(14),
        CHECK_BOX(2),
        RADIO_BUTTON(3),
        SLIDER(4),
        SWITCH(5),
        STAR(15),
        THUMBS_UP(16),
        HEART(17),
        TEXT(6),
        TEXT_INPUT(7),
        IMAGE(8),
        GRAPHICS(12),
        MAP(13),
        HORIZONTAL_SCROLL_BAR(9),
        VERTICAL_SCROLL_BAR(10),
        KEYBOARD(18),
        GROUP(11),
        PICTOGRAM(98),
        LIST_ITEM(99),
        NAVIGATION_BAR(100),
        TOOLBAR(FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu),
        LABEL(102),
        PAGER_INDICATOR(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu),
        ICON_UNSPECIFIED(19),
        ICON_PLUS(20),
        ICON_ARROW_BACKWARD(21),
        ICON_ARROW_FORWARD(22),
        ICON_CALL(23),
        ICON_CHAT(24),
        ICON_CHECK(25),
        ICON_X(26),
        ICON_DELETE(27),
        ICON_EDIT(28),
        ICON_EMOJI(29),
        ICON_END_CALL(30),
        ICON_V_DOWNWARD(31),
        ICON_HEART(32),
        ICON_HOME(33),
        ICON_INFO(34),
        ICON_LAUNCH_APPS(35),
        ICON_THUMBS_UP(36),
        ICON_THREE_BARS(37),
        ICON_THREE_DOTS(38),
        ICON_NOTIFICATIONS(39),
        ICON_PAUSE(40),
        ICON_PLAY(41),
        ICON_REFRESH(42),
        ICON_MAGNIFYING_GLASS(43),
        ICON_SEND(44),
        ICON_SETTINGS(45),
        ICON_SHARE(46),
        ICON_STAR(47),
        ICON_TAKE_PHOTO(48),
        ICON_TIME(49),
        ICON_VIDEOCAM(50),
        ICON_EXPAND(51),
        ICON_CONTRACT(52),
        ICON_GOOGLE(53),
        ICON_TWITTER(54),
        ICON_FACEBOOK(55),
        ICON_ASSISTANT(56),
        ICON_WEATHER(57),
        ICON_SHOPPING_CART(58),
        ICON_UPLOAD(59),
        ICON_QUESTION(60),
        ICON_MIC(61),
        ICON_MIC_MUTE(62),
        ICON_GALLERY(63),
        ICON_COMPASS(64),
        ICON_PEOPLE(65),
        ICON_ARROW_UPWARD(66),
        ICON_ENVELOPE(67),
        ICON_EMOJI_FACE(68),
        ICON_PAPERCLIP(69),
        ICON_NAV_BAR_RECT(70),
        ICON_NAV_BAR_CIRCLE(71),
        ICON_CAST(72),
        ICON_VOLUME_UP(73),
        ICON_VOLUME_DOWN(74),
        ICON_VOLUME_STATE(75),
        ICON_VOLUME_MUTE(76),
        ICON_STOP(77),
        ICON_SHOPPING_BAG(78),
        ICON_LIST(79),
        ICON_LOCATION(80),
        ICON_CALENDAR(81),
        ICON_THUMBS_DOWN(82),
        ICON_HEADSET(83),
        ICON_REDO(84),
        ICON_UNDO(85),
        ICON_DOWNLOAD(86),
        ICON_ARROW_DOWNWARD(87),
        ICON_V_UPWARD(88),
        ICON_V_FORWARD(89),
        ICON_V_BACKWARD(90),
        ICON_HISTORY(91),
        ICON_PERSON(92),
        ICON_HAPPY_FACE(93),
        ICON_SAD_FACE(94),
        ICON_MOON(95),
        ICON_CLOUD(96),
        ICON_SUN(97),
        UNRECOGNIZED(-1);

        private final int value;

        Type(int i) {
            this.value = i;
        }

        public static Type forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN_COMPONENT_TYPE;
                case 1:
                    return BUTTON;
                case 2:
                    return CHECK_BOX;
                case 3:
                    return RADIO_BUTTON;
                case 4:
                    return SLIDER;
                case 5:
                    return SWITCH;
                case 6:
                    return TEXT;
                case 7:
                    return TEXT_INPUT;
                case 8:
                    return IMAGE;
                case 9:
                    return HORIZONTAL_SCROLL_BAR;
                case 10:
                    return VERTICAL_SCROLL_BAR;
                case 11:
                    return GROUP;
                case 12:
                    return GRAPHICS;
                case 13:
                    return MAP;
                case 14:
                    return ICON;
                case 15:
                    return STAR;
                case 16:
                    return THUMBS_UP;
                case 17:
                    return HEART;
                case 18:
                    return KEYBOARD;
                case 19:
                    return ICON_UNSPECIFIED;
                case 20:
                    return ICON_PLUS;
                case 21:
                    return ICON_ARROW_BACKWARD;
                case 22:
                    return ICON_ARROW_FORWARD;
                case 23:
                    return ICON_CALL;
                case 24:
                    return ICON_CHAT;
                case 25:
                    return ICON_CHECK;
                case 26:
                    return ICON_X;
                case 27:
                    return ICON_DELETE;
                case 28:
                    return ICON_EDIT;
                case 29:
                    return ICON_EMOJI;
                case 30:
                    return ICON_END_CALL;
                case 31:
                    return ICON_V_DOWNWARD;
                case 32:
                    return ICON_HEART;
                case 33:
                    return ICON_HOME;
                case 34:
                    return ICON_INFO;
                case 35:
                    return ICON_LAUNCH_APPS;
                case 36:
                    return ICON_THUMBS_UP;
                case 37:
                    return ICON_THREE_BARS;
                case 38:
                    return ICON_THREE_DOTS;
                case 39:
                    return ICON_NOTIFICATIONS;
                case 40:
                    return ICON_PAUSE;
                case 41:
                    return ICON_PLAY;
                case 42:
                    return ICON_REFRESH;
                case 43:
                    return ICON_MAGNIFYING_GLASS;
                case 44:
                    return ICON_SEND;
                case 45:
                    return ICON_SETTINGS;
                case 46:
                    return ICON_SHARE;
                case 47:
                    return ICON_STAR;
                case 48:
                    return ICON_TAKE_PHOTO;
                case 49:
                    return ICON_TIME;
                case 50:
                    return ICON_VIDEOCAM;
                case 51:
                    return ICON_EXPAND;
                case 52:
                    return ICON_CONTRACT;
                case 53:
                    return ICON_GOOGLE;
                case 54:
                    return ICON_TWITTER;
                case 55:
                    return ICON_FACEBOOK;
                case 56:
                    return ICON_ASSISTANT;
                case 57:
                    return ICON_WEATHER;
                case 58:
                    return ICON_SHOPPING_CART;
                case 59:
                    return ICON_UPLOAD;
                case 60:
                    return ICON_QUESTION;
                case 61:
                    return ICON_MIC;
                case 62:
                    return ICON_MIC_MUTE;
                case 63:
                    return ICON_GALLERY;
                case 64:
                    return ICON_COMPASS;
                case 65:
                    return ICON_PEOPLE;
                case 66:
                    return ICON_ARROW_UPWARD;
                case 67:
                    return ICON_ENVELOPE;
                case 68:
                    return ICON_EMOJI_FACE;
                case 69:
                    return ICON_PAPERCLIP;
                case 70:
                    return ICON_NAV_BAR_RECT;
                case 71:
                    return ICON_NAV_BAR_CIRCLE;
                case 72:
                    return ICON_CAST;
                case 73:
                    return ICON_VOLUME_UP;
                case 74:
                    return ICON_VOLUME_DOWN;
                case 75:
                    return ICON_VOLUME_STATE;
                case 76:
                    return ICON_VOLUME_MUTE;
                case 77:
                    return ICON_STOP;
                case 78:
                    return ICON_SHOPPING_BAG;
                case 79:
                    return ICON_LIST;
                case 80:
                    return ICON_LOCATION;
                case 81:
                    return ICON_CALENDAR;
                case 82:
                    return ICON_THUMBS_DOWN;
                case 83:
                    return ICON_HEADSET;
                case 84:
                    return ICON_REDO;
                case 85:
                    return ICON_UNDO;
                case 86:
                    return ICON_DOWNLOAD;
                case 87:
                    return ICON_ARROW_DOWNWARD;
                case 88:
                    return ICON_V_UPWARD;
                case 89:
                    return ICON_V_FORWARD;
                case 90:
                    return ICON_V_BACKWARD;
                case 91:
                    return ICON_HISTORY;
                case 92:
                    return ICON_PERSON;
                case 93:
                    return ICON_HAPPY_FACE;
                case 94:
                    return ICON_SAD_FACE;
                case 95:
                    return ICON_MOON;
                case BraillebackLogProto$LanguageCode.NORWEGIAN_COMP8$ar$edu /* 96 */:
                    return ICON_CLOUD;
                case 97:
                    return ICON_SUN;
                case 98:
                    return PICTOGRAM;
                case 99:
                    return LIST_ITEM;
                case 100:
                    return NAVIGATION_BAR;
                case FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu /* 101 */:
                    return TOOLBAR;
                case 102:
                    return LABEL;
                case ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu /* 103 */:
                    return PAGER_INDICATOR;
                default:
                    return null;
            }
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Override // java.lang.Enum
        public final String toString() {
            return Integer.toString(getNumber());
        }
    }

    static {
        Visual$UIComponent visual$UIComponent = new Visual$UIComponent();
        DEFAULT_INSTANCE = visual$UIComponent;
        GeneratedMessageLite.registerDefaultInstance(Visual$UIComponent.class, visual$UIComponent);
    }

    private Visual$UIComponent() {
        emptyProtobufList();
        emptyProtobufList();
        GeneratedMessageLite.emptyProtobufList();
        emptyLongList();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0007\u0002\u0000\u0001\u0000\u0001\u001b\u0007ဉ\u0001", new Object[]{"bitField0_", "predictedType_", PredictedType.class, "boundingBox_"});
            case NEW_MUTABLE_INSTANCE:
                return new Visual$UIComponent();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Visual$UIComponent.class) {
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

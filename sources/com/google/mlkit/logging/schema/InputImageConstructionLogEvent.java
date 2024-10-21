package com.google.mlkit.logging.schema;

import com.google.firebase.encoders.proto.ProtoEnum;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InputImageConstructionLogEvent {
    public Object InputImageConstructionLogEvent$ar$durationMs;
    public Object InputImageConstructionLogEvent$ar$imageByteSize;
    public Object InputImageConstructionLogEvent$ar$imageFormat;
    public Object InputImageConstructionLogEvent$ar$imageHeight;
    public Object InputImageConstructionLogEvent$ar$imageSource;
    public Object InputImageConstructionLogEvent$ar$rotationDegrees;
    public Integer imageWidth;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ImageSource implements ProtoEnum {
        SOURCE_UNKNOWN(0),
        BITMAP(1),
        BYTEARRAY(2),
        BYTEBUFFER(3),
        FILEPATH(4),
        ANDROID_MEDIA_IMAGE(5);

        private final int value;

        ImageSource(int i) {
            this.value = i;
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public final int getNumber() {
            return this.value;
        }
    }

    public InputImageConstructionLogEvent() {
    }

    public InputImageConstructionLogEvent(InputImageConstructionLogEvent inputImageConstructionLogEvent) {
        this.InputImageConstructionLogEvent$ar$durationMs = inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$durationMs;
        this.InputImageConstructionLogEvent$ar$imageSource = inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageSource;
        this.InputImageConstructionLogEvent$ar$imageFormat = inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageFormat;
        this.InputImageConstructionLogEvent$ar$imageByteSize = inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageByteSize;
        this.imageWidth = inputImageConstructionLogEvent.imageWidth;
        this.InputImageConstructionLogEvent$ar$imageHeight = inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageHeight;
        this.InputImageConstructionLogEvent$ar$rotationDegrees = inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$rotationDegrees;
    }
}

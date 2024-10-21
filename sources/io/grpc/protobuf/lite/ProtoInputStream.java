package io.grpc.protobuf.lite;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.grpc.KnownLength;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoInputStream extends InputStream implements KnownLength, InputStreamRetargetInterface {
    public MessageLite message;
    public final Parser parser;
    public ByteArrayInputStream partial;

    public ProtoInputStream(MessageLite messageLite, Parser parser) {
        this.message = messageLite;
        this.parser = parser;
    }

    @Override // java.io.InputStream
    public final int available() {
        MessageLite messageLite = this.message;
        if (messageLite != null) {
            return messageLite.getSerializedSize();
        }
        ByteArrayInputStream byteArrayInputStream = this.partial;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.available();
        }
        return 0;
    }

    @Override // java.io.InputStream
    public final int read() {
        MessageLite messageLite = this.message;
        if (messageLite != null) {
            this.partial = new ByteArrayInputStream(messageLite.toByteArray());
            this.message = null;
        }
        ByteArrayInputStream byteArrayInputStream = this.partial;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.read();
        }
        return -1;
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public final /* synthetic */ long transferTo(OutputStream outputStream) {
        return DesugarInputStream.transferTo(this, outputStream);
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        MessageLite messageLite = this.message;
        if (messageLite != null) {
            int serializedSize = messageLite.getSerializedSize();
            if (serializedSize == 0) {
                this.message = null;
                this.partial = null;
                return -1;
            }
            if (i2 >= serializedSize) {
                CodedOutputStream.ArrayEncoder arrayEncoder = new CodedOutputStream.ArrayEncoder(bArr, i, serializedSize);
                this.message.writeTo(arrayEncoder);
                arrayEncoder.checkNoSpaceLeft();
                this.message = null;
                this.partial = null;
                return serializedSize;
            }
            this.partial = new ByteArrayInputStream(this.message.toByteArray());
            this.message = null;
        }
        ByteArrayInputStream byteArrayInputStream = this.partial;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.read(bArr, i, i2);
        }
        return -1;
    }
}

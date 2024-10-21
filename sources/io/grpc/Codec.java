package io.grpc;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Codec extends Compressor, Decompressor {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Identity implements Codec {
        public static final Codec NONE = new Identity(0);
        private final /* synthetic */ int switching_field;

        public Identity(int i) {
            this.switching_field = i;
        }

        @Override // io.grpc.Compressor
        public final OutputStream compress(OutputStream outputStream) {
            if (this.switching_field != 0) {
                return new GZIPOutputStream(outputStream);
            }
            return outputStream;
        }

        @Override // io.grpc.Decompressor
        public final InputStream decompress(InputStream inputStream) {
            if (this.switching_field != 0) {
                return new GZIPInputStream(inputStream);
            }
            return inputStream;
        }

        @Override // io.grpc.Compressor, io.grpc.Decompressor
        public final String getMessageEncoding() {
            if (this.switching_field != 0) {
                return "gzip";
            }
            return "identity";
        }
    }
}

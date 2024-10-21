package com.google.common.io;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Queue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ByteStreams {
    static {
        new OutputStream() { // from class: com.google.common.io.ByteStreams.1
            public final String toString() {
                return "ByteStreams.nullOutputStream()";
            }

            @Override // java.io.OutputStream
            public final void write(int i) {
            }

            @Override // java.io.OutputStream
            public final void write(byte[] bArr) {
                bArr.getClass();
            }

            @Override // java.io.OutputStream
            public final void write(byte[] bArr, int i, int i2) {
                bArr.getClass();
                ContextDataProvider.checkPositionIndexes(i, i2 + i, bArr.length);
            }
        };
    }

    private static byte[] combineBuffers(Queue queue, int i) {
        if (queue.isEmpty()) {
            return new byte[0];
        }
        byte[] bArr = (byte[]) queue.remove();
        int length = bArr.length;
        if (length == i) {
            return bArr;
        }
        byte[] copyOf = Arrays.copyOf(bArr, i);
        int i2 = i - length;
        while (i2 > 0) {
            byte[] bArr2 = (byte[]) queue.remove();
            int min = Math.min(i2, bArr2.length);
            System.arraycopy(bArr2, 0, copyOf, i - i2, min);
            i2 -= min;
        }
        return copyOf;
    }

    public static byte[] toByteArrayInternal(InputStream inputStream, Queue queue, int i) {
        int i2;
        int highestOneBit = Integer.highestOneBit(i);
        int min = Math.min(8192, Math.max(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE, highestOneBit + highestOneBit));
        while (i < 2147483639) {
            int min2 = Math.min(min, 2147483639 - i);
            byte[] bArr = new byte[min2];
            queue.add(bArr);
            int i3 = 0;
            while (i3 < min2) {
                int read = inputStream.read(bArr, i3, min2 - i3);
                if (read == -1) {
                    return combineBuffers(queue, i);
                }
                i3 += read;
                i += read;
            }
            if (min < 4096) {
                i2 = 4;
            } else {
                i2 = 2;
            }
            min = ContextDataProvider.saturatedCast(min * i2);
        }
        if (inputStream.read() == -1) {
            return combineBuffers(queue, 2147483639);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }
}

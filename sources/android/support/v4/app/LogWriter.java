package android.support.v4.app;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import java.io.Writer;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LogWriter extends Writer {
    private final StringBuilder mBuilder = new StringBuilder(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);

    private final void flushBuilder() {
        if (this.mBuilder.length() > 0) {
            this.mBuilder.toString();
            StringBuilder sb = this.mBuilder;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        flushBuilder();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        flushBuilder();
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                flushBuilder();
            } else {
                this.mBuilder.append(c);
            }
        }
    }
}

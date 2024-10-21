package com.google.android.accessibility.braille.brltty;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import androidx.core.content.ContextCompat$Api24Impl;
import com.google.android.accessibility.braille.brailledisplay.platform.Displayer;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrlttyEncoder implements Encoder {
    private final Displayer.EncoderCallback callback$ar$class_merging$203f6ad6_0;
    public final Context context;
    public int dataFileState$ar$edu = 2;
    public final File tablesDir;
    private final String tablesDirPath;

    static {
        if (!"robolectric".equals(Build.FINGERPRINT)) {
            System.loadLibrary("brlttywrap");
            classInitNative();
        }
    }

    public BrlttyEncoder(Context context, Displayer.EncoderCallback encoderCallback) {
        this.context = context;
        this.callback$ar$class_merging$203f6ad6_0 = encoderCallback;
        File dir = ContextCompat$Api24Impl.createDeviceProtectedStorageContext(context).getDir("keytables", 0);
        this.tablesDir = dir;
        this.tablesDirPath = dir.getPath();
    }

    private static native void classInitNative();

    private boolean sendBytesToDevice(byte[] bArr) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.v("Displayer", "sendPacketToDevice invoked on main thread; ignoring");
        }
        Displayer.this.callback$ar$class_merging$dbd9c3ba_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSendPacketToDisplay(bArr);
        return true;
    }

    public native void addBytesFromDeviceNative(byte[] bArr, int i);

    public native BrailleKeyBinding[] getKeyMapNative();

    public native int getStatusCellsNative();

    public native int getTextCellsNative();

    public native boolean initNative(Context context);

    public native int readCommandNative();

    public void readDelayed(long j) {
        this.callback$ar$class_merging$203f6ad6_0.readAfterDelay((int) j);
    }

    public native boolean startNative(String str, String str2, float f);

    public native void stopNative();

    public native boolean writeWindowNative(byte[] bArr);
}

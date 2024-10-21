package com.google.android.accessibility.braille.brailledisplay.platform.connect.bt;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brltty.BrailleInputEventIA;
import io.grpc.internal.RetryingNameResolver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BtConnection implements D2dConnection {
    public RetryingNameResolver.ResolutionResultListener callback$ar$class_merging$35226cf6_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final ConnectableDevice device;
    public final InputStream inputStream;
    public boolean isFailed;
    public boolean isShutdown;
    private final OutputStream outputStream;
    public final byte[] readBuffer = new byte[16384];
    public boolean readThreadAlive;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ReadThread extends Thread {
        public ReadThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            while (true) {
                try {
                    BtConnection btConnection = BtConnection.this;
                    if (btConnection.readThreadAlive) {
                        int read = btConnection.inputStream.read(btConnection.readBuffer, 0, 16384);
                        AppCompatTextViewAutoSizeHelper.Api23Impl.v("BtConnection", String.format(Locale.ENGLISH, "<- (%d bytes)", Integer.valueOf(read)));
                        byte[] bArr = new byte[read];
                        System.arraycopy(BtConnection.this.readBuffer, 0, bArr, 0, read);
                        ((Connectioneer) BtConnection.this.callback$ar$class_merging$35226cf6_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectTraffic.notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(bArr, 3));
                        BtConnection.this.callback$ar$class_merging$35226cf6_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onRead();
                    } else {
                        return;
                    }
                } catch (IOException e) {
                    BtConnection btConnection2 = BtConnection.this;
                    btConnection2.readThreadAlive = false;
                    btConnection2.postExceptionToMain(e);
                    return;
                }
            }
        }
    }

    public BtConnection(ConnectableDevice connectableDevice, InputStream inputStream, OutputStream outputStream) {
        this.device = connectableDevice;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection
    public final ConnectableDevice getDevice() {
        return this.device;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection
    public final void open$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        BrailleInputEventIA.assertMainThread();
        this.callback$ar$class_merging$35226cf6_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.readThreadAlive = true;
        new ReadThread().start();
    }

    public final void postExceptionToMain(Exception exc) {
        new Handler(Looper.getMainLooper()).post(new DelayedWorkTracker.AnonymousClass1(this, exc, 7, (byte[]) null));
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection
    public final void sendOutgoingPacket(byte[] bArr) {
        if (!this.isShutdown && !this.isFailed) {
            try {
                this.outputStream.write(bArr);
                this.outputStream.flush();
                return;
            } catch (IOException e) {
                postExceptionToMain(e);
                return;
            }
        }
        AppCompatTextViewAutoSizeHelper.Api23Impl.e("BtConnection", "sendOutgoingMessage ignored");
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection
    public final void shutdown() {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnection", "shutdown");
        this.isShutdown = true;
        this.readThreadAlive = false;
    }
}

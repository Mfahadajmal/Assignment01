package com.google.android.libraries.accessibility.utils.screencapture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.hardware.display.VirtualDisplay;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenCaptureController {
    public MediaProjection activeProjection;
    public final LocalBroadcastManager broadcastManager;
    private final Context context;
    public final Handler handler;
    public ImageReader imageReader;
    public final MediaProjection.Callback projectionCallback;
    public final MediaProjectionManager projectionManager;
    public VirtualDisplay virtualDisplay;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AuthorizationListener {
        void onAuthorizationFinished(boolean z);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface CaptureListener {
        void onScreenCaptureFinished(Bitmap bitmap, boolean z);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ScreenCaptureImageProcessor implements ImageReader.OnImageAvailableListener {
        public final CaptureListener listener;

        public ScreenCaptureImageProcessor(CaptureListener captureListener) {
            this.listener = captureListener;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x005c  */
        /* renamed from: lambda$onImageAvailable$0$com-google-android-libraries-accessibility-utils-screencapture-ScreenCaptureController$ScreenCaptureImageProcessor, reason: not valid java name */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final /* synthetic */ void m189x7e161aa9(android.media.ImageReader r8) {
            /*
                r7 = this;
                r0 = 1
                r1 = 0
                r2 = 0
                android.media.Image r8 = r8.acquireLatestImage()     // Catch: java.lang.UnsupportedOperationException -> L43
                if (r8 != 0) goto Lb
            L9:
                r3 = r2
                goto L41
            Lb:
                android.media.Image$Plane[] r3 = r8.getPlanes()     // Catch: java.lang.UnsupportedOperationException -> L43
                if (r3 == 0) goto L9
                int r4 = r3.length     // Catch: java.lang.UnsupportedOperationException -> L43
                if (r4 > 0) goto L15
                goto L9
            L15:
                r4 = r3[r1]     // Catch: java.lang.UnsupportedOperationException -> L43
                int r5 = r4.getRowStride()     // Catch: java.lang.UnsupportedOperationException -> L43
                int r4 = r4.getPixelStride()     // Catch: java.lang.UnsupportedOperationException -> L43
                int r5 = r5 / r4
                int r4 = r8.getHeight()     // Catch: java.lang.UnsupportedOperationException -> L43
                android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.ARGB_8888     // Catch: java.lang.UnsupportedOperationException -> L43
                android.graphics.Bitmap r4 = android.graphics.Bitmap.createBitmap(r5, r4, r6)     // Catch: java.lang.UnsupportedOperationException -> L43
                r3 = r3[r1]     // Catch: java.lang.UnsupportedOperationException -> L43
                java.nio.ByteBuffer r3 = r3.getBuffer()     // Catch: java.lang.UnsupportedOperationException -> L43
                r4.copyPixelsFromBuffer(r3)     // Catch: java.lang.UnsupportedOperationException -> L43
                android.graphics.Rect r3 = r8.getCropRect()     // Catch: java.lang.UnsupportedOperationException -> L43
                android.graphics.Bitmap r3 = com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.cropBitmap(r4, r3)     // Catch: java.lang.UnsupportedOperationException -> L43
                r4.recycle()     // Catch: java.lang.UnsupportedOperationException -> L43
                r8.close()     // Catch: java.lang.UnsupportedOperationException -> L43
            L41:
                r1 = r0
                goto L44
            L43:
                r3 = r2
            L44:
                com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController r8 = com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.this
                android.hardware.display.VirtualDisplay r8 = r8.virtualDisplay
                r8.release()
                com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController r8 = com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.this
                r8.virtualDisplay = r2
                android.media.ImageReader r8 = r8.imageReader
                r8.close()
                com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController r8 = com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.this
                r8.imageReader = r2
                com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController$CaptureListener r2 = r7.listener
                if (r2 == 0) goto L66
                android.os.Handler r8 = r8.handler
                io.grpc.internal.InternalSubchannel$7 r2 = new io.grpc.internal.InternalSubchannel$7
                r2.<init>(r7, r3, r1, r0)
                r8.post(r2)
            L66:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.ScreenCaptureImageProcessor.m189x7e161aa9(android.media.ImageReader):void");
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public final void onImageAvailable(ImageReader imageReader) {
            imageReader.setOnImageAvailableListener(null, null);
            new Thread(new ListMenuManager$$ExternalSyntheticLambda3(this, imageReader, 19, (char[]) null)).start();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ScreenshotAuthorizationReceiver extends BroadcastReceiver {
        public final AuthorizationListener listener;

        public ScreenshotAuthorizationReceiver(AuthorizationListener authorizationListener) {
            this.listener = authorizationListener;
        }

        private final void deliverResult(boolean z) {
            if (this.listener != null) {
                ScreenCaptureController screenCaptureController = ScreenCaptureController.this;
                screenCaptureController.handler.post(new MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0(this, z, 1));
            }
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            MediaProjection mediaProjection;
            if ("com.google.android.libraries.accessibility.utils.screencapture.ACTION_SCREEN_CAPTURE_AUTHORIZED".equals(intent.getAction())) {
                LogUtils.i("ScreenCaptureController", "Screen capture was authorized.", new Object[0]);
                Intent intent2 = (Intent) intent.getParcelableExtra("com.google.android.libraries.accessibility.utils.screencapture.EXTRA_SCREEN_CAPTURE_AUTH_INTENT");
                if (intent2 != null) {
                    try {
                        mediaProjection = ScreenCaptureController.this.projectionManager.getMediaProjection(-1, intent2);
                    } catch (IllegalStateException unused) {
                        LogUtils.e("ScreenCaptureController", "MediaProjectionManager indicated projection has already started.", new Object[0]);
                        mediaProjection = null;
                    }
                    if (mediaProjection != null) {
                        LogUtils.i("ScreenCaptureController", "Obtained MediaProjection from system.", new Object[0]);
                        ScreenCaptureController screenCaptureController = ScreenCaptureController.this;
                        screenCaptureController.activeProjection = mediaProjection;
                        screenCaptureController.activeProjection.registerCallback(screenCaptureController.projectionCallback, null);
                        deliverResult(true);
                    } else {
                        LogUtils.e("ScreenCaptureController", "Unable to obtain MediaProjection from system.", new Object[0]);
                        deliverResult(false);
                    }
                } else {
                    LogUtils.e("ScreenCaptureController", "Screen capture token was not valid.", new Object[0]);
                    deliverResult(false);
                }
            } else if ("com.google.android.libraries.accessibility.utils.screencapture.ACTION_SCREEN_CAPTURE_NOT_AUTHORIZED".equals(intent.getAction())) {
                LogUtils.w("ScreenCaptureController", "Screen capture was not authorized.", new Object[0]);
                deliverResult(false);
            }
            ScreenCaptureController.this.broadcastManager.unregisterReceiver(this);
        }
    }

    public ScreenCaptureController(Context context) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.projectionCallback = new MediaProjection.Callback() { // from class: com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.1
            @Override // android.media.projection.MediaProjection.Callback
            public final void onStop() {
                ScreenCaptureController.this.deauthorizeCapture();
            }
        };
        this.context = context;
        this.handler = handler;
        this.projectionManager = (MediaProjectionManager) context.getSystemService("media_projection");
        this.broadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public final void authorizeCaptureAsync(AuthorizationListener authorizationListener) {
        if (canRequestScreenCapture()) {
            LogUtils.w("ScreenCaptureController", "Authorization requested for previously authorized instance.", new Object[0]);
            this.handler.post(new TrainingActivity$$ExternalSyntheticLambda1(authorizationListener, 14));
            return;
        }
        this.handler.post(new BrailleIme$21$$ExternalSyntheticLambda1(4));
        ScreenshotAuthorizationReceiver screenshotAuthorizationReceiver = new ScreenshotAuthorizationReceiver(authorizationListener);
        LocalBroadcastManager localBroadcastManager = this.broadcastManager;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.libraries.accessibility.utils.screencapture.ACTION_SCREEN_CAPTURE_AUTHORIZED");
        intentFilter.addAction("com.google.android.libraries.accessibility.utils.screencapture.ACTION_SCREEN_CAPTURE_NOT_AUTHORIZED");
        localBroadcastManager.registerReceiver(screenshotAuthorizationReceiver, intentFilter);
        Intent intent = new Intent(this.context, (Class<?>) ScreenshotAuthProxyActivity.class);
        intent.putExtra("com.google.android.libraries.accessibility.utils.screencapture.EXTRA_SCREEN_CAPTURE_INTENT", this.projectionManager.createScreenCaptureIntent());
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }

    public final boolean canRequestScreenCapture() {
        if (this.activeProjection != null) {
            return true;
        }
        return false;
    }

    public final void deauthorizeCapture() {
        LogUtils.i("ScreenCaptureController", "Deauthorizing.", new Object[0]);
        MediaProjection mediaProjection = this.activeProjection;
        if (mediaProjection != null) {
            mediaProjection.unregisterCallback(this.projectionCallback);
            this.activeProjection.stop();
            this.activeProjection = null;
        }
        VirtualDisplay virtualDisplay = this.virtualDisplay;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            this.virtualDisplay = null;
        }
        ImageReader imageReader = this.imageReader;
        if (imageReader != null) {
            imageReader.close();
            this.imageReader = null;
        }
    }

    public final void requestScreenCaptureAsync(final CaptureListener captureListener) {
        if (!canRequestScreenCapture()) {
            final CaptureListener captureListener2 = new CaptureListener(this) { // from class: com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.2
                final /* synthetic */ ScreenCaptureController this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.CaptureListener
                public final void onScreenCaptureFinished(Bitmap bitmap, boolean z) {
                    captureListener.onScreenCaptureFinished(bitmap, z);
                    this.this$0.deauthorizeCapture();
                }
            };
            authorizeCaptureAsync(new AuthorizationListener(this) { // from class: com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.3
                final /* synthetic */ ScreenCaptureController this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.AuthorizationListener
                public final void onAuthorizationFinished(boolean z) {
                    if (z) {
                        this.this$0.requestScreenCaptureAsync(captureListener2);
                    } else {
                        captureListener2.onScreenCaptureFinished(null, true);
                    }
                }
            });
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        ImageReader newInstance = ImageReader.newInstance(displayMetrics.widthPixels, displayMetrics.heightPixels, 1, 1);
        this.imageReader = newInstance;
        newInstance.setOnImageAvailableListener(new ScreenCaptureImageProcessor(captureListener), this.handler);
        VirtualDisplay virtualDisplay = this.virtualDisplay;
        if (virtualDisplay != null) {
            virtualDisplay.release();
        }
        try {
            this.virtualDisplay = this.activeProjection.createVirtualDisplay("com.google.android.libraries.accessibility.utils.screencapture.VIRTUAL_DISPLAY_SCREEN_CAPTURE", displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.densityDpi, 16, this.imageReader.getSurface(), null, null);
        } catch (SecurityException unused) {
            LogUtils.e("ScreenCaptureController", "Unexpected invalid MediaProjection token", new Object[0]);
            deauthorizeCapture();
            this.handler.post(new TrainingActivity$$ExternalSyntheticLambda1(captureListener, 13));
        }
    }
}

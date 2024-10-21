package com.google.android.accessibility.utils;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.google.android.accessibility.braille.common.TouchDots;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProximitySensor {
    public FloatingActionButton.ShadowDelegateImpl mCallback$ar$class_merging$16a1d12_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final float mFarValue;
    public boolean mIsActive;
    public boolean mIsClose;
    public final Sensor mProxSensor;
    public final SensorManager mSensorManager;
    public boolean mShouldDropEvents;
    public final Handler mHandler = new Handler();
    public final SensorEventListener mListener = new AnonymousClass1(this, 0);
    public final Runnable mFilterRunnable = new TrainingActivity$$ExternalSyntheticLambda1(this, 3, null);

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.utils.ProximitySensor$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements SensorEventListener {
        final /* synthetic */ Object ProximitySensor$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.ProximitySensor$1$ar$this$0 = obj;
        }

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
            if (this.switching_field != 0) {
                return;
            }
            LogUtils.v("ProximitySensor", "Processing onAccuracyChanged event at %d.", Long.valueOf(System.currentTimeMillis()));
            ProximitySensor proximitySensor = (ProximitySensor) this.ProximitySensor$1$ar$this$0;
            proximitySensor.mShouldDropEvents = true;
            proximitySensor.mHandler.removeCallbacks(proximitySensor.mFilterRunnable);
            ProximitySensor proximitySensor2 = (ProximitySensor) this.ProximitySensor$1$ar$this$0;
            proximitySensor2.mHandler.postDelayed(proximitySensor2.mFilterRunnable, 120L);
        }

        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
            boolean z;
            boolean z2;
            TouchDots touchDots;
            boolean z3 = false;
            if (this.switching_field != 0) {
                int displayRotationDegrees = _BOUNDARY.getDisplayRotationDegrees(((Verifier) this.ProximitySensor$1$ar$this$0).context);
                float[] fArr = sensorEvent.values;
                int[] iArr = new int[][]{new int[]{1, -1, 0, 1}, new int[]{-1, -1, 1, 0}, new int[]{-1, 1, 0, 1}, new int[]{1, 1, 1, 0}}[displayRotationDegrees];
                float[] fArr2 = {iArr[0] * fArr[iArr[2]], iArr[1] * fArr[iArr[3]], fArr[2]};
                float f = fArr2[0];
                float f2 = fArr2[1];
                float f3 = fArr2[2];
                int round = (int) Math.round(Math.toDegrees(Math.acos(f3 / ((float) Math.sqrt(((f * f) + (f2 * f2)) + (f3 * f3))))));
                if (round >= 25 && round <= 155) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                boolean isEmpty = ((Optional) ((Verifier) this.ProximitySensor$1$ar$this$0).Verifier$ar$basePackageInfo).isEmpty();
                if (z2) {
                    touchDots = TouchDots.TABLETOP;
                } else {
                    touchDots = TouchDots.SCREEN_AWAY;
                }
                if (isEmpty || ((Optional) ((Verifier) this.ProximitySensor$1$ar$this$0).Verifier$ar$basePackageInfo).get() != touchDots) {
                    z3 = true;
                }
                ((Verifier) this.ProximitySensor$1$ar$this$0).Verifier$ar$basePackageInfo = Optional.of(touchDots);
                if (z3) {
                    ((RetryingNameResolver.ResolutionResultListener) ((Verifier) this.ProximitySensor$1$ar$this$0).Verifier$ar$manifestParser).onDetectionChanged(z2, isEmpty);
                    return;
                }
                return;
            }
            if (((ProximitySensor) this.ProximitySensor$1$ar$this$0).mShouldDropEvents) {
                LogUtils.v("ProximitySensor", "Dropping onSensorChanged event at %d.", Long.valueOf(System.currentTimeMillis()));
                return;
            }
            float f4 = sensorEvent.values[0];
            ProximitySensor proximitySensor = (ProximitySensor) this.ProximitySensor$1$ar$this$0;
            if (f4 < proximitySensor.mFarValue) {
                z = true;
            } else {
                z = false;
            }
            proximitySensor.mIsClose = z;
            if (z) {
                LogUtils.v("ProximitySensor", "Processing onSensorChanged event at %d.  Closed, distance=%s , farValue=%s ", Long.valueOf(System.currentTimeMillis()), Float.valueOf(f4), Float.valueOf(((ProximitySensor) this.ProximitySensor$1$ar$this$0).mFarValue));
            } else {
                LogUtils.v("ProximitySensor", "Processing onSensorChanged event at %d. ", Long.valueOf(System.currentTimeMillis()));
            }
            ProximitySensor proximitySensor2 = (ProximitySensor) this.ProximitySensor$1$ar$this$0;
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = proximitySensor2.mCallback$ar$class_merging$16a1d12_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (proximitySensor2.mIsClose) {
                ((Pipeline) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).interruptAllFeedback$ar$ds$404beace_0();
            }
        }
    }

    public ProximitySensor(Context context) {
        float f;
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        this.mSensorManager = sensorManager;
        Sensor defaultSensor = sensorManager.getDefaultSensor(8);
        this.mProxSensor = defaultSensor;
        if (defaultSensor != null) {
            f = Math.min(defaultSensor.getMaximumRange(), 5.0f);
        } else {
            f = 0.0f;
        }
        this.mFarValue = f;
    }

    public final void stop() {
        if (this.mProxSensor != null && this.mIsActive) {
            LogUtils.v("ProximitySensor", "Proximity sensor stopped at %d.", Long.valueOf(System.currentTimeMillis()));
            this.mIsActive = false;
            this.mSensorManager.unregisterListener(this.mListener);
        }
    }
}

package com.google.mlkit.logging.schema;

import android.bluetooth.BluetoothDevice;
import android.graphics.Rect;
import android.hardware.usb.UsbDevice;
import android.os.Build;
import android.support.v7.app.AppCompatDelegate;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsAnimation$Callback;
import android.view.animation.Interpolator;
import androidx.core.view.WindowInsetsAnimationCompat$Impl;
import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableBluetoothDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableUsbDevice;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.coordinate.MagnificationCoordinateConfig;
import com.google.android.accessibility.utils.output.FeedbackItem;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.usagereporting.internal.OptInOptionsResultImpl;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import j$.util.DesugarCollections;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.PathUtils$$ExternalSyntheticApiModelOutline2;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceTextDetectionLoadLogEvent {
    private static OnDeviceTextDetectionLoadLogEvent instance$ar$class_merging$dd985aa5_0$ar$class_merging;
    public Object OnDeviceTextDetectionLoadLogEvent$ar$errorCode;

    public OnDeviceTextDetectionLoadLogEvent() {
    }

    public static OnDeviceTextDetectionLoadLogEvent getInstance$ar$class_merging$8b242409_0$ar$class_merging() {
        if (instance$ar$class_merging$dd985aa5_0$ar$class_merging == null) {
            instance$ar$class_merging$dd985aa5_0$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
        }
        return instance$ar$class_merging$dd985aa5_0$ar$class_merging;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.accessibility.utils.output.SpeechControllerImpl$FeedbackItemPredicate, java.lang.Object] */
    public final boolean accept(FeedbackItem feedbackItem) {
        ?? r0 = this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        if (r0 != 0 && r0.accept(feedbackItem)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.accessibility.utils.output.SpeechControllerImpl$FeedbackItemPredicate, java.lang.Object] */
    public final void addFeedbackItemPredicate(SpeechControllerImpl.FeedbackItemPredicate feedbackItemPredicate) {
        ?? r0 = this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        if (r0 == 0) {
            this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = feedbackItemPredicate;
        } else {
            this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = new SpeechControllerImpl.FeedbackItemDisjunctionPredicateSet(r0, feedbackItemPredicate, 0);
        }
    }

    /* renamed from: build */
    public final TelemetryLoggingOptions m224build() {
        return new TelemetryLoggingOptions((String) this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode);
    }

    public final AccessibilityNodeInfoCompat.CollectionItemInfoCompat build$ar$class_merging$ar$class_merging$ar$class_merging() {
        return new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode, (byte[]) null);
    }

    public final void clear() {
        this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = null;
    }

    public final Rect getDisplayGlobal() {
        Object obj = this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        if (obj != null) {
            return ((MagnificationCoordinateConfig) obj).displayGlobal;
        }
        return null;
    }

    public final Rect getDisplayLocal() {
        Object obj = this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        if (obj != null) {
            return ((MagnificationCoordinateConfig) obj).displayLocal;
        }
        return null;
    }

    public final float getInterpolatedFraction() {
        return ((WindowInsetsAnimationCompat$Impl) this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode).getInterpolatedFraction();
    }

    public final InputStream next() {
        Object obj = this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = null;
        return (InputStream) obj;
    }

    public final void setBluetoothDevice$ar$ds(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = bluetoothDevice;
            return;
        }
        throw new NullPointerException("Null bluetoothDevice");
    }

    public final void setFraction(float f) {
        ((WindowInsetsAnimationCompat$Impl) this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode).setFraction(f);
    }

    public final OnDeviceTextDetectionLoadLogEvent setImages$ar$class_merging$ar$class_merging(List list) {
        if (list != null) {
            this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = list;
            return this;
        }
        throw new NullPointerException("Null images");
    }

    public final void setToolbarColor$ar$ds(int i) {
        this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = Integer.valueOf(i | (-16777216));
    }

    public final void setUsbDevice$ar$ds(UsbDevice usbDevice) {
        if (usbDevice != null) {
            this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = usbDevice;
            return;
        }
        throw new NullPointerException("Null usbDevice");
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    public final void speak(CharSequence charSequence, int i, SpeechController.SpeakOptions speakOptions) {
        Object obj = this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        if (obj == null) {
            AppCompatDelegate.Api33Impl.e("BrailleCommon", "BrailleCommonTalkBackSpeaker", "Instance does not init correctly.");
            return;
        }
        Logger logger = Performance.DEFAULT_LOGGER;
        Feedback.Part.Builder speech = Feedback.speech(charSequence, speakOptions);
        speech.setDelayMs$ar$ds(i);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) ((WindowTrackerFactory) obj).WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) null, speech);
    }

    protected OnDeviceTextDetectionLoadLogEvent(Result result) {
        this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = result;
    }

    public final ConnectableBluetoothDevice build() {
        Object obj = this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        if (obj != null) {
            return new ConnectableBluetoothDevice((BluetoothDevice) obj);
        }
        throw new IllegalStateException("Missing required properties: bluetoothDevice");
    }

    public OnDeviceTextDetectionLoadLogEvent(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
    }

    /* renamed from: build */
    public final ConnectableUsbDevice m223build() {
        Object obj = this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
        if (obj != null) {
            return new ConnectableUsbDevice((UsbDevice) obj);
        }
        throw new IllegalStateException("Missing required properties: usbDevice");
    }

    public OnDeviceTextDetectionLoadLogEvent(InputStream inputStream) {
        this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = inputStream;
    }

    public OnDeviceTextDetectionLoadLogEvent(byte[] bArr, byte[] bArr2, char[] cArr) {
        this();
    }

    public OnDeviceTextDetectionLoadLogEvent(byte[] bArr, byte[] bArr2, char[] cArr, byte[] bArr3) {
        this();
    }

    public OnDeviceTextDetectionLoadLogEvent(byte[] bArr, char[] cArr, byte[] bArr2, byte[] bArr3) {
        this();
    }

    public OnDeviceTextDetectionLoadLogEvent(short[] sArr, byte[] bArr, byte[] bArr2) {
        this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = null;
    }

    public OnDeviceTextDetectionLoadLogEvent(int i, Interpolator interpolator, long j) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = new WindowInsetsAnimationCompat$Impl(new WindowInsetsAnimation(i, interpolator, j)) { // from class: androidx.core.view.WindowInsetsAnimationCompat$Impl30
                private final WindowInsetsAnimation mWrapped;

                /* compiled from: PG */
                /* loaded from: classes.dex */
                public final class ProxyCallback extends WindowInsetsAnimation$Callback {
                    private final HashMap mAnimations;
                    private final WindowInsetsAnimationCompat$Callback mCompat;
                    private List mRORunningAnimations;
                    private ArrayList mTmpRunningAnimations;

                    public ProxyCallback(WindowInsetsAnimationCompat$Callback windowInsetsAnimationCompat$Callback) {
                        super(0);
                        this.mAnimations = new HashMap();
                        this.mCompat = windowInsetsAnimationCompat$Callback;
                    }

                    private final OnDeviceTextDetectionLoadLogEvent getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(WindowInsetsAnimation windowInsetsAnimation) {
                        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = (OnDeviceTextDetectionLoadLogEvent) this.mAnimations.get(windowInsetsAnimation);
                        if (onDeviceTextDetectionLoadLogEvent == null) {
                            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent2 = new OnDeviceTextDetectionLoadLogEvent(windowInsetsAnimation);
                            this.mAnimations.put(windowInsetsAnimation, onDeviceTextDetectionLoadLogEvent2);
                            return onDeviceTextDetectionLoadLogEvent2;
                        }
                        return onDeviceTextDetectionLoadLogEvent;
                    }

                    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                        getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(windowInsetsAnimation);
                        this.mCompat.onEnd$ar$ds();
                        this.mAnimations.remove(windowInsetsAnimation);
                    }

                    public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                        getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(windowInsetsAnimation);
                        this.mCompat.onPrepare$ar$ds();
                    }

                    public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
                        float fraction;
                        ArrayList arrayList = this.mTmpRunningAnimations;
                        if (arrayList == null) {
                            ArrayList arrayList2 = new ArrayList(list.size());
                            this.mTmpRunningAnimations = arrayList2;
                            this.mRORunningAnimations = DesugarCollections.unmodifiableList(arrayList2);
                        } else {
                            arrayList.clear();
                        }
                        int size = list.size();
                        while (true) {
                            size--;
                            if (size >= 0) {
                                WindowInsetsAnimation m300m = PathUtils$$ExternalSyntheticApiModelOutline2.m300m(list.get(size));
                                OnDeviceTextDetectionLoadLogEvent windowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(m300m);
                                fraction = m300m.getFraction();
                                windowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.setFraction(fraction);
                                this.mTmpRunningAnimations.add(windowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                            } else {
                                WindowInsetsAnimationCompat$Callback windowInsetsAnimationCompat$Callback = this.mCompat;
                                WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets);
                                windowInsetsAnimationCompat$Callback.onProgress$ar$ds$82a9f68b_0(windowInsetsCompat, this.mRORunningAnimations);
                                return windowInsetsCompat.toWindowInsets();
                            }
                        }
                    }

                    public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
                        getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(windowInsetsAnimation);
                        WindowInsetsAnimationCompat$BoundsCompat windowInsetsAnimationCompat$BoundsCompat = new WindowInsetsAnimationCompat$BoundsCompat(bounds);
                        this.mCompat.onStart$ar$ds(windowInsetsAnimationCompat$BoundsCompat);
                        return new WindowInsetsAnimation.Bounds(windowInsetsAnimationCompat$BoundsCompat.mLowerBound.toPlatformInsets(), windowInsetsAnimationCompat$BoundsCompat.mUpperBound.toPlatformInsets());
                    }
                }

                {
                    super(0, null, 0L);
                    this.mWrapped = r5;
                }

                @Override // androidx.core.view.WindowInsetsAnimationCompat$Impl
                public final long getDurationMillis() {
                    long durationMillis;
                    durationMillis = this.mWrapped.getDurationMillis();
                    return durationMillis;
                }

                @Override // androidx.core.view.WindowInsetsAnimationCompat$Impl
                public final float getInterpolatedFraction() {
                    float interpolatedFraction;
                    interpolatedFraction = this.mWrapped.getInterpolatedFraction();
                    return interpolatedFraction;
                }

                @Override // androidx.core.view.WindowInsetsAnimationCompat$Impl
                public final int getTypeMask() {
                    int typeMask;
                    typeMask = this.mWrapped.getTypeMask();
                    return typeMask;
                }

                @Override // androidx.core.view.WindowInsetsAnimationCompat$Impl
                public final void setFraction(float f) {
                    this.mWrapped.setFraction(f);
                }
            };
        } else {
            this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = new WindowInsetsAnimationCompat$Impl21(i, interpolator, j);
        }
    }

    public OnDeviceTextDetectionLoadLogEvent(OptInOptionsResultImpl optInOptionsResultImpl) {
        this((Result) optInOptionsResultImpl);
    }

    public OnDeviceTextDetectionLoadLogEvent(SwipeDismissBehavior swipeDismissBehavior) {
        swipeDismissBehavior.alphaStartSwipeDistance = SwipeDismissBehavior.clamp$ar$ds(0.1f);
        swipeDismissBehavior.alphaEndSwipeDistance = SwipeDismissBehavior.clamp$ar$ds(0.6f);
        swipeDismissBehavior.swipeDirection = 0;
    }

    public OnDeviceTextDetectionLoadLogEvent(WindowInsetsAnimation windowInsetsAnimation) {
        this(0, (Interpolator) null, 0L);
        if (Build.VERSION.SDK_INT >= 30) {
            this.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = new WindowInsetsAnimationCompat$Impl(windowInsetsAnimation) { // from class: androidx.core.view.WindowInsetsAnimationCompat$Impl30
                private final WindowInsetsAnimation mWrapped;

                /* compiled from: PG */
                /* loaded from: classes.dex */
                public final class ProxyCallback extends WindowInsetsAnimation$Callback {
                    private final HashMap mAnimations;
                    private final WindowInsetsAnimationCompat$Callback mCompat;
                    private List mRORunningAnimations;
                    private ArrayList mTmpRunningAnimations;

                    public ProxyCallback(WindowInsetsAnimationCompat$Callback windowInsetsAnimationCompat$Callback) {
                        super(0);
                        this.mAnimations = new HashMap();
                        this.mCompat = windowInsetsAnimationCompat$Callback;
                    }

                    private final OnDeviceTextDetectionLoadLogEvent getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(WindowInsetsAnimation windowInsetsAnimation) {
                        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = (OnDeviceTextDetectionLoadLogEvent) this.mAnimations.get(windowInsetsAnimation);
                        if (onDeviceTextDetectionLoadLogEvent == null) {
                            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent2 = new OnDeviceTextDetectionLoadLogEvent(windowInsetsAnimation);
                            this.mAnimations.put(windowInsetsAnimation, onDeviceTextDetectionLoadLogEvent2);
                            return onDeviceTextDetectionLoadLogEvent2;
                        }
                        return onDeviceTextDetectionLoadLogEvent;
                    }

                    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                        getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(windowInsetsAnimation);
                        this.mCompat.onEnd$ar$ds();
                        this.mAnimations.remove(windowInsetsAnimation);
                    }

                    public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                        getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(windowInsetsAnimation);
                        this.mCompat.onPrepare$ar$ds();
                    }

                    public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
                        float fraction;
                        ArrayList arrayList = this.mTmpRunningAnimations;
                        if (arrayList == null) {
                            ArrayList arrayList2 = new ArrayList(list.size());
                            this.mTmpRunningAnimations = arrayList2;
                            this.mRORunningAnimations = DesugarCollections.unmodifiableList(arrayList2);
                        } else {
                            arrayList.clear();
                        }
                        int size = list.size();
                        while (true) {
                            size--;
                            if (size >= 0) {
                                WindowInsetsAnimation m300m = PathUtils$$ExternalSyntheticApiModelOutline2.m300m(list.get(size));
                                OnDeviceTextDetectionLoadLogEvent windowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(m300m);
                                fraction = m300m.getFraction();
                                windowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.setFraction(fraction);
                                this.mTmpRunningAnimations.add(windowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                            } else {
                                WindowInsetsAnimationCompat$Callback windowInsetsAnimationCompat$Callback = this.mCompat;
                                WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets);
                                windowInsetsAnimationCompat$Callback.onProgress$ar$ds$82a9f68b_0(windowInsetsCompat, this.mRORunningAnimations);
                                return windowInsetsCompat.toWindowInsets();
                            }
                        }
                    }

                    public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
                        getWindowInsetsAnimationCompat$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(windowInsetsAnimation);
                        WindowInsetsAnimationCompat$BoundsCompat windowInsetsAnimationCompat$BoundsCompat = new WindowInsetsAnimationCompat$BoundsCompat(bounds);
                        this.mCompat.onStart$ar$ds(windowInsetsAnimationCompat$BoundsCompat);
                        return new WindowInsetsAnimation.Bounds(windowInsetsAnimationCompat$BoundsCompat.mLowerBound.toPlatformInsets(), windowInsetsAnimationCompat$BoundsCompat.mUpperBound.toPlatformInsets());
                    }
                }

                {
                    super(0, null, 0L);
                    this.mWrapped = windowInsetsAnimation;
                }

                @Override // androidx.core.view.WindowInsetsAnimationCompat$Impl
                public final long getDurationMillis() {
                    long durationMillis;
                    durationMillis = this.mWrapped.getDurationMillis();
                    return durationMillis;
                }

                @Override // androidx.core.view.WindowInsetsAnimationCompat$Impl
                public final float getInterpolatedFraction() {
                    float interpolatedFraction;
                    interpolatedFraction = this.mWrapped.getInterpolatedFraction();
                    return interpolatedFraction;
                }

                @Override // androidx.core.view.WindowInsetsAnimationCompat$Impl
                public final int getTypeMask() {
                    int typeMask;
                    typeMask = this.mWrapped.getTypeMask();
                    return typeMask;
                }

                @Override // androidx.core.view.WindowInsetsAnimationCompat$Impl
                public final void setFraction(float f) {
                    this.mWrapped.setFraction(f);
                }
            };
        }
    }
}

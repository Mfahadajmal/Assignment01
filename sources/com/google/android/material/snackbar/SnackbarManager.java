package com.google.android.material.snackbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.input.TextEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.gms.clearcut.AbstractClearcutLogger;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.PIILevel;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.storage.protostore.loggers.NoOpLogger;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Optional;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SnackbarManager {
    private static SnackbarManager snackbarManager;
    public Object SnackbarManager$ar$currentSnackbar;
    public final Object SnackbarManager$ar$handler;
    public Object SnackbarManager$ar$nextSnackbar;
    public final Object lock;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SnackbarRecord {
        public final Object SnackbarManager$SnackbarRecord$ar$callback;
        public int duration;
        public boolean paused;

        public SnackbarRecord(FloatingActionButton floatingActionButton) {
            this.paused = false;
            this.duration = 0;
            this.SnackbarManager$SnackbarRecord$ar$callback = floatingActionButton;
        }

        final boolean isSnackbar$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
            if (resolutionResultListener != null && ((WeakReference) this.SnackbarManager$SnackbarRecord$ar$callback).get() == resolutionResultListener) {
                return true;
            }
            return false;
        }

        public SnackbarRecord(int i, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
            this.SnackbarManager$SnackbarRecord$ar$callback = new WeakReference(resolutionResultListener);
            this.duration = i;
        }
    }

    public SnackbarManager(ProcessorPhoneticLetters processorPhoneticLetters) {
        this.SnackbarManager$ar$nextSnackbar = Optional.empty();
        this.SnackbarManager$ar$currentSnackbar = Optional.empty();
        this.lock = new ConcurrentHashMap();
        this.SnackbarManager$ar$handler = processorPhoneticLetters;
    }

    public static SnackbarManager getInstance() {
        if (snackbarManager == null) {
            snackbarManager = new SnackbarManager();
        }
        return snackbarManager;
    }

    public static CharSequence getIterableTextForAccessibility(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        CharSequence text = AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat);
        if (AccessibilityNodeInfoUtils.isTextSelectable(accessibilityNodeInfoCompat) && !TextUtils.isEmpty(text)) {
            return text;
        }
        return accessibilityNodeInfoCompat.getContentDescription();
    }

    public static boolean isExternalDevice(AudioDeviceInfo audioDeviceInfo) {
        if (!audioDeviceInfo.isSink()) {
            return false;
        }
        if (audioDeviceInfo.getType() == 8 || audioDeviceInfo.getType() == 7 || audioDeviceInfo.getType() == 26 || audioDeviceInfo.getType() == 19 || audioDeviceInfo.getType() == 4 || audioDeviceInfo.getType() == 3) {
            return true;
        }
        if (audioDeviceInfo.getType() != 22) {
            return false;
        }
        return true;
    }

    public static boolean isHeadphoneOn(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            return false;
        }
        for (AudioDeviceInfo audioDeviceInfo : audioManager.getDevices(2)) {
            if (isExternalDevice(audioDeviceInfo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean shouldHandleGranularityTraversalInTalkback(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (WebInterfaceUtils.isWebContainer(accessibilityNodeInfoCompat)) {
            LogUtils.v("GranularityTraversal", "Granularity traversal not handled by Talkback since its a webview", new Object[0]);
            return false;
        }
        if ((SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4 && accessibilityNodeInfoCompat.isFocused()) || AccessibilityNodeInfoUtils.isNonEditableSelectableText(accessibilityNodeInfoCompat)) {
            LogUtils.v("GranularityTraversal", "Granularity traversal not handled by Talkback as node is focused", new Object[0]);
            return false;
        }
        if (TextUtils.isEmpty(getIterableTextForAccessibility(accessibilityNodeInfoCompat))) {
            LogUtils.v("GranularityTraversal", "Granularity traversal not handled by Talkback as iterable text is null or empty string", new Object[0]);
            return false;
        }
        LogUtils.d("GranularityTraversal", "Granularity traversal handled by Talkback", new Object[0]);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.animation.Animator$AnimatorListener, java.lang.Object] */
    public final void addState(int[] iArr, ValueAnimator valueAnimator) {
        RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = new RemoteModelManager.RemoteModelManagerRegistration(iArr, valueAnimator);
        valueAnimator.addListener(this.SnackbarManager$ar$handler);
        ((ArrayList) this.lock).add(remoteModelManagerRegistration);
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [com.google.common.base.Supplier, java.lang.Object] */
    public final ClearcutLogger build() {
        Object obj = this.SnackbarManager$ar$currentSnackbar;
        ?? r7 = this.SnackbarManager$ar$nextSnackbar;
        EnumSet enumSet = (EnumSet) obj;
        return new ClearcutLogger((Context) this.SnackbarManager$ar$handler, (String) this.lock, null, enumSet, null, null, r7);
    }

    public final boolean cancelSnackbarLocked(SnackbarRecord snackbarRecord, int i) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) ((WeakReference) snackbarRecord.SnackbarManager$SnackbarRecord$ar$callback).get();
        if (resolutionResultListener != null) {
            ((Handler) this.SnackbarManager$ar$handler).removeCallbacksAndMessages(snackbarRecord);
            resolutionResultListener.dismiss(i);
            return true;
        }
        return false;
    }

    public final AudioDeviceCallback getAudioDeviceCallback() {
        if (this.SnackbarManager$ar$nextSnackbar == null) {
            this.SnackbarManager$ar$nextSnackbar = new AudioDeviceCallback() { // from class: com.google.android.accessibility.utils.monitor.HeadphoneStateMonitor$1
                /* JADX WARN: Type inference failed for: r3v2, types: [java.util.Set, java.lang.Object] */
                @Override // android.media.AudioDeviceCallback
                public final void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
                    for (AudioDeviceInfo audioDeviceInfo : audioDeviceInfoArr) {
                        if (SnackbarManager.isExternalDevice(audioDeviceInfo)) {
                            SnackbarManager.this.lock.add(Integer.valueOf(audioDeviceInfo.getId()));
                        }
                    }
                    SnackbarManager snackbarManager2 = SnackbarManager.this;
                    Object obj = snackbarManager2.SnackbarManager$ar$currentSnackbar;
                    if (obj != null) {
                        ((HapticPatternParser$$ExternalSyntheticLambda1) obj).onHeadphoneStateChanged(snackbarManager2.hasHeadphones());
                    }
                }

                /* JADX WARN: Type inference failed for: r3v2, types: [java.util.Set, java.lang.Object] */
                @Override // android.media.AudioDeviceCallback
                public final void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
                    for (AudioDeviceInfo audioDeviceInfo : audioDeviceInfoArr) {
                        if (SnackbarManager.isExternalDevice(audioDeviceInfo)) {
                            SnackbarManager.this.lock.remove(Integer.valueOf(audioDeviceInfo.getId()));
                        }
                    }
                    SnackbarManager snackbarManager2 = SnackbarManager.this;
                    Object obj = snackbarManager2.SnackbarManager$ar$currentSnackbar;
                    if (obj != null) {
                        ((HapticPatternParser$$ExternalSyntheticLambda1) obj).onHeadphoneStateChanged(snackbarManager2.hasHeadphones());
                    }
                }
            };
        }
        return (AudioDeviceCallback) this.SnackbarManager$ar$nextSnackbar;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Set, java.lang.Object] */
    public final boolean hasHeadphones() {
        if (!this.lock.isEmpty()) {
            return true;
        }
        return false;
    }

    public final boolean isCurrentSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        Object obj = this.SnackbarManager$ar$currentSnackbar;
        if (obj != null && ((SnackbarRecord) obj).isSnackbar$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
            return true;
        }
        return false;
    }

    public final boolean isNextSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        Object obj = this.SnackbarManager$ar$nextSnackbar;
        if (obj != null && ((SnackbarRecord) obj).isSnackbar$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
            return true;
        }
        return false;
    }

    public final void pauseTimeout$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        synchronized (this.lock) {
            if (isCurrentSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                Object obj = this.SnackbarManager$ar$currentSnackbar;
                if (!((SnackbarRecord) obj).paused) {
                    ((SnackbarRecord) obj).paused = true;
                    ((Handler) this.SnackbarManager$ar$handler).removeCallbacksAndMessages(obj);
                }
            }
        }
    }

    public final void restoreTimeoutIfPaused$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        synchronized (this.lock) {
            if (isCurrentSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                Object obj = this.SnackbarManager$ar$currentSnackbar;
                if (((SnackbarRecord) obj).paused) {
                    ((SnackbarRecord) obj).paused = false;
                    scheduleTimeoutLocked((SnackbarRecord) obj);
                }
            }
        }
    }

    public final void scheduleTimeoutLocked(SnackbarRecord snackbarRecord) {
        int i = snackbarRecord.duration;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            if (i == -1) {
                i = 1500;
            } else {
                i = 2750;
            }
        }
        ((Handler) this.SnackbarManager$ar$handler).removeCallbacksAndMessages(snackbarRecord);
        Handler handler = (Handler) this.SnackbarManager$ar$handler;
        handler.sendMessageDelayed(Message.obtain(handler, 0, snackbarRecord), i);
    }

    public final void sendViewTextTraversedAtGranularityEvent(int i, int i2, CharSequence charSequence, int i3, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        CharSequence subsequenceWithSpans = TextEventInterpreter.getSubsequenceWithSpans(charSequence, Math.min(i, i2), Math.max(i, i2));
        if (((Optional) this.SnackbarManager$ar$currentSnackbar).isPresent()) {
            LogUtils.d("GranularityTraversal", "sendViewTextTraversedAtGranularityEvent: ".concat(String.valueOf(String.valueOf(subsequenceWithSpans))), new Object[0]);
            Object obj = ((FloatingActionButton.ShadowDelegateImpl) ((Optional) this.SnackbarManager$ar$currentSnackbar).get()).FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            Logger logger = Performance.DEFAULT_LOGGER;
            Pipeline.m94$$Nest$minputEvent$ar$ds((Pipeline) obj, new Pipeline.SyntheticEvent(2, subsequenceWithSpans));
        }
        boolean isTalkBackPackage = SpannableUtils$IdentifierSpan.isTalkBackPackage(accessibilityNodeInfoCompat.getPackageName());
        if (i3 == CursorGranularity.CHARACTER.value) {
            ((ProcessorPhoneticLetters) this.SnackbarManager$ar$handler).speakPhoneticLetterForTraversedText(isTalkBackPackage, subsequenceWithSpans.toString(), eventId);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    public final void setCursorPosition(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        this.lock.put(accessibilityNodeInfoCompat, Integer.valueOf(i));
    }

    public final SnackbarManager setPiiLevelSet$ar$class_merging$ar$class_merging$ar$class_merging(EnumSet enumSet) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(enumSet);
        this.SnackbarManager$ar$currentSnackbar = enumSet;
        AbstractClearcutLogger.checkPIILevelSet(enumSet);
        return this;
    }

    public final void showNextSnackbarLocked() {
        Object obj = this.SnackbarManager$ar$nextSnackbar;
        if (obj != null) {
            this.SnackbarManager$ar$currentSnackbar = obj;
            this.SnackbarManager$ar$nextSnackbar = null;
            RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) ((WeakReference) ((SnackbarRecord) obj).SnackbarManager$SnackbarRecord$ar$callback).get();
            if (resolutionResultListener != null) {
                resolutionResultListener.show();
            } else {
                this.SnackbarManager$ar$currentSnackbar = null;
            }
        }
    }

    public SnackbarManager(Context context) {
        this.lock = new HashSet();
        this.SnackbarManager$ar$handler = context;
    }

    protected SnackbarManager(Context context, String str) {
        this.SnackbarManager$ar$nextSnackbar = new PrimesController$$ExternalSyntheticLambda9(6);
        this.SnackbarManager$ar$currentSnackbar = PIILevel.noRestrictions;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(context);
        this.SnackbarManager$ar$handler = context;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds(str);
        this.lock = str;
    }

    public SnackbarManager(Context context, String str, byte[] bArr) {
        this(context, str);
    }

    public SnackbarManager(Context context, byte[] bArr) {
        this(context, "FIREBASE_ML_SDK");
    }

    public SnackbarManager(byte[] bArr, byte[] bArr2) {
        this.SnackbarManager$ar$handler = NoOpLogger.INSTANCE;
        this.lock = new HashMap();
    }

    public SnackbarManager(byte[] bArr) {
        this.lock = new ArrayList();
        this.SnackbarManager$ar$currentSnackbar = null;
        this.SnackbarManager$ar$nextSnackbar = null;
        this.SnackbarManager$ar$handler = new AnimatorListenerAdapter() { // from class: com.google.android.material.internal.StateListAnimator$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                SnackbarManager snackbarManager2 = SnackbarManager.this;
                if (snackbarManager2.SnackbarManager$ar$nextSnackbar == animator) {
                    snackbarManager2.SnackbarManager$ar$nextSnackbar = null;
                }
            }
        };
    }

    private SnackbarManager() {
        this.lock = new Object();
        this.SnackbarManager$ar$handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.SnackbarManager.1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                if (message.what != 0) {
                    return false;
                }
                SnackbarManager snackbarManager2 = SnackbarManager.this;
                SnackbarRecord snackbarRecord = (SnackbarRecord) message.obj;
                synchronized (snackbarManager2.lock) {
                    if (snackbarManager2.SnackbarManager$ar$currentSnackbar == snackbarRecord || snackbarManager2.SnackbarManager$ar$nextSnackbar == snackbarRecord) {
                        snackbarManager2.cancelSnackbarLocked(snackbarRecord, 2);
                    }
                }
                return true;
            }
        });
    }
}

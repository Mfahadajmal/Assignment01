package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v7.view.menu.CascadingMenuPopup;
import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.work.Configuration;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkGenerationalId;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.libraries.phenotype.client.Phlogger$PhClient;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.MLKitEnum$EventName;
import com.google.mlkit.logging.schema.MLKitSdkLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.mlkit.logging.schema.SystemInfo;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.shared.logger.SchemaLogEvent;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.DelayedStream;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.SharedResourceHolder;
import j$.util.Objects;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowInsetsAnimationCompat$Impl21 extends WindowInsetsAnimationCompat$Impl {
    public static final Interpolator SHOW_IME_INTERPOLATOR = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);
    public static final Interpolator HIDE_IME_INTERPOLATOR = new FastOutLinearInInterpolator();
    public static final Interpolator DEFAULT_INSET_INTERPOLATOR = new DecelerateInterpolator();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Impl21OnApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        final WindowInsetsAnimationCompat$Callback mCallback;
        private WindowInsetsCompat mLastInsets;

        /* compiled from: PG */
        /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass3 implements Runnable {
            final /* synthetic */ Object WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim;
            final /* synthetic */ Object WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds;
            final /* synthetic */ Object WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator;
            final /* synthetic */ Object WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v;
            private final /* synthetic */ int switching_field;

            public AnonymousClass3(CascadingMenuPopup.AnonymousClass3 anonymousClass3, PhenotypeProcessReaper phenotypeProcessReaper, MenuItem menuItem, MenuBuilder menuBuilder, int i) {
                this.switching_field = i;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator = anonymousClass3;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v = phenotypeProcessReaper;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim = menuItem;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds = menuBuilder;
            }

            /* JADX WARN: Finally extract failed */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v13, types: [java.util.List, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, android.view.MenuItem] */
            /* JADX WARN: Type inference failed for: r1v43, types: [io.grpc.internal.SharedResourceHolder$Resource, java.lang.Object] */
            @Override // java.lang.Runnable
            public final void run() {
                String mlSdkInstanceId;
                switch (this.switching_field) {
                    case 0:
                        Object obj = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v;
                        View view = (View) obj;
                        WindowInsetsAnimationCompat$Impl21.dispatchOnStart$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(view, (OnDeviceTextDetectionLoadLogEvent) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim, (WindowInsetsAnimationCompat$BoundsCompat) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds);
                        ((ValueAnimator) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator).start();
                        return;
                    case 1:
                        Object obj2 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v;
                        if (obj2 != null) {
                            CascadingMenuPopup.this.mShouldCloseImmediately = true;
                            ((MenuBuilder) ((PhenotypeProcessReaper) obj2).PhenotypeProcessReaper$ar$isKillable).close(false);
                            CascadingMenuPopup.this.mShouldCloseImmediately = false;
                        }
                        ?? r0 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim;
                        if (r0.isEnabled() && r0.hasSubMenu()) {
                            ((MenuBuilder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).performItemAction(r0, 4);
                            return;
                        }
                        return;
                    case 2:
                        ?? r02 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim;
                        int i = Schedulers.Schedulers$ar$NoOp;
                        Iterator it = r02.iterator();
                        while (it.hasNext()) {
                            ((Scheduler) it.next()).cancel(((WorkGenerationalId) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).workSpecId);
                        }
                        Schedulers.schedule((Configuration) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator, (WorkDatabase) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v, r02);
                        return;
                    case 3:
                        ((GoogleLogger.Api) ((GoogleLogger.Api) Phlogger$PhClient.logger.at((Level) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).withCause((Throwable) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v)).withInjectedLogSite("com/google/android/libraries/phenotype/client/Phlogger", "lambda$logInternal$0", 49, "Phlogger.java")).logVarargs((String) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator, (Object[]) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim);
                        return;
                    case 4:
                        SchemaLogEvent schemaLogEvent = (SchemaLogEvent) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds;
                        MLKitSdkLogEvent mLKitSdkLogEvent = schemaLogEvent.builder$ar$class_merging$afa8b912_0;
                        mLKitSdkLogEvent.eventName = (MLKitEnum$EventName) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator;
                        SystemInfo systemInfo = new MLKitSdkLogEvent(mLKitSdkLogEvent).systemInfo;
                        Object obj3 = "NA";
                        if (systemInfo != null && !ContextDataProvider.stringIsNullOrEmpty((String) systemInfo.SystemInfo$ar$tfliteSchemaVersion)) {
                            obj3 = systemInfo.SystemInfo$ar$tfliteSchemaVersion;
                            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(obj3);
                        }
                        Object obj4 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim;
                        Object obj5 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v;
                        SystemInfo systemInfo2 = new SystemInfo();
                        MLKitStatsLogger mLKitStatsLogger = (MLKitStatsLogger) obj5;
                        systemInfo2.SystemInfo$ar$appId = mLKitStatsLogger.appPackageName;
                        systemInfo2.SystemInfo$ar$appVersion = mLKitStatsLogger.appVersion;
                        systemInfo2.SystemInfo$ar$languages = MLKitStatsLogger.getLanguages();
                        systemInfo2.SystemInfo$ar$isStandaloneMlkit = true;
                        systemInfo2.SystemInfo$ar$tfliteSchemaVersion = obj3;
                        systemInfo2.SystemInfo$ar$mlSdkVersion = obj4;
                        if (mLKitStatsLogger.mlSdkInstanceIdTask.isSuccessful()) {
                            mlSdkInstanceId = (String) mLKitStatsLogger.mlSdkInstanceIdTask.getResult();
                        } else {
                            mlSdkInstanceId = mLKitStatsLogger.sharedPrefManager.getMlSdkInstanceId();
                        }
                        systemInfo2.SystemInfo$ar$mlSdkInstanceId = mlSdkInstanceId;
                        Integer num = 10;
                        num.getClass();
                        systemInfo2.SystemInfo$ar$buildLevel = 10;
                        systemInfo2.SystemInfo$ar$optionalModuleVersion = Integer.valueOf(mLKitStatsLogger.optionalModuleVersion);
                        schemaLogEvent.systemInfoBuilder$ar$class_merging = systemInfo2;
                        mLKitStatsLogger.loggingTransport.logSdkEvent$ar$class_merging(schemaLogEvent);
                        return;
                    case 5:
                        Object obj6 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator;
                        Object obj7 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim;
                        ((AbstractStream.TransportState) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).closeListener((Status) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v, (ClientStreamListener.RpcProgress) obj7, (Metadata) obj6);
                        return;
                    case 6:
                        Object obj8 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator;
                        Object obj9 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds;
                        ((DelayedStream.DelayedStreamListener) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim).realListener.closed((Status) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v, (ClientStreamListener.RpcProgress) obj9, (Metadata) obj8);
                        return;
                    case 7:
                        ((RetriableStream) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim).isClosed = true;
                        Object obj10 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator;
                        Object obj11 = this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds;
                        ((RetriableStream) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim).masterListener.closed((Status) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v, (ClientStreamListener.RpcProgress) obj11, (Metadata) obj10);
                        return;
                    default:
                        synchronized (this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds) {
                            if (((SharedResourceHolder.Instance) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator).refcount == 0) {
                                try {
                                    this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v.close(this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim);
                                    ((SharedResourceHolder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).instances.remove(this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v);
                                    if (((SharedResourceHolder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).instances.isEmpty()) {
                                        ((SharedResourceHolder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).destroyer.shutdown();
                                        ((SharedResourceHolder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).destroyer = null;
                                    }
                                } catch (Throwable th) {
                                    ((SharedResourceHolder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).instances.remove(this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v);
                                    if (((SharedResourceHolder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).instances.isEmpty()) {
                                        ((SharedResourceHolder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).destroyer.shutdown();
                                        ((SharedResourceHolder) this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds).destroyer = null;
                                    }
                                    throw th;
                                }
                            }
                        }
                        return;
                }
            }

            public AnonymousClass3(View view, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent, WindowInsetsAnimationCompat$BoundsCompat windowInsetsAnimationCompat$BoundsCompat, ValueAnimator valueAnimator, int i) {
                this.switching_field = i;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v = view;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim = onDeviceTextDetectionLoadLogEvent;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds = windowInsetsAnimationCompat$BoundsCompat;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator = valueAnimator;
            }

            public /* synthetic */ AnonymousClass3(MLKitStatsLogger mLKitStatsLogger, SchemaLogEvent schemaLogEvent, MLKitEnum$EventName mLKitEnum$EventName, String str, int i) {
                this.switching_field = i;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v = mLKitStatsLogger;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds = schemaLogEvent;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator = mLKitEnum$EventName;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim = str;
            }

            public AnonymousClass3(AbstractStream.TransportState transportState, Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata, int i) {
                this.switching_field = i;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v = status;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim = rpcProgress;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator = metadata;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds = transportState;
            }

            public AnonymousClass3(SharedResourceHolder sharedResourceHolder, SharedResourceHolder.Instance instance, SharedResourceHolder.Resource resource, Object obj, int i) {
                this.switching_field = i;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator = instance;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v = resource;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim = obj;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds = sharedResourceHolder;
            }

            public AnonymousClass3(Object obj, Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata, int i) {
                this.switching_field = i;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v = status;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds = rpcProgress;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator = metadata;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim = obj;
            }

            public /* synthetic */ AnonymousClass3(List list, WorkGenerationalId workGenerationalId, Configuration configuration, WorkDatabase workDatabase, int i) {
                this.switching_field = i;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim = list;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds = workGenerationalId;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator = configuration;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v = workDatabase;
            }

            public /* synthetic */ AnonymousClass3(Level level, Throwable th, String str, Object[] objArr, int i) {
                this.switching_field = i;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animationBounds = level;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$v = th;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$animator = str;
                this.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3$ar$val$anim = objArr;
            }
        }

        public Impl21OnApplyWindowInsetsListener(View view, WindowInsetsAnimationCompat$Callback windowInsetsAnimationCompat$Callback) {
            WindowInsetsCompat windowInsetsCompat;
            this.mCallback = windowInsetsAnimationCompat$Callback;
            WindowInsetsCompat rootWindowInsets = ViewCompat.Api23Impl.getRootWindowInsets(view);
            if (rootWindowInsets != null) {
                windowInsetsCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(rootWindowInsets).m31build();
            } else {
                windowInsetsCompat = null;
            }
            this.mLastInsets = windowInsetsCompat;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public final WindowInsets onApplyWindowInsets(final View view, WindowInsets windowInsets) {
            Interpolator interpolator;
            if (!view.isLaidOut()) {
                this.mLastInsets = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                return WindowInsetsAnimationCompat$Impl21.forwardToViewIfNeeded(view, windowInsets);
            }
            final WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
            if (this.mLastInsets == null) {
                this.mLastInsets = ViewCompat.Api23Impl.getRootWindowInsets(view);
            }
            if (this.mLastInsets == null) {
                this.mLastInsets = windowInsetsCompat;
                return WindowInsetsAnimationCompat$Impl21.forwardToViewIfNeeded(view, windowInsets);
            }
            WindowInsetsAnimationCompat$Callback callback = WindowInsetsAnimationCompat$Impl21.getCallback(view);
            if (callback != null && Objects.equals(callback.mDispachedInsets, windowInsets)) {
                return WindowInsetsAnimationCompat$Impl21.forwardToViewIfNeeded(view, windowInsets);
            }
            WindowInsetsCompat windowInsetsCompat2 = this.mLastInsets;
            final int i = 0;
            for (int i2 = 1; i2 <= 256; i2 += i2) {
                if (!windowInsetsCompat.getInsets(i2).equals(windowInsetsCompat2.getInsets(i2))) {
                    i |= i2;
                }
            }
            if (i == 0) {
                return WindowInsetsAnimationCompat$Impl21.forwardToViewIfNeeded(view, windowInsets);
            }
            final WindowInsetsCompat windowInsetsCompat3 = this.mLastInsets;
            if ((i & 8) != 0) {
                if (windowInsetsCompat.getInsets(8).bottom > windowInsetsCompat3.getInsets(8).bottom) {
                    interpolator = WindowInsetsAnimationCompat$Impl21.SHOW_IME_INTERPOLATOR;
                } else {
                    interpolator = WindowInsetsAnimationCompat$Impl21.HIDE_IME_INTERPOLATOR;
                }
            } else {
                interpolator = WindowInsetsAnimationCompat$Impl21.DEFAULT_INSET_INTERPOLATOR;
            }
            final OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent(i, interpolator, 160L);
            onDeviceTextDetectionLoadLogEvent.setFraction(0.0f);
            ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(((WindowInsetsAnimationCompat$Impl) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode).getDurationMillis());
            Insets insets = windowInsetsCompat.getInsets(i);
            Insets insets2 = windowInsetsCompat3.getInsets(i);
            WindowInsetsAnimationCompat$BoundsCompat windowInsetsAnimationCompat$BoundsCompat = new WindowInsetsAnimationCompat$BoundsCompat(Insets.of(Math.min(insets.left, insets2.left), Math.min(insets.top, insets2.top), Math.min(insets.right, insets2.right), Math.min(insets.bottom, insets2.bottom)), Insets.of(Math.max(insets.left, insets2.left), Math.max(insets.top, insets2.top), Math.max(insets.right, insets2.right), Math.max(insets.bottom, insets2.bottom)));
            WindowInsetsAnimationCompat$Impl21.dispatchOnPrepare$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(view, onDeviceTextDetectionLoadLogEvent, windowInsets, false);
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    OnDeviceTextDetectionLoadLogEvent.this.setFraction(valueAnimator.getAnimatedFraction());
                    float interpolatedFraction = OnDeviceTextDetectionLoadLogEvent.this.getInterpolatedFraction();
                    WindowInsetsCompat windowInsetsCompat4 = windowInsetsCompat;
                    AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(windowInsetsCompat4);
                    for (int i3 = 1; i3 <= 256; i3 += i3) {
                        if ((i & i3) == 0) {
                            collectionItemInfoCompat.setInsets$ar$ds(i3, windowInsetsCompat4.getInsets(i3));
                        } else {
                            WindowInsetsCompat windowInsetsCompat5 = windowInsetsCompat3;
                            Insets insets3 = windowInsetsCompat4.getInsets(i3);
                            Insets insets4 = windowInsetsCompat5.getInsets(i3);
                            float f = 1.0f - interpolatedFraction;
                            collectionItemInfoCompat.setInsets$ar$ds(i3, WindowInsetsCompat.insetInsets(insets3, (int) (((insets3.left - insets4.left) * f) + 0.5d), (int) (((insets3.top - insets4.top) * f) + 0.5d), (int) (((insets3.right - insets4.right) * f) + 0.5d), (int) (((insets3.bottom - insets4.bottom) * f) + 0.5d)));
                        }
                    }
                    WindowInsetsAnimationCompat$Impl21.dispatchOnProgress(view, collectionItemInfoCompat.m31build(), Collections.singletonList(OnDeviceTextDetectionLoadLogEvent.this));
                }
            });
            duration.addListener(new AnimatorListenerAdapter() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    OnDeviceTextDetectionLoadLogEvent.this.setFraction(1.0f);
                    WindowInsetsAnimationCompat$Impl21.dispatchOnEnd$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(view, OnDeviceTextDetectionLoadLogEvent.this);
                }
            });
            OneShotPreDrawListener.add$ar$ds$ef887652_0(view, new AnonymousClass3(view, onDeviceTextDetectionLoadLogEvent, windowInsetsAnimationCompat$BoundsCompat, duration, 0));
            this.mLastInsets = windowInsetsCompat;
            return WindowInsetsAnimationCompat$Impl21.forwardToViewIfNeeded(view, windowInsets);
        }
    }

    public WindowInsetsAnimationCompat$Impl21(int i, Interpolator interpolator, long j) {
        super(i, interpolator, j);
    }

    static void dispatchOnEnd$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(View view, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        WindowInsetsAnimationCompat$Callback callback = getCallback(view);
        if (callback == null) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnEnd$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(viewGroup.getChildAt(i), onDeviceTextDetectionLoadLogEvent);
                }
                return;
            }
            return;
        }
        callback.onEnd$ar$ds();
    }

    static void dispatchOnPrepare$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(View view, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent, WindowInsets windowInsets, boolean z) {
        WindowInsetsAnimationCompat$Callback callback = getCallback(view);
        if (callback != null) {
            callback.mDispachedInsets = windowInsets;
            if (!z) {
                callback.onPrepare$ar$ds();
                z = true;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                dispatchOnPrepare$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(viewGroup.getChildAt(i), onDeviceTextDetectionLoadLogEvent, windowInsets, z);
            }
        }
    }

    static void dispatchOnProgress(View view, WindowInsetsCompat windowInsetsCompat, List list) {
        WindowInsetsAnimationCompat$Callback callback = getCallback(view);
        if (callback == null) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnProgress(viewGroup.getChildAt(i), windowInsetsCompat, list);
                }
                return;
            }
            return;
        }
        callback.onProgress$ar$ds$82a9f68b_0(windowInsetsCompat, list);
    }

    static void dispatchOnStart$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(View view, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent, WindowInsetsAnimationCompat$BoundsCompat windowInsetsAnimationCompat$BoundsCompat) {
        WindowInsetsAnimationCompat$Callback callback = getCallback(view);
        if (callback == null) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnStart$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(viewGroup.getChildAt(i), onDeviceTextDetectionLoadLogEvent, windowInsetsAnimationCompat$BoundsCompat);
                }
                return;
            }
            return;
        }
        callback.onStart$ar$ds(windowInsetsAnimationCompat$BoundsCompat);
    }

    static WindowInsets forwardToViewIfNeeded(View view, WindowInsets windowInsets) {
        if (view.getTag(R.id.tag_on_apply_window_listener) != null) {
            return windowInsets;
        }
        return view.onApplyWindowInsets(windowInsets);
    }

    static WindowInsetsAnimationCompat$Callback getCallback(View view) {
        Object tag = view.getTag(R.id.tag_window_insets_animation_callback);
        if (tag instanceof Impl21OnApplyWindowInsetsListener) {
            return ((Impl21OnApplyWindowInsetsListener) tag).mCallback;
        }
        return null;
    }
}

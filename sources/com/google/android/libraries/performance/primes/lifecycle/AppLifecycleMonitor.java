package com.google.android.libraries.performance.primes.lifecycle;

import android.accessibilityservice.AccessibilityService;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.caption.ImageNode;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda16;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.gms.auth.api.AuthProxyOptions;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskImpl;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.concurrent.InternalExecutorDecorator;
import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;
import com.google.android.libraries.storage.protostore.SingleProcProtoDataStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppLifecycleMonitor {
    public final Object AppLifecycleMonitor$ar$tracker;

    public AppLifecycleMonitor() {
    }

    public static AccessibilityNodeInfoCompat getAccessibilityFocusNode(AccessibilityService accessibilityService, boolean z) {
        AccessibilityNodeInfo rootInActiveWindow;
        AccessibilityNodeInfo findFocus = accessibilityService.findFocus(2);
        if (findFocus == null && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
            findFocus = rootInActiveWindow.findFocus(2);
        }
        if (findFocus == null || (!findFocus.isVisibleToUser() && !WebInterfaceUtils.isWebContainer(AccessibilityNodeInfoUtils.toCompat(findFocus)))) {
            findFocus = null;
        }
        if (findFocus == null && z && (findFocus = accessibilityService.getRootInActiveWindow()) == null) {
            LogUtils.e("FocusFinder", "No current window root", new Object[0]);
        }
        if (findFocus == null || !findFocus.refresh()) {
            return null;
        }
        return AccessibilityNodeInfoUtils.toCompat(findFocus);
    }

    public static final ImageNode getCaptionResults$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat);
        return null;
    }

    public final AuthProxyOptions build() {
        return new AuthProxyOptions((Bundle) this.AppLifecycleMonitor$ar$tracker);
    }

    public final void cancel() {
        ((TaskImpl) ((AppLifecycleMonitor) this.AppLifecycleMonitor$ar$tracker).AppLifecycleMonitor$ar$tracker).trySetResult(null);
    }

    public final synchronized void clear() {
        ((HashMap) this.AppLifecycleMonitor$ar$tracker).clear();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [javax.inject.Provider, java.lang.Object] */
    public final ProbabilitySampler create(float f) {
        Random random = (Random) this.AppLifecycleMonitor$ar$tracker.get();
        random.getClass();
        return new ProbabilitySampler(random, f);
    }

    public final AccessibilityNodeInfoCompat findFocusCompat(int i) {
        if (i != 1) {
            return getAccessibilityFocusNode((AccessibilityService) this.AppLifecycleMonitor$ar$tracker, false);
        }
        return AccessibilityNodeInfoUtils.toCompat(((AccessibilityService) this.AppLifecycleMonitor$ar$tracker).findFocus(1));
    }

    public final int get() {
        return ((AtomicInteger) this.AppLifecycleMonitor$ar$tracker).get();
    }

    public final String get$ar$ds$e3f95f0a_0(Uri uri, String str, String str2) {
        SimpleArrayMap simpleArrayMap;
        if (uri != null) {
            simpleArrayMap = (SimpleArrayMap) ((SimpleArrayMap) this.AppLifecycleMonitor$ar$tracker).get(uri.toString());
        } else {
            simpleArrayMap = null;
        }
        if (simpleArrayMap == null) {
            return null;
        }
        if (str != null) {
            str2 = str.concat(str2);
        }
        return (String) simpleArrayMap.get(str2);
    }

    public final ApplicationInfo getApplicationInfo(String str, int i) {
        return ((Context) this.AppLifecycleMonitor$ar$tracker).getPackageManager().getApplicationInfo(str, i);
    }

    public final PackageInfo getPackageInfo(String str, int i) {
        return ((Context) this.AppLifecycleMonitor$ar$tracker).getPackageManager().getPackageInfo(str, i);
    }

    @Deprecated
    public final TogglingData getTogglingData() {
        return ((GoogleHelp) this.AppLifecycleMonitor$ar$tracker).togglingData;
    }

    public final WindowTrackerFactory getTransport$ar$class_merging$ar$ds$ar$class_merging(Encoding encoding, Transformer transformer) {
        if (new Encoding("proto").equals(encoding)) {
            return new WindowTrackerFactory((Context) this.AppLifecycleMonitor$ar$tracker, transformer);
        }
        throw new IllegalArgumentException("Only \"proto\" encoding is supported by firelog1p. Got: ".concat(encoding.toString()));
    }

    public final ListenableFuture handleReadException$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(IOException iOException, StatsStorage statsStorage) {
        if (!(iOException.getCause() instanceof InvalidProtocolBufferException)) {
            return ContextDataProvider.immediateFailedFuture(iOException);
        }
        Object obj = this.AppLifecycleMonitor$ar$tracker;
        Object obj2 = statsStorage.StatsStorage$ar$storage;
        return AbstractCatchingFuture.create(AbstractTransformFuture.create(ContextDataProvider.immediateFuture(obj), TracePropagation.propagateAsyncFunction(new AiCoreBaseService$$ExternalSyntheticLambda16(obj2, 9)), ((SingleProcProtoDataStore) obj2).ioExecutor), IOException.class, new AiCoreBaseService$$ExternalSyntheticLambda16(iOException, 16), DirectExecutor.INSTANCE);
    }

    public final boolean isCancellationRequested() {
        return ((TaskImpl) this.AppLifecycleMonitor$ar$tracker).isComplete();
    }

    public final void register(AppLifecycleListener appLifecycleListener) {
        appLifecycleListener.getClass();
        Object obj = ((StatsStorage) this.AppLifecycleMonitor$ar$tracker).StatsStorage$ar$storage;
        int i = AppLifecycleTracker$Callbacks.AppLifecycleTracker$Callbacks$ar$NoOp;
        ((AppLifecycleTracker$Callbacks) obj).lifecycleListeners.add(appLifecycleListener);
    }

    public final void setClientVersion$ar$ds(int i) {
        ((GoogleHelp) this.AppLifecycleMonitor$ar$tracker).clientVersion = i;
    }

    public final void setException(Exception exc) {
        ((TaskImpl) this.AppLifecycleMonitor$ar$tracker).setException(exc);
    }

    public final void setResult(Object obj) {
        ((TaskImpl) this.AppLifecycleMonitor$ar$tracker).setResult(obj);
    }

    public final boolean trySetException(Exception exc) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(exc, "Exception must not be null");
        Object obj = this.AppLifecycleMonitor$ar$tracker;
        TaskImpl taskImpl = (TaskImpl) obj;
        synchronized (taskImpl.lock) {
            if (((TaskImpl) obj).complete) {
                return false;
            }
            ((TaskImpl) obj).complete = true;
            ((TaskImpl) obj).exception = exc;
            taskImpl.listenerQueue$ar$class_merging.flush((Task) obj);
            return true;
        }
    }

    public final boolean trySetResult(Object obj) {
        return ((TaskImpl) this.AppLifecycleMonitor$ar$tracker).trySetResult(obj);
    }

    public final void unregister(AppLifecycleListener appLifecycleListener) {
        Object obj = ((StatsStorage) this.AppLifecycleMonitor$ar$tracker).StatsStorage$ar$storage;
        int i = AppLifecycleTracker$Callbacks.AppLifecycleTracker$Callbacks$ar$NoOp;
        ((AppLifecycleTracker$Callbacks) obj).lifecycleListeners.remove(appLifecycleListener);
    }

    public AppLifecycleMonitor(Application application, DaggerProdInternalComponent.Builder builder) {
        builder.DaggerProdInternalComponent$Builder$ar$setApplicationContext = application;
        Absent absent = Absent.INSTANCE;
        builder.DaggerProdInternalComponent$Builder$ar$setSharedPreferencesSupplier = absent;
        builder.DaggerProdInternalComponent$Builder$ar$setThreadsConfigurations = absent;
        builder.DaggerProdInternalComponent$Builder$ar$setDisableAutomaticCrashInitToken = absent;
        this.AppLifecycleMonitor$ar$tracker = builder;
    }

    public AppLifecycleMonitor(MessageLite messageLite) {
        this();
        this.AppLifecycleMonitor$ar$tracker = messageLite;
    }

    public AppLifecycleMonitor(Object obj) {
        this.AppLifecycleMonitor$ar$tracker = obj;
    }

    public AppLifecycleMonitor(Object obj, byte[] bArr) {
        this.AppLifecycleMonitor$ar$tracker = obj;
    }

    protected AppLifecycleMonitor(byte[] bArr) {
        this.AppLifecycleMonitor$ar$tracker = "tiktok_systrace";
    }

    public AppLifecycleMonitor(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this();
        this.AppLifecycleMonitor$ar$tracker = new TaskImpl();
    }

    public AppLifecycleMonitor(byte[] bArr, char[] cArr) {
        this.AppLifecycleMonitor$ar$tracker = new AppLifecycleMonitor((byte[]) null, (byte[]) null, (byte[]) null);
    }

    public AppLifecycleMonitor(short[] sArr) {
        this.AppLifecycleMonitor$ar$tracker = new TaskImpl();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.app.Application$ActivityLifecycleCallbacks, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.content.ComponentCallbacks, java.lang.Object] */
    public AppLifecycleMonitor(Context context, StatsStorage statsStorage) {
        this.AppLifecycleMonitor$ar$tracker = statsStorage;
        Application application = (Application) context;
        application.registerActivityLifecycleCallbacks(statsStorage.StatsStorage$ar$storage);
        application.registerComponentCallbacks(statsStorage.StatsStorage$ar$storage);
    }

    public AppLifecycleMonitor(AppLifecycleMonitor appLifecycleMonitor) {
        this.AppLifecycleMonitor$ar$tracker = new TaskImpl();
        final FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(this);
        ((TaskImpl) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.gms.tasks.CancellationTokenImpl$1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final /* bridge */ /* synthetic */ void onSuccess(Object obj) {
                ((TaskImpl) ((AppLifecycleMonitor) FloatingActionButton.ShadowDelegateImpl.this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).AppLifecycleMonitor$ar$tracker).trySetCanceled$ar$ds();
            }
        });
    }

    public AppLifecycleMonitor(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        this.AppLifecycleMonitor$ar$tracker = new HashMap(ContextDataProvider.capacity(500));
    }

    public AppLifecycleMonitor(Optional optional) {
        this.AppLifecycleMonitor$ar$tracker = (InternalExecutorDecorator) optional.orNull();
    }

    public AppLifecycleMonitor(byte[] bArr, char[] cArr, byte[] bArr2) {
        this.AppLifecycleMonitor$ar$tracker = new AppLifecycleMonitor(null, null, null, null);
    }

    public AppLifecycleMonitor(short[] sArr, byte[] bArr) {
        this.AppLifecycleMonitor$ar$tracker = new Bundle();
    }

    public AppLifecycleMonitor(Provider provider) {
        provider.getClass();
        this.AppLifecycleMonitor$ar$tracker = provider;
    }

    public AppLifecycleMonitor(char[] cArr) {
        this.AppLifecycleMonitor$ar$tracker = new AtomicInteger();
    }

    public AppLifecycleMonitor(byte[] bArr, byte[] bArr2) {
        this((byte[]) null);
    }
}

package io.grpc.okhttp.internal;

import android.app.Notification;
import android.app.RemoteInput;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.TwilightManager$TwilightState;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.collection.ArraySet;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.WidgetContainer;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat$Action;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.app.NotificationCompatBuilder$Api20Impl;
import androidx.core.app.NotificationCompatBuilder$Api21Impl;
import androidx.core.app.NotificationCompatBuilder$Api23Impl;
import androidx.core.app.NotificationCompatBuilder$Api24Impl;
import androidx.core.app.NotificationCompatBuilder$Api26Impl;
import androidx.core.app.NotificationCompatBuilder$Api28Impl;
import androidx.core.app.NotificationCompatBuilder$Api29Impl;
import androidx.core.app.NotificationCompatBuilder$Api31Impl;
import androidx.core.app.RemoteInput$Api20Impl;
import androidx.core.content.ContextCompat$Api33Impl;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.selecttospeak.AccessibilityNodeInfoCompatWithVisibility;
import com.google.android.accessibility.selecttospeak.SelectToSpeakJobService;
import com.google.android.accessibility.selecttospeak.UIManager;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIterator;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIteratorFactory;
import com.google.android.accessibility.selecttospeak.logging.SelectToSpeakLogSender;
import com.google.android.accessibility.selecttospeak.nodefilters.ActionableNodeFilter;
import com.google.android.accessibility.selecttospeak.nodefilters.FilterList;
import com.google.android.accessibility.selecttospeak.nodefilters.IntersectsBoundsFilter;
import com.google.android.accessibility.selecttospeak.nodefilters.IsGoogleDocsEditTextFilter;
import com.google.android.accessibility.selecttospeak.nodefilters.IsImageFilter;
import com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.analytics.ClearcutAnalyticsHelper$PropertySetter;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.ocr.OcrController;
import com.google.android.accessibility.utils.ocr.OcrInfo;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.foreground.ForegroundConfigurationModule_UseDebouncedForegroundSignalsInternalFactory;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.metriccapture.RunningAppProcessInfoResponse;
import com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.shareddir.FlagsBlob;
import com.google.android.libraries.phenotype.client.shareddir.SnapshotBlob;
import com.google.android.libraries.phenotype.client.stable.SnapshotProto$Snapshot;
import com.google.android.libraries.phenotype.client.stable.SnapshotProto$SnapshotFlag;
import com.google.android.libraries.storage.file.Opener;
import com.google.android.libraries.storage.file.common.UnsupportedFileStorageOperation;
import com.google.android.libraries.storage.file.common.internal.LiteTransformFragments;
import com.google.android.libraries.storage.file.spi.Backend;
import com.google.android.libraries.storage.file.spi.Transform;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.splitcompat.reflectutils.SplitCompatReflectionException;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.android.concurrent.ContextHolder;
import com.google.common.android.concurrent.FutureCallbackRegistry$$ExternalSyntheticLambda0;
import com.google.common.android.concurrent.FutureCallbackRegistry$Callback;
import com.google.common.android.concurrent.FutureCallbackViewModel;
import com.google.common.android.concurrent.ParcelableFuture;
import com.google.common.base.Functions$ConstantFunction;
import com.google.common.base.Splitter;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.hash.Murmur3_128HashFunction;
import com.google.common.io.BaseEncoding;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.components.Component;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.mlkit.logging.schema.DurationStats;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.protobuf.ByteString;
import io.grpc.okhttp.internal.OptionalMethod;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Provider;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.scheduling.WorkQueue;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OptionalMethod {
    public static OptionalMethod sInstance$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Object OptionalMethod$ar$methodName;
    public final Object OptionalMethod$ar$methodParams;
    public final Object OptionalMethod$ar$returnType;

    public OptionalMethod(Context context, LocationManager locationManager) {
        this.OptionalMethod$ar$returnType = new TwilightManager$TwilightState();
        this.OptionalMethod$ar$methodName = context;
        this.OptionalMethod$ar$methodParams = locationManager;
    }

    private final void addAction(NotificationCompat$Action notificationCompat$Action) {
        IconCompat iconCompat = notificationCompat$Action.getIconCompat();
        Icon icon = null;
        if (iconCompat != null) {
            icon = IconCompat.Api23Impl.toIcon(iconCompat, null);
        }
        Notification.Action.Builder createBuilder = NotificationCompatBuilder$Api23Impl.createBuilder(icon, notificationCompat$Action.title, notificationCompat$Action.actionIntent);
        ActivityCompat.Api31Impl[] api31ImplArr = notificationCompat$Action.mRemoteInputs$ar$class_merging;
        if (api31ImplArr != null) {
            int length = api31ImplArr.length;
            RemoteInput[] remoteInputArr = new RemoteInput[length];
            for (int i = 0; i < api31ImplArr.length; i++) {
                ActivityCompat.Api31Impl api31Impl = api31ImplArr[i];
                remoteInputArr[i] = RemoteInput$Api20Impl.fromCompat$ar$ds();
            }
            for (int i2 = 0; i2 < length; i2++) {
                NotificationCompatBuilder$Api20Impl.addRemoteInput(createBuilder, remoteInputArr[i2]);
            }
        }
        Bundle bundle = new Bundle(notificationCompat$Action.mExtras);
        bundle.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action.mAllowGeneratedReplies);
        NotificationCompatBuilder$Api24Impl.setAllowGeneratedReplies(createBuilder, notificationCompat$Action.mAllowGeneratedReplies);
        bundle.putInt("android.support.action.semanticAction", 0);
        if (Build.VERSION.SDK_INT >= 28) {
            NotificationCompatBuilder$Api28Impl.setSemanticAction(createBuilder, 0);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            NotificationCompatBuilder$Api29Impl.setContextual(createBuilder, false);
        }
        if (Build.VERSION.SDK_INT >= 31) {
            NotificationCompatBuilder$Api31Impl.setAuthenticationRequired(createBuilder, false);
        }
        bundle.putBoolean("android.support.action.showsUserInterface", notificationCompat$Action.mShowsUserInterface);
        NotificationCompatBuilder$Api20Impl.addExtras(createBuilder, bundle);
        NotificationCompatBuilder$Api20Impl.addAction((Notification.Builder) this.OptionalMethod$ar$methodParams, NotificationCompatBuilder$Api20Impl.build(createBuilder));
    }

    public static OptionalMethod forFragment$ar$class_merging$ar$class_merging(Fragment fragment) {
        Lifecycle lifecycle = fragment.getLifecycle();
        fragment.getClass();
        return new OptionalMethod(lifecycle, new FutureCallbackRegistry$$ExternalSyntheticLambda0(fragment, 0), ContextDataProvider.memoize(new FutureCallbackRegistry$$ExternalSyntheticLambda0(fragment, 2)), ContextHolder.NOOP);
    }

    private final Method getMethod(Class cls) {
        Object obj;
        Method publicMethod = getPublicMethod(cls, (String) this.OptionalMethod$ar$methodName, (Class[]) this.OptionalMethod$ar$methodParams);
        if (publicMethod != null && (obj = this.OptionalMethod$ar$returnType) != null) {
            if (!((Class) obj).isAssignableFrom(publicMethod.getReturnType())) {
                return null;
            }
            return publicMethod;
        }
        return publicMethod;
    }

    private static Method getPublicMethod(Class cls, String str, Class[] clsArr) {
        if (cls == null) {
            return null;
        }
        try {
            if ((cls.getModifiers() & 1) == 0) {
                return getPublicMethod(cls.getSuperclass(), str, clsArr);
            }
            Method method = cls.getMethod(str, clsArr);
            try {
                if (1 != (method.getModifiers() & 1)) {
                    return null;
                }
            } catch (NoSuchMethodException unused) {
            }
            return method;
        } catch (NoSuchMethodException unused2) {
            return null;
        }
    }

    public static SplitInstallSharedPreferences ignoringValueFuture$ar$class_merging$ar$class_merging(ListenableFuture listenableFuture) {
        return new SplitInstallSharedPreferences(AbstractTransformFuture.create(listenableFuture, new Functions$ConstantFunction(), DirectExecutor.INSTANCE), null);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.common.base.Supplier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.google.common.base.Supplier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    private final void listenInternal$ar$class_merging$ar$class_merging(SplitInstallSharedPreferences splitInstallSharedPreferences, FutureCallbackRegistry$Callback futureCallbackRegistry$Callback, Object obj) {
        boolean z;
        boolean z2;
        boolean z3;
        FragmentManager fragmentManager = (FragmentManager) this.OptionalMethod$ar$methodParams.get();
        boolean z4 = false;
        int i = 1;
        if (!fragmentManager.isStateSaved() && !fragmentManager.mDestroyed) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Called when state-loss is possible.");
        FutureCallbackViewModel futureCallbackViewModel = (FutureCallbackViewModel) this.OptionalMethod$ar$returnType.get();
        FutureCallbackViewModel.assertMainThread$ar$ds$72a67533_0();
        int indexOfValue = futureCallbackViewModel.callbacks.indexOfValue(futureCallbackRegistry$Callback);
        if (indexOfValue != -1) {
            z2 = true;
        } else {
            z2 = false;
        }
        ContextDataProvider.checkState(z2, "Callback not registered.");
        int keyAt = futureCallbackViewModel.callbacks.keyAt(indexOfValue);
        ParcelableFuture parcelableFuture = new ParcelableFuture(keyAt, obj, splitInstallSharedPreferences.SplitInstallSharedPreferences$ar$context);
        FutureCallbackViewModel.assertMainThread$ar$ds$72a67533_0();
        if (SparseArrayCompatKt.commonGet(futureCallbackViewModel.callbacks, keyAt) != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        ContextDataProvider.checkState(z3, "Callback not registered.");
        if (futureCallbackViewModel.hostFragmentManager != null) {
            z4 = true;
        }
        ContextDataProvider.checkState(z4, "Listening outside of callback window.");
        ContextDataProvider.checkState(futureCallbackViewModel.resumedOnce, "Executing tasks from lifecycle methods is disallowed since it can result in unnecessary operations during configuration changes or other lifecycle transitions.");
        ContextDataProvider.checkState(!futureCallbackViewModel.isDeliveringResult, "Calling listen() from FutureCallbackRegistry callbacks is disallowed because hopping through the UI thread adds extra latency. Chain the new Future to the original Future using Futures.transformAsync instead.");
        ContextHolder contextHolder = futureCallbackViewModel.contextHolder;
        ListenableFuture listenableFuture = parcelableFuture.future;
        Futures$$ExternalSyntheticLambda1 futures$$ExternalSyntheticLambda1 = new Futures$$ExternalSyntheticLambda1(parcelableFuture, i);
        contextHolder.propagateToRunnable$ar$ds(futures$$ExternalSyntheticLambda1);
        listenableFuture.addListener(futures$$ExternalSyntheticLambda1, DirectExecutor.INSTANCE);
        futureCallbackViewModel.pendingFutures.add(parcelableFuture);
        parcelableFuture.setListener$ar$class_merging$59ab07de_0(futureCallbackViewModel);
        if (!parcelableFuture.hasResult()) {
            futureCallbackViewModel.callOnPending((FutureCallbackRegistry$Callback) SparseArrayCompatKt.commonGet(futureCallbackViewModel.callbacks, keyAt), parcelableFuture);
        }
    }

    private static synchronized void scheduleDailyJob$ar$ds(Context context) {
        synchronized (OptionalMethod.class) {
            LogUtils.v("SelectToSpeakClearcutAnalytics", "In scheduleDailyJob", new Object[0]);
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            Iterator<JobInfo> it = jobScheduler.getAllPendingJobs().iterator();
            while (it.hasNext()) {
                if (it.next().getId() == -559038737) {
                    return;
                }
            }
            JobInfo.Builder builder = new JobInfo.Builder(-559038737, new ComponentName(context, (Class<?>) SelectToSpeakJobService.class));
            SelectToSpeakLogSender selectToSpeakLogSender = SelectToSpeakLogSender.INSTANCE;
            jobScheduler.schedule(builder.setPeriodic(SelectToSpeakLogSender.INTERVAL_BETWEEN_LOGS_MS).build());
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.lang.Runnable] */
    public final void addMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        ((CopyOnWriteArrayList) this.OptionalMethod$ar$methodName).add(shadowDelegateImpl);
        this.OptionalMethod$ar$methodParams.run();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, com.google.android.accessibility.talkback.analytics.ClearcutAnalyticsHelper$PropertySetter] */
    public final void addPrefValueToLogEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SystemHealthProto$PackedHistogram.Builder builder, SystemHealthProto$PackedHistogram.Builder builder2, String str) {
        Object obj = ((HashMap) this.OptionalMethod$ar$methodParams).get(str);
        if (obj == null) {
            return;
        }
        this.OptionalMethod$ar$returnType.set$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder, builder2, obj);
    }

    public final void addValueMapping(String str, Object obj) {
        ((HashMap) this.OptionalMethod$ar$methodParams).put(str, obj);
    }

    public final IllegalArgumentException exception() {
        Object obj = this.OptionalMethod$ar$returnType;
        Object obj2 = this.OptionalMethod$ar$methodParams;
        Object obj3 = this.OptionalMethod$ar$methodName;
        return new IllegalArgumentException("Multiple entries with same key: " + obj3.toString() + "=" + obj2.toString() + " and " + obj3.toString() + "=" + obj.toString());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    public final boolean exists(Uri uri) {
        WorkQueue context$ar$class_merging$ar$class_merging = getContext$ar$class_merging$ar$class_merging(uri);
        return context$ar$class_merging$ar$class_merging.WorkQueue$ar$buffer.exists((Uri) context$ar$class_merging$ar$class_merging.WorkQueue$ar$consumerIndex);
    }

    public final void expandArray(Collection collection) {
        int length;
        Object[] objArr = (Object[]) get();
        if (objArr == null) {
            length = 0;
        } else {
            length = objArr.length;
        }
        Object[] objArr2 = (Object[]) Array.newInstance((Class<?>) getFieldArrayType(), collection.size() + length);
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            objArr2[length] = it.next();
            length++;
        }
        set(objArr2);
    }

    public final Object get() {
        try {
            return ((Class) this.OptionalMethod$ar$methodParams).cast(((Field) this.OptionalMethod$ar$methodName).get(this.OptionalMethod$ar$returnType));
        } catch (Exception e) {
            throw new SplitCompatReflectionException(String.format("Failed to get value of field %s of type %s on object of type %s", ((Field) this.OptionalMethod$ar$methodName).getName(), this.OptionalMethod$ar$returnType.getClass().getName(), ((Class) this.OptionalMethod$ar$methodParams).getName()), e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.util.Map, java.lang.Object] */
    public final WorkQueue getContext$ar$class_merging$ar$class_merging(Uri uri) {
        ImmutableList immutableList;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        Pattern pattern = LiteTransformFragments.XFORM_NAME_PATTERN;
        ImmutableList.Builder builder2 = new ImmutableList.Builder();
        String encodedFragment = uri.getEncodedFragment();
        if (!TextUtils.isEmpty(encodedFragment) && encodedFragment.startsWith("transform=")) {
            immutableList = ImmutableList.copyOf(Splitter.on("+").omitEmptyStrings().split(encodedFragment.substring(10)));
        } else {
            immutableList = RegularImmutableList.EMPTY;
        }
        int size = immutableList.size();
        for (int i = 0; i < size; i++) {
            String str = (String) immutableList.get(i);
            Matcher matcher = LiteTransformFragments.XFORM_NAME_PATTERN.matcher(str);
            if (matcher.matches()) {
                builder2.add$ar$ds$4f674a09_0(matcher.group(1));
            } else {
                throw new IllegalArgumentException("Invalid fragment spec: ".concat(String.valueOf(str)));
            }
        }
        ImmutableList build = builder2.build();
        int i2 = ((RegularImmutableList) build).size;
        for (int i3 = 0; i3 < i2; i3++) {
            String str2 = (String) build.get(i3);
            Transform transform = (Transform) this.OptionalMethod$ar$methodName.get(str2);
            if (transform != null) {
                builder.add$ar$ds$4f674a09_0(transform);
            } else {
                throw new UnsupportedFileStorageOperation("No such transform: " + str2 + ": " + String.valueOf(uri));
            }
        }
        ImmutableList reverse = builder.build().reverse();
        DurationStats durationStats = new DurationStats();
        durationStats.DurationStats$ar$medianMs = this;
        String scheme = uri.getScheme();
        Backend backend = (Backend) this.OptionalMethod$ar$returnType.get(scheme);
        if (backend != null) {
            durationStats.DurationStats$ar$maxMs = backend;
            durationStats.DurationStats$ar$minMs = this.OptionalMethod$ar$methodParams;
            durationStats.DurationStats$ar$thirdQuartileMs = reverse;
            durationStats.DurationStats$ar$firstQuartileMs = uri;
            if (!reverse.isEmpty()) {
                ArrayList arrayList = new ArrayList(uri.getPathSegments());
                if (!arrayList.isEmpty() && !uri.getPath().endsWith("/")) {
                    String str3 = (String) arrayList.get(arrayList.size() - 1);
                    ListIterator<E> listIterator = reverse.listIterator(reverse.size());
                    while (listIterator.hasPrevious()) {
                        str3 = ((Transform) listIterator.previous()).encode$ar$ds();
                    }
                    arrayList.set(arrayList.size() - 1, str3);
                    uri = uri.buildUpon().path(TextUtils.join("/", arrayList)).encodedFragment(null).build();
                }
            }
            durationStats.DurationStats$ar$avgMs = uri;
            return new WorkQueue(durationStats);
        }
        throw new UnsupportedFileStorageOperation(String.format("Cannot open, unregistered backend: %s", scheme));
    }

    public final Class getFieldArrayType() {
        return ((Field) this.OptionalMethod$ar$methodName).getType().getComponentType();
    }

    public final Location getLastKnownLocationForProvider(String str) {
        try {
            if (((LocationManager) this.OptionalMethod$ar$methodParams).isProviderEnabled(str)) {
                return ((LocationManager) this.OptionalMethod$ar$methodParams).getLastKnownLocation(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public final SentenceIterator getSelected(Bitmap bitmap, Rect rect, boolean z, List list) {
        boolean z2;
        int i;
        int type;
        AccessibilityNodeInfo root;
        boolean z3;
        list.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        int i2 = 0;
        boolean z4 = false;
        while (it.hasNext()) {
            AccessibilityWindowInfo accessibilityWindowInfo = (AccessibilityWindowInfo) it.next();
            if (accessibilityWindowInfo != null && (type = SpannableUtils$IdentifierSpan.getType(accessibilityWindowInfo)) != 5 && type != 6 && (root = accessibilityWindowInfo.getRoot()) != null) {
                CharSequence className = root.getClassName();
                if (className != null) {
                    String str = UIManager.S2S_OVERLAY_IDENTIFIER;
                    str.getClass();
                    if (str.contentEquals(className)) {
                        LogUtils.d("SelectedSentenceFinderNodeInfo", "Window ignored. Reason: S2S window. %s", accessibilityWindowInfo);
                    }
                }
                if (type != 3) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                z4 |= !z3;
                arrayList.add(accessibilityWindowInfo);
            }
        }
        ArrayList<AccessibilityNodeInfoCompatWithVisibility> arrayList2 = new ArrayList();
        if (z && z4) {
            z2 = true;
        } else {
            z2 = false;
        }
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size) {
            if (z2 && ((AccessibilityWindowInfo) arrayList.get(i3)).getType() == 3) {
                Object[] objArr = new Object[1];
                objArr[i2] = arrayList.get(i3);
                LogUtils.d("SelectedSentenceFinderNodeInfo", "Window ignored. Reason: Should ignore system window. %s", objArr);
            } else {
                AccessibilityNodeInfo root2 = ((AccessibilityWindowInfo) arrayList.get(i3)).getRoot();
                if (root2 != null) {
                    AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility = new AccessibilityNodeInfoCompatWithVisibility(root2, arrayList.subList(i2, i3));
                    Object obj = this.OptionalMethod$ar$returnType;
                    IntersectsBoundsFilter intersectsBoundsFilter = new IntersectsBoundsFilter();
                    ActionableNodeFilter actionableNodeFilter = new ActionableNodeFilter();
                    intersectsBoundsFilter.bounds.set(rect);
                    i = i3;
                    SpannableUtils$NonCopyableTextSpan.findNodeDFS(accessibilityNodeInfoCompatWithVisibility, intersectsBoundsFilter, actionableNodeFilter, (Filter) obj, arrayList2, new HashSet());
                    i3 = i + 1;
                    i2 = 0;
                }
            }
            i = i3;
            i3 = i + 1;
            i2 = 0;
        }
        if (!arrayList2.isEmpty()) {
            Rect rect2 = new Rect();
            int[] iArr = new int[arrayList2.size()];
            int size2 = arrayList2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility2 = (AccessibilityNodeInfoCompatWithVisibility) arrayList2.get(i4);
                if (accessibilityNodeInfoCompatWithVisibility2.nonOverlapBoundInScreen == null) {
                    try {
                        accessibilityNodeInfoCompatWithVisibility2.nonOverlapBoundInScreen = new Rect();
                        if (!accessibilityNodeInfoCompatWithVisibility2.isVisibleToUserBeneathWindows()) {
                            accessibilityNodeInfoCompatWithVisibility2.nonOverlapBoundInScreen.setEmpty();
                        } else {
                            accessibilityNodeInfoCompatWithVisibility2.getVisibleBoundsInScreen(accessibilityNodeInfoCompatWithVisibility2.nonOverlapBoundInScreen);
                            AccessibilityWindowInfoCompat window = AccessibilityNodeInfoUtils.getWindow(accessibilityNodeInfoCompatWithVisibility2);
                            if (window != null && window.getType() != 3 && !WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompatWithVisibility2)) {
                                AccessibilityNodeInfoCompatWithVisibility.reduceVisibleRectangleForDrawingOrderRecursive(accessibilityNodeInfoCompatWithVisibility2.nonOverlapBoundInScreen, AccessibilityNodeInfoCompatWithVisibility.obtain(accessibilityNodeInfoCompatWithVisibility2), new HashSet());
                            }
                        }
                    } catch (Exception unused) {
                        LogUtils.e("NodeInfoCompatWithVisib", "Fail to update non-overlap bound for node: %s", accessibilityNodeInfoCompatWithVisibility2);
                    }
                }
                rect2.set(accessibilityNodeInfoCompatWithVisibility2.nonOverlapBoundInScreen);
                if (rect2.intersect(rect)) {
                    iArr[i4] = rect2.width() * rect2.height();
                }
            }
            int size3 = arrayList2.size();
            int i5 = -1;
            int i6 = 0;
            while (true) {
                if (i6 < size3) {
                    if (iArr[i6] > 0) {
                        if (i5 != -1) {
                            break;
                        }
                        i5 = i6;
                    }
                    i6++;
                } else if (i5 != -1) {
                    AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility3 = (AccessibilityNodeInfoCompatWithVisibility) arrayList2.get(i5);
                    arrayList2.remove(i5);
                    arrayList2.add(0, accessibilityNodeInfoCompatWithVisibility3);
                }
            }
            if (bitmap == null) {
                return SentenceIteratorFactory.generateIterator(arrayList2);
            }
            ArrayList arrayList3 = new ArrayList();
            for (final AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility4 : arrayList2) {
                arrayList3.add(new OcrInfo(accessibilityNodeInfoCompatWithVisibility4) { // from class: com.google.android.accessibility.selecttospeak.feedback.SelectionFinder$SelectToSpeakOcrInfo
                    @Override // com.google.android.accessibility.utils.ocr.OcrInfo
                    public final void getBoundsInScreenForOcr(Rect rect3) {
                        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.node;
                        accessibilityNodeInfoCompat.getClass();
                        ((AccessibilityNodeInfoCompatWithVisibility) accessibilityNodeInfoCompat).getVisibleBoundsInScreen(rect3);
                    }
                });
            }
            if (arrayList3.isEmpty()) {
                return SentenceIteratorFactory.generateIterator(arrayList2);
            }
            Object obj2 = this.OptionalMethod$ar$methodName;
            if (obj2 != null) {
                ((OcrController) obj2).recognizeTextForNodes(bitmap, arrayList3, rect, (Filter) this.OptionalMethod$ar$returnType);
            }
            return SentenceIteratorFactory.generateIterator(EmptyList.INSTANCE);
        }
        return null;
    }

    public final String getSnapshotToken() {
        Object obj = this.OptionalMethod$ar$methodParams;
        if (obj != null) {
            return ((SnapshotProto$Snapshot) obj).snapshotToken_;
        }
        Object obj2 = this.OptionalMethod$ar$returnType;
        obj2.getClass();
        return ((SnapshotBlob) obj2).getSnapshotToken();
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object, java.util.concurrent.ExecutorService] */
    public final void increaseEventCount$ar$edu(int i) {
        this.OptionalMethod$ar$methodParams.execute(new CallbackWithHandler$2(this, i, 4));
    }

    public final void invokeOptionalWithoutCheckedException$ar$ds(Object obj, Object... objArr) {
        try {
            Method method = getMethod(obj.getClass());
            if (method == null) {
                return;
            }
            try {
                method.invoke(obj, objArr);
            } catch (IllegalAccessException unused) {
            }
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public final Object invokeWithoutCheckedException(Object obj, Object... objArr) {
        try {
            Method method = getMethod(obj.getClass());
            if (method != null) {
                try {
                    return method.invoke(obj, objArr);
                } catch (IllegalAccessException e) {
                    AssertionError assertionError = new AssertionError("Unexpectedly could not call: ".concat(method.toString()));
                    assertionError.initCause(e);
                    throw assertionError;
                }
            }
            Object obj2 = this.OptionalMethod$ar$methodName;
            throw new AssertionError("Method " + ((String) obj2) + " not supported for object " + String.valueOf(obj));
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError2 = new AssertionError("Unexpected exception");
            assertionError2.initCause(targetException);
            throw assertionError2;
        }
    }

    public final boolean isInForeground(RunningAppProcessInfoResponse runningAppProcessInfoResponse) {
        if (((ForegroundConfigurationModule_UseDebouncedForegroundSignalsInternalFactory) this.OptionalMethod$ar$returnType).get().booleanValue()) {
            if (((ForegroundTracker) this.OptionalMethod$ar$methodParams).foregroundSignalMultiplexer.foregroundState$ar$edu == 2) {
                return true;
            }
            return false;
        }
        return ProcessStats.processHasForegroundImportance((Context) this.OptionalMethod$ar$methodName, runningAppProcessInfoResponse);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Set, java.lang.Object] */
    public final boolean isRoot() {
        return this.OptionalMethod$ar$returnType.isEmpty();
    }

    public final boolean isSupported(Object obj) {
        if (getMethod(obj.getClass()) != null) {
            return true;
        }
        return false;
    }

    public final void listen$ar$class_merging$cca55420_0$ar$class_merging(SplitInstallSharedPreferences splitInstallSharedPreferences, FutureCallbackRegistry$Callback futureCallbackRegistry$Callback, Parcelable parcelable) {
        listenInternal$ar$class_merging$ar$class_merging(splitInstallSharedPreferences, futureCallbackRegistry$Callback, parcelable);
    }

    public final boolean measure$ar$class_merging$6b6fe65d_0(ConstraintLayout.Measurer measurer, ConstraintWidget constraintWidget, int i) {
        boolean z;
        boolean z2;
        ((BasicMeasure$Measure) this.OptionalMethod$ar$methodParams).horizontalBehavior$ar$edu = constraintWidget.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0();
        ((BasicMeasure$Measure) this.OptionalMethod$ar$methodParams).verticalBehavior$ar$edu = constraintWidget.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0();
        ((BasicMeasure$Measure) this.OptionalMethod$ar$methodParams).horizontalDimension = constraintWidget.getWidth();
        ((BasicMeasure$Measure) this.OptionalMethod$ar$methodParams).verticalDimension = constraintWidget.getHeight();
        BasicMeasure$Measure basicMeasure$Measure = (BasicMeasure$Measure) this.OptionalMethod$ar$methodParams;
        basicMeasure$Measure.measuredNeedsSolverPass = false;
        basicMeasure$Measure.measureStrategy = i;
        int i2 = basicMeasure$Measure.horizontalBehavior$ar$edu;
        BasicMeasure$Measure basicMeasure$Measure2 = (BasicMeasure$Measure) this.OptionalMethod$ar$methodParams;
        int i3 = basicMeasure$Measure2.verticalBehavior$ar$edu;
        if (i2 == 3 && constraintWidget.mDimensionRatio > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        if (i3 == 3 && constraintWidget.mDimensionRatio > 0.0f) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
            basicMeasure$Measure2.horizontalBehavior$ar$edu = 1;
        }
        if (z2 && constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
            basicMeasure$Measure2.verticalBehavior$ar$edu = 1;
        }
        measurer.measure(constraintWidget, basicMeasure$Measure2);
        constraintWidget.setWidth(((BasicMeasure$Measure) this.OptionalMethod$ar$methodParams).measuredWidth);
        constraintWidget.setHeight(((BasicMeasure$Measure) this.OptionalMethod$ar$methodParams).measuredHeight);
        BasicMeasure$Measure basicMeasure$Measure3 = (BasicMeasure$Measure) this.OptionalMethod$ar$methodParams;
        constraintWidget.mHasBaseline = basicMeasure$Measure3.measuredHasBaseline;
        constraintWidget.setBaselineDistance(basicMeasure$Measure3.measuredBaseline);
        BasicMeasure$Measure basicMeasure$Measure4 = (BasicMeasure$Measure) this.OptionalMethod$ar$methodParams;
        basicMeasure$Measure4.measureStrategy = 0;
        return basicMeasure$Measure4.measuredNeedsSolverPass;
    }

    public final void onCreateMenu(Menu menu, MenuInflater menuInflater) {
        Iterator it = ((CopyOnWriteArrayList) this.OptionalMethod$ar$methodName).iterator();
        while (it.hasNext()) {
            ((FloatingActionButton.ShadowDelegateImpl) it.next()).onCreateMenu(menu, menuInflater);
        }
    }

    public final boolean onMenuItemSelected(MenuItem menuItem) {
        Iterator it = ((CopyOnWriteArrayList) this.OptionalMethod$ar$methodName).iterator();
        while (it.hasNext()) {
            if (((FloatingActionButton.ShadowDelegateImpl) it.next()).onMenuItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public final void onPrepareMenu(Menu menu) {
        Iterator it = ((CopyOnWriteArrayList) this.OptionalMethod$ar$methodName).iterator();
        while (it.hasNext()) {
            ((FloatingActionButton.ShadowDelegateImpl) it.next()).onPrepareMenu(menu);
        }
    }

    public final Object open(Uri uri, Opener opener) {
        return opener.open$ar$class_merging$a9b907d0_0$ar$class_merging(getContext$ar$class_merging$ar$class_merging(uri));
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object, java.util.concurrent.ExecutorService] */
    public final void recordWordCount$ar$edu(int i, int i2) {
        this.OptionalMethod$ar$methodParams.execute(new SpeechControllerImpl.AnonymousClass4(this, i, i2, 1));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.base.Supplier, java.lang.Object] */
    public final void registerCallback$ar$ds(int i, FutureCallbackRegistry$Callback futureCallbackRegistry$Callback) {
        boolean z = true;
        ContextDataProvider.checkArgument(true, (Object) "Use an R.id value as the callbackId");
        FutureCallbackViewModel futureCallbackViewModel = (FutureCallbackViewModel) this.OptionalMethod$ar$returnType.get();
        FutureCallbackViewModel.assertMainThread$ar$ds$72a67533_0();
        ContextDataProvider.checkState(!futureCallbackViewModel.callbacksFrozen, "Callbacks must be registered in onCreate().");
        if (SparseArrayCompatKt.commonGet(futureCallbackViewModel.callbacks, i) != null) {
            z = false;
        }
        ContextDataProvider.checkState(z, "Callback already registered.");
        SparseArrayCompat sparseArrayCompat = futureCallbackViewModel.callbacks;
        futureCallbackRegistry$Callback.getClass();
        sparseArrayCompat.put(i, futureCallbackRegistry$Callback);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object, java.lang.Runnable] */
    public final void removeMenuProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        ((CopyOnWriteArrayList) this.OptionalMethod$ar$methodName).remove(shadowDelegateImpl);
        if (((ContextCompat$Api33Impl) this.OptionalMethod$ar$returnType.remove(shadowDelegateImpl)) == null) {
            this.OptionalMethod$ar$methodParams.run();
            return;
        }
        throw null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    public final void rename(Uri uri, Uri uri2) {
        WorkQueue context$ar$class_merging$ar$class_merging = getContext$ar$class_merging$ar$class_merging(uri);
        Object obj = context$ar$class_merging$ar$class_merging.WorkQueue$ar$buffer;
        WorkQueue context$ar$class_merging$ar$class_merging2 = getContext$ar$class_merging$ar$class_merging(uri2);
        if (obj == context$ar$class_merging$ar$class_merging2.WorkQueue$ar$buffer) {
            context$ar$class_merging$ar$class_merging.WorkQueue$ar$buffer.rename((Uri) context$ar$class_merging$ar$class_merging.WorkQueue$ar$consumerIndex, (Uri) context$ar$class_merging$ar$class_merging2.WorkQueue$ar$consumerIndex);
            return;
        }
        throw new UnsupportedFileStorageOperation("Cannot rename file across backends");
    }

    public final void set(Object obj) {
        try {
            ((Field) this.OptionalMethod$ar$methodName).set(this.OptionalMethod$ar$returnType, obj);
        } catch (Exception e) {
            throw new SplitCompatReflectionException(String.format("Failed to set value of field %s of type %s on object of type %s", ((Field) this.OptionalMethod$ar$methodName).getName(), this.OptionalMethod$ar$returnType.getClass().getName(), ((Class) this.OptionalMethod$ar$methodParams).getName()), e);
        }
    }

    public final void shutdown() {
        OcrController ocrController;
        TextRecognizer textRecognizer;
        Object obj = this.OptionalMethod$ar$methodName;
        if (obj != null && (textRecognizer = (ocrController = (OcrController) obj).recognizer) != null) {
            textRecognizer.close();
            ocrController.recognizer = null;
        }
    }

    public final void solveLinearSystem$ar$ds(ConstraintWidgetContainer constraintWidgetContainer, int i, int i2, int i3) {
        int i4 = constraintWidgetContainer.mMinWidth;
        int i5 = constraintWidgetContainer.mMinHeight;
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.setWidth(i2);
        constraintWidgetContainer.setHeight(i3);
        constraintWidgetContainer.setMinWidth(i4);
        constraintWidgetContainer.setMinHeight(i5);
        Object obj = this.OptionalMethod$ar$methodName;
        ((ConstraintWidgetContainer) obj).mPass = i;
        ((WidgetContainer) obj).layout();
    }

    public final void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer) {
        ((ArrayList) this.OptionalMethod$ar$returnType).clear();
        int size = constraintWidgetContainer.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer.mChildren.get(i);
            if (constraintWidget.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 || constraintWidget.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3) {
                ((ArrayList) this.OptionalMethod$ar$returnType).add(constraintWidget);
            }
        }
        constraintWidgetContainer.invalidateGraph();
    }

    public OptionalMethod(Context context, ForegroundTracker foregroundTracker, Provider provider) {
        this.OptionalMethod$ar$methodName = context;
        this.OptionalMethod$ar$methodParams = foregroundTracker;
        this.OptionalMethod$ar$returnType = provider;
    }

    public OptionalMethod(RegisterListenerMethod registerListenerMethod, UnregisterListenerMethod unregisterListenerMethod, Runnable runnable, byte[] bArr) {
        this.OptionalMethod$ar$methodParams = registerListenerMethod;
        this.OptionalMethod$ar$returnType = unregisterListenerMethod;
        this.OptionalMethod$ar$methodName = runnable;
    }

    public OptionalMethod(Object obj, Object obj2, Object obj3) {
        this.OptionalMethod$ar$returnType = obj;
        this.OptionalMethod$ar$methodName = obj2;
        this.OptionalMethod$ar$methodParams = obj3;
    }

    public OptionalMethod(Object obj, Object obj2, Object obj3, byte[] bArr) {
        this.OptionalMethod$ar$methodName = obj;
        this.OptionalMethod$ar$methodParams = obj2;
        this.OptionalMethod$ar$returnType = obj3;
    }

    public OptionalMethod(Object obj, Object obj2, Object obj3, char[] cArr) {
        this.OptionalMethod$ar$methodName = obj;
        this.OptionalMethod$ar$returnType = obj2;
        this.OptionalMethod$ar$methodParams = obj3;
    }

    public OptionalMethod(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.OptionalMethod$ar$methodParams = map;
        this.OptionalMethod$ar$returnType = map2;
        this.OptionalMethod$ar$methodName = objectEncoder;
    }

    public OptionalMethod(ConstraintWidgetContainer constraintWidgetContainer) {
        this.OptionalMethod$ar$returnType = new ArrayList();
        this.OptionalMethod$ar$methodParams = new BasicMeasure$Measure();
        this.OptionalMethod$ar$methodName = constraintWidgetContainer;
    }

    public OptionalMethod(ByteString byteString, final String str, final String str2) {
        this.OptionalMethod$ar$methodName = BaseEncoding.BASE64_URL;
        this.OptionalMethod$ar$returnType = ContextDataProvider.memoize(new FrameMetricServiceImpl$$ExternalSyntheticLambda0(this, byteString, 3));
        this.OptionalMethod$ar$methodParams = ContextDataProvider.memoize(new Supplier() { // from class: com.google.android.libraries.phenotype.client.shareddir.PhenotypeSharedDirectoryPath$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                int i = Hashing.Hashing$ar$NoOp;
                HashFunction hashFunction = Murmur3_128HashFunction.MURMUR3_128;
                Hasher putBytes = new Murmur3_128HashFunction.Murmur3_128Hasher(0).putBytes(str.getBytes());
                putBytes.putByte$ar$ds();
                return ((BaseEncoding) OptionalMethod.this.OptionalMethod$ar$methodName).encode(putBytes.putBytes(str2.getBytes()).hash().asBytes());
            }
        });
    }

    public OptionalMethod(NotificationCompat$Builder notificationCompat$Builder) {
        ArrayList arrayList;
        Bundle[] bundleArr;
        new ArrayList();
        this.OptionalMethod$ar$returnType = new Bundle();
        this.OptionalMethod$ar$methodName = notificationCompat$Builder;
        Notification.Builder createBuilder = NotificationCompatBuilder$Api26Impl.createBuilder(notificationCompat$Builder.mContext, notificationCompat$Builder.mChannelId);
        this.OptionalMethod$ar$methodParams = createBuilder;
        Notification notification = notificationCompat$Builder.mNotification;
        createBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(notificationCompat$Builder.mContentTitle).setContentText(notificationCompat$Builder.mContentText).setContentInfo(null).setContentIntent(notificationCompat$Builder.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) != 0).setNumber(0).setProgress(0, 0, false);
        NotificationCompatBuilder$Api23Impl.setLargeIcon(createBuilder, null);
        createBuilder.setSubText(null).setUsesChronometer(false).setPriority(notificationCompat$Builder.mPriority);
        ArrayList arrayList2 = notificationCompat$Builder.mActions;
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            addAction((NotificationCompat$Action) arrayList2.get(i));
        }
        Bundle bundle = notificationCompat$Builder.mExtras;
        if (bundle != null) {
            ((Bundle) this.OptionalMethod$ar$returnType).putAll(bundle);
        }
        ((Notification.Builder) this.OptionalMethod$ar$methodParams).setShowWhen(notificationCompat$Builder.mShowWhen);
        NotificationCompatBuilder$Api20Impl.setLocalOnly((Notification.Builder) this.OptionalMethod$ar$methodParams, notificationCompat$Builder.mLocalOnly);
        NotificationCompatBuilder$Api20Impl.setGroup((Notification.Builder) this.OptionalMethod$ar$methodParams, null);
        NotificationCompatBuilder$Api20Impl.setSortKey((Notification.Builder) this.OptionalMethod$ar$methodParams, null);
        NotificationCompatBuilder$Api20Impl.setGroupSummary((Notification.Builder) this.OptionalMethod$ar$methodParams, false);
        NotificationCompatBuilder$Api21Impl.setCategory((Notification.Builder) this.OptionalMethod$ar$methodParams, null);
        NotificationCompatBuilder$Api21Impl.setColor((Notification.Builder) this.OptionalMethod$ar$methodParams, 0);
        NotificationCompatBuilder$Api21Impl.setVisibility((Notification.Builder) this.OptionalMethod$ar$methodParams, 0);
        NotificationCompatBuilder$Api21Impl.setPublicVersion((Notification.Builder) this.OptionalMethod$ar$methodParams, null);
        NotificationCompatBuilder$Api21Impl.setSound((Notification.Builder) this.OptionalMethod$ar$methodParams, notification.sound, notification.audioAttributes);
        if (Build.VERSION.SDK_INT < 28) {
            ArrayList arrayList3 = notificationCompat$Builder.mPersonList;
            if (arrayList3 == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(arrayList3.size());
                Iterator it = arrayList3.iterator();
                if (it.hasNext()) {
                    throw null;
                }
            }
            ArrayList arrayList4 = notificationCompat$Builder.mPeople;
            if (arrayList == null) {
                arrayList = arrayList4;
            } else if (arrayList4 != null) {
                ArraySet arraySet = new ArraySet(arrayList.size() + arrayList4.size());
                arraySet.addAll(arrayList);
                arraySet.addAll(arrayList4);
                arrayList = new ArrayList(arraySet);
            }
        } else {
            arrayList = notificationCompat$Builder.mPeople;
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                NotificationCompatBuilder$Api21Impl.addPerson((Notification.Builder) this.OptionalMethod$ar$methodParams, (String) it2.next());
            }
        }
        if (notificationCompat$Builder.mInvisibleActions.size() > 0) {
            Bundle bundle2 = notificationCompat$Builder.getExtras().getBundle("android.car.EXTENSIONS");
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            Bundle bundle3 = new Bundle(bundle2);
            Bundle bundle4 = new Bundle();
            for (int i2 = 0; i2 < notificationCompat$Builder.mInvisibleActions.size(); i2++) {
                String num = Integer.toString(i2);
                NotificationCompat$Action notificationCompat$Action = (NotificationCompat$Action) notificationCompat$Builder.mInvisibleActions.get(i2);
                Bundle bundle5 = new Bundle();
                IconCompat iconCompat = notificationCompat$Action.getIconCompat();
                bundle5.putInt("icon", iconCompat != null ? iconCompat.getResId() : 0);
                bundle5.putCharSequence("title", notificationCompat$Action.title);
                bundle5.putParcelable("actionIntent", notificationCompat$Action.actionIntent);
                Bundle bundle6 = new Bundle(notificationCompat$Action.mExtras);
                bundle6.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action.mAllowGeneratedReplies);
                bundle5.putBundle("extras", bundle6);
                ActivityCompat.Api31Impl[] api31ImplArr = notificationCompat$Action.mRemoteInputs$ar$class_merging;
                if (api31ImplArr == null) {
                    bundleArr = null;
                } else {
                    int length = api31ImplArr.length;
                    bundleArr = new Bundle[length];
                    if (length > 0) {
                        ActivityCompat.Api31Impl api31Impl = api31ImplArr[0];
                        new Bundle();
                        throw null;
                    }
                }
                bundle5.putParcelableArray("remoteInputs", bundleArr);
                bundle5.putBoolean("showsUserInterface", notificationCompat$Action.mShowsUserInterface);
                bundle5.putInt("semanticAction", 0);
                bundle4.putBundle(num, bundle5);
            }
            bundle2.putBundle("invisible_actions", bundle4);
            bundle3.putBundle("invisible_actions", bundle4);
            notificationCompat$Builder.getExtras().putBundle("android.car.EXTENSIONS", bundle2);
            ((Bundle) this.OptionalMethod$ar$returnType).putBundle("android.car.EXTENSIONS", bundle3);
        }
        ((Notification.Builder) this.OptionalMethod$ar$methodParams).setExtras(notificationCompat$Builder.mExtras);
        NotificationCompatBuilder$Api24Impl.setRemoteInputHistory((Notification.Builder) this.OptionalMethod$ar$methodParams, null);
        NotificationCompatBuilder$Api26Impl.setBadgeIconType((Notification.Builder) this.OptionalMethod$ar$methodParams, 0);
        NotificationCompatBuilder$Api26Impl.setSettingsText((Notification.Builder) this.OptionalMethod$ar$methodParams, null);
        NotificationCompatBuilder$Api26Impl.setShortcutId((Notification.Builder) this.OptionalMethod$ar$methodParams, null);
        NotificationCompatBuilder$Api26Impl.setTimeoutAfter((Notification.Builder) this.OptionalMethod$ar$methodParams, 0L);
        NotificationCompatBuilder$Api26Impl.setGroupAlertBehavior((Notification.Builder) this.OptionalMethod$ar$methodParams, 0);
        if (!TextUtils.isEmpty(notificationCompat$Builder.mChannelId)) {
            ((Notification.Builder) this.OptionalMethod$ar$methodParams).setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            ArrayList arrayList5 = notificationCompat$Builder.mPersonList;
            if (arrayList5.size() > 0) {
                throw null;
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            NotificationCompatBuilder$Api29Impl.setAllowSystemGeneratedContextualActions((Notification.Builder) this.OptionalMethod$ar$methodParams, notificationCompat$Builder.mAllowSystemGeneratedContextualActions);
            NotificationCompatBuilder$Api29Impl.setBubbleMetadata((Notification.Builder) this.OptionalMethod$ar$methodParams, null);
        }
    }

    public OptionalMethod(SnapshotProto$Snapshot snapshotProto$Snapshot, SnapshotBlob snapshotBlob) {
        ImmutableMap buildKeepingLast;
        int i;
        ByteString byteString;
        String str;
        ContextDataProvider.checkState((snapshotProto$Snapshot != null) ^ (snapshotBlob != null));
        this.OptionalMethod$ar$methodParams = snapshotProto$Snapshot;
        this.OptionalMethod$ar$returnType = snapshotBlob;
        if (snapshotProto$Snapshot != null) {
            ImmutableMap.Builder builderWithExpectedSize = ImmutableMap.builderWithExpectedSize(snapshotProto$Snapshot.flag_.size() + 3);
            for (SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag : snapshotProto$Snapshot.flag_) {
                int i2 = snapshotProto$SnapshotFlag.valueCase_;
                if (i2 == 0) {
                    i = SnapshotProto$SnapshotFlag.ValueCase.VALUE_NOT_SET$ar$edu$d6cc3af1_0;
                } else if (i2 == 2) {
                    i = SnapshotProto$SnapshotFlag.ValueCase.LONG_VALUE$ar$edu$d6cc3af1_0;
                } else if (i2 == 3) {
                    i = SnapshotProto$SnapshotFlag.ValueCase.BOOLEAN_VALUE$ar$edu;
                } else if (i2 == 4) {
                    i = SnapshotProto$SnapshotFlag.ValueCase.DOUBLE_VALUE$ar$edu$d6cc3af1_0;
                } else if (i2 != 5) {
                    i = i2 != 6 ? 0 : SnapshotProto$SnapshotFlag.ValueCase.BYTES_VALUE$ar$edu$d6cc3af1_0;
                } else {
                    i = SnapshotProto$SnapshotFlag.ValueCase.STRING_VALUE$ar$edu$d6cc3af1_0;
                }
                if (i == 0) {
                    throw null;
                }
                int i3 = i - 1;
                if (i3 == 0) {
                    builderWithExpectedSize.put$ar$ds$de9b9d28_0(snapshotProto$SnapshotFlag.name_, Long.valueOf(snapshotProto$SnapshotFlag.valueCase_ == 2 ? ((Long) snapshotProto$SnapshotFlag.value_).longValue() : 0L));
                } else if (i3 == 1) {
                    builderWithExpectedSize.put$ar$ds$de9b9d28_0(snapshotProto$SnapshotFlag.name_, Boolean.valueOf(snapshotProto$SnapshotFlag.valueCase_ == 3 ? ((Boolean) snapshotProto$SnapshotFlag.value_).booleanValue() : false));
                } else if (i3 == 2) {
                    builderWithExpectedSize.put$ar$ds$de9b9d28_0(snapshotProto$SnapshotFlag.name_, Double.valueOf(snapshotProto$SnapshotFlag.valueCase_ == 4 ? ((Double) snapshotProto$SnapshotFlag.value_).doubleValue() : 0.0d));
                } else if (i3 == 3) {
                    String str2 = snapshotProto$SnapshotFlag.name_;
                    if (snapshotProto$SnapshotFlag.valueCase_ == 5) {
                        str = (String) snapshotProto$SnapshotFlag.value_;
                    } else {
                        str = "";
                    }
                    builderWithExpectedSize.put$ar$ds$de9b9d28_0(str2, str);
                } else if (i3 == 4) {
                    String str3 = snapshotProto$SnapshotFlag.name_;
                    if (snapshotProto$SnapshotFlag.valueCase_ == 6) {
                        byteString = (ByteString) snapshotProto$SnapshotFlag.value_;
                    } else {
                        byteString = ByteString.EMPTY;
                    }
                    builderWithExpectedSize.put$ar$ds$de9b9d28_0(str3, byteString.toByteArray());
                }
            }
            builderWithExpectedSize.put$ar$ds$de9b9d28_0("__phenotype_server_token", snapshotProto$Snapshot.serverToken_);
            builderWithExpectedSize.put$ar$ds$de9b9d28_0("__phenotype_snapshot_token", snapshotProto$Snapshot.snapshotToken_);
            builderWithExpectedSize.put$ar$ds$de9b9d28_0("__phenotype_configuration_version", Long.valueOf(snapshotProto$Snapshot.configurationVersion_));
            buildKeepingLast = builderWithExpectedSize.buildKeepingLast();
        } else {
            snapshotBlob.getClass();
            FlagsBlob flagsBlob = snapshotBlob.flagsBlob;
            ImmutableMap.Builder builderWithExpectedSize2 = ImmutableMap.builderWithExpectedSize(flagsBlob.values.size() + 3);
            UnmodifiableIterator listIterator = flagsBlob.values.listIterator();
            while (listIterator.hasNext()) {
                FlagsBlob.ParsedParam parsedParam = (FlagsBlob.ParsedParam) listIterator.next();
                builderWithExpectedSize2.put$ar$ds$de9b9d28_0(parsedParam.nameAsString(), parsedParam.toObject());
            }
            builderWithExpectedSize2.put$ar$ds$de9b9d28_0("__phenotype_server_token", snapshotBlob.tokens.serverToken_);
            builderWithExpectedSize2.put$ar$ds$de9b9d28_0("__phenotype_snapshot_token", snapshotBlob.getSnapshotToken());
            builderWithExpectedSize2.put$ar$ds$de9b9d28_0("__phenotype_configuration_version", Long.valueOf(snapshotBlob.tokens.configurationVersion_));
            buildKeepingLast = builderWithExpectedSize2.buildKeepingLast();
        }
        this.OptionalMethod$ar$methodName = buildKeepingLast;
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.util.List, java.lang.Object] */
    public OptionalMethod(List list) {
        List<Transform> emptyList = Collections.emptyList();
        List emptyList2 = Collections.emptyList();
        this.OptionalMethod$ar$returnType = new HashMap();
        this.OptionalMethod$ar$methodName = new HashMap();
        this.OptionalMethod$ar$methodParams = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Backend backend = (Backend) it.next();
            if (TextUtils.isEmpty(backend.name())) {
                Log.w("MobStore.FileStorage", "Cannot register backend, name empty");
            } else {
                Backend backend2 = (Backend) this.OptionalMethod$ar$returnType.put(backend.name(), backend);
                if (backend2 != null) {
                    throw new IllegalArgumentException("Cannot override Backend " + backend2.getClass().getCanonicalName() + " with " + backend.getClass().getCanonicalName());
                }
            }
        }
        for (Transform transform : emptyList) {
            if (TextUtils.isEmpty(transform.name())) {
                Log.w("MobStore.FileStorage", "Cannot register transform, name empty");
            } else {
                Transform transform2 = (Transform) this.OptionalMethod$ar$methodName.put(transform.name(), transform);
                if (transform2 != null) {
                    throw new IllegalArgumentException("Cannot to override Transform " + transform2.getClass().getCanonicalName() + " with " + transform.getClass().getCanonicalName());
                }
            }
        }
        this.OptionalMethod$ar$methodParams.addAll(emptyList2);
    }

    public OptionalMethod(Object obj, Field field, Class cls, byte[] bArr) {
        this(obj, field, Array.newInstance((Class<?>) cls, 0).getClass());
    }

    private OptionalMethod(Lifecycle lifecycle, Supplier supplier, Supplier supplier2, ContextHolder contextHolder) {
        ContextDataProvider.checkState(lifecycle.getCurrentState() == Lifecycle.State.INITIALIZED, "FutureCallbackRegistry must be created in onCreate of the Activity/Fragment.");
        this.OptionalMethod$ar$methodParams = supplier;
        this.OptionalMethod$ar$methodName = contextHolder;
        this.OptionalMethod$ar$returnType = ContextDataProvider.memoize(new FrameMetricServiceImpl$$ExternalSyntheticLambda0(this, supplier2, 4));
        lifecycle.addObserver(new DefaultLifecycleObserver() { // from class: com.google.common.android.concurrent.FutureCallbackRegistry$FutureListenerLifecycleObserver
            private boolean isLaterObserverRegistered = false;

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.base.Supplier, java.lang.Object] */
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onDestroy$ar$ds() {
                boolean z;
                FutureCallbackViewModel futureCallbackViewModel = (FutureCallbackViewModel) OptionalMethod.this.OptionalMethod$ar$returnType.get();
                if (futureCallbackViewModel.hostFragmentManager == null) {
                    z = true;
                } else {
                    z = false;
                }
                ContextDataProvider.checkState(z);
                int size = futureCallbackViewModel.callbacks.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        SparseArrayCompat sparseArrayCompat = futureCallbackViewModel.callbacks;
                        if (sparseArrayCompat.garbage) {
                            SparseArrayCompatKt.gc(sparseArrayCompat);
                        }
                        sparseArrayCompat.values[size] = null;
                    } else {
                        futureCallbackViewModel.callbacksFrozen = false;
                        return;
                    }
                }
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.base.Supplier, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r1v1, types: [com.google.common.base.Supplier, java.lang.Object] */
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onResume(LifecycleOwner lifecycleOwner) {
                ((FutureCallbackViewModel) OptionalMethod.this.OptionalMethod$ar$returnType.get()).startCallbacks((FragmentManager) OptionalMethod.this.OptionalMethod$ar$methodParams.get());
                if (!this.isLaterObserverRegistered) {
                    Lifecycle lifecycle2 = lifecycleOwner.getLifecycle();
                    final OptionalMethod optionalMethod = OptionalMethod.this;
                    lifecycle2.addObserver(new DefaultLifecycleObserver() { // from class: com.google.common.android.concurrent.FutureCallbackRegistry$LaterFutureListenerLifecycleObserver
                        /* JADX WARN: Type inference failed for: r2v2, types: [com.google.common.base.Supplier, java.lang.Object] */
                        @Override // androidx.lifecycle.DefaultLifecycleObserver
                        public final void onResume(LifecycleOwner lifecycleOwner2) {
                            ((FutureCallbackViewModel) OptionalMethod.this.OptionalMethod$ar$returnType.get()).resumedOnce = true;
                        }

                        @Override // androidx.lifecycle.DefaultLifecycleObserver
                        public final /* synthetic */ void onDestroy$ar$ds() {
                        }

                        @Override // androidx.lifecycle.DefaultLifecycleObserver
                        public final /* synthetic */ void onStart$ar$ds$f453d6c4_0() {
                        }

                        @Override // androidx.lifecycle.DefaultLifecycleObserver
                        public final /* synthetic */ void onStop$ar$ds() {
                        }
                    });
                    this.isLaterObserverRegistered = true;
                }
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.base.Supplier, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r1v1, types: [com.google.common.base.Supplier, java.lang.Object] */
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onStart$ar$ds$f453d6c4_0() {
                ((FutureCallbackViewModel) OptionalMethod.this.OptionalMethod$ar$returnType.get()).startCallbacks((FragmentManager) OptionalMethod.this.OptionalMethod$ar$methodParams.get());
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.base.Supplier, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r1v1, types: [com.google.common.base.Supplier, java.lang.Object] */
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onStop$ar$ds() {
                boolean z;
                FutureCallbackViewModel futureCallbackViewModel = (FutureCallbackViewModel) OptionalMethod.this.OptionalMethod$ar$returnType.get();
                Object obj = OptionalMethod.this.OptionalMethod$ar$methodParams.get();
                boolean z2 = true;
                if (obj != null) {
                    z = true;
                } else {
                    z = false;
                }
                ContextDataProvider.checkArgument(z);
                FragmentManager fragmentManager = futureCallbackViewModel.hostFragmentManager;
                if (fragmentManager != null) {
                    if (obj != fragmentManager) {
                        z2 = false;
                    }
                    ContextDataProvider.checkState(z2);
                    futureCallbackViewModel.hostFragmentManager = null;
                    ArraySet.ElementIterator elementIterator = new ArraySet.ElementIterator();
                    while (elementIterator.hasNext()) {
                        ((ParcelableFuture) elementIterator.next()).setListener$ar$class_merging$59ab07de_0(null);
                    }
                }
            }
        });
    }

    public OptionalMethod(Component component) {
        this.OptionalMethod$ar$methodParams = new HashSet();
        this.OptionalMethod$ar$returnType = new HashSet();
        this.OptionalMethod$ar$methodName = component;
    }

    public OptionalMethod(Runnable runnable) {
        this.OptionalMethod$ar$methodName = new CopyOnWriteArrayList();
        this.OptionalMethod$ar$returnType = new HashMap();
        this.OptionalMethod$ar$methodParams = runnable;
    }

    public OptionalMethod(Context context, byte[] bArr) {
        this.OptionalMethod$ar$methodParams = new ColorDrawable(0);
        this.OptionalMethod$ar$methodName = context;
        this.OptionalMethod$ar$returnType = ColorStateList.valueOf(context.getColor(R.color.ripple_material_color));
    }

    public OptionalMethod(Context context) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.OptionalMethod$ar$methodName = context;
        this.OptionalMethod$ar$returnType = SelectToSpeakLogSender.getSharedPreference(context);
        this.OptionalMethod$ar$methodParams = newSingleThreadExecutor;
        scheduleDailyJob$ar$ds(context);
    }

    public OptionalMethod(Function0 function0, OcrController.OcrListener ocrListener) {
        this.OptionalMethod$ar$methodParams = function0;
        FilterList filterList = new FilterList();
        int i = 1;
        if (((Boolean) function0.invoke()).booleanValue()) {
            filterList.add(new IsImageFilter());
            filterList.add(new IsGoogleDocsEditTextFilter());
        } else {
            filterList.add(new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(i)));
        }
        this.OptionalMethod$ar$returnType = filterList;
        this.OptionalMethod$ar$methodName = ocrListener != null ? new OcrController(ocrListener, true) : null;
    }

    public OptionalMethod(String str, ClearcutAnalyticsHelper$PropertySetter clearcutAnalyticsHelper$PropertySetter) {
        this.OptionalMethod$ar$returnType = clearcutAnalyticsHelper$PropertySetter;
        this.OptionalMethod$ar$methodName = str;
        this.OptionalMethod$ar$methodParams = new HashMap();
    }
}

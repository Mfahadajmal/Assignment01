package com.google.common.flogger.context;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.os.StrictMode;
import androidx.preference.Preference;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$MemoizingSupplier;
import com.google.common.base.Suppliers$NonSerializableMemoizingSupplier;
import com.google.common.base.VerifyException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists$RandomAccessReverseList;
import com.google.common.collect.Lists$ReverseList;
import com.google.common.collect.Lists$TransformingRandomAccessList;
import com.google.common.collect.Lists$TransformingSequentialList;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.SortedIterable;
import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.io.Closeables;
import com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto;
import com.google.common.logging.proto2api.Logrecord$ThrowableProto;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractListeningExecutorService;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures$NonCancellationPropagatingFuture;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors$ScheduledListeningDecorator;
import com.google.common.util.concurrent.TimeoutFuture;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.CycleDetector$Dep;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.DependencyCycleException;
import com.google.firebase.components.Qualified;
import com.google.firebase.inject.Provider;
import com.google.frameworks.client.data.android.cronet.CronetConfigurations;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10;
import com.google.frameworks.client.data.android.util.FailingClientCall;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import dagger.internal.InstanceFactory;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.internal.RetriableStream;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.NetworkQualityRttListener;
import org.chromium.net.NetworkQualityThroughputListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ContextDataProvider {
    public static Object $default$get(ComponentContainer componentContainer, Class cls) {
        Provider provider = componentContainer.getProvider(new Qualified(Qualified.Unqualified.class, cls));
        if (provider == null) {
            return null;
        }
        return provider.get();
    }

    public static Set $default$setOf(ComponentContainer componentContainer, Qualified qualified) {
        return (Set) componentContainer.setOfProvider(qualified).get();
    }

    public ContextDataProvider() {
    }

    public static boolean addAll(Collection collection, Iterator it) {
        it.getClass();
        boolean z = false;
        while (it.hasNext()) {
            z |= collection.add(it.next());
        }
        return z;
    }

    public static void addCallback(final ListenableFuture listenableFuture, final FutureCallback futureCallback, Executor executor) {
        listenableFuture.addListener(new Runnable(listenableFuture, futureCallback) { // from class: com.google.common.util.concurrent.Futures$CallbackListener
            final FutureCallback callback;
            final Future future;

            {
                this.future = listenableFuture;
                this.callback = futureCallback;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Throwable tryInternalFastPathGetFailure;
                Object obj = this.future;
                if ((obj instanceof InternalFutureFailureAccess) && (tryInternalFastPathGetFailure = ((InternalFutureFailureAccess) obj).tryInternalFastPathGetFailure()) != null) {
                    this.callback.onFailure(tryInternalFastPathGetFailure);
                    return;
                }
                try {
                    this.callback.onSuccess(ContextDataProvider.getDone(this.future));
                } catch (ExecutionException e) {
                    this.callback.onFailure(e.getCause());
                } catch (Throwable th) {
                    this.callback.onFailure(th);
                }
            }

            public final String toString() {
                MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
                stringHelper.addHolder().value = this.callback;
                return stringHelper.toString();
            }
        }, executor);
    }

    public static void applyNetworkQualityBuilderSettings(Optional optional, ExperimentalCronetEngine.Builder builder) {
        if (optional.isPresent()) {
            builder.enableNetworkQualityEstimator(((CronetConfigurations.CronetConfig) optional.get()).enableNetworkQualityEstimator());
        }
    }

    public static void applyNetworkQualitySettings(Optional optional, ExperimentalCronetEngine experimentalCronetEngine, javax.inject.Provider provider, javax.inject.Provider provider2) {
        if (optional.isPresent() && ((CronetConfigurations.CronetConfig) optional.get()).enableNetworkQualityEstimator()) {
            Iterator it = ((Set) ((InstanceFactory) provider).instance).iterator();
            while (it.hasNext()) {
                experimentalCronetEngine.addRttListener((NetworkQualityRttListener) it.next());
            }
            Iterator it2 = ((Set) ((InstanceFactory) provider2).instance).iterator();
            while (it2.hasNext()) {
                experimentalCronetEngine.addThroughputListener((NetworkQualityThroughputListener) it2.next());
            }
        }
    }

    public static void applyNonDiskCacheSettings(Optional optional, ExperimentalCronetEngine.Builder builder) {
        if (!optional.isPresent()) {
            return;
        }
        if (((CronetConfigurations.CronetConfig) optional.get()).enableInMemoryFallbackCache()) {
            builder.enableHttpCache(1, r2.inMemoryFallbackCacheSizeBytes());
        } else {
            builder.enableHttpCache(0, 0L);
        }
    }

    private static String badPositionIndex(int i, int i2, String str) {
        if (i < 0) {
            return lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return lenientFormat("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i2, "negative size: "));
    }

    public static int capacity(int i) {
        if (i < 3) {
            checkNonnegative$ar$ds(i, "expectedSize");
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) Math.ceil(i / 0.75d);
        }
        return Preference.DEFAULT_ORDER;
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkElementIndex$ar$ds(int i, int i2) {
        String lenientFormat;
        if (i >= 0 && i < i2) {
            return;
        }
        if (i >= 0) {
            if (i2 < 0) {
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i2, "negative size: "));
            }
            lenientFormat = lenientFormat("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            lenientFormat = lenientFormat("%s (%s) must not be negative", "index", Integer.valueOf(i));
        }
        throw new IndexOutOfBoundsException(lenientFormat);
    }

    public static void checkElementNotNull$ar$ds(Object obj, int i) {
        if (obj != null) {
        } else {
            throw new NullPointerException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "at index "));
        }
    }

    public static void checkElementsNotNull$ar$ds$c35b535c_0(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            checkElementNotNull$ar$ds(objArr[i2], i2);
        }
    }

    public static void checkEntryNotNull(Object obj, Object obj2) {
        if (obj != null) {
            if (obj2 != null) {
                return;
            } else {
                throw new NullPointerException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(obj, "null value in entry: ", "=null"));
            }
        }
        throw new NullPointerException("null key in entry: null=".concat(String.valueOf(String.valueOf(obj2))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [io.grpc.MethodDescriptor$ReflectableMarshaller] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public static void checkMessageType(MethodDescriptor methodDescriptor, Class cls, boolean z) {
        ?? r0;
        Class<?> cls2;
        boolean z2;
        String str;
        String str2;
        if (z) {
            r0 = methodDescriptor.requestMarshaller;
        } else {
            r0 = methodDescriptor.responseMarshaller;
        }
        try {
            cls2 = r0.getMessageClass();
            z2 = false;
        } catch (ClassCastException unused) {
            cls2 = Object.class;
            z2 = true;
        }
        if (!cls.isAssignableFrom(cls2)) {
            if (true != z) {
                str = "response";
            } else {
                str = "request";
            }
            String str3 = methodDescriptor.fullMethodName;
            Status status = Status.INTERNAL;
            String name = cls2.getName();
            if (true != z2) {
                str2 = "";
            } else {
                str2 = ", assumed because method doesn't use ReflectableMarshaller";
            }
            throw new StatusException(status.withDescription("AsyncClientInterceptor: The " + str + " message type of method " + str3 + " (" + name + str2 + ") must be a subclass of " + cls.toString()));
        }
    }

    public static void checkNonnegative$ar$ds(int i, String str) {
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + i);
    }

    public static void checkPositionIndex$ar$ds(int i, int i2) {
        if (i >= 0 && i <= i2) {
        } else {
            throw new IndexOutOfBoundsException(badPositionIndex(i, i2, "index"));
        }
    }

    public static void checkPositionIndexes(int i, int i2, int i3) {
        String badPositionIndex;
        if (i >= 0 && i2 >= i && i2 <= i3) {
            return;
        }
        if (i >= 0 && i <= i3) {
            if (i2 >= 0 && i2 <= i3) {
                badPositionIndex = lenientFormat("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            } else {
                badPositionIndex = badPositionIndex(i2, i3, "end index");
            }
        } else {
            badPositionIndex = badPositionIndex(i, i3, "start index");
        }
        throw new IndexOutOfBoundsException(badPositionIndex);
    }

    public static void checkRemove(boolean z) {
        checkState(z, "no calls to next() since the last call to remove()");
    }

    public static void checkRoundingUnnecessary(boolean z) {
        if (z) {
        } else {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void clear(Iterator it) {
        it.getClass();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public static int constrainToRange(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public static void copyReaderToBuilder$ar$ds(Reader reader, StringBuilder sb) {
        char[] cArr = new char[2048];
        while (true) {
            int read = reader.read(cArr);
            if (read != -1) {
                sb.append(cArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static ExperimentalCronetEngine.Builder createBuilderWithDefaultCacheSettings(Context context, Set set, Set set2, Optional optional, Optional optional2, javax.inject.Provider provider) {
        ExperimentalCronetEngine.Builder builder = null;
        if (optional.isPresent()) {
            CronetConfigurations.CronetConfig cronetConfig = (CronetConfigurations.CronetConfig) optional.get();
            if (cronetConfig.context() != null) {
                context = cronetConfig.context();
            }
            if (cronetConfig.cronetEngineBuilderFactory() != null) {
                builder = cronetConfig.cronetEngineBuilderFactory().createBuilder();
            }
        }
        if (builder == null) {
            builder = new ExperimentalCronetEngine.Builder(context);
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            builder.addQuicHint(((CronetConfigurations.QuicHint) it.next()).host(), 443, 443);
        }
        Optional optional3 = (Optional) ((InstanceFactory) provider).instance;
        if (optional3.isPresent() && set2.isEmpty()) {
            throw new IllegalArgumentException("Google digests were provided, but no hostnames were pinned - this is either a security vulnerability or a waste of APK size. Add hostnames, or remove the dep on google digests.");
        }
        if (!set2.isEmpty() && !optional3.isPresent()) {
            throw new IllegalArgumentException("Pinning was requested, but digests weren't provided. Add a dependency on the google digests.");
        }
        if (optional2.isPresent()) {
            checkState(!set2.isEmpty(), "Local trust anchor bypass was specified, but no pinned hostnames were provided.");
            builder.enablePublicKeyPinningBypassForLocalTrustAnchors(((Boolean) optional2.get()).booleanValue());
        }
        Iterator it2 = set2.iterator();
        while (it2.hasNext()) {
            CronetConfigurations.PublicKeyPin publicKeyPin = (CronetConfigurations.PublicKeyPin) it2.next();
            builder.addPublicKeyPins(publicKeyPin.host(), (Set<byte[]>) optional3.get(), publicKeyPin.includeSubdomains(), publicKeyPin.expirationDate());
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            if (optional.isPresent()) {
                CronetConfigurations.CronetConfig cronetConfig2 = (CronetConfigurations.CronetConfig) optional.get();
                builder.enableQuic(cronetConfig2.enableQuic());
                builder.enableBrotli(cronetConfig2.enableBrotli());
                if (cronetConfig2.libraryLoader() != null) {
                    builder.setLibraryLoader(cronetConfig2.libraryLoader());
                }
                if (cronetConfig2.experimentalOptions() != null) {
                    builder.setExperimentalOptions(cronetConfig2.experimentalOptions());
                }
                if (cronetConfig2.threadPriority() != 20) {
                    builder.setThreadPriority(cronetConfig2.threadPriority());
                }
            } else {
                builder.enableQuic(true);
            }
            return builder;
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static SystemHealthProto$PackedHistogram.Builder createNode$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Throwable th) {
        StackTraceElement[] stackTraceElementArr;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Logrecord$ThrowableProto.ThrowableNode.DEFAULT_INSTANCE.createBuilder();
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Logrecord$ThrowableBlockProto.DEFAULT_INSTANCE.createBuilder();
        String name = th.getClass().getName();
        builder2.copyOnWrite();
        Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto = (Logrecord$ThrowableBlockProto) builder2.instance;
        name.getClass();
        logrecord$ThrowableBlockProto.bitField0_ |= 1;
        logrecord$ThrowableBlockProto.originalClass_ = name;
        if (th.getMessage() != null) {
            String message = th.getMessage();
            builder2.copyOnWrite();
            Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto2 = (Logrecord$ThrowableBlockProto) builder2.instance;
            message.getClass();
            logrecord$ThrowableBlockProto2.bitField0_ |= 2;
            logrecord$ThrowableBlockProto2.message_ = message;
        }
        try {
            stackTraceElementArr = th.getStackTrace();
        } catch (NullPointerException unused) {
            stackTraceElementArr = null;
        }
        if (stackTraceElementArr != null) {
            for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) Logrecord$ThrowableBlockProto.StackTraceElement.DEFAULT_INSTANCE.createBuilder();
                if (stackTraceElement != null) {
                    String className = stackTraceElement.getClassName();
                    builder3.copyOnWrite();
                    Logrecord$ThrowableBlockProto.StackTraceElement stackTraceElement2 = (Logrecord$ThrowableBlockProto.StackTraceElement) builder3.instance;
                    className.getClass();
                    stackTraceElement2.bitField0_ |= 1;
                    stackTraceElement2.declaringClass_ = className;
                    String methodName = stackTraceElement.getMethodName();
                    builder3.copyOnWrite();
                    Logrecord$ThrowableBlockProto.StackTraceElement stackTraceElement3 = (Logrecord$ThrowableBlockProto.StackTraceElement) builder3.instance;
                    methodName.getClass();
                    stackTraceElement3.bitField0_ |= 2;
                    stackTraceElement3.methodName_ = methodName;
                    int lineNumber = stackTraceElement.getLineNumber();
                    builder3.copyOnWrite();
                    Logrecord$ThrowableBlockProto.StackTraceElement stackTraceElement4 = (Logrecord$ThrowableBlockProto.StackTraceElement) builder3.instance;
                    stackTraceElement4.bitField0_ |= 8;
                    stackTraceElement4.lineNumber_ = lineNumber;
                    if (stackTraceElement.getFileName() != null) {
                        String fileName = stackTraceElement.getFileName();
                        builder3.copyOnWrite();
                        Logrecord$ThrowableBlockProto.StackTraceElement stackTraceElement5 = (Logrecord$ThrowableBlockProto.StackTraceElement) builder3.instance;
                        fileName.getClass();
                        stackTraceElement5.bitField0_ |= 4;
                        stackTraceElement5.fileName_ = fileName;
                    }
                }
                builder2.copyOnWrite();
                Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto3 = (Logrecord$ThrowableBlockProto) builder2.instance;
                Logrecord$ThrowableBlockProto.StackTraceElement stackTraceElement6 = (Logrecord$ThrowableBlockProto.StackTraceElement) builder3.build();
                stackTraceElement6.getClass();
                Internal.ProtobufList protobufList = logrecord$ThrowableBlockProto3.stackTraceElement_;
                if (!protobufList.isModifiable()) {
                    logrecord$ThrowableBlockProto3.stackTraceElement_ = GeneratedMessageLite.mutableCopy(protobufList);
                }
                logrecord$ThrowableBlockProto3.stackTraceElement_.add(stackTraceElement6);
            }
        }
        builder.copyOnWrite();
        Logrecord$ThrowableProto.ThrowableNode throwableNode = (Logrecord$ThrowableProto.ThrowableNode) builder.instance;
        Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto4 = (Logrecord$ThrowableBlockProto) builder2.build();
        logrecord$ThrowableBlockProto4.getClass();
        throwableNode.throwableInfo_ = logrecord$ThrowableBlockProto4;
        throwableNode.bitField0_ |= 1;
        return builder;
    }

    public static void createParentDirs(File file) {
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
            if (parentFile.isDirectory()) {
            } else {
                throw new IOException("Unable to create parent directories of ".concat(file.toString()));
            }
        }
    }

    public static Object createTable(int i) {
        if (i >= 2 && i <= 1073741824 && Integer.highestOneBit(i) == i) {
            if (i <= 256) {
                return new byte[i];
            }
            if (i <= 65536) {
                return new short[i];
            }
            return new int[i];
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "must be power of 2 between 2^1 and 2^30: "));
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.Set, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.Set, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.Set, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.util.Set, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.util.Set, java.lang.Object] */
    public static void detect(List list) {
        Set<OptionalMethod> set;
        HashMap hashMap = new HashMap(list.size());
        Iterator it = list.iterator();
        while (true) {
            int i = 0;
            if (it.hasNext()) {
                Component component = (Component) it.next();
                OptionalMethod optionalMethod = new OptionalMethod(component);
                for (Qualified qualified : component.providedInterfaces) {
                    CycleDetector$Dep cycleDetector$Dep = new CycleDetector$Dep(qualified, !component.isValue());
                    if (!hashMap.containsKey(cycleDetector$Dep)) {
                        hashMap.put(cycleDetector$Dep, new HashSet());
                    }
                    Set set2 = (Set) hashMap.get(cycleDetector$Dep);
                    if (!set2.isEmpty() && !cycleDetector$Dep.set) {
                        throw new IllegalArgumentException(String.format("Multiple components provide %s.", qualified));
                    }
                    set2.add(optionalMethod);
                }
            } else {
                Iterator it2 = hashMap.values().iterator();
                while (it2.hasNext()) {
                    for (OptionalMethod optionalMethod2 : (Set) it2.next()) {
                        for (Dependency dependency : ((Component) optionalMethod2.OptionalMethod$ar$methodName).dependencies) {
                            if (dependency.isDirectInjection() && (set = (Set) hashMap.get(new CycleDetector$Dep(dependency.anInterface, dependency.isSet()))) != null) {
                                for (OptionalMethod optionalMethod3 : set) {
                                    optionalMethod2.OptionalMethod$ar$methodParams.add(optionalMethod3);
                                    optionalMethod3.OptionalMethod$ar$returnType.add(optionalMethod2);
                                }
                            }
                        }
                    }
                }
                HashSet<OptionalMethod> hashSet = new HashSet();
                Iterator it3 = hashMap.values().iterator();
                while (it3.hasNext()) {
                    hashSet.addAll((Set) it3.next());
                }
                HashSet hashSet2 = new HashSet();
                for (OptionalMethod optionalMethod4 : hashSet) {
                    if (optionalMethod4.isRoot()) {
                        hashSet2.add(optionalMethod4);
                    }
                }
                while (!hashSet2.isEmpty()) {
                    OptionalMethod optionalMethod5 = (OptionalMethod) hashSet2.iterator().next();
                    hashSet2.remove(optionalMethod5);
                    i++;
                    for (OptionalMethod optionalMethod6 : optionalMethod5.OptionalMethod$ar$methodParams) {
                        optionalMethod6.OptionalMethod$ar$returnType.remove(optionalMethod5);
                        if (optionalMethod6.isRoot()) {
                            hashSet2.add(optionalMethod6);
                        }
                    }
                }
                if (i == list.size()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (OptionalMethod optionalMethod7 : hashSet) {
                    if (!optionalMethod7.isRoot() && !optionalMethod7.OptionalMethod$ar$methodParams.isEmpty()) {
                        arrayList.add(optionalMethod7.OptionalMethod$ar$methodName);
                    }
                }
                throw new DependencyCycleException(arrayList);
            }
        }
    }

    public static String emptyToNull(String str) {
        if (stringIsNullOrEmpty(str)) {
            return null;
        }
        return str;
    }

    public static boolean equalsImpl(List list, Object obj) {
        if (obj == list) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list2 = (List) obj;
        int size = list.size();
        if (size != list2.size()) {
            return false;
        }
        if (list2 instanceof RandomAccess) {
            for (int i = 0; i < size; i++) {
                if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(list.get(i), list2.get(i))) {
                    return false;
                }
            }
            return true;
        }
        Iterator it = list.iterator();
        Iterator it2 = list2.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!it2.hasNext() || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(it.next(), it2.next())) {
                    break;
                }
            } else if (!it2.hasNext()) {
                return true;
            }
        }
        return false;
    }

    public static Object firstNonNull(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        if (obj2 != null) {
            return obj2;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static ClientInterceptor forStage(javax.inject.Provider provider) {
        final PrimesConfigurations$Builder$$ExternalSyntheticLambda0 primesConfigurations$Builder$$ExternalSyntheticLambda0 = new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(provider, 10);
        final Class<MessageLite> cls = MessageLite.class;
        final int i = 2;
        return new ClientInterceptor(primesConfigurations$Builder$$ExternalSyntheticLambda0, i, cls, cls) { // from class: com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptors$AsyncInterceptorsClientInterceptor
            private final javax.inject.Provider asyncInterceptors;
            private final Class requestClass;
            private final Class responseClass;
            private final int transportType$ar$edu = 2;

            {
                this.asyncInterceptors = primesConfigurations$Builder$$ExternalSyntheticLambda0;
                this.requestClass = cls;
                this.responseClass = cls;
            }

            @Override // io.grpc.ClientInterceptor
            public final ClientCall interceptCall(MethodDescriptor methodDescriptor, CallOptions callOptions, Channel channel) {
                try {
                    Object obj = this.asyncInterceptors.get();
                    ContextDataProvider.checkMessageType(methodDescriptor, this.requestClass, true);
                    ContextDataProvider.checkMessageType(methodDescriptor, this.responseClass, false);
                    return new OrderVerifyingClientCall(new AsyncInterceptorsClientCall(channel, methodDescriptor, callOptions, 2, (ImmutableList) obj));
                } catch (StatusException e) {
                    return new FailingClientCall(Status.fromThrowable(e));
                }
            }
        };
    }

    public static int getAndroidLevel$ar$edu(Level level) {
        int intValue = level.intValue();
        if (intValue >= Level.SEVERE.intValue()) {
            return 6;
        }
        if (intValue >= Level.WARNING.intValue()) {
            return 5;
        }
        if (intValue >= Level.INFO.intValue()) {
            return 4;
        }
        if (intValue >= Level.FINE.intValue()) {
            return 3;
        }
        return 2;
    }

    public static Object getDone(Future future) {
        checkState(future.isDone(), "Future was expected to be done: %s", future);
        return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_14(future);
    }

    public static SplitInstallSharedPreferences getFieldSetter$ar$class_merging$ar$class_merging$ar$class_merging(Class cls, String str) {
        try {
            return new SplitInstallSharedPreferences(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static int getHashPrefix(int i, int i2) {
        return i & (~i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object getLast(Iterable iterable) {
        Object next;
        if (iterable instanceof List) {
            if (!iterable.isEmpty()) {
                return iterable.get(iterable.size() - 1);
            }
            throw new NoSuchElementException();
        }
        Iterator it = iterable.iterator();
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    public static Object getNext$ar$ds(Iterator it) {
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static String getValidTag$ar$ds(String str) {
        if (str.length() > 23) {
            int i = -1;
            for (int length = str.length() - 1; length >= 0; length--) {
                char charAt = str.charAt(length);
                if (charAt == '.' || charAt == '$') {
                    i = length;
                    break;
                }
            }
            str = str.substring(i + 1);
        }
        String concat = "".concat(String.valueOf(str));
        return concat.substring(0, Math.min(concat.length(), 23));
    }

    public static boolean hasSameComparator(Comparator comparator, Iterable iterable) {
        Comparator comparator2;
        comparator.getClass();
        iterable.getClass();
        if (iterable instanceof SortedSet) {
            comparator2 = ((SortedSet) iterable).comparator();
            if (comparator2 == null) {
                comparator2 = NaturalOrdering.INSTANCE;
            }
        } else if (iterable instanceof SortedIterable) {
            comparator2 = ((SortedIterable) iterable).comparator();
        } else {
            return false;
        }
        return comparator.equals(comparator2);
    }

    public static int hashCodeImpl(Set set) {
        int i;
        int i2 = 0;
        for (Object obj : set) {
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            i2 += i;
        }
        return i2;
    }

    public static ListenableFuture immediateCancelledFuture() {
        ImmediateFuture.ImmediateCancelledFuture immediateCancelledFuture = ImmediateFuture.ImmediateCancelledFuture.INSTANCE;
        if (immediateCancelledFuture != null) {
            return immediateCancelledFuture;
        }
        return new ImmediateFuture.ImmediateCancelledFuture();
    }

    public static ListenableFuture immediateFailedFuture(Throwable th) {
        th.getClass();
        return new ImmediateFuture.ImmediateFailedFuture(th);
    }

    public static ListenableFuture immediateFuture(Object obj) {
        if (obj == null) {
            return ImmediateFuture.NULL;
        }
        return new ImmediateFuture(obj);
    }

    public static boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

    public static boolean isLowerCase(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        return false;
    }

    public static boolean isUpperCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

    public static String lenientFormat(String str, Object... objArr) {
        int length;
        int length2;
        int indexOf;
        String str2;
        String valueOf = String.valueOf(str);
        int i = 0;
        int i2 = 0;
        while (true) {
            length = objArr.length;
            if (i2 >= length) {
                break;
            }
            Object obj = objArr[i2];
            if (obj == null) {
                str2 = "null";
            } else {
                try {
                    str2 = obj.toString();
                } catch (Exception e) {
                    String str3 = obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj));
                    Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(str3), (Throwable) e);
                    str2 = "<" + str3 + " threw " + e.getClass().getName() + ">";
                }
            }
            objArr[i2] = str2;
            i2++;
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (length * 16));
        int i3 = 0;
        while (true) {
            length2 = objArr.length;
            if (i >= length2 || (indexOf = valueOf.indexOf("%s", i3)) == -1) {
                break;
            }
            sb.append((CharSequence) valueOf, i3, indexOf);
            sb.append(objArr[i]);
            i3 = indexOf + 2;
            i++;
        }
        sb.append((CharSequence) valueOf, i3, valueOf.length());
        if (i < length2) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i4 = i + 1; i4 < objArr.length; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static ListeningExecutorService listeningDecorator(ExecutorService executorService) {
        ListeningExecutorService abstractListeningExecutorService;
        if (executorService instanceof ListeningExecutorService) {
            return (ListeningExecutorService) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            abstractListeningExecutorService = new MoreExecutors$ScheduledListeningDecorator((ScheduledExecutorService) executorService);
        } else {
            abstractListeningExecutorService = new AbstractListeningExecutorService(executorService);
        }
        return abstractListeningExecutorService;
    }

    public static int maskCombine(int i, int i2, int i3) {
        return (i & (~i3)) | (i2 & i3);
    }

    public static void maybePropagateCancellation(ListenableFuture listenableFuture, Future future) {
        if (listenableFuture instanceof AbstractFuture) {
            ((AbstractFuture) listenableFuture).maybePropagateCancellationTo(future);
        } else if (listenableFuture != null && listenableFuture.isCancelled() && future != null) {
            future.cancel(false);
        }
    }

    public static Supplier memoize(final Supplier supplier) {
        if (!(supplier instanceof Suppliers$NonSerializableMemoizingSupplier) && !(supplier instanceof Suppliers$MemoizingSupplier)) {
            if (supplier instanceof Serializable) {
                return new Suppliers$MemoizingSupplier(supplier);
            }
            return new Supplier(supplier) { // from class: com.google.common.base.Suppliers$NonSerializableMemoizingSupplier
                private static final Supplier SUCCESSFULLY_COMPUTED = new PrimesController$$ExternalSyntheticLambda9(17);
                private volatile Supplier delegate;
                private Object value;

                {
                    this.delegate = supplier;
                }

                @Override // com.google.common.base.Supplier
                public final Object get() {
                    Supplier supplier2 = this.delegate;
                    Supplier supplier3 = SUCCESSFULLY_COMPUTED;
                    if (supplier2 != supplier3) {
                        synchronized (this) {
                            if (this.delegate != supplier3) {
                                Object obj = this.delegate.get();
                                this.value = obj;
                                this.delegate = supplier3;
                                return obj;
                            }
                        }
                    }
                    return this.value;
                }

                public final String toString() {
                    Object obj = this.delegate;
                    if (obj == SUCCESSFULLY_COMPUTED) {
                        obj = "<supplier that returned " + String.valueOf(this.value) + ">";
                    }
                    return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_11(obj, "Suppliers.memoize(", ")");
                }
            };
        }
        return supplier;
    }

    @SafeVarargs
    public static ArrayList newArrayList(Object... objArr) {
        objArr.getClass();
        int length = objArr.length;
        checkNonnegative$ar$ds(length, "arraySize");
        ArrayList arrayList = new ArrayList(saturatedCast(length + 5 + (length / 10)));
        Collections.addAll(arrayList, objArr);
        return arrayList;
    }

    public static ArrayList newArrayListWithCapacity(int i) {
        checkNonnegative$ar$ds(i, "initialArraySize");
        return new ArrayList(i);
    }

    public static int newCapacity(int i) {
        int i2;
        if (i < 32) {
            i2 = 4;
        } else {
            i2 = 2;
        }
        return i2 * (i + 1);
    }

    public static HashSet newHashSetWithExpectedSize(int i) {
        return new HashSet(capacity(i));
    }

    public static Set newIdentityHashSet() {
        return Collections.newSetFromMap(new IdentityHashMap());
    }

    public static ListenableFuture nonCancellationPropagating(ListenableFuture listenableFuture) {
        if (listenableFuture.isDone()) {
            return listenableFuture;
        }
        Futures$NonCancellationPropagatingFuture futures$NonCancellationPropagatingFuture = new Futures$NonCancellationPropagatingFuture(listenableFuture);
        listenableFuture.addListener(futures$NonCancellationPropagatingFuture, DirectExecutor.INSTANCE);
        return futures$NonCancellationPropagatingFuture;
    }

    public static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static Executor rejectionPropagatingExecutor(Executor executor, AbstractFuture abstractFuture) {
        executor.getClass();
        if (executor == DirectExecutor.INSTANCE) {
            return executor;
        }
        return new AsyncInterceptorsClientCall$$ExternalSyntheticLambda10(executor, abstractFuture, 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
    
        if (r4 != (-1)) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
    
        tableSet(r10, r1, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
    
        r11[r4] = maskCombine(r11[r4], r6, r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int remove(java.lang.Object r7, java.lang.Object r8, int r9, java.lang.Object r10, int[] r11, java.lang.Object[] r12, java.lang.Object[] r13) {
        /*
            int r0 = smearedHash(r7)
            r1 = r0 & r9
            int r2 = tableGet(r10, r1)
            r3 = -1
            if (r2 != 0) goto Le
            return r3
        Le:
            int r0 = getHashPrefix(r0, r9)
            r4 = r3
        L13:
            int r2 = r2 + r3
            r5 = r11[r2]
            r6 = r5 & r9
            int r5 = getHashPrefix(r5, r9)
            if (r5 != r0) goto L3f
            r5 = r12[r2]
            boolean r5 = _COROUTINE._BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(r7, r5)
            if (r5 == 0) goto L3f
            if (r13 == 0) goto L30
            r5 = r13[r2]
            boolean r5 = _COROUTINE._BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(r8, r5)
            if (r5 == 0) goto L3f
        L30:
            if (r4 != r3) goto L36
            tableSet(r10, r1, r6)
            goto L3e
        L36:
            r7 = r11[r4]
            int r7 = maskCombine(r7, r6, r9)
            r11[r4] = r7
        L3e:
            return r2
        L3f:
            if (r6 == 0) goto L44
            r4 = r2
            r2 = r6
            goto L13
        L44:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.flogger.context.ContextDataProvider.remove(java.lang.Object, java.lang.Object, int, java.lang.Object, int[], java.lang.Object[], java.lang.Object[]):int");
    }

    public static boolean removeAllImpl(Set set, Collection collection) {
        collection.getClass();
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if ((collection instanceof Set) && collection.size() > set.size()) {
            Iterator it = set.iterator();
            collection.getClass();
            boolean z = false;
            while (it.hasNext()) {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }
        return removeAllImpl(set, collection.iterator());
    }

    public static String repeat$ar$ds(int i) {
        boolean z = false;
        int i2 = 1;
        if (i <= 1) {
            if (i >= 0) {
                z = true;
            }
            checkArgument(z, "invalid count: %s", i);
            if (i != 0) {
                return "•";
            }
            return "";
        }
        long j = 1 * i;
        int i3 = (int) j;
        if (i3 == j) {
            char[] cArr = new char[i3];
            "•".getChars(0, 1, cArr, 0);
            while (true) {
                int i4 = i3 - i2;
                if (i2 < i4) {
                    System.arraycopy(cArr, 0, cArr, i2, i2);
                    i2 += i2;
                } else {
                    System.arraycopy(cArr, 0, cArr, i2, i4);
                    return new String(cArr);
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "Required array size too large: "));
        }
    }

    public static void restoreInterruptIfIsInterruptedException(Throwable th) {
        if (th instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    public static List reverse(List list) {
        if (list instanceof ImmutableList) {
            return ((ImmutableList) list).reverse();
        }
        if (list instanceof Lists$ReverseList) {
            return ((Lists$ReverseList) list).forwardList;
        }
        if (list instanceof RandomAccess) {
            return new Lists$RandomAccessReverseList(list);
        }
        return new Lists$ReverseList(list);
    }

    public static Object safeGet(Map map, Object obj) {
        map.getClass();
        try {
            return map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    public static int saturatedCast(long j) {
        if (j > 2147483647L) {
            return Preference.DEFAULT_ORDER;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    public static int smear(int i) {
        return (int) (Integer.rotateLeft((int) (i * (-862048943)), 15) * 461845907);
    }

    public static int smearedHash(Object obj) {
        int hashCode;
        if (obj == null) {
            hashCode = 0;
        } else {
            hashCode = obj.hashCode();
        }
        return smear(hashCode);
    }

    public static boolean stringIsNullOrEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return false;
        }
        return true;
    }

    public static ListenableFuture submit(Callable callable, Executor executor) {
        TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(callable);
        executor.execute(trustedListenableFutureTask);
        return trustedListenableFutureTask;
    }

    public static ListenableFuture submitAsync(AsyncCallable asyncCallable, Executor executor) {
        TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(asyncCallable);
        executor.execute(trustedListenableFutureTask);
        return trustedListenableFutureTask;
    }

    public static void suppress$ar$ds(Closeable closeable, Throwable th, Throwable th2) {
        if (th == th2) {
            return;
        }
        try {
            th.addSuppressed(th2);
        } catch (Throwable unused) {
            Closeables.logger.logp(Level.WARNING, "com.google.common.io.Closer", "lambda$static$0", "Suppressing exception thrown when closing ".concat(String.valueOf(String.valueOf(closeable))), th2);
        }
    }

    public static int tableGet(Object obj, int i) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[i] & 255;
        }
        if (obj instanceof short[]) {
            return (char) ((short[]) obj)[i];
        }
        return ((int[]) obj)[i];
    }

    public static void tableSet(Object obj, int i, int i2) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i] = (byte) i2;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i] = (short) i2;
        } else {
            ((int[]) obj)[i] = i2;
        }
    }

    public static int toInt(byte b) {
        return b & 255;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [char] */
    /* JADX WARN: Type inference failed for: r3v3 */
    public static String toLowerCase(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return toLowerCase((String) charSequence);
        }
        int length = charSequence.length();
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            int charAt = charSequence.charAt(i);
            if (isUpperCase(charAt)) {
                charAt ^= 32;
            }
            cArr[i] = (char) charAt;
        }
        return String.valueOf(cArr);
    }

    public static MoreObjects$ToStringHelper toStringHelper(Class cls) {
        return new MoreObjects$ToStringHelper(cls.getSimpleName());
    }

    public static String toStringImpl(Map map) {
        int size = map.size();
        checkNonnegative$ar$ds(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : map.entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    public static String toUpperCase(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (isLowerCase(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c = charArray[i];
                    if (isLowerCase(c)) {
                        charArray[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    public static ListenableFuture transform(ListenableFuture listenableFuture, Function function, Executor executor) {
        return AbstractTransformFuture.create(listenableFuture, TracePropagation.propagateFunction(function), executor);
    }

    public static void verify(boolean z, String str, Object obj) {
        if (z) {
        } else {
            throw new VerifyException(lenientFormat(str, obj));
        }
    }

    public static Object verifyNotNull(Object obj) {
        Object[] objArr = new Object[0];
        if (obj != null) {
            return obj;
        }
        throw new VerifyException(lenientFormat("expected a non-null reference", objArr));
    }

    public static RetriableStream.HedgingPlan whenAllComplete$ar$class_merging$ar$class_merging(Iterable iterable) {
        return new RetriableStream.HedgingPlan(false, ImmutableList.copyOf(iterable));
    }

    @SafeVarargs
    public static RetriableStream.HedgingPlan whenAllComplete$ar$class_merging$c090da7e_0$ar$class_merging(ListenableFuture... listenableFutureArr) {
        return new RetriableStream.HedgingPlan(false, ImmutableList.copyOf(listenableFutureArr));
    }

    public static RetriableStream.HedgingPlan whenAllSucceed$ar$class_merging$ar$class_merging(Iterable iterable) {
        return new RetriableStream.HedgingPlan(true, ImmutableList.copyOf(iterable));
    }

    public static ListenableFuture withTimeout(ListenableFuture listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        if (listenableFuture.isDone()) {
            return listenableFuture;
        }
        TimeoutFuture timeoutFuture = new TimeoutFuture(listenableFuture);
        TimeoutFuture.Fire fire = new TimeoutFuture.Fire(timeoutFuture);
        timeoutFuture.timer = scheduledExecutorService.schedule(fire, j, timeUnit);
        listenableFuture.addListener(fire, DirectExecutor.INSTANCE);
        return timeoutFuture;
    }

    public static void writeMultimap(Multimap multimap, ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(multimap.asMap().size());
        for (Map.Entry entry : multimap.asMap().entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(((Collection) entry.getValue()).size());
            Iterator it = ((Collection) entry.getValue()).iterator();
            while (it.hasNext()) {
                objectOutputStream.writeObject(it.next());
            }
        }
    }

    public Metadata getMetadata() {
        return Metadata.Empty.INSTANCE;
    }

    public Tags getTags() {
        return Tags.EMPTY_TAGS;
    }

    public byte[] read() {
        throw null;
    }

    public ContextDataProvider(char[] cArr) {
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException((String) obj);
        }
    }

    public static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public ContextDataProvider(Object obj, byte[] bArr) {
        obj.getClass();
    }

    public static void checkArgument(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(lenientFormat(str, Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalStateException(lenientFormat(str, Integer.valueOf(i)));
        }
    }

    public static MoreObjects$ToStringHelper toStringHelper(Object obj) {
        return new MoreObjects$ToStringHelper(obj.getClass().getSimpleName());
    }

    public static List transform(List list, Function function) {
        if (list instanceof RandomAccess) {
            return new Lists$TransformingRandomAccessList(list, function);
        }
        return new Lists$TransformingSequentialList(list, function);
    }

    public ContextDataProvider(io.grpc.Metadata metadata) {
        metadata.getClass();
    }

    public static void checkArgument(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalArgumentException(lenientFormat(str, Integer.valueOf(i)));
        }
    }

    public static void checkState(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalStateException(lenientFormat(str, obj));
        }
    }

    public ContextDataProvider(Object obj) {
        obj.getClass();
    }

    public static void checkArgument(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(lenientFormat(str, Long.valueOf(j)));
        }
    }

    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService scheduledExecutorService) {
        return scheduledExecutorService instanceof ListeningScheduledExecutorService ? (ListeningScheduledExecutorService) scheduledExecutorService : new MoreExecutors$ScheduledListeningDecorator(scheduledExecutorService);
    }

    public static void checkArgument(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(lenientFormat(str, obj));
        }
    }

    public static String toLowerCase(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (isUpperCase(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c = charArray[i];
                    if (isUpperCase(c)) {
                        charArray[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    public static void checkArgument(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(lenientFormat(str, obj, obj2));
        }
    }

    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static boolean removeAllImpl(Set set, Iterator it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    public static boolean equalsImpl(Map map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public void shouldForceLogging$ar$ds(String str, Level level, boolean z) {
    }
}

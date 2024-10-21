package com.google.android.libraries.storage.protostore;

import android.content.Context;
import android.net.Uri;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.FullScreenReadActor;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.keyboard.TalkBackPhysicalKeyboardShortcut;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.crash.EarlyCrashLoopMonitor;
import com.google.android.libraries.storage.protostore.loggers.NoOpLogger;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.Lazy;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoDataStoreFactory {
    public final Object ProtoDataStoreFactory$ar$executor;
    public final Object ProtoDataStoreFactory$ar$logger$ar$class_merging$b9e9d160_0;
    public final Object ProtoDataStoreFactory$ar$obfuscator;
    public final Object ProtoDataStoreFactory$ar$pdsCache;
    public final Object ProtoDataStoreFactory$ar$pdsConfigs;
    public final Object ProtoDataStoreFactory$ar$storage$ar$class_merging;
    public final Object ProtoDataStoreFactory$ar$variantFactories;

    public ProtoDataStoreFactory(Context context, Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, SelectorController selectorController, ListMenuManager listMenuManager, FullScreenReadActor fullScreenReadActor, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.ProtoDataStoreFactory$ar$pdsConfigs = context;
        this.ProtoDataStoreFactory$ar$variantFactories = feedbackReturner;
        this.ProtoDataStoreFactory$ar$storage$ar$class_merging = actorState;
        this.ProtoDataStoreFactory$ar$obfuscator = selectorController;
        this.ProtoDataStoreFactory$ar$pdsCache = listMenuManager;
        this.ProtoDataStoreFactory$ar$logger$ar$class_merging$b9e9d160_0 = fullScreenReadActor;
        this.ProtoDataStoreFactory$ar$executor = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    public final Feedback.FocusDirection.Builder createFocusDirectionBuilder(boolean z) {
        int i;
        if (true != z) {
            i = 2;
        } else {
            i = 1;
        }
        Feedback.FocusDirection.Builder focusDirection = Feedback.focusDirection(i);
        focusDirection.setInputMode$ar$ds(1);
        focusDirection.setWrap$ar$ds(true);
        focusDirection.setScroll$ar$ds(true);
        focusDirection.setDefaultToInputFocus$ar$ds(true);
        CursorGranularity currentGranularity = ((HapticPatternParser$$ExternalSyntheticLambda1) this.ProtoDataStoreFactory$ar$executor).getCurrentGranularity();
        if (currentGranularity != null) {
            TalkBackPhysicalKeyboardShortcut talkBackPhysicalKeyboardShortcut = TalkBackPhysicalKeyboardShortcut.ACTION_UNKNOWN;
            int ordinal = currentGranularity.ordinal();
            if (ordinal != 13) {
                if (ordinal == 14) {
                    focusDirection.setToContainer$ar$ds(true);
                    return focusDirection;
                }
            } else {
                focusDirection.setToWindow$ar$ds(true);
                return focusDirection;
            }
        }
        return focusDirection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v25, types: [java.lang.Object, com.google.common.util.concurrent.AsyncFunction] */
    public final synchronized XDataStore getOrCreateInternal$ar$class_merging(ProtoDataStoreConfig protoDataStoreConfig) {
        XDataStore xDataStore;
        boolean z;
        String substring;
        boolean z2;
        boolean z3;
        boolean z4;
        String str;
        ?? r0 = this.ProtoDataStoreFactory$ar$pdsCache;
        Uri uri = protoDataStoreConfig.uri;
        xDataStore = (XDataStore) r0.get(uri);
        if (xDataStore == null) {
            Uri uri2 = protoDataStoreConfig.uri;
            ContextDataProvider.checkArgument(uri2.isHierarchical(), "Uri must be hierarchical: %s", uri2);
            String nullToEmpty = ContextDataProvider.nullToEmpty(uri2.getLastPathSegment());
            int lastIndexOf = nullToEmpty.lastIndexOf(46);
            if (lastIndexOf == -1) {
                substring = "";
            } else {
                substring = nullToEmpty.substring(lastIndexOf + 1);
            }
            ContextDataProvider.checkArgument(substring.equals("pb"), "Uri extension must be .pb: %s", uri2);
            if (protoDataStoreConfig.schema != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            ContextDataProvider.checkArgument(z2, (Object) "Proto schema cannot be null");
            if (protoDataStoreConfig.handler != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            ContextDataProvider.checkArgument(z3, (Object) "Handler cannot be null");
            XDataStoreVariantFactory xDataStoreVariantFactory = (XDataStoreVariantFactory) this.ProtoDataStoreFactory$ar$variantFactories.get("singleproc");
            if (xDataStoreVariantFactory != 0) {
                z4 = true;
            } else {
                z4 = false;
            }
            ContextDataProvider.checkArgument(z4, "No XDataStoreVariantFactory registered for ID %s", "singleproc");
            String nullToEmpty2 = ContextDataProvider.nullToEmpty(protoDataStoreConfig.uri.getLastPathSegment());
            int lastIndexOf2 = nullToEmpty2.lastIndexOf(46);
            if (lastIndexOf2 != -1) {
                str = nullToEmpty2.substring(0, lastIndexOf2);
            } else {
                str = nullToEmpty2;
            }
            ListenableFuture create = AbstractTransformFuture.create(ContextDataProvider.immediateFuture(protoDataStoreConfig.uri), (AsyncFunction) this.ProtoDataStoreFactory$ar$obfuscator, DirectExecutor.INSTANCE);
            SingleProcProtoDataStore create$ar$edu$3ef90a92_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = xDataStoreVariantFactory.create$ar$edu$3ef90a92_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(protoDataStoreConfig, str, this.ProtoDataStoreFactory$ar$executor, (OptionalMethod) this.ProtoDataStoreFactory$ar$storage$ar$class_merging, 1);
            Object obj = this.ProtoDataStoreFactory$ar$logger$ar$class_merging$b9e9d160_0;
            xDataStoreVariantFactory.id$ar$edu(1);
            XDataStore xDataStore2 = new XDataStore(create$ar$edu$3ef90a92_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, (NoOpLogger) obj, create, null);
            ImmutableList immutableList = protoDataStoreConfig.migrations;
            if (!immutableList.isEmpty()) {
                xDataStore2.addInitializer(new ProtoDataMigrationInitializer(immutableList, this.ProtoDataStoreFactory$ar$executor));
            }
            this.ProtoDataStoreFactory$ar$pdsCache.put(uri, xDataStore2);
            this.ProtoDataStoreFactory$ar$pdsConfigs.put(uri, protoDataStoreConfig);
            xDataStore = xDataStore2;
        } else {
            ProtoDataStoreConfig protoDataStoreConfig2 = (ProtoDataStoreConfig) this.ProtoDataStoreFactory$ar$pdsConfigs.get(uri);
            if (!protoDataStoreConfig.equals(protoDataStoreConfig2)) {
                String lenientFormat = ContextDataProvider.lenientFormat("ProtoDataStoreConfig<%s> doesn't match previous call [uri=%s] [%s]", protoDataStoreConfig.schema.getClass().getSimpleName(), protoDataStoreConfig.uri);
                ContextDataProvider.checkArgument(protoDataStoreConfig.uri.equals(protoDataStoreConfig2.uri()), lenientFormat, "uri");
                ContextDataProvider.checkArgument(protoDataStoreConfig.schema.equals(protoDataStoreConfig2.schema()), lenientFormat, "schema");
                ContextDataProvider.checkArgument(protoDataStoreConfig.handler.equals(protoDataStoreConfig2.handler()), lenientFormat, "handler");
                ContextDataProvider.checkArgument(ContextDataProvider.equalsImpl(protoDataStoreConfig.migrations, protoDataStoreConfig2.migrations()), lenientFormat, "migrations");
                ContextDataProvider.checkArgument(protoDataStoreConfig.variantConfig$ar$class_merging$ba00fa32_0$ar$class_merging.equals(protoDataStoreConfig2.variantConfig$ar$class_merging$ar$class_merging()), lenientFormat, "variantConfig");
                if (protoDataStoreConfig.useGeneratedExtensionRegistry == protoDataStoreConfig2.useGeneratedExtensionRegistry()) {
                    z = true;
                } else {
                    z = false;
                }
                ContextDataProvider.checkArgument(z, lenientFormat, "useGeneratedExtensionRegistry");
                ContextDataProvider.checkArgument(true, lenientFormat, (Object) "enableTracing");
                throw new IllegalArgumentException(ContextDataProvider.lenientFormat(lenientFormat, "unknown"));
            }
        }
        return xDataStore;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    public final boolean performWebNavigationKeyCombo(int i, boolean z, Performance.EventId eventId) {
        int i2;
        if (true != z) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        Feedback.FocusDirection.Builder focusDirection = Feedback.focusDirection(i2);
        focusDirection.setInputMode$ar$ds(1);
        focusDirection.setHtmlTargetType$ar$ds(i);
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.ProtoDataStoreFactory$ar$variantFactories, eventId, focusDirection);
    }

    public ProtoDataStoreFactory(EarlyCrashLoopMonitor earlyCrashLoopMonitor, Supplier supplier, Supplier supplier2, Executor executor, Lazy lazy, MetricRecorderFactory metricRecorderFactory, Provider provider) {
        this.ProtoDataStoreFactory$ar$pdsCache = new AtomicBoolean(true);
        this.ProtoDataStoreFactory$ar$storage$ar$class_merging = earlyCrashLoopMonitor;
        this.ProtoDataStoreFactory$ar$executor = supplier;
        this.ProtoDataStoreFactory$ar$variantFactories = supplier2;
        this.ProtoDataStoreFactory$ar$logger$ar$class_merging$b9e9d160_0 = executor;
        this.ProtoDataStoreFactory$ar$pdsConfigs = metricRecorderFactory.create(executor, lazy, null);
        this.ProtoDataStoreFactory$ar$obfuscator = provider;
    }

    public ProtoDataStoreFactory(Executor executor, OptionalMethod optionalMethod, NoOpLogger noOpLogger, Map map) {
        this.ProtoDataStoreFactory$ar$pdsCache = new HashMap();
        this.ProtoDataStoreFactory$ar$pdsConfigs = new HashMap();
        executor.getClass();
        this.ProtoDataStoreFactory$ar$executor = executor;
        optionalMethod.getClass();
        this.ProtoDataStoreFactory$ar$storage$ar$class_merging = optionalMethod;
        this.ProtoDataStoreFactory$ar$logger$ar$class_merging$b9e9d160_0 = noOpLogger;
        this.ProtoDataStoreFactory$ar$variantFactories = map;
        ContextDataProvider.checkArgument(!map.isEmpty());
        this.ProtoDataStoreFactory$ar$obfuscator = new ProtoDataStoreFactory$$ExternalSyntheticLambda1(0);
    }
}

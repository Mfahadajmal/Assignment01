package com.google.android.gms.clearcut;

import android.content.Context;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl;
import com.google.android.gms.clearcut.internal.PlayLoggerContext;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.common.base.Supplier;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutLogger extends AbstractClearcutLogger {
    public static final List processGlobalEventModifiers = new CopyOnWriteArrayList();
    public final List eventModifiers;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EventModifier {
        LogEventBuilder apply(LogEventBuilder logEventBuilder);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogEventBuilder extends AbstractLogEventBuilder {
        public CollectionBasisLogVerifier logVerifier$ar$class_merging;
        private final MessageLite sourceExtension;

        public LogEventBuilder(ClearcutLogger clearcutLogger, MessageLite messageLite) {
            super(clearcutLogger);
            this.sourceExtension = messageLite;
        }

        @Override // com.google.android.gms.clearcut.AbstractLogEventBuilder
        public final /* bridge */ /* synthetic */ AbstractLogEventBuilder applyEventModifiers() {
            Iterator it = ((ClearcutLogger) this.logger).eventModifiers.iterator();
            LogEventBuilder logEventBuilder = this;
            while (it.hasNext()) {
                logEventBuilder = ((EventModifier) it.next()).apply(logEventBuilder);
            }
            Iterator it2 = ClearcutLogger.processGlobalEventModifiers.iterator();
            while (it2.hasNext()) {
                logEventBuilder = ((EventModifier) it2.next()).apply(logEventBuilder);
            }
            return logEventBuilder;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.clearcut.AbstractLogEventBuilder
        public final LogEventParcelable getLogEventParcelable() {
            String[] strArr;
            ExperimentTokens[] experimentTokensArr;
            SchedulerOptions.Builder builder = this.logEvent$ar$class_merging$ar$class_merging;
            ByteString byteString = this.sourceExtension.toByteString();
            builder.copyOnWrite();
            ClientAnalytics$LogEvent clientAnalytics$LogEvent = (ClientAnalytics$LogEvent) builder.instance;
            ClientAnalytics$LogEvent clientAnalytics$LogEvent2 = ClientAnalytics$LogEvent.DEFAULT_INSTANCE;
            byteString.getClass();
            clientAnalytics$LogEvent.bitField0_ |= 2048;
            clientAnalytics$LogEvent.sourceExtension_ = byteString;
            ClientAnalytics$LogEvent clientAnalytics$LogEvent3 = (ClientAnalytics$LogEvent) this.logEvent$ar$class_merging$ar$class_merging.build();
            ClearcutLogger clearcutLogger = (ClearcutLogger) this.logger;
            PlayLoggerContext playLoggerContext = new PlayLoggerContext(clearcutLogger.packageName, AbstractClearcutLogger.getMemoizedPackageVersionCode(clearcutLogger.context), this.logSourceName, this.uploadAccountName, getQosTier$ar$edu(), ((ClearcutLogger) this.logger).piiLevelSet);
            byte[] byteArray = clientAnalytics$LogEvent3.toByteArray();
            String[] strArr2 = null;
            int[] intArray = AbstractClearcutLogger.toIntArray(null);
            ArrayList arrayList = this.mendelPackages;
            if (arrayList != null) {
                strArr = (String[]) arrayList.toArray(AbstractClearcutLogger.EMPTY_STRING);
            } else {
                strArr = null;
            }
            int[] intArray2 = AbstractClearcutLogger.toIntArray(this.experimentIds);
            ArrayList arrayList2 = this.experimentTokensParcelables;
            if (arrayList2 != null) {
                experimentTokensArr = (ExperimentTokens[]) arrayList2.toArray(AbstractClearcutLogger.EMPTY_EXPERIMENT_TOKENS);
            } else {
                experimentTokensArr = null;
            }
            Set set = this.mendelPackagesToFilter;
            if (set != null) {
                strArr2 = (String[]) set.toArray(AbstractClearcutLogger.EMPTY_STRING);
            }
            return new LogEventParcelable(playLoggerContext, clientAnalytics$LogEvent3, byteArray, intArray, strArr, intArray2, experimentTokensArr, true, strArr2, clientAnalytics$LogEvent3.eventCode_, null);
        }

        @Override // com.google.android.gms.clearcut.AbstractLogEventBuilder
        public final PendingResult logAsync() {
            if (!this.isConsumed) {
                this.isConsumed = true;
                ClearcutLoggerApi clearcutLoggerApi = ((ClearcutLogger) this.logger).loggerApi;
                return ((ClearcutLoggerApiImpl) clearcutLoggerApi).logEventInternal$ar$ds(this);
            }
            throw new IllegalStateException("do not reuse LogEventBuilder");
        }
    }

    public ClearcutLogger(Context context, String str, String str2, EnumSet enumSet, ClearcutLoggerApi clearcutLoggerApi, LegacyLogSampler legacyLogSampler, Supplier supplier) {
        super(context, str, str2, enumSet, clearcutLoggerApi, legacyLogSampler, supplier);
        this.eventModifiers = new CopyOnWriteArrayList();
    }

    public static void addProcessEventModifier(EventModifier eventModifier) {
        processGlobalEventModifiers.add(0, eventModifier);
    }

    @Deprecated
    public final LogEventBuilder newEvent(MessageLite messageLite) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(messageLite);
        return new LogEventBuilder(this, messageLite);
    }

    public final LogEventBuilder newEvent$ar$class_merging(MessageLite messageLite, CollectionBasisLogVerifier collectionBasisLogVerifier) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(messageLite);
        LogEventBuilder logEventBuilder = new LogEventBuilder(this, messageLite);
        logEventBuilder.logVerifier$ar$class_merging = collectionBasisLogVerifier;
        return logEventBuilder;
    }
}

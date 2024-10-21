package com.google.android.gms.clearcut;

import android.content.Context;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl;
import com.google.android.gms.clearcut.internal.PlayLoggerContext;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.common.base.Supplier;
import com.google.protobuf.ByteString;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RestrictedByteStringClearcutLogger extends AbstractClearcutLogger {
    public final List eventModifiers;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EventModifier {
        LogEventBuilder apply$ar$ds$b72bde41_0();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogEventBuilder extends AbstractLogEventBuilder {
        public LogEventBuilder(RestrictedByteStringClearcutLogger restrictedByteStringClearcutLogger, ByteString byteString) {
            super(restrictedByteStringClearcutLogger);
            SchedulerOptions.Builder builder = this.logEvent$ar$class_merging$ar$class_merging;
            builder.copyOnWrite();
            ClientAnalytics$LogEvent clientAnalytics$LogEvent = (ClientAnalytics$LogEvent) builder.instance;
            ClientAnalytics$LogEvent clientAnalytics$LogEvent2 = ClientAnalytics$LogEvent.DEFAULT_INSTANCE;
            clientAnalytics$LogEvent.bitField0_ |= 2048;
            clientAnalytics$LogEvent.sourceExtension_ = byteString;
        }

        @Override // com.google.android.gms.clearcut.AbstractLogEventBuilder
        public final /* bridge */ /* synthetic */ AbstractLogEventBuilder applyEventModifiers() {
            Iterator it = ((RestrictedByteStringClearcutLogger) this.logger).eventModifiers.iterator();
            LogEventBuilder logEventBuilder = this;
            while (it.hasNext()) {
                logEventBuilder = ((EventModifier) it.next()).apply$ar$ds$b72bde41_0();
                if (logEventBuilder == null) {
                    return null;
                }
            }
            return logEventBuilder;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.clearcut.AbstractLogEventBuilder
        public final LogEventParcelable getLogEventParcelable() {
            String[] strArr;
            ExperimentTokens[] experimentTokensArr;
            ClientAnalytics$LogEvent clientAnalytics$LogEvent = (ClientAnalytics$LogEvent) this.logEvent$ar$class_merging$ar$class_merging.build();
            RestrictedByteStringClearcutLogger restrictedByteStringClearcutLogger = (RestrictedByteStringClearcutLogger) this.logger;
            PlayLoggerContext playLoggerContext = new PlayLoggerContext(restrictedByteStringClearcutLogger.packageName, AbstractClearcutLogger.getMemoizedPackageVersionCode(restrictedByteStringClearcutLogger.context), this.logSourceName, this.uploadAccountName, getQosTier$ar$edu(), ((RestrictedByteStringClearcutLogger) this.logger).piiLevelSet);
            byte[] byteArray = clientAnalytics$LogEvent.toByteArray();
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
            return new LogEventParcelable(playLoggerContext, clientAnalytics$LogEvent, byteArray, intArray, strArr, intArray2, experimentTokensArr, true, strArr2, clientAnalytics$LogEvent.eventCode_, null);
        }

        @Override // com.google.android.gms.clearcut.AbstractLogEventBuilder
        public final PendingResult logAsync() {
            if (!this.isConsumed) {
                this.isConsumed = true;
                return ((ClearcutLoggerApiImpl) ((RestrictedByteStringClearcutLogger) this.logger).loggerApi).logEventInternal$ar$ds(this);
            }
            throw new IllegalStateException("do not reuse LogEventBuilder");
        }
    }

    public RestrictedByteStringClearcutLogger(Context context, String str, EnumSet enumSet, Supplier supplier) {
        super(context, str, null, enumSet, null, null, supplier);
        this.eventModifiers = new CopyOnWriteArrayList();
    }
}

package com.google.android.gms.clearcut;

import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protos.privacy.context.external.ExternalPRequestContext;
import com.google.protos.privacy.context.external.ExternalPrivacyContext;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$LogEvent;
import com.google.wireless.android.play.playlog.proto.Compliance$ComplianceData;
import java.util.ArrayList;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractLogEventBuilder {
    protected final boolean addPhenotypeExperimentTokens;
    public boolean complianceDataWasResolved;
    protected ArrayList experimentIds;
    public ArrayList experimentTokensParcelables;
    protected boolean isConsumed;
    public final SchedulerOptions.Builder logEvent$ar$class_merging$ar$class_merging;
    public final String logSourceName;
    public final AbstractClearcutLogger logger;
    public ArrayList mendelPackages;
    public Set mendelPackagesToFilter;
    public final ComplianceProductData processLevelProductComplianceData;
    public final ListenableFuture processLevelSocsComplianceDataFuture;
    public int qosTier$ar$edu;
    public String uploadAccountName;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public AbstractLogEventBuilder(AbstractClearcutLogger abstractClearcutLogger) {
        ComplianceDataProcessProvider complianceDataProcessProvider;
        ComplianceProductData complianceProductData;
        String str;
        String str2;
        SchedulerOptions.Builder builder = (SchedulerOptions.Builder) ClientAnalytics$LogEvent.DEFAULT_INSTANCE.createBuilder();
        this.logEvent$ar$class_merging$ar$class_merging = builder;
        this.isConsumed = false;
        this.mendelPackages = null;
        this.experimentIds = null;
        this.experimentTokensParcelables = null;
        this.addPhenotypeExperimentTokens = true;
        this.complianceDataWasResolved = false;
        this.logger = abstractClearcutLogger;
        this.logSourceName = abstractClearcutLogger.logSourceName;
        this.uploadAccountName = abstractClearcutLogger.uploadAccountName;
        if (abstractClearcutLogger.context.getApplicationContext() instanceof ComplianceDataProcessProvider) {
            complianceDataProcessProvider = (ComplianceDataProcessProvider) abstractClearcutLogger.context.getApplicationContext();
        } else {
            complianceDataProcessProvider = (ComplianceDataProcessProvider) ComplianceDataProviderHolder.provider.get();
        }
        if (complianceDataProcessProvider != null) {
            complianceProductData = complianceDataProcessProvider.getCurrentComplianceProductData();
        } else {
            complianceProductData = null;
        }
        if (complianceProductData == null) {
            this.processLevelProductComplianceData = null;
        } else if (complianceProductData.getProductIdOrigin$ar$edu() != Compliance$ComplianceData.ProductIdOrigin.CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu && complianceProductData.getProductIdOrigin$ar$edu() != Compliance$ComplianceData.ProductIdOrigin.NON_CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu) {
            int productIdOrigin$ar$edu = complianceProductData.getProductIdOrigin$ar$edu();
            if (productIdOrigin$ar$edu == 0) {
                str = "null";
            } else {
                str = Compliance$ComplianceData.ProductIdOrigin.toString$ar$edu$3f0d2c63_0(productIdOrigin$ar$edu);
            }
            String valueOf = String.valueOf(str);
            int i = Compliance$ComplianceData.ProductIdOrigin.CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu;
            if (i == 0) {
                str2 = "null";
            } else {
                str2 = Compliance$ComplianceData.ProductIdOrigin.toString$ar$edu$3f0d2c63_0(i);
            }
            String valueOf2 = String.valueOf(str2);
            int i2 = Compliance$ComplianceData.ProductIdOrigin.NON_CPS_APP_PROCESS_GLOBAL_PROVIDER$ar$edu;
            Log.e("AbstractLogEventBuilder", "The provided ProductIdOrigin " + valueOf + " is not one of the process-level expected values: " + valueOf2 + " or " + String.valueOf(i2 != 0 ? Compliance$ComplianceData.ProductIdOrigin.toString$ar$edu$3f0d2c63_0(i2) : "null"));
            this.processLevelProductComplianceData = null;
        } else {
            this.processLevelProductComplianceData = complianceProductData;
        }
        this.processLevelSocsComplianceDataFuture = complianceDataProcessProvider != null ? complianceDataProcessProvider.getCurrentComplianceSocsData() : null;
        long currentTimeMillis = System.currentTimeMillis();
        builder.copyOnWrite();
        ClientAnalytics$LogEvent clientAnalytics$LogEvent = (ClientAnalytics$LogEvent) builder.instance;
        clientAnalytics$LogEvent.bitField0_ |= 1;
        clientAnalytics$LogEvent.eventTimeMs_ = currentTimeMillis;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(TimeZone.getDefault().getOffset(((ClientAnalytics$LogEvent) builder.instance).eventTimeMs_));
        builder.copyOnWrite();
        ClientAnalytics$LogEvent clientAnalytics$LogEvent2 = (ClientAnalytics$LogEvent) builder.instance;
        clientAnalytics$LogEvent2.bitField0_ |= 131072;
        clientAnalytics$LogEvent2.timezoneOffsetSeconds_ = seconds;
        if (DirectBootUtils.isDirectBoot(abstractClearcutLogger.context)) {
            builder.copyOnWrite();
            ClientAnalytics$LogEvent clientAnalytics$LogEvent3 = (ClientAnalytics$LogEvent) builder.instance;
            clientAnalytics$LogEvent3.bitField0_ |= 8388608;
            clientAnalytics$LogEvent3.inDirectBootMode_ = true;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime != 0) {
            builder.copyOnWrite();
            ClientAnalytics$LogEvent clientAnalytics$LogEvent4 = (ClientAnalytics$LogEvent) builder.instance;
            clientAnalytics$LogEvent4.bitField0_ |= 2;
            clientAnalytics$LogEvent4.eventUptimeMs_ = elapsedRealtime;
        }
    }

    public final void addExperimentIds$ar$ds(int[] iArr) {
        if (!this.logger.isDeidentified()) {
            if (iArr.length != 0) {
                if (this.experimentIds == null) {
                    this.experimentIds = new ArrayList();
                }
                for (int i : iArr) {
                    this.experimentIds.add(Integer.valueOf(i));
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("addExperimentIds forbidden on deidentified logger");
    }

    public abstract AbstractLogEventBuilder applyEventModifiers();

    public abstract LogEventParcelable getLogEventParcelable();

    public final int getQosTier$ar$edu() {
        int i = this.qosTier$ar$edu;
        if (i != 0) {
            return i;
        }
        return this.logger.qosTier$ar$edu;
    }

    public abstract PendingResult logAsync();

    /* JADX WARN: Multi-variable type inference failed */
    public final void setComplianceProductDataOnLogEvent(ComplianceProductData complianceProductData) {
        Compliance$ComplianceData compliance$ComplianceData = ((ClientAnalytics$LogEvent) this.logEvent$ar$class_merging$ar$class_merging.instance).complianceData_;
        if (compliance$ComplianceData == null) {
            compliance$ComplianceData = Compliance$ComplianceData.DEFAULT_INSTANCE;
        }
        SchedulerOptions.Builder builder = (SchedulerOptions.Builder) compliance$ComplianceData.toBuilder();
        int productIdOrigin$ar$edu = complianceProductData.getProductIdOrigin$ar$edu();
        builder.copyOnWrite();
        Compliance$ComplianceData compliance$ComplianceData2 = (Compliance$ComplianceData) builder.instance;
        int i = productIdOrigin$ar$edu - 1;
        if (productIdOrigin$ar$edu != 0) {
            compliance$ComplianceData2.productIdOrigin_ = i;
            compliance$ComplianceData2.bitField0_ |= 2;
            ExternalPrivacyContext externalPrivacyContext = ((Compliance$ComplianceData) builder.instance).privacyContext_;
            if (externalPrivacyContext == null) {
                externalPrivacyContext = ExternalPrivacyContext.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) externalPrivacyContext.toBuilder();
            ExternalPRequestContext externalPRequestContext = ((ExternalPrivacyContext) builder2.instance).prequest_;
            if (externalPRequestContext == null) {
                externalPRequestContext = ExternalPRequestContext.DEFAULT_INSTANCE;
            }
            SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) externalPRequestContext.toBuilder();
            int productId = complianceProductData.getProductId();
            builder3.copyOnWrite();
            ExternalPRequestContext externalPRequestContext2 = (ExternalPRequestContext) builder3.instance;
            externalPRequestContext2.bitField0_ |= 1;
            externalPRequestContext2.originAssociatedProductId_ = productId;
            builder2.copyOnWrite();
            ExternalPrivacyContext externalPrivacyContext2 = (ExternalPrivacyContext) builder2.instance;
            ExternalPRequestContext externalPRequestContext3 = (ExternalPRequestContext) builder3.build();
            externalPRequestContext3.getClass();
            externalPrivacyContext2.prequest_ = externalPRequestContext3;
            externalPrivacyContext2.bitField0_ |= 1;
            SchedulerOptions.Builder builder4 = this.logEvent$ar$class_merging$ar$class_merging;
            builder.copyOnWrite();
            Compliance$ComplianceData compliance$ComplianceData3 = (Compliance$ComplianceData) builder.instance;
            ExternalPrivacyContext externalPrivacyContext3 = (ExternalPrivacyContext) builder2.build();
            externalPrivacyContext3.getClass();
            compliance$ComplianceData3.privacyContext_ = externalPrivacyContext3;
            compliance$ComplianceData3.bitField0_ |= 1;
            Compliance$ComplianceData compliance$ComplianceData4 = (Compliance$ComplianceData) builder.build();
            builder4.copyOnWrite();
            ClientAnalytics$LogEvent clientAnalytics$LogEvent = (ClientAnalytics$LogEvent) builder4.instance;
            compliance$ComplianceData4.getClass();
            clientAnalytics$LogEvent.complianceData_ = compliance$ComplianceData4;
            clientAnalytics$LogEvent.bitField0_ |= 134217728;
            return;
        }
        throw null;
    }

    public final String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder("AbstractLogEventBuilderuploadAccount: ");
        sb.append(this.uploadAccountName);
        sb.append(", logSourceName: ");
        sb.append(this.logSourceName);
        sb.append(", qosTier: ");
        int qosTier$ar$edu = getQosTier$ar$edu();
        int i = qosTier$ar$edu - 1;
        String str3 = null;
        if (qosTier$ar$edu != 0) {
            sb.append(i);
            sb.append(", veMessage: null, testCodes: null, mendelPackages: ");
            ArrayList arrayList = this.mendelPackages;
            if (arrayList != null) {
                str = AbstractClearcutLogger.join(arrayList);
            } else {
                str = null;
            }
            sb.append(str);
            sb.append(", experimentIds: ");
            ArrayList arrayList2 = this.experimentIds;
            if (arrayList2 != null) {
                str2 = AbstractClearcutLogger.join(arrayList2);
            } else {
                str2 = null;
            }
            sb.append(str2);
            sb.append(", experimentTokens: ");
            ArrayList arrayList3 = this.experimentTokensParcelables;
            if (arrayList3 != null) {
                str3 = AbstractClearcutLogger.join(arrayList3);
            }
            sb.append(str3);
            sb.append(", addPhenotype: true]");
            return sb.toString();
        }
        throw null;
    }
}

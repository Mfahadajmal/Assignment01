package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl;
import com.google.android.gms.clearcut.internal.ClearcutLoggerClientImpl;
import com.google.android.gms.clearcut.internal.LogSamplerImpl;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.common.base.Joiner;
import com.google.common.base.Supplier;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics$QosTierConfiguration$QosTier;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.ArrayList;
import java.util.EnumSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AbstractClearcutLogger {

    @Deprecated
    public static final OptionalMethod API$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$IdentifierSpan CLIENT_BUILDER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final SpannableUtils$IdentifierSpan CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging;
    static final ExperimentTokens[] EMPTY_EXPERIMENT_TOKENS = new ExperimentTokens[0];
    static final String[] EMPTY_STRING = new String[0];
    private static volatile int packageVersionCode = -1;
    protected final DefaultClock clock$ar$class_merging$e4e96890_0;
    protected final Context context;
    public final LegacyLogSampler legacyLogSampler;
    protected final String logSourceName;
    protected final ClearcutLoggerApi loggerApi;
    protected final String packageName;
    public final EnumSet piiLevelSet;
    public final int qosTier$ar$edu;
    protected final SpannableUtils$IdentifierSpan timeZoneOffsetProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final String uploadAccountName;

    static {
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan = new SpannableUtils$IdentifierSpan((byte[]) null, (char[]) null);
        CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan;
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan2 = new SpannableUtils$IdentifierSpan() { // from class: com.google.android.gms.clearcut.AbstractClearcutLogger.1
            @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
            public final /* synthetic */ Api$Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new ClearcutLoggerClientImpl(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
            }
        };
        CLIENT_BUILDER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan2;
        API$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod("ClearcutLogger.API", spannableUtils$IdentifierSpan2, spannableUtils$IdentifierSpan, (char[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractClearcutLogger(Context context, String str, String str2, EnumSet enumSet, ClearcutLoggerApi clearcutLoggerApi, LegacyLogSampler legacyLogSampler, Supplier supplier) {
        boolean z;
        if (!enumSet.contains(PIILevel.ACCOUNT_NAME)) {
            if (str2 == null) {
                z = true;
            } else {
                z = false;
            }
            StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(z, "Upload account name cannot be used with a deidentified or pseudonymous logger.");
        }
        checkPIILevelSet(enumSet);
        this.context = context.getApplicationContext();
        this.packageName = context.getPackageName();
        this.logSourceName = str;
        this.uploadAccountName = str2;
        this.piiLevelSet = enumSet;
        this.qosTier$ar$edu = ClientAnalytics$QosTierConfiguration$QosTier.DEFAULT$ar$edu$90e3c492_0;
        this.loggerApi = clearcutLoggerApi == null ? new ClearcutLoggerApiImpl(context, supplier) : clearcutLoggerApi;
        this.clock$ar$class_merging$e4e96890_0 = DefaultClock.instance;
        this.legacyLogSampler = legacyLogSampler == null ? new LogSamplerImpl(context) : legacyLogSampler;
        this.timeZoneOffsetProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$IdentifierSpan();
    }

    public static final void checkPIILevelSet(EnumSet enumSet) {
        if (!enumSet.equals(PIILevel.zwiebackOnly) && !enumSet.equals(PIILevel.noRestrictions) && !enumSet.equals(PIILevel.deidentified)) {
            throw new IllegalArgumentException("piiLevelSet must be one of ZWIEBACK_ONLY, NO_RESTRICTIONS, or PIILevel.DEIDENTIFIED");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int getMemoizedPackageVersionCode(Context context) {
        if (packageVersionCode == -1) {
            synchronized (AbstractClearcutLogger.class) {
                if (packageVersionCode == -1) {
                    try {
                        packageVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.wtf("AbstractClearcutLogger", "This can't happen.", e);
                    }
                }
            }
        }
        return packageVersionCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String join(Iterable iterable) {
        return new Joiner(", ").join(iterable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int[] toIntArray(ArrayList arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            iArr[i2] = ((Integer) arrayList.get(i)).intValue();
            i++;
            i2++;
        }
        return iArr;
    }

    public final boolean isDeidentified() {
        return this.piiLevelSet.equals(PIILevel.deidentified);
    }
}

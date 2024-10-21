package com.google.android.accessibility.braille.brailledisplay.analytics;

import android.content.Context;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.utils.AnalyticsCommon;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.consentverifier.BaseProtoCollectionBasis;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier;
import com.google.android.material.snackbar.SnackbarManager;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$BraillebackExtension;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$LanguageCode;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$Mode;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$SettingBrailleCode;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$State;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$Translator;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleDisplayAnalytics {
    private static BrailleDisplayAnalytics instance;
    private final AnalyticsCommon analyticsCommon;
    public long connectToBrailleDisplayStartTimeMs;
    public long connectToHidStartTimeMs;
    public long connectToRfcommStartTimeMs;
    public final Context context;
    private final boolean enableSendLog = true;
    public final ClearcutLogger logger;
    public int readCount;
    public int typedCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends AnalyticsCommon {
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Context context, Context context2) {
            super(context);
            r2 = context2;
        }

        @Override // com.google.android.accessibility.utils.AnalyticsCommon
        public final /* bridge */ /* synthetic */ void sendLog(Object obj) {
            BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(r2);
            BaseProtoCollectionBasis baseProtoCollectionBasis = new BaseProtoCollectionBasis() { // from class: logs.proto.accessibility.brailleback.BraillebackCollectionBasisHelper$BraillebackExtension
                @Override // com.google.android.libraries.consentverifier.BaseProtoCollectionBasis
                public final void singleCollectionBasis$ar$ds() {
                }
            };
            brailleDisplayAnalytics.logger.newEvent$ar$class_merging((BraillebackLogProto$BraillebackExtension) obj, CollectionBasisLogVerifier.newInstance$ar$class_merging$30765897_0(brailleDisplayAnalytics.context, baseProtoCollectionBasis)).logAsync();
        }
    }

    private BrailleDisplayAnalytics(Context context) {
        this.context = context;
        this.logger = new SnackbarManager(context, "BRAILLEBACK", null).build();
        this.analyticsCommon = new AnalyticsCommon(context) { // from class: com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics.1
            final /* synthetic */ Context val$context;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(Context context2, Context context22) {
                super(context22);
                r2 = context22;
            }

            @Override // com.google.android.accessibility.utils.AnalyticsCommon
            public final /* bridge */ /* synthetic */ void sendLog(Object obj) {
                BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(r2);
                BaseProtoCollectionBasis baseProtoCollectionBasis = new BaseProtoCollectionBasis() { // from class: logs.proto.accessibility.brailleback.BraillebackCollectionBasisHelper$BraillebackExtension
                    @Override // com.google.android.libraries.consentverifier.BaseProtoCollectionBasis
                    public final void singleCollectionBasis$ar$ds() {
                    }
                };
                brailleDisplayAnalytics.logger.newEvent$ar$class_merging((BraillebackLogProto$BraillebackExtension) obj, CollectionBasisLogVerifier.newInstance$ar$class_merging$30765897_0(brailleDisplayAnalytics.context, baseProtoCollectionBasis)).logAsync();
            }
        };
    }

    public static final BraillebackLogProto$SettingBrailleCode getBrailleCodeSetting$ar$ds(BrailleLanguages.Code code, boolean z) {
        int i;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$SettingBrailleCode.DEFAULT_INSTANCE.createBuilder();
        int[] values$ar$edu$d0aaf819_0 = BraillebackLogProto$LanguageCode.values$ar$edu$d0aaf819_0();
        int length = values$ar$edu$d0aaf819_0.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                i = values$ar$edu$d0aaf819_0[i2];
                String stringGenerated9efd27e335393a97 = BraillebackLogProto$LanguageCode.toStringGenerated9efd27e335393a97(i);
                if (i != 0) {
                    if (stringGenerated9efd27e335393a97.equals(code.name())) {
                        break;
                    }
                    i2++;
                } else {
                    throw null;
                }
            } else {
                i = BraillebackLogProto$LanguageCode.UNSPECIFIED_CODE$ar$edu;
                break;
            }
        }
        builder.copyOnWrite();
        BraillebackLogProto$SettingBrailleCode braillebackLogProto$SettingBrailleCode = (BraillebackLogProto$SettingBrailleCode) builder.instance;
        if (i != 0) {
            braillebackLogProto$SettingBrailleCode.code_ = i - 1;
            braillebackLogProto$SettingBrailleCode.bitField0_ |= 1;
            String language = code.locale.getLanguage();
            builder.copyOnWrite();
            BraillebackLogProto$SettingBrailleCode braillebackLogProto$SettingBrailleCode2 = (BraillebackLogProto$SettingBrailleCode) builder.instance;
            language.getClass();
            braillebackLogProto$SettingBrailleCode2.bitField0_ |= 2;
            braillebackLogProto$SettingBrailleCode2.locale_ = language;
            int i3 = BraillebackLogProto$Translator.LIB_LOUIS$ar$edu;
            builder.copyOnWrite();
            BraillebackLogProto$SettingBrailleCode braillebackLogProto$SettingBrailleCode3 = (BraillebackLogProto$SettingBrailleCode) builder.instance;
            int i4 = i3 - 1;
            if (i3 != 0) {
                braillebackLogProto$SettingBrailleCode3.translator_ = i4;
                braillebackLogProto$SettingBrailleCode3.bitField0_ |= 4;
                builder.copyOnWrite();
                BraillebackLogProto$SettingBrailleCode braillebackLogProto$SettingBrailleCode4 = (BraillebackLogProto$SettingBrailleCode) builder.instance;
                braillebackLogProto$SettingBrailleCode4.bitField0_ |= 8;
                braillebackLogProto$SettingBrailleCode4.contracted_ = z;
                return (BraillebackLogProto$SettingBrailleCode) builder.build();
            }
            throw null;
        }
        throw null;
    }

    public static BrailleDisplayAnalytics getInstance(Context context) {
        if (instance == null) {
            instance = new BrailleDisplayAnalytics(context.getApplicationContext());
        }
        return instance;
    }

    public static final int getState$ar$ds$ar$edu(boolean z) {
        if (z) {
            return BraillebackLogProto$State.STATE_ON$ar$edu$bc488e37_0;
        }
        return BraillebackLogProto$State.STATE_OFF$ar$edu$bc488e37_0;
    }

    public final void logBrailleInputCodeSetting(BrailleLanguages.Code code, boolean z) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
        boolean z2 = false;
        if (code.isSupportsContracted(this.context) && z) {
            z2 = true;
        }
        BraillebackLogProto$SettingBrailleCode brailleCodeSetting$ar$ds = getBrailleCodeSetting$ar$ds(code, z2);
        builder.copyOnWrite();
        BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = (BraillebackLogProto$BraillebackExtension) builder.instance;
        brailleCodeSetting$ar$ds.getClass();
        braillebackLogProto$BraillebackExtension.settingBrailleInputCode_ = brailleCodeSetting$ar$ds;
        braillebackLogProto$BraillebackExtension.bitField0_ |= 2;
        sendLogs((BraillebackLogProto$BraillebackExtension) builder.build());
    }

    public final void logBrailleOutputCodeSetting(BrailleLanguages.Code code, boolean z) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
        boolean z2 = false;
        if (code.isSupportsContracted(this.context) && z) {
            z2 = true;
        }
        BraillebackLogProto$SettingBrailleCode brailleCodeSetting$ar$ds = getBrailleCodeSetting$ar$ds(code, z2);
        builder.copyOnWrite();
        BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = (BraillebackLogProto$BraillebackExtension) builder.instance;
        brailleCodeSetting$ar$ds.getClass();
        braillebackLogProto$BraillebackExtension.settingBrailleOutputCode_ = brailleCodeSetting$ar$ds;
        braillebackLogProto$BraillebackExtension.bitField0_ |= 4;
        sendLogs((BraillebackLogProto$BraillebackExtension) builder.build());
    }

    public final void logChangeTypingMode(boolean z) {
        int i;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
        if (z) {
            i = BraillebackLogProto$Mode.MODE_PHYSICAL$ar$edu;
        } else {
            i = BraillebackLogProto$Mode.MODE_ON_SCREEN$ar$edu;
        }
        builder.copyOnWrite();
        BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = (BraillebackLogProto$BraillebackExtension) builder.instance;
        if (i != 0) {
            braillebackLogProto$BraillebackExtension.changeTypingMode_ = i - 1;
            braillebackLogProto$BraillebackExtension.bitField0_ |= 32;
            sendLogs((BraillebackLogProto$BraillebackExtension) builder.build());
            return;
        }
        throw null;
    }

    public final void logConnectionReset() {
        this.connectToRfcommStartTimeMs = 0L;
        this.connectToHidStartTimeMs = 0L;
        this.connectToBrailleDisplayStartTimeMs = 0L;
    }

    public final void sendLogs(BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension) {
        this.analyticsCommon.doInBackground(braillebackLogProto$BraillebackExtension);
    }
}

package com.google.android.accessibility.talkback.analytics;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import org.chromium.net.impl.VersionSafeCallbacks$NetworkQualityRttListenerWrapper;
import org.chromium.net.impl.VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Object TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4$ar$f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4(TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut, int i, int i2, long j, int i3) {
        this.switching_field = i3;
        this.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4$ar$f$0 = talkBackAnalyticsLoggerWithClearcut;
        this.f$1 = i;
        this.f$2 = i2;
        this.f$3 = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    ((VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper) this.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4$ar$f$0).onThroughputObservation(this.f$2, this.f$3, this.f$1);
                    return;
                } else {
                    ((VersionSafeCallbacks$NetworkQualityRttListenerWrapper) this.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4$ar$f$0).onRttObservation(this.f$1, this.f$3, this.f$2);
                    return;
                }
            }
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = (TalkBackAnalyticsLoggerWithClearcut) this.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4$ar$f$0;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                long j = this.f$3;
                int i2 = this.f$2;
                int i3 = this.f$1;
                SQLiteDatabase safeGetWritableDatabase = talkBackAnalyticsLoggerWithClearcut.dbHelper.safeGetWritableDatabase();
                ContentValues contentValues = new ContentValues();
                String join = TextUtils.join("-", new String[]{String.valueOf(i3), String.valueOf(i2), String.valueOf(j)});
                contentValues.put("keyboardShortcutUsed", Integer.valueOf(i3));
                contentValues.put("triggerModifier", Integer.valueOf(i2));
                contentValues.put("keyComboCode", Long.valueOf(j));
                contentValues.put("compound", join);
                contentValues.put("count", (Integer) 1);
                TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase, "keyboardShortcutUsedEntry", join, contentValues);
                return;
            }
            return;
        }
        TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut2 = (TalkBackAnalyticsLoggerWithClearcut) this.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4$ar$f$0;
        if (talkBackAnalyticsLoggerWithClearcut2.dbHelper != null) {
            long j2 = this.f$3;
            int i4 = this.f$2;
            int i5 = this.f$1;
            SQLiteDatabase safeGetWritableDatabase2 = talkBackAnalyticsLoggerWithClearcut2.dbHelper.safeGetWritableDatabase();
            ContentValues contentValues2 = new ContentValues();
            String join2 = TextUtils.join("-", new String[]{String.valueOf(i5), String.valueOf(i4), String.valueOf(j2)});
            contentValues2.put("keyboardShortcutChanged", Integer.valueOf(i5));
            contentValues2.put("triggerModifier", Integer.valueOf(i4));
            contentValues2.put("keyComboCode", Long.valueOf(j2));
            contentValues2.put("compound", join2);
            contentValues2.put("count", (Integer) 1);
            TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase2, "keyboardShortcutChangedEntry", join2, contentValues2);
        }
    }

    public TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4(VersionSafeCallbacks$NetworkQualityRttListenerWrapper versionSafeCallbacks$NetworkQualityRttListenerWrapper, int i, long j, int i2, int i3) {
        this.switching_field = i3;
        this.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4$ar$f$0 = versionSafeCallbacks$NetworkQualityRttListenerWrapper;
        this.f$1 = i;
        this.f$3 = j;
        this.f$2 = i2;
    }

    public TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4(VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper versionSafeCallbacks$NetworkQualityThroughputListenerWrapper, int i, long j, int i2, int i3) {
        this.switching_field = i3;
        this.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4$ar$f$0 = versionSafeCallbacks$NetworkQualityThroughputListenerWrapper;
        this.f$2 = i;
        this.f$3 = j;
        this.f$1 = i2;
    }
}

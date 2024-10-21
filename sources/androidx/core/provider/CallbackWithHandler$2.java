package androidx.core.provider;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.selecttospeak.logging.SelectToSpeakLogSender;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SSettings;
import com.google.android.accessibility.selecttospeak.ui.HighlightScrollingTextView;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsDBHelper;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.surveys.internal.view.RatingView;
import com.google.android.marvin.talkback.R;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallbackWithHandler$2 implements Runnable {
    final /* synthetic */ Object CallbackWithHandler$2$ar$val$callback;
    private final /* synthetic */ int switching_field;
    final /* synthetic */ int val$reason;

    public CallbackWithHandler$2(NotificationManagerCompat.Api24Impl api24Impl, int i, int i2) {
        this.switching_field = i2;
        this.CallbackWithHandler$2$ar$val$callback = api24Impl;
        this.val$reason = i;
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v10, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v19, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v7, types: [android.content.SharedPreferences, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        int i;
        switch (this.switching_field) {
            case 0:
                ResourcesCompat.FontCallback fontCallback = ((TypefaceCompat.ResourcesCallbackAdapter) this.CallbackWithHandler$2$ar$val$callback).mFontCallback;
                if (fontCallback != null) {
                    fontCallback.onFontRetrievalFailed(this.val$reason);
                    return;
                }
                return;
            case 1:
                ((ResourcesCompat.FontCallback) this.CallbackWithHandler$2$ar$val$callback).onFontRetrievalFailed(this.val$reason);
                return;
            case 2:
                BrailleIme brailleIme = BrailleIme.instance;
                this.CallbackWithHandler$2$ar$val$callback.performEditorAction(this.val$reason);
                return;
            case 3:
                ((BrailleIme.AnonymousClass10) this.CallbackWithHandler$2$ar$val$callback).m71x1dc0b536(this.val$reason);
                return;
            case 4:
                ReentrantReadWriteLock reentrantReadWriteLock = SelectToSpeakLogSender.lock;
                reentrantReadWriteLock.writeLock().lock();
                int i2 = this.val$reason;
                Object obj = this.CallbackWithHandler$2$ar$val$callback;
                try {
                    z = ((OptionalMethod) obj).OptionalMethod$ar$returnType.getBoolean(((Context) ((OptionalMethod) obj).OptionalMethod$ar$methodName).getResources().getString(R.string.s2s_pref_analytics_is_talkback_on), false);
                    String stringGenerated96103c6a9278eb61 = A11yS2SProtoEnums$A11yS2SActions.toStringGenerated96103c6a9278eb61(i2);
                    if (i2 != 0) {
                        LogUtils.v("SelectToSpeakClearcutAnalytics", "increaseEventCount/id: %s", stringGenerated96103c6a9278eb61);
                        String stringGenerated96103c6a9278eb612 = A11yS2SProtoEnums$A11yS2SActions.toStringGenerated96103c6a9278eb61(i2);
                        if (i2 != 0) {
                            if (!z && SelectToSpeakLogSender.isTalkBackOn((Context) ((OptionalMethod) obj).OptionalMethod$ar$methodName)) {
                                SpannableUtils$IdentifierSpan.putBooleanPref(((OptionalMethod) obj).OptionalMethod$ar$returnType, ((Context) ((OptionalMethod) obj).OptionalMethod$ar$methodName).getResources(), R.string.s2s_pref_analytics_is_talkback_on, true);
                            }
                            ((OptionalMethod) obj).OptionalMethod$ar$returnType.edit().putInt(stringGenerated96103c6a9278eb612, ((OptionalMethod) obj).OptionalMethod$ar$returnType.getInt(stringGenerated96103c6a9278eb612, 0) + 1).apply();
                            int i3 = i2 - 1;
                            if (i2 != 0) {
                                if (i3 != 3) {
                                    if (i3 != 4) {
                                        i = A11yS2SProtoEnums$A11yS2SSettings.UNSPECIFIED_SETTING$ar$edu;
                                    } else {
                                        i = A11yS2SProtoEnums$A11yS2SSettings.ENABLE_OCR_SETTING$ar$edu;
                                    }
                                } else {
                                    i = A11yS2SProtoEnums$A11yS2SSettings.MULTITASK_SETTING$ar$edu;
                                }
                                if (i != A11yS2SProtoEnums$A11yS2SSettings.UNSPECIFIED_SETTING$ar$edu) {
                                    SharedPreferences.Editor edit = ((OptionalMethod) obj).OptionalMethod$ar$returnType.edit();
                                    String stringGeneratedcb0e5ccfc321d674 = A11yS2SProtoEnums$A11yS2SSettings.toStringGeneratedcb0e5ccfc321d674(i);
                                    if (i != 0) {
                                        edit.putBoolean(stringGeneratedcb0e5ccfc321d674, true).commit();
                                    } else {
                                        throw null;
                                    }
                                }
                                return;
                            }
                            throw null;
                        }
                        throw null;
                    }
                    throw null;
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            case 5:
                ((HighlightScrollingTextView) this.CallbackWithHandler$2$ar$val$callback).adjustScroll(this.val$reason);
                return;
            case 6:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                    int i4 = this.val$reason;
                    SQLiteDatabase safeGetWritableDatabase = talkBackAnalyticsLoggerWithClearcut.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    String valueOf = String.valueOf(i4);
                    contentValues.put("keyboardKeymapUsed", Integer.valueOf(i4));
                    contentValues.put("compound", valueOf);
                    contentValues.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase, "keyboardKeymapUsedEntry", valueOf, contentValues);
                    return;
                }
                return;
            case 7:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut2 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut2.dbHelper != null) {
                    int i5 = this.val$reason;
                    if (i5 != 0) {
                        SQLiteDatabase safeGetWritableDatabase2 = talkBackAnalyticsLoggerWithClearcut2.dbHelper.safeGetWritableDatabase();
                        ContentValues contentValues2 = new ContentValues();
                        String valueOf2 = String.valueOf(i5);
                        contentValues2.put("selectedItem", Integer.valueOf(i5));
                        contentValues2.put("compound", valueOf2);
                        contentValues2.put("count", (Integer) 1);
                        TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase2, "selectorActionEvent", valueOf2, contentValues2);
                    }
                    if (talkBackAnalyticsLoggerWithClearcut2.prefs.getBoolean(talkBackAnalyticsLoggerWithClearcut2.service.getString(R.string.pref_key_selector_changed_key), false)) {
                        talkBackAnalyticsLoggerWithClearcut2.dbHelper.cacheSelectorEvent(true);
                        talkBackAnalyticsLoggerWithClearcut2.prefs.edit().putBoolean(talkBackAnalyticsLoggerWithClearcut2.service.getString(R.string.pref_key_selector_changed_key), false).apply();
                        return;
                    }
                    return;
                }
                return;
            case 8:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut3 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut3.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut3.dbHelper.cacheMiscellaneousEvent(this.val$reason);
                    return;
                }
                return;
            case 9:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut4 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut4.dbHelper != null) {
                    int i6 = this.val$reason;
                    SQLiteDatabase safeGetWritableDatabase3 = talkBackAnalyticsLoggerWithClearcut4.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues3 = new ContentValues();
                    String valueOf3 = String.valueOf(i6);
                    contentValues3.put("magnificationUsed", Integer.valueOf(i6));
                    contentValues3.put("compound", valueOf3);
                    contentValues3.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase3, "magnificationEntry", valueOf3, contentValues3);
                    return;
                }
                return;
            case 10:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut5 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut5.dbHelper != null) {
                    int i7 = this.val$reason;
                    SQLiteDatabase safeGetWritableDatabase4 = talkBackAnalyticsLoggerWithClearcut5.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues4 = new ContentValues();
                    String valueOf4 = String.valueOf(i7);
                    contentValues4.put("event", Integer.valueOf(i7));
                    contentValues4.put("compound", valueOf4);
                    contentValues4.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase4, "imageCaptionEvent", valueOf4, contentValues4);
                    return;
                }
                return;
            case 11:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut6 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut6.dbHelper != null) {
                    int i8 = this.val$reason;
                    SQLiteDatabase safeGetWritableDatabase5 = talkBackAnalyticsLoggerWithClearcut6.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues5 = new ContentValues();
                    String valueOf5 = String.valueOf(i8);
                    contentValues5.put("keyboardModifierKeyUsed", Integer.valueOf(i8));
                    contentValues5.put("compound", valueOf5);
                    contentValues5.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase5, "keyboardModifierKeyUsedEntry", valueOf5, contentValues5);
                    return;
                }
                return;
            case 12:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut7 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut7.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut7.dbHelper.cacheMiscellaneousEvent(this.val$reason + 100);
                    return;
                }
                return;
            case 13:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut8 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut8.dbHelper != null) {
                    int i9 = this.val$reason;
                    SQLiteDatabase safeGetWritableDatabase6 = talkBackAnalyticsLoggerWithClearcut8.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues6 = new ContentValues();
                    String valueOf6 = String.valueOf(i9);
                    contentValues6.put("event", Integer.valueOf(i9));
                    contentValues6.put("compound", valueOf6);
                    contentValues6.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase6, "voiceCommandEvent", valueOf6, contentValues6);
                    return;
                }
                return;
            case 14:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut9 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut9.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut9.dbHelper.cacheMiscellaneousEvent(this.val$reason);
                    return;
                }
                return;
            case 15:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut10 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut10.dbHelper != null) {
                    int i10 = this.val$reason;
                    SQLiteDatabase safeGetWritableDatabase7 = talkBackAnalyticsLoggerWithClearcut10.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues7 = new ContentValues();
                    String valueOf7 = String.valueOf(i10);
                    contentValues7.put("gestureId", Integer.valueOf(i10));
                    contentValues7.put("compound", valueOf7);
                    contentValues7.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase7, "gestureUsedEntry", valueOf7, contentValues7);
                    return;
                }
                return;
            case 16:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut11 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut11.dbHelper != null) {
                    int i11 = this.val$reason;
                    SQLiteDatabase safeGetWritableDatabase8 = talkBackAnalyticsLoggerWithClearcut11.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues8 = new ContentValues();
                    String valueOf8 = String.valueOf(i11);
                    contentValues8.put("menuAction", Integer.valueOf(i11));
                    contentValues8.put("compound", valueOf8);
                    contentValues8.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase8, "globalContextMenuEntry", valueOf8, contentValues8);
                    return;
                }
                return;
            case 17:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut12 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut12.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut12.dbHelper.cacheMiscellaneousEvent(this.val$reason);
                    return;
                }
                return;
            case 18:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut13 = (TalkBackAnalyticsLoggerWithClearcut) this.CallbackWithHandler$2$ar$val$callback;
                if (talkBackAnalyticsLoggerWithClearcut13.dbHelper != null) {
                    int i12 = this.val$reason;
                    SQLiteDatabase safeGetWritableDatabase9 = talkBackAnalyticsLoggerWithClearcut13.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues9 = new ContentValues();
                    String valueOf9 = String.valueOf(i12);
                    contentValues9.put("menuAction", Integer.valueOf(i12));
                    contentValues9.put("compound", valueOf9);
                    contentValues9.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase9, "localContextMenuEntry", valueOf9, contentValues9);
                    return;
                }
                return;
            case 19:
                ((GoogleApiManager.ClientConnection) this.CallbackWithHandler$2$ar$val$callback).onConnectionSuspendedInternal(this.val$reason);
                return;
            default:
                RatingView ratingView = (RatingView) this.CallbackWithHandler$2$ar$val$callback;
                RatingView.OnRatingClickListener onRatingClickListener = ratingView.onRatingClickListener;
                if (onRatingClickListener != null) {
                    onRatingClickListener.onClickRating(this.val$reason);
                    ratingView.onRatingClickListener = null;
                    return;
                }
                return;
        }
    }

    public CallbackWithHandler$2(GoogleApiManager.ClientConnection clientConnection, int i, int i2) {
        this.switching_field = i2;
        this.val$reason = i;
        this.CallbackWithHandler$2$ar$val$callback = clientConnection;
    }

    public /* synthetic */ CallbackWithHandler$2(Object obj, int i, int i2) {
        this.switching_field = i2;
        this.CallbackWithHandler$2$ar$val$callback = obj;
        this.val$reason = i;
    }
}

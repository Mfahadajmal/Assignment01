package com.google.android.accessibility.talkback;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.android.accessibility.talkback.actor.gemini.GeminiActor;
import com.google.android.accessibility.talkback.actor.gemini.progress.ProgressTonePlayer;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsDBHelper;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.controller.TelevisionNavigationController;
import com.google.android.accessibility.talkback.imagecaption.Request;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.widget.SimpleOverlay;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import com.google.search.mdi.aratea.proto.FeatureName;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class VoiceActionMonitor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Object VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public VoiceActionMonitor$$ExternalSyntheticLambda0(SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan, int i) {
        this.switching_field = i;
        this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0 = spannableUtils$IdentifierSpan;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String string;
        switch (this.switching_field) {
            case 0:
                ((VoiceActionMonitor) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).skipInterruption = false;
                return;
            case 1:
                RingerModeAndScreenMonitor ringerModeAndScreenMonitor = (RingerModeAndScreenMonitor) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ringerModeAndScreenMonitor.service.getString(R.string.value_screen_off));
                if (ringerModeAndScreenMonitor.isIdle() && ringerModeAndScreenMonitor.callStateMonitor != null) {
                    int i = ringerModeAndScreenMonitor.ringerMode;
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                LogUtils.e("RingerModeAndScreenMon", "Unknown ringer mode: %d", Integer.valueOf(i));
                            }
                        } else {
                            string = ringerModeAndScreenMonitor.service.getString(R.string.value_ringer_vibrate);
                        }
                    } else {
                        string = ringerModeAndScreenMonitor.service.getString(R.string.value_ringer_silent);
                    }
                    StringBuilderUtils.appendWithSeparator$ar$ds(spannableStringBuilder, false, string);
                }
                TelevisionNavigationController televisionNavigationController = ringerModeAndScreenMonitor.televisionNavigationController;
                if (televisionNavigationController != null) {
                    televisionNavigationController.mode = 0;
                }
                ringerModeAndScreenMonitor.service.clearQueues();
                if (!ringerModeAndScreenMonitor.isWatch) {
                    SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                    speakOptions.mQueueMode = 1;
                    speakOptions.mFlags = 2;
                    if (ringerModeAndScreenMonitor.ringerMode == 2) {
                        float streamVolume = ringerModeAndScreenMonitor.getStreamVolume(10);
                        float f = 1.0f;
                        if (streamVolume > 0.0f && (ringerModeAndScreenMonitor.audioManager.isWiredHeadsetOn() || ringerModeAndScreenMonitor.audioManager.isBluetoothA2dpOn())) {
                            f = Math.min(1.0f, ringerModeAndScreenMonitor.getStreamVolume(2) / streamVolume);
                        }
                        Pipeline.FeedbackReturner feedbackReturner = ringerModeAndScreenMonitor.pipeline;
                        Feedback.Part.Builder builder = Feedback.Part.builder();
                        builder.sound = new Feedback.Sound(R.raw.volume_beep, 1.0f, f, 0L);
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder.speech(spannableStringBuilder, speakOptions));
                        return;
                    }
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(ringerModeAndScreenMonitor.pipeline, (Performance.EventId) null, Feedback.speech(spannableStringBuilder, speakOptions));
                    return;
                }
                return;
            case 2:
                ((VolumeMonitor) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).releaseControl();
                return;
            case 3:
                ((VolumeMonitor) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).releaseControl();
                return;
            case 4:
                ((VolumeMonitor) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).releaseControl();
                return;
            case 5:
                AiCoreEndpoint aiCoreEndpoint = (AiCoreEndpoint) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                aiCoreEndpoint.astreaUpdateDialog.showDialog();
                TalkBackAnalyticsImpl.onGeminiAiCoreDialogAction(aiCoreEndpoint.perfs, aiCoreEndpoint.context, 5);
                return;
            case 6:
                AiCoreEndpoint aiCoreEndpoint2 = (AiCoreEndpoint) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                aiCoreEndpoint2.downloadDialog.showDialog();
                TalkBackAnalyticsImpl.onGeminiAiCoreDialogAction(aiCoreEndpoint2.perfs, aiCoreEndpoint2.context, 1);
                return;
            case 7:
                AiCoreEndpoint aiCoreEndpoint3 = (AiCoreEndpoint) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                aiCoreEndpoint3.aiCoreUpdateDialog.showDialog();
                TalkBackAnalyticsImpl.onGeminiAiCoreDialogAction(aiCoreEndpoint3.perfs, aiCoreEndpoint3.context, 3);
                return;
            case 8:
                ((GeminiActor) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).onTimeout();
                return;
            case 9:
                ((ProgressTonePlayer) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).playNextTone();
                return;
            case 10:
                SearchScreenOverlay searchScreenOverlay = (SearchScreenOverlay) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                searchScreenOverlay.searchStrategy.cacheNodeTree(searchScreenOverlay.initialFocusedWindow);
                searchScreenOverlay.searchStrategy.searchKeyword(searchScreenOverlay.keywordEditText.getText().toString());
                searchScreenOverlay.refreshUiState();
                return;
            case 11:
                SearchScreenOverlay searchScreenOverlay2 = (SearchScreenOverlay) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                searchScreenOverlay2.searchStrategy.cacheNodeTree(searchScreenOverlay2.initialFocusedWindow);
                searchScreenOverlay2.searchStrategy.searchKeyword(searchScreenOverlay2.keywordEditText.getText());
                return;
            case 12:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = (TalkBackAnalyticsLoggerWithClearcut) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut.dbHelper.cacheSelectorEvent(false);
                    if (!talkBackAnalyticsLoggerWithClearcut.prefs.getBoolean(talkBackAnalyticsLoggerWithClearcut.service.getString(R.string.pref_key_selector_changed_key), false)) {
                        talkBackAnalyticsLoggerWithClearcut.prefs.edit().putBoolean(talkBackAnalyticsLoggerWithClearcut.service.getString(R.string.pref_key_selector_changed_key), true).apply();
                        return;
                    }
                    return;
                }
                return;
            case 13:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut2 = (TalkBackAnalyticsLoggerWithClearcut) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                if (talkBackAnalyticsLoggerWithClearcut2.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut2.dbHelper.cacheMiscellaneousEvent(0);
                    return;
                }
                return;
            case 14:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut3 = (TalkBackAnalyticsLoggerWithClearcut) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                if (talkBackAnalyticsLoggerWithClearcut3.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut3.dbHelper.cacheMiscellaneousEvent(1);
                    return;
                }
                return;
            case 15:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut4 = (TalkBackAnalyticsLoggerWithClearcut) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                if (talkBackAnalyticsLoggerWithClearcut4.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut4.dbHelper.cacheMiscellaneousEvent(2);
                    return;
                }
                return;
            case 16:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut5 = (TalkBackAnalyticsLoggerWithClearcut) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                if (talkBackAnalyticsLoggerWithClearcut5.dbHelper != null) {
                    SQLiteDatabase safeGetWritableDatabase = talkBackAnalyticsLoggerWithClearcut5.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    String join = TextUtils.join("-", new String[]{String.valueOf(0), String.valueOf(0)});
                    contentValues.put("menuType", (Integer) 0);
                    contentValues.put("menuStyle", (Integer) 0);
                    contentValues.put("compound", join);
                    contentValues.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase, "ContextMenuOpenedEntry", join, contentValues);
                    return;
                }
                return;
            case 17:
                ((ContextMenuItem) ((OrderVerifyingClientCall.State) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).OrderVerifyingClientCall$State$ar$cancellationStatus).onClickPerformed();
                return;
            case 18:
                Object obj = this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0;
                LogUtils.e("ImageCaptionRequest", "CaptionRequest timeout is reached. ".concat(obj.toString()), new Object[0]);
                ((Request) obj).onError(FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu);
                return;
            case 19:
                ((SpannableUtils$IdentifierSpan) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).onFail();
                return;
            default:
                ((SimpleOverlay) this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0).hide();
                return;
        }
    }

    public /* synthetic */ VoiceActionMonitor$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.VoiceActionMonitor$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }
}

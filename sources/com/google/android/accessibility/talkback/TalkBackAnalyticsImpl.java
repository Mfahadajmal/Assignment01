package com.google.android.accessibility.talkback;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import androidx.core.os.UserManagerCompat$Api24Impl;
import androidx.core.provider.CallbackWithHandler$2;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4;
import com.google.android.accessibility.talkback.analytics.TalkBackLogProto$TalkBackExtension;
import com.google.android.accessibility.talkback.analytics.TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType;
import com.google.android.accessibility.talkback.analytics.TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery;
import com.google.android.accessibility.talkback.compositor.EventFeedback$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.talkback.keyboard.TalkBackPhysicalKeyboardShortcut;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.output.FailoverTextToSpeech;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.base.Splitter;
import com.google.common.collect.CollectCollectors;
import com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda21;
import com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda51;
import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import j$.util.DesugarArrays;
import j$.util.stream.Collector;
import j$.util.stream.Stream;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackAnalyticsImpl extends TalkBackAnalytics {
    private static final ImmutableMap EVENT_RES_MAP;
    public static final /* synthetic */ int TalkBackAnalyticsImpl$ar$NoOp = 0;
    private final TalkBackService service;
    public final TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLogger;
    public boolean ready = false;
    public boolean initialized = false;

    static {
        int i = 0;
        Stream stream = DesugarArrays.stream(new Integer[][]{new Integer[]{17, Integer.valueOf(R.string.pref_icon_detection_install_lib_success)}, new Integer[]{18, Integer.valueOf(R.string.pref_icon_detection_install_lib_fail)}, new Integer[]{15, Integer.valueOf(R.string.pref_icon_detection_install_lib_request)}, new Integer[]{16, Integer.valueOf(R.string.pref_icon_detection_install_lib_deny)}, new Integer[]{19, Integer.valueOf(R.string.pref_icon_detection_uninstalled_request)}, new Integer[]{20, Integer.valueOf(R.string.pref_icon_detection_uninstalled_deny)}, new Integer[]{28, Integer.valueOf(R.string.pref_image_description_install_lib_success)}, new Integer[]{29, Integer.valueOf(R.string.pref_image_description_install_lib_fail)}, new Integer[]{26, Integer.valueOf(R.string.pref_image_description_install_lib_request)}, new Integer[]{27, Integer.valueOf(R.string.pref_image_description_install_lib_deny)}, new Integer[]{30, Integer.valueOf(R.string.pref_image_description_uninstalled_request)}, new Integer[]{31, Integer.valueOf(R.string.pref_image_description_uninstalled_deny)}});
        TalkBackAnalyticsImpl$$ExternalSyntheticLambda1 talkBackAnalyticsImpl$$ExternalSyntheticLambda1 = new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(1);
        TalkBackAnalyticsImpl$$ExternalSyntheticLambda1 talkBackAnalyticsImpl$$ExternalSyntheticLambda12 = new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(i);
        Collector collector = CollectCollectors.TO_IMMUTABLE_LIST;
        EVENT_RES_MAP = (ImmutableMap) stream.collect(Collector.CC.of(new EventFeedback$$ExternalSyntheticLambda0(5), new CollectCollectors$$ExternalSyntheticLambda51(talkBackAnalyticsImpl$$ExternalSyntheticLambda1, talkBackAnalyticsImpl$$ExternalSyntheticLambda12, i), new CollectCollectors$$ExternalSyntheticLambda21(3), new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(14), new Collector.Characteristics[0]));
    }

    public TalkBackAnalyticsImpl(TalkBackService talkBackService) {
        this.service = talkBackService;
        this.talkBackAnalyticsLogger = new TalkBackAnalyticsLoggerWithClearcut(talkBackService);
    }

    public static void onGeminiAiCoreDialogAction(SharedPreferences sharedPreferences, Context context, int i) {
        int i2;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            i2 = R.string.pref_astrea_update_accept_key;
                        } else {
                            i2 = R.string.pref_astrea_update_request_key;
                        }
                    } else {
                        i2 = R.string.pref_ai_core_update_accept_key;
                    }
                } else {
                    i2 = R.string.pref_ai_core_update_request_key;
                }
            } else {
                i2 = R.string.pref_ai_feature_download_accept_key;
            }
        } else {
            i2 = R.string.pref_ai_feature_download_request_key;
        }
        sharedPreferences.edit().putInt(context.getString(i2), sharedPreferences.getInt(context.getString(i2), 0) + 1).apply();
    }

    public static void onGeminiOptInFromSettings(SharedPreferences sharedPreferences, Context context, int i, boolean z) {
        int i2;
        int i3;
        if (i != 1) {
            if (i != 2) {
                i2 = R.string.pref_gemini_aicore_opt_in_dissent_key;
                i3 = R.string.pref_gemini_opt_in_dissent_key;
            } else {
                i2 = R.string.pref_gemini_aicore_opt_in_consent_key;
                i3 = R.string.pref_gemini_opt_in_consent_key;
            }
        } else {
            i2 = R.string.pref_gemini_aicore_opt_in_show_dialog_key;
            i3 = R.string.pref_gemini_opt_in_show_dialog_key;
        }
        if (true == z) {
            i2 = i3;
        }
        sharedPreferences.edit().putInt(context.getString(i2), sharedPreferences.getInt(context.getString(i2), 0) + 1).apply();
    }

    public static void onImageCaptionEventFromSettings(SharedPreferences sharedPreferences, Context context, int i) {
        ImmutableMap immutableMap = EVENT_RES_MAP;
        Integer valueOf = Integer.valueOf(i);
        if (!immutableMap.containsKey(valueOf)) {
            return;
        }
        int intValue = ((Integer) immutableMap.get(valueOf)).intValue();
        sharedPreferences.edit().putInt(context.getString(intValue), sharedPreferences.getInt(context.getString(intValue), 0) + 1).apply();
    }

    public final void conditionalPing(String str) {
        if (this.ready && tryToInitialize()) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                long j = talkBackAnalyticsLoggerWithClearcut.lastLogTime;
                if (timeInMillis < j) {
                    talkBackAnalyticsLoggerWithClearcut.saveLastLogTime(timeInMillis);
                } else if (timeInMillis - j >= 43200000) {
                    talkBackAnalyticsLoggerWithClearcut.lastLogTimeBackup = j;
                    talkBackAnalyticsLoggerWithClearcut.saveLastLogTime(timeInMillis);
                    talkBackAnalyticsLoggerWithClearcut.ttsInUse = str;
                    TalkBackService.instance.getLabelManager().getLabelsFromDatabase(talkBackAnalyticsLoggerWithClearcut.finalizeLogListener);
                }
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void logPendingChanges() {
        if (this.initialized) {
            this.talkBackAnalyticsLogger.logPendingChanges();
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onGeminiEvent(int i, boolean z, final boolean z2) {
        final int i2;
        if (this.initialized) {
            final TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                if (i != 1) {
                    if (true != z) {
                        i2 = 21;
                    } else {
                        i2 = 6;
                    }
                } else if (true != z) {
                    i2 = 20;
                } else {
                    i2 = 5;
                }
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new Runnable() { // from class: com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut2 = TalkBackAnalyticsLoggerWithClearcut.this;
                        if (talkBackAnalyticsLoggerWithClearcut2.dbHelper != null) {
                            boolean z3 = z2;
                            int i3 = i2;
                            talkBackAnalyticsLoggerWithClearcut2.dbHelper.cacheMiscellaneousEvent(i3);
                            if (z3 && i3 == 20) {
                                talkBackAnalyticsLoggerWithClearcut2.dbHelper.cacheMiscellaneousEvent(37);
                            }
                        }
                    }
                }).execute(new Void[0]);
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x000d. Please report as an issue. */
    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onGeminiFailEvent(int i, boolean z) {
        int i2;
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                int i3 = 8;
                switch (i) {
                    case 2:
                        if (true != z) {
                            i2 = 23;
                        } else {
                            i2 = 8;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    case 3:
                        if (true != z) {
                            i2 = 24;
                        } else {
                            i2 = 9;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    case 4:
                        if (true != z) {
                            i2 = 25;
                        } else {
                            i2 = 10;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    case 5:
                        if (true != z) {
                            i2 = 26;
                        } else {
                            i2 = 11;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    case 6:
                        if (true != z) {
                            i2 = 27;
                        } else {
                            i2 = 12;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    case 7:
                        if (true != z) {
                            i2 = 28;
                        } else {
                            i2 = 13;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    case 8:
                        if (true != z) {
                            i2 = 29;
                        } else {
                            i2 = 14;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    case 9:
                        if (true != z) {
                            i2 = 30;
                        } else {
                            i2 = 15;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    case 10:
                        if (true != z) {
                            i2 = 36;
                        } else {
                            i2 = 35;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                        return;
                    default:
                        if (!z) {
                            i2 = 34;
                            new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
                            return;
                        }
                        return;
                }
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onGeminiOptInEvent(int i, boolean z) {
        int i2;
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                int i3 = 17;
                if (i != 1) {
                    if (i != 2) {
                        if (true != z) {
                            i2 = 33;
                        } else {
                            i2 = 18;
                        }
                    } else if (true != z) {
                        i2 = 32;
                    } else {
                        i2 = 17;
                    }
                } else if (true != z) {
                    i2 = 31;
                } else {
                    i2 = 16;
                }
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i2, i3)).execute(new Void[0]);
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onGesture(int i) {
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i, 15)).execute(new Void[0]);
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onImageCaptionEvent(int i) {
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                switch (i) {
                    case 32:
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new VoiceActionMonitor$$ExternalSyntheticLambda0(talkBackAnalyticsLoggerWithClearcut, 13)).execute(new Void[0]);
                        return;
                    case 33:
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new VoiceActionMonitor$$ExternalSyntheticLambda0(talkBackAnalyticsLoggerWithClearcut, 14)).execute(new Void[0]);
                        return;
                    case 34:
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new VoiceActionMonitor$$ExternalSyntheticLambda0(talkBackAnalyticsLoggerWithClearcut, 15)).execute(new Void[0]);
                        return;
                    default:
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i, 10)).execute(new Void[0]);
                        return;
                }
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onLocalContextMenuAction(int i, int i2) {
        int i3;
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                switch (i) {
                    case 1:
                        if (i2 == R.string.granularity_default) {
                            i3 = 20;
                            break;
                        } else if (i2 == R.string.granularity_character) {
                            i3 = 21;
                            break;
                        } else if (i2 == R.string.granularity_word) {
                            i3 = 22;
                            break;
                        } else if (i2 == R.string.granularity_line) {
                            i3 = 23;
                            break;
                        } else if (i2 == R.string.granularity_paragraph) {
                            i3 = 24;
                            break;
                        } else if (i2 == R.string.granularity_native_heading) {
                            i3 = 25;
                            break;
                        } else if (i2 == R.string.granularity_native_link) {
                            i3 = 26;
                            break;
                        } else if (i2 == R.string.granularity_native_control) {
                            i3 = 27;
                            break;
                        } else if (i2 == R.string.granularity_web_heading) {
                            i3 = 28;
                            break;
                        } else if (i2 == R.string.granularity_web_link) {
                            i3 = 29;
                            break;
                        } else if (i2 == R.string.granularity_web_list) {
                            i3 = 30;
                            break;
                        } else if (i2 == R.string.granularity_web_control) {
                            i3 = 31;
                            break;
                        } else if (i2 == R.string.granularity_web_landmark) {
                            i3 = 33;
                            break;
                        } else if (i2 == R.string.granularity_window) {
                            i3 = 34;
                            break;
                        } else {
                            if (i2 == R.string.granularity_container) {
                                i3 = 35;
                                break;
                            }
                            i3 = 0;
                            break;
                        }
                    case 2:
                        if (i2 == R.id.edittext_breakout_move_to_beginning) {
                            i3 = 40;
                            break;
                        } else if (i2 == R.id.edittext_breakout_move_to_end) {
                            i3 = 41;
                            break;
                        } else if (i2 == R.id.edittext_breakout_cut) {
                            i3 = 42;
                            break;
                        } else if (i2 == R.id.edittext_breakout_copy) {
                            i3 = 43;
                            break;
                        } else if (i2 == R.id.edittext_breakout_paste) {
                            i3 = 44;
                            break;
                        } else if (i2 == R.id.edittext_breakout_select_all) {
                            i3 = 45;
                            break;
                        } else if (i2 == R.id.edittext_breakout_start_selection_mode) {
                            i3 = 46;
                            break;
                        } else {
                            if (i2 == R.id.edittext_breakout_end_selection_mode) {
                                i3 = 47;
                                break;
                            }
                            i3 = 0;
                            break;
                        }
                    case 3:
                        if (i2 == R.id.viewpager_breakout_prev_page) {
                            i3 = 50;
                            break;
                        } else if (i2 == R.id.viewpager_breakout_next_page) {
                            i3 = 51;
                            break;
                        } else if (i2 == R.id.viewpager_breakout_page_up) {
                            i3 = 52;
                            break;
                        } else if (i2 == R.id.viewpager_breakout_page_down) {
                            i3 = 53;
                            break;
                        } else if (i2 == R.id.viewpager_breakout_page_left) {
                            i3 = 54;
                            break;
                        } else {
                            if (i2 == R.id.viewpager_breakout_page_right) {
                                i3 = 55;
                                break;
                            }
                            i3 = 0;
                            break;
                        }
                    case 4:
                        if (i2 == R.id.labeling_breakout_add_label) {
                            i3 = 60;
                            break;
                        } else if (i2 == R.id.labeling_breakout_edit_label) {
                            i3 = 61;
                            break;
                        } else {
                            if (i2 == R.id.labeling_breakout_remove_label) {
                                i3 = 62;
                                break;
                            }
                            i3 = 0;
                            break;
                        }
                    case 5:
                        if (i2 == 1048576) {
                            i3 = 71;
                            break;
                        } else if (i2 == 262144) {
                            i3 = 72;
                            break;
                        } else if (i2 == 524288) {
                            i3 = 73;
                            break;
                        } else {
                            i3 = 70;
                            break;
                        }
                    case 6:
                        i3 = 80;
                        break;
                    default:
                        i3 = 81;
                        break;
                }
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i3, 18)).execute(new Void[0]);
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onManuallyChangeSetting$ar$ds(String str) {
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.clearcutHelper$ar$class_merging.isLoggableInClearcutEvent(str)) {
                OrderVerifyingClientCall.State state = talkBackAnalyticsLoggerWithClearcut.pendingSettingChangeAction$ar$class_merging$ar$class_merging$ar$class_merging;
                if (state != null && !str.equals(state.OrderVerifyingClientCall$State$ar$cancellationStatus)) {
                    talkBackAnalyticsLoggerWithClearcut.logPendingChanges();
                }
                talkBackAnalyticsLoggerWithClearcut.pendingSettingChangeAction$ar$class_merging$ar$class_merging$ar$class_merging = new OrderVerifyingClientCall.State(str);
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onMoveWithGranularity(CursorGranularity cursorGranularity) {
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new ListMenuManager$$ExternalSyntheticLambda3(talkBackAnalyticsLoggerWithClearcut, cursorGranularity, 1)).execute(new Void[0]);
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onSelectorActionEvent(SelectorController.Setting setting) {
        int i;
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                int i2 = 7;
                switch (setting.ordinal()) {
                    case 0:
                        i = 1;
                        break;
                    case 1:
                        i = 2;
                        break;
                    case 2:
                        i = 3;
                        break;
                    case 3:
                        i = 6;
                        break;
                    case 4:
                    default:
                        i = 0;
                        break;
                    case 5:
                        i = 4;
                        break;
                    case 6:
                        i = 5;
                        break;
                    case 7:
                        i = 7;
                        break;
                    case 8:
                        i = 8;
                        break;
                    case 9:
                        i = 10;
                        break;
                    case 10:
                        i = 25;
                        break;
                    case 11:
                        i = 22;
                        break;
                    case 12:
                        i = 24;
                        break;
                    case 13:
                        i = 21;
                        break;
                    case 14:
                        i = 23;
                        break;
                    case 15:
                        i = 27;
                        break;
                    case 16:
                        i = 26;
                        break;
                    case 17:
                        i = 28;
                        break;
                    case 18:
                        i = 29;
                        break;
                    case 19:
                        i = 30;
                        break;
                    case 20:
                        i = 31;
                        break;
                    case 21:
                        i = 9;
                        break;
                    case 22:
                        i = 32;
                        break;
                }
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i, i2)).execute(new Void[0]);
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics, android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        String loggablePrefValue;
        long parseLong;
        int i;
        int i2;
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                OrderVerifyingClientCall.State state = talkBackAnalyticsLoggerWithClearcut.pendingSettingChangeAction$ar$class_merging$ar$class_merging$ar$class_merging;
                if ((state == null || !((String) state.OrderVerifyingClientCall$State$ar$cancellationStatus).equals(str)) && talkBackAnalyticsLoggerWithClearcut.clearcutHelper$ar$class_merging.isLoggableInClearcutEvent(str) && (loggablePrefValue = talkBackAnalyticsLoggerWithClearcut.getLoggablePrefValue(str)) != null) {
                    if (str.startsWith("keycombo_shortcut") || str.startsWith("default_key_combo_model")) {
                        int i3 = MetricRecorderFactory.actionIdToClearcutEnum(TalkBackPhysicalKeyboardShortcut.getActionFromKey(((Context) talkBackAnalyticsLoggerWithClearcut.clearcutHelper$ar$class_merging.MetricRecorderFactory$ar$activeTraceProviderProvider).getResources(), (String) ContextDataProvider.getLast(Splitter.on('|').splitToList(str))).keyboardShortcutOrdinal).value;
                        if (str.startsWith("default_key_combo_model")) {
                            MetricRecorderFactory metricRecorderFactory = talkBackAnalyticsLoggerWithClearcut.clearcutHelper$ar$class_merging;
                            boolean equals = String.valueOf(sharedPreferences.getAll().get(((Context) metricRecorderFactory.MetricRecorderFactory$ar$activeTraceProviderProvider).getString(R.string.pref_default_keymap_trigger_modifier_key))).equals(((Context) metricRecorderFactory.MetricRecorderFactory$ar$activeTraceProviderProvider).getString(R.string.trigger_modifier_alt_entry_value));
                            long parseLong2 = Long.parseLong(loggablePrefValue);
                            int modifier = KeyComboManager.getModifier(parseLong2);
                            int i4 = (int) parseLong2;
                            if (true != equals) {
                                i2 = 65536;
                            } else {
                                i2 = 2;
                            }
                            long keyComboCode = KeyComboManager.getKeyComboCode(i2 | modifier, i4);
                            i = i2;
                            parseLong = keyComboCode;
                        } else {
                            parseLong = Long.parseLong(loggablePrefValue);
                            i = 0;
                        }
                        new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4(talkBackAnalyticsLoggerWithClearcut, i3, i, parseLong, 0)).execute(new Void[0]);
                    }
                    new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(talkBackAnalyticsLoggerWithClearcut, str, loggablePrefValue, 10, (short[]) null)).execute(new Void[0]);
                }
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onShortcutActionEvent(final GestureShortcutMapping.TalkbackAction talkbackAction) {
        if (this.initialized) {
            final TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new Runnable() { // from class: com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda11
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i;
                        TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut2 = TalkBackAnalyticsLoggerWithClearcut.this;
                        if (talkBackAnalyticsLoggerWithClearcut2.dbHelper != null) {
                            GestureShortcutMapping.TalkbackAction talkbackAction2 = talkbackAction;
                            TalkBackAnalyticsDBHelper talkBackAnalyticsDBHelper = talkBackAnalyticsLoggerWithClearcut2.dbHelper;
                            TalkBackPhysicalKeyboardShortcut talkBackPhysicalKeyboardShortcut = TalkBackPhysicalKeyboardShortcut.ACTION_UNKNOWN;
                            CursorGranularity cursorGranularity = CursorGranularity.DEFAULT;
                            switch (talkbackAction2) {
                                case UNASSIGNED_ACTION:
                                    i = 1;
                                    break;
                                case PERFORM_CLICK:
                                    i = 2;
                                    break;
                                case PERFORM_LONG_CLICK:
                                    i = 3;
                                    break;
                                case PREVIOUS:
                                    i = 4;
                                    break;
                                case NEXT:
                                    i = 5;
                                    break;
                                case FIRST_IN_SCREEN:
                                    i = 6;
                                    break;
                                case LAST_IN_SCREEN:
                                    i = 7;
                                    break;
                                case PREV_CONTAINER:
                                    i = 8;
                                    break;
                                case NEXT_CONTAINER:
                                    i = 9;
                                    break;
                                case PREVIOUS_WINDOW:
                                    i = 10;
                                    break;
                                case NEXT_WINDOW:
                                    i = 11;
                                    break;
                                case SCROLL_BACK:
                                    i = 12;
                                    break;
                                case SCROLL_FORWARD:
                                    i = 13;
                                    break;
                                case SCROLL_UP:
                                    i = 14;
                                    break;
                                case SCROLL_DOWN:
                                    i = 15;
                                    break;
                                case SCROLL_LEFT:
                                    i = 16;
                                    break;
                                case SCROLL_RIGHT:
                                    i = 17;
                                    break;
                                case HOME:
                                    i = 25;
                                    break;
                                case BACK:
                                    i = 26;
                                    break;
                                case OVERVIEW:
                                    i = 27;
                                    break;
                                case NOTIFICATIONS:
                                    i = 28;
                                    break;
                                case QUICK_SETTINGS:
                                    i = 29;
                                    break;
                                case ALL_APPS:
                                    i = 30;
                                    break;
                                case A11Y_BUTTON:
                                    i = 31;
                                    break;
                                case A11Y_BUTTON_LONG_PRESS:
                                    i = 32;
                                    break;
                                case READ_FROM_TOP:
                                    i = 40;
                                    break;
                                case READ_FROM_CURRENT:
                                    i = 41;
                                    break;
                                case PAUSE_OR_RESUME_FEEDBACK:
                                    i = 42;
                                    break;
                                case TOGGLE_VOICE_FEEDBACK:
                                    i = 43;
                                    break;
                                case SHOW_LANGUAGE_OPTIONS:
                                    i = 44;
                                    break;
                                case TALKBACK_BREAKOUT:
                                    i = 50;
                                    break;
                                case SELECT_PREVIOUS_SETTING:
                                    i = 51;
                                    break;
                                case SELECT_NEXT_SETTING:
                                    i = 52;
                                    break;
                                case SELECTED_SETTING_PREVIOUS_ACTION:
                                    i = 53;
                                    break;
                                case SELECTED_SETTING_NEXT_ACTION:
                                    i = 54;
                                    break;
                                case START_SELECTION_MODE:
                                    i = 60;
                                    break;
                                case MOVE_CURSOR_TO_BEGINNING:
                                    i = 61;
                                    break;
                                case MOVE_CURSOR_TO_END:
                                    i = 62;
                                    break;
                                case SELECT_ALL:
                                    i = 63;
                                    break;
                                case COPY:
                                    i = 64;
                                    break;
                                case CUT:
                                    i = 65;
                                    break;
                                case PASTE:
                                    i = 66;
                                    break;
                                case COPY_LAST_SPOKEN_UTTERANCE:
                                    i = 67;
                                    break;
                                case BRAILLE_KEYBOARD:
                                    i = 68;
                                    break;
                                case MEDIA_CONTROL:
                                    i = 75;
                                    break;
                                case INCREASE_VOLUME:
                                    i = 76;
                                    break;
                                case DECREASE_VOLUME:
                                    i = 77;
                                    break;
                                case VOICE_COMMANDS:
                                    i = 78;
                                    break;
                                case SCREEN_SEARCH:
                                    i = 79;
                                    break;
                                case SHOW_HIDE_SCREEN:
                                    i = 80;
                                    break;
                                case PASS_THROUGH_NEXT_GESTURE:
                                    i = 81;
                                    break;
                                case PRINT_NODE_TREE:
                                    i = 82;
                                    break;
                                case PRINT_PERFORMANCE_STATS:
                                    i = 83;
                                    break;
                                case SHOW_CUSTOM_ACTIONS:
                                    i = 84;
                                    break;
                                case NAVIGATE_BRAILLE_SETTINGS:
                                    i = 85;
                                    break;
                                case TUTORIAL:
                                    i = 86;
                                    break;
                                case PRACTICE_GESTURE:
                                    i = 87;
                                    break;
                                case REPORT_GESTURE:
                                    i = 88;
                                    break;
                                case TOGGLE_BRAILLE_DISPLAY_ON_OFF:
                                    i = 89;
                                    break;
                                case DESCRIBE_IMAGE:
                                    i = 90;
                                    break;
                                default:
                                    i = 0;
                                    break;
                            }
                            SQLiteDatabase safeGetWritableDatabase = talkBackAnalyticsDBHelper.safeGetWritableDatabase();
                            ContentValues contentValues = new ContentValues();
                            String valueOf = String.valueOf(i);
                            contentValues.put("shortcutActions", Integer.valueOf(i));
                            contentValues.put("compound", valueOf);
                            contentValues.put("count", (Integer) 1);
                            TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase, "shortcutActionsEntry", valueOf, contentValues);
                        }
                    }
                }).execute(new Void[0]);
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.analytics.TalkBackAnalytics
    public final void onVoiceCommandEvent(int i) {
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i, 13)).execute(new Void[0]);
            }
        }
    }

    public final void sendLogImmediately(int i) {
        int i2;
        if (this.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) TalkBackLogProto$TalkBackExtension.DEFAULT_INSTANCE.createBuilder();
            builder.copyOnWrite();
            TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension = (TalkBackLogProto$TalkBackExtension) builder.instance;
            int i3 = talkBackAnalyticsLoggerWithClearcut.hardwareVariant$ar$edu;
            if (i3 != 0) {
                talkBackLogProto$TalkBackExtension.hardwareVariant_ = i3 - 1;
                talkBackLogProto$TalkBackExtension.bitField0_ |= 134217728;
                String versionName = SpannableUtils$IdentifierSpan.getVersionName(talkBackAnalyticsLoggerWithClearcut.service);
                builder.copyOnWrite();
                TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension2 = (TalkBackLogProto$TalkBackExtension) builder.instance;
                versionName.getClass();
                talkBackLogProto$TalkBackExtension2.bitField0_ |= 1;
                talkBackLogProto$TalkBackExtension2.talkbackVersion_ = versionName;
                int hours = (int) TimeUnit.MILLISECONDS.toHours(SystemClock.elapsedRealtime() - talkBackAnalyticsLoggerWithClearcut.serviceUpTime);
                builder.copyOnWrite();
                TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension3 = (TalkBackLogProto$TalkBackExtension) builder.instance;
                talkBackLogProto$TalkBackExtension3.bitField0_ |= 32768;
                talkBackLogProto$TalkBackExtension3.survivalTimeHour_ = hours;
                int i4 = talkBackAnalyticsLoggerWithClearcut.activationCount;
                builder.copyOnWrite();
                TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension4 = (TalkBackLogProto$TalkBackExtension) builder.instance;
                talkBackLogProto$TalkBackExtension4.bitField0_ |= 131072;
                talkBackLogProto$TalkBackExtension4.activationCount_ = i4;
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery.DEFAULT_INSTANCE.createBuilder();
                builder2.copyOnWrite();
                TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery = (TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery) builder2.instance;
                talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery.bitField0_ |= 1;
                talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery.hasTurnedOffTalkback_ = true;
                int i5 = TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType.TYPE_TALKBACK_EXIT_BANNER$ar$edu;
                int i6 = i5 - 1;
                if (i5 != 0) {
                    if (i == i6) {
                        i2 = TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType.TYPE_TALKBACK_EXIT_BANNER$ar$edu;
                    } else {
                        int i7 = TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType.TYPE_AUTOMATIC_TURNOFF_LOCKSCREEN$ar$edu;
                        int i8 = i7 - 1;
                        if (i7 != 0) {
                            if (i != i8) {
                                i7 = TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType.TYPE_AUTOMATIC_TURNOFF_SHUTDOWN$ar$edu;
                                int i9 = i7 - 1;
                                if (i7 != 0) {
                                    if (i != i9) {
                                        i7 = TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType.TYPE_ACCESSIBILITY_SHORTCUT$ar$edu;
                                        int i10 = i7 - 1;
                                        if (i7 != 0) {
                                            if (i != i10) {
                                                i2 = TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType.TYPE_UNSPECIFIED$ar$edu;
                                            }
                                        } else {
                                            throw null;
                                        }
                                    }
                                } else {
                                    throw null;
                                }
                            }
                            i2 = i7;
                        } else {
                            throw null;
                        }
                    }
                    builder2.copyOnWrite();
                    TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery2 = (TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery) builder2.instance;
                    int i11 = i2 - 1;
                    if (i2 != 0) {
                        talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery2.recoveryType_ = i11;
                        talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery2.bitField0_ |= 2;
                        builder.copyOnWrite();
                        TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension5 = (TalkBackLogProto$TalkBackExtension) builder.instance;
                        TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery3 = (TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery) builder2.build();
                        talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery3.getClass();
                        talkBackLogProto$TalkBackExtension5.talkbackMistriggeringRecovery_ = talkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery3;
                        talkBackLogProto$TalkBackExtension5.bitField0_ |= 2097152;
                        talkBackAnalyticsLoggerWithClearcut.analyticsCommon.doInBackground((TalkBackLogProto$TalkBackExtension) builder.build());
                        return;
                    }
                    throw null;
                }
                throw null;
            }
            throw null;
        }
    }

    public final boolean tryToInitialize() {
        if (!this.initialized && UserManagerCompat$Api24Impl.isUserUnlocked(this.service)) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = this.talkBackAnalyticsLogger;
            String string = talkBackAnalyticsLoggerWithClearcut.service.getString(R.string.pref_key_last_log_time_key);
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            talkBackAnalyticsLoggerWithClearcut.lastLogTime = talkBackAnalyticsLoggerWithClearcut.prefs.getLong(string, timeInMillis);
            if (!talkBackAnalyticsLoggerWithClearcut.prefs.contains(string) || talkBackAnalyticsLoggerWithClearcut.lastLogTime > timeInMillis) {
                talkBackAnalyticsLoggerWithClearcut.saveLastLogTime(timeInMillis);
            }
            Context context = talkBackAnalyticsLoggerWithClearcut.service;
            SharedPreferences sharedPreferences = talkBackAnalyticsLoggerWithClearcut.prefs;
            String string2 = context.getString(R.string.pref_key_service_activation_count_key);
            talkBackAnalyticsLoggerWithClearcut.activationCount = sharedPreferences.getInt(string2, 0) + 1;
            talkBackAnalyticsLoggerWithClearcut.prefs.edit().putInt(string2, talkBackAnalyticsLoggerWithClearcut.activationCount).apply();
            this.service.getSpeechController().mFailoverTts.addListener(new FailoverTextToSpeech.FailoverTtsListener() { // from class: com.google.android.accessibility.talkback.TalkBackAnalyticsImpl.1
                String ttsPackage;

                @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
                public final void onTtsInitialized(boolean z, String str) {
                    TalkBackAnalyticsImpl talkBackAnalyticsImpl = TalkBackAnalyticsImpl.this;
                    talkBackAnalyticsImpl.ready = true;
                    this.ttsPackage = str;
                    talkBackAnalyticsImpl.conditionalPing(str);
                }

                @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
                public final void onUtteranceCompleted(String str, boolean z) {
                    TalkBackAnalyticsImpl.this.conditionalPing(this.ttsPackage);
                }

                @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
                public final void onUtteranceStarted(String str) {
                }

                @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
                public final /* synthetic */ void onUtteranceStarted(String str, long j) {
                    onUtteranceStarted(str);
                }

                @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
                public final void onBeforeUtteranceRequested(String str, FailoverTextToSpeech.UtteranceInfoCombo utteranceInfoCombo) {
                }

                @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
                public final void onUtteranceRangeStarted(String str, int i, int i2) {
                }
            });
            this.initialized = true;
        }
        return this.initialized;
    }
}

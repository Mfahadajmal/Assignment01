package io.grpc;

import androidx.core.provider.CallbackWithHandler$2;
import androidx.transition.Transition;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.eventprocessor.EventState;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.libraries.surveys.internal.controller.SurveyControllerImpl;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.android.marvin.talkback.R;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import io.grpc.internal.CallCredentialsApplyingTransportFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallCredentials$RequestInfo {
    public final /* synthetic */ Object CallCredentials$RequestInfo$ar$this$1;
    public final /* synthetic */ Object CallCredentials$RequestInfo$ar$val$callOptions;
    public final /* synthetic */ Object CallCredentials$RequestInfo$ar$val$method;

    public CallCredentials$RequestInfo() {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.Runnable] */
    public final void onCancel() {
        ?? r0 = this.CallCredentials$RequestInfo$ar$this$1;
        if (r0 == 0) {
            ?? r02 = this.CallCredentials$RequestInfo$ar$val$method;
            ((Transition) this.CallCredentials$RequestInfo$ar$val$callOptions).cancel();
            r02.run();
            return;
        }
        r0.run();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.accessibility.utils.AccessibilityEventListener, java.lang.Object] */
    public final void onItemClick(int i) {
        int i2;
        ContextMenuItem item = ((ContextMenu) this.CallCredentials$RequestInfo$ar$val$callOptions).getItem(i);
        if (item.enabled) {
            Object obj = this.CallCredentials$RequestInfo$ar$val$method;
            ?? r1 = this.CallCredentials$RequestInfo$ar$this$1;
            if (!item.hasNextPopupWidget() && item.needRestoreFocus) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(((ListMenuManager) r1).pipeline, (Performance.EventId) obj, Feedback.focus(Feedback.Focus.Action.RESTORE_ON_NEXT_WINDOW));
            }
            int i3 = 1;
            if (item.hasNextPopupWidget()) {
                i2 = 1;
            } else {
                i2 = item.deferredType$ar$edu;
            }
            if (i2 != 1) {
                if (i2 == 3) {
                    ((ListMenuManager) r1).service.addEventListener(r1);
                }
                ((ListMenuManager) r1).deferredAction$ar$class_merging$ar$class_merging = new OrderVerifyingClientCall.State(item, i2);
            } else {
                ((ListMenuManager) r1).deferredAction$ar$class_merging$ar$class_merging = null;
            }
            ListMenuManager listMenuManager = (ListMenuManager) r1;
            TalkBackAnalytics talkBackAnalytics = listMenuManager.analytics;
            int i4 = item.itemId;
            TalkBackAnalyticsImpl talkBackAnalyticsImpl = (TalkBackAnalyticsImpl) talkBackAnalytics;
            if (talkBackAnalyticsImpl.initialized) {
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = talkBackAnalyticsImpl.talkBackAnalyticsLogger;
                if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                    int i5 = 16;
                    if (i4 != R.id.read_from_top) {
                        if (i4 == R.id.read_from_current) {
                            i3 = 2;
                        } else if (i4 == R.id.repeat_last_utterance) {
                            i3 = 3;
                        } else if (i4 == R.id.spell_last_utterance) {
                            i3 = 4;
                        } else if (i4 == R.id.copy_last_utterance_to_clipboard) {
                            i3 = 5;
                        } else if (i4 == R.id.talkback_settings) {
                            i3 = 8;
                        } else if (i4 == R.id.tts_settings) {
                            i3 = 9;
                        } else if (i4 == R.id.language_menu) {
                            i3 = 12;
                        } else if (i4 == R.id.enable_dimming) {
                            i3 = 10;
                        } else if (i4 == R.id.disable_dimming) {
                            i3 = 11;
                        } else if (i4 == R.id.screen_search) {
                            i3 = 7;
                        } else if (i4 == R.id.verbosity) {
                            i3 = 14;
                        } else if (i4 == R.id.audio_ducking) {
                            i3 = 15;
                        } else if (i4 == R.id.sound_feedback) {
                            i3 = 16;
                        } else if (i4 == R.id.vibration_feedback) {
                            i3 = 17;
                        } else if (i4 == R.id.voice_commands) {
                            i3 = 18;
                        } else if (i4 == R.id.image_caption_menu) {
                            i3 = 82;
                        } else if (i4 == R.id.window_menu) {
                            i3 = 19;
                        } else if (i4 == R.id.braille_display_settings) {
                            i3 = 83;
                        }
                    }
                    new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i3, i5)).execute(new Void[0]);
                }
            }
            if (!item.hasNextPopupWidget() && item.skipRefocusEvents) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(listMenuManager.pipeline, (Performance.EventId) obj, Feedback.focus(Feedback.Focus.Action.MUTE_NEXT_FOCUS));
            }
            A11yAlertDialogWrapper a11yAlertDialogWrapper = listMenuManager.currentDialog;
            if (a11yAlertDialogWrapper != null && a11yAlertDialogWrapper.isShowing()) {
                if (!item.hasNextPopupWidget() && item.skipWindowEvents) {
                    EventState.instance.setFlag(4);
                    EventState.instance.setFlag(5);
                }
                listMenuManager.currentDialog.dismiss();
            }
            if (listMenuManager.deferredAction$ar$class_merging$ar$class_merging == null) {
                item.onClickPerformed();
            }
        }
    }

    public CallCredentials$RequestInfo(SurveyControllerImpl surveyControllerImpl, SurveyRequest surveyRequest, NetworkCaller networkCaller) {
        this.CallCredentials$RequestInfo$ar$this$1 = surveyRequest;
        this.CallCredentials$RequestInfo$ar$val$method = networkCaller;
        this.CallCredentials$RequestInfo$ar$val$callOptions = surveyControllerImpl;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CallCredentials$RequestInfo(CallCredentialsApplyingTransportFactory.CallCredentialsApplyingTransport callCredentialsApplyingTransport, MethodDescriptor methodDescriptor, CallOptions callOptions) {
        this();
        this.CallCredentials$RequestInfo$ar$val$method = methodDescriptor;
        this.CallCredentials$RequestInfo$ar$val$callOptions = callOptions;
        this.CallCredentials$RequestInfo$ar$this$1 = callCredentialsApplyingTransport;
    }

    public /* synthetic */ CallCredentials$RequestInfo(Object obj, Object obj2, Object obj3) {
        this.CallCredentials$RequestInfo$ar$this$1 = obj;
        this.CallCredentials$RequestInfo$ar$val$callOptions = obj2;
        this.CallCredentials$RequestInfo$ar$val$method = obj3;
    }
}

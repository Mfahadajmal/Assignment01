package androidx.work;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import androidx.work.impl.Processor;
import androidx.work.impl.ToContinuation;
import androidx.work.impl.background.systemalarm.CommandHandler;
import androidx.work.impl.background.systemalarm.DelayMetCommandHandler;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.utils.WorkTimer;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuService;
import com.google.android.accessibility.accessibilitymenu.view.A11yMenuOverlayLayout;
import com.google.android.accessibility.accessibilitymenu.view.A11yMenuViewPager;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity;
import com.google.android.accessibility.braille.brailledisplay.BrailleView;
import com.google.android.accessibility.braille.brailledisplay.controller.AutoScrollManager;
import com.google.android.accessibility.braille.brailledisplay.controller.BdController;
import com.google.android.accessibility.braille.brailledisplay.platform.Displayer;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.BrailleImeGestureController;
import com.google.android.accessibility.brailleime.EscapeReminder;
import com.google.android.accessibility.brailleime.TypoHandler;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class WorkerKt$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Object WorkerKt$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ WorkerKt$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [kotlinx.coroutines.Job, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v17, types: [java.lang.CharSequence, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        int i = 0;
        switch (this.switching_field) {
            case 0:
                ((AtomicBoolean) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).set(true);
                return;
            case 1:
                ?? r0 = this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0;
                if (r0 != 0) {
                    r0.cancel(null);
                    return;
                }
                return;
            case 2:
                DelayMetCommandHandler delayMetCommandHandler = (DelayMetCommandHandler) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0;
                if (delayMetCommandHandler.mCurrentState < 2) {
                    WorkGenerationalId workGenerationalId = delayMetCommandHandler.mWorkGenerationalId;
                    delayMetCommandHandler.mCurrentState = 2;
                    Logger.get$ar$ds$16341a92_0();
                    Context context = delayMetCommandHandler.mContext;
                    WorkGenerationalId workGenerationalId2 = delayMetCommandHandler.mWorkGenerationalId;
                    Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
                    intent.setAction("ACTION_STOP_WORK");
                    CommandHandler.writeWorkGenerationalId$ar$ds(intent, workGenerationalId2);
                    delayMetCommandHandler.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(delayMetCommandHandler.mDispatcher, intent, delayMetCommandHandler.mStartId));
                    SystemAlarmDispatcher systemAlarmDispatcher = delayMetCommandHandler.mDispatcher;
                    WorkGenerationalId workGenerationalId3 = delayMetCommandHandler.mWorkGenerationalId;
                    Processor processor = systemAlarmDispatcher.mProcessor;
                    String str = workGenerationalId.workSpecId;
                    if (processor.isEnqueued(workGenerationalId3.workSpecId)) {
                        Logger.get$ar$ds$16341a92_0();
                        delayMetCommandHandler.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(delayMetCommandHandler.mDispatcher, CommandHandler.createScheduleWorkIntent(delayMetCommandHandler.mContext, delayMetCommandHandler.mWorkGenerationalId), delayMetCommandHandler.mStartId));
                        return;
                    }
                    Logger.get$ar$ds$16341a92_0();
                    return;
                }
                Logger.get$ar$ds$16341a92_0();
                return;
            case 3:
                Object obj = this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0;
                DelayMetCommandHandler delayMetCommandHandler2 = (DelayMetCommandHandler) obj;
                if (delayMetCommandHandler2.mCurrentState == 0) {
                    delayMetCommandHandler2.mCurrentState = 1;
                    Logger.get$ar$ds$16341a92_0();
                    WorkGenerationalId workGenerationalId4 = delayMetCommandHandler2.mWorkGenerationalId;
                    Objects.toString(workGenerationalId4);
                    workGenerationalId4.toString();
                    if (delayMetCommandHandler2.mDispatcher.mProcessor.startWork$ar$class_merging$1716d22_0$ar$class_merging$ar$class_merging$ar$class_merging(delayMetCommandHandler2.mToken$ar$class_merging$ar$class_merging, null)) {
                        SystemAlarmDispatcher systemAlarmDispatcher2 = delayMetCommandHandler2.mDispatcher;
                        WorkGenerationalId workGenerationalId5 = delayMetCommandHandler2.mWorkGenerationalId;
                        WorkTimer workTimer = systemAlarmDispatcher2.mWorkTimer;
                        synchronized (workTimer.mLock) {
                            Logger.get$ar$ds$16341a92_0();
                            Objects.toString(workGenerationalId5);
                            workTimer.stopTimer(workGenerationalId5);
                            ToContinuation toContinuation = new ToContinuation(workTimer, workGenerationalId5, 3);
                            workTimer.mTimerMap.put(workGenerationalId5, toContinuation);
                            workTimer.mListeners.put(workGenerationalId5, obj);
                            workTimer.mRunnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.scheduleWithDelay(600000L, toContinuation);
                        }
                        return;
                    }
                    delayMetCommandHandler2.cleanUp();
                    return;
                }
                Logger.get$ar$ds$16341a92_0();
                WorkGenerationalId workGenerationalId6 = delayMetCommandHandler2.mWorkGenerationalId;
                Objects.toString(workGenerationalId6);
                workGenerationalId6.toString();
                return;
            case 4:
                ((AccessibilityMenuService) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).performGlobalAction(9);
                return;
            case 5:
                if (AccessibilityMenuService.instance == null) {
                    return;
                }
                ((AccessibilityMenuService) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).getTheme().applyStyle(R.style.ServiceTheme, true);
                A11yMenuOverlayLayout a11yMenuOverlayLayout = ((AccessibilityMenuService) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).a11yMenuLayout;
                A11yMenuViewPager a11yMenuViewPager = a11yMenuOverlayLayout.a11yMenuViewPager;
                if (a11yMenuViewPager != null) {
                    i = a11yMenuViewPager.viewPager.mCurItem;
                }
                a11yMenuOverlayLayout.configureLayout(i);
                return;
            case 6:
                ((BrailleDisplayImeUnavailableActivity) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).inputMethodManager.showInputMethodPicker();
                return;
            case 7:
                BrailleView brailleView = (BrailleView) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0;
                brailleView.highlightedCell = -1;
                brailleView.invalidate();
                return;
            case 8:
                if (!((AutoScrollManager) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).behaviorNavigation$ar$class_merging$ar$class_merging$ar$class_merging.handlePanDown(true)) {
                    AppCompatTextViewAutoSizeHelper.Api23Impl.i("AutoScrollManager", "Auto scroll reached to the end.");
                    ((AutoScrollManager) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).stop();
                    return;
                }
                return;
            case 9:
                BdController.this.someBrailleCommandUnavailableDialog.show();
                return;
            case 10:
                ((FloatingActionButton.ShadowDelegateImpl) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).onTimedMessageCleared();
                return;
            case 11:
                Displayer.this.readCommand();
                return;
            case 12:
                OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0, 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
                return;
            case 13:
                ((TypoHandler) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).updateTypoTarget();
                return;
            case 14:
                ((BrailleImeGestureController) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).m72x5bdeef8();
                return;
            case 15:
                ((BrailleImeGestureController) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(44, new Object[0]);
                return;
            case 16:
                ((BrailleImeGestureController) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(46, new Object[0]);
                return;
            case 17:
                ((EscapeReminder) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).finishSpeaking.set(false);
                EscapeReminder escapeReminder = (EscapeReminder) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0;
                OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(((BrailleIme) escapeReminder.callback$ar$class_merging$1705af2a_0$ar$class_merging$ar$class_merging.RetryingNameResolver$ResolutionResultListener$ar$this$0).getString(R.string.reminder_announcement), 800, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, escapeReminder.utteranceCompleteRunnable));
                ((EscapeReminder) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).reminderCount++;
                return;
            case 18:
                Context context2 = ((ContextMenuDialog) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).context;
                BrailleUserPreferences.writeCurrentActiveInputCode(context2, BrailleUserPreferences.getNextInputCode(context2));
                return;
            case 19:
                BrailleUserPreferences.writeContractedMode(((ContextMenuDialog) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0).context, !BrailleUserPreferences.readContractedMode(r0));
                return;
            default:
                ContextMenuDialog contextMenuDialog = (ContextMenuDialog) this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0;
                contextMenuDialog.basicActionsDialog.show(contextMenuDialog.viewToAttach);
                return;
        }
    }

    public WorkerKt$$ExternalSyntheticLambda0(Object obj, int i, byte[] bArr) {
        this.switching_field = i;
        this.WorkerKt$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }
}

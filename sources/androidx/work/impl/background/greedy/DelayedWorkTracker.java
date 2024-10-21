package androidx.work.impl.background.greedy;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.widget.AppCompatTextHelper;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModelStore;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnection;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnection;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog;
import com.google.android.accessibility.brailleime.input.BrailleDisplayImeStripView;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.accessibility.selecttospeak.UIManager;
import com.google.android.accessibility.talkback.TouchInteractionMonitor;
import com.google.android.accessibility.talkback.actor.gemini.GeminiActor;
import com.google.android.accessibility.talkback.actor.gemini.GeminiResultDialog;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay;
import com.google.android.accessibility.talkback.actor.voicecommands.SpeechRecognizerActor;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.output.FeedbackController;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.internal.RetryingNameResolver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.chromium.base.PathUtils$$ExternalSyntheticApiModelOutline2;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DelayedWorkTracker {
    public static final /* synthetic */ int DelayedWorkTracker$ar$NoOp = 0;
    static final String TAG = Logger.tagWithPrefix("DelayedWorkTracker");
    public final WindowCallbackWrapper.Api26Impl mClock$ar$class_merging$ar$class_merging$ar$class_merging;
    final Scheduler mImmediateScheduler;
    public final ViewModelStore mRunnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Map mRunnables = new HashMap();

    /* compiled from: PG */
    /* renamed from: androidx.work.impl.background.greedy.DelayedWorkTracker$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Runnable {
        final /* synthetic */ Object DelayedWorkTracker$1$ar$this$0;
        final /* synthetic */ Object DelayedWorkTracker$1$ar$val$workSpec;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(UsbConnection usbConnection, RetryingNameResolver.ResolutionResultListener resolutionResultListener, int i) {
            this.switching_field = i;
            this.DelayedWorkTracker$1$ar$this$0 = resolutionResultListener;
            this.DelayedWorkTracker$1$ar$val$workSpec = usbConnection;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Object, java.lang.Iterable] */
        /* JADX WARN: Type inference failed for: r0v60, types: [com.google.android.accessibility.brailleime.tutorial.TutorialView$TutorialState, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v29, types: [java.lang.CharSequence, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object, androidx.work.impl.constraints.OnConstraintsStateChangedListener] */
        @Override // java.lang.Runnable
        public final void run() {
            String str;
            WorkSpec workSpec = null;
            byte b = 0;
            switch (this.switching_field) {
                case 0:
                    Logger.get$ar$ds$16341a92_0();
                    int i = DelayedWorkTracker.DelayedWorkTracker$ar$NoOp;
                    WorkSpec workSpec2 = (WorkSpec) this.DelayedWorkTracker$1$ar$val$workSpec;
                    String str2 = workSpec2.id;
                    ((DelayedWorkTracker) this.DelayedWorkTracker$1$ar$this$0).mImmediateScheduler.schedule(workSpec2);
                    return;
                case 1:
                    Object obj = this.DelayedWorkTracker$1$ar$val$workSpec;
                    Object obj2 = this.DelayedWorkTracker$1$ar$this$0;
                    synchronized (((Processor) obj2).mLock) {
                        Iterator it = ((Processor) obj2).mOuterListeners.iterator();
                        while (it.hasNext()) {
                            ((ExecutionListener) it.next()).onExecuted((WorkGenerationalId) obj, false);
                        }
                    }
                    return;
                case 2:
                    ((TimeLimiter) this.DelayedWorkTracker$1$ar$val$workSpec).launcher$ar$class_merging$ar$class_merging.stopWorkWithReason$ar$class_merging$ar$class_merging((ViewModelStore) this.DelayedWorkTracker$1$ar$this$0, 3);
                    return;
                case 3:
                    Iterator it2 = this.DelayedWorkTracker$1$ar$val$workSpec.iterator();
                    while (it2.hasNext()) {
                        ((GooglePlayServicesUpdatedReceiver.Callback) it2.next()).onConstraintChanged(((ConstraintTracker) this.DelayedWorkTracker$1$ar$this$0).currentState);
                    }
                    return;
                case 4:
                    Processor processor = ((SystemForegroundDispatcher) this.DelayedWorkTracker$1$ar$this$0).mWorkManagerImpl.mProcessor;
                    Object obj3 = processor.mLock;
                    Object obj4 = this.DelayedWorkTracker$1$ar$val$workSpec;
                    synchronized (obj3) {
                        WorkerWrapper workerWrapperUnsafe = processor.getWorkerWrapperUnsafe((String) obj4);
                        if (workerWrapperUnsafe != null) {
                            workSpec = workerWrapperUnsafe.workSpec;
                        }
                    }
                    if (workSpec != null && workSpec.hasConstraints()) {
                        synchronized (((SystemForegroundDispatcher) this.DelayedWorkTracker$1$ar$this$0).mLock) {
                            ((SystemForegroundDispatcher) this.DelayedWorkTracker$1$ar$this$0).mWorkSpecById.put(AppCompatTextHelper.Api24Impl.generationalId(workSpec), workSpec);
                            Object obj5 = this.DelayedWorkTracker$1$ar$this$0;
                            ((SystemForegroundDispatcher) this.DelayedWorkTracker$1$ar$this$0).mTrackedWorkSpecs.put(AppCompatTextHelper.Api24Impl.generationalId(workSpec), WorkConstraintsTrackerKt.listen$ar$class_merging$ar$class_merging$ar$class_merging(((SystemForegroundDispatcher) obj5).mConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging, workSpec, ((SystemForegroundDispatcher) obj5).mTaskExecutor$ar$class_merging.getTaskCoroutineDispatcher(), this.DelayedWorkTracker$1$ar$this$0));
                        }
                        return;
                    }
                    return;
                case 5:
                    String uuid = ((UUID) this.DelayedWorkTracker$1$ar$val$workSpec).toString();
                    uuid.getClass();
                    AppCompatTextHelper.Api26Impl.cancel((WorkManagerImpl) this.DelayedWorkTracker$1$ar$this$0, uuid);
                    return;
                case 6:
                    Rect rect = new Rect();
                    Object obj6 = this.DelayedWorkTracker$1$ar$this$0;
                    ImageButton imageButton = (ImageButton) obj6;
                    imageButton.getHitRect(rect);
                    Rect rect2 = new Rect();
                    ((TextView) this.DelayedWorkTracker$1$ar$val$workSpec).getHitRect(rect2);
                    int i2 = rect.left;
                    rect.left = 0;
                    rect.right += i2;
                    rect.top = 0;
                    rect.bottom = rect2.bottom;
                    ((View) imageButton.getParent()).setTouchDelegate(new TouchDelegate(rect, (View) obj6));
                    return;
                case 7:
                    Exception exc = (Exception) this.DelayedWorkTracker$1$ar$val$workSpec;
                    AppCompatTextViewAutoSizeHelper.Api23Impl.e("BtConnection", exc.getMessage());
                    BtConnection btConnection = (BtConnection) this.DelayedWorkTracker$1$ar$this$0;
                    if (!btConnection.isShutdown && !btConnection.isFailed) {
                        btConnection.isFailed = true;
                        btConnection.shutdown();
                        AppCompatTextViewAutoSizeHelper.Api23Impl.e("BtConnection", "invoke onFatalError");
                        RetryingNameResolver.ResolutionResultListener resolutionResultListener = btConnection.callback$ar$class_merging$35226cf6_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                        AppCompatTextViewAutoSizeHelper.Api23Impl.e("Connectioneer", "onFatalError: ".concat(String.valueOf(exc.getMessage())));
                        ((Connectioneer) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.disconnect();
                        Context context = ((Connectioneer) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).context;
                        Toast.makeText(context, context.getString(R.string.bd_bt_connection_disconnected_message), 1).show();
                        return;
                    }
                    AppCompatTextViewAutoSizeHelper.Api23Impl.e("BtConnection", "ignore failure because already shutdown or failed");
                    return;
                case 8:
                    ((RetryingNameResolver.ResolutionResultListener) this.DelayedWorkTracker$1$ar$this$0).onRead();
                    ((UsbConnection) this.DelayedWorkTracker$1$ar$val$workSpec).handler.postDelayed(this, 20L);
                    return;
                case 9:
                    FeedbackManager$Type feedbackManager$Type = FeedbackManager$Type.NAVIGATE_OUT_OF_BOUNDS;
                    int i3 = ((FeedbackManager$Type) this.DelayedWorkTracker$1$ar$this$0).resId;
                    com.google.android.accessibility.utils.Logger logger = Performance.DEFAULT_LOGGER;
                    ((FeedbackController) ((ApplicationModule) this.DelayedWorkTracker$1$ar$val$workSpec).ApplicationModule$ar$application).playAuditory(i3, null);
                    return;
                case 10:
                    OnDeviceTextDetectionLoadLogEvent instance$ar$class_merging$8b242409_0$ar$class_merging = OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging();
                    ?? r1 = this.DelayedWorkTracker$1$ar$val$workSpec;
                    instance$ar$class_merging$8b242409_0$ar$class_merging.speak(r1, 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, ((BrailleIme) this.DelayedWorkTracker$1$ar$this$0).getRepeatAnnouncementRunnable(r1)));
                    return;
                case 11:
                    ((ViewAttachedDialog) this.DelayedWorkTracker$1$ar$this$0).show(((KeyboardView) this.DelayedWorkTracker$1$ar$val$workSpec).viewContainer);
                    return;
                case 12:
                    ((ViewAttachedDialog) this.DelayedWorkTracker$1$ar$this$0).show(((KeyboardView) this.DelayedWorkTracker$1$ar$val$workSpec).imeInputView);
                    return;
                case 13:
                    KeyboardView keyboardView = (KeyboardView) this.DelayedWorkTracker$1$ar$val$workSpec;
                    keyboardView.stripView = new BrailleDisplayImeStripView(keyboardView.context);
                    keyboardView.stripView.setCallBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging((RetryingNameResolver.ResolutionResultListener) this.DelayedWorkTracker$1$ar$this$0);
                    RetryingNameResolver.ResolutionResultListener resolutionResultListener2 = keyboardView.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    KeyboardView.ViewContainer viewContainer = keyboardView.viewContainer;
                    BrailleDisplayImeStripView brailleDisplayImeStripView = keyboardView.stripView;
                    resolutionResultListener2.getClass();
                    viewContainer.addView$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(brailleDisplayImeStripView, new RetryingNameResolver.ResolutionResultListener(resolutionResultListener2, b == true ? 1 : 0));
                    return;
                case 14:
                    ?? r0 = this.DelayedWorkTracker$1$ar$this$0;
                    String str3 = TutorialView.TAG;
                    switch (r0.getCurrentState$ar$edu()) {
                        case 1:
                            str = "NONE";
                            break;
                        case 2:
                            str = "INTRO";
                            break;
                        case 3:
                            str = "ROTATE_ORIENTATION";
                            break;
                        case 4:
                            str = "ROTATE_ORIENTATION_CONTINUE";
                            break;
                        case 5:
                            str = "TYPE_LETTER_A";
                            break;
                        case 6:
                            str = "TYPE_LETTER_BCD";
                            break;
                        case 7:
                            str = "SWIPE_LEFT";
                            break;
                        case 8:
                            str = "SWIPE_RIGHT";
                            break;
                        case 9:
                            str = "SWIPE_UP";
                            break;
                        case 10:
                            str = "SWIPE_DOWN";
                            break;
                        case 11:
                            str = "SWIPE_DOWN_2_FINGERS";
                            break;
                        case 12:
                            str = "SWIPE_DOWN_3_FINGERS";
                            break;
                        case 13:
                            str = "SWIPE_UP_3_FINGERS";
                            break;
                        case 14:
                            str = "CONTEXT_MENU_OPENED";
                            break;
                        default:
                            str = "HOLD_6_FINGERS";
                            break;
                    }
                    Object obj7 = this.DelayedWorkTracker$1$ar$val$workSpec;
                    _BOUNDARY.d(str3, "Switch to ".concat(str));
                    TutorialView tutorialView = (TutorialView) obj7;
                    tutorialView.handler.removeCallbacksAndMessages(null);
                    tutorialView.state = r0;
                    tutorialView.numberOfInteractionsPerState = 0;
                    tutorialView.reloadView();
                    return;
                case 15:
                    int[] iArr = new int[2];
                    UIManager uIManager = (UIManager) this.DelayedWorkTracker$1$ar$val$workSpec;
                    uIManager.drawingBoard.getLocationOnScreen(iArr);
                    uIManager.drawingBoard.setBackground(new BitmapDrawable(Bitmap.createBitmap((Bitmap) this.DelayedWorkTracker$1$ar$this$0, iArr[0], iArr[1], uIManager.drawingBoard.getWidth(), uIManager.drawingBoard.getHeight())));
                    return;
                case 16:
                    ((TouchInteractionMonitor) this.DelayedWorkTracker$1$ar$val$workSpec).service.onGesture(PathUtils$$ExternalSyntheticApiModelOutline2.m(this.DelayedWorkTracker$1$ar$this$0));
                    return;
                case 17:
                    ((TouchInteractionMonitor) this.DelayedWorkTracker$1$ar$val$workSpec).service.onGesture(PathUtils$$ExternalSyntheticApiModelOutline2.m(this.DelayedWorkTracker$1$ar$this$0));
                    return;
                case 18:
                    GeminiActor geminiActor = (GeminiActor) this.DelayedWorkTracker$1$ar$this$0;
                    GeminiResultDialog geminiResultDialog = geminiActor.geminiResultDialog;
                    if (geminiResultDialog != null) {
                        geminiResultDialog.dismissDialog();
                    }
                    geminiActor.geminiResultDialog = new GeminiResultDialog(geminiActor.context, (String) this.DelayedWorkTracker$1$ar$val$workSpec);
                    geminiActor.geminiResultDialog.showDialog();
                    return;
                case 19:
                    ((SearchScreenOverlay) this.DelayedWorkTracker$1$ar$this$0).speakHint((String) this.DelayedWorkTracker$1$ar$val$workSpec);
                    return;
                default:
                    if (((SpeechRecognizerActor) this.DelayedWorkTracker$1$ar$this$0).voiceCommandProcessor.handleSpeechCommand(((String) this.DelayedWorkTracker$1$ar$val$workSpec).toLowerCase())) {
                        ((SpeechRecognizerActor) this.DelayedWorkTracker$1$ar$this$0).reset();
                        return;
                    }
                    return;
            }
        }

        public AnonymousClass1(SpeechRecognizerActor speechRecognizerActor, String str, int i) {
            this.switching_field = i;
            this.DelayedWorkTracker$1$ar$val$workSpec = str;
            this.DelayedWorkTracker$1$ar$this$0 = speechRecognizerActor;
        }

        public AnonymousClass1(Object obj, Object obj2, int i) {
            this.switching_field = i;
            this.DelayedWorkTracker$1$ar$this$0 = obj;
            this.DelayedWorkTracker$1$ar$val$workSpec = obj2;
        }

        public /* synthetic */ AnonymousClass1(Object obj, Object obj2, int i, byte[] bArr) {
            this.switching_field = i;
            this.DelayedWorkTracker$1$ar$this$0 = obj;
            this.DelayedWorkTracker$1$ar$val$workSpec = obj2;
        }

        public /* synthetic */ AnonymousClass1(Object obj, Object obj2, int i, char[] cArr) {
            this.switching_field = i;
            this.DelayedWorkTracker$1$ar$val$workSpec = obj;
            this.DelayedWorkTracker$1$ar$this$0 = obj2;
        }
    }

    public DelayedWorkTracker(Scheduler scheduler, ViewModelStore viewModelStore, WindowCallbackWrapper.Api26Impl api26Impl) {
        this.mImmediateScheduler = scheduler;
        this.mRunnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = viewModelStore;
        this.mClock$ar$class_merging$ar$class_merging$ar$class_merging = api26Impl;
    }
}

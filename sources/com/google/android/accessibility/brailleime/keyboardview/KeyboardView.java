package com.google.android.accessibility.brailleime.keyboardview;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.util.Size;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.accessibilitymenu.view.A11yMenuFooter;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog;
import com.google.android.accessibility.brailleime.input.BrailleDisplayImeStripView;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.tutorial.DotBlockView;
import com.google.android.accessibility.brailleime.tutorial.DotsFlashingAnimationView;
import com.google.android.accessibility.brailleime.tutorial.TutorialAnimationView;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor;
import com.google.android.marvin.talkback.R;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Objects;
import j$.util.Optional;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class KeyboardView {
    public BrailleInputView brailleInputView;
    public final Context context;
    private final DisplayManager.DisplayListener displayListener = new AccessibilityButtonMonitor.AnonymousClass1(this, 1);
    private DisplayManager displayManager;
    public View imeInputView;
    public final RetryingNameResolver.ResolutionResultListener keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public BrailleDisplayImeStripView stripView;
    public TutorialView tutorialView;
    public ViewContainer viewContainer;
    public WindowManager windowManager;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.brailleime.keyboardview.KeyboardView$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ Object KeyboardView$1$ar$this$0;
        final /* synthetic */ Object KeyboardView$1$ar$val$runnable;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(KeyboardView keyboardView, Runnable runnable, int i) {
            this.switching_field = i;
            this.KeyboardView$1$ar$val$runnable = runnable;
            this.KeyboardView$1$ar$this$0 = keyboardView;
        }

        /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Object, java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object, java.lang.Runnable] */
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        ((ViewContainer) this.KeyboardView$1$ar$val$runnable).getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        ((RetryingNameResolver.ResolutionResultListener) ((RetryingNameResolver.ResolutionResultListener) this.KeyboardView$1$ar$this$0).RetryingNameResolver$ResolutionResultListener$ar$this$0).onViewReady();
                        return;
                    } else {
                        if (((KeyboardView) this.KeyboardView$1$ar$this$0).isImeInputViewShown()) {
                            ((KeyboardView) this.KeyboardView$1$ar$this$0).imeInputView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            this.KeyboardView$1$ar$val$runnable.run();
                            return;
                        }
                        return;
                    }
                }
                ((ViewGroup) this.KeyboardView$1$ar$this$0).getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Object obj = this.KeyboardView$1$ar$this$0;
                A11yMenuFooter a11yMenuFooter = (A11yMenuFooter) this.KeyboardView$1$ar$val$runnable;
                a11yMenuFooter.expandBtnTouchArea(a11yMenuFooter.previousPageBtn, (View) obj);
                A11yMenuFooter a11yMenuFooter2 = (A11yMenuFooter) this.KeyboardView$1$ar$val$runnable;
                ImageButton imageButton = a11yMenuFooter2.nextPageBtn;
                a11yMenuFooter2.expandBtnTouchArea(imageButton, (View) imageButton.getParent());
                return;
            }
            if (((KeyboardView) this.KeyboardView$1$ar$this$0).isViewContainerShown()) {
                ((KeyboardView) this.KeyboardView$1$ar$this$0).viewContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                this.KeyboardView$1$ar$val$runnable.run();
            }
        }

        public AnonymousClass1(Object obj, Object obj2, int i) {
            this.switching_field = i;
            this.KeyboardView$1$ar$this$0 = obj2;
            this.KeyboardView$1$ar$val$runnable = obj;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ViewContainer extends FrameLayout {
        public ViewContainer(Context context) {
            super(context);
        }

        @Override // android.view.ViewGroup
        public final void addView(View view) {
            removeAllViews();
            super.addView(view);
        }

        public final void addView$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(View view, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
            getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(this, resolutionResultListener, 3));
            addView(view);
        }

        public final boolean contains(View view) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i).equals(view)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyboardView(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.context = new ContextThemeWrapper(context, R.style.BrailleImeTheme);
        this.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
    }

    public final void createAndAddInputView(BrailleInputView.Callback callback) {
        if (this.brailleInputView != null && this.viewContainer.isAttachedToWindow() && this.viewContainer.contains(this.brailleInputView)) {
            this.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onViewReady();
        } else {
            runWhenViewContainerIsReady(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0((Object) this, (Object) callback, (Object) obtainBrailleInputOptions(), 9, (byte[]) null));
        }
    }

    public final void createAndAddTutorialView$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(int i, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        if (this.tutorialView != null && this.viewContainer.isAttachedToWindow() && this.viewContainer.contains(this.tutorialView)) {
            this.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onViewReady();
            this.tutorialView.switchState$ar$edu$ar$ds(i);
        } else {
            runWhenViewContainerIsReady(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(this, resolutionResultListener, i, 3));
        }
    }

    public final View createImeInputView() {
        init();
        View createImeInputViewInternal = createImeInputViewInternal();
        this.imeInputView = createImeInputViewInternal;
        return createImeInputViewInternal;
    }

    protected abstract View createImeInputViewInternal();

    public abstract ViewContainer createViewContainerInternal();

    public abstract Size getScreenSize();

    public final boolean inTwoStepCalibration() {
        BrailleInputView brailleInputView = this.brailleInputView;
        if (brailleInputView != null) {
            return brailleInputView.inTwoStepCalibration();
        }
        return false;
    }

    public final void init() {
        if (this.displayManager == null) {
            DisplayManager displayManager = (DisplayManager) this.context.getSystemService("display");
            this.displayManager = displayManager;
            displayManager.registerDisplayListener(this.displayListener, null);
        }
    }

    public final boolean isImeInputViewShown() {
        View view = this.imeInputView;
        if (view != null && view.isShown()) {
            return true;
        }
        return false;
    }

    public final boolean isInputViewCreated() {
        if (this.brailleInputView != null) {
            return true;
        }
        return false;
    }

    public final boolean isStripViewAttached() {
        ViewContainer viewContainer;
        if (this.stripView != null && (viewContainer = this.viewContainer) != null && viewContainer.isAttachedToWindow()) {
            return true;
        }
        return false;
    }

    public final boolean isTutorialShown() {
        TutorialView tutorialView = this.tutorialView;
        if (tutorialView != null && tutorialView.isShown()) {
            return true;
        }
        return false;
    }

    public final boolean isViewContainerCreated() {
        if (this.viewContainer != null) {
            return true;
        }
        return false;
    }

    public final boolean isViewContainerShown() {
        if (isViewContainerCreated() && this.viewContainer.isShown()) {
            return true;
        }
        return false;
    }

    public final BrailleInputOptions obtainBrailleInputOptions() {
        Context context = this.context;
        BrailleInputOptions.Builder builder = BrailleInputOptions.builder();
        builder.setReverseDots$ar$ds(BrailleUserPreferences.readReverseDotsMode(context));
        builder.setBrailleType$ar$edu$ar$ds(BrailleUserPreferences.getCurrentTypingLanguageType$ar$edu(this.context));
        builder.setTutorialMode$ar$ds(false);
        return builder.build();
    }

    public final Optional obtainImeViewRegion() {
        Object obj = this.imeInputView;
        if (isStripViewAttached()) {
            obj = this.stripView;
        }
        return Optional.ofNullable(obj).map(new BtConnectManager$$ExternalSyntheticLambda1(18));
    }

    public final void onOrientationChanged(int i) {
        _BOUNDARY.d("KeyboardView", "onOrientationChanged");
        BrailleInputView brailleInputView = this.brailleInputView;
        if (brailleInputView != null) {
            brailleInputView.onOrientationChanged(i, getScreenSize());
        }
        BrailleDisplayImeStripView brailleDisplayImeStripView = this.stripView;
        if (brailleDisplayImeStripView != null) {
            getScreenSize();
            brailleDisplayImeStripView.refreshViews();
        }
        TutorialView tutorialView = this.tutorialView;
        if (tutorialView != null) {
            Size screenSize = getScreenSize();
            tutorialView.orientation = i;
            tutorialView.tutorialAnimationView = new TutorialAnimationView(tutorialView.context, i, screenSize, tutorialView.isTabletop);
            tutorialView.inputView.onOrientationChanged(i, screenSize);
            DotBlockView dotBlockView = tutorialView.dotBlockView;
            dotBlockView.orientation = i;
            dotBlockView.invalidate();
            dotBlockView.requestLayout();
            DotsFlashingAnimationView dotsFlashingAnimationView = tutorialView.dotsFlashingAnimationView;
            if (dotsFlashingAnimationView != null) {
                ArrayList arrayList = new ArrayList();
                Iterator it = dotsFlashingAnimationView.dotTargets.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(((OrderVerifyingClientCall.State) it.next()).type$ar$edu$88c656f2_0));
                }
                dotsFlashingAnimationView.dotTargets = tutorialView.getDotTargetsForBrailleCharacter(new BrailleCharacter(arrayList));
                DotsFlashingAnimationView dotsFlashingAnimationView2 = tutorialView.dotsFlashingAnimationView;
                dotsFlashingAnimationView2.orientation = i;
                dotsFlashingAnimationView2.invalidate();
                dotsFlashingAnimationView2.requestLayout();
            }
            TutorialAnimationView tutorialAnimationView = tutorialView.tutorialAnimationView;
            tutorialAnimationView.canvasView.setLayoutParams(new FrameLayout.LayoutParams(screenSize.getWidth(), screenSize.getHeight()));
            TutorialAnimationView.CanvasView canvasView = tutorialAnimationView.canvasView;
            canvasView.orientation = i;
            canvasView.canvasSize = canvasView.calculateCanvasSize(screenSize);
            canvasView.invalidate();
            canvasView.requestLayout();
            tutorialAnimationView.invalidate();
            tutorialAnimationView.requestLayout();
            if (tutorialView.contextMenuDialog.isShowing()) {
                tutorialView.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onBrailleImeInactivated();
            }
            if (!tutorialView.state.isActionCompleted()) {
                tutorialView.reloadView();
            }
        }
        if (this.viewContainer != null) {
            updateViewContainerInternal();
        }
    }

    public final void runWhenViewContainerIsReady(Runnable runnable) {
        if (!isViewContainerShown() && !Objects.equals(Build.FINGERPRINT, "robolectric")) {
            this.viewContainer.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(this, runnable, 0));
        } else {
            runnable.run();
        }
    }

    public abstract void setKeyboardViewTransparent(boolean z);

    public final void setTableMode(boolean z) {
        BrailleInputView brailleInputView = this.brailleInputView;
        if (brailleInputView != null) {
            brailleInputView.setTableMode(z);
        }
    }

    public final void showViewAttachedDialog(ViewAttachedDialog viewAttachedDialog) {
        char[] cArr = null;
        if (this.viewContainer != null) {
            runWhenViewContainerIsReady(new DelayedWorkTracker.AnonymousClass1(this, viewAttachedDialog, 11, cArr));
            return;
        }
        if (this.imeInputView != null) {
            DelayedWorkTracker.AnonymousClass1 anonymousClass1 = new DelayedWorkTracker.AnonymousClass1(this, viewAttachedDialog, 12, cArr);
            if (!isImeInputViewShown() && !Objects.equals(Build.FINGERPRINT, "robolectric")) {
                this.imeInputView.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(this, (Runnable) anonymousClass1, 2));
                return;
            } else {
                anonymousClass1.run();
                return;
            }
        }
        throw new IllegalArgumentException("No available view to attach.");
    }

    public final void tearDown() {
        ViewContainer viewContainer = this.viewContainer;
        if (viewContainer != null) {
            viewContainer.removeAllViews();
        }
        this.stripView = null;
        this.brailleInputView = null;
        this.tutorialView = null;
        tearDownInternal();
        ViewContainer viewContainer2 = this.viewContainer;
        if (viewContainer2 != null && viewContainer2.getChildCount() == 0) {
            this.viewContainer = null;
        }
        this.windowManager = null;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        _BOUNDARY.d("BrailleIme", "onViewCleared");
        ((BrailleIme) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).layoutOrientator$ar$class_merging.stop();
        DisplayManager displayManager = this.displayManager;
        if (displayManager != null) {
            displayManager.unregisterDisplayListener(this.displayListener);
            this.displayManager = null;
        }
    }

    protected abstract void tearDownInternal();

    public abstract void updateViewContainerInternal();
}

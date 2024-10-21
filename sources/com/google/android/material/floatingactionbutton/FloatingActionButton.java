package com.google.android.material.floatingactionbutton;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityService;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.AdapterHelper$UpdateOp;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Range;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.collection.SimpleArrayMap;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompatBuilder$Api26Impl;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.transition.Transition;
import com.google.android.accessibility.accessibilitymenu.view.A11yMenuViewPager;
import com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics;
import com.google.android.accessibility.braille.brailledisplay.controller.AutoScrollManager;
import com.google.android.accessibility.braille.brailledisplay.controller.BdController;
import com.google.android.accessibility.braille.brailledisplay.controller.CellsContent;
import com.google.android.accessibility.braille.brailledisplay.controller.CellsContentManager;
import com.google.android.accessibility.braille.brailledisplay.controller.ContentHelper;
import com.google.android.accessibility.braille.brailledisplay.platform.BrailleDisplayManager;
import com.google.android.accessibility.braille.brailledisplay.platform.Displayer;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.brailleime.input.BrailleInputPlane;
import com.google.android.accessibility.brailleime.input.BrailleInputPlaneResult;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.LanguageActor;
import com.google.android.accessibility.talkback.actor.PassThroughModeActor;
import com.google.android.accessibility.talkback.actor.gemini.GeminiActor;
import com.google.android.accessibility.talkback.actor.search.UniversalSearchActor;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.phenotype.client.stable.FlagStore;
import com.google.android.libraries.phenotype.client.stable.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2;
import com.google.android.libraries.storage.protostore.XDataStore;
import com.google.android.libraries.surveys.PresentSurveyRequest;
import com.google.android.libraries.surveys.internal.controller.SurveyControllerImpl;
import com.google.android.libraries.surveys.internal.datastore.SurveyDataStore;
import com.google.android.libraries.surveys.internal.event.SurveyInternalEvent;
import com.google.android.libraries.surveys.internal.model.SurveyDataImpl;
import com.google.android.libraries.surveys.internal.model.SurveyStyle;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.ViewOffsetHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.floatingactionbutton.FloatingActionButtonImpl;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.material.stateful.ExtendableSavedState;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$BraillebackExtension;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.okhttp.OutboundFlowController;
import j$.util.Objects;
import j$.util.Optional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import kotlinx.coroutines.scheduling.WorkQueue;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import org.chromium.base.PathUtils$$ExternalSyntheticApiModelOutline2;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements Shapeable, CoordinatorLayout.AttachedBehavior {
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private int borderWidth;
    boolean compatPadding;
    private int customSize;
    private final SnackbarManager.SnackbarRecord expandableWidgetHelper$ar$class_merging;
    private final SharedResourceHolder.Instance imageHelper$ar$class_merging$ar$class_merging;
    public int imagePadding;
    private FloatingActionButtonImpl impl;
    private int maxImageSize;
    private ColorStateList rippleColor;
    final Rect shadowPadding;
    private int size;
    private final Rect touchArea;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private final boolean autoHideEnabled;
        private Rect tmpRect;

        public BaseBehavior() {
            this.autoHideEnabled = true;
        }

        private static boolean isBottomSheet(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).mBehavior instanceof BottomSheetBehavior;
            }
            return false;
        }

        private final boolean shouldUpdateVisibility(View view, FloatingActionButton floatingActionButton) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();
            if (!this.autoHideEnabled || layoutParams.mAnchorId != view.getId() || floatingActionButton.userSetVisibility != 0) {
                return false;
            }
            return true;
        }

        private final boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.hide$ar$ds();
                return true;
            }
            floatingActionButton.show$ar$ds();
            return true;
        }

        private final boolean updateFabVisibilityForBottomSheet(View view, FloatingActionButton floatingActionButton) {
            if (!shouldUpdateVisibility(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams()).topMargin) {
                floatingActionButton.hide$ar$ds();
                return true;
            }
            floatingActionButton.show$ar$ds();
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            getInsetDodgeRect$ar$ds(coordinatorLayout, (FloatingActionButton) view, rect);
            return true;
        }

        public void getInsetDodgeRect$ar$ds(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.shadowPadding;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: onDependentViewChanged$ar$ds$1d341687_0, reason: merged with bridge method [inline-methods] */
        public void onDependentViewChanged$ar$ds(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, floatingActionButton);
            } else if (isBottomSheet(view)) {
                updateFabVisibilityForBottomSheet(view, floatingActionButton);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            onLayoutChild$ar$ds$66ebbce8_0(coordinatorLayout, (FloatingActionButton) view, i);
            return true;
        }

        public void onLayoutChild$ar$ds$66ebbce8_0(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            int i2;
            List dependencies = coordinatorLayout.getDependencies(floatingActionButton);
            int size = dependencies.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                View view = (View) dependencies.get(i4);
                if (view instanceof AppBarLayout) {
                    if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.onLayoutChild(floatingActionButton, i);
            Rect rect = floatingActionButton.shadowPadding;
            if (rect != null && rect.centerX() > 0 && rect.centerY() > 0) {
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();
                if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - layoutParams.rightMargin) {
                    i2 = rect.right;
                } else if (floatingActionButton.getLeft() <= layoutParams.leftMargin) {
                    i2 = -rect.left;
                } else {
                    i2 = 0;
                }
                if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - layoutParams.bottomMargin) {
                    i3 = rect.bottom;
                } else if (floatingActionButton.getTop() <= layoutParams.topMargin) {
                    i3 = -rect.top;
                }
                if (i3 != 0) {
                    int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                    floatingActionButton.offsetTopAndBottom(i3);
                }
                if (i2 != 0) {
                    int[] iArr2 = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                    floatingActionButton.offsetLeftAndRight(i2);
                }
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public final /* bridge */ /* synthetic */ void getInsetDodgeRect$ar$ds(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            super.getInsetDodgeRect$ar$ds(coordinatorLayout, floatingActionButton, rect);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* renamed from: onDependentViewChanged$ar$ds$1d341687_0 */
        public final /* bridge */ /* synthetic */ void onDependentViewChanged$ar$ds(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            super.onDependentViewChanged$ar$ds(coordinatorLayout, floatingActionButton, view);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public final /* bridge */ /* synthetic */ void onLayoutChild$ar$ds$66ebbce8_0(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            super.onLayoutChild$ar$ds$66ebbce8_0(coordinatorLayout, floatingActionButton, i);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ShadowDelegateImpl {
        public final /* synthetic */ Object FloatingActionButton$ShadowDelegateImpl$ar$this$0;

        public ShadowDelegateImpl() {
        }

        public final boolean acceptInput() {
            AccessibilityNodeInfoCompat accessibilityFocusNode;
            if (!((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isBrailleKeyboardActivated() || (accessibilityFocusNode = ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getAccessibilityFocusNode(false)) == null || !accessibilityFocusNode.isFocused() || !AccessibilityNodeInfoUtils.nodeMatchesClassByType(accessibilityFocusNode, EditText.class)) {
                return false;
            }
            return true;
        }

        public final boolean allowSelectLanguage() {
            return ((LanguageActor) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).allowSelectLanguage();
        }

        public final boolean copySelectedText() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().copySelectedText();
        }

        public final boolean cutSelectedText() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().cutSelectedText();
        }

        public final void deleteBackward$ar$ds() {
            ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().deleteBackward$ar$ds$81eb5611_0();
        }

        public final void deleteWordBackward$ar$ds() {
            ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().deleteWordBackward$ar$ds$984ca5ff_0();
        }

        public final boolean detect(Optional optional) {
            optional.isPresent();
            int i = ((BrailleInputPlaneResult) optional.get()).type;
            int i2 = ((BrailleInputPlaneResult) optional.get()).pointersHeldCount;
            if (i == 2 && (((BrailleInputView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).options.brailleType$ar$edu != 8 ? i2 == 6 || i2 == 3 : i2 == 8 || i2 == 4)) {
                Object obj = this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                BrailleInputPlane brailleInputPlane = ((BrailleInputView) obj).inputPlane;
                BrailleInputOptions brailleInputOptions = brailleInputPlane.options;
                if (!brailleInputOptions.tutorialMode) {
                    int i3 = brailleInputOptions.brailleType$ar$edu;
                    brailleInputPlane.dotCenterPosition = new PointF[i3];
                    brailleInputPlane.textPosition = new PointF[i3];
                    for (int i4 = 0; i4 < brailleInputPlane.options.brailleType$ar$edu; i4++) {
                        brailleInputPlane.dotCenterPosition[i4] = new PointF();
                        brailleInputPlane.textPosition[i4] = new PointF();
                    }
                    AnimatorSet animatorSet = new AnimatorSet();
                    ArrayList arrayList = new ArrayList();
                    for (int i5 = 0; i5 < brailleInputPlane.dotTargets.size(); i5++) {
                        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("ScaleX", ((PointF) ((OrderVerifyingClientCall.State) brailleInputPlane.oldDotTargets.get(i5)).OrderVerifyingClientCall$State$ar$cancellationStatus).x, ((PointF) ((OrderVerifyingClientCall.State) brailleInputPlane.dotTargets.get(i5)).OrderVerifyingClientCall$State$ar$cancellationStatus).x);
                        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("ScaleY", ((PointF) ((OrderVerifyingClientCall.State) brailleInputPlane.oldDotTargets.get(i5)).OrderVerifyingClientCall$State$ar$cancellationStatus).y, ((PointF) ((OrderVerifyingClientCall.State) brailleInputPlane.dotTargets.get(i5)).OrderVerifyingClientCall$State$ar$cancellationStatus).y);
                        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt("alpha", 0, PrivateKeyType.INVALID);
                        ValueAnimator valueAnimator = new ValueAnimator();
                        valueAnimator.setValues(ofFloat, ofFloat2, ofInt);
                        valueAnimator.setDuration(100L);
                        valueAnimator.setInterpolator(new LinearInterpolator());
                        valueAnimator.addUpdateListener(new TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0(brailleInputPlane, i5, (View) obj, 1));
                        arrayList.add(valueAnimator);
                    }
                    animatorSet.playTogether(arrayList);
                    animatorSet.start();
                }
            }
            boolean processResult = ((BrailleInputView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).processResult((BrailleInputPlaneResult) optional.get());
            ((BrailleInputView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).invalidate();
            return processResult;
        }

        public final void didAnswerMultiSelectQuestion$ar$ds(SurveyInternalEvent surveyInternalEvent) {
            if (((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyDataStore.getSurveyEventListener(surveyInternalEvent.sessionId) != null) {
                ((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyData.getSurveyMetadata();
            }
        }

        public final void didAnswerOpenTextQuestion(SurveyInternalEvent surveyInternalEvent) {
            if (((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyDataStore.getSurveyEventListener(surveyInternalEvent.sessionId) != null) {
                ((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyData.getSurveyMetadata();
            }
        }

        public final void didAnswerRatingQuestion$ar$ds(SurveyInternalEvent surveyInternalEvent) {
            if (((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyDataStore.getSurveyEventListener(surveyInternalEvent.sessionId) != null) {
                ((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyData.getSurveyMetadata();
            }
        }

        public final void didAnswerSingleSelectQuestion$ar$ds(SurveyInternalEvent surveyInternalEvent) {
            if (((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyDataStore.getSurveyEventListener(surveyInternalEvent.sessionId) != null) {
                ((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyData.getSurveyMetadata();
            }
        }

        public final void dispatchUpdate(AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
            int i = adapterHelper$UpdateOp.cmd;
            if (i != 1) {
                if (i != 2) {
                    if (i != 4) {
                        if (i != 8) {
                            return;
                        }
                        ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mLayout.onItemsMoved$ar$ds(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
                        return;
                    } else {
                        RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                        RecyclerView.LayoutManager layoutManager = recyclerView.mLayout;
                        int i2 = adapterHelper$UpdateOp.positionStart;
                        int i3 = adapterHelper$UpdateOp.itemCount;
                        Object obj = adapterHelper$UpdateOp.payload;
                        layoutManager.onItemsUpdated$ar$ds(recyclerView, i2, i3);
                        return;
                    }
                }
                ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mLayout.onItemsRemoved$ar$ds(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
                return;
            }
            ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mLayout.onItemsAdded$ar$ds(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
        }

        public final void displayDots(byte[] bArr, CharSequence charSequence, int[] iArr) {
            if (!((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isDisplayerReady()) {
                return;
            }
            Displayer displayer = ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).displayer;
            if (displayer.isReady()) {
                Handler handler = displayer.bgHandler;
                int i = Displayer.MessageBg.WRITE_BRAILLE_DOTS$ar$edu;
                int i2 = i - 1;
                if (i != 0) {
                    if (handler.hasMessages(i2)) {
                        Handler handler2 = displayer.bgHandler;
                        int i3 = Displayer.MessageBg.WRITE_BRAILLE_DOTS$ar$edu;
                        int i4 = i3 - 1;
                        if (i3 != 0) {
                            handler2.removeMessages(i4);
                        } else {
                            throw null;
                        }
                    }
                    Handler handler3 = displayer.bgHandler;
                    int i5 = Displayer.MessageBg.WRITE_BRAILLE_DOTS$ar$edu;
                    int i6 = i5 - 1;
                    if (i5 != 0) {
                        handler3.obtainMessage(i6, bArr).sendToTarget();
                    } else {
                        throw null;
                    }
                } else {
                    throw null;
                }
            }
            ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).overlayDisplay.displayDots(bArr, charSequence, iArr);
        }

        public final RecyclerView.ViewHolder findViewHolder(int i) {
            RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i2 = 0;
            RecyclerView.ViewHolder viewHolder = null;
            while (true) {
                if (i2 >= unfilteredChildCount) {
                    break;
                }
                RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i2));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.mPosition == i) {
                    if (recyclerView.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        viewHolder = childViewHolderInt;
                    } else {
                        viewHolder = childViewHolderInt;
                        break;
                    }
                }
                i2++;
            }
            if (viewHolder == null || ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mChildHelper.isHidden(viewHolder.itemView)) {
                return null;
            }
            return viewHolder;
        }

        public final AccessibilityNodeInfoCompat getAccessibilityFocusNode(boolean z) {
            return WorkQueue.getAccessibilityFocusNode$ar$ds(z);
        }

        public final AccessibilityService getAccessibilityService() {
            return (AccessibilityService) ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$buffer;
        }

        public final View getChildAt(int i) {
            return ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getChildAt(i);
        }

        public final int getChildCount() {
            return ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getChildCount();
        }

        public final String getCurrentLanguageString() {
            LanguageActor languageActor = (LanguageActor) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            return LanguageActor.getLocaleString(languageActor.context, languageActor.speechLanguage$ar$class_merging$ar$class_merging.getCurrentLanguage());
        }

        public final int getCurrentShowContentLength() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).cellsContentManager.getCurrentDisplayInfoWrapper().displayInfo.displayedBraille.array().length;
        }

        public final CharSequence getCustomLabelText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Label labelForViewIdFromCache = ((TalkBackLabelManager) ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$producerIndex).getLabelForViewIdFromCache(accessibilityNodeInfoCompat.getViewIdResourceName());
            if (labelForViewIdFromCache != null) {
                return labelForViewIdFromCache.mText;
            }
            return null;
        }

        public final Set getLanguages() {
            return ((SpeechControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getLanguages();
        }

        public final int getSpeechRatePercentage() {
            return ((OutboundFlowController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).initialWindowSize;
        }

        public final boolean handleBrailleKeyWithoutKeyboardOpen(int i) {
            if (((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() != null) {
                HapticPatternParser$$ExternalSyntheticLambda1 brailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
                if (((BrailleIme) brailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getCurrentInputEditorInfo() != null && Objects.equals(((BrailleIme) brailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getCurrentInputEditorInfo().packageName, "gov.loc.nls.dtb")) {
                    return ((BrailleIme) brailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getCurrentInputConnection().commitText(BrailleUserPreferences.readTranslatorFactory((Context) brailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).create((Context) brailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0, BrailleLanguages.Code.EN_NABCC.name(), false).translateToPrint(new BrailleWord(new byte[]{(byte) i})), 1);
                }
            }
            return false;
        }

        public final boolean handlePanDown(boolean z) {
            CellsContentManager cellsContentManager = ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).cellsContentManager;
            if (cellsContentManager.getCurrentDisplayInfoWrapper().panDown()) {
                cellsContentManager.refresh();
                cellsContentManager.cellOnDisplayContentChanged();
            } else if (cellsContentManager.timedMessager.timedMessageDisplaying) {
                cellsContentManager.clearTimedMessage();
            } else {
                if (z && ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isOnscreenKeyboardActive()) {
                    return false;
                }
                return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(1, new Object[0]);
            }
            return true;
        }

        public final boolean hasAiCore() {
            return ((GeminiActor) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).aiCoreEndpoint.hasAiCore();
        }

        public final int indexOfChild(View view) {
            return ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).indexOfChild(view);
        }

        public final void invitationAnswered$ar$ds(SurveyInternalEvent surveyInternalEvent) {
            if (((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyDataStore.getSurveyEventListener(surveyInternalEvent.sessionId) != null) {
                ((SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).surveyData.getSurveyMetadata();
            }
        }

        public final boolean isAiFeatureAvailable() {
            return ((GeminiActor) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).aiCoreEndpoint.isAiFeatureAvailable();
        }

        public final boolean isBrailleDisplayConnected() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).brailleDisplayForBrailleIme.isBrailleDisplayConnectedAndNotSuspended();
        }

        public final boolean isBrailleKeyboardActivated() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isBrailleKeyboardActivated();
        }

        public final boolean isCompatPaddingEnabled() {
            return ((FloatingActionButton) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).compatPadding;
        }

        public final boolean isImeOpen() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isBrailleKeyboardActivated();
        }

        public final boolean isOnscreenKeyboardActive() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isOnscreenKeyboardActive();
        }

        public final boolean isPassThroughModeActive() {
            return ((PassThroughModeActor) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).touchExplorePassThroughActive;
        }

        public final boolean isSpeaking() {
            return ((SpeechControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mIsSpeaking;
        }

        public final boolean isSpeakingOrQueuedAndNotSourceIsVolumeAnnouncment() {
            SpeechControllerImpl speechControllerImpl = (SpeechControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            if (!speechControllerImpl.sourceIsVolumeControl) {
                if (speechControllerImpl.mIsSpeaking || !speechControllerImpl.feedbackQueue.isEmpty()) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public final boolean isUiVisible() {
            return ((UniversalSearchActor) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isUiVisible();
        }

        public final void markViewHoldersUpdated(int i, int i2, Object obj) {
            int i3;
            int i4;
            int i5;
            RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i6 = 0;
            while (true) {
                i3 = i + i2;
                if (i6 >= unfilteredChildCount) {
                    break;
                }
                View unfilteredChildAt = recyclerView.mChildHelper.getUnfilteredChildAt(i6);
                RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(unfilteredChildAt);
                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i5 = childViewHolderInt.mPosition) >= i && i5 < i3) {
                    childViewHolderInt.addFlags(2);
                    childViewHolderInt.addChangePayload(obj);
                    ((RecyclerView.LayoutParams) unfilteredChildAt.getLayoutParams()).mInsetsDirty = true;
                }
                i6++;
            }
            RecyclerView.Recycler recycler = recyclerView.mRecycler;
            int size = recycler.mCachedViews.size();
            while (true) {
                size--;
                if (size >= 0) {
                    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) recycler.mCachedViews.get(size);
                    if (viewHolder != null && (i4 = viewHolder.mPosition) >= i && i4 < i3) {
                        viewHolder.addFlags(2);
                        recycler.recycleCachedViewAt(size);
                    }
                } else {
                    ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mItemsChanged = true;
                    return;
                }
            }
        }

        public final boolean moveCursor(int i) {
            CellsContentManager.Cursor cursor;
            try {
                CellsContentManager cellsContentManager = ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).cellsContentManager;
                ContentHelper contentHelper = cellsContentManager.getCurrentDisplayInfoWrapper().contentHelper;
                int wholeContentIndex = contentHelper.toWholeContentIndex(i);
                int i2 = -1;
                if (wholeContentIndex != -1) {
                    Range range = cellsContentManager.actionRange;
                    Integer valueOf = Integer.valueOf(wholeContentIndex);
                    if (range.contains((Range) valueOf)) {
                        cursor = new CellsContentManager.Cursor(-1, 3);
                    } else if (cellsContentManager.holdingsRange.contains((Range) valueOf)) {
                        cursor = new CellsContentManager.Cursor(wholeContentIndex - ((Integer) cellsContentManager.holdingsRange.getLower()).intValue(), 2);
                    } else if (((Integer) cellsContentManager.holdingsRange.getLower()).intValue() != -1) {
                        if (wholeContentIndex > ((Integer) cellsContentManager.holdingsRange.getLower()).intValue()) {
                            i2 = (((Integer) cellsContentManager.holdingsRange.getUpper()).intValue() - ((Integer) cellsContentManager.holdingsRange.getLower()).intValue()) + 1;
                        }
                        cursor = new CellsContentManager.Cursor(i2, 2);
                    } else {
                        for (Range range2 : cellsContentManager.onScreenRange) {
                            if (range2.contains((Range) Integer.valueOf(wholeContentIndex))) {
                                cursor = new CellsContentManager.Cursor(i2 + (contentHelper.transferByteIndexToTextIndex(wholeContentIndex) - contentHelper.transferByteIndexToTextIndex(((Integer) range2.getLower()).intValue())) + 1, 1);
                            } else if (wholeContentIndex > ((Integer) range2.getUpper()).intValue()) {
                                i2 += (contentHelper.transferByteIndexToTextIndex(((Integer) range2.getUpper()).intValue()) - contentHelper.transferByteIndexToTextIndex(((Integer) range2.getLower()).intValue())) + 1;
                            }
                        }
                        throw new ExecutionException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Can't move cursor to "), null);
                    }
                    int i3 = cursor.type$ar$edu;
                    if (i3 == 3) {
                        ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().commitHoldingsAndPerformEditorAction$ar$ds();
                        return true;
                    }
                    if (i3 == 2) {
                        return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveHoldingsCursor(cursor.position);
                    }
                    return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveTextFieldCursor(cursor.position);
                }
                throw new ExecutionException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Can't move cursor to "), null);
            } catch (ExecutionException e) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.w("BdController", "Move cursor failed.", e);
                return false;
            }
        }

        public final boolean moveCursorBackward() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveCursorBackward();
        }

        public final boolean moveCursorBackwardByLine() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveCursorBackwardByLine();
        }

        public final void moveCursorBackwardByWord$ar$ds() {
            ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveCursorBackwardByWord$ar$ds$69f8338f_0();
        }

        public final boolean moveCursorForward() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveCursorForward();
        }

        public final boolean moveCursorForwardByLine() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveCursorForwardByLine();
        }

        public final boolean moveCursorForwardByWord() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveCursorForwardByWord();
        }

        public final boolean moveToBeginning() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveCursorToBeginning();
        }

        public final boolean moveToEnd() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().moveCursorToEnd();
        }

        public final void offsetPositionsForAdd(int i, int i2) {
            RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            for (int i3 = 0; i3 < unfilteredChildCount; i3++) {
                RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i3));
                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i) {
                    childViewHolderInt.offsetPosition(i2, false);
                    recyclerView.mState.mStructureChanged = true;
                }
            }
            RecyclerView.Recycler recycler = recyclerView.mRecycler;
            int size = recycler.mCachedViews.size();
            for (int i4 = 0; i4 < size; i4++) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) recycler.mCachedViews.get(i4);
                if (viewHolder != null && viewHolder.mPosition >= i) {
                    viewHolder.offsetPosition(i2, false);
                }
            }
            recyclerView.requestLayout();
            ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mItemsAddedOrRemoved = true;
        }

        public final void offsetPositionsForMove(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i9 = 0;
            while (true) {
                int i10 = -1;
                if (i9 >= unfilteredChildCount) {
                    break;
                }
                RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i9));
                if (childViewHolderInt != null) {
                    if (i < i2) {
                        i7 = i;
                    } else {
                        i7 = i2;
                    }
                    int i11 = childViewHolderInt.mPosition;
                    if (i11 >= i7) {
                        if (i < i2) {
                            i8 = i2;
                        } else {
                            i8 = i;
                        }
                        if (i11 <= i8) {
                            if (i11 == i) {
                                childViewHolderInt.offsetPosition(i2 - i, false);
                            } else {
                                if (i >= i2) {
                                    i10 = 1;
                                }
                                childViewHolderInt.offsetPosition(i10, false);
                            }
                            recyclerView.mState.mStructureChanged = true;
                        }
                    }
                }
                i9++;
            }
            RecyclerView.Recycler recycler = recyclerView.mRecycler;
            if (i < i2) {
                i3 = i2;
            } else {
                i3 = i;
            }
            if (i < i2) {
                i4 = i;
            } else {
                i4 = i2;
            }
            int size = recycler.mCachedViews.size();
            for (int i12 = 0; i12 < size; i12++) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) recycler.mCachedViews.get(i12);
                if (viewHolder != null && (i5 = viewHolder.mPosition) >= i4 && i5 <= i3) {
                    if (i5 == i) {
                        viewHolder.offsetPosition(i2 - i, false);
                    } else {
                        if (i < i2) {
                            i6 = -1;
                        } else {
                            i6 = 1;
                        }
                        viewHolder.offsetPosition(i6, false);
                    }
                }
            }
            recyclerView.requestLayout();
            ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mItemsAddedOrRemoved = true;
        }

        public final void offsetPositionsForRemovingInvisible(int i, int i2) {
            ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).offsetPositionRecordsForRemove(i, i2, true);
            RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            recyclerView.mItemsAddedOrRemoved = true;
            recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i2;
        }

        public final void onAnimationEnd$ar$ds$486c95f9_0(DynamicAnimation dynamicAnimation, float f, float f2) {
            ((Transition.SeekController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).m36x8255e67e(f);
        }

        public final void onBackgroundStateChanged(boolean z) {
            Object obj = this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            Boolean valueOf = Boolean.valueOf(z);
            Handler handler = ((GoogleApiManager) obj).handler;
            handler.sendMessage(handler.obtainMessage(1, valueOf));
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.common.api.internal.OnConnectionFailedListener, java.lang.Object] */
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            this.FloatingActionButton$ShadowDelegateImpl$ar$this$0.onConnectionFailed(connectionResult);
        }

        public final void onCreateMenu(Menu menu, MenuInflater menuInflater) {
            ((FragmentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).dispatchCreateOptionsMenu(menu, menuInflater);
        }

        public final void onDisplayContentChanged() {
            AutoScrollManager autoScrollManager = (AutoScrollManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            autoScrollManager.handler.removeCallbacksAndMessages(null);
            autoScrollManager.handler.postDelayed(autoScrollManager.runnable, autoScrollManager.getDuration());
        }

        /* JADX WARN: Type inference failed for: r3v2, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        public final void onFailure(String str) {
            LogUtils.w("GeminiEndpoint", "ErrorResponse processing Gemini request:%s", str);
            this.FloatingActionButton$ShadowDelegateImpl$ar$this$0.onResponse$ar$edu(3, null);
        }

        public final void onFocusCleared() {
            if (isBrailleKeyboardActivated()) {
                ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().commitHoldings();
            }
        }

        public final void onLeftButtonClicked() {
            ((A11yMenuViewPager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).goToPage(r0.viewPager.mCurItem - 1);
            ((A11yMenuViewPager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).updateFooterState();
        }

        public final void onLeftHiddenState(View view) {
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).setChildImportantForAccessibilityInternal$ar$ds(childViewHolderInt, childViewHolderInt.mWasImportantForAccessibilityBeforeHidden);
                childViewHolderInt.mWasImportantForAccessibilityBeforeHidden = 0;
            }
        }

        public final void onMenuClosed(Menu menu) {
            ((FragmentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).dispatchOptionsMenuClosed(menu);
        }

        public final boolean onMenuItemSelected(MenuItem menuItem) {
            return ((FragmentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).dispatchOptionsItemSelected(menuItem);
        }

        public final void onOffsetChanged$ar$ds(int i) {
            int i2;
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            collapsingToolbarLayout.currentOffset = i;
            WindowInsetsCompat windowInsetsCompat = collapsingToolbarLayout.lastInsets;
            if (windowInsetsCompat != null) {
                i2 = windowInsetsCompat.getSystemWindowInsetTop();
            } else {
                i2 = 0;
            }
            int childCount = ((CollapsingToolbarLayout) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = ((CollapsingToolbarLayout) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getChildAt(i3);
                CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) childAt.getLayoutParams();
                ViewOffsetHelper viewOffsetHelper = CollapsingToolbarLayout.getViewOffsetHelper(childAt);
                int i4 = layoutParams.collapseMode;
                if (i4 != 1) {
                    if (i4 == 2) {
                        viewOffsetHelper.setTopAndBottomOffset(Math.round((-i) * layoutParams.parallaxMult));
                    }
                } else {
                    viewOffsetHelper.setTopAndBottomOffset(NotificationCompatBuilder$Api26Impl.clamp(-i, 0, ((CollapsingToolbarLayout) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getMaxOffsetForPinChild(childAt)));
                }
            }
            ((CollapsingToolbarLayout) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).updateScrimVisibility();
            CollapsingToolbarLayout collapsingToolbarLayout2 = (CollapsingToolbarLayout) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            if (collapsingToolbarLayout2.statusBarScrim != null && i2 > 0) {
                collapsingToolbarLayout2.postInvalidateOnAnimation();
            }
            Object obj = this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            int height = ((CollapsingToolbarLayout) obj).getHeight();
            int minimumHeight = height - ((CollapsingToolbarLayout) obj).getMinimumHeight();
            int scrimVisibleHeightTrigger = height - ((CollapsingToolbarLayout) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getScrimVisibleHeightTrigger();
            Object obj2 = this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            int i5 = minimumHeight - i2;
            float f = scrimVisibleHeightTrigger;
            float f2 = i5;
            float min = Math.min(1.0f, f / f2);
            CollapsingTextHelper collapsingTextHelper = ((CollapsingToolbarLayout) obj2).collapsingTextHelper;
            collapsingTextHelper.fadeModeStartFraction = min;
            collapsingTextHelper.fadeModeThresholdFraction = collapsingTextHelper.calculateFadeModeThresholdFraction();
            CollapsingToolbarLayout collapsingToolbarLayout3 = (CollapsingToolbarLayout) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            int i6 = collapsingToolbarLayout3.currentOffset + i5;
            CollapsingTextHelper collapsingTextHelper2 = collapsingToolbarLayout3.collapsingTextHelper;
            collapsingTextHelper2.currentOffsetY = i6;
            collapsingTextHelper2.setExpansionFraction(Math.abs(i) / f2);
        }

        public final void onPacketArrived(byte[] bArr) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.v("BrailleDisplayManager", "onPacketArrived " + bArr.length + " bytes");
            Displayer displayer = ((BrailleDisplayManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).displayer;
            if (displayer != null) {
                displayer.consumePacketFromDevice(bArr);
            }
        }

        public final void onPerform(BrailleInputPlaneResult brailleInputPlaneResult) {
            ((BrailleInputView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).processResult(brailleInputPlaneResult);
        }

        public final void onPrepareMenu(Menu menu) {
            ((FragmentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).dispatchPrepareOptionsMenu(menu);
        }

        public final void onRead() {
            if (((BrailleDisplayManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).canSendPackets()) {
                ((BrailleDisplayManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).displayer.readCommand();
            }
        }

        public final void onRightButtonClicked() {
            A11yMenuViewPager a11yMenuViewPager = (A11yMenuViewPager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            a11yMenuViewPager.goToPage(a11yMenuViewPager.viewPager.mCurItem + 1);
            ((A11yMenuViewPager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).updateFooterState();
        }

        public final void onSurveyFinished() {
            SurveyControllerImpl.markSurveyFinished();
            long epochMilli = SpannableUtils$NonCopyableTextSpan.instant$ar$ds().toEpochMilli();
            SurveyControllerImpl surveyControllerImpl = (SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            surveyControllerImpl.minValidTriggerTimeMs = epochMilli;
            PresentSurveyRequest.SurveyEventListener surveyEventListener = surveyControllerImpl.surveyEventListener;
            if (surveyEventListener != null) {
                surveyControllerImpl.surveyData.getSurveyMetadata();
                surveyEventListener.onSurveyClosed$ar$ds();
            }
        }

        public final void onSurveyRunning() {
            SurveyControllerImpl.markSurveyRunning();
            SurveyControllerImpl surveyControllerImpl = (SurveyControllerImpl) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            PresentSurveyRequest.SurveyEventListener surveyEventListener = surveyControllerImpl.surveyEventListener;
            if (surveyEventListener != null) {
                surveyEventListener.onSurveyPrompted(surveyControllerImpl.surveyData.getSurveyMetadata());
            }
        }

        public final void onSwitchToNextIme() {
            ((BrailleIme) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).switchToNextInputMethod();
        }

        public final void onTalkBackResumed() {
            _BOUNDARY.d("BrailleIme", "onTalkBackResumed");
            if (((BrailleIme) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isInputViewShown()) {
                ((BrailleIme) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).dismissDialogs();
                ((BrailleIme) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).activateIfNeeded();
            }
        }

        public final void onTalkBackSuspended() {
            _BOUNDARY.d("BrailleIme", "onTalkBackSuspended");
            if (((BrailleIme) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isInputViewShown()) {
                BrailleIme.talkBackForBrailleIme.getServiceStatus$ar$edu();
            }
        }

        public final void onTimedMessageCleared() {
            if (((CellsContentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).timedMessageDisplayInfoWrapper.hasDisplayInfo()) {
                CellsContentManager cellsContentManager = (CellsContentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                cellsContentManager.timedMessageDisplayInfoWrapper.displayInfo = null;
                cellsContentManager.refresh();
                ((CellsContentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).cellOnDisplayContentChanged();
            }
        }

        public final void onTimedMessageDisplayed(CellsContent cellsContent) {
            ((CellsContentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).timedMessageDisplayInfoWrapper.renewDisplayInfo$ar$ds(cellsContent.text, cellsContent.panStrategy);
            ((CellsContentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).refresh();
            ((CellsContentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).cellOnDisplayContentChanged();
        }

        public final void onTwoStepCalibrationFailed() {
            BrailleInputView brailleInputView = (BrailleInputView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            brailleInputView.callback.onCalibrationFailed$ar$edu(brailleInputView.calibrationType$ar$edu);
            ((BrailleInputView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).invalidate();
        }

        public final void onUpdateReceived$ar$ds() {
            StatsStorage statsStorage = FlagStore.SHARED_REGISTRY$ar$class_merging;
            ((FlagStore) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).handleFlagUpdates();
        }

        public final boolean pasteSelectedText() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().pasteSelectedText();
        }

        public final boolean performAction$ar$edu$3bc9316c_0(int i, Object... objArr) {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(i, objArr);
        }

        public final boolean performEnterKeyAction() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().commitHoldingsAndPerformEnterKeyAction();
        }

        public final void processAppeared$ar$class_merging(RecyclerView.ViewHolder viewHolder, NestedScrollingParentHelper nestedScrollingParentHelper, NestedScrollingParentHelper nestedScrollingParentHelper2) {
            int i;
            int i2;
            viewHolder.setIsRecyclable(false);
            RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            RecyclerView.ItemAnimator itemAnimator = recyclerView.mItemAnimator;
            if (nestedScrollingParentHelper != null && ((i = nestedScrollingParentHelper.mNestedScrollAxesNonTouch) != (i2 = nestedScrollingParentHelper2.mNestedScrollAxesNonTouch) || nestedScrollingParentHelper.mNestedScrollAxesTouch != nestedScrollingParentHelper2.mNestedScrollAxesTouch)) {
                if (!((SimpleItemAnimator) itemAnimator).animateMove(viewHolder, i, nestedScrollingParentHelper.mNestedScrollAxesTouch, i2, nestedScrollingParentHelper2.mNestedScrollAxesTouch)) {
                    return;
                }
            } else {
                ((SimpleItemAnimator) itemAnimator).animateAdd$ar$ds(viewHolder);
            }
            recyclerView.postAnimationRunner();
        }

        public final void processDisappeared$ar$class_merging(RecyclerView.ViewHolder viewHolder, NestedScrollingParentHelper nestedScrollingParentHelper, NestedScrollingParentHelper nestedScrollingParentHelper2) {
            int i;
            int i2;
            ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mRecycler.unscrapView(viewHolder);
            RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            recyclerView.addAnimatingView(viewHolder);
            viewHolder.setIsRecyclable(false);
            RecyclerView.ItemAnimator itemAnimator = recyclerView.mItemAnimator;
            int i3 = nestedScrollingParentHelper.mNestedScrollAxesNonTouch;
            int i4 = nestedScrollingParentHelper.mNestedScrollAxesTouch;
            View view = viewHolder.itemView;
            if (nestedScrollingParentHelper2 == null) {
                i = view.getLeft();
            } else {
                i = nestedScrollingParentHelper2.mNestedScrollAxesNonTouch;
            }
            int i5 = i;
            if (nestedScrollingParentHelper2 == null) {
                i2 = view.getTop();
            } else {
                i2 = nestedScrollingParentHelper2.mNestedScrollAxesTouch;
            }
            int i6 = i2;
            if (!viewHolder.isRemoved() && (i3 != i5 || i4 != i6)) {
                view.layout(i5, i6, view.getWidth() + i5, view.getHeight() + i6);
                if (!((SimpleItemAnimator) itemAnimator).animateMove(viewHolder, i3, i4, i5, i6)) {
                    return;
                }
            } else {
                ((SimpleItemAnimator) itemAnimator).animateRemove$ar$ds(viewHolder);
            }
            recyclerView.postAnimationRunner();
        }

        public final void pulse() {
            CellsContentManager cellsContentManager = (CellsContentManager) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            cellsContentManager.overlaysOn = !cellsContentManager.overlaysOn;
            cellsContentManager.refresh();
        }

        public final void removeViewAt(int i) {
            View childAt = ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getChildAt(i);
            if (childAt != null) {
                RecyclerView.dispatchChildDetached$ar$ds(childAt);
                childAt.clearAnimation();
            }
            ((RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).removeViewAt(i);
        }

        public final void requestFlag$ar$ds(boolean z) {
            ((TalkBackService) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).requestServiceFlag(2048, z);
        }

        public final boolean selectAllText() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectAllText();
        }

        public final boolean selectCurrentToEnd() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectCurrentToEnd();
        }

        public final boolean selectCurrentToStart() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectCurrentToStart();
        }

        public final boolean selectNextCharacter() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectNextCharacter();
        }

        public final boolean selectNextLine() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectNextLine();
        }

        public final boolean selectNextWord() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectNextWord();
        }

        public final boolean selectPreviousCharacter() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectPreviousCharacter();
        }

        public final boolean selectPreviousLine() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectPreviousLine();
        }

        public final boolean selectPreviousWord() {
            return ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().selectPreviousWord();
        }

        public final void sendBrailleDots$ar$ds(int i) {
            BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).context);
            int i2 = brailleDisplayAnalytics.typedCount + 1;
            brailleDisplayAnalytics.typedCount = i2;
            brailleDisplayAnalytics.typedCount = i2 % 20;
            for (int i3 = 0; i3 < i2 / 20; i3++) {
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
                builder.copyOnWrite();
                BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = (BraillebackLogProto$BraillebackExtension) builder.instance;
                braillebackLogProto$BraillebackExtension.bitField0_ |= 8;
                braillebackLogProto$BraillebackExtension.inputRecord_ = 20;
                brailleDisplayAnalytics.sendLogs((BraillebackLogProto$BraillebackExtension) builder.build());
            }
            int i4 = i & PrivateKeyType.INVALID;
            BdController bdController = (BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            bdController.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitOnFailure$ar$ds(bdController.getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().sendBrailleDots(new BrailleCharacter((byte) i4)), FeedbackManager$Type.COMMAND_FAILED);
        }

        public final void setBackgroundDrawable(Drawable drawable) {
            if (drawable != null) {
                FloatingActionButton.super.setBackgroundDrawable(drawable);
            }
        }

        public final boolean switchToNextInputMethod() {
            String str;
            if (((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).isSomeBrailleCommandUnavailableDialogShowing()) {
                ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).someBrailleCommandUnavailableDialog.dismiss();
            }
            if (isBrailleKeyboardActivated()) {
                return ((BrailleIme) ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).switchToNextInputMethod();
            }
            if (isOnscreenKeyboardActive()) {
                Object obj = this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
                    WorkQueue workQueue = ((BdController) obj).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    String string = Settings.Secure.getString(((Context) workQueue.WorkQueue$ar$buffer).getContentResolver(), "default_input_method");
                    List<InputMethodInfo> enabledInputMethodList = ((InputMethodManager) ((TalkBackService) workQueue.WorkQueue$ar$buffer).getSystemService("input_method")).getEnabledInputMethodList();
                    Iterator<InputMethodInfo> it = enabledInputMethodList.iterator();
                    boolean z = false;
                    while (true) {
                        if (it.hasNext()) {
                            InputMethodInfo next = it.next();
                            if (z) {
                                str = next.getId();
                                break;
                            }
                            z = next.getPackageName().equals(ComponentName.unflattenFromString(string).getPackageName());
                        } else if (z && TextUtils.isEmpty(null)) {
                            str = enabledInputMethodList.get(0).getId();
                        } else {
                            str = null;
                        }
                    }
                    if (!TextUtils.isEmpty(str) && !TextUtils.equals(string, str)) {
                        return PathUtils$$ExternalSyntheticApiModelOutline2.m(((TalkBackService) workQueue.WorkQueue$ar$buffer).getSoftKeyboardController(), str);
                    }
                }
            }
            return false;
        }

        public final void triggerUpdateDisplay() {
            ((BdController) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().updateResultForDisplay();
        }

        public final void unused(RecyclerView.ViewHolder viewHolder) {
            RecyclerView recyclerView = (RecyclerView) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            recyclerView.mLayout.removeAndRecycleView(viewHolder.itemView, recyclerView.mRecycler);
        }

        public final ListenableFuture updateDataAsync(AsyncFunction asyncFunction, Executor executor) {
            return JankMetricService.toVoid(AbstractTransformFuture.create(ContextDataProvider.nonCancellationPropagating(((XDataStore) this.FloatingActionButton$ShadowDelegateImpl$ar$this$0).variantInit.get()), TracePropagation.propagateAsyncFunction(new PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2(this, asyncFunction, executor, 4)), DirectExecutor.INSTANCE));
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public ShadowDelegateImpl(ActionMenuPresenter actionMenuPresenter) {
            this();
            this.FloatingActionButton$ShadowDelegateImpl$ar$this$0 = actionMenuPresenter;
        }

        public ShadowDelegateImpl(Object obj) {
            this.FloatingActionButton$ShadowDelegateImpl$ar$this$0 = obj;
        }

        public final void onSurveyRunning(SurveyInternalEvent surveyInternalEvent) {
            if (!surveyInternalEvent.surveyStyle.equals(SurveyStyle.EMBEDDED)) {
                SurveyControllerImpl.markSurveyRunning();
            }
            Object obj = this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            String str = surveyInternalEvent.sessionId;
            String str2 = surveyInternalEvent.sessionId;
            SurveyControllerImpl surveyControllerImpl = (SurveyControllerImpl) obj;
            PresentSurveyRequest.SurveyEventListener surveyEventListener = surveyControllerImpl.surveyDataStore.getSurveyEventListener(str);
            SurveyDataImpl surveyData = surveyControllerImpl.surveyDataStore.getSurveyData(str2);
            if (surveyEventListener == null || surveyData == null) {
                return;
            }
            surveyEventListener.onSurveyPrompted(surveyData.getSurveyMetadata());
        }

        public /* synthetic */ ShadowDelegateImpl(Object obj, byte[] bArr) {
            this.FloatingActionButton$ShadowDelegateImpl$ar$this$0 = obj;
        }

        public final void onSurveyFinished(SurveyInternalEvent surveyInternalEvent) {
            if (!surveyInternalEvent.surveyStyle.equals(SurveyStyle.EMBEDDED)) {
                SurveyControllerImpl.markSurveyFinished();
            }
            Object obj = this.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            String str = surveyInternalEvent.sessionId;
            String str2 = surveyInternalEvent.sessionId;
            SurveyStyle surveyStyle = surveyInternalEvent.surveyStyle;
            SurveyControllerImpl surveyControllerImpl = (SurveyControllerImpl) obj;
            SurveyDataStore surveyDataStore = surveyControllerImpl.surveyDataStore;
            PresentSurveyRequest.SurveyEventListener surveyEventListener = surveyControllerImpl.surveyDataStore.getSurveyEventListener(str);
            SurveyDataImpl surveyData = surveyDataStore.getSurveyData(str2);
            if (surveyStyle != SurveyStyle.EMBEDDED) {
                surveyControllerImpl.minValidTriggerTimeMs = SpannableUtils$NonCopyableTextSpan.instant$ar$ds().toEpochMilli();
            }
            if (surveyEventListener != null && surveyData != null) {
                surveyData.getSurveyMetadata();
                surveyEventListener.onSurveyClosed$ar$ds();
            }
            SurveyDataStore surveyDataStore2 = surveyControllerImpl.surveyDataStore;
            surveyDataStore2.sessionIdToEventListenerMap.remove(surveyInternalEvent.sessionId);
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private final FloatingActionButtonImpl getImpl() {
        if (this.impl == null) {
            this.impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl(this));
        }
        return this.impl;
    }

    private final void onApplySupportImageTint() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        drawable.clearColorFilter();
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().onDrawableStateChanged(getDrawableState());
    }

    @Override // android.view.View
    public final ColorStateList getBackgroundTintList() {
        return this.backgroundTint;
    }

    @Override // android.view.View
    public final PorterDuff.Mode getBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public final CoordinatorLayout.Behavior getBehavior() {
        return new Behavior();
    }

    public final void getMeasuredContentRect(Rect rect) {
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        rect.left += this.shadowPadding.left;
        rect.top += this.shadowPadding.top;
        rect.right -= this.shadowPadding.right;
        rect.bottom -= this.shadowPadding.bottom;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getSizeDimension() {
        return getSizeDimension(this.size);
    }

    final void hide$ar$ds() {
        AnimatorSet createDefaultAnimator;
        final FloatingActionButtonImpl impl = getImpl();
        if (impl.view.getVisibility() == 0) {
            if (impl.animState == 1) {
                return;
            }
        } else if (impl.animState != 2) {
            return;
        }
        Animator animator = impl.currentAnimator;
        if (animator != null) {
            animator.cancel();
        }
        if (impl.shouldAnimateVisibilityChange()) {
            MotionSpec motionSpec = impl.hideMotionSpec;
            if (motionSpec != null) {
                createDefaultAnimator = impl.createAnimator(motionSpec, 0.0f, 0.0f, 0.0f);
            } else {
                createDefaultAnimator = impl.createDefaultAnimator(0.0f, 0.4f, 0.4f, FloatingActionButtonImpl.HIDE_ANIM_DURATION_ATTR, FloatingActionButtonImpl.HIDE_ANIM_EASING_ATTR);
            }
            final FloatingActionButtonImpl.InternalVisibilityChangedListener internalVisibilityChangedListener = null;
            createDefaultAnimator.addListener(new AnimatorListenerAdapter(internalVisibilityChangedListener) { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.1
                private boolean cancelled;
                final /* synthetic */ InternalVisibilityChangedListener val$listener = null;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator2) {
                    this.cancelled = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator2) {
                    FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
                    floatingActionButtonImpl.animState = 0;
                    floatingActionButtonImpl.currentAnimator = null;
                    if (!this.cancelled) {
                        floatingActionButtonImpl.view.internalSetVisibility(4, false);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator2) {
                    FloatingActionButtonImpl.this.view.internalSetVisibility(0, false);
                    FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
                    floatingActionButtonImpl.animState = 1;
                    floatingActionButtonImpl.currentAnimator = animator2;
                    this.cancelled = false;
                }
            });
            createDefaultAnimator.start();
            return;
        }
        impl.view.internalSetVisibility(4, false);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().jumpDrawableToCurrentState();
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        FloatingActionButtonImpl impl = getImpl();
        MaterialShapeDrawable materialShapeDrawable = impl.shapeDrawable;
        if (materialShapeDrawable != null) {
            EdgeTreatment.setParentAbsoluteElevation(impl.view, materialShapeDrawable);
        }
        if (impl.requirePreDrawListener()) {
            ViewTreeObserver viewTreeObserver = impl.view.getViewTreeObserver();
            if (impl.preDrawListener == null) {
                impl.preDrawListener = new FloatingActionButtonImpl.AnonymousClass6(impl, 0);
            }
            viewTreeObserver.addOnPreDrawListener(impl.preDrawListener);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FloatingActionButtonImpl impl = getImpl();
        ViewTreeObserver viewTreeObserver = impl.view.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = impl.preDrawListener;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            impl.preDrawListener = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void onMeasure(int i, int i2) {
        int sizeDimension = getSizeDimension();
        this.imagePadding = (sizeDimension - this.maxImageSize) / 2;
        getImpl().updatePadding();
        int min = Math.min(View.resolveSize(sizeDimension, i), View.resolveSize(sizeDimension, i2));
        setMeasuredDimension(this.shadowPadding.left + min + this.shadowPadding.right, min + this.shadowPadding.top + this.shadowPadding.bottom);
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.mSuperState);
        SnackbarManager.SnackbarRecord snackbarRecord = this.expandableWidgetHelper$ar$class_merging;
        Bundle bundle = (Bundle) extendableSavedState.extendableStates.get("expandableWidgetHelper");
        bundle.getClass();
        snackbarRecord.paused = bundle.getBoolean("expanded", false);
        snackbarRecord.duration = bundle.getInt("expandedComponentIdHint", 0);
        if (snackbarRecord.paused) {
            ViewParent parent = ((View) snackbarRecord.SnackbarManager$SnackbarRecord$ar$callback).getParent();
            if (parent instanceof CoordinatorLayout) {
                ((CoordinatorLayout) parent).dispatchDependentViewsChanged((View) snackbarRecord.SnackbarManager$SnackbarRecord$ar$callback);
            }
        }
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (onSaveInstanceState == null) {
            onSaveInstanceState = new Bundle();
        }
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(onSaveInstanceState);
        SimpleArrayMap simpleArrayMap = extendableSavedState.extendableStates;
        SnackbarManager.SnackbarRecord snackbarRecord = this.expandableWidgetHelper$ar$class_merging;
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", snackbarRecord.paused);
        bundle.putInt("expandedComponentIdHint", snackbarRecord.duration);
        simpleArrayMap.put("expandableWidgetHelper", bundle);
        return extendableSavedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            Rect rect = this.touchArea;
            getMeasuredContentRect(rect);
            int i = -this.impl.getTouchTargetPadding();
            rect.inset(i, i);
            if (!this.touchArea.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.backgroundTint != colorStateList) {
            this.backgroundTint = colorStateList;
            FloatingActionButtonImpl impl = getImpl();
            MaterialShapeDrawable materialShapeDrawable = impl.shapeDrawable;
            if (materialShapeDrawable != null) {
                materialShapeDrawable.setTintList(colorStateList);
            }
            BorderDrawable borderDrawable = impl.borderDrawable;
            if (borderDrawable != null) {
                borderDrawable.setBorderTint(colorStateList);
            }
        }
    }

    @Override // android.view.View
    public final void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.backgroundTintMode != mode) {
            this.backgroundTintMode = mode;
            MaterialShapeDrawable materialShapeDrawable = getImpl().shapeDrawable;
            if (materialShapeDrawable != null) {
                materialShapeDrawable.setTintMode(mode);
            }
        }
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        super.setElevation(f);
        getImpl().updateShapeElevation(f);
    }

    @Override // android.widget.ImageView
    public final void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            getImpl().updateImageMatrixScale();
        }
    }

    @Override // android.widget.ImageView
    public final void setImageResource(int i) {
        this.imageHelper$ar$class_merging$ar$class_merging.setImageResource(i);
        onApplySupportImageTint();
    }

    @Override // android.view.View
    public final void setScaleX(float f) {
        super.setScaleX(f);
        getImpl();
    }

    @Override // android.view.View
    public final void setScaleY(float f) {
        super.setScaleY(f);
        getImpl();
    }

    @Override // com.google.android.material.shape.Shapeable
    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        getImpl().setShapeAppearance(shapeAppearanceModel);
    }

    @Override // android.view.View
    public final void setTranslationX(float f) {
        super.setTranslationX(f);
        getImpl();
    }

    @Override // android.view.View
    public final void setTranslationY(float f) {
        super.setTranslationY(f);
        getImpl();
    }

    @Override // android.view.View
    public final void setTranslationZ(float f) {
        super.setTranslationZ(f);
        getImpl();
    }

    final void show$ar$ds() {
        AnimatorSet createDefaultAnimator;
        final FloatingActionButtonImpl impl = getImpl();
        if (impl.view.getVisibility() != 0) {
            if (impl.animState == 2) {
                return;
            }
        } else if (impl.animState != 1) {
            return;
        }
        Animator animator = impl.currentAnimator;
        if (animator != null) {
            animator.cancel();
        }
        MotionSpec motionSpec = impl.showMotionSpec;
        if (impl.shouldAnimateVisibilityChange()) {
            if (impl.view.getVisibility() != 0) {
                float f = 0.0f;
                impl.view.setAlpha(0.0f);
                FloatingActionButton floatingActionButton = impl.view;
                if (motionSpec == null) {
                    f = 0.4f;
                }
                floatingActionButton.setScaleY(f);
                impl.view.setScaleX(f);
                impl.setImageMatrixScale(f);
            }
            MotionSpec motionSpec2 = impl.showMotionSpec;
            if (motionSpec2 != null) {
                createDefaultAnimator = impl.createAnimator(motionSpec2, 1.0f, 1.0f, 1.0f);
            } else {
                createDefaultAnimator = impl.createDefaultAnimator(1.0f, 1.0f, 1.0f, FloatingActionButtonImpl.SHOW_ANIM_DURATION_ATTR, FloatingActionButtonImpl.SHOW_ANIM_EASING_ATTR);
            }
            final FloatingActionButtonImpl.InternalVisibilityChangedListener internalVisibilityChangedListener = null;
            createDefaultAnimator.addListener(new AnimatorListenerAdapter(internalVisibilityChangedListener) { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.2
                final /* synthetic */ InternalVisibilityChangedListener val$listener = null;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator2) {
                    FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
                    floatingActionButtonImpl.animState = 0;
                    floatingActionButtonImpl.currentAnimator = null;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator2) {
                    FloatingActionButtonImpl.this.view.internalSetVisibility(0, false);
                    FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
                    floatingActionButtonImpl.animState = 2;
                    floatingActionButtonImpl.currentAnimator = animator2;
                }
            });
            createDefaultAnimator.start();
            return;
        }
        impl.view.internalSetVisibility(0, false);
        impl.view.setAlpha(1.0f);
        impl.view.setScaleY(1.0f);
        impl.view.setScaleX(1.0f);
        impl.setImageMatrixScale(1.0f);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.floatingActionButtonStyle);
    }

    private final int getSizeDimension(int i) {
        int i2 = this.customSize;
        if (i2 != 0) {
            return i2;
        }
        Resources resources = getResources();
        if (i != -1) {
            if (i != 1) {
                return resources.getDimensionPixelSize(R.dimen.design_fab_size_normal);
            }
            return resources.getDimensionPixelSize(R.dimen.design_fab_size_mini);
        }
        if (Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470) {
            return getSizeDimension(1);
        }
        return getSizeDimension(0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, R.style.Widget_Design_FloatingActionButton), attributeSet, i);
        this.shadowPadding = new Rect();
        this.touchArea = new Rect();
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.FloatingActionButton, i, R.style.Widget_Design_FloatingActionButton, new int[0]);
        this.backgroundTint = DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes, 1);
        this.backgroundTintMode = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(obtainStyledAttributes.getInt(2, -1), null);
        this.rippleColor = DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes, 12);
        this.size = obtainStyledAttributes.getInt(7, -1);
        this.customSize = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        this.borderWidth = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        float dimension = obtainStyledAttributes.getDimension(4, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(9, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(11, 0.0f);
        this.compatPadding = obtainStyledAttributes.getBoolean(16, false);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.mtrl_fab_min_touch_target);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(10, 0);
        this.maxImageSize = dimensionPixelSize2;
        FloatingActionButtonImpl impl = getImpl();
        if (impl.maxImageSize != dimensionPixelSize2) {
            impl.maxImageSize = dimensionPixelSize2;
            impl.updateImageMatrixScale();
        }
        MotionSpec createFromAttribute = MotionSpec.createFromAttribute(context2, obtainStyledAttributes, 15);
        MotionSpec createFromAttribute2 = MotionSpec.createFromAttribute(context2, obtainStyledAttributes, 8);
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel(ShapeAppearanceModel.builder(context2, attributeSet, i, R.style.Widget_Design_FloatingActionButton, ShapeAppearanceModel.PILL));
        boolean z = obtainStyledAttributes.getBoolean(5, false);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        SharedResourceHolder.Instance instance = new SharedResourceHolder.Instance((ImageView) this);
        this.imageHelper$ar$class_merging$ar$class_merging = instance;
        instance.loadFromAttributes(attributeSet, i);
        this.expandableWidgetHelper$ar$class_merging = new SnackbarManager.SnackbarRecord(this);
        getImpl().setShapeAppearance(shapeAppearanceModel);
        getImpl().initializeBackgroundDrawable(this.backgroundTint, this.backgroundTintMode, this.rippleColor, this.borderWidth);
        getImpl().minTouchTargetSize = dimensionPixelSize;
        FloatingActionButtonImpl impl2 = getImpl();
        if (impl2.elevation != dimension) {
            impl2.elevation = dimension;
            impl2.onElevationsChanged(dimension, impl2.hoveredFocusedTranslationZ, impl2.pressedTranslationZ);
        }
        FloatingActionButtonImpl impl3 = getImpl();
        if (impl3.hoveredFocusedTranslationZ != dimension2) {
            impl3.hoveredFocusedTranslationZ = dimension2;
            impl3.onElevationsChanged(impl3.elevation, dimension2, impl3.pressedTranslationZ);
        }
        FloatingActionButtonImpl impl4 = getImpl();
        if (impl4.pressedTranslationZ != dimension3) {
            impl4.pressedTranslationZ = dimension3;
            impl4.onElevationsChanged(impl4.elevation, impl4.hoveredFocusedTranslationZ, dimension3);
        }
        getImpl().showMotionSpec = createFromAttribute;
        getImpl().hideMotionSpec = createFromAttribute2;
        getImpl().ensureMinTouchTargetSize = z;
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i) {
    }

    @Override // android.view.View
    public final void setBackgroundDrawable(Drawable drawable) {
    }

    @Override // android.view.View
    public final void setBackgroundResource(int i) {
    }
}

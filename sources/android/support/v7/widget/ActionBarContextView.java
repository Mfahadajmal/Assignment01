package android.support.v7.widget;

import android.content.Context;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.settingslib.widget.MainSwitchPreference;
import com.google.android.accessibility.accessibilitymenu.view.A11yMenuFooter;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ActionBarContextView extends AbsActionBarView {
    public View mClose;
    private View mCloseButton;
    private int mCloseItemLayout;
    private View mCustomView;
    public CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    public CharSequence mTitle;
    private LinearLayout mTitleLayout;
    public boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    /* compiled from: PG */
    /* renamed from: android.support.v7.widget.ActionBarContextView$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ Object ActionBarContextView$1$ar$val$mode;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.ActionBarContextView$1$ar$val$mode = obj;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            Message message4;
            switch (this.switching_field) {
                case 0:
                    ((ActionMode) this.ActionBarContextView$1$ar$val$mode).finish();
                    return;
                case 1:
                    AlertController alertController = (AlertController) this.ActionBarContextView$1$ar$val$mode;
                    if (view == alertController.mButtonPositive && (message4 = alertController.mButtonPositiveMessage) != null) {
                        message = Message.obtain(message4);
                    } else if (view == alertController.mButtonNegative && (message3 = alertController.mButtonNegativeMessage) != null) {
                        message = Message.obtain(message3);
                    } else if (view == alertController.mButtonNeutral && (message2 = alertController.mButtonNeutralMessage) != null) {
                        message = Message.obtain(message2);
                    } else {
                        message = null;
                    }
                    if (message != null) {
                        message.sendToTarget();
                    }
                    AlertController alertController2 = (AlertController) this.ActionBarContextView$1$ar$val$mode;
                    alertController2.mHandler.obtainMessage(1, alertController2.mDialog).sendToTarget();
                    return;
                case 2:
                    ((Toolbar) this.ActionBarContextView$1$ar$val$mode).collapseActionView();
                    return;
                case 3:
                    ((Preference) this.ActionBarContextView$1$ar$val$mode).performClick(view);
                    return;
                case 4:
                    Object obj = this.ActionBarContextView$1$ar$val$mode;
                    boolean isChecked = ((TwoStatePreference) obj).isChecked();
                    if (!((Preference) obj).callChangeListener(Boolean.valueOf(isChecked))) {
                        ((MainSwitchPreference) obj).mMainSwitchBar.setChecked(!isChecked);
                        return;
                    }
                    return;
                case 5:
                    if (view.getId() == R.id.menu_prev_button) {
                        ((A11yMenuFooter) this.ActionBarContextView$1$ar$val$mode).callBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onLeftButtonClicked();
                        return;
                    } else {
                        if (view.getId() == R.id.menu_next_button) {
                            ((A11yMenuFooter) this.ActionBarContextView$1$ar$val$mode).callBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onRightButtonClicked();
                            return;
                        }
                        return;
                    }
                case 6:
                    ((OverlayDisplay.DisplayThreadHandler) ((OverlayDisplay) this.ActionBarContextView$1$ar$val$mode).OverlayDisplay$ar$displayThreadHandler).sendInputEvent(new BrailleInputEvent(5, 0, SystemClock.uptimeMillis()));
                    return;
                case 7:
                    ((OverlayDisplay.DisplayThreadHandler) ((OverlayDisplay) this.ActionBarContextView$1$ar$val$mode).OverlayDisplay$ar$displayThreadHandler).sendInputEvent(new BrailleInputEvent(6, 0, SystemClock.uptimeMillis()));
                    return;
                case 8:
                    ((BrailleDisplaySettingsFragment) this.ActionBarContextView$1$ar$val$mode).m50x9900da89(view);
                    return;
                case 9:
                    ((BrailleDisplaySettingsFragment) this.ActionBarContextView$1$ar$val$mode).m51xf86f428b(view);
                    return;
                case 10:
                    ((BrailleDisplaySettingsFragment) this.ActionBarContextView$1$ar$val$mode).m52x2826768c(view);
                    return;
                case 11:
                    ((BrailleDisplaySettingsFragment) this.ActionBarContextView$1$ar$val$mode).m53x57ddaa8d(view);
                    return;
                case 12:
                    ((BrailleDisplaySettingsFragment) this.ActionBarContextView$1$ar$val$mode).m54x8794de8e(view);
                    return;
                case 13:
                    BrailleDisplaySettingsFragment.DevicePreference devicePreference = (BrailleDisplaySettingsFragment.DevicePreference) this.ActionBarContextView$1$ar$val$mode;
                    String str = devicePreference.rowDevice.deviceAddress;
                    Connectioneer.AspectConnection aspectConnection = BrailleDisplaySettingsFragment.this.aspectConnection;
                    aspectConnection.connectioneer.userDisconnectedDevices.add(str);
                    aspectConnection.disconnectFromDevice(str);
                    BrailleDisplaySettingsFragment.this.onModelChanged();
                    devicePreference.dismissConnectionDeviceDetailDialog();
                    return;
                case 14:
                    BrailleDisplaySettingsFragment.DevicePreference devicePreference2 = (BrailleDisplaySettingsFragment.DevicePreference) this.ActionBarContextView$1$ar$val$mode;
                    BrailleDisplaySettingsFragment.this.aspectConnection.onUserChoseConnectDevice(devicePreference2.rowDevice.device);
                    BrailleDisplaySettingsFragment.this.onModelChanged();
                    devicePreference2.dismissConnectionDeviceDetailDialog();
                    return;
                case 15:
                    Object obj2 = this.ActionBarContextView$1$ar$val$mode;
                    BrailleDisplaySettingsFragment.DevicePreference devicePreference3 = (BrailleDisplaySettingsFragment.DevicePreference) obj2;
                    ConnectableDevice connectableDevice = devicePreference3.rowDevice.device;
                    AppCompatDelegate.Api24Impl.deleteRememberedDevice(((Preference) obj2).getContext(), connectableDevice.address());
                    Connectioneer.AspectConnection aspectConnection2 = BrailleDisplaySettingsFragment.this.aspectConnection;
                    aspectConnection2.connectioneer.connectManagerProxy.forget(connectableDevice);
                    aspectConnection2.disconnectFromDevice(connectableDevice.address());
                    BrailleDisplaySettingsFragment.this.onModelChanged();
                    devicePreference3.dismissConnectionDeviceDetailDialog();
                    return;
                case 16:
                    ((BrailleDisplaySettingsFragment.DevicePreference) this.ActionBarContextView$1$ar$val$mode).dismissConnectionDeviceDetailDialog();
                    return;
                case 17:
                    Preference preference = (Preference) this.ActionBarContextView$1$ar$val$mode;
                    BrailleUserPreferences.removePreferredCode(preference.getContext(), BrailleLanguages.Code.valueOf(preference.getKey()));
                    preference.getParent().removePreference$ar$ds(preference);
                    return;
                case 18:
                    ((RetryingNameResolver.ResolutionResultListener) this.ActionBarContextView$1$ar$val$mode).onSwitchToOnscreenKeyboard();
                    return;
                case 19:
                    ((BrailleIme) ((RetryingNameResolver.ResolutionResultListener) this.ActionBarContextView$1$ar$val$mode).RetryingNameResolver$ResolutionResultListener$ar$this$0).switchToNextInputMethod();
                    return;
                default:
                    ((TutorialView.Intro) this.ActionBarContextView$1$ar$val$mode).m85x247088b4();
                    return;
            }
        }

        public /* synthetic */ AnonymousClass1(Object obj, int i, byte[] bArr) {
            this.switching_field = i;
            this.ActionBarContextView$1$ar$val$mode = obj;
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    private final void initTitle() {
        int i;
        if (this.mTitleLayout == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleLayout = linearLayout;
            this.mTitleView = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(R.id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean z = !TextUtils.isEmpty(this.mTitle);
        boolean isEmpty = TextUtils.isEmpty(this.mSubtitle);
        boolean z2 = !isEmpty;
        TextView textView = this.mSubtitleView;
        int i2 = 8;
        if (true != isEmpty) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        LinearLayout linearLayout2 = this.mTitleLayout;
        if (z || z2) {
            i2 = 0;
        }
        linearLayout2.setVisibility(i2);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public final void initForMode(ActionMode actionMode) {
        View view = this.mClose;
        if (view == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, (ViewGroup) this, false);
            this.mClose = inflate;
            addView(inflate);
        } else if (view.getParent() == null) {
            addView(this.mClose);
        }
        View findViewById = this.mClose.findViewById(R.id.action_mode_close_button);
        this.mCloseButton = findViewById;
        findViewById.setOnClickListener(new AnonymousClass1(actionMode, 0));
        Menu menu = actionMode.getMenu();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus$ar$ds();
        }
        this.mActionMenuPresenter = new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter.setReserveOverflow$ar$ds();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        ((MenuBuilder) menu).addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
        this.mMenuView.setBackground(null);
        addView(this.mMenuView, layoutParams);
    }

    public final void killMode() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
        this.mActionMenuPresenter = null;
        View view = this.mCloseButton;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    @Override // android.support.v7.widget.AbsActionBarView, android.view.View
    public final /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft;
        int paddingRight;
        int i5;
        int i6;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        if (isLayoutRtl) {
            paddingLeft = (i3 - i) - getPaddingRight();
        } else {
            paddingLeft = getPaddingLeft();
        }
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.mClose;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
            if (isLayoutRtl) {
                i5 = marginLayoutParams.rightMargin;
            } else {
                i5 = marginLayoutParams.leftMargin;
            }
            if (isLayoutRtl) {
                i6 = marginLayoutParams.leftMargin;
            } else {
                i6 = marginLayoutParams.rightMargin;
            }
            int next = next(paddingLeft, i5, isLayoutRtl);
            paddingLeft = next(next + positionChild$ar$ds(this.mClose, next, paddingTop, paddingTop2, isLayoutRtl), i6, isLayoutRtl);
        }
        LinearLayout linearLayout = this.mTitleLayout;
        if (linearLayout != null && this.mCustomView == null && linearLayout.getVisibility() != 8) {
            paddingLeft += positionChild$ar$ds(this.mTitleLayout, paddingLeft, paddingTop, paddingTop2, isLayoutRtl);
        }
        View view2 = this.mCustomView;
        if (view2 != null) {
            positionChild$ar$ds(view2, paddingLeft, paddingTop, paddingTop2, isLayoutRtl);
        }
        if (isLayoutRtl) {
            paddingRight = getPaddingLeft();
        } else {
            paddingRight = (i3 - i) - getPaddingRight();
        }
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            positionChild$ar$ds(actionMenuView, paddingRight, paddingTop, paddingTop2, !isLayoutRtl);
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        boolean z;
        int i4;
        int i5 = 1073741824;
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            if (View.MeasureSpec.getMode(i2) != 0) {
                int size = View.MeasureSpec.getSize(i);
                int i6 = this.mContentHeight;
                if (i6 <= 0) {
                    i6 = View.MeasureSpec.getSize(i2);
                }
                int paddingTop = getPaddingTop() + getPaddingBottom();
                int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
                int i7 = i6 - paddingTop;
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, Integer.MIN_VALUE);
                View view = this.mClose;
                if (view != null) {
                    int measureChildView$ar$ds = measureChildView$ar$ds(view, paddingLeft, makeMeasureSpec);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
                    paddingLeft = measureChildView$ar$ds - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
                }
                ActionMenuView actionMenuView = this.mMenuView;
                if (actionMenuView != null && actionMenuView.getParent() == this) {
                    paddingLeft = measureChildView$ar$ds(this.mMenuView, paddingLeft, makeMeasureSpec);
                }
                LinearLayout linearLayout = this.mTitleLayout;
                if (linearLayout != null && this.mCustomView == null) {
                    if (this.mTitleOptional) {
                        this.mTitleLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                        int measuredWidth = this.mTitleLayout.getMeasuredWidth();
                        if (measuredWidth <= paddingLeft) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            paddingLeft -= measuredWidth;
                        }
                        LinearLayout linearLayout2 = this.mTitleLayout;
                        if (true != z) {
                            i4 = 8;
                        } else {
                            i4 = 0;
                        }
                        linearLayout2.setVisibility(i4);
                    } else {
                        paddingLeft = measureChildView$ar$ds(linearLayout, paddingLeft, makeMeasureSpec);
                    }
                }
                View view2 = this.mCustomView;
                if (view2 != null) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if (layoutParams.width != -2) {
                        i3 = 1073741824;
                    } else {
                        i3 = Integer.MIN_VALUE;
                    }
                    if (layoutParams.width >= 0) {
                        paddingLeft = Math.min(layoutParams.width, paddingLeft);
                    }
                    if (layoutParams.height == -2) {
                        i5 = Integer.MIN_VALUE;
                    }
                    if (layoutParams.height >= 0) {
                        i7 = Math.min(layoutParams.height, i7);
                    }
                    this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i3), View.MeasureSpec.makeMeasureSpec(i7, i5));
                }
                if (this.mContentHeight <= 0) {
                    int childCount = getChildCount();
                    int i8 = 0;
                    for (int i9 = 0; i9 < childCount; i9++) {
                        int measuredHeight = getChildAt(i9).getMeasuredHeight() + paddingTop;
                        if (measuredHeight > i8) {
                            i8 = measuredHeight;
                        }
                    }
                    setMeasuredDimension(size, i8);
                    return;
                }
                setMeasuredDimension(size, i6);
                return;
            }
            throw new IllegalStateException(String.valueOf(getClass().getSimpleName()).concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        throw new IllegalStateException(String.valueOf(getClass().getSimpleName()).concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
    }

    @Override // android.support.v7.widget.AbsActionBarView, android.view.View
    public final /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.support.v7.widget.AbsActionBarView
    public final void setContentHeight(int i) {
        this.mContentHeight = i;
    }

    public final void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.mCustomView;
        if (view2 != null) {
            removeView(view2);
        }
        this.mCustomView = view;
        if (view != null && (linearLayout = this.mTitleLayout) != null) {
            removeView(linearLayout);
            this.mTitleLayout = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public final void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        initTitle();
    }

    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        initTitle();
        ViewCompat.setAccessibilityPaneTitle(this, charSequence);
    }

    public final void setTitleOptional(boolean z) {
        if (z != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = z;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public final void showOverflowMenu$ar$ds() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.showOverflowMenu();
        }
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(context, attributeSet, R$styleable.ActionMode, i, 0);
        setBackground(obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(0));
        this.mTitleStyleRes = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(5, 0);
        this.mSubtitleStyleRes = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(4, 0);
        this.mContentHeight = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getLayoutDimension(3, 0);
        this.mCloseItemLayout = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(2, R.layout.abc_action_mode_close_item_material);
        obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
    }
}

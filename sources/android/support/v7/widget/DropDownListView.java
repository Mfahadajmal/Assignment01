package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4;
import android.support.v7.graphics.drawable.DrawableWrapperCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter$Resolver;
import androidx.concurrent.futures.CallbackToFutureAdapter$SafeFuture;
import androidx.core.widget.AutoScrollHelper;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DropDownListView extends ListView {
    private boolean mDrawsInPressedState;
    private final boolean mHijackFocus;
    public boolean mListSelectionHidden;
    private int mMotionPosition;
    public DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 mResolveHoverRunnable$ar$class_merging;
    private AutoScrollHelper mScrollHelper$ar$class_merging;
    private int mSelectionBottomPadding;
    private int mSelectionLeftPadding;
    private int mSelectionRightPadding;
    private int mSelectionTopPadding;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api21Impl {
        static void drawableHotspotChanged(View view, float f, float f2) {
            view.drawableHotspotChanged(f, f2);
        }

        public static /* synthetic */ String toStringGenerated23ac2ca7ba239ff5(int i) {
            switch (i) {
                case 1:
                    return "USER_CHOSE_CONNECT_DEVICE";
                case 2:
                    return "BONDED_BROADCAST";
                case 3:
                    return "AUTO_CONNECT_DEVICE_SEEN";
                case 4:
                    return "AUTO_CONNECT_BONDED_REMEMBERED_BD_ENABLED";
                case 5:
                    return "AUTO_CONNECT_BONDED_REMEMBERED_AUTO_CONNECT_ENABLED";
                case 6:
                    return "AUTO_CONNECT_BONDED_REMEMBERED_BT_TURNED_ON";
                case 7:
                    return "AUTO_CONNECT_BONDED_REMEMBERED_SCREEN_ON";
                case 8:
                    return "AUTO_CONNECT_USB_UNPLUGGED";
                default:
                    return "AUTO_CONNECT_USB_PLUGGED";
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Api30Impl {
        public static boolean sHasMethods;
        public static Method sPositionSelector;
        public static Method sSetNextSelectedPositionInt;
        public static Method sSetSelectedPositionInt;

        static {
            try {
                Class cls = Float.TYPE;
                Method declaredMethod = AbsListView.class.getDeclaredMethod("positionSelector", Integer.TYPE, View.class, Boolean.TYPE, cls, cls);
                sPositionSelector = declaredMethod;
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = AdapterView.class.getDeclaredMethod("setSelectedPositionInt", Integer.TYPE);
                sSetSelectedPositionInt = declaredMethod2;
                declaredMethod2.setAccessible(true);
                Method declaredMethod3 = AdapterView.class.getDeclaredMethod("setNextSelectedPositionInt", Integer.TYPE);
                sSetNextSelectedPositionInt = declaredMethod3;
                declaredMethod3.setAccessible(true);
                sHasMethods = true;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api33Impl {
        public static ListenableFuture getFuture(CallbackToFutureAdapter$Resolver callbackToFutureAdapter$Resolver) {
            CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer = new CallbackToFutureAdapter$Completer();
            CallbackToFutureAdapter$SafeFuture callbackToFutureAdapter$SafeFuture = new CallbackToFutureAdapter$SafeFuture(callbackToFutureAdapter$Completer);
            callbackToFutureAdapter$Completer.future = callbackToFutureAdapter$SafeFuture;
            callbackToFutureAdapter$Completer.tag = callbackToFutureAdapter$Resolver.getClass();
            try {
                Object attachCompleter = callbackToFutureAdapter$Resolver.attachCompleter(callbackToFutureAdapter$Completer);
                if (attachCompleter != null) {
                    callbackToFutureAdapter$Completer.tag = attachCompleter;
                }
            } catch (Exception e) {
                callbackToFutureAdapter$SafeFuture.setException(e);
            }
            return callbackToFutureAdapter$SafeFuture;
        }

        static boolean isSelectedChildViewEnabled(AbsListView absListView) {
            boolean isSelectedChildViewEnabled;
            isSelectedChildViewEnabled = absListView.isSelectedChildViewEnabled();
            return isSelectedChildViewEnabled;
        }

        static void setSelectedChildViewEnabled(AbsListView absListView, boolean z) {
            absListView.setSelectedChildViewEnabled(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GateKeeperDrawable extends DrawableWrapperCompat {
        public boolean mEnabled;

        public GateKeeperDrawable(Drawable drawable) {
            super(drawable);
            this.mEnabled = true;
        }

        @Override // android.support.v7.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public final void draw(Canvas canvas) {
            if (this.mEnabled) {
                super.draw(canvas);
            }
        }

        @Override // android.support.v7.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public final void setHotspot(float f, float f2) {
            if (this.mEnabled) {
                super.setHotspot(f, f2);
            }
        }

        @Override // android.support.v7.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public final void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.mEnabled) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        @Override // android.support.v7.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public final boolean setState(int[] iArr) {
            if (this.mEnabled) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // android.support.v7.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public final boolean setVisible(boolean z, boolean z2) {
            if (this.mEnabled) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class PreApi33Impl {
        public static final Field sIsChildViewEnabled;

        static {
            NoSuchFieldException e;
            Field field;
            try {
                field = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            } catch (NoSuchFieldException e2) {
                e = e2;
                field = null;
            }
            try {
                field.setAccessible(true);
            } catch (NoSuchFieldException e3) {
                e = e3;
                e.printStackTrace();
                sIsChildViewEnabled = field;
            }
            sIsChildViewEnabled = field;
        }
    }

    public DropDownListView(Context context, boolean z) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.mSelectorRect = new Rect();
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mHijackFocus = z;
        setCacheColorHint(0);
    }

    private final void setSelectorEnabled(boolean z) {
        GateKeeperDrawable gateKeeperDrawable = this.mSelector;
        if (gateKeeperDrawable != null) {
            gateKeeperDrawable.mEnabled = z;
        }
    }

    private final void updateSelectorStateCompat() {
        Drawable selector = getSelector();
        if (selector != null && this.mDrawsInPressedState && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        if (!this.mSelectorRect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.mSelectorRect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        if (this.mResolveHoverRunnable$ar$class_merging != null) {
            return;
        }
        super.drawableStateChanged();
        setSelectorEnabled(true);
        updateSelectorStateCompat();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean hasFocus() {
        if (!this.mHijackFocus && !super.hasFocus()) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean hasWindowFocus() {
        if (!this.mHijackFocus && !super.hasWindowFocus()) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean isFocused() {
        if (!this.mHijackFocus && !super.isFocused()) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean isInTouchMode() {
        if ((this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode()) {
            return true;
        }
        return false;
    }

    public int measureHeightOfChildrenCompat$ar$ds(int i, int i2) {
        int i3;
        int makeMeasureSpec;
        int listPaddingTop = getListPaddingTop() + getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop;
        }
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i4 = 0;
        int i5 = 0;
        View view = null;
        while (i4 < count) {
            int itemViewType = adapter.getItemViewType(i4);
            if (itemViewType != i5) {
                i3 = itemViewType;
            } else {
                i3 = i5;
            }
            if (itemViewType != i5) {
                view = null;
            }
            view = adapter.getView(i4, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            if (layoutParams.height > 0) {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            } else {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, makeMeasureSpec);
            view.forceLayout();
            if (i4 > 0) {
                listPaddingTop += dividerHeight;
            }
            listPaddingTop += view.getMeasuredHeight();
            if (listPaddingTop >= i2) {
                return i2;
            }
            i4++;
            i5 = i3;
        }
        return listPaddingTop;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        this.mResolveHoverRunnable$ar$class_merging = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x014a A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onForwardedEvent(android.view.MotionEvent r17, int r18) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.DropDownListView.onForwardedEvent(android.view.MotionEvent, int):boolean");
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10) {
            if (this.mResolveHoverRunnable$ar$class_merging == null) {
                DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 14, null);
                this.mResolveHoverRunnable$ar$class_merging = defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4;
                ((DropDownListView) defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4$ar$f$0).post(defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4);
            }
            actionMasked = 10;
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked != 9 && actionMasked != 7) {
            setSelection(-1);
        } else {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (pointToPosition != -1 && pointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    requestFocus();
                    if (Build.VERSION.SDK_INT >= 30 && Api30Impl.sHasMethods) {
                        try {
                            Method method = Api30Impl.sPositionSelector;
                            Integer valueOf = Integer.valueOf(pointToPosition);
                            method.invoke(this, valueOf, childAt, false, -1, -1);
                            Api30Impl.sSetSelectedPositionInt.invoke(this, valueOf);
                            Api30Impl.sSetNextSelectedPositionInt.invoke(this, valueOf);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                    }
                }
                updateSelectorStateCompat();
            }
        }
        return onHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mMotionPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 = this.mResolveHoverRunnable$ar$class_merging;
        if (defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 != null) {
            DropDownListView dropDownListView = (DropDownListView) defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4$ar$f$0;
            dropDownListView.mResolveHoverRunnable$ar$class_merging = null;
            dropDownListView.removeCallbacks(defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsListView
    public final void setSelector(Drawable drawable) {
        GateKeeperDrawable gateKeeperDrawable;
        if (drawable != null) {
            gateKeeperDrawable = new GateKeeperDrawable(drawable);
        } else {
            gateKeeperDrawable = null;
        }
        this.mSelector = gateKeeperDrawable;
        super.setSelector(gateKeeperDrawable);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.mSelectionLeftPadding = rect.left;
        this.mSelectionTopPadding = rect.top;
        this.mSelectionRightPadding = rect.right;
        this.mSelectionBottomPadding = rect.bottom;
    }
}

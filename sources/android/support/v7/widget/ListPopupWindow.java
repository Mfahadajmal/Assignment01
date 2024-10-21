package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.core.widget.PopupWindowCompat$Api23Impl;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId;
import com.google.android.material.textfield.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ListPopupWindow implements ShowableListMenu {
    private static Method sSetClipToWindowEnabledMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    public View mDropDownAnchorView;
    public int mDropDownGravity;
    private int mDropDownHeight;
    public int mDropDownHorizontalOffset;
    public DropDownListView mDropDownList;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    public int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    public final Handler mHandler;
    private final DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 mHideSelector$ar$class_merging;
    public AdapterView.OnItemClickListener mItemClickListener;
    public AdapterView.OnItemSelectedListener mItemSelectedListener;
    public int mListItemExpandMaximum;
    public boolean mModal;
    private DataSetObserver mObserver;
    public boolean mOverlapAnchor;
    public boolean mOverlapAnchorSet;
    public PopupWindow mPopup;
    public final DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 mResizePopupRunnable$ar$class_merging;
    private final PopupScrollListener mScrollListener;
    private final Rect mTempRect;
    private final DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1 mTouchInterceptor$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api24Impl {
        static int getMaxAvailableHeight(PopupWindow popupWindow, View view, int i, boolean z) {
            int maxAvailableHeight;
            maxAvailableHeight = popupWindow.getMaxAvailableHeight(view, i, z);
            return maxAvailableHeight;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        /* JADX WARN: Code restructure failed: missing block: B:151:0x02a0, code lost:
        
            if (r4.mOwner == r7) goto L170;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x0119, code lost:
        
            if (r6.mOwner == r5) goto L63;
         */
        /* JADX WARN: Removed duplicated region for block: B:182:0x0321  */
        /* JADX WARN: Removed duplicated region for block: B:185:0x033c  */
        /* JADX WARN: Removed duplicated region for block: B:194:0x0357  */
        /* JADX WARN: Removed duplicated region for block: B:229:0x0458 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:247:0x0715 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:253:0x0723  */
        /* JADX WARN: Removed duplicated region for block: B:256:0x072e  */
        /* JADX WARN: Removed duplicated region for block: B:259:0x0737  */
        /* JADX WARN: Removed duplicated region for block: B:261:0x073e  */
        /* JADX WARN: Removed duplicated region for block: B:266:0x074d  */
        /* JADX WARN: Removed duplicated region for block: B:268:0x0751 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:272:0x076f A[ADDED_TO_REGION, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:273:0x073a  */
        /* JADX WARN: Removed duplicated region for block: B:274:0x0731  */
        /* JADX WARN: Removed duplicated region for block: B:275:0x0725  */
        /* JADX WARN: Removed duplicated region for block: B:284:0x04be  */
        /* JADX WARN: Removed duplicated region for block: B:304:0x05b1  */
        /* JADX WARN: Removed duplicated region for block: B:307:0x05b3  */
        /* JADX WARN: Removed duplicated region for block: B:320:0x054f  */
        /* JADX WARN: Removed duplicated region for block: B:355:0x05d2 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:422:0x05ca  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static void applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r40, androidx.constraintlayout.core.LinearSystem r41, java.util.ArrayList r42, int r43) {
            /*
                Method dump skipped, instructions count: 1917
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ListPopupWindow.Api29Impl.applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, java.util.ArrayList, int):void");
        }

        static void setEpicenterBounds(PopupWindow popupWindow, Rect rect) {
            popupWindow.setEpicenterBounds(rect);
        }

        static void setIsClippedToScreen(PopupWindow popupWindow, boolean z) {
            popupWindow.setIsClippedToScreen(z);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class PopupDataSetObserver extends DataSetObserver {
        public PopupDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public final void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                sSetClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
            }
            try {
                sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
            }
        }
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, null);
    }

    public final void clearListSelection() {
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.mListSelectionHidden = true;
            dropDownListView.requestLayout();
        }
    }

    public DropDownListView createDropDownListView(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final void dismiss() {
        this.mPopup.dismiss();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable$ar$class_merging);
    }

    public final Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public final int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final ListView getListView() {
        return this.mDropDownList;
    }

    public final int getSelectedItemPosition() {
        if (!isShowing()) {
            return -1;
        }
        return this.mDropDownList.getSelectedItemPosition();
    }

    public final int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    public final boolean isInputMethodNotNeeded() {
        if (this.mPopup.getInputMethodMode() == 2) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public void setAdapter(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.mObserver;
        if (dataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.mAdapter);
        }
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public final void setContentWidth(int i) {
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            Rect rect = this.mTempRect;
            this.mDropDownWidth = rect.left + rect.right + i;
            return;
        }
        this.mDropDownWidth = i;
    }

    public final void setEpicenterBounds(Rect rect) {
        Rect rect2;
        if (rect != null) {
            rect2 = new Rect(rect);
        } else {
            rect2 = null;
        }
        this.mEpicenterBounds = rect2;
    }

    public final void setHorizontalOffset(int i) {
        this.mDropDownHorizontalOffset = i;
    }

    public final void setInputMethodMode$ar$ds() {
        this.mPopup.setInputMethodMode(2);
    }

    public final void setModal$ar$ds() {
        this.mModal = true;
        this.mPopup.setFocusable(true);
    }

    public final void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    public final void setVerticalOffset(int i) {
        this.mDropDownVerticalOffset = i;
        this.mDropDownVerticalOffsetSet = true;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final void show() {
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        int makeMeasureSpec;
        if (this.mDropDownList == null) {
            DropDownListView createDropDownListView = createDropDownListView(this.mContext, !this.mModal);
            this.mDropDownList = createDropDownListView;
            createDropDownListView.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new DropDownPreference.AnonymousClass1(this, 1));
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.mItemSelectedListener;
            if (onItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(onItemSelectedListener);
            }
            this.mPopup.setContentView(this.mDropDownList);
        }
        Drawable background = this.mPopup.getBackground();
        int i5 = 0;
        if (background != null) {
            background.getPadding(this.mTempRect);
            Rect rect = this.mTempRect;
            i = rect.top + rect.bottom;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
            }
        } else {
            this.mTempRect.setEmpty();
            i = 0;
        }
        if (this.mPopup.getInputMethodMode() == 2) {
            z = true;
        } else {
            z = false;
        }
        int maxAvailableHeight = Api24Impl.getMaxAvailableHeight(this.mPopup, this.mDropDownAnchorView, this.mDropDownVerticalOffset, z);
        if (this.mDropDownHeight != -1) {
            int i6 = this.mDropDownWidth;
            if (i6 != -2) {
                if (i6 != -1) {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
                } else {
                    int i7 = this.mContext.getResources().getDisplayMetrics().widthPixels;
                    Rect rect2 = this.mTempRect;
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7 - (rect2.left + rect2.right), 1073741824);
                }
            } else {
                int i8 = this.mContext.getResources().getDisplayMetrics().widthPixels;
                Rect rect3 = this.mTempRect;
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect3.left + rect3.right), Integer.MIN_VALUE);
            }
            maxAvailableHeight = this.mDropDownList.measureHeightOfChildrenCompat$ar$ds(makeMeasureSpec, maxAvailableHeight);
            if (maxAvailableHeight > 0) {
                i += this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom();
            } else {
                i = 0;
            }
        }
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        PopupWindowCompat$Api23Impl.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        int i9 = maxAvailableHeight + i;
        if (this.mPopup.isShowing()) {
            if (this.mDropDownAnchorView.isAttachedToWindow()) {
                int i10 = this.mDropDownWidth;
                if (i10 == -1) {
                    i10 = -1;
                } else if (i10 == -2) {
                    i10 = this.mDropDownAnchorView.getWidth();
                }
                int i11 = this.mDropDownHeight;
                if (i11 == -1) {
                    if (true != isInputMethodNotNeeded) {
                        i9 = -1;
                    }
                    if (isInputMethodNotNeeded) {
                        PopupWindow popupWindow = this.mPopup;
                        if (this.mDropDownWidth == -1) {
                            i4 = -1;
                        } else {
                            i4 = 0;
                        }
                        popupWindow.setWidth(i4);
                        this.mPopup.setHeight(0);
                    } else {
                        PopupWindow popupWindow2 = this.mPopup;
                        if (this.mDropDownWidth == -1) {
                            i5 = -1;
                        }
                        popupWindow2.setWidth(i5);
                        this.mPopup.setHeight(-1);
                    }
                } else if (i11 != -2) {
                    i9 = i11;
                }
                this.mPopup.setOutsideTouchable(true);
                PopupWindow popupWindow3 = this.mPopup;
                View view = this.mDropDownAnchorView;
                int i12 = this.mDropDownHorizontalOffset;
                int i13 = this.mDropDownVerticalOffset;
                if (i10 < 0) {
                    i2 = -1;
                } else {
                    i2 = i10;
                }
                if (i9 < 0) {
                    i3 = -1;
                } else {
                    i3 = i9;
                }
                popupWindow3.update(view, i12, i13, i2, i3);
                return;
            }
            return;
        }
        int i14 = this.mDropDownWidth;
        if (i14 == -1) {
            i14 = -1;
        } else if (i14 == -2) {
            i14 = this.mDropDownAnchorView.getWidth();
        }
        int i15 = this.mDropDownHeight;
        if (i15 == -1) {
            i9 = -1;
        } else if (i15 != -2) {
            i9 = i15;
        }
        this.mPopup.setWidth(i14);
        this.mPopup.setHeight(i9);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = sSetClipToWindowEnabledMethod;
            if (method != null) {
                try {
                    method.invoke(this.mPopup, true);
                } catch (Exception unused) {
                }
            }
        } else {
            Api29Impl.setIsClippedToScreen(this.mPopup, true);
        }
        this.mPopup.setOutsideTouchable(true);
        this.mPopup.setTouchInterceptor(this.mTouchInterceptor$ar$class_merging);
        if (this.mOverlapAnchorSet) {
            PopupWindowCompat$Api23Impl.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = sSetEpicenterBoundsMethod;
            if (method2 != null) {
                try {
                    method2.invoke(this.mPopup, this.mEpicenterBounds);
                } catch (Exception e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
        } else {
            Api29Impl.setEpicenterBounds(this.mPopup, this.mEpicenterBounds);
        }
        this.mPopup.showAsDropDown(this.mDropDownAnchorView, this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
        this.mDropDownList.setSelection(-1);
        if (!this.mModal || this.mDropDownList.isInTouchMode()) {
            clearListSelection();
        }
        if (!this.mModal) {
            this.mHandler.post(this.mHideSelector$ar$class_merging);
        }
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, byte[] bArr) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = TrainingProto$TrainingPageId.PAGE_ID_UPDATE_WELCOME_WITHOUT_TYPO_CORRECTION$ar$edu;
        this.mDropDownGravity = 0;
        this.mListItemExpandMaximum = Preference.DEFAULT_ORDER;
        this.mResizePopupRunnable$ar$class_merging = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 19, null);
        this.mTouchInterceptor$ar$class_merging = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1(this, 1);
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector$ar$class_merging = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 18, null);
        this.mTempRect = new Rect();
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i, 0);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.mDropDownVerticalOffset = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i);
        this.mPopup = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PopupScrollListener implements AbsListView.OnScrollListener {
        public PopupScrollListener() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public final void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.mHandler.removeCallbacks(listPopupWindow.mResizePopupRunnable$ar$class_merging);
                ListPopupWindow.this.mResizePopupRunnable$ar$class_merging.run();
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }
    }
}

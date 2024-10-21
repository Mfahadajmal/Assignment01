package com.google.android.accessibility.selecttospeak.ui;

import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2SWindowOverlay implements SelectToSpeakOverlay {
    public final ViewGroup contentView;
    private final Context context;
    private int maxWindowX;
    private int maxWindowY;
    public View.OnTouchListener onTouchListener;
    public final WindowManager.LayoutParams params;
    public final WindowManager windowManager;
    public boolean isVisible = false;
    public CharSequence rootViewClassName = null;

    public S2SWindowOverlay(Context context, int i, int i2, int i3) {
        LogUtils.v("S2SWindowOverlay", "construct: %s", Integer.valueOf(i));
        this.context = context;
        this.windowManager = (WindowManager) context.getSystemService("window");
        FrameLayout frameLayout = new FrameLayout(SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(context)) { // from class: com.google.android.accessibility.selecttospeak.ui.S2SWindowOverlay.1
            @Override // android.view.ViewGroup, android.view.View
            public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
                motionEvent.offsetLocation(-getTranslationX(), -getTranslationY());
                return super.dispatchHoverEvent(motionEvent);
            }

            @Override // android.view.ViewGroup, android.view.View
            public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
                motionEvent.offsetLocation(-getTranslationX(), -getTranslationY());
                View.OnTouchListener onTouchListener = S2SWindowOverlay.this.onTouchListener;
                if (onTouchListener != null && onTouchListener.onTouch(this, motionEvent)) {
                    return true;
                }
                return super.dispatchTouchEvent(motionEvent);
            }

            @Override // android.view.View
            public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
                CharSequence charSequence = S2SWindowOverlay.this.rootViewClassName;
                if (charSequence != null) {
                    accessibilityNodeInfo.setClassName(charSequence);
                }
            }
        };
        this.contentView = frameLayout;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.params = layoutParams;
        layoutParams.type = 2032;
        layoutParams.format = -2;
        layoutParams.flags |= 520;
        layoutParams.gravity = 8388659;
        layoutParams.width = i2;
        layoutParams.height = i3;
        layoutParams.setTitle(context.getString(R.string.s2s_service_name));
        frameLayout.removeAllViews();
        LayoutInflater.from(SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(context)).inflate(i, frameLayout);
        updateScreenBounds();
    }

    private final Point getMaximumDisplaySize() {
        int i;
        WindowMetrics currentWindowMetrics;
        WindowInsets windowInsets;
        Insets insets;
        int i2;
        int i3;
        int i4;
        int i5;
        Point screenPixelSizeWithoutWindowDecor = SpannableUtils$IdentifierSpan.getScreenPixelSizeWithoutWindowDecor(this.context);
        if (Build.VERSION.SDK_INT >= 35) {
            currentWindowMetrics = this.windowManager.getCurrentWindowMetrics();
            windowInsets = currentWindowMetrics.getWindowInsets();
            insets = windowInsets.getInsets(135);
            i2 = insets.left;
            i3 = insets.right;
            int i6 = i2 + i3;
            i4 = insets.top;
            i5 = insets.bottom;
            Point point = new Point(i6, i4 + i5);
            screenPixelSizeWithoutWindowDecor.x -= point.x;
            screenPixelSizeWithoutWindowDecor.y -= point.y;
        } else if ((this.params.flags & 134217728) != 0) {
            Context context = this.context;
            int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                i = context.getResources().getDimensionPixelSize(identifier);
            } else {
                i = 0;
            }
            screenPixelSizeWithoutWindowDecor.y += i;
        }
        return screenPixelSizeWithoutWindowDecor;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final View findViewById(int i) {
        return this.contentView.findViewById(i);
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void fractionalToPixelCoordinates(float[] fArr, int[] iArr) {
        iArr[0] = (int) (fArr[0] * this.maxWindowX);
        iArr[1] = (int) (fArr[1] * this.maxWindowY);
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final int getMaxWindowX() {
        return this.maxWindowX;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final int getMaxWindowY() {
        return this.maxWindowY;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void getPixelCoordinates(int[] iArr) {
        iArr[0] = this.params.x + ((int) this.contentView.getTranslationX());
        iArr[1] = this.params.y + ((int) this.contentView.getTranslationY());
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void hide() {
        LogUtils.v("S2SWindowOverlay", "hide isvisible: %s contentView: %s", Boolean.valueOf(this.isVisible), this.contentView);
        if (!this.isVisible) {
            return;
        }
        this.windowManager.removeView(this.contentView);
        this.isVisible = false;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void pixelToFractionalCoordinates(int[] iArr, float[] fArr) {
        int i = this.maxWindowX;
        if (i == 0) {
            fArr[0] = 0.0f;
        } else {
            fArr[0] = iArr[0] / i;
        }
        int i2 = this.maxWindowY;
        if (i2 == 0) {
            fArr[1] = 0.0f;
        } else {
            fArr[1] = iArr[1] / i2;
        }
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void setPixelCoordinates(int i, int i2) {
        int i3 = this.maxWindowX;
        if (i > i3) {
            this.params.x = i3;
            this.contentView.setTranslationX(i - this.maxWindowX);
        } else if (i < 0) {
            this.params.x = 0;
            this.contentView.setTranslationX(i);
        } else {
            this.params.x = i;
            this.contentView.setTranslationX(0.0f);
        }
        int i4 = this.maxWindowY;
        if (i2 > i4) {
            this.params.y = i4;
            this.contentView.setTranslationY(i2 - this.maxWindowY);
        } else if (i2 < 0) {
            this.params.y = 0;
            this.contentView.setTranslationY(i2);
        } else {
            this.params.y = i2;
            this.contentView.setTranslationY(0.0f);
        }
        if (this.isVisible) {
            this.contentView.invalidate();
            this.windowManager.updateViewLayout(this.contentView, this.params);
        }
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void setWatchOutsideTouch$ar$ds() {
        this.params.flags |= 262144;
        if (this.isVisible) {
            this.windowManager.updateViewLayout(this.contentView, this.params);
        }
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void show() {
        LogUtils.v("S2SWindowOverlay", "show isvisible: %s contentView: %s", Boolean.valueOf(this.isVisible), this.contentView);
        if (this.isVisible) {
            return;
        }
        this.contentView.invalidate();
        this.windowManager.addView(this.contentView, this.params);
        this.isVisible = true;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void updateScreenBounds() {
        Point point;
        ViewGroup viewGroup = this.contentView;
        Point maximumDisplaySize = getMaximumDisplaySize();
        if (viewGroup.getWidth() != 0 && this.contentView.getHeight() != 0) {
            ViewGroup viewGroup2 = this.contentView;
            point = new Point(viewGroup2.getWidth(), viewGroup2.getHeight());
        } else {
            Point maximumDisplaySize2 = getMaximumDisplaySize();
            this.contentView.measure(maximumDisplaySize2.x, maximumDisplaySize2.y);
            point = new Point();
            if (this.params.width == -1) {
                point.x = maximumDisplaySize2.x;
            } else {
                point.x = this.contentView.getMeasuredWidth();
            }
            if (this.params.height == -1) {
                point.y = maximumDisplaySize2.y;
            } else {
                point.y = this.contentView.getMeasuredHeight();
            }
        }
        if (this.params.width == -1) {
            this.maxWindowX = 0;
        } else if (this.params.width == -2) {
            this.maxWindowX = Math.max(0, maximumDisplaySize.x - point.x);
        } else {
            this.maxWindowX = Math.max(0, maximumDisplaySize.x - this.params.width);
        }
        if (this.params.height == -1) {
            this.maxWindowY = 0;
        } else if (this.params.height == -2) {
            this.maxWindowY = Math.max(0, maximumDisplaySize.y - point.y);
        } else {
            this.maxWindowY = Math.max(0, maximumDisplaySize.y - this.params.height);
        }
    }
}
